package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
import es.uma.informatica.sii.anotaciones.Requisitos;

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

	@Requisitos({"RF13"})
	@Test
	public void testInsertarCentro() {
				
		final String nombre = "Facultad de Derecho";
		final String direccion = "C/ Alforja Nº27";
		final String tlfConserjeria = "952423547";
		
		try {
			
			Centro derecho = new Centro(nombre, direccion, tlfConserjeria);
			gestionCentro.insertarCentro(derecho);
			
			try {
				
				Centro centro = gestionCentro.obtenerCentro(nombre,direccion,tlfConserjeria);
				assertEquals(nombre,centro.getNombre());
				assertEquals(direccion,centro.getDireccion());
				assertEquals(tlfConserjeria,centro.getTLF_Conserjeria());
			} catch (ProyectoException e) {
				
				fail("NO debería lanzar excepción");
			}
		} catch (ProyectoException e) {
			
			throw new RuntimeException(e);
		}
	}
	@Requisitos({"RF13"})
	@Test
	public void testEliminarCentro() {

		try {
			gestionCentro.eliminarCentro("ETSII Informática","Boulevar Louis Pasteur Nº24","952345678");
		} catch(CentroNoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Requisitos({"RF13"})
	@Test
	public void testObtenerCentro() {

		try {
			gestionCentro.obtenerCentro("ETSII Informática","Boulevar Louis Pasteur Nº24","952345678");
		} catch(CentroNoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}
	@Requisitos({"RF13"})
	@Test
	public void testActualizarCentro() {
		try {
			Centro centro = gestionCentro.obtenerCentro("ETSII Informática","Boulevar Louis Pasteur Nº24","952345678");
			Centro centro_new = new Centro();
			
			centro_new.setNombre(centro.getNombre());
			centro_new.setDireccion("Avenida Franz Kafka Nº24");
			centro_new.setTlfConserjeria("952484848");
			
			gestionCentro.actualizarCentro(centro, centro_new);
			
			assertNotEquals(centro_new.getDireccion(), centro.getDireccion());
			assertNotEquals(centro_new.getTLF_Conserjeria(), centro.getTLF_Conserjeria());
		} catch (CentroNoEncontradoException e) {
			
			throw new RuntimeException(e);
		}
	}
}
