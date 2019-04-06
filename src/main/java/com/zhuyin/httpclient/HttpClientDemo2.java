package com.zhuyin.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


/**
 * <p>Title: HttpClientDemo2.java</p>
 * <p>Description: </p>
 * <p>Company: </p>
 * @author ZY
 * <p> Just go on !!!</p>
 * @date 2018年5月8日 下午10:38:40
 * @version v1.0
 */
public class HttpClientDemo2 {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		//以下是get方法
		HttpGet httpGet = new HttpGet("http://www.baidu.com");
		String body = "";
		HttpResponse response;
		HttpEntity entity;
		response = httpclient.execute(httpGet);
		entity = response.getEntity();
		body = EntityUtils.toString(entity, Consts.UTF_8);// 这个就是页面源码了
		httpGet.abort();// 中断请求,接下来可以开始另一段请求
		System.out.println(body);
		System.out.println("=========================================================================");
		// httpGet.releaseConnection();//释放请求.如果释放了相当于要清空session

		// 以下是post方法
		HttpPost httpPost = new HttpPost("http://www.baidu.com");// 一定要改成可以提交的地址,这里用百度代替
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("name", "1"));// 名值对
		nvps.add(new BasicNameValuePair("account", "xxxx"));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		response = httpclient.execute(httpPost);
		entity = response.getEntity();
		body = EntityUtils.toString(entity, Consts.UTF_8);
		System.out.println("Login form get: " + response.getStatusLine());// 这个可以打印状态
		httpPost.abort();
		System.out.println(body);
		httpPost.releaseConnection();
	}
}
