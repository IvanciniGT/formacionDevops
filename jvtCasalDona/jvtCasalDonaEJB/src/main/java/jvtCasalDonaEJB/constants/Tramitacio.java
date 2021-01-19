package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tramitacio database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="tramitacio")
@NamedQuery(name="Tramitacio.findAll", query="SELECT t FROM Tramitacio t order by t.textTramitacio")
public class Tramitacio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textTramitacio;

	public Tramitacio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextTramitacio() {
		return this.textTramitacio;
	}

	public void setTextTramitacio(String textTramitacio) {
		this.textTramitacio = textTramitacio;
	}

}