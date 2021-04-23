package es.uma.informatica.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import es.uma.informatica.ejb.exceptions.CentroYaExistenteException;
import es.uma.informatica.jpa.demo.Centro;

@Stateless
public class CentroEJB implements GestionCentro {
	
	@PersistenceContext(name= "Secretaria2")
	private EntityManager em;

	@Override
	public void insertarCentro(Centro centro) throws CentroYaExistenteException {
		// TODO Auto-generated method stub
		Centro cent = em.find(Centro.class,centro.getId());
		if( cent != null)
			throw new CentroYaExistenteException();
		em.persist(centro);
		
	}
	
}
