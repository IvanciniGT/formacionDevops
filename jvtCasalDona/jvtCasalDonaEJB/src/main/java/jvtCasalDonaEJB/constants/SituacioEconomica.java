package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the situacioEconomica database table.
 * 
 */
@Entity
@Table(schema="siadConstants", name="situacioEconomica")
@NamedQuery(name="SituacioEconomica.findAll", query="SELECT s FROM SituacioEconomica s order by s.textSituacioEconomica")
public class SituacioEconomica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textSituacioEconomica;

	public SituacioEconomica() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextSituacioEconomica() {
		return this.textSituacioEconomica;
	}

	public void setTextSituacioEconomica(String textSituacioEconomica) {
		this.textSituacioEconomica = textSituacioEconomica;
	}

}