package es.uma.informatica.jpa.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Centro {
	
	@Id @GeneratedValue
	private Long id;
	private String NombreCompleto;
	private String EmailInstitucional;
	private String EmailPersonal;
	private String Telefono;
	private String Movil;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombreCompleto() {
		return NombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		NombreCompleto = nombreCompleto;
	}
	public String getEmailInstitucional() {
		return EmailInstitucional;
	}
	public void setEmailInstitucional(String emailInstitucional) {
		EmailInstitucional = emailInstitucional;
	}
	public String getEmailPersonal() {
		return EmailPersonal;
	}
	public void setEmailPersonal(String emailPersonal) {
		EmailPersonal = emailPersonal;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getMovil() {
		return Movil;
	}
	public void setMovil(String movil) {
		Movil = movil;
	}
	

}
