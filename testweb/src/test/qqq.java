package test;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class qqq {
	private static Logger logger = Logger.getLogger(qqq.class);

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver",
				"driver\\chromedriver.exe");
		// WebDriver driver = new HtmlUnitDriver(true);
		// // // driver.manage().window().maximize();
		// // driver.get("http://www.banggo.com");
		// // // Cookie cookie = new Cookie("userName", "testq5");
		// // // driver.manage().addCookie(cookie);
		// // // Cookie cookie1 = new Cookie("PHPSESSID",
		// // // "ST16596fOjGgTgrb3ejqtGjfsNpassportbanggocom01");
		// // // driver.manage().addCookie(cookie1);
		// // // Set<Cookie> allCookies = driver.manage().getCookies();
		// // // for (Cookie loadedCookie : allCookies) {
		// // // // System.out.println(String.format("%s ---> %s",
		// // // // loadedCookie.getName(), loadedCookie.getValue()));
		// // // logger.error(loadedCookie.getName() + "是" +
		// // loadedCookie.getValue());
		// // // }
		// // // Actions action = new Actions(driver);
		// // //
		// //
		// action.moveToElement(driver.findElement(By.linkText("登录"))).perform();
		// // //
		// driver.findElement(By.xpath("//*[@id=\"ad_common_logo\"]/a/img"))
		// // // .click();
		// // driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/a"))
		// // .click();
		// //
		// // new windowshandle().switchToWindow(driver, "会员登录", 30);
		// // driver.findElement(By.id("username")).sendKeys("monitor");
		// // driver.findElement(By.id("password")).sendKeys("BgItesting2015");
		// // driver.findElement(By.id("vcode")).sendKeys("tttt");
		// // driver.findElement(By.id("log_btn")).click();
		// Cookie cc = driver.manage().getCookieNamed("PHPSESSID");
		// System.out.println(cc.toString());
		// driver.quit();
		// try {
		// Thread.sleep(3000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.banggo.com");
		Cookie cc = new Cookie("PHPSESSID",
				"ST2977kQ3ZvCPXdBtuAZ7QO7hOpassportbanggocom01");
		driver.manage().addCookie(cc);
		driver.findElement(By.xpath("//*[@id=\"ad_common_logo\"]/a/img"))
				.click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
		// logger.error(" ");
		// Set<Cookie> allCookies1 = driver.manage().getCookies();
		// for (Cookie loadedCookie : allCookies1) {
		// // System.out.println(String.format("%s ---> %s",
		// // loadedCookie.getName(), loadedCookie.getValue()));
		// logger.error(loadedCookie.getName() + "是" + loadedCookie.getValue());
		//
		// }

	}
}
