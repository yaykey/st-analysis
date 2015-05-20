package com.st.analysis.utils.stock.finddata.ths;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.st.Global;
import com.st.framework.module.stock.ChatroomChat;
import com.st.framework.module.stock.CircleChat;
import com.st.framework.module.stock.CircleChatDetail;
import com.st.framework.module.stock.CircleChatDetailWithBLOBs;
import com.st.framework.module.stock.ThsOwner;
import com.st.framework.module.stock.ThsUser;
import com.st.framework.module.stock.example.ThsOwnerExample;
import com.st.framework.utils.db.BaseDBUtils;
import com.st.framework.utils.encrypt.impl.EncryptorMD5Impl;
import com.st.framework.utils.network.HttpStackManager;

public class FindTHSDataUtils extends BaseDBUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(FindTHSDataUtils.class);

	// http://t.10jqka.com.cn/
	private static String HOME_PAGE_URL = "http://t.10jqka.com.cn/";

	private static String BASE_ROOME_URL = "http://t.10jqka.com.cn/live/";

	// POST /circle/group/userApply/ HTTP/1.1
	private static String JOIN_URL = "http://t.10jqka.com.cn/circle/group/userApply/";

	public void joinCircle(String dataAction) {
		if (logger.isInfoEnabled()) {
			logger.info("joinCircle(String) - String dataAction=" + dataAction);
		}

		URL purl = null;
		try {
			purl = new URL(JOIN_URL);
		} catch (MalformedURLException e) {
			logger.warn("joinCircle(Integer, String) - exception ignored", e);

		}

		HttpURLConnection pconn = null;
		try {
			pconn = (HttpURLConnection) purl.openConnection();
		} catch (IOException e) {
			logger.warn("joinCircle(Integer, String) - exception ignored", e);

		}
		pconn.setRequestProperty("Referer", "http://t.10jqka.com.cn/circle");
		pconn.setRequestProperty("Cookie", myCookies.toString());
		pconn.setDoInput(true);
		pconn.setDoOutput(true);
		try {
			pconn.connect();
		} catch (IOException e) {
			logger.warn("joinCircle(Integer, String) - exception ignored", e);

		}

		OutputStreamWriter out = null;
		try {
			out = new OutputStreamWriter(pconn.getOutputStream(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			logger.warn("joinCircle(Integer, String) - exception ignored", e);

		} catch (IOException e) {
			logger.warn("joinCircle(Integer, String) - exception ignored", e);

		}

		// access 1 8
		// fid 5902 8
		// name 国琪论股_1 43
		// side 1 6

		String login = dataAction;

		try {
			out.write(login);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.warn("joinCircle(Integer, String) - exception ignored", e);

		}

		try {
			InputStream inputStream1 = pconn.getInputStream();
			inputStream1.close();
		} catch (IOException e) {
			logger.warn("joinCircle(Integer, String) - exception ignored", e);

		}

		// BufferedReader reader1 = new BufferedReader(new
		// InputStreamReader(inputStream1,"utf-8"));
		// String line1 = reader1.readLine();
		// while(line1 != null){
		// System.out.println(line1);
		// line1 = reader1.readLine();
		// }
		// reader1.close();
		// int chByte = 0;
		// FileOutputStream fileOut = new FileOutputStream(new File(outPath));
		// chByte = inputStream1.read();
		// while (chByte != -1) {
		// fileOut.write(chByte);
		// chByte = inputStream1.read();
		// }
	}

	public void findOwner() {
		Document doc = null;
		String htmlUrl = HOME_PAGE_URL;

		Connection connect = Jsoup.connect(htmlUrl).timeout(10000);

		try {
			doc = connect.get();

			Element el = doc.getElementById("mlbottom");

			Elements els = el.getElementsByClass("mlbottom-item");
			{
				Elements lis = els.get(0).getElementsByTag("ul").first()
						.getElementsByTag("a");
				parseOwnerEls(lis, "dj");
			}

			{
				Elements lis = els.get(1).getElementsByTag("ul").first()
						.getElementsByTag("a");
				parseOwnerEls(lis, "jx");
			}
		} catch (IOException e) {
			logger.warn("findOwner() - exception ignored", e);

		}
	}

	private void parseOwnerEls(Elements els, String roomType) {
		for (Element el : els) {
			String roomUrl = el.attr("href");
			String roomName = el.select("span").first().text().trim();

			ThsOwner owner = new ThsOwner();
			owner.setRoomeType(roomType);
			owner.setRoomName(roomName);
			owner.setRoomUrl(roomUrl);
			// http://t.10jqka.com.cn/live/3139/
			owner.setId(Integer.parseInt(roomUrl.substring(28,
					roomUrl.length() - 1)));

			thsOwnerManager.insertOrUpdate(owner);

			if (logger.isInfoEnabled()) {
				logger.info("parseEls(Elements, String) - FactChatroomOwner owner="
						+ owner);
			}
		}
	}

	private static HttpURLConnection connection = null;

	public static HttpURLConnection getLoginConnection() {

		// http://pass.10jqka.com.cn/login?act=loginByJsonp
		// &callback=jQuery111003583853623648132_1433920384168
		// &username=yzy0612%40163.com
		// &password=223334
		// &longLogin=true&_=1433920384170

		String lgUrl = "http://pass.10jqka.com.cn/login?act=loginByJsonp";
		lgUrl += "&callback=jQuery111003583853623648132_1433920384168";
		lgUrl += "&username=yzy0612%40163.com";
		lgUrl += "&password=223334";

		URL url = null;
		try {
			url = new URL(lgUrl);
		} catch (MalformedURLException e) {
			logger.warn("getLoginConnection() - exception ignored", e);

		}

		HttpURLConnection connection = null;
		try {
			// 建立链接
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			logger.warn("getLoginConnection() - exception ignored", e);
		}

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

		return connection;
	}

	private static StringBuilder myCookies = new StringBuilder();

	public static InputStream getInputStream(String liveurl) throws IOException {

		if (connection == null) {
			connection = getLoginConnection();
			HttpURLConnection conn = connection;
			OutputStreamWriter out = new OutputStreamWriter(
					conn.getOutputStream(), "utf-8");

			out.flush();
			out.close();

			InputStream inputStream = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "utf-8"));
			reader.close();
			// 链接到personal页面
			String headerName = null;

			// myCookies.append(cookies + ";");
			for (int i = 1; (headerName = conn.getHeaderFieldKey(i)) != null; i++) {
				if (headerName.equals("Set-Cookie")) {
					String cookie = conn.getHeaderField(i);
					cookie = cookie.substring(0, cookie.indexOf(";"));
					String cookieName = cookie
							.substring(0, cookie.indexOf("="));
					String cookieValue = cookie.substring(
							cookie.indexOf("=") + 1, cookie.length());
					myCookies.append(cookieName + "=");
					myCookies.append(cookieValue + ";");
				}
			}

			System.out.println(myCookies.toString());
		}

		// URL purl = new URL("http://t.10jqka.com.cn/live/8902/");
		URL purl = new URL(liveurl);

		HttpURLConnection pconn = (HttpURLConnection) purl.openConnection();
		pconn.setRequestProperty("Referer", "http://t.10jqka.com.cn/live");
		pconn.setRequestProperty("Cookie", myCookies.toString());

		pconn.connect();

		InputStream inputStream1 = pconn.getInputStream();
		return inputStream1;
		// BufferedReader reader1 = new BufferedReader(new
		// InputStreamReader(inputStream1,"utf-8"));
		// String line1 = reader1.readLine();
		// while(line1 != null){
		// System.out.println(line1);
		// line1 = reader1.readLine();
		// }
		// reader1.close();
		// int chByte = 0;
		// FileOutputStream fileOut = new FileOutputStream(new File(outPath));
		// chByte = inputStream1.read();
		// while (chByte != -1) {
		// fileOut.write(chByte);
		// chByte = inputStream1.read();
		// }
	}

	public void findChars(String roomeUrl, Integer roomeId) {
		Document doc = null;
		// String htmlUrl = roomeUrl;

		// Connection connect = Jsoup.connect(htmlUrl).timeout(10000);
		// getInputStream
		InputStream in = null;
		try {
			in = getInputStream(roomeUrl);
			doc = Jsoup.parse(in, "UTF-8", BASE_ROOME_URL);

			{// join
				try {
					Element el = doc.getElementById("J_SideJoinBtn");
					if ("申请加入圈子".equals(el.text().trim())) {
						String dataAction = el.attr("data-action");
						joinCircle(dataAction);

						if (in != null) {
							in.close();
						}
						doc = null;
						in = getInputStream(roomeUrl);
						doc = Jsoup.parse(in, "UTF-8", BASE_ROOME_URL);
					}
				} catch (NullPointerException e1) {
					logger.warn(
							"findChars(String, Integer) - exception ignored");

				}
			}

			{
				Element el = doc.getElementById("J_Circlelivelist");

				Elements lis = el.getElementsByTag("li");

				if (lis != null && lis.size() > 0) {
					Date dateId = new Date();
					for (Element li : lis) {
						parseCircleLi(li, roomeId, dateId);
					}
				}
			}
			{
				Element room = doc.getElementById("chatroom-main-con");
				Elements chats = null;
				try {
					chats = room.getElementsByClass("chatroom-list-other");
				} catch (Exception ex) {
					logger.warn(
							"findChars(String, Integer) - exception ignored");
					return;
				}

				if (chats != null && chats.size() > 0) {
					Date dateId = new Date();
					for (Element chat : chats) {
						parseChatroomChat(chat, roomeId, dateId);
					}
				}
			}

		} catch (IOException e) {
			logger.warn("findChars(String, Integer) - exception ignored", e);

		}

		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				logger.warn("findChars(String, Integer) - exception ignored", e);

			}
		}

	}

	private void parseChatroomChat(Element chat, Integer roomeId, Date dateId) {
		ChatroomChat chatroom = new ChatroomChat();
		chatroom.setCircleId(roomeId);
		chatroom.setDateId(dateId);

		String timeId = chat.getElementsByClass("c-txt-light").first().text();
		timeId = timeId.replaceAll("[^\\d:]*", "");
		try {
			chatroom.setTimeId(Global.DF_TIME().parse(timeId));
		} catch (ParseException e) {
			logger.warn(
					"parseChatroomChat(Element, Integer, Date) - exception ignored",
					e);

		}
		String content = chat.getElementsByClass("chatroom-content-c").first()
				.text();
		chatroom.setContent(content);

		Element ela = chat.getElementsByClass("chatroom-content-c").first()
				.getElementsByTag("a").first();
		String url = ela.attr("href");
		url = url.substring(0, url.length() - 1);
		String userId = url.substring(url.lastIndexOf("/") + 1);

		ThsUser thsUser = thsUserManager.selectByPrimaryKey(Integer
				.parseInt(userId));
		if (thsUser == null) {
			thsUser = new ThsUser();
			thsUser.setId(Integer.parseInt(userId));
			thsUser.setUserUrl(url + "/");
			thsUser.setUserName(ela.text());

			thsUserManager.insert(thsUser);
		}

		chatroom.setUserId(Integer.parseInt(userId));
		chatroom.setUserName(ela.text());

		try {
		chatroomChatManager.insertOrUpdate(chatroom);
		} catch (Exception ex) {
			logger.warn("parseChatroomChat(Element, Integer, Date) - exception ignored", ex);
			
		}
	}

	private void parseCircleLi(Element li, Integer roomeId, Date dateId) {

		CircleChat CircleChat = new CircleChat();

		CircleChat.setRoomeId(roomeId);
		CircleChat.setDateId(dateId);

		try {
			String timeId = li.getElementsByClass("circle-livelist-time")
					.first().text().trim();
			CircleChat.setTimeId(Global.DF_TIME().parse(timeId));
		} catch (ParseException e) {
			logger.warn(
					"parseCharLi(Element, Integer, Date) - exception ignored");
			return;
		} catch (NullPointerException e) {
			logger.warn("NullPointerException ignored " + e.getMessage());
			return;
		}

		Element eldes = li.getElementsByClass("circle-livelist-content")
				.first();

		Element elstrong = eldes.getElementsByTag("strong").first();

		if (elstrong != null) {

			Elements spans = elstrong.getElementsByTag("span");
			if (spans != null && spans.size() > 0) {
				StringBuffer buffer = new StringBuffer();

				for (Element span : spans) {
					buffer.append(span.html());
					buffer.append("\r\n");
				}

				CircleChat.setContent(buffer.toString());
				buffer = null;

				appendChatMD5(CircleChat);

				circleChatManager.insertOrUpdateSelective(CircleChat);

				int i = 1;
				for (Element span : spans) {
					Elements elsTemp = span.getAllElements();

					for (Element elTemp : elsTemp) {

						if (elTemp.hasText()
								&& !"".equals(elTemp.text().trim())) {
							CircleChatDetailWithBLOBs chatDetail = new CircleChatDetailWithBLOBs();

							chatDetail.setCircleId(CircleChat.getId());
							chatDetail.setSort(i);
							chatDetail.setRoomeId(roomeId);
							chatDetail.setContent(elTemp.text());

							circleChatDetailManager
									.insertOrUpdateSelective(chatDetail);

							i++;
						} else if ("img".equalsIgnoreCase(elTemp.tagName())) {
							CircleChatDetailWithBLOBs chatDetail = new CircleChatDetailWithBLOBs();

							chatDetail.setCircleId(CircleChat.getId());
							chatDetail.setSort(i);
							chatDetail.setRoomeId(roomeId);
							chatDetail.setContent(elTemp.html());

							String imgUrl = elTemp.attr("src");

							appendImg(chatDetail, imgUrl);

							circleChatDetailManager
									.insertOrUpdateSelective(chatDetail);

							i++;
						}
					}

				}

			} else {
				CircleChat.setContent(elstrong.text());
				appendChatMD5(CircleChat);

				circleChatManager.insertOrUpdateSelective(CircleChat);
			}

		} else {// 无 content,只有img

			appendChatMD5(CircleChat);

			circleChatManager.insertOrUpdateSelective(CircleChat);

			Elements imgs = eldes.getElementsByTag("img");

			if (imgs != null && imgs.size() > 0) {
				for (int i = 1; i <= imgs.size(); i++) {
					Element img = imgs.get(i - 1);
					CircleChatDetailWithBLOBs chatDetail = new CircleChatDetailWithBLOBs();

					chatDetail.setCircleId(CircleChat.getId());
					chatDetail.setSort(i);
					chatDetail.setContent(img.html());
					chatDetail.setRoomeId(roomeId);

					String imgUrl = img.attr("src");

					try {
						appendImg(chatDetail, imgUrl);
					} catch (Exception ex) {
						logger.warn("parseCircleLi(Element, Integer, Date) - exception ignored", ex);
						
					}
					circleChatDetailManager.insertOrUpdateSelective(chatDetail);

				}
			}

		}

	}

	private void appendImg(CircleChatDetailWithBLOBs chatDetail, String url) {
		HttpClient httpClient = HttpStackManager.getInstance().getHttpclient();

		int extnum = url.lastIndexOf('.');
		String ext = url.substring(extnum + 1);

		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = null;
		HttpEntity entity = null;
		BufferedImage image = null;
		ByteArrayOutputStream out = null;
		try {
			response = httpClient.execute(httpGet);
			entity = response.getEntity();

			image = ImageIO.read(entity.getContent());
			out = new ByteArrayOutputStream();
			ImageIO.write(image, ext, out);

			byte[] b = out.toByteArray();

			chatDetail.setImg(b);

		} catch (ClientProtocolException e) {
			logger.warn(
					"appendImg(FactChatroomChatsContentWithBLOBs, String) - exception ignored",
					e);

		} catch (IOException e) {
			logger.warn(
					"appendImg(FactChatroomChatsContentWithBLOBs, String) - exception ignored",
					e);

		} finally {
			if (entity != null) {
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					logger.warn(
							"appendImg(FactChatroomChatsContentWithBLOBs, String) - exception ignored",
							e);

				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					logger.warn(
							"appendImg(FactChatroomChatsContentWithBLOBs, String) - exception ignored",
							e);

				}
			}

		}
	}

	private void appendChatMD5(CircleChat factChatroomChats) {
		EncryptorMD5Impl md4Impl = new EncryptorMD5Impl();
		if (factChatroomChats.getContent() != null
				&& !"".equals(factChatroomChats.getContent().trim())) {
			factChatroomChats.setMd5Code(md4Impl.encrypt(factChatroomChats
					.getContent()));
		} else {
			factChatroomChats.setMd5Code(md4Impl.encrypt(""
					+ factChatroomChats.getRoomeId()
					+ Global.DF_DAY().format(factChatroomChats.getDateId())
					+ Global.DF_TIME().format(factChatroomChats.getTimeId())));
		}
	}

	public void findAll() {
		ThsOwnerExample example = new ThsOwnerExample();
		List<ThsOwner> list = thsOwnerManager.selectByExample(example);

		for (ThsOwner owner : list) {
			FindTHSDataUtils findTHSDataUtils = new FindTHSDataUtils();
			findTHSDataUtils.findChars(owner.getRoomUrl(), owner.getId());
			findTHSDataUtils = null;
		}
	}

	public static void main(String[] args) {
		// new FindTHSDataUtils().findChars("http://t.10jqka.com.cn/live/8902/",
		// 8902);
		new FindTHSDataUtils().findAll();
	}
}
