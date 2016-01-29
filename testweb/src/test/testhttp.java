package test;

import http.httpRequest;

public class testhttp {
	public static void main(String[] args) throws Exception {
		long a = System.currentTimeMillis();
		httpRequest h = new httpRequest();
		h.cc = 2;
		// System.out.println(withHttp.sendPost("http://www.baidu.com", "")
		// + "-->\n" + (System.currentTimeMillis() - a));
		// a = System.currentTimeMillis();
		System.out.println(h.connect("www.baidu.com")[0] + "-->\n"
				+ h.connect("www.baidu.com")[1] + "\n"
				+ (System.currentTimeMillis() - a));
	}
}
