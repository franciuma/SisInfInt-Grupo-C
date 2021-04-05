package es.uma.informatica.jpa.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Grupo {
	@Id 
	@GeneratedValue
	@Column(name="ID")
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
	
	
	
	@Override
	public String toString() {
		return "Grupo [id=" + id + ", curso=" + curso + ", letra=" + letra + ", turnoMañanaTarde=" + turnoMañanaTarde
				+ ", ingles=" + ingles + ", visible=" + visible + ", asignar=" + asignar + ", plazas=" + plazas + "]";
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
	
	
}
