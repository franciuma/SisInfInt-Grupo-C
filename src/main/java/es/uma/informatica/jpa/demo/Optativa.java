package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Optativa extends Asignatura {

	@Column(name = "Plazas", nullable = false)
	private Integer plazas;
	@Column(name = "Mencion", length = 20)
	private String mencion;
	
	public Integer getPlazas() {
		return plazas;
	}
	
	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}
	
	public String getMencion() {
		return mencion;
	}
	
	public void setMencion(String mencion) {
		this.mencion = mencion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mencion == null) ? 0 : mencion.hashCode());
		result = prime * result + ((plazas == null) ? 0 : plazas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Optativa other = (Optativa) obj;
		if (mencion == null) {
			if (other.mencion != null)
				return false;
		} else if (!mencion.equals(other.mencion))
			return false;
		if (plazas == null) {
			if (other.plazas != null)
				return false;
		} else if (!plazas.equals(other.plazas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Optativa [plazas=" + plazas + ", mencion=" + mencion + "]";
	}
	
}
