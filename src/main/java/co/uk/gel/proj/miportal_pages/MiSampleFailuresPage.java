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
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class MiSampleFailuresPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public MiSampleFailuresPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//h3[text()='Sample Failures Report']")
    public WebElement sampleFailuresHeader;

    @FindBy(xpath = "(//a[text()='Report Guidance'])[2]")
    public WebElement reportGuidanceLink;

    @FindBy(xpath = "//button[@data-id='sample_failures-filter_glh']")
    public WebElement sampleFailuresGlhDd;

    @FindBy(xpath = "//button[@data-id='sample_failures-filter_programme']")
    public WebElement sampleFailuresProgrammeDd;

    @FindBy(xpath = "//button[@data-id='sample_failures-filter_failure_type']")
    public WebElement sampleFailuresFailureTypeDd;

    @FindBy(xpath = "//select[@id='sample_failures-filter_glh']")
    public WebElement sampleFailuresGlhDropdown;

    @FindBy(xpath = "(//div[@class='btn-group btn-group-sm btn-block']//button[text()][1])[2]")
    public WebElement sampleFailuresGlhSelectAll;

    @FindBy(xpath = "(//div[@class='btn-group btn-group-sm btn-block']//button[text()][2])[2]")
    public WebElement sampleFailuresGlhDeselectAll;

    @FindBy(xpath = "(//div[@class='btn-group btn-group-sm btn-block']//button[text()][1])[3]")
    public WebElement sampleFailuresProgrammeSelectAll;

    @FindBy(xpath = "(//div[@class='btn-group btn-group-sm btn-block']//button[text()][2])[3]")
    public WebElement sampleFailuresProgrammeDeselectAll;

    @FindBy(xpath = "(//div[@class='btn-group btn-group-sm btn-block']//button[text()][1])[4]")
    public WebElement sampleFailuresFailureTypeSelectAll;

    @FindBy(xpath = "(//div[@class='btn-group btn-group-sm btn-block']//button[text()][2])[4]")
    public WebElement sampleFailuresFailureTypeDeselectAll;

    @FindBy(xpath = "//select[@id='sample_failures-filter_programme']")
    public WebElement sampleFailuresProgrammeDropdown;

    @FindBy(xpath = "//select[@id='sample_failures-filter_failure_type']")
    public WebElement sampleFailuresFailureTypeDropdown;

    @FindBy(xpath = "//span[text()='Hide replaced samples']/../input")
    public WebElement hideReplacedSamplesCheckBox;

    @FindBy(xpath = "//span[text()='Hide closed failures']/../input")
    public WebElement hideClosedFailuresCheckBox;

    @FindBy(xpath = "//span[text()='Over 14 days old only']/../input")
    public WebElement overDaysOldOnlyCheckBox;

    @FindBy(xpath = "//button[@id='sample_failures-apply_filters']")
    public WebElement applyFiltersButton;

    String link="(//a[text()='dummyLink'])[2]";

    @FindBy(xpath = "//div[@id='sample_failures-timestamp']")
    public WebElement lastUpdatedTimeStamp;

    @FindBy(xpath = "//div[@id='DataTables_Table_0_info']")
    public WebElement showingEntriesInfo;

    @FindBy(xpath = "//select[contains(@name,'DataTables_Table')]")
    public WebElement paginationEntryOptions;

    @FindBy(xpath = "//div[contains(@class,'scrollHeadInner')]/table/thead/tr")
    public WebElement displayedResultTableHeader;

    @FindBy(xpath = "//div[contains(@class,'active')]//a[contains(string(),'Download Data')]")
    public WebElement downloadDataButton;

    By resultTableHeader = By.xpath("//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th");

    @FindBy(xpath = "//div[contains(@class,'scrollHeadInner')]/table/thead/tr/th")
    public List<WebElement> resultDataTableHeader;

    @FindBy(xpath = "//div[@role='listbox']//a[@role='option'][@aria-selected='true']//span[@class='glyphicon glyphicon-ok check-mark']")
    public List<WebElement> allValueSelections;

    @FindBy(xpath = "//div[@role='listbox']//a[@role='option'][@aria-selected='false']//span[@class='glyphicon glyphicon-ok check-mark']")
    public List<WebElement> allValueDeselections;

    public boolean verifySampleFailuresReportHeader(String expValue) {
        try {
            Wait.forElementToBeDisplayed(driver, sampleFailuresHeader);
            String sampleFailuresHeaderText = sampleFailuresHeader.getText();
            if (!expValue.equalsIgnoreCase(sampleFailuresHeaderText)) {
                Debugger.println("Expected header text is: " +expValue+ " Actual header text is: " +sampleFailuresHeaderText);
                SeleniumLib.takeAScreenShot("sampleFailuresReportHeaderIsNotMatched.jpg");
                return false;
            }
//            Debugger.println("The " +sampleFailuresHeaderText+ " header is visible");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from MIPortalSampleFailures:verifySampleFailuresReportHeader :  " + exp);
            SeleniumLib.takeAScreenShot("sampleFailuresReportHeaderIsNotPresent.jpg");
            return false;
        }
    }

    public boolean verifyReportGuidance(String expValue) {
        try {
            Wait.forElementToBeDisplayed(driver, reportGuidanceLink);
            String reportGuidanceText = reportGuidanceLink.getText();
            if (!expValue.equalsIgnoreCase(reportGuidanceText)) {
                Debugger.println("Expected is: " +expValue + " but Actual value is: " +reportGuidanceText);
                SeleniumLib.takeAScreenShot("reportGuidanceLinkNotPresent.jpg");
                return false;
            }
//            Debugger.println("The " +reportGuidanceText+ " link is present");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from MIPortalSampleFailures:verifyReportGuidance:  " + exp);
            SeleniumLib.takeAScreenShot("reportGuidanceLinkNotPresent.jpg");
            return false;
        }
    }

    public boolean clickOnSampleFailuresGLHDd() {
        try{
            if(!Wait.isElementDisplayed(driver, sampleFailuresGlhDd,30)){
                Debugger.println("Glh dropdown is not displayed");
                SeleniumLib.takeAScreenShot("noGlhDropdown.jpg");
                return false;
            }
            Wait.seconds(5);  //To load the dropdown elements
            Actions.clickElement(driver, sampleFailuresGlhDd);
//            Debugger.println("Glh dropdown is clicked");
            return true;
        }catch (Exception exp){
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresGLHDd: "+ exp);
            SeleniumLib.takeAScreenShot("noGlhDropdown.jpg");
            return false;
        }
    }

    public boolean selectSampleFailuresFilterGlh(String value) {
        try {
            return seleniumLib.selectFromListByText(sampleFailuresGlhDropdown, value);
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:selectSampleFailuresFilterGlh: " + exp);
            SeleniumLib.takeAScreenShot("noSampleFailuresGlhFilter" + value + ".jpg");
            return false;
        }
    }

    public boolean selectValueInGlhDropDown(String value) {
        try {
            Wait.seconds(2);
            if(!seleniumLib.selectFromListByText(sampleFailuresGlhDropdown,value)){
                Debugger.println("The " + value + " is not present");
                return seleniumLib.selectFromListByText(sampleFailuresGlhDropdown,value);
            }
//            Debugger.println("The " + value + " is selected");
            return true;
        }
        catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:selectValueInGlhDropDown: "+ exp);
            SeleniumLib.takeAScreenShot("noGlhDropdown" + value + ".jpg");
            return false;
        }
    }

    public boolean clickOnSampleFailuresGLHSelectAllButton() {
        try {
            if(!Wait.isElementDisplayed(driver, sampleFailuresGlhSelectAll, 30)){
                Debugger.println("SelectAllButton is not displayed in GLH drop-down");
                SeleniumLib.takeAScreenShot("noSelectAllButtonIsDisplayed.jpg");
                return false;
            }
            Actions.clickElement(driver, sampleFailuresGlhSelectAll);
//            Debugger.println("Selected all the Glh values");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresGLHSelectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("noSelectAllButtonIsDisplayed.jpg");
            return false;
        }
    }

