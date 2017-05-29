package org.apache.directory.intalio.embed.webapp;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.directory.server.core.DirectoryService;
import org.apache.directory.server.core.entry.ServerEntry;
import org.apache.directory.server.core.partition.Partition;
import org.apache.directory.server.core.partition.impl.btree.jdbm.JdbmIndex;
import org.apache.directory.server.core.partition.impl.btree.jdbm.JdbmPartition;
import org.apache.directory.server.xdbm.Index;
import org.apache.directory.shared.ldap.exception.LdapNameNotFoundException;
import org.apache.directory.shared.ldap.name.LdapDN;
import org.apache.noggit.JSONParser;
import org.apache.noggit.ObjectBuilder;
import org.slf4j.LoggerFactory;

public class PartitionHandler {

	private DirectoryService directoryService;

	PartitionHandler(DirectoryService directoryService) {
		this.directoryService = directoryService;
	}

	@SuppressWarnings("unchecked")
	public void init(String name) throws Exception {
	    URL pathname = this.getClass()
	                .getResource(name);
	    File file = new File(pathname.toURI());
		FileReader r = new FileReader(file);
		JSONParser parser = new JSONParser(r);
		HashMap top = (HashMap) ObjectBuilder.getVal(parser);
		HashMap partitions = (HashMap) top.get("partitions");
		Iterator iter = partitions.keySet().iterator();
		while (iter.hasNext()) {
			String partitionName = (String) iter.next();
			HashMap partition = (HashMap) partitions.get(partitionName);
			String dn = (String) partition.get("dn");
			HashMap attributes = (HashMap) partition.get("attributes");
			checkPartition(partitionName, dn, attributes);
		}
		
	}

	@SuppressWarnings("unchecked")
	private void checkPartition(String partitionName, String dn,
			HashMap attributes) throws Exception {
		LoggerFactory.getLogger(this.getClass()).debug("Adding partition:"+partitionName+dn+attributes.toString());

		Partition apachePartition = addPartition(partitionName, dn);
		// Index some attributes on the partition
		addIndex(apachePartition, "objectClass", "ou", "uid");

		// Inject the apache root entry if it does not already exist
		try {
			directoryService.getAdminSession().lookup(
					apachePartition.getSuffixDn());
		} catch (LdapNameNotFoundException lnnfe) {
			LdapDN dnApache = new LdapDN(dn);
			ServerEntry entryApache = directoryService.newEntry(dnApache);
			Iterator<String> iter = attributes.keySet().iterator();
			while (iter.hasNext()) {
				String attributeName = (String) iter.next();
				Object value = attributes.get(attributeName);
				if (value instanceof String) {
					entryApache.add(attributeName, (String) value);
				} else {
					ArrayList<String> val = (ArrayList<String>) value;
					for (String v : val) {
						entryApache.add(attributeName, v);
					}
				}
			}
			directoryService.getAdminSession().add(entryApache);
		}
	}

	/**
	 * Add a new partition to the server
	 * 
	 * @param partitionId
	 *            The partition Id
	 * @param partitionDn
	 *            The partition DN
	 * @return The newly added partition
	 * @throws Exception
	 *             If the partition can't be added
	 */
	private Partition addPartition(String partitionId, String partitionDn)
			throws Exception {
		// Create a new partition named 'foo'.
		Partition partition = new JdbmPartition();
		partition.setId(partitionId);
		partition.setSuffix(partitionDn);
		directoryService.addPartition(partition);
		return partition;
	}

	/**
	 * Add a new set of index on the given attributes
	 * 
	 * @param partition
	 *            The partition on which we want to add index
	 * @param attrs
	 *            The list of attributes to index
	 */
	private void addIndex(Partition partition, String... attrs) {
		// Index some attributes on the apache partition
		HashSet<Index<?, ServerEntry>> indexedAttributes = new HashSet<Index<?, ServerEntry>>();

		for (String attribute : attrs) {
			indexedAttributes
					.add(new JdbmIndex<String, ServerEntry>(attribute));
		}

		((JdbmPartition) partition).setIndexedAttributes(indexedAttributes);
	}
}
