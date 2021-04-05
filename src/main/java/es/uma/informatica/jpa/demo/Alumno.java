package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Alumno {

	@Id 
	@GeneratedValue
	private Long id;
	@Column(name = "DNI", nullable = false, unique = true, length = 9)
	private String dni;
	@Column(nullable = false)
	private String nombreCompleto;
	@Column(nullable = false)
	private String emailInstitucional;
	@Column(name = "Email Personal")
	private String emailPersonal;
	@Column(name = "Telefono fijo")
	private String telefono;
	@Column(name = "Telefono movil")
	private String movil;
}
