package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the derivacioInterna database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="derivacioInterna")
@NamedQuery(name="DerivacioInterna.findAll", query="SELECT d FROM DerivacioInterna d order by d.textDereivacioInterna")
public class DerivacioInterna implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textDereivacioInterna;

	public DerivacioInterna() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextDereivacioInterna() {
		return this.textDereivacioInterna;
	}

	public void setTextDereivacioInterna(String textDereivacioInterna) {
		this.textDereivacioInterna = textDereivacioInterna;
	}

}