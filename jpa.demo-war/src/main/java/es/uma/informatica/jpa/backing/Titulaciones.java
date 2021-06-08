package es.uma.informatica.jpa.backing;

import java.io.File;

import es.uma.informatica.jpa.demo.Alumno;
import es.uma.informatica.jpa.demo.Titulacion;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import es.uma.informatica.ejb.GestionTitulacion;
import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.TitulacionNoEncontradaException;
import es.uma.informatica.ejb.exceptions.TitulacionYaExistenteException;

@Named(value="titulaciones")
@RequestScoped
public class Titulaciones {
	private static final Logger LOGGER = Logger.getLogger(Titulaciones.class.getCanonicalName());
	
	@Inject
	private GestionTitulacion titulaciones;
	private Part upload;
	public Part getUpload() {
		return upload;
	}
	public void setUpload(Part upload) {
		this.upload = upload;
	}

	private Titulacion titulacion;
	private boolean insertar_TI;
	private List<Titulacion> listTitulaciones;
	private boolean buscar;
	private String codigo;
	
	public String borrarTodas() {
		titulaciones.eliminarTodas();
		FacesMessage message = new FacesMessage("Borrado con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}
	public String importarTitulaciones() {	

		String sFile ="/tmp/titulaciones.xlsx"; 
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
			titulaciones.importarTitulacion(sFile);
			filtemp.delete();
			return "lista_titulaciones.xhtml";
		} catch (TitulacionYaExistenteException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Titulacion ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (IOException e) {
			FacesMessage message = new FacesMessage("Error al leer el fichero");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	
		filtemp.delete();
		return null;
	}
	public Titulaciones() {
		// TODO Auto-generated constructor stub
		titulacion = new Titulacion();
		insertar_TI = false;
	}
	
	public String anaidir_Titulacion() {
		
		try {
			titulaciones.insertarTitulacion(titulacion);
			setInsertar_TI(true);
			return "lista_titulaciones.xhtml";
		} catch (TitulacionYaExistenteException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Alumno ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
	}
	public String eliminarTitulacion(Titulacion tit) {
		try {
			LOGGER.info(tit.toString());
			titulaciones.eliminarTitulacion(tit.getCodigo());
		} catch (TitulacionNoEncontradaException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Titulacion no encontrada");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String actualizarTitulacion() {
		try {
			titulaciones.actualizarTitulacion(titulacion);
			return "lista_titulaciones.xhtml";
		} catch (TitulacionNoEncontradaException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Alumno no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public Titulacion BuscarTitulacion(String codigo) {
		Titulacion titulacion = null;
		try {
			if(!codigo.equals("")) {
				titulacion = titulaciones.obtenerTitulacion(Integer.parseInt(codigo));
			}
		}catch(TitulacionNoEncontradaException e){
			FacesMessage message = new FacesMessage("Titulacion no encontrada");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return titulacion;
	}
	
	public String modoModificarTitulacion(Titulacion tit) {
		titulacion = tit;
		return "editarTitulacion.xhtml";
	}
	
	public String VarBuscar() {
		buscar = true;
		return null;
	}
	
	public List<Titulacion> getListTitulaciones() {
		listTitulaciones = titulaciones.obtenerTitulaciones();
		return listTitulaciones;
	}

	public void setListAlumnos(List<Titulacion> list_titulaciones) {
		this.listTitulaciones = list_titulaciones;
	}
	
	public Titulacion getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public boolean isInsertar_TI() {
		return insertar_TI;
	}

	public void setInsertar_TI(boolean insertar_TI) {
		this.insertar_TI = insertar_TI;
	}

	public boolean isBuscar() {
		return buscar;
	}
	
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
