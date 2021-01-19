package jvtCasalDonaEJB;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the solicitant database table.
 * 
 */
@Entity
@Table(schema="siad", name ="solicitant")
@Cacheable(false) // dins del persistence.xml cal que hi hagi: <shared-cache-mode>DISABLE_SELECTIVE</shared-cache-mode> 
@NamedQueries(
		{
			@NamedQuery(name="Solicitant.finNom", query="SELECT s FROM Solicitant s where s.NomCognoms like :id_nomCognoms", hints = {@QueryHint(name = "id_nomCognoms", value = "%")}),
			@NamedQuery(name="Solicitant.findAll", query="SELECT s FROM Solicitant s"),
			@NamedQuery(name="Solicitant.DatesConsultaNomSolicitant", query="SELECT distinct s FROM Solicitant s inner join s.consultas c where c.datacon between :id_DataInicial and :id_DataFinal and s.NomCognoms like :id_nomSolicitant order by s.NomCognoms, c.datacon"),
			@NamedQuery(name="Solicitant.maxExpedient", query="SELECT max(s.nExpedient) FROM Solicitant s where s.anyExpedient = :id_anyExpedient"),
			@NamedQuery(name="Solicitant.numeroExpedient", query="SELECT s FROM Solicitant s where s.nExpedient = :id_nExpedient and s.anyExpedient = :id_anyExpedient"),
			//@NamedQuery(name="Solicitant.dataNaixament", query="SELECT s FROM Solicitant s where s.DataNaix between :id_dataNaixIncial and :id_dataNaixFinal")
			//@NamedQuery(name="Solicitant.cercador", query="SELECT distinct s FROM Solicitant s left join s.consultas c where s.dataalta between :id_DataInicial and :id_DataFinal and s.NomCognoms like :id_nomSolicitant and s.DataNaix between :id_dataNaixIncial and :id_dataNaixFinal order by s.NomCognoms")
			@NamedQuery(name="Solicitant.cercador", query="SELECT s FROM Solicitant s where s.dataalta between :id_DataInicial and :id_DataFinal and s.NomCognoms like :id_nomSolicitant and s.DataNaix between :id_dataNaixIncial and :id_dataNaixFinal order by s.NomCognoms"),
			@NamedQuery(name="Solicitant.cercadorSenseDataNaix", query="SELECT s FROM Solicitant s where s.dataalta between :id_DataInicial and :id_DataFinal and s.NomCognoms like :id_nomSolicitant order by s.NomCognoms")

		})

public class Solicitant implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*
	 * 
	 * 
	 *   Per cada consulta guardo en un string el sql per tal de guardar-ho en el log d'accessos (ENS).
	 * 
	 */
	
	public static String consultaPK = new String ("SELECT s FROM Solicitant s where codiexpedient = :id_codiExpedient");
	public static String consultaNom = new String ("SELECT s FROM Solicitant s where s.nomcognoms like :id_nomCognoms");
	public static String consultaDatesConsultaNomSolicitant = new String("SELECT distinct s FROM Solicitant s inner join s.consultas c where c.datacon between :id_DataInicial and :id_DataFinal and s.NomCognoms like :id_nomSolicitant order by s.NomCognoms, c.datacon");
	public static String consultaNumeroExpedient = new String("SELECT s FROM Solicitant s where s.nExpedient = :id_nExpedient and s.anyExpedient = :id_anyExpedient");
	//public static String consultaDataNaixment = new String ("SELECT s FROM Solicitant s where s.DataNaix between :id_dataNaixIncial and :id_dataNaixFinal");
	public static String consultaCercador = new String ("SELECT  s FROM Solicitant s where s.dataalta between :id_DataInicial and :id_DataFinal and s.NomCognoms like :id_nomSolicitant and s.DataNaix between :id_dataNaixIncial and :id_dataNaixFinal order by s.NomCognoms");
	public static String consultaCercadorSenseDataNaix = new String ("SELECT s FROM Solicitant s where s.dataalta between :id_DataInicial and :id_DataFinal and s.NomCognoms like :id_nomSolicitant order by s.NomCognoms");
	
	
	// el paràmetre allocationSize ha de ser igual al valor incremental de la seqüència. Funcionament: Alta -> conuslta+incrementa al sequence
	// retorna un id -> ejb utiliza aquest id i el va incrementant id+n (on n <= allocationSize) -> quan ha fet n (allocationSize) altes ho torna a preguntar.
	// D'aquesta forma es redueix el nombre de consultes/updates a la seqüència i a la taula hi poden haver id's no consecutius.
	// si no es fica el allocationSize ho fa malament i al tornar a executar l'aplicació retorna un duplicate key.
	@Id
	@SequenceGenerator(name="SOLICITANT_CODIEXPEDIENT_GENERATOR", sequenceName="SIAD.SOLICITANTCODIEXPEDIENT", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLICITANT_CODIEXPEDIENT_GENERATOR")
	private Integer CodiExpedient;

	private String Barri;

	private String CanalEntrada;

	private String Comarca;

	private String CP;

	@Temporal(TemporalType.DATE)
	private Date DataNaix;
	
	//@Temporal(TemporalType.DATE)
	//private java.sql.Timestamp DataNaix;

	private String Demarcacio;

	private String EstatCivil;

	private String FormacioHomologada;

	private String NivellFormacio;

	private String NomCognoms;

	private String PaisOrigen;

	private String Poblacio;

	private String Prestacio;

	private String Professio;

	private String RepPrestacions;

	private String Sexe;

	private String Siad;

	private String SituacioEconomica;

	private String SituacioLaboral;

	private String Telefon;

	private String UnitatConvivencia;
	
	private String usuari;
	
	//@Temporal(TemporalType.DATE)
	private java.sql.Timestamp dataalta;
	
	private int nExpedient;
	
	private int anyExpedient;
	
	private String trasllatHestia;
		
	private String observacions ;
	
	//bi-directional many-to-one association to Consulta
	@OneToMany(targetEntity=Consulta.class, mappedBy="solicitant",cascade = CascadeType.PERSIST)
	@OrderBy("datacon desc")
	private List<Consulta> consultas;
		
	//bi-directional many-to-one association to SolicitantAmbitViolencia
	@OneToMany(mappedBy="solicitant",cascade = CascadeType.PERSIST)
	private List<SolicitantAmbitViolencia> solicitantAmbitViolencias;
		
	//bi-directional many-to-one association to SolicitantViolenciaViscuda
	@OneToMany(mappedBy="solicitant",cascade = CascadeType.PERSIST)
	private List<SolicitantViolenciaViscuda> solicitantViolenciaViscudas;
	
		
	public String getObservacions() {
		return observacions;
	}

	public void setObservacions(String observacions) {
		this.observacions = observacions;
	}

	

	public Solicitant() {
	}

	public Integer getCodiexpedient() {
		return this.CodiExpedient;
	}

	public void setCodiexpedient(Integer codiexpedient) {
		this.CodiExpedient = codiexpedient;
	}

	public String getBarri() {
		return this.Barri;
	}

	public void setBarri(String barri) {
		this.Barri = barri;
	}

	public String getCanalentrada() {
		return this.CanalEntrada;
	}

	public void setCanalentrada(String canalentrada) {
		this.CanalEntrada = canalentrada;
	}

	public String getComarca() {
		return this.Comarca;
	}

	public void setComarca(String comarca) {
		this.Comarca = comarca;
	}

	public String getCp() {
		return this.CP;
	}

	public void setCp(String cp) {
		this.CP = cp;
	}

	public Date getDatanaix() {
		return this.DataNaix;
	}

	public void setDatanaix(Date datanaix) {
		this.DataNaix = datanaix;
	}

	public String getDemarcacio() {
		return this.Demarcacio;
	}

	public void setDemarcacio(String demarcacio) {
		this.Demarcacio = demarcacio;
	}

	public String getEstatcivil() {
		return this.EstatCivil;
	}

	public void setEstatcivil(String estatcivil) {
		this.EstatCivil = estatcivil;
	}

	public String getFormaciohomologada() {
		return this.FormacioHomologada;
	}

	public void setFormaciohomologada(String formaciohomologada) {
		this.FormacioHomologada = formaciohomologada;
	}

	public String getNivellformacio() {
		return this.NivellFormacio;
	}

	public void setNivellformacio(String nivellformacio) {
		this.NivellFormacio = nivellformacio;
	}

	public String getNomcognoms() {
		return this.NomCognoms;
	}

	public void setNomcognoms(String nomcognoms) {
		this.NomCognoms = nomcognoms;
	}

	public String getPaisorigen() {
		return this.PaisOrigen;
	}

	public void setPaisorigen(String paisorigen) {
		this.PaisOrigen = paisorigen;
	}

	public String getPoblacio() {
		return this.Poblacio;
	}

	public void setPoblacio(String poblacio) {
		this.Poblacio = poblacio;
	}

	public String getPrestacio() {
		return this.Prestacio;
	}

	public void setPrestacio(String prestacio) {
		this.Prestacio = prestacio;
	}

	public String getProfessio() {
		return this.Professio;
	}

	public void setProfessio(String professio) {
		this.Professio = professio;
	}

	public String getRepprestacions() {
		return this.RepPrestacions;
	}

	public void setRepprestacions(String repprestacions) {
		this.RepPrestacions = repprestacions;
	}

	public String getSexe() {
		return this.Sexe;
	}

	public void setSexe(String sexe) {
		this.Sexe = sexe;
	}

	public String getSiad() {
		return this.Siad;
	}

	public void setSiad(String siad) {
		this.Siad = siad;
	}

	public String getSituacioeconomica() {
		return this.SituacioEconomica;
	}

	public void setSituacioeconomica(String situacioeconomica) {
		this.SituacioEconomica = situacioeconomica;
	}

	public String getSituaciolaboral() {
		return this.SituacioLaboral;
	}

	public void setSituaciolaboral(String situaciolaboral) {
		this.SituacioLaboral = situaciolaboral;
	}

	public String getTelefon() {
		return this.Telefon;
	}

	public void setTelefon(String telefon) {
		this.Telefon = telefon;
	}


	public String getUnitatconvivencia() {
		return this.UnitatConvivencia;
	}

	public void setUnitatconvivencia(String unitatconvivencia) {
		this.UnitatConvivencia = unitatconvivencia;
	}
	
	public String getUsuari() {
		return usuari;
	}

	public void setUsuari(String usuari) {
		this.usuari = usuari;
	}

	public Timestamp getDataAlta() {
		return dataalta;
	}

	public void setDataAlta(Timestamp dataAlta) {
		this.dataalta = dataAlta;
	}

	public List<Consulta> getConsultas() {
		return this.consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Consulta addConsulta(Consulta consulta) {
		getConsultas().add(consulta);
		consulta.setSolicitant(this);

		return consulta;
	}

	public Consulta removeConsulta(Consulta consulta) {
		getConsultas().remove(consulta);
		consulta.setSolicitant(null);

		return consulta;
	}
	
	public int getnExpedient() {
		return nExpedient;
	}

	public void setnExpedient(int nExpedient) {
		this.nExpedient = nExpedient;
	}

	public int getAnyExpedient() {
		return anyExpedient;
	}

	public void setAnyExpedient(int anyExpedient) {
		this.anyExpedient = anyExpedient;
	}
	
	public String getTrasllatHestia() {
		return trasllatHestia;
	}

	public void setTrasllatHestia(String trasllatHestia) {
		this.trasllatHestia = trasllatHestia;
	}


	public List<SolicitantAmbitViolencia> getSolicitantAmbitViolencias() {
		return this.solicitantAmbitViolencias;
	}

	public void setSolicitantAmbitViolencias(List<SolicitantAmbitViolencia> solicitantAmbitViolencias) {
		this.solicitantAmbitViolencias = solicitantAmbitViolencias;
	}

	public SolicitantAmbitViolencia addSolicitantAmbitViolencia(SolicitantAmbitViolencia solicitantAmbitViolencia) {
		getSolicitantAmbitViolencias().add(solicitantAmbitViolencia);
		solicitantAmbitViolencia.setSolicitant(this);

		return solicitantAmbitViolencia;
	}

	public SolicitantAmbitViolencia removeSolicitantAmbitViolencia(SolicitantAmbitViolencia solicitantAmbitViolencia) {
		getSolicitantAmbitViolencias().remove(solicitantAmbitViolencia);
		solicitantAmbitViolencia.setSolicitant(null);

		return solicitantAmbitViolencia;
	}
	
	public List<SolicitantViolenciaViscuda> getSolicitantViolenciaViscudas() {
		return this.solicitantViolenciaViscudas;
	}

	public void setSolicitantViolenciaViscudas(List<SolicitantViolenciaViscuda> solicitantViolenciaViscudas) {
		this.solicitantViolenciaViscudas = solicitantViolenciaViscudas;
	}

	public SolicitantViolenciaViscuda addSolicitantViolenciaViscuda(SolicitantViolenciaViscuda solicitantViolenciaViscuda) {
		getSolicitantViolenciaViscudas().add(solicitantViolenciaViscuda);
		solicitantViolenciaViscuda.setSolicitant(this);

		return solicitantViolenciaViscuda;
	}

	public SolicitantViolenciaViscuda removeSolicitantViolenciaViscuda(SolicitantViolenciaViscuda solicitantViolenciaViscuda) {
		getSolicitantViolenciaViscudas().remove(solicitantViolenciaViscuda);
		solicitantViolenciaViscuda.setSolicitant(null);

		return solicitantViolenciaViscuda;
	}

}