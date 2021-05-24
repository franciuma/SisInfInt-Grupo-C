package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Test;


import es.uma.informatica.ejb.GestionExpediente;
import es.uma.informatica.ejb.exceptions.CentroNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ExpedienteYaExistenteException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Expediente;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class Sample_Expediente {
	
	private static final Logger LOG = Logger.getLogger(Sample_Expediente.class.getCanonicalName());

	private static final String EXPEDIENTE_EJB = "java:global/classes/ExpedienteEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionExpediente gestionExpediente;
	
	@Before
	public void setup() throws NamingException  {
		gestionExpediente = (GestionExpediente) SuiteTest.ctx.lookup(EXPEDIENTE_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	@Requisitos({"RF14"})
	@Test
	public void testInsertarExpediente() {
			
			final Integer numExpediente = 22;
			final Boolean activo = true;
			final Integer notaMediaProvisional = 8;
			
		try {
			
			Expediente exp1 = new Expediente(numExpediente,activo,notaMediaProvisional);
			gestionExpediente.insertarExpediente(exp1);
		try {
			Expediente exp = gestionExpediente.obtenerExpediente(numExpediente);
			assertEquals(numExpediente, exp.getNumExpediente());
			assertEquals(activo, exp.getActivo());
			assertEquals(notaMediaProvisional, exp.getNotaMediaProvisional());
			}catch(ProyectoException e) {
				fail("NO deberia lanzar excepcion");
			}
			}catch(ProyectoException e){
				throw new RuntimeException(e);
			}
		
	}
	@Requisitos({"RF14"})
	@Test
	public void testObtenerExpediente() {

		try {
			gestionExpediente.obtenerExpediente(11);
		} catch(ExpedienteNoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}
	@Requisitos({"RF14"})
	@Test
	public void testEliminarExpediente() {

		try {
			gestionExpediente.eliminarExpediente(11);
		}catch (ExpedienteNoEncontradoException e) {
			throw new RuntimeException(e);
		}
		
	}
}
