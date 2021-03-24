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


public class MiPickListsPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//input[@id='picklists-search-value']")
    public WebElement pickListsSearchInput;

    @FindBy(xpath = "//select[@id='picklists-search-value']")
    public WebElement pickListsSearchValue;

    @FindBy(xpath = "//select[@id='picklists-search-operator']")
    public WebElement pickListsSearchOperator;

    @FindBy(xpath = "//button[@data-id='picklists-search-col']")
    public WebElement pickListsSearchColumnDropDownBtn;

    @FindBy(xpath = "//select[@id='picklists-search-col']")
    public WebElement pickListsSearchColumn;

    By pickListsTableHead = By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th");
    String pickListsTableRows = "//div[contains(@class,'scrollHeadInner')]/table/thead/tr";

    public MiPickListsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    public boolean selectPickListsDropDownSearchColumn(String value) {
        try {
            if(!seleniumLib.selectFromListByText(pickListsSearchColumn,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(pickListsSearchColumn,value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPickLists:selectDropDownSearchColumn: "+ exp);
            SeleniumLib.takeAScreenShot("pickListsSearchColumn.jpg");
            return false;
        }
    }
    public boolean selectPickListsDropDownSearchOperator(String value) {
        try {
            if(!seleniumLib.selectFromListByText(pickListsSearchOperator,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(pickListsSearchOperator,value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPickLists:selectDropDownSearchOperator: "+ exp);
            SeleniumLib.takeAScreenShot("pickListsSearchOperator.jpg");
            return false;
        }
    }
    public boolean selectPickListsDropDownSearchValue(String value) {
        try {
            if(!seleniumLib.selectFromListByText(pickListsSearchValue,value)){
                Wait.seconds(10);
                return seleniumLib.selectFromListByText(pickListsSearchValue,value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPickLists:selectDropDownSearchValue: "+ exp);
            SeleniumLib.takeAScreenShot("pickListsSearchValue.jpg");
            return false;
        }
    }
    public boolean enterPickListsTextSearchValue(String value) {
        try {

            Wait.seconds(2);
            seleniumLib.sendValue(pickListsSearchInput,value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:pickListsSearchInput: "+ exp);
            SeleniumLib.takeAScreenShot("pickListsSearchInput.jpg");
            return false;
        }
    }

    public boolean verifyColumnHeaderInPickListsSearchResultTable(List <List<String>> expColumns){
        Wait.seconds(3);
        try {
            int colIndex = -1;
            List<WebElement> colHeads = driver.findElements(pickListsTableHead);
            for(int i=0; i<expColumns.size(); i++){
                colIndex = seleniumLib.getColumnIndex(colHeads,expColumns.get(i).get(0));
                if(colIndex == -1) {
                    Debugger.println("Specified column " + expColumns.get(i).get(0) + " not present in the FileSubmission Search Result Table.");
                    SeleniumLib.takeAScreenShot("fileSubmissionTable.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifyColumnHeaderInFileSubmissionSearchResultTable." + exp);
            SeleniumLib.takeAScreenShot("FileSubmissionColumnVerification.jpg");
            return false;
        }
    }
    public boolean verifyColumnValueInPickListsSearchResultTable(String columnName,String expValue) {
        Wait.seconds(3);
        try {
            int noOfFilteredRows = seleniumLib.getNoOfRows(pickListsTableRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No search result found in Pick Lists Search Result Table");
                SeleniumLib.takeAScreenShot("pickListTable.jpg");
                return false;
            }
            List<WebElement> colHeads = driver.findElements(pickListsTableHead);
            int colIndex = seleniumLib.getColumnIndex(colHeads,columnName);
            if(colIndex == -1){
                Debugger.println("Specified column "+columnName+" not present in the PickLists Search Result Table.");
                SeleniumLib.takeAScreenShot("pickListsTable.jpg");
                return false;
            }
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for(int i=0; i<noOfFilteredRows;i++){
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(pickListsTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if(expValue.equalsIgnoreCase("non-empty-data")){
                    if(cellValue.isEmpty()){
                        Debugger.println("Column:" + columnName + " value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("pickListsTable.jpg");
                        return false;
                    }
                }else {
                    if (!cellValue.contains(expValue)) {
                        Debugger.println("Column:" + columnName + " value, Expected:" + expValue + ",Actual:" + cellValue);
                        SeleniumLib.takeAScreenShot("pickListsTable.jpg");
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyColumnValueInPickListsSearchResultTable:" + exp);
            SeleniumLib.takeAScreenShot("PickListsResultException.jpg");
            return false;
        }
    }
    public boolean verifyPicklistsDropDownSearchOperator(String value) {
        Wait.seconds(3); // To load the dropdown values
        try {
            Wait.seconds(3);
            if (!seleniumLib.selectFromListByText(pickListsSearchOperator, value)) {
                Wait.seconds(5);
                if (!seleniumLib.selectFromListByText(pickListsSearchOperator, value)) {
                    By optionPath = By.xpath("//ul//li/a/span[contains(text(),'" + value + "')]");
                    seleniumLib.clickOnElement(optionPath);
                }
            }
            return true;
        } catch (StaleElementReferenceException exp) {
            By searchOperator = By.xpath("//select[@id='picklists-search-operator']");
            return seleniumLib.optionFromListByText(searchOperator, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPicklists:selectDropDownSearchOperator: " + exp);
            SeleniumLib.takeAScreenShot("PicklsistSearchOperator.jpg");
            return false;
        }
    }

    public List<String> searchColumnDropDownMenu() {
        List<String> allOptions = new ArrayList<>();
        try {
            Wait.seconds(3);
            if (!Wait.isElementDisplayed(driver, pickListsSearchColumnDropDownBtn, 10)) {
                Debugger.println("Picklists search column not displayed");
                SeleniumLib.takeAScreenShot("DropDownValues.jpg");
                return null;
            }
            Select searchColumnSelect = new Select(pickListsSearchColumn);
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