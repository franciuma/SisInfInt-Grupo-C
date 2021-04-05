package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Expedientes {

	@Id
	@GeneratedValue
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
}
