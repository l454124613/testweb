package mainDo;

import java.io.FileNotFoundException;
import java.io.IOException;

import excel.doExcel;

public class excelTo {
	/**
	 * 用来制作excel的临时文件
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String Tmp() throws FileNotFoundException, IOException {
		getProperties gp = new getProperties();
		String[][] a;
		DoWrite dw = new DoWrite();
		a = new doExcel().read(gp.read("casePath"), gp.read("sheetname"));
		int title = 1;
		int ti = Integer.parseInt(gp.read("title")) - 1;
		int im = Integer.parseInt(gp.read("importantlevel")) - 1;
		int step = Integer.parseInt(gp.read("step")) - 1;
		String filename = String.valueOf(System.currentTimeMillis()) + ".tmp";
		dw.add(filename, "#临时文档", false);
		for (int i = 1; i < a.length; i++) {
			if (a[i][ti].length() > 0 & a[i][im].length() > 0
					& a[i][step].length() > 0) {
				dw.add(filename, String.valueOf(title) + "/"
						+ a[i][ti].replaceAll("\t", "").replaceAll(" ", "")
						+ ":" + a[i][im], true);
				title++;
				String[] b = a[i][step].split("\n");
				for (int j = 0; j < b.length; j++) {
					dw.add(filename, b[j], true);
				}
			}

		}
		// System.out.println(a.split("\n"));

		return filename;
	}
}
