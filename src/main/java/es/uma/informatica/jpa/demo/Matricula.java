package es.uma.informatica.jpa.demo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Matricula {

	@Id 
	@GeneratedValue
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
	public String toString() {
		return "Matricula [cursoAcademico=" + cursoAcademico + ", estado=" + estado + ", numArchivo=" + numArchivo
				+ ", turnoPreferente=" + turnoPreferente + ", fechaMatricula=" + fechaMatricula + ", nuevoIngreso="
				+ nuevoIngreso + ", listadoAsignaturas=" + listadoAsignaturas + "]";
	}
	
}
