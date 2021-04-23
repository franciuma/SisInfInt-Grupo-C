package es.uma.informatica.jpa.demo;

import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Encuesta {

	@Id
	@Column(name = "Fecha_Envio")
	private DateTimeFormatter fechaEnvio;
	
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

	@ManyToOne
	private Expediente expediente;

	public Encuesta() {
		
	}
	
	public Encuesta(DateTimeFormatter fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public DateTimeFormatter getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(DateTimeFormatter fechaEnvio) {
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
