package com.evon.service.restex;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.logging.*;
@Path("/")
public class RestServices {
	DbConnection dbConnection=null;
	Logger log = Logger.getLogger(RestServices.class); 
	VersionName versionName = new VersionName();
	@GET
	@Path("message/{param}")
	public Response printMessage(@PathParam("param") String msg) {
 
		log.debug("In getUserById of reast easy VERSION IS == > " +versionName.getVersionName() );
		String result = "Restful example : " + msg; 
		dbConnection=new DbConnection();;
		boolean status=dbConnection.validiateToken(msg);
		System.out.println("Token status ----"+status);
		return Response.status(200).entity(result).build();
 
	}
	@POST
	@Path("createUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(UserBean user) {
		
		
		log.debug("In create usere of reast easy VERSION IS == > " +versionName.getVersionName() );
		dbConnection=new DbConnection();
		dbConnection.insertUser(user);
		int userId = dbConnection.insertUser(user);
		user.setUserId(userId);	
		return Response.status(200).entity(user).build();
 
	}
	
	@GET
	@Path("getUserById")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@QueryParam("userId") int userId) {
		
       
        log.debug("In getUserById of reast easy VERSION IS == > " +versionName.getVersionName() );
        dbConnection=new DbConnection();
		UserBean user =dbConnection.getUserDetail(userId);
		user.setUserId(userId);
		System.out.println("getUserById ::::---"+ user);
		return Response.status(200).entity(user).build();
 
	}
	
	@GET
	@Path("deleteUser")
	public Response deleteUser(@QueryParam("userId") int userId) {
		
       
        log.debug("In deleteUser of reast easy VERSION IS == > " +versionName.getVersionName() );
        dbConnection=new DbConnection();
		String result = dbConnection.deleteUser(userId);
		return Response.status(200).entity(result).build();
 
	}
	@POST
	@Path("updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(UserBean user) {
         
		 log.debug("In updateUser of reast easy VERSION IS == > " +versionName.getVersionName() );
         dbConnection=new DbConnection();
		 String result=dbConnection.updateUser(user);
		 return Response.status(200).entity(result).build();
 
	}

}
