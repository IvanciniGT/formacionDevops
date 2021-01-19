package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ciutats database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="ciutats")
@NamedQueries(
		{
			@NamedQuery(name="Ciutats.findProvincia", query="SELECT c FROM Provincies p inner join p.ciutats c where p.codiProvincia = :id_CodiProvincia order by c.nom"),
			@NamedQuery(name="Ciutats.findNomProvincia", query="SELECT c FROM Provincies p inner join p.ciutats c where p.nom like :id_NomProvincia order by c.nom")
			
		})
public class Ciutats implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codiCiutat;

	private String cp;

	private String nom;

	//bi-directional many-to-one association to Provincy
	@ManyToOne
	@JoinColumn(name="codiProvincia")
	private Provincies provincies;

	public Ciutats() {
	}

	public int getCodiCiutat() {
		return this.codiCiutat;
	}

	public void setCodiCiutat(int codiCiutat) {
		this.codiCiutat = codiCiutat;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Provincies getProvincia() {
		return this.provincies;
	}

	public void setProvincia(Provincies provincia) {
		this.provincies = provincia;
	}

}