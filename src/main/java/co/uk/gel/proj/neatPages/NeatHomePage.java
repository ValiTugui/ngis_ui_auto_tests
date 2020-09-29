package co.uk.gel.proj.neatPages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class NeatHomePage {
    WebDriver driver;
    SeleniumLib seleniumLib;

    public NeatHomePage(WebDriver driver) {
        this.driver = driver;
        seleniumLib = new SeleniumLib(driver);
        PageFactory.initElements(driver, this);
    }

    //WebElements
    @FindBy(xpath = "//a[contains(@href,'admin-tool')]/p")
    WebElement gMSLogo;
    @FindBy(xpath = "//div[contains(@class,'headerRight')]/span/span")
    WebElement userName;
    @FindBy(xpath = "//input[@id='humanReadableId_id']")
    WebElement searchBox;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement searchButton;
    @FindBy(xpath = "//span[@id='humanReadableId']/following-sibling::span/strong")
    public WebElement patientNgisID;

    //Validation Methods
    public boolean openAndVerifyNeatToolPage() {
        try {
            if (!Wait.isElementDisplayed(driver, gMSLogo, 5)) {
                Debugger.println("The NEAT Tool page is not loaded properly.");
                SeleniumLib.takeAScreenShot("NeatHomePageError.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver, userName, 5)) {
                Debugger.println("The user name is not present.");
                SeleniumLib.takeAScreenShot("NeatHomePageError.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from openAndVerifyNeatToolPage:" + exp);
            SeleniumLib.takeAScreenShot("NeatLoginException.jpg");
            return false;
        }
    }

    public boolean enterAndSearchPatientNGISId(String searchNgisId) {
        try {
            if (!Wait.isElementDisplayed(driver, searchBox, 5)) {
                Debugger.println("The NEAT tool page search box is not displayed.");
                SeleniumLib.takeAScreenShot("NeatSearchError.jpg");
                return false;
            }
            Debugger.println("The NGIS-ID to search:" + searchNgisId);
            if (!Wait.isElementDisplayed(driver, searchButton, 5)) {
                Debugger.println("The search button is not present.");
                SeleniumLib.takeAScreenShot("NeatSearchError.jpg");
                return false;
            }
            seleniumLib.sendValue(searchBox, searchNgisId);
            seleniumLib.clickOnWebElement(searchButton);
            Wait.seconds(2);//wait for the search result to load
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from enterAndSearchPatientNGISId:" + exp);
            SeleniumLib.takeAScreenShot("NeatSearchError.jpg");
            return false;
        }
    }

    public boolean verifyNgisID(String ngisID) {
        try {
            if (!Wait.isElementDisplayed(driver, patientNgisID, 5)) {
                Debugger.println("The patientNgisID is not displayed");
                SeleniumLib.takeAScreenShot("VerifyNgisID.jpg");
                return false;
            }
            String oldNgisId = patientNgisID.getText();
            if (!oldNgisId.equalsIgnoreCase(ngisID)) {
                Debugger.println("The Old Ngis ID is: " + oldNgisId + ":" + "updated ngis id: " + ngisID);
                SeleniumLib.takeAScreenShot("VerifyNgisID.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyNgisID:" + exp);
            SeleniumLib.takeAScreenShot("VerifyNgisID.jpg");
            return false;
        }
    }


}//end class