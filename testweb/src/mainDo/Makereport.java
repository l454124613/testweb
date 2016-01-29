package mainDo;

import org.apache.log4j.Logger;

public class Makereport {
	private sstring sst = new sstring();
	private String file = "";
	private int stepnum = 0;
	private long starttime;
	private long reptime;
	private int err = 0, warn = 0, pass = 0;
	private static Logger logger = Logger.getLogger(Makereport.class);
	private int stepallnum = 0;

	private Makereport() {

		String a = sst.rehead;
		DoWrite.add("report/index.html", a, false);

	}

	private static Makereport m = new Makereport();

	/**
	 * 单例模式
	 */
	public static Makereport getinstance() {
		return m;
	}

	/**
	 * 开始计时
	 */
	public void reportstart() {
		reptime = System.currentTimeMillis();// 开始计时
	}

	/**
	 * 用于开始报告文件
	 * 
	 * @param file
	 *            文件名
	 */
	public void Todetail(String file) {
		logger.info("file");
		this.file = file;// 文件名共享
		String b = sst.head;
		DoWrite.add("report/" + file, b, false);// 添加报告头
		stepnum = 0;// 初始化操作步骤
		starttime = System.currentTimeMillis();// 开始计时
	}

	/**
	 * 添加具体报告描述
	 * 
	 * @param name
	 *            描述或用例
	 */
	public void detailnameadd(String name) {
		logger.info(name);
		String b = sst.addname(name);
		DoWrite.add("report/" + file, b, true);// 添加用例名称

	}

	/**
	 * 用于记录操作的的步骤信息
	 * 
	 * @param ccpic
	 *            缩略图地址
	 * @param pic
	 *            大图地址
	 * @param stepname
	 *            步骤名称
	 */
	public void addstep(String ccpic, String pic, String stepname) {
		logger.info(ccpic + " " + pic + " " + stepname);
		String b = sst.addbody(ccpic, pic, stepname);// 获取html
		DoWrite.add("report/" + file, b, true);// 添加步骤
		stepnum++;// 记录步骤数
		stepallnum++;// 添加总步数
		// logger.error(stepallnum);
	}

	/**
	 * 用于添加报错日志
	 * 
	 * @param log
	 */
	public void adddetaillog(String log) {
		logger.info(log);
		DoWrite.add("report/" + file, "<br class=\"clear\" /> <p>" + log
				+ "</p>", true);// 添加报错日志
	}

	/**
	 * 添加具体文档尾
	 */
	public void Offdetail() {
		String b = sst.end;
		DoWrite.add("report/" + file, b, true);
	}

	/**
	 * 用于一条聚合报告用例
	 * 
	 * @param title
	 *            用例标题
	 * @param priority
	 *            优先级
	 * @param res
	 *            结果
	 * @param attachment
	 *            备注
	 */
	public void addreport(String title, String priority, int res,
			String attachment) {
		logger.info(title + " " + priority + " " + res + " " + attachment);
		String resu = "";
		switch (res) {// 获得结果
		case -1:
			resu = "不通过";
			err++;
			break;
		case 0:
			resu = "警告";
			warn++;
			break;
		case 1:
			resu = "通过";
			pass++;
			break;
		}
		String a = sst.addrebody(title,
				String.valueOf(System.currentTimeMillis() - starttime),
				String.valueOf(stepnum), priority, resu, attachment, file);
		DoWrite.add("report/index.html", a, true);
		// logger.error(stepallnum);
	}

	/**
	 * 用于添加成功失败数量
	 * 
	 * @param runform
	 */
	public void addrepfoot(String runform) {
		logger.info(runform);
		String res = "未执行";
		if (err > 0) {
			res = "不通过";
		} else {
			if (warn > 0) {
				res = "警告";
			} else {
				if (pass > 0) {
					res = "通过";
				}
			}
		}
		String a = sst.addrefoot(
				String.valueOf(System.currentTimeMillis() - reptime), runform,
				res, String.valueOf(stepallnum));

		a = a + sst.addrere(err, warn, pass);
		DoWrite.add("report/index.html", a, true);
	}

	/**
	 * 用于添加聚合报告尾
	 */
	public void Offreport() {
		String a = sst.reend;
		DoWrite.add("report/index.html", a, true);
	}
}
