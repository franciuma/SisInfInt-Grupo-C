package es.uma.informatica.ejb;

import java.io.IOException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class MatriculaEJB implements GestionMatricula {
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;

	@Override
	public void insertarMatricula(Matricula mat) throws MatriculaYaExistenteException {
		// TODO Auto-generated method stub
		Matricula matricula= em.find(Matricula.class, mat.getId());
		if(matricula != null) {
			throw new MatriculaYaExistenteException();
		}	
		em.persist(mat);
	}
	@Override
	public Matricula obtenerMatricula(String cursoAcademico) throws MatriculaNoEncontradaException {
		// TODO iAuto-generated method stub

		TypedQuery<Matricula>  matriculas = em.createQuery("Select m from Matricula m where m.cursoAcademico = :cursoAcademico" , Matricula.class);
		matriculas.setParameter("cursoAcademico", cursoAcademico);
		List<Matricula> matricula = matriculas.getResultList();
		Matricula mat = matricula.get(0);
		if(mat == null) {
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
	public void actualizarMatricula(Matricula mat) throws MatriculaNoEncontradaException{
		Matricula matri = obtenerMatricula(mat.getCursoAcademico());
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
		TypedQuery<Matricula> query = em.createQuery("finAllMatricula", Matricula.class);
		return query.getResultList();
	}
	
	@Override
	public void importarMatricula() throws IOException, ParseException, MatriculaYaExistenteException {
		try {
			String directorio_de_ejecucion_de_la_aplicacion = new java.io.File(".").getCanonicalPath();
			String sFile = directorio_de_ejecucion_de_la_aplicacion + "/" + "Datos alumnadoFAKE.xlsx";
			@SuppressWarnings("deprecation")
			XSSFWorkbook workbook = new XSSFWorkbook(sFile);
			XSSFSheet sheet = workbook.getSheet("Hoja1");
			for(int fila=4; fila<sheet.getLastRowNum(); fila++) {//Con sheet.getLastRowNum() no funciona el test, transaction aborted
				if(sheet.getRow(fila).getCell(0).getStringCellValue().length() > 2) {
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
					String numero_expediente = (String) sheet.getRow(fila).getCell(4).getStringCellValue();
					Long x = Long.parseLong(numero_expediente);
					Matricula_ID m_id = new Matricula_ID(x);
					m.setId(m_id);
					insertarMatricula(m);
					//importarMatricula(m);
					//System.out.println(m.toString());
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
