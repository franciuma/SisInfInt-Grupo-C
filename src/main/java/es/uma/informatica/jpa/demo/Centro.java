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
	private String tlfConserjeria;
	
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
		return tlfConserjeria;
	}
	

	public void setTlfConserjeria(String tlfConserjeria) {
		this.tlfConserjeria = tlfConserjeria;
	}

	@Override
	public String toString() {
		return "Centro [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", tlfConserjeria="
				+ tlfConserjeria + "]";
	}



}
