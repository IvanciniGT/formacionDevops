package beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jvtCasalDonaEJB.Solicitant;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="BDadesCercador")
@SessionScoped
public class DadesCercadorBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3624682075352228646L;
	
	private Date dataInici, dataFi, dataNaix;
	private String nomSolicitant, numeroExpedient, anyExpedient;
	
	
	private boolean panelLlistaSolicitants;
	private List<Solicitant> llistaSolicitants;
	
	private Solicitant solicitantSeleccionat;
	

	
	public Solicitant getSolicitantSeleccionat() {
		return solicitantSeleccionat;
	}

	public void setSolicitantSeleccionat(Solicitant solicitantSeleccionat) {
		this.solicitantSeleccionat = solicitantSeleccionat;
	}

	public DadesCercadorBean() {
		// TODO Apendice de constructor generado automaticamente
		inicialitza();
	}
	
	@SuppressWarnings("deprecation")
	public void inicialitza() {
		dataInici = new Date();
		dataInici.setDate(1);
		dataFi = new Date();
		dataFi.setDate(dataFi.getDate()+1);
		dataNaix= null;
		nomSolicitant="";
		numeroExpedient="";
		anyExpedient="";
		panelLlistaSolicitants=false;
		llistaSolicitants=null;
		solicitantSeleccionat=null;
	}
	
	@SuppressWarnings("deprecation")
	public void cercaExpedients(String usuari) {
		llistaSolicitants=null;
		if (!numeroExpedient.equals("") && !anyExpedient.equals("")) {
			llistaSolicitants=EJBFacade.getInstance().cercaExpedients(Integer.valueOf(numeroExpedient), Integer.valueOf(anyExpedient), usuari);
			panelLlistaSolicitants=true;
		} else {
			String sNomSolicitant="%";
			if (!nomSolicitant.equals(""))
				sNomSolicitant="%"+nomSolicitant+"%";
			
			Date dataNaixInici=null;
			Date dataNaixFi=null;
			if(dataNaix!=null) {
				dataNaixInici=dataNaix;
				dataNaixFi=dataNaix;
			}
			Date dataIniciConsulta= new Date();
			dataIniciConsulta.setYear(dataIniciConsulta.getYear()-200);
			if (dataInici!=null)
				dataIniciConsulta=dataInici;
			
			Date dataFiConsulta= new Date();
			if (dataFi!=null)
				dataFiConsulta=dataFi;
			
			
			llistaSolicitants=EJBFacade.getInstance().cercaExpedients(dataNaixInici, dataNaixFi, dataIniciConsulta, dataFiConsulta, sNomSolicitant, usuari);
			panelLlistaSolicitants=true;
		}
		
	}

	public Date getDataInici() {
		return dataInici;
	}

	public void setDataInici(Date dataInici) {
		this.dataInici = dataInici;
	}

	public Date getDataFi() {
		return dataFi;
	}

	public void setDataFi(Date dataFi) {
		this.dataFi = dataFi;
	}

	public String getNomSolicitant() {
		return nomSolicitant;
	}

	public void setNomSolicitant(String nomSolicitant) {
		this.nomSolicitant = nomSolicitant;
	}

	public boolean isPanelLlistaSolicitants() {
		return panelLlistaSolicitants;
	}

	public void setPanelLlistaSolicitants(boolean panelLlistaSolicitants) {
		this.panelLlistaSolicitants = panelLlistaSolicitants;
	}

	public List<Solicitant> getLlistaSolicitants() {
		return llistaSolicitants;
	}

	public void setLlistaSolicitants(List<Solicitant> llistaSolicitants) {
		this.llistaSolicitants = llistaSolicitants;
	}

	public String getNumeroExpedient() {
		return numeroExpedient;
	}

	public void setNumeroExpedient(String numeroExpedient) {
		this.numeroExpedient = numeroExpedient;
	}

	public String getAnyExpedient() {
		return anyExpedient;
	}

	public void setAnyExpedient(String anyExpedient) {
		this.anyExpedient = anyExpedient;
	}

	public Date getDataNaix() {
		return dataNaix;
	}

	public void setDataNaix(Date dataNaix) {
		this.dataNaix = dataNaix;
	}

	
}
