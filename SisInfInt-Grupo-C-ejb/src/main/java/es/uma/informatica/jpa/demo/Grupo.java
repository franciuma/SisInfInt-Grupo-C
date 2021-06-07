package es.uma.informatica.jpa.demo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name = "findAllGrupos", query = "select grp from Grupo grp")
public class Grupo {
	
	@Id 
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	@Column(nullable = false, name = "Curso")
	private Integer curso;
	@Column(nullable = false, name = "Letra", length = 1)
	private String letra;
	@Column(nullable = false, name = "Turno_Mañana_Tarde", length = 10)
	private String turnoMañanaTarde;
	@Column(nullable = false, name = "Ingles")
	private Boolean ingles;
	@Column(name = "Visible")
	private Boolean visible;
	@Column(name = "Asignar", length = 20)
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
	
	public Grupo(int curso2, String letra2, String turno, boolean ingles2, boolean visible2, Titulacion tit) {
		curso = curso2;
		letra = letra2;
		turnoMañanaTarde = turno;
		ingles = ingles2;
		visible = visible2;
		titulacion = tit;
	}
	
	public Grupo(Integer curso2, String letra2, String turnoMañanaTarde2, Boolean ingles2,
			Boolean visible2, String asignar2, Integer plazas2) {
		curso = curso2;
		letra = letra2;
		turnoMañanaTarde = turnoMañanaTarde2;
		ingles = ingles2;
		visible = visible2;
		asignar = asignar2;
		plazas = plazas2;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Integer getCurso() {
		return curso;
	}
	
	public void setCurso(Integer curso) {
		this.curso = curso;
	}
	
	public String getLetra() {
		return letra;
	}
	
	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	public String getTurnoMañanaTarde() {
		return turnoMañanaTarde;
	}
	
	public void setTurnoMañanaTarde(String turnoMañanaTarde) {
		this.turnoMañanaTarde = turnoMañanaTarde;
	}
	public boolean getIngles() {
		return ingles;
	}
	
	public void setIngles(boolean ingles) {
		this.ingles = ingles;
	}
	
	public boolean getVisible() {
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
		result = prime * result + ((clases == null) ? 0 : clases.hashCode());
		result = prime * result + ((curso == null) ? 0 : curso.hashCode());
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((grupos == null) ? 0 : grupos.hashCode());
		result = prime * result + ((gruposPorAsig == null) ? 0 : gruposPorAsig.hashCode());
		result = prime * result + id;
		result = prime * result + ((ingles == null) ? 0 : ingles.hashCode());
		result = prime * result + ((letra == null) ? 0 : letra.hashCode());
		result = prime * result + ((plazas == null) ? 0 : plazas.hashCode());
		result = prime * result + ((titulacion == null) ? 0 : titulacion.hashCode());
		result = prime * result + ((turnoMañanaTarde == null) ? 0 : turnoMañanaTarde.hashCode());
		result = prime * result + ((visible == null) ? 0 : visible.hashCode());
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
		if (clases == null) {
			if (other.clases != null)
				return false;
		} else if (!clases.equals(other.clases))
			return false;
		if (curso == null) {
			if (other.curso != null)
				return false;
		} else if (!curso.equals(other.curso))
			return false;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (grupos == null) {
			if (other.grupos != null)
				return false;
		} else if (!grupos.equals(other.grupos))
			return false;
		if (gruposPorAsig == null) {
			if (other.gruposPorAsig != null)
				return false;
		} else if (!gruposPorAsig.equals(other.gruposPorAsig))
			return false;
		if (id != other.id)
			return false;
		if (ingles == null) {
			if (other.ingles != null)
				return false;
		} else if (!ingles.equals(other.ingles))
			return false;
		if (letra == null) {
			if (other.letra != null)
				return false;
		} else if (!letra.equals(other.letra))
			return false;
		if (plazas == null) {
			if (other.plazas != null)
				return false;
		} else if (!plazas.equals(other.plazas))
			return false;
		if (titulacion == null) {
			if (other.titulacion != null)
				return false;
		} else if (!titulacion.equals(other.titulacion))
			return false;
		if (turnoMañanaTarde == null) {
			if (other.turnoMañanaTarde != null)
				return false;
		} else if (!turnoMañanaTarde.equals(other.turnoMañanaTarde))
			return false;
		if (visible == null) {
			if (other.visible != null)
				return false;
		} else if (!visible.equals(other.visible))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grupo [id=" + id + ", curso=" + curso + ", letra=" + letra + ", turnoMañanaTarde=" + turnoMañanaTarde
				+ ", ingles=" + ingles + ", visible=" + visible + ", asignar=" + asignar + ", plazas=" + plazas
				+ ", titulacion=" + titulacion + ", grupos=" + grupos + ", grupo=" + grupo + ", gruposPorAsig="
				+ gruposPorAsig + ", clases=" + clases + "]";
	}
}
