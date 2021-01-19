/**
 * 
 */
package pagecode.pages.protegides;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputFormat;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.NamingException;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import beans.DadesCercadorBean;
import beans.DadesExpedientBean;
import jvtCasalDonaEJB.Consulta;
import login.ControlSessio;
import pagecode.PageCodeBase;
import javax.faces.component.html.HtmlPanelGroup;

/**
 * @author vtriquell
 *
 */
public class DetallsExpedient extends PageCodeBase {

	protected DadesExpedientBean BDadesExpedient;
	protected HtmlForm form1;
	protected HtmlOutputFormat format1;
	protected HtmlOutputFormat lblTitol;
	protected DadesCercadorBean BDadesCercador;
	protected ControlSessio ControlSessioUsuari;
	protected HtmlPanelGroup panelNovaConsulta;
	/** 
	* @managed-bean true
	*/
	protected DadesExpedientBean getBDadesExpedient() {
		if (BDadesExpedient == null) {
			BDadesExpedient = (DadesExpedientBean) getManagedBean("BDadesExpedient");
		}
		return BDadesExpedient;
	}

	/** 
	* @managed-bean true
	*/
	protected void setBDadesExpedient(DadesExpedientBean BDadesExpedient) {
		this.BDadesExpedient = BDadesExpedient;
	}

	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlOutputFormat getFormat1() {
		if (format1 == null) {
			format1 = (HtmlOutputFormat) findComponentInRoot("format1");
		}
		return format1;
	}

	protected HtmlOutputFormat getLblTitol() {
		if (lblTitol == null) {
			lblTitol = (HtmlOutputFormat) findComponentInRoot("lblTitol");
		}
		return lblTitol;
	}

	

	/** 
	* @managed-bean true
	*/
	protected DadesCercadorBean getBDadesCercador() {
		if (BDadesCercador == null) {
			BDadesCercador = (DadesCercadorBean) getManagedBean("BDadesCercador");
		}
		return BDadesCercador;
	}

	/** 
	* @managed-bean true
	*/
	protected void setBDadesCercador(DadesCercadorBean BDadesCercador) {
		this.BDadesCercador = BDadesCercador;
	}

	/** 
	* @managed-bean true
	*/
	protected ControlSessio getControlSessioUsuari() {
		if (ControlSessioUsuari == null) {
			ControlSessioUsuari = (ControlSessio) getManagedBean("ControlSessioUsuari");
		}
		return ControlSessioUsuari;
	}

	/** 
	* @managed-bean true
	*/
	protected void setControlSessioUsuari(ControlSessio ControlSessioUsuari) {
		this.ControlSessioUsuari = ControlSessioUsuari;
	}

	
	/*
	 * 
	 * ***********************
	 * M�TODES D'ACCIONS
	 * ************************
	 * 
	 */
	
	public void onPageLoadAction() {
		if (this.getBDadesExpedient().getOrigenNavegacio().equals("alta")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta expedient", "L'alta s'ha realitzat correctament");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	}
	
	public String btnAltaConsultaAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "success";
		this.getBDadesExpedient().altaConsulta(this.getControlSessioUsuari().getSessioUsuari().getUsuari());
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alta consulta", "L'alta s'ha realitzat correctament");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return "success";
	}

	

	public String btnCancelaAltaConsultaAction() {
		this.getBDadesExpedient().inicialitzaConsultes();

		return "success";
	}

	public String btnEliminaConsultaAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "success";
		// return "back";
		this.getBDadesExpedient().eliminaConsulta(this.getControlSessioUsuari().getSessioUsuari().getUsuari());
		
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baixa Consulta", "La baixa s'ha realitzat correctament");
        FacesContext.getCurrentInstance().addMessage(null, message);
		
		return "success";
	}
	

	public void onRowEditaIniciAction(RowEditEvent<Consulta> event) {
		this.getBDadesExpedient().carregaAreaConsulta(event.getObject().getArea());
		PrimeFaces.current().ajax().update("form1:dataTableConsultes:menuAreaConsultaTaula");

	}
	
	public void onRowEditaConsultaAction(RowEditEvent<Consulta> event) {
		this.getBDadesExpedient().modificaConsulta(event.getObject(), this.getControlSessioUsuari().getSessioUsuari().getUsuari());
		FacesMessage msg = new FacesMessage("Edici� consulta", "Les dades s'han actualitzat correctament");

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	

	public void onRowCancelaConsultaAction(RowEditEvent<Consulta> event) {
		FacesMessage msg = new FacesMessage("Edici� cancel�lada", "Les dades no s'han modificat");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	   

	public String btnDesferAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "success";
		// return "selecciona";
		PrimeFaces.current().resetInputs("form1");

		return "success";
	}
	
	public String btnEnreraAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "success";
		//this.getBDadesExpedient().inicialitza();
		return this.getBDadesExpedient().getOrigenNavegacio();
	}
	

	public String btnModificaAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "success";
		// return "back";
		try {
			this.getBDadesExpedient().modificaExpedient(this.getControlSessioUsuari().getSessioUsuari().getUsuari());
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Edici� expedient", "Els canvis s'han desat correctament");
	        FacesContext.getCurrentInstance().addMessage(null, message);
			
		} catch (NamingException e) {
			// TODO Bloque catch generado autom�ticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Edici� expedient", "S'ha produ�t un error al desar els canvis.");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return "success";
	}

	public String btnEliminaExpedientAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "success";
		// return "back";
		
		String msg=this.getBDadesExpedient().eliminaExpedient(this.getControlSessioUsuari().getSessioUsuari().getUsuari());
		String resultat="success";
		if (msg.equals("")) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Baixa expedient", "La baixa s'ha realitzat correctament");
	        FacesContext.getCurrentInstance().addMessage(null, message);
			
			this.getBDadesCercador().cercaExpedients(this.getControlSessioUsuari().getSessioUsuari().getUsuari());
			resultat=this.getBDadesExpedient().getOrigenNavegacio();
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Baixa expedient", msg);
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
	        
		return resultat;
	}
	

	public void seleccionaAreaEdicio(ValueChangeEvent event){
		// Escribir aqui el codigo Java que va a manejar el suceso de cambio de valor	
		// Nota: valueChangeEvent contiene valores nuevos y viejos

	    //this.getBDadesExpedient().getConsulta().setArea(event.getNewValue().toString());
	    this.getBDadesExpedient().carregaAreaConsulta(event.getNewValue().toString());
	}
	
	public void seleccionaArea(){
		// Escribir aqui el codigo Java que va a manejar el suceso de cambio de valor	
		// Nota: valueChangeEvent contiene valores nuevos y viejos

	    //this.getBDadesExpedient().getConsulta().setArea(event.getNewValue().toString());
	    this.getBDadesExpedient().carregaAreaConsulta();
	}

	
	public void menuSeleccionaProvincia() {
		// Escribir aqui el codigo Java que va a manejar el suceso de cambio de valor	
		// Nota: valueChangeEvent contiene valores nuevos y viejos

		this.getBDadesExpedient().carregaCiutats();
	}

	protected HtmlPanelGroup getPanelNovaConsulta() {
		if (panelNovaConsulta == null) {
			panelNovaConsulta = (HtmlPanelGroup) findComponentInRoot("panelNovaConsulta");
		}
		return panelNovaConsulta;
	}
	
}