/**
 * 
 */
package pagecode.pages.protegides;

import pagecode.PageCodeBase;
import login.ControlSessio;
import javax.faces.component.html.HtmlForm;
import javax.faces.component.html.HtmlOutputFormat;

import beans.DadesExpedientBean;

/**
 * @author vtriquell
 *
 */
public class Principal extends PageCodeBase {

	protected ControlSessio ControlSessioUsuari;
	protected HtmlForm form1;
	protected HtmlOutputFormat lblBenvingut;
	protected HtmlOutputFormat lblConnexio;
	protected DadesExpedientBean BDadesExpedient;
	
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
	
	/** 
	* @managed-bean true
	*/
	protected DadesExpedientBean getBDadesExpedient() {
		if (BDadesExpedient == null) {
			BDadesExpedient = (DadesExpedientBean) getManagedBean("BDadesExpedient");
		}
		return BDadesExpedient;
	}
	
	
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}

	protected HtmlOutputFormat getLblBenvingut() {
		if (lblBenvingut == null) {
			lblBenvingut = (HtmlOutputFormat) findComponentInRoot("lblBenvingut");
		}
		return lblBenvingut;
	}

	protected HtmlOutputFormat getLblConnexio() {
		if (lblConnexio == null) {
			lblConnexio = (HtmlOutputFormat) findComponentInRoot("lblConnexio");
		}
		return lblConnexio;
	}

	public void onPageLoad() {
		this.getBDadesExpedient().inicialitza();


	}

}