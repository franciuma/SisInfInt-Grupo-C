package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import es.uma.informatica.ejb.exceptions.CentroNoEncontradoException;
import es.uma.informatica.ejb.exceptions.CentroYaExistenteException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Centro;

@Stateless
public class CentroEJB implements GestionCentro {

	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarCentro(Centro cen) throws CentroYaExistenteException {
		// TODO Auto-generated method stub
		Centro centro = em.find(Centro.class, cen.getId());
		if(centro != null) 
			throw new CentroYaExistenteException();
		else 
			em.persist(cen);
	}

	@Override
	public Centro obtenerCentro(String nombre) throws CentroNoEncontradoException {
		// TODO Auto-generated method stub
		
		Query centros = em.createQuery("Select e from Centro e where e.nombre = :nombre");
		centros.setParameter("nombre", nombre);
		List<Centro> centro = centros.getResultList();
		Centro cen = centro.get(0);
		if(centro == null) throw new CentroNoEncontradoException();
		
		return cen;
	}

	@Override
	public void eliminarCentro(String nombre) throws CentroNoEncontradoException {
		// TODO Auto-generated method stub
		Centro centro = obtenerCentro(nombre);
		em.remove(centro);
	}

	@Override
	public void actualizarCentro(Centro centro) throws CentroNoEncontradoException {
		// TODO Auto-generated method stub
		Centro cen = em.find(Centro.class, centro.getNombre());
		cen.setNombre(centro.getNombre());
		cen.setDireccion(centro.getDireccion());
		cen.setTlfConserjeria(centro.getTLF_Conserjeria());
		em.merge(centro);
	}

	@Override
	public List<Centro> obtenerCentros() {
		// TODO Auto-generated method stub
		List<Centro> centros = em.createQuery("Select cen from Centro cen").getResultList();
		return centros;
	}
}