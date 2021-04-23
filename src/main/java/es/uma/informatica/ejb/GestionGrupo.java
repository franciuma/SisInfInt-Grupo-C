package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.GrupoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.GrupoYaExistenteException;
import es.uma.informatica.jpa.demo.Grupo;

@Local
public interface GestionGrupo {

	public void insertarExpediente (Grupo grupo) throws GrupoYaExistenteException;
    public Grupo obtenerGrupo(Integer id) throws GrupoNoEncontradoException;
    public void eliminarGrupo (Integer id) throws GrupoNoEncontradoException;
    public List<Grupo> obtenerGrupos();
    void actualizarExpediente(Grupo grupo) throws GrupoNoEncontradoException;
    
}