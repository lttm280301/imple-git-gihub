package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import config.actionKeyword;

public class BMIPageFactory {
	private WebDriver driver;
	//private actionKeyword;

	@FindBy(id="htc")
	private WebElement txtHeight;
	
	@FindBy(id="kg")
	private WebElement txtWeight;
	
	@FindBy(id="calcbmi")
	private WebElement btnCalcu;
	
	@FindBy(id="yourbmi")
	private WebElement lblBmi;
	
	public BMIPageFactory(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		//actionKeyword=new A
	}
	
	public void Calcu_BMI(String height, String weight) {
		txtHeight.sendKeys(height);
		txtWeight.sendKeys(weight);;
		btnCalcu.click();
	}
	
	public String getBmi() {
		return lblBmi.getAttribute("value");
	}
	
}
