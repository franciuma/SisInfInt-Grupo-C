package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alumno {

	@Id 
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "DNI", nullable = false, unique = true, length = 9)
	private String dni;
	@Column(name = "Nombre Completo", nullable = false)
	private String nombreCompleto;
	@Column(name = "Email Institucional", nullable = false)
	private String emailInstitucional;
	@Column(name = "Email Personal")
	private String emailPersonal;
	@Column(name = "Telefono fijo")
	private String telefono;
	@Column(name = "Telefono movil")
	private String movil;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getEmailInstitucional() {
		return emailInstitucional;
	}
	
	public void setEmailInstitucional(String emailInstitucional) {
		this.emailInstitucional = emailInstitucional;
	}
	
	public String getEmailPersonal() {
		return emailPersonal;
	}
	
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getMovil() {
		return movil;
	}
	
	public void setMovil(String movil) {
		this.movil = movil;
	}
}
