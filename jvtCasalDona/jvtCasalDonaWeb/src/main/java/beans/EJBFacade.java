package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.SessioLocal;
import jvtCasalDonaEJB.AmbitViolencia;
import jvtCasalDonaEJB.Consulta;
import jvtCasalDonaEJB.Solicitant;
import jvtCasalDonaEJB.SolicitantAmbitViolencia;
import jvtCasalDonaEJB.SolicitantViolenciaViscuda;
import jvtCasalDonaEJB.ViolenciaViscuda;
import jvtCasalDonaEJB.constants.*;

public class EJBFacade {

	private static EJBFacade instance;
	
	private SessioLocal EJBSessio;

	public EJBFacade() {
		// TODO Apendice de constructor generado automaticamente
		InitialContext ic;
		try {
			ic = new InitialContext();
			EJBSessio = (SessioLocal) ic.lookup("java:global/jvtCasalDona/jvtCasalDonaEJB/Sessio!ejb.SessioLocal");
			
		} catch (NamingException e) {
			// TODO Bloque catch generado automaticamente
			e.printStackTrace();
		}
		
	}
	
	public static synchronized EJBFacade getInstance() {
		if(instance==null)
			instance=new EJBFacade();
		
		return instance;
	}
	
	
	/*
	 * ******************************************
	 * CARREGA DE LES TAULES DE VALORS CONSTANTS
	 * ******************************************
	 */
	
	public List<Pais> cercaPaisos() {
		return EJBSessio.PaisTots();
	}
	
	public List<Comarques> cercaComarques() {
		return EJBSessio.comarquesTotes();
	}
	
	public List<Provincies> cercaProvincies(String nomProvincia) {
		return EJBSessio.provinciesNom(nomProvincia);
	}
	
	public List<Ciutats> cercaCiutats(String nomProvincia) {
		return EJBSessio.ciutatsNomPrivincia(nomProvincia);
	}
	
	
	public List<Area> cercaArees() {
		return EJBSessio.areaTotes();
	}
	
	public List<AreaConsulta> cercaAreaConsulta(String  nomArea) {
		return EJBSessio.areaConsultaText(nomArea);
	}

	public List<DerivacioConsellComarcal> cercaDerivacioConsellComarcal() {
		return EJBSessio.derivacioConsellComarcalTots();
	}
	
	public List<DerivacioGeneralitat> cercaDerivacioGeneralitat() {
		return EJBSessio.derivacioGeneralitatTots();
	}
	
	public List<DerivacioInterna> cercaDerivacioInterna() {
		return EJBSessio.derivacioInternaTots();
	}
	
	public List<DerivacionEntitat> cercaDerivacioEntitat() {
		return EJBSessio.derivacionEntitatTots();
	}
	
	public List<DerivacioEstat> cercaDerivacioEstat() {
		return EJBSessio.derivacioEstatTots();
	}
	
	public List<CanalEntrada> cercaCanalEntrada() {
		return EJBSessio.canalEntradaTots();
	}
	
	public List<UnitatConvivenca> cercaUnitatConvivencia() {
		return EJBSessio.unitatConvivencaTots();
	}
	
	public List<AmbitViolencia> cercaAmbitViolencia() {
		return EJBSessio.ambitViolenciaTots();
	}
	
	public List<ViolenciaViscuda> cercaViolenciaViscuda() {
		return EJBSessio.violenciaViscudaTots();
	}
	
	public List<EstatCivil> cercaEstatCivil() {
		return EJBSessio.estatCivilTots();
	}
	
	public List<Professio> cercaProfessio() {
		return EJBSessio.professioTots();
	}
	
	public List<SituacioLaboral> cercaSituacioLaboral() {
		return EJBSessio.situacioLaboralTots();
	}
	
	public List<SituacioEconomica> cercaSitiacioEconomica() {
		return EJBSessio.situacioEconomicaTots();
	}
	
	public List<Tramitacio> cercaTramitacio() {
		return EJBSessio.tramitacioTots();
	}
	
	public List<NivellFormacio> cercaNivellFormacio() {
		return EJBSessio.nivellFormacioTots();
	}
	
	public List<ServeiAtencio> cercaServeiAtencio() {
		return EJBSessio.serveiAtencioTots();
	}
	
	
	
