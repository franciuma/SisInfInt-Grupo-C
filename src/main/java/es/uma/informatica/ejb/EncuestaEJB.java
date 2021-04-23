package es.uma.informatica.ejb;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import es.uma.informatica.ejb.exceptions.ClaseNoEncontradaException;
import es.uma.informatica.ejb.exceptions.EncuestaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.EncuestaYaExistenteException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.*;


@Stateless
public class EncuestaEJB implements GestionEncuesta {
	
	private static final Logger LOG = Logger.getLogger(EncuestaEJB.class.getCanonicalName());
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarEncuesta(Encuesta encuest) throws EncuestaYaExistenteException {
		// TODO Auto-generated method stub
		Encuesta encuesta = em.find(Encuesta.class,encuest.getFechaEnvio());
		
		if( encuesta != null) {
			throw new EncuestaYaExistenteException();
		}
		
		em.persist(encuesta);
		
	}
	
	@Override
	public Encuesta obtenerEncuesta(DateTimeFormatter fecha_envio) throws EncuestaNoEncontradaException {
		// TODO iAuto-generated method stub
		Encuesta encuesta = em.find(Encuesta.class, fecha_envio);
		if(encuesta == null) {
			throw new EncuestaNoEncontradaException();
		}
		return encuesta;
	}
	
	@Override
	public void eliminarEncuesta(DateTimeFormatter fecha_envio) throws EncuestaNoEncontradaException {
		// TODO Auto-generated method stub
		Encuesta encuesta = em.find(Encuesta.class, fecha_envio);
		if(encuesta == null) {
			throw new EncuestaNoEncontradaException();
		}
		em.remove(encuesta);
	}
	
	public void actualizarEncuesta(Encuesta encuesta) throws EncuestaNoEncontradaException{
		Encuesta encuest = em.find(Encuesta.class, encuesta.getFechaEnvio());
		encuest.setFechaEnvio(encuesta.getFechaEnvio());
		em.merge(encuesta);
	}
}
