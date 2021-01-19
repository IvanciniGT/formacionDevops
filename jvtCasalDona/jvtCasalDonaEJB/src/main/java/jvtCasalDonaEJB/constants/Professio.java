package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the professio database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="professio")
@NamedQuery(name="Professio.findAll", query="SELECT p FROM Professio p order by p.textProfessio")
public class Professio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textProfessio;

	public Professio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextProfessio() {
		return this.textProfessio;
	}

	public void setTextProfessio(String textProfessio) {
		this.textProfessio = textProfessio;
	}

}