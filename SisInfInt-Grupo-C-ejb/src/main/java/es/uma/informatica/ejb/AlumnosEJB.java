package es.uma.informatica.ejb;


import javax.persistence.EntityManager;


import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.jpa.demo.*;



@Stateless
public class AlumnosEJB implements GestionAlumno {
	private static final Logger LOGGER = Logger.getLogger(AlumnosEJB.class.getCanonicalName());
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;

	@Override
	public void insertarAlumno(Alumno alum) throws AlumnoYaExistenteException {
		// TODO Auto-generated method stub
		Alumno alumno = em.find(Alumno.class,alum.getId());
		if (alumno == null ) {
			try {
				obtenerAlumno(alum.getDni());
				throw new AlumnoYaExistenteException();
			} catch (AlumnoNoEncontradoException e) {
				// TODO Auto-generated catch block
				em.persist(alum);
			}
			
		}else throw new AlumnoYaExistenteException();

		 
	}
	@Override
	public Alumno obtenerAlumno(String dni) throws AlumnoNoEncontradoException {
		// TODO iAuto-generated method stub

		TypedQuery<Alumno>  alumnos = em.createQuery("Select e from Alumno e where e.dni = :dni" , Alumno.class);
		alumnos.setParameter("dni", dni);
		List<Alumno> alumno = alumnos.getResultList();
		if(alumno == null || alumno.size() == 0) throw new AlumnoNoEncontradoException();
		Alumno al = alumno.get(0);
		return al;
	}
	
	@Override
	public void eliminarAlumno(String dni) throws AlumnoNoEncontradoException {
		Alumno alumno = obtenerAlumno(dni);
		for(Expediente ex : alumno.getExpedientes()) {
			ex.setAlumno(null);
			ex.setActivo(false);
		}
		em.remove(alumno);
		
	}
	@Override
	public void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException {
		LOGGER.info("DENTRO DEL EJB :--------------> " + alumno.toString());
		Alumno al = obtenerAlumno(alumno.getDni());
		al.setEmailPersonal(alumno.getEmailPersonal());
		al.setMovil(alumno.getMovil());
		al.setTelefono(alumno.getTelefono());
		em.merge(al);
		
		
		
	}
	@Override
	public List<Alumno> obtenerAlumnos() {
		TypedQuery<Alumno> query = em.createNamedQuery("finAllAlumnos", Alumno.class);

		return query.getResultList();
	}
	@Override
	public void importarAlumnos(String sFile) throws IOException, AlumnoYaExistenteException {
		try {
//			String directorio_de_ejecucion_de_la_aplicacion = new java.io.File(".").getCanonicalPath();
//			String sFile = directorio_de_ejecucion_de_la_aplicacion + "/" + "Datos alumnadoFAKE.xlsx";
			LOGGER.info("AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII : " + sFile);
			@SuppressWarnings("deprecation")
			XSSFWorkbook workbook = new XSSFWorkbook(sFile);
			XSSFSheet sheet = workbook.getSheet("Hoja1");
			for(int fila=4; fila<sheet.getLastRowNum(); fila++) {//Con sheet.getLastRowNum() no funciona el test, transaction aborted
				Alumno a = new Alumno();
				String dni = (String) sheet.getRow(fila).getCell(0).getStringCellValue();
				a.setDni(dni);
				String nombre = (String) sheet.getRow(fila).getCell(1).getStringCellValue() + " " 
						+ (String) sheet.getRow(fila).getCell(2).getStringCellValue() + " " 
						+ (String) sheet.getRow(fila).getCell(3).getStringCellValue();
				a.setNombreCompleto(nombre);
				String emailIns = (String) sheet.getRow(fila).getCell(6).getStringCellValue();
				a.setEmailInstitucional(emailIns);
				String emailPer = (String) sheet.getRow(fila).getCell(7).getStringCellValue();
				a.setEmailPersonal(emailPer);
				String telfFijo = (String) sheet.getRow(fila).getCell(8).getStringCellValue();
				a.setTelefono(telfFijo);
				String telfMov = (String) sheet.getRow(fila).getCell(9).getStringCellValue();
				a.setMovil(telfMov);	
				
				
				
				if(a.getDni().length() > 2) {
					//el alumno
					insertarAlumno(a);				
					//el expediente
					String numero_expediente = (String) sheet.getRow(fila).getCell(4).getStringCellValue();
					Integer x = Integer.parseInt(numero_expediente);
					if(em.find(Expediente.class, x) == null) {
						Expediente ex = null;
						if(sheet.getRow(fila).getCell(17).getCellType()==0) {
							Double nota_media = (double) Math.round(sheet.getRow(fila).getCell(17).getNumericCellValue());
							ex = new Expediente(x, true, nota_media);
							LOGGER.info(nota_media.toString());
						} else {
							String nota_media = (String) sheet.getRow(fila).getCell(17).getStringCellValue();
							Double nota_med = Double.parseDouble(nota_media);
							LOGGER.info(nota_med.toString());
							ex = new Expediente(x, true, nota_med);
						}
						ex.setAlumno(a);
						em.persist(ex);
						
						Matricula m = new Matricula();
						String curso = (String) sheet.getRow(0).getCell(1).getStringCellValue();
						m.setCursoAcademico(curso);
						String estado = (String) sheet.getRow(2).getCell(1).getStringCellValue();
						m.setEstado(estado);
						String turno = (String) sheet.getRow(fila).getCell(15).getStringCellValue();
						m.setTurnoPreferente(turno);
						String fecha = (String) sheet.getRow(fila).getCell(14).getStringCellValue();
						DateFormat format = new SimpleDateFormat("DD/MM/YYYY", Locale.ENGLISH);
						Date date = format.parse(fecha);
						m.setFechaMatricula(date);
						String listado = (String) sheet.getRow(fila).getCell(16).getStringCellValue();
						m.setListadoAsignaturas(listado);
						m.setExpediente(ex);
						
						Matricula_ID m_id = new Matricula_ID(x,curso);
						m.setId(m_id);
						em.persist(m);
					}
				}
			}
		} catch(IOException | ParseException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public boolean autenticar(String dni, String nombre) {
		// TODO Auto-generated method stub

		try {
			Alumno al = obtenerAlumno(dni);
			return al.getNombreCompleto().equals(nombre);
		} catch (AlumnoNoEncontradoException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
			
	}
	
	@Override
	public void eliminarTodos() {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = obtenerAlumnos();
		if(alumnos.size() != 0) {
			for (Alumno al : alumnos) {
				try {
					if(!al.getDni().equals("admin")) {
						eliminarAlumno(al.getDni());
					}
				} catch (AlumnoNoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