	/*
	 * ********************************** 
	 * METODES DE EXPEDIENT I CONSULTA
	 * **********************************
	 */
	
	
	public Solicitant altaExpedient(Solicitant s, Consulta c, List<Integer> ambitViolenciaSeleccionats, List<Integer> violenciaViscudaSeleccionats, String usuari) throws NamingException {
		
		List<SolicitantAmbitViolencia> solicitantAmbitViolencia = null;
		if (ambitViolenciaSeleccionats.size()>0) {
			solicitantAmbitViolencia= new ArrayList<>();
			for (int i=0; i<ambitViolenciaSeleccionats.size();i++) {
				SolicitantAmbitViolencia sav=new SolicitantAmbitViolencia();
				sav.setFkAmbitViolencia(Integer.valueOf(ambitViolenciaSeleccionats.get(i)));
				sav.setSolicitant(s);
				solicitantAmbitViolencia.add(sav);
			}
		}
		
		
		List<SolicitantViolenciaViscuda> solicitantViolenciaViscuda = null;
		if (violenciaViscudaSeleccionats.size()>0) {
			solicitantViolenciaViscuda= new ArrayList<>();
			for (int i=0; i<violenciaViscudaSeleccionats.size();i++) {
				SolicitantViolenciaViscuda svv=new SolicitantViolenciaViscuda();
				svv.setFkViolenciaViscuda(Integer.valueOf(violenciaViscudaSeleccionats.get(i)));
				svv.setSolicitant(s);
				solicitantViolenciaViscuda.add(svv);
			}
		}
		
		s.setSolicitantAmbitViolencias(solicitantAmbitViolencia);
		s.setSolicitantViolenciaViscudas(solicitantViolenciaViscuda);
		
		List<Consulta> consultes = new ArrayList<>();
		c.setSolicitant(s);
		
		consultes.add(c);
		s.setConsultas(consultes);
		
		
		Solicitant solicitant=EJBSessio.SolicitantAlta(s,usuari);
		return solicitant;
	}
	
	public List<Solicitant> cercaExpedients(Integer numeroExpedient, Integer anyExpedient, String usuari) {
		return EJBSessio.SolicitabntNumeroAnyExpedient(numeroExpedient, anyExpedient, usuari);
	}
	
	public List<Solicitant> cercaExpedients(Date dNaixInicial,Date dNaixFinal, Date dDataInicial,Date dDataFinal, String strNomSolicitant, String usuari) {
		return EJBSessio.SolicitantCercador(dNaixInicial, dNaixFinal, dDataInicial, dDataFinal, strNomSolicitant, usuari);
	}
	
	public void modificaExpedient(Solicitant s, List<Integer> ambitViolenciaSeleccionats, List<Integer> violenciaViscudaSeleccionats, String usuari) {
		
		List<SolicitantAmbitViolencia> solicitantAmbitViolencia = null;
		if (ambitViolenciaSeleccionats!=null && ambitViolenciaSeleccionats.size()>0) {
			solicitantAmbitViolencia= new ArrayList<>();
			for (int i=0; i<ambitViolenciaSeleccionats.size();i++) {
				SolicitantAmbitViolencia sav=new SolicitantAmbitViolencia();
				sav.setFkAmbitViolencia(Integer.valueOf(ambitViolenciaSeleccionats.get(i)));
				sav.setSolicitant(s);
				solicitantAmbitViolencia.add(sav);
			}
			
		}
		
		List<SolicitantViolenciaViscuda> solicitantViolenciaViscuda = null;
		if (violenciaViscudaSeleccionats!=null && violenciaViscudaSeleccionats.size()>0) {
			solicitantViolenciaViscuda= new ArrayList<>();
			for (int i=0; i<violenciaViscudaSeleccionats.size();i++) {
				SolicitantViolenciaViscuda svv=new SolicitantViolenciaViscuda();
				svv.setFkViolenciaViscuda(Integer.valueOf(violenciaViscudaSeleccionats.get(i)));
				svv.setSolicitant(s);
				solicitantViolenciaViscuda.add(svv);
			}
			
		} 
			
		s.setSolicitantAmbitViolencias(solicitantAmbitViolencia);
		s.setSolicitantViolenciaViscudas(solicitantViolenciaViscuda);
		EJBSessio.SolicitantModificar(s, usuari);
	}
	
	public void eliminaExpedient(Solicitant s,  String usuari) {
		EJBSessio.SolicitantBaixa(s, usuari);
	}
	
	public void eliminaConsulta(Consulta c, String usuari) {
		EJBSessio.ConsultaBaixa(c, usuari);
	}
	
	public void modificaConsulta(Consulta c, String usuari) {
		EJBSessio.ConsultaModificar(c, usuari);
	}
	
	
}
