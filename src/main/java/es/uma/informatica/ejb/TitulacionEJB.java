package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		Titulacion titulacion = em.find(Titulacion.class, codigo);
		if(titulacion == null)
			throw new TitulacionNoEncontradaException();
		em.remove(titulacion);
	}

	@Override
	public void actualizarTitulacion(Titulacion titulacion) throws TitulacionNoEncontradaException {
		// TODO Auto-generated method stub
		Titulacion tit = em.find(Titulacion.class, titulacion.getCodigo());
		tit.setCodigo(titulacion.getCodigo());
		tit.setCreditos(titulacion.getCreditos());
		tit.setNombre(titulacion.getNombre());
		em.merge(titulacion);
	}

	@Override
	public List<Titulacion> obtenerTitulaciones() {
		// TODO Auto-generated method stub
		List<Titulacion> titulaciones = em.createQuery("Select tit from Titulacion tit").getResultList();
		return titulaciones;
	}

}