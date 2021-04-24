package es.uma.informatica.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.ejb.exceptions.GruposPorAsigNoEncontradaException;
import es.uma.informatica.ejb.exceptions.GruposPorAsigYaExistenteException;
import es.uma.informatica.jpa.demo.Asignatura_Matricula;
import es.uma.informatica.jpa.demo.GruposPorAsig;

@Stateless
public class GruposPorAsigEJB implements GestionGruposPorAsig{
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarGruposPorAsig(GruposPorAsig asig_ma) throws GruposPorAsigYaExistenteException {
		// TODO Auto-generated method stub
		Asignatura_Matricula as_m = em.find(Asignatura_Matricula.class,asig_ma.getId());
		
		if( as_m != null) {
			throw new GruposPorAsigYaExistenteException();
		}else {
			em.persist(as_m);
		}
		 
	}
	@Override
	public GruposPorAsig obtenerGruposPorAsig(GruposPorAsig id) throws GruposPorAsigNoEncontradaException {
		// TODO iAuto-generated method stub
		GruposPorAsig grpas = em.find(GruposPorAsig.class, id);
		if(grpas == null) {
			throw new GruposPorAsigNoEncontradaException();
		}
		return grpas;
	}
}
