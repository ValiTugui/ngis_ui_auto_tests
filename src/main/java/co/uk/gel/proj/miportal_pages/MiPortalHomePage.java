package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.util.Debugger;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class MiPortalHomePage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiPortalHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//a[@data-value='file_submissions']")
    public WebElement fileSubmissionLnk;

    @FindBy(xpath = "//a[contains(string(),'File Submissions')]")
    public WebElement genericNavigation;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box box-solid box-primary']")
    public WebElement mainSearchContainer;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//div[@class='tab-pane active']//h3[@class='box-title']")
    public WebElement searchTitle;

    @FindBy(xpath = "//div[@class='inner open'and@aria-expanded='true']//li//span")
    public List<WebElement> genericDropDropDownValues;

    @FindBy(xpath = "//select[@id='file_submissions-search-col']/option")
    public List<WebElement> dropDownFileSubmissionsSearchDropValues;

    @FindBy(xpath = "//button[@data-id='file_submissions-search-col']")
    public WebElement dropDownFileSubmissionsSearchDropValues1;

    //sidebarCollapsed
    @FindBy(id = "sidebarCollapsed")
    public WebElement mainSideBar;     //User attribute to get the value - data-collapsed="false"

    @FindBy(xpath = "//ul[contains(string(),'Sample Processing')]")
    public WebElement sampleProcessing;

    @FindBy(xpath = "//ul[contains(string(),'Sample Processing')]//ul")
    public WebElement sampleProcessing2;

    @FindBy(xpath = "//ul[contains(string(),'Sample Processing')]//ul/li/a")
    public List <WebElement> subMenusOfSimpleProcessing;

    @FindBy(xpath = "//a[@class='sidebar-toggle']")
    public WebElement sideBarToggle;


    public boolean navigateToMiPage(String expectedMipage) {
        try {
            By miPage;
            miPage = By.xpath("//a[contains(string(),\"" + expectedMipage + "\")]");
            Wait.forElementToBeDisplayed(driver, driver.findElement(miPage), 30);
            if (!Wait.isElementDisplayed(driver, driver.findElement(miPage), 10)) {
                Debugger.println(" Mandatory page Link is not displayed even after waiting period...Failing.");
                SeleniumLib.takeAScreenShot("MandatoryPageLink.jpg");
                return false;
            }
            Actions.retryClickAndIgnoreElementInterception(driver, driver.findElement(miPage));
            return true;
        } catch (Exception exp) {
            Debugger.println("Mandatory Page Link is not displayed even after waiting period...Failing." + exp);
            SeleniumLib.takeAScreenShot("MandatoryStageLink.jpg");
            return false;
        }
    }//end

    public boolean searchBoxContainerIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, mainSearchContainer);
            Wait.forElementToBeDisplayed(driver, searchBoxHeader);
            if (Wait.isElementDisplayed(driver, mainSearchContainer, 10)) {
                Debugger.println("main search container is displayed");
                return true;
            } else {
                Debugger.println("element not found ");
                SeleniumLib.takeAScreenShot("searchcontainerNotFound.jpg");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("main search container is not displayed");
            SeleniumLib.takeAScreenShot("searchcontainerNotFound.jpg");
            return false;
        }
    }

    public List<String> getDropDownValues(String dropDownButton) {
        try {
            By buttonElement;
            //button[@data-id='file_submissions-search-operator']
            Wait.seconds(3);
            buttonElement = By.xpath("//button[@data-id=\"" + dropDownButton + "\"]");
            Actions.clickElement(driver, driver.findElement(buttonElement));
            List<String> actualDropDownValues = new ArrayList<>();
            for (WebElement actualValue : genericDropDropDownValues) {
                actualDropDownValues.add(actualValue.getText().trim());
            }
            Debugger.println("Actual values: " + actualDropDownValues);
            return actualDropDownValues;
        } catch (Exception exp) {
            Debugger.println("Actual values are not displayed");
            SeleniumLib.takeAScreenShot("dropDownValuesAreNotFound.jpg");
            return null;
        }
    }

    public boolean verifyNoSearchResultMessage(String noResultMessage) {
        try {
            By displayedMessage;
            if (noResultMessage.contains("\'")) {
                // if the string contains apostrophe character, apply double quotes in the xpath string
                displayedMessage = By.xpath("//p[text()= \"" + noResultMessage + "\"]");
            } else {
                displayedMessage = By.xpath("//p[text()='" + noResultMessage + "']");
            }
            Wait.forElementToBeDisplayed(driver, driver.findElement(displayedMessage), 30);
            if (!Wait.isElementDisplayed(driver, driver.findElement(displayedMessage), 10)) {
                Debugger.println(" no result message is shown...Failing.");
                SeleniumLib.takeAScreenShot("noResultMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("no result message is shown...Failing...Failing." + exp);
            SeleniumLib.takeAScreenShot("noResultMessage.jpg");
            return false;
        }
    }//end

}

