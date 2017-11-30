package com.langzi.wechat.confirmer.util;

import org.json.simple.JSONObject;

import java.net.URL;
import java.net.URLConnection;

import org.apache.http.*;


public class UserDataDecryptor {

	public static final String WX_PUBLIC_URL="https://api.weixin.qq.com/sns/jscode2session";
	
	public void get3rdSession() {

//	    Cache userInfoCache = Redis.use("userInfo");
	    String sessionId = "";
	    JSONObject json = new JSONObject();
	    String url = "http://www.google.com/search?q=httpClient";


	        URL oracle = new URL("http://www.oracle.com/");
	        URLConnection yc = oracle.openConnection();

	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream()));
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) 
	            System.out.println(inputLine);
	        in.close();
	        
	    HttpGet httpget = new HttpGet("http://httpbin.org/");
	    
	    HttpClient client = HttpClientBuilder.create().build();
	    HttpGet request = new HttpGet(url);

	    // add request header
	    request.addHeader("User-Agent", USER_AGENT);
	    HttpResponse response = client.execute(request);

	    System.out.println("Response Code : "
	                    + response.getStatusLine().getStatusCode());

	    BufferedReader rd = new BufferedReader(
	    	new InputStreamReader(response.getEntity().getContent()));

	    StringBuffer result = new StringBuffer();
	    String line = "";
	    while ((line = rd.readLine()) != null) {
	    	result.append(line);
	    }
	    
	    HttpRequest request = new HttpGetRequest();
	    
	    String code = getPara("code");
	    String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx7560b8008e2c445d&secret=f1af3312b7038513fd17dd9cbc3b357c&js_code=" + code + "&grant_type=authorization_code";

	    String session = ExecLinuxCMDUtil.instance.exec("cat /dev/urandom |od -x | tr -d ' '| head -n 1").toString();
	    json.put("session", session);

	    HttpClient method = new HttpPost(url);
	    
	    CloseableHttpClient httpClient = getHttpClient();
	    try {
	        //用get方法发送http请求
	        HttpGet get = new HttpGet(url);
	        System.out.println("执行get请求:...." + get.getURI());
	        CloseableHttpResponse httpResponse = null;
	        //发送get请求
	        httpResponse = httpClient.execute(get);
	        try {
	            //response实体
	            HttpEntity entity = httpResponse.getEntity();
	            if (null != entity) {
	                String result = EntityUtils.toString(entity);
	                System.out.println(result);
	                JSONObject resultJson = JSONObject.fromObject(result);
	                String session_key = resultJson.getString("session_key");
	                String openid = resultJson.getString("openid");
	                //session存储
	                userInfoCache.set(session,session_key+","+openid);
	                }
	            } finally {
	                httpResponse.close();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                closeHttpClient(httpClient);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        renderJson(json);
	}
	private CloseableHttpClient getHttpClient() {
	    return HttpClients.createDefault();
	}

	private void closeHttpClient(CloseableHttpClient client) throws IOException {
	    if (client != null) {
	        client.close();
	    }


}
