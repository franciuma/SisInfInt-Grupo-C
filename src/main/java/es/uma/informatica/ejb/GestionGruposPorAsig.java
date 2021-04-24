package es.uma.informatica.ejb;

import es.uma.informatica.ejb.exceptions.GruposPorAsigNoEncontradaException;
import es.uma.informatica.ejb.exceptions.GruposPorAsigYaExistenteException;
import es.uma.informatica.jpa.demo.GruposPorAsig;

public interface GestionGruposPorAsig {
		
	void insertarGruposPorAsig(GruposPorAsig asig_ma) throws GruposPorAsigYaExistenteException;

	public GruposPorAsig obtenerGruposPorAsig(GruposPorAsig id)
			throws GruposPorAsigNoEncontradaException;

}
