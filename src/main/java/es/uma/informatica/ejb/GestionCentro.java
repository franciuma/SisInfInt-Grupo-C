package es.uma.informatica.ejb;

import java.util.List;

import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.CentroNoEncontradoExeption;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Centro;



@Local
public interface GestionCentro {

	public void insertarCentro (Centro centro) throws ProyectoException;
	public Centro obtenerCentro(String nombre) throws CentroNoEncontradoExeption;
	public void eliminarCentro (String nombre) throws CentroNoEncontradoExeption;
	public void actualizarCentro (Centro centro) throws CentroNoEncontradoExeption;
	public List<Centro> obtenerCentros ();
	
}
