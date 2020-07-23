package co.uk.gel.proj.miportal_pages;


import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.hu.De;
import org.apache.bcel.generic.ARETURN;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collection;
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
    public List<WebElement> clinical_dq_filter_glh;

    @FindBy(xpath = "(//div[@class='box']//span[@class='caret'])[1]")
    public WebElement clinical_dq_filter_dropdown;

    @FindBy(xpath = "//select[@id='clinical_dq-filter_glh']")
    public WebElement glhDropDown;

    @FindBy(xpath = "(//div[@class='box']//span[@class='caret'])[2]")
    public WebElement orderingEntityDropdown;

    @FindBy(xpath = "//div[@class='btn-group btn-group-sm btn-block']//button[text()][2]")
    public WebElement clickOnDeselectAllButton;

    @FindBy(xpath = "//div[@class='btn-group btn-group-sm btn-block']//button[text()][1]")
    public WebElement clickOnSelectAllButton;

    @FindBy(xpath = "//button[@id='clinical_dq-apply_filters']")
    public WebElement clickOnApplyFiltersButton;

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

    By clinicalDqReportTableHead = By.xpath("//div[@class='dataTables_scrollHeadInner']//table[@class='display dataTable no-footer']/thead/tr/th") ;
    String clinicalDqReportTableRows = "//div[@class='dataTables_scrollBody']//table[@class='display dataTable no-footer']/tbody/tr";

    By fullOutputTabHead = By.xpath("(//div[@class='dataTables_scrollHeadInner'])[2]//table[@class='display dataTable no-footer']/thead/tr/th") ;
    String fullOutputTabHeadRows = "(//div[@class='dataTables_scrollBody'])[2]//table[@class='display dataTable no-footer']/tbody/tr";

    By streamlineOutputTabColumn = By.xpath("(//div[@class='dataTables_scrollHeadInner'])[3]//table[@class='display dataTable no-footer']/thead/tr/th") ;
    String streamlineOutputTabRows = "(//div[@class='dataTables_scrollBody'])[3]//table[@class='display dataTable no-footer']/tbody/tr";

    public boolean verifyClinicalDataQualityReport(String exp_value) {
        try {
            By Clinical_dq_Report_Path = By.xpath("//h3[text()='" + exp_value + "']");
            SeleniumLib.waitForElementVisible(Clinical_dq_Report_Path);
            if (!seleniumLib.getText(Clinical_dq_Report_Path).equalsIgnoreCase(exp_value)) {
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

    public boolean verifyReportGuidance(String exp_value) {
        try {
            By Report_Guidance_Path = By.xpath("//a[text()='" + exp_value + "']");
            SeleniumLib.waitForElementVisible(Report_Guidance_Path);
            if (!seleniumLib.isElementClickable(Report_Guidance_Path)) {
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

    public void clickOnClinicalDqFilterGlhDropdown() {
        clinical_dq_filter_dropdown.click();
    }
    public boolean selectClinicalDqFilterGlh(String value) {
        try {
            Wait.seconds(8);
            return seleniumLib.selectFromListByText(glhDropDown, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalGlhSamples:selectDropDownSearchValue: " + exp);
            SeleniumLib.takeAScreenShot("GlhselectDropDownSearchValue.jpg");
            return false;
        }
    }

    public boolean selectValueInGlhDropDown(String value) {
        try {
            if(!seleniumLib.selectFromListByText(glhDropDown,value)){
                Wait.seconds(5);
                Debugger.println("The " + value + " is present");
                return seleniumLib.selectFromListByText(glhDropDown,value);
            }
            Debugger.println("The " + value + " is selected");
            Wait.seconds(3);
            return true;
        }
        catch (Exception exp) {
            Debugger.println("Exception in MIPortalClinicalDataQuality:selectGlhDropdownMenu: "+ exp);
            SeleniumLib.takeAScreenShot("GlhDropdownMenu.jpg");
            return false;
        }

    }

    public boolean clickOnOrderingEntityDd(){
        Wait.seconds(5);
        orderingEntityDropdown.click();
        return true;
    }

    public boolean clickOnDeselectAllButton() {
        Wait.seconds(3);
        clickOnDeselectAllButton.click();
        Debugger.println("Deselected all the ordering entities");
        return true;
    }

    public boolean clickOnSelectAllButton() {
        Wait.seconds(2);
        clickOnSelectAllButton.click();
        Debugger.println("Selected all the ordering entities");
        return true;
    }

    public boolean clickOnApplyFiltersButton() {
        Wait.seconds(5);
        clickOnApplyFiltersButton.click();
        Debugger.println("Apply filters button clicked");
        Wait.seconds(15);
        return true;
    }

    public boolean verifyTheElementsPresentInApplyFiltersSection() {
        try{
            Wait.seconds(8);
            if(!Wait.isElementDisplayed(driver,summaryTitle,20)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,fullOutputTitle,20)){
                Debugger.println("Full Output Title is displayed");
                SeleniumLib.takeAScreenShot("FullOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,streamlineOutputTitle,20)){
                Debugger.println("Streamline Output Title is displayed");
                SeleniumLib.takeAScreenShot("StreamlineOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,genomicIdentityOutputTitle,20)){
                Debugger.println("Genomic Identity Output Title is displayed");
                SeleniumLib.takeAScreenShot("GenomicIdentityOutputTitle.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,appendixAllRulesTitle,20)){
                Debugger.println("Appendix All RulesTitle is displayed");
                SeleniumLib.takeAScreenShot("AppendixAllRulesTitle.jpg");
                return false;
            }
            List<WebElement> expectedElements = new ArrayList<WebElement>();
            expectedElements.add(summaryTitle);
            expectedElements.add(fullOutputTitle);
            expectedElements.add(streamlineOutputTitle);
            expectedElements.add(genomicIdentityOutputTitle);
            expectedElements.add(appendixAllRulesTitle);
            for (int i=0; i < expectedElements.size(); i++){
                if (!seleniumLib.isElementPresent(expectedElements.get(i))){
                    Debugger.println("Summary result section element not displayed: "+expectedElements.get(i));
                    SeleniumLib.takeAScreenShot("SummaryResultSectionNotFound.jpg");
                    return false;
                }
            } return true;
        } catch (Exception exp){
            Debugger.println("Summary result section is not properly loaded" + exp);
            SeleniumLib.takeAScreenShot("SummaryResultSectionNotFound.jpg");
            return false;
        }
    }

    public boolean orderingEntitiesDeselect() {
        try {
            Wait.seconds(5);
            if(!checkMark.isDisplayed()){
                Debugger.println("Check mark is not present");
                SeleniumLib.takeAScreenShot("CheckMark_1.jpg");
                return true;
            }
            return false;
        } catch (Exception exp){
            Debugger.println("Check mark is present");
            return false;
        }
    }

    public boolean orderingEntitiesSelect() {
        try {
            Wait.seconds(5);
            if(checkMark.isDisplayed()){
                Debugger.println("Check mark is present");
                SeleniumLib.takeAScreenShot("CheckMark_2.jpg");
                return true;
            }
            return false;
        } catch (Exception exp){
            Debugger.println("Check mark is not present");
            return false;
        }
    }

    public boolean verifyTheColumnValuesInClinicalDqReportTable(String ColName, String expValue) {
        try{
            Wait.seconds(10);
            if(!Wait.isElementDisplayed(driver,summaryTitle,20)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(clinicalDqReportTableRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No Filter results found in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("Clinical_Dq_Report_1.jpg");
                return false;
            }
            Wait.seconds(5);
            int colIndex = seleniumLib.getColumnIndex(clinicalDqReportTableHead,ColName);
            if(colIndex == -1){
                Debugger.println("Specified column "+ColName+" not present in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("Clinical_Dq_Report_2.jpg");
                return false;
            }
            Wait.seconds(15);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue  = "";
            for(int i=0; i<noOfFilteredRows; i++){
                cellPath = By.xpath(clinicalDqReportTableRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")){
                    if (cellValue.isEmpty()){
                        Debugger.println("Column: "+ ColName +" value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("Clinical_Dq_Report_3");
                        return false;
                    }else {
                        if (!cellValue.contains(expValue)){
                            Debugger.println("Column: " + ColName + " value, Expected: " + expValue + ", Actual: " + cellValue);
                            SeleniumLib.takeAScreenShot("Clinical_Dq_Report_4");
                            return true;
                        }
                    }
                }
            }return true;
        }catch (Exception exp){
            Debugger.println("Exception from verifyColumnValueInClinicalDqReportResultTable:" + exp);
            SeleniumLib.takeAScreenShot("ClinicalDqReportException.jpg");
            return false;
        }
    }

    public boolean clickOnSpecifiedTab(String expectedTabName) {
        By tabName = null;
        try{
            tabName = By.xpath("//a[text()='"+ expectedTabName + "']");
            if(!Wait.isElementDisplayed(driver, driver.findElement(tabName), 5)){
                Debugger.println(expectedTabName + " is not displayed");
                SeleniumLib.takeAScreenShot("NoAddButton.jpg");
                return false;
            }
            Wait.forElementToBeClickable(driver, driver.findElement(tabName));
            Click.element(driver, driver.findElement(tabName));
            Wait.seconds(2);
            Debugger.println("The " + expectedTabName + " is clicked");
            return true;
        } catch (Exception exp){
            Debugger.println("Exception from Clicking on addButton:" + exp);
            SeleniumLib.takeAScreenShot("NoAddButton.jpg");
            return false;
        }

    }

    public boolean verifyTheColumnValuesUnderFullOutputTab(String ColName, String expValue) {
        try{
            Wait.seconds(10);
            if(!Wait.isElementDisplayed(driver,summaryTitle,20)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            int noOfFilteredRows = seleniumLib.getNoOfRows(fullOutputTabHeadRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No Filter results found in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("FullOutputTab_1.jpg");
                return false;
            }
            int colIndex = seleniumLib.getColumnIndex(fullOutputTabHead,ColName);
            if(colIndex == -1){
                Debugger.println("Specified column "+ColName+" not present in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("FullOutputTab_2.jpg");
                return false;
            }
            Wait.seconds(15);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue  = "";
            for(int i=0; i<noOfFilteredRows; i++){
                cellPath = By.xpath(fullOutputTabHeadRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")){
                    if (cellValue.isEmpty()){
                        Debugger.println("Column: "+ ColName +" value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("FullOutputTab_3");
                        return false;
                    }else {
                        if (!cellValue.contains(expValue)){
                            Debugger.println("Column: " + ColName + " value, Expected: " + expValue + ",Actual: " + cellValue);
                            SeleniumLib.takeAScreenShot("FullOutputTab_4");
                            return true;
                        }
                    }
                }
            }return true;
        }catch (Exception exp){
            Debugger.println("Exception from verifyColumnValueInClinicalDqReportResultTable:" + exp);
            SeleniumLib.takeAScreenShot("FullOutputTabException.jpg");
            return false;
        }
    }

    public boolean verifyTheColumnValuesUnderStreamlineOutputTab(String ColName, String expValue) {
        try{
            if(!Wait.isElementDisplayed(driver,summaryTitle,20)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            Wait.seconds(15);
            int noOfFilteredRows = seleniumLib.getNoOfRows(streamlineOutputTabRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No Filter results found in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("StreamlineOutputTab_1.jpg");
                return false;
            }
            Wait.seconds(10);
            int colIndex = seleniumLib.getColumnIndex(streamlineOutputTabColumn,ColName);
            if(colIndex == -1){
                Debugger.println("Specified column "+ColName+" not present in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("StreamlineOutputTab_2.jpg");
                return false;
            }
            Wait.seconds(15);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue  = "";
            for(int i=0; i<noOfFilteredRows; i++){
                cellPath = By.xpath(streamlineOutputTabRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")){
                    if (cellValue.isEmpty()){
                        Debugger.println("Column: "+ ColName +" value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("StreamlineOutputTab_3");
                        return false;
                    }else {
                        if (!cellValue.contains(expValue)){
                            Debugger.println("Column: " + ColName + " value, Expected: " + expValue + ",Actual: " + cellValue);
                            SeleniumLib.takeAScreenShot("StreamlineOutputTab_4");
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

    By genomicIdentityOutputTabColumn = By.xpath("(//div[@class='dataTables_scrollHeadInner'])[4]//table[@class='display dataTable no-footer']/thead/tr/th");
    String genomicIdentityOutputTabRows = "(//div[@class='dataTables_scrollBody'])[4]//table[@class='display dataTable no-footer']/tbody/tr";

    public boolean verifyTheColumnValuesUnderGenomicIdentityOutputTab(String ColName, String expValue) {
        try{
            if(!Wait.isElementDisplayed(driver,summaryTitle,20)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            Wait.seconds(15);
            int noOfFilteredRows = seleniumLib.getNoOfRows(genomicIdentityOutputTabRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No Filter results found in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("StreamlineOutputTab_1.jpg");
                return false;
            }
            Wait.seconds(10);
            int colIndex = seleniumLib.getColumnIndex(genomicIdentityOutputTabColumn,ColName);
            if(colIndex == -1){
                Debugger.println("Specified column "+ColName+" not present in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("StreamlineOutputTab_2.jpg");
                return false;
            }
            Wait.seconds(15);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue  = "";
            for(int i=0; i<noOfFilteredRows; i++){
                cellPath = By.xpath(genomicIdentityOutputTabRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")){
                    if (cellValue.isEmpty()){
                        Debugger.println("Column: "+ ColName +" value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("StreamlineOutputTab_3");
                        return false;
                    }else {
                        if (!cellValue.contains(expValue)){
                            Debugger.println("Column: " + ColName + " value, Expected: " + expValue + ",Actual: " + cellValue);
                            SeleniumLib.takeAScreenShot("StreamlineOutputTab_4");
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

    By appendixAllRulesTabColumn = By.xpath("(//div[@class='dataTables_scrollHeadInner'])[5]//table[@class='display dataTable no-footer']/thead/tr/th");
    String appendixAllRulesTabRows = "(//div[@class='dataTables_scrollBody'])[5]//table[@class='display dataTable no-footer']/tbody/tr";

    public boolean verifyTheColumnValuesUnderAppendixAllRulesTab(String ColName, String expValue) {
        try{
            if(!Wait.isElementDisplayed(driver,summaryTitle,20)){
                Debugger.println("Summary Title is displayed");
                SeleniumLib.takeAScreenShot("SummaryTitle.jpg");
                return false;
            }
            Wait.seconds(15);
            int noOfFilteredRows = seleniumLib.getNoOfRows(appendixAllRulesTabRows);
            if(noOfFilteredRows == 0){
                Debugger.println("No Filter results found in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("StreamlineOutputTab_1.jpg");
                return false;
            }
            Wait.seconds(10);
            int colIndex = seleniumLib.getColumnIndex(appendixAllRulesTabColumn,ColName);
            if(colIndex == -1){
                Debugger.println("Specified column "+ColName+" not present in Clinical Dq Report table");
                SeleniumLib.takeAScreenShot("StreamlineOutputTab_2.jpg");
                return false;
            }
            Wait.seconds(15);
            //Verify value in each column value as expected.
            By cellPath = null;
            String cellValue  = "";
            for(int i=0; i<noOfFilteredRows; i++){
                cellPath = By.xpath(appendixAllRulesTabRows+"["+(i+1)+"]/td["+colIndex+"]");
                cellValue = seleniumLib.getText(cellPath);
                if (expValue.equalsIgnoreCase("non-empty-data")){
                    if (cellValue.isEmpty()){
                        Debugger.println("Column: "+ ColName +" value supposed to be non-empty, but Actual is empty");
                        SeleniumLib.takeAScreenShot("StreamlineOutputTab_3");
                        return false;
                    }else {
                        if (!cellValue.contains(expValue)){
                            Debugger.println("Column: " + ColName + " value, Expected: " + expValue + ",Actual: " + cellValue);
                            SeleniumLib.takeAScreenShot("StreamlineOutputTab_4");
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
        Wait.seconds(10);
        resetFiltersButton.click();
        Debugger.println("Reset filters button was clicked");
        return true;
    }

    @FindBy(xpath = "//a[@data-value=\"clinical_dq_tab\"]")
    public WebElement clinicalDqTab;

    public boolean navigateToClinicalDataQualityPage() {
        Wait.seconds(5);
        clinicalDqTab.click();
        Debugger.println("Clinical Dq report button is is selected");
        return true;
    }
}




