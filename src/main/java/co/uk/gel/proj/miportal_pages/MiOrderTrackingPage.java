package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
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

    @FindBy(xpath = "//*[contains(@id,'-display-table')]//h3[contains(text(),'Search Results')]")
    public WebElement searchResults;

    By orderTrackingTableHead = By.xpath("//div[@id='order_tracking-display-table_contents']//table[contains(@id,'DataTables_Table')]/thead/tr/th");
    String orderTrackingTableRows = "//div[@id='order_tracking-display-table_contents']//table[contains(@id,'DataTables_Table')]/tbody/tr";

    public boolean selectOrderTrackingDropDownSearchColumn(String value) {
        try {
            if(!seleniumLib.selectFromListByText(orderTrackSearchColumn,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(orderTrackSearchColumn,value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:selectDropDownSearchColumn: "+ exp);
            SeleniumLib.takeAScreenShot("orderTrackSearchColumn.jpg");
            return false;
        }
    }
    public boolean selectOrderTrackingDropDownSearchOperator(String value) {
        try {
            if(!seleniumLib.selectFromListByText(orderTrackSearchOperator,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(orderTrackSearchOperator,value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:selectDropDownSearchOperator: "+ exp);
            SeleniumLib.takeAScreenShot("orderTrackSearchOperator.jpg");
            return false;
        }
    }
    public boolean selectOrderTrackingDropDownSearchValue(String value) {
        try {
            if(!seleniumLib.selectFromListByText(orderTrackSearchValue,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(orderTrackSearchValue,value);
            }
            return true;
        } catch (StaleElementReferenceException exp) {
            By searchValue = By.xpath("//select[@id='order_tracking-search-value']");
            return seleniumLib.optionFromListByText(searchValue,value);
        }catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:selectDropDownSearchValue: "+ exp);
            SeleniumLib.takeAScreenShot("orderTrackSearchValue.jpg");
            return false;
        }


    }
    public boolean enterOrderTrackingTextSearchValue(String value) {
        try {
            if(!Wait.isElementDisplayed(driver,orderTrackSearchInput,30)){
                Debugger.println("orderTrackSearchInput not loaded...");
                SeleniumLib.takeAScreenShot("orderTrackSearchInput.jpg");
                return false;
            }
            seleniumLib.sendValue(orderTrackSearchInput,value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:enterOrderTrackingTextSearchValue: "+ exp);
            SeleniumLib.takeAScreenShot("enterOrderTrackingTextSearchValue.jpg");
            return false;
        }
    }
    public boolean verifyColumnValueInOrderTrackingSearchResultTable(String columnName,String expValue) {

        try {
            Wait.seconds(5);
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                Debugger.println("Search results are not displayed");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(orderTrackingTableRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No search result found in Order Tracking Search Result Table");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int colIndex = seleniumLib.getColumnIndex(orderTrackingTableHead,columnName);
            if(colIndex == -1){
                Debugger.println("Specified column "+columnName+" not present in the Order Tracking Search Result Table.");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            Wait.seconds(3);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for(int i=0; i<noOfFilteredRows;i++){
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(orderTrackingTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if(expValue.equalsIgnoreCase("non-empty-data")){
                    if(cellValue.isEmpty()){
                        Debugger.println("Column:" + columnName + " value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                        return false;
                    }
                }else {
                    if (!cellValue.contains(expValue)) {
                        Debugger.println("Column:" + columnName + " value, Expected:" + expValue + ",Actual:" + cellValue);
                        SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                        return true;
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
    public boolean verifyOrderTrackingResultColumnValuesDifference(String columnName1,String columnName2) {
        Wait.seconds(3);
        try {
            if (!Wait.isElementDisplayed(driver, searchResults, 20)) {
                Debugger.println("Search results are not displayed");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(orderTrackingTableRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No search result found in Order Tracking Search Result Table");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int colIndex1 = seleniumLib.getColumnIndex(orderTrackingTableHead,columnName1);
            if(colIndex1 == -1){
                Debugger.println("Specified column "+columnName1+" not present in the Order Tracking Search Result Table.");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            int colIndex2 = seleniumLib.getColumnIndex(orderTrackingTableHead,columnName2);
            if(colIndex2 == -1){
                Debugger.println("Specified column "+columnName2+" not present in the Order Tracking Search Result Table.");
                SeleniumLib.takeAScreenShot("orderTrackingTable.jpg");
                return false;
            }
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue1 = "",cellValue2 = "";
            for(int i=0; i<noOfFilteredRows;i++){
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(orderTrackingTableRows+"["+(i+1)+"]/td["+colIndex1+"]");
                cellValue1 = seleniumLib.getText(cellPath);
                cellPath = By.xpath(orderTrackingTableRows+"["+(i+1)+"]/td["+colIndex2+"]");
                cellValue2 = seleniumLib.getText(cellPath);
                if(!cellValue1.equalsIgnoreCase(cellValue2)){
                    Debugger.println("Different value expected for columns :" + columnName1 + " and "+columnName2+", but found same.");
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

}