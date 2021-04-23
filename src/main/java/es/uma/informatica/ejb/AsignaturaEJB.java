package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.uma.informatica.ejb.exceptions.AsignaturaNoEncontradaException;
import es.uma.informatica.ejb.exceptions.AsignaturaYaExistenteException;
import es.uma.informatica.ejb.exceptions.CentroNoEncontradoExeption;
import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Asignatura;
import es.uma.informatica.jpa.demo.Centro;
import es.uma.informatica.jpa.demo.Expediente;

@Stateless
public class AsignaturaEJB implements GestionAsignatura{

	private EntityManager em;
	
	@Override
	public void insertarAsignatura(Asignatura asig) throws AsignaturaYaExistenteException {
		// TODO Auto-generated method stub
		Asignatura asignatura = em.find(Asignatura.class, asig.getReferencia());
		if(asignatura != null) {
			throw new AsignaturaYaExistenteException();
		}
			em.persist(asignatura);
	}

	@Override
	public Asignatura obtenerAsignatura(Integer referencia) throws AsignaturaNoEncontradaException {
		// TODO Auto-generated method stub
		
		Asignatura asignatura = em.find(Asignatura.class, referencia);
        if(asignatura == null) {
            throw new AsignaturaNoEncontradaException();
        }
        return asignatura;
	}

	@Override
	public void eliminarAsignatura(Integer referencia) throws AsignaturaNoEncontradaException {
		// TODO Auto-generated method stub
		Asignatura asignatura = em.find(Asignatura.class, referencia);
		if(asignatura == null) {
            throw new AsignaturaNoEncontradaException();
        }
		em.remove(asignatura);
	}

	@Override
	public void actualizarAsignatura(Asignatura asignatura) throws AsignaturaNoEncontradaException{
		// TODO Auto-generated method stub
		Asignatura asi = em.find(Asignatura.class, asignatura.getReferencia());
		asi.setCaracter(asignatura.getCaracter());
		asi.setCodigo(asignatura.getCodigo());
        asi.setCreditos(asignatura.getCreditos());
        asi.setUnidadTemporal(asignatura.getUnidadTemporal());
        asi.setCurso(asignatura.getCurso());
        asi.setOfertada(asignatura.getOfertada());
        asi.setIdiomasImparticion(asignatura.getIdiomasImparticion());
        asi.setNombre(asignatura.getNombre());
        asi.setDuracion(asignatura.getDuracion());
        em.merge(asi);
	}

	@Override
	public List<Asignatura> obtenerAsignaturas() {
		// TODO Auto-generated method stub
		List<Asignatura> asignaturas = em.createQuery("select * from Asignatura").getResultList();
		return asignaturas;
	}

}
