package test;

import java.util.List;
import java.util.Map;

import mainDo.Server;
import mainDo.action;
import mainDo.excelTo;
import mainDo.getProperties;
import mainDo.read;

import org.openqa.selenium.WebDriver;

public class test1 {
	public static void main(String[] args) throws Exception {
		getProperties gp = new getProperties();
		action a = new action();
		String tmpfile = "";// 临时文件
		List<String> l = null;
		if (!gp.read("loadstyle").toUpperCase().equals("EXCEL")) {
			l = new read().from(gp.read("casePath"));
		} else {
			// l=Arrays.asList(new doExcel().read("casePath",
			// gp.read("sheetname"), rownum, cellnum))
			// System.out.println(tmpfile);
			tmpfile = new excelTo().Tmp();// 临时文件名称
			l = new read().from(tmpfile);
		}
		Map<String, String> m = new read().from1(gp.read("elementPath"));
		// boolean check = true;// 是否没有问题

		try {
			Server.run(gp.read("driverPath"));

			WebDriver driver = Server
					.driver(Long.parseLong(gp.read("findtime")), l.get(0)
							.split(":")[0].substring(1), gp.read("linkWebsite"));
			a.done(driver, l, m);
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (a.isCheck()) {
				Server.driverClose(a.getTitle(), a.getImplv(), 1, "无");
			}
			Server.stop(tmpfile);
		}

	}
}
