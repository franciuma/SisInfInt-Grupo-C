package es.uma.informatica.ejb;

import es.uma.informatica.ejb.exceptions.*;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import javax.ejb.Local;
import es.uma.informatica.jpa.demo.Alumno;

@Local
public interface GestionAlumno{
	
	/*
	 * Introducir un alumno en la base de datos.
	 */
	public void insertarAlumno (Alumno alumno) throws AlumnoYaExistenteException;
	public Alumno obtenerAlumno(String dni) throws AlumnoNoEncontradoException;
	public void eliminarAlumno (String dni) throws AlumnoNoEncontradoException;
	public void actualizarAlumno (Alumno alumno) throws AlumnoNoEncontradoException;
	public List<Alumno> obtenerAlumnos ();
	public void importarAlumnos(String sFile) throws IOException, AlumnoYaExistenteException, ParseException;
	public boolean autenticar(String dni, String contra);
	public void eliminarTodos();

}
