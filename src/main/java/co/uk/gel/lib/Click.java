package co.uk.gel.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Click {
	
	public static void element(WebDriver driver, WebElement element) {
		Wait.forElementToBeClickable(driver, element);
		element.click();
	}

}
