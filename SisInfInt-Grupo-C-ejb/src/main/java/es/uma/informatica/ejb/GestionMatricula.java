package es.uma.informatica.ejb;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.MatriculaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.MatriculaYaExistenteException;
import es.uma.informatica.jpa.demo.Matricula;
import es.uma.informatica.jpa.demo.Matricula_ID;

@Local
public interface GestionMatricula {

	public void insertarMatricula(Matricula mat) throws MatriculaYaExistenteException;

	public Matricula obtenerMatricula(String dni, Matricula_ID mat) throws MatriculaNoEncontradaException;

	public void actualizarMatricula(Matricula mat) throws MatriculaNoEncontradaException;

	public void eliminarMatricula(Matricula mat) throws MatriculaNoEncontradaException;

	public List<Matricula> obtenerMatriculas();

	void importarMatricula(String sFile) throws IOException, ParseException, MatriculaYaExistenteException;

	void eliminarTodas();	
}