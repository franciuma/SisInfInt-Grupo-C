package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import es.uma.informatica.ejb.exceptions.ExpedienteNoEncontradoException;
import es.uma.informatica.ejb.exceptions.GrupoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.GrupoYaExistenteException;
import es.uma.informatica.jpa.demo.Expediente;
import es.uma.informatica.jpa.demo.Grupo;

@Stateless
public class GrupoEJB implements GestionGrupo{
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	@Override
	public void insertarExpediente(Grupo grupo) throws GrupoYaExistenteException {
		// TODO Auto-generated method stub
		Grupo grup = em.find(Grupo.class,grupo.getId());
		if( grup != null) 
			throw new GrupoYaExistenteException();
		em.persist(grupo);
	}

	@Override
	public Grupo obtenerGrupo(Integer id) throws GrupoNoEncontradoException {
		// TODO Auto-generated method stub
		Grupo grupo = em.find(Grupo.class, id);
        if(grupo == null) {
            throw new GrupoNoEncontradoException();
        }
        return grupo;
	}

	@Override
	public void eliminarGrupo(Integer id) throws GrupoNoEncontradoException {
		// TODO Auto-generated method stub
		Grupo grup = em.find(Grupo.class, id);
        if(grup == null) {
            throw new GrupoNoEncontradoException();
        }
        em.remove(grup);
	}

	@Override
	public List<Grupo> obtenerGrupos() {
		// TODO Auto-generated method stub
		List<Grupo> grup = em.createQuery("Select * from Grupo").getResultList();
		return grup;
	}

	@Override
	public void actualizarExpediente(Grupo grupo) throws GrupoNoEncontradoException {
		// TODO Auto-generated method stub
		Grupo gr = em.find(Grupo.class, grupo.getId());
        gr.setCurso(grupo.getCurso());
        gr.setLetra(grupo.getLetra());
        gr.setTurnoMañanaTarde(grupo.getTurnoMañanaTarde());
        gr.setIngles(grupo.getIngles());
        gr.setVisible(grupo.getVisible());
        gr.setAsignar(grupo.getAsignar());
        gr.setPlazas(grupo.getPlazas());
        em.merge(grupo);
	}

}