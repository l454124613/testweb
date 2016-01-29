package excel;

import java.io.IOException;

public class doExcel {
	/**
	 * 
	 * @param filename
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 */
	public String[][] read(final String filename, final String sheetname) {
		try {
			final String[][] s = new withExcel().openExcel(filename).read(
					sheetname);

			return s;
		} catch (final Exception e) {
			//
			e.printStackTrace();
		}
		return new String[][] { { "错误信息" } };
	}

	/**
	 * 用于读取excel的表格值
	 * 
	 * @param filename
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @return
	 */
	public String read(final String filename, final String sheetname,
			final int rownum, final int cellnum) {
		try {
			final String s = new withExcel().openExcel(filename).read(
					sheetname, rownum, cellnum);

			return s;
		} catch (final Exception e) {
			//
			e.printStackTrace();
		}
		return "错误信息";
	}

	/**
	 * 第一个是（1,1）
	 * @param sheetname
	 * @param rownum
	 * @param cellnum
	 * @param value
	 * @param args
	 *            超链接格式l!linkPath 颜色格式要 c!coloer 颜色有: AQUA
	 * 
	 *            AUTOMATIC
	 * 
	 *            BLACK
	 * 
	 *            BLUE
	 * 
	 *            BLUE_GREY
	 * 
	 *            BRIGHT_GREEN
	 * 
	 *            BROWN
	 * 
	 *            CORAL
	 * 
	 *            CORNFLOWER_BLUE
	 * 
	 *            DARK_BLUE
	 * 
	 *            DARK_GREEN
	 * 
	 *            DARK_RED
	 * 
	 *            DARK_TEAL
	 * 
	 *            DARK_YELLOW
	 * 
	 *            GOLD
	 * 
	 *            GREEN
	 * 
	 *            GREY_25_PERCENT
	 * 
	 *            GREY_40_PERCENT
	 * 
	 *            GREY_50_PERCENT
	 * 
	 *            GREY_80_PERCENT
	 * 
	 *            INDIGO
	 * 
	 *            LAVENDER
	 * 
	 *            LEMON_CHIFFON
	 * 
	 *            LIGHT_BLUE
	 * 
	 *            LIGHT_CORNFLOWER_BLUE
	 * 
	 *            LIGHT_GREEN
	 * 
	 *            LIGHT_ORANGE
	 * 
	 *            LIGHT_TURQUOISE
	 * 
	 *            LIGHT_YELLOW
	 * 
	 *            LIME
	 * 
	 *            MAROON
	 * 
	 *            OLIVE_GREEN
	 * 
	 *            ORANGE
	 * 
	 *            ORCHID
	 * 
	 *            PALE_BLUE
	 * 
	 *            PINK
	 * 
	 *            PLUM
	 * 
	 *            RED
	 * 
	 *            ROSE
	 * 
	 *            ROYAL_BLUE
	 * 
	 *            SEA_GREEN
	 * 
	 *            SKY_BLUE
	 * 
	 *            TAN
	 * 
	 *            TEAL
	 * 
	 *            TURQUOISE
	 * 
	 *            VIOLET
	 * 
	 *            WHITE
	 * 
	 *            YELLOW
	 * 
	 * @throws IOException
	 */
	public void write(final String filename, final String sheetname,
			final int rownum, final int cellnum, final String value,
			final String... args) {

		try {
			new withExcel().openExcel(filename).write2exist(sheetname, rownum,
					cellnum, value, args);
		} catch (final IOException e) {

			e.printStackTrace();
		}
	}
}
