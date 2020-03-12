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
    public static NewPatient testData = new NewPatient();
    static Faker faker = new Faker();
    SeleniumLib seleniumLib;

    public MiPortalFileSubmissionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//a[@data-value='file_submissions']")
    public WebElement fileSubmissionLnk;

    @FindBy(xpath = "//button[@data-id='file_submissions-search-col']")
    public WebElement FileSubmissionSearchDropDownButton;

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
    public WebElement FileSubmissionSearchOperatorDropDownButton;

    @FindBy(xpath = "//button[@data-id='file_submissions-search-value']")
    public WebElement FileSubmissionSearchValueDropDownButton;

    @FindBy(xpath = "//ul[@class='dropdown-menu inner ']/li//span[text()='GLH']")
    public WebElement DropDownGLH;

    @FindBy(xpath = "//input[@data-shinyjs-resettable-id='file_submissions-search-value']")
    public WebElement getFileSubmissionDate;

    @FindBy(id = "file_submissions-search-add")
    public WebElement addButton;

    @FindBy(xpath = "//div[@id='file_submissions-search-search_term_pills']/span")
    public WebElement filterCriteria;

    @FindBy(xpath = "//div[@id='file_submissions-search-search_term_pills']/span/a")
    public WebElement ClosefilterCriteria;

    @FindBy(xpath = "//button[@id='file_submissions-search-search']")
    public WebElement searchButton;

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

    public boolean searchForCSVFileNameInSearchResults(String csvFilename) {
        try {
            String fileNameLocator = "//td[text()=" + csvFilename + "]";
            WebElement fileNameLocatorElement = null;
            fileNameLocatorElement = driver.findElement(By.xpath(fileNameLocator));
            if (Wait.isElementDisplayed(driver, fileNameLocatorElement, 10)) {
                Debugger.println("FileName is found :" + csvFilename);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("CSV File " + csvFilename + " not found." + exp);
            Actions.scrollToTop(driver);
            SeleniumLib.takeAScreenShot("csvFilenameNotFound.jpg");
            return false;
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
                System.out.println("Individual rows\n" + pairs + "\n");
                allRowsData.add(pairs);
            }
            return allRowsData;
        } catch (Exception exp) {
            Debugger.println("Exception from retrieving data." + exp);
            SeleniumLib.takeAScreenShot("retrieving-search-results.jpg");
            return null;
        }
    }

}