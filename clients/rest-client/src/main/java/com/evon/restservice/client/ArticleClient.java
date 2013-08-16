package com.evon.restservice.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.evon.restservice.model.ApiModel;
import com.evon.restservice.model.RestUser;

/**
 * Client used for communicating with the ArticleService RESTful web service
 * @author shaines
 *
 */
@Component( "articleClient" )
public class ArticleClient
{
	/**
	 * Facilitates communication with the ArticleService; autowired by Spring
	 */
	@Autowired
	protected RestTemplate restTemplate;
	
	@Autowired
	protected Credentials credentials;
	
	/*public ArticleClient(RestTemplate restTemplate, Credentials credentials)
	{
		this.restTemplate = restTemplate;
		this.credentials = credentials;
        CommonsClientHttpRequestFactory factory = (CommonsClientHttpRequestFactory) restTemplate.getRequestFactory();
        HttpClient client = factory.getHttpClient();
        client.getState().setCredentials(AuthScope.ANY, credentials);
		
	}*/

	/**
	 * The base URL of the ArticleService web service - should be configurable
	 */
		
	// REST EASY
	
	private static String createUserUrl = "http://localhost:8081/Adaptor/UserService/createUser";
	private static String createUserUrl1 = "http://localhost:8081/RestApplication/rest/createUser";
	private static String getUserByIdUrl = "http://localhost:8081/Adaptor/UserService/getUser?userId={userId}";
	private static String updateUserUrl = "http://localhost:8081/Adaptor/UserService/updateUser";
	private static String deleteUrl = "http://localhost:8081/Adaptor/UserService/deleteUser?userId={userId}";
	private static String getOnlineTokenUrl = "https://localhost:8443/token-generator/rest/getOnlineToken/{param}";
	
	
	public String createUser(RestUser user)
	{
		System.out.println("create user::::");
		String versionDate = "2013-06-01";
		String token  = getOnlineToken("sachin");
		String result = null;
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
        requestHeaders.add("versioDate", versionDate);
        requestHeaders.add("token", token);
        HttpEntity<RestUser> requestEntity = new HttpEntity<RestUser>(user, requestHeaders);       
		//return restTemplate.postForObject(createUserUrl, user, String.class);		
        ResponseEntity<ApiModel> response = restTemplate.exchange(createUserUrl, HttpMethod.POST, requestEntity, ApiModel.class);
        System.out.println("Final Responce == > "+ response);
        if(response != null)
        {
        	result = response.getBody().getStatus();
        }
        return result;
	}
	
