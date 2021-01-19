package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nivellFormacio database table.
 * 
 */
@Entity
@Table(schema="siadConstants", name="nivellFormacio")
@NamedQuery(name="NivellFormacio.findAll", query="SELECT n FROM NivellFormacio n order by n.textNivellFormacio")
public class NivellFormacio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textNivellFormacio;

	public NivellFormacio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextNivellFormacio() {
		return this.textNivellFormacio;
	}

	public void setTextNivellFormacio(String textNivellFormacio) {
		this.textNivellFormacio = textNivellFormacio;
	}

}