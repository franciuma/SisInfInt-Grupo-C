package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.imageio.IIOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class MatriculaEJB implements GestionMatricula {
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;

	@Override
	public void insertarMatricula(Matricula mat) throws MatriculaYaExistenteException {
		// TODO Auto-generated method stub
		Matricula matricula= em.find(Matricula.class,mat.getId());
		if(matricula != null) {
			throw new MatriculaYaExistenteException();
		}	
		em.persist(matricula);
	}
	@Override
	public Matricula obtenerMatricula(String cursoAcademico) throws MatriculaNoEncontradaException {
		// TODO iAuto-generated method stub

		Query  matriculas = em.createQuery("Select m from Matricula m where m.cursoAcademico = :cursoAcademico" );
		matriculas.setParameter("cursoAcademico", cursoAcademico);
		List<Matricula> matricula = matriculas.getResultList();
		Matricula mat = matricula.get(0);
		if(matricula == null) {
			throw new MatriculaNoEncontradaException();
		}
		
		return mat;
	}
	
	@Override
	public void eliminarMatricula(String cursoAcademico) throws MatriculaNoEncontradaException {
		Matricula matricula = obtenerMatricula(cursoAcademico);
		em.remove(matricula);
		
	}
	
	@Override
	public void actualizarMatricula(Matricula mat){
		Matricula matri = em.find(Matricula.class,mat.getId());
		matri.setEstado(mat.getEstado());
		matri.setFechaMatricula(mat.getFechaMatricula());
		matri.setListadoAsignaturas(mat.getListadoAsignaturas());
		matri.setNuevoIngreso(mat.getNuevoIngreso());
		matri.setNumArchivo(mat.getNumArchivo());
		matri.setTurnoPreferente(mat.getTurnoPreferente());
		em.merge(matri);
	}
	
	@Override
	public List<Matricula> obtenerMatriculas() {
		List<Matricula> matriculas = em.createQuery("Select * from Matricula").getResultList();
		return matriculas;
	}
//	@Override
//	public void importarAlumnos() throws IOException{
//		// TODO Auto-generated method stub
//		try {
//			String directorio_de_ejecucion_de_la_aplicacion = new java.io.File(".").getCanonicalPath();
//			String sFile = directorio_de_ejecucion_de_la_aplicacion + "/" + "DAtos alumnadoFAKE.xlsx";
//			XSSFWorkbook workbook = new XSSFWorkbook(sFile);
//			XSSFSheet sheet = workbook.getSheet("Hoja 1");
//			XSSFRow row = sheet.getRow(0);
//			XSSFCell cell = null;
//			for(int fila=4; fila<row.getRowNum(); fila++) {
//				Alumno a = new Alumno();
//				String dni = (String) sheet.getRow(fila).getCell(1).getStringCellValue();
//				a.setDni(dni);
//				String nombre = (String) sheet.getRow(fila).getCell(2).getStringCellValue() + " " + (String) sheet.getRow(fila).getCell(3).getStringCellValue() + " " + (String) sheet.getRow(fila).getCell(4).getStringCellValue();
//				a.setNombreCompleto(nombre);
//				String emailIns = (String) sheet.getRow(fila).getCell(7).getStringCellValue();
//				a.setEmailInstitucional(emailIns);
//				String emailPer = (String) sheet.getRow(fila).getCell(8).getStringCellValue();
//				a.setEmailPersonal(emailPer);
//				String telfFijo = (String) sheet.getRow(fila).getCell(9).getStringCellValue();
//				a.setEmailPersonal(telfFijo);
//				String telfMov = (String) sheet.getRow(fila).getCell(10).getStringCellValue();
//				a.setEmailPersonal(telfMov);
//				em.persist(a);
//			}
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
}
