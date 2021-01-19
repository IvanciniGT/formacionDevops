package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.naming.NamingException;

import jvtCasalDonaEJB.constants.*;
import jvtCasalDonaEJB.AmbitViolencia;
import jvtCasalDonaEJB.Consulta;

import jvtCasalDonaEJB.Solicitant;
import jvtCasalDonaEJB.ViolenciaViscuda;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;


@ManagedBean(name="BDadesExpedient")
@SessionScoped
public class DadesExpedientBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2831658160264698667L;
	
	private Solicitant solicitant;
	private Consulta consulta;
	private List<SelectItem> llistatSexe;
	private List<SelectItem> llistatCanalsEntrada;
	private List<SelectItem> llistatTramitacio;
	private List<SelectItem> llistatSituacioLaboral;
	private List<SelectItem> llistatEstatCivil;
	private List<SelectItem> llistatUnitatConvivencia;
	private List<SelectItem> llistatProfessio;
	private List<SelectItem> llistatNivellFormacio;
	private List<SelectItem> llistatSituacioEconomica;
	private List<SelectItem> llistatServeiAtencio;

	private List<SelectItem> llistatAmbitViolencia;
	private List<SelectItem> llistatViolenciaViscuda;
	
	private List<Integer> ambitViolenciaSeleccionats;
	private List<Integer> violenciaViscudaSeleccionats;
	
	private List<SelectItem> llistatDerivacioInterna;
	private List<SelectItem> llistatDerivacioEstat;
	private List<SelectItem> llistatDerivacioConsellComarcal;
	private List<SelectItem> llistatDerivacioGeneralitat;
	private List<SelectItem> llistatDerivacioEntitat;
	
	private List<SelectItem> llistaPaisos;
	private List<SelectItem> llistaComarques;
	private List<SelectItem> llistaProvincies;
	private List<SelectItem> llistaCiutats;

	private List<SelectItem> llistatArea;
	private List<SelectItem> llistatAreaConsulta;

	//Variables Detalls Expedient
	private Consulta consultaSeleccionada;
	private String origenNavegacio;

	public String getOrigenNavegacio() {
		return origenNavegacio;
	}

	public void setOrigenNavegacio(String origenNavegacio) {
		this.origenNavegacio = origenNavegacio;
	}

	public DadesExpedientBean() {
		// TODO Apendice de constructor generado automaticamente
		inicialitza();
		carregaDesplegables();
	}
	
	public void inicialitza() {
		solicitant=new Solicitant();
		consulta= new Consulta();
		consultaSeleccionada= new Consulta();
		inicialitzaConsultes();
		ambitViolenciaSeleccionats=new ArrayList<>();
		violenciaViscudaSeleccionats=new ArrayList<>();
	}
	
	public void inicialitzaConsultes() {
		consulta= new Consulta();
		consultaSeleccionada= new Consulta();
		consulta.setDataalta(new java.sql.Timestamp(new java.util.Date().getTime()));
		consulta.setDatacon(new Date(System.currentTimeMillis()));
		consultaSeleccionada=null;
	}
	
	public void carregaDesplegables() {
		System.out.println("******* Carreguem desplegables");
		carregaSexe();
		carregaCanalsEntrada();
		carregaTramitacio();
		carregaEstatCivil();
		carregaNivellFormacio();
		carregaProfessions();
		carregaSituacioLaboral();
		carregaUnitatConvivencia();
		carregaSituacioEconomica();
		carregaServeiAtencio();
		carregaPaisos();
		carregaProvincies();
		carregaComarques();
		carregaCiutats();
		carregaAmbitViolencia();
		carregaViolenciaViscuda();
		carregaDerivacioInterna();
		carregaDerivacioEntitat();
		carregaDerivacioConsellComarcal();
		carregaDerivacioEstat();
		carregaDerivacioGeneralitat();
		carregaArees();
		carregaAreaConsulta();

	}
	
	public void altaExpedient(String usuari) throws NamingException {
		solicitant.setSiad("CENTRE D'INFORMACIÓ I ATENCIÓ A LES DONES - LLEIDA");
		solicitant.setUsuari(usuari);
		consulta.setUsuari(usuari);

		solicitant=EJBFacade.getInstance().altaExpedient(solicitant, consulta, ambitViolenciaSeleccionats, violenciaViscudaSeleccionats, usuari);
		inicialitzaConsultes();
	}
	
	public void modificaExpedient(String usuari) throws NamingException {
		solicitant.setUsuari(usuari);
		EJBFacade.getInstance().modificaExpedient(solicitant, ambitViolenciaSeleccionats, violenciaViscudaSeleccionats, usuari);
	}
	
	public String eliminaExpedient(String usuari) {
		String msg="";
		if(solicitant.getConsultas().size()>0)
			msg="L'expedient no es pot donar de baixa perquè té consultes associades. Ha d'eliminar primer les consultes.";
		else
			EJBFacade.getInstance().eliminaExpedient(solicitant, usuari);
		
		return msg;
	}
	
	public void seleccionaExpedient(Solicitant s, String usuari) {
		inicialitza();
		solicitant=s;
		carregaDesplegables();
		if(solicitant.getSolicitantAmbitViolencias()!=null && solicitant.getSolicitantAmbitViolencias().size()>0) {
			for (int i=0;i<solicitant.getSolicitantAmbitViolencias().size();i++) 
				ambitViolenciaSeleccionats.add(solicitant.getSolicitantAmbitViolencias().get(i).getFkAmbitViolencia());
		} 

		if(solicitant.getSolicitantViolenciaViscudas()!=null && solicitant.getSolicitantViolenciaViscudas().size()>0) {
			for (int i=0;i<solicitant.getSolicitantViolenciaViscudas().size();i++) 
				violenciaViscudaSeleccionats.add(solicitant.getSolicitantViolenciaViscudas().get(i).getFkViolenciaViscuda());
		}
		
	}
	
	public void altaConsulta(String usuari) {
		consulta.setUsuari(usuari);
		solicitant.addConsulta(consulta);
		EJBFacade.getInstance().modificaExpedient(solicitant, null, null, usuari);
		inicialitzaConsultes();
	}
	
	public void eliminaConsulta(String usuari) {
		solicitant.getConsultas().remove(consultaSeleccionada);
		EJBFacade.getInstance().eliminaConsulta(consultaSeleccionada, usuari);
		consultaSeleccionada= null;
	}
	
	public void modificaConsulta(Consulta  c, String usuari) {
		
		EJBFacade.getInstance().modificaConsulta(c, usuari);

		for (int i=0; i<solicitant.getConsultas().size();i++)
			if (c.getCodiconsulta().equals(solicitant.getConsultas().get(i).getCodiconsulta())) {
				solicitant.getConsultas().set(i, c);
			}
	}
	

	public void carregaPaisos() {
		if(llistaPaisos==null) {
			List<Pais> paisos=EJBFacade.getInstance().cercaPaisos();
			llistaPaisos=new ArrayList<>();
			llistaPaisos.add(new SelectItem("[No consta]","[No consta]"));
			for (int i=0; i<paisos.size();i++) {
				llistaPaisos.add(new SelectItem(paisos.get(i).getPais(),paisos.get(i).getPais()));
			}
		}
	}
	
	private void carregaSexe() {
		if (llistatSexe==null) {
			llistatSexe= new ArrayList<>();
			llistatSexe.add(new SelectItem("Dona","Dona"));
			llistatSexe.add(new SelectItem("Home","Home"));
			llistatSexe.add(new SelectItem("No binari","No binari"));
			llistatSexe.add(new SelectItem("[No consta]","[No consta]"));
		}
	}
	
	private void carregaCanalsEntrada() {
		if(llistatCanalsEntrada==null) {
			List<CanalEntrada> canals= EJBFacade.getInstance().cercaCanalEntrada();		
			llistatCanalsEntrada= new ArrayList<>();
			for (int i=0; i<canals.size();i++) 
				llistatCanalsEntrada.add(new SelectItem(canals.get(i).getTextCanalEntrada(),canals.get(i).getTextCanalEntrada()));
		}
	}
	
	private void carregaDerivacioInterna() {
		if(llistatDerivacioInterna==null) {
			List<DerivacioInterna> derivacions= EJBFacade.getInstance().cercaDerivacioInterna();
			llistatDerivacioInterna= new ArrayList<>();
			for (int i=0; i<derivacions.size();i++) 
				llistatDerivacioInterna.add(new SelectItem(derivacions.get(i).getTextDereivacioInterna(),derivacions.get(i).getTextDereivacioInterna()));
		}
	}
	
	private void carregaDerivacioEstat() {
		if(llistatDerivacioEstat==null) {
			List<DerivacioEstat> derivacions= EJBFacade.getInstance().cercaDerivacioEstat();
			llistatDerivacioEstat= new ArrayList<>();
			for (int i=0; i<derivacions.size();i++) 
				llistatDerivacioEstat.add(new SelectItem(derivacions.get(i).getTextDerivacioEstat(),derivacions.get(i).getTextDerivacioEstat()));
		}
	}

	private void carregaDerivacioConsellComarcal() {
		if(llistatDerivacioConsellComarcal==null) {
			List<DerivacioConsellComarcal> derivacions= EJBFacade.getInstance().cercaDerivacioConsellComarcal();
			llistatDerivacioConsellComarcal= new ArrayList<>();
			for (int i=0; i<derivacions.size();i++) 
				llistatDerivacioConsellComarcal.add(new SelectItem(derivacions.get(i).getTextDerivacioConsellComarcal(),derivacions.get(i).getTextDerivacioConsellComarcal()));
		}
	}
	
	private void carregaDerivacioEntitat() {
		if(llistatDerivacioEntitat==null) {
			List<DerivacionEntitat> derivacions= EJBFacade.getInstance().cercaDerivacioEntitat();
			llistatDerivacioEntitat= new ArrayList<>();
			for (int i=0; i<derivacions.size();i++) 
				llistatDerivacioEntitat.add(new SelectItem(derivacions.get(i).getTextDerivacioEntitat(),derivacions.get(i).getTextDerivacioEntitat()));
		}
	}
	
	private void carregaDerivacioGeneralitat() {
		if(llistatDerivacioGeneralitat==null) {
			List<DerivacioGeneralitat> derivacions= EJBFacade.getInstance().cercaDerivacioGeneralitat();
			llistatDerivacioGeneralitat= new ArrayList<>();
			for (int i=0; i<derivacions.size();i++) 
				llistatDerivacioGeneralitat.add(new SelectItem(derivacions.get(i).getTextDerivacioGeneralitat(),derivacions.get(i).getTextDerivacioGeneralitat()));
		}
	}
	
	private void carregaTramitacio() {
		if(llistatTramitacio==null) {
			List<Tramitacio> llista= EJBFacade.getInstance().cercaTramitacio();	
			llistatTramitacio= new ArrayList<>();
			for (int i=0; i<llista.size();i++) 
				llistatTramitacio.add(new SelectItem(llista.get(i).getTextTramitacio(),llista.get(i).getTextTramitacio()));	
		}
	}
	
	private void carregaSituacioLaboral() {
		if(llistatSituacioLaboral==null) {
			List<SituacioLaboral> llista= EJBFacade.getInstance().cercaSituacioLaboral();
			llistatSituacioLaboral= new ArrayList<>();
			for (int i=0; i<llista.size();i++) 
				llistatSituacioLaboral.add(new SelectItem(llista.get(i).getTextSituacioLaboral(),llista.get(i).getTextSituacioLaboral()));	
		}
	}
	
	private void carregaEstatCivil() {
		if(llistatEstatCivil==null) {
			List<EstatCivil> llista= EJBFacade.getInstance().cercaEstatCivil();
			llistatEstatCivil= new ArrayList<>();
			for (int i=0; i<llista.size();i++) 
				llistatEstatCivil.add(new SelectItem(llista.get(i).getTextEstatCivil(),llista.get(i).getTextEstatCivil()));
		}
	}
	
	private void carregaUnitatConvivencia() {
		if(llistatUnitatConvivencia==null) {
			List<UnitatConvivenca> llista= EJBFacade.getInstance().cercaUnitatConvivencia();
			llistatUnitatConvivencia= new ArrayList<>();
			for (int i=0; i<llista.size();i++) 
				llistatUnitatConvivencia.add(new SelectItem(llista.get(i).getTextUnitatConvivencia(),llista.get(i).getTextUnitatConvivencia()));
		}
	}
	
	private void carregaProfessions() {
		if(llistatProfessio==null) {
			List<Professio> llista= EJBFacade.getInstance().cercaProfessio();
			llistatProfessio= new ArrayList<>();
			for (int i=0; i<llista.size();i++) 
				llistatProfessio.add(new SelectItem(llista.get(i).getTextProfessio(),llista.get(i).getTextProfessio()));	
		}
	}
	
	private void carregaNivellFormacio() {
		if(llistatNivellFormacio==null) {
			List<NivellFormacio> llista= EJBFacade.getInstance().cercaNivellFormacio();
			llistatNivellFormacio= new ArrayList<>();
			for (int i=0; i<llista.size();i++) 
				llistatNivellFormacio.add(new SelectItem(llista.get(i).getTextNivellFormacio(),llista.get(i).getTextNivellFormacio()));
		}
	}
	
	private void carregaSituacioEconomica() {
		if(llistatSituacioEconomica==null) {
			List<SituacioEconomica> llista= EJBFacade.getInstance().cercaSitiacioEconomica();
			llistatSituacioEconomica= new ArrayList<>();
			for (int i=0; i<llista.size();i++) 
				llistatSituacioEconomica.add(new SelectItem(llista.get(i).getTextSituacioEconomica(),llista.get(i).getTextSituacioEconomica()));
		}
	}
	
	private void carregaServeiAtencio() {
		if(llistatServeiAtencio==null) {
			List<ServeiAtencio> llista= EJBFacade.getInstance().cercaServeiAtencio();
			llistatServeiAtencio= new ArrayList<>();
			for (int i=0; i<llista.size();i++) 
				llistatServeiAtencio.add(new SelectItem(llista.get(i).getTextServeiAtencio(),llista.get(i).getTextServeiAtencio()));
		}
	}
	 
	public void carregaComarques() {
		if (llistaComarques==null) {
			List<Comarques> comarques= EJBFacade.getInstance().cercaComarques();
			llistaComarques = new ArrayList<>();
			llistaComarques.add(new SelectItem("[No consta]","[No consta]"));
			for (int i=0; i<comarques.size();i++) {
				llistaComarques.add(new SelectItem(comarques.get(i).getNomComarca(),comarques.get(i).getNomComarca()));
			}
		}
	}
	
	public void carregaProvincies() {
		if (llistaProvincies==null) {
			List<Provincies> provincies= EJBFacade.getInstance().cercaProvincies("%");
			llistaProvincies = new ArrayList<>();
			llistaProvincies.add(new SelectItem("[No consta]","[No consta]"));
			for (int i=0; i<provincies.size();i++) 
				llistaProvincies.add(new SelectItem(provincies.get(i).getNom(),provincies.get(i).getNom()));
		}
	}
	
	public void carregaCiutats() {
		if(solicitant.getDemarcacio()!=null && !solicitant.getDemarcacio().equals("[No consta]")) {
			List<Ciutats> ciutats= EJBFacade.getInstance().cercaCiutats(solicitant.getDemarcacio() );
			llistaCiutats = new ArrayList<>();
			for (int i=0; i<ciutats.size();i++) 
				llistaCiutats.add(new SelectItem(ciutats.get(i).getNom(),ciutats.get(i).getNom()));
		} else {
			llistaCiutats = new ArrayList<>();
			llistaCiutats.add(new SelectItem("[No consta]","[No consta]"));
		}
	}
	
	private void carregaArees() {
		if(llistatArea==null) {
			llistatArea  = new ArrayList<>();
			List<Area> arees = EJBFacade.getInstance().cercaArees();
			for (int i=0;i<arees.size();i++) 
				llistatArea.add(new SelectItem(arees.get(i).getTextArea(), arees.get(i).getTextArea()));
		}
	}
	
	public void carregaAreaConsulta(String area) {
		 if(area !=null && !area.equals("[No consta]")) {
			List<AreaConsulta> areaConsulta = EJBFacade.getInstance().cercaAreaConsulta(area);
			llistatAreaConsulta = new ArrayList<>();
			llistatAreaConsulta.add(new SelectItem("[No consta]","[No consta]"));
			for(int i=0;i<areaConsulta.size();i++) 
				llistatAreaConsulta.add(new SelectItem(areaConsulta.get(i).getTextAreaConsulta(), areaConsulta.get(i).getTextAreaConsulta()));
		 } else {
			 
	        	llistatAreaConsulta = new ArrayList<>();
	        	llistatAreaConsulta.add(new SelectItem("[No consta]","[No consta]"));
	     }
	}

	public void carregaAreaConsulta() {
		 if(consulta.getArea() !=null && !consulta.getArea().equals("[No consta]")) {
			List<AreaConsulta> areaConsulta = EJBFacade.getInstance().cercaAreaConsulta(consulta.getArea());
			llistatAreaConsulta = new ArrayList<>();
			llistatAreaConsulta.add(new SelectItem("[No consta]","[No consta]"));
			for(int i=0;i<areaConsulta.size();i++) 
				llistatAreaConsulta.add(new SelectItem(areaConsulta.get(i).getTextAreaConsulta(), areaConsulta.get(i).getTextAreaConsulta()));
		 } else {
	        	llistatAreaConsulta = new ArrayList<>();
	        	llistatAreaConsulta.add(new SelectItem("[No consta]","[No consta]"));
	     }
	}
	
	private void carregaAmbitViolencia() {
		if(llistatAmbitViolencia==null) {
			List<AmbitViolencia> violencia= EJBFacade.getInstance().cercaAmbitViolencia();
			llistatAmbitViolencia= new ArrayList<>();
			for (int i=0; i<violencia.size();i++) 
				llistatAmbitViolencia.add(new SelectItem(violencia.get(i).getId(),violencia.get(i).getTextAmbitViolencia()));	
		}
	}
	
	private void carregaViolenciaViscuda() {
		if(llistatViolenciaViscuda==null) {
			List<ViolenciaViscuda> violencia= EJBFacade.getInstance().cercaViolenciaViscuda();
			llistatViolenciaViscuda= new ArrayList<>();
			for (int i=0; i<violencia.size();i++) 
				llistatViolenciaViscuda.add(new SelectItem(violencia.get(i).getId(),violencia.get(i).getTerxtViolenciaViscuda()));	
		}
	}

	public Solicitant getSolicitant() {
		return solicitant;
	}

	public void setSolicitant(Solicitant solicitant) {
		this.solicitant = solicitant;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public List<SelectItem> getLlistatSexe() {
		return llistatSexe;
	}

	public List<SelectItem> getLlistatCanalsEntrada() {
		return llistatCanalsEntrada;
	}

	public List<SelectItem> getLlistatTramitacio() {
		return llistatTramitacio;
	}

	public List<SelectItem> getLlistatSituacioLaboral() {
		return llistatSituacioLaboral;
	}

	public List<SelectItem> getLlistatEstatCivil() {
		return llistatEstatCivil;
	}

	public List<SelectItem> getLlistatUnitatConvivencia() {
		return llistatUnitatConvivencia;
	}

	public List<SelectItem> getLlistatProfessio() {
		return llistatProfessio;
	}

	public List<SelectItem> getLlistatNivellFormacio() {
		return llistatNivellFormacio;
	}

	public List<SelectItem> getLlistatSituacioEconomica() {
		return llistatSituacioEconomica;
	}

	public List<SelectItem> getLlistatServeiAtencio() {
		return llistatServeiAtencio;
	}

	public List<SelectItem> getLlistaPaisos() {
		return llistaPaisos;
	}

	public List<SelectItem> getLlistaProvincies() {
		return llistaProvincies;
	}

	public List<SelectItem> getLlistaCiutats() {
		return llistaCiutats;
	}

	public List<SelectItem> getLlistaComarques() {
		return llistaComarques;
	}

	public List<SelectItem> getLlistatAmbitViolencia() {
		return llistatAmbitViolencia;
	}

	public List<SelectItem> getLlistatViolenciaViscuda() {
		return llistatViolenciaViscuda;
	}

	public List<SelectItem> getLlistatDerivacioInterna() {
		return llistatDerivacioInterna;
	}

	public List<SelectItem> getLlistatDerivacioEstat() {
		return llistatDerivacioEstat;
	}

	public List<SelectItem> getLlistatDerivacioConsellComarcal() {
		return llistatDerivacioConsellComarcal;
	}

	public List<SelectItem> getLlistatDerivacioGeneralitat() {
		return llistatDerivacioGeneralitat;
	}

	public List<SelectItem> getLlistatDerivacioEntitat() {
		return llistatDerivacioEntitat;
	}

	public Consulta getConsultaSeleccionada() {
		return consultaSeleccionada;
	}

	public void setConsultaSeleccionada(Consulta consultaSeleccionada) {
		this.consultaSeleccionada = consultaSeleccionada;
	}


	public List<Integer> getAmbitViolenciaSeleccionats() {
		return ambitViolenciaSeleccionats;
	}

	public void setAmbitViolenciaSeleccionats(List<Integer> ambitViolenciaSeleccionats) {
		this.ambitViolenciaSeleccionats = ambitViolenciaSeleccionats;
	}

	public List<Integer> getViolenciaViscudaSeleccionats() {
		return violenciaViscudaSeleccionats;
	}

	public void setViolenciaViscudaSeleccionats(List<Integer> violenciaViscudaSeleccionats) {
		this.violenciaViscudaSeleccionats = violenciaViscudaSeleccionats;
	}

	public List<SelectItem> getLlistatArea() {
		return llistatArea;
	}

	public void setLlistatArea(List<SelectItem> llistatArea) {
		this.llistatArea = llistatArea;
	}

	public List<SelectItem> getLlistatAreaConsulta() {
		return llistatAreaConsulta;
	}

	public void setLlistatAreaConsulta(List<SelectItem> llistatAreaConsulta) {
		this.llistatAreaConsulta = llistatAreaConsulta;
	}


	


}
