package ejb;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.CacheStoreMode;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import beans.BAccessos;
import jvtCasalDonaEJB.*;
import jvtCasalDonaEJB.constants.Area;
import jvtCasalDonaEJB.constants.AreaConsulta;
import jvtCasalDonaEJB.constants.CanalEntrada;
import jvtCasalDonaEJB.constants.Ciutats;
import jvtCasalDonaEJB.constants.Comarques;
import jvtCasalDonaEJB.constants.DerivacioConsellComarcal;
import jvtCasalDonaEJB.constants.DerivacioEstat;
import jvtCasalDonaEJB.constants.DerivacioGeneralitat;
import jvtCasalDonaEJB.constants.DerivacioInterna;
import jvtCasalDonaEJB.constants.DerivacionEntitat;
import jvtCasalDonaEJB.constants.EstatCivil;
import jvtCasalDonaEJB.constants.NivellFormacio;
import jvtCasalDonaEJB.constants.Pais;
import jvtCasalDonaEJB.constants.Professio;
import jvtCasalDonaEJB.constants.Provincies;
import jvtCasalDonaEJB.constants.ServeiAtencio;
import jvtCasalDonaEJB.constants.SituacioEconomica;
import jvtCasalDonaEJB.constants.SituacioLaboral;
import jvtCasalDonaEJB.constants.Tramitacio;
import jvtCasalDonaEJB.constants.UnitatConvivenca;

import static java.nio.charset.StandardCharsets.*;

/**
 * Session Bean implementation class Sesio
 */
@Stateless
public class Sessio implements SessioLocal{ //, SessioRemote {
	
	 @PersistenceContext(unitName = "jvtCasalDonaEJB")
	 private EntityManager em;
	 
	 // PreProduccio
	 private String strURLjvtUTils = new String("http://was-srv2.paeria.loc:9080/jvtUtilsWeb/javaTeam/login/controlAccessos");
	 // Produccio
	// private String strURLjvtUTils = new String("http://jvtUtils.paeria.loc/jvtUtilsWeb/javaTeam/login/controlAccessos");
	 

    /**
     * Default constructor. 
     */
    public Sessio() {
        // TODO Auto-generated constructor stub
    }
    
    /*
     * 
     *   Control log Accessos.
     * 
     * 
     */
    
    private void logAccessos(String strQuery, String strUser)
    {
    	
    	try {
			 
			Gson gson = new Gson();
			 
			System.out.println("Cridem a controlAccessos");
			 
			URL url = new URL(strURLjvtUTils);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");			
			conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON);	
			conn.setDoOutput(true); //per a poder enviar parametres.
				
			BAccessos ba = new BAccessos();
			ba.setStrAplicacio("jvtCasalDona");
			ba.setStrObservacions(null);
				
			byte[] ptext = strQuery.getBytes(ISO_8859_1); 
			String valor_utf8 = new String(ptext, UTF_8); 
				
			ba.setStrQuery(valor_utf8);
				
			ptext = strUser.getBytes(ISO_8859_1); 
			valor_utf8 = new String(ptext, UTF_8);
				
			ba.setStrUsuari(valor_utf8);
								
			// Transformem el pojo a json
			String input = gson.toJson(ba) ;
				
			//	System.out.print("\nJson:"+input+"\n");

			// enviem el paraetre d'entrada al servei web.
			
			OutputStream os = conn.getOutputStream();
			os.write(input.getBytes());
			os.flush();
				
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Error invocacio:" + strURLjvtUTils +" :" + conn.getResponseCode());
			}
				
