package login;

public class BLogin {
	
	private String observacions;
	private String usuari;

	private String aplicacio;
	
	private long lDataHora;
	
	private String esAccessValid;

//	@Temporal(TemporalType.TIMESTAMP)
//	private java.util.Date datahora;
	
	public BLogin()
	{
		observacions = new String();
		usuari = new String();
		aplicacio = new String();
		lDataHora = 0;
		esAccessValid = new String();
	//	datahora = null;
	}

	public String getEsAccessValid() {
		return esAccessValid;
	}

	public void setEsAccessValid(String esAccessValid) {
		this.esAccessValid = esAccessValid;
	}

	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public String getAplicacio() {
		return aplicacio;
	}

	public void setAplicacio(String aplicacio) {
		this.aplicacio = aplicacio;
	}
	
	public java.util.Date getDatahora() {
		return new java.util.Date(lDataHora);
	}

	public void setDatahora(java.util.Date datahora) {
		//this.datahora = datahora;
		this.lDataHora = datahora.getTime();
	}
	
	public long getlDataHora() {
		return lDataHora;
	}

	public void setlDataHora(long lDataHora) {
		this.lDataHora = lDataHora;
	}
	

	/*
	public java.util.Date getDatahora() {
		return datahora;
	}

	public void setDatahora(java.util.Date datahora) {
		this.datahora = datahora;
	}
	*/
	
	

}
