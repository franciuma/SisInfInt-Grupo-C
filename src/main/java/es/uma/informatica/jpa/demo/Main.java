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
			String sFile = directorio_de_ejecucion_de_la_aplicacion + "/" + "Oferta asignaturas.xlsx";
			@SuppressWarnings("deprecation")
			XSSFWorkbook workbook = new XSSFWorkbook(sFile);
			XSSFSheet sheet = workbook.getSheet("GII");
			for(int fila=1; fila<83; fila++) {//Con sheet.getLastRowNum() no funciona el test, transaction aborted
				Asignatura a = new Asignatura();
				Integer referencia = (int) Math.round(sheet.getRow(fila).getCell(3).getNumericCellValue());
				a.setReferencia(referencia);
				Integer codigo = (int) Math.round(sheet.getRow(fila).getCell(2).getNumericCellValue());
				a.setCodigo(codigo);
				Integer creditos = (int) Math.round(sheet.getRow(fila).getCell(8).getNumericCellValue());
				a.setCreditos(creditos);
				String ofertada = (String) sheet.getRow(fila).getCell(1).getStringCellValue();
				Boolean ofer = false;
				if(ofertada.equalsIgnoreCase("Sí")) ofer = true;
				a.setOfertada(ofer);
				String nombre = (String) sheet.getRow(fila).getCell(4).getStringCellValue();
				a.setNombre(nombre);
				Integer curso = (int) Math.round(sheet.getRow(fila).getCell(5).getNumericCellValue());
				String cur = curso.toString();
				a.setCurso(cur);
				if(sheet.getRow(fila).getCell(10).getCellType() == 0) {
					Integer carac = (int) Math.round(sheet.getRow(fila).getCell(10).getNumericCellValue());
					a.setCaracter(carac.toString());
				} else {
					String caracter = (String) sheet.getRow(fila).getCell(10).getStringCellValue();
					if(caracter.equalsIgnoreCase("-")) {
						caracter = "Obligatoria";
					} else {
						caracter = "Optativa";
					}
					a.setCaracter(caracter);
				}
				
				
				
				String dur = (String) sheet.getRow(fila).getCell(9).getStringCellValue();
				Integer duracion = 2;
				if(dur.equalsIgnoreCase("1º Semestre")) {
					duracion = 1;
				}
				a.setDuracion(duracion);
				String idiomasIm = (String) sheet.getRow(fila).getCell(11).getStringCellValue();
				a.setIdiomasImparticion(idiomasIm);
				
				System.out.println(a.toString());
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
