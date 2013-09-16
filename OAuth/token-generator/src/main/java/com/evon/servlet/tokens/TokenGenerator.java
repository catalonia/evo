package com.evon.servlet.tokens;

import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.skeleton.key.representations.AccessTokenResponse;


import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import java.sql.Timestamp;
import java.util.HashMap;


/**
 * @author Sachin sharma </a>
 
 */

@Path("/")
public class TokenGenerator {
	
	DbConnection connection = new DbConnection();

	
	@SuppressWarnings("unchecked")
	@GET
	@Path("getOnlineToken/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public HashMap getOnlineToken(@HeaderParam("deviceToken") String deviceToken, @PathParam("param") String user_name) 
	{
		ResteasyClient client = null;
		String status = connection.checkTokenExist(user_name);
		HashMap responce = new HashMap();
		if(status == null || status.isEmpty())
		{
			try 
			{
				client = new ResteasyClientBuilder().disableTrustManager().build();
				System.out.println("Get Acess token here: client grant ::::::--->"+deviceToken);
				Form form = new Form().param("user_name", user_name);
				ResteasyWebTarget target = client.target("https://localhost:8443/auth-server/j_oauth_token_grant");
				//ResteasyWebTarget target = client.target("https://192.168.1.191:8443/auth-server/j_oauth_token_grant");
				target.register(new BasicAuthentication("sachin", "123456"));
				AccessTokenResponse res = target.request().post(Entity.form(form),AccessTokenResponse.class);
				res.setExpiresIn(60*60);
				String _token = res.getToken();
				long currentTimeMillis = System.currentTimeMillis();
				long alteredTime = currentTimeMillis + 60*60*1000;//change the time 1 hrs is now.
				java.util.Date date= new java.util.Date(alteredTime);
				Timestamp time=new Timestamp(date.getTime());
				connection.insertTokens(_token,user_name,time,deviceToken);
				//return Response.status(200).entity(_token).build();
				responce.put("token", _token);
				return responce;
			} 
			catch (Exception e) 
			{
				return null;
			} 
			finally 
			{
				client.close();
			}
		}
		else
		{
			System.out.println("::::::-- ALREADY HAD TOKEN ----::::::"+ status);
			//return Response.status(200).entity(status).build();
			responce.put("token", status);
			return responce;
		}
	}
	
	@GET
	@Path("getOfflineToken/{param}")
	public Response getOfflineToken(@HeaderParam("deviceToken") String deviceToken, @PathParam("param") String user_name) 
	{
		ResteasyClient client = null;
		String status = connection.checkTokenExist(user_name);
		if(status == null || status.isEmpty())
		{
			try 
			{
				client = new ResteasyClientBuilder().disableTrustManager().build();
				System.out.println("Get Acess token here: client grant ::::::--->");
				Form form = new Form().param("user_name", user_name);
				ResteasyWebTarget target = client.target("https://localhost:8443/auth-server/j_oauth_token_grant");
				target.register(new BasicAuthentication("sachin", "123456"));
				AccessTokenResponse res = target.request().post(Entity.form(form),AccessTokenResponse.class);
				res.setExpiresIn(30*24*60*60);
				String _token = res.getToken();
				long currentTimeMillis = System.currentTimeMillis();
				long alteredTime = currentTimeMillis + (30*86400000L);
				java.util.Date date= new java.util.Date(alteredTime);
				Timestamp time=new Timestamp(date.getTime());
				connection.insertTokens(_token,user_name,time, deviceToken);
				return Response.status(200).entity(_token).build();
			} 
			catch (Exception e) 
			{
				return null;
			} 
			finally 
			{
				client.close();
			}
		}
		else
		{
			System.out.println("::::::-- ALREADY HAD TOKEN ----::::::"+ status);
			return Response.status(200).entity(status).build();
		}
	}
}
