package com.fosu.lesson.utils;

import com.fosu.lesson.dao.TCourseMapper;
import com.fosu.lesson.dao.TScheduleMapper;
import com.fosu.lesson.pojo.TCourse;
import com.fosu.lesson.pojo.TCourseExample;
import com.fosu.lesson.pojo.TPreschedule;
import com.fosu.lesson.pojo.TSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScheduleUtils {

   @Autowired
   private TScheduleMapper tScheduleMapper;

   @Autowired
   private TCourseMapper tCourseMapper;

   double maxExpect=-200000;
   double Expect=0;


   public synchronized void schedulePlan(List<TPreschedule> tPreschedules, String grade){
      maxExpect=-200000;
      Expect=0;
      List<TCourse> TCourseList;
      //获取课程教师信息
      if(grade!=null&&!(grade.equals(""))){
         TCourseExample tCourseExample = new TCourseExample();
         tCourseExample.createCriteria().andGradeEqualTo(grade);
          TCourseList = tCourseMapper.selectByExample(tCourseExample);
      }else{
          TCourseList = tCourseMapper.selectByExample(null);
      }

      //对开课任务进行编码
      List<Map<String, List<String>>> geneList = coding(TCourseList,tPreschedules);
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
     //  System.out.println(judgeConflict(closedGene(individualMap))+"我太难了");
      List<String> classNoList = tCourseMapper.selectByColumnName(ConstantInfo.CLASS_ID);
      for (String classId:classNoList) {
         List<String> geneList = individualMap.get(classId);
         if(geneList!=null){
            double oldExpect = ClassSchedulUtil.alculateExpectedValue(geneList);
            System.out.println("+++++++++"+oldExpect+"+++++++++++");
            String Time_id = new String();
            String Course_id = new String();
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
               tSchedule.setClassroomId((Integer.parseInt(gene.substring(0,1))-6)+gene.substring(2,3));
               tSchedule.setScheduleId("2018-2019上学期");
               tScheduleList.add(tSchedule);
            }
         }



      }
      for (TSchedule tSchedule:tScheduleList) {
         tScheduleMapper.insert(tSchedule);
      }
   }


   //编码规则（class_id+teacher_id+course_name+course_sort+class_hour）
   private List<Map<String, List<String>>> coding(List<TCourse> tCourseList, List<TPreschedule> tPreschedules){
      Map<String, List<String>> geneListMap = new HashMap<>();
      List<String> unFixedTimeGeneList = new ArrayList<>();//不固定时间的编码基因组
      List<String> FixedTimeGeneList = new ArrayList<>();//不固定时间的编码基因组
       for (int i = 0; i <tPreschedules.size() ; i++) {
          TPreschedule tPreschedule = tPreschedules.get(i);
           for(int j = 0 ; j<tCourseList.size();j++){
               TCourse tCourse = tCourseList.get(j);
               if(tPreschedule.getClassId().equals(tCourse.getClassId())&&tPreschedule.getRemark().equals(tCourse.getRemark())&&tPreschedule.getTeacherId().equals(tCourse.getTeacherId())){
                   String courseID = getCourseID(tCourse.getCourseId());
                   String time = tPreschedule.getTimeId();
                   if(time.length()<2){
                       time = "0"+time;
                   }
                   String gene = tCourse.getClassId() + tCourse.getTeacherId() + tCourse.getCourseName() + tCourse.getCourseSort() + tCourse.getClassHour()+courseID+"1"+time;
                   FixedTimeGeneList.add(gene);
                   tCourse.setClassHour((Integer.parseInt(tCourse.getClassHour())-1)+"");
                   tCourseList.remove(j);
                   tCourseList.add(j,tCourse);
               }
           }
       }
      for (TCourse tCourse:tCourseList) {
         //计算一周的上课次数，一次对应一个基因，2次对应两个基因。依此类推
         int size = Integer.parseInt(tCourse.getClassHour());
         System.out.println("====================================");
         for (int i = 0; i < size; i++) {

            String courseID = getCourseID(tCourse.getCourseId());

            String gene = tCourse.getClassId() + tCourse.getTeacherId() + tCourse.getCourseName() + tCourse.getCourseSort() + tCourse.getClassHour()+courseID+"000";
            unFixedTimeGeneList.add(gene);
            System.out.println(gene);
         }
         System.out.println("====================================");
      }
       geneListMap.put("FixedTime", FixedTimeGeneList);
      geneListMap.put("unFixedTime", unFixedTimeGeneList);
       List<Map<String, List<String>>> list =  new ArrayList<>();
       list.add(geneListMap);
      return list;
   }

    private String getCourseID(String courseId) {
       if(courseId.length()<2){
           courseId = "0"+courseId;
       }
       if(courseId.length()<3){
           courseId = "0"+courseId;
       }
       return courseId;
    }

    private List<String> codingTime(List<Map<String, List<String>>> geneList) {
      List<String> resultGeneList = new ArrayList<>();
      List<String> unFixedTimeGeneList = geneList.get(0).get("unFixedTime");
       List<String> FixedTimeGeneList = geneList.get(0).get("FixedTime");
      for (String gene : unFixedTimeGeneList) {
         //获取一个不重复的时间片值
         String classTime = ClassSchedulUtil.randomTime(gene, resultGeneList);
         gene = gene.substring(0, 19) + classTime;
         resultGeneList.add(gene);
      }
       resultGeneList.addAll(FixedTimeGeneList);
      return resultGeneList;
   }

   ////将编码按班级进行分类，形成初始个体（不含教室的初始课表）
   private Map<String, List<String>> transformIndividual(List<String> resultGeneList,Boolean flag) {

      Map<String, List<String>> individualMap = new HashMap<>();
      //选出只有班级的列
      List<String> classNoList = tCourseMapper.selectByColumnName(ConstantInfo.CLASS_ID);
      if(flag==true){
         Expect=0;
         Expect+=judgeConflict(resultGeneList);
      }
      for (String classNo : classNoList) {
         List<String> geneList = new ArrayList<>();
         for (String gene : resultGeneList) {
            if (classNo.equals(ClassSchedulUtil.cutGene(ConstantInfo.CLASS_ID, gene))) {
               geneList.add(gene);
            }
         }
         //分班填入初始化map，map<string，geneList> 键为班级号 值为这个班级的基因
         if (geneList.size() >= 1) {
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
         //第二步合拢个体准备变异 closedGene(individualMap)
         //第三步开始变异
          resultGeneList = geneticMutation(closedGene(individualMap));
         //第四步进行冲突检测并消除  conflictResolution(resultGeneList);
         //第五步将冲突消除后的个体再次进行分割，按班级进行分配准备进入下一次的进化
         individualMap = transformIndividual(conflictResolution(resultGeneList),true);
         //冲突解决后存储权重高的
         if(maxExpect<Expect){
            for (Map.Entry<String, List<String>> entry : individualMap.entrySet())
            {
               maxIndividualMap.put(entry.getKey(),
                       new ArrayList<String>(entry.getValue()));
            }
            maxExpect=Expect;

         }
         System.out.println("》》》》》》"+maxExpect+"《《《《《《"+"  》》》》》》"+Expect+"《《《《《《《");
      }
      return maxIndividualMap;
   }

   //判断老师课程是否冲突；
   private double judgeConflict(List<String> resultGeneList) {
      for (int i = 0; i < resultGeneList.size(); ++i) {
         String gene = resultGeneList.get(i);
         String teacherNoAndClassTime = ClassSchedulUtil.cutGene(ConstantInfo.TEACHER_ID, gene)+ ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, gene);
         for (int j = i + 1; j < resultGeneList.size(); ++j) {
            String tempGene = resultGeneList.get(j);
            String teacherNoAndClassTime2 = ClassSchedulUtil.cutGene(ConstantInfo.TEACHER_ID, tempGene)+ ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, tempGene);
            if (teacherNoAndClassTime2.equals(teacherNoAndClassTime)) {
              return  -200000;
            }
         }
      }
      return 0;
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
         if (ClassSchedulUtil.cutGene(ConstantInfo.IS_FIX, gene).equals("1")) {
            break;
         } else {
            String newClassTime = ClassSchedulUtil.randomTime(gene, resultGeneList);
            gene = gene.substring(0, 19) + newClassTime;
             resultGeneList.add(temp, gene);
             i = i + 1;
         } resultGeneList.remove(temp);

      }
      return resultGeneList;
   }

   //解决冲突，同一时间一个教师上多门课的冲突
   private List<String> conflictResolution(List<String> resultGeneList) {
      exit:
      for (int i = 0; i < resultGeneList.size()-1; i++) {
         String gene = resultGeneList.get(i);
         String teacherNo = ClassSchedulUtil.cutGene(ConstantInfo.TEACHER_ID, gene);
         String classTime = ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, gene);
         for (int j = i + 1; j < resultGeneList.size(); j++) {
            String tempGene = resultGeneList.get(j);
            String tempTeacherNo = ClassSchedulUtil.cutGene(ConstantInfo.TEACHER_ID, tempGene);
            String tempClassTime = ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, tempGene);
            if (teacherNo.equals(tempTeacherNo) && classTime.equals(tempClassTime)) {
                  String newClassTime = ClassSchedulUtil.randomTime(gene, resultGeneList);
                  if(ClassSchedulUtil.cutGene(ConstantInfo.IS_FIX,gene).equals("0")){
                     gene = gene.substring(0, 19) + newClassTime;
                     resultGeneList.remove(i);
                     resultGeneList.add(gene);
                     i=i-1;
                  }else{
                     tempGene = tempGene.substring(0, 19) + newClassTime;
                     resultGeneList.remove(j);
                     resultGeneList.add(tempGene);
                      i=i-1;
                  }

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
         //System.out.println("======="+newExpect+"=========="+oldExpect);
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
         }  else if(ClassSchedulUtil.cutGene(ConstantInfo.IS_FIX,firstGene).equals("1")||ClassSchedulUtil.cutGene(ConstantInfo.IS_FIX,secondGene).equals("1")){
            //判断选择的两条基因对应的时间值是否固定，如果固定则重新选择两条
            flag = false;
         }
         else {
            //分别获取所选的两条基因的时间片值
            String firstClassTime = ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, firstGene);
            String secondClassTime = ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME, secondGene);
            //将它们的时间进行交换
            firstGene = firstGene.substring(0, 19) + secondClassTime;
            secondGene = secondGene.substring(0, 19) + firstClassTime;
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
