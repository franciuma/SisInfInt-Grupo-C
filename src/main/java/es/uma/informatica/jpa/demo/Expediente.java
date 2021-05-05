package es.uma.informatica.jpa.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(value = { @NamedQuery(name = "findAllExpedientes", query = "select exp from Expediente exp")})

public class Expediente {

	@Id
	@Column(name = "Numero_Expediente")
	private Integer numExpediente;
	@Column(name = "Activo")
	private Boolean activo;
	@Column(name = "Nota_Media_Provisional")
	private Double notaMediaProvisional;
	
	@ManyToOne
	private Titulacion titulacion;
	
	@ManyToOne
	private Alumno alumno;
	
	@OneToMany(mappedBy = "expediente")
	private List<Encuesta> encuestas;
	
	@OneToMany(mappedBy = "expediente")
	private List<Matricula> matriculas;
	
public Expediente(Integer numExpediente, Boolean activo, Double notaMediaProvisional) {
		
		super();
		
		this.numExpediente = numExpediente;
		this.activo = activo;
		this.notaMediaProvisional = notaMediaProvisional;
	}
	
	public Expediente() {
		
	}
	
	public void insertarEncuesta(Encuesta en) {
		encuestas.add(en);
	}
	public void insertarMatricula(Matricula ma) {
		matriculas.add(ma);
	}
	
	public Integer getNumExpediente() {
		return numExpediente;
	}
	
	public void setNumExpediente(Integer numExpediente) {
		this.numExpediente = numExpediente;
	}
	
	public boolean isActivo() {
		return activo;
	}
	
	public boolean getActivo() {
		return activo;
	}
	
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Double getNotaMediaProvisional() {
		return notaMediaProvisional;
	}
	
	public void setNotaMediaProvisional(Double notaMediaProvisional) {
		this.notaMediaProvisional = notaMediaProvisional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
		result = prime * result + ((notaMediaProvisional == null) ? 0 : notaMediaProvisional.hashCode());
		result = prime * result + ((numExpediente == null) ? 0 : numExpediente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expediente other = (Expediente) obj;
		if (activo != other.activo)
			return false;
		if (notaMediaProvisional == null) {
			if (other.notaMediaProvisional != null)
				return false;
		} else if (!notaMediaProvisional.equals(other.notaMediaProvisional))
			return false;
		if (numExpediente == null) {
			if (other.numExpediente != null)
				return false;
		} else if (!numExpediente.equals(other.numExpediente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Expedientes [numExpediente=" + numExpediente + ", activo=" + activo + ", notaMediaProvisional="
				+ notaMediaProvisional + "]";
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Encuesta> getEncuestas() {
		return encuestas;
	}

	public void setEncuestas(List<Encuesta> encuestas) {
		this.encuestas = encuestas;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
}
