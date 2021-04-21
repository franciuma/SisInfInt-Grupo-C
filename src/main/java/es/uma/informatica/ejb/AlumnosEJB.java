package es.uma.informatica.ejb;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public Alumno obtenerAlumno(String dni) throws AlumnoNoEncontradoException {
		// TODO iAuto-generated method stub
		Alumno alumno = em.find(Alumno.class, dni);
		if(alumno == null) throw new AlumnoNoEncontradoException();
		return alumno;
	}
	
	@Override
	public void eliminarAlumno(String dni) throws AlumnoNoEncontradoException {
		// TODO Auto-generated method stub
		Alumno alumno = obtenerAlumno(dni);
		em.remove(alumno);
		
	}
	
	 
	


	
	
}