	public String createUser1(RestUser user)
	{
		return restTemplate.postForObject(createUserUrl1, user, String.class);		
	}
	public ApiModel getSUserById(String userId)
	{
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("userId", userId);
		System.out.println("userId v= " + userId);
		String versionDate = "2013-06-01";
		String token  = getOnlineToken("sachin");
		//String result = null;
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
        requestHeaders.add("versioDate", versionDate);
        requestHeaders.add("token", token);
        HttpEntity requestEntity = new HttpEntity(requestHeaders);       
		//return restTemplate.postForObject(createUserUrl, user, String.class);		
        ResponseEntity<ApiModel> response = restTemplate.exchange(getUserByIdUrl, HttpMethod.GET, requestEntity, ApiModel.class, uriVariables);               
        return response.getBody();
		//return restTemplate.getForObject(getUserByIdUrl, RestUser.class, id);
	}
	public String updateUser(RestUser user)
	{
		String versionDate = null;//"2013-01-01";
		String result = null;
		String token  = getOnlineToken("sachin");
		System.out.println("getID =====>"+user.getUserId());
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
        requestHeaders.add("versioDate", versionDate);
        requestHeaders.add("token", token);
        HttpEntity<RestUser> requestEntity = new HttpEntity<RestUser>(user, requestHeaders);       
		//return restTemplate.postForObject(createUserUrl, user, String.class);		
        ResponseEntity<ApiModel> response = restTemplate.exchange(updateUserUrl, HttpMethod.POST, requestEntity, ApiModel.class);
        if(response != null)
        {
        	result = response.getBody().getStatus();
        }
        return result;
		//return restTemplate.postForObject(updateUserUrl, user, String.class);
	}
	public String deleteUser(String userId)
	{
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("userId", userId);
		System.out.println("userId v= " + userId);
		String versionDate = "2013-06-01";
		//String token  = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIxLmI1YTVjMjA3LTNhNDEtNDhkOC1hNjEyLWQwZDFmODRlYWE4MSIsImF1ZCI6ImNvbW1lcmNlIiwicHJuIjoic2FjaGluIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX19.BS_x66HwQbkPoa4OxIMFg2nd4zk_JPvW2utH2DVFn2n6EfjZiLx1pfyiFlkxQlDFOhTlBYoMG-etBzyrVM2iUMqnTcgZ2DDETh8nCo4i9DE_aN7vRc80XKsszKbP6-RtibHbD2GthicOOt87lT-ZKx16zqG3SfIZR5EWdO_L2Po";
		String token  = getOnlineToken("sachin");
		//String result = null;
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
        requestHeaders.add("versioDate", versionDate);
        requestHeaders.add("token", token);
        HttpEntity requestEntity = new HttpEntity(requestHeaders);       
		//return restTemplate.postForObject(createUserUrl, user, String.class);		
        ResponseEntity<ApiModel> response = restTemplate.exchange(deleteUrl, HttpMethod.GET, requestEntity, ApiModel.class, uriVariables);               
        return response.getBody().getStatus();
		//return restTemplate.getForObject(deleteUrl, String.class, id);
	}
	
	public String getOnlineToken(String userName)
	{
		trustSelfSignedSSL();
		
		MappingJacksonHttpMessageConverter messageConverter = (MappingJacksonHttpMessageConverter)restTemplate.getMessageConverters().get(0);
		StringHttpMessageConverter stringMsgConverter = (StringHttpMessageConverter)restTemplate.getMessageConverters().get(1);
		List<HttpMessageConverter<?>> converters = restTemplate.getMessageConverters();
		converters.set(0, stringMsgConverter);
		converters.set(1, messageConverter);
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("param", userName);
		System.out.println("getOnlineToken + ===> userName v= " + userName);		
        //MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
        //HttpEntity requestEntity = new HttpEntity(requestHeaders);       
		//return restTemplate.postForObject(createUserUrl, user, String.class);        
        String response = restTemplate.getForObject(getOnlineTokenUrl, String.class, uriVariables);
        messageConverter = (MappingJacksonHttpMessageConverter)restTemplate.getMessageConverters().get(1);
		stringMsgConverter = (StringHttpMessageConverter)restTemplate.getMessageConverters().get(0);
		converters = restTemplate.getMessageConverters();
		converters.set(0, messageConverter);
		converters.set(1, stringMsgConverter);
        return response;
		//return restTemplate.getForObject(deleteUrl, String.class, id);
        
	}
	
	  public void trustSelfSignedSSL()
	  {
		  try
		  {
			  final SSLContext ctx = SSLContext.getInstance("TLS");
			  final X509TrustManager tm = new X509TrustManager()
			  {
				  public void checkClientTrusted(final X509Certificate[] xcs, final String string) throws CertificateException
				  {
					  // do nothing
				  }
				  public void checkServerTrusted(final X509Certificate[] xcs, final String string) throws CertificateException
				  {
					  // do nothing
				  }
				  public X509Certificate[] getAcceptedIssuers()
				  {
					  return null;
				  }
			  };
			  ctx.init(null, new TrustManager[]{ tm }, null);
			  SSLContext.setDefault(ctx);
		  }
		  catch (final Exception ex)
		  {
			  ex.printStackTrace();
		  }
	  }
}