			//System.out.println("ControlAccessos fet succesful");
			 
		 } catch(Exception e) { e.printStackTrace();}
    	
    }
    
    
    /*
     * 
     * 
     * 	SOL·LICITANT
     * 
     * 
     */
       
    @SuppressWarnings("deprecation")
	public Solicitant SolicitantAlta(Solicitant s, String strUser)
    {
   
    	int nExpedient, anyExpedient;
    	
    	// Sol·licitant
    	
    	// com la pk es autogenerada cal enviar-li un nul altrament dona error.
    	s.setCodiexpedient(null);
    	
    	// m'asseguro de ficar la data correcta d'alta:
    	s.setDataAlta(new java.sql.Timestamp(new java.util.Date().getTime()));
    	
    	// Calculem numero d'expedient i any.
    	anyExpedient = s.getDataAlta().getYear()+1900;
    	Query query = em.createNamedQuery("Solicitant.maxExpedient");
   		query.setParameter("id_anyExpedient", anyExpedient);
   		
   		nExpedient = (int) query.getSingleResult();
    	nExpedient ++;
    	
    	s.setAnyExpedient(anyExpedient);
    	s.setnExpedient(nExpedient);
    	
    	// Fem l'alta.
       	em.persist(s);
       // em.merge(s);
       // em.flush();
       
       	
       	this.logAccessos("Solicitant. Alta. PK:"+s.getCodiexpedient(), strUser);   
       	
        return s;
    }
    
    public Solicitant SolicitantPK(Integer pk, String strUser)
    {
    	
    	// Control log accessos:
    	String strQuery = Solicitant.consultaPK + " ;id_codiExpedient:"+pk;
    	this.logAccessos(strQuery, strUser);
    	
    	return em.find(Solicitant.class, pk);
    }
    
   
    
    public void SolicitantBaixa(Solicitant s, String strUser )
    {    
    	this.logAccessos("Solicitant. Baixa. PK:" + s.getCodiexpedient() , strUser);
    	em.remove(em.find(Solicitant.class, s.getCodiexpedient() ));
    	//em.remove(s);
    	
    }
    
    public void SolicitantModificar(Solicitant s, String strUser )
    {
    	
    	// treiem ambit violencia i violencia viscuda
    	Solicitant sVell = this.SolicitantPK(s.getCodiexpedient(), strUser);
    	
    	
    	List<SolicitantAmbitViolencia> sav = sVell.getSolicitantAmbitViolencias();
    	if (!sav.equals(s.getSolicitantAmbitViolencias()) || s.getSolicitantAmbitViolencias() == null ) 
    		for (int i=0; i!=sav.size(); i++) 
    			em.remove(em.find(SolicitantAmbitViolencia.class, sav.get(i).getId() ));
    			 //sVell.removeSolicitantAmbitViolencia(sav.get(i)); //em.remove(sav.get(i));
    	    	
    	List<SolicitantViolenciaViscuda> svv = sVell.getSolicitantViolenciaViscudas();
    	if (!svv.equals(s.getSolicitantViolenciaViscudas()) || s.getSolicitantViolenciaViscudas() == null)	
    		for (int i=0; i!=svv.size(); i++) 
    			em.remove(em.find(SolicitantViolenciaViscuda.class, svv.get(i).getId() ));
    			//sVell.removeSolicitantViolenciaViscuda(svv.get(i));  //em.remove(svv.get(i));
    	
    	em.flush();
    	em.merge(s);
    	
    	this.logAccessos("Solicitant. Modificacio. PK:"+s.getCodiexpedient(), strUser);
    }
       
    @SuppressWarnings("unchecked")
	public List<Solicitant> SolicitantNom(String strNom, String strUser)
    {
    	
    	Query query = em.createNamedQuery("Solicitant.finNom");
		query.setParameter("id_nomCognoms", strNom);
		
		// Control log accessos:
		String strQuery = Solicitant.consultaNom + " ;id_nomCognoms: "+ strNom;
		this.logAccessos(strQuery, strUser);
		 
		return query.getResultList();
		 	
    }
    
    
    
    @SuppressWarnings("unchecked")
   	public List<Solicitant> SolicitabntDatesNomSolicitant(Date dDataInicial, Date dDataFinal, String strNomSolicitant, String strUser )
       {
       	
       	Query query = em.createNamedQuery("Solicitant.DatesConsultaNomSolicitant");
   		query.setParameter("id_DataInicial", dDataInicial);
   		query.setParameter("id_DataFinal", dDataFinal);
   		query.setParameter("id_nomSolicitant", strNomSolicitant);
   	
   		// Control accessos:
   		String strQuery =  Solicitant.consultaDatesConsultaNomSolicitant + " ;id_DataInicial:"+ dDataInicial+" ;id_DataFinal:"+dDataFinal+" ;id_nomSolicitant: "+ strNomSolicitant;    
   	   	this.logAccessos(strQuery, strUser);
   		
   		return query.getResultList();
   		 	
       }
    
    
    @SuppressWarnings("unchecked")
   	public List<Solicitant> SolicitabntNumeroAnyExpedient(int numeroExpedient, int anyExpedient, String strUser )
       {
       	
       	Query query = em.createNamedQuery("Solicitant.numeroExpedient");
   		query.setParameter("id_nExpedient", numeroExpedient);
   		query.setParameter("id_anyExpedient", anyExpedient);
   		
   	
   		// Control accessos:
   		String strQuery =  Solicitant.consultaNumeroExpedient + " ;id_nExpedient:"+ numeroExpedient+" ;id_anyExpedient:"+anyExpedient;
   		this.logAccessos(strQuery, strUser);
   		
   		return query.getResultList();
   		 	
       }
    
    @SuppressWarnings("unchecked")
   	public List<Solicitant> SolicitantCercador(java.util.Date dNaixInicial, java.util.Date dNaixFinal, Date dDataInicial, Date dDataFinal, String strNomSolicitant, String strUser )
       {
    	Query query = null;
    	String strQuery = new String();
    	if (dNaixInicial != null)
    	{
    		query = em.createNamedQuery("Solicitant.cercador");
    		query.setParameter("id_dataNaixIncial", dNaixInicial);
    		query.setParameter("id_dataNaixFinal", dNaixFinal);
    		query.setParameter("id_DataInicial", dDataInicial);
    		query.setParameter("id_DataFinal", dDataFinal);
    		query.setParameter("id_nomSolicitant", strNomSolicitant);
   		
   	   		// Control accessos:
    		strQuery =  Solicitant.consultaCercador + " ;id_dataNaixIncial:"+ dNaixInicial+" ;id_dataNaixFinal:"+ dNaixFinal + " ;id_DataInicial:"+ dDataInicial+" ;id_DataFinal:"+dDataFinal+" ;id_nomSolicitant: "+ strNomSolicitant; 
    	}
    	else
    	{
    		query = em.createNamedQuery("Solicitant.cercadorSenseDataNaix");
    		query.setParameter("id_DataInicial", dDataInicial);
    		query.setParameter("id_DataFinal", dDataFinal);
    		query.setParameter("id_nomSolicitant", strNomSolicitant);
   		
   	   		// Control accessos:
    		strQuery =  Solicitant.consultaCercadorSenseDataNaix + " ;id_DataInicial:"+ dDataInicial+" ;id_DataFinal:"+dDataFinal+" ;id_nomSolicitant: "+ strNomSolicitant; 
    	}
   		
    	em.flush();
       	//query.setFlushMode(FlushModeType.AUTO);
    	//query.setHint("javax.persistence.cache.storeMode", CacheStoreMode.REFRESH);
    	this.logAccessos(strQuery, strUser);
   		return query.getResultList();
   		 	
       }
    
    /*
     * 
     * 
     * 		SOL·LICITANT AMBIT VIOLENCIA
     * 
     */
    @SuppressWarnings("null")
	public void solicitantAmbitViolenciaAlta(List <SolicitantAmbitViolencia> sav, String strUser)
    {
    		
    	for (int i=0; i!=sav.size(); i++)
    	{
    		//sav.get(i).setId((Long) null); // Ja esta inicialitzat alconstructor.
    		em.persist(sav.get(i));
    		this.logAccessos("solicitantAmbitViolencia. Alta PK:" + sav.get(i).getId(), strUser);
    	}
    }
    
    /*
     * 
     * 
     * 		SOL·LICITANT VIOLENCIA VISCUDA
     * 
     * 
     */
    
    @SuppressWarnings("null")
	public void solicitantViolenciaViscudaAlta(List<SolicitantViolenciaViscuda> svv, String strUser)
    {
    	for (int i=0; i!=svv.size();i++)
    	{
    		svv.get(i).setId((Long) null);
    		em.persist(svv.get(i));
    		this.logAccessos("solicitantViolenciaViscuda. Alta PK:"+svv.get(i).getId(), strUser);
    	}
    }
    
    
    /*
     * 
     * 
     * 
     *  CONSULTA
     * 
     * 
     */
    
    public Integer ConsultaAlta(Consulta c, String strUser)
    {
   
    	// com la pk es autogenerada cal enviar-li un nul altrament dona error.
    	c.setCodiconsulta(null);
    	
    	// Fiquem data d'alta.
    	c.setDataalta(new java.sql.Timestamp(new java.util.Date().getTime()));
    	
       	em.persist(c);
       	
       	this.logAccessos("Consulta. Alta. PK:"+ c.getCodiconsulta() , strUser);
    	
    	return c.getCodiconsulta();
    }
    
    public Consulta ConsultaPK(Integer pk, String strUser)
    {
    	
    	// Control accessos:
    	String strQuery = Consulta.consultaPK  + "; id_codiConsulta:"  +pk;
    	this.logAccessos(strQuery, strUser);
    	
    	return em.find(Consulta.class, pk);
    }
    
    public void ConsultaBaixa(Integer pk, String strUser)
    {
    	em.remove(em.find(Consulta.class, pk));
    	//em.setFlushMode(FlushModeType.AUTO);
    	//em.flush();
    	    	
    	this.logAccessos("Consulta. Baixa. PK:"+ pk , strUser);
    	
    }
    
    public void ConsultaBaixa(Consulta c, String strUser )
    {
    	//em.remove(c);
    	
    	this.ConsultaBaixa(c.getCodiconsulta(), strUser);
    	
    	//this.logAccessos("Consulta. Baixa. PK:"+ c.getCodiconsulta() , strUser);
    }
    
    public void ConsultaModificar(Consulta c, String strUser )
    {
    	em.merge(c);
    	
    	this.logAccessos("Consulta. Modificacio. PK:"+ c.getCodiconsulta() , strUser);
    	
    }
    
    @SuppressWarnings("unchecked")
   	public List<Consulta> ConsultaCodiExpedient(Integer nCodiExpedient, String strUser)
       {
       	
       	Query query = em.createNamedQuery("Consulta.Solicitant");
   		query.setParameter("id_codiExpedient", nCodiExpedient);
   		
   		// Control accessos:
   		String strQuery = Consulta.consultaSolicitant + " ;id_codiExpedient: "+nCodiExpedient ;
   		this.logAccessos(strQuery, strUser);
   		 
   		return query.getResultList();
   		 	
       }
    
    @SuppressWarnings("unchecked")
   	public List<Consulta> ConsultaDatesNomSolicitant(Date dDataInicial, Date dDataFinal, String strNomSolicitant, String strUser )
       {
       	
       	Query query = em.createNamedQuery("Consulta.DatesConsultaNomSolicitant");
   		query.setParameter("id_DataInicial", dDataInicial);
   		query.setParameter("id_DataFinal", dDataFinal);
   		query.setParameter("id_nomSolicitant", strNomSolicitant);
   	
   		// Control accessos:
   		String strQuery = Consulta.consultaDatesConsultaNomSolicitant + " ;id_DataInicial:"+ dDataInicial+" ;id_DataFinal:"+dDataFinal+" ;id_nomSolicitant: "+ strNomSolicitant;    
   	   	this.logAccessos(strQuery, strUser);
   		
   		return query.getResultList();
   		 	
       }
    
    /*
     * 
     *  Ambit Violencia
     * 
     */
