package edu.uclm.esi.tysweb2015.dominio.test;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class InsertarAnuncioSinFoto {
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
  public void testInsertarAnuncioSinFoto() throws Exception {
    driver.get(baseUrl + "/proyectoTySW/");
    //Login
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("logemail")).clear();
    driver.findElement(By.id("logemail")).sendKeys("luisnarrosanchez@gmail.com");
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("pass");
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    Thread.sleep(2000);
    
    //Insertar Anuncio
    driver.findElement(By.id("nuevoAnuncio")).click();
    Thread.sleep(1000);
    driver.findElement(By.id("descripcion")).clear();
    driver.findElement(By.id("descripcion")).sendKeys("AnuncioPrueba2");
    new Select(driver.findElement(By.id("selectCategoria"))).selectByVisibleText("Hogar");
    driver.findElement(By.id("publicar")).click();
    Thread.sleep(1000);
    try{
    	assertEquals("OK: Anuncio publicado.", closeAlertAndGetItsText());
    }catch (Exception e){
    	verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("desconectar")).click();
    
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
