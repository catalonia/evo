package com.adaptor.service;



import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.logging.Logger;

import com.adaptor.endpoint.client.RestClient;
import com.adaptor.model.ApiResponce;
import com.adaptor.model.User;
import com.adaptor.utils.TokenVerification;
import com.adaptor.utils.VersionIdentifier;


@Path("/UserService")
public class UserService
{
 Logger log = Logger.getLogger(UserService.class); 
 private VersionIdentifier version = new VersionIdentifier();
 private TokenVerification tokenVerification = new TokenVerification();
 private RestClient restClient = new RestClient();
	@POST
	@Path("/createUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public ApiResponce createUser(@HeaderParam("versioDate") String versionDate, @HeaderParam("token") String token, User user)
	{
		log.debug("In adaptor create user:: Version Date == > "+versionDate);
		log.debug("Token is == > "+token);
		String responce="Token is not valid ";
		ApiResponce apiResponce = new ApiResponce();		
		boolean status = tokenVerification.validiateToken(token);
		log.debug("Status of Token is == > "+status);
		if(status)
		{
			String url=version.getApi(versionDate);
			log.debug("Rest easy version URL is == > "+url);
			if(url!=null)
			{
				responce=restClient.postClientDetail(url + "createUser", user);	
			}
			apiResponce.setStatus("OK");
			apiResponce.setMessage("success");
			apiResponce.setObject(responce);
			
		}
		else
		{
			apiResponce.setStatus("FAIL");
			apiResponce.setMessage("Token is not valid");
			apiResponce.setObject(responce);
		}
		log.debug("Final Responce is == >"+apiResponce.getStatus());
		return 	apiResponce;
	}

	@POST
	@Path("/updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public ApiResponce updateUser(@HeaderParam("versioDate") String versionDate,@HeaderParam("token") String token, User user)
	{
		log.debug("In adaptor update user:: Version Date == > "+versionDate);
		log.debug("Token is == > "+token);
		String responce="Token is not valid ";
		boolean status = tokenVerification.validiateToken(token);
		log.debug("Status of logger is == > "+status);
		ApiResponce apiResponce =new ApiResponce();
		if(status)
		{
			String url=version.getApi(versionDate);	
			log.debug("Rest easy version URL is == > "+url);
			if(url!=null)
			{
				responce=restClient.postClientDetail(url + "updateUser", user);
			}
			apiResponce.setStatus("OK");
			apiResponce.setMessage("success");
			apiResponce.setObject(responce);
		}
		else
		{
			apiResponce.setStatus("FAIL");
			apiResponce.setMessage("Token is not valid");
			apiResponce.setObject(responce);
		}
		log.debug("Final Responce Status is == > "+apiResponce.getStatus());
		return apiResponce;
	}

	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public ApiResponce getUser(@HeaderParam("versioDate") String versionDate,@HeaderParam("token") String token, @QueryParam("userId") String userId) 
	{
		log.debug("In adaptor get user:: Version Date == > "+versionDate);
		log.debug("Token is == > "+token);
		ApiResponce apiResponce =new ApiResponce();
		User user = null;
		boolean status = tokenVerification.validiateToken(token);
		log.debug("Status of Token is == > "+status);
		if(status)
		{
			String url=version.getApi(versionDate);
			log.debug("Rest easy URL is given by versionning == >"+url);
			if(url!=null)
			{
				user = 	restClient.getClientDetail(url+"getUserById",userId);
			}
			if(user != null)
			{
				apiResponce.setStatus("OK");
				apiResponce.setMessage("success");
				apiResponce.setObject(user);
			}
		}
		else
		{
			apiResponce.setStatus("FAIL");
			apiResponce.setMessage("Token is not valid");
			apiResponce.setObject(user);
		}
		log.debug("Final Responce is == > "+apiResponce.getStatus());
		return apiResponce;
	}

	@GET
	@Path("/deleteUser")
	public ApiResponce deleteUser(@HeaderParam("versioDate") String versionDate,@HeaderParam("token") String token, @QueryParam("userId") String userId)
	{
		log.debug("In adaptor delete user:: Version Date == > "+versionDate);
		log.debug("Token is == > "+token);
		ApiResponce apiResponce = new ApiResponce();
		String responce="Token is not valid";
		boolean status = tokenVerification.validiateToken(token);
		log.debug("Status of Token is == > "+status);
		if(status)
		{
			String url=version.getApi(versionDate);
			log.debug("Rest easy URL is given by versionning == >"+url);
			if(url!=null)
			{
				responce = restClient.deleteClient(url+"deleteUser",userId);
			}
			apiResponce.setStatus("OK");
			apiResponce.setMessage("success");
			apiResponce.setObject(responce);
		}
		else
		{
			apiResponce.setStatus("FAIL");
			apiResponce.setMessage("Token is not valid");
			apiResponce.setObject(responce);
		}
		log.debug("Final Responce is == > "+apiResponce.getStatus());
		return apiResponce;
	}
	
}
