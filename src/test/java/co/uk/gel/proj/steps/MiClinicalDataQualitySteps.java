package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.miportal_pages.MiClinicalDataQualityPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class MiClinicalDataQualitySteps extends Pages {
    MiClinicalDataQualityPage miClinicalDataQualityPage = new MiClinicalDataQualityPage(driver);

    public MiClinicalDataQualitySteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user sees a header as Clinical Data Quality Report on {string} stage")
    public void theUserSeesAHeaderAsClinicalDataQualityReportOnStage(String exp_value) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyClinicalDataQualityReport(exp_value);
        Assert.assertTrue(testResult);

    }

    @Given("the user sees a link (.*) under the Clinical Data Quality Report header")
    public void theUserSeesALinkUnderTheClinicalDataQualityReportHeader(String exp_value) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyReportGuidance(exp_value);
        Assert.assertTrue(testResult);

    }

    @Then("the user sees the below values in the GLH search column drop-down menu")
    public void theUserSeesTheBelowValuesInTheGLHSearchColumnDropDownMenu(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(3);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miClinicalDataQualityPage.selectClinicalDqFilterGlh(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
        //Debugger.println("The dropdown values are " + expectedDropDownValues);
    }

    @When("the user selects (.*) as the Clinical Dq Filter Glh drop-down menu")
    public void theUserSelectsspecifiedvalueAsTheClinicalDqFilterGlhDropDownMenu(String value) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.selectValueInGlhDropDown(value);
        Assert.assertTrue(testResult);
    }

    @When("the user selects on ordering entity drop-down")
    public void theUserSelectsOnOrderingEntityDropDown() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnOrderingEntityDd();
        Assert.assertTrue(testResult);
    }

    @When("the user click on Deselect All button by default all the ordering entities should select")
    public void theUserClickOnDeselectAllButtonByDefaultAllTheOrderingEntitiesShouldSelect() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnDeselectAllButton();
        Assert.assertTrue(testResult);
    }

    @When("the user click on Select All button")
    public void theUserClickOnSelectAllButton() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnSelectAllButton();
        Assert.assertTrue(testResult);
    }

    @When("the user click on Apply Filters button")
    public void theUserClickOnApplyFiltersButton() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnApplyFiltersButton();
        Assert.assertTrue(testResult);
    }

    @Then("the filter results displays the elements - Summary, Full Output, Streamline Output, Genomic Identity Output, Appendix - all rules")
    public void theFilterResultsDisplaysTheElementsSummaryFullOutputStreamlineOutputGenomicIdentityOutputAppendixAllRules() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyTheElementsPresentInApplyFiltersSection();
        Assert.assertTrue(testResult);
    }

    @Then("the user able to see all the ordering entities should deselect")
    public void theUserAbleToSeeAllTheOrderingEntitiesShouldDeselect() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.orderingEntitiesDeselect();
        Assert.assertTrue(testResult);
    }

    @Then("the user able to see all the ordering entities should select")
    public void theUserAbleToSeeAllTheOrderingEntitiesShouldSelect() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.orderingEntitiesSelect();
        Assert.assertTrue(testResult);
    }

    @When("the user click on Reset Filters Button")
    public void theUserClickOnResetFiltersButton() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnResetFiltersButton();
        Assert.assertTrue(testResult);
    }

    @Then("the user sees the column (.*) is displayed with data (.*)")
    public void theUserSeesTheColumnIsDisplayedWithDataNonEmptyData(String ColName, String ColValue) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyTheColumnValuesUnderSpecifiedTab(ColName, ColValue);
        Assert.assertTrue(testResult);
    }

    @When("the user click on Clinical Data Quality section select the filters (.*) and click on Add Filters button and verify the table loaded")
    public void theUserClickOnClinicalDataQualitySectionSelectTheFiltersAndClickOnAddFiltersButtonAndVerifyTheTableLoaded(String value) {
        Assert.assertTrue(miClinicalDataQualityPage.navigateToClinicalDataQualityPage());
        Assert.assertTrue(miClinicalDataQualityPage.selectValueInGlhDropDown(value));
        Assert.assertTrue(miClinicalDataQualityPage.clickOnApplyFiltersButton());
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_MIPortal_ClinicalDataQuality.jpg");
        }
        Assert.assertTrue(miClinicalDataQualityPage.verifyTheElementsPresentInApplyFiltersSection());
        Debugger.println("The Elements Present In Apply Filters Section are verified");
    }

    @When("the user clicks the {string} link to open the document in a new tab having {string} url")
    public void theUserClicksTheReportGuidanceLinkToOpenTheDocumentInANewTabHavingUrl(String linkName,String linkURL) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.openReportGuidance(linkName,linkURL);
        Assert.assertTrue(testResult);
    }

    @Given("the user sees the column headers of each of the tables with data present in the tabs")
    public void theUserSeesTheColumnHeadersPresentInTheTabs(DataTable tabNameAndFields) {
        boolean testResult = false;
        List<List<String>> data = tabNameAndFields.cells();
        Debugger.println("The number of data tabs to be checked:" + data.size());
        for (int i = 0; i < data.size(); i++) {
            String tabName = data.get(i).get(0);
            String headerValues = data.get(i).get(1);
            String dataPresence = data.get(i).get(2);
            testResult = miClinicalDataQualityPage.verifyDQTabNamesAndColumnHeaders(tabName, headerValues,dataPresence);
            Assert.assertTrue(testResult);
        }
    }

    @Given("the user selects the tab {string}")
    public void theUserSelectsTheTab(String tabName) {
        boolean testResult=false;
        testResult=miClinicalDataQualityPage.clickOnSpecifiedTab(tabName);
        Assert.assertTrue(testResult);
    }

    @Given("the user clicks the {string} link in the {string} tab which opens {string} url")
    public void theUserClicksTheLinkInTheTabWhichOpensUrl(String linkName, String tabName, String linkUrl) {
        boolean testResult=false;
        testResult=miClinicalDataQualityPage.openTestOrderLink(linkName,tabName,linkUrl);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to download the filtered DQ report")
    public void theUserShouldBeAbleToDownloadTheFilteredDQReport() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.downloadDqCSVFile("Data_quality_report");
        Assert.assertTrue(testResult);
        testResult = TestUtils.isFilePresent("Data_quality_report","");
        Assert.assertTrue(testResult);
    }

    @Then("the last updated date is displayed in the {string} format")

    public void theLastUpdatedDateIsDisplayedInTheFormat(String dateFormat){
        Wait.seconds(5);
        boolean testResult = miClinicalDataQualityPage.verifyDQDateFormat(dateFormat);
        Assert.assertTrue(testResult);
    }
}//end