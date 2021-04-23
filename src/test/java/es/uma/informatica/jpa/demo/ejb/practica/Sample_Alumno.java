package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
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
import org.glassfish.hk2.runlevel.RunLevelException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.ejb.GestionAlumno;
import es.uma.informatica.ejb.GestionClase;


import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.jpa.demo.*;



public class Sample_Alumno {
	
	private static final Logger LOG = Logger.getLogger(Sample_Alumno.class.getCanonicalName());

	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosEJB";
//	private static final String CLASE_EJB = "java:global/classes/ClaseEJB";
//	private static final String CENTRO_EJB = "java:global/classes/CentroEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionAlumno gestionAlumnos;
//	private GestionClase gestionClase;
//	private GestionCentro gestionCentro;

	
	@Before
	public void setup() throws NamingException  {
		gestionAlumnos = (GestionAlumno) SuiteTest.ctx.lookup(ALUMNOS_EJB);
//		gestionClase = (GestionClase) SuiteTest.ctx.lookup(CLASE_EJB);
//		gestionCentro = (GestionCentro) ctx.lookup(CENTRO_EJB);
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
	