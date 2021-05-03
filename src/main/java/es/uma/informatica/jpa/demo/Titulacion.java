package es.uma.informatica.jpa.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries( { @NamedQuery (name = "findAllTitulaciones", query = "select tit from Titulacion tit") })

public class Titulacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "Codigo_Titulacion")
	private Integer codigo;
	@Column(name = "Nombre", nullable = false, length = 40)
	private String nombre;
	@Column(name = "Creditos", nullable = false)
	private Integer creditos;
	
	@ManyToMany
	@JoinTable(name = "JND_Centro_Titulacion",
		joinColumns = @JoinColumn(name = "Titulacion_FK"),
		inverseJoinColumns = @JoinColumn(name = "Centro_FK"))
	private List<Centro> perteneceAcentros;
	
	@OneToMany(mappedBy = "titulacion")
	private List<Grupo> grupos;
	
	@OneToMany(mappedBy = "titulacion")
	private List<Expediente> expedientes;
	
	@OneToMany(mappedBy = "titulacion")
	private List<Asignatura> asignaturas;
	
	public Titulacion(Integer codigo, String nombre, Integer creditos) {
		
		super();
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.creditos = creditos;
	}
	
	public Titulacion() {
		
	}

	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getCreditos() {
		return creditos;
	}
	
	public void setCreditos(Integer creditos) {
		this.creditos = creditos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((creditos == null) ? 0 : creditos.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		Titulacion other = (Titulacion) obj;
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
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Titulacion [codigo=" + codigo + ", nombre=" + nombre + ", creditos=" + creditos + "]";
	}
}