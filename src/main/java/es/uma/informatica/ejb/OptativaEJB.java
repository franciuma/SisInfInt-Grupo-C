package es.uma.informatica.ejb;
 
import java.util.List;
 
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
import es.uma.informatica.ejb.exceptions.OptativaNoEncontradoException;
import es.uma.informatica.ejb.exceptions.OptativaYaExistenteException;
import es.uma.informatica.jpa.demo.Optativa;
 
@Stateless
public class OptativaEJB implements GestionOptativa{
 
    @PersistenceContext(name= "Secretaria")
    private EntityManager em;
    
    @Override
    public void insertarOptativa(Optativa opt) throws OptativaYaExistenteException {
        // TODO Auto-generated method stub
        Optativa optativa = em.find(Optativa.class,opt.getReferencia());
        if( optativa != null)
            throw new OptativaYaExistenteException();
        em.persist(opt);
    }
    
    public Optativa obtenerOptativa(Integer referencia) throws OptativaNoEncontradoException{
        Optativa optativa = em.find(Optativa.class, referencia);
        if(optativa == null) {
            throw new OptativaNoEncontradoException();
        }
        return optativa;
    }
    public void eliminarOptativa (Integer referencia) throws OptativaNoEncontradoException{
        Optativa opt = em.find(Optativa.class, referencia);
        if(opt == null) {
            throw new OptativaNoEncontradoException();
        }
        em.remove(opt);
    }
    public List<Optativa> obtenerOptativas(){
        List<Optativa> opt = em.createQuery("Select * from Optativa").getResultList();
        return opt;
    }
    public void actualizarOptativa(Optativa optativa) throws OptativaNoEncontradoException{
        Optativa opt = em.find(Optativa.class, optativa.getReferencia());
        opt.setPlazas(optativa.getPlazas());
        opt.setMencion(optativa.getMencion());
        em.merge(opt);
    }
    
}