package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import es.uma.informatica.ejb.exceptions.CentroNoEncontradoExeption;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Asignatura;
import es.uma.informatica.jpa.demo.Centro;

@Stateless
public class AsignaturaEJB implements GestionAsignatura{

	private EntityManager em;
	
	@Override
	public void insertarAsignatura(Asignatura asig) throws AsignaturaYaExistenteException {
		// TODO Auto-generated method stub
		Asignatura asignatura = em.find(Asignatura.class, asig.getReferencia());
		if(asignatura != null) {
			throw new ProyectoException("Ese centro ya esta insertado");
		}
			em.persist(asignatura);
	}

	@Override
	public Centro obtenerCentro(String nombre) throws CentroNoEncontradoExeption {
		// TODO Auto-generated method stub
		
		Query centros = em.createQuery("Select c from Centro c where c.nombre = :nombre");
		centros.setParameter("nombre", nombre);
		
		List<Centro> centros_list = centros.getResultList();
		Centro centro = centros_list.get(0);
		if(centro == null) throw new CentroNoEncontradoExeption();
		return centro;
	}

	@Override
	public void eliminarCentro(String nombre) throws CentroNoEncontradoExeption {
		// TODO Auto-generated method stub
		Centro cen = obtenerCentro(nombre);
		em.remove(cen);
	}

	@Override
	public void actualizarCentro(Centro cen) throws CentroNoEncontradoExeption {
		// TODO Auto-generated method stub
		Centro centro = obtenerCentro(cen.getNombre());
		centro.setNombre(cen.getNombre());
		centro.setDireccion(cen.getDireccion());
		centro.setTlfConserjeria(cen.getTLF_Conserjeria());
		em.merge(centro);
	}

	@Override
	public List<Centro> obtenerCentros() {
		// TODO Auto-generated method stub
		List<Centro> centros = em.createQuery("select * from Centro").getResultList();
		return centros;
	}

}
