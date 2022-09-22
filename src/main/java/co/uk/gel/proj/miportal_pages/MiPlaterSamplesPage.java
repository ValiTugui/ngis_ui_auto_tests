package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;


public class MiPlaterSamplesPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//input[@id='plater_samples-search-value']")
    public WebElement platerSampleSearchInput;

    @FindBy(xpath = "//select[@id='plater_samples-search-value']")
    public WebElement platerSampleSearchValue;

    @FindBy(xpath = "//select[@id='plater_samples-search-operator']")
    public WebElement platerSampleSearchOperator;

    @FindBy(xpath = "//select[@id='plater_samples-search-col']")
    public WebElement platerSampleSearchColumn;

    @FindBy(xpath = "//button[@data-id='plater_samples-search-col']")
    public WebElement platerSampleSearchColumnDropDownBtn;

    @FindBy(xpath = "//input[@data-shinyjs-resettable-id='plater_samples-search-value']")
    public WebElement getPlaterSamplesDate;

    @FindBy(xpath = "//*[contains(@id,'-display-table')]//h3[contains(text(),'Search Results')]")
    public WebElement searchResults;

    @FindBy(xpath = "//select[@id='plater_samples-search-col']")
    public List<WebElement> searchColumnDropdownValues;

    By platerSamplesTableHead = By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th");
    String platerSamplesTableRows = "//div[contains(@class,'scrollHeadInner')]/table/thead/tr";

    public MiPlaterSamplesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    public boolean fillInThePlaterSamplesDate(String date) {
        try {
            Wait.forElementToBeClickable(driver, getPlaterSamplesDate);
            String actualDate = getPlaterSamplesDate.getAttribute("data-shinyjs-resettable-value");
            if (date.equals("today") && !actualDate.isEmpty()) {
                Debugger.println("today's date " + getPlaterSamplesDate.getAttribute("data-shinyjs-resettable-value"));
            } else if (date.equalsIgnoreCase("future_date")) {
                String dateToday = TestUtils.todayInDDMMYYYFormat();
                Debugger.println("today's date in dd/mm/yyyy " + dateToday);
                dateToday = dateToday.replace("/", "-");
                Debugger.println("today's date in dd-mm-yyyy " + dateToday);
                String updatedFutureDate = TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, 1, 0, 0); //Add future day +1
                Debugger.println("future date in dd-mm-yyyy " + updatedFutureDate);
                Action.clickElement(driver, getPlaterSamplesDate);
                Action.clearInputField(getPlaterSamplesDate);
                Wait.seconds(2);
                Action.fillInValue(getPlaterSamplesDate, updatedFutureDate);
            } else {
                Wait.seconds(1);
                Action.clickElement(driver, getPlaterSamplesDate);
                Action.clearInputField(getPlaterSamplesDate);
                Wait.seconds(2);
                Action.fillInValue(getPlaterSamplesDate, date);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception filling date:" + exp);
            SeleniumLib.takeAScreenShot("UnableToFillDate.jpg");
            return false;
        }
    }

    public boolean fillInPastDateInThePlaterSampleDate(String noOfDays) {
        String pastDate = "";
        try {
            Wait.seconds(5);
            if (!Wait.isElementDisplayed(driver, getPlaterSamplesDate, 30)) {
                Debugger.println("fillInPastDateInThePlaterSampleDate not displayed.");
                SeleniumLib.takeAScreenShot("fillInPastDateInThePlaterSampleDate.jpg");
                return false;
            }
            int daysBefore = -1 * Integer.parseInt(noOfDays);
            String dateToday = TestUtils.todayInDDMMYYYFormat();
            dateToday = dateToday.replace("/", "-");
            pastDate = TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, daysBefore, 0, 0); //Add future day +1
            Action.clickElement(driver, getPlaterSamplesDate);
            Action.clearInputField(getPlaterSamplesDate);
            Wait.seconds(2);
            Action.fillInValue(getPlaterSamplesDate, pastDate);
            return true;

        } catch (Exception exp) {
            try {
                seleniumLib.sendValue(getPlaterSamplesDate, pastDate);
                return true;
            } catch (Exception exp1) {
                Debugger.println("Exception in fillInPastDateInThePlaterSampleDate:" + exp);
                SeleniumLib.takeAScreenShot("fillInPastDateInThePlaterSampleDate.jpg");
                return false;
            }
        }
    }

    public boolean selectPlaterSamplesDropDownSearchColumn(String value) {
        try {
            if (!seleniumLib.selectFromListByText(platerSampleSearchColumn, value)) {
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(platerSampleSearchColumn, value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPlaterSamples:selectDropDownSearchColumn: " + exp);
            SeleniumLib.takeAScreenShot("platerSampleSearchColumn.jpg");
            return false;
        }
    }

    public boolean selectPlaterSamplesDropDownSearchOperator(String value) {
        try {
            if (!seleniumLib.selectFromListByText(platerSampleSearchOperator, value)) {
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(platerSampleSearchOperator, value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPlaterSamples:selectDropDownSearchOperator: " + exp);
            SeleniumLib.takeAScreenShot("platerSampleSearchOperator.jpg");
            return false;
        }
    }

    public boolean selectPlaterSamplesDropDownSearchValue(String value) {
        try {
            if (!seleniumLib.selectFromListByText(platerSampleSearchValue, value)) {
                Wait.seconds(10);
                return seleniumLib.selectFromListByText(platerSampleSearchValue, value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in platerSampleSearchValue:selectDropDownSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("platerSampleSearchValue.jpg");
            return false;
        }
    }

    public boolean verifyColumnValueInPlaterSamplesSearchResultTable(String columnName, String expValue) {
        Wait.seconds(3);
        try {
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                Debugger.println("Search results are not displayed");
                SeleniumLib.takeAScreenShot("platerSamplesTable.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(platerSamplesTableRows);
            if (noOfFilteredRows == 0) {
                Debugger.println("No search result found in PlaterSamples Search Result Table");
                SeleniumLib.takeAScreenShot("platerSamplesTable.jpg");
                return false;
            }
            List<WebElement> colHeads = driver.findElements(platerSamplesTableHead);
            int colIndex = seleniumLib.getColumnIndex(colHeads, columnName);
            if (colIndex == -1) {
                Debugger.println("Specified column " + columnName + " not present in the Plater Samples Search Result Table.");
                SeleniumLib.takeAScreenShot("platerSamplesTable.jpg");
                return false;
            }
            Wait.seconds(3);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for (int i = 0; i < noOfFilteredRows; i++) {
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(platerSamplesTableRows + "[" + (i + 1) + "]/td[" + colIndex + "]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")) {
                    if (cellValue.isEmpty()) {
                        Debugger.println("Column:" + columnName + " value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("platerSamplesTable.jpg");
                        return false;
                    }
                } else {
                    if (!cellValue.contains(expValue)) {
                        Debugger.println("Column:" + columnName + " value, Expected:" + expValue + ",Actual:" + cellValue);
                        SeleniumLib.takeAScreenShot("platerSamplesTable.jpg");
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyColumnValueInPlaterSamplesSearchResultTable:" + exp);
            SeleniumLib.takeAScreenShot("PlaterSamplesResultException.jpg");
            return false;
        }
    }

    public boolean enterPlaterSampleTextSearchValue(String value) {
        try {
            Wait.seconds(2);
            seleniumLib.sendValue(platerSampleSearchInput, value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:enterPlaterSampleTextSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("enterPlaterSampleTextSearchValue.jpg");
            return false;
        }
    }

    public boolean verifyExpectedColumnNameInPlaterSamplesSearchColumnDropdown(String expectedDropdownValue) {
        try {
            List<String> actualDropdownValueList = new ArrayList<>();
            Wait.seconds(5);
            for (WebElement dropDownValues : searchColumnDropdownValues) {
                actualDropdownValueList.add(dropDownValues.getText().trim());
            }
            boolean isPresent = false;
            for (int i = 0; i <= actualDropdownValueList.size(); i++) {
                if (!actualDropdownValueList.get(i).equalsIgnoreCase(expectedDropdownValue)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Expected value :" + expectedDropdownValue + " Not present under section:" + actualDropdownValueList);
                SeleniumLib.takeAScreenShot("ColumnHeaderOrderingShowHide.jpg");
                return isPresent;
            } else {
                Debugger.println("Expected value: " + expectedDropdownValue + " is not present under section:" + actualDropdownValueList);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyExpectedColumnNameInPlaterSamplesSearchColumnDropdown: " + exp);
            SeleniumLib.takeAScreenShot("ExpectedColumnPresent.jpg");
            return false;
        }
    }

    public boolean verifyPlaterSampleDropDownSearchOperator(String value) {
        Wait.seconds(3); // To load the dropdown values
        try {
            Wait.seconds(3);
            if (!seleniumLib.selectFromListByText(platerSampleSearchOperator, value)) {
                Wait.seconds(5);
                if (!seleniumLib.selectFromListByText(platerSampleSearchOperator, value)) {
                    By optionPath = By.xpath("//ul//li/a/span[contains(text(),'" + value + "')]");
                    seleniumLib.clickOnElement(optionPath);
                }
            }
            return true;
        } catch (StaleElementReferenceException exp) {
            By searchOperator = By.xpath("//select[@id='plater_samples-search-operator']");
            return seleniumLib.optionFromListByText(searchOperator, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPlaterSamples:selectDropDownSearchOperator: " + exp);
            SeleniumLib.takeAScreenShot("platerSamplesSearchOperator.jpg");
            return false;
        }
    }

    public List<String> searchColumnDropDownMenu() {
        List<String> allOptions = new ArrayList<>();
        try {
            Wait.seconds(3);
            if (!Wait.isElementDisplayed(driver, platerSampleSearchColumnDropDownBtn, 10)) {
                Debugger.println("Plater Samples search column not displayed");
                SeleniumLib.takeAScreenShot("DropDownValues.jpg");
                return null;
            }
            Select searchColumnSelect = new Select(platerSampleSearchColumn);
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