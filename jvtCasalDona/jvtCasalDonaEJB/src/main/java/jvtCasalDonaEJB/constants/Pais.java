package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the pais database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="pais")
@NamedQueries(
		{
			@NamedQuery(name="Pais.tots", query="SELECT p FROM Pais p order by p.pais"),
			@NamedQuery(name="Pais.nom", query="SELECT p FROM Pais p where p.pais like :id_nomPais order by p.pais")
		})

public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int codi;

	private String abreviatura;

	private String pais;

	public Pais() {
	}

	public int getCodi() {
		return this.codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public String getAbreviatura() {
		return this.abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}