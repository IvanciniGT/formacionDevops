/**
 * 
 */
package pagecode.pages.protegides;

import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputFormat;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;

import beans.DadesCercadorBean;
import beans.DadesExpedientBean;
import login.ControlSessio;
import pagecode.PageCodeBase;

/**
 * @author vtriquell
 *
 */
public class Cercador extends PageCodeBase {

	protected HtmlForm form1;
	protected DadesCercadorBean bDadesCercador;
	protected HtmlPanelGroup group1;
	protected HtmlOutputText nexpedient1;
	protected DadesExpedientBean BDadesExpedient;
	protected ControlSessio ControlSessioUsuari;
	protected HtmlPanelGrid grid1;
	protected HtmlOutputText lblSeparador;
	protected HtmlOutputFormat numeroExpedient;
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	/** 
	* @managed-bean true
	*/
	protected DadesCercadorBean getBDadesCercador() {
		if (bDadesCercador == null) {
			bDadesCercador = (DadesCercadorBean) getManagedBean("BDadesCercador");
		}
		return bDadesCercador;
	}
	/** 
	* @managed-bean true
	*/
	protected void setBDadesCercador(DadesCercadorBean bDadesCercador) {
		this.bDadesCercador = bDadesCercador;
	}
	protected HtmlPanelGroup getGroup1() {
		if (group1 == null) {
			group1 = (HtmlPanelGroup) findComponentInRoot("group1");
		}
		return group1;
	}
	protected HtmlOutputText getNexpedient1() {
		if (nexpedient1 == null) {
			nexpedient1 = (HtmlOutputText) findComponentInRoot("nexpedient1");
		}
		return nexpedient1;
	}
	
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
	protected HtmlPanelGrid getGrid1() {
		if (grid1 == null) {
			grid1 = (HtmlPanelGrid) findComponentInRoot("grid1");
		}
		return grid1;
	}
	protected HtmlOutputText getLblSeparador() {
		if (lblSeparador == null) {
			lblSeparador = (HtmlOutputText) findComponentInRoot("lblSeparador");
		}
		return lblSeparador;
	}
	

	
	/*
	 * 
	 * ***********************
	 * METODES D'ACCIONS
	 * ************************
	 * 
	 */
	
	
	public String btnNetejaAction() {
		// This is java code that runs when this action method is invoked
	
		// TODO: Return an outcome that corresponds to a navigation rule
		// return "success";
		// return "selecciona";
		//PrimeFaces.current().resetInputs("form1");
		this.getBDadesCercador().inicialitza();
		return "success";
	}
	
	public String btnCercaExpedientsAction() {
		// This is java code that runs when this action method is invoked
		// TODO: Return an outcome that corresponds to a navigation rule
		this.getBDadesCercador().cercaExpedients(this.getControlSessioUsuari().getSessioUsuari().getUsuari());
		return "success";
	}
	
	
	public String btnSeleccionaExpedientAction() {
					
			this.getBDadesExpedient().setOrigenNavegacio("cercador");		
			this.getBDadesExpedient().seleccionaExpedient(this.getBDadesCercador().getSolicitantSeleccionat(),this.getControlSessioUsuari().getSessioUsuari().getUsuari());
			/*try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("detallsExpedient.xhtml");
			} catch (IOException e) {
				// TODO Bloque catch generado automaticamente
				e.printStackTrace();
			}*/
		
		return "selecciona";
	
	}
	protected HtmlOutputFormat getNumeroExpedient() {
		if (numeroExpedient == null) {
			numeroExpedient = (HtmlOutputFormat) findComponentInRoot("numeroExpedient");
		}
		return numeroExpedient;
	}
	
}