package com.st.framework.utils.network;

import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLException;

import java.io.*;
import java.net.UnknownHostException;

/**
 * Created by xu on 2014/7/23.
 */
public class HttpStackManager {
    private static final Logger logger = Logger.getLogger(HttpStackManager.class);
    private static final String ECODEING = "utf-8";
    RequestConfig requestConfig;

    private HttpStackManager() {
        requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).setConnectionRequestTimeout(5000).build();
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(1200);
        cm.setDefaultMaxPerRoute(400);

//        cm.setDefaultMaxPerRoute(100); //定义每个路径的最大连接数量  总共两个地址 可以平均fen一下


        ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {

            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                // 可以根据应答的keepalived 参数进行设置 'keep-alive' header
//                HeaderElementIterator it = new BasicHeaderElementIterator(
//                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
//                while (it.hasNext()) {
//                    HeaderElement he = it.nextElement();
//                    String param = he.getName();
//                    String value = he.getValue();
//                    if (value != null && param.equalsIgnoreCase("timeout")) {
//                        try {
//                            return Long.parseLong(value) * 1000;
//                        } catch(NumberFormatException ignore) {
//                        }
//                    }
//                }
//                HttpHost target = (HttpHost) context.getAttribute(
//                        HttpClientContext.HTTP_TARGET_HOST);
                return 1000* 30;
            }

        };


        HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

            public boolean retryRequest(
                    IOException exception,
                    int executionCount,
                    HttpContext context) {
                if (executionCount >= 3) {
                    // Do not retry if over max retry count
                    return false;
                }
                if (exception instanceof InterruptedIOException) {
                    // 超时
                    return true;
                }
                if (exception instanceof UnknownHostException) {
                    // Unknown host
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {
                    // 拒绝连接的情况下
                    return true;
                }
                if (exception instanceof SSLException) {
                    // SSL 握手异常 不进行重试
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                if (idempotent) {
                    //只重试幂等性的请求
                    return true;
                }
                return false;
            }

        };

        httpclient = HttpClients.custom().setConnectionManager(cm).setKeepAliveStrategy(myStrategy).setRetryHandler(myRetryHandler).build();
    }

    public static HttpStackManager getInstance() {
        return InstanceHolder.instance;
    }

    //用内部类来初始化单实例的Manager
    private static class InstanceHolder {
        private InstanceHolder() {
        }

        public static HttpStackManager instance = new HttpStackManager();
    }

    public CloseableHttpClient getHttpclient() {
        return httpclient;
    }

    private CloseableHttpClient httpclient;

    /**
     * ＳＳ对接执行ＰＯＳＴ方法
     */
    public String execPost(HttpUriRequest request) {

        logger.info("HttpStackManager : ip.dest = " + request.getURI() + "    request args = " + request.getRequestLine());

        String respstr = "";

        try {
            CloseableHttpResponse resp = this.getHttpclient().execute(request);
            HttpEntity respEntity = resp.getEntity();
            respstr = EntityUtils.toString(respEntity, "utf-8");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        logger.info("HttpStackManager  response = " + respstr);
        return respstr;
    }

    public static String findGetData(String url) {
    	HttpClient httpClient = HttpStackManager.getInstance().getHttpclient();
		
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response=null;
		HttpEntity entity = null;
		try {
			response = httpClient.execute(httpGet);
			entity = response.getEntity();
			
			String res = EntityUtils.toString(entity, "utf-8");
			
			EntityUtils.consume(entity);
			
			return res;			
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
		
		return null;
    }
}
