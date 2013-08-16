package com.evon.restservice.main;

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
		createRestUser();		
		//getUserById();
		//updateUser();
		//deleteUser();
		
	}
	
	private static void groponDeals() {
		// TODO Auto-generated method stub
		String responce = articleClient.groponDeals("93e9598d1ac2d5eca3017dee020980eb2ad8e87b");
		System.out.println("gropons deals :::::::-- > "+ responce);
		
	}

	public static void createRestUser()
	{
		RestUser user = new RestUser();
		user.setFirstName("fname1");
		user.setMiddleName("mname2");
		user.setLastName("lname3");
		String resultStatus = articleClient.createUser(user);
		System.out.println("Result::::::::----:= " + resultStatus);
	}
	
	
	public static void getUserById()
	{
		ApiModel resultStatus=articleClient.getSUserById("20");
		System.out.println("First Name -> ::::"+resultStatus.getObject());
	}
	public static void updateUser()
	{
		RestUser user = new RestUser();
		user.setUserId(18);
		user.setFirstName("amam");
		user.setMiddleName("kumar111");
		user.setLastName("sharma111");			
		String resultStatus = articleClient.updateUser(user);
		System.out.println("Result:= " + resultStatus);
	}
	public static void deleteUser()
	{
		String resultStatus=articleClient.deleteUser("18");
		System.out.println("Result in jsopn ::::---"+resultStatus);
	}
}
