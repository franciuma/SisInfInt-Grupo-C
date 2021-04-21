package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;


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

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.ejb.GestionClase;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;



public class SampleTest {
	
	private static final Logger LOG = Logger.getLogger(SampleTest.class.getCanonicalName());

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosEJB";
	private static final String CLASE_EJB = "java:global/classes/ClaseEJB";
	private static final String GLASSFISH_CONFIGI_FILE_PROPERTY = "org.glassfish.ejb.embedded.glassfish.configuration.file";
	private static final String CONFIG_FILE = "target/test-classes/META-INF/domain.xml";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private static EJBContainer ejbContainer;
	private static Context ctx;
	
	private GestionAlumno gestionAlumnos;
	private GestionClase gestionClase;

	
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
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	@Ignore
	public void testInsertarAlumno() {
		
			
			final String dni = "12345678D";
			final String nombreCompleto = "Fransi";
			final String emailInstitucional = "fransi@huevo.le";
			final String emailPersonal = "fransi@easter-egg.si";
			final String telefono = "12365478";
			final String movil = "9549845656";
		try {
			
			Alumno fransi = new Alumno(dni, nombreCompleto, emailInstitucional, emailPersonal, telefono, movil);
			gestionAlumnos.insertarAlumno(fransi);
			
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
		try {
			Alumno alumno = gestionAlumnos.obtenerAlumno(dni);
			assertEquals(alumno.getDni(), dni);
			//assertEquals(nombreCompleto,alumnos.get(0).getNombreCompleto());
		}catch(ProyectoException e) {
			fail("NO deberia lanzar excepcion");
		}
		
		
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testInsertarClase() {
		
		
			final Clase_ID id = new Clase_ID();
			Date horaFin = new Date(2000, 12, 22);
		try {
			
			Clase informatica = new Clase(id,horaFin);
			gestionClase.insertarClase(informatica);
			
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
		try {
			Clase clase = gestionClase.obtenerClase(id);
			assertEquals(clase.getId(),id);
			//assertEquals(nombreCompleto,alumnos.get(0).getNombreCompleto());
		}catch(ProyectoException e) {
			fail("NO deberia lanzar excepcion");
		}
	}

	/*
	@Test
	@Ignore
	public void testInsertarLoteProductoNoEncontrado() {
		
	}
	
	@Test
	@Ignore
	public void testInsertarLoteIngredientesIncorrectos() {

		
	}
	
	@Test
	@Ignore
	public void testInsertarLoteExistente() {

		
	}
	
	@Test
	@Ignore
	public void testObtenerLotes() {
		
	}
	
	@Test
	@Ignore
	public void testObtenerLotesProductoNoEncontrado() {
		
	}
	
	@Test
	@Ignore
	public void testActualizarLote() {
		
		
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
	public void testEliminarLote() {
		
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
	
	*/
	@AfterClass
	public static void tearDownClass() {
		if (ejbContainer != null) {
			ejbContainer.close();
		}
	}

}
