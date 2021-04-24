package es.uma.informatica.ejb;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.CentroNoEncontradoException;
import es.uma.informatica.ejb.exceptions.CentroYaExistenteException;
import es.uma.informatica.jpa.demo.Centro;

@Local
public interface GestionCentro {
	public void insertarCentro (Centro centro) throws CentroYaExistenteException;
	public void actualizarCentro(Centro centro) throws CentroNoEncontradoException;
	public List<Centro> obtenerCentros();
	public Centro obtenerCentro(String nombre, String direccion, String tlfConserjeria) throws CentroNoEncontradoException;
	void eliminarCentro(String nombre, String direccion, String tlfConserjeria) throws CentroNoEncontradoException;
}
