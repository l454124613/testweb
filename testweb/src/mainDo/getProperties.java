package mainDo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class getProperties {
	/**
	 * 用于读取文件
	 * 
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String read(String key) throws FileNotFoundException, IOException {
		String a = "";
		Properties pps = new Properties();
		pps.load(new FileInputStream("config.properties"));
		a = pps.getProperty(key);
		return a;

	}

}
