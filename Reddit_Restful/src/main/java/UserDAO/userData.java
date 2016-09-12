package UserDAO;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
@Consumes(MediaType.APPLICATION_JSON)
public class userData {
	
     private String username;
     public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	private String password;
 
     
     public userData(String u,String p) {
 		
 		
 		  username = u;
 		  password = p;
 		 
 		  
 	}
     
     public userData() {
     }
     
     
}
