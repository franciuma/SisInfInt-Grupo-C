package es.uma.informatica.jpa.backing;

import java.io.File;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.ejb.GestionExpediente;
import es.uma.informatica.ejb.GestionMatricula;
import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.AlumnoYaExistenteException;
import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.MatriculaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.MatriculaYaExistenteException;
import es.uma.informatica.jpa.demo.Alumno;
import es.uma.informatica.jpa.demo.Expediente;
import es.uma.informatica.jpa.demo.Matricula;
import es.uma.informatica.jpa.demo.Matricula_ID;

@Named(value="matriculas")
@RequestScoped
public class Matriculas {
	private static final Logger LOGGER = Logger.getLogger(Matriculas.class.getCanonicalName());
	
	@Inject
	private GestionMatricula matriculas;
	//private UploadedFile upload;
	@Inject
	private GestionExpediente expedientes;
	private Part upload;
	public Part getUpload() {
		return upload;
	}
	public void setUpload(Part upload) {
		this.upload = upload;
	}

	private Matricula matricula;
	private Matricula_ID matricula_id;
	private String expediente;
	private String fecha_ingreso;
	private String curso_academico;
	private boolean buscar;
	private Integer nExpediente;
	private Expediente exp;

	private boolean insertar_MA;
	private List<Matricula> listMatriculas;
	
	public String borrarTodas() {
		matriculas.eliminarTodas();
		FacesMessage message = new FacesMessage("Borradas con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}
	
	
//	public String importarMatriculas() throws ParseException {
//
//		String sFile ="/tmp/matriculas.xlsx"; 
//		File filtemp = new File(sFile);
//		filtemp.delete();
//		try {
//			upload.write(sFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			FacesMessage message = new FacesMessage("Error al subir el fichero");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//		}
//
//		try {
//			matriculas.importarMatricula(sFile);
//			filtemp.delete();
//			return "lista_matriculas.xhtml";
//		} catch (MatriculaYaExistenteException e) {
//			// TODO Auto-generated catch block
//			FacesMessage message = new FacesMessage("Matricula ya existente");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			FacesMessage message = new FacesMessage("Error al leer el fichero");
//			FacesContext.getCurrentInstance().addMessage(null, message);
//		}
//
//		filtemp.delete();
//		return null;
//	}
	
	
	public Matriculas() {
		// TODO Auto-generated constructor stub
		matricula = new Matricula();
		matricula_id = new Matricula_ID();
		insertar_MA = false;
	}
	
	
	public String aniadir_Matricula() {
		
		try {
//			Integer exp = Integer.parseInt(expediente);
			exp = expedientes.obtenerExpediente(nExpediente);
			matricula_id.setExpediente(nExpediente);
			matricula_id.setCursoAcademico(matricula.getCursoAcademico());
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = null;
			fecha = formatoDelTexto.parse(fecha_ingreso);
			matricula.setFechaMatricula(fecha);
			matricula_id.setExpediente(exp.getNumExpediente());
			matricula.setId(matricula_id);
			LOGGER.info("Matricula: " + matricula.toString());
			matriculas.insertarMatricula(matricula);
			setInsertar_MA(true);
			return "lista_matriculas.xhtml";
		} catch (MatriculaYaExistenteException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Matricula ya existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Error al leer la fecha de ingreso");
			FacesContext.getCurrentInstance().addMessage(null, message);
		} catch (ExpedienteNoEncontradoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String eliminarMatricula(Matricula mat) {
		try {
			matriculas.eliminarMatricula(mat);
		} catch (MatriculaNoEncontradaException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Matricula no existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String actualizarMatricula() {
		try {
			matriculas.actualizarMatricula(matricula);
			return "lista_matriculas.xhtml";
		} catch (MatriculaNoEncontradaException e) {
			// TODO Auto-generated catch block
			FacesMessage message = new FacesMessage("Matricula no existente");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}

	
	public String modoModificarMatricula(Matricula mat) {
		matricula = mat;
		return "editarMatricula.xhtml";
	}
	
	public String varBuscar() {
		buscar = true;
		return null;
	}
	
	public Matricula buscarMatricula(String curso_academico, String exp) {
		Matricula matricula = null;
		try {
			
			if(!exp.equals("")) {
				Matricula_ID matri = new Matricula_ID(Integer.parseInt(exp),curso_academico);
				matricula = matriculas.obtenerMatricula(curso_academico,matri);
			}
		}catch(MatriculaNoEncontradaException e){
			FacesMessage message = new FacesMessage("Matricula no encontrado");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return matricula;
	}
	
	public List<Matricula> getListMatriculas() {
		listMatriculas = matriculas.obtenerMatriculas();
		return listMatriculas;
	}

	public void setListMatriculas(List<Matricula> list_matriculas) {
		this.listMatriculas = list_matriculas;
	}
	
	public Matricula getMatricula() {
		return matricula;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}

	public boolean isInsertar_MA() {
		return insertar_MA;
	}

	public void setInsertar_MA(boolean insertar_MA) {
		this.insertar_MA = insertar_MA;
	}
	public String getExpediente() {
		return expediente;
	}
	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}
	public String getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(String fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getCurso_academico() {
		return curso_academico;
	}
	public void setCurso_academico(String curso_academico) {
		this.curso_academico = curso_academico;
	}
	public boolean isBuscar() {
		return buscar;
	}
	public void setBuscar(boolean buscar) {
		this.buscar = buscar;
	}
	public Matricula_ID getMatricula_id() {
		return matricula_id;
	}
	public void setMatricula_id(Matricula_ID matricula_id) {
		this.matricula_id = matricula_id;
	}
	public Integer getnExpediente() {
		return nExpediente;
	}
	public void setnExpediente(Integer nExpediente) {
		this.nExpediente = nExpediente;
	}
	


	
	
}
