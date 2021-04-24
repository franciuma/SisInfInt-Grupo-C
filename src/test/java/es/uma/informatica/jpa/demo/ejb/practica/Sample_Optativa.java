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

import javax.ejb.EJB;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
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
import es.uma.informatica.ejb.GestionOptativa;
import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.jpa.demo.*;
import es.uma.informatica.sii.anotaciones.Requisitos;



public class Sample_Optativa {
	
	private static final Logger LOG = Logger.getLogger(Sample_Optativa.class.getCanonicalName());

	private static final String OPTATIVA_EJB = "java:global/classes/OptativaEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionOptativa gestionOPtativa;
	
	@Before
	public void setup() throws NamingException  {
		gestionOPtativa = (GestionOptativa) SuiteTest.ctx.lookup(OPTATIVA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
		
	}

	@Test
	public void testInsertarOptativa() {
		final Integer referencia = 50659;
		final Integer codigo = 101;
		final Integer creditos = 6;
		final Boolean ofertada = true;
		final String nombre = "Cálculo para la Computación";
		final String curso = "1";
		final String caracter = "Obligatoria";
		final Integer duracion = 1;
		final String idiomasIm = "No";
		final Integer plazas = 35;
		final String mencion = "Informatica";
		try {
			Optativa calculo2 = new Optativa(referencia, codigo, creditos, ofertada,
					nombre, curso, caracter, duracion, idiomasIm, plazas, mencion);
			gestionOPtativa.insertarOptativa(calculo2);
			
		} catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
	}
	

}