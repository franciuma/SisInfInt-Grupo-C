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
import java.net.MalformedURLException;
import java.net.URL;
public class AlumnosIT {
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
  public void insertaralumno() {
    driver.get("http://localhost:8080/jpa.demo-war/faces/index.xhtml");
    driver.manage().window().setSize(new Dimension(1853, 948));
    driver.findElement(By.id("j_idt6:vista-index-alumnos")).click();
    driver.findElement(By.id("j_idt6:vista-insertar-alumno")).click();
    driver.findElement(By.id("insertar-alumno:dni")).click();
    driver.findElement(By.id("insertar-alumno:dni")).sendKeys("1234");
    driver.findElement(By.id("insertar-alumno:nombre")).click();
    driver.findElement(By.id("insertar-alumno:nombre")).sendKeys("Erick");
    driver.findElement(By.id("insertar-alumno:telefono")).click();
    driver.findElement(By.id("insertar-alumno:telefono")).sendKeys("65656565");
    driver.findElement(By.id("insertar-alumno:movil")).click();
    driver.findElement(By.id("insertar-alumno:movil")).sendKeys("54545454");
    driver.findElement(By.id("insertar-alumno:email-institucional")).click();
    driver.findElement(By.id("insertar-alumno:email-institucional")).sendKeys("erick@uma.es");
    driver.findElement(By.id("insertar-alumno:email-personal")).click();
    driver.findElement(By.id("insertar-alumno:email-personal")).sendKeys("personal@gamil.com");
    driver.findElement(By.id("insertar-alumno:boton-crear-alumno")).click();
    driver.findElement(By.id("alumnos-id:j_idt8:1:dni-alumno")).click();
    driver.findElement(By.id("alumnos-id:j_idt8:1:dni-alumno")).click();
    {
      WebElement element = driver.findElement(By.id("alumnos-id:j_idt8:1:dni-alumno"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    assertThat(driver.findElement(By.id("alumnos-id:j_idt8:1:dni-alumno")).getText(), is("1234"));
    driver.findElement(By.id("alumnos-id:j_idt8:1:boton-eliminar-alumno")).click();
  }
}
