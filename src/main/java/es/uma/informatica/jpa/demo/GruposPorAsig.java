package es.uma.informatica.jpa.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@IdClass(GruposPorAsig.GruposPorAsigId.class)
public class GruposPorAsig {
	
	public static class GruposPorAsigId implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private String cursoAcademico;
		private Integer asignatura;
		private Integer grupo;
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
			result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
			result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
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
			GruposPorAsigId other = (GruposPorAsigId) obj;
			if (asignatura == null) {
				if (other.asignatura != null)
					return false;
			} else if (!asignatura.equals(other.asignatura))
				return false;
			if (cursoAcademico == null) {
				if (other.cursoAcademico != null)
					return false;
			} else if (!cursoAcademico.equals(other.cursoAcademico))
				return false;
			if (grupo == null) {
				if (other.grupo != null)
					return false;
			} else if (!grupo.equals(other.grupo))
				return false;
			return true;
		}
	}
	
	@Id
	@Column(name = "Curso Academico")
	private String cursoAcademico;
	@Column(name = "Oferta")
	private Boolean oferta;
	
	@ManyToMany(mappedBy = "perteneceAGruposPorAsig")
	private List<Encuesta> contieneEncuestas;
	
	@Id
	@ManyToOne
	private Asignatura asignatura;
	
	@Id
	@ManyToOne
	private Grupo grupo;
	
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
