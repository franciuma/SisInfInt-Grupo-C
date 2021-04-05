package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Centro {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "Nombre",nullable = false,unique = true)
	private String nombre;
	@Column(name = "Direccion",nullable = false)
	private String direccion;
	@Column(name = "Telefono de Conserjeria",length = 9)
	private String TLF_Conserjeria;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
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
		return TLF_Conserjeria;
	}
	
	public void setTLF_Conserjeria(String tLF_Conserjeria) {
		TLF_Conserjeria = tLF_Conserjeria;
	}

	@Override
	public String toString() {
		return "Centro [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", TLF_Conserjeria="
				+ TLF_Conserjeria + "]";
	}

}
