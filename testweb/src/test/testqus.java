package test;

import java.util.Arrays;

///n个人报数123，报到3的人踢出去，继续报数，问留到最后的是编号几的人

public class testqus {
	public static void main(String[] args) {
		int n = 3;// 总人数
		int[] ns = new int[n];
		for (int i = 0; i < n; i++) {
			ns[i] = i + 1;
		}
		int now = 1, left = n;
		while (left > 1) {
			
			for (int i = 0; i < n; i++) {
				if(ns[i]==-1){
					continue;
				}
				if (now == 3) {
					now = 0;
					left--;
					ns[i]=-1;

				}
				now++;
			}
			
		}
		System.out.println(Arrays.toString(ns));
	}
}
