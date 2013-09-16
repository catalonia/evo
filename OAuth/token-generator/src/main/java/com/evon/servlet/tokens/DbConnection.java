package com.evon.servlet.tokens;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
public class DbConnection {
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
	
	public void insertTokens(String  token, String user_name, Timestamp time, String deviceToken)
	{
	   
	   System.out.println("insert beansssssss:::::::");
	   connection=getConnection();		  
	   String insertSQL = "INSERT INTO tokens (access_token,user_name,expiration_time,device_token) VALUES (?,?,?,?)";
	   try 
	   {
		    System.out.println("INSERTING----------   "+token);
		    System.out.println("INSERTING-- CON--------   "+connection);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, token);
			preparedStatement.setString(2, user_name);
			preparedStatement.setTimestamp(3,time);
			preparedStatement.setString(4,deviceToken);
		    preparedStatement.executeUpdate();
		} 
	   catch (SQLException e) 
	   {
			// TODO Auto-generated catch block
			e.printStackTrace();
	   }
	   
	  
	
	   
	  
	}
	
	public String checkTokenExist(String user_name)
	{
		connection=getConnection();
		String query = "SELECT * FROM tokens WHERE user_name=?";
		try
		{
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, user_name);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				long currentTimeMillis = System.currentTimeMillis();
				java.util.Date date= new java.util.Date(currentTimeMillis);
				Timestamp time1=new Timestamp(date.getTime());
				Timestamp time2=resultSet.getTimestamp("expiration_time");
				if(time1.before(time2))
				{
					return resultSet.getString("access_token");
				}
				else
				{
					return "Token Expire";
				}
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
		
	}
	
	

}
