package es.uma.informatica.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.ejb.exceptions.Asignatura_MatriculaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.Asignatura_MatriculaYaExistenteException;
import es.uma.informatica.jpa.demo.Asignatura_Matricula;


@Stateless
public class Asignatura_MatriculaEJB implements GestionAsignatura_Matricula{

	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarAsignatura_Matricula(Asignatura_Matricula asig_ma) throws Asignatura_MatriculaYaExistenteException {
		// TODO Auto-generated method stub
		Asignatura_Matricula as_m = em.find(Asignatura_Matricula.class,asig_ma.getId());
		
		if( as_m != null) {
			throw new Asignatura_MatriculaYaExistenteException();
		}else {
			em.persist(as_m);
		}
		 
	}
	@Override
	public Asignatura_Matricula obtenerAsignatura_Matricula(Asignatura_Matricula id) throws Asignatura_MatriculaNoEncontradaException {
		// TODO iAuto-generated method stub
		Asignatura_Matricula as_m = em.find(Asignatura_Matricula.class, id);
		if(as_m == null) {
			throw new Asignatura_MatriculaNoEncontradaException();
		}
		return as_m;
	}
}

