package mainDo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class move {
	/**
	 * 用户鼠标移动
	 * 
	 * @param driver
	 *            你知道的
	 * @param e
	 *            元素
	 */
	public void to(WebDriver driver, WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).perform();
	}
}
