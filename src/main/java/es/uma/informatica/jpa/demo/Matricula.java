package es.uma.informatica.jpa.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Matricula {

	@Id
	@Column(name = "Curso Academico")
	private Integer cursoAcademico;
	@Column(name = "Estado", nullable = false)
	private String estado;
	@Column(name = "Numero de Archivo")
	private Integer numArchivo;
	@Column(name = "Turno Preferente")
	private String turnoPreferente;
	@Column(name = "Fecha de matricula", nullable = false)
	private Date fechaMatricula;
	@Column(name = "Nuevo ingreso")
	private Boolean nuevoIngreso;
	@Column(name = "Listado de Asignaturas")
	private String listadoAsignaturas;
	
	@ManyToOne
	private Expediente expediente;
	
	public Integer getCursoAcademico() {
		return cursoAcademico;
	}
	
	public void setCursoAcademico(Integer cursoAcademico) {
		this.cursoAcademico = cursoAcademico;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Integer getNumArchivo() {
		return numArchivo;
	}
	
	public void setNumArchivo(Integer numArchivo) {
		this.numArchivo = numArchivo;
	}
	
	public String getTurnoPreferente() {
		return turnoPreferente;
	}
	
	public void setTurnoPreferente(String turnoPreferente) {
		this.turnoPreferente = turnoPreferente;
	}
	
	public Date getFechaMatricula() {
		return fechaMatricula;
	}
	
	public void setFechaMatricula(Date fechaMatricula) {
		this.fechaMatricula = fechaMatricula;
	}
	
	public Boolean getNuevoIngreso() {
		return nuevoIngreso;
	}
	
	public void setNuevoIngreso(Boolean nuevoIngreso) {
		this.nuevoIngreso = nuevoIngreso;
	}
	
	public String getListadoAsignaturas() {
		return listadoAsignaturas;
	}
	
	public void setListadoAsignaturas(String listadoAsignaturas) {
		this.listadoAsignaturas = listadoAsignaturas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cursoAcademico == null) ? 0 : cursoAcademico.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((fechaMatricula == null) ? 0 : fechaMatricula.hashCode());
		result = prime * result + ((listadoAsignaturas == null) ? 0 : listadoAsignaturas.hashCode());
		result = prime * result + ((nuevoIngreso == null) ? 0 : nuevoIngreso.hashCode());
		result = prime * result + ((numArchivo == null) ? 0 : numArchivo.hashCode());
		result = prime * result + ((turnoPreferente == null) ? 0 : turnoPreferente.hashCode());
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
		Matricula other = (Matricula) obj;
		if (cursoAcademico == null) {
			if (other.cursoAcademico != null)
				return false;
		} else if (!cursoAcademico.equals(other.cursoAcademico))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (fechaMatricula == null) {
			if (other.fechaMatricula != null)
				return false;
		} else if (!fechaMatricula.equals(other.fechaMatricula))
			return false;
		if (listadoAsignaturas == null) {
			if (other.listadoAsignaturas != null)
				return false;
		} else if (!listadoAsignaturas.equals(other.listadoAsignaturas))
			return false;
		if (nuevoIngreso == null) {
			if (other.nuevoIngreso != null)
				return false;
		} else if (!nuevoIngreso.equals(other.nuevoIngreso))
			return false;
		if (numArchivo == null) {
			if (other.numArchivo != null)
				return false;
		} else if (!numArchivo.equals(other.numArchivo))
			return false;
		if (turnoPreferente == null) {
			if (other.turnoPreferente != null)
				return false;
		} else if (!turnoPreferente.equals(other.turnoPreferente))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Matricula [cursoAcademico=" + cursoAcademico + ", estado=" + estado + ", numArchivo=" + numArchivo
				+ ", turnoPreferente=" + turnoPreferente + ", fechaMatricula=" + fechaMatricula + ", nuevoIngreso="
				+ nuevoIngreso + ", listadoAsignaturas=" + listadoAsignaturas + "]";
	}
}
