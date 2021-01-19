package beans;

public class BAccessos {
	
	private String strUsuari;
	private String strAplicacio;
	private String strQuery;
	private String strObservacions;
	
	public BAccessos()
	{
		strUsuari = new String();
		strAplicacio = new String();
		strQuery = new String();
		strObservacions = new String();
	}

	public String getStrUsuari() {
		return strUsuari;
	}

	public void setStrUsuari(String strUsuari) {
		this.strUsuari = strUsuari;
	}

	public String getStrAplicacio() {
		return strAplicacio;
	}

	public void setStrAplicacio(String strAplicacio) {
		this.strAplicacio = strAplicacio;
	}

	public String getStrQuery() {
		return strQuery;
	}

	public void setStrQuery(String strQuery) {
		this.strQuery = strQuery;
	}

	public String getStrObservacions() {
		return strObservacions;
	}

	public void setStrObservacions(String strObservacions) {
		this.strObservacions = strObservacions;
	}

}
