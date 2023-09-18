package POM;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import General.WebUI;

public class SignUpPageFactory {
	private WebDriver driver;
	// private actionKeyword;

	@FindBy(id = "last_name")
	private WebElement txtSurname;

	@FindBy(id = "first_name")
	private WebElement txtName;

	@FindBy(xpath = "//label[contains(text(),'Nữ')]")
	private WebElement Gender;

	@FindBy(id = "gender")
	private WebElement GenderGeneral;

	@FindBy(xpath = "//label[normalize-space()='Nam']")
	private WebElement GenderNam;

	@FindBy(id = "birthday")
	WebElement txtbirthday;

	@FindBy(id = "email")
	private WebElement txtemail;

	@FindBy(id = ("password"))
	private WebElement txtpass;

	@FindBy(xpath = ("//input[@value='Đăng ký']"))
	private WebElement btnDK;

	@FindBy(xpath = "//div[@class='action-account']")
	private WebElement account;

	@FindBy(xpath = ("//div[@data-element-name='Minimize Button']"))
	private WebElement purchase;

	@FindBy(xpath = ("//h2[contains(text(),'Không tìm thấy nội dung bạn yêu cầu')]"))
	private WebElement inforInvalid;

	//// div[@data-element-name='Minimize Button'

	@FindBy(xpath = "//div[@class='middle-header']//li[2]")
	WebElement signUp;

	public SignUpPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void beforeDN() {
		Actions action = new Actions(driver);
		action.moveToElement(account).build().perform();
		// if(WebUI.isElementExist(driver,
		// By.xpath("//div[@class='middle-header']//li[1]"))) {
		signUp.click();

	}

	public void dn(String surname, String name, String gender, String birthday, String email, String pass) {

		Actions action = new Actions(driver);

		action.sendKeys(txtSurname, surname).build().perform();

		action.sendKeys(txtName, name).build().perform();

		GenderGeneral.click();

		// txtbirthday.sendKeys(birthday);
		action.sendKeys(txtbirthday, birthday).build().perform();

		// txtemail.sendKeys(email);
		action.sendKeys(txtemail, email).build().perform();

		// txtpass.sendKeys(pass);
		action.sendKeys(txtpass, pass).build().perform();

		btnDK.click();
	}

	public String getHTML5ValidationMessage_Surname() {

		// JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
//				driver.findElement(By.xpath("//input[@id='last_name']")));
		return driver.findElement(By.xpath("//input[@id='last_name']")).getAttribute("validationMessage");
	}

	public String getHTML5ValidationMessage_Name() {
		//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		//return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", txtName);
		return txtName.getAttribute("validationMessage");
	}

	public String getHTML5ValidationMessage_email() {
		//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		//return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", txtemail);
		return txtemail.getAttribute("validationMessage");
	}

	public String getHTML5ValidationMessage_pass() {
		//JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		//return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", txtpass);
		return txtpass.getAttribute("validationMessage");
	}

	public void refresh() {
		if (!(txtSurname.getAttribute("value") == null)) {
			txtSurname.clear();
		}

		txtName.clear();
		txtbirthday.clear();
		txtemail.clear();
		txtpass.clear();
	}

}
