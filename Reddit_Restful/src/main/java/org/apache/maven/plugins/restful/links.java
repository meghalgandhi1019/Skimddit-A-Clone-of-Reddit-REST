package org.apache.maven.plugins.restful;

import io.dropwizard.auth.Auth;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ArrayBlockingQueue;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import LinksDAO.linkDAO;
import LinksDAO.linkData;
import UserDAO.*;

import org.apache.maven.plugins.restful.App;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Path("/links")
@Produces(MediaType.APPLICATION_JSON)
public class links {
     
	  private static final Logger LOGGER =LoggerFactory.getLogger(App.class);
	  linkDAO linkdao;
	  userDAO userdao;
	  public links(DBI jdbi) {linkdao = jdbi.onDemand(linkDAO.class); userdao = jdbi.onDemand(userDAO.class);}

	  @GET
	  @Path("/all")
	  public Response getAll() {
	  
		  
			 Iterator<linkData> i = linkdao.getAllLinks();
			 ArrayList<linkData> list = new ArrayList<linkData>();
				
					 
			 while(i.hasNext())
			 {
				 
				 list.add(i.next());
			 }
			 
			 return Response.ok(list).build();
		  
	  }
	@GET
	  @Path("/{id}")
	  public Response getLink(@PathParam("id") int id,@Auth boolean isAuthenticated) {
	    
		LOGGER.info("response preparing");
		  linkData link = linkdao.getLinkById(id);
		  if(link!=null)
		  {
	      return Response.ok(link).build();
		  }
		  else
		  {
			  
			  return Response.ok("{message:link not found}").build();
		  }
	  }
	  
	  
	@POST
	  public Response createLink(linkData link, @Auth
			  Boolean isAuthenticated) throws URISyntaxException {
	   
		
		 userData user = userdao.getContactByOnlyUsername(link.username);
		 if(user!=null)
		 {
			 int newContactId = linkdao.createLink(link.name,link.url,link.username,link.votes);
			 return Response.created(new URI(String.valueOf(newContactId))).build();
		 }
		 else
		 {
			 
			 return Response.ok("error : you are not registered or logged in").build();
		 }
	  }
	
	 @PUT
	  @Path("/{id}")
	  public Response updateLink(@PathParam("id") int id,linkData link, @Auth
			  Boolean isAuthenticated) throws URISyntaxException {
	    
		 userData user = userdao.getContactByOnlyUsername(link.username);
		 if(user!=null)
		 {
			 linkdao.updateLink(id,link.name,link.url,link.username,link.votes);
		       return Response.ok(new linkData(id,link.name,link.url,link.username,link.votes)).build();
		 }
		 else
		 {
			 return Response.ok("error : you are not registered or logged in").build();
		 }
	  }
	 
	 @DELETE
	  @Path("/{id}")
	  public Response deleteLink(@PathParam("id") int id, @Auth
			  Boolean isAuthenticated) {
	    
	     linkdao.deleteLink(id);
	    return Response.ok("{message:link deleted}").build();
	  }
	  
}
