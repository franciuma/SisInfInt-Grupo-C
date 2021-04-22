package es.uma.informatica.ejb;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import es.uma.informatica.ejb.exceptions.AlumnoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class AlumnosEJB implements GestionAlumno {
	
	private static final Logger LOG = Logger.getLogger(AlumnosEJB.class.getCanonicalName());
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarAlumno(Alumno alum) throws ProyectoException {
		// TODO Auto-generated method stub
		Alumno alumno = em.find(Alumno.class,alum.getDni());
		if( alumno != null)
			throw new ProyectoException("Ya esta ese alumno");
		else
			em.persist(alum);
		 
	}
	@Override
	public List<Alumno> obtenerAlumno(String dni) throws AlumnoNoEncontradoException {
		// TODO iAuto-generated method stub
		List<Alumno> alumnos = em.createQuery("Select e" +" from Alumno e " + "where e.dni = '" + dni + "'" ).getResultList();
//		Alumno alumno = em.find(Alumno.class, dni);
//		if(alumno == null) throw new AlumnoNoEncontradoException();
		
		return alumnos;
	}
	
	@Override
	public void eliminarAlumno(String dni) throws AlumnoNoEncontradoException {
		// TODO Auto-generated method stub
//		Alumno alumno = obtenerAlumno(dni);
//		em.remove(alumno);
		
	}
	@Override
	public void actualizarAlumno(String dni) throws AlumnoNoEncontradoException {
		// TODO Auto-generated method stub
		Alumno alumno = em.find(Alumno.class, dni);
		
		
	}
	@Override
	public List<Alumno> obtenerAlumnos() {
		List<Alumno> alumnos = em.createQuery("Select al from Alumno").getResultList();
		return alumnos;
	}
	
	 
	


	
	
}
