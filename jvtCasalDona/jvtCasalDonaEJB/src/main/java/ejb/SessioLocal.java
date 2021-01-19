package ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import jvtCasalDonaEJB.AmbitViolencia;
import jvtCasalDonaEJB.Consulta;
import jvtCasalDonaEJB.Solicitant;
import jvtCasalDonaEJB.SolicitantAmbitViolencia;
import jvtCasalDonaEJB.SolicitantViolenciaViscuda;
import jvtCasalDonaEJB.ViolenciaViscuda;
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

@Local
public interface SessioLocal {
	
	 public Solicitant SolicitantAlta(Solicitant s, String strUser);
	 public Solicitant SolicitantPK(Integer pk, String strUser);
	 public void SolicitantBaixa(Solicitant s, String strUser );
	 public List<Solicitant> SolicitantNom(String strNom, String strUser);
	 public void SolicitantModificar(Solicitant s , String strUser);
	 public List<Solicitant> SolicitabntDatesNomSolicitant(Date dDataInicial, Date dDataFinal, String strNomSolicitant, String strUser );
	 
	 public Integer ConsultaAlta(Consulta c, String strUser);
	 public Consulta ConsultaPK(Integer pk, String strUser);
	 public void ConsultaBaixa(Integer pk, String strUser);
	 public void ConsultaBaixa(Consulta c , String strUser);
	 public void ConsultaModificar(Consulta c, String strUser );
	 public List<Consulta> ConsultaCodiExpedient(Integer nCodiExpedient, String strUser);
	 public List<Consulta> ConsultaDatesNomSolicitant(Date dDataInicial, Date dDataFinal, String strNomSolicitant, String strUser );
	 public List<Solicitant> SolicitabntNumeroAnyExpedient(int numeroExpedient, int anyExpedient, String strUser );
	 //public List<Solicitant> SolicitabntDataNaix(java.util.Date dInicial, java.util.Date dFinal, String strUser );
	 public List<Solicitant> SolicitantCercador(java.util.Date dNaixInicial, java.util.Date dNaixFinal, Date dDataInicial, Date dDataFinal, String strNomSolicitant, String strUser );

	 /*
	 public void solicitantAmbitViolenciAlta( List <SolicitantAmbitViolencia> sav , String strUser);
	 public void solicitantViolenciViscudaAlta( List <SolicitantViolenciaViscuda> svv , String strUser);
	 public List<SolicitantAmbitViolencia> solicitantAmbitViolenciaCerca( Integer codiExpedient);
	 public void solicitantAmbitViolenciaModificacio(List <SolicitantAmbitViolencia> sav, String strUser);
	 */
	 public List<Provincies> provinciesNom(String strNomProvincia);
	 
	 public List<Ciutats> ciutatsCodiPrivincia(int nCodiProvincia);
	 public List<Ciutats> ciutatsNomPrivincia(String  strNomProvincia);
	 
	 public List <Area> areaTotes();
	 
	 public List <Pais> PaisTots();
	 public List <Pais> PaisNom(String strNomPais);
	 
	 public List <AreaConsulta> areaConsultaId(long lId);
	 public List <AreaConsulta> areaConsultaText(String strTexte);
	 
	 public List <Comarques> comarquesTotes();
	
	public List <AmbitViolencia> ambitViolenciaTots();
	public List <CanalEntrada> canalEntradaTots();
	public List <DerivacioConsellComarcal> derivacioConsellComarcalTots();
  	public List <DerivacioEstat> derivacioEstatTots();
  	public List <DerivacioGeneralitat> derivacioGeneralitatTots();
  	public List <DerivacioInterna> derivacioInternaTots();
  	public List <DerivacionEntitat> derivacionEntitatTots();
	public List <EstatCivil> estatCivilTots();
	public List <NivellFormacio> nivellFormacioTots();
	public List <Professio> professioTots();
	public List <ServeiAtencio> serveiAtencioTots();
	public List <SituacioEconomica> situacioEconomicaTots();
	public List <SituacioLaboral> situacioLaboralTots();
	public List <Tramitacio> tramitacioTots();
	public List <UnitatConvivenca> unitatConvivencaTots();
	public List <ViolenciaViscuda> violenciaViscudaTots();
    
}
