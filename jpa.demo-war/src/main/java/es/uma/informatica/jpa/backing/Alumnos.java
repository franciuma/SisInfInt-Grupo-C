package es.uma.informatica.jpa.backing;

import java.io.File;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
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
	//private UploadedFile upload;
	private Part upload;
	public Part getUpload() {
		return upload;
	}
	public void setUpload(Part upload) {
		this.upload = upload;
	}

	private Alumno alumno;
	private boolean insertar_AL;
	private List<Alumno> listAlumnos;
	private boolean buscar;
	private String dni;

	public String borrarTodos() {
		alumnos.eliminarTodos();
		FacesMessage message = new FacesMessage("Borrado con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}
	public String importarAlumnos() {
		

//		/* 	1ERA OPCIÓN
//		 * - Crear con FILE un fichero temporal con nombre con prefijo aleatorio
//		 * - Pasamos la ruta de ese fichero al upload.write 
//		 * upload.write("/tmp/alumnos.xlsx"); */
//		
//		/* 2A OPCIÓN
//		 * - Asegurarnos de que no existe el archivo antes de hacer write porque si no no funciona
//		 * - Pasamos la ruta del fichero al upload.write
//		 * upload.write("/tmp/alumnos.xlsx"); */
//		

		String sFile ="/tmp/alumnos.xlsx"; 
		File filtemp = new File(sFile);
		filtemp.delete();
		try {
			upload.write(sFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Error al subir el fichero");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		try {
			alumnos.importarAlumnos(sFile);
			filtemp.delete();
			return "lista_alumnos.xhtml";
		} catch (AlumnoYaExistenteException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Alumno ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Error al leer el fichero");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	
		filtemp.delete();
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
			return "lista_alumnos.xhtml";
		} catch (AlumnoYaExistenteException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Alumno ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
	}
	public String eliminarAlumno(Alumno al) {
		try {
			alumnos.eliminarAlumno(al.getDni());
		} catch (AlumnoNoEncontradoException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Alumno no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
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
			FacesMessage message = new FacesMessage("Alumno no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String autenticar(){
		LOGGER.info("alumno: " + alumno.getDni() + " | " + alumno.getNombreCompleto());
		
			boolean t = alumnos.autenticar(alumno.getDni(), alumno.getNombreCompleto());
			if (t)return "index.xhtml";
			FacesMessage message = new FacesMessage("Usuario/contrasenia incorrectos");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
	}
	
	public Alumno BuscarAlumno(String dni) {
		Alumno alumno = null;
		try {
			alumno = alumnos.obtenerAlumno(dni);
		}catch(AlumnoNoEncontradoException e){
			FacesMessage message = new FacesMessage("Alumno no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return alumno;
	}
	
	public String modoModificarAlumno(Alumno al) {
		LOGGER.info("ALUMNO QUE SUPUESTAMENTE LLEGA ME CAGO DIOOOS: " + al.toString());
		alumno = al;
		return "editarAlumno.xhtml";
	}
	
	public String VarBuscar() {
		buscar = true;
		return null;
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

	public boolean isBuscar() {
		return buscar;
	}
	
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
}
