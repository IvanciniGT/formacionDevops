package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the situacioLaboral database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="situacioLaboral")
@NamedQuery(name="SituacioLaboral.findAll", query="SELECT s FROM SituacioLaboral s order by s.textSituacioLaboral")
public class SituacioLaboral implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textSituacioLaboral;

	public SituacioLaboral() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextSituacioLaboral() {
		return this.textSituacioLaboral;
	}

	public void setTextSituacioLaboral(String textSituacioLaboral) {
		this.textSituacioLaboral = textSituacioLaboral;
	}

}