package com.st.analysis.utils.stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.st.framework.utils.db.BaseDBUtils;

public class DDXUtils extends BaseDBUtils {

	// http://ddx.gubit.cn/ygetddxdata.php?code=dp000001&m=0.4112513389112381&date=2015-05-20

	// 创建CookieStore实例
	static CookieStore cookieStore = null;
	static HttpClientContext context = null;

	public static void findData() {

		String url = "http://ddx.gubit.cn/ygetddxdata.php?code=dp000001&date=2015-05-20";
		url += "&m=" + Math.random();

		System.out.println(url);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);

		CookieStore cookieStore = new BasicCookieStore();
		{
			BasicClientCookie cookie = new BasicClientCookie("AJSTAT_ok_times",
					"2");

			// cookie.setVersion(0);
			cookie.setDomain("ddx.gubit.cn");
			cookie.setPath("/");
			//cookie.setExpiryDate(cal.getTime());
			cookie.setSecure(false);
			cookie.setAttribute("HttpOnly", "No");
			cookieStore.addCookie(cookie);
		}

		{
			BasicClientCookie cookie = new BasicClientCookie("AJSTAT_ok_pages",
					"2");

			// cookie.setVersion(0);
			cookie.setDomain("ddx.gubit.cn");
			cookie.setPath("/");
			//cookie.setExpiryDate(cal.getTime());
			cookie.setSecure(false);
			cookie.setAttribute("HttpOnly", "No");
			cookieStore.addCookie(cookie);
		}

		{
			BasicClientCookie cookie = new BasicClientCookie("tingpai", "0");

			// cookie.setVersion(0);
			cookie.setDomain("ddx.gubit.cn");

			cookie.setPath("/");
			cookie.setExpiryDate(cal.getTime());
			cookie.setSecure(false);
			cookie.setAttribute("HttpOnly", "No");
			cookieStore.addCookie(cookie);
		}

		System.out.println(cookieStore);

		CloseableHttpClient httpClient = HttpClients.custom()
				.setDefaultCookieStore(cookieStore).build();

		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = null;
		HttpEntity entity = null;

		try {

			response = httpClient.execute(httpGet);

			entity = response.getEntity();

			String res = EntityUtils.toString(entity, "utf-8");

			EntityUtils.consume(entity);

			System.out.println(res);

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (entity != null) {
				try {
					EntityUtils.consume(entity);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void setContext() {
		System.out.println("----setContext");
		context = HttpClientContext.create();
		Registry<CookieSpecProvider> registry = RegistryBuilder
				.<CookieSpecProvider> create()
				.register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
				.register(CookieSpecs.BROWSER_COMPATIBILITY,
						new BrowserCompatSpecFactory()).build();
		context.setCookieSpecRegistry(registry);
		context.setCookieStore(cookieStore);
	}

	public static void setCookieStore(HttpResponse httpResponse) {
		System.out.println("----setCookieStore");
		cookieStore = new BasicCookieStore();
		// JSESSIONID
		String setCookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
		String JSESSIONID = setCookie.substring("JSESSIONID=".length(),
				setCookie.indexOf(";"));
		System.out.println("JSESSIONID:" + JSESSIONID);
		// 新建一个Cookie
		BasicClientCookie cookie = new BasicClientCookie("JSESSIONID",
				JSESSIONID);
		cookie.setVersion(0);
		cookie.setDomain("127.0.0.1");
		cookie.setPath("/");
		// cookie.setAttribute(ClientCookie.VERSION_ATTR, "0");
		// cookie.setAttribute(ClientCookie.DOMAIN_ATTR, "127.0.0.1");
		// cookie.setAttribute(ClientCookie.PORT_ATTR, "8080");
		// cookie.setAttribute(ClientCookie.PATH_ATTR, "/CwlProWeb");
		cookieStore.addCookie(cookie);
	}

	
	public static List<NameValuePair> getParam(Map parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator it = parameterMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry parmEntry = (Entry) it.next();
			param.add(new BasicNameValuePair((String) parmEntry.getKey(),
					(String) parmEntry.getValue()));
		}
		return param;
	}

	public static void main(String[] args) {
		findData();
	}
}
