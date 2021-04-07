package es.uma.informatica.jpa.demo;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Asignatura_Matricula implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private Asignatura_Matricula_ID id;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Grupo grupo;
	
	@MapsId("idAsignatura")
	@ManyToOne
	private Asignatura asignatura;
	
	@MapsId("idMatricula")
	@Id
	@ManyToOne
	private Matricula matricula;
}
