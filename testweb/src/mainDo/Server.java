package mainDo;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

public class Server {
	static ChromeDriverService service;
	static WebDriver driver;

	/**
	 * 用于启动谷歌浏览器服务
	 * 
	 * @param driverPath
	 * @throws Exception
	 */
	public static void run(String driverPath) throws Exception {
		new del().report();
		System.setProperty("webdriver.chrome.driver", driverPath);// 配置驱动
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(driverPath)).usingAnyFreePort()
				.build();
		service.start();// 开启谷歌服务
		Makereport.getinstance().reportstart();// 开始记录聚合报告
	}

	/**
	 * 用户启动浏览器
	 * 
	 * @param time
	 *            等待元素时间,秒
	 * @param name
	 *            测试用例描述或名称
	 * @param website
	 *            网址
	 * @return driver数据
	 * @throws Exception
	 */
	public static WebDriver driver(long time, String name, String website)
			throws Exception {

		driver = new ChromeDriver();// 启动浏览器
		driver.manage().window().maximize();// 浏览器最大化

		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS); // 设置元素等到时间
		new Find(driver);// 元素定位传入driver数据
		if (!website.substring(0, 4).equals("http")) { // 设置网址是否http开头
			website = "http://" + website; // 加上http头
		}
		driver.navigate().to(website); // 浏览器打开网址

		Makereport.getinstance().Todetail(// 进行报告第一步记录
				String.valueOf(System.currentTimeMillis()) + ".html");
		getscreen.snapshot(driver, "进入首页.jpg");// 截屏
		getscreen.JpgTset("进入首页.jpg");// 图片缩小
		Makereport.getinstance().detailnameadd(name);// 添加用例名称或描述
		Makereport.getinstance().addstep("picture/cc" + "进入首页.jpg",
				"picture/" + "进入首页.jpg", "进入首页");// 记录第一步操作

		return driver;
	}

	/**
	 * 用于关闭浏览器
	 * 
	 * @param title
	 *            用例名称或描述
	 * @param priority
	 *            优先级
	 * @param res
	 *            最后结果
	 * @param attachment
	 *            备注
	 */
	public static void driverClose(String title, String priority, int res,
			String attachment) {
		if (driver != null)
			driver.quit(); // 关闭浏览器
		Makereport.getinstance().Offdetail(); // 添加一个具体报告的结尾
		Makereport.getinstance().addreport(title, priority, res, attachment);// 添加一条用例的的聚合报告
	}

	/**
	 * 用于停止服务
	 * 
	 * @param tmpfile
	 *            excel的临时文件
	 */
	public static void stop(String tmpfile) {
		new del().DeleteFolder(tmpfile);// 删除文件
		service.stop();// 停止服务
		Makereport.getinstance().addrepfoot("全部运行");// 当前运行模式
		Makereport.getinstance().Offreport();// 关闭聚合报告
	}

}
