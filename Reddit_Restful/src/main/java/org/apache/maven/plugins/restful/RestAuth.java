package org.apache.maven.plugins.restful;


import com.google.common.base.Optional;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

public class RestAuth implements
Authenticator<BasicCredentials, Boolean>{
		
	@Override
	public Optional<Boolean> authenticate(BasicCredentials c)
			throws AuthenticationException {
		
		
		
		if (c.getUsername().equals("jeet_shah") &&
				c.getPassword().equals("mememe92")) {
			return Optional.of(true);
			}
			return Optional.absent();
	}

}
