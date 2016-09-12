package org.apache.maven.plugins.restful;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import io.dropwizard.logging.LoggingFactory;



public class DbConfig extends Configuration{

	
	@JsonProperty
    private DataSourceFactory database = new DataSourceFactory();
 
     public DataSourceFactory getDataSourceFactory()
     {
    
         return database;
     }

}
