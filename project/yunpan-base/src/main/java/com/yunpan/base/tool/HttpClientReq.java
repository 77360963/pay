package com.yunpan.base.tool;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 
 * @author Administrator
 * 
 */
public class HttpClientReq {

	private String url;
	private static final Logger logger = LoggerFactory
			.getLogger(HttpClientReq.class);
	private CloseableHttpClient client;

	public HttpClientReq(String url) {
		super();
		this.url = url;
		client = HttpClients.createDefault();
	}

	public <T> T sendPostRawRequest(String contextPath, Object reqObj,
			Class<T> returnClazz) throws Exception {
		HttpPost post = new HttpPost(url + contextPath);
		String req = JSON.toJSONString(reqObj);
		logger.info("===========>请求报文:" + req);
		StringEntity reqEntity = new StringEntity(req, ContentType.create(
				"application/json", Consts.UTF_8));
		reqEntity.setChunked(true);
		post.setEntity(reqEntity);
		CloseableHttpResponse response = client.execute(post);

		/*
		 * // 服务器返回码 int status_code = response.getStatusLine().getStatusCode();
		 * System.out.println("status_code = " + status_code);
		 */// 服务器返回内容
		try {
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String rsp = EntityUtils.toString(entity);
				logger.info("===========>响应报文:" + rsp);
				return (T) JSON.parseObject(rsp, returnClazz);
			}
		} finally {
			response.close();
		}
		return null;

	}

	public static void main(String[] args) throws Exception {
		/*Map<String, String> param = new HashMap<String, String>();
		param.put("key", "CUSTST");

		HttpClientReq req = new HttpClientReq("http://localhost:6001");
		Map<String, Object> i = req.sendPostRawRequest(
				"/parameter/getParameter", param, Map.class);
		System.out.println(i);*/

	/*	RestTemplate rtl = new RestTemplate();
		ParameterReq reqq = new ParameterReq();
		reqq.setKey("CUSTST");
		ResponseEntity<ParameterResult> rsp = rtl.postForEntity(
				"http://localhost:6001/parameter/getParameter", reqq,
				ParameterResult.class);
		ParameterResult r = rsp.getBody();
		System.out.println(r.toString());*/
		
		
	

	}
}
