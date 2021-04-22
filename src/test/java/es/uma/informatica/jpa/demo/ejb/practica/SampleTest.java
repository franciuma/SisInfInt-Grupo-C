package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;

import org.eclipse.persistence.jpa.jpql.Assert.AssertException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.ejb.GestionAlumno;

import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;


import es.uma.informatica.ejb.GestionClase;
<<<<<<< HEAD
import es.uma.informatica.ejb.GestionEncuesta;
import es.uma.informatica.ejb.exceptions.ClaseNoEncontradaException;
import es.uma.informatica.ejb.exceptions.EncuestaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.EncuestaYaExistenteException;
import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;
=======


import es.uma.informatica.ejb.exceptions.*;

>>>>>>> 105133757af8e774b2b1242dedd28c96b6d46240

import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;



public class SampleTest {
	
	private static final Logger LOG = Logger.getLogger(SampleTest.class.getCanonicalName());

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosEJB";
	private static final String CLASE_EJB = "java:global/classes/ClaseEJB";
	private static final String ENCUESTA_EJB = "java:global/classes/EncuestaEJB";
	private static final String CENTRO_EJB = "java:global/classes/CentroEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionAlumno gestionAlumnos;
	private GestionClase gestionClase;
	private GestionEncuesta gestionEncuesta;
	private GestionCentro gestionCentro;
	
	@BeforeClass
	public static void setUpClass() {
		Properties properties = new Properties();
		properties.setProperty(GLASSFISH_CONFIGI_FILE_PROPERTY, CONFIG_FILE);
		ejbContainer = EJBContainer.createEJBContainer(properties);
		ctx = ejbContainer.getContext();
	}
	
	@Before
	public void setup() throws NamingException  {
		gestionAlumnos = (GestionAlumno) ctx.lookup(ALUMNOS_EJB);
		gestionClase = (GestionClase) ctx.lookup(CLASE_EJB);
		gestionEncuesta = (GestionEncuesta) ctx.lookup(ENCUESTA_EJB);
		gestionCentro = (GestionCentro) ctx.lookup(CENTRO_EJB);
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
			gestionAlumnos.insertarAlumno(ale);
			
			try {

	
				Alumno alumno = gestionAlumnos.obtenerAlumno(dni);
//				assertEquals(1, alumno.size());
//				assertEquals(alumno.getNombreCompleto(), nombreCompleto);
				assertEquals("No es el mismo alumno", nombreCompleto,alumno.getNombreCompleto());

			}catch(ProyectoException e) {
				fail("NO deberia lanzar excepcion");
			}
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}

	}
	
	
	
	@Test
	@Ignore
	public void testInsertarClase() {
		
		final Clase_ID id = new Clase_ID();
		final Date hora_inicio = new Date(10^6);
		id.setHoraInicio(hora_inicio);
		final Date hora_fin = new Date(10^9);
		try {
			Clase clase = new Clase(id, hora_fin);
			gestionClase.insertarClase(clase);
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	@Ignore
	public void TestObtenerClase() {
		
	}


	@Test
	public void testInsertarEncuesta() throws EncuestaNoEncontradaException{
		
		final Date fechaEnvio = new Date(116,10,3);
		
		try {
			Encuesta encuesta = new Encuesta(fechaEnvio);
			gestionEncuesta.insertarEncuesta(encuesta);
			
			try {
				Encuesta encuest = gestionEncuesta.obtenerEncuesta(fechaEnvio);
				assertEquals(encuesta.getFechaEnvio(),encuest.getFechaEnvio());
			}catch(EncuestaNoEncontradaException e){
				fail("Excepcion");
			}
			
		}catch(EncuestaYaExistenteException e){
			throw new RuntimeException(e);
		}
		
		
	}
	
	@Test
	@Ignore
	public void testInsertarLoteProductoNoEncontrado() {
		
	}
	
	
	@Test
	public void testEliminarALumno() {

		try {
			gestionAlumnos.eliminarAlumno("1111111D");
		}catch (AlumnoNoEncontradoException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}
	
	@Test
	public void testObtenerAlumno() {
		try {
			gestionAlumnos.obtenerAlumno("1111111D");
		}catch (AlumnoNoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testActualizarAlumno() {
		try {
			Alumno alumno = gestionAlumnos.obtenerAlumno("1111111D");
			assertEquals(alumno.getNombreCompleto(), "Fransi");
			String correo = alumno.getEmailPersonal();
			alumno.setEmailPersonal("cambio");
			gestionAlumnos.actualizarAlumno(alumno);
			assertNotEquals("Deberia haberse actualizado el alumno", correo , alumno.getEmailPersonal());
			
		}catch(AlumnoNoEncontradoException e) {
			throw new RuntimeException(e);
		}
		
	}


	public void testInsertarLoteIngredientesIncorrectos() {


		
	}
	
	@Test
	@Ignore

	public void testInsertarLoteExistente() {

		
	}
	

	
	@Test
	@Ignore
	public void testObtenerLotesProductoNoEncontrado() {
		
	}

	
	@Test
	@Ignore
	public void testActualizarLoteProductoNoEncontrado() {
		
	}
	
	@Test
	@Ignore
	public void testActualizarLoteIngredientesIncorrectos() {
		
	}

	
	@Test
	@Ignore
	public void testEliminarLoteProductoNoEncontrado() {
		
	}
	
	@Test
	@Ignore
	public void testEliminarLoteNoEncontrado() {
		
	}
	
	@Test
	@Ignore
	public void testEliminarTodosLotes() {
		
	}
	
	@Test
	@Ignore
	public void testEliminarTodosLotesProductoNoEncontrado() {
		
	}
	
//	@After
//	public static void comienzo() {
//		final String dni = "12345678D";
//		final String nombreCompleto = "Fransi";
//		final String emailInstitucional = "fransi@uma.es";
//		final String emailPersonal = "fransi@crack.sii";
//		final String telefono = "123654789";
//		final String movil = "95498456563";
//		
//		try {
//			
//			Alumno fransi = new Alumno(dni, nombreCompleto, emailInstitucional, emailPersonal, telefono, movil);
//			gestionAlumnos.insertarAlumno(fransi);
//			
//		}catch(ProyectoException e) {
//			throw new RuntimeException(e);
//		}
//			
//		
//		
//	}
//	
	
	@AfterClass
	public static void tearDownClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}

}
