package es.uma.informatica.jpa.demo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Clase implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private Clase_ID id;
	
	@Column(name = "Hora_Fin")
	private Date horaFin;
	
	@ManyToOne
	private Asignatura asignatura;

	@Id
	@ManyToOne
	private Grupo grupo;

	public Clase() {
		
	}
	
	public Clase(Clase_ID id2, Date horaFin2) {
		id = id2;
		horaFin = horaFin2;
	}

	public Clase_ID getId() {
		return id;
	}

	public void setId(Clase_ID id) {
		this.id = id;
	}

	public Date getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
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
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((horaFin == null) ? 0 : horaFin.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Clase other = (Clase) obj;
		if (asignatura == null) {
			if (other.asignatura != null)
				return false;
		} else if (!asignatura.equals(other.asignatura))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (horaFin == null) {
			if (other.horaFin != null)
				return false;
		} else if (!horaFin.equals(other.horaFin))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clase [id=" + id + ", horaFin=" + horaFin + ", asignatura=" + asignatura + ", grupo=" + grupo + "]";
	}
}

