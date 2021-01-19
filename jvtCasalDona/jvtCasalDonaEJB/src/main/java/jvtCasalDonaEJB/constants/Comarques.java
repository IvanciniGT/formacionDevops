package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the comarques database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="comarques")
@NamedQuery(name="Comarques.totes", query="SELECT c FROM Comarques c order by c.nomComarca")
public class Comarques implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String nomComarca;

	public Comarques() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomComarca() {
		return this.nomComarca;
	}

	public void setNomComarca(String nomComarca) {
		this.nomComarca = nomComarca;
	}

}