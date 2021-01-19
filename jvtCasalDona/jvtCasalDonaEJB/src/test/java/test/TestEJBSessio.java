package test;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import ejb.SessioLocal;

import jvtCasalDonaEJB.Consulta;
import jvtCasalDonaEJB.Solicitant;

//import ejb.Sessio;

public class TestEJBSessio {
	/*
	@PersistenceContext EntityManager entityManager;
	private SessioLocal ejb;
	private Integer nPKSol;
	private Integer nPKC;
	
	//@BeforeMethod
	@BeforeClass
	public void setUp() 
	{
		System.out.println(this.getClass().getSimpleName() + " test started:");
	
		//https://www.programcreek.com/java-api-examples/?api=javax.ejb.embeddable.EJBContainer
		
		/*
		 * 
		 *  IMPORTANT: PEL WAS MIRAR: 
		 *  
		 *  https://www.ibm.com/support/knowledgecenter/es/SS7K4U_9.0.5/com.ibm.websphere.zseries.doc/ae/tnam_develop_naming.html
		 * 
		 * 
		 */
	/*	
		try {
			Properties jndiProperties = new Properties();
            jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
			 
			InitialContext ic = new InitialContext(jndiProperties);
			
			 ejb = (SessioLocal) ic.lookup("ejb:jvtCasalDona/jvtCasalDonaEJB/Sessio!ejb.SessioRemote");
			
			System.out.println(this.getClass().getSimpleName() + " Lookup fet.\n");
		} 
		catch (NamingException e) {e.printStackTrace(); }
     

	}
	
	/*
	 * 
	 * 
	 * 		He ordenat el mètodes per ordre d'execució malgrat que l'ordre ho indica el paràmetre priority.
	 * 
	 * 
	 */
	/*
  @Test (priority = 10)
  public void SolicitantAlta() {
	  
	Solicitant s = new Solicitant();
	s.setBarri("Barri");
	s.setCanalentrada("CanalEntrada");
	s.setCodiexpedient(1);
	s.setComarca("Comarca");
	s.setCp("25001");
	s.setDemarcacio("Demarcacio");
	s.setEstatcivil("C");
	s.setFormaciohomologada("FH");
	s.setNivellformacio("Nivell");
	s.setNomcognoms("NomCognoms Proves TestNG");
	s.setPaisorigen("Pais");
	s.setPoblacio("Poblacio");
	s.setPrestacio("PRestacio");
	s.setProfessio("Professió");
	s.setRepprestacions("RP");
	s.setSexe("H");
	s.setSiad("SIAD");
	s.setSituacioeconomica("Situacio€");
	s.setSituaciolaboral("SituacioLaboral");
	s.setTelefon("telf");
	
	s.setUnitatconvivencia("UnitatC");
	s.setDatanaix(null );
	s.setUsuari("userTest");
		
	s = ejb.SolicitantAlta(s, "userTest");
	
	 nPKSol = s.getCodiexpedient();
	 
	System.out.println("Sol·licitant: Alta PK:"+nPKSol);  
	
  }
  
  @Test (priority = 20)
  public void SolicitantPK()
  {
	  Solicitant s = ejb.SolicitantPK(nPKSol , "userTest");
	  System.out.println("Sol·lictant: Buscar PK :"+s.getCodiexpedient() + " Nom:"+s.getNomcognoms());  
  }
  

  @Test (priority = 30)
  public void SolicitantModificar()
  {
	  
	  Solicitant s = ejb.SolicitantPK(nPKSol , "userTest");
	  s.setNomcognoms("Modificat TESTNG proves TestNG");
	  ejb.SolicitantModificar(s, "userTest");
	  System.out.println("Sol·lictant: Modificar :"+s.getCodiexpedient() + " Nom:"+s.getNomcognoms());  
  }
  
  @Test (priority = 40)
  public void SolicitantBuscarNom()
  {
	  
	  List<Solicitant> s = ejb.SolicitantNom("Modificat TESTNG%" , "userTest");
	  System.out.println("Sol·lictant: Consulta per nom. Elements trobats:" + s.size() + " Nom primer element:"+s.get(0).getNomcognoms()+" PK:"+s.get(0).getCodiexpedient());  
  }
  
  
  
  @Test (priority = 200)
  public void ConsultaAlta()
  {
	Consulta c = new Consulta();
		
//	c.setAltres("AL");
	c.setArea("Area");
	c.setAreaconsulta("Area consulta PROVES TESTNG");
	c.setDatacon(new java.util.Date());
//	c.setDerivacigen("Derivació");
//	c.setDerivacioaltres("Derivacions Altres");
//	c.setGeneralitat("GE");
//	c.setJuridic("JU");
//	c.setPsicologic("PS");
//	c.setSense("SE");
	c.setServeiaten("SA");
	c.setSolicitant(ejb.SolicitantPK(nPKSol , "userTest"));
		
	nPKC = ejb.ConsultaAlta(c, "userTest");
	
	System.out.println("Consulta: Alta PK:" + nPKC);
	  
  }
  
  @Test (priority = 210)
  public void ConsultaPK()
  {
	  Consulta c = ejb.ConsultaPK(nPKC , "userTest");
	  
	  System.out.println("Consulta: Consulta PK:" + c.getCodiconsulta() + " Area Consulta:" + c.getAreaconsulta() + " Sol·licitant:" + c.getSolicitant().getNomcognoms());
	  
  }
  
  
  @Test (priority = 220)
  public void ConsultaModificar()
  {
	  Consulta c = ejb.ConsultaPK(nPKC , "userTest");
	  c.setAreaconsulta("Area consulta PROVES TESTNG MODIFICAT AL TEST");
	  ejb.ConsultaModificar(c, "userTest");
	  
	  System.out.println("Consulta: Modificacio PK:" + c.getCodiconsulta() + " AreaConsulta:"+c.getAreaconsulta());
	  
  }
  
  @Test (priority = 230)
  public void ConsultaCodiExpedientSolicitant()
  {
	  List<Consulta> c = ejb.ConsultaCodiExpedient(nPKSol, "userTest");
	  System.out.println("Consulta: Cerca codi expedient sol·licitant Quants:"+c.size()+ " Primer pk consulta:"+c.get(0).getCodiconsulta()+ " Nom Sol·licitant: "+ c.get(0).getSolicitant().getNomcognoms() );	  
  }
  
  @Test (priority = 240)
  public void ConsultaDatesNom()
  {
	  List<Consulta> c = ejb.ConsultaDatesNomSolicitant(new java.util.Date(), new java.util.Date(), "%TestNG%", "userTest");
	  System.out.println("Consulta: Cerca per dates de consulta i nom sol·licitant. Quants:"+c.size()+ " Primer pk consulta:"+c.get(0).getCodiconsulta()+ " Nom Sol·licitant: "+ c.get(0).getSolicitant().getNomcognoms() );	  
	  
  }
  
  @Ignore
  @Test (priority = 499)
  public void ConsultaBaixa()
  {
	  ejb.ConsultaBaixa(nPKC, "userTest");
	  System.out.println("Consulta: Baixa per pk: "+nPKC);
	  
  }
  
//Vull utilitzar aquest registre per tots els tests --> última prioritat d'execució.
  @Ignore
  @Test (priority = 500)
  public void SolicitantBaixa()
  {
	 // ejb.SolicitantBaixa(nPKSol, "userTest");
	  System.out.println("Sol·licitant: Baixa per pk: "+nPKSol);
  }
  */
  
}
