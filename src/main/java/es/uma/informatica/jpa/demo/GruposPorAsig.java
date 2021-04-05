package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GruposPorAsig {
	
	@Id
	@Column(name = "Curso Academico")
	private String cursoAcademico;
	@Column(name = "Oferta")
	private Boolean oferta;
	
	public String getCursoAcademico() {
		return cursoAcademico;
	}
	
	public void setCursoAcademico(String cursoAcademico) {
		this.cursoAcademico = cursoAcademico;
	}
	
	public Boolean getOferta() {
		return oferta;
	}
	
	public void setOferta(Boolean oferta) {
		this.oferta = oferta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
		result = prime * result + ((oferta == null) ? 0 : oferta.hashCode());
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
		GruposPorAsig other = (GruposPorAsig) obj;
		if (cursoAcademico == null) {
			if (other.cursoAcademico != null)
				return false;
		} else if (!cursoAcademico.equals(other.cursoAcademico))
			return false;
		if (oferta == null) {
			if (other.oferta != null)
				return false;
		} else if (!oferta.equals(other.oferta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GruposPorAsig [cursoAcademico=" + cursoAcademico + ", oferta=" + oferta + "]";
	}
}
