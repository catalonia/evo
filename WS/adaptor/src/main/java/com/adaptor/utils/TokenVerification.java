package com.adaptor.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;



public class TokenVerification {
	Connection connection = null;
    Statement statement = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	public Connection getConnection()
	{
		try
		{
			
			Class.forName("com.mysql.jdbc.Driver");		
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restdb","root", "");
		
		}
		catch(Exception e)
		{
			
		}
		return connection;
	}
	
	
	public boolean validiateToken(String token)
	{
		
		connection=getConnection();
		String getQuery="SELECT expiration_time FROM tokens WHERE access_token=?" ;
		try 
		{
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setString(1, token);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				long currentTimeMillis = System.currentTimeMillis();
				java.util.Date date= new java.util.Date(currentTimeMillis);
				Timestamp time1=new Timestamp(date.getTime());
				Timestamp time2=resultSet.getTimestamp("expiration_time");
				if(time1.before(time2))
				{
					return true;
				}
				
							
			}
		} 
		catch (SQLException e)
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return false;
		
	}


	public String getDeviceToken(String token) 
	{
		// TODO Auto-generated method stub
		connection = getConnection();
		String deviceToken = null;
		String getQuery="SELECT device_token FROM tokens WHERE access_token=?" ;
		try 
		{
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.setString(1, token);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				
				deviceToken = resultSet.getString("device_token");
											
			}
		} 
		catch (SQLException e)
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return deviceToken;
	}

}
