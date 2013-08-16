package com.adaptor.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;


import com.adaptor.service.CustomHttpDispatcher;

public class VersionIdentifier {


	Properties properties = CustomHttpDispatcher.properties;
	Map<String, String>versionInfo = CustomHttpDispatcher.versionInfo;
	
	public String getApi(String versionDate)
	{
		
		String versionApi=null;
		if(versionDate == null || versionDate.isEmpty())
		{
			   			
    		    for(String key : properties.stringPropertyNames()) 
    		    {					
					   String value = properties.getProperty(key);		
				       for (String mapKey : versionInfo.keySet()) 
				       {
		        		    if(mapKey.trim().equals(value.trim()))
		        		    {
		        		    	
		        		    	versionApi = versionInfo.get(mapKey);		        		    	
		        		    	break;
		        		    }
				       }
				       break;
    		    }
			        	
    		      		
		}
		else
		{
			Date parseVersionDate;
	    	Date propertiesDate; 	    	
			for(String key : properties.stringPropertyNames()) 
			{
				
				  String value = properties.getProperty(key);
				  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 	
				  try 
				  {
					  parseVersionDate = sdf.parse(versionDate);
					  propertiesDate = sdf.parse(key);
						if(parseVersionDate.compareTo(propertiesDate)== 0)
						{
			        		
			        		for (String mapKey : versionInfo.keySet())
			        		{
			        			
			        		    if(mapKey.trim().equals(value.trim()))
			        		    {
			        		    	
			        		    	versionApi = versionInfo.get(mapKey);
			        		    	break;
			        		    }
			        		}
			        		
			        		break;
						}
						
				 } 
				 catch (ParseException e) 
				 {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 }
		        	
		        	
			}
		}
		return versionApi;
	}

}
