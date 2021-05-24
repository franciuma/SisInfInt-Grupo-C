package es.uma.informatica.jpa.demo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GruposPorAsig_ID implements Serializable{

	private static final long serialVersionUID = 1L;

		@Column(name = "Curso_Academico", length = 10)
		private String cursoAcademico;
		private Integer asignatura;
		private Integer grupo;
		
		public String getCursoAcademico() {
			return cursoAcademico;
		}
		
		public void setCursoAcademico(String cursoAcademico) {
			this.cursoAcademico = cursoAcademico;
		}
		
		public Integer getAsignatura() {
			return asignatura;
		}
		
		public void setAsignatura(Integer asignatura) {
			this.asignatura = asignatura;
		}
		
		public Integer getGrupo() {
			return grupo;
		}
		
		public void setGrupo(Integer grupo) {
			this.grupo = grupo;
		}

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
			GruposPorAsig_ID other = (GruposPorAsig_ID) obj;
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
