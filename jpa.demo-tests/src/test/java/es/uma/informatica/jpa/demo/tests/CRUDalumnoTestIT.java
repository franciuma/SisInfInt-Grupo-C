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
public class CRUDalumnoTestIT {
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
  public void editaralumno() throws InterruptedException {
    driver.get("http://localhost:8080/jpa.demo-war/faces/index.xhtml");
    driver.findElement(By.id("indexIndex:vista-index-alumnos")).click();
    driver.findElement(By.id("indexAl:IndAl-InsertarAl")).click();
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-dni")).sendKeys("541");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-nombre")).sendKeys("Fran");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-telefono")).sendKeys("654123654");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-movil")).sendKeys("652142536");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-email-institucional")).sendKeys("franci@uma.es");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-email-personal")).sendKeys("franci@gmail.com");
    driver.findElement(By.id("InsAl-insertar-alumno:InsAl-boton-crear-alumno")).click();
    assertTrue("No existe el alumno",driver.getPageSource().contains("541"));
    driver.findElement(By.id("LiAl-alumnos-id:j_idt12:1:LiAl-boton-modificar-alumno")).click();
    
    driver.findElement(By.id("EdAl-EditarAlumno:EdAl-movil")).clear();
    
    driver.findElement(By.id("EdAl-EditarAlumno:EdAl-movil")).sendKeys("123");
    driver.findElement(By.xpath("//a[contains(text(),\'Actualizar Alumno\')]")).click();
    driver.findElement(By.xpath("//form[@id=\'LiAl-alumnos-id\']/table/tbody/tr[2]/td[4]")).click();
    assertThat(driver.findElement(By.id("LiAl-alumnos-id:j_idt12:1:LiAl-movil")).getText(), is("123"));
    driver.findElement(By.id("LiAl-alumnos-id:j_idt12:1:LiAl-boton-eliminar-alumno")).click();
    Thread.sleep(8000);
    assertThat(driver.switchTo().alert().getText(), is("ALUMNO BORRADO"));
    driver.switchTo().alert().accept();
    Thread.sleep(4000);
    
  }
}
