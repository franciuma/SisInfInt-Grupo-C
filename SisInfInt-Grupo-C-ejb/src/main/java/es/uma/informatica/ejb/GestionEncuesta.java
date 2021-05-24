package es.uma.informatica.ejb;

import java.sql.Timestamp;


import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.EncuestaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.EncuestaYaExistenteException;

import es.uma.informatica.jpa.demo.Encuesta;


@Local
public interface GestionEncuesta{
	
	
//	public void insertarEncuesta(Encuesta encuest) throws EncuestaYaExistenteException;
	public Encuesta obtenerEncuesta(Timestamp fechaEnvio) throws EncuestaNoEncontradaException;
	void eliminarEncuesta(Timestamp fechaEnvio) throws EncuestaNoEncontradaException;
	public void actualizarEncuesta(Encuesta encuesta) throws EncuestaNoEncontradaException;
	public void responderEncuesta(Encuesta e) throws EncuestaYaExistenteException;
}