package es.uma.informatica.ejb;


import javax.persistence.EntityManager;


import javax.persistence.PersistenceContext;

import javax.ejb.Stateless;

import es.uma.informatica.ejb.exceptions.ClaseNoEncontradaException;
import es.uma.informatica.ejb.exceptions.ClaseYaExistenteException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class ClaseEJB implements GestionClase {
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarClase(Clase clas) throws ClaseYaExistenteException {
		// TODO Auto-generated method stub
		Clase clase = em.find(Clase.class,clas.getId());
		
		if( clase != null) {
			throw new ClaseYaExistenteException();
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
	
	@Override
	public void eliminarClase(Clase_ID id) throws ClaseNoEncontradaException {
		// TODO Auto-generated method stub
		Clase clas = em.find(Clase.class, id);
		if(clas == null) {
			throw new ClaseNoEncontradaException();
		}
		em.remove(clas);
	}
}
