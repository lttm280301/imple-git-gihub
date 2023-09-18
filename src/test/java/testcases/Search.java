package testcases;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POM.LoginPageFactory;
import POM.SearchPagefactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import models.LoginData;
import models.SearchData;
import models.ValidationDataLogin;
import models.ValidationDataSearch;
import models.ValidationDataSignUp;

public class Search {
	WebDriver driver;
	String url;
    LoginPageFactory loginPF;
    SearchPagefactory sf;
    //driver = new ChromeDriver();
	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//url="https://www.guardian.com.vn/";
		driver.get("https://www.guardian.com.vn/");
		loginPF =new LoginPageFactory(driver);
		loginPF.checkPopUp();
		
		loginPF.checkPopUpCollagen();
		//WebUI.sleep(500);
		//loginPF.chatFrame();
		//loginPF.beforeDN();
		
		loginPF.purchase();
	}
	@DataProvider(name="dp")
	public String[] readJson() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader Reader = new FileReader(".\\dataJson\\search.json");
		Object obj = jsonParser.parse(Reader);
		JSONObject dataLoginJSONObj = (JSONObject) obj;
		 //System.out.println(dataLoginJSONObj);
		JSONArray jArray=(JSONArray) dataLoginJSONObj.get("dataSearch");
		//System.out.println(jArray);
		String[] arr=new String[jArray.size()];
		for(int i=0; i<jArray.size(); i++) {
			JSONObject user=(JSONObject) jArray.get(i);
			String input=(String)user.get("input");
			//mSystem.out.println(email);
			//String pass=(String) user.get("pass");
			String mess=(String) user.get("mess");
			//System.out.println(pass);
			arr[i]=input+","+mess;
		}
	return arr;
	}
	@DataProvider(name="dataSearch")
	  public Iterator<Object[]> dataValidation() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		SearchData ld = objectMapper.readValue(new File("dataJson/search.json"), SearchData.class);
		List<Object[]> results = new ArrayList<>();
		  for (ValidationDataSearch data : ld.getDataSearch()) {
		   results.add(new String[] {data.getInput(), data.getMess()});
		  
		}
		  return results.iterator();   
		 
		  }
	  @DataProvider(name="dataEmpty")
	  public Iterator<Object[]> dataEmpty() throws StreamReadException, DatabindException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		SearchData ld = objectMapper.readValue(new File("dataJson/search.json"), SearchData.class);
		List<Object[]> results = new ArrayList<>();
		
		  for (ValidationDataSearch data : ld.getDataEmpty()) {
		   results.add(new String[] {data.getInput(), data.getMess()});
		  
		}
		  return results.iterator();   
		 
		  }

	@Test(dataProvider="dataSearch")
    public void searchResult(String input, String mess) {
    	//driver.get("https://www.guardian.com.vn/");
		driver.manage().deleteAllCookies();
    	sf = new SearchPagefactory(driver);
    	//String user[]=data.split(",");
    	sf.searchResult(input);
    	Assert.assertTrue(sf.search(), mess);
    	loginPF.clearText();
	}
	@Test(dataProvider="dataSearch")
    public void dataEmpty(String input, String mess) {
    	//driver.get("https://www.guardian.com.vn/");
		driver.manage().deleteAllCookies();
    	sf = new SearchPagefactory(driver);
    	//String user[]=data.split(",");
    	sf.searchResult(input);
    	String message=sf.getHTML5ValidationMessage_search();
    	Assert.assertEquals(message, mess);
    	loginPF.clearText();
	}
		
		
}
