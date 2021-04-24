package es.uma.informatica.jpa.demo.ejb.practica;

import javax.persistence.EntityManager;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.informatica.jpa.demo.Alumno;
<<<<<<< refs/remotes/origin/dev/franciuma
=======
import es.uma.informatica.jpa.demo.Centro;
import es.uma.informatica.jpa.demo.Grupo;
>>>>>>> local


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
		
<<<<<<< refs/remotes/origin/dev/franciuma
=======
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
>>>>>>> local
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}
}
