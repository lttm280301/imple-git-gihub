package General;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class WebUI {

	private static WebDriver driver;

	public WebUI(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Chờ đợi ép buộc với đơn vị là Giây
	 *
	 * @param second là số nguyên dương tương ứng số Giây
	 */
	public static void sleep(double second) {
		try {
			Thread.sleep((long) (second * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Chờ đợi element sẵn sàng hiển thị để thao tác theo thời gian tuỳ ý
	 *
	 * @param by      an element of object type By
	 * @param timeOut thời gian chờ tối đa
	 * @return một đối tượng WebElement đã sẵn sàng để thao tác
	 */
	public static WebElement waitForElementVisible(By by, long timeOut) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(500));

			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

		} catch (Throwable error) {
			//System.out.println("Timeout waiting for the element Visible. " + by.toString());
			Assert.fail("Timeout waiting for the element Visible. " + by.toString());

		}
		return null;
	}

	/**
	 * Chờ đợi element sẵn sàng hiển thị để thao tác
	 *
	 * @param by an element of object type By
	 * @return một đối tượng WebElement đã sẵn sàng để thao tác
	 */
	public static WebElement waitForElementVisible(By by) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(500));

			return wait.until(ExpectedConditions.visibilityOfElementLocated(by));

		} catch (Throwable error) {
			System.out.println("Timeout waiting for the element Visible. " + by.toString());
			//Assert.fail("Timeout waiting for the element Visible. " + by.toString());
		}
		return null;
	}

	/**
	 * Chờ đợi trang tải xong (Javascript) với thời gian mặc định từ config
	 */
	public static void waitForPageLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(500));
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// wait for Javascript to loaded
		ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver)
				.executeScript("return document.readyState").toString().equals("complete");

		// Get JS is Ready
		boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

		// Wait Javascript until it is Ready!
		if (!jsReady) {
			System.out.println("Javascript in NOT Ready!");
			// Wait for Javascript to load
			try {
				wait.until(jsLoad);
			} catch (Throwable error) {
				error.printStackTrace();
				Assert.fail("Timeout waiting for page load (Javascript). (30s)");
			}
		}
		}
		public static  boolean isElementExist(WebDriver driver,By locator) {
			try {
				driver.findElement(locator);
				return true;
			}
			catch (NoSuchElementException e) {
				return false;
			}

		}
	

}
