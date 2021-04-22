package es.uma.informatica.jpa.demo.ejb.practica;


import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.informatica.jpa.demo.Alumno;

import es.uma.informatica.jpa.demo.Clase;
import es.uma.informatica.jpa.demo.Clase_ID;




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
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
}
