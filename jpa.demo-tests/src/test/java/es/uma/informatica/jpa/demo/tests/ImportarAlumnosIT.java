package es.uma.informatica.jpa.demo.tests;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;
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
import java.util.concurrent.TimeUnit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
public class ImportarAlumnosIT {
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
  @Ignore
  @Test
  public void importarAlumnosIT() throws InterruptedException {  
	File f = new File("Datos alumnadoFAKE.xlsx");
	String absolutePath = f.getAbsolutePath();
	
	driver.get("http://localhost:8080/jpa.demo-war/faces/insertar_Alumno_v2.xhtml");
    driver.manage().window().maximize();
    WebElement uploadElement = driver.findElement(By.xpath("//input[@id='InsAl-importarALumnos:InsAl-file']"));
    uploadElement.sendKeys(absolutePath);
    driver.findElement(By.cssSelector("a:nth-child(3)")).click();
	Thread.sleep(30000);
	assertTrue("No existe el alumno", driver.getPageSource().contains("94949702C")); // 94949702C
    Thread.sleep(4000);
    
    //Ir a la lista de expedientes
    driver.findElement(By.id("LiAl-alumnos-id:LiAl-boton-atras-vista-lista-alumno")).click();
    driver.findElement(By.id("indexAl:IndAl-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-expediente")).click();
    driver.findElement(By.id("indexEx:IndEx-ListaExp")).click();
    assertTrue("No existe el expediente asociado al alumno", driver.getPageSource().contains("94949702C"));
    Thread.sleep(4000);
    
    //Ir a la lista de matriculas
    driver.findElement(By.cssSelector("a")).click();
    driver.findElement(By.id("indexEx:IndEx-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-matricula")).click();
    driver.findElement(By.id("indexMa:IndMa-ListaMa")).click();
    assertTrue("No existe la matricula asociada al expediente", driver.getPageSource().contains("104200006"));
    Thread.sleep(4000);
     
    // Elimina las matriculas
    driver.findElement(By.id("LiMa-matriculas-id:LiMa-EliminarTodasMa")).click();
    assertThat(driver.switchTo().alert().getText(), is("MATRICULAS BORRADAS"));
    driver.switchTo().alert().accept();
    Thread.sleep(15000);
    
    //Ir a la lista de expedientes
    driver.findElement(By.xpath("//a[contains(text(),'Atrás')]")).click();
    driver.findElement(By.id("indexMa:IndMa-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-expediente")).click();
    driver.findElement(By.id("indexEx:IndEx-ListaExp")).click();
    
    //Elimina los expedientes
    driver.findElement(By.name("LiExp-expedientes-id:j_idt13")).click();
    assertThat(driver.switchTo().alert().getText(), is("EXPEDIENTES BORRADOS"));
    driver.switchTo().alert().accept();
    Thread.sleep(10000);
  
    //Ir a la lista de alumnos
    driver.findElement(By.xpath("//a[contains(text(),'Atrás')]")).click();
    driver.findElement(By.id("indexEx:IndEx-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-alumnos")).click();
    driver.findElement(By.id("indexAl:IndAl-ListaAl")).click();
    
    //Elimina los alumnos
    driver.findElement(By.id("LiAl-alumnos-id:LiAl-boton-eliminarTodos-alumno")).click();
    assertThat(driver.switchTo().alert().getText(), is("ALUMNOs BORRADOs"));
    driver.switchTo().alert().accept();
    Thread.sleep(4000);
  }
}
