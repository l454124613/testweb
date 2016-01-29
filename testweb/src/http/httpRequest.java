package http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class httpRequest {
	/**
	 * cc为调制参数,0是本地代理,2是获得头文件,3是本地代理加头文件
	 */
	public int cc = 1;

	/**
	 * 
	 * @param url
	 * @param value
	 *            postvalue-=- post的参数必须这个格式, 其他也是以等于分割
	 * @throws Exception
	 */
	public String[] connect(String url, String... value) throws Exception {
		if (cc == 0 | cc == 3) {
			System.setProperty("http.proxyHost", "localhost");
			System.setProperty("http.proxyPort", "8888");
			System.setProperty("https.proxyHost", "localhost");
			System.setProperty("https.proxyPort", "8888");
		}
		String postvalue = "";
		URL postUrl = null;
		if (url.contains("http")) {
			postUrl = new URL(url);
		} else {
			url = "http://" + url;
			postUrl = new URL(url);
		}
		int check = value.length;
		Map<String, String> mm = new HashMap<String, String>();
		if (check > 0) {

			for (int i = 0; i < value.length; i++) {
				String[] aa = value[i].split("-=-");
				mm.put(aa[0], aa[1]);
			}
		}
		if (mm.containsKey("postvalue")) {
			postvalue = mm.get("postvalue");
			postvalue = postvalue.replace(" ", "");
			postvalue = postvalue.replace(",", "&");
			mm.remove("postvalue");
		}

		final HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();
		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		for (Map.Entry<String, String> entry : mm.entrySet()) {
			// System.out.println(entry.getKey().replace(" ", "") + "~"
			// + entry.getValue().replace(" ", ""));
			connection.setRequestProperty(entry.getKey().replace(" ", ""),
					entry.getValue().replace(" ", ""));
		}

		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		// Post 请求不能使用缓存
		connection.setUseCaches(false);

		connection.connect();

		final DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		// The URL-encoded contend
		// 正文，正文内容其实跟get的URL中'?'后的参数字符串一致

		// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面
		out.writeBytes(postvalue);

		out.flush();
		out.close(); // flush and close
		final BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream(), "UTF-8"));
		String line;
		String res = "";

		while ((line = reader.readLine()) != null) {
			// System.out.println(line);
			res = res + line;
		}
		// System.out.println(res);

		reader.close();
		String aa = "\n";
		if (cc == 2 | cc == 3) {
			Map<String, List<String>> map = connection.getHeaderFields();

			for (Map.Entry<String, List<String>> entry : map.entrySet()) {
				aa = aa + entry.getValue().get(0) + "\n";
				// System.out.println(entry.getKey() + ":" +
				// entry.getValue().get(0));
			}
		}
		connection.disconnect();
		return new String[] { res, aa };
	}

	/** http下载 */
	public boolean httpDownload(String httpUrl, String saveFile, String cookies) {
		// 下载网络文件
		int bytesum = 0;
		int byteread = 0;

		URL url = null;
		try {
			url = new URL(httpUrl);
		} catch (MalformedURLException e1) {

			e1.printStackTrace();
			return false;
		}

		try {
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("Cookie", cookies);
			InputStream inStream = conn.getInputStream();
			conn.connect();
			FileOutputStream fs = new FileOutputStream(saveFile);

			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread;
				// System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

}
