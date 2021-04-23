package es.uma.informatica.jpa.demo;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Main {

	public static void main(String[] args) {
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
			for(int fila=4; fila<sheet.getLastRowNum(); fila++) {
				Alumno a = new Alumno();
				String dni = (String) sheet.getRow(fila).getCell(0).getStringCellValue();
				a.setDni(dni);
				String nombre = (String) sheet.getRow(fila).getCell(1).getStringCellValue() + " " + (String) sheet.getRow(fila).getCell(2).getStringCellValue() + " " + (String) sheet.getRow(fila).getCell(3).getStringCellValue();
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
					System.out.println(a.toString());
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
