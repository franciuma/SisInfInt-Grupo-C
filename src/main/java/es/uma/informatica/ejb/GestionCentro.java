package es.uma.informatica.ejb;
import javax.ejb.Local;

import es.uma.informatica.ejb.exceptions.CentroYaExistenteException;
import es.uma.informatica.jpa.demo.Centro;

@Local
public interface GestionCentro {
	public void insertarCentro (Centro centro) throws CentroYaExistenteException;
}
