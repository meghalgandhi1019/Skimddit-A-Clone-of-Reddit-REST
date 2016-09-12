package org.apache.maven.plugins.restful;

import io.dropwizard.auth.Auth;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import LinksDAO.linkData;
import UserDAO.*;
import VoteDAO.voteDAO;
import VoteDAO.voteUser;

import org.apache.maven.plugins.restful.App;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Path("/votes")
@Produces(MediaType.APPLICATION_JSON)

public class votes {
	
	  voteDAO votedao;
	  userDAO userdao;
	  public votes(DBI jdbi) {votedao = jdbi.onDemand(voteDAO.class); userdao = jdbi.onDemand(userDAO.class);}
	  
	 
	  
	  @GET
	  @Path("/{id}")
	  public Response getVotes(@PathParam("id") int id, @Auth
			  Boolean isAuthenticated) {
	      linkData linkdata =  votedao.getVotesById(id);
		  if(linkdata!=null)
		  {
			  return Response.ok(linkdata).build();
		  }
		  else
		  {
			  return Response.ok("{error: link doesnot exist}").build();
		  }
	  }
	  
	  
	  @POST
	  @Path("up/{id}")
	  public Response incVote(@PathParam("id") int id,userData user, @Auth
			  Boolean isAuthenticated) {
	    
		  linkData linkdata =  votedao.getVotesById(id);
		  if(linkdata==null)
		  {
			  return Response.ok("{error : link not found}").build();
			  
		  }
		  userData olduser = userdao.getContactByUsername(user.getUsername(),user.getPassword());
		
		  if(olduser!=null)
		  {
			  voteUser vUser = votedao.getUserById(olduser.getUsername(),linkdata.name);
			  if(vUser!=null)
			  {
				  return Response.ok("{error : you already voted this link}").build();
				  
			  }
			  else
			  {
				  
				  votedao.incVote(id);
				  votedao.createvote(linkdata.name, olduser.getUsername());
				  voteUser checkUser = votedao.getUserForDownById(olduser.getUsername(),linkdata.name);
				  if(checkUser!=null)
				  {
					  votedao.deleteUserFromDown(checkUser.username,linkdata.name);
					  
				  }
				  return Response.ok("{vote : incremented}").build();
			  }
		  }
		 
		  else
		  {
			  return Response.ok("{error: you are not registered or logged in }").build();
		  }
			 
			  
		  
	  }
	  
	  
	@POST
	@Path("down/{id}")
	  public Response decVote(@PathParam("id") int id,userData user, @Auth
			  Boolean isAuthenticated) throws URISyntaxException {
	   
		linkData linkdata =  votedao.getVotesById(id);
		  userData olduser = userdao.getContactByUsername(user.getUsername(),user.getPassword());
		
		  if(olduser!=null)
		  {
			  voteUser vUser = votedao.getUserForDownById(olduser.getUsername(),linkdata.name);
			  if(vUser!=null)
			  {
				  return Response.ok("{error : you already voted this link down}").build();
				  
			  }
			  else
			  {
				  
				  votedao.decVote(id);
				  votedao.createvotedown(linkdata.name, olduser.getUsername());
				  voteUser checkUser = votedao.getUserById(olduser.getUsername(),linkdata.name);
				  if(checkUser!=null)
				  {
					  votedao.deleteUserFromVotes(checkUser.username,linkdata.name);
					  
				  }
				  return Response.ok("{vote : deccremented}").build();
			  }
		  }
		 
		  else
		  {
			  return Response.ok("{error: you are not registered or logged in }").build();
		  }
	
	}

}
