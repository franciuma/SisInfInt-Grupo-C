package es.uma.informatica.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import es.uma.informatica.ejb.exceptions.CentroNoEncontradoException;
import es.uma.informatica.ejb.exceptions.CentroYaExistenteException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Centro;
import es.uma.informatica.jpa.demo.Grupo;

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
		em.persist(cen);
	}

	@Override
	public Centro obtenerCentro(String nombre,String direccion,String tlfConserjeria) throws CentroNoEncontradoException {
		// TODO Auto-generated method stub
		
		TypedQuery<Centro> centros = em.createQuery("Select c from Centro c where c.nombre = :nombre AND c.direccion = :direccion AND c.tlfConserjeria = :tlfConserjeria",Centro.class );
		centros.setParameter("nombre", nombre);
		centros.setParameter("direccion", direccion);
		centros.setParameter("tlfConserjeria", tlfConserjeria);
		List<Centro> centro = centros.getResultList();
		Centro cen = centro.get(0);
		if(centro == null) throw new CentroNoEncontradoException();
		
		return cen;
	}

	@Override
	public void eliminarCentro(String nombre,String direccion,String tlfConserjeria) throws CentroNoEncontradoException {
		// TODO Auto-generated method stub
		Centro centro = obtenerCentro(nombre,direccion,tlfConserjeria);
		em.remove(centro);
	}

	@Override
	public void actualizarCentro(Centro centro, Centro centro_new) throws CentroNoEncontradoException {
		// TODO Auto-generated method stub
		Centro cen = obtenerCentro(centro.getNombre(), centro.getDireccion(), centro.getTLF_Conserjeria());
		cen.setNombre(centro_new.getNombre());
		cen.setDireccion(centro_new.getDireccion());
		cen.setTlfConserjeria(centro_new.getTLF_Conserjeria());
		em.merge(cen);
	}

	@Override
	public List<Centro> obtenerCentros() {
		// TODO Auto-generated method stub
		List<Centro> centros = em.createQuery("Select * from Centro").getResultList();
		return centros;
	}
}