package es.uma.informatica.jpa.demo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Encuesta {

	@Id
	private Date fechaEnvio;
}
