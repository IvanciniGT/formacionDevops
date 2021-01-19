package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the areaConsulta database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="areaConsulta")
@NamedQueries(
		{
			@NamedQuery(name="AreaConsulta.totes", query="SELECT a FROM AreaConsulta a order by a.textAreaConsulta"),
			@NamedQuery(name="AreaConsulta.areaID", query="SELECT ac FROM Area a inner join a.areaConsultas ac where a.id = :id_id order by ac.textAreaConsulta"),
			@NamedQuery(name="AreaConsulta.areaText", query="SELECT ac FROM Area a inner join a.areaConsultas ac where a.textArea like :id_textArea order by ac.textAreaConsulta")
			
		})
public class AreaConsulta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String textAreaConsulta;

	//bi-directional many-to-one association to Area
	@ManyToOne
	@JoinColumn(name="fkConsulta")
	private Area area;

	public AreaConsulta() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTextAreaConsulta() {
		return this.textAreaConsulta;
	}

	public void setTextAreaConsulta(String textAreaConsulta) {
		this.textAreaConsulta = textAreaConsulta;
	}

	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}