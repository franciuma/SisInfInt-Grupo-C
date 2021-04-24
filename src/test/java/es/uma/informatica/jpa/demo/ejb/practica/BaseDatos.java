package es.uma.informatica.jpa.demo.ejb.practica;

import java.sql.Timestamp;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.informatica.jpa.demo.Alumno;
import es.uma.informatica.jpa.demo.Centro;
import es.uma.informatica.jpa.demo.Encuesta;
import es.uma.informatica.jpa.demo.Grupo;
import es.uma.informatica.jpa.demo.Centro;
import es.uma.informatica.jpa.demo.Expediente;


public class BaseDatos {
	public static void inicializaBaseDatos(String nombreUnidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(nombreUnidadPersistencia);
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		final String dni = "1111111D";
		final String nombreCompleto = "Fransi";
		final String emailInstitucional = "fransi@huevo.le";
		final String emailPersonal = "fransi@easter-egg.si";
		final String telefono = "12365478";
		final String movil = "9549845656";
		
		Alumno fransi = new Alumno(dni, nombreCompleto, emailInstitucional, emailPersonal, telefono, movil);
		em.persist(fransi);
		

		final String nombre = "ETSII Informática";
		final String direccion = "Boulevar Louis Pasteur Nº24";
		final String tlfConserjeria = "952345678";
		
		Centro etsii_info = new Centro(nombre, direccion, tlfConserjeria);
		em.persist(etsii_info);
		
		final Integer curso = 1;
		final String letra = "B";
		final String turnoMañanaTarde = "Tarde";
		final Boolean ingles = true;
		final Boolean visible = true;
		final String asignar = "asignao";
		final Integer plazas = 3;
		
		Grupo a = new Grupo(curso, letra, turnoMañanaTarde, ingles, visible, asignar, plazas);
		em.persist(a);

		final Integer numExpediente = 11;
		final Boolean activo = true;
		final Integer notaMediaProvisional = 9;
		
		Expediente exp1 = new Expediente(numExpediente, activo, notaMediaProvisional);
		em.persist(exp1);
		
		final Timestamp fechaEnvio = java.sql.Timestamp.valueOf("2021-04-24 18:23:15");
		final String matriculacionIngles = "No";
		final String turnoPreferente = "Tarde";
		
		Encuesta enc = new Encuesta(fechaEnvio, matriculacionIngles, turnoPreferente);
		em.persist(enc);

		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
}
