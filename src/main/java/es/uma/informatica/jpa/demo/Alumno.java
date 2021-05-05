package es.uma.informatica.jpa.demo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "finAllAlumnos", query = "Select al from Alumno al")
public class Alumno implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "DNI", nullable = false, unique = true, length = 9)
	private String dni;
	@Column(name = "Nombre_Completo", nullable = false, length = 100)
	private String nombreCompleto;
	@Column(name = "Email_Institucional", nullable = false, length = 100)
	private String emailInstitucional;
	@Column(name = "Email_Personal", length = 100)
	private String emailPersonal;
	@Column(name = "Telefono_Fijo", length = 20)
	private String telefono;
	@Column(name = "Telefono_Movil", length = 20)
	private String movil;
	
	@OneToMany(mappedBy = "alumno")
	private List<Expediente> expedientes;
	
	public Alumno(String dni, String nombreCompleto, String emailInstitucional, String emailPersonal, String telefono, String movil) {
		
		super();
		
		this.dni = dni;
		this.nombreCompleto = nombreCompleto;
		this.emailInstitucional = emailInstitucional;
		this.emailPersonal = emailPersonal;
		this.telefono = telefono;
		this.movil = movil;
	}
	
	public Alumno() {
		
		
	}
	
	public void insertar_Expediente(Expediente exp) {
		expedientes.add(exp);
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
		return "Alumno [ dni=" + dni + ", nombreCompleto=" + nombreCompleto + ", emailInstitucional="
				+ emailInstitucional + ", emailPersonal=" + emailPersonal + ", telefono=" + telefono + ", movil="
				+ movil + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Expediente> getExpedientes() {
		return expedientes;
	}

	public void setExpedientes(List<Expediente> expedientes) {
		this.expedientes = expedientes;
	}
}
