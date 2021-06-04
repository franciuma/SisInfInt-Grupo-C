package es.uma.informatica.jpa.demo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Matricula_ID implements Serializable {
		
	
	private static final long serialVersionUID = 1L;
	private Integer expediente;
	
	public Matricula_ID() {
		// TODO Auto-generated constructor stub
	}
	public Matricula_ID(Integer expediente) {
		super();
		this.expediente = expediente;
	}
	
	public Integer getExpediente() {
		return expediente;
	}
	public void setExpediente(Integer expediente) {
		this.expediente = expediente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula_ID other = (Matricula_ID) obj;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		return true;
	}
	
	
}
