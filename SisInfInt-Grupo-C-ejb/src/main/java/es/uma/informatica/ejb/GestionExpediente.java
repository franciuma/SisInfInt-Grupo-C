package es.uma.informatica.ejb;
 

import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;

import es.uma.informatica.ejb.exceptions.ExpedienteYaExistenteException;

import java.util.List;
 
 
 
import javax.ejb.Local;
import es.uma.informatica.jpa.demo.Expediente;
 
@Local
public interface GestionExpediente {
    
    /*
     * Introducir un alumno en la base de datos.
     */
    public void insertarExpediente (Expediente expediente) throws ExpedienteYaExistenteException;
    public Expediente obtenerExpediente(Integer numExpediente) throws ExpedienteNoEncontradoException;
    public void eliminarExpediente (Expediente ex) throws ExpedienteNoEncontradoException;
    public List<Expediente> obtenerExpedientes();
    void actualizarExpediente(Expediente expediente) throws ExpedienteNoEncontradoException;
    void eliminarTodos();
}