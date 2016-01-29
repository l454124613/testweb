package mainDo;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Find {
	private static FindElement fe;
	private WebDriver driver;
	private static Logger logger = Logger.getLogger(Find.class);

	/**
	 * 找元素的方式
	 * 
	 * @param driver
	 */
	Find(WebDriver driver) {
		this.driver = driver;
		fe = new FindElement(driver);
	}

	static WebElement by(final By by, String stepname) throws Exception {
		return fe.findWebElement(by, stepname);

	}

	public static WebElement className(String className, String stepname)
			throws Exception {
		logger.info(className + " " + stepname);
		return by(By.className(className), stepname);
	}

	public static WebElement cssSelector(String selector, String stepname)
			throws Exception {
		logger.info(selector + " " + stepname);
		return by(By.cssSelector(selector), stepname);
	}

	public static WebElement id(String id, String stepname) throws Exception {
		logger.info(id + " " + stepname);
		return by(By.id(id), stepname);
	}

	public static WebElement linkText(String linkText, String stepname)
			throws Exception {
		logger.info(linkText + " " + stepname);
		return by(By.linkText(linkText), stepname);
	}

	public static WebElement name(String name, String stepname)
			throws Exception {
		logger.info(name + " " + stepname);
		return by(By.name(name), stepname);
	}

	public static WebElement tagName(String tagName, String stepname)
			throws Exception {
		logger.info(tagName + " " + stepname);
		return by(By.tagName(tagName), stepname);
	}

	public static WebElement xpath(String xpath, String stepname)
			throws Exception {
		logger.info(xpath + " " + stepname);
		WebElement a;
		try {
			a = by(By.xpath(xpath), stepname);
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			Thread.sleep(2000);
			try {
				a = by(By.xpath(xpath), stepname);
			} catch (org.openqa.selenium.StaleElementReferenceException e1) {
				Thread.sleep(2000);
				a = by(By.xpath(xpath), stepname);
			}
		}
		return a;
	}
}
