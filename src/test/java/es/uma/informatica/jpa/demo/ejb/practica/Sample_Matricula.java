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
import javax.persistence.Column;
import javax.persistence.ManyToOne;

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
import es.uma.informatica.ejb.GestionMatricula;
import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.jpa.demo.*;
import es.uma.informatica.sii.anotaciones.Requisitos;



public class Sample_Matricula {
	
	private static final Logger LOG = Logger.getLogger(Sample_Matricula.class.getCanonicalName());

	private static final String MATRICULA_EJB = "java:global/classes/MatriculaEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionMatricula gestionMatricula;
	
	@Before
	public void setup() throws NamingException  {
		gestionMatricula = (GestionMatricula) SuiteTest.ctx.lookup(MATRICULA_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
		
	}

	@Test
	public void testInsertarAsignatura() {
		final Matricula_ID mat_id = new Matricula_ID(1232442L);
		final  String cursoAcademico = "pa";
		final  String estado = "estado";
	
		final  Integer numArchivo = 56156;
	
		final  String turnoPreferente = "TU";
	
		final  Date fechaMatricula = new Date(116, 2, 6);
		
		final  Boolean nuevoIngreso = true;
		
		final  String listadoAsignaturas = "si";
		final Integer numExpediente = 22;
		final Boolean activo = true;
		final Integer notaMediaProvisional = 8;
		final Expediente exp1 = new Expediente(numExpediente,activo,notaMediaProvisional);
		
		
		try {
			
			Matricula matricula = new Matricula(mat_id, cursoAcademico,
					estado, numArchivo, turnoPreferente, fechaMatricula, nuevoIngreso, listadoAsignaturas, exp1);
			gestionMatricula.insertarMatricula(matricula);
		
		} catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
	}
	
	

}