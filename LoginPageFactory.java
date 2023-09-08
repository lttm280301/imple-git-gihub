package POM;

import General.WebUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
	private WebDriver driver;
	//private actionKeyword;

	@FindBy(id="customer_email")
	private WebElement txtEmail;
	
	@FindBy(id="customer_password")
	private WebElement txtPass;
	
	@FindBy(xpath="//input[@value='Đăng nhập']")
	private WebElement btnSignin;
	
	@FindBy(xpath="//div[@class='title-infor-account text-center']")
	 WebElement lblYourAccount;
	
	@FindBy(xpath="//div[@class='action-account']")
	private WebElement account;

	@FindBy(xpath=("//div[starts-with(@id,'wrap-close-button')]//span"))
	private WebElement promotion;

	@FindBy(xpath=("//button[@class='close close-popup-contact']"))
	private WebElement collagen;

	@FindBy(xpath=("//div[@data-element-name='Minimize Button']"))
	private WebElement purchase;
	
	@FindBy(xpath=("//div[@class='errors']"))
	private WebElement inforInvalid;
	
	////div[@data-element-name='Minimize Button'
	
	@FindBy(xpath="//div[@class='middle-header']//li[1]")
      WebElement signin;
	
	public LoginPageFactory(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void beforeDN() {
		Actions action = new Actions(driver);
		action.moveToElement(account).build().perform();
		//if(WebUI.isElementExist(driver, By.xpath("//div[@class='middle-header']//li[1]"))) {
		signin.click();
		//}
		//else {
			
		//}
	}
	public void dn(String email, String pass) {
		
		txtEmail.sendKeys(email);
		txtPass.sendKeys(pass);
		btnSignin.click();
	}
	public String getHTML5ValidationMessage_email() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", txtEmail);
		}
	public String getHTML5ValidationMessage_pass() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", txtPass);
		}
	public Boolean getYourccount() {
		return lblYourAccount.isDisplayed();
	}
	public Boolean getMessage() {
		return inforInvalid.isDisplayed();
	}
	public void checkPopUp(){
	//if(promotion.isDisplayed()){
		if (WebUI.isElementExist(driver, By.xpath("//div[starts-with(@id,'wrap-close-button')]//span"))) {
       promotion.click();
	}
	else {
		
	}
	}
	public void checkPopUpCollagen(){
		//if(collagen.isDisplayed()){
		if (WebUI.isElementExist(driver, By.xpath("//button[@class='close close-popup-contact']"))) {
			collagen.click();
		}
		else {
			
		}
	}
	public void purchase() {
		if (WebUI.isElementExist(driver, By.xpath("//div[@data-element-name='Minimize Button']"))) {
			purchase.click();
			
		}
		//else if(WebUI.isElementExist(driver, By.xpath("//div[@class='ins-purchase-progress-button ins-element-link']"))&&(WebUI.isElementExist(driver, By.cssSelector("#button-popup-loyalty-1")))) {
			
		//}
		else if (WebUI.isElementExist(driver, By.cssSelector("#button-popup-loyalty-1"))) {

		}
		else if(WebUI.isElementExist(driver, By.xpath("//div[@data-element-name='Minimize Button']")) && (WebUI.isElementExist(driver, By.cssSelector("#button-popup-loyalty-1")))){
			System.out.println("haha");
		}
		else {

		}
	}
	public void clearText() {
		txtEmail.clear();
		txtPass.clear();
	}


		
}
