package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Expedientes {

	@Id
	@Column(name = "Numero de Expediente")
	private Long numExpediente;
	@Column(name = "Activo")
	private boolean activo;
	@Column(name = "Nota Media Provisional")
	private Long notaMediaProvisional;
	
	public Long getNumExpediente() {
		return numExpediente;
	}
	
	public void setNumExpediente(Long numExpediente) {
		this.numExpediente = numExpediente;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Long getNotaMediaProvisional() {
		return notaMediaProvisional;
	}
	
	public void setNotaMediaProvisional(Long notaMediaProvisional) {
		this.notaMediaProvisional = notaMediaProvisional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
		result = prime * result + ((notaMediaProvisional == null) ? 0 : notaMediaProvisional.hashCode());
		result = prime * result + ((numExpediente == null) ? 0 : numExpediente.hashCode());
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
		Expedientes other = (Expedientes) obj;
		if (activo != other.activo)
			return false;
		if (notaMediaProvisional == null) {
			if (other.notaMediaProvisional != null)
				return false;
		} else if (!notaMediaProvisional.equals(other.notaMediaProvisional))
			return false;
		if (numExpediente == null) {
			if (other.numExpediente != null)
				return false;
		} else if (!numExpediente.equals(other.numExpediente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expedientes [numExpediente=" + numExpediente + ", activo=" + activo + ", notaMediaProvisional="
				+ notaMediaProvisional + "]";
	}
}
