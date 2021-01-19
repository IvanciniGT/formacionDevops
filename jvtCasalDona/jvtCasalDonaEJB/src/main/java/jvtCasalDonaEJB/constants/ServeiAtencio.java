package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the serveiAtencio database table.
 * 
 */
@Entity
@Table(schema="siadConstants", name="serveiAtencio")
@NamedQuery(name="ServeiAtencio.findAll", query="SELECT s FROM ServeiAtencio s order by s.textServeiAtencio")
public class ServeiAtencio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textServeiAtencio;

	public ServeiAtencio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextServeiAtencio() {
		return this.textServeiAtencio;
	}

	public void setTextServeiAtencio(String textServeiAtencio) {
		this.textServeiAtencio = textServeiAtencio;
	}

}