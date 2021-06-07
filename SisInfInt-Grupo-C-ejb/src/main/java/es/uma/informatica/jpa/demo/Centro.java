package es.uma.informatica.jpa.demo;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllCentros", query = "select c from Centro c")
public class Centro implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
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
	
<<<<<<< HEAD:SisInfInt-Grupo-C-ejb/src/main/java/es/uma/informatica/jpa/demo/Centro.java
	public int getId() {
=======
	public Centro(String nombre2, String direccion2, String tlfSecretaria) {
		nombre = nombre2;
		direccion = direccion2;
		tlfConserjeria = tlfSecretaria;
	}

	public Integer getId() {
>>>>>>> 2c21fa8abf028974942618bb3e85f14b74057578:src/main/java/es/uma/informatica/jpa/demo/Centro.java
		return id;
	}
	
	public void setId(int id) {
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
		result = prime * result + ((contieneTitulaciones == null) ? 0 : contieneTitulaciones.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + id;
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
		if (contieneTitulaciones == null) {
			if (other.contieneTitulaciones != null)
				return false;
		} else if (!contieneTitulaciones.equals(other.contieneTitulaciones))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (id != other.id)
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
