package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.junit.Assert;
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


    @FindBy(xpath = "//button[@data-id='file_submissions-search-col']")
    public WebElement fileSubmissionSearchDropDownButton;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box box-solid box-primary']")
    public WebElement mainSearchContainer;

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//div[@class='tab-pane active']//h3[@class='box-title']")
    public WebElement searchTitle;

    @FindBy(xpath = "//button[@data-id='file_submissions-search-operator']")
    public WebElement fileSubmissionSearchOperatorDropDownButton;

//    @FindBy(xpath = "//input[@data-shinyjs-resettable-id='file_submissions-search-value']")
    @FindBy(xpath = "//input[contains(@data-shinyjs-resettable-id,'search-value')]")
    public WebElement getFileSubmissionDate;

    @FindBy(id = "file_submissions-search-add")
    public WebElement addButton;

    @FindBy(xpath = "//div[@id='file_submissions-search-search_term_pills']/span/a")
    public WebElement badgeClosefilterCriteria;

    @FindBy(xpath = "//button[@id='file_submissions-search-search']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[@id='file_submissions-search-reset']")
    public WebElement resetButton;

    @FindBy(xpath = "//table[contains(@id,'DataTables_Table')]//tbody/tr")
    public List<WebElement> searchResultTable;

    @FindBy(xpath = "//div[contains(@id,'column_order_hidden')]")
    public WebElement hideColumnSpace;


    public void fillInTheFileSubmissionDate(String date) {
        try {
            Wait.forElementToBeClickable(driver, getFileSubmissionDate);
            String actualDate = getFileSubmissionDate.getAttribute("data-shinyjs-resettable-value");
            if (date.equals("today") && !actualDate.isEmpty()) {
                Debugger.println("today's date " + getFileSubmissionDate.getAttribute("data-shinyjs-resettable-value"));
            } else if (date.equalsIgnoreCase("future_date")     ){
                String dateToday = TestUtils.todayInDDMMYYYFormat();
                Debugger.println("today's date in dd/mm/yyyy " + dateToday);
                dateToday = dateToday.replace("/", "-");
                Debugger.println("today's date in dd-mm-yyyy " + dateToday);
                String updatedFutureDate =  TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, 1,0, 0); //Add future day +1
                Debugger.println("future date in dd-mm-yyyy " + updatedFutureDate);
                Actions.clickElement(driver, getFileSubmissionDate);
                Actions.clearInputField(getFileSubmissionDate);
                Wait.seconds(2);
                Actions.fillInValue(getFileSubmissionDate,updatedFutureDate);
            } else{
                Wait.seconds(1);
                Actions.clickElement(driver, getFileSubmissionDate);
                Actions.clearInputField(getFileSubmissionDate);
                Wait.seconds(2);
                Actions.fillInValue(getFileSubmissionDate,date);
            }
        } catch (Exception exp) {
            Debugger.println("Exception filling date:" + exp);
            SeleniumLib.takeAScreenShot("UnableToFillDate.jpg");
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

    public List<String> getValuesOfAColumnField(String headerName) {
        Wait.seconds(3);
        try {
            List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table')]/thead//th"));
            //Retrieve the column headers
            List<String> headers = new ArrayList<>();
            for (WebElement elementHeader : allHeaders) {
                String header = elementHeader.getText();
                headers.add(header);
            }
            Debugger.println("All headers" + headers);
            int headerIndex = headers.indexOf(headerName) + 1;
            Debugger.println("Header name is " + headerName);
            Debugger.println("Header index is " + headerIndex);
            String columnValueLocator = "//table[contains(@id,'DataTables_Table')]//tbody/tr/td[" + headerIndex + "]";
            // Retrieve the values
            List<WebElement> allResultRows = driver.findElements(By.xpath(columnValueLocator));
            List<String> allColumnData = new ArrayList<>();
            for (WebElement columnValue : allResultRows) {
                String data = columnValue.getText();
                Debugger.println("Data is " + data);
                allColumnData.add(data);
            }
            return allColumnData;
        } catch (Exception exp) {
            Debugger.println("Exception from retrieving data." + exp);
            SeleniumLib.takeAScreenShot("UnableToRetrieveAllColumnData.jpg");
            return null;
        }
    }

    public List<String> getAllHeadersInSearchResultTable() {
        Wait.seconds(3);
        try {
            List<WebElement> allHeaders = driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table')]/thead//th"));
            //Retrieve the column headers
            List<String> headers = new ArrayList<>();
            for (WebElement elementHeader : allHeaders) {
                String header = elementHeader.getText();
                headers.add(header);
            }
            Debugger.println("All headers" + headers);
            return headers;
        } catch (Exception exp) {
            Debugger.println("Exception from retrieving data." + exp);
            SeleniumLib.takeAScreenShot("UnableToRetrieiveAllHeaders.jpg");
            return null;
        }
    }


    public boolean verifyThePusIconAtTheStartOfEachRowAndClickToExpand() {
        try {
            String loc = "//table[contains(@id,'DataTables_Table')]//tbody/tr[1]/td[@style='display: none;']";
            List<WebElement> allRows = driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table')]//tbody/tr"));
            String allHiddenCol = "//table[contains(@id,'DataTables_Table')]//tbody/tr/td[@style='display: none;']";
            List<WebElement> allHiddenColEle = driver.findElements(By.xpath(allHiddenCol));

            if (allHiddenColEle.size() > 0) {
                for (int i = 0; i < allRows.size(); i++) {
                    List<WebElement> allHiddenColumns = driver.findElements(By.xpath("//table[contains(@id,'DataTables_Table')]//tbody/tr[" + i + "]/td[@style='display: none;']"));
//                    Debugger.println("Columns hidden :" + allHiddenColumns.size());
                    String expandLoc = "//table[contains(@id,'DataTables_Table')]//tbody/tr[" + (i + 1) + "]/td[1]";
                    Actions.clickElement(driver, driver.findElement(By.xpath(expandLoc)));
                    Wait.seconds(2);
                    List<WebElement> allUnhiddenColumns = driver.findElements(By.xpath("//tbody/tr[position()= " + (i + 2) + "and @class='child']"));
                    for (WebElement col : allUnhiddenColumns) {
                        String value = col.getText();
                        // Checking if no col value is null
                        Assert.assertNotNull(value);
//                        Debugger.println("Unhidden col values " + value);
                    }
                }
                return true;
            } else {
                Debugger.println("No column is hidden");
                SeleniumLib.takeAScreenShot("noColumnIsHidden.jpg");
                return false;
            }
        } catch (Exception exp) {
            Debugger.println("Exception due to ExpandCompactLocator element." + exp);
            SeleniumLib.takeAScreenShot("noExpandCompactLocatorExp.jpg");
            return false;
        }
    }
    @FindBy(xpath = "//button[contains(.,'Show all')]")
    public WebElement showAllButtonOnModalContentPage;
    @FindBy(xpath = "//button[contains(.,'Hide all')]")
    public WebElement hideAllButtonOnModalContentPage;
    public boolean theUserClicksHideAllOrShowAllButtonOnTheModalContentPage(String buttonOnModalContentPage) {
        try {
            if (showAllButtonOnModalContentPage.isDisplayed()) {
                if (showAllButtonOnModalContentPage.getText().contains(buttonOnModalContentPage)) {
                    Actions.clickElement(driver, showAllButtonOnModalContentPage);
                    Debugger.println("Show All button clicked");
                    Wait.seconds(5);
                    return true;
                } else {
                    Actions.clickElement(driver, hideAllButtonOnModalContentPage);
                    Debugger.println("Hide All button clicked");
                    Wait.seconds(5);
                    return true;
                }
            }
            SeleniumLib.takeAScreenShot("buttonOnModalContentPage.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("MiportalfileSubmissionPage : userClicksButonOnModalContentPage" + exp);
            SeleniumLib.takeAScreenShot("buttonOnModalContentPage.jpg");
            return false;
        }
    }
    @FindBy(xpath = "//button[contains(@data-id,'file_submissions-search')]")
    public List<WebElement> searchFieldsForFileSubmission;
    public boolean verifyThePresenceOfSelectedOption(String selectedOption) {
        try {
            if (searchFieldsForFileSubmission.size() == 0) {
                Debugger.println("There is nothing with search field.");
                SeleniumLib.takeAScreenShot("SearchFieldNotFound.jpg");
                return false;
            }
            for (int i = 0; i < searchFieldsForFileSubmission.size(); i++) {
                if (searchFieldsForFileSubmission.get(i).getText().equalsIgnoreCase(selectedOption)) {
                    return true;
                }
            }
            Debugger.println("Not selected any options for " + selectedOption);
            SeleniumLib.takeAScreenShot("SelectedOptionNotFound.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePresenceOfSelectedOption," + exp);
            SeleniumLib.takeAScreenShot("SelectedOptionNotFound.jpg");
            return false;
        }
    }

    public boolean addColumnHeadersToHideSection(String fieldColumn) {
        try {
            String culumn_value = "//div[text()='" + fieldColumn + "']";
            WebElement columnToHide = driver.findElement(By.xpath(culumn_value));
            org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
            if (!columnToHide.isDisplayed()) {
                Debugger.println("teh column is not avilable");
                SeleniumLib.takeAScreenShot("selectedColumn.jpg");
                return false;
            }
            act.dragAndDrop(columnToHide, hideColumnSpace).build().perform();
            return true;
        } catch (Exception exp) {
            Debugger.println("MiPortalfileSubmissionPage :addColumnHeadersToHideSection :" + exp);
            SeleniumLib.takeAScreenShot("selectedColumn.jpg");
            return false;
        }
    }
}