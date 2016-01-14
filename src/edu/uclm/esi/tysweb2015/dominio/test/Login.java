package edu.uclm.esi.tysweb2015.dominio.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class Login {
  private String emailLogin;
  private String password;
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  @Parameters
  public static Collection<String[]> valores(){
	  return Arrays.asList(new String[][] {{"luisnarrosanchez@gmail.com","pass"}, {"paco@paco.com","pass"}});
  }
  
  public Login(String email, String pass){
	  this.emailLogin = email;
	  this.password = pass;
  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testLogin() throws Exception {
    driver.get(baseUrl + "/proyectoTySW/");
    driver.findElement(By.linkText("Login")).click();
    driver.findElement(By.id("logemail")).clear();
    driver.findElement(By.id("logemail")).sendKeys(emailLogin);
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys(password);
    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
    try {
    	assertEquals(emailLogin, driver.findElement(By.id("useremail")).getAttribute("value"));
    } catch(Error e){
    	verificationErrors.append(e.toString());
    }
    Thread.sleep(2000);
    driver.findElement(By.id("desconectar")).click();
    try {
    	String textoAlerta = closeAlertAndGetItsText();
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
