package es.uma.informatica.ejb;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import es.uma.informatica.ejb.exceptions.ClaseNoEncontradaException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class ClaseEJB implements GestionClase {
	
	private static final Logger LOG = Logger.getLogger(ClaseEJB.class.getCanonicalName());
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarClase(Clase clas) throws ProyectoException {
		// TODO Auto-generated method stub
		Clase clase = em.find(Clase.class,clas.getId());
		
		if( clase != null) {
			throw new ProyectoException("Ya esta esa clase");
		}else {
			em.persist(clase);
		}
		 
	}
	@Override
	public Clase obtenerClase(Clase_ID id) throws ClaseNoEncontradaException {
		// TODO iAuto-generated method stub
		Clase clase = em.find(Clase.class, id);
		if(clase == null) {
			throw new ClaseNoEncontradaException();
		}
		return clase;
	}
}