// After clicking on Select all button all the values should Select
    public boolean sampleFailuresAllValueSelect() {
        //Check mark should be present
        if (allValueSelections.size() < 1) {
            Debugger.println("All values are shown as deselected, but expected to be selected.");
            SeleniumLib.takeAScreenShot("noValueSelected.jpg");
            return false;
        }
        return true;
    }

    public boolean clickOnSampleFailuresGLHDeselectAllButton() {
        try{
            if(!Wait.isElementDisplayed(driver, sampleFailuresGlhDeselectAll,30)){
                Debugger.println("deselectAllButton is not displayed in GLH drop-down");
                SeleniumLib.takeAScreenShot("noDeselectAllButtonIsDisplayed.jpg");
                return false;
            }
            Actions.clickElement(driver, sampleFailuresGlhDeselectAll);
//            Debugger.println("Deselected all the Glh values");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresGLHDeselectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("noDeselectAllButtonIsDisplayed.jpg");
            return false;
        }
    }


// After clicking on Deselect all button all the values should Deselect
    public boolean sampleFailuresAllValueDeselect() {
        // Check mark should not be present
        Wait.seconds(2);
        if (allValueDeselections.size() < 1) {
            Debugger.println("All values are shown as selected, but expected to be deselected.");
            SeleniumLib.takeAScreenShot("noValueDeselected.jpg");
            return false;
        }
        return true;
    }

    public boolean clickOnSampleFailuresProgrammeDd() {
        try{
            if(!Wait.isElementDisplayed(driver, sampleFailuresProgrammeDd,30)){
                Debugger.println("Programme dropdown is not displayed");
                SeleniumLib.takeAScreenShot("noProgrammeDropdown.jpg");
                return false;
            }
            Wait.seconds(3); //To load the dropdown elements
            Actions.clickElement(driver, sampleFailuresProgrammeDd);
//            Debugger.println("Programme dropdown is clicked");
            return true;
        }catch (Exception exp){
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresProgrammeDd: "+ exp);
            SeleniumLib.takeAScreenShot("noProgrammeDropdown.jpg");
            return false;
        }
    }

    public boolean clickOnSampleFailuresProgrammeSelectAllButton() {
        try {
            if(!Wait.isElementDisplayed(driver, sampleFailuresProgrammeSelectAll, 30)){
                Debugger.println("SelectAllButton is not displayed in Programme drop-down");
                SeleniumLib.takeAScreenShot("noSelectAllButtonIsDisplayed.jpg");
                return false;
            }
            Actions.clickElement(driver, sampleFailuresProgrammeSelectAll);
//            Debugger.println("Selected all the values in Programme drop-down");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresProgrammeSelectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("noSelectAllButtonIsDisplayed.jpg");
            return false;
        }
    }

    public boolean clickOnSampleFailuresProgrammeDeselectAllButton() {
        try{
            if(!Wait.isElementDisplayed(driver, sampleFailuresProgrammeDeselectAll,30)){
                Debugger.println("deselectAllButton is not displayed in Programme drop-down");
                SeleniumLib.takeAScreenShot("noDeselectAllButtonIsDisplayed.jpg");
                return false;
            }
            Actions.clickElement(driver, sampleFailuresProgrammeDeselectAll);
//            Debugger.println("Deselected all the values in Programme drop-down");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresProgrammeDeselectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("noDeselectAllButtonIsDisplayed.jpg");
            return false;
        }
    }

    public boolean selectValueInProgrammeDropDown(String value) {
        try {
            Wait.seconds(2);
            if (!seleniumLib.selectFromListByText(sampleFailuresProgrammeDropdown, value)) {
                Debugger.println("The " + value + " is not present");
                return seleniumLib.selectFromListByText(sampleFailuresProgrammeDropdown, value);
            }
//            Debugger.println("The " + value + " is selected");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:selectValueInProgrammeDropDown: " + exp);
            SeleniumLib.takeAScreenShot("notFoundProgrammeDropdown" + value + ".jpg");
            return false;
        }
    }

    public boolean clickOnSampleFailuresFailureTypeDd() {
        try{
            if(!Wait.isElementDisplayed(driver, sampleFailuresFailureTypeDd,30)){
                Debugger.println("FailureType dropdown is not displayed");
                SeleniumLib.takeAScreenShot("noFailureTypeDropdown.jpg");
                return false;
            }
            Wait.seconds(5);//To load the dropdown elements
            Actions.clickElement(driver, sampleFailuresFailureTypeDd);
//            Debugger.println("Failure Type dropdown is clicked");
            return true;
        }catch (Exception exp){
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresFailureTypeDd: "+ exp);
            SeleniumLib.takeAScreenShot("noFailureTypeDropdown.jpg");
            return false;
        }
    }

    public boolean clickOnSampleFailuresFailureTypeSelectAllButton() {
        try {
            if(!Wait.isElementDisplayed(driver, sampleFailuresFailureTypeSelectAll, 30)){
                Debugger.println("SelectAllButton is not displayed in FailureType drop-down");
                SeleniumLib.takeAScreenShot("noSelectAllButtonIsDisplayed.jpg");
                return false;
            }
            Actions.clickElement(driver, sampleFailuresFailureTypeSelectAll);
//            Debugger.println("Selected all the values in Failure Type drop-down");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresFailureTypeSelectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("noSelectAllButtonIsDisplayed.jpg");
            return false;
        }
    }

    public boolean clickOnSampleFailuresFailureTypeDeselectAllButton() {
        try{
            if(!Wait.isElementDisplayed(driver, sampleFailuresFailureTypeDeselectAll,30)){
                Debugger.println("deselectAllButton is not displayed in FailureType drop-down");
                SeleniumLib.takeAScreenShot("noDeselectAllButtonIsDisplayed.jpg");
                return false;
            }
            Actions.clickElement(driver, sampleFailuresFailureTypeDeselectAll);
//            Debugger.println("Deselected all the values in Failure Type drop-down");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnSampleFailuresFailureTypeDeselectAllButton: "+ exp);
            SeleniumLib.takeAScreenShot("noDeselectAllButtonIsDisplayed.jpg");
            return false;
        }
    }

    public boolean selectValueInFailureTypeDropDown(String value) {
        try {
            Wait.seconds(2);
            if (!seleniumLib.selectFromListByText(sampleFailuresFailureTypeDropdown, value)) {
                Debugger.println("The " + value + " is not present");
                return seleniumLib.selectFromListByText(sampleFailuresFailureTypeDropdown, value);
            }
//            Debugger.println("The " + value + " is selected");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:selectValueInFailureTypeDropDown: " + exp);
            SeleniumLib.takeAScreenShot("notFoundProgrammeDropdown" + value + ".jpg");
            return false;
        }
    }

    public boolean clickOnHideReplacedSamplesCheckBoxToDeselect() {
        try {
            if (!Wait.isElementDisplayed(driver, hideReplacedSamplesCheckBox, 10)) {
                Debugger.println("Hide Replaced Samples Check Box option is not displayed.");
                SeleniumLib.takeAScreenShot("noHideReplacedSamplesCheckBox.jpg");
                return false;
                }
            seleniumLib.clickOnWebElement(hideReplacedSamplesCheckBox);
//            Debugger.println("Hide Replaced Samples CheckBox is clicked");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnHideReplacedSamplesCheckBoxToDeselect: " + exp);
            SeleniumLib.takeAScreenShot("noHideReplacedSamplesCheckBox.jpg");
            return false;
        }
    }


    public boolean clickOnHideReplacedSamplesCheckBoxToSelect() {
        try {
            if (!Wait.isElementDisplayed(driver, hideReplacedSamplesCheckBox, 10)) {
                Debugger.println("Hide Replaced Samples Check Box option is not displayed.");
                SeleniumLib.takeAScreenShot("hideReplacedSamplesCheckBoxIsNotPresent.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(hideReplacedSamplesCheckBox);
//            Debugger.println("Hide Replaced Samples CheckBox is clicked");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnHideReplacedSamplesCheckBoxToSelect: " + exp);
            SeleniumLib.takeAScreenShot("noHideReplacedSamplesCheckBox.jpg");
            return false;
        }
    }

    public boolean clickOnOverDaysOldOnlyCheckBoxToDeselect() {
        try {
            if (!Wait.isElementDisplayed(driver, overDaysOldOnlyCheckBox, 10)) {
                Debugger.println("Hide Replaced Samples Check Box option is not displayed.");
                SeleniumLib.takeAScreenShot("overDaysOldOnlyCheckBoxIsNotPresent.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(overDaysOldOnlyCheckBox);
//            Debugger.println("Over Days Old Only CheckBox is clicked");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnHideReplacedSamplesCheckBoxToDeselect: " + exp);
            SeleniumLib.takeAScreenShot("noOverDaysOldOnlyCheckBox.jpg");
            return false;
        }
    }

    public boolean clickOnApplyFiltersButtonOnSampleFailures() {
        try{
            if(!Wait.isElementDisplayed(driver, applyFiltersButton, 30)){
                Debugger.println("ApplyFiltersButton is not displayed");
                SeleniumLib.takeAScreenShot("noApplyFiltersButton.jpg");
                return false;
            }
            Actions.clickElement(driver, applyFiltersButton);
//            Debugger.println("Apply filters button clicked");
            Wait.seconds(10); //This wait is for to load the data table
            return true;
        } catch (Exception exp){
            Debugger.println("Exception in MIPortalSampleFailures:clickOnApplyFiltersButton: "+ exp);
            SeleniumLib.takeAScreenShot("noApplyFiltersButton.jpg");
            return false;
        }
    }

    public boolean openReportGuidanceLink(String linkName, String linkURL) {
        try{
            String linkPath=link.replace("dummyLink",linkName);
            WebElement linkButton = driver.findElement(By.xpath(linkPath));
            if(!Wait.isElementDisplayed(driver,linkButton,10)){
                Debugger.println("The link "+linkName+" is not present.");
                SeleniumLib.takeAScreenShot("LinkNotPresent.jpg");
                return false;
            }
            linkButton.click();
            Wait.seconds(3);
            seleniumLib.ChangeWindow();
            String currentURL= driver.getCurrentUrl();
            if(!currentURL.contains(linkURL)){
                Debugger.println("The current url is: " +currentURL+ ", But expected to contain- " +linkURL);
                SeleniumLib.takeAScreenShot("OpenReportLinkError.jpg");
                return false;
            }
            SeleniumLib.closeCurrentWindow();
            return true;
        }catch (Exception exp){
            Debugger.println("Exception from MIPortalSampleFailures:openReportGuidanceLink:"+exp);
            SeleniumLib.takeAScreenShot("OpenReportGuidanceError.jpg");
            return false;
        }
    }

    public boolean verifyTheElementsPresentInSampleFailuresResultTable() {
        try{
            if(!Wait.isElementDisplayed(driver, lastUpdatedTimeStamp,30)){
                Debugger.println("Last Updated TimeStamp is not displayed");
                SeleniumLib.takeAScreenShot("noLastUpdatedTimeStamp.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver, showingEntriesInfo,30)){
                Debugger.println("Showing Entries Info is not displayed");
                SeleniumLib.takeAScreenShot("noShowingEntriesInfo.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver, paginationEntryOptions,30)){
                Debugger.println("Pagination Entry is not displayed");
                SeleniumLib.takeAScreenShot("noPaginationEntryOptions.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver, displayedResultTableHeader,30)){
                Debugger.println("Result Table Header is not displayed");
                SeleniumLib.takeAScreenShot("noResultTableHeader.jpg");
                return false;
            }
            if(!Wait.isElementDisplayed(driver, downloadDataButton,30)){
                Debugger.println("Download Data Button is not displayed");
                SeleniumLib.takeAScreenShot("noDownloadDataButton.jpg");
                return false;
            }
            List<WebElement> expectedElements = new ArrayList<>();
            expectedElements.add(lastUpdatedTimeStamp);
            expectedElements.add(showingEntriesInfo);
            expectedElements.add(paginationEntryOptions);
            expectedElements.add(displayedResultTableHeader);
            expectedElements.add(downloadDataButton);
            for (int i=0; i < expectedElements.size(); i++){
                if (!seleniumLib.isElementPresent(expectedElements.get(i))){
                    Debugger.println("Result table is not displayed: "+expectedElements.get(i));
                    SeleniumLib.takeAScreenShot("noResultTableFound.jpg");
                    return false;
                }
            } return true;
        } catch (Exception exp){
            Debugger.println("Exception from MIPortalSampleFailures:verifyTheElementsPresentInSampleFailuresResultTable" + exp);
            SeleniumLib.takeAScreenShot("resultTableNotFound.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfTableHeaders(List<List<String>> tableHeaders) {
        try {
            if (!Wait.isElementDisplayed(driver, displayedResultTableHeader, 10)) {
                Debugger.println("No ResultTableHeader element displayed.");
                SeleniumLib.takeAScreenShot("noResultTableHeader.jpg");
                return false;
            }
            List<String> actualHeaders = seleniumLib.scrollTableAndGetHeaders(resultTableHeader);
            String headerValue = " ";
            boolean flag = false;
            for (int i = 0; i < actualHeaders.size() ; i++) {
                if (!actualHeaders.get(i).equalsIgnoreCase(tableHeaders.get(i).get(0))) {
                    headerValue = tableHeaders.get(i).get(0);
                    flag = true;
                    break;
                }
            }
            if (flag == true) {
                Debugger.println("The header value is not matched and it is: " +headerValue);
                return false;
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from MIPortalSampleFailures:verifyThePresenceOfTableHeaders:" + exp);
            SeleniumLib.takeAScreenShot("noTableHeaderPresent.jpg");
            return false;
        }
    }

    public boolean downloadSampleFailuresReport(String Sample_Failures_Report) {
        try {
            //Delete if File already present
            TestUtils.deleteIfFilePresent(Sample_Failures_Report, "");
            if (!Wait.isElementDisplayed(driver, downloadDataButton, 20)) {
                Debugger.println("The Sample Failures Data download option is not displayed");
                SeleniumLib.takeAScreenShot("noDownloadDataButton.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(downloadDataButton);
            Wait.seconds(15); //Wait for 15 seconds to ensure file got downloaded, large file taking time to download
            Debugger.println("Form: " + Sample_Failures_Report + ", downloaded from section: " + Sample_Failures_Report);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from MIPortalSampleFailures:downloadSampleFailuresReport: " + exp);
            SeleniumLib.takeAScreenShot("noDownloadData.jpg");
            return false;
        }
    }

    public boolean clickOnHideClosedFailuresCheckBoxToDeselect() {
        try {
            if (!Wait.isElementDisplayed(driver, hideClosedFailuresCheckBox, 10)) {
                Debugger.println("Hide Closed Failures Check Box option is not displayed.");
                SeleniumLib.takeAScreenShot("noClosedFailuresCheckBox.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(hideClosedFailuresCheckBox);
//            Debugger.println("Hide Closed Failures CheckBox is clicked");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnHideClosedFailuresCheckBoxToDeselect: " + exp);
            SeleniumLib.takeAScreenShot("noClosedFailuresCheckBox.jpg");
            return false;
        }
    }

    public boolean clickOnHideClosedFailuresCheckBoxToSelect() {
        try {
            if (!Wait.isElementDisplayed(driver, hideClosedFailuresCheckBox, 10)) {
                Debugger.println("Hide Closed Failures Check Box option is not displayed.");
                SeleniumLib.takeAScreenShot("hideClosedFailuresCheckBoxIsNotPresent.jpg");
                return false;
            }
            seleniumLib.clickOnWebElement(hideClosedFailuresCheckBox);
//            Debugger.println("Hide Closed Failures CheckBox is clicked");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in MIPortalSampleFailures:clickOnHideClosedFailuresCheckBoxToSelect: " + exp);
            SeleniumLib.takeAScreenShot("noHideClosedFailuresCheckBox.jpg");
            return false;
        }
    }
}
