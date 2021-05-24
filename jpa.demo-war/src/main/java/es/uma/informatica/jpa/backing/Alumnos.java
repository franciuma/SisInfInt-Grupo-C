package es.uma.informatica.jpa.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.jpa.demo.Alumno;

@Named(value="alumnos2")
@RequestScoped
public class Alumnos {
	
	@Inject
	private GestionAlumno alumnos;
	private Alumno alumno;
	private List<Alumno> listAlumnos;
	
	
	public Alumnos() {
		// TODO Auto-generated constructor stub
		alumno = new Alumno();
	}
	public List<Alumno> getListAlumnos() {
		listAlumnos = alumnos.obtenerAlumnos();
		return listAlumnos;
	}

	public void setListAlumnos(List<Alumno> list_alumnos) {
		this.listAlumnos = list_alumnos;
	}
	
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	
	
}
