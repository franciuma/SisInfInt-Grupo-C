package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import es.uma.informatica.ejb.GestionCentro;

import es.uma.informatica.ejb.exceptions.*;

import es.uma.informatica.jpa.demo.*;

public class Sample_Centro {
	
	private static final Logger LOG = Logger.getLogger(Sample_Centro.class.getCanonicalName());

	private static final String CENTRO_EJB = "java:global/classes/CentroEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionCentro gestionCentro;

	
	@Before
	public void setup() throws NamingException  {
	
		gestionCentro = (GestionCentro) SuiteTest.ctx.lookup(CENTRO_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}

	@Test
	@Ignore
	public void testInsertarCentro() {
				
		final String nombre = "Facultad de Derecho";
		final String direccion = "C/ Alforja Nº27";
		final String tlfConserjeria = "952423547";
		
		try {
			
			Centro derecho = new Centro(nombre, direccion, tlfConserjeria);
			gestionCentro.insertarCentro(derecho);
			
			try {
				
				Centro centro = gestionCentro.obtenerCentro(nombre);
				assertEquals("No es el mismo centro", nombre, centro.getNombre());
			} catch (ProyectoException e) {
				
				fail("NO debería lanzar excepción");
			}
		} catch (ProyectoException e) {
			
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testEliminarCentro() {

		try {
			gestionCentro.eliminarCentro("ETSII Informática");
		} catch(CentroNoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testObtenerCentro() {

		try {
			gestionCentro.obtenerCentro("ETSII Informática");
		} catch(CentroNoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}
	
}
