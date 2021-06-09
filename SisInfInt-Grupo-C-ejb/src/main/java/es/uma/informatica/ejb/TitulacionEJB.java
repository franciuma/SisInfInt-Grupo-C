package es.uma.informatica.ejb;

import java.io.IOException;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.uma.informatica.ejb.exceptions.AsignaturaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.TitulacionNoEncontradaException;
import es.uma.informatica.ejb.exceptions.TitulacionYaExistenteException;
import es.uma.informatica.jpa.demo.Asignatura;
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
	public void importarTitulacion(String sFile) throws IOException, TitulacionYaExistenteException {
		// TODO Auto-generated method stub
		try {
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
			throw new IOException("ERROR AL LEER EL DICHERO");
		}
	}

	@Override
	public void eliminarTodas() {
		// TODO Auto-generated method stub
		List<Titulacion> titulaciones = obtenerTitulaciones();
		if(titulaciones.size() != 0) {
			for (Titulacion tit : titulaciones) {
				try {
					eliminarTitulacion(tit.getCodigo());
				} catch (TitulacionNoEncontradaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}	
}