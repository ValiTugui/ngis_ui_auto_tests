package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class MiGlhSamplesPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiGlhSamplesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//input[@data-shinyjs-resettable-id='glh_samples-search-value']")
    public WebElement getGlhFileSubmissionDate;

    @FindBy(xpath = "//div[@id='glh_samples-search-value_input']//input")
    public WebElement sampleSearchValue;

    public boolean fillInTheSampleConsignmentNumber(String number) {
        try {
            if (!Wait.isElementDisplayed(driver, sampleSearchValue, 10)) {
                Debugger.println("Search box is not displayed.");
                SeleniumLib.takeAScreenShot("ConsignmentNumberSearchboxNotPresent.jpg");
                return false;
            }
            Actions.clickElement(driver, sampleSearchValue);
            Actions.fillInValue(sampleSearchValue, number);
            return true;

        } catch (Exception exp) {
            Debugger.println("Exception filling SampleConsignmentNumber:" + exp);
            SeleniumLib.takeAScreenShot("fillInTheSampleConsignmentNumber.jpg");
            return false;
        }
    }

}