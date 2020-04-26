package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


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

    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//input[@data-shinyjs-resettable-id='plater_samples-search-value']")
    public WebElement getPlaterSamplesDate;

    By platerSamplesTableHead = By.xpath("//div[@id='plater_samples-display-table_contents']//table[contains(@id,'DataTables_Table')]/thead/tr/th");
    String platerSamplesTableRows = "//div[@id='plater_samples-display-table_contents']//table[contains(@id,'DataTables_Table')]/tbody/tr";

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
            } else if (date.equalsIgnoreCase("future_date")     ){
                String dateToday = TestUtils.todayInDDMMYYYFormat();
                Debugger.println("today's date in dd/mm/yyyy " + dateToday);
                dateToday = dateToday.replace("/", "-");
                Debugger.println("today's date in dd-mm-yyyy " + dateToday);
                String updatedFutureDate =  TestUtils.getDateNineMonthsOrMoreBeforeDoB(dateToday, 1,0, 0); //Add future day +1
                Debugger.println("future date in dd-mm-yyyy " + updatedFutureDate);
                Actions.clickElement(driver, getPlaterSamplesDate);
                Actions.clearInputField(getPlaterSamplesDate);
                Wait.seconds(2);
                Actions.fillInValue(getPlaterSamplesDate,updatedFutureDate);
            } else{
                Wait.seconds(1);
                Actions.clickElement(driver, getPlaterSamplesDate);
                Actions.clearInputField(getPlaterSamplesDate);
                Wait.seconds(2);
                Actions.fillInValue(getPlaterSamplesDate,date);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception filling date:" + exp);
            SeleniumLib.takeAScreenShot("UnableToFillDate.jpg");
            return false;
        }
    }
    public boolean selectPlaterSamplesDropDownSearchColumn(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(platerSampleSearchColumn,value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPlaterSamples:selectDropDownSearchColumn: "+ exp);
            SeleniumLib.takeAScreenShot("PlaterSamplesselectDropDownSearchColumn.jpg");
            return false;
        }
    }
    public boolean selectPlaterSamplesDropDownSearchOperator(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(platerSampleSearchOperator,value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPlaterSamples:selectDropDownSearchOperator: "+ exp);
            SeleniumLib.takeAScreenShot("PlaterSamplesselectDropDownSearchOperator.jpg");
            return false;
        }
    }
    public boolean selectPlaterSamplesDropDownSearchValue(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(platerSampleSearchValue,value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalPlaterSamples:selectDropDownSearchValue: "+ exp);
            SeleniumLib.takeAScreenShot("PlaterSamplesselectDropDownSearchValue.jpg");
            return false;
        }
    }
    public boolean verifyColumnValueInPlaterSamplesSearchResultTable(String columnName,String expValue) {
        Wait.seconds(3);
        try {
            int noOfFilteredRows = seleniumLib.getNoOfRows(platerSamplesTableRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No search result found in PlaterSamples Search Result Table");
                SeleniumLib.takeAScreenShot("platerSamplesTable.jpg");
                return false;
            }
            int colIndex = seleniumLib.getColumnIndex(platerSamplesTableHead,columnName);
            if(colIndex == -1){
                Debugger.println("Specified column "+columnName+" not present in the Plater Samples Search Result Table.");
                SeleniumLib.takeAScreenShot("platerSamplesTable.jpg");
                return false;
            }
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for(int i=0; i<noOfFilteredRows;i++){
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(platerSamplesTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if(expValue.equalsIgnoreCase("non-empty-data")){
                    if(cellValue.isEmpty()){
                        Debugger.println("Column:" + columnName + " value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("platerSamplesTable.jpg");
                        return false;
                    }
                }else {
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
            seleniumLib.sendValue(platerSampleSearchInput,value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalOrderTracking:enterPlaterSampleTextSearchValue: "+ exp);
            SeleniumLib.takeAScreenShot("enterPlaterSampleTextSearchValue.jpg");
            return false;
        }
    }
}