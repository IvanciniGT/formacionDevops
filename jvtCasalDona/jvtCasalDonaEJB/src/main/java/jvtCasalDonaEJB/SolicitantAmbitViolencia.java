package jvtCasalDonaEJB;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SolicitantAmbitViolencia database table.
 * 
 */
@Entity
@Table(schema="siad", name ="SolicitantAmbitViolencia")
@Cacheable(false) // dins del persistence.xml cal que hi hagi: <shared-cache-mode>DISABLE_SELECTIVE</shared-cache-mode> 
@NamedQueries(
		{
			@NamedQuery(name="SolicitantAmbitViolencia.findAll", query="SELECT s FROM SolicitantAmbitViolencia s")
		//	@NamedQuery(name="SolicitantAmbitViolencia.pkSolicitant", query="SELECT sav FROM Solicitant s inner join s.solicitantAmbitViolencias sav where s.CodiExpedient = :id_fkSolicitant")
		})
public class SolicitantAmbitViolencia implements Serializable {
	
	private static final long serialVersionUID = 1L;
		 
	@Id
	@SequenceGenerator(name="SOLICITANTAMBITVIOLENCIA_ID_GENERATOR" , sequenceName="SIAD.pkSolicitantAmbitViolencia", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SOLICITANTAMBITVIOLENCIA_ID_GENERATOR")
	private long id;

	private int fkAmbitViolencia;

	//bi-directional many-to-one association to Solicitant
	@ManyToOne  (cascade = CascadeType.PERSIST )//, optional = false)
	@JoinColumn(name = "fkSolicitant")
	private Solicitant solicitant;
	
	@ManyToOne 
	@JoinColumn(name = "fkAmbitViolencia", insertable=false, updatable=false)
	private AmbitViolencia ambitViolencia;

	public AmbitViolencia getAmbitViolencia() {
		return ambitViolencia;
	}

	public void setAmbitViolencia(AmbitViolencia ambitViolencia) {
		this.ambitViolencia = ambitViolencia;
	}

	public SolicitantAmbitViolencia() {
		
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getFkAmbitViolencia() {
		return this.fkAmbitViolencia;
	}

	public void setFkAmbitViolencia(int fkAmbitViolencia) {
		this.fkAmbitViolencia = fkAmbitViolencia;
	}

	public Solicitant getSolicitant() {
		return this.solicitant;
	}

	public void setSolicitant(Solicitant solicitant) {
		this.solicitant = solicitant;
	}

}