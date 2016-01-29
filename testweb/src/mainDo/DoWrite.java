package mainDo;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.log4j.Logger;

public class DoWrite {
	private static Logger logger = Logger.getLogger(DoWrite.class);

	public static void add(String file, String conent, boolean over) {
		// logger.info(file + " " + conent);
		BufferedWriter out = null;
		try {
			file = file.replace("\\", "/");
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file, over)));
			out.write(conent + "\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
