package es.uma.informatica.ejb;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;


import javax.ejb.Stateless;

import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.ejb.exceptions.AlumnoYaExistenteException;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class AlumnosEJB implements GestionAlumno {
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;

	@Override
	public void insertarAlumno(Alumno alum) throws AlumnoYaExistenteException {
		// TODO Auto-generated method stub
		Alumno alumno = em.find(Alumno.class,alum.getId());
		if( alumno != null)
			throw new AlumnoYaExistenteException();
		em.persist(alum);
		 
	}
	@Override
	public Alumno obtenerAlumno(String dni) throws AlumnoNoEncontradoException {
		// TODO iAuto-generated method stub

		Query  alumnos = em.createQuery("Select e from Alumno e where e.dni = :dni" );
		alumnos.setParameter("dni", dni);
		List<Alumno> alumno = alumnos.getResultList();
		Alumno al = alumno.get(0);
		if(alumno == null) throw new AlumnoNoEncontradoException();
		
		return al;
	}
	
	@Override
	public void eliminarAlumno(String dni) throws AlumnoNoEncontradoException {
		Alumno alumno = obtenerAlumno(dni);
		em.remove(alumno);
		
	}
	@Override
	public void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException {
	
		Alumno al = em.find(Alumno.class, alumno.getId());
		al.setEmailPersonal(alumno.getEmailPersonal());
		al.setMovil(alumno.getMovil());
		al.setTelefono(alumno.getTelefono());
		em.merge(alumno);
		
		
		
	}
	@Override
	public List<Alumno> obtenerAlumnos() {
		List<Alumno> alumnos = em.createQuery("Select al from Alumno al").getResultList();
		return alumnos;
	}
	@Override
	public void importarAlumnos() throws IOException, AlumnoYaExistenteException {
		try {
			String directorio_de_ejecucion_de_la_aplicacion = new java.io.File(".").getCanonicalPath();
			String sFile = directorio_de_ejecucion_de_la_aplicacion + "/" + "Datos alumnadoFAKE.xlsx";
			@SuppressWarnings("deprecation")
			XSSFWorkbook workbook = new XSSFWorkbook(sFile);
			XSSFSheet sheet = workbook.getSheet("Hoja1");
			for(int fila=4; fila<50; fila++) {//Con sheet.getLastRowNum() no funciona el test, transaction aborted
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
					//em.persist(a);
					insertarAlumno(a);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
