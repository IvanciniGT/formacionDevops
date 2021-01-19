package jvtCasalDonaEJB.constants;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the canalEntrada database table.
 * 
 */
@Entity
@Table(schema="siadConstants",name="canalEntrada")
@NamedQuery(name="CanalEntrada.findAll", query="SELECT c FROM CanalEntrada c order by c.textCanalEntrada")
public class CanalEntrada implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String textCanalEntrada;

	public CanalEntrada() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextCanalEntrada() {
		return this.textCanalEntrada;
	}

	public void setTextCanalEntrada(String textCanalEntrada) {
		this.textCanalEntrada = textCanalEntrada;
	}

}