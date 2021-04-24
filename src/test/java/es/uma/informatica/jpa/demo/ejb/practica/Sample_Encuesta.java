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
import es.uma.informatica.ejb.GestionExpediente;
import es.uma.informatica.ejb.exceptions.EncuestaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.EncuestaYaExistenteException;
import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Encuesta;
import es.uma.informatica.jpa.demo.Expediente;

public class Sample_Encuesta {
	
	private static final Logger LOG = Logger.getLogger(Sample_Encuesta.class.getCanonicalName());

	private static final String ENCUESTA_EJB = "java:global/classes/EncuestaEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionEncuesta gestionEncuesta;
	private GestionExpediente gestionExpediente;
	
	@Before
	public void setup() throws NamingException  {
		gestionEncuesta = (GestionEncuesta) SuiteTest.ctx.lookup(ENCUESTA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	
	@Test
	public void TestResponderEncuesta() throws EncuestaYaExistenteException, EncuestaNoEncontradaException {
		
		final Timestamp fechaEnvio = java.sql.Timestamp.valueOf("2021-10-23 10:10:10");
		final String matriculacionIngles = "SI";
		final String turnoPreferente = "Mañana";
		
		try {
			
			Encuesta enc = new Encuesta(fechaEnvio, matriculacionIngles, turnoPreferente);
			gestionEncuesta.responderEncuesta(enc);
			
			try {
				
				Encuesta encuesta = gestionEncuesta.obtenerEncuesta(fechaEnvio);
				assertEquals(fechaEnvio, encuesta.getFechaEnvio());
				assertEquals(matriculacionIngles, encuesta.getMatriculacionIngles());
				assertEquals(turnoPreferente, encuesta.getTurnoPreferente());
			}catch(EncuestaNoEncontradaException e) {
				
				fail("No se ha encontrado la encuesta insertada");
			}
		} catch(EncuestaYaExistenteException e) {
			
			fail("Ya existe una encuesta con esa fecha de envío");
		}
	}
	
	@Test
	public void TestObtenerEncuesta() {
		
		Timestamp fechaEnvio = java.sql.Timestamp.valueOf("2021-04-24 18:23:15");
		
		try {
			
			gestionEncuesta.obtenerEncuesta(fechaEnvio);
		} catch (EncuestaNoEncontradaException e) {
			
			fail("La encuesta no existe");
		}
	}
	
	@Test
	public void testActualizarEncuesta() {
		
		Timestamp fechaEnvio = java.sql.Timestamp.valueOf("2021-04-24 18:23:15");
		
		try {
			
			Encuesta encuesta = gestionEncuesta.obtenerEncuesta(fechaEnvio);
			
			assertEquals(encuesta.getMatriculacionIngles(), "No");
			assertEquals(encuesta.getTurnoPreferente(), "Tarde");
			
			encuesta.setMatriculacionIngles("Si");
			encuesta.setTurnoPreferente("Mañana");
			
			gestionEncuesta.actualizarEncuesta(encuesta);
			
			assertEquals(encuesta.getMatriculacionIngles(), "Si");
			assertEquals(encuesta.getTurnoPreferente(), "Mañana");
		} catch (EncuestaNoEncontradaException e) {
			
			fail("No se ha encontrado la encuesta a actualizar");
		}
	}
	
	@Test
	public void testEliminarEncuesta() {
		
		Timestamp fechaEnvio = java.sql.Timestamp.valueOf("2021-04-24 18:23:15");
		
		try {
			
			gestionEncuesta.eliminarEncuesta(fechaEnvio);
		} catch (EncuestaNoEncontradaException e) {
			
			fail("La encuesta no existe");
		}
	}
}