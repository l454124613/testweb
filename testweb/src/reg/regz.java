package reg;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 用于字符串的正则匹配
 * 
 * @author lxc
 * 
 */
public class regz {
	private static Logger logger = Logger.getLogger(regz.class);
	private static regz instance = new regz();

	private regz() {
	}

	public static regz getInstance() {
		return instance;
	}

	/**
	 * 用于记录匹配的数量
	 * 
	 * @param source
	 *            原文件爱你
	 * @param reg
	 *            正则
	 * @return 数量
	 */
	public int count(final String source, final String reg) {
		logger.info(source + " " + reg);
		final Pattern pat = Pattern.compile(reg);
		final Matcher mat = pat.matcher(source);
		int count = 0;
		while (mat.find()) {
			count++;
		}
		return count;

	}

	/**
	 * 
	 * @param source
	 *            要匹配的字符串
	 * @param reg
	 *            正则表达式
	 * @return 有就返回第一个 ;没有就返回null
	 */
	public String match(final String source, final String reg) {
		logger.info(source + " " + reg);
		final Pattern pat = Pattern.compile(reg);
		final Matcher mat = pat.matcher(source);
		if (mat.find()) {
			return mat.group();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @param source
	 *            要匹配的字符串
	 * @param reg
	 *            正则表达式
	 * @return 有就返回第一个 ;没有就返回null
	 * @param fromNumber
	 *            从第几个开始匹配
	 * 
	 */
	public String match(final String source, final String reg,
			final int fromNumber) {
		logger.info(source + " " + reg);
		final Pattern pat = Pattern.compile(reg);
		final Matcher mat = pat.matcher(source);
		if (mat.find(fromNumber)) {
			return mat.group();
		} else {
			return null;
		}
	}
}
