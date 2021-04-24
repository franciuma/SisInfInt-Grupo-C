package es.uma.informatica.ejb;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import es.uma.informatica.ejb.exceptions.MatriculaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.MatriculaYaExistenteException;
import es.uma.informatica.jpa.demo.Matricula;

public interface GestionMatricula {

	public void insertarMatricula(Matricula mat) throws MatriculaYaExistenteException;

	public Matricula obtenerMatricula(String dni) throws MatriculaNoEncontradaException;

	public void actualizarMatricula(Matricula mat) throws MatriculaNoEncontradaException;

	public void eliminarMatricula(String cursoAcademico) throws MatriculaNoEncontradaException;

	public List<Matricula> obtenerMatriculas();

	void importarMatricula() throws IOException, ParseException, MatriculaYaExistenteException;
	

}