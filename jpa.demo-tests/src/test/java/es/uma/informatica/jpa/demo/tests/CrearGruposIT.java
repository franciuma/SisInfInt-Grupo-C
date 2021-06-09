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

import es.uma.informatica.sii.anotaciones.Requisitos;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
public class CrearGruposIT {
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
  
  @Requisitos({"RF-19"})
  @Ignore
  @Test
  public void importarTitulacionIT() throws InterruptedException {
	File f = new File("Titulacion.xlsx");
	String absolutePath = f.getAbsolutePath();
		
	driver.get("http://localhost:8080/jpa.demo-war/faces/index.xhtml");
	driver.manage().window().maximize();
	    
	driver.findElement(By.id("indexIndex:vista-index-titulacion")).click();
	driver.findElement(By.id("indexTi:IndTi-InsertarTi")).click();
	WebElement uploadElement = driver.findElement(By.xpath("//input[@id='InsTit-importarTitulaciones:InsTit-file']"));
	uploadElement.sendKeys(absolutePath);
	driver.findElement(By.cssSelector("a:nth-child(3)")).click();
	Thread.sleep(4000);
	assertTrue("No existe la titulacion", driver.getPageSource().contains("1042"));
    
	// Ir a grupos
	driver.findElement(By.cssSelector("a")).click();
    driver.findElement(By.id("indexTi:IndTi-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-grupos")).click();
    driver.findElement(By.id("indexGr:IndGr-InsertarGr")).click();
    driver.findElement(By.id("InsGr-insertar-grupo:InsGr-CrearTodosGrupos")).click();
    driver.findElement(By.id("LiGr-grupos-id:LiGr-EliminarTodos")).click();
    assertThat(driver.switchTo().alert().getText(), is("GRUPOS BORRADOS"));
    driver.switchTo().alert().accept();
    Thread.sleep(4000);
    
    //Ir a titulaciones
    driver.findElement(By.cssSelector("a")).click();
    driver.findElement(By.id("indexGr:IndGr-Index")).click();
    driver.findElement(By.id("indexIndex:vista-index-titulacion")).click();
    driver.findElement(By.id("indexTi:IndTi-ListaTi")).click();
    driver.findElement(By.id("LiTit-titulaciones-id:LiTit-EliminarTodas")).click();
    assertThat(driver.switchTo().alert().getText(), is("TITULACIONES BORRADAS"));
    driver.switchTo().alert().accept();
    Thread.sleep(4000);
  }
}
