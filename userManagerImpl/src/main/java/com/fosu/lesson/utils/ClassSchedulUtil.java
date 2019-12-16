package com.fosu.lesson.utils;

import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;

public class ClassSchedulUtil {
    public static String randomTime(String gene, List<String> resultGeneList) {
        int min = 1;
        int max = 35;
        String time;
        //随机生成1到25范围的数字，并将其转化为字符串，方便进行编码
        int temp = min + (int) (Math.random() * (max + 1 - min));
        if (temp < 10) {
            time = "0" + temp;
        } else {
            time = "" + temp;
        }
        if (isTimeRepe(time, gene, resultGeneList)) {
            return time;
        } else {
            return randomTime(gene, resultGeneList);
        }

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
            case ConstantInfo.CLASS_TIME:
                return source.substring(15, 17);
            default:
                return "";
        }
    }

    public static double alculateExpectedValue(List<String> individualList) {
        double K1 = 0.3;//专业课所占权重
        double K2 = 0.2;//非专业课所占权重
        double K3 = 0.1;//娱乐课所占权重
        double K5 = 0.2;//课程离散程度所占权重
        int F1 = 0;//专业课期望总值
        int F2 = 0;//非专业课期望总值
        int F3 = 0;//娱乐课期望总值

        int F5;//课程离散程度期望总值
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
       // F5 = calculateDiscreteExpect(individualList);
        Fx = K1 * F1 + K2 * F2 + K3 * F3;
        return Fx;
    }

    //计算专业课期望值
    private static int calculateProfessExpect(String classTime) {
        String[] tenExpectValue = {"01", "02",  "08","09", "15", "16","22", "23","29", "31"};//专业课期望值为10时的时间片值
        String[] eightExpectValue = {"05","06","12","13","19","20","26","27","33","34"};//专业课期望值为8时的时间片值
        String[] fourExpectValue = {"03", "04", "10", "11", "17","18","24","25","31","32"};//专业课期望值为4时的时间片值
        String[] twoExpectValue = {"7", "14", "21", "28", "35"};//专业课期望值为2时的时间片值

        if (ArrayUtils.contains(tenExpectValue, classTime)) {
            return 10;
        } else if (ArrayUtils.contains(eightExpectValue, classTime)) {
            return 8;
        } else if (ArrayUtils.contains(fourExpectValue, classTime)) {
            return 4;
        } else if (ArrayUtils.contains(twoExpectValue, classTime)) {
            return 2;
        } else {
            return 0;
        }
    }

    //计算非专业课课期望值
    private static int calculateElectiveExpect(String classTime) {
        String[] tenExpectValue = {"03", "04", "10", "11", "17","18","24","25","31","32"};//选修期望值为10时的时间片值
        String[] eightExpectValue = {"05","06","12","13","19","20","26","27","33","34"};//选修课期望值为8时的时间片值
        String[] fourExpectValue = {"01", "02",  "08","09", "15", "16","22", "23","29", "31"};//选修课期望值为4时的时间片值
        //String [] zeroExpectValue = {"05","10","15","20","25"};//选修课期望值为0时的时间片值

        if (ArrayUtils.contains(tenExpectValue, classTime)) {
            return 10;
        } else if (ArrayUtils.contains(eightExpectValue, classTime)) {
            return 8;
        } else if (ArrayUtils.contains(fourExpectValue, classTime)) {
            return 4;
        } else {
            return 0;
        }
    }

    //计算娱乐课期望值
    private static int calculatePhysicalExpect(String classTime) {
        String[] tenExpectValue = {"7", "14", "21", "28", "35"};//体育课期望值为10时的时间片值
        //String[] eightExpectValue = {"03", "08", "13", "18"};//体育课期望值为8时的时间片值
       // String[] fourExpectValue = {"02", "07", "12", "17", "22"};//体育课期望值为4时的时间片值
        //String [] zeroExpectValue = {"01","05","06","10","11","15","16","20","21","23","24","25"};//体育课期望值为0时的时间片值

        if (ArrayUtils.contains(tenExpectValue, classTime)) {
            return 10;
        }  else {
            return 0;
        }
    }


}
