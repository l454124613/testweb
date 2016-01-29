package test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class testTIME {
	public static void main(String[] args) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		System.out.println(df.format(new Date()) + " 00:00:00");// new
		// System.out.println(new Date(new Date().getTime() + 1000000000));//
		// new
		// Date()为获取当前系统时间
	}
}
