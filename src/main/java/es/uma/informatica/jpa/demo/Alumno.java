package es.uma.informatica.jpa.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "DNI", nullable = false, unique = true, length = 9)
	private String dni;
	@Column(name = "Nombre_Completo", nullable = false)
	private String nombreCompleto;
	@Column(name = "Email_Institucional", nullable = false)
	private String emailInstitucional;
	@Column(name = "Email_Personal")
	private String emailPersonal;
	@Column(name = "Telefono_fijo")
	private String telefono;
	@Column(name = "Telefono_movil")
	private String movil;
	
	@OneToMany(mappedBy = "alumno")
	private List<Expediente> expedientes;
	
	public Alumno(Long id, String dni, String nombreCompleto, String emailInstitucional, String emailPersonal, String telefono, String movil) {
		
		super();
		this.id = id;
		this.dni = dni;
		this.nombreCompleto = nombreCompleto;
		this.emailInstitucional = emailInstitucional;
		this.emailPersonal = emailPersonal;
		this.telefono = telefono;
		this.movil = movil;
	}
	
	public Alumno() {
		
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	
	public String getEmailInstitucional() {
		return emailInstitucional;
	}
	
	public void setEmailInstitucional(String emailInstitucional) {
		this.emailInstitucional = emailInstitucional;
	}
	
	public String getEmailPersonal() {
		return emailPersonal;
	}
	
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getMovil() {
		return movil;
	}
	
	public void setMovil(String movil) {
		this.movil = movil;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((emailInstitucional == null) ? 0 : emailInstitucional.hashCode());
		result = prime * result + ((emailPersonal == null) ? 0 : emailPersonal.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((movil == null) ? 0 : movil.hashCode());
		result = prime * result + ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
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
		Alumno other = (Alumno) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (emailInstitucional == null) {
			if (other.emailInstitucional != null)
				return false;
		} else if (!emailInstitucional.equals(other.emailInstitucional))
			return false;
		if (emailPersonal == null) {
			if (other.emailPersonal != null)
				return false;
		} else if (!emailPersonal.equals(other.emailPersonal))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (movil == null) {
			if (other.movil != null)
				return false;
		} else if (!movil.equals(other.movil))
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alumno [id=" + id + ", dni=" + dni + ", nombreCompleto=" + nombreCompleto + ", emailInstitucional="
				+ emailInstitucional + ", emailPersonal=" + emailPersonal + ", telefono=" + telefono + ", movil="
				+ movil + "]";
	}
}
