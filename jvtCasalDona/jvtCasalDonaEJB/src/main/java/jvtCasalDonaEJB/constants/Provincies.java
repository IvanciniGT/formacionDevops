package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the provincies database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="provincies")
@NamedQueries(
		{
			@NamedQuery(name="Provincies.finNom", query="SELECT p FROM  Provincies p where p.nom like :id_Nom order by p.nom")
			
		})	
public class Provincies implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codiProvincia;

	private String nom;

	//bi-directional many-to-one association to Ciutat
	@OneToMany(mappedBy="provincies")
	private List<Ciutats> ciutats;

	public Provincies() {
	}

	public int getCodiProvincia() {
		return this.codiProvincia;
	}

	public void setCodiProvincia(int codiProvincia) {
		this.codiProvincia = codiProvincia;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Ciutats> getCiutats() {
		return this.ciutats;
	}

	public void setCiutats(List<Ciutats> ciutats) {
		this.ciutats = ciutats;
	}

	public Ciutats addCiutat(Ciutats ciutat) {
		getCiutats().add(ciutat);
		ciutat.setProvincia(this);

		return ciutat;
	}

	public Ciutats removeCiutat(Ciutats ciutat) {
		getCiutats().remove(ciutat);
		ciutat.setProvincia(null);

		return ciutat;
	}

}