package es.uma.informatica.jpa.demo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Matricula_ID implements Serializable {
		
	private static final long serialVersionUID = 1L;
	private String cursoAcademico;
	private Long expediente;
	
	public String getCursoAcademico() {
		return cursoAcademico;
	}
	public void setCursoAcademico(String cursoAcademico) {
		this.cursoAcademico = cursoAcademico;
	}
	public Long getExpediente() {
		return expediente;
	}
	public void setExpediente(Long expediente) {
		this.expediente = expediente;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
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
		if (cursoAcademico == null) {
			if (other.cursoAcademico != null)
				return false;
		} else if (!cursoAcademico.equals(other.cursoAcademico))
			return false;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		return true;
	}
}
