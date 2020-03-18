package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
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


    @FindBy(xpath = "//a[contains(string(),'File Submissions')]")
    public WebElement genericNavigation;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box box-solid box-primary']")
    public WebElement mainSearchContainer;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//div[@class='tab-pane active']//h3[@class='box-title']")
    public WebElement searchTitle;

    @FindBy(xpath = "//div[@class='inner open'and@aria-expanded='true']//li//span")
    public List<WebElement> genericDropDropDownValues1;

    //enhanced for multiple drop-down value selection
    @FindBy(xpath = "//div[@class='inner open'and@aria-expanded='true']//li//span[@class='text']")
    public List<WebElement> genericDropDropDownValues;

    @FindBy(xpath = "//select[@id='file_submissions-search-col']/option")
    public List<WebElement> dropDownFileSubmissionsSearchDropValues;

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

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(),'Add')]")
    public WebElement addButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(),'Search')]")
    public WebElement searchButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(),'Reset')]")
    public WebElement resetButton;

    @FindBy(xpath = "//h3[text()='Search Results']")
    public WebElement searchResultTitle;

    @FindBy(xpath = "//div[contains(@class,'active')]//button[contains(string(), 'Display Options')]")
    public WebElement searchResultDisplayOptionsButton;

    @FindBy(xpath = "//div[contains(@class,'active')]//a[contains(string(),'Download CSV')]")
    public WebElement downloadCSVButton;

    @FindBy(xpath = "//table[contains(@id,'DataTables_Table')]/thead//tr")
    public WebElement searchResultRowHeader;

    @FindBy(xpath = "//div[@id='DataTables_Table_1_length']//select")
    public WebElement searchResultEntryOptionsSelection;

    @FindBy(css = "div[class*='modal-content']")
    public WebElement displayOptionsModalContent;

    // //span[text()='Compact grid']/../input
    @FindBy(xpath = "//span[text()='Compact grid']/../input")
    public WebElement compactGridCheckBox;

    @FindBy(xpath = "//span[text()='Compact grid']")
    public WebElement compactGridCheckBoxLabel;

    @FindBy(xpath = "//span[text()='Truncate columns']/../input")
    public WebElement truncateColumnsCheckBox;

    @FindBy(xpath = "//span[text()='Truncate columns']")
    public WebElement truncateColumnsCheckBoxLabel;

    @FindBy(xpath = "//h3[text()='Column ordering']")
    public WebElement headerColumnOrdering;

    @FindBy(xpath = "//button[@id='file_submissions-display-reset']")
    public WebElement resetHeaderOrdering;


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


    public void clickAddButton() {
        try {
            Wait.forElementToBeClickable(driver, addButton);
            Click.element(driver, addButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on addButton:" + exp);
            SeleniumLib.takeAScreenShot("NoaddButton.jpg");
        }
    }

    public void clickSearchButton() {
        try {
            Wait.forElementToBeClickable(driver, searchButton);
            Click.element(driver, searchButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on searchButton:" + exp);
            SeleniumLib.takeAScreenShot("NoSearchButton.jpg");
        }
    }

    public void clickResetButton() {
        try {
            Wait.forElementToBeClickable(driver, resetButton);
            Click.element(driver, resetButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on resetButton:" + exp);
            SeleniumLib.takeAScreenShot("NoResetButton.jpg");
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


    public boolean verifyTheElementsInTheSearchResultSection() {
        Wait.forElementToBeDisplayed(driver,searchResultRowHeader, 10 );
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(searchResultTitle);
        expectedElements.add(searchResultDisplayOptionsButton);
        expectedElements.add(searchResultRowHeader);
        expectedElements.add(searchResultEntryOptionsSelection);
        expectedElements.add(downloadCSVButton);
        for (int i = 0; i < expectedElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                return false;
            }
            Debugger.println("element " + i + " shown");
        }
        return true;
    }


    public boolean downloadMiCSVFile(String fileName) {
        try {
            //Delete if File already present
            TestUtils.deleteIfFilePresent(fileName, "");
            seleniumLib.clickOnWebElement(downloadCSVButton);
            Wait.seconds(15);//Wait for 15 seconds to ensure file got downloaded, large file taking time to download
            Debugger.println("Form: " + fileName + " ,downloaded from section: " + fileName);
            return true;
        } catch (Exception exp) {
            Debugger.println("Could not locate the form download button ..... " + exp);
            SeleniumLib.takeAScreenShot("OfflineOrderPrintFormsDownload.jpg");
            return false;
        }
    }


    public void clickSearchResultDisplayOptionsButton() {
        try {
            Wait.forElementToBeClickable(driver, searchResultDisplayOptionsButton);
            Click.element(driver, searchResultDisplayOptionsButton);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on displayOptions:" + exp);
            SeleniumLib.takeAScreenShot("NoDisplayOptions.jpg");
        }
    }

    public boolean modalContentIsDisplayed() {
        try {
            if (Wait.isElementDisplayed(driver, displayOptionsModalContent, 10)) {
                Debugger.println("modal-content is displayed as expected");
                return true;
            } else {
                Debugger.println("modal-content is NOT displayed");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("modal-content is NOT displayed");
            SeleniumLib.takeAScreenShot("modalContentNotShown.jpg");
            return false;
        }
    }

    public boolean verifyTheCheckBoxesDisplayedOnModalContent() {
        Wait.forElementToBeDisplayed(driver,displayOptionsModalContent, 10 );
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(compactGridCheckBox);
        expectedElements.add(truncateColumnsCheckBox);
        for (int i = 0; i < expectedElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                return false;
            }
            Debugger.println("element " + i + " shown");
        }
        return true;
    }


    public void clickResetButtonOnModalContent() {
        try {
            Wait.forElementToBeClickable(driver, resetHeaderOrdering);
            Click.element(driver, resetHeaderOrdering);
        } catch (Exception exp) {
            Debugger.println("Exception from Clicking on resetHeaderOrderingButton:" + exp);
            SeleniumLib.takeAScreenShot("NoResetHeaderOrderingButton.jpg");
        }
    }

}

