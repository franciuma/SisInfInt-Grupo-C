package es.uma.informatica.ejb;

import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;

import javax.ejb.Local;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Alumno;

@Local
public interface GestionAlumno{
	
	/*
	 * Introducir un alumno en la base de datos.
	 */
	public void insertarAlumno (Alumno alumno) throws ProyectoException;
	public Alumno obtenerAlumno(String dni) throws AlumnoNoEncontradoException;
	public void eliminarAlumno (String dni) throws AlumnoNoEncontradoException;
}
