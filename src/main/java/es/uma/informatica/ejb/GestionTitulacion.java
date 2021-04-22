package es.uma.informatica.ejb;

import java.util.List;

import es.uma.informatica.ejb.exceptions.TitulacionNoEncontradaException;
import es.uma.informatica.ejb.exceptions.TitulacionYaExistenteException;
import es.uma.informatica.jpa.demo.Titulacion;

public interface GestionTitulacion {

	public void insertarTitulacion (Titulacion titulacion) throws TitulacionYaExistenteException;
	public Titulacion obtenerTitulacion(Integer codigo) throws TitulacionNoEncontradaException;
	public void eliminarTitulacion (Integer codigo) throws TitulacionNoEncontradaException;
	public void actualizarTitulacion (Titulacion titulacion) throws TitulacionNoEncontradaException;
	public List<Titulacion> obtenerTitulaciones();
}