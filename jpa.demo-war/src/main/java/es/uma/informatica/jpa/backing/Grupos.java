package es.uma.informatica.jpa.backing;

import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import es.uma.informatica.ejb.GestionGrupo;
import es.uma.informatica.ejb.GestionTitulacion;
import es.uma.informatica.ejb.exceptions.GrupoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.GrupoYaExistenteException;
import es.uma.informatica.ejb.exceptions.TitulacionNoEncontradaException;
import es.uma.informatica.jpa.demo.Grupo;
import es.uma.informatica.jpa.demo.Titulacion;

@Named(value="grupos")
@RequestScoped
public class Grupos {
	private static final Logger LOGGER = Logger.getLogger(Grupos.class.getCanonicalName());
	
	@Inject
	private GestionGrupo grupos;
	
	private Part upload;
	private String id ;
	public Part getUpload() {
		return upload;
	}
	public void setUpload(Part upload) {
		this.upload = upload;
	}
	@Inject
	private GestionTitulacion titulaciones;
	private Grupo grupo;
	private String titulacion;
	private String letra;
	private String curso;
	private Boolean buscar;
	public String getTitulacion() {
		return titulacion;
	}
	public void setTitulacion(String titulacion) {
		this.titulacion = titulacion;
	}
	
	private Grupo grupoviejo;
	private boolean insertar_GR;
	private List<Grupo> listGrupos;

	public String borrarTodos() {
		grupos.eliminarTodos();
		FacesMessage message = new FacesMessage("Borrado con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}

	public Grupos() {
		// TODO Auto-generated constructor stub
		grupo = new Grupo();
		insertar_GR = false;
		grupo.setIngles(false);
		grupo.setVisible(true);
	}
	
	public String crearGrupos(){
		grupos.crearGrupos();
		return "lista_grupos.xhtml";
	}
	public String anaidir_Grupo() {
		try {
			Integer titulo = Integer.parseInt(titulacion);
			Titulacion tit = titulaciones.obtenerTitulacion(titulo);	
			grupo.setTitulacion(tit);		
			grupos.insertarGrupo(grupo);
			setInsertar_GR(true);
			return "lista_grupos.xhtml";
		} catch (GrupoYaExistenteException e) {
			FacesMessage message = new FacesMessage("Grupo ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		} catch (TitulacionNoEncontradaException e) {
			FacesMessage message = new FacesMessage("TITULACION NO existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
	}
	public String eliminarGrupo(Grupo gr) {
		try {
			grupos.eliminarGrupo(gr.getCurso(), gr.getLetra(), gr.getTitulacion());
		} catch (GrupoNoEncontradoException e) {
			FacesMessage message = new FacesMessage("Grupo no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String actualizarGrupo() {
		try {
			grupos.actualizarGrupo(grupoviejo, grupo);
			return "lista_grupos.xhtml";
		} catch (GrupoNoEncontradoException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Grupo no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String modoModificarGrupo(Grupo gr) {
		grupoviejo = gr;
		grupo = gr;
		return "editarGrupo.xhtml";
	}
	

	public Grupo buscarGrupos() {
		Grupo grupo = null;
		try {
			Titulacion ti = titulaciones.obtenerTitulacion(Integer.parseInt(titulacion));
			grupo = grupos.obtenerGrupo(Integer.parseInt(curso), letra,ti);
		}catch(GrupoNoEncontradoException e){
			FacesMessage message = new FacesMessage("Grupo no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Dato incorrecto");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (TitulacionNoEncontradaException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Titulacion no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return grupo;
	}
	
	public String varBuscar() {
		buscar = true;
		return null;
	}

	public List<Grupo> getListGrupos() {
		listGrupos = grupos.obtenerGrupos();
		return listGrupos;
	}

	public void setListGrupos(List<Grupo> list_grupos) {
		this.listGrupos = list_grupos;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public boolean isInsertar_GR() {
		return insertar_GR;
	}

	public void setInsertar_GR(boolean insertar_GR) {
		this.insertar_GR = insertar_GR;
	}
	public Boolean getBuscar() {
		return buscar;
	}
	public void setBuscar(Boolean buscar) {
		this.buscar = buscar;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLetra() {
		return letra;
	}
	public void setLetra(String letra) {
		this.letra = letra;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}

}
