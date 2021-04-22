package es.uma.informatica.ejb;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;


import javax.ejb.Stateless;

import es.uma.informatica.ejb.exceptions.*;
import es.uma.informatica.ejb.exceptions.AlumnoYaExistenteException;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class AlumnosEJB implements GestionAlumno {
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;

	@Override
	public void insertarAlumno(Alumno alum) throws AlumnoYaExistenteException {
		// TODO Auto-generated method stub
		Alumno alumno = em.find(Alumno.class,alum.getId());
		if( alumno != null)
			throw new AlumnoYaExistenteException();
		em.persist(alum);
		 
	}
	@Override
	public Alumno obtenerAlumno(String dni) throws AlumnoNoEncontradoException {
		// TODO iAuto-generated method stub

		Query  alumnos = em.createQuery("Select e from Alumno e where e.dni = :dni" );
		alumnos.setParameter("dni", dni);
		List<Alumno> alumno = alumnos.getResultList();
		Alumno al = alumno.get(0);
		if(alumno == null) throw new AlumnoNoEncontradoException();
		
		return al;
	}
	
	@Override
	public void eliminarAlumno(String dni) throws AlumnoNoEncontradoException {
		Alumno alumno = obtenerAlumno(dni);
		em.remove(alumno);
		
	}
	@Override
	public void actualizarAlumno(Alumno alumno) throws AlumnoNoEncontradoException {
	
		Alumno al = em.find(Alumno.class, alumno.getId());
		al.setEmailPersonal(alumno.getEmailPersonal());
		al.setMovil(alumno.getMovil());
		al.setTelefono(alumno.getTelefono());
		em.merge(alumno);
		
		
		
	}
	@Override
	public List<Alumno> obtenerAlumnos() {
		List<Alumno> alumnos = em.createQuery("Select al from Alumno al").getResultList();
		return alumnos;
	}
}
