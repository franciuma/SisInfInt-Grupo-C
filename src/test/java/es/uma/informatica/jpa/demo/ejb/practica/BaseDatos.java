package es.uma.informatica.jpa.demo.ejb.practica;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.informatica.jpa.demo.Alumno;
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
		
		final Integer numExpediente = 11;
		final Boolean activo = true;
		final Integer notaMediaProvisional = 9;
		
		Expediente exp1 = new Expediente(numExpediente, activo, notaMediaProvisional);
		em.persist(exp1);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
}
