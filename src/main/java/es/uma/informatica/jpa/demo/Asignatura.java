package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Asignatura {

	@Id 
	@Column(name = "Referencia")
	private Integer referencia;
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
	
}
