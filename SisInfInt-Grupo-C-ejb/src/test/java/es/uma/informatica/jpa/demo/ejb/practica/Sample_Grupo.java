package es.uma.informatica.jpa.demo.ejb.practica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import java.util.logging.Logger;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Test;
import es.uma.informatica.ejb.GestionGrupo;
import es.uma.informatica.ejb.exceptions.GrupoNoEncontradoException;
import es.uma.informatica.ejb.exceptions.ProyectoException;
import es.uma.informatica.jpa.demo.Grupo;
import es.uma.informatica.jpa.demo.Titulacion;
import es.uma.informatica.sii.anotaciones.Requisitos;

public class Sample_Grupo {

	
	private static final Logger LOG = Logger.getLogger(Sample_Grupo.class.getCanonicalName());

	private static final String GRUPO_EJB = "java:global/classes/GrupoEJB";
	private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaTest";
	
	private GestionGrupo gestionGrupo;
	
	@Before
	public void setup() throws NamingException  {
		gestionGrupo = (GestionGrupo) SuiteTest.ctx.lookup(GRUPO_EJB);
		BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
	}
	@Requisitos({"RF15"})
	@Test
	public void testInsertarGrupo() {
			final Integer curso = 2;
			final String letra = "A";
			final String turnoMañanaTarde = "mañana";
			final Boolean ingles = true;
			final Boolean visible = true;
			final String asignar = "asignao";
			final Integer plazas = 3;
			final Titulacion tit = new Titulacion(11, "sheesh", 66);
			
		try {
			
			Grupo g = new Grupo(curso,letra,turnoMañanaTarde,ingles,visible,asignar,plazas,tit);
			gestionGrupo.insertarGrupo(g);
			
			try {
				Grupo grup = gestionGrupo.obtenerGrupo(curso,letra,tit);
				assertEquals(curso,grup.getCurso());
				assertEquals(letra,grup.getLetra());
				assertEquals(turnoMañanaTarde,grup.getTurnoMañanaTarde());
				assertEquals(ingles,grup.getIngles());
				assertEquals(visible,grup.getVisible());
				assertEquals(asignar,grup.getAsignar());
				assertEquals(plazas,grup.getPlazas());
				
			}catch(ProyectoException e) {
				fail("NO deberia lanzar excepcion");
			}
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}

	}
	
	

	@Requisitos({"RF15"})
	@Test
	public void testEliminarGrupo() throws GrupoNoEncontradoException {
			
		final Integer curso = 2;
		final String letra = "A";
		final String turnoMañanaTarde = "mañana";
		final Boolean ingles = true;
		final Boolean visible = true;
		final String asignar = "asignao";
		final Integer plazas = 3;
		final Titulacion tit = new Titulacion(11, "sheesh", 66);
	
		try {
			Grupo g = new Grupo(curso,letra,turnoMañanaTarde,ingles,visible,asignar,plazas,tit);
			gestionGrupo.insertarGrupo(g);
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
		
		try {
			gestionGrupo.eliminarGrupo(2,"A",tit);
		}catch (GrupoNoEncontradoException e) {
			throw new RuntimeException(e);
		}
		
	}
	@Requisitos({"RF15"})
	@Test
	public void testObtenerGrupo() throws GrupoNoEncontradoException {
		final Integer curso = 2;
		final String letra = "A";
		final String turnoMañanaTarde = "mañana";
		final Boolean ingles = true;
		final Boolean visible = true;
		final String asignar = "asignao";
		final Integer plazas = 3;
		final Titulacion tit = new Titulacion(11, "sheesh", 66);
		Grupo g = new Grupo(curso,letra,turnoMañanaTarde,ingles,visible,asignar,plazas,tit);
		try {
			gestionGrupo.insertarGrupo(g);
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
		
		try {
			Grupo buscado = gestionGrupo.obtenerGrupo(2,"A",tit);
			assertEquals(buscado.getCurso(), g.getCurso());
			assertEquals(buscado.getLetra(), g.getLetra());
			assertEquals(buscado.getTitulacion(), g.getTitulacion());
		}catch (GrupoNoEncontradoException e) {
			throw new RuntimeException(e);
		}
	}
	@Requisitos({"RF15"})
	@Test
	public void testActualizarGrupo() throws GrupoNoEncontradoException {
		final Integer curso = 2;
		final String letra = "A";
		final String turnoMañanaTarde = "mañana";
		final Boolean ingles = true;
		final Boolean visible = true;
		final String asignar = "asignao";
		final Integer plazas = 3;
		final Titulacion tit = new Titulacion(11, "sheesh", 66);
		Grupo g = new Grupo(curso,letra,turnoMañanaTarde,ingles,visible,asignar,plazas,tit);
		
		try {
			gestionGrupo.insertarGrupo(g);
		}catch(ProyectoException e) {
			throw new RuntimeException(e);
		}
		
		try {
			Grupo grupo = gestionGrupo.obtenerGrupo(2,"A",tit);
			Grupo grupo_new = new Grupo();
			grupo_new.setCurso(1);
			grupo_new.setLetra("C");
			grupo_new.setTurnoMañanaTarde(grupo.getTurnoMañanaTarde());
			grupo_new.setIngles(grupo.getIngles());
			grupo_new.setVisible(grupo.getVisible());
			grupo_new.setAsignar(grupo.getAsignar());
			grupo_new.setPlazas(grupo.getPlazas());
			
			gestionGrupo.actualizarGrupo(grupo, grupo_new);
			assertNotEquals("Deberia haberse actualizado el grupo", grupo_new.getLetra() , grupo.getLetra());
			
		}catch(GrupoNoEncontradoException e) {
			throw new RuntimeException(e);
		}
		
	}
}
