package jvtCasalDonaEJB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the solicitantViolenciaViscuda database table.
 * 
 */
@Entity
@Table(schema="siad", name ="solicitantViolenciaViscuda")
@Cacheable(false) // dins del persistence.xml cal que hi hagi: <shared-cache-mode>DISABLE_SELECTIVE</shared-cache-mode> 
@NamedQuery(name="SolicitantViolenciaViscuda.findAll", query="SELECT s FROM SolicitantViolenciaViscuda s")
public class SolicitantViolenciaViscuda implements Serializable {
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator(name="SOLICITANTVIOLENCIAVISCUDA_ID_GENERATOR", sequenceName="SIAD.pkSolicitantViolenciaViscuda", allocationSize=1 )
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLICITANTVIOLENCIAVISCUDA_ID_GENERATOR")
	private long id;

	private int fkViolenciaViscuda;

	//bi-directional many-to-one association to Solicitant
	@ManyToOne  (cascade = CascadeType.PERSIST)// , optional = false)
	@JoinColumn(name="fkSolicitant")
	private Solicitant solicitant;
	
	@ManyToOne 
	@JoinColumn(name = "fkViolenciaViscuda" , insertable=false, updatable=false)
	private ViolenciaViscuda violenciaViscuda;

	public ViolenciaViscuda getViolenciaViscuda() {
		return violenciaViscuda;
	}

	public void setViolenciaViscuda(ViolenciaViscuda violenciaViscuda) {
		this.violenciaViscuda = violenciaViscuda;
	}

	public SolicitantViolenciaViscuda() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFkViolenciaViscuda() {
		return this.fkViolenciaViscuda;
	}

	public void setFkViolenciaViscuda(int fkViolenciaViscuda) {
		this.fkViolenciaViscuda = fkViolenciaViscuda;
	}

	public Solicitant getSolicitant() {
		return this.solicitant;
	}

	public void setSolicitant(Solicitant solicitant) {
		this.solicitant = solicitant;
	}

}