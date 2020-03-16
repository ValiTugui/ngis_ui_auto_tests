package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
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

import java.util.*;


public class MiPortalFileSubmissionPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiPortalFileSubmissionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//a[@data-value='file_submissions']")
    public WebElement fileSubmissionLnk;

    @FindBy(xpath = "//button[@data-id='file_submissions-search-col']")
    public WebElement fileSubmissionSearchDropDownButton;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box box-solid box-primary']")
    public WebElement mainSearchContainer;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//div[@class='tab-pane active']//h3[@class='box-title']")
    public WebElement searchTitle;

    @FindBy(xpath = "//div[@class='inner open'and@aria-expanded='true']//li//span")
    public WebElement genericDropDropDownValues;

    @FindBy(xpath = "//select[@id='file_submissions-search-col']/option")
    public List<WebElement> dropDownFileSubmissionsSearchDropValues;

    @FindBy(xpath = "//button[@data-id='file_submissions-search-col']")
    public WebElement dropDownFileSubmissionsSearchDropValues1;

    @FindBy(xpath = "//button[@data-id='file_submissions-search-operator']")
    public WebElement fileSubmissionSearchOperatorDropDownButton;

    @FindBy(xpath = "//button[@data-id='file_submissions-search-value']")
    public WebElement fileSubmissionSearchValueDropDownButton;

    @FindBy(xpath = "//input[@data-shinyjs-resettable-id='file_submissions-search-value']")
    public WebElement getFileSubmissionDate;

    @FindBy(id = "file_submissions-search-add")
    public WebElement addButton;

    @FindBy(xpath = "//div[@id='file_submissions-search-search_term_pills']/span")
    public WebElement badgeFilterSearchCriteria;

    @FindBy(xpath = "//div[@id='file_submissions-search-search_term_pills']/span/a")
    public WebElement badgeClosefilterCriteria;

    @FindBy(xpath = "//button[@id='file_submissions-search-search']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[@id='file_submissions-search-reset']")
    public WebElement resetButton;

    @FindBy(id = "file_submissions-display-display_options")
    public WebElement searchResultDisplayOptions;

    @FindBy(xpath = "//table[contains(@id,'DataTables_Table')]//tbody/tr")
    public List<WebElement> searchResultTable;


    public void selectSearchValueDropDown(WebElement element, String value) {
        try {
            Actions.retryClickAndIgnoreElementInterception(driver, element);
            // replaced due to intermittent error org.openqa.selenium.ElementClickInterceptedException: element click intercepted:
            //Click.element(driver, element);
            Wait.seconds(2);
            Click.element(driver, driver.findElement(By.xpath("//ul[@class='dropdown-menu inner ']/li//span[text()='" + value + "']")));
        } catch (Exception exp) {
            Debugger.println("Oops unable to locate drop-down element value : " + value + ":" + exp);
        }
    }

    public void fillInTheFileSubmissionDate(String date) {
        try {
            Wait.forElementToBeClickable(driver, getFileSubmissionDate);
            String actualDate = getFileSubmissionDate.getAttribute("data-shinyjs-resettable-value");
            if (!date.equals("today") || actualDate.isEmpty()) {
                Wait.seconds(2);
                getFileSubmissionDate.click();
                getFileSubmissionDate.clear();
                Wait.seconds(3);
                getFileSubmissionDate.sendKeys(date);
            }
            Debugger.println("Get current date : " + getFileSubmissionDate.getAttribute("data-shinyjs-resettable-value"));
        } catch (Exception exp) {
            Debugger.println("Exception filling date:" + exp);
            SeleniumLib.takeAScreenShot("UnableToFillDate.jpg");
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

    public String getAValueOfSearchedResult(String filterCriteria, int index) {
        String locator = "//td[text()='" + filterCriteria + "']/../td[" + index + "]";
        return driver.findElement(By.xpath(locator)).getText();
    }

    public Map<String, String> getValuesOfSearchedResult(String filterCriteria) {

        // deliberate 3 seconds wait is added to handle the slowness of UI
        //Exception.org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page document
        Wait.seconds(3);
        List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table')]/thead//th"));
        List<String> headers = new ArrayList<>();
        for (WebElement ele : allHeaders) {
            String header = ele.getText();
            headers.add(header);
        }
        String locator = "//td[text()='" + filterCriteria + "']/../td";
        List<WebElement> allValues = driver.findElements(By.xpath(locator));
        List<String> values = new ArrayList<>();
        for (WebElement ele : allValues) {
            String val = ele.getText();
            values.add(val);
        }

        Map<String, String> pairs = new HashMap<>();
        for (int i = 0; i < headers.size(); i++) {
            pairs.put(headers.get(i), values.get(i));
        }
        return pairs;
    }

    //getValuesOfCsvFileNamesSearchedResult

    public List<Map<String, String>> getValuesOfCsvFileNamesSearchedResult(String filterCriteria) {
        Wait.seconds(5);
        try {
            List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table')]/thead//th"));
            //Retrieve the column headers
            List<String> headers = new ArrayList<>();
            for (WebElement ele : allHeaders) {
                String header = ele.getText();
                headers.add(header);
            }
            // Retrieve the values
            String allResultRowsLoc = "//td[contains(text(),'" + filterCriteria + "')]/..";
            List<WebElement> allResultRows = driver.findElements(By.xpath(allResultRowsLoc));
            List<Map<String, String>> allRowsData = new ArrayList<>();

            // iterating all rows which matches condition
            for (WebElement row : allResultRows) {
                // Retrieve column data of each row
                List<WebElement> colData = row.findElements(By.tagName("td"));
                List<String> colVal = new ArrayList<>();
                if (colData != null) {
                    for (WebElement ele : colData) {
                        String val = ele.getText();
                        colVal.add(val);
                        System.out.println("test");
                    }
                }
                //Adding to map as key value - column data of row
                Map<String, String> pairs = new HashMap<>();
                if (colVal != null && colVal.size() > 0) {
                    for (int i = 0; i < headers.size(); i++) {
                        pairs.put(headers.get(i), colVal.get(i));
                    }
                }
                Debugger.println("Individual rows\n" + pairs + "\n");
                allRowsData.add(pairs);
            }
            return allRowsData;
        } catch (Exception exp) {
            Debugger.println("Exception from retrieving data." + exp);
            SeleniumLib.takeAScreenShot("retrieving-search-results.jpg");
            return null;
        }
    }

    public boolean resetButtonIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, mainSearchContainer);
            Wait.forElementToBeDisplayed(driver, searchBoxHeader);
            if (Wait.isElementDisplayed(driver, resetButton, 10)) {
                Debugger.println("reset button is displayed");
                return true;
            } else {
                Debugger.println("reset button element is not found");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("reset button element is not found");
            SeleniumLib.takeAScreenShot("resetButtonIsNotFound.jpg");
            return false;
        }
    }

    public boolean badgeFilterSearchCriteriaIsDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, mainSearchContainer);
            Wait.forElementToBeDisplayed(driver, searchBoxHeader);
            if (Wait.isElementDisplayed(driver, badgeFilterSearchCriteria, 10)) {
                Debugger.println("badge search criteria is displayed");
                return true;
            } else {
                Debugger.println("badge search criteria element is not found");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("badge search criteria element is not found");
            SeleniumLib.takeAScreenShot("badgeSearchIsNotFound.jpg");
            return false;
        }
    }

    public boolean verifyTheElementsOnFileSubmissionPage() {
        Wait.forElementToBeDisplayed(driver, mainSearchContainer);
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(searchBoxHeader);
        expectedElements.add(fileSubmissionSearchDropDownButton);
        expectedElements.add(fileSubmissionSearchOperatorDropDownButton);
        expectedElements.add(getFileSubmissionDate);
        expectedElements.add(addButton);
        expectedElements.add(searchButton);
        expectedElements.add(resetButton);

        for (int i = 0; i < expectedElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expectedElements.get(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean badgeFilterSearchCriteriaIsNotDisplayed() {
        try {
            Wait.forElementToBeDisplayed(driver, mainSearchContainer);
            if (!Wait.isElementDisplayed(driver, badgeFilterSearchCriteria, 10)) {
                Debugger.println("badge search criteria is NOT displayed as expected");
                return true;
            } else {
                Debugger.println("badge search criteria element is found");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("badge search criteria element is found");
            SeleniumLib.takeAScreenShot("badgeSearchIsFound.jpg");
            return false;
        }
    }

}