/*    
    public void solicitantAmbitViolenciAlta( List <SolicitantAmbitViolencia> sav , String strUser)
    {
    	String strAux = new String();
    	for (int i=0; i!= sav.size(); i++)
    	{
    		em.merge(sav.get(i));
    		strAux += sav.get(i).getId() + " ";
    	}
    	
       	this.logAccessos("SolicitantAmbitViolencia. Alta. PKs: "+ strAux , strUser);
    	
    }
    
    @SuppressWarnings("unchecked")
	public List<SolicitantAmbitViolencia> solicitantAmbitViolenciaCerca( Integer codiExpedient)
    {
    	Query query = em.createNamedQuery("SolicitantAmbitViolencia.pkSolicitant");
   		query.setParameter("id_fkSolicitant", codiExpedient);
   		
   		// No ho passo pel log pq ja es sobreenten que quan s'accedeix al sol·licitant tb s'accedeix a aquesta taula.
   		
   		return query.getResultList();
    }
    
    public void solicitantAmbitViolenciaModificacio(List <SolicitantAmbitViolencia> sav, String strUser)
    {
    	// Obtenim registres actuals i els eliminem.
    	List<SolicitantAmbitViolencia> eliminar = this.solicitantAmbitViolenciaCerca(sav.get(0).getSolicitant().getCodiexpedient());
    //	for (int i=0; i!= eliminar.size(); i++) em.remove(eliminar.get(i));
    	
    	// Donem d'alta els nous.
    	this.solicitantAmbitViolenciaAlta(sav, strUser);
    	
    }
 */   
    
    /*
     * 
     *  Violencia Viscuda
     * 
     */
 /*   
    public void solicitantViolenciViscudaAlta( List <SolicitantViolenciaViscuda> svv , String strUser)
    {
    	String strAux = new String();
    	for (int i=0; i!= svv.size(); i++)
    	{
    		em.persist(svv.get(i));
    		strAux += svv.get(i).getId() + " ";
    	}
    	
       	this.logAccessos("SolicitantViolenciaViscuda. Alta. PKs: "+ strAux , strUser);
    	
    }
  
 */   
    
    /*
     * 
     * 
     * 			P	R	O	V	I	N	C	I	E	S
     * 
     * 
     */
    
    @SuppressWarnings("unchecked")
	public List<Provincies> provinciesNom(String strNomProvincia)
    {
    	
    		Query query = em.createNamedQuery("Provincies.finNom");
    		query.setParameter("id_Nom", strNomProvincia);
    		
    		// No cal passar pel control d'accessos. 
    		
    		return query.getResultList();
    	
    }
    
    /*
     * 
     * 
     * 	C	I	U	T	A	T	S
     * 
     * 
     */
    
    @SuppressWarnings("unchecked")
   	public List<Ciutats> ciutatsCodiPrivincia(int nCodiProvincia)
       {
       	
       		Query query = em.createNamedQuery("Ciutats.findProvincia");
       		query.setParameter("id_CodiProvincia", nCodiProvincia);
       		
       		// No cal passar pel control d'accessos. 
       		
       		return query.getResultList();
       	
       }
    
    @SuppressWarnings("unchecked")
   	public List<Ciutats> ciutatsNomPrivincia(String  strNomProvincia)
       {
       	
       		Query query = em.createNamedQuery("Ciutats.findNomProvincia");
       		query.setParameter("id_NomProvincia", strNomProvincia);
       		
       		// No cal passar pel control d'accessos. 
       		
       		return query.getResultList();
       }
    
    
    /*
     * 
     * 
     * 	P	A	I	S
     * 
     * 
     */
    
    @SuppressWarnings("unchecked")
	public List <Pais> PaisTots()
    {
    	Query query = em.createNamedQuery("Pais.tots");
    	return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
   	public List <Pais> PaisNom(String strNomPais)
       {
       	Query query = em.createNamedQuery("Pais.nom");
       	query.setParameter("id_nomPais", strNomPais);
       	
       	return query.getResultList();
       }
    
    
    /*
     * 
     * 
     * 
     * 			A	R	E	A
     * 
     * 
     */
    
    @SuppressWarnings("unchecked")
	public List <Area> areaTotes()
    {
    	Query query = em.createNamedQuery("Area.tots");
    	return query.getResultList();
    }
    
    /*
     * 
     * 
     * 
     * 		A	R	E	A		C	O	N	S	U	L	T	A
     * 
     */
    
    @SuppressWarnings("unchecked")
   	public List <AreaConsulta> areaConsultaId(long lId)
       {
       	Query query = em.createNamedQuery("AreaConsulta.areaID");
       	query.setParameter("id_id", lId);
       	return query.getResultList();
       }
    
    @SuppressWarnings("unchecked")
   	public List <AreaConsulta> areaConsultaText(String strTexte)
       {
       	Query query = em.createNamedQuery("AreaConsulta.areaText");
       	query.setParameter("id_textArea", strTexte);
       	return query.getResultList();
       }
    
    /*
     * 
     * 
     * 		C	O	M	A	R	Q	U	E	S	
     * 
     * 
     */
    
    @SuppressWarnings("unchecked")
   	public List <Comarques> comarquesTotes()
       {
       	Query query = em.createNamedQuery("Comarques.totes");
        return query.getResultList();
       }
    
    /*
     * 
     * 
     * 		TAULES AUXILIARS
     * 
     */
    
    // ambit violencia
    
    @SuppressWarnings("unchecked")
   	public List <AmbitViolencia> ambitViolenciaTots()
       {
       	Query query = em.createNamedQuery("AmbitViolencia.findAll");
        return query.getResultList();
       }
    
    // Canal d'entrada  
    @SuppressWarnings("unchecked")
   	public List <CanalEntrada> canalEntradaTots()
       {
       	Query query = em.createNamedQuery("CanalEntrada.findAll");
        return query.getResultList();
       }
    
    // DerivacioConsellComarcal
    @SuppressWarnings("unchecked")
   	public List <DerivacioConsellComarcal> derivacioConsellComarcalTots()
       {
       	Query query = em.createNamedQuery("DerivacioConsellComarcal.findAll");
        return query.getResultList();
       }
    
 // DerivacioEstat
    @SuppressWarnings("unchecked")
   	public List <DerivacioEstat> derivacioEstatTots()
       {
       	Query query = em.createNamedQuery("DerivacioEstat.findAll");
        return query.getResultList();
       }
    
 // DerivacioGeneralitat
    @SuppressWarnings("unchecked")
   	public List <DerivacioGeneralitat> derivacioGeneralitatTots()
       {
       	Query query = em.createNamedQuery("DerivacioGeneralitat.findAll");
        return query.getResultList();
       }
    
    // DerivacioInterna
    @SuppressWarnings("unchecked")
   	public List <DerivacioInterna> derivacioInternaTots()
       {
       	Query query = em.createNamedQuery("DerivacioInterna.findAll");
        return query.getResultList();
       }
    
    // DerivacionEntitat
    @SuppressWarnings("unchecked")
   	public List <DerivacionEntitat> derivacionEntitatTots()
       {
       	Query query = em.createNamedQuery("DerivacionEntitat.findAll");
        return query.getResultList();
       }
    
    // NivellFormacio
    @SuppressWarnings("unchecked")
   	public List <NivellFormacio> nivellFormacioTots()
       {
       	Query query = em.createNamedQuery("NivellFormacio.findAll");
        return query.getResultList();
       }
    
    // NivellFormacio
    @SuppressWarnings("unchecked")
   	public List <Professio> professioTots()
       {
       	Query query = em.createNamedQuery("Professio.findAll");
        return query.getResultList();
       }
    
    // ServeiAtencio
    @SuppressWarnings("unchecked")
   	public List <ServeiAtencio> serveiAtencioTots()
       {
       	Query query = em.createNamedQuery("ServeiAtencio.findAll");
        return query.getResultList();
       }
    
    // SituacioEconomica
    @SuppressWarnings("unchecked")
   	public List <SituacioEconomica> situacioEconomicaTots()
       {
       	Query query = em.createNamedQuery("SituacioEconomica.findAll");
        return query.getResultList();
       }
    
 // SituacioEconomica
    @SuppressWarnings("unchecked")
   	public List <SituacioLaboral> situacioLaboralTots()
       {
       	Query query = em.createNamedQuery("SituacioLaboral.findAll");
        return query.getResultList();
       }
    
 // Tramitacio
    @SuppressWarnings("unchecked")
   	public List <Tramitacio> tramitacioTots()
       {
       	Query query = em.createNamedQuery("Tramitacio.findAll");
        return query.getResultList();
       }
    
    // UnitatConvivenca
    @SuppressWarnings("unchecked")
   	public List <UnitatConvivenca> unitatConvivencaTots()
       {
       	Query query = em.createNamedQuery("UnitatConvivenca.findAll");
        return query.getResultList();
       }
    
    // ViolenciaViscuda
    @SuppressWarnings("unchecked")
   	public List <ViolenciaViscuda> violenciaViscudaTots()
       {
       	Query query = em.createNamedQuery("ViolenciaViscuda.findAll");
        return query.getResultList();
       }
    
    @SuppressWarnings("unchecked")
    public List <EstatCivil> estatCivilTots()
    {
       	Query query = em.createNamedQuery("EstatCivil.findAll");
        return query.getResultList();
       }
    
}
