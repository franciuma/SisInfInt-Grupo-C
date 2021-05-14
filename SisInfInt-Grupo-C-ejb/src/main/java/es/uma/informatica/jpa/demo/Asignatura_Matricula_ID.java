package es.uma.informatica.jpa.demo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Asignatura_Matricula_ID implements Serializable{

	private static final long serialVersionUID = 1L;
	private Matricula_ID idMatricula;
	private Integer idAsignatura;
	
	public Asignatura_Matricula_ID() {
		
		super();
	}

	public Matricula_ID getIdMatricula() {
		return idMatricula;
	}

	public void setIdMatricula(Matricula_ID idMatricula) {
		this.idMatricula = idMatricula;
	}

	public Integer getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(Integer idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAsignatura == null) ? 0 : idAsignatura.hashCode());
		result = prime * result + ((idMatricula == null) ? 0 : idMatricula.hashCode());
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
		Asignatura_Matricula_ID other = (Asignatura_Matricula_ID) obj;
		if (idAsignatura == null) {
			if (other.idAsignatura != null)
				return false;
		} else if (!idAsignatura.equals(other.idAsignatura))
			return false;
		if (idMatricula == null) {
			if (other.idMatricula != null)
				return false;
		} else if (!idMatricula.equals(other.idMatricula))
			return false;
		return true;
	}
}
