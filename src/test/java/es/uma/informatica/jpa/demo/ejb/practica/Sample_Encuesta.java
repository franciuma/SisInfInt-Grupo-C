package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;

import es.uma.informatica.ejb.GestionEncuesta;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Encuesta;

public class Sample_Encuesta {
	
	private static final Logger LOG = Logger.getLogger(Sample_Encuesta.class.getCanonicalName());

	private static final String ENCUESTA_EJB = "java:global/classes/EncuestaEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionEncuesta gestionEncuesta;
	
	@Before
	public void setup() throws NamingException  {
		gestionEncuesta = (GestionEncuesta) SuiteTest.ctx.lookup(ENCUESTA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void TestInsertarEncuesta() {
		
		final Timestamp fechaEnvio = java.sql.Timestamp.valueOf("2021-10-23 10:10:10");
		
		try {
			
			Encuesta enc = new Encuesta(fechaEnvio);
			gestionEncuesta.insertarEncuesta(enc);
			
			try {
				
				Encuesta encuesta = gestionEncuesta.obtenerEncuesta(fechaEnvio);
				assertEquals(fechaEnvio, encuesta.getFechaEnvio());
			}catch(ProyectoException e) {
				fail("NO deberia lanzar excepcion");
			}
		} catch(ProyectoException e) {
			
			throw new RuntimeException(e);
		}
	}
}