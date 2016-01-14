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
import org.openqa.selenium.support.ui.Select;

@RunWith(Parameterized.class)
public class Registro {
	private String email;
	private String password1;
	private String password2;
	private String textoEsperado;
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@Parameters
	public static Collection<String[]> valores(){
		return Arrays.asList(new String[][] {{"prueba1@prueba.com", "prueba","prueba", "OK"},
											 {"prueba2@prueba.com","prueba","pass", "Las passwords no coinciden"},
											 {"prueba1@prueba.com","prueba","prueba", "Error al registrar usuario. ¿Tal vez ya existe?"}});
	}
	
	public Registro(String email, String pwd1, String pwd2, String texto){
		this.email = email;
		this.password1 = pwd1;
		this.password2 = pwd2;
		this.textoEsperado = texto;
	}
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
	    baseUrl = "http://localhost:8080";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
    @Test
    public void testRegistro() throws Exception{
        driver.get(baseUrl + "/proyectoTySW/");
        driver.findElement(By.linkText("Crear Cuenta")).click();
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("nombre")).clear();
        driver.findElement(By.id("nombre")).sendKeys("prueba");
        driver.findElement(By.id("apellido1")).clear();
        driver.findElement(By.id("apellido1")).sendKeys("prueba");
        driver.findElement(By.id("apellido2")).clear();
        driver.findElement(By.id("apellido2")).sendKeys("prueba");
        driver.findElement(By.id("telefono")).clear();
        driver.findElement(By.id("telefono")).sendKeys("666666666");
        driver.findElement(By.id("pwd1")).clear();
        driver.findElement(By.id("pwd1")).sendKeys(password1);
        driver.findElement(By.id("pwd2")).clear();
        driver.findElement(By.id("pwd2")).sendKeys(password2);
        new Select(driver.findElement(By.id("selectCCAA"))).selectByVisibleText("Castilla-La Mancha");
        new Select(driver.findElement(By.id("selectProvincias"))).selectByVisibleText("Ciudad Real");
        new Select(driver.findElement(By.id("selectMunicipios"))).selectByVisibleText("Ciudad Real");
        driver.findElement(By.cssSelector("button.btn.btn-primary")).click();
        Thread.sleep(2000);
        try{
        	String textoAlerta = closeAlertAndGetItsText();
            assertEquals(textoEsperado, textoAlerta);
        }catch(Exception e){
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
