package com.wmq.sys.utils;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.*;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * HTTP请求工具类
 * 
 * @author zhangkun
 */
public class HttpConnHelper {
	private static final Logger log = LogManager.getLogger(HttpConnHelper.class);
	private static String toKen;
	private static Date tokenDate = null;


	/**
	 * GET请求
	 */
	public static String doHttpRequest(String url) {
		if (StringUtils.isEmpty(url)) {
			return "";
		}
		HttpClient client = new DefaultHttpClient();
		HttpRequestBase request = null;
		String content = "";
		try {
			request = new HttpGet(url);
			HttpResponse response = client.execute(request);
			response.setHeader("charset","utf-8");
			HttpEntity entity = response.getEntity();
			content = EntityUtils.toString(entity, HTTP.UTF_8);
		} catch (ClientProtocolException e) {
			log.error("doHttpGetRequest ClientProtocolException.", e);
		} catch (IllegalStateException e) {
			log.error("doHttpGetRequest IllegalStateException.", e);
		} catch (IOException e) {
			log.error("doHttpGetRequest IOException.", e);
		} finally {
			if (request != null && !request.isAborted()) {
				request.abort();
			}
			client.getConnectionManager().shutdown();
		}
		return content;
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param postParams
	 * @return HTTP响应返回内容
	 */
	public static String doHttpRequest(String url, Object postParams) {
		if (StringUtils.isEmpty(url)) {
			return "";
		}
		if (postParams == null) {
			return doHttpRequest(url);
		}
		HttpClient client = new DefaultHttpClient();
		HttpRequestBase request = null;
		HttpEntity entity = null;
		String content = "";
		try {
			request = new HttpPost(url);
			if (postParams instanceof Map) {
				Map<String, String> params = (Map<String, String>) postParams;
				setPostParams(request, params);
			} else if (postParams instanceof String) {
				String params = (String) postParams;
				setPostParams(request, params);
			}
			HttpResponse response = client.execute(request);
			entity = response.getEntity();
			content = EntityUtils.toString(entity, HTTP.UTF_8);
		} catch (ClientProtocolException e) {
			log.error("doHttpPostRequest ClientProtocolException.", e);
		} catch (IllegalStateException e) {
			log.error("doHttpPostRequest IllegalStateException.", e);
		} catch (UnsupportedEncodingException e) {
			log.error("doHttpPostRequest UnsupportedEncodingException.", e);
		} catch (IOException e) {
			log.error("doHttpPostRequest IOException.", e);
		} catch (Exception e) {
			log.error("doHttpPostRequest Exception.", e);
		} finally {
			if (request != null && !request.isAborted()) {
				request.abort();
			}
			client.getConnectionManager().shutdown();
		}
		return content;
	}

	/**
	 * 设置POST请求参数
	 * 
	 * @param request
	 * @param postParams
	 * @throws UnsupportedEncodingException
	 */
	public static void setPostParams(HttpRequestBase request,
			Map<String, String> postParams) throws UnsupportedEncodingException {
		List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
		for (Map.Entry<String, String> entry : postParams.entrySet()) {
			postData.add(new BasicNameValuePair(entry.getKey(), entry
					.getValue()));
		}
		AbstractHttpEntity entity = new UrlEncodedFormEntity(postData,
				HTTP.UTF_8);
		((HttpPost) request).setEntity(entity);
	}

	/**
	 * 设置POST请求参数
	 * 
	 * @param request
	 * @param postParams
	 * @throws UnsupportedEncodingException
	 */
	private static void setPostParams(HttpRequestBase request, String postParams)
			throws UnsupportedEncodingException {
		AbstractHttpEntity entity = new StringEntity(postParams, HTTP.UTF_8);
		((HttpPost) request).setEntity(entity);
	}

	/**
	 * 发起https请求,客户端直接信任所有证书
	 */
	public static String httpsRequest(String requestUrl, Object output) {
		if (StringUtils.isEmpty(requestUrl)) {
			return "";
		}
		if (output==null) {
			return httpsRequest(requestUrl);
		}
		HttpClient httpclient = null;
		HttpPost httpPost = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

			} };
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, tm, new SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpclient = new DefaultHttpClient();
			//通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上 
			httpclient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, ssf));
			httpPost = new HttpPost(requestUrl);
			HttpParams httpParameters = new BasicHttpParams();
			httpPost.setParams(httpParameters);
			// 设置超时时间
			HttpConnectionParams
					.setConnectionTimeout(httpParameters, 30 * 1000);
			HttpConnectionParams.setSoTimeout(httpParameters, 30 * 1000);
			// 设置请求参数
			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
			if (output instanceof Map) {
				Map<String, String> paramsMap = (Map<String, String>) output;
				Set<String> keys = paramsMap.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					pairs.add(new BasicNameValuePair(key, paramsMap.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
			} else if (output instanceof String) {
				String postParams = (String) output;
				httpPost.setEntity(new StringEntity(postParams, "UTF-8"));
			}
			// 执行请求
			HttpResponse httpRespons = httpclient.execute(httpPost);
			// 获取返回值
			HttpEntity entity = httpRespons.getEntity();
			String content = EntityUtils.toString(entity, HTTP.UTF_8);
			return content;
		} catch (ConnectException ce) {
			log.error("httpsRequest ConnectException.", ce);
		} catch (Exception e) {
			log.error("httpsRequest Exception.", e);
		} finally {
			if (httpPost != null && !httpPost.isAborted()) {
				httpPost.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return "";
	}
	/**
	 * 发起https get请求,客户端直接信任所有证书
	 */
	public static String httpsRequest(String requestUrl) {
		if (StringUtils.isEmpty(requestUrl)) {
			return "";
		}
		HttpClient httpclient = null;
		HttpGet httpGet = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new X509TrustManager() {

				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

			} };
			SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, tm, new SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			httpclient = new DefaultHttpClient();
			//通过SchemeRegistry将SSLSocketFactory注册到我们的HttpClient上 
			httpclient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, ssf)); 
			httpGet = new HttpGet(requestUrl);
			HttpParams httpParameters = new BasicHttpParams();
			httpGet.setParams(httpParameters);
			// 设置超时时间
			HttpConnectionParams
					.setConnectionTimeout(httpParameters, 30 * 1000);
			HttpConnectionParams.setSoTimeout(httpParameters, 30 * 1000);
			// 执行请求
			HttpResponse httpRespons = httpclient.execute(httpGet);
			// 获取返回值
			HttpEntity entity = httpRespons.getEntity();
			String content = EntityUtils.toString(entity, HTTP.UTF_8);
			return content;
		} catch (ConnectException ce) {
			log.error("httpsRequest ConnectException.", ce);
		} catch (Exception e) {
			log.error("httpsRequest Exception.", e);
		} finally {
			if (httpGet != null && !httpGet.isAborted()) {
				httpGet.abort();
			}
			if (httpclient != null) {
				httpclient.getConnectionManager().shutdown();
			}
		}
		return "";
	}
	/**
	 * 发起https请求,加载服务器端证书
	 * 
	 * @param requestUrl请求地址
	 * @return String
	 */
	public static String httpsRequestWithTrust(String requestUrl, Object output) {
		if (StringUtils.isEmpty(requestUrl)) {
			return "";
		}
		HttpClient httpclient = null;
		HttpPost httpPost = null;
		BufferedReader reader = null;
		try {
			// 下载的证书放到项目中的config目录中
			InputStream is = HttpConnHelper.class
					.getResourceAsStream("/mangoStore.p12");
			log.info("is==" + is);
			log.info("storePath="
					+ HttpConnHelper.class.getResource("/mangoStore.p12")
							.getPath());
			// InputStream is = new FileInputStream("D:\\mangoStore.p12");
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(is, "changeit".toCharArray());
			SSLSocketFactory socketFactory = new SSLSocketFactory(
					keyStore, "changeit");
			Scheme sch = new Scheme("https", 443, socketFactory);
			httpclient = new DefaultHttpClient();
			httpclient.getConnectionManager().getSchemeRegistry().register(sch);
			httpPost = new HttpPost(requestUrl);
			HttpParams httpParameters = new BasicHttpParams();
			httpPost.setParams(httpParameters);
			// 设置超时时间
			HttpConnectionParams
					.setConnectionTimeout(httpParameters, 30 * 1000);
			HttpConnectionParams.setSoTimeout(httpParameters, 30 * 1000);
			// 设置请求参数
			List<BasicNameValuePair> pairs = new ArrayList<BasicNameValuePair>();
			if (output instanceof Map) {
				Map<String, String> paramsMap = (Map<String, String>) output;
				Set<String> keys = paramsMap.keySet();
				for (Iterator<String> i = keys.iterator(); i.hasNext();) {
					String key = (String) i.next();
					pairs.add(new BasicNameValuePair(key, paramsMap.get(key)));
				}
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
			} else if (output instanceof String) {
				String postParams = (String) output;
				httpPost.setEntity(new StringEntity(postParams, "UTF-8"));
			}
			// 执行请求
			HttpResponse httpRespons = httpclient.execute(httpPost);
			// 获取返回值
			InputStream inStream = httpRespons.getEntity().getContent();
			reader = new BufferedReader(
					new InputStreamReader(inStream, "utf-8"));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			String ret = sb.toString();
			return ret;
		} catch (Exception e) {
			log.error("httpsRequestWithTrust Exception", e);
		} finally {
			if (httpPost != null && !httpPost.isAborted()) {
				httpPost.abort();
			}
			if (httpclient != null)
				httpclient.getConnectionManager().shutdown();
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
				}
				reader = null;
			}
		}
		return "";
	}

	/**
	 * POST JSON字符串
	 * @param requestUrl
	 * @param jsonStr
	 * @return
	 */
	public static String doPostJson(String requestUrl,String jsonObj){
		if (StringUtils.isEmpty(requestUrl)) {
			return "";
		}
		String result = "";
		try{
			URL console = new URL(requestUrl);
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			HttpURLConnection conn = (HttpURLConnection) console.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
					"UTF-8");
			out.write(jsonObj);
			out.flush();
			out.close();
			//读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
            		conn.getInputStream()));
             String lines;
             StringBuffer sb = new StringBuffer("");
             while ((lines = reader.readLine()) != null) {
                 lines = new String(lines.getBytes(), "utf-8");
                 sb.append(lines);
             }
             result = sb.toString();
             reader.close();
             //断开连接
             conn.disconnect();
		} catch (Exception e) {
			log.error("doPostJson.exception:",e);
		}
	    return result;
	}

	public static InputStream doPostJsonStream(String requestUrl,String jsonObj){
		if (StringUtils.isEmpty(requestUrl)) {
			return null;
		}
		String result = "";
		try{
			URL console = new URL(requestUrl);
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			HttpURLConnection conn = (HttpURLConnection) console.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
					"UTF-8");
			out.write(jsonObj);
			out.flush();
			out.close();
			//读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));

			return conn.getInputStream();
		} catch (Exception e) {
			log.error("doPostJson.exception:",e);
		}
		return null;
	}

	//获取环信TOKEN
	public static String getToken(){
		try {
			if(tokenDate==null||isTokenOverdue()){
				String  newURL ="https://a1.easemob.com/wnx614/wmq/token";
				JSONObject obj = new JSONObject();
				obj.element("grant_type", "client_credentials");
				obj.element("client_id", "YXA6I5swoFumEeWGOUmum67THw");
				obj.element("client_secret", "YXA6XuDOv_2qrnNncVfaLVdlntbi5CI");
				String newToken = doPostJson(newURL, obj.toString());
				toKen = newToken.toString().substring(17, 80);
				tokenDate = new Date();
			}
			return toKen;
		} catch (Exception e) {
			log.error("getToken.exception:",e);
		}
		return null;
	}

	private static boolean isTokenOverdue(){
	//	Calendar calendar=Calendar.getInstance();
	//	calendar.setTime(tokenDate);
	//	calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+5);
	//	int renewalTime =  calendar.get(Calendar.DATE);

		//calendar.setTime(new Date());
		//int Currenttime = calendar.get(Calendar.DAY_OF_MONTH);

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String renewalTime = format.format(new Date(tokenDate.getTime() + 5 * 24 * 60 * 60 * 1000));

		Date d =  new Date();
		String currentTime  = format.format(d);

	    if(currentTime.equals(renewalTime)){
			return true;
		}else{
			return false;
		}
	}

	//请求环信用户注册接口
	public static String doPostJsonHx(String requestUrl,String jsonObj){
		getToken();
		if (StringUtils.isEmpty(requestUrl)) {
			return "";
		}
		String result = "";
		try{
			URL console = new URL(requestUrl);
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			HttpURLConnection conn = (HttpURLConnection) console.openConnection();
			conn.setRequestProperty("Content-Type","application/json");
			conn.setRequestProperty("Authorization","Bearer "+toKen);
			System.out.println("toKen"+toKen);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
					"UTF-8");
			out.write(jsonObj);
			out.flush();
			out.close();
			//读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
            		conn.getInputStream()));
             String lines;
             StringBuffer sb = new StringBuffer("");
             while ((lines = reader.readLine()) != null) {
                 lines = new String(lines.getBytes(), "utf-8");
                 sb.append(lines);
             }
             result = sb.toString();
             reader.close();
             //断开连接
             conn.disconnect();
		} catch (Exception e) {
			log.error("doPostJsonHx.exception:",e);
		}
	    return result;
	}

	//PUT请求环信用户注册接口
	public static String doPutJsonHx(String requestUrl,String jsonObj){
		getToken();
		if (StringUtils.isEmpty(requestUrl)) {
			return "";
		}
		String result = "";
		try{
			URL console = new URL(requestUrl);
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			HttpURLConnection conn = (HttpURLConnection) console.openConnection();
			conn.setRequestProperty("Authorization","Bearer "+toKen);
			System.out.println("toKen"+toKen);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setRequestMethod("PUT");
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(),
					"UTF-8");
			out.write(jsonObj);
	        out.flush();
			out.close();
			//读取响应
            BufferedReader reader = new BufferedReader(new InputStreamReader(
            		conn.getInputStream()));
             String lines;
             StringBuffer sb = new StringBuffer("");
             while ((lines = reader.readLine()) != null) {
                 lines = new String(lines.getBytes(), "utf-8");
                 sb.append(lines);
             }
             result = sb.toString();
             reader.close();
             //断开连接
             conn.disconnect();
		} catch (Exception e) {
			log.error("doPutJsonHx.exception:",e);
		}
	    return result;
	}




	public static void main(String[] args) throws Exception {

	}



	//忽略安全证书
	static HostnameVerifier hv = new HostnameVerifier() {
        public boolean verify(String urlHostName, SSLSession session) {
            System.out.println("Warning: URL Host: " + urlHostName + " vs. "
                               + session.getPeerHost());
            return true;
        }
    };

    private static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext
                .getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc
                .getSocketFactory());
    }

    static class miTM implements TrustManager,
            X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(
                X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(
                X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(
                X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(
                X509Certificate[] certs, String authType)
                throws CertificateException {
            return;   
        }   
    }  

}
