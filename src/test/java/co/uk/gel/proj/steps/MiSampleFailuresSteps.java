package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.miportal_pages.MiSampleFailuresPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;

public class MiSampleFailuresSteps extends Pages {
    MiSampleFailuresPage miSampleFailuresPage = new MiSampleFailuresPage(driver);
    public MiSampleFailuresSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("the user sees a header as Sample Failures Report on {string} stage")
    public void theUserSeesAHeaderAsSampleFailuresReportOnStage(String exp_value) {
        boolean testResult = false;
        testResult = miSampleFailuresPage.verifySampleFailuresReportHeader(exp_value);
        Assert.assertTrue(testResult);
    }

    @And("the user sees a link (.*) under the Sample Failures Report header")
    public void theUserSeesALinkUnderTheClinicalDataQualityReportHeader(String exp_value) {
        boolean testResult = false;
        testResult = miSampleFailuresPage.verifyReportGuidance(exp_value);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the below values in the GLH search column drop-down menu on Sample Failures section")
    public void theUserSeesTheBelowValuesInTheGLHSearchColumnDropDownMenuOnSampleFailuresSection(DataTable dataTable) {
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(3);
        for (int i = 0; i < expectedDropDownValues.size(); i++) {
            testResult = miSampleFailuresPage.selectSampleFailuresFilterGlh(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
        //Debugger.println("The dropdown values are " + expectedDropDownValues);
    }

    @And("the user selects on Sample Failures GLH drop-down")
    public void theUserSelectsOnSampleFailuresGLHDropDown() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresGLHDd();
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the Sample Failures GLH drop-down menu")
    public void theUserSelectsGlh_nameAsTheSampleFailuresGLHDropDownMenu(String value) {
        boolean testResult = false;
        testResult = miSampleFailuresPage.selectValueInGlhDropDown(value);
        Assert.assertTrue(testResult);
    }

    @And("the user click on Select All button for Sample Failures GLH drop-down")
    public void theUserClickOnSelectAllButtonForSampleFailuresGLHDropDown() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresGLHSelectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Deselect All button for Sample Failures GLH drop-down")
    public void theUserClickOnDeselectAllButtonForSampleFailuresGLHDropDown() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresGLHDeselectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user selects on Sample Failures Programme drop-down")
    public void theUserSelectsOnSampleFailuresProgrammeDropDown() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresProgrammeDd();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Select All button from Sample failures Programme drop-down menu")
    public void theUserClickOnSelectAllButtonFromSampleFailuresProgrammeDropDownMenu() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresProgrammeSelectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Deselect All button from Sample failures Programme drop-down menu")
    public void theUserClickOnDeselectAllButtonFromSampleFailuresProgrammeDropDownMenu() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresProgrammeDeselectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the Sample failures Programme drop-down menu")
    public void theUserSelectsProgramme_nameAsTheSampleFailuresProgrammeDropDownMenu(String value) {
        boolean testResult = false;
        testResult = miSampleFailuresPage.selectValueInProgrammeDropDown(value);
        Assert.assertTrue(testResult);
    }

    @And("the user selects on Sample Failures Failure Type drop-down")
    public void theUserSelectsOnSampleFailuresFailureTypeDropDown() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresFailureTypeDd();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Select All button from Sample failures Failure Type drop-down menu")
    public void theUserClickOnSelectAllButtonFromSampleFailuresFailureTypeDropDownMenu() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresFailureTypeSelectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Deselect All button from Sample failures Failure Type drop-down menu")
    public void theUserClickOnDeselectAllButtonFromSampleFailuresFailureTypeDropDownMenu() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnSampleFailuresFailureTypeDeselectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user selects (.*) as the sample failures Failure Type drop-down menu")
    public void theUserSelectsFailure_TypeAsTheSampleFailuresFailureTypeDropDownMenu(String value) {
        boolean testResult = false;
        testResult = miSampleFailuresPage.selectValueInFailureTypeDropDown(value);
        Assert.assertTrue(testResult);
    }

    @And("the user click on Hide replaced samples check box to deselect by default it is selected")
    public void theUserClickOnHideReplacedSamplesCheckBoxToDeselectByDefaultItIsSelected() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnHideReplacedSamplesCheckBoxToDeselect();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Over 14 days old only check box to select")
    public void theUserClickOnOverDaysOldOnlyCheckBoxToSelect() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnOverDaysOldOnlyCheckBoxToDeselect();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Apply Filters button on Sample Failures Page")
    public void theUserClickOnApplyFiltersButtonOnSampleFailuresPage() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnApplyFiltersButtonOnSampleFailures();
        Assert.assertTrue(testResult);
    }

    @And("the user clicks the {string} link to open the document in a new tab having {string} in the url")
    public void theUserClicksTheLinkToOpenTheDocumentInANewTabHavingInTheUrl(String linkName, String linkURL) {
        boolean testResult = false;
        testResult = miSampleFailuresPage.openReportGuidanceLink(linkName,linkURL);
        Assert.assertTrue(testResult);
    }

    @Then("the filter results displays the elememts - PaginationEntry, Last Updated, Showing entries, Result Row Header and Download Data")
    public void theFilterResultsDisplaysTheElememtsPaginationEntryLastUpdatedShowingEntriesResultRowHeaderAndDownloadData() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.verifyTheElementsPresentInSampleFailuresResultTable();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Hide replaced samples check box to select")
    public void theUserClickOnHideReplacedSamplesCheckBoxToSelect() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnHideReplacedSamplesCheckBoxToSelect();
        Assert.assertTrue(testResult);
    }

    @And("the user sees all the table header values in the result table as follows")
    public void theUserSeesAllTheTableHeaderValuesInTheResultTableAsFollows(DataTable tableHeaders) {
        try {
            Wait.seconds(10);
            boolean testResult = false;
            List<List<String>> resultTableHeaders = tableHeaders.asLists();
            testResult = miSampleFailuresPage.verifyThePresenceOfTableHeaders(resultTableHeaders);
            Assert.assertTrue(testResult);
        } catch (Exception exp) {
            Debugger.println("Exception from table headers " + exp);
            Assert.assertFalse("MIPortalSampleFailures: Exception from table headers " + exp, true);
        }
    }

    @And("the user should be able to download the filtered Sample failures report")
    public void theUserShouldBeAbleToDownloadTheFilteredSampleFailuresReport() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.downloadSampleFailuresReport("wwm-eme-yne-lnn-now-lns-sow");
        Assert.assertTrue(testResult);
        testResult = TestUtils.isFilePresent("wwm-eme-yne-lnn-now-lns-sow", "");
        Assert.assertTrue(testResult);
    }

    @And("the user should see all the values should select")
    public void theUserShouldSeeAllTheValuesShouldSelect() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.sampleFailuresAllValueSelect();
        Assert.assertTrue(testResult);
    }

    @And("the user should see all the values should Deselect")
    public void theUserShouldSeeAllTheValuesShouldDeselect() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.sampleFailuresAllValueDeselect();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Hide closed failures check box to deselect by default it is selected")
    public void theUserClickOnHideClosedFailuresCheckBoxToDeselectByDefaultItIsSelected() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnHideClosedFailuresCheckBoxToDeselect();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Hide closed failures check box to select")
    public void theUserClickOnHideClosedFailuresCheckBoxToSelect() {
        boolean testResult = false;
        testResult = miSampleFailuresPage.clickOnHideClosedFailuresCheckBoxToSelect();
        Assert.assertTrue(testResult);
    }
}
