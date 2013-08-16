package com.evon.service.restex;
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
	
	public void insertUser(UserBean user)
	{
		System.out.println("insert beansssssss:::::::");
	    connection=getConnection();
	    String insertSQL = "INSERT INTO users (FIRST_NAME,MIDDLE_NAME,LAST_NAME) VALUES (?,?,?)";
	    try 
	    {
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getFirstName());
		    preparedStatement.setString(2, user.getMiddleName());
		    preparedStatement.setString(3, user.getLastName());
		    preparedStatement.executeUpdate();
		 } 
	    catch (SQLException e)
	    {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	}
	public UserBean getUserDetail(int id)
	{
		System.out.println("get user details--------");
		connection=getConnection();
		UserBean user=new UserBean();
		String getQuery="SELECT * FROM users WHERE ID= " + id;
		try 
		{
			preparedStatement = connection.prepareStatement(getQuery);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) 
			{
				user.setFirstName(resultSet.getString("FIRST_NAME"));
				user.setMiddleName(resultSet.getString("MIDDLE_NAME"));
				user.setLastName(resultSet.getString("LAST_NAME"));
				
			}
		}
		catch (SQLException e)
		{
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		return user;
	}
	
	public String deleteUser(int id)	
	{
		System.out.println("delete db connection   ---> ");
		connection=getConnection();
		String getQuery="DELETE FROM users WHERE ID= " + id;
		try 
		{
			preparedStatement = connection.prepareStatement(getQuery);
			preparedStatement.executeUpdate();
			System.out.println("Record is deleted!");
										
		} 
		catch (SQLException e)
		{
				// TODO Auto-generated catch block
			    System.out.println(e.getMessage());
				e.printStackTrace();
		}
		return"success";
	}

	public String updateUser(UserBean user) 
	{
		// TODO Auto-generated method stub
		  System.out.println("In update user ---- >"+ user.getUserId());
		  connection=getConnection();
		  String insertSQL = "UPDATE users SET FIRST_NAME= ?,MIDDLE_NAME = ?,LAST_NAME=? WHERE ID=" + user.getUserId();
		  try 
		  {
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getFirstName());
		    preparedStatement.setString(2, user.getMiddleName());
		    preparedStatement.setString(3, user.getLastName());
		    preparedStatement.executeUpdate();
		 } 
		 catch (SQLException e)
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
		return "success";
	}
	
	public boolean validiateToken(String token)
	{
		System.out.println("Valideate token :::::---> "+token);
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
				System.out.println("DB time----->>>>"+time2);
				System.out.println("Now time is ------->"+time1);
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
	

}
