package test;

import mainDo.Find;
import mainDo.Makereport;
import mainDo.Server;
import mainDo.windowshandle;

import org.openqa.selenium.WebDriver;

public class ccc {
	public static void main(String[] args) {
		int res = 1;
		try {
			Server.run("");

			try {

				WebDriver driver = Server.driver(15, "测试整个流程",
						"http://www.banggo.com");

				Find.linkText("保暖三宝", "点击保暖三包").click();
				windowshandle
						.switchToWindow(
								driver,
								"保暖三宝--banggo.com 邦购网 | 美特斯邦威官方商城-Me&City官方商城，100%正品保证!",
								12);
				Find.xpath("//a[5]/i", "点击comback").click();

			} catch (Exception e) {
				Makereport.getinstance().adddetaillog(e.getMessage());
				res = -1;
			} finally {
				Server.driverClose("测试整个流程", "  高", res, "无");
				res = 1;
			}
		} catch (Exception e1) {

			e1.printStackTrace();
		} finally {
			Server.stop("");
		}

	}
}
