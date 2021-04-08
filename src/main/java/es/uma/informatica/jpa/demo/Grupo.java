package es.uma.informatica.jpa.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Grupo {
	
	@Id 
	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	@Column(nullable = false, unique = true, name = "Curso")
	private Integer curso;
	@Column(nullable = false, unique = true, name = "Letra")
	private char letra;
	@Column(nullable = false, name = "Turno_Mañana_Tarde")
	private String turnoMañanaTarde;
	@Column(nullable = false, name = "Ingles")
	private boolean ingles;
	@Column(name = "Visible")
	private boolean visible;
	@Column(name = "Asignar")
	private String asignar;
	@Column(name = "Plazas")
	private Integer plazas;
	
	@ManyToOne
	private Titulacion titulacion;
	
	@OneToMany(mappedBy = "grupo")
	private List<Grupo> grupos;
	
	@ManyToOne
	private Grupo grupo;
	
	@OneToMany(mappedBy = "grupo")
	private List<GruposPorAsig> gruposPorAsig;
	
	@OneToMany(mappedBy = "grupo")
	private List<Clase> clases;
	
	public Grupo() {
		
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCurso() {
		return curso;
	}
	
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	
	public char getLetra() {
		return letra;
	}
	
	public void setLetra(char letra) {
		this.letra = letra;
	}
	
	public String getTurnoMañanaTarde() {
		return turnoMañanaTarde;
	}
	
	public void setTurnoMañanaTarde(String turnoMañanaTarde) {
		this.turnoMañanaTarde = turnoMañanaTarde;
	}
	public boolean isIngles() {
		return ingles;
	}
	
	public void setIngles(boolean ingles) {
		this.ingles = ingles;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public String getAsignar() {
		return asignar;
	}
	public void setAsignar(String asignar) {
		this.asignar = asignar;
	}
	
	public Integer getPlazas() {
		return plazas;
	}
	
	public void setPlazas(Integer plazas) {
		this.plazas = plazas;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((asignar == null) ? 0 : asignar.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (ingles ? 1231 : 1237);
		result = prime * result + letra;
		result = prime * result + ((plazas == null) ? 0 : plazas.hashCode());
		result = prime * result + ((turnoMañanaTarde == null) ? 0 : turnoMañanaTarde.hashCode());
		result = prime * result + (visible ? 1231 : 1237);
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
		Grupo other = (Grupo) obj;
		if (asignar == null) {
			if (other.asignar != null)
				return false;
		} else if (!asignar.equals(other.asignar))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ingles != other.ingles)
			return false;
		if (letra != other.letra)
			return false;
		if (plazas == null) {
			if (other.plazas != null)
				return false;
		} else if (!plazas.equals(other.plazas))
			return false;
		if (turnoMañanaTarde == null) {
			if (other.turnoMañanaTarde != null)
				return false;
		} else if (!turnoMañanaTarde.equals(other.turnoMañanaTarde))
			return false;
		if (visible != other.visible)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Grupo [id=" + id + ", curso=" + curso + ", letra=" + letra + ", turnoMañanaTarde=" + turnoMañanaTarde
				+ ", ingles=" + ingles + ", visible=" + visible + ", asignar=" + asignar + ", plazas=" + plazas + "]";
	}
}
