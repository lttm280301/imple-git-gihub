package POM;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPagefactory {
	private WebDriver driver;
	//private actionKeyword;

	@FindBy(id="inputSearchAuto")
	private WebElement txtSearch;
	
	@FindBy(id="search-header-btn")
	private WebElement btnSearch;
	@FindBy(xpath="//p[@class='subtxt']")
	private WebElement result;
	@FindBy(xpath="//h2[contains(text(),'Không tìm thấy nội dung bạn yêu cầu')]")
	private WebElement noresult;
	
	public String getHTML5ValidationMessage_search() {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", txtSearch);
		}
	public Boolean SearchFunction() {
		return txtSearch.isDisplayed();
	}
	public Boolean noResult() {
		return noresult.isDisplayed();
	}
	public boolean search() {
		return result.isDisplayed();
	}
	public void searchResult(String input) {
		txtSearch.sendKeys(input);
		btnSearch.click();
	}
	public SearchPagefactory(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
