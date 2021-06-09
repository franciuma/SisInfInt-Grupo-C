package es.uma.informatica.jpa.backing;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.ejb.GestionAsignatura;
import es.uma.informatica.ejb.GestionExpediente;
import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ExpedienteYaExistenteException;
import es.uma.informatica.jpa.demo.Alumno;
import es.uma.informatica.jpa.demo.Expediente;

@Named(value="expedientes")
@RequestScoped
public class Expedientes {
private static final Logger LOGGER = Logger.getLogger(Expedientes.class.getCanonicalName());

	@Inject
	private GestionExpediente expedientes;
	private Part upload;
	public Part getUpload() {
		return upload;
	}
	public void setUpload(Part upload) {
		this.upload = upload;
	}
	
	@Inject
	private GestionAlumno alumnos;

	private Expediente expediente;
	private boolean insertar_EX;
	private List<Expediente> listExpedientes;
	private boolean buscar;
	private String numExpediente;
	
	public String aniadir_Expediente(){
		
		try {
			LOGGER.info(expediente.getNotaMediaProvisional().toString());
			expedientes.insertarExpediente(expediente);
			setInsertar_EX(true);
			return "lista_expedientes.xhtml";
		} catch (ExpedienteYaExistenteException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Expediente ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String eliminarExpediente(Expediente ex) {
	try {
		expedientes.eliminarExpediente(ex);
	} catch (ExpedienteNoEncontradoException e) {
		// TODO Auto-generated catch block
		FacesMessage message = new FacesMessage("Expediente no existente");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return null;
	}

	public String actualizarExpediente() {
	try {
		expedientes.actualizarExpediente(expediente);
		return "lista_expedientes.xhtml";
	} catch (ExpedienteNoEncontradoException e) {
		// TODO Auto-generated catch block
		FacesMessage message = new FacesMessage("Expediente no existente");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	return null;
	}

	public Expediente buscarExpediente(String numExpediente) {
		Expediente expediente = null;
		try {
			if(!numExpediente.equals("")) {
				expediente = expedientes.obtenerExpediente(Integer.parseInt(numExpediente));
			}
		}catch(ExpedienteNoEncontradoException e){
			FacesMessage message = new FacesMessage("Expediente no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return expediente;	
	}
	
	public String varBuscar() {
		buscar = true;
		return null;
	}
	
	public String modoModificarExpediente(Expediente ex) {
	expediente = ex;
	return "editarExpediente.xhtml";
	}
	public Expediente getExpediente() {
		return expediente;
	}
	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}
	public boolean isInsertar_EX() {
		return insertar_EX;
	}
	public void setInsertar_EX(boolean insertar_EX) {
		this.insertar_EX = insertar_EX;
	}
	public List<Expediente> getListExpedientes() {
		listExpedientes = expedientes.obtenerExpedientes();
		return listExpedientes;
	}
	public void setListExpedientes(List<Expediente> listExpedientes) {
		this.listExpedientes = listExpedientes;
	}
	public boolean isBuscar() {
		return buscar;
	}
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}

	public String borrarTodos() {
		expedientes.eliminarTodos();
		FacesMessage message = new FacesMessage("Borrado con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}
	public String getNumExpediente() {
		return numExpediente;
	}
	public void setNumExpediente(String numExpediente) {
		this.numExpediente = numExpediente;
	}
	public Expedientes() {
		// TODO Auto-generated constructor stub
		expediente = new Expediente();
		insertar_EX = false;
	}
	
}
