package org.apache.directory.intalio.embed.webapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Hashtable;
import java.util.Iterator;

import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.directory.shared.ldap.entry.Entry;
import org.apache.directory.shared.ldap.ldif.LdifEntry;
import org.apache.directory.shared.ldap.ldif.LdifReader;
import org.apache.directory.shared.ldap.name.LdapDN;
import org.apache.directory.shared.ldap.util.AttributeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportLDIF implements ServletContextListener {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public void contextDestroyed(ServletContextEvent event) {

	}

	public void contextInitialized(ServletContextEvent event) {
		try {
			Hashtable<Object, Object> createEnv = EnvHelper.createEnv(event
					.getServletContext());

			File f = new File(event.getServletContext().getResource("/WEB-INF/classes/ldif/").getFile());
			File[] ldifs = f.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".ldif") ? true : false;
				}
			});

			logger.info("LDIFs files to process:"+ldifs.length);
			for (File toimport : ldifs) {
				BufferedReader in = new BufferedReader(new FileReader(toimport));
				Iterator<LdifEntry> iterator = new LdifReader(in).iterator();
				while (iterator.hasNext()) {
					LdifEntry entry = iterator.next();
					Entry en = entry.getEntry();
					LdapDN dn = new LdapDN(entry.getDn());
					LdapContext rootDSE = new InitialLdapContext(createEnv,null);
					rootDSE.createSubcontext(dn, AttributeUtils.toAttributes(en));
				}
				in.close();
				boolean renamed = toimport.renameTo(new File(toimport.getAbsolutePath()+ ".imported"));
				logger.info("Processed and renamed:"+renamed);
			}
		} catch (Exception e) {
			logger.debug(e.getMessage(), e);
		}

	}

}
