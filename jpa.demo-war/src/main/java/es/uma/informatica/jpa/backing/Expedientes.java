package es.uma.informatica.jpa.backing;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import es.uma.informatica.ejb.GestionAsignatura;
import es.uma.informatica.ejb.GestionExpediente;
import es.uma.informatica.ejb.exceptions.AsignaturaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.AsignaturaYaExistenteException;
import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ExpedienteYaExistenteException;
import es.uma.informatica.jpa.demo.Asignatura;
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

	private Expediente expediente;
	private boolean insertar_EX;
	private List<Expediente> listExpedientes;
	
	public String borrarTodos() {
		expedientes.eliminarTodos();
		FacesMessage message = new FacesMessage("Borrado con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}
	
	public Expedientes() {
		// TODO Auto-generated constructor stub
		expediente = new Expediente();
		insertar_EX = false;
	}
	
	public String aniadir_Asignatura(){
		
		try {
			
			expedientes.insertarExpediente(expediente);
			setInsertar_EX(true);
			return "exitoInsertarExpediente.xhtml";
		} catch (ExpedienteYaExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String eliminarExpediente(Expediente ex) {
	try {
		expedientes.eliminarExpediente(ex);
	} catch (ExpedienteNoEncontradoException e) {
		// TODO Auto-generated catch block
		return "index.xhtml";
	}
	return null;
	}

	public String actualizarAsignatura() {
	try {
		expedientes.actualizarExpediente(expediente);
		return "lista_expedientes.xhtml";
	} catch (ExpedienteNoEncontradoException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
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
	
	
}
