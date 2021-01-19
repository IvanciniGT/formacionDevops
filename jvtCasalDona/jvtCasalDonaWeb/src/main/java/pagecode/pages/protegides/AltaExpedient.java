/**
 * 
 */
package pagecode.pages.protegides;

import pagecode.PageCodeBase;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.component.html.HtmlSelectOneRadio;
import javax.faces.context.FacesContext;
import javax.naming.NamingException;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlCommandButton;
import beans.DadesExpedientBean;
import login.ControlSessio;
import javax.faces.component.html.HtmlForm;

/**
 * @author vtriquell
 *
 */
public class AltaExpedient extends PageCodeBase {

	protected HtmlOutputText lblTitulo;
	protected HtmlOutputText lblInfo;
	protected HtmlOutputText lblNumeroRemesa;
	protected HtmlInputText txtNumeroRemesa;
	protected HtmlInputText txtCorreu;
	protected HtmlOutputText lblNom;
	protected HtmlOutputText lblCognoms;
	protected HtmlInputText txtNom;
	protected HtmlInputText txtCognoms;
	protected HtmlOutputText lblCorreu;
	protected HtmlInputText txtCanalEntrada;
	protected HtmlSelectOneMenu menu1;
	protected HtmlPanelGroup group1;
	protected HtmlInputText txtDemarcacio;
	protected HtmlInputText txtPaisOrigen;
	protected HtmlSelectOneRadio radio1;
	protected HtmlCommandButton button1;
	protected HtmlInputText txtServeiAtencio;
	protected HtmlInputText txtPsicologic;
	protected HtmlInputText txtJuridic;
	protected HtmlOutputText lblAltres;
	protected HtmlInputText txtSense;
	protected HtmlOutputText lblGeneralitat;
	protected HtmlInputText txtGeneralitat;
	protected HtmlInputText txtDerivacioAltres;
	protected HtmlInputText txtDerivacioGeneralitat;
	protected DadesExpedientBean BDadesExpedient;
	protected HtmlInputText txtPoblacio;
	protected ControlSessio ControlSessioUsuari;
	protected HtmlForm form1;
	protected HtmlOutputText lblDerivacionsInternes;
	protected HtmlOutputText lblCanalEntrada;
	protected HtmlOutputText lblDerivacionsExternes;
	protected HtmlOutputText lblHestia;
	protected HtmlOutputText lblDerivacionsEntitats;
	protected HtmlOutputText lblComarca;
	protected HtmlOutputText lblSexe;
	protected HtmlOutputText lblSituacioLaboral;
	protected HtmlOutputText lblCP;
	protected HtmlOutputText lblEstatCivil;
	protected HtmlOutputText lblProfessio;
	protected HtmlOutputText lblBarri;
	protected HtmlOutputText lblUnitatConvivencia;
	protected HtmlOutputText lblTramitacio;
	protected HtmlOutputText lblServeiAtencio;
	protected HtmlSelectOneMenu menuServeiAtencio;
	protected HtmlOutputText lblDerivacions;
	protected HtmlOutputText lblSense;
	protected HtmlOutputText lblPsicologic;
	protected HtmlOutputText lblJuridic;
	protected HtmlOutputText lblDerivacioGeneralitat;
	protected HtmlSelectOneMenu menuGeneralitat;
	protected HtmlOutputText lblDerivacioAltres;
	protected HtmlOutputText lblAreaConsulta;
	protected HtmlSelectOneMenu menuAreaConsulta;
	protected HtmlOutputText msgError;
	protected HtmlOutputText lblDerivacionsEntitats2;
	protected HtmlOutputText getLblTitulo() {
		if (lblTitulo == null) {
			lblTitulo = (HtmlOutputText) findComponentInRoot("lblTitulo");
		}
		return lblTitulo;
	}
	protected HtmlOutputText getLblInfo() {
		if (lblInfo == null) {
			lblInfo = (HtmlOutputText) findComponentInRoot("lblInfo");
		}
		return lblInfo;
	}
	protected HtmlOutputText getLblNumeroRemesa() {
		if (lblNumeroRemesa == null) {
			lblNumeroRemesa = (HtmlOutputText) findComponentInRoot("lblNumeroRemesa");
		}
		return lblNumeroRemesa;
	}
	protected HtmlInputText getTxtNumeroRemesa() {
		if (txtNumeroRemesa == null) {
			txtNumeroRemesa = (HtmlInputText) findComponentInRoot("txtNumeroRemesa");
		}
		return txtNumeroRemesa;
	}
	protected HtmlInputText getTxtCorreu() {
		if (txtCorreu == null) {
			txtCorreu = (HtmlInputText) findComponentInRoot("txtCorreu");
		}
		return txtCorreu;
	}
	protected HtmlOutputText getLblNom() {
		if (lblNom == null) {
			lblNom = (HtmlOutputText) findComponentInRoot("lblNom");
		}
		return lblNom;
	}
	protected HtmlOutputText getLblCognoms() {
		if (lblCognoms == null) {
			lblCognoms = (HtmlOutputText) findComponentInRoot("lblCognoms");
		}
		return lblCognoms;
	}
	protected HtmlInputText getTxtNom() {
		if (txtNom == null) {
			txtNom = (HtmlInputText) findComponentInRoot("txtNom");
		}
		return txtNom;
	}
	protected HtmlInputText getTxtCognoms() {
		if (txtCognoms == null) {
			txtCognoms = (HtmlInputText) findComponentInRoot("txtCognoms");
		}
		return txtCognoms;
	}
	protected HtmlOutputText getLblCorreu() {
		if (lblCorreu == null) {
			lblCorreu = (HtmlOutputText) findComponentInRoot("lblCorreu");
		}
		return lblCorreu;
	}
	protected HtmlInputText getTxtCanalEntrada() {
		if (txtCanalEntrada == null) {
			txtCanalEntrada = (HtmlInputText) findComponentInRoot("txtCanalEntrada");
		}
		return txtCanalEntrada;
	}
	
