package org.apache.directory.intalio.embed.webapp;

import java.io.File;
import java.net.SocketAddress;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;
import javax.naming.ldap.Control;

import junit.framework.TestCase;

import org.apache.directory.server.core.CoreSession;
import org.apache.directory.server.core.DirectoryService;
import org.apache.directory.server.core.OperationManager;
import org.apache.directory.server.core.ReferralHandlingMode;
import org.apache.directory.server.core.authn.LdapPrincipal;
import org.apache.directory.server.core.changelog.ChangeLog;
import org.apache.directory.server.core.entry.ClonedServerEntry;
import org.apache.directory.server.core.entry.ServerEntry;
import org.apache.directory.server.core.event.EventService;
import org.apache.directory.server.core.filtering.EntryFilteringCursor;
import org.apache.directory.server.core.interceptor.Interceptor;
import org.apache.directory.server.core.interceptor.InterceptorChain;
import org.apache.directory.server.core.interceptor.context.OperationContext;
import org.apache.directory.server.core.partition.Partition;
import org.apache.directory.server.core.partition.PartitionNexus;
import org.apache.directory.server.core.schema.SchemaService;
import org.apache.directory.server.schema.registries.Registries;
import org.apache.directory.shared.ldap.constants.AuthenticationLevel;
import org.apache.directory.shared.ldap.entry.Modification;
import org.apache.directory.shared.ldap.filter.ExprNode;
import org.apache.directory.shared.ldap.filter.SearchScope;
import org.apache.directory.shared.ldap.ldif.LdifEntry;
import org.apache.directory.shared.ldap.message.AddRequest;
import org.apache.directory.shared.ldap.message.AliasDerefMode;
import org.apache.directory.shared.ldap.message.CompareRequest;
import org.apache.directory.shared.ldap.message.DeleteRequest;
import org.apache.directory.shared.ldap.message.ModifyDnRequest;
import org.apache.directory.shared.ldap.message.ModifyRequest;
import org.apache.directory.shared.ldap.message.SearchRequest;
import org.apache.directory.shared.ldap.message.UnbindRequest;
import org.apache.directory.shared.ldap.name.LdapDN;
import org.apache.directory.shared.ldap.name.Rdn;
import org.apache.directory.shared.ldap.schema.AttributeTypeOptions;

public class PartiionHandlerTest extends TestCase {

	public void testJSonImport() throws Exception {
		new PartitionHandler(directoryService).init("/partitions-test.json");
	}

