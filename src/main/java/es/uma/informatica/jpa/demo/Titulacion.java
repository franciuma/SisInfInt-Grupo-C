package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Titulacion {

	@Id
	@Column(name = "Codigo")
	private Long codigo;
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	@Column(name = "Creditos", nullable = false)
	private Long creditos;
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Long getCreditos() {
		return creditos;
	}
	
	public void setCreditos(Long creditos) {
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
