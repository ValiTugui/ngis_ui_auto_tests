package co.uk.gel.lib;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;

import java.text.DateFormatSymbols;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Actions {

	static Random random = new Random();
	static Faker faker = new Faker();

	public static void clickElement(WebDriver driver, WebElement element) {
		Wait.forElementToBeClickable(driver, element);
		element.click();
	}

	public static void selectValueFromDropdown(WebElement dropdownValue, String value) {
		dropdownValue.findElement(By.xpath("//span[text()='" + value + "']")).click();
	}

	public static void selectRandomValueFromDropdown(List<WebElement> dropdownValues) {
		int index = random.nextInt(dropdownValues.size() - 1);
		dropdownValues.get(index).click();
	}

	public static String getText(WebElement element) {
		return element.getText();
	}

	public static String getValue(WebElement element) {
		return element.getAttribute("value");
	}

	public static void fillInValue(WebElement element, String value) {
		element.sendKeys(value);
	}

	public static void clearTextField(WebElement element) {
		element.clear();
	}

	public static void clearField(WebElement element) {
		while (!getValue(element).isEmpty()) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	public static String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month-1].substring(0, 3);
	}

	public static String createValidNHSNumber() {
		String finalNhsNumber;
		// Generating random 9 digits string
		String nineDigitsNhsNumber = String.valueOf(faker.number().randomNumber(9, true));
		// Array with the weighting factor
		int weightingFactor[] = {10, 9, 8, 7, 6, 5, 4, 3, 2};
		int sum = 0;
		// Get every digit of the String and multiply it with the weighting factor
		for (int i = 0; i < 9; i++) {
			int digit = Character.getNumericValue(nineDigitsNhsNumber.charAt(i));
			int multiplies = digit*weightingFactor[i];
			sum = sum + multiplies;
		}
		// get the last digit of the NHS number
		int remainder = sum % 11;
		int lastDigit = 11 - remainder;
		if (lastDigit == 11) {
			finalNhsNumber = nineDigitsNhsNumber + "0";
		} else if (lastDigit == 10) {
			finalNhsNumber = createValidNHSNumber();
		} else {
			finalNhsNumber =  nineDigitsNhsNumber + String.valueOf(lastDigit);
		}
		return finalNhsNumber;
	}

	public static void switchTab(WebDriver driver) {
		String mainWindow = driver.getWindowHandle();	
		Set<String> s1 = driver.getWindowHandles();		
		Iterator<String> i1 = s1.iterator();		
		while(i1.hasNext())	{		
			String childWindow = i1.next();		
			if (!mainWindow.equalsIgnoreCase(childWindow)) {    		
				driver.switchTo().window(childWindow);		
				driver.switchTo().activeElement();
			}
		}
	}

	public static void scrollToTop(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0);"); 
	}

}
