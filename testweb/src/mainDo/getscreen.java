package mainDo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class getscreen {
	private static Logger logger = Logger.getLogger(getscreen.class);

	/**
	 * 用于截屏
	 * 
	 * @param driver
	 * @param filename
	 *            //文件名
	 */
	public static void snapshot(WebDriver driver, String filename) {
		// this method will take screen shot ,require two parameters ,one is
		// driver name, another is file name
		logger.info(filename);
		String currentPath = System.getProperty("user.dir"); // get current work
		TakesScreenshot ss = (TakesScreenshot) driver; // folder
		System.out.println(currentPath);
		File scrFile = ss.getScreenshotAs(OutputType.FILE);
		// Now you can do whatever you need to do with it, for example copy
		// somewhere
		try {
			System.out.println("save snapshot path is:" + currentPath + "/"
					+ filename);
			FileUtils.copyFile(scrFile, new File(currentPath
					+ "/report/picture/" + filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Can't save screenshot");
			e.printStackTrace();
		} finally {

			System.out.println("screen shot finished");
		}
	}

	/**
	 * 用于制造缩略图
	 * 
	 * @param picname
	 * @throws Exception
	 */
	public static void JpgTset(String picname) throws Exception {
		logger.info(picname);
		File _file = new File("report/picture/" + picname); // 读入文件
		Image src = javax.imageio.ImageIO.read(_file); // 构造Image对象
		int wideth = src.getWidth(null); // 得到源图宽
		int height = src.getHeight(null); // 得到源图长
		BufferedImage tag = new BufferedImage(wideth / 4, height / 4,
				BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(src, 0, 0, wideth / 4, height / 4, null); // 绘制缩小后的图
		FileOutputStream out = new FileOutputStream("report/picture/cc"
				+ picname); // 输出到文件流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(tag); // 近JPEG编码
		// System.out.print(width+"*"+height);
		out.close();
	}
}
