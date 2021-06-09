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
public class AsignaturasTestIT {
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
  public void crearasignatura() {
    driver.get("http://localhost:8080/jpa.demo-war/faces/index.xhtml");
    driver.manage().window().setSize(new Dimension(665, 731));
    driver.findElement(By.id("indexIndex:vista-index-asignatura")).click();
    driver.findElement(By.id("indexAs:IndAs-insertarAsig")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-referencia")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-referencia")).sendKeys("1234");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-Codigo_Asignatura")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-Codigo_Asignatura")).sendKeys("213");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-creditos")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-creditos")).sendKeys("123");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-ofertada")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-ofertada")).sendKeys("si");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-nombre")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-nombre")).sendKeys("c");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-curso")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-curso")).sendKeys("1");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-caracter")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-caracter")).sendKeys("obligatoria");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-duracion")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-duracion")).sendKeys("213413");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-unidadTemporal")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-unidadTemporal")).sendKeys("213");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-idiomasImparticion")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-idiomasImparticion")).sendKeys("2");
    driver.findElement(By.id("InsAs-InsertarAsig:InsAs-BotonCrearAsig")).click();
    driver.findElement(By.cssSelector("td:nth-child(1)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-ReferenciaC2")).getText(), is("1234"));
    driver.findElement(By.cssSelector("td:nth-child(2)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-CodigoC2")).getText(), is("213"));
    driver.findElement(By.cssSelector("td:nth-child(3)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-CreditosC2")).getText(), is("123"));
    driver.findElement(By.cssSelector("td:nth-child(4)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-OfertadaC2")).getText(), is("false"));
    driver.findElement(By.cssSelector("td:nth-child(5)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-NombreC2")).getText(), is("c"));
    driver.findElement(By.cssSelector("td:nth-child(6)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-CursoC2")).getText(), is("1"));
    driver.findElement(By.cssSelector("table")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-CaracterC2")).getText(), is("obligatoria"));
    driver.findElement(By.cssSelector("td:nth-child(8)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-DurcacionC2")).getText(), is("213413"));
    driver.findElement(By.cssSelector("td:nth-child(9)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-UnidadTempCuatriC2")).getText(), is("213"));
    driver.findElement(By.cssSelector("td:nth-child(10)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-IdiomasImpartC2")).getText(), is("2"));
    driver.findElement(By.id("LiAsi-asignaturas-id")).click();
    driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-EliminarC2")).click();
  }
  @Ignore
  @Test
  public void modasignatura() {
    driver.get("http://localhost:8080/jpa.demo-war/faces/index.xhtml");
    driver.manage().window().setSize(new Dimension(665, 735));
    driver.findElement(By.id("indexIndex:vista-index-asignatura")).click();
    driver.findElement(By.id("indexAs:IndAs-insertarAsig")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-referencia")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-referencia")).sendKeys("25");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-Codigo_Asignatura")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-Codigo_Asignatura")).sendKeys("21345");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-creditos")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-creditos")).sendKeys("22334");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-ofertada")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-ofertada")).sendKeys("si");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-nombre")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-nombre")).sendKeys("r");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-curso")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-curso")).sendKeys("2");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-caracter")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-caracter")).sendKeys("c");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-duracion")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-duracion")).sendKeys("231");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-unidadTemporal")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-unidadTemporal")).sendKeys("2");
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-idiomasImparticion")).click();
    driver.findElement(By.id("InsAs-InsertarAsig:InsRef-idiomasImparticion")).sendKeys("3");
    driver.findElement(By.id("InsAs-InsertarAsig:InsAs-BotonCrearAsig")).click();
    driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-ModificarC2")).click();
    driver.findElement(By.id("EdAs-EditarAs:EdAs-nombre")).click();
    driver.findElement(By.id("EdAs-EditarAs:EdAs-nombre")).sendKeys("riiiii");
    driver.findElement(By.cssSelector("a:nth-child(13)")).click();
    driver.findElement(By.cssSelector("td:nth-child(5)")).click();
    assertThat(driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-NombreC2")).getText(), is("riiiii"));
    driver.findElement(By.id("LiAsi-asignaturas-id:j_idt10:0:LiAsi-EliminarC2")).click();
    assertThat(driver.switchTo().alert().getText(), is("ASIGNATURA BORRADA"));
  }
}
