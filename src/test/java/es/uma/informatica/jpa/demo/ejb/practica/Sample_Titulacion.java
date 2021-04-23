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
import es.uma.informatica.ejb.GestionTitulacion;
import es.uma.informatica.ejb.exceptions.*;


import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;



public class Sample_Titulacion {
	
	private static final Logger LOG = Logger.getLogger(Sample_Centro.class.getCanonicalName());

	private static final String TITULACION_EJB = "java:global/classes/TitulacionEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionTitulacion gestionTitulacion;

	
	@Before
	public void setup() throws NamingException  {
		gestionTitulacion = (GestionTitulacion) SuiteTest.ctx.lookup(TITULACION_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	public void testInsertarTitulacion() {
		
		final Integer codigo = 123456;
		final String nombre = "Grado en Ingenieria Informatica";
		final Integer creditos = 6;
		
		try {
			Titulacion info = new Titulacion(codigo, nombre, creditos);
			gestionTitulacion.insertarTitulacion(info);
		} catch (TitulacionYaExistenteException e) {
			
			fail("NO debería lanzar excepción");
		}
		
		List<Titulacion> titulaciones = gestionTitulacion.obtenerTitulaciones();
		assertEquals(1, titulaciones.size());
		assertEquals(codigo, titulaciones.get(0).getCodigo());
		assertEquals(nombre, titulaciones.get(0).getNombre());
		assertEquals(creditos, titulaciones.get(0).getCreditos());
	}
	
	@Test(expected = TitulacionYaExistenteException.class)
	public void testInsertarTitulacionYaExistente() throws TitulacionYaExistenteException {
		
		final Integer codigo = 56789;
		final String nombre = "Grado en Ingeniería del Software";
		final Integer creditos = 6;
		
		try {
		Titulacion soft = new Titulacion(codigo, nombre, creditos);
		gestionTitulacion.insertarTitulacion(soft);
		} catch (TitulacionYaExistenteException e) {
			
			fail("NO debería lanzar excepción aún");
		}
		
		final Integer codigo2 = 56789;
		final String nombre2 = "Grado en Ingeniería del Software";
		final Integer creditos2 = 6;
		
		Titulacion soft2 = new Titulacion(codigo2, nombre2, creditos2);
		gestionTitulacion.insertarTitulacion(soft2);
	}
	
	@Test
	public void testEliminarTitulacion() {
		
		final Integer codigo = 12345;
		final String nombre = "Grado en Ingeniería Informática";
		final Integer creditos = 6;
		
		try {
			Titulacion info = new Titulacion(codigo, nombre, creditos);
			gestionTitulacion.insertarTitulacion(info);
		} catch (TitulacionYaExistenteException e) {
			
			fail("NO debería lanzar excepción");
		}
		
		List<Titulacion> titulaciones = gestionTitulacion.obtenerTitulaciones();
		assertEquals(1, titulaciones.size());
		
		try {

			gestionTitulacion.eliminarTitulacion(codigo);
			List<Titulacion> titulaciones2 = gestionTitulacion.obtenerTitulaciones();
			assertEquals(0, titulaciones2.size());
		} catch (TitulacionNoEncontradaException e) {
			
			fail("NO debería lanzar excepción");
		}
	}

}