package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the derivacioGeneralitat database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="derivacioGeneralitat")
@NamedQuery(name="DerivacioGeneralitat.findAll", query="SELECT d FROM DerivacioGeneralitat d order by d.textDerivacioGeneralitat")
public class DerivacioGeneralitat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textDerivacioGeneralitat;

	public DerivacioGeneralitat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextDerivacioGeneralitat() {
		return this.textDerivacioGeneralitat;
	}

	public void setTextDerivacioGeneralitat(String textDerivacioGeneralitat) {
		this.textDerivacioGeneralitat = textDerivacioGeneralitat;
	}

}