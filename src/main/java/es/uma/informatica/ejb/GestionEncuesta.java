package es.uma.informatica.ejb;

import java.time.format.DateTimeFormatter;
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
	public Encuesta obtenerEncuesta(DateTimeFormatter fechaEnvio) throws EncuestaNoEncontradaException;
	void eliminarEncuesta(DateTimeFormatter fechaEnvio) throws EncuestaNoEncontradaException;
	public void actualizarEncuesta(Encuesta encuesta) throws EncuestaNoEncontradaException;
}