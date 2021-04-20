package es.uma.informatica.ejb;


import javax.persistence.EntityManager;

import es.uma.informatica.ejb.exceptions.AlumnoNoEncontardoException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;

public class AlumnosEJB implements GestionAlumno {

	private EntityManager em;
	@Override
	public void añadirAlumno(Alumno alum) throws ProyectoException {
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
