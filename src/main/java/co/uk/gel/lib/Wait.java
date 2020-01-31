package co.uk.gel.lib;

import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import sun.security.ssl.Debug;

public class Wait {

   // protected static WebDriverWait wait;
    protected static WebDriver webDriver;

    public Wait(WebDriver driver) {
        webDriver = driver;
    }

    public static void forElementToBeDisplayed(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.visibilityOf(element));
        }catch(Exception exp){
            try{
                Actions.scrollToTop(driver);
                WebDriverWait wait = new WebDriverWait(driver, 100);
                wait.until(ExpectedConditions.visibilityOf(element));
            }catch(Exception exp1){
                try{
                    Actions.scrollToBottom(driver);
                    WebDriverWait wait = new WebDriverWait(driver, 100);
                    wait.until(ExpectedConditions.visibilityOf(element));
                }catch(Exception exp2){
                    Debugger.println("Wait Exception:"+exp);
                    SeleniumLib.takeAScreenShot("WaitException.jpg");
                    Assert.assertTrue(false);
                }
            }

        }
    }
    /*
	Added this method to verify the element is actually displayed after the specified waiting period.
	 */
    public static boolean isElementDisplayed(WebDriver driver, WebElement element,int seconds) {
        try{
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        }catch (Exception exp){
            Debugger.println("Exception from isElementDisplayed in Wait.java"+exp);
            return false;
        }
    }

    public static void forElementToBeDisplayed(WebDriver driver, WebElement element, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSeconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void forElementToBeClickable(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }catch(Exception exp){
            Debugger.println("Exception from waiting for element to be clickable...."+element+"..Waiting for 30 more seconds...");
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    public static void forElementToDisappear(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static boolean forNumberOfElementsToBeGreaterThan(WebDriver driver, By locator, int number) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));
            return true;
        }catch (Exception exp){
            Debugger.println("No of elements of locator expected to more than "+number+", but not, even after waiting period. Clicking again on Save and Continue.");
            return false;

        }
    }

    public static void forNumberOfElementsToBeEqualTo(WebDriver driver, By locator, int number) {
        try {
            Debugger.println("Closing Cookies Banner.");
            WebDriverWait wait = new WebDriverWait(driver, 100);
            wait.until(ExpectedConditions.numberOfElementsToBe(locator, number));
        }catch(Exception exp){
            Debugger.println("Exception from closing cookies banner: "+exp);
        }
    }

    public static void forURLToContainSpecificText(WebDriver driver, String text) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.urlContains(text));
    }

    public static void forAlertToBePresent(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public static void seconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void forPageToBeLoaded(WebDriver driver) {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
