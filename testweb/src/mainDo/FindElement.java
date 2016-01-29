package mainDo;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FindElement {
	private getscreen s = new getscreen();
	private static Logger logger = Logger.getLogger(FindElement.class);
	private WebDriver driver = null;

	/**
	 * 必须传入driver 用于找到元素
	 * 
	 * @param driver
	 */
	public FindElement(final WebDriver driver) {
		super();
		logger.info(driver);
		this.driver = driver;

	}

	/**
	 * 用于找到元素
	 * 
	 * @param by
	 * @param stepname
	 *            操作步骤名称
	 * @return
	 * @throws Exception
	 */
	public WebElement findWebElement(final By by, String stepname)
			throws Exception {
		logger.info(by + " " + stepname);
		WebElement we = null;

		we = driver.findElement(by);
		Highlight.highlightElement(driver, we);// 高亮元素
		String filename = String.valueOf(System.currentTimeMillis()) + ".jpg";
		s.snapshot(driver, filename);// 截屏
		s.JpgTset(filename);// 制作缩略图
		Makereport.getinstance().addstep("picture/cc" + filename,
				"picture/" + filename, stepname);// 写报告
		return we;

	}

}
