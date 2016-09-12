package org.apache.maven.plugins.restful;

import io.dropwizard.auth.Auth;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import UserDAO.*;

import org.apache.maven.plugins.restful.App;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class users {
     
	  private static final Logger LOGGER =LoggerFactory.getLogger(App.class);
	  userDAO userdao;
	  public users(DBI jdbi) 
	  {userdao = jdbi.onDemand(userDAO.class);}


	@GET
	  @Path("/{id}")
	  public Response getUser(@PathParam("id") int id, @Auth boolean isAuthenticated) {
	  
		LOGGER.info("response preparing");
		  userData user = userdao.getContactById(id);
		  if(user==null)
		  {
			  return Response.ok("{username: doesnot exist}").build();
			  
		  }
		  else
		  {  
			  return Response.ok("{username:"+user.getUsername()+"}").build();
		  }
	  }
	  
	  
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(userData user, @Auth
			  Boolean isAuthenticated) throws URISyntaxException {
	   
		 LOGGER.info("response post ");
		 userData oldUser = userdao.getContactByUsername(user.getUsername(),user.getPassword());
		 if(oldUser!=null)
		 {
			  return Response.ok("{error : username already exists}").build();
			 
		 }
		 else
		 {
			 int newContactId = userdao.createUser(user.getUsername(),user.getPassword());
			 return Response.created(new URI(String.valueOf(newContactId))).build();
		
		 }
	  }
	
	 @PUT
	  @Path("/{id}")
	  public Response updateUser(@PathParam("id") int id,userData user, @Auth
			  Boolean isAuthenticated) {
	    
	   userdao.updateUser(id,user.getUsername(),user.getPassword());
	    
	    return Response.ok(userdao.getContactById(id)).build();
	  }
	 
	 @DELETE
	  @Path("/{id}")
	  public Response deleteUser(@PathParam("id") int id, @Auth
			  Boolean isAuthenticated) {
	    
	    userdao.deleteUser(id);
	    return Response.ok("{message : user deleted}").build();
	  }
	  
}
