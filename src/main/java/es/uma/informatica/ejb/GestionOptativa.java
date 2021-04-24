package es.uma.informatica.ejb;
 
import java.util.List;
 
import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.OptativaNoEncontradoException;
import es.uma.informatica.ejb.exceptions.OptativaYaExistenteException;
import es.uma.informatica.jpa.demo.Optativa;
 
@Local
public interface GestionOptativa {
 
    public void insertarOptativa (Optativa opt) throws OptativaYaExistenteException;
    public Optativa obtenerOptativa(Integer referencia) throws OptativaNoEncontradoException;
    public void eliminarOptativa (Integer referencia) throws OptativaNoEncontradoException;
    public List<Optativa> obtenerOptativas();
    void actualizarOptativa(Optativa opt) throws OptativaNoEncontradoException;
    
}