package com.adaptor.endpoint.client;


import java.io.IOException;
import java.net.MalformedURLException;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.apache.http.client.ClientProtocolException;

import com.adaptor.model.User;


public class RestClient {
	
	public User getClientDetail(String apiURL, String userId)
	{
		ClientResponse<User>clientResponse=null;
		 try 
		 {			 
				ClientRequest clientRequest = new ClientRequest(apiURL);
				clientRequest.queryParameter("userId", userId);
				clientResponse = clientRequest.get(User.class);
				if (clientResponse.getStatus() != 200) 
				{
					throw new RuntimeException("Failed : HTTP error code : "
						+ clientResponse.getStatus());
				}
		 	 
		} 
		catch (ClientProtocolException e) 
		{
		 	e.printStackTrace();
		 
		}
		catch (IOException e)
		{
		 	e.printStackTrace();
		 
		} 
		catch (Exception e)
		{
		 	e.printStackTrace();
		}			
		 return clientResponse.getEntity();
	 }
	public User postClientDetail(String apiURL, User user)
	 {
		ClientResponse<User>clientResponse = null;
		 try 
		 {
			    System.out.println("in rest client");
				ClientRequest clientRequest = new ClientRequest(apiURL);
				clientRequest.accept(MediaType.APPLICATION_JSON);
				clientRequest.body(MediaType.APPLICATION_JSON, user);		 
				clientResponse = clientRequest.post(User.class);				
				if (clientResponse.getStatus() != 200) 
				{
					throw new RuntimeException("Failed : HTTP error code : "
						+ clientResponse.getStatus());
				}
		 
				return clientResponse.getEntity();				
		 
		  } 
		 catch (MalformedURLException e)
		 {		 
				e.printStackTrace();
		 
		 } 
		 catch (IOException e) 
		 {		 
				e.printStackTrace();
		 
		 } 
		 catch (Exception e)
		 {		 
				e.printStackTrace();
		 
		 }
			  
		 return clientResponse.getEntity();
	 }
	/*
	 * post for update
	 */
	public String updateClientDetail(String apiURL, User user)
	 {
		ClientResponse<String>clientResponse = null;
		 try 
		 {
			    System.out.println("in rest client");
				ClientRequest clientRequest = new ClientRequest(apiURL);
				clientRequest.accept(MediaType.APPLICATION_JSON);
				clientRequest.body(MediaType.APPLICATION_JSON, user);		 
				clientResponse = clientRequest.post(String.class);				
				if (clientResponse.getStatus() != 200) 
				{
					throw new RuntimeException("Failed : HTTP error code : "
						+ clientResponse.getStatus());
				}
		 
				return clientResponse.getEntity();				
		 
		  } 
		 catch (MalformedURLException e)
		 {		 
				e.printStackTrace();
		 
		 } 
		 catch (IOException e) 
		 {		 
				e.printStackTrace();
		 
		 } 
		 catch (Exception e)
		 {		 
				e.printStackTrace();
		 
		 }
			  
		 return clientResponse.getEntity();
	 }
	public String deleteClient(String apiURL,String userId) {
		// TODO Auto-generated method stub
		ClientResponse<String> clientResponse=null;
		 try 
		 {			 
				ClientRequest clientRequest = new ClientRequest(apiURL);
				clientRequest.queryParameter("userId", userId);
				clientResponse = clientRequest.get(String.class);
				if (clientResponse.getStatus() != 200) 
				{
					throw new RuntimeException("Failed : HTTP error code : "
						+ clientResponse.getStatus());
				}
		 	 
		 } 
		 catch(ClientProtocolException e) 
		 {
		 
				e.printStackTrace();
		 
		 } 
		 catch(IOException e)
		 {
		 
				e.printStackTrace();
		 
		 } 
		 catch(Exception e) 
		 {
		 
				e.printStackTrace();		 
			  
		 }		
	    
		 return clientResponse.getEntity();
		
	}
	/*
	 *  getAlluser for ios
	 */
	public User getAllClientDetail(String apiURL)
	{
		ClientResponse<User>clientResponse=null;
		 try 
		 {			 
				ClientRequest clientRequest = new ClientRequest(apiURL);
				clientResponse = clientRequest.get(User.class);
				if (clientResponse.getStatus() != 200) 
				{
					throw new RuntimeException("Failed : HTTP error code : "
						+ clientResponse.getStatus());
				}
		 	 
		} 
		catch (ClientProtocolException e) 
		{
		 	e.printStackTrace();
		 
		}
		catch (IOException e)
		{
		 	e.printStackTrace();
		 
		} 
		catch (Exception e)
		{
		 	e.printStackTrace();
		}			
		 return clientResponse.getEntity();
	 }

}

 