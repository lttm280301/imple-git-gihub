package POM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LoginPage {
	private WebDriver driver;
	//Khai báo các đối tượng trên trang sẽ làm việc
//	private By pageTitle=By.xpath("/html/head/title");
	private By txtUser=By.name("uid");
	private By txtPass=By.name("password");
	private By btnLogin=By.name("btnLogin");
//	private By btnReset=By.name("btnReset");
//	private By txtTitle=By.className("barone");
	
//	private By lblUserMess=By.id("message23");//User-ID must not be blank
//	private By lblPassMess=By.name("message18");//Password must not be blank
	
	//Khởi tạo class khi được gọi và truyền driver vào để các thành phần trong class này đọc
	public LoginPage(WebDriver driver) {
		this.driver=driver;
	}

	
	public void setUserName(String userName) {
		driver.findElement(txtUser).sendKeys(userName);
	}
	
	public void setPassWord(String passWord) {
		driver.findElement(txtPass).sendKeys(passWord);
	}
	
	public void clickLogin() {
		driver.findElement(btnLogin).click();
	}
	
	public String getLoginPageTitle() {
		String pageTitle=driver.getTitle();
		return pageTitle;
	}
	public void Login(String user, String pass) {
		setUserName(user);
		setPassWord(pass);
		clickLogin();
	}
}
