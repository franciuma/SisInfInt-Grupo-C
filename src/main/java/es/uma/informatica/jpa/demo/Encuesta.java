package es.uma.informatica.jpa.demo;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Encuesta {

	@Id
	@Column(name = "Fecha de Envio")
	private Date fechaEnvio;
	
	@ManyToMany
	@JoinTable(name = "JND_Encuesta_GruposPorAsig",
		joinColumns = @JoinColumn(name = "Encuesta_FK"),
		inverseJoinColumns = @JoinColumn(name = "GruposPorAsig_FK"))
	private List<GruposPorAsig> perteneceAGruposPorAsig;
	
	@ManyToOne
	private Expediente expediente;

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fechaEnvio == null) ? 0 : fechaEnvio.hashCode());
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
		if (fechaEnvio == null) {
			if (other.fechaEnvio != null)
				return false;
		} else if (!fechaEnvio.equals(other.fechaEnvio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Encuesta [fechaEnvio=" + fechaEnvio + "]";
	}
}
