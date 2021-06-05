package es.uma.informatica.ejb;
import es.uma.informatica.ejb.exceptions.*;
import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.jpa.demo.Titulacion;

@Local
public interface GestionTitulacion {

	public void insertarTitulacion (Titulacion titulacion) throws TitulacionYaExistenteException;
	public Titulacion obtenerTitulacion(Integer codigo) throws TitulacionNoEncontradaException;
	public void eliminarTitulacion (Integer codigo) throws TitulacionNoEncontradaException;
	public void actualizarTitulacion (Titulacion titulacion) throws TitulacionNoEncontradaException;
	public List<Titulacion> obtenerTitulaciones();
	public void importarTitulacion(String sFile) throws TitulacionYaExistenteException, IOException;
	void eliminarTodas();
}