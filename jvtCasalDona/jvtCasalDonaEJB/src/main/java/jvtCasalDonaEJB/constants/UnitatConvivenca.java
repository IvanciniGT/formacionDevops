package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the unitatConvivenca database table.
 * 
 */
@Entity
@Table(schema="siadConstants", name="unitatConvivenca")
@NamedQuery(name="UnitatConvivenca.findAll", query="SELECT u FROM UnitatConvivenca u order by u.textUnitatConvivencia")
public class UnitatConvivenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textUnitatConvivencia;

	public UnitatConvivenca() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextUnitatConvivencia() {
		return this.textUnitatConvivencia;
	}

	public void setTextUnitatConvivencia(String textUnitatConvivencia) {
		this.textUnitatConvivencia = textUnitatConvivencia;
	}

}