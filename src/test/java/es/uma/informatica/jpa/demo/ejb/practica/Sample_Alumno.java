package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.jpa.demo.*;



public class Sample_Alumno {
	
	private static final Logger LOG = Logger.getLogger(Sample_Alumno.class.getCanonicalName());

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionAlumno gestionAlumno;
	
	@Before
	public void setup() throws NamingException  {
		gestionAlumno = (GestionAlumno) SuiteTest.ctx.lookup(ALUMNOS_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void testInsertarAlumno() {
		
			final String dni = "222222D";
			final String nombreCompleto = "Ale";
			final String emailInstitucional = "Ale@huevo.le";
			final String emailPersonal = "Ale@easter-egg.si";
			final String telefono = "87654321";
			final String movil = "12354682";
			
		try {
			
			Alumno ale = new Alumno(dni, nombreCompleto, emailInstitucional, emailPersonal, telefono, movil);
			gestionAlumno.insertarAlumno(ale);
			
			try {

	
				Alumno alumno = gestionAlumno.obtenerAlumno(dni);
				assertEquals("No es el mismo alumno", nombreCompleto,alumno.getNombreCompleto());
			}catch(ProyectoException e) {
				fail("NO deberia lanzar excepcion");
			}
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}

	}
	
	

	
	@Test
	public void testEliminarALumno() {

		try {
			gestionAlumno.eliminarAlumno("1111111D");
		}catch (AlumnoNoEncontradoException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Test
	public void testObtenerAlumno() {
		try {
			gestionAlumno.obtenerAlumno("1111111D");
		}catch (AlumnoNoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testActualizarAlumno() {
		try {
			Alumno alumno = gestionAlumno.obtenerAlumno("1111111D");
			assertEquals(alumno.getNombreCompleto(), "Fransi");
			String correo = alumno.getEmailPersonal();
			alumno.setEmailPersonal("cambio");
			gestionAlumno.actualizarAlumno(alumno);
			assertNotEquals("Deberia haberse actualizado el alumno", correo , alumno.getEmailPersonal());
			
		}catch(AlumnoNoEncontradoException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Test
	public void testImportarDatosAlumno() throws AlumnoYaExistenteException, IOException {
		final String dni = "02758528E";
		final String nombreCompleto = "Amarilia Espino Melgar";
		final String emailInstitucional = "06105600003@uma.es";
		final String emailPersonal = "AmariliaEspinoMelgar@jourrapide.com";
		final String telefono = "602 758 528";
		final String movil = "602 758 528";
		try {
			Alumno alumno = new Alumno(dni, nombreCompleto, emailInstitucional, emailPersonal, telefono, movil);
			gestionAlumnos.importarAlumnos();
			Alumno a = gestionAlumnos.obtenerAlumno(dni);
			assertEquals("No es el mismo alumno", a,alumno);
		}catch(AlumnoNoEncontradoException e) {
			throw new RunLevelException(e);
		}
	}

}
	