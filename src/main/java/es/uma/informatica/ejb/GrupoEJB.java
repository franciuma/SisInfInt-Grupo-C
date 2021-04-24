package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	public void insertarGrupo(Grupo grupo) throws GrupoYaExistenteException {
		// TODO Auto-generated method stub
		Grupo grup = em.find(Grupo.class,grupo.getId());
		if( grup != null) 
			throw new GrupoYaExistenteException();
		em.persist(grupo);
	}

	@Override
	public Grupo obtenerGrupo(Integer curso, String letra, String turnoMañanaTarde, Boolean ingles, Boolean visible, String asignar, Integer plazas) throws GrupoNoEncontradoException {
		// TODO Auto-generated method stub
		TypedQuery<Grupo> grupos = em.createQuery("Select g from Grupo g where g.curso = :curso AND g.letra = :letra AND g.turnoMañanaTarde = :turnoMañanaTarde AND g.ingles = :ingles AND g.visible = :visible AND g.asignar = :asignar AND g.plazas = :plazas",Grupo.class );
        grupos.setParameter("curso", curso);
        grupos.setParameter("letra", letra);
        grupos.setParameter("turnoMañanaTarde", turnoMañanaTarde);
        grupos.setParameter("ingles", ingles);
        grupos.setParameter("visible", visible);
        grupos.setParameter("asignar", asignar);
        grupos.setParameter("plazas", plazas);
        List<Grupo> grupo = grupos.getResultList();
        Grupo gru = grupo.get(0);
		if(grupo == null) {
            throw new GrupoNoEncontradoException();
        }
        return gru;
	}

	@Override
	public void eliminarGrupo(Integer curso, String letra, String turnoMañanaTarde, Boolean ingles, Boolean visible, String asignar, Integer plazas) throws GrupoNoEncontradoException {
		// TODO Auto-generated method stub
		Grupo grup = obtenerGrupo(curso,letra,turnoMañanaTarde,ingles,visible,asignar,plazas);
        em.remove(grup);
	}

	@Override
	public List<Grupo> obtenerGrupos() {
		// TODO Auto-generated method stub
		List<Grupo> grup = em.createQuery("Select * from Grupo").getResultList();
		return grup;
	}

	@Override
	public void actualizarGrupo(Grupo grupo) throws GrupoNoEncontradoException {
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