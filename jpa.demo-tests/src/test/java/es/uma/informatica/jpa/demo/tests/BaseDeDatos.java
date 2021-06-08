package es.uma.informatica.jpa.demo.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.uma.informatica.jpa.demo.Alumno;


public class BaseDeDatos {
	
	public static void inicializar(String unidadPersistencia) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(unidadPersistencia);
		EntityManager em = emf.createEntityManager();
		/* Comentar la línea de abajo si los datos se incluyen en un script de SQL */
		datos(em); 
		
		em.close();
		emf.close();
		
	}

	public static void datos(EntityManager em) {
		Alumno admin = new Alumno();
			admin.setDni("admin");
			admin.setNombreCompleto("admin");
		em.persist(admin);
	}

}
