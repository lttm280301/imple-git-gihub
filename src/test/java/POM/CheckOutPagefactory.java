package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import General.WebUI;

public class CheckOutPagefactory {
	private WebDriver driver;
	@FindBy(xpath="//input[@id='billing_address_phone']")
	private WebElement txtPhone;
	
	@FindBy(xpath="//input[@id='billing_address_address1']")
	private WebElement txtAdress;
	
	@FindBy(xpath="//select[@id='customer_shipping_province']")
	private WebElement cbbProvince;
	
	@FindBy(xpath="//select[@id='customer_shipping_district']")
	 WebElement cbbDistric;
	
	@FindBy(xpath="//select[@id='customer_shipping_ward']")
	private WebElement cbbWard;
	
	@FindBy(xpath="//div[@class='action-cart']")
	private WebElement cart;

	@FindBy(xpath=("//input[@name='shipping_rate_id']"))
	private WebElement radioCOD;
	
	@FindBy(xpath="//div[@class='middle-header']//li[1]")
    WebElement signin;
	
	public CheckOutPagefactory(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void beforeDN() {
		Actions action = new Actions(driver);
		action.click(cart).build().perform();
		
		signin.click();
		
		
	}
	
	
}
