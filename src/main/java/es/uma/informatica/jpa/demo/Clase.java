package es.uma.informatica.jpa.demo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@IdClass(ClaseId.class)
public class Clase {

	@Id
	@Temporal(TemporalType.DATE)
	private Date dia;
	@Id 
	@Temporal(TemporalType.TIME)
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	private Date horaFin;
	
	
}

