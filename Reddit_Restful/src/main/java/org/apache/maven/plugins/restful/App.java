package org.apache.maven.plugins.restful;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.maven.plugins.restful.DbConfig;
import org.apache.maven.plugins.restful.users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import io.dropwizard.logging.LoggingFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import org.skife.jdbi.v2.DBI;


public class App extends Application<DbConfig> {
	 
	 
	    @Override
	    public void initialize(Bootstrap<DbConfig> b) 
	    {
	    	
	     
	    	
	    }
	    
	    @Override
		public void run(DbConfig c, Environment e) throws Exception {
			
			  final DBIFactory factory = new DBIFactory();
				final DBI jdbi = factory.build(e,c.getDataSourceFactory(),"hsqldb");
				
				
		      e.jersey().register(new users(jdbi));
			  e.jersey().register(new links(jdbi));
			  e.jersey().register(new votes(jdbi));
			  e.jersey().register(new BasicAuthProvider<Boolean>(
					  new RestAuth(), "Web Service Realm"));
		}
		
	  
    public static void main(String[] args) throws Exception
    {
    	
    	new App().run(args);
    }

	
	
}
