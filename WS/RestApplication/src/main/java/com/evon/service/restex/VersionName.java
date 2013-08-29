package com.evon.service.restex;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VersionName {
	public static Properties properties = new Properties();
	public  String getVersionName()
	{
		 String value=null;
		InputStream inputStream1 = this.getClass().getClassLoader()
				.getResourceAsStream("versionname.properties");
		try 
		{
			properties.load(inputStream1);			
			 for(String key : properties.stringPropertyNames()) 
 		     {					
				 value = properties.getProperty(key);	
		     }
		} 
		catch (IOException e) 
		{		
			e.printStackTrace();
		}
		return value;
	}
}
