package com.fosu.lesson.utils;

import com.alibaba.dubbo.config.annotation.Service;
import com.fosu.lesson.dao.TCourseMapper;
import com.fosu.lesson.dao.TScheduleMapper;
import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TSchedule;
import com.fosu.lesson.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScheduleUtils {


   @Autowired
   private TCourseMapper tCourseMapper;

   @Autowired
   private  CourseServiceImpl courseService;

   @Autowired
   private TScheduleMapper tScheduleMapper;

   double maxExpect=-200000;
   double Expect=0;


   public synchronized void schedulePlan(){
      //获取课程教师信息
      List<TCourse> TCourseList = tCourseMapper.selectByExample(null);
      //对开课任务进行编码
      Map<String, List<String>> geneList = coding(TCourseList);
     //开始进行时间分配
      List<String> resultGeneList = codingTime(geneList);
      //第四步对已分配好时间的基因进行分类，生成以班级为范围的个体
      Map<String, List<String>> individualMap = transformIndividual(resultGeneList,false);
      //第五步进行遗传进化操作
      individualMap = geneticEvolution(individualMap);
      //第六步分配教室
      //List<String> resultList = finalResult(individualMap);
      //第七步对分配好时间教室的基因进行解码，准备存入数据库
      decoding(individualMap);

   }

   //解码
   private void decoding(Map<String, List<String>> individualMap) {
      //选出只有班级的列
      List<TSchedule> tScheduleList =new ArrayList<>();
      List<String> classNoList = tCourseMapper.selectByColumnName(ConstantInfo.CLASS_ID);
      for (String classId:classNoList) {
         List<String> geneList = individualMap.get(classId);
         double oldExpect = ClassSchedulUtil.alculateExpectedValue(geneList);
         System.out.println("+++++++++"+oldExpect+"+++++++++++");
         String Time_id = new String();
         String Course_id = new String();
         int classroomId=1;
         for (String gene:geneList) {
            Course_id=ClassSchedulUtil.cutGene(ConstantInfo.COURSE_ID,gene);
            if(Course_id.substring(0,2).equals("00")){
               Course_id=Course_id.substring(2,3);
            }else if(Course_id.substring(0,1).equals("0")){
               Course_id=Course_id.substring(1,3);
            }
            Time_id=ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME,gene);
            if(Time_id.substring(0,1).equals("0")){
               Time_id=Time_id.substring(1,2);
            }
            TSchedule tSchedule = new TSchedule();
            tSchedule.setClassId(ClassSchedulUtil.cutGene(ConstantInfo.CLASS_ID,gene));
            tSchedule.setTeacherId(ClassSchedulUtil.cutGene(ConstantInfo.TEACHER_ID,gene));
            tSchedule.setCourseId(Course_id);
            tSchedule.setTimeId(Time_id);
            tSchedule.setClassroomId(classroomId+"");
            classroomId++;
            tSchedule.setScheduleId("4");
            tScheduleList.add(tSchedule);
         }


      }
      System.out.println("1");
      for (TSchedule tSchedule:tScheduleList) {
         tScheduleMapper.insert(tSchedule);
      }
   }


   //编码规则（class_id+teacher_id+course_name+course_sort+class_hour）
   private Map<String, List<String>> coding(List<TCourse> tCourseList){
      Map<String, List<String>> geneListMap = new HashMap<>();
      List<String> unFixedTimeGeneList = new ArrayList<>();//不固定时间的编码基因组
      for (TCourse tCourse:tCourseList) {
         //计算一周的上课次数，一次对应一个基因，2次对应两个基因。依此类推
         int size = Integer.parseInt(tCourse.getClassHour());
         System.out.println("====================================");
         for (int i = 0; i < size; i++) {
            String courseID = tCourse.getCourseId();
            if(courseID.length()<2){
               courseID="00"+courseID;
            }else if(courseID.length()<3){
               courseID="0"+courseID;
            }

            String gene = tCourse.getClassId() + tCourse.getTeacherId() + tCourse.getCourseName() + tCourse.getCourseSort() + tCourse.getClassHour()+courseID+"00";
            unFixedTimeGeneList.add(gene);
            System.out.println(gene);
         }
         System.out.println("====================================");
      }
      geneListMap.put("unFixedTime", unFixedTimeGeneList);
      return geneListMap;
   }

   private List<String> codingTime(Map<String, List<String>> geneList) {
      List<String> resultGeneList = new ArrayList<>();
      List<String> unFixedTimeGeneList = geneList.get("unFixedTime");
      for (String gene : unFixedTimeGeneList) {
         //获取一个不重复的时间片值
         String classTime = ClassSchedulUtil.randomTime(gene, resultGeneList);
         gene = gene.substring(0, 18) + classTime;
         resultGeneList.add(gene);
      }
      return resultGeneList;
   }

   ////将编码按班级进行分类，形成初始个体（不含教室的初始课表）
   private Map<String, List<String>> transformIndividual(List<String> resultGeneList,Boolean flag) {
      Map<String, List<String>> individualMap = new HashMap<>();
      //选出只有班级的列
      List<String> classNoList = tCourseMapper.selectByColumnName(ConstantInfo.CLASS_ID);
      for (String classNo : classNoList) {
         List<String> geneList = new ArrayList<>();
         for (String gene : resultGeneList) {
            if (classNo.equals(ClassSchedulUtil.cutGene(ConstantInfo.CLASS_ID, gene))) {
               geneList.add(gene);
            }
         }
         //分班填入初始化map，map<string，geneList> 键为班级号 值为这个班级的基因
         if (geneList.size() > 1) {
            individualMap.put(classNo, geneList);
            if(flag==true){
               Expect+=ClassSchedulUtil.alculateExpectedValue(geneList);
            }

         }
      }
      return individualMap;
   }

   private Map<String, List<String>> geneticEvolution(Map<String, List<String>> individualMap) {
      int generation = ConstantInfo.GENERATION;//进化代数设为100
      List<String> resultGeneList;
      Map<String, List<String>> maxIndividualMap = new HashMap<>();
      for (int i = 0; i < generation; ++i) {
         //第一步完成交叉操作,产生新一代的父本
         individualMap = hybridization(individualMap);
         //第二步合拢个体准备变异

         //第三步开始变异
          resultGeneList = geneticMutation(closedGene(individualMap));
         //第四步进行冲突检测并消除
         conflictResolution(resultGeneList);
         //第五步将冲突消除后的个体再次进行分割，按班级进行分配准备进入下一次的进化
         individualMap = transformIndividual(conflictResolution(resultGeneList),true);
         //冲突解决后存储权重高的
         if(maxExpect<Expect){
            maxIndividualMap=individualMap;
            maxExpect=Expect;

         }
         System.out.println("》》》》》》"+maxExpect+"《《《《《《"+"  》》》》》》"+Expect+"《《《《《《《");
         Expect=0;
      }
      return maxIndividualMap;
   }


   //将分割好的个体（按班级分好的初始课表）重新合拢在一起，方便进行冲突检测
   private List<String> closedGene(Map<String, List<String>> individualMap) {
      List<String> resultGeneList = new ArrayList<>();
      for (List<String> individualList : individualMap.values()) {
         resultGeneList.addAll(individualList);
      }
      return resultGeneList;
   }

   //基因变异操作
   private List<String> geneticMutation(List<String> resultGeneList) {
      int min = 0;
      int max = resultGeneList.size() - 1;
      double mutationRate = 0.01;//变异概率
      int mutationNumber = (int) (resultGeneList.size() * mutationRate);//每一代所要选取的变异个数,计算公式为基因数量*变异率
      if (mutationNumber < 1) {
         mutationNumber = 1;
      }
      for (int i = 0; i < mutationNumber; ) {
         int temp = min + (int) (Math.random() * (max + 1 - min));//生成随机数
         String gene = resultGeneList.get(temp);
         if(!(gene.substring(0,1).equals("9"))){
            String newClassTime = ClassSchedulUtil.randomTime(gene, resultGeneList);
            gene = gene.substring(0, 18) + newClassTime;
            resultGeneList.remove(temp);
            resultGeneList.add(temp, gene);
         }
            i = i + 1;
         }
      return resultGeneList;
   }

   //解决冲突，同一时间一个教师上多门课的冲突
   private List<String> conflictResolution(List<String> resultGeneList) {
      exit:
      for (int i = 0; i < resultGeneList.size(); ++i) {
         String gene = resultGeneList.get(i);
         String teacherNo = ClassSchedulUtil.cutGene(ConstantInfo.TEACHER_ID, gene);
         String classTime = ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, gene);
         for (int j = i + 1; j < resultGeneList.size(); ++j) {
            String tempGene = resultGeneList.get(j);
            String tempTeacherNo = ClassSchedulUtil.cutGene(ConstantInfo.TEACHER_ID, tempGene);
            String tempClassTime = ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, tempGene);
            if (teacherNo.equals(tempTeacherNo) && classTime.equals(tempClassTime)) {
               String newClassTime = ClassSchedulUtil.randomTime(gene, resultGeneList);
               gene = gene.substring(0, 18) + newClassTime;
               continue exit;
            }

         }
      }
      return resultGeneList;
   }

   //完成整个种群的交叉操作
   private Map<String, List<String>> hybridization(Map<String, List<String>> individualMap) {
      for (String classNo : individualMap.keySet()) {
         List<String> individualList = individualMap.get(classNo);
         //一个班级的所有基因，先保存原有的基因
         List<String> oldIndividualList = new ArrayList<>();
         
         for(String oldGene:individualList){
            oldIndividualList.add(oldGene);
         }
         individualList = selectiveGene(individualList);//进行基因的交叉操作生成新个体
         //对父代的适应度值和新生成的子代适应值进行对比，选择适应度值高的一个进入下一代的遗传
         double oldExpect = ClassSchedulUtil.alculateExpectedValue(oldIndividualList);
         double newExpect = ClassSchedulUtil.alculateExpectedValue(individualList);
         System.out.println("======="+newExpect+"=========="+oldExpect);
         if ( newExpect>= oldExpect) {
            individualMap.put(classNo, individualList);
         } else {
            individualMap.put(classNo, oldIndividualList);
         }

      }
      return individualMap;
   }

   //个体间的随机选择两条基因准备进行杂交并生成一个新个体
   private List<String> selectiveGene(List<String> individualList) {
      int min = 0;
      int max = individualList.size() - 1;
      boolean flag;
      do {
         //随机生成0到individualList.size - 1的两个数，用来选取基因
         int firstTemp = min + (int) (Math.random() * (max + 1 - min));//选取第一个随机数
         int secondTemp = min + (int) (Math.random() * (max + 1 - min));//选取第二个随机数
         String firstGene = individualList.get(firstTemp);//获取第一条基因
         String secondGene = individualList.get(secondTemp);//获取第二条基因
         //判断选择的两个随机数为否相同，确保不会选择同一条基因进行交叉操作
         if (firstTemp == secondTemp) {
            flag = false;
         }  else {
            //分别获取所选的两条基因的时间片值
            String firstClassTime = ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, firstGene);
            String secondClassTime = ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, secondGene);
            //将它们的时间进行交换
            firstGene = firstGene.substring(0, 18) + secondClassTime;
            secondGene = secondGene.substring(0, 18) + firstClassTime;
            //对原有的基因进行移除，然后将交换过时间的两条基因添加进去
            individualList.remove(firstTemp);
            individualList.add(firstTemp, firstGene);
            individualList.remove(secondTemp);
            individualList.add(secondTemp, secondGene);
            flag = true;
         }
      } while (!flag);
      return individualList;
   }


}
