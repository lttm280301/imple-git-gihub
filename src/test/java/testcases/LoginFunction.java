package testcases;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import General.WebUI;
import POM.LoginPageFactory;


import io.github.bonigarcia.wdm.WebDriverManager;
import models.LoginData;
import models.ValidationDataLogin;

public class LoginFunction {
	WebDriver driver;
	String url;
    LoginPageFactory loginPF;
    //driver = new ChromeDriver();
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//url="https://www.guardian.com.vn/";
		driver.get("https://www.guardian.com.vn/");
		loginPF =new LoginPageFactory(driver);
		loginPF.checkPopUp();
		loginPF.checkPopUpCollagen();
		//WebUI.sleep(500);
		//loginPF.chatFrame();
		loginPF.beforeDN();
		loginPF.purchase();
	
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	
	}
	
	/*@DataProvider(name="dp")
	public String[] readJson() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader Reader = new FileReader(".\\dataJson\\loginInvalid.json");
		Object obj = jsonParser.parse(Reader);
		JSONObject dataLoginJSONObj = (JSONObject) obj;
		 //System.out.println(dataLoginJSONObj);
		JSONArray jArray=(JSONArray) dataLoginJSONObj.get("dataLoginInvalid");
		//System.out.println(jArray);
		String[] arr=new String[jArray.size()];
		for(int i=0; i<jArray.size(); i++) {
			JSONObject user=(JSONObject) jArray.get(i);
			String email=(String)user.get("email");
			//mSystem.out.println(email);
			String pass=(String) user.get("pass");
			String mess=(String) user.get("mess");
			//System.out.println(pass);
			arr[i]=email+","+pass+","+mess;
		}
	return arr;
	}*/
	
	  @DataProvider(name="dataLogin")
	  public Iterator<Object[]> emailValidation() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		LoginData ld = objectMapper.readValue(new File("dataJson/login1.json"), LoginData.class);
		List<Object[]> results = new ArrayList<>();
		
		  for (ValidationDataLogin data : ld.getValidationEmail()) {
		   results.add(new String[] {data.getEmail(), data.getPass(), data.getMess()});
		  
		}
		  return results.iterator();   
		 
		  }
	  @DataProvider(name="dataLoginPass")
	  public Iterator<Object[]> PassValidation(Method method) throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		LoginData ld = objectMapper.readValue(new File("dataJson/login1.json"), LoginData.class);
		List<Object[]> results = new ArrayList<>();
		  for (ValidationDataLogin data : ld.getValidationPass()) {
		   results.add(new String[] {data.getEmail(), data.getPass(), data.getMess()});
		  }
		  return results.iterator();   
		 
		  }
	  @Test(dataProvider="dataLoginPass")
		public void EmFieldEmpty1(String email, String pass, String mess) {
			//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			loginPF=new LoginPageFactory(driver);
			//loginPF.dn("", "hahahatrami01");
			loginPF.dn(email, pass);
			
			String message=loginPF.getHTML5ValidationMessage_email();
			
			Assert.assertEquals(message, mess);
			
			loginPF.clearText();
			
		}
	  
	  
		
	  
	   @Test(dataProvider="dataLogin")
		public void ValidationEmailField(String email, String pass, String mess) {
			//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			loginPF=new LoginPageFactory(driver);
			//loginPF.dn("", "hahahatrami01");
			loginPF.dn(email, pass);
			
			String message=loginPF.getHTML5ValidationMessage_email();
			
			Assert.assertEquals(message, mess);
			
			loginPF.clearText();
			
		}
	   /*@Test(dataProvider="dataLogin")
		public void PassFieldEmpty1(String email, String pass, String mess) {
			//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
			loginPF=new LoginPageFactory(driver);
			//loginPF.dn("", "hahahatrami01");
			loginPF.dn(email, pass);
			String message=loginPF.getHTML5ValidationMessage_email();
			Assert.assertEquals(message, mess);
			loginPF.clearText();
		}*/
	  

	/*@Test
	public void PassFieldEmpty(String email, String pass, String mess) {
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		loginPF=new LoginPageFactory(driver);
		loginPF.dn(email, pass);
		Reporter.log("Excute step in login function");
		String message=loginPF.getHTML5ValidationMessage_pass();
		Reporter.log("Get message");
		Assert.assertEquals(message, mess);
		Reporter.log("Assert with expect");
		//Assert.loginPF
		loginPF.clearText();
		
	}*/
	/*@Test
	public void EmailFieldEmpty() {
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		loginPF=new LoginPageFactory(driver);
		loginPF.dn("", "hahahatrami01");
		String message=loginPF.getHTML5ValidationMessage_email();
		Assert.assertEquals(message, "Please fill out this field.");
		loginPF.clearText();
	}
	@Test
	public void emailInvalid() {
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		loginPF=new LoginPageFactory(driver);
		loginPF.dn("trami070701@@gmail.com", "");
		String message=loginPF.getHTML5ValidationMessage_email();
		Assert.assertEquals(message, "A part following '@' should not contain the symbol '@'.");
		loginPF.clearText();
	}
	@Test
	public void EmailIncorrect() {
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		loginPF=new LoginPageFactory(driver);
		loginPF.dn("@gmail.com", "hahahatrami01");
		String message=loginPF.getHTML5ValidationMessage_email();
		Assert.assertEquals(message, "Please enter a part followed by '@'. '@gmail.com' is incomplete.");
		loginPF.clearText();
	}
	@Test
	public void EmailAndpassEmpty() {
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		loginPF=new LoginPageFactory(driver);
		loginPF.dn("", "");
		String message=loginPF.getHTML5ValidationMessage_email();
		Assert.assertEquals(message, "Please fill out this field.");
		loginPF.clearText();
	}
	@Test
	public void MisingDomainPartEmail() {
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		loginPF=new LoginPageFactory(driver);
		loginPF.dn("trami070701", "hahahatrami01");
		String message=loginPF.getHTML5ValidationMessage_email();
		Assert.assertEquals(message,"Please include an '@' in the email address. 'trami070701' is missing an '@'.");
		loginPF.clearText();
	}
	@Test
	public void IncludeSpaceInEmail() {
		//driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		loginPF=new LoginPageFactory(driver);
		loginPF.dn("trami070701 @gmail.com", "hahahatrami01");
		String message=loginPF.getHTML5ValidationMessage_email();
		Assert.assertEquals(message,"A part followed by '@' should not contain the symbol ' '.");
		loginPF.clearText();
	}*/
	/*@Test(dataProvider="dp")
    public void login(String data) {
    	//driver.get("https://www.guardian.com.vn/");
		driver.manage().deleteAllCookies();
    	loginPF=new LoginPageFactory(driver);
    	String user[]=data.split(",");
    	loginPF.dn(user[0], user[1]);
    	Reporter.log("Excute step in login function");
    	Assert.assertTrue(loginPF.getMessage(), user[2]);
    	Reporter.log("Assert with expect");
    	loginPF.clearText();
	}*/

	
    
}