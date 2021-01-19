package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the estatCivil database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="estatCivil")
@NamedQuery(name="EstatCivil.findAll", query="SELECT e FROM EstatCivil e order by e.textEstatCivil")
public class EstatCivil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textEstatCivil;

	public EstatCivil() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextEstatCivil() {
		return this.textEstatCivil;
	}

	public void setTextEstatCivil(String textEstatCivil) {
		this.textEstatCivil = textEstatCivil;
	}

}