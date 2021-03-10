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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MiSequencerSamplesPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//select[@id='sequencer_samples-search-value']")
    public WebElement sequencerSamplesSearchValue;

    @FindBy(xpath = "//select[@id='sequencer_samples-search-operator']")
    public WebElement sequencerSamplesSearchOperator;

    @FindBy(xpath = "//select[@id='sequencer_samples-search-col']")
    public WebElement sequencerSamplesSearchColumn;

    @FindBy(xpath = "//button[@data-id='sequencer_samples-search-col']")
    public WebElement sequencerSamplesSearchColumnDropDownBtn;

    @FindBy(xpath = "//div[contains(@class,'active')]//ancestor::div[@class='wrapper']/..//div[contains(@id,'visible')]/div")
    public List<WebElement> visibleColumnReorderingList;

    @FindBy(xpath = "//div[contains(@class,'active')]//ancestor::div[@class='wrapper']/..//div[contains(@id,'hidden')]/div")
    public List<WebElement> hiddenColumnReorderingList;

    By sequencerSampleTableHead = By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th");
    String sequencerSampleTableRows = "//div[contains(@class,'scrollBody')]/table/tbody/tr";

    @FindBy(xpath = "//input[@id='sequencer_samples-search-value']")
    public WebElement sequencerSamplesSearchInput;

    public MiSequencerSamplesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public boolean selectSequencerSamplesDropDownSearchColumn(String value) {
        try {
            if(!seleniumLib.selectFromListByText(sequencerSamplesSearchColumn,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(sequencerSamplesSearchColumn,value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSequencerSamples:selectDropDownSearchColumn: "+ exp);
            SeleniumLib.takeAScreenShot("sequencerSamplesSearchColumn.jpg");
            return false;
        }
    }
    public boolean selectSequencerSamplesDropDownSearchOperator(String value) {
        try {
            if(!seleniumLib.selectFromListByText(sequencerSamplesSearchOperator,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(sequencerSamplesSearchOperator,value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSequencerSamples:selectDropDownSearchOperator: "+ exp);
            SeleniumLib.takeAScreenShot("sequencerSamplesSearchOperator.jpg");
            return false;
        }
    }
    public boolean selectSequencerSamplesDropDownSearchValue(String value) {
        try {
            if(!seleniumLib.selectFromListByText(sequencerSamplesSearchValue,value)){
                Wait.seconds(5);
                return seleniumLib.selectFromListByText(sequencerSamplesSearchValue,value);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSequencerSamples:selectDropDownSearchValue: "+ exp);
            SeleniumLib.takeAScreenShot("SequencerSamplesselectDropDownSearchValue.jpg");
            return false;
        }
    }
    public boolean verifyDateDisplayFormatOnColumn(String columnName,String format) {
        try {
            int noOfFilteredRows = seleniumLib.getNoOfRows(sequencerSampleTableRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No search result found in Sequencer Sample Search Result Table");
                SeleniumLib.takeAScreenShot("sequencerSamplesTable.jpg");
                return false;
            }
            int colIndex = -1;
            List<WebElement> colHeads = driver.findElements(sequencerSampleTableHead);
            try {
                colIndex = seleniumLib.getColumnIndex(colHeads, columnName);
            }catch(Exception StaleElementReferenceException){

            }
            if(colIndex == -1){
                Debugger.println("Specified column "+columnName+" not present in the Sequencer Sample Search Result Table.");
                SeleniumLib.takeAScreenShot("sequencerSamplesTable.jpg");
                return false;
            }
            //Verify value in each column value as expected.
            String regex = "";
            if(format.equalsIgnoreCase("dd/mm/yyyy")) {
                regex = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$";
            }
            Pattern p = Pattern.compile(regex);
            By cellPath = null;
            String cellValue = "";
            for(int i=0; i<noOfFilteredRows;i++){
                Wait.seconds(2);
                cellPath = By.xpath(sequencerSampleTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                Matcher matcher = p.matcher(cellValue);
                if (!matcher.matches()) {
                    Debugger.println("Column:" + columnName + " value, Expected format :" + format + ",Actual:" + cellValue);
                    SeleniumLib.takeAScreenShot("SequencerSampleResult.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verify Date Format " + exp);
            SeleniumLib.takeAScreenShot("verifyDateFormat.jpg");
            return false;
        }
    }

    public boolean verifyListOfColumnsInHeaderShowOrHidden(String headerColumnStatus, List<List<String>> expValues) {
        try {
            List<String> actualHeaderValueList = new ArrayList<>();
            Wait.seconds(5);
            if (headerColumnStatus.equalsIgnoreCase("Show")) {
                for (WebElement headerValue : visibleColumnReorderingList) {
                    actualHeaderValueList.add(headerValue.getText().trim());
                }
            } else if (headerColumnStatus.equalsIgnoreCase("Hide")) {
                for (WebElement headerValue : hiddenColumnReorderingList) {
                    actualHeaderValueList.add(headerValue.getText().trim());
                }
            }
            boolean isPresent = false;
            //Debugger.println("Size : "+actualHeaderValueList.size());
            for (int i = 1; i < expValues.size(); i++) {//Starts from index 1 to exclude heading
                for (int j = 0; j < actualHeaderValueList.size(); j++) {
                    //Debugger.println("Value: "+actualHeaderValueList.get(j));
                    if (expValues.get(i).get(0).equalsIgnoreCase(actualHeaderValueList.get(j))) {
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    Debugger.println("Expected value :" + expValues.get(i).get(0) + " Not present under section:" + headerColumnStatus);
                    SeleniumLib.takeAScreenShot("ColumnHeaderOrderingShowHide.jpg");
                    return isPresent;
                }
                isPresent = false;//For next value verification
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from:getListOfColumnsInHeaderShowOrHidden:" + exp);
            SeleniumLib.takeAScreenShot("ColumnHeaderOrderingShowHide.jpg");
            return false;
        }
    }
    public boolean enterSequencerSamplesTextSearchValue(String value) {
        try {

            Wait.seconds(2);
            seleniumLib.sendValue(sequencerSamplesSearchInput,value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSequencerSamples:sequencerSamplesSearchInput: "+ exp);
            SeleniumLib.takeAScreenShot("sequencerSamplesSearchInput.jpg");
            return false;
        }
    }


    public boolean verifySequencerSamplesDropDownSearchOperator(String value) {
        Wait.seconds(3); // To load the dropdown values
        try {
            Wait.seconds(3);
            if (!seleniumLib.selectFromListByText(sequencerSamplesSearchOperator, value)) {
                Wait.seconds(5);
                if (!seleniumLib.selectFromListByText(sequencerSamplesSearchOperator, value)) {
                    By optionPath = By.xpath("//ul//li/a/span[contains(text(),'" + value + "')]");
                    seleniumLib.clickOnElement(optionPath);
                }
            }
            return true;
        } catch (StaleElementReferenceException exp) {
            By searchOperator = By.xpath("//select[@id='sequencer_samples-search-operator']");
            return seleniumLib.optionFromListByText(searchOperator, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MISequencerSamples:selectDropDownSearchOperator: " + exp);
            SeleniumLib.takeAScreenShot("SequencerSamplesSearchOperator.jpg");
            return false;
        }
    }

    public List<String> searchColumnDropDownMenu() {
        List<String> allOptions = new ArrayList<>();
        try {
            Wait.seconds(3);
            if (!Wait.isElementDisplayed(driver, sequencerSamplesSearchColumnDropDownBtn, 10)) {
                Debugger.println("Picklists search column not displayed");
                SeleniumLib.takeAScreenShot("DropDownValues.jpg");
                return null;
            }
            Select searchColumnSelect = new Select(sequencerSamplesSearchColumn);
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