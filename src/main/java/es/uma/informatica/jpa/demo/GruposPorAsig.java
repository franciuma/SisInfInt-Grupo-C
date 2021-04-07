package es.uma.informatica.jpa.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class GruposPorAsig implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private GruposPorAsig_ID id;
	@Column(name = "Oferta")
	private Boolean oferta;
	
	@ManyToMany(mappedBy = "perteneceAGruposPorAsig")
	private List<Encuesta> contieneEncuestas;

	@ManyToOne
	private Asignatura asignatura;
	
	@ManyToOne
	private Grupo grupo;

	public GruposPorAsig_ID getId() {
		return id;
	}

	public void setId(GruposPorAsig_ID id) {
		this.id = id;
	}

	public Boolean getOferta() {
		return oferta;
	}

	public void setOferta(Boolean oferta) {
		this.oferta = oferta;
	}

	public List<Encuesta> getContieneEncuestas() {
		return contieneEncuestas;
	}

	public void setContieneEncuestas(List<Encuesta> contieneEncuestas) {
		this.contieneEncuestas = contieneEncuestas;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignatura == null) ? 0 : asignatura.hashCode());
		result = prime * result + ((contieneEncuestas == null) ? 0 : contieneEncuestas.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (contieneEncuestas == null) {
			if (other.contieneEncuestas != null)
				return false;
		} else if (!contieneEncuestas.equals(other.contieneEncuestas))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
		return "GruposPorAsig [id=" + id + ", oferta=" + oferta + ", contieneEncuestas=" + contieneEncuestas
				+ ", asignatura=" + asignatura + ", grupo=" + grupo + "]";
	}
}
