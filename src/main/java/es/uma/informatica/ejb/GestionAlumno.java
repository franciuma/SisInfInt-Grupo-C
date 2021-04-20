package es.uma.informatica.ejb;

import es.uma.informatica.ejb.exceptions.AlumnoNoEncontardoException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Alumno;


public interface GestionAlumno{
	
	/*
	 * Introducir un alumno en la base de datos.
	 */
	public void añadirAlumno (Alumno alumno) throws ProyectoException;
	public Alumno obtenerAlumno(Integer id) throws AlumnoNoEncontardoException;
}
