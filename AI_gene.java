package test;
import java.util.*;
//import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

public class AI_gene {
	public static void main(String[] args) {
		int x1 = (int) (Math.random() * 15);// 先隨機一個0~15的數(因為四個bits有16種可能)
		int x2 = (int) (Math.random() * 15);
		int x3 = (int) (Math.random() * 15);
		int x4 = (int) (Math.random() * 15);
		int x5 = (int) (Math.random() * 15);
		int x6 = (int) (Math.random() * 15);
		
		String ansx1="";
		String ansx2="";
		String ansx3="";
		String ansx4="";
		String ansx5="";
		String ansx6="";
		System.out.println("please input the number:");
		Scanner sc = new Scanner(System.in);
		int z = sc.nextInt();
		int r=0;
		while (r<z) {
			
			int X1 = adapt(x1);// 帶入適應性函數
			int X2 = adapt(x2);
			int X3 = adapt(x3);
			int X4 = adapt(x4);
			int X5 = adapt(x5);
			int X6 = adapt(x6);
			
			
			int total = X1 + X2 + X3 + X4 + X5 + X6;// 適應性比率
			int x1R = adaptrate(X1,total);
			int x2R = adaptrate(X2,total);
			int x3R = adaptrate(X3,total);
			int x4R = adaptrate(X4,total);
			int x5R = adaptrate(X5,total);
			int x6R = adaptrate(X6,total);
			
			int p1 = x1R;// 轉盤趴數
			int p2 = x1R + x2R;
			int p3 = x1R + x2R + x3R;
			int p4 = x1R + x2R + x3R + x4R;
			int p5 = x1R + x2R + x3R + x4R + x5R;
			int p6 = x1R + x2R + x3R + x4R + x5R + x6R;
			char table[][] = new char[6][4];// 存六個數字 用01表示 四位元
			Arrays.fill(table[0],'0');
			Arrays.fill(table[1],'0');
			Arrays.fill(table[2],'0');
			Arrays.fill(table[3],'0');
			Arrays.fill(table[4],'0');
			Arrays.fill(table[5],'0');
			for (int i = 0; i < 6; i++) {

				int choose = (int) (Math.random() * p6);// 轉盤
				if (choose <= p1) {// 填滿數字矩陣
					write(i, x1, table);
				}
				if (p2 >= choose && choose > p1) {
					write(i, x2, table);
				}
				if (p3 >= choose && choose > p2) {
					write(i, x3, table);
				}
				if (p4 >= choose && choose > p3) {
					write(i, x4, table);
				}
				if (p5 >= choose && choose > p4) {
					write(i, x5, table);
				}
				if (p6>=choose && choose > p5) {
					write(i,x6, table);
				}
			}
			
			int b = 0;
			for (int k = 0; k < 3; k++) {// 交配
				int will = (int) (Math.random() * 9);//交配機率
				
				if (will < 6) {
					int bit = (int) (Math.random() * 3);
					
					char temp = '0';
					char temp2 = '0';
					char temp3 = '0';
					if (bit == 1) {
						temp = table[b][3];
						table[b][3] = table[b + 1][3];
						table[b + 1][3] = temp;
						b += 2;
					}
					if (bit == 2) {
						temp = table[b][3];
						temp2 = table[b][2];
						table[b][3] = table[b + 1][3];
						table[b + 1][3] = temp;
						table[b][2] = table[b + 1][2];
						table[b + 1][2] = temp2;
						b += 2;
					}
					if (bit == 3) {
						temp = table[b][3];
						temp2 = table[b][2];
						temp3 = table[b][1];
						table[b][3] = table[b + 1][3];
						table[b + 1][3] = temp;
						table[b][2] = table[b + 1][2];
						table[b + 1][2] = temp2;
						table[b][1] = table[b + 1][1];
						table[b + 1][1] = temp3;
					}
				}

			}
			
			for (int l = 0; l < 6; l++) {// 突變
				for (int m = 0; m < 4; m++) {
					int mutation = (int) (Math.random() * 1000);
					if (mutation == 1) {
						if (table[l][m]=='1') {
							table[l][m] = '0';
						}
						else {
							table[l][m] = '1';
						}
					}
				}
			}

			ArrayList<String> array = new ArrayList<String>();
			for (int o = 0; o < 6; o++) {
				String xis = "";
				for (int n = 0; n < 4; n++) {
					xis += table[o][n];
				}
				array.add(xis);
			}
			ansx1 = array.get(0);
			ansx2 = array.get(1);
			ansx3 = array.get(2);
			ansx4 = array.get(3);
			ansx5 = array.get(4);
			ansx6 = array.get(5);
			
			x1 = Integer.parseInt(array.get(0),2);//改回十進制
			x2 = Integer.parseInt(array.get(1),2);
			x3 = Integer.parseInt(array.get(2),2);
			x4 = Integer.parseInt(array.get(3),2);
			x5 = Integer.parseInt(array.get(4),2);
			x6 = Integer.parseInt(array.get(5),2);
			r++;
		}
		
		int total = x1 + x2 + x3 + x4 + x5 + x6;// 適應性總比率
		int x1R = adaptrate(x1,total);
		int x2R = adaptrate(x2,total);
		int x3R = adaptrate(x3,total);
		int x4R = adaptrate(x4,total);
		int x5R = adaptrate(x5,total);
		int x6R = adaptrate(x6,total);
		System.out.println("genenumber  genebit     int     adapt   adaptrate  ");
		System.out.println("   x1   : "+"   "+ansx1+"      "+x1+"    "+adapt(x1)+"  "+x1R);
		System.out.println("   x2   : "+"   "+ansx2+"      "+x2+"    "+adapt(x2)+"  "+x2R);
		System.out.println("   x3   : "+"   "+ansx3+"      "+x3+"    "+adapt(x3)+"  "+x3R);
		System.out.println("   x4   : "+"   "+ansx4+"      "+x4+"    "+adapt(x4)+"  "+x4R);
		System.out.println("   x5   : "+"   "+ansx5+"      "+x5+"    "+adapt(x5)+"  "+x5R);
		System.out.println("   x6   : "+"   "+ansx6+"      "+x6+"    "+adapt(x6)+"  "+x6R);
		sc.close();
	}

	public static int adapt(int x) {// 適應性函數
		return (15 * x) - (x * x);
	}

	public static void write(int x, int y, char b[][]) {//存基因排序到陣列(目前i,輸入的choose,陣列)
		String in = Integer.toBinaryString(y);
		if (y == 0) {
			for (int j = 0; j < 4; j++) {
				b[x][j] = 0;
			}
		} 
		else {
			int k = 3;
			for(int j = in.length()-1; j>=0 ; j--) {
				b[x][k] = in.charAt(j);
				k-=1;
			}
		}
	}
	public static int adaptrate(int x,int y) {// 適應性比率
		if(x==0) {
			return 0;
		}
		else
			return (Math.round(x* 100 / y));
	}
}
