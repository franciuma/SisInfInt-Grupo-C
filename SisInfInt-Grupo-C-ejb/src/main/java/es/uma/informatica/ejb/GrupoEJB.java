package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import es.uma.informatica.ejb.exceptions.GrupoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.GrupoYaExistenteException;
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
        
		if(grupo == null || grupo.size() == 0) {
            throw new GrupoNoEncontradoException();
        }
		Grupo gru = grupo.get(0);
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
		TypedQuery<Grupo> query = em.createNamedQuery("findAllGrupos", Grupo.class);
		return query.getResultList();
	}

	@Override
	public void actualizarGrupo(Grupo grupo, Grupo grupo_new) throws GrupoNoEncontradoException {
		// TODO Auto-generated method stub
		Grupo gr = obtenerGrupo(grupo.getCurso(), grupo.getLetra(), grupo.getTurnoMañanaTarde(), grupo.getIngles(), grupo.getVisible(), grupo.getAsignar(), grupo.getPlazas());
        gr.setCurso(grupo_new.getCurso());
        gr.setLetra(grupo_new.getLetra());
        gr.setTurnoMañanaTarde(grupo_new.getTurnoMañanaTarde());
        gr.setIngles(grupo_new.getIngles());
        gr.setVisible(grupo_new.getVisible());
        gr.setAsignar(grupo_new.getAsignar());
        gr.setPlazas(grupo_new.getPlazas());
        em.merge(gr);
	}

}