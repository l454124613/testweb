package mainDo;

import java.util.Set;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

public class windowshandle {
	/**
	 * 用于获得屏幕切换
	 * 
	 * @param driver
	 * @param windowTitle
	 *            页面标题
	 * @param time
	 *            允许等待最大时间
	 */
	public static void switchToWindow(final WebDriver driver,
			final String windowTitle, int time) {

		try {
			// 获得当前页面
			final String currentHandle = driver.getWindowHandle();
			// 获得所有页面
			final Set<String> handles = driver.getWindowHandles();
			// 循环所有页面,寻找匹配页面标题的页面
			for (final String s : handles) {
				// 如果是当前页面,则跳过
				if (s.equals(currentHandle)) {

					continue;
				} else {
					driver.switchTo().window(s);
					// 比较页面标题
					if (driver.getTitle().contains(windowTitle)) {
						long start = System.currentTimeMillis();
						while (!driver.getTitle().equals(windowTitle)
								& (System.currentTimeMillis() - start) < time) {

						}

						break;
					}
				}
			}
		} catch (final NoSuchWindowException e) {

		}
	}
}
