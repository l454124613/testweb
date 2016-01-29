package mainDo;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Highlight {
	private static Logger logger = Logger.getLogger(Highlight.class);

	/**
	 * 用于页面找到的元素高亮
	 * 
	 * @param driver
	 * @param element
	 */
	public static void highlightElement(WebDriver driver, WebElement element) {
		logger.info("highlight");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"element = arguments[0];"
						+ "original_style = element.getAttribute('style');"
						+ "element.setAttribute('style', original_style + \";"
						+ "background: yellow; border: 2px solid red;\");"
						+ "setTimeout(function(){element.setAttribute('style', original_style);}, 200);",
				element);
	}
}