package com.fosu.lesson.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class ClassSchedulUtil {
    public static String randomTime(String gene, List<String> resultGeneList) {
       /* int min = 1;
        int max = 35;
        String time;
        //随机生成1到35范围的数字，并将其转化为字符串，方便进行编码
        int temp = min + (int) (Math.random() * (max + 1 - min));
        if (temp < 10) {
            time = "0" + temp;
        } else {
            time = "" + temp;
        }
        if (isTimeRepe(time, gene, resultGeneList)) {
            return time;
        } else {
            time=null;
            return randomTime(gene, resultGeneList);*/
       String time;
       for(int i=1;i<=ConstantInfo.TIMENUM;i++){
           if (i < 10) {
               time = "0" + i;
           } else {
               time = "" + i;
           }
           if (isTimeRepe(time, gene, resultGeneList)) {
               return time;
           }

       }
        System.out.println("11111");
       return "00";
    }

    //判断同一个班是否在同一时间内上课有重复
    private static boolean isTimeRepe(String time, String gene, List<String> resultGeneList) {
        //获得班级编号
        String classNo = cutGene(ConstantInfo.CLASS_ID, gene);
        for (String str : resultGeneList) {
            //判断班级编号是否相等
            if (classNo.equals(cutGene(ConstantInfo.CLASS_ID, str))) {
                //班级编号相等的则判断时间是否有重复，没有返回true
                String classTime = cutGene(ConstantInfo.CLASS_TIME, str);
                if (time.equals(classTime)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String cutGene(String aim, String source) {
        switch (aim) {
            case ConstantInfo.CLASS_ID:
                return source.substring(0, 3);
            case ConstantInfo.TEACHER_ID:
                return source.substring(3, 11);
            case ConstantInfo.COURSE_NAME:
                return source.substring(11,13);
            case ConstantInfo.COURSE_SORT:
                return source.substring(13, 14);
            case ConstantInfo.CLASS_HOUR:
                return source.substring(14, 15);
            case ConstantInfo.COURSE_ID:
                return source.substring(15, 18);
            case ConstantInfo.CLASS_TIME:
                return source.substring(18,20);
            default:
                return "";
        }
    }

    public static double alculateExpectedValue(List<String> individualList) {
        double K1 = ConstantInfo.K1;//专业课所占权重
        double K2 = ConstantInfo.K2;//非专业课所占权重
        double K3 = ConstantInfo.K3;//娱乐课所占权重
        double K4 = ConstantInfo.K4;//空闲时间权重
        double K5 = ConstantInfo.K5;//课程离散程度所占权重
        double K6 = ConstantInfo.K6;//一天顶多两门相同课的权重
        int F1 = 0;//专业课期望总值
        int F2 = 0;//非专业课期望总值
        int F3 = 0;//娱乐课期望总值
        int F4 = 0;//空闲时间总值
        int F5=0;//课程离散程度期望总值
        int F6=0;//一天顶多两门相同课的总值
        double Fx;//适应度值SORT

        for (String gene : individualList) {
            String courseAttr = cutGene(ConstantInfo.COURSE_SORT, gene);//获得属性
            String classTime = cutGene(ConstantInfo.CLASS_TIME, gene);//获得该课程的开课时间
            if (courseAttr.equals(ConstantInfo.PROFESSIONAL_CODE)) {
                F1 = F1 + calculateProfessExpect(classTime);
            } else if (courseAttr.equals(ConstantInfo.ELECTIVE_CODE)) {
                F2 = F2 + calculateElectiveExpect(classTime);
            } else if (courseAttr.equals(ConstantInfo.PHYSICAL_CODE)) {
                F3 = F3 + calculatePhysicalExpect(classTime);
            }
        }
        F4=calculateFreeTime(individualList);//
        F5 = calculateDiscreteExpect(individualList);
        F6=calculateOnedayCourse(individualList);
        Fx = K1 * F1 + K2 * F2 + K3 * F3  + F4 * K4 + K6 * F6;
       // Fx=K4*F4;
        return Fx;
    }

    //计算空闲时间总值
    private static int calculateFreeTime(List<String> individualList){
        String[] maxExpectValue = {"04", "11",  "18","25", "32", "07","14", "21","28", "35"};//专业课期望值为10时的时间片值
        int num=0;
        for (String gene : individualList){
            if (ArrayUtils.contains(maxExpectValue, ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME,gene))) {
               num++;
            }
        }
        if(num==9){
            return ConstantInfo.F9;
        }else{
            return ConstantInfo.F1;
        }
    }

    //计算一天顶多两门相同课的总值
    private static int calculateOnedayCourse(List<String> individualList){
        Set<String> mon = new HashSet<>();
        Set<String> tue = new HashSet<>();
        Set<String> wed = new HashSet<>();
        Set<String> thu = new HashSet<>();
        Set<String> fri = new HashSet<>();
        int num = 0;
        int F6=0;
        for(String s:individualList){
            num=Integer.parseInt(ClassSchedulUtil.cutGene(ConstantInfo.CLASS_TIME,s));
            if(num<8){
                mon.add(ClassSchedulUtil.cutGene(ConstantInfo.COURSE_NAME,s));
            }else if(num<15){
                tue.add(ClassSchedulUtil.cutGene(ConstantInfo.COURSE_NAME,s));
            }else if(num<22){
                wed.add(ClassSchedulUtil.cutGene(ConstantInfo.COURSE_NAME,s));
            }else if(num<29){
                thu.add(ClassSchedulUtil.cutGene(ConstantInfo.COURSE_NAME,s));
            }else{
                fri.add(ClassSchedulUtil.cutGene(ConstantInfo.COURSE_NAME,s));
            }

        }
        F6=calculateOneDaySum(mon)+calculateOneDaySum(tue)+calculateOneDaySum(wed)+calculateOneDaySum(thu)+calculateOneDaySum(fri);
        return F6;
    }

    //计算一天课程的期望值（一天顶多两门同课程）
    private static int calculateOneDaySum(Set<String> day) {
        if(day.size()<=5){
            return ConstantInfo.F2;
        }else{
            return ConstantInfo.F5;
        }
    }


    //计算课程离散度期望值
    private static int calculateDiscreteExpect(List<String> individualList) {
        int F5 = 0;//离散程度期望值
        Map<String, List<String>> classTimeMap = courseGrouping(individualList);
        for (List<String> classTimeList : classTimeMap.values()) {
            if (classTimeList.size() > 1) {
                for (int i = 0; i < classTimeList.size() -1 ; ++i) {
                    int temp = Integer.parseInt(classTimeList.get(++i)) - Integer.parseInt(classTimeList.get(i - 1));
                    F5 = F5 + judgingDiscreteValues(temp);
                }
            }
        }
        return F5;
    }


    //判断两课时间差在那个区间并返回对于的期望值
    private static int judgingDiscreteValues(int temp) {
        int[] tenExpectValue = {1, 8,9,15,16,22,23,30,31};//期望值为10时两课之间的时间差
        int[] sixExpectValue = {5, 6, 12, 13, 19, 20,27,28};//期望值为6时两课之间的时间差
        int[] fourExpectValue = {4, 11, 18, 26, 17, 18};//期望值为4时两课之间的时间差
        int[] twoExpectValue = {7, 14, 21, 29};//期望值为2时两课之间的时间差
        //int [] zeroExpectValue = {1,24};//期望值为0时两课之间的时间差
        if (ArrayUtils.contains(tenExpectValue, temp)) {
            return ConstantInfo.F9;
        } else if (ArrayUtils.contains(sixExpectValue, temp)) {
            return ConstantInfo.F8;
        } else if (ArrayUtils.contains(fourExpectValue, temp)) {
            return ConstantInfo.F6;
        } else if (ArrayUtils.contains(twoExpectValue, temp)) {
            return ConstantInfo.F5;
        } else {
            return ConstantInfo.F4;
        }
    }

    /**
     * 将一个个体（班级课表）的同一门课程的所有上课时间进行一个统计，并且进行一个分组
     *
     * @param individualList
     * @return
     */
    private static Map<String, List<String>> courseGrouping(List<String> individualList) {
        Map<String, List<String>> classTimeMap = new HashMap<>();
        //先将一个班级课表所上的课程区分出来（排除掉重复的课程）
        for (String gene : individualList) {
            classTimeMap.put(cutGene(ConstantInfo.COURSE_NAME, gene), null);
        }
        //遍历课程
        for (String courseNo : classTimeMap.keySet()) {
            List<String> classTimeList = new ArrayList<>();
            for (String gene : individualList) {
                //获得同一门课程的所有上课时间片
                if (cutGene(ConstantInfo.COURSE_NAME, gene).equals(courseNo)) {
                    classTimeList.add(cutGene(ConstantInfo.CLASS_TIME, gene));
                }
            }
            //将课程的时间片进行排序
            Collections.sort(classTimeList);
            classTimeMap.put(courseNo, classTimeList);
        }
        return classTimeMap;
    }


    //计算专业课期望值
    private static int calculateProfessExpect(String classTime) {
        String[] tenExpectValue = {"01", "02",  "08","09", "15", "16","22", "23","29", "31"};//专业课期望值为10时的时间片值
        String[] eightExpectValue = {"05","06","12","13","19","20","26","27","33","34"};//专业课期望值为8时的时间片值
        String[] fourExpectValue = {"03", "04", "10", "11", "17","18","24","25","31","32"};//专业课期望值为4时的时间片值
        String[] twoExpectValue = {"7", "14", "21", "28", "35"};//专业课期望值为2时的时间片值

        if (ArrayUtils.contains(tenExpectValue, classTime)) {
            return ConstantInfo.F9;
        } else if (ArrayUtils.contains(eightExpectValue, classTime)) {
            return ConstantInfo.F8;
        } else if (ArrayUtils.contains(fourExpectValue, classTime)) {
            return ConstantInfo.F6;
        } else if (ArrayUtils.contains(twoExpectValue, classTime)) {
            return ConstantInfo.F5;
        } else {
            return ConstantInfo.F4;
        }
    }

    //计算非专业课课期望值
    private static int calculateElectiveExpect(String classTime) {
        String[] tenExpectValue = {"03", "04", "10", "11", "17","18","24","25","31","32"};//选修期望值为10时的时间片值
        String[] eightExpectValue = {"05","06","12","13","19","20","26","27","33","34"};//选修课期望值为8时的时间片值
        String[] fourExpectValue = {"01", "02",  "08","09", "15", "16","22", "23","29", "31"};//选修课期望值为4时的时间片值
        //String [] zeroExpectValue = {"05","10","15","20","25"};//选修课期望值为0时的时间片值

        if (ArrayUtils.contains(tenExpectValue, classTime)) {
            return ConstantInfo.F9;
        } else if (ArrayUtils.contains(eightExpectValue, classTime)) {
            return ConstantInfo.F8;
        } else if (ArrayUtils.contains(fourExpectValue, classTime)) {
            return ConstantInfo.F6;
        } else {
            return ConstantInfo.F4;
        }
    }

    //计算娱乐课期望值
    private static int calculatePhysicalExpect(String classTime) {
        String[] tenExpectValue = {"7", "14", "21", "28", "35"};//体育课期望值为10时的时间片值
        //String[] eightExpectValue = {"03", "08", "13", "18"};//体育课期望值为8时的时间片值
       // String[] fourExpectValue = {"02", "07", "12", "17", "22"};//体育课期望值为4时的时间片值
        //String [] zeroExpectValue = {"01","05","06","10","11","15","16","20","21","23","24","25"};//体育课期望值为0时的时间片值

        if (ArrayUtils.contains(tenExpectValue, classTime)) {
            return ConstantInfo.F9;
        }  else {
            return ConstantInfo.F4;
        }
    }


}
