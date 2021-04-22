package es.uma.informatica.ejb;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.ClaseNoEncontradaException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Clase;
import es.uma.informatica.jpa.demo.Clase_ID;


@Local
public interface GestionClase{
	
	/*
	 * Introducir un alumno en la base de datos.
	 */
	public void insertarClase(Clase clas) throws ProyectoException;
	public Clase obtenerClase(Clase_ID id) throws ClaseNoEncontradaException;
	void eliminarClase(Clase_ID id) throws ClaseNoEncontradaException;
}
