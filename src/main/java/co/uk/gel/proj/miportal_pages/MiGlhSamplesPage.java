package co.uk.gel.proj.miportal_pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class MiGlhSamplesPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiGlhSamplesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }


    @FindBy(xpath = "//div[@class='tab-pane active']//div[@class='box-header']")
    public WebElement searchBoxHeader;

    @FindBy(xpath = "//input[@data-shinyjs-resettable-id='glh_samples-search-value']")
    public WebElement getGlhFileSubmissionDate;

    @FindBy(xpath = "//div[@id='glh_samples-search-value_input']//input")
    public WebElement sampleSearchValue;

    @FindBy(xpath = "//input[@id='glh_samples-search-value']")
    public WebElement glhSearchValueInputField;

    @FindBy(xpath = "//select[@id='glh_samples-search-value']")
    public WebElement glhSearchValue;

    @FindBy(xpath = "//select[@id='glh_samples-search-operator']")
    public WebElement glhSearchOperator;

    @FindBy(xpath = "//select[@id='glh_samples-search-col']")
    public WebElement glhSearchColumn;

    By glhSamplesTableHead = By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th");
    String glhSamplesTableRows = "//div[contains(@class,'scrollBody')]/table/tbody/tr";


    public boolean fillInTheSampleConsignmentNumber(String number) {
        try {
            if (!Wait.isElementDisplayed(driver, sampleSearchValue, 10)) {
                Debugger.println("Search box is not displayed.");
                SeleniumLib.takeAScreenShot("ConsignmentNumberSearchboxNotPresent.jpg");
                return false;
            }
            Actions.clickElement(driver, sampleSearchValue);
            Actions.fillInValue(sampleSearchValue, number);
            return true;

        } catch (Exception exp) {
            Debugger.println("Exception filling SampleConsignmentNumber:" + exp);
            SeleniumLib.takeAScreenShot("fillInTheSampleConsignmentNumber.jpg");
            return false;
        }
    }

    public boolean selectGlhDropDownSearchColumn(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(glhSearchColumn, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalGlhSamples:selectDropDownSearchColumn: " + exp);
            SeleniumLib.takeAScreenShot("GlhselectDropDownSearchColumn.jpg");
            return false;
        }
    }

    public boolean selectGlhDropDownSearchOperator(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(glhSearchOperator, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalGlhSamples:selectDropDownSearchOperator: " + exp);
            SeleniumLib.takeAScreenShot("GlhselectDropDownSearchOperator.jpg");
            return false;
        }
    }

    public boolean selectGlhDropDownSearchValue(String value) {
        try {
            Wait.seconds(2);
            return seleniumLib.selectFromListByText(glhSearchValue, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalGlhSamples:selectDropDownSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("GlhselectDropDownSearchValue.jpg");
            return false;
        }
    }

    public boolean verifyColumnValueInGlhSamplesSearchResultTable(String columnName, String expValue) {
        Wait.seconds(3);
        try {
            int noOfFilteredRows = seleniumLib.getNoOfRows(glhSamplesTableRows);
            if (noOfFilteredRows == 0) {
                Debugger.println("No search result found in GLH Samples Search Result Table");
                SeleniumLib.takeAScreenShot("glhSampleTable.jpg");
                return false;
            }
            List<WebElement> colHeads = driver.findElements(glhSamplesTableHead);
            int colIndex = seleniumLib.getColumnIndex(colHeads, columnName);
            if (colIndex == -1) {
                Debugger.println("Specified column " + columnName + " not present in the GLH Samples Search Result Table.");
                SeleniumLib.takeAScreenShot("glhSampleTable.jpg");
                return false;
            }
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue = "";
            for (int i = 0; i < noOfFilteredRows; i++) {
                //Debugger.println("PATH:"+fileSubmissionTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellPath = By.xpath(glhSamplesTableRows + "[" + (i + 1) + "]/td[" + colIndex + "]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")) {
                    if (cellValue.isEmpty()) {
                        Debugger.println("Column:" + columnName + " value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("GLHSamplesResult.jpg");
                        return false;
                    }
                } else {
                    if (!cellValue.contains(expValue)) {
                        Debugger.println("Column:" + columnName + " value, Expected:" + expValue + ",Actual:" + cellValue);
                        SeleniumLib.takeAScreenShot("GLHSamplesResult.jpg");
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

    public boolean fillValueInGLHSamplesSearchInputField(String searchValue) {
        try {
            Wait.seconds(2);
            if (!Wait.isElementDisplayed(driver, glhSearchValueInputField, 20)) {
                Debugger.println("The text search box is Not displayed");
                SeleniumLib.takeAScreenShot("GLHSamplesTextSearchBox.jpg");
                return false;
            }
            seleniumLib.sendValue(glhSearchValueInputField, searchValue);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from fillValueInGLHSamplesSearchInputField:" + exp);
            SeleniumLib.takeAScreenShot("GLHSamplesTextSearchBox.jpg");
            return false;
        }
    }

    @FindBy(xpath = "//div[contains(@class,'scrollHeadInner')]/table/thead/tr")
    public WebElement searchResultRowHeader;

    public boolean doubleClickDataRow() {
        try {
            if (!Wait.isElementDisplayed(driver, searchResultRowHeader, 20)) {
                Debugger.println("Table Header is not display." + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("TableHeaderNotPresent.jpg");
                return false;
            }
            List<WebElement> tableRows = driver.findElements(By.xpath(glhSamplesTableRows));
            if (tableRows.size() < 1) {
                //Return with proper message
                Debugger.println("Table data rows are not loaded." + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("TableRowNotPresent.jpg");
                return false;
            }
            List<WebElement> tableHeads = driver.findElements(By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th"));
            WebElement firstRow = tableRows.get(0);
            List<WebElement> rowColumns = driver.findElements(By.xpath(glhSamplesTableRows + "[1]/td"));
            String[] rowValues = new String[rowColumns.size()];
            for (int i = 0; i < rowColumns.size(); i++) {
                Debugger.println("Setting RowValue: "+(i+1)+"."+tableHeads.get(i).getText()+": "+rowColumns.get(i).getText());
                rowValues[i] = tableHeads.get(i).getText()+": "+rowColumns.get(i).getText();
            }
            seleniumLib.doubleClickOperation(firstRow);
            Debugger.println("Double Clicked on First Row....");
            boolean result =verifyPopUpBox(rowValues);
            Wait.seconds(10);

            return result;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyErrorMessage : " + exp + "\n" + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("ErrorMessage.jpg");
            return false;
        }
    }
    public boolean verifyPopUpBox(String[] rowValues) {
        try {
            List<WebElement> popupList = driver.findElements(By.xpath("//div[@id='pop-up']/p"));
            Debugger.println("Popup Values Size: "+popupList.size()+", RowValues Size:"+rowValues.length);
            boolean isPresent = false;
            for (int i = 0; i < rowValues.length; i++) {
                if(rowValues[i] == null){
                    continue;
                }
                Debugger.println("Verifying RowValue: "+(i+1)+"."+rowValues[i]);
                for (int j = 0; j < popupList.size(); j++) {
                    if(popupList.get(j).getText() == null){
                        continue;
                    }
                    if ((popupList.get(j).getText()).contains(rowValues[i])) {
                        isPresent = true;
                        break;
                    }
                }
                if (!isPresent) {
                    Debugger.println("Expected Value: " + rowValues[i] + " Not present in the popup.");
                    SeleniumLib.takeAScreenShot("mismatchInValue.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyPopUpBox : " + exp + "\n" + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("PopUpBoxError.jpg");
            return false;
        }
    }
    @FindBy(xpath = "//div[@id=\"pop-up-close-button\"]")
    public WebElement closePopUp;

    public boolean closePopUpBox() {
        try {
            if (!Wait.isElementDisplayed(driver, closePopUp, 20)) {
                Debugger.println("Pop up close icon is not displayed." + driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("closeIconNotPresent.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(closePopUp);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyErrorMessage : " + exp + "\n" + driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("ErrorMessage.jpg");
            return false;
        }
    }

}