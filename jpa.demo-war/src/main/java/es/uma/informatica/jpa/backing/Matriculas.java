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
import es.uma.informatica.ejb.GestionMatricula;
import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.AlumnoYaExistenteException;
import es.uma.informatica.ejb.exceptions.MatriculaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.MatriculaYaExistenteException;
import es.uma.informatica.jpa.demo.Alumno;
import es.uma.informatica.jpa.demo.Matricula;
import es.uma.informatica.jpa.demo.Matricula_ID;

@Named(value="matriculas")
@RequestScoped
public class Matriculas {
	private static final Logger LOGGER = Logger.getLogger(Matriculas.class.getCanonicalName());
	
	@Inject
	private GestionMatricula matriculas;
	//private UploadedFile upload;
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
	public Matricula_ID getMatricula_id() {
		return matricula_id;
	}
	public void setMatricula_id(Matricula_ID matricula_id) {
		this.matricula_id = matricula_id;
	}

	private boolean insertar_MA;
	private List<Matricula> listMatriculas;
	
	public String borrarTodas() {
		matriculas.eliminarTodas();
		FacesMessage message = new FacesMessage("Borradas con exito");
		FacesContext.getCurrentInstance().addMessage(null, message);
		return null;
	}
	
	
	public String importarMatriculas() throws ParseException {

		String sFile ="/tmp/matriculas.xlsx"; 
		File filtemp = new File(sFile);

		try {
			upload.write(sFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			matriculas.importarMatricula(sFile);
			filtemp.delete();
			return "lista_matriculas.xhtml";
		} catch (MatriculaYaExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		filtemp.delete();
		return null;
	}
	
	
	public Matriculas() {
		// TODO Auto-generated constructor stub
		matricula = new Matricula();
		matricula_id = new Matricula_ID();
		insertar_MA = false;
	}
	
	
	public String aniadir_Matricula() {
		
		try {
			Long exp = Long.parseLong(expediente);
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = null;
			fecha = formatoDelTexto.parse(fecha_ingreso);
			matricula.setFechaMatricula(fecha);
			matricula_id.setExpediente(exp);
			matricula.setId(matricula_id);
			LOGGER.info("Matricula: " + matricula.toString());
			matriculas.insertarMatricula(matricula);
			setInsertar_MA(true);
			return "exitoInsertarMatricula.xhtml";
		} catch (MatriculaYaExistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
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
			return "index.xhtml";
		}
		return null;
	}
	
	public String actualizarMatricula() {
		try {
			matriculas.actualizarMatricula(matricula);
			return "lista_matriculas.xhtml";
		} catch (MatriculaNoEncontradaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public String modoModificarMatricula(Matricula mat) {
		matricula = mat;
		return "editarMatricula.xhtml";
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


	


	
	
}
