package com.evon.restservice.main;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.evon.restservice.client.ArticleClient;
import com.evon.restservice.model.ApiModel;
import com.evon.restservice.model.RestUser;

public class RestTemplateExample
{
	public static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(	"applicationContext.xml" );
	public static ArticleClient articleClient = applicationContext.getBean( "articleClient", ArticleClient.class);
	public static void main( String[] args )
	{
		//createRestUser();		
		//getUserById();
		//updateUser();
		//deleteUser();		
		//getOnlineToken();
		pushService();
		
	}
	
	
	
	private static void getOnlineToken() 
	{
		// TODO Auto-generated method stub
		String responce = articleClient.getOnlineToken("deep");
		System.out.println("getOnlineToken :::::::-- > "+ responce);
		
	}
	private static void pushService() 
	{
		// TODO Auto-generated method stub
		String responce = articleClient.pushService("Tarun Notification");
		System.out.println("pushService :::::::-- > "+ responce);
		
	}

	public static void createRestUser()
	{
		RestUser user = new RestUser();
		user.setFirstName("push12");
		user.setMiddleName("noti2222");
		user.setLastName("fication11111");
		//user.setAddress("America");
		ApiModel resultStatus = articleClient.createUser(user);
		System.out.println("Result::::::::----:= " + resultStatus.getObject());
	}
	
	
	public static void getUserById()
	{
		ApiModel resultStatus=articleClient.getSUserById("79");
		System.out.println("First Name -> ::::"+resultStatus.getObject());
	}
	public static void updateUser()
	{
		RestUser user = new RestUser();
		user.setUserId(59);
		user.setFirstName("ami1");
		user.setMiddleName("kuma");
		user.setLastName("tiriphati11");			
		String resultStatus = articleClient.updateUser(user);
		System.out.println("Result:= " + resultStatus);
	}
	public static void deleteUser()
	{
		String resultStatus=articleClient.deleteUser("59");
		System.out.println("Result in jsopn ::::---"+resultStatus);
	}
}
