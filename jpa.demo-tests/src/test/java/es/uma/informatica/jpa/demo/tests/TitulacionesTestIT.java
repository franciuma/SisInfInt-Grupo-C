// Generated by Selenium IDE
package es.uma.informatica.jpa.demo.tests;
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
import java.net.MalformedURLException;
import java.net.URL;
public class TitulacionesTestIT {
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
  public void insertartitulacion() {
    driver.get("http://localhost:8080/jpa.demo-war/faces/index.xhtml");
    driver.manage().window().maximize();
    driver.findElement(By.id("indexIndex:vista-index-titulacion")).click();
    driver.findElement(By.id("indexTi:IndTi-InsertarTi")).click();
    driver.findElement(By.id("InsTit-InsertarTitu:InsTit-codigo")).click();
    driver.findElement(By.id("InsTit-importarTitulaciones:InsTit-file")).click();
    driver.findElement(By.id("InsTit-importarTitulaciones:InsTit-file")).sendKeys("C:\\fakepath\\Titulacion.xlsx");
    driver.findElement(By.cssSelector("a:nth-child(3)")).click();
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(1)")).click();
    assertThat(driver.findElement(By.id("LiTit-titulaciones-id:j_idt10:0:LiTit-CodigoC2")).getText(), is("1041"));
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(2)")).click();
    assertThat(driver.findElement(By.id("LiTit-titulaciones-id:j_idt10:0:LiTit-NombreC2")).getText(), is("Grado en Ingeniería Informática"));
    driver.findElement(By.cssSelector("tr:nth-child(1) > td:nth-child(3)")).click();
    assertThat(driver.findElement(By.id("LiTit-titulaciones-id:j_idt10:0:LiTit-CreditosC2")).getText(), is("240"));
    driver.findElement(By.cssSelector("th:nth-child(5)")).click();
    driver.findElement(By.id("LiTit-titulaciones-id:LiTit-EliminarTodas")).click();
    assertThat(driver.switchTo().alert().getText(), is("TITULACIONES BORRADAS"));
  }
  @Ignore
  @Test
  public void creartitulacion() {
    driver.get("http://localhost:8080/jpa.demo-war/faces/index.xhtml");
    driver.manage().window().maximize();
    driver.findElement(By.id("indexIndex:vista-index-titulacion")).click();
    driver.findElement(By.id("indexTi:IndTi-InsertarTi")).click();
    driver.findElement(By.id("InsTit-InsertarTitu:InsTit-codigo")).click();
    driver.findElement(By.id("InsTit-InsertarTitu:InsTit-codigo")).sendKeys("1042");
    driver.findElement(By.id("InsTit-InsertarTitu:InsTit-nombre")).click();
    driver.findElement(By.id("InsTit-InsertarTitu:InsTit-nombre")).sendKeys("Ingenieria Informatica");
    driver.findElement(By.id("InsTit-InsertarTitu:InsTit-creditos")).click();
    driver.findElement(By.id("InsTit-InsertarTitu:InsTit-creditos")).sendKeys("60");
    driver.findElement(By.id("InsTit-InsertarTitu:InsTit-BotonCrearTit")).click();
//    assertThat(driver.findElement(By.id("LiTit-titulaciones-id:j_idt10:0:LiTit-CodigoC2")).getText(), is("1042"));
//    assertThat(driver.findElement(By.id("LiTit-titulaciones-id:j_idt10:0:LiTit-NombreC2")).getText(), is("Ingenieria Informatica"));
//    assertThat(driver.findElement(By.id("LiTit-titulaciones-id:j_idt10:0:LiTit-CreditosC2")).getText(), is("60"));
    driver.findElement(By.id("LiTit-titulaciones-id:j_idt10:0:LiTit-EliminarC2")).click();
  }
}
