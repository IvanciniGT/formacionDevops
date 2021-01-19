package login;

public class SessioUsuari {
	
	private Integer codi;
	private String nom, cognoms, mail, departament, edifici;
	private String observacions, usuari, aplicacio;
	private long lDataHora;
	
	public SessioUsuari()
	{
		codi=0;
		nom="";
		cognoms="";
		mail="";
		departament="";
		edifici="";
		observacions = new String();
		usuari = new String();
		aplicacio = new String();
		lDataHora = 0;
	}
	
	public Integer getCodi() {
		return codi;
	}
	public void setCodi(Integer codi) {
		this.codi = codi;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCognoms() {
		return cognoms;
	}
	public void setCognoms(String cognoms) {
		this.cognoms = cognoms;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getDepartament() {
		return departament;
	}
	public void setDepartament(String departament) {
		this.departament = departament;
	}
	public String getEdifici() {
		return edifici;
	}
	public void setEdifici(String edifici) {
		this.edifici = edifici;
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
	public long getlDataHora() {
		return lDataHora;
	}
	public void setlDataHora(long lDataHora) {
		this.lDataHora = lDataHora;
	}

	
	
}
