
package es.uma.informatica.ejb;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.AsignaturaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.AsignaturaYaExistenteException;
import es.uma.informatica.ejb.exceptions.MatriculaYaExistenteException;
import es.uma.informatica.jpa.demo.Asignatura;

@Local
public interface GestionAsignatura {

	public void insertarAsignatura(Asignatura asig) throws AsignaturaYaExistenteException;

	public Asignatura obtenerAsignatura(Integer referencia) throws AsignaturaNoEncontradaException;

	public void eliminarAsignatura(Asignatura asi) throws AsignaturaNoEncontradaException;

	public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException;

	public List<Asignatura> obtenerAsignaturas();

	void importarAsignatura(String sFile) throws IOException, ParseException, AsignaturaYaExistenteException;
	
	void eliminarTodas();

}