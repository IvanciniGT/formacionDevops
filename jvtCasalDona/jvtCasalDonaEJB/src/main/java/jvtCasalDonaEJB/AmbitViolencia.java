package jvtCasalDonaEJB;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;




/**
 * The persistent class for the ambitViolencia database table.
 * 
 */
@Entity
@Table(schema="siad",name="ambitViolencia")
@NamedQuery(name="AmbitViolencia.findAll", query="SELECT a FROM AmbitViolencia a order by a.textAmbitViolencia")
public class AmbitViolencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textAmbitViolencia;

	//bi-directional many-to-one association to SolicitantAmbitViolencia
	@OneToMany(mappedBy="ambitViolencia")
	private List<SolicitantAmbitViolencia> solicitantAmbitViolencias;

	public AmbitViolencia() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextAmbitViolencia() {
		return this.textAmbitViolencia;
	}

	public void setTextAmbitViolencia(String textAmbitViolencia) {
		this.textAmbitViolencia = textAmbitViolencia;
	}

	public List<SolicitantAmbitViolencia> getSolicitantAmbitViolencias() {
		return this.solicitantAmbitViolencias;
	}

	public void setSolicitantAmbitViolencias(List<SolicitantAmbitViolencia> solicitantAmbitViolencias) {
		this.solicitantAmbitViolencias = solicitantAmbitViolencias;
	}

	public SolicitantAmbitViolencia addSolicitantAmbitViolencia(SolicitantAmbitViolencia solicitantAmbitViolencia) {
		getSolicitantAmbitViolencias().add(solicitantAmbitViolencia);
		solicitantAmbitViolencia.setAmbitViolencia(this);

		return solicitantAmbitViolencia;
	}

	public SolicitantAmbitViolencia removeSolicitantAmbitViolencia(SolicitantAmbitViolencia solicitantAmbitViolencia) {
		getSolicitantAmbitViolencias().remove(solicitantAmbitViolencia);
		solicitantAmbitViolencia.setAmbitViolencia(null);

		return solicitantAmbitViolencia;
	}

}