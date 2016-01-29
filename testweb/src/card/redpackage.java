package card;

import http.httpRequest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class redpackage {
	/**
	 * 1为红包,其他是打折券
	 */
	public int cc = 1;
	private httpRequest h = new httpRequest();

	// regz r = regz.getInstance();

	public void setregPackage() throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 显示当前时间的格式

		try {
			// 请求获得验证码和批次号
			String[] a = h
					.connect(
							"http://10.100.200.38/CardManager/custom/insertCardLnPage.do",
							"Cookie-=-user_userName=admin; user_empNo=admin");
			// 提取验证码和批次号
			final Pattern pat = Pattern
					.compile("value=\"(\\d{4})\".*value=\"(\\w{6})\" style=\"float: left;");
			final Matcher mat = pat.matcher(a[0]);
			String card_ln = "";
			String yanzheng = "";
			if (mat.find()) {

				card_ln = mat.group(1);
				yanzheng = mat.group(2);
			}
			String card_tpye = "";
			// 判断是否红包
			if (cc == 1) {
				card_tpye = "20";
			} else {
				card_tpye = "30";
			}
			// 连接成功批次红包
			h.connect(
					"http://10.100.200.38/CardManager/custom/insertCardLnData.do ",
					"Cookie-=-user_userName=admin; user_empNo=admin",
					"postvalue-=-ln="
							+ card_ln
							+ "&cardType="
							+ card_tpye
							+ "&cardLnName=test&cardLnType=5&cardMoney=5&pointLimit=500&cardLimitMoney=11&issue=system&cardCrc="
							+ yanzheng
							+ "&cardCount=1&maxcardCount=500000&reserveNumber=0&channelCode=0&rangeCode=0&brandCode=&codeValue=&rangeValue=&activeTime_int="
							+ df.format(new Date())
							+ "+00%3A00%3A00"
							+ "&expireTime_int="
							+ df.format(new Date(
									new Date().getTime() + 2000000000))
							+ "+23%3A59%3A59" + "&act=in");
			// 连接领用批次红包
			h.connect(
					"http://10.100.200.38/CardManager/custom/insertCardRecipients.do ",
					"Cookie-=-user_userName=admin; user_empNo=admin",
					"postvalue-=-------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition: \tform-data; name=\"cardType\"\r\n\r\n"
							+ card_tpye
							+ "\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition: \tform-data; name=\"issue\"\r\n\r\nsystem\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition:\tform-data; name=\"cardCrc\"\r\n\r\n"
							+ yanzheng
							+ "\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition: \tform-data; name=\"crWho\"\r\n\r\nxxx\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition: \tform-data; name=\"crCount\"\r\n\r\n1\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition: \tform-data; name=\"crRemark\"\r\n\r\n\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition: \tform-data; name=\"crFilePath\"; \r\nfilename=\"\"\r\nContent-Type: \tapplication/octet-stream\r\n\r\n\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition \tform-data; name=\"recipientsTime\"\r\n\r\n"
							+ df.format(new Date())
							+ "\t00:10:00"
							+ "\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk\r\nContent-Disposition: \tform-data; name=\"crType\"\r\n\r\n1\r\n------WebKitFormBoundaryAABj7rc8cUgfrTWk--\r\n\r\n",
					"Content-Type-=-multipart/form-data; boundary=----WebKitFormBoundaryAABj7rc8cUgfrTWk");
			// 连接下载16位红包文件
			h.httpDownload(
					"http://10.100.200.38/CardManager/custom/downCardDataToExcel.do?userId=&cardLn="
							+ card_ln
							+ "&cardMoney=0&brandCode=&isReserve=-1&cardType="
							+ card_tpye
							+ "&status=-2&activeTime_int=&cardNo=&expireTime_int=&currentPage=1&isAll=0 ",
					"download/tmp.zip", "user_userName=admin; user_empNo=admin");
			// 读取文件内容
			readZipFile("download/tmp.zip");
		} catch (Exception e) {
			throw new Exception("获得卡券失败" + e.getMessage());
		}
	}

	/**
	 * 查看zip中的文件内容
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void readZipFile(String file) throws Exception {
		ZipFile zf = new ZipFile(file);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ZipInputStream zin = new ZipInputStream(in);
		ZipEntry ze;
		while ((ze = zin.getNextEntry()) != null) {
			// if (ze.isDirectory()) {
			// } else {
			// System.err.println("file - " + ze.getName() + " : "
			// + ze.getSize() + " bytes");
			// long size = ze.getSize();
			// if (size > -2) {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					zf.getInputStream(ze)));
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			br.close();
			// }
			
			

		}
		zin.closeEntry();
	}

	public static void main(String[] args) throws Exception {
		new redpackage().setregPackage();
	}
}
