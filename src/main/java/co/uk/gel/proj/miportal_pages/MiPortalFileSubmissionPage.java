package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
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
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @FindBy(xpath = "//button[@data-id='file_submissions-search-operator']")
    public WebElement fileSubmissionSearchOperatorDropDownButton;

    @FindBy(xpath = "//input[contains(@data-shinyjs-resettable-id,'search-value')]")
    public WebElement getFileSubmissionDate;

    @FindBy(id = "file_submissions-search-add")
    public WebElement addButton;

    @FindBy(xpath = "//button[@id='file_submissions-search-search']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[@id='file_submissions-search-reset']")
    public WebElement resetButton;

    @FindBy(xpath = "//div[contains(@id,'column_order_hidden')]")
    public WebElement hideColumnSpace;

    @FindBy(xpath = "//div[contains(@id,'column_order_visible')]")
    public WebElement showColumnSpace;

    By fileSubmissionTableHead = By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th");
    String fileSubmissionTableRows = "//div[contains(@class,'scrollBody')]/table/tbody/tr";
    @FindBy(xpath = "//select[contains(@id,'-search-value')]")
    public WebElement fileSubmissionSearchValue;

    @FindBy(xpath = "//select[@id='file_submissions-search-operator']")
    public WebElement fileSubmissionSearchOperator;

    @FindBy(xpath = "//select[@id='file_submissions-search-col']")
    public WebElement fileSubmissionSearchColumn;

    @FindBy(xpath = "//*[contains(@id,'-display-table')]//h3[contains(text(),'Search Results')]")
    public WebElement searchResults;

    @FindBy(xpath = "//button[contains(.,'Show all')]")
    public WebElement showAllButtonOnModalContentPage;
    @FindBy(xpath = "//button[contains(.,'Hide all')]")
    public WebElement hideAllButtonOnModalContentPage;

    @FindBy(xpath = "//button[contains(@data-id,'file_submissions-search')]")
    public List<WebElement> searchFieldsForFileSubmission;

    By tablePath = By.xpath("//table[contains(@id,'DataTables_Table')]//tbody/tr");
    String allHiddenCol = "//table[contains(@id,'DataTables_Table')]//tbody/tr/td[@style='display: none;']";

    public boolean fillInTheFileSubmissionDate(String date) {
        try {
            if (!Wait.isElementDisplayed(driver, getFileSubmissionDate, 30)) {
                return false;
            }
            String actualDate = getFileSubmissionDate.getAttribute("data-shinyjs-resettable-value");
            if (date.equals("today") && !actualDate.isEmpty()) {
                //Today by default selected - Nothing to do
            } else if (date.equalsIgnoreCase("future_date")) {
                String dateToday = TestUtils.todayInDDMMYYYFormat();
                dateToday = dateToday.replace("/", "-");
                String updatedFutureDate = TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, 1, 0, 0); //Add future day +1
                try {
                    Actions.clickElement(driver, getFileSubmissionDate);
                } catch (Exception exp1) {
                    Debugger.println("Exception in clicking on Date field...trying again..." + exp1);
                    seleniumLib.clickOnWebElement(getFileSubmissionDate);
                }
                Actions.clearInputField(getFileSubmissionDate);
                Wait.seconds(2);
                Actions.fillInValue(getFileSubmissionDate, updatedFutureDate);
                return true;
            } else if (date.equalsIgnoreCase("past_date")) {
                String dateToday = TestUtils.todayInDDMMYYYFormat();
                dateToday = dateToday.replace("/", "-");
                String updatedFutureDate = TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, -5, 0, 0); //Add future day +1
                try {
                    Actions.clickElement(driver, getFileSubmissionDate);
                } catch (Exception exp1) {
                    Debugger.println("Exception in clicking on Date field...trying again..." + exp1);
                    seleniumLib.clickOnWebElement(getFileSubmissionDate);
                }
                Actions.clearInputField(getFileSubmissionDate);
                Wait.seconds(2);
                Actions.fillInValue(getFileSubmissionDate, updatedFutureDate);
                return true;
            } else {
                Wait.seconds(2);
                try {
                    Actions.clickElement(driver, getFileSubmissionDate);
                } catch (Exception exp1) {
                    Debugger.println("Exception in clicking on Date field...trying again..." + exp1);
                    seleniumLib.clickOnWebElement(getFileSubmissionDate);
                }
                Actions.clearInputField(getFileSubmissionDate);
                Wait.seconds(2);
                Actions.fillInValue(getFileSubmissionDate, date);
            }
            return true;
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean fillInPastDateInTheFileSubmissionDate(String noOfDays) {
        String pastDate = "";
        try {
            Wait.seconds(5);
            if (!Wait.isElementDisplayed(driver, getFileSubmissionDate, 30)) {
                return false;
            }
            int daysBefore = -1 * Integer.parseInt(noOfDays);
            String dateToday = TestUtils.todayInDDMMYYYFormat();
            dateToday = dateToday.replace("/", "-");
            pastDate = TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, daysBefore, 0, 0); //Add future day +1
            Actions.clickElement(driver, getFileSubmissionDate);
            Wait.seconds(2);
            Actions.clearInputField(getFileSubmissionDate);
            Wait.seconds(2);
            Actions.fillInValue(getFileSubmissionDate, pastDate);
            return true;

        } catch (Exception exp) {
            try {
                seleniumLib.sendValue(getFileSubmissionDate, pastDate);
                return true;
            } catch (Exception exp1) {
                return false;
            }
        }
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

    public boolean verifyTheElementsOnFileSubmissionPage() {

        if (!Wait.isElementDisplayed(driver, mainSearchContainer, 20)) {
            return false;
        }
        List<WebElement> expectedElements = new ArrayList<WebElement>();
        expectedElements.add(searchBoxHeader);
        expectedElements.add(fileSubmissionSearchDropDownButton);
        expectedElements.add(fileSubmissionSearchOperatorDropDownButton);
        expectedElements.add(getFileSubmissionDate);
        expectedElements.add(addButton);
        expectedElements.add(searchButton);
        expectedElements.add(resetButton);

        for (int i = 0; i < expectedElements.size(); i++) {
            if (!expectedElements.get(i).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public boolean verifyTheTotalNumberOfSearchResult(int size) {
        try {
            Wait.seconds(2);
            int noOfFilteredRows = seleniumLib.getNoOfRows(fileSubmissionTableRows);
            if (noOfFilteredRows != size) {
                Debugger.println("Expected rows in Table:" + size + ", But Actual:" + noOfFilteredRows);
                SeleniumLib.takeAScreenShot("fileSubmissionTable.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("No search result");
            SeleniumLib.takeAScreenShot("TotalSearchResultNum.jpg");
            return false;
        }
    }

    public boolean verifyColumnValueInFileSubmissionSearchResultTable(String columnName, String expValue) {
        Wait.seconds(5);
        try {
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(fileSubmissionTableRows);
            if (noOfFilteredRows == 0) {
                return false;
            }
            List<WebElement> colHeads = driver.findElements(fileSubmissionTableHead);
            int colIndex = seleniumLib.getColumnIndex(colHeads, columnName);
            if (colIndex == -1) {
                return false;
            }
            //Debugger.println("Index of Column:"+columnName+" is "+colIndex+", No of Rows:"+noOfFilteredRows);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for (int i = 0; i < noOfFilteredRows; i++) {
               // Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(fileSubmissionTableRows + "[" + (i + 1) + "]/td[" + colIndex + "]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")) {
                    if (cellValue.isEmpty()) {
                        Debugger.println("Column:" + columnName + " value supposed to be non-empty, but Actual is empty");
                        return false;
                    }
                } else {
                    if (!cellValue.contains(expValue)) {
                        Debugger.println("Column:" + columnName + " value, Expected:" + expValue + ",Actual:" + cellValue + " ,at row no: " + (i + 1));
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from retrieving data." + exp);
            return false;
        }
    }

    public List<String> getValuesOfAColumnField(String columnName) {
        Wait.seconds(3);
        List<String> allColumnData = new ArrayList<>();
        try {
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                Debugger.println("Search results are not displayed");
                return null;
            }
            List<WebElement> colHeads = driver.findElements(fileSubmissionTableHead);
            int colIndex = seleniumLib.getColumnIndex(colHeads, columnName);
            if (colIndex == -1) {
                Debugger.println("Specified column " + columnName + " not present in the FileSubmission Search Result Table.");
                return allColumnData;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(fileSubmissionTableRows);
            if (noOfFilteredRows == 0) {
                Debugger.println("No search result found in File Submission Search Result Table");
                return allColumnData;
            }
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for (int i = 0; i < noOfFilteredRows; i++) {
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(fileSubmissionTableRows + "[" + (i + 1) + "]/td[" + colIndex + "]");
                cellValue = seleniumLib.getText(cellPath);
                allColumnData.add(cellValue);
            }
            return allColumnData;
        } catch (Exception exp) {
            Debugger.println("Exception from retrieving data." + exp);
            return allColumnData;
        }
    }

    public boolean verifyColumnHeaderInFileSubmissionSearchResultTable(List<List<String>> expColumns) {
        Wait.seconds(3);
        try {
            int colIndex = -1;
            List<WebElement> colHeads = driver.findElements(fileSubmissionTableHead);
            for (int i = 1; i < expColumns.size(); i++) {
                colIndex = seleniumLib.getColumnIndex(colHeads, expColumns.get(i).get(0));
                if (colIndex == -1) {
                    Debugger.println("Specified column " + expColumns.get(i).get(0) + " not present in the FileSubmission Search Result Table.");
                    return false;
                }
                seleniumLib.sleepInSeconds(2);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifyColumnHeaderInFileSubmissionSearchResultTable." + exp);
            return false;
        }
    }

    public List<String> getAllHeadersInSearchResultTable() {
        Wait.seconds(3);
        try {
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                Debugger.println("Search results are not displayed");
                SeleniumLib.takeAScreenShot("UnableToRetrieveAllHeaders.jpg");
                return null;
            }
            List<WebElement> allHeaders = driver.findElements(fileSubmissionTableHead);
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
            SeleniumLib.takeAScreenShot("UnableToRetrieveAllHeaders.jpg");
            return null;
        }
    }


    public String verifyThePusIconAtTheStartOfEachRowAndClickToExpand() {
        try {
            if(!seleniumLib.isElementPresent(tablePath)){//30 seconds max
                if(!seleniumLib.isElementPresent(tablePath)){//another 30 seconds
                    return "Result table not loaded as expected.";
                }
            }
            List<WebElement> allRows = driver.findElements(tablePath);
            List<WebElement> allHiddenColEle = driver.findElements(By.xpath(allHiddenCol));
            if(allHiddenColEle.size() == 0){
                Debugger.println("No column is hidden");
                return "Success";
            }
            int count = 1;
            for (int i = 0; i < allRows.size(); i++) {
                String expandLoc = "//table[contains(@id,'DataTables_Table')]//tbody/tr[" + (i + 1) + "]/td[1]";
                 seleniumLib.clickOnElement(By.xpath(expandLoc));
                 Wait.seconds(2);
                 List<WebElement> allUnhiddenColumns = driver.findElements(By.xpath("//tbody/tr[position()= " + (i + 2) + "and @class='child']"));
                 for (WebElement col : allUnhiddenColumns) {
                    String value = col.getText();
                    Assert.assertNotNull(value);
                 }
                 count++;
                 if (count > 5) {
                     break;
                 }
            }
            return "Success";
        } catch (Exception exp) {
            Debugger.println("Exception due to ExpandCompactLocator element." + exp);
            return "Exception due to ExpandCompactLocator element." + exp;
        }
    }

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
            List<WebElement> columnToHides = driver.findElements(By.xpath(culumn_value));

            if (columnToHides.size() == 0) {
                Debugger.println("The column: " + fieldColumn + " is not available");
                //SeleniumLib.takeAScreenShot("selectedColumn.jpg");
                return false;
            }
            for(WebElement columnToHide:columnToHides) {
                if(columnToHide.isDisplayed()) {
                    org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
                    act.dragAndDrop(columnToHide, hideColumnSpace).build().perform();
                    break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("MiPortalFileSubmissionPage :addColumnHeadersToHideSection :" + exp);
            //SeleniumLib.takeAScreenShot("selectedColumn.jpg");
            return false;
        }
    }
    public boolean addColumnHeadersToShowSection(String fieldColumn) {
        try {
            String culumn_value = "//div[text()='" + fieldColumn + "']";
            List<WebElement> columnToHides = driver.findElements(By.xpath(culumn_value));

            if (columnToHides.size() == 0) {
                Debugger.println("The column: " + fieldColumn + " is not available");
                SeleniumLib.takeAScreenShot("selectedColumn.jpg");
                return false;
            }
            for(WebElement columnToHide:columnToHides) {
                if(columnToHide.isDisplayed()) {
                    org.openqa.selenium.interactions.Actions act = new org.openqa.selenium.interactions.Actions(driver);
                    act.dragAndDrop(columnToHide, showColumnSpace).build().perform();
                    break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("MiPortalFileSubmissionPage :addColumnHeadersToShowSection :" + exp);
            SeleniumLib.takeAScreenShot("selectedColumn.jpg");
            return false;
        }
    }

    public boolean selectDropDownSearchColumn(String value) {
        try {
            if (!seleniumLib.selectFromListByText(fileSubmissionSearchColumn, value)) {
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(fileSubmissionSearchColumn, value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalFileSubmission:selectDropDownSearchColumn: " + exp);
            return false;
        }
    }

    public boolean selectDropDownSearchOperator(String value) {
        try {
            if (!seleniumLib.selectFromListByText(fileSubmissionSearchOperator, value)) {
                Wait.seconds(10);
                return seleniumLib.selectFromListByText(fileSubmissionSearchOperator, value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalFileSubmission:selectDropDownSearchOperator: " + exp);
            return false;
        }
    }

    public boolean selectDropDownSearchValue(String value) {
        try {
            Wait.seconds(5);
            if (!seleniumLib.selectFromListByText(fileSubmissionSearchValue, value)) {
                Wait.seconds(10);
                return seleniumLib.selectFromListByText(fileSubmissionSearchValue, value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalFileSubmission:selectDropDownSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("selectDropDownSearchValue.jpg");
            return false;
        }
    }

    public boolean verifyColumnHeadPresence(String columnName) {
        Wait.seconds(3);
        try {
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                Debugger.println("Search results are not displayed");
                return false;
            }
            List<WebElement> colHeads = driver.findElements(fileSubmissionTableHead);
            int colIndex = seleniumLib.getColumnIndex(colHeads, columnName);
            if (colIndex == -1) {
                Debugger.println("Specified column " + columnName + " not present in the FileSubmission Search Result Table.");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from getAllHeadersInSearchResultTable. " + exp);
            return false;
        }
    }

    public List<String> searchColumnDropDownMenu() {
        List<String> allOptions = new ArrayList<>();
        try {
            Wait.seconds(3);
            if (!Wait.isElementDisplayed(driver, fileSubmissionSearchDropDownButton, 10)) {
                Debugger.println("File submission search column not displayed");
                SeleniumLib.takeAScreenShot("DropDownValues.jpg");
                return null;
            }
            Select searchColumnSelect = new Select(fileSubmissionSearchColumn);
            List<WebElement> allOptionsElement = searchColumnSelect.getOptions();
            for (WebElement optionElement : allOptionsElement) {
                allOptions.add(optionElement.getText());
            }
            return allOptions;
        } catch (Exception exp) {
            Debugger.println("Exception from checking the search column drop down values: " + exp);
            SeleniumLib.takeAScreenShot("DropDownValues.jpg");
            return null;
        }
    }
}