package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.GrupoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.GrupoYaExistenteException;
import es.uma.informatica.jpa.demo.Grupo;
import es.uma.informatica.jpa.demo.Titulacion;

@Local
public interface GestionGrupo {

	public void insertarGrupo (Grupo grupo) throws GrupoYaExistenteException;
    public List<Grupo> obtenerGrupos();
	void actualizarGrupo(Grupo grupo, Grupo grupo_new) throws GrupoNoEncontradoException;
	Grupo obtenerGrupo(Integer curso, String letra, Titulacion tit) throws GrupoNoEncontradoException;
	void eliminarGrupo(Integer curso, String letra, Titulacion tit) throws GrupoNoEncontradoException;
	public void eliminarTodos();
	void crearGrupos();
    
}