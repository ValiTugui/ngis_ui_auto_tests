package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class KibanaPage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public KibanaPage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "(//div[contains(@class,'euiFormControlLayout__childrenWrapper')])[1]")
    public WebElement searchBox;

    @FindBy(xpath = "//div[@id='QuickSelectPopover']//button")
    public WebElement dateIcon;

    String buttonAction = "//span[contains(@class,'euiButton__content')]//span[text()='dummyName']";

    @FindBy(xpath = "//div[@class='euiPanel euiPanel--paddingMedium euiPopover__panel euiPopover__panel--left euiPopover__panel-isOpen']")
    public WebElement datePanel;

    String datelink = "//div[contains(@class,'euiFlexGrid--halves')]//button[text()='dummyLink']";

    public boolean searchReferralId(String refID) {
        try {
            Wait.seconds(20);
            if (!Wait.isElementDisplayed(driver, searchBox, 30)) {
                Debugger.println("The KIBANA tool page search box is not displayed.");
                SeleniumLib.takeAScreenShot("KibanaSearchError.jpg");
                return false;
            }
            Wait.seconds(12);//wait for the search result to load
            Debugger.println("The Ref ID to search:" + refID);
//            seleniumLib.sendValue(searchBox, refID);
            searchBox.click();
            searchBox.sendKeys(refID);
             Wait.seconds(2);//wait for the search result to load
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from searchReferralId:" + exp);
            SeleniumLib.takeAScreenShot("searchReferralId.jpg");
            return false;
        }
    }

    public boolean clickOnButton(String buttonName) {
        try {
            String button = buttonAction.replaceAll("dummyName", buttonName);
            WebElement buttonElement = driver.findElement(By.xpath(button));
            if (!Wait.isElementDisplayed(driver, buttonElement, 30)) {
                Debugger.println("Could not click on button.");
                SeleniumLib.takeAScreenShot("clickOnButton.jpg");
                return false;
            }
            Actions.clickElement(driver, buttonElement);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clickOnButton:" + exp);
            SeleniumLib.takeAScreenShot("clickOnButton.jpg");
            return false;
        }
    }

    public boolean selectTheDateLink(List<String> link) {
        try {
            String selectedLink = link.get(0);
            String linkName = datelink.replaceAll("dummyLink", selectedLink);
            WebElement date = driver.findElement(By.xpath(linkName));

            Wait.forPageToBeLoaded(driver);
            if (!Wait.isElementDisplayed(driver, dateIcon, 20)) {
                Debugger.println("Date Icon button is not displayed");
                SeleniumLib.takeAScreenShot("selectTheDateLink.jpg");
                return false;
            }
            Actions.clickElement(driver, dateIcon);
            Wait.seconds(20);
            Wait.forElementToBeDisplayed(driver, date);
            Click.element(driver, date);
            Wait.seconds(10);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from selectTheDateLink:" + exp);
            SeleniumLib.takeAScreenShot("selectTheDateLink.jpg");
            return false;
        }

    }

}
