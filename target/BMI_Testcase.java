package testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BMIPageFactory;

public class BMI_Testcase {
	WebDriver driver;
	String url;
	
	public BMIPageFactory  objBMIPage;

	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		url="https://www.nhlbi.nih.gov/health/educational/lose_wt/BMI/bmi-m.htm";
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get(url);
		driver.manage().window().maximize();
		driver.navigate().refresh();
	}
	
	@AfterTest
	public void afterTest() {
		driver.close();
	}
	
	@Test
	public void BMI_TC() {
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		objBMIPage=new BMIPageFactory(driver);
		
		//Login to application
		objBMIPage.Calcu_BMI("160", "50");
		
		Assert.assertTrue(objBMIPage.getBmi().contains("19.5"));
	}
	

}
