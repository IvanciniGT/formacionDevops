package ldap;


public class LDAPException extends Exception  { 

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3665260647346380234L;
	
	private Integer errorCode;
	private String errorMessage;
	
	public LDAPException(Integer errorCode, String errorMessage) { 
		super (); 
		this.errorCode=errorCode;
		this.errorMessage=errorMessage;
	}

	
	public Integer getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	} 
	
	
} 

