package es.uma.informatica.ejb;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.Asignatura_MatriculaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.Asignatura_MatriculaYaExistenteException;
import es.uma.informatica.jpa.demo.Asignatura_Matricula;

@Local
public interface GestionAsignatura_Matricula {

	void insertarAsignatura_Matricula(Asignatura_Matricula asig_ma) throws Asignatura_MatriculaYaExistenteException;

	public Asignatura_Matricula obtenerAsignatura_Matricula(Asignatura_Matricula id)
			throws Asignatura_MatriculaNoEncontradaException;

}
