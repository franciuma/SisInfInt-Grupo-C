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
import es.uma.informatica.ejb.GestionCentro;
import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;


import es.uma.informatica.ejb.GestionClase;


import es.uma.informatica.ejb.exceptions.*;


import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;



public class Sample_Centro {
	
	private static final Logger LOG = Logger.getLogger(Sample_Centro.class.getCanonicalName());

//	private static final String ALUMNOS_EJB = "java:global/classes/AlumnosEJB";
//	private static final String CLASE_EJB = "java:global/classes/ClaseEJB";
	private static final String CENTRO_EJB = "java:global/classes/CentroEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
//	private GestionAlumno gestionAlumnos;
//	private GestionClase gestionClase;
	private GestionCentro gestionCentro;

	
	@Before
	public void setup() throws NamingException  {
//		gestionAlumnos = (GestionAlumno) SuiteTest.ctx.lookup(ALUMNOS_EJB);
//		gestionClase = (GestionClase) SuiteTest.ctx.lookup(CLASE_EJB);
		gestionCentro = (GestionCentro) SuiteTest.ctx.lookup(CENTRO_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	@Ignore
	public void testInsertarCentro() {
				
		final String nombre = "malaga";
		try {
			
			Centro centro = new Centro(nombre);
			gestionCentro.insertarCentro(centro);
		}catch(CentroYaExistenteException e) {
			throw new RuntimeException(e);
		}

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
	

}