	protected HtmlSelectOneMenu getMenu1() {
		if (menu1 == null) {
			menu1 = (HtmlSelectOneMenu) findComponentInRoot("menu1");
		}
		return menu1;
	}
	protected HtmlPanelGroup getGroup1() {
		if (group1 == null) {
			group1 = (HtmlPanelGroup) findComponentInRoot("group1");
		}
		return group1;
	}
	protected HtmlInputText getTxtDemarcacio() {
		if (txtDemarcacio == null) {
			txtDemarcacio = (HtmlInputText) findComponentInRoot("txtDemarcacio");
		}
		return txtDemarcacio;
	}
	protected HtmlInputText getTxtPaisOrigen() {
		if (txtPaisOrigen == null) {
			txtPaisOrigen = (HtmlInputText) findComponentInRoot("txtPaisOrigen");
		}
		return txtPaisOrigen;
	}
	protected HtmlSelectOneRadio getRadio1() {
		if (radio1 == null) {
			radio1 = (HtmlSelectOneRadio) findComponentInRoot("radio1");
		}
		return radio1;
	}
	protected HtmlCommandButton getButton1() {
		if (button1 == null) {
			button1 = (HtmlCommandButton) findComponentInRoot("button1");
		}
		return button1;
	}
	protected HtmlInputText getTxtServeiAtencio() {
		if (txtServeiAtencio == null) {
			txtServeiAtencio = (HtmlInputText) findComponentInRoot("txtServeiAtencio");
		}
		return txtServeiAtencio;
	}
	protected HtmlInputText getTxtPsicologic() {
		if (txtPsicologic == null) {
			txtPsicologic = (HtmlInputText) findComponentInRoot("txtPsicologic");
		}
		return txtPsicologic;
	}
	protected HtmlInputText getTxtJuridic() {
		if (txtJuridic == null) {
			txtJuridic = (HtmlInputText) findComponentInRoot("txtJuridic");
		}
		return txtJuridic;
	}
	protected HtmlOutputText getLblAltres() {
		if (lblAltres == null) {
			lblAltres = (HtmlOutputText) findComponentInRoot("lblAltres");
		}
		return lblAltres;
	}
	protected HtmlInputText getTxtSense() {
		if (txtSense == null) {
			txtSense = (HtmlInputText) findComponentInRoot("txtSense");
		}
		return txtSense;
	}
	protected HtmlOutputText getLblGeneralitat() {
		if (lblGeneralitat == null) {
			lblGeneralitat = (HtmlOutputText) findComponentInRoot("lblGeneralitat");
		}
		return lblGeneralitat;
	}
	protected HtmlInputText getTxtGeneralitat() {
		if (txtGeneralitat == null) {
			txtGeneralitat = (HtmlInputText) findComponentInRoot("txtGeneralitat");
		}
		return txtGeneralitat;
	}
	protected HtmlInputText getTxtDerivacioAltres() {
		if (txtDerivacioAltres == null) {
			txtDerivacioAltres = (HtmlInputText) findComponentInRoot("txtDerivacioAltres");
		}
		return txtDerivacioAltres;
	}
	protected HtmlInputText getTxtDerivacioGeneralitat() {
		if (txtDerivacioGeneralitat == null) {
			txtDerivacioGeneralitat = (HtmlInputText) findComponentInRoot("txtDerivacioGeneralitat");
		}
		return txtDerivacioGeneralitat;
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
	
	protected HtmlInputText getTxtPoblacio() {
		if (txtPoblacio == null) {
			txtPoblacio = (HtmlInputText) findComponentInRoot("txtPoblacio");
		}
		return txtPoblacio;
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
	protected HtmlForm getForm1() {
		if (form1 == null) {
			form1 = (HtmlForm) findComponentInRoot("form1");
		}
		return form1;
	}
	protected HtmlOutputText getLblDerivacionsInternes() {
		if (lblDerivacionsInternes == null) {
			lblDerivacionsInternes = (HtmlOutputText) findComponentInRoot("lblDerivacionsInternes");
		}
		return lblDerivacionsInternes;
	}
	protected HtmlOutputText getLblCanalEntrada() {
		if (lblCanalEntrada == null) {
			lblCanalEntrada = (HtmlOutputText) findComponentInRoot("lblCanalEntrada");
		}
		return lblCanalEntrada;
	}
	protected HtmlOutputText getLblDerivacionsExternes() {
		if (lblDerivacionsExternes == null) {
			lblDerivacionsExternes = (HtmlOutputText) findComponentInRoot("lblDerivacionsExternes");
		}
		return lblDerivacionsExternes;
	}
	protected HtmlOutputText getLblHestia() {
		if (lblHestia == null) {
			lblHestia = (HtmlOutputText) findComponentInRoot("lblHestia");
		}
		return lblHestia;
	}
	protected HtmlOutputText getLblDerivacionsEntitats() {
		if (lblDerivacionsEntitats == null) {
			lblDerivacionsEntitats = (HtmlOutputText) findComponentInRoot("lblDerivacionsEntitats");
		}
		return lblDerivacionsEntitats;
	}
	protected HtmlOutputText getLblComarca() {
		if (lblComarca == null) {
			lblComarca = (HtmlOutputText) findComponentInRoot("lblComarca");
		}
		return lblComarca;
	}
	protected HtmlOutputText getLblSexe() {
		if (lblSexe == null) {
			lblSexe = (HtmlOutputText) findComponentInRoot("lblSexe");
		}
		return lblSexe;
	}
	protected HtmlOutputText getLblSituacioLaboral() {
		if (lblSituacioLaboral == null) {
			lblSituacioLaboral = (HtmlOutputText) findComponentInRoot("lblSituacioLaboral");
		}
		return lblSituacioLaboral;
	}
	protected HtmlOutputText getLblCP() {
		if (lblCP == null) {
			lblCP = (HtmlOutputText) findComponentInRoot("lblCP");
		}
		return lblCP;
	}
	protected HtmlOutputText getLblEstatCivil() {
		if (lblEstatCivil == null) {
			lblEstatCivil = (HtmlOutputText) findComponentInRoot("lblEstatCivil");
		}
		return lblEstatCivil;
	}
	protected HtmlOutputText getLblProfessio() {
		if (lblProfessio == null) {
			lblProfessio = (HtmlOutputText) findComponentInRoot("lblProfessio");
		}
		return lblProfessio;
	}
	protected HtmlOutputText getLblBarri() {
		if (lblBarri == null) {
			lblBarri = (HtmlOutputText) findComponentInRoot("lblBarri");
		}
		return lblBarri;
	}
	protected HtmlOutputText getLblUnitatConvivencia() {
		if (lblUnitatConvivencia == null) {
			lblUnitatConvivencia = (HtmlOutputText) findComponentInRoot("lblUnitatConvivencia");
		}
		return lblUnitatConvivencia;
	}
	protected HtmlOutputText getLblTramitacio() {
		if (lblTramitacio == null) {
			lblTramitacio = (HtmlOutputText) findComponentInRoot("lblTramitacio");
		}
		return lblTramitacio;
	}
	protected HtmlOutputText getLblServeiAtencio() {
		if (lblServeiAtencio == null) {
			lblServeiAtencio = (HtmlOutputText) findComponentInRoot("lblServeiAtencio");
		}
		return lblServeiAtencio;
	}
	protected HtmlSelectOneMenu getMenuServeiAtencio() {
		if (menuServeiAtencio == null) {
			menuServeiAtencio = (HtmlSelectOneMenu) findComponentInRoot("menuServeiAtencio");
		}
		return menuServeiAtencio;
	}
	protected HtmlOutputText getLblDerivacions() {
		if (lblDerivacions == null) {
			lblDerivacions = (HtmlOutputText) findComponentInRoot("lblDerivacions");
		}
		return lblDerivacions;
	}
	protected HtmlOutputText getLblSense() {
		if (lblSense == null) {
			lblSense = (HtmlOutputText) findComponentInRoot("lblSense");
		}
		return lblSense;
	}
	protected HtmlOutputText getLblPsicologic() {
		if (lblPsicologic == null) {
			lblPsicologic = (HtmlOutputText) findComponentInRoot("lblPsicologic");
		}
		return lblPsicologic;
	}
	protected HtmlOutputText getLblJuridic() {
		if (lblJuridic == null) {
			lblJuridic = (HtmlOutputText) findComponentInRoot("lblJuridic");
		}
		return lblJuridic;
	}
	protected HtmlOutputText getLblDerivacioGeneralitat() {
		if (lblDerivacioGeneralitat == null) {
			lblDerivacioGeneralitat = (HtmlOutputText) findComponentInRoot("lblDerivacioGeneralitat");
		}
		return lblDerivacioGeneralitat;
	}
	protected HtmlSelectOneMenu getMenuGeneralitat() {
		if (menuGeneralitat == null) {
			menuGeneralitat = (HtmlSelectOneMenu) findComponentInRoot("menuGeneralitat");
		}
		return menuGeneralitat;
	}
	protected HtmlOutputText getLblDerivacioAltres() {
		if (lblDerivacioAltres == null) {
			lblDerivacioAltres = (HtmlOutputText) findComponentInRoot("lblDerivacioAltres");
		}
		return lblDerivacioAltres;
	}
	protected HtmlOutputText getLblAreaConsulta() {
		if (lblAreaConsulta == null) {
			lblAreaConsulta = (HtmlOutputText) findComponentInRoot("lblAreaConsulta");
		}
		return lblAreaConsulta;
	}
	protected HtmlSelectOneMenu getMenuAreaConsulta() {
		if (menuAreaConsulta == null) {
			menuAreaConsulta = (HtmlSelectOneMenu) findComponentInRoot("menuAreaConsulta");
		}
		return menuAreaConsulta;
	}
	protected HtmlOutputText getMsgError() {
		if (msgError == null) {
			msgError = (HtmlOutputText) findComponentInRoot("msgError");
		}
		return msgError;
	}
	protected HtmlOutputText getLblDerivacionsEntitats2() {
		if (lblDerivacionsEntitats2 == null) {
			lblDerivacionsEntitats2 = (HtmlOutputText) findComponentInRoot("lblDerivacionsEntitats2");
		}
		return lblDerivacionsEntitats2;
	}


	protected void setBDadesExpedient(DadesExpedientBean BDadesExpedient) {
		this.BDadesExpedient = BDadesExpedient;
	}
	
	
	/*
	 * 
	 * ***********************
	 * METODES D'ACCIONS
	 * ************************
	 * 
	 */
	
	
	public String btnAltaExpedientAction() {
		// TODO: Return an outcome that corresponds to a navigation rules
		try {
			this.getBDadesExpedient().altaExpedient(this.getControlSessioUsuari().getSessioUsuari().getUsuari());
			this.getBDadesExpedient().setOrigenNavegacio("alta");

			//this.getBDadesExpedient().seleccionaExpedient(this.getBDadesExpedient().getSolicitant(),this.getControlSessioUsuari().getSessioUsuari().getUsuari());
			//FacesContext.getCurrentInstance().getExternalContext().redirect("detallsExpedient.xhtml");
	       
		} catch (NamingException e) {
			// TODO Bloque catch generado automaticamente
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alta Expedient", "S'ha produït un error en l'alta");
	        FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return "success";
	}

	public String btnNetejaAction() {
		// TODO: Return an outcome that corresponds to a navigation rule
		this.getBDadesExpedient().inicialitza();
		carregaDadesPoblacio();
		return "neteja";
	}
	
	
	public void onPageLoadAction() {
		this.getBDadesExpedient().inicialitza();
		carregaDadesPoblacio();
	}
	
	
	private void carregaDadesPoblacio() {
		this.getBDadesExpedient().getSolicitant().setComarca("Segrià");
		this.getBDadesExpedient().getSolicitant().setPaisorigen("ESPA�A");
		this.getBDadesExpedient().getSolicitant().setDemarcacio("LLEIDA");
		this.getBDadesExpedient().carregaCiutats();
		this.getBDadesExpedient().getSolicitant().setPoblacio("LLEIDA");
	}
	
	public void menuSeleccionaProvincia() {
		this.getBDadesExpedient().carregaCiutats();
	}
	
	
	public void menuSeleccionaArea() {
		this.getBDadesExpedient().carregaAreaConsulta();
	}
}