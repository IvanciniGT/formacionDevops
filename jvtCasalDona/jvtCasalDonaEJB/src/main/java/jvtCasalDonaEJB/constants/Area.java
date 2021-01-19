package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the area database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="area")
@NamedQueries(
		{
			@NamedQuery(name="Area.tots", query="SELECT a FROM Area a order by a.textArea")
		})
		
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String textArea;

	//bi-directional many-to-one association to AreaConsulta
	@OneToMany(mappedBy="area")
	private List<AreaConsulta> areaConsultas;

	public Area() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTextArea() {
		return this.textArea;
	}

	public void setTextArea(String textArea) {
		this.textArea = textArea;
	}

	public List<AreaConsulta> getAreaConsultas() {
		return this.areaConsultas;
	}

	public void setAreaConsultas(List<AreaConsulta> areaConsultas) {
		this.areaConsultas = areaConsultas;
	}

	public AreaConsulta addAreaConsulta(AreaConsulta areaConsulta) {
		getAreaConsultas().add(areaConsulta);
		areaConsulta.setArea(this);

		return areaConsulta;
	}

	public AreaConsulta removeAreaConsulta(AreaConsulta areaConsulta) {
		getAreaConsultas().remove(areaConsulta);
		areaConsulta.setArea(null);

		return areaConsulta;
	}

}