package jvtCasalDonaEJB;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;




/**
 * The persistent class for the violenciaViscuda database table.
 * 
 */
@Entity
@Table(schema="siad", name="violenciaViscuda")
@NamedQuery(name="ViolenciaViscuda.findAll", query="SELECT v FROM ViolenciaViscuda v order by v.terxtViolenciaViscuda")
public class ViolenciaViscuda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String terxtViolenciaViscuda;

	//bi-directional many-to-one association to SolicitantViolenciaViscuda
	@OneToMany(mappedBy="violenciaViscuda")
	private List<SolicitantViolenciaViscuda> solicitantViolenciaViscudas;

	public ViolenciaViscuda() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTerxtViolenciaViscuda() {
		return this.terxtViolenciaViscuda;
	}

	public void setTerxtViolenciaViscuda(String terxtViolenciaViscuda) {
		this.terxtViolenciaViscuda = terxtViolenciaViscuda;
	}

	public List<SolicitantViolenciaViscuda> getSolicitantViolenciaViscudas() {
		return this.solicitantViolenciaViscudas;
	}

	public void setSolicitantViolenciaViscudas(List<SolicitantViolenciaViscuda> solicitantViolenciaViscudas) {
		this.solicitantViolenciaViscudas = solicitantViolenciaViscudas;
	}

	public SolicitantViolenciaViscuda addSolicitantViolenciaViscuda(SolicitantViolenciaViscuda solicitantViolenciaViscuda) {
		getSolicitantViolenciaViscudas().add(solicitantViolenciaViscuda);
		solicitantViolenciaViscuda.setViolenciaViscuda(this);

		return solicitantViolenciaViscuda;
	}

	public SolicitantViolenciaViscuda removeSolicitantViolenciaViscuda(SolicitantViolenciaViscuda solicitantViolenciaViscuda) {
		getSolicitantViolenciaViscudas().remove(solicitantViolenciaViscuda);
		solicitantViolenciaViscuda.setViolenciaViscuda(null);

		return solicitantViolenciaViscuda;
	}
}