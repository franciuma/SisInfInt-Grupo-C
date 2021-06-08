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
import java.util.concurrent.TimeUnit;
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
  @Test
  public void importarAlumnosIT() {  
	driver.get("http://0.0.0.0:8080/jpa.demo-war/faces/insertar_Alumno_v2.xhtml");
    driver.manage().window().maximize();
    WebElement uploadElement = driver.findElement(By.xpath("//input[@id='InsAl-importarALumnos:InsAl-file']"));
    //WebDriverWait wait = new WebDriverWait(driver, 30);
    //wait.until(ExpectedConditions.elementToBeClickable(uploadElement));
    uploadElement.sendKeys("/home/alumno/GitHub/SisInfInt-Grupo-C/SisInfInt-Grupo-C-ejb/Datos alumnadoFAKE.xlsx/");
    driver.findElement(By.cssSelector("a:nth-child(3)")).click();
    driver.get("http://0.0.0.0:8080/jpa.demo-war/faces/lista_alumnos.xhtml");
    
    // TO-DO: FALTA QUE SE ELIMINE DESPUES DE LA PRUEBA
    
	/*driver.get(baseUrl);
    driver.manage().window().setSize(new Dimension(593, 580));
    driver.findElement(By.xpath("//input[@id='InsAl-importarALumnos:InsAl-file']")).click();
    WebElement uploadElement = driver.findElement(By.xpath("//input[@id='InsAl-importarALumnos:InsAl-file']"));
    //WebElement uploadElement = driver.findElement(By.id("InsAl-file"));
    uploadElement.sendKeys("/home/alumno/GitHub/SisInfInt-Grupo-C/SisInfInt-Grupo-C-ejb/Datos alumnadoFAKE.xlsx/");
    //driver.findElement(By.id("InsAl-importarALumnos:InsAl-file")).sendKeys("/home/alumno/GitHub/SisInfInt-Grupo-C/SisInfInt-Grupo-C-ejb/Datos alumnadoFAKE.xlsx/");
    driver.findElement(By.cssSelector("a:nth-child(3)")).click();
    driver.findElement(By.id("LiAl-alumnos-id:LiAl-boton-eliminarTodos-alumno")).click();
    assertThat(driver.switchTo().alert().getText(), is("ALUMNOs BORRADOs"));
  */
  }
}
