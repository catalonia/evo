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
@Path("/")
public class RestServices {
	DbConnection dbConnection=null;
	@GET
	@Path("message/{param}")
	public Response printMessage(@PathParam("param") String msg) {
 
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
		
		System.out.println("In Create User");
		dbConnection=new DbConnection();
		dbConnection.insertUser(user);
		String result = "sucess";
		return Response.status(200).entity(result).build();
 
	}
	
	@GET
	@Path("getUserById")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserById(@QueryParam("userId") int userId) {
		
        System.out.println("getUserById ::::---"+ userId);
        dbConnection=new DbConnection();
		UserBean user =dbConnection.getUserDetail(userId);
		System.out.println("getUserById ::::---"+ user);
		return Response.status(200).entity(user).build();
 
	}
	
	@GET
	@Path("deleteUser")
	public Response deleteUser(@QueryParam("userId") int userId) {
		
        System.out.println("Delete user by id:::-----"+ userId);
        dbConnection=new DbConnection();
		String result = dbConnection.deleteUser(userId);
		 System.out.println("Delete user by id:::-----"+ result);
		return Response.status(200).entity(result).build();
 
	}
	@POST
	@Path("updateUser")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(UserBean user) {
         
		 System.out.println("update user :::--------"+user.getUserId());
         dbConnection=new DbConnection();
		 String result=dbConnection.updateUser(user);
		 return Response.status(200).entity(result).build();
 
	}

}
