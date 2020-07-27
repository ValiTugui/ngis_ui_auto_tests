package co.uk.gel.proj.miportal_pages;


import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MiClinicalDataQualityPage<checkTheErrorMessagesInDOBFutureDate> {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiClinicalDataQualityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//select[@id='clinical_dq-filter_glh']/child::*")
    public List<WebElement> clinicalDQFilterGLH;

    @FindBy(xpath = "(//div[@class='box']//span[@class='caret'])[1]")
    public WebElement clinicalDQFilterDropdown;

    @FindBy(xpath = "//select[@id='clinical_dq-filter_glh']")
    public WebElement glhDropDown;

    @FindBy(xpath = "(//div[@class='box']//span[@class='caret'])[2]")
    public WebElement orderingEntityDropdown;

    @FindBy(xpath = "//div[@class='btn-group btn-group-sm btn-block']//button[text()][2]")
    public WebElement deselectAllButton;

    @FindBy(xpath = "//div[@class='btn-group btn-group-sm btn-block']//button[text()][1]")
    public WebElement selectAllButton;

    @FindBy(xpath = "//button[@id='clinical_dq-apply_filters']")
    public WebElement applyFiltersButton;

    @FindBy(xpath = "//a[contains(text(),'Summary')]")
    public WebElement summaryTitle;

    @FindBy(xpath = "//a[text()='Full Output']")
    public WebElement fullOutputTitle;

    @FindBy(xpath = "//a[text()='Streamline Output']")
    public WebElement streamlineOutputTitle;

    @FindBy(xpath = "//a[text()='Genomic Identity Output']")
    public WebElement genomicIdentityOutputTitle;

    @FindBy(xpath = "//a[text()='Appendix - all rules']")
    public WebElement appendixAllRulesTitle;

    @FindBy(xpath = "//a[@id='bs-select-16-0']//span[@class='glyphicon glyphicon-ok check-mark']")
    public WebElement checkMark;

    @FindBy(xpath = "//button[@id='clinical_dq-reset_filters']")
    public WebElement resetFiltersButton;

    @FindBy(xpath = "//a[@data-value=\"clinical_dq_tab\"]")
    public WebElement clinicalDqTab;

    By clinicalDqReportTableHead = By.xpath("//div[@class='dataTables_scrollHeadInner']//table[@class='display dataTable no-footer']/thead/tr/th") ;
    String clinicalDqReportTableRows = "//div[@class='dataTables_scrollBody']//table[@class='display dataTable no-footer']/tbody/tr";

    public boolean verifyClinicalDataQualityReport(String expValue) {
        try {
            By clinicalDQReportPath = By.xpath("//h3[text()='" + expValue + "']");
            SeleniumLib.waitForElementVisible(clinicalDQReportPath);
            if (!seleniumLib.getText(clinicalDQReportPath).equalsIgnoreCase(expValue)) {
                Debugger.println("Clinical_dq_Report is not present");
                return false;
            }
            Debugger.println("Clinical_dq_Report header is present");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from : verifyClinicalDataQualityReport :  " + exp);
            return false;
        }
    }

    public boolean verifyReportGuidance(String expValue) {
        try {
            By reportGuidancePath = By.xpath("//a[text()='" + expValue + "']");
            SeleniumLib.waitForElementVisible(reportGuidancePath);
            if (!seleniumLib.getText(reportGuidancePath).equalsIgnoreCase(expValue)) {
                Debugger.println("Report_Guidance is not present");
                return false;
            }
            Debugger.println("Report_Guidance link is present");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from : verifyReportGuidance :  " + exp);
            return false;
        }
    }

//    public void clickOnClinicalDqFilterGlhDropdown() {
//        clinicalDQFilterDropdown.click();
//    }
    public boolean selectClinicalDqFilterGlh(String value) {
        try {
            return seleniumLib.selectFromListByText(glhDropDown, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalGlhSamples:selectDropDownSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("GlhselectDropDownSearchValue.jpg");
            return false;
        }
    }

    public boolean selectValueInGlhDropDown(String value) {
        try {
            Wait.seconds(2);
            if(!seleniumLib.selectFromListByText(glhDropDown,value)){
                Debugger.println("The " + value + " is present");
                return seleniumLib.selectFromListByText(glhDropDown,value);
            }
            Debugger.println("The " + value + " is selected");
            return true;
        }
        catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:selectGlhDropdownMenu: "+ exp);
            SeleniumLib.takeAScreenShot("GlhDropdownMenu.jpg");
            return false;
        }
    }

    public boolean clickOnOrderingEntityDd(){
        try{
            if(!Wait.isElementDisplayed(driver, orderingEntityDropdown,30)){
                Debugger.println("Ordering entity dropdown is not displayed");
                SeleniumLib.takeAScreenShot("OrderingEntityDropdownMenu.jpg");
                return false;
            }
            orderingEntityDropdown.click();
            Debugger.println("Ordering entity dropdown is clicked");
            return true;
        }catch (Exception exp){
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnOrderingEntityDd: "+ exp);
            SeleniumLib.takeAScreenShot("OrderingEntityDropdownMenu.jpg");
            return false;
        }
    }

    public boolean clickOnDeselectAllButton() {
        try{
            if(!Wait.isElementDisplayed(driver, deselectAllButton,30)){
                Debugger.println("deselectAllButton is not displayed");
                SeleniumLib.takeAScreenShot("deselectAllButton.jpg");
                return false;
            }
            deselectAllButton.click();
            Debugger.println("Deselected all the ordering entities");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnDeselectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("deselectAllButton.jpg");
            return false;
        }
    }

    public boolean clickOnSelectAllButton() {
        try {
            if(!Wait.isElementDisplayed(driver, selectAllButton, 30)){
                Debugger.println("SelectAllButton is not displayed");
                SeleniumLib.takeAScreenShot("SelectAllButton.jpg");
                return false;
            }
            selectAllButton.click();
            Debugger.println("Selected all the ordering entities");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnSelectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("SelectAllButton.jpg");
            return false;
        }
    }

    public boolean clickOnApplyFiltersButton() {
        try{
            if(!Wait.isElementDisplayed(driver, applyFiltersButton, 30)){
                Debugger.println("ApplyFiltersButton is not displayed");
                SeleniumLib.takeAScreenShot("ApplyFiltersButton.jpg");
                return false;
            }
            applyFiltersButton.click();
            Debugger.println("Apply filters button clicked");
            Wait.seconds(8);
            return true;
        } catch (Exception exp){
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnApplyFiltersButton: "+ exp);
            SeleniumLib.takeAScreenShot("SelectAllButton.jpg");
            return false;
        }
    }

    public boolean verifyTheElementsPresentInApplyFiltersSection() {
        try{
            if(!Wait.isElementDisplayed(driver,summaryTitle,30)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,fullOutputTitle,30)){
                Debugger.println("Full Output Title is displayed");
                SeleniumLib.takeAScreenShot("FullOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,streamlineOutputTitle,30)){
                Debugger.println("Streamline Output Title is displayed");
                SeleniumLib.takeAScreenShot("StreamlineOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,genomicIdentityOutputTitle,30)){
                Debugger.println("Genomic Identity Output Title is displayed");
                SeleniumLib.takeAScreenShot("GenomicIdentityOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,appendixAllRulesTitle,30)){
                Debugger.println("Appendix All RulesTitle is displayed");
                SeleniumLib.takeAScreenShot("AppendixAllRulesTitle.jpg");
                return false;
            }
            List<WebElement> expectedElements = new ArrayList<>();
            expectedElements.add(summaryTitle);
            expectedElements.add(fullOutputTitle);
            expectedElements.add(streamlineOutputTitle);
            expectedElements.add(genomicIdentityOutputTitle);
            expectedElements.add(appendixAllRulesTitle);
            for (int i=0; i < expectedElements.size(); i++){
                if (!seleniumLib.isElementPresent(expectedElements.get(i))){
                    Debugger.println("Summary result section element not displayed: "+expectedElements.get(i));
                    SeleniumLib.takeAScreenShot("SummaryResultSectionFound.jpg");
                    return false;
                }
            } return true;
        } catch (Exception exp){
            Debugger.println("Summary result section is not properly loaded" + exp);
            SeleniumLib.takeAScreenShot("SummaryResultSectionNotFound.jpg");
            return false;
        }
    }
//After clicking on deselect all button all the ordering entities should deselect
    public boolean orderingEntitiesDeselect() {
        try {
            if(!Wait.isElementDisplayed(driver, checkMark, 60)){
                Debugger.println("Check mark is not present");
                SeleniumLib.takeAScreenShot("CheckMark.jpg");
                return true;
            }
            return false;
        } catch (Exception exp){
            Debugger.println("Check mark is present");
            SeleniumLib.takeAScreenShot("CheckMark.jpg");
            return false;
        }
    }
//After clicking on select all button all the ordering entities should select
    public boolean orderingEntitiesSelect() {
        try {
            Wait.seconds(5);
            if(Wait.isElementDisplayed(driver, checkMark, 60)){
                Debugger.println("Tick mark is present");
                SeleniumLib.takeAScreenShot("TickMark.jpg");
                return true;
            }
            return false;
        } catch (Exception exp){
            Debugger.println("Check mark is not present");
            SeleniumLib.takeAScreenShot("TickMark.jpg");
            return false;
        }
    }

    public boolean clickOnSpecifiedTab(String expectedTabName) {
        By tabName = null;
        try{
            tabName = By.xpath("//a[text()='"+ expectedTabName + "']");
            if(!Wait.isElementDisplayed(driver, driver.findElement(tabName), 30)){
                Debugger.println(expectedTabName + " is not displayed");
                SeleniumLib.takeAScreenShot("NoExpectedTab.jpg");
                return false;
            }
            Wait.forElementToBeClickable(driver, driver.findElement(tabName));
            Click.element(driver, driver.findElement(tabName));
            Wait.seconds(2);
            Debugger.println("The " + expectedTabName + " is clicked");
            return true;
        } catch (Exception exp){
            Debugger.println("Exception from clickOnSpecifiedTab:" + exp);
            SeleniumLib.takeAScreenShot("NoSpecifiedTab.jpg");
            return false;
        }

    }

    public boolean verifyTheColumnValuesUnderSpecifiedTab(String colName, String expValue) {
        try{
            if(!Wait.isElementDisplayed(driver,summaryTitle,30)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(clinicalDqReportTableRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No Filter results found in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("SpecifiedRow.jpg");
                return false;
            }
            int colIndex = seleniumLib.getColumnIndex(clinicalDqReportTableHead,colName);
            if(colIndex == -1){
                Debugger.println("Specified column "+colName+" not present in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("SpecifiedColumn.jpg");
                return false;
            }
            Wait.seconds(5);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue  = "";
            for(int i=0; i<noOfFilteredRows; i++){
                cellPath = By.xpath(clinicalDqReportTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")){
                    if (cellValue.isEmpty()){
                        Debugger.println("Column: "+ colName +" value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("SpecifiedColumn");
                        return false;
                    }else {
                        if (!cellValue.contains(expValue)){
                            Debugger.println("Column: " + colName + " value, Expected: " + expValue + ", Actual: " + cellValue);
                            SeleniumLib.takeAScreenShot("SpecifiedColumn");
                            return true;
                        }
                    }
                }
            }return true;
        }catch (Exception exp){
            Debugger.println("Exception from verifyColumnValueInClinicalDqReportResultTable:" + exp);
            SeleniumLib.takeAScreenShot("StreamlineOutputTabException.jpg");
            return false;
        }
    }

    public boolean clickOnResetFiltersButton() {
        try{
            if(!Wait.isElementDisplayed(driver, resetFiltersButton,30)){
                Debugger.println("ResetFiltersButton is not displayed");
                SeleniumLib.takeAScreenShot("ResetFiltersButton.jpg");
                return false;
            }
            resetFiltersButton.click();
            Debugger.println("Reset filters button was clicked");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnResetFiltersButton: " + exp);
            SeleniumLib.takeAScreenShot("ResetFiltersButton.jpg");
            return false;
        }
    }

    public boolean navigateToClinicalDataQualityPage() {
        try {
            if (!Wait.isElementDisplayed(driver, clinicalDqTab, 30)){
                Debugger.println("clinicalDqTab is not displayed");
                SeleniumLib.takeAScreenShot("clinicalDqTab.jpg");
                return false;
            }
            clinicalDqTab.click();
            Debugger.println("Clinical Dq report tab is selected");
            return true;
        } catch (Exception exp){
            Debugger.println("Exception in MIPortalClinicalDataQuality:navigateToClinicalDataQualityPage: " + exp);
            SeleniumLib.takeAScreenShot("clinicalDqTab.jpg");
            return false;
        }
    }
}




