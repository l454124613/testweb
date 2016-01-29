package test;

import mainDo.DoWrite;
import excel.doExcel;

public class zaza {
	public static void main(String[] args) {
		String[][] a;
		DoWrite dw = new DoWrite();
		a = new doExcel().read("logs\\积分.xlsx", "红包分摊");
		int title = 1;
		String filename = String.valueOf(System.currentTimeMillis()) + ".tmp";
		dw.add(filename, "#临时文档", false);
		for (int i = 1; i < a.length; i++) {
			if (a[i][2].length() > 0 & a[i][3].length() > 0
					& a[i][6].length() > 0) {
				dw.add(filename, String.valueOf(title) + "/"
						+ a[i][2].replaceAll("\t", "").replaceAll(" ", "")
						+ ":" + a[i][3], true);
				title++;
				String[] b = a[i][6].split("\n");
				for (int j = 0; j < b.length; j++) {
					dw.add(filename, b[j], true);
				}
			}

		}
		// System.out.println(a.split("\n"));

	}
}
