package es.uma.informatica.ejb;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.logging.Logger;

import javax.ejb.Stateless;

import es.uma.informatica.ejb.exceptions.AlumnoNoEncontardoException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class AlumnosEJB implements GestionAlumno {
	
	private static final Logger LOG = Logger.getLogger(AlumnosEJB.class.getCanonicalName());
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	//hey
	@Override
	public void insertarAlumno(Alumno alum) throws ProyectoException {
		// TODO Auto-generated method stub
		Alumno alumno = em.find(Alumno.class,alum.getId());
		if( alumno != null)
			throw new ProyectoException("Ya esta ese alumno");
		else
			em.persist(alum);
		 
	}
	@Override
	public Alumno obtenerAlumno(Integer id) throws AlumnoNoEncontardoException {
		// TODO iAuto-generated method stub
		Alumno alumno = em.find(Alumno.class, id);
		if(alumno == null) throw new AlumnoNoEncontardoException();
		return alumno;
	}


	
	
}
