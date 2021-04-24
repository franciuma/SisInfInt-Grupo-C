package es.uma.informatica.jpa.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
	@NamedQuery ( name = "finAll", query = "select a from Asignatura a")
})
public class Asignatura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name = "Referencia")
	private Integer referencia;
	@Column(name = "Codigo_Asignatura", nullable = false)
	private Integer codigo;
	@Column(name = "Creditos", nullable = false)
	private Integer creditos;
	@Column(name = "Ofertada", nullable = false)
	private Boolean ofertada;
	@Column(name = "Nombre", nullable = false, length = 100)
	private String nombre;
	@Column(name = "Curso", length = 10)
	private String curso;
	@Column(name = "Caracter", length = 20)
	private String caracter;
	@Column(name = "Duracion")
	private Integer Duracion;
	@Column(name = "Unidad_Temporal_Cuatrimestre", length = 20)
	private String unidadTemporal;
	@Column(name = "Idiomas_Imparticion", length = 100)
	private String idiomasImparticion;
	
	@ManyToOne
	private Titulacion titulacion;
	
	@OneToMany(mappedBy = "asignatura")
	private List<Clase> clases;
	
	@OneToMany(mappedBy = "asignatura")
	private List<GruposPorAsig> gruposPorAsig;
	
	public Asignatura(Integer referencia2, Integer codigo2, Integer creditos2, Boolean ofertada2, String nombre2, String curso2, String caracter2, Integer duracion2, String idiomasIm) {
		super();
		referencia = referencia2;
		codigo = codigo2;
		creditos = creditos2;
		ofertada = ofertada2;
		nombre = nombre2;
		curso = curso2;
		caracter = caracter2;
		Duracion = duracion2;
		idiomasImparticion = idiomasIm;
	}
	
	public Asignatura() {
		// TODO Auto-generated constructor stub
	}

	public Integer getReferencia() {
		return referencia;
	}
	
	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Integer getCreditos() {
		return creditos;
	}
	
	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}
	
	public Boolean getOfertada() {
		return ofertada;
	}
	
	public void setOfertada(Boolean ofertada) {
		this.ofertada = ofertada;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCurso() {
		return curso;
	}
	
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public String getCaracter() {
		return caracter;
	}
	
	public void setCaracter(String caracter) {
		this.caracter = caracter;
	}
	
	public Integer getDuracion() {
		return Duracion;
	}
	
	public void setDuracion(Integer duracion) {
		Duracion = duracion;
	}
	
	public String getUnidadTemporal() {
		return unidadTemporal;
	}
	
	public void setUnidadTemporal(String unidadTemporal) {
		this.unidadTemporal = unidadTemporal;
	}
	
	public String getIdiomasImparticion() {
		return idiomasImparticion;
	}
	
	public void setIdiomasImparticion(String idiomasImparticion) {
		this.idiomasImparticion = idiomasImparticion;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Duracion == null) ? 0 : Duracion.hashCode());
		result = prime * result + ((caracter == null) ? 0 : caracter.hashCode());
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((creditos == null) ? 0 : creditos.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((idiomasImparticion == null) ? 0 : idiomasImparticion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((ofertada == null) ? 0 : ofertada.hashCode());
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((unidadTemporal == null) ? 0 : unidadTemporal.hashCode());
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
		Asignatura other = (Asignatura) obj;
		if (Duracion == null) {
			if (other.Duracion != null)
				return false;
		} else if (!Duracion.equals(other.Duracion))
			return false;
		if (caracter == null) {
			if (other.caracter != null)
				return false;
		} else if (!caracter.equals(other.caracter))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (creditos == null) {
			if (other.creditos != null)
				return false;
		} else if (!creditos.equals(other.creditos))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (idiomasImparticion == null) {
			if (other.idiomasImparticion != null)
				return false;
		} else if (!idiomasImparticion.equals(other.idiomasImparticion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (ofertada == null) {
			if (other.ofertada != null)
				return false;
		} else if (!ofertada.equals(other.ofertada))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (unidadTemporal == null) {
			if (other.unidadTemporal != null)
				return false;
		} else if (!unidadTemporal.equals(other.unidadTemporal))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Asignatura [referencia=" + referencia + ", codigo=" + codigo + ", creditos=" + creditos + ", ofertada="
				+ ofertada + ", nombre=" + nombre + ", curso=" + curso + ", caracter=" + caracter + ", Duracion="
				+ Duracion + ", unidadTemporal=" + unidadTemporal + ", idiomasImparticion=" + idiomasImparticion + "]";
	}

	public Titulacion getTitulacion() {
		return titulacion;
	}

	public void setTitulacion(Titulacion titulacion) {
		this.titulacion = titulacion;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public List<GruposPorAsig> getGruposPorAsig() {
		return gruposPorAsig;
	}

	public void setGruposPorAsig(List<GruposPorAsig> gruposPorAsig) {
		this.gruposPorAsig = gruposPorAsig;
	}
}
