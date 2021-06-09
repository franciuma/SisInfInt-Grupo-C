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
  @Test
  public void importarAlumnosIT() throws InterruptedException {  
	File f = new File("Datos alumnadoFAKE.xlsx");
	String absolutePath = f.getAbsolutePath();
	driver.get("http://localhost:8080/jpa.demo-war/faces/insertar_Alumno_v2.xhtml");
    driver.manage().window().maximize();
    WebElement uploadElement = driver.findElement(By.xpath("//input[@id='InsAl-importarALumnos:InsAl-file']"));
    uploadElement.sendKeys(absolutePath);
    driver.findElement(By.cssSelector("a:nth-child(3)")).click();
	Thread.sleep(10000);
	assertTrue("No existe el alumno", driver.getPageSource().contains("94949702C")); // 94949702C
    driver.findElement(By.id("LiAl-alumnos-id:LiAl-boton-eliminarTodos-alumno")).click();
    Thread.sleep(8000);
    assertThat(driver.switchTo().alert().getText(), is("ALUMNOs BORRADOs"));
    driver.switchTo().alert().accept();
    Thread.sleep(4000);
  }
}
