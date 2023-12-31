package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static co.uk.gel.proj.util.ExcelDataRead.readAllData;
import static co.uk.gel.proj.util.ExcelDataRead.retrieveData;

public class MiOrderTrackingPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiOrderTrackingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//input[@id='order_tracking-search-value']")
    public WebElement orderTrackSearchInput;

    @FindBy(xpath = "//select[@id='order_tracking-search-value']")
    public WebElement orderTrackSearchValue;

    @FindBy(xpath = "//select[@id='order_tracking-search-operator']")
    public WebElement orderTrackSearchOperator;

    @FindBy(xpath = "//select[@id='order_tracking-search-col']")
    public WebElement orderTrackSearchColumn;

    @FindBy(xpath = "//button[@data-id='order_tracking-search-col']")
    public WebElement orderTrackSearchColumnDropDown;

    @FindBy(xpath = "//*[contains(@id,'-display-table')]//h3[contains(text(),'Search Results')]")
    public WebElement searchResults;

    By orderTrackingTableHead = By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th");
    String orderTrackingTableRows = "//div[contains(@class,'scrollHeadInner')]/table/thead/tr";

    @FindBy(xpath = "//select[@id='order_tracking-search-value']//option")
    List<WebElement> optionsList;

    public boolean selectOrderTrackingDropDownSearchColumn(String value) {
        try {
            if (!seleniumLib.selectFromListByText(orderTrackSearchColumn, value)) {
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(orderTrackSearchColumn, value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:selectDropDownSearchColumn: " + exp);
            SeleniumLib.takeAScreenShot("orderTrackSearchColumn.jpg");
            return false;
        }
    }

    public boolean selectOrderTrackingDropDownSearchOperator(String value) {
        try {
            Wait.seconds(3);
            if (!seleniumLib.selectFromListByText(orderTrackSearchOperator, value)) {
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(orderTrackSearchOperator, value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:selectDropDownSearchOperator: " + exp);
            SeleniumLib.takeAScreenShot("orderTrackSearchOperator.jpg");
            return false;
        }
    }

    public boolean selectOrderTrackingDropDownSearchValue(String value) {
        Wait.seconds(3); // To load the dropdown values
        try {
            Wait.seconds(3);
            if (!seleniumLib.selectFromListByText(orderTrackSearchValue, value)) {
                Wait.seconds(5);
                if (!seleniumLib.selectFromListByText(orderTrackSearchValue, value)) {
                    By optionPath = By.xpath("//ul//li/a/span[contains(text(),'" + value + "')]");
                    seleniumLib.clickOnElement(optionPath);
                }
            }
            return true;
        } catch (StaleElementReferenceException exp) {
            By searchValue = By.xpath("//select[@id='order_tracking-search-value']");
            return seleniumLib.optionFromListByText(searchValue, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:selectDropDownSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("orderTrackSearchValue.jpg");
            return false;
        }


    }

    public boolean enterOrderTrackingTextSearchValue(String value) {
        try {
            if (!Wait.isElementDisplayed(driver, orderTrackSearchInput, 30)) {
                Debugger.println("orderTrackSearchInput not loaded...");
                SeleniumLib.takeAScreenShot("orderTrackSearchInput.jpg");
                return false;
            }
            seleniumLib.sendValue(orderTrackSearchInput, value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:enterOrderTrackingTextSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("enterOrderTrackingTextSearchValue.jpg");
            return false;
        }
    }

    public boolean verifyColumnValueInOrderTrackingSearchResultTable(String columnName, String expValue) {
        Wait.seconds(5);// To load the table elements
        try {
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                Debugger.println("Search results are not displayed");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(orderTrackingTableRows);
            if (noOfFilteredRows == 0) {
                Debugger.println("No search result found in Order Tracking Search Result Table");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }

            List<WebElement> colHeads = driver.findElements(orderTrackingTableHead);
            int colIndex = seleniumLib.getColumnIndex(colHeads, columnName);
            if (colIndex == -1) {
                Debugger.println("Specified column " + columnName + " not present in the Order Tracking Search Result Table.");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            Wait.seconds(3);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for (int i = 0; i < noOfFilteredRows; i++) {
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(orderTrackingTableRows + "[" + (i + 1) + "]/td[" + colIndex + "]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")) {
                    if (cellValue.isEmpty()) {
                        Debugger.println("Column:" + columnName + " value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                        return false;
                    }
                } else {
                    if (!cellValue.contains(expValue)) {
                        Debugger.println("Column:" + columnName + " value, Expected:" + expValue + ",Actual:" + cellValue);
                        SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyColumnValueInGlhSamplesSearchResultTable:" + exp);
            SeleniumLib.takeAScreenShot("GLHSamplesResultException.jpg");
            return false;
        }
    }

    public boolean verifyOrderTrackingResultColumnValuesDifference(String columnName1, String columnName2) {
        Wait.seconds(3);
        try {
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                Debugger.println("Search results are not displayed");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(orderTrackingTableRows);
            if (noOfFilteredRows == 0) {
                Debugger.println("No search result found in Order Tracking Search Result Table");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }

            List<WebElement> colHeads = driver.findElements(orderTrackingTableHead);
            int colIndex1 = seleniumLib.getColumnIndex(colHeads, columnName1);
            if (colIndex1 == -1) {
                Debugger.println("Specified column " + columnName1 + " not present in the Order Tracking Search Result Table.");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int colIndex2 = seleniumLib.getColumnIndex(colHeads, columnName2);
            if (colIndex2 == -1) {
                Debugger.println("Specified column " + columnName2 + " not present in the Order Tracking Search Result Table.");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue1 = "", cellValue2 = "";
            for (int i = 0; i < noOfFilteredRows; i++) {
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(orderTrackingTableRows + "[" + (i + 1) + "]/td[" + colIndex1 + "]");
                cellValue1 = seleniumLib.getText(cellPath);
                cellPath = By.xpath(orderTrackingTableRows + "[" + (i + 1) + "]/td[" + colIndex2 + "]");
                cellValue2 = seleniumLib.getText(cellPath);
                if (!cellValue1.equalsIgnoreCase(cellValue2)) {
                    Debugger.println("Different value expected for columns :" + columnName1 + " and " + columnName2 + ", but found same.");
                    SeleniumLib.takeAScreenShot("orderTrackingTableDifference.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyOrderTrackingResultColumnValuesDifference:" + exp);
            SeleniumLib.takeAScreenShot("GLHSamplesResultException.jpg");
            return false;
        }
    }

    public boolean verifyColumnDropdownInOrderTrackingSearchOptions(String glhName, String fileName) {
        try {
            List<String> allOptions = new ArrayList<>();
            if (!seleniumLib.isElementPresent(orderTrackSearchColumn)) {
                Debugger.println("Order tracking search Options are not displayed");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int actualNumOfOptions = optionsList.size();
            for (WebElement optionElement : optionsList) {
                allOptions.add(optionElement.getText());
            }
            readAllData(fileName);
            for (int i = 0; i < allOptions.size(); i++) {
                if (!allOptions.get(i).equalsIgnoreCase(retrieveData(glhName).get(i))) {
                    Debugger.println("The Actual options names are: " + allOptions + ",But Expected " + retrieveData(glhName));
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyColumnDropdownInOrderTrackingSearchOptions:" + exp);
            SeleniumLib.takeAScreenShot("OrderTrackingOptionsNotFound.jpg");
            return false;
        }
    }

    public boolean verifyOrderTrackingDropDownSearchOperator (String value) {
        Wait.seconds(3); // To load the dropdown values
        try {
            Wait.seconds(3);
            if (!seleniumLib.selectFromListByText(orderTrackSearchOperator, value)) {
                Wait.seconds(5);
                if (!seleniumLib.selectFromListByText(orderTrackSearchOperator, value)) {
                    By optionPath = By.xpath("//ul//li/a/span[contains(text(),'" + value + "')]");
                    seleniumLib.clickOnElement(optionPath);
                }
            }
            return true;
        } catch (StaleElementReferenceException exp) {
            By searchOperator = By.xpath("//select[@id='order_tracking-search-operator']");
            return seleniumLib.optionFromListByText(searchOperator, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:selectDropDownSearchOperator: " + exp);
            SeleniumLib.takeAScreenShot("orderTrackSearchOperator.jpg");
            return false;
        }


    }

    public List<String> searchColumnDropDownMenu() {
        List<String> allOptions = new ArrayList<>();
        try {
            Wait.seconds(3);
            if (!Wait.isElementDisplayed(driver, orderTrackSearchColumnDropDown, 10)) {
                Debugger.println("Order Tracking search column not displayed");
                SeleniumLib.takeAScreenShot("DropDownValues.jpg");
                return null;
            }
            Select searchColumnSelect = new Select(orderTrackSearchColumn);
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