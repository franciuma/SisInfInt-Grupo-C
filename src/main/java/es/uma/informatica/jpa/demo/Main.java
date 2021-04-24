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
			String sFile = directorio_de_ejecucion_de_la_aplicacion + "/" + "Titulacion.xlsx";
			@SuppressWarnings("deprecation")
			XSSFWorkbook workbook = new XSSFWorkbook(sFile);
			XSSFSheet sheet = workbook.getSheet("Hoja1");
			for(int fila=1; fila<6; fila++) {//Con sheet.getLastRowNum() no funciona el test, transaction aborted
				Titulacion t = new Titulacion();
				String nombre = (String) sheet.getRow(fila).getCell(1).getStringCellValue();
				t.setNombre(nombre);
				Integer codigo = (int) sheet.getRow(fila).getCell(0).getNumericCellValue();
				t.setCodigo(codigo);
				Integer creditos = (int) sheet.getRow(fila).getCell(2).getNumericCellValue();
				t.setCreditos(creditos);
				if(nombre.length() > 2) {
					//em.persist(a);
					System.out.println(t.toString());
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
