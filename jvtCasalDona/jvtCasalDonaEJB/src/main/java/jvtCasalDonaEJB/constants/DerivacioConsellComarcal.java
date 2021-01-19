package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the derivacioConsellComarcal database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="derivacioConsellComarcal")
@NamedQuery(name="DerivacioConsellComarcal.findAll", query="SELECT d FROM DerivacioConsellComarcal d order by d.textDerivacioConsellComarcal")
public class DerivacioConsellComarcal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textDerivacioConsellComarcal;

	public DerivacioConsellComarcal() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextDerivacioConsellComarcal() {
		return this.textDerivacioConsellComarcal;
	}

	public void setTextDerivacioConsellComarcal(String textDerivacioConsellComarcal) {
		this.textDerivacioConsellComarcal = textDerivacioConsellComarcal;
	}

}