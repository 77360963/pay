package com.yunpan.base.tool;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtils {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
	
	/**
	 * @param strUrl	请求地址
	 * @param params	请求参数
	 * @param method	请求方法
	 * @return
	 * @throws Exception
	 */
	public static  String httpAsUrl(String strUrl, Map<String, Object> params, String method) throws Exception {
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		String result = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				if(params != null){
					strUrl = strUrl + "?" + mapToStringParams(params);
				}
			}
			URL url = new URL(strUrl);
			connection = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				connection.setRequestMethod("GET");
			} else {
				connection.setRequestMethod("POST");
				connection.setDoOutput(true);
			}
			connection.setRequestProperty("User-agent", userAgent);
			connection.setUseCaches(false);
			connection.setConnectTimeout(DEF_CONN_TIMEOUT);
			connection.setReadTimeout(DEF_READ_TIMEOUT);
			connection.setInstanceFollowRedirects(false);
			connection.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(connection.getOutputStream());
					out.writeBytes(mapToStringParams(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			result = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (connection != null) {
				connection.disconnect();
			}
		}
		return result;
	}

	// 将map型转为请求参数型
	public static String mapToStringParams(Map<String,Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(httpAsUrl("http://localhost:8080/pos/login.html",null,"GET"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
