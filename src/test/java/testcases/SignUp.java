package testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import General.WebUI;
import POM.LoginPageFactory;
import POM.SignUpPageFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.LoginData;
import models.SignUpData;
import models.ValidationDataLogin;
import models.ValidationDataSignUp;

public class SignUp {
	WebDriver driver;
	String url;
	LoginPageFactory loginPF;
	SignUpPageFactory signUpPF;

	// driver = new ChromeDriver();
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		// url="https://www.guardian.com.vn/";
		driver.get("https://www.guardian.com.vn/");
		signUpPF = new SignUpPageFactory(driver);
		loginPF = new LoginPageFactory(driver);
		loginPF.checkPopUp();
		loginPF.checkPopUpCollagen();
		driver.manage().deleteAllCookies();
		signUpPF.beforeDN();

	}

	
	@DataProvider(name="dataSignUp")
	  public Iterator<Object[]> surNameValidation() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		SignUpData ld = objectMapper.readValue(new File("dataJson/signUp.json"), SignUpData.class);
		List<Object[]> results = new ArrayList<>();
		
		  for (ValidationDataSignUp data : ld.getValidationSurName()) {
		   results.add(new String[] {data.getSurName(), data.getName(), data.getGender(),data.getDateOfBirth(), data.getEmail(), data.getPass(), data.getMess()});
		  
		}
		  return results.iterator();   
		 
		  }
	    @Test
		public void SurnameFieldEmpty(String surname,String name,String gender,String birthday,String email,String pass,String mess ) {
			// driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			signUpPF = new SignUpPageFactory(driver);
			//signUpPF.dn("", "duy", "", "", "destin_turcotte30@hotmail.com", "123456");
			signUpPF.dn(surname, name, gender, birthday, email, pass);
			//WebUI.sleep(1);
			String message = signUpPF.getHTML5ValidationMessage_Surname();
			//System.out.println(message);
			
			Assert.assertEquals(message, mess);
			signUpPF.refresh();

		}
	    @Test
		public void nameFieldEmpty(String surname,String name,String gender,String birthday,String email,String pass,String mess ) {
			// driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			signUpPF = new SignUpPageFactory(driver);
			//signUpPF.dn("", "duy", "", "", "destin_turcotte30@hotmail.com", "123456");
			signUpPF.dn(surname, name, gender, birthday, email, pass);
			//WebUI.sleep(1);
			String message = signUpPF.getHTML5ValidationMessage_Surname();
			//System.out.println(message);
			
			Assert.assertEquals(message, mess);
			signUpPF.refresh();

		}
	    @Test
		public void EmailFieldEmpty(String surname,String name,String gender,String birthday,String email,String pass,String mess ) {
			// driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			signUpPF = new SignUpPageFactory(driver);
			//signUpPF.dn("", "duy", "", "", "destin_turcotte30@hotmail.com", "123456");
			signUpPF.dn(surname, name, gender, birthday, email, pass);
			//WebUI.sleep(1);
			String message = signUpPF.getHTML5ValidationMessage_Surname();
			//System.out.println(message);
			
			Assert.assertEquals(message, mess);
			signUpPF.refresh();

		}
	    @Test
		public void PassFieldEmpty(String surname,String name,String gender,String birthday,String email,String pass,String mess ) {
			// driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			signUpPF = new SignUpPageFactory(driver);
			//signUpPF.dn("", "duy", "", "", "destin_turcotte30@hotmail.com", "123456");
			signUpPF.dn(surname, name, gender, birthday, email, pass);
			//WebUI.sleep(1);
			String message = signUpPF.getHTML5ValidationMessage_Surname();
			//System.out.println(message);
			
			Assert.assertEquals(message, mess);
			signUpPF.refresh();

		}
    //@Test
//	public void SurNameFieldEmptyBirthdayEmpty() {
//		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//		signUpPF=new SignUpPageFactory(driver);
//		signUpPF.dn("","duy","Nữ","","orrin.oconnell@yahoo.com","123456");
//		String message=signUpPF.getHTML5ValidationMessage_Surname();
//		Assert.assertEquals(message, "Please fill out this field.");
//		signUpPF.refresh();
//	}
	/*
	 * @Test public void SurNameFieldEmptyGenderEmpty() {
	 * //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	 * signUpPF=new SignUpPageFactory(driver);
	 * signUpPF.dn("","duy","","5/22/2012","giovanna_kreiger7@hotmail.com","123456")
	 * ; String message=signUpPF.getHTML5ValidationMessage_Surname();
	 * Assert.assertEquals(message, "Please fill out this field."); //Assert.loginPF
	 * signUpPF.refresh(); }
	 * 
	 * @Test public void NameEmpty() {
	 * //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	 * signUpPF=new SignUpPageFactory(driver);
	 * signUpPF.dn("le","","Nữ","","orrn.oconnell@yahoo.com","123456"); String
	 * message=signUpPF.getHTML5ValidationMessage_Name();
	 * Assert.assertEquals(message, "Please fill out this field.");
	 * signUpPF.refresh();
	 * 
	 * }
	 * 
	 * @Test public void EmailEmpty() {
	 * //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	 * signUpPF=new SignUpPageFactory(driver);
	 * signUpPF.dn("le","duy","Nữ","","orrn.oonnell@yahoo.com","123456"); String
	 * message=signUpPF.getHTML5ValidationMessage_email();
	 * Assert.assertEquals(message, "Please fill out this field.");
	 * signUpPF.refresh();
	 * 
	 * }
	 * 
	 * @Test public void PassEmpty() {
	 * //driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
	 * signUpPF=new SignUpPageFactory(driver);
	 * signUpPF.dn("le","duy","Nữ","","orrn.oonnel@yahoo.com","123456"); String
	 * message=signUpPF.getHTML5ValidationMessage_pass();
	 * Assert.assertEquals(message, "Please fill out this field.");
	 * signUpPF.refresh();
	 * 
	 * }
	 */
}
