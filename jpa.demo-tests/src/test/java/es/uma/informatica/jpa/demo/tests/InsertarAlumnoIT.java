package es.uma.informatica.jpa.demo.tests;

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
public class InsertarAlumnoIT {
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
  public void testinsertaralumno() {
    driver.get("http://0.0.0.0:8080/jpa.demo-war/");
    driver.manage().window().setSize(new Dimension(1853, 948));
    driver.findElement(By.id("Login-Form:Login-DNI")).click();
    driver.findElement(By.id("Login-Form:Login-DNI")).sendKeys("admin");
    driver.findElement(By.id("Login-Form:Login-DNI")).sendKeys(Keys.ENTER);
    driver.findElement(By.id("Login-Form:Login-NombreCompleto")).sendKeys("admin");
    driver.findElement(By.cssSelector("a:nth-child(5)")).click();
    driver.findElement(By.id("indexIndex:vista-index-alumnos")).click();
    driver.findElement(By.id("indexAl:IndAl-InsertarAl")).click();
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-dni")).click();
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-dni")).sendKeys("79039261Z");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-nombre")).click();
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-nombre")).sendKeys("David");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-movil")).sendKeys("623 623 623");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-telefono")).click();
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-telefono")).sendKeys("952 952 952");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-email-institucional")).click();
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-email-institucional")).click();
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-email-institucional")).sendKeys("david@uma.es");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-email-personal")).sendKeys("david@gmail.com");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-boton-crear-alumno")).click();
    assertTrue("No existe el alumno", driver.getPageSource().contains("79039261Z")); //79039261Z
  }
}
