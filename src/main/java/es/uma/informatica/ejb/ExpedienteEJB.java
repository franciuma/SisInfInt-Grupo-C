package es.uma.informatica.ejb;
 
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
 
import java.util.List;
import java.util.logging.Logger;
 
import javax.ejb.Stateless;
 
import es.uma.informatica.ejb.exceptions.ClaseNoEncontradaException;
import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ExpedienteYaExistenteException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
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
        Expediente expediente = em.find(Expediente.class, numExpediente);
        if(expediente == null) {
            throw new ExpedienteNoEncontradoException();
        }
        return expediente;
    }
 
    @Override
    public void eliminarExpediente(Integer numExpediente) throws ExpedienteNoEncontradoException {
        // TODO Auto-generated method stub
        Expediente exp = em.find(Expediente.class, numExpediente);
        if(exp == null) {
            throw new ExpedienteNoEncontradoException();
        }
        em.remove(exp);
    }
 
    @Override
    public void actualizarExpediente(Expediente expediente) throws ExpedienteNoEncontradoException {
        // TODO Auto-generated method stub
        Expediente exp = em.find(Expediente.class, expediente.getNumExpediente());
        exp.setNotaMediaProvisional(expediente.getNotaMediaProvisional());
        exp.setActivo(expediente.getActivo());
        em.merge(expediente);
    }
 
    @Override
    public List<Expediente> obtenerExpedientes() {
        // TODO Auto-generated method stub
        List<Expediente> exp = em.createQuery("Select al from Expediente al").getResultList();
        return exp;
    }
    
    
    
}