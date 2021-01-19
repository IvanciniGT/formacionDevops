package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the derivacioEstat database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="derivacioEstat")
@NamedQuery(name="DerivacioEstat.findAll", query="SELECT d FROM DerivacioEstat d order by d.textDerivacioEstat")
public class DerivacioEstat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textDerivacioEstat;

	public DerivacioEstat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextDerivacioEstat() {
		return this.textDerivacioEstat;
	}

	public void setTextDerivacioEstat(String textDerivacioEstat) {
		this.textDerivacioEstat = textDerivacioEstat;
	}

}