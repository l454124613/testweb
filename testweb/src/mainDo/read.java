package mainDo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class read {
	public List<String> from(String casePath) throws IOException {
		List<String> l = new ArrayList<String>();// 新建队列存放文件
		File file = new File(casePath);// Text文件
		BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
		String s = null;
		while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
			// System.out.println(s);
			if (s.length() > 0) {// 取出空行
				if (s.charAt(0) != '#' & s.length() > 0) {// 取出备注的信息
					// System.out.println(s);
					if (s.contains("/")) {// 判断是否标题

						String[] a = s.split("/");
						if (a.length > 1) {
							l.add("/" + a[1]);
						}
					}
					if (s.contains(".")) { // 判断是否操作步骤
						int num = 0;
						for (int i = 0; i < s.length(); i++) {
							if (s.charAt(i) == '.') {
								num = i;
								break;
							}
						}

						l.add(s.substring(num));

					}
				}
			}
		}
		br.close();
		return l;

	}

	public Map<String, String> from1(String elementPath) throws Exception {
		Map<String, String> m = new HashMap<String, String>();// 新建元素定位的map
		File file = new File(elementPath);// Text文件
		BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
		String s = null;
		while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
			// System.out.println(s);
			if (s.length() > 0) {// 过滤空行
				if (s.charAt(0) != '#' | s.split("==").length > 1) {// 过滤备注文件
					try {
						m.put(s.split("==")[0], s.split("==")[1]);// 添加定位元素
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {

						throw new Exception("文档格式错误:XXX==XXXXX");
					}
				}
			}
		}
		br.close();
		return m;

	}
}
