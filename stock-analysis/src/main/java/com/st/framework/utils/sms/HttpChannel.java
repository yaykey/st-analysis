/*
 * 文件名： s.java
 * 
 * 创建日期： 2009-9-29
 *
 * Copyright(C) 2009, Ryoma.
 *
 * 原始作者: <a href="mailto:lemom8000@gmail.com">Ryoma</a>
 *
 */
package com.st.framework.utils.sms;

/**
 * post方式发送信息
 *
 * @author <a href="mailto:lemom8000@gmail.com">Ryoma</a>
 *
 * @version $Revision: 1.1 $
 *
 * @since 2009-9-29
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLDecoder;

public class HttpChannel {

	public final static int HTTP_POST_REQUEST_CONNECT_TIMEOUT = 30 * 1000;
	public final static int HTTP_POST_RESPONSE_BUFFER_SIZE = 1024 * 16;
	public final static String INTERNAL_CHARSET = "utf8";

	private final URL url;

	private HttpChannel(String url) throws IOException {
		this.url = new URL(url);
	}

	/**
	 * 获取HttpChannel对象
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 */
	public static HttpChannel valueOf(String url) throws IOException {
		return new HttpChannel(url);
	}

	/**
	 * 发送post消息
	 * 
	 * @param content
	 * @return
	 * @throws IOException 
	 * @throws IOException 
	 * @throws IOException
	 */
	public String sendPostRequest(String content) throws IOException{
		HttpURLConnection conn = (HttpURLConnection) this.url.openConnection();
		conn.setConnectTimeout(HTTP_POST_REQUEST_CONNECT_TIMEOUT);
		conn.setReadTimeout(HTTP_POST_REQUEST_CONNECT_TIMEOUT);
		conn.setDoOutput(true);
		OutputStream output = null;
		try {
			conn.setRequestMethod("POST");
			output = conn.getOutputStream();
			output.write(content.getBytes(INTERNAL_CHARSET));
		} catch (ProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		}finally{
			if(output!=null){
				output.flush();
				output.close();
			}
		}
		BufferedReader reader=null;
		String value = null;
		try {
			reader = new BufferedReader(new InputStreamReader(conn
					.getInputStream()));
			String line;
			StringBuffer buffer = new StringBuffer(HTTP_POST_RESPONSE_BUFFER_SIZE);
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			value = buffer.toString();
			value = URLDecoder.decode(value, INTERNAL_CHARSET);
		} catch (IOException e) {
			throw e;
		}finally{
			if(reader!=null){
				reader.close();
			}
		}
		conn.disconnect();
		return value;
	}

}
