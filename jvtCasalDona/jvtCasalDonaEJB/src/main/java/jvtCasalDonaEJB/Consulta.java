package jvtCasalDonaEJB;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import java.util.Date;

 
/**
 * The persistent class for the consulta database table.
 * 
 */
@Entity
@Table(schema="siad", name ="consulta")
@Cacheable(false) // dins del persistence.xml cal que hi hagi: <shared-cache-mode>DISABLE_SELECTIVE</shared-cache-mode> 
@NamedQueries(
		{
			@NamedQuery(name="Consulta.findAll", query="SELECT c FROM Consulta c"),
			@NamedQuery(name="Consulta.DatesConsultaNomSolicitant", query="SELECT c FROM Solicitant s inner join s.consultas c where c.datacon between :id_DataInicial and :id_DataFinal and s.NomCognoms like :id_nomSolicitant order by c.datacon "),
			@NamedQuery(name="Consulta.Solicitant", query="SELECT c FROM Solicitant s inner join s.consultas c where s.CodiExpedient = :id_codiExpedient order by c.codiConsulta desc ")
			
		})
public class Consulta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	/*
	 * 
	 * 
	 *   Per cada consulta guardo en un string el sql per tal de guardar-ho en el log d'accessos (ENS).
	 * 
	 */
	
	public static String consultaPK = new String ("SELECT c FROM Consulta c where c.codiconsulta = :id_codiConsulta");
	public static String consultaDatesConsultaNomSolicitant = new String("SELECT c FROM Solicitant s inner join s.consultas c where c.datacon between :id_DataInicial and :id_DataFinal and s.nomcognoms like :id_nomSolicitant order by c.datacon ");
	public static String consultaSolicitant = new String("SELECT c FROM Solicitant s inner join s.consultas c where s.codiexpedient = :id_codiExpedient order by c.codiconsulta desc");
	
	

	@Id 
	@SequenceGenerator(name="CONSULTA_CODICONSULTA_GENERATOR", sequenceName="siad.consultaCodiCosnulta", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONSULTA_CODICONSULTA_GENERATOR")
	private Integer codiConsulta;

	

	private String area;

	private String areaConsulta;

	@Temporal(TemporalType.DATE)
	private Date datacon;
	
	private String observacions ;

	

/*	
	private String Altres;  //Fora
	
	private String Generalitat;  //Fora

	private String Juridic;  //Fora

	private String Psicologic;  //Fora

	private String Sense; //Fora
*/
	
	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	private String ServeiAten;
	
	private String usuari;
	
	//@Temporal(TemporalType.DATE)
	private java.sql.Timestamp dataalta;
	
	private String tramitacio;
	
	private String derivacioInterna;
	
	public String getDerivacioInterna() {
		return derivacioInterna;
	}

	public void setDerivacioInterna(String derivacioInterna) {
		this.derivacioInterna = derivacioInterna;
	}

	public String getDerivacioEstat() {
		return derivacioEstat;
	}

	public void setDerivacioEstat(String derivacioEstat) {
		this.derivacioEstat = derivacioEstat;
	}

	public String getDerivacioEntitat() {
		return derivacioEntitat;
	}

	public void setDerivacioEntitat(String derivacioEntitat) {
		this.derivacioEntitat = derivacioEntitat;
	}

	public String getDerivacioGeneralitat() {
		return derivacioGeneralitat;
	}

	public void setDerivacioGeneralitat(String derivacioGeneralitat) {
		this.derivacioGeneralitat = derivacioGeneralitat;
	}

	public String getDerivacioConsellComarcal() {
		return derivacioConsellComarcal;
	}

	public void setDerivacioConsellComarcal(String derivacioConsellComarcal) {
		this.derivacioConsellComarcal = derivacioConsellComarcal;
	}

	private String derivacioEstat;
	
	private String derivacioEntitat;
	
	private String derivacioGeneralitat;
	
	private String derivacioConsellComarcal;
	

	//bi-directional many-to-one association to Solicitant
	@ManyToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name="CodiExpedient") //Camp definit en aquesta taula que és la FK de la relació
	private Solicitant solicitant;

	public Consulta() {
	}

	/*
	 * 
	 * 	
	 public String getAltres() {
		return this.Altres;
	}

	public void setAltres(String altres) {
		this.Altres = altres;
	}
	
	public String getGeneralitat() {
		return this.Generalitat;
	}

	public void setGeneralitat(String generalitat) {
		this.Generalitat = generalitat;
	}

	public String getJuridic() {
		return this.Juridic;
	}

	public void setJuridic(String juridic) {
		this.Juridic = juridic;
	}

	public String getPsicologic() {
		return this.Psicologic;
	}

	public void setPsicologic(String psicologic) {
		this.Psicologic = psicologic;
	}

	public String getSense() {
		return this.Sense;
	}

	public void setSense(String sense) {
		this.Sense = sense;
	}

	 * 
	 */
	
	public Integer getCodiconsulta() {
		return this.codiConsulta;
	}

	public void setCodiconsulta(Integer codiconsulta) {
		this.codiConsulta = codiconsulta;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaconsulta() {
		return this.areaConsulta;
	}

	public void setAreaconsulta(String areaconsulta) {
		this.areaConsulta = areaconsulta;
	}

	public Date getDatacon() {
		return this.datacon;
	}

	public void setDatacon(Date datacon) {
		this.datacon = datacon;
	}

	public String getServeiaten() {
		return this.ServeiAten;
	}

	public void setServeiaten(String serveiaten) {
		this.ServeiAten = serveiaten;
	}

	public Solicitant getSolicitant() {
		return this.solicitant;
	}

	public void setSolicitant(Solicitant solicitant) {
		this.solicitant = solicitant;
	}
	
	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public java.sql.Timestamp getDataalta() {
		return dataalta;
	}

	public void setDataalta(Timestamp dataalta) {
		this.dataalta = dataalta;
	}
	
	public String getTramitacio() {
		return this.tramitacio;
	}

	public void setTramitacio(String tramitacio) {
		this.tramitacio = tramitacio;
	}

}