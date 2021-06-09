package es.uma.informatica.jpa.backing;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import es.uma.informatica.ejb.GestionAsignatura;
import es.uma.informatica.ejb.exceptions.AsignaturaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.AsignaturaYaExistenteException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Asignatura;

@Named(value="asignaturas")
@RequestScoped
public class Asignaturas {
	private static final Logger LOGGER = Logger.getLogger(Asignaturas.class.getCanonicalName());
	
	@Inject
	private GestionAsignatura asignaturas;
	private Part upload;
	public Part getUpload() {
		return upload;
	}
	public void setUpload(Part upload) {
		this.upload = upload;
	}
	
	private Asignatura asignatura;
	private boolean insertar_AS;
	private List<Asignatura> listAsignaturas;
	private boolean buscar;
	private String referencia;
	
	public String borrarTodas() {
		asignaturas.eliminarTodas();
		FacesMessage message = new FacesMessage("Borrado con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}
	
	public String importarAsignaturas() throws ParseException {
		String sFile ="/tmp/asignaturas.xlsx"; 
		File filtemp = new File(sFile);
		
		try {
			upload.write(sFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Error al subir el fichero");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		try {
			asignaturas.importarAsignatura(sFile);
			filtemp.delete();
			return "lista_asignaturas.xhtml";
		} catch (AsignaturaYaExistenteException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Asignatura ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Error al leer el fichero de Asignaturas");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	
		filtemp.delete();
		return null;
	}
	public Asignaturas() {
		// TODO Auto-generated constructor stub
		asignatura = new Asignatura();
		insertar_AS = false;
	}
	
	public String aniadir_Asignatura(){
		
		try {
			
			asignaturas.insertarAsignatura(asignatura);
			setInsertar_AS(true);
			return "lista_asignaturas.xhtml";
		} catch (AsignaturaYaExistenteException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Asignatura ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}catch (ProyectoException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("PYECTO SUPU ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);}
		return null;
	}
	public String eliminarAsignatura(Asignatura as) {
		try {
			asignaturas.eliminarAsignatura(as);
		} catch (AsignaturaNoEncontradaException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Asignatura no existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String actualizarAsignatura() {
		try {
			asignaturas.actualizarAsignatura(asignatura);
			return "lista_asignaturas.xhtml";
		} catch (AsignaturaNoEncontradaException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Asignatura no existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String modoModificarAsignatura(Asignatura asi) {
		asignatura = asi;
		return "editarAsignatura.xhtml";
	}
	
	public Asignatura BuscarAsignatura(String referencia) {
		Asignatura asignatura = null;
		try {
			if(!referencia.equals("")) {
				asignatura = asignaturas.obtenerAsignatura(Integer.parseInt(referencia));
			}
		}catch(AsignaturaNoEncontradaException e){
			FacesMessage message = new FacesMessage("Asignatura no encontrada");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return asignatura;
	}
	
	public String VarBuscar() {
		buscar = true;
		return null;
	}
	
	public Asignatura getAsignatura() {
		return asignatura;
	}
	
	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	public boolean isInsertar_AS() {
		return insertar_AS;
	}
	
	public void setInsertar_AS(boolean insertar_AS) {
		this.insertar_AS = insertar_AS;
	}
	
	public List<Asignatura> getListAsignaturas() {
		listAsignaturas = asignaturas.obtenerAsignaturas();
		return listAsignaturas;
	}
	
	public void setListAsignaturas(List<Asignatura> listAsignaturas) {
		this.listAsignaturas = listAsignaturas;
	}
	
	public boolean isBuscar() {
		return buscar;
	}
	
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}
	
	public String getReferencia() {
		return referencia;
	}
	
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
}
