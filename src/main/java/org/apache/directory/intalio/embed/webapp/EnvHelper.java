package org.apache.directory.intalio.embed.webapp;

import java.util.Hashtable;

import javax.naming.Context;
import javax.servlet.ServletContext;

import org.apache.directory.server.core.DirectoryService;
import org.apache.directory.server.core.jndi.CoreContextFactory;

/**
 * A helper class to provide the JNDI environent for the ApacheDS core.
 */
public class EnvHelper {

	protected static Hashtable<Object, Object> createEnv(
			ServletContext servletContext) {
		DirectoryService directoryService = (DirectoryService) servletContext
				.getAttribute(DirectoryService.JNDI_KEY);

		Hashtable<Object, Object> env = new Hashtable<Object, Object>();
		env.put(DirectoryService.JNDI_KEY, directoryService);
		env.put(Context.PROVIDER_URL, "");
		env.put(Context.INITIAL_CONTEXT_FACTORY, CoreContextFactory.class.getName());

		env.put(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
		env.put(Context.SECURITY_CREDENTIALS, "secret");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");

		return env;
	}
}
