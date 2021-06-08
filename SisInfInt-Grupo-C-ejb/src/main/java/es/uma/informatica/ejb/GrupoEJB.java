package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import es.uma.informatica.ejb.exceptions.GrupoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.GrupoYaExistenteException;
import es.uma.informatica.jpa.demo.Grupo;
import es.uma.informatica.jpa.demo.Titulacion;

@Stateless
public class GrupoEJB implements GestionGrupo{
	
	@PersistenceContext(name= "Secretaria")
	private EntityManager em;
	
	GestionTitulacion titulaciones;
	
	@Override
	public void insertarGrupo(Grupo grupo) throws GrupoYaExistenteException {
		// TODO Auto-generated method stub
		Grupo grup = em.find(Grupo.class,grupo.getId());
		if( grup != null && grup.getTitulacion() == grupo.getTitulacion()) 
			throw new GrupoYaExistenteException();
		em.persist(grupo);
	}

	@Override
	public Grupo obtenerGrupo(Integer curso, String letra, Titulacion tit) throws GrupoNoEncontradoException {
		// TODO Auto-generated method stub
		TypedQuery<Grupo> grupos = em.createQuery("Select g from Grupo g where g.curso = :curso AND g.letra = :letra AND g.titulacion = :tit",Grupo.class );
        grupos.setParameter("curso", curso);
        grupos.setParameter("letra", letra);
        grupos.setParameter("tit", tit);
        List<Grupo> grupo = grupos.getResultList();
        
		if(grupo == null || grupo.size() == 0) {
            throw new GrupoNoEncontradoException();
        }
		Grupo gru = grupo.get(0);
        return gru;
	}

	@Override
	public void eliminarGrupo(Integer curso, String letra, Titulacion tit) throws GrupoNoEncontradoException {
		// TODO Auto-generated method stub
		Grupo grup = obtenerGrupo(curso,letra,tit);
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
		Grupo gr = obtenerGrupo(grupo.getCurso(), grupo.getLetra(), grupo.getTitulacion());
        gr.setCurso(grupo_new.getCurso());
        gr.setLetra(grupo_new.getLetra());
        gr.setTurnoMañanaTarde(grupo_new.getTurnoMañanaTarde());
        gr.setIngles(grupo_new.getIngles());
        gr.setVisible(grupo_new.getVisible());
        gr.setAsignar(grupo_new.getAsignar());
        gr.setPlazas(grupo_new.getPlazas());
        em.merge(gr);
	}
	
	@Override
	public void eliminarTodos() {
		// TODO Auto-generated method stub
		List<Grupo> grupos = obtenerGrupos();
		if(grupos.size() != 0) {
			for (Grupo gr : grupos) {
				try {
					eliminarGrupo(gr.getCurso(),gr.getLetra(),gr.getTitulacion());
				} catch (GrupoNoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void crearGrupos() {
		for(Titulacion tit : titulaciones.obtenerTitulaciones()) {
			if(tit.getCodigo() == 1041) {//INFORMATICA
				for(int i = 0; i<4; i++) {
					if(i==0) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						Grupo g2 = new Grupo(i+1, "B", "Mañana", false, true,tit);
						Grupo g3 = new Grupo(i+1, "C", "Tarde", false, true,tit);
						Grupo g4 = new Grupo(i+1, "D", "Tarde", true, true,tit);
						try {
							insertarGrupo(g1);
							insertarGrupo(g2);
							insertarGrupo(g3);
							insertarGrupo(g4);
						} catch (GrupoYaExistenteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					if(i==1) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						Grupo g2 = new Grupo(i+1, "B", "Mañana", false, true,tit);
						Grupo g3 = new Grupo(i+1, "C", "Tarde", false, true,tit);
						Grupo g4 = new Grupo(i+1, "D", "Tarde", true, true,tit);
						try {
							insertarGrupo(g1);
							insertarGrupo(g2);
							insertarGrupo(g3);
							insertarGrupo(g4);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==2) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						Grupo g2 = new Grupo(i+1, "B", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
							insertarGrupo(g2);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==3) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			if(tit.getCodigo() == 1042) {//SOFTWARE
				for(int i = 0; i<4; i++) {
					if(i==0) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						Grupo g2 = new Grupo(i+1, "B", "Mañana", false, true,tit);
						Grupo g3 = new Grupo(i+1, "C", "Tarde", false, true,tit);
						Grupo g4 = new Grupo(i+1, "D", "Tarde", true, true,tit);
						try {
							insertarGrupo(g1);
							insertarGrupo(g2);
							insertarGrupo(g3);
							insertarGrupo(g4);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==1) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						Grupo g2 = new Grupo(i+1, "B", "Mañana", false, true,tit);
						Grupo g3 = new Grupo(i+1, "C", "Tarde", false, true,tit);
						Grupo g4 = new Grupo(i+1, "D", "Tarde", true, true,tit);
						try {
							insertarGrupo(g1);
							insertarGrupo(g2);
							insertarGrupo(g3);
							insertarGrupo(g4);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==2) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==3) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			if(tit.getCodigo() == 1043) {//COMPUTADORES
				for(int i = 0; i<4; i++) {
					if(i==0) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						Grupo g2 = new Grupo(i+1, "B", "Mañana", false, true,tit);
						Grupo g3 = new Grupo(i+1, "C", "Tarde", false, true,tit);
						try {
							insertarGrupo(g1);
							insertarGrupo(g2);
							insertarGrupo(g3);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==1) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						Grupo g2 = new Grupo(i+1, "B", "Mañana", false, true,tit);
						Grupo g3 = new Grupo(i+1, "C", "Tarde", false, true,tit);
						try {
							insertarGrupo(g1);
							insertarGrupo(g2);
							insertarGrupo(g3);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==2) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==3) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
			if(tit.getCodigo() == 1056) {//SALUD
				for(int i = 0; i<4; i++) {
					if(i==0) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==1) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==2) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
					if(i==3) {
						Grupo g1 = new Grupo(i+1, "A", "Mañana", false, true,tit);
						try {
							insertarGrupo(g1);
						} catch (GrupoYaExistenteException e) {
							e.printStackTrace();
						}
					}
				}
			}
			
		}
	}

}