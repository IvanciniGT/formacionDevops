package ldap;


import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;


public class LDAPServices {

    //required private variables   
    private Properties properties;
    private DirContext dirContext;
    private SearchControls searchCtls;
    private String[] returnAttributes = { "memberOf"};
    private String domainBase;
    private String baseFilter = "(&((&(objectCategory=Person)(objectClass=User)))";
	private String provider_url;
	private String domain;
	
    public LDAPServices(String provider_url, String domain) {
		this.provider_url= provider_url;
		this.domain=domain;

    }
    
    
    
    /**
     * Method for validate an user in the LDAP
     * 
     * @param username 
     * @param password
     * @throws LDAPException
     */
    public void authentication(String username, String password) throws LDAPException {
        properties = new Properties();        

        properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        properties.put(Context.PROVIDER_URL, provider_url);
        properties.put(Context.SECURITY_PRINCIPAL, username + "@"+domain);
        properties.put(Context.SECURITY_CREDENTIALS, password);

        //initializing active directory LDAP connection
        try {
            
        	dirContext = new InitialDirContext(properties); 
            
            //default domain base for search
            domainBase = getDomainBase(domain);

            //initializing search controls
            searchCtls = new SearchControls();
            searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            searchCtls.setReturningAttributes(returnAttributes);
            
        } catch (NamingException e) {
            throw new LDAPException(0, "Invalid Credentials");
        }
    }


    
    /**
     * Method for search user in a group
     * 
     * @param username User to search
     * @param group Group to search
     * @return True if the user is in group or false if not
     * @throws LDAPException 
     * @throws NamingException
     */
    public boolean isInGroup(String username, String group, String domainBase) throws LDAPException  {
    	boolean isInGroup=false;
    	
    	try {
    		NamingEnumeration<SearchResult> userResults= searchUser(username, null);
    		System.out.println("isInGroup 1");
        	if(userResults.hasMore()) {
                SearchResult rs= (SearchResult)userResults.next();
                Attributes attrs = rs.getAttributes();

                if (attrs.get("memberOf")!=null) {
                	 NamingEnumeration<?> e = attrs.get("memberOf").getAll();
                     
                 	while (e.hasMore()) {
                 		String groupName = (String) e.next();
                 		if (groupName.contains("CN="+group+","+domainBase))
                 			isInGroup=true;
                 	}
                }
            }
        	
    	} catch (NamingException e) {
            throw new LDAPException(1, "User or group not found");
        }
    	System.out.println("isInGroup 2 "+isInGroup);

    	return isInGroup;
    }


    /**
     * closes the LDAP connection with Domain controller
     * @throws LDAPException 
     */
    public void closeLdapConnection() throws LDAPException{
        try {
            if(dirContext != null)
                dirContext.close();
        }
        catch (NamingException e) {
        	throw new LDAPException(2,"Error closing the connection");
        }
    }
    
    
    private NamingEnumeration<SearchResult> searchUser(String searchValue, String searchBase) throws NamingException {

        String filter = this.baseFilter;        
        filter += "(samaccountname=" + searchValue + "))";
        String base = (null == searchBase) ? domainBase : getDomainBase(searchBase); // for eg.: "DC=PAERIA,DC=LOC";

        return this.dirContext.search(base, filter, this.searchCtls);
    }
    
    

    /**
     * creating a domain base value from domain controller name
     * 
     * @param base - name of the domain controller
     * @return base name for eg. DC=paeria,DC=loc
     */
    private static String getDomainBase(String base) {
        char[] namePair = base.toUpperCase().toCharArray();
        String dn = "DC=";
        for (int i = 0; i < namePair.length; i++) {
            if (namePair[i] == '.') {
                dn += ",DC=" + namePair[++i];
            } else {
                dn += namePair[i];
            }
        }
        return dn;
    }
 
   
}
