package com.evon.restservice.client;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
	
	
	public String createUser(RestUser user)
	{
		System.out.println("create user::::");
		String versionDate = "2013-06-01";
		String token  = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIxLmI1YTVjMjA3LTNhNDEtNDhkOC1hNjEyLWQwZDFmODRlYWE4MSIsImF1ZCI6ImNvbW1lcmNlIiwicHJuIjoic2FjaGluIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX19.BS_x66HwQbkPoa4OxIMFg2nd4zk_JPvW2utH2DVFn2n6EfjZiLx1pfyiFlkxQlDFOhTlBYoMG-etBzyrVM2iUMqnTcgZ2DDETh8nCo4i9DE_aN7vRc80XKsszKbP6-RtibHbD2GthicOOt87lT-ZKx16zqG3SfIZR5EWdO_L2Po";
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
		String token  = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIxLmI1YTVjMjA3LTNhNDEtNDhkOC1hNjEyLWQwZDFmODRlYWE4MSIsImF1ZCI6ImNvbW1lcmNlIiwicHJuIjoic2FjaGluIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX19.BS_x66HwQbkPoa4OxIMFg2nd4zk_JPvW2utH2DVFn2n6EfjZiLx1pfyiFlkxQlDFOhTlBYoMG-etBzyrVM2iUMqnTcgZ2DDETh8nCo4i9DE_aN7vRc80XKsszKbP6-RtibHbD2GthicOOt87lT-ZKx16zqG3SfIZR5EWdO_L2Po";
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
		String token  = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIxLmI1YTVjMjA3LTNhNDEtNDhkOC1hNjEyLWQwZDFmODRlYWE4MSIsImF1ZCI6ImNvbW1lcmNlIiwicHJuIjoic2FjaGluIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX19.BS_x66HwQbkPoa4OxIMFg2nd4zk_JPvW2utH2DVFn2n6EfjZiLx1pfyiFlkxQlDFOhTlBYoMG-etBzyrVM2iUMqnTcgZ2DDETh8nCo4i9DE_aN7vRc80XKsszKbP6-RtibHbD2GthicOOt87lT-ZKx16zqG3SfIZR5EWdO_L2Po";
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
		String token  = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiIxLmI1YTVjMjA3LTNhNDEtNDhkOC1hNjEyLWQwZDFmODRlYWE4MSIsImF1ZCI6ImNvbW1lcmNlIiwicHJuIjoic2FjaGluIiwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVzZXIiXX19.BS_x66HwQbkPoa4OxIMFg2nd4zk_JPvW2utH2DVFn2n6EfjZiLx1pfyiFlkxQlDFOhTlBYoMG-etBzyrVM2iUMqnTcgZ2DDETh8nCo4i9DE_aN7vRc80XKsszKbP6-RtibHbD2GthicOOt87lT-ZKx16zqG3SfIZR5EWdO_L2Po";
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
	// group on api access**********************/
	public String groponDeals(String clientId)
	{
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("clientId", clientId);
		System.out.println("groponDeals + + ===> clientId v= " + clientId);		
        MultiValueMap<String, String> requestHeaders = new LinkedMultiValueMap<String, String>();
        HttpEntity requestEntity = new HttpEntity(requestHeaders);       
		//return restTemplate.postForObject(createUserUrl, user, String.class);		
        ResponseEntity<String> response = restTemplate.exchange(deleteUrl, HttpMethod.GET, requestEntity, String.class, uriVariables);               
        return response.getBody();
		//return restTemplate.getForObject(deleteUrl, String.class, id);
	}
	}
