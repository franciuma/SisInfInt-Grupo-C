package es.uma.informatica.jpa.demo.tests;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
public class InsertarmatriculaTestIT {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void insertarmatricula() throws InterruptedException {
	File f = new File("Datos alumnadoFAKE.xlsx");
	String absolutePath = f.getAbsolutePath();
		
    driver.get("http://0.0.0.0:8080/jpa.demo-war/faces/index.xhtml");
    driver.manage().window().maximize();
    
    driver.findElement(By.id("indexIndex:vista-index-alumnos")).click();
    driver.findElement(By.id("indexAl:IndAl-InsertarAl")).click();
    WebElement uploadElement = driver.findElement(By.xpath("//input[@id='InsAl-importarALumnos:InsAl-file']"));
    uploadElement.sendKeys(absolutePath);
    driver.findElement(By.cssSelector("a:nth-child(3)")).click();
    Thread.sleep(30000);
    driver.findElement(By.id("LiAl-alumnos-id:LiAl-boton-atras-vista-lista-alumno")).click();
    driver.findElement(By.id("indexAl:IndAl-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-expediente")).click();
    driver.findElement(By.id("indexEx:IndEx-ListaExp")).click();
    assertTrue("No existe el expediente",driver.getPageSource().contains("104100002"));
    driver.findElement(By.id("LiExp-expedientes-id:j_idt13:0:LiExp-Modificar")).click();
    driver.findElement(By.cssSelector("h2")).click();
    driver.findElement(By.cssSelector("a:nth-child(7)")).click();
    driver.findElement(By.id("InsMa-InsertarMatr:InsMa-cursoAcademico")).sendKeys("1");
    driver.findElement(By.id("InsMa-InsertarMatr:InsMa-estado")).sendKeys("Activa");
    driver.findElement(By.id("InsMa-InsertarMatr:InsMa-numArchivo")).sendKeys("12");
    driver.findElement(By.id("InsMa-InsertarMatr:InsMa-TurnoPreferente")).sendKeys("tarde");
    driver.findElement(By.id("InsMa-InsertarMatr:InsMa-fechaMatricula")).sendKeys("19-12-29");
    driver.findElement(By.id("InsMa-InsertarMatr:InsMa-nuevoIngreso")).sendKeys("si");
    driver.findElement(By.id("InsMa-InsertarMatr:InsMa-listado_asignaturas")).sendKeys("307-");
    driver.findElement(By.id("InsMa-InsertarMatr:InsMa-BotonCrearMatr")).click();
    driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-expC2")).click();
    assertThat(driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-expC2")).getText(), is("104100002"));
    driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-CursoAcadC2")).click();
    assertThat(driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-CursoAcadC2")).getText(), is("1"));
    driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-EstadoC2")).click();
    assertThat(driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-EstadoC2")).getText(), is("Activa"));
    driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-NumArchivoC2")).click();
    assertThat(driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-NumArchivoC2")).getText(), is("12"));
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(5)")).click();
    assertThat(driver.findElement(By.id("LiMa-matriculas-id:j_idt10:0:LiMa-TurnoPrefC2")).getText(), is("tarde"));
    driver.findElement(By.id("LiMa-matriculas-id:LiMa-EliminarTodasMa")).click();
    assertThat(driver.switchTo().alert().getText(), is("MATRICULAS BORRADAS"));
    driver.switchTo().alert().accept();
	Thread.sleep(8000);

    driver.findElement(By.cssSelector("a:nth-child(3)")).click();
    driver.findElement(By.id("indexMa:IndMa-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-expediente")).click();
    driver.findElement(By.id("indexEx:IndEx-ListaExp")).click();
    driver.findElement(By.name("LiExp-expedientes-id:j_idt12")).click();
    assertThat(driver.switchTo().alert().getText(), is("EXPEDIENTES BORRADOS"));
    driver.switchTo().alert().accept();
	Thread.sleep(8000);
	
    driver.findElement(By.cssSelector("a")).click();
    driver.findElement(By.id("indexEx:IndEx-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-alumnos")).click();
    driver.findElement(By.id("indexAl:IndAl-ListaAl")).click();
    driver.findElement(By.id("LiAl-alumnos-id:LiAl-boton-eliminarTodos-alumno")).click();
    assertThat(driver.switchTo().alert().getText(), is("ALUMNOs BORRADOs"));
    driver.switchTo().alert().accept();
	Thread.sleep(8000);
	
    driver.findElement(By.id("LiAl-alumnos-id:LiAl-boton-atras-vista-lista-alumno")).click();
  }
}