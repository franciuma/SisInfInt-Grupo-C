package es.uma.informatica.jpa.demo;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Encuesta implements Serializable {
	
	@Id
	@Column(name = "Fecha_Envio")
	private Timestamp fechaEnvio;
	private String campos;
	
	@ManyToOne
	private Expediente expediente;
	
	/*@ManyToMany
	@JoinTable(name = "JND_Encuesta_GruposPorAsig",
		joinColumns = @JoinColumn(name = "Encuesta_FK"),
		inverseJoinColumns = @JoinColumn(name = "GruposPorAsig_FK"))*/
	
	@ManyToMany
	@JoinColumns({
		
		@JoinColumn(name = "Encuesta_fechaEnvio", referencedColumnName = "fecha"),
		@JoinColumn(name = "GruposPorAsig_id", referencedColumnName = "id")
	})
	
	private List<GruposPorAsig> perteneceAGruposPorAsig;

	public Encuesta(Timestamp fechaEnvio, String campos) {
		
		super();
		
		this.fechaEnvio = fechaEnvio;
		this.campos = campos;
	}
	
	public Encuesta() {
		
	}
	
	public Timestamp getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Timestamp fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}
	
	public String getCampos() {
		return campos;
	}
	
	public void setCampos(String campos) {
		this.campos = campos;
	}

	public List<GruposPorAsig> getPerteneceAGruposPorAsig() {
		return perteneceAGruposPorAsig;
	}

	public void setPerteneceAGruposPorAsig(List<GruposPorAsig> perteneceAGruposPorAsig) {
		this.perteneceAGruposPorAsig = perteneceAGruposPorAsig;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((campos == null) ? 0 : campos.hashCode());
		result = prime * result + ((expediente == null) ? 0 : expediente.hashCode());
		result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
		result = prime * result + ((perteneceAGruposPorAsig == null) ? 0 : perteneceAGruposPorAsig.hashCode());
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
		Encuesta other = (Encuesta) obj;
		if (campos == null) {
			if (other.campos != null)
				return false;
		} else if (!campos.equals(other.campos))
			return false;
		if (expediente == null) {
			if (other.expediente != null)
				return false;
		} else if (!expediente.equals(other.expediente))
			return false;
		if (fechaEnvio == null) {
			if (other.fechaEnvio != null)
				return false;
		} else if (!fechaEnvio.equals(other.fechaEnvio))
			return false;
		if (perteneceAGruposPorAsig == null) {
			if (other.perteneceAGruposPorAsig != null)
				return false;
		} else if (!perteneceAGruposPorAsig.equals(other.perteneceAGruposPorAsig))
			return false;
		return true;
	}
}
