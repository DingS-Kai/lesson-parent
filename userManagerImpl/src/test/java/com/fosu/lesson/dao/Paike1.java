package com.fosu.lesson.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Paike1 {
	//生成第几种课表
	static int count = 1;
	static Random random = new Random();
	//行为上课的时间片，列为班级
	static int dp[][] = new int[35][6];
	//班级的科目
	static String course[] = new String[] 
	{ "", "语文", "数学", "英语", "物理", "化学","生物", "政治", "地理", "历史", "美术" };
	//一个班一星期的科目的总数  顺序同 course数组
	static int courseNum[] = new int[] { 0, 5, 5, 4, 3, 3, 3, 3, 3, 3, 3 };
	//存储所有课程的容器
	static List<Integer> map = new ArrayList<Integer>();
	public static void main(String[] args) {
        //初始化课程即数量
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++)
				map.add(j + 1);
		}
		for (int i = 90; i <= 95; i++) {
			map.add(1);
			map.add(2);
		}
		map.add(3);
		map.add(3);
		map.add(3);
		//排课
		sort();
	}

	private static void sort() {
		digui(0, 0);
	}

	private static void digui(int h, int l) {	
		if (map.isEmpty()) {
            if(count++==2)
            {	
            	printA();
    			System.out.println("===============================");
    		return;
            }
		}
		else {
			int randomNum = random.nextInt(map.size());
			int num=0;
			int coun=0;
			while(true){
				num = map.get(randomNum);
				while (!(jduge(h, l, num) == 0)||coun<5) {
					randomNum = random.nextInt(map.size());
					num = map.get(randomNum);
					coun++;
				}
				if(count<5){
					map.remove(randomNum);
					dp[h][l] = num;
					if (l == 2) {
						digui(h + 1, l - 2);
					} else {
						digui(h, l + 1);
					}
					dp[h][l] = 0;
					map.add(num);
				}
			}
		}
	}
	// 判断是不是可以插入数据
	private static int jduge(int h, int l, int i) {
		for (int k = 0; k < l; k++) {
			if (dp[h][k] == i) {
				return -1;
			}
		}
		int count = 0;
		for (int q = 0; q < h; q++) {
			if (dp[q][l] == i) {
				count++;
			}
		}
		if (count >= courseNum[i] || (h >= 1 && dp[h - 1][l] == i))
			return -1;
		return 0;
	}

	// 输出数据
	private static void printA() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			
			System.out.println("          三年级"+(i+1)+"班");
			
			for (int j = 0; j < 7; j++) {
				for(int k=0;k<5;k++){
					System.out.print(course[dp[j+k*5][i]]);
					System.out.print("     ");	
				}
				System.out.println("");
			}
			System.out.println("");
		}
	}
}
