package es.uma.informatica.ejb;

import java.util.Date;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.EncuestaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.EncuestaYaExistenteException;

import es.uma.informatica.jpa.demo.Encuesta;


@Local
public interface GestionEncuesta{
	
	/*
	 * Introducir un alumno en la base de datos.
	 */
	public void insertarEncuesta(Encuesta encuest) throws EncuestaYaExistenteException;
	public Encuesta obtenerEncuesta(Date fechaEnvio) throws EncuestaNoEncontradaException;
	void eliminarEncuesta(Date fechaEnvio) throws EncuestaNoEncontradaException;
	public void actualizarEncuesta(Encuesta encuesta) throws EncuestaNoEncontradaException;
}
