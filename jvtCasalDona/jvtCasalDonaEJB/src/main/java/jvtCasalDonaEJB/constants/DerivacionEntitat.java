package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the derivacionEntitat database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="derivacionEntitat")
@NamedQuery(name="DerivacionEntitat.findAll", query="SELECT d FROM DerivacionEntitat d order by d.textDerivacioEntitat")
public class DerivacionEntitat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textDerivacioEntitat;

	public DerivacionEntitat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextDerivacioEntitat() {
		return this.textDerivacioEntitat;
	}

	public void setTextDerivacioEntitat(String textDerivacioEntitat) {
		this.textDerivacioEntitat = textDerivacioEntitat;
	}

}