package mainDo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class action {

	private String title = "";// 标题
	private String implv = "";// 重要等级

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImplv() {
		return implv;
	}

	public void setImplv(String implv) {
		this.implv = implv;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean check = true;// 是否没有问题
	private getProperties gp = new getProperties();

	public void done(WebDriver driver, List<String> caselist,
			Map<String, String> elementlist) throws Exception,
			FileNotFoundException, IOException, Exception {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		title = caselist.get(0).split(":")[0].substring(1);
		implv = caselist.get(0).split(":")[1];
		for (int i = 1; i < caselist.size(); i++) {

			String line = caselist.get(i);
			if (line.charAt(0) == '/') {
				if (check) {
					Server.driverClose(title, implv, 1, "无");
				}
				title = line.split(":")[0].substring(1);
				implv = line.split(":")[1];
				driver = Server.driver(Long.parseLong(gp.read("findtime")),
						title, gp.read("linkWebsite"));
				wait = new WebDriverWait(driver, 30);
				check = true;

			}
			if (line.charAt(0) == '.' & check) {

				try {
					switch (line.substring(1, 3)) {
					case "点击":

						Find.xpath(elementlist.get(line.substring(3)),
								line.substring(1)).click();
						break;

					case "输入":

						Find.xpath(
								elementlist
										.get(line.substring(3).split(":")[0]),
								line.substring(1)).sendKeys(
								line.substring(3).split(":")[1]);
						break;
					case "获得":

						System.out.println("获得" + line.substring(4));
						break;
					case "移到":

						new move().to(driver, Find.xpath(
								elementlist.get(line.substring(3)),
								line.substring(1)));
						break;
					case "进入":
						windowshandle.switchToWindow(driver, line.substring(3),
								Integer.parseInt(gp.read("linktime")));
						wait.until(isPageLoaded());

						break;
					case "等待":
						Thread.sleep(Integer.parseInt(line.substring(3)));
						break;
					case "提示":
						alert a = new alert().switchto(driver);
						if (line.substring(3).equals("确定")
								| line.substring(3).equals("确认")) {
							a.accept();
						}
						if (line.substring(3).equals("取消")
								| line.substring(3).equals("关闭")) {
							a.dismiss();
						}
						if (line.substring(3, 5).equals("输入")) {
							a.sendkeys(line.substring(5));
						}
						break;
					default:
						throw new Exception("无法识别操作:" + line.substring(1));

					}
				} catch (Exception e) {
					Thread.sleep(3000);

					try {
						String filename = String.valueOf(System
								.currentTimeMillis()) + ".jpg";
						getscreen.snapshot(driver, filename);
						getscreen.JpgTset(filename);
						Makereport.getinstance().addstep(
								"picture\\cc" + filename,
								"picture\\" + filename, "错误截图");
					} catch (org.openqa.selenium.UnhandledAlertException e1) {
						new alert().switchto(driver).accept();
						String filename = String.valueOf(System
								.currentTimeMillis()) + ".jpg";
						getscreen.snapshot(driver, filename);
						getscreen.JpgTset(filename);
						Makereport.getinstance().addstep(
								"picture\\cc" + filename,
								"picture\\" + filename, "有意料之外的提示框");
					}
					Makereport.getinstance().adddetaillog(
							e.getLocalizedMessage());
					Server.driverClose(title, implv, -1, "无");
					check = false;

				}
			}

		}
	}

	protected Function<WebDriver, Boolean> isPageLoaded() {
		return new Function<WebDriver, Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript(
						"return document.readyState").equals("complete");
			}
		};
	}
}
