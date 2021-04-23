package es.uma.informatica.jpa.demo;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Centro {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	@Column(name = "Nombre", nullable = false, unique = true, length = 50)
	private String nombre;
	@Column(name = "Direccion", nullable = false, length = 200)
	private String direccion;
	@Column(name = "Telefono_Conserjeria", length = 20)
	private String tlfConserjeria;
	
	@ManyToMany(mappedBy = "perteneceAcentros")
	private List<Titulacion> contieneTitulaciones;
	
	public Centro() {
		// TODO Auto-generated constructor stub
	}
	public Centro(String nombre, String direccion, String tlfConserjeria) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.tlfConserjeria = tlfConserjeria;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTLF_Conserjeria() {
		return tlfConserjeria;
	}
	

	public void setTlfConserjeria(String tlfConserjeria) {
		this.tlfConserjeria = tlfConserjeria;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tlfConserjeria == null) ? 0 : tlfConserjeria.hashCode());
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
		Centro other = (Centro) obj;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tlfConserjeria == null) {
			if (other.tlfConserjeria != null)
				return false;
		} else if (!tlfConserjeria.equals(other.tlfConserjeria))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Centro [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", tlfConserjeria="
				+ tlfConserjeria + "]";
	}
}
