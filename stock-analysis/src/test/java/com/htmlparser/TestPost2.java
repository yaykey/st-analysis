package com.htmlparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TestPost2 {
	//http://pass.10jqka.com.cn/login?act=loginByJsonp&callback=jQuery111003583853623648132_1433920384168&username=yzy0612%40163.com&password=223334&longLogin=true&_=1433920384170
	public static void main(String args[]) throws IOException {
		//http://pass.10jqka.com.cn/login?act=loginByJsonp
		//&callback=jQuery111003583853623648132_1433920384168
		//&username=yzy0612%40163.com
		//&password=223334
		//&longLogin=true&_=1433920384170
		
		//Scanner scanner = new Scanner(System.in);
		//System.out.println("请输入用户名：");
		//String user_name = scanner.next();
		//System.out.println("请输入密码：");
		//String password = scanner.next();
		String user_name = "yzy0612%40163.com";
		String password = "223334";
		
		testPost(user_name, password, "d:/fileDown2.txt");
		testJsoup();
	}

	public static void testPost(String user_name, String password,
			String outPath) throws IOException {
		
		//http://pass.10jqka.com.cn/login?act=loginByJsonp
				//&callback=jQuery111003583853623648132_1433920384168
				//&username=yzy0612%40163.com
				//&password=223334
				//&longLogin=true&_=1433920384170
		
		String login = "";
		//URL url = new URL("http://passport.mop.com");
		String lgUrl = "http://pass.10jqka.com.cn/login?act=loginByJsonp";
		lgUrl += "&callback=jQuery111003583853623648132_1433920384168";
		lgUrl += "&username=" + user_name;
		lgUrl += "&password=" + password;
		
		URL url = new URL(lgUrl);
		
		HttpURLConnection connection = null;
		connection = (HttpURLConnection) url.openConnection();// 建立链接

		connection.setInstanceFollowRedirects(false);
		connection.setRequestProperty("Connection", "keep-alive");
		connection
				.setRequestProperty(
						"User-Agent",
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36");
		connection.addRequestProperty("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		connection.setDoInput(true);
		connection.setDoOutput(true);
		// connection.disconnect();
		
		HttpURLConnection conn = connection;
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
				"utf-8");
				
		out.flush();
		out.close();
		InputStream inputStream = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream, "utf-8"));
		reader.close();
		// 链接到personal页面
		String headerName = null;
		StringBuilder myCookies = new StringBuilder();
		// myCookies.append(cookies + ";");
		for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++) {
			if (headerName.equals("Set-Cookie")) {
				String cookie = conn.getHeaderField(i);
				cookie = cookie.substring(0, cookie.indexOf(";"));
				String cookieName = cookie.substring(0, cookie.indexOf("="));
				String cookieValue = cookie.substring(cookie.indexOf("=") + 1,
						cookie.length());
				myCookies.append(cookieName + "=");
				myCookies.append(cookieValue + ";");
			}
		}
		
//		URL purl = new URL("http://passport.mop.com/personal");
		URL purl = new URL("http://t.10jqka.com.cn/live/8902/");
		
		HttpURLConnection pconn = (HttpURLConnection) purl.openConnection();
		pconn.setRequestProperty("Referer", "http://t.10jqka.com.cn/live");
		pconn.setRequestProperty("Cookie", myCookies.toString());

		pconn.connect();
		InputStream inputStream1 = pconn.getInputStream();
		// BufferedReader reader1 = new BufferedReader(new
		// InputStreamReader(inputStream1,"utf-8"));
		// String line1 = reader1.readLine();
		// while(line1 != null){
		// System.out.println(line1);
		// line1 = reader1.readLine();
		// }
		// reader1.close();
		int chByte = 0;
		FileOutputStream fileOut = new FileOutputStream(new File(outPath));
		chByte = inputStream1.read();
		while (chByte != -1) {
			fileOut.write(chByte);
			chByte = inputStream1.read();
		}
	}

	private static String getCookies(HttpURLConnection conn) {
		// TODO Auto-generated method stub
		// StringBuffer cookies = new StringBuffer();
		StringBuilder cookies = new StringBuilder();
		String headName;

		for (int i = 1; (headName = conn.getHeaderField(i)) != null; i++) {

			StringTokenizer st = new StringTokenizer(headName, "; ");

			while (st.hasMoreTokens()) {

				cookies.append(st.nextToken() + "; ");
			}
		}
		return cookies.toString();
	}

	private static void testJsoup() throws IOException {
		// 解析html文档
		File input = new File("D:/fileDown2.txt");
		Document doc = Jsoup.parse(input, "UTF-8");
		// for(Element ele : doc.getElementsByClass("zhnc").select("ul")){
		// if(!ele.select("li").toString().equals("")){
		// String text = ele.select("li").text();
		// System.out.println("user_name is:"+text);
		// }
		// }
		Elements ele = doc.getElementsByClass("zhnc").select("ul");
		if (!ele.select("li").toString().equals("")) {
			String text = ele.select("li").text();
			System.out.println("user_name is:" + text);
		} else {
			System.out.println("登录失败");
		}
	}
}
