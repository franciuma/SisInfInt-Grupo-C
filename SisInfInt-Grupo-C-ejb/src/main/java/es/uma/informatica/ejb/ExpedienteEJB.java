package es.uma.informatica.ejb;
 
import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;
 
import javax.ejb.Stateless;


import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ExpedienteYaExistenteException;
import es.uma.informatica.jpa.demo.*;
 
 
 
 
@Stateless
public class ExpedienteEJB implements GestionExpediente{
    
    @PersistenceContext(name= "Secretaria")
    private EntityManager em;
    
    @Override
    public void insertarExpediente(Expediente exp) throws ExpedienteYaExistenteException {
        Expediente expediente = em.find(Expediente.class, exp.getNumExpediente());
        if(expediente != null) {
            throw new ExpedienteYaExistenteException();
        } else {
            em.persist(exp);
        }
    }
 
    @Override
    public Expediente obtenerExpediente(Integer numExpediente) throws ExpedienteNoEncontradoException {
        // TODO Auto-generated method stub
    	TypedQuery<Expediente>  expedientes = em.createQuery("Select e from Expediente e where e.numExpediente = :numExpediente" ,Expediente.class);
		expedientes.setParameter("numExpediente", numExpediente);
		List<Expediente> expediente = expedientes.getResultList();
		
		if(expediente == null || expediente.size() == 0) {
			throw new ExpedienteNoEncontradoException();
		}
		Expediente ex = expediente.get(0);
		return ex;
    }
 
    @Override
    public void eliminarExpediente(Expediente ex) throws ExpedienteNoEncontradoException {
        // TODO Auto-generated method stub
        Expediente exp = obtenerExpediente(ex.getNumExpediente());
//        if(exp == null) {
//            throw new ExpedienteNoEncontradoException();
//        }
        em.remove(exp);
    }
 
    @Override
    public void actualizarExpediente(Expediente expediente) throws ExpedienteNoEncontradoException {
        // TODO Auto-generated method stub
        Expediente exp = obtenerExpediente(expediente.getNumExpediente());
        exp.setNotaMediaProvisional(expediente.getNotaMediaProvisional());
        exp.setActivo(expediente.getActivo());
        em.merge(exp);
    }
 
    @Override
    public List<Expediente> obtenerExpedientes() {
        // TODO Auto-generated method stub
    	TypedQuery<Expediente> query = em.createNamedQuery("findAllExpedientes", Expediente.class);
        return query.getResultList();
    }

	@Override
	public void eliminarTodos() {
		// TODO Auto-generated method stub
		for(Expediente ex : obtenerExpedientes()) {
			em.remove(ex);
		}
	}
    
    
    
}