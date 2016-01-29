package mainDo;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class alert {
	Alert alert;

	public alert switchto(WebDriver driver) {
		alert = driver.switchTo().alert();

		// // 点击第二个按钮，输出对话框上面的文字，然后点击确认
		// dr.findElement(By.id("confirm")).click();
		// Alert confirm = dr.switchTo().alert();
		// String text1 = confirm.getText();
		// System.out.println(text1);
		// confirm.accept();
		//
		// // 点击第三个按钮，输入你的名字，然后点击确认，最后
		// dr.findElement(By.id("prompt")).click();
		// Alert prompt = dr.switchTo().alert();
		// String text2 = prompt.getText();
		// System.out.println(text2);
		// prompt.sendKeys("jarvi");
		// prompt.accept();
		return this;
	}

	public String getname() {
		String text = alert.getText();
		// System.out.println(text);
		return text;
	}

	public void dismiss() {
		alert.dismiss();
	}

	public void accept() {
		alert.accept();
	}

	public void sendkeys(String keys) {
		alert.sendKeys(keys);
	}

}
