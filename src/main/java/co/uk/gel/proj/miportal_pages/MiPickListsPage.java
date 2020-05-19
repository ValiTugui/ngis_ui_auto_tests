package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//select[@id='picklists-search-col']")
    public WebElement pickListsSearchColumn;

    By pickListsTableHead = By.xpath("//div[@id='picklists-display-table_contents']//table[contains(@id,'DataTables_Table')]/thead/tr/th");
    String pickListsTableRows = "//div[@id='picklists-display-table_contents']//table[contains(@id,'DataTables_Table')]/tbody/tr";

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
                Wait.seconds(5);
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
            for(int i=0; i<expColumns.size(); i++){
                colIndex = seleniumLib.getColumnIndex(pickListsTableHead,expColumns.get(i).get(0));
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
            int colIndex = seleniumLib.getColumnIndex(pickListsTableHead,columnName);
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
}