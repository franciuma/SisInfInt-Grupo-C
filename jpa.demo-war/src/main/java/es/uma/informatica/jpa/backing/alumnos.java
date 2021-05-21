package es.uma.informatica.jpa.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.jpa.demo.Alumno;

@Named(value="alumnos")
@RequestScoped
public class alumnos {
	
	@Inject
	private GestionAlumno alumnos;
	private Alumno alumno;
	private List<Alumno> list_alumnos;
	
	
	public alumnos() {
		// TODO Auto-generated constructor stub
		list_alumnos = alumnos.obtenerAlumnos();
	}
	public List<Alumno> getList_alumnos() {
		return list_alumnos;
	}

	public void setList_alumnos(List<Alumno> list_alumnos) {
		this.list_alumnos = list_alumnos;
	}
	
	public String listar_alumnos(){
		list_alumnos=alumnos.obtenerAlumnos();
		return null;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	
	
}
