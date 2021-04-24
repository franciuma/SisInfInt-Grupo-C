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
import es.uma.informatica.ejb.GestionAsignatura;
import es.uma.informatica.ejb.GestionClase;


import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.jpa.demo.*;



public class Sample_Asignatura {
	
	private static final Logger LOG = Logger.getLogger(Sample_Alumno.class.getCanonicalName());

	private static final String ASIGNATURA_EJB = "java:global/classes/AsignaturaEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionAsignatura gestionAsignatura;
	
	@Before
	public void setup() throws NamingException  {
		gestionAsignatura = (GestionAsignatura) SuiteTest.ctx.lookup(ASIGNATURA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void testInsertarAsignatura() {
		final Integer referencia = 50658;
		final Integer codigo = 101;
		final Integer creditos = 6;
		final Boolean ofertada = true;
		final String nombre = "Cálculo para la Computación";
		final String curso = "1";
		final String caracter = "Obligatoria";
		final Integer duracion = 1;
		final String idiomasIm = "No";
		try {
			Asignatura calculo = new Asignatura(referencia, codigo, creditos, ofertada, nombre, curso, caracter, duracion, idiomasIm);
			gestionAsignatura.insertarAsignatura(calculo);
			
			try {
				Asignatura cal = gestionAsignatura.obtenerAsignatura(referencia);
				assertEquals("No es la misma asignatura", nombre, cal.getNombre());
				
			} catch(ProyectoException e) {
				fail("No deberia lanzar exception");
			}
		} catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testImportarDatosAsignatura() throws AsignaturaYaExistenteException, IOException, AsignaturaNoEncontradaException {
		final Integer referencia = 50658;
		final Integer codigo = 101;
		final Integer creditos = 6;
		final Boolean ofertada = true;
		final String nombre = "Cálculo para la Computación";
		final String curso = "1";
		final String caracter = "Obligatoria";
		final Integer duracion = 1;
		final String idiomasIm = "";
		try {
			Asignatura calculo = new Asignatura(referencia, codigo, creditos, ofertada, nombre, curso, caracter, duracion, idiomasIm);
			gestionAsignatura.importarAsignatura();
			Asignatura c = gestionAsignatura.obtenerAsignatura(referencia);
			assertEquals("No es la misma asignatura", c,calculo);
		}catch(AsignaturaYaExistenteException e) {
			throw new RunLevelException(e);
		}
	}

}