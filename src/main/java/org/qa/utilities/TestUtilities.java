package org.qa.utilities;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.testbase.BaseClass;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class TestUtilities extends BaseClass {
	
	public static WebDriver driver;
	public WebDriverWait wait;
	public Select s;
	public int i;
	public int count = 3;
	public SoftAssert sa;
	public static String filepath = "user.dir" + "/Screenshots";


	public WebDriver switchtoFrameByWebElement(WebDriver driver, WebElement frameElement) {

		driver.switchTo().frame(frameElement);
		return driver;
	}

	public void switchtoFrameByIndex(int frameNumber) {

		driver.switchTo().frame(frameNumber);
	}

	public void explicitWaitinit(WebDriver driver, int timeout) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
	}

	public void visibilityofWebElement(WebElement element, WebDriver driver) {

		wait.until(ExpectedConditions.visibilityOf(element)).click();
	}

	static String pageLoadStatus;
	static JavascriptExecutor js;

	public static void waitForPageLoad1() {

		do {

			js = (JavascriptExecutor) driver;

			pageLoadStatus = (String) js.executeScript("return document.readyState");

		} while (!pageLoadStatus.equals("complete"));

		System.out.println("Page Loaded.");

	}

	public void waitForPageLoad(WebDriver driver, int timeout) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(new Function<WebDriver, Object>() {
			public Object apply(WebDriver webDriver) {
				return ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
			}
		});
	}

	/*
	 * public void waitForPageLoad(WebDriver driver, int timeout) { wait = new
	 * WebDriverWait(driver, timeout); wait.until(webDriver -> ((JavascriptExecutor)
	 * webDriver).executeScript("return document.readyState") .equals("complete"));
	 * }
	 */

	public static void selectDateByJSDate(WebDriver driver, WebElement element, String dateVal) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].setAttribute('value','" + dateVal + "');", element);

	}

	public void waitForPageLoad2(WebDriver driver, int timeout) {

//	    Wait<WebDriver> wait = new WebDriverWait(driver, 30);
		wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(new Function<WebDriver, Boolean>() {
			public Boolean apply(WebDriver driver) {
//	            System.out.println("Current Window State       : "
//	                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
				return String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
						.equals("complete");
			}
		});
	}

	// Method to enter the values
	public void sendKeys(WebDriver driver, WebElement element, String value, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(value);
	}

	// method to clear the text field
	public void clear(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		element.clear();
	}

	// method to click on element using explicit wait
	public void clickOn(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	// method to check visibility of an element
	public WebElement clickOn1(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	public void clickOnAfterPageLoad(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.stalenessOf(element));
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	// method to click using actions class
	public void clickOnActions(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	// method to click using js
	public void clickUsingJS(WebDriver driver, WebElement element, int timeout) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	// method to get text
	public String getText(WebDriver driver, WebElement element, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.elementToBeClickable(element));
		return element.getText();
	}

	// method to select the value
	public void selectValue(WebDriver driver, WebElement element, int timeout) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("return document.readyState", "complete");
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).until(ExpectedConditions.visibilityOf(element));
		executor.executeScript("arguments[0].click();", element);
	}

	public static void clickonforStale(WebDriver driver, WebElement locator, int timeout) {
		new WebDriverWait(driver, Duration.ofSeconds(timeout)).ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));
		locator.click();
	}

	


}
