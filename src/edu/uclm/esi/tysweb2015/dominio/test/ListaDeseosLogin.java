package edu.uclm.esi.tysweb2015.dominio.test;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ListaDeseosLogin {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testListaDeseosLogin() throws Exception {
	String email = "luisnarrosanchez@gmail.com";
	String categoria = "Bricolaje";
	String anuncio = "Sierra para madera";
	String textoAlerta = "";
	
	driver.get(baseUrl + "/proyectoTySW/");
	driver.findElement(By.linkText("Login")).click();
	driver.findElement(By.id("logemail")).clear();
	driver.findElement(By.id("logemail")).sendKeys(email);
	driver.findElement(By.id("password")).clear();
	driver.findElement(By.id("password")).sendKeys("pass");
	driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
	Thread.sleep(2000);
	driver.findElement(By.linkText(categoria)).click();
	Thread.sleep(1000);
	driver.findElement(By.linkText(anuncio)).click();
	Thread.sleep(1000);
	driver.findElement(By.id("botonListaDeseos")).click();
	Thread.sleep(1000);
	try {
		textoAlerta = closeAlertAndGetItsText();
	    assertEquals("Añadido a tu lista correctamente.", textoAlerta);
	} catch (Exception e) {
		verificationErrors.append(e.toString());
	}
	Thread.sleep(2000);
	
	driver.findElement(By.linkText(email)).click();
	Thread.sleep(1000);
	driver.findElement(By.linkText(anuncio)).click();
	Thread.sleep(1000);
	driver.findElement(By.id("botonListaDeseos")).click();
	try {
		textoAlerta = closeAlertAndGetItsText();
	    assertEquals("Ya tienes este producto en tu lista de deseos.", textoAlerta);
	}catch (Exception e) {
		verificationErrors.append(e.toString());
	}
	driver.findElement(By.id("botonListaDeseos")).click();
	try {
		textoAlerta = closeAlertAndGetItsText();
	    assertEquals("Anuncio eliminado de la lista correctamente", textoAlerta);
	}catch (Exception e) {
		verificationErrors.append(e.toString());
	}
	Thread.sleep(2000);
	driver.findElement(By.id("desconectar")).click();
	try {
		textoAlerta = closeAlertAndGetItsText();
	    assertEquals("Usuario desconectado correctamente.", textoAlerta);
	}catch (Exception e) {
		verificationErrors.append(e.toString());
	}
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
