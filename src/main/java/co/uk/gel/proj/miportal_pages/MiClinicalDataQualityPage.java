package co.uk.gel.proj.miportal_pages;


import co.uk.gel.lib.Actions;
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

public class MiClinicalDataQualityPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiClinicalDataQualityPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//select[@id='clinical_dq-filter_glh']")
    public WebElement glhDropDown;

    @FindBy(xpath = "//button[@data-id='clinical_dq-filter_ordering_entity']")
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

    @FindBy(xpath = "//div[@role='listbox']//a[@role='option'][@aria-selected='true']//span[@class='glyphicon glyphicon-ok check-mark']")
    public List<WebElement> orderingEntitySelections;

    @FindBy(xpath = "//button[@id='clinical_dq-reset_filters']")
    public WebElement resetFiltersButton;

    @FindBy(xpath = "//a[@data-value=\"clinical_dq_tab\"]")
    public WebElement clinicalDqTab;

    @FindBy(xpath = "//h3[text()='Clinical Data Quality Report']")
    public WebElement clinicalDqHeader;

    @FindBy(xpath = "//a[text()='Report Guidance']")
    public WebElement reportGuidanceLink;

    By clinicalDqReportTableHead = By.xpath("//div[@class='dataTables_scrollHeadInner']//table[@class='display dataTable no-footer']/thead/tr/th") ;
    String clinicalDqReportTableRows = "//div[@class='dataTables_scrollBody']//table[@class='display dataTable no-footer']/tbody/tr";

    public boolean verifyClinicalDataQualityReport(String expValue) {
        try {
            Wait.forElementToBeDisplayed(driver, clinicalDqHeader);
            String clinicalDqHeaderText = clinicalDqHeader.getText();
            if (!expValue.equalsIgnoreCase(clinicalDqHeaderText)) {
                Debugger.println("Expected header text is: " +expValue+ "Actual header text is: " +clinicalDqHeaderText);
                SeleniumLib.takeAScreenShot("clinicalDqReportHeaderIsNotMatched.jpg");
                return false;
            }
            Debugger.println("Clinical_dq_Report header is visible");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from : verifyClinicalDataQualityReport :  " + exp);
            SeleniumLib.takeAScreenShot("clinicalDqReportHeaderIsNotPresent.jpg");
            return false;
        }
    }

    public boolean verifyReportGuidance(String expValue) {
        try {
            Wait.forElementToBeDisplayed(driver, reportGuidanceLink);
            String reportGuidanceText = reportGuidanceLink.getText();
            if (!expValue.equalsIgnoreCase(reportGuidanceText)) {
                Debugger.println("Report_Guidance link is not present");
                SeleniumLib.takeAScreenShot("reportGuidanceLinkNotPresent.jpg");
                return false;
            }
            Debugger.println("Report_Guidance link is present");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from : verifyReportGuidance :  " + exp);
            SeleniumLib.takeAScreenShot("reportGuidanceLinkNotPresent.jpg");
            return false;
        }
    }

    public boolean selectClinicalDqFilterGlh(String value) {
        try {
            return seleniumLib.selectFromListByText(glhDropDown, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalGlhSamples:selectDropDownSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("notFoundClinicalDqFilter" + value + ".jpg");
            return false;
        }
    }

    public boolean selectValueInGlhDropDown(String value) {
        try {
            Wait.seconds(2);
            if(!seleniumLib.selectFromListByText(glhDropDown,value)){
                Debugger.println("The " + value + " is not present");
                return seleniumLib.selectFromListByText(glhDropDown,value);
            }
            Debugger.println("The " + value + " is selected");
            return true;
        }
        catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:selectGlhDropdownMenu: "+ exp);
            SeleniumLib.takeAScreenShot("notFoundGlhDropdown" + value + ".jpg");
            return false;
        }
    }

    public boolean clickOnOrderingEntityDd(){
        try{
            if(!Wait.isElementDisplayed(driver, orderingEntityDropdown,30)){
                Debugger.println("Ordering entity dropdown is not displayed");
                SeleniumLib.takeAScreenShot("noOrderingEntityDropdown.jpg");
                return false;
            }
            Wait.seconds(5);//To load the dropdown elements
            Actions.clickElement(driver, orderingEntityDropdown);
            Debugger.println("Ordering entity dropdown is clicked");
            return true;
        }catch (Exception exp){
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnOrderingEntityDd: "+ exp);
            SeleniumLib.takeAScreenShot("noOrderingEntityDropdown.jpg");
            return false;
        }
    }

    public boolean clickOnDeselectAllButton() {
        try{
            if(!Wait.isElementDisplayed(driver, deselectAllButton,30)){
                Debugger.println("deselectAllButton is not displayed");
                SeleniumLib.takeAScreenShot("noDeselectAllButtonIsDisplayed.jpg");
                return false;
            }
            Actions.clickElement(driver, deselectAllButton);
            Debugger.println("Deselected all the ordering entities");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnDeselectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("noDeselectAllButtonIsDisplayed.jpg");
            return false;
        }
    }

    public boolean clickOnSelectAllButton() {
        try {
            if(!Wait.isElementDisplayed(driver, selectAllButton, 30)){
                Debugger.println("SelectAllButton is not displayed");
                SeleniumLib.takeAScreenShot("noSelectAllButtonIsDisplayed.jpg");
                return false;
            }
            Actions.clickElement(driver, selectAllButton);
            Debugger.println("Selected all the ordering entities");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnSelectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("noSelectAllButtonIsDisplayed.jpg");
            return false;
        }
    }

    public boolean clickOnApplyFiltersButton() {
        try{
            if(!Wait.isElementDisplayed(driver, applyFiltersButton, 30)){
                Debugger.println("ApplyFiltersButton is not displayed");
                SeleniumLib.takeAScreenShot("noApplyFiltersButton.jpg");
                return false;
            }
            Actions.clickElement(driver, applyFiltersButton);
            Debugger.println("Apply filters button clicked");
            Wait.seconds(8); //This wait is for to load the apply filters table
            return true;
        } catch (Exception exp){
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnApplyFiltersButton: "+ exp);
            SeleniumLib.takeAScreenShot("noApplyFiltersButton.jpg");
            return false;
        }
    }

    public boolean verifyTheElementsPresentInApplyFiltersSection() {
        try{
            if(!Wait.isElementDisplayed(driver,summaryTitle,30)){
                Debugger.println("Summary title is not displayed");
                SeleniumLib.takeAScreenShot("noSummaryTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,fullOutputTitle,30)){
                Debugger.println("Full Output title is not displayed");
                SeleniumLib.takeAScreenShot("noFullOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,streamlineOutputTitle,30)){
                Debugger.println("Streamline Output title is not displayed");
                SeleniumLib.takeAScreenShot("noStreamlineOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,genomicIdentityOutputTitle,30)){
                Debugger.println("Genomic Identity Output title is not displayed");
                SeleniumLib.takeAScreenShot("noGenomicIdentityOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,appendixAllRulesTitle,30)){
                Debugger.println("Appendix All Rules title is not displayed");
                SeleniumLib.takeAScreenShot("noAppendixAllRulesTitle.jpg");
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
                    Debugger.println("Summary result section not displayed: "+expectedElements.get(i));
                    SeleniumLib.takeAScreenShot("applyFiltersResultSectionNotFound.jpg");
                    return false;
                }
            } return true;
        } catch (Exception exp){
            Debugger.println("Summary result section is not properly loaded" + exp);
            SeleniumLib.takeAScreenShot("applyFiltersResultSectionNotFound.jpg");
            return false;
        }
    }
//After clicking on deselect all button all the ordering entities should deselect
    public boolean orderingEntitiesDeselect() {
        //Check mark should not be present
        if(orderingEntitySelections.size() > 1){
            Debugger.println("Ordering entity is shown as selected, but not expected to be selected.");
            SeleniumLib.takeAScreenShot("OrderingEntitySelected.jpg");
            return false;
        }
        return true;
    }
//After clicking on select all button all the ordering entities should select
    public boolean orderingEntitiesSelect() {
        //Check mark should be present
        if(orderingEntitySelections.size() < 1){
            Debugger.println("Ordering entity is shown as deselected, but expected to be selected.");
            SeleniumLib.takeAScreenShot("OrderingEntityDeselected.jpg");
            return false;
        }
        return true;
    }

    public boolean clickOnSpecifiedTab(String expectedTabName) {
        By tabName = null;
        try{
            tabName = By.xpath("//a[text()='"+ expectedTabName + "']");
            if(!Wait.isElementDisplayed(driver, driver.findElement(tabName), 30)){
                Debugger.println(expectedTabName + " is not displayed");
                SeleniumLib.takeAScreenShot("noExpectedTab" + expectedTabName + ".jpg");
                return false;
            }
            Wait.forElementToBeClickable(driver, driver.findElement(tabName));
            Click.element(driver, driver.findElement(tabName));
            Wait.seconds(2); // It is observed that particular tab is taking time to load
            Debugger.println("The " + expectedTabName + " is clicked");
            return true;
        } catch (Exception exp){
            Debugger.println("Exception from clickOnSpecifiedTab:" + exp);
            SeleniumLib.takeAScreenShot("noExpectedTab" + expectedTabName + ".jpg");
            return false;
        }

    }

    public boolean verifyTheColumnValuesUnderSpecifiedTab(String colName, String expValue) {
        try{
            if(!Wait.isElementDisplayed(driver,summaryTitle,30)){
                Debugger.println("Summary Title is not displayed");
                SeleniumLib.takeAScreenShot("noSummaryTitle.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(clinicalDqReportTableRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No Filter results found in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("noRowValue.jpg");
                return false;
            }
            int colIndex = seleniumLib.getColumnIndex(clinicalDqReportTableHead, colName);
            if(colIndex == -1){
                Debugger.println("Specified column "+colName+" not present in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("noColumn"+colName+".jpg");
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
                        SeleniumLib.takeAScreenShot("noCellValueUnder"+colName+".jpg");
                        return false;
                    }else {
                        if (!cellValue.contains(expValue)){
                            Debugger.println("Column: " + colName + " value, Expected: " + expValue + ", Actual: " + cellValue);
                            return true;
                        }
                    }
                }
            }return true;
        }catch (Exception exp){
            Debugger.println("Exception from verifyTheColumnValuesUnderSpecifiedTab:" + exp);
            SeleniumLib.takeAScreenShot("noRowValueFor"+colName+".jpg");
            return false;
        }
    }

    public boolean clickOnResetFiltersButton() {
        try{
            if(!Wait.isElementDisplayed(driver, resetFiltersButton,30)){
                Debugger.println("ResetFiltersButton is not displayed");
                SeleniumLib.takeAScreenShot("noResetFiltersButton.jpg");
                return false;
            }
            Actions.clickElement(driver, resetFiltersButton);
            Debugger.println("Reset filters button is clicked");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:clickOnResetFiltersButton: " + exp);
            SeleniumLib.takeAScreenShot("noResetFiltersButton.jpg");
            return false;
        }
    }

    public boolean navigateToClinicalDataQualityPage() {
        try {
            if (!Wait.isElementDisplayed(driver, clinicalDqTab, 30)){
                Debugger.println("clinicalDqTab is not displayed");
                SeleniumLib.takeAScreenShot("noClinicalDqTabIsPresent.jpg");
                return false;
            }
            Actions.clickElement(driver, clinicalDqTab);
            Debugger.println("Clinical Dq report tab is selected");
            return true;
        } catch (Exception exp){
            Debugger.println("Exception in MIPortalClinicalDataQuality:navigateToClinicalDataQualityPage: " + exp);
            SeleniumLib.takeAScreenShot("noClinicalDqTabIsPresent.jpg");
            return false;
        }
    }
}