	DirectoryService directoryService = new DirectoryService() {

		public void addPartition(Partition arg0) throws Exception {

		}

		public CoreSession getAdminSession() throws Exception {
			return new CoreSession(){

				public void add(AddRequest addrequest) throws Exception {
					
				}

				public void add(ServerEntry serverentry) throws Exception {
					
				}

				public boolean compare(CompareRequest comparerequest)
						throws Exception {
					return false;
				}

				public void compare(LdapDN ldapdn, String s, Object obj)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void delete(DeleteRequest deleterequest)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void delete(LdapDN ldapdn) throws Exception {
					// TODO Auto-generated method stub
					
				}

				public boolean exists(LdapDN ldapdn) throws Exception {
					// TODO Auto-generated method stub
					return false;
				}

				public LdapPrincipal getAuthenticatedPrincipal() {
					// TODO Auto-generated method stub
					return null;
				}

				public AuthenticationLevel getAuthenticationLevel() {
					// TODO Auto-generated method stub
					return null;
				}

				public SocketAddress getClientAddress() {
					// TODO Auto-generated method stub
					return null;
				}

				public Set<Control> getControls() {
					// TODO Auto-generated method stub
					return null;
				}

				public DirectoryService getDirectoryService() {
					// TODO Auto-generated method stub
					return null;
				}

				public LdapPrincipal getEffectivePrincipal() {
					// TODO Auto-generated method stub
					return null;
				}

				public Set<OperationContext> getOutstandingOperations() {
					// TODO Auto-generated method stub
					return null;
				}

				public SocketAddress getServiceAddress() {
					// TODO Auto-generated method stub
					return null;
				}

				public boolean isAdministrator() {
					// TODO Auto-generated method stub
					return false;
				}

				public boolean isAnAdministrator() {
					// TODO Auto-generated method stub
					return false;
				}

				public boolean isAnonymous() {
					// TODO Auto-generated method stub
					return false;
				}

				public boolean isConfidential() {
					// TODO Auto-generated method stub
					return false;
				}

				public boolean isVirtual() {
					// TODO Auto-generated method stub
					return false;
				}

				public EntryFilteringCursor list(LdapDN arg0,
						AliasDerefMode arg1, Set<AttributeTypeOptions> arg2,
						int arg3, int arg4) throws Exception {
					// TODO Auto-generated method stub
					return null;
				}

				public EntryFilteringCursor list(LdapDN arg0,
						AliasDerefMode arg1, Set<AttributeTypeOptions> arg2)
						throws Exception {
					// TODO Auto-generated method stub
					return null;
				}

				public ClonedServerEntry lookup(LdapDN ldapdn,
						Control[] acontrol,
						ReferralHandlingMode referralhandlingmode,
						LdapDN ldapdn1) throws Exception {
					// TODO Auto-generated method stub
					return null;
				}

				public ClonedServerEntry lookup(LdapDN ldapdn, String[] as)
						throws Exception {
					// TODO Auto-generated method stub
					return null;
				}

				public ClonedServerEntry lookup(LdapDN ldapdn) throws Exception {
					// TODO Auto-generated method stub
					return null;
				}

				public void modify(LdapDN arg0, List<Modification> arg1)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void modify(ModifyRequest modifyrequest)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void move(LdapDN ldapdn, LdapDN ldapdn1)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void move(ModifyDnRequest modifydnrequest)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void moveAndRename(LdapDN ldapdn, LdapDN ldapdn1,
						Rdn rdn, boolean flag) throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void moveAndRename(ModifyDnRequest modifydnrequest)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void rename(LdapDN ldapdn, Rdn rdn, boolean flag)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void rename(ModifyDnRequest modifydnrequest)
						throws Exception {
					// TODO Auto-generated method stub
					
				}

				public EntryFilteringCursor search(LdapDN arg0,
						SearchScope arg1, ExprNode arg2, AliasDerefMode arg3,
						Set<AttributeTypeOptions> arg4, int arg5, int arg6)
						throws Exception {
					// TODO Auto-generated method stub
					return null;
				}

				public EntryFilteringCursor search(LdapDN arg0,
						SearchScope arg1, ExprNode arg2, AliasDerefMode arg3,
						Set<AttributeTypeOptions> arg4) throws Exception {
					// TODO Auto-generated method stub
					return null;
				}

				public EntryFilteringCursor search(SearchRequest searchrequest)
						throws Exception {
					// TODO Auto-generated method stub
					return null;
				}

				public void unbind() throws Exception {
					// TODO Auto-generated method stub
					
				}

				public void unbind(UnbindRequest unbindrequest)
						throws Exception {
					// TODO Auto-generated method stub
					
				}};
			
		}

		public ChangeLog getChangeLog() {

			return null;
		}

		public EventService getEventService() {

			return null;
		}

		public String getInstanceId() {

			return null;
		}

		public InterceptorChain getInterceptorChain() {

			return null;
		}

		public List<Interceptor> getInterceptors() {

			return null;
		}

		public int getMaxSizeLimit() {

			return 0;
		}

		public int getMaxTimeLimit() {

			return 0;
		}

		public OperationManager getOperationManager() {

			return null;
		}

		public PartitionNexus getPartitionNexus() {

			return null;
		}

		public Set<? extends Partition> getPartitions() {

			return null;
		}

		public Registries getRegistries() {

			return null;
		}

		public SchemaService getSchemaService() {

			return null;
		}

		public CoreSession getSession() throws Exception {

			return null;
		}

		public CoreSession getSession(LdapPrincipal arg0) throws Exception {

			return null;
		}

		public CoreSession getSession(LdapDN arg0, byte[] arg1)
				throws Exception {

			return null;
		}

		public CoreSession getSession(LdapDN arg0, byte[] arg1, String arg2,
				String arg3) throws Exception {

			return null;
		}

		public Partition getSystemPartition() {

			return null;
		}

		public List<LdifEntry> getTestEntries() {

			return null;
		}

		public File getWorkingDirectory() {

			return null;
		}

		public boolean isAccessControlEnabled() {

			return false;
		}

		public boolean isAllowAnonymousAccess() {

			return false;
		}

		public boolean isDenormalizeOpAttrsEnabled() {

			return false;
		}

		public boolean isExitVmOnShutdown() {

			return false;
		}

		public boolean isShutdownHookEnabled() {

			return false;
		}

		public boolean isStarted() {

			return false;
		}

		public ServerEntry newEntry(String arg0, String arg1) {

			return null;
		}

		public void removePartition(Partition arg0) throws Exception {

		}

		public long revert() throws Exception {

			return 0;
		}

		public long revert(long arg0) throws Exception {

			return 0;
		}

		public void setAccessControlEnabled(boolean arg0) {

		}

		public void setAllowAnonymousAccess(boolean arg0) {

		}

		public void setChangeLog(ChangeLog arg0) {

		}

		public void setDenormalizeOpAttrsEnabled(boolean arg0) {

		}

		public void setEventService(EventService arg0) {

		}

		public void setExitVmOnShutdown(boolean arg0) {

		}

		public void setInstanceId(String arg0) {

		}

		public void setInterceptors(List<Interceptor> arg0) {

		}

		public void setMaxSizeLimit(int arg0) {

		}

		public void setMaxTimeLimit(int arg0) {

		}

		public void setPartitions(Set<? extends Partition> arg0) {

		}

		public void setRegistries(Registries arg0) {

		}

		public void setSchemaService(SchemaService arg0) {

		}

		public void setShutdownHookEnabled(boolean arg0) {

		}

		public void setSystemPartition(Partition arg0) {

		}

		public void setTestEntries(List<? extends LdifEntry> arg0) {

		}

		public void setWorkingDirectory(File arg0) {

		}

		public void shutdown() throws Exception {

		}

		public void startup() throws Exception {

		}

		public void sync() throws Exception {

		}

		public ServerEntry newEntry(LdapDN arg0) throws NamingException {

			return null;
		}

	};
}
