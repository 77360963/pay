/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package com.yunpan.base.tool;

import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class HttpsClientUtilWithCer {
	private CloseableHttpClient httpclient;
	private static final Logger logger = LoggerFactory
			.getLogger(HttpsClientUtilWithCer.class);

	private static class SSLHandler implements X509TrustManager,
			HostnameVerifier {
		private SSLHandler() {
		}

		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}

		public boolean verify(String arg0, SSLSession arg1) {
			return true;
		}
	}

	private HttpsClientUtilWithCer() {
		try {
			if (httpclient == null) {
				SSLContext sc = SSLContext.getInstance("TLS");
				sc.init(null, new TrustManager[] { new SSLHandler() }, null);
				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
						sc, new HostnameVerifier() {

							@Override
							public boolean verify(String paramString,
									SSLSession paramSSLSession) {
								// TODO Auto-generated method stub
								return true;
							}

						});
				
				RequestConfig defaultRequestConfig = RequestConfig.custom()
						  .setSocketTimeout(20000)
						  .setConnectTimeout(20000)
						  .setConnectionRequestTimeout(20000)
						  .setStaleConnectionCheckEnabled(true)
						  .build();
				
				httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).setDefaultRequestConfig(defaultRequestConfig)
						.build();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public CloseableHttpClient getClient(){
		return this.httpclient;
	}
	

	public static String sendPostBinRequest(String url, String reqStr,
			String encode) throws Exception {
		logger.info("===============>请求报文:[" + url + "][" + reqStr + "]");
		CloseableHttpResponse rsp = null;
		 HttpsClientUtilWithCer c = new  HttpsClientUtilWithCer();
			CloseableHttpClient httpclient= c.getClient();
		try {
			RequestBuilder builder = RequestBuilder.post().setUri(new URI(url));
			HttpEntity s = new StringEntity(reqStr, encode);
			builder.setEntity(s);
			HttpUriRequest req = builder.build();
			
			rsp = httpclient.execute(req);
			if (rsp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String resultStr = EntityUtils
						.toString(rsp.getEntity(), encode);
				logger.info("===============>返回报文:[" + url + "][" + resultStr
						+ "]");
				return resultStr;
			} else {
				logger.info("==========>返回状态异常["
						+ rsp.getStatusLine().getStatusCode() + "]");
				return null;
			}
		} catch (Exception ek) {
			logger.error(ek.getMessage(), ek);
			throw ek;
		}  finally {
			if (rsp != null) {
				rsp.close();
			}
			httpclient.close();
		}

	}

	public static byte[] sendPostRequestWrithReturnBytes(String url,
			Map<String, String> param, String encode) throws Exception {
		logger.info("===============>请求报文:[" + url + "][" + param + "]");

		RequestBuilder builder = RequestBuilder.post().setUri(new URI(url));
		if (param != null) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				builder.addParameter(entry.getKey(), entry.getValue());
			}
		}
		 HttpsClientUtilWithCer c = new  HttpsClientUtilWithCer();
			CloseableHttpClient httpclient= c.getClient();
		CloseableHttpResponse rsp = httpclient.execute(builder.build());
		try {
			if (rsp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				byte[] resultStr = EntityUtils.toByteArray(rsp.getEntity());
				logger.info("===============>返回报文:[" + url + "][" + resultStr
						+ "]");
				return resultStr;
			} else {
				logger.info("==========>返回状态异常["
						+ rsp.getStatusLine().getStatusCode() + "]");
				return null;
			}
		} catch (Exception ek) {
			logger.error(ek.getMessage(), ek);
			throw ek;
		} finally {
			if (rsp != null) {
				rsp.close();
			}
			 httpclient.close();
		}

	}

	public static String sendPostRequest(String url, Map<String, String> param,
			String encode) throws Exception {
		logger.info("===============>请求报文:[" + url + "][" + param + "]");

		RequestBuilder builder = RequestBuilder.post().setUri(new URI(url));
		if (param != null) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				builder.addParameter(entry.getKey(), entry.getValue());
			}
		}
		 HttpsClientUtilWithCer c = new  HttpsClientUtilWithCer();
			CloseableHttpClient httpclient= c.getClient();
		CloseableHttpResponse rsp = httpclient.execute(builder.build());
		try {

			// HttpUriRequest req = builder.build();
			// rsp = httpclient.execute(req);
			if (rsp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String resultStr = EntityUtils
						.toString(rsp.getEntity(), encode);
				logger.info("===============>返回报文:[" + url + "][" + resultStr
						+ "]");
				return resultStr;
			} else {
				logger.info("==========>返回状态异常["
						+ rsp.getStatusLine().getStatusCode() + "]");
				return null;
			}
		}  catch (Exception ek) {
			logger.error(ek.getMessage(), ek);
			throw ek;
		} finally {
			if (rsp != null) {
				rsp.close();
			}
			 httpclient.close();
		}

	}

	public static String sendGetRequest(String url, Map<String, String> param,
			String encode) throws Exception {
		logger.info("===============>请求报文:[" + url + "][" + param + "]");

		RequestBuilder builder = RequestBuilder.get().setUri(new URI(url));
		if (param != null) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
				builder.addParameter(entry.getKey(), entry.getValue());
			}
		}
		 HttpsClientUtilWithCer c = new  HttpsClientUtilWithCer();
		CloseableHttpClient httpclient= c.getClient();
		CloseableHttpResponse rsp = null;
		try {
			HttpUriRequest req = builder.build();
			rsp = httpclient.execute(req);
			// if (rsp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String resultStr = EntityUtils.toString(rsp.getEntity(), encode);
			logger.info("===============>返回报文:[" + url + "][" + resultStr + "]");
			return resultStr;
			// } else {
			// logger.info("==========>返回状态异常["
			// + rsp.getStatusLine().getStatusCode() + "]");
			// return null;
			// }
		} catch (Exception ek) {
			logger.error(ek.getMessage(), ek);
			throw ek;
		} finally {
			if (rsp != null) {
				rsp.close();
			}
			httpclient.close();
		}

	}

	public static String sendPostRequest(String url, Map<String, String> param)
			throws Exception {
		return sendPostRequest(url, param, "utf-8");
	}

	public static String sendGetRequest(String url, Map<String, String> param)
			throws Exception {
		return sendGetRequest(url, param, "utf-8");
	}

	public static void main(String[] args) throws Exception {
		// HttpsClientUtilWithCer.init("c:/temp/baidu.cer");
		HttpsClientUtilWithCer.sendPostRequest(
				"http://localhost:8080/uinonPay/payNotify", null);
	}

	/*
	 * public final static void main(String[] args) throws Exception {
	 * 
	 * CloseableHttpClient httpclient =
	 * HttpClients.custom().setSSLSocketFactory(sslsf).build(); try {
	 * 
	 * HttpGet httpget = new
	 * HttpGet("https://api.mch.weixin.qq.com/secapi/pay/refund");
	 * 
	 * System.out.println("executing request" + httpget.getRequestLine());
	 * 
	 * CloseableHttpResponse response = httpclient.execute(httpget); try {
	 * HttpEntity entity = response.getEntity();
	 * 
	 * System.out.println("----------------------------------------");
	 * System.out.println(response.getStatusLine()); if (entity != null) {
	 * System.out.println("Response content length: " +
	 * entity.getContentLength()); BufferedReader bufferedReader = new
	 * BufferedReader(new InputStreamReader( entity.getContent())); String text;
	 * while ((text = bufferedReader.readLine()) != null) {
	 * System.out.println(text); }
	 * 
	 * } EntityUtils.consume(entity); } finally { response.close(); } } finally
	 * { httpclient.close(); } }
	 */

}
