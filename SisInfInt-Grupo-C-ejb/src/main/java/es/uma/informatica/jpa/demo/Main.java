package es.uma.informatica.jpa.demo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

	public static void main(String[] args) throws ParseException {
//		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa.demo");
//		EntityManager em = emf.createEntityManager();
//		
//		em.close();
//		emf.close();
//		System.out.println("Se ha generado el esquema DDL");
		
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
					String listado = (String) sheet.getRow(2).getCell(1).getStringCellValue();
					m.setListadoAsignaturas(listado);
					//em.persist(m);
					//importarMatricula(m);
					System.out.println(m.toString());
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
