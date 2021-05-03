package es.uma.informatica.ejb;

import java.io.IOException;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uma.informatica.ejb.exceptions.TitulacionNoEncontradaException;
import es.uma.informatica.ejb.exceptions.TitulacionYaExistenteException;
import es.uma.informatica.jpa.demo.Titulacion;

@Stateless
public class TitulacionEJB implements GestionTitulacion {
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarTitulacion(Titulacion tit) throws TitulacionYaExistenteException {
		// TODO Auto-generated method stub
		Titulacion titulacion = em.find(Titulacion.class, tit.getCodigo());
		if(titulacion != null)
			throw new TitulacionYaExistenteException();
		em.persist(tit);
	}

	@Override
	public Titulacion obtenerTitulacion(Integer codigo) throws TitulacionNoEncontradaException {
		// TODO Auto-generated method stub
		Titulacion titulacion = em.find(Titulacion.class, codigo);
		if(titulacion == null)
			throw new TitulacionNoEncontradaException();
		
		return titulacion;
	}

	@Override
	public void eliminarTitulacion(Integer codigo) throws TitulacionNoEncontradaException {
		// TODO Auto-generated method stub
		Titulacion titulacion = obtenerTitulacion(codigo);
		if(titulacion == null)
			throw new TitulacionNoEncontradaException();
		em.remove(titulacion);
	}

	@Override
	public void actualizarTitulacion(Titulacion titulacion) throws TitulacionNoEncontradaException {
		// TODO Auto-generated method stub
		Titulacion tit = obtenerTitulacion(titulacion.getCodigo());
		tit.setCodigo(titulacion.getCodigo());
		tit.setCreditos(titulacion.getCreditos());
		tit.setNombre(titulacion.getNombre());
		em.merge(titulacion);
	}

	@Override
	public List<Titulacion> obtenerTitulaciones() {
		// TODO Auto-generated method stub
		TypedQuery<Titulacion> query = em.createNamedQuery("findAllTitulaciones", Titulacion.class);
		return query.getResultList();
	}

	@Override
	public void importarTitulacion() throws TitulacionYaExistenteException {
		// TODO Auto-generated method stub
		try {
			String directorio_de_ejecucion_de_la_aplicacion = new java.io.File(".").getCanonicalPath();
			String sFile = directorio_de_ejecucion_de_la_aplicacion + "/" + "Titulacion.xlsx";
			@SuppressWarnings("deprecation")
			XSSFWorkbook workbook = new XSSFWorkbook(sFile);
			XSSFSheet sheet = workbook.getSheet("Hoja1");
			for(int fila=1; fila<sheet.getLastRowNum(); fila++) {//Con sheet.getLastRowNum() no funciona el test, transaction aborted
				Titulacion t = new Titulacion();
				String nombre = (String) sheet.getRow(fila).getCell(1).getStringCellValue();
				t.setNombre(nombre);
				Integer codigo = (int) sheet.getRow(fila).getCell(0).getNumericCellValue();
				t.setCodigo(codigo);
				Integer creditos = (int) sheet.getRow(fila).getCell(2).getNumericCellValue();
				t.setCreditos(creditos);
				if(nombre.length() > 2) {
					//em.persist(a);
					insertarTitulacion(t);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}		
}