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


public class MiNewReferralsPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//select[@id='new_referrals-search-value']")
    public WebElement newReferralsSearchValue;

    @FindBy(xpath = "//select[@id='new_referrals-search-operator']")
    public WebElement newReferralsSearchOperator;

    @FindBy(xpath = "//select[@id='new_referrals-search-col']")
    public WebElement newReferralsSearchColumn;

    @FindBy(xpath = "//input[@id='new_referrals-search-value']")
    public WebElement newReferralsSearchInput;

    By newReferralsTableHead = By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th");
    String newReferralsTableRows = "//div[contains(@class,'scrollHeadInner')]/table/thead/tr";


    public MiNewReferralsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    public boolean selectNewReferralsDropDownSearchColumn(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(newReferralsSearchColumn,value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalNewReferrals:selectDropDownSearchColumn: "+ exp);
            SeleniumLib.takeAScreenShot("NewReferralsselectDropDownSearchColumn.jpg");
            return false;
        }
    }
    public boolean selectNewReferralsDropDownSearchOperator(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(newReferralsSearchOperator,value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalNewReferrals:selectDropDownSearchOperator: "+ exp);
            SeleniumLib.takeAScreenShot("NewReferralsselectDropDownSearchOperator.jpg");
            return false;
        }
    }
    public boolean selectNewReferralsDropDownSearchValue(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(newReferralsSearchValue,value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalNewReferrals:selectDropDownSearchValue: "+ exp);
            SeleniumLib.takeAScreenShot("NewReferralsselectDropDownSearchValue.jpg");
            return false;
        }
    }
    public boolean verifyColumnValueInNewReferralsSearchResultTable(String columnName,String expValue) {
        Wait.seconds(3);
        try {
            int noOfFilteredRows = seleniumLib.getNoOfRows(newReferralsTableRows);
            if (noOfFilteredRows == 0) {
                Debugger.println("No search result found in NewReferrals Search Result Table");
                SeleniumLib.takeAScreenShot("newReferralTable.jpg");
                return false;
            }
            List<WebElement> colHeads = driver.findElements(newReferralsTableHead);
            int colIndex = seleniumLib.getColumnIndex(colHeads, columnName);
            if (colIndex == -1) {
                Debugger.println("Specified column " + columnName + " not present in the New Referrals Search Result Table.");
                SeleniumLib.takeAScreenShot("newReferralTable.jpg");
                return false;
            }
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for (int i = 0; i < noOfFilteredRows; i++) {
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(newReferralsTableRows + "[" + (i + 1) + "]/td[" + colIndex + "]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")) {
                    if (cellValue.isEmpty()) {
                        Debugger.println("Column:" + columnName + " value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("NewReferralResult.jpg");
                        return false;
                    }
                } else {
                    if (!cellValue.contains(expValue)) {
                        Debugger.println("Column:" + columnName + " value, Expected:" + expValue + ",Actual:" + cellValue);
                        SeleniumLib.takeAScreenShot("NewReferralResult.jpg");
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from retrieving data." + exp);
            SeleniumLib.takeAScreenShot("UnableToRetrieveAllColumnData.jpg");
            return false;
        }
    }
    public boolean enterNewReferralsTextSearchValue(String value) {
        try {

            Wait.seconds(2);
            seleniumLib.sendValue(newReferralsSearchInput,value);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalNewReferrals:newReferralsSearchInput: "+ exp);
            SeleniumLib.takeAScreenShot("newReferralsSearchInput.jpg");
            return false;
        }
    }
}