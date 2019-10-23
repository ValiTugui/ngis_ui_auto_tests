package co.uk.gel.lib;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
	
	protected static WebDriverWait wait;
	
	public static void forElementToBeDisplayed(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void forElementToBeDisplayed(WebDriver driver, WebElement element, int timeInSeconds) {
		wait = new WebDriverWait(driver, timeInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static void forElementToBeClickable(WebDriver driver, WebElement element) {
		wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void forElementToDisappear(WebDriver driver, By locator) {
		wait = new WebDriverWait(driver, 40);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public static void forNumberOfElementsToBeGreaterThan(WebDriver driver, By locator, int number) {
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
	}
	
	public static void forNumberOfElementsToBeEqualTo(WebDriver driver, By locator, int number) {
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
	}
	
	public static void forURLToContainSpecificText(WebDriver driver, String text) {
		wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.urlContains(text));
	}
	
	public static void forAlertToBePresent(WebDriver driver) {
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public static void seconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
