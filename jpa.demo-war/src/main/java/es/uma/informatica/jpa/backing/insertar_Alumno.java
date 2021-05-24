package es.uma.informatica.jpa.backing;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import es.uma.informatica.ejb.AlumnosEJB;
import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.ejb.GestionMatricula;
import es.uma.informatica.ejb.exceptions.AlumnoYaExistenteException;
import es.uma.informatica.jpa.demo.Alumno;

@Named(value = "insertar_alumno")
@RequestScoped
public class insertar_Alumno {
	
	@Inject
	private GestionAlumno alumnos;
	private Alumno alumno;
	private boolean insertar_AL;
	private List<Alumno> list_alumnos;
	
	public insertar_Alumno() {
		// TODO Auto-generated constructor stub
		alumno = new Alumno();
		insertar_AL = false;
	}

	
	public String anaidir_Alumno() {
			
		try {
			alumnos.insertarAlumno(alumno);
			setInsertar_AL(true);
			return "exito_insertar_Alumno.xhtml";
		} catch (AlumnoYaExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public boolean isInsertar_AL() {
		return insertar_AL;
	}

	public void setInsertar_AL(boolean insertar_AL) {
		this.insertar_AL = insertar_AL;
	}


	public List<Alumno> getList_alumnos() {
		return list_alumnos;
	}


	public void setList_alumnos(List<Alumno> list_alumnos) {
		this.list_alumnos = list_alumnos;
	}
}
