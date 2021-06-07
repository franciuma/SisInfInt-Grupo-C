package es.uma.informatica.ejb;

import es.uma.informatica.ejb.exceptions.*;

<<<<<<< HEAD:SisInfInt-Grupo-C-ejb/src/main/java/es/uma/informatica/ejb/GestionAlumno.java

=======
>>>>>>> 2c21fa8abf028974942618bb3e85f14b74057578:src/main/java/es/uma/informatica/ejb/GestionAlumno.java
import java.io.IOException;
import java.util.List;
import javax.ejb.Local;
import javax.imageio.IIOException;

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
<<<<<<< HEAD:SisInfInt-Grupo-C-ejb/src/main/java/es/uma/informatica/ejb/GestionAlumno.java
	public void importarAlumnos(String sFile) throws IOException, AlumnoYaExistenteException;
	public boolean autenticar(String dni, String contra);
	public void eliminarTodos();

=======
	public void importarAlumnos() throws IOException;
>>>>>>> 2c21fa8abf028974942618bb3e85f14b74057578:src/main/java/es/uma/informatica/ejb/GestionAlumno.java
}
