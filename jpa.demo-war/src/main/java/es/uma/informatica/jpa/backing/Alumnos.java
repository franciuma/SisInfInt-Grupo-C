package es.uma.informatica.jpa.backing;

import java.io.IOException;

import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.swing.text.Document;

import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.AlumnoYaExistenteException;
import es.uma.informatica.jpa.demo.Alumno;

@Named(value="alumnos2")
@RequestScoped
public class Alumnos {
	private static final Logger LOGGER = Logger.getLogger(Alumnos.class.getCanonicalName());
	
	@Inject
	private GestionAlumno alumnos;
	private Part upload;
	private Alumno alumno;
	private boolean insertar_AL;
	private List<Alumno> listAlumnos;
	
	public String importarAlumnos() {
		String sFile = Paths.get(upload.getName()).getFileName().toString();
		
		try {
			alumnos.importarAlumnos(sFile);
			return "lista_alumnos.xhtml";
		} catch (AlumnoYaExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Alumnos() {
		// TODO Auto-generated constructor stub
		alumno = new Alumno();
		insertar_AL = false;
	}
	
	public String anaidir_Alumno() {
		
		try {
			
			alumnos.insertarAlumno(alumno);
			setInsertar_AL(true);
			return "exitoInsertarAlumno.xhtml";
		} catch (AlumnoYaExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String eliminarAlumno(Alumno al) {
		try {
			alumnos.eliminarAlumno(al.getDni());
		} catch (AlumnoNoEncontradoException e) {
			// TODO Auto-generated catch block
			return "index.xhtml";
		}
		return null;
	}
	
	public String actualizarAlumno() {
		try {
			LOGGER.info("ALUMNO QUE SUPUESTAMENTE LLEGA ME CAGO DIOOOS: " + alumno.toString());
			alumnos.actualizarAlumno(alumno);
			return "lista_alumnos.xhtml";
		} catch (AlumnoNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String autenticar(){
		LOGGER.info("alumno: " + alumno.getDni() + " | " + alumno.getNombreCompleto());
		try {
			 boolean t = alumnos.autenticar(alumno.getDni(), alumno.getNombreCompleto());
			if (t)return "index.xhtml";
			else return "insertar_Alumno.xhtml";
		}catch (AlumnoNoEncontradoException e) {
			// TODO: handle exception
			return "insertar_Alumno.xhtml";
		}
		
		
	}
	
	public String modoModificarAlumno(Alumno al) {
		LOGGER.info("ALUMNO QUE SUPUESTAMENTE LLEGA ME CAGO DIOOOS: " + al.toString());
		alumno = al;
		return "editarAlumno.xhtml";
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

	public boolean isInsertar_AL() {
		return insertar_AL;
	}

	public void setInsertar_AL(boolean insertar_AL) {
		this.insertar_AL = insertar_AL;
	}

	public Part getUpload() {
		return upload;
	}

	public void setUpload(Part upload) {
		this.upload = upload;
	}
	


	
	
}
