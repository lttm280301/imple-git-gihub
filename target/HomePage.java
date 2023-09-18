package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	private WebDriver driver;
	
	//private By lblExpect=By.xpath("//td[normalize-space()='Manger Id : mgr123']");
	private WebElement lblUserAccount;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public String getHomePageTitle() {
		 return driver.getTitle();
	}
	
	public boolean verifyHomePageTitle() {
		String expectedTitle=" Guru99 Bank Manager HomePage ";
		return getHomePageTitle().equals(expectedTitle); 
	}
	
	public boolean verifyUserAccount() {
		String expectedTitle="Manger Id : mgr123";
		return lblUserAccount.getText().equals(expectedTitle);
	}
}
