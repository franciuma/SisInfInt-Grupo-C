
package es.uma.informatica.ejb;

import java.util.List;

import es.uma.informatica.ejb.exceptions.AsignaturaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.AsignaturaYaExistenteException;
import es.uma.informatica.jpa.demo.Asignatura;
import es.uma.informatica.jpa.demo.Centro;

public interface GestionAsignatura {

	public void insertarAsignatura(Asignatura asig) throws AsignaturaYaExistenteException;

	public Asignatura obtenerAsignatura(Integer referencia) throws AsignaturaNoEncontradaException;

	public void eliminarAsignatura(Integer referencia) throws AsignaturaNoEncontradaException;

	public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;

	public List<Asignatura> obtenerAsignaturas();

}