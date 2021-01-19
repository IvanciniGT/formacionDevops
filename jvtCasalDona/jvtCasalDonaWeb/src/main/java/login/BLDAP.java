package login;

public class BLDAP {
	
	private String url, domini, infoBloqueig, infoGrupLDAP, domainBase;
	

	

	private int nIntents;
	
	public BLDAP()
	{
		url = "";
		domini = "";
		infoBloqueig = "";
		infoGrupLDAP = "";
		domainBase = "";
	}
	
	public String getDomainBase() {
		return domainBase;
	}

	public void setDomainBase(String domainBase) {
		this.domainBase = domainBase;
	}
	
	public String getInfoGrupLDAP() {
		return infoGrupLDAP;
	}

	public void setInfoGrupLDAP(String infoGrupLDAP) {
		this.infoGrupLDAP = infoGrupLDAP;
	}
	
	public String getUrl() {
		return url;
	}

	public String getInfoBloqueig() {
		return infoBloqueig;
	}

	public void setInfoBloqueig(String infoBloqueig) {
		this.infoBloqueig = infoBloqueig;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDomini() {
		return domini;
	}

	public void setDomini(String domini) {
		this.domini = domini;
	}

	public int getnIntents() {
		return nIntents;
	}

	public void setnIntents(int nIntents) {
		this.nIntents = nIntents;
	}

	

}
