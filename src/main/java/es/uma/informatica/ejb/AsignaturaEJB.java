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
import es.uma.informatica.ejb.exceptions.AsignaturaYaExistenteException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Asignatura;


@Stateless
public class AsignaturaEJB implements GestionAsignatura{

	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarAsignatura(Asignatura asig) throws AsignaturaYaExistenteException {
		// TODO Auto-generated method stub
		try {
			Asignatura asignatura = em.find(Asignatura.class, asig.getReferencia());
			if(asignatura != null) {
				throw new AsignaturaYaExistenteException();
			}
			em.persist(asig);
		} catch (ProyectoException e){
			throw new RuntimeException(e);
		}
	}

	@Override
	public Asignatura obtenerAsignatura(Integer referencia) throws AsignaturaNoEncontradaException {
		// TODO Auto-generated method stub
		
		Asignatura asignatura = em.find(Asignatura.class, referencia);
        if(asignatura == null) {
            throw new AsignaturaNoEncontradaException();
        }
        return asignatura;
	}

	@Override
	public void eliminarAsignatura(Integer referencia) throws AsignaturaNoEncontradaException {
		// TODO Auto-generated method stub
		Asignatura asignatura = em.find(Asignatura.class, referencia);
		if(asignatura == null) {
            throw new AsignaturaNoEncontradaException();
        }
		em.remove(asignatura);
	}

	@Override
	public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException{
		// TODO Auto-generated method stub
		Asignatura asi = obtenerAsignatura(asignatura.getReferencia());
		asi.setCaracter(asignatura.getCaracter());
		asi.setCodigo(asignatura.getCodigo());
        asi.setCreditos(asignatura.getCreditos());
        asi.setUnidadTemporal(asignatura.getUnidadTemporal());
        asi.setCurso(asignatura.getCurso());
        asi.setOfertada(asignatura.getOfertada());
        asi.setIdiomasImparticion(asignatura.getIdiomasImparticion());
        asi.setNombre(asignatura.getNombre());
        asi.setDuracion(asignatura.getDuracion());
        em.merge(asi);
	}

	@Override
	public List<Asignatura> obtenerAsignaturas() {
		// TODO Auto-generated method stub
		TypedQuery<Asignatura> query = em.createQuery("finAllAsignatura", Asignatura.class);
		return query.getResultList();
	}
	
	@Override
	public void importarAsignatura() throws IOException, AsignaturaYaExistenteException {
		try {
			String directorio_de_ejecucion_de_la_aplicacion = new java.io.File(".").getCanonicalPath();
			String sFile = directorio_de_ejecucion_de_la_aplicacion + "/" + "Oferta asignaturas.xlsx";
			@SuppressWarnings("deprecation")
			XSSFWorkbook workbook = new XSSFWorkbook(sFile);
			XSSFSheet sheet = workbook.getSheet("GII");
			for(int fila=1; fila<sheet.getLastRowNum(); fila++) {//Con sheet.getLastRowNum() no funciona el test, transaction aborted
				if(sheet.getRow(fila).getCell(3).getCellType()==0) {
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
					insertarAsignatura(a);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}