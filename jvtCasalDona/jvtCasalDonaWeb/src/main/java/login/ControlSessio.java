package login;


import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name="ControlSessioUsuari")
@SessionScoped
public class ControlSessio implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6943699841490737499L;
	
	private SessioUsuari sessioUsuari;
	private Date ultimaConnexio;
	


	public ControlSessio() {
		// TODO Apendice de constructor generado automaticamente
		ultimaConnexio=null;
		sessioUsuari=null;
	}
	
	
	
	public Date getUltimaConnexio() {
		return ultimaConnexio;
	}


	public SessioUsuari getSessioUsuari() {
		return sessioUsuari;
	}


	public void setSessioUsuari(SessioUsuari sessioUsuari) {
		this.sessioUsuari = sessioUsuari;
	}


	public void setUltimaConnexio(Date ultimaConnexio) {
		this.ultimaConnexio = ultimaConnexio;
	}


	

}
