package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.miportal_pages.MiClinicalDataQualityPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.maven.wagon.Wagon;
import org.junit.Assert;

import java.util.List;

public class MiClinicalDataQualitySteps extends Pages {
    MiClinicalDataQualityPage miClinicalDataQualityPage = new MiClinicalDataQualityPage(driver);
    public MiClinicalDataQualitySteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user sees a header as Clinical Data Quality Report on {string} stage")
    public void theUserSeesAHeaderAsClinicalDataQualityReportOnStage(String exp_value) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyClinicalDataQualityReport(exp_value);
        Assert.assertTrue(testResult);

    }

    @And("the user sees a link (.*) under the Clinical Data Quality Report header")
    public void theUserSeesALinkUnderTheClinicalDataQualityReportHeader(String exp_value) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyReportGuidance(exp_value);
        Assert.assertTrue(testResult);

    }

    @Then("the user sees the below values in the GLH search column drop-down menu")
    public void theUserSeesTheBelowValuesInTheGLHSearchColumnDropDownMenu(DataTable dataTable) {
//        miClinicalDataQualityPage.clickOnClinicalDqFilterGlhDropdown();
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        Wait.seconds(3);
        for (int i=0; i < expectedDropDownValues.size(); i++){
            testResult = miClinicalDataQualityPage.selectClinicalDqFilterGlh(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
         }
        Debugger.println("The dropdown values are " +expectedDropDownValues);
    }

    @And("the user selects (.*) as the Clinical Dq Filter Glh drop-down menu")
    public void theUserSelectsspecifiedvalueAsTheClinicalDqFilterGlhDropDownMenu(String value) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.selectValueInGlhDropDown(value);
        Assert.assertTrue(testResult);
    }

    @And("the user selects on ordering entity drop-down")
    public void theUserSelectsOnOrderingEntityDropDown() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnOrderingEntityDd();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Deselect All button by default all the ordering entities should select")
    public void theUserClickOnDeselectAllButtonByDefaultAllTheOrderingEntitiesShouldSelect() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnDeselectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Select All button")
    public void theUserClickOnSelectAllButton() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnSelectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Apply Filters button")
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

    @And("the user click on Reset Filters Button")
    public void theUserClickOnResetFiltersButton() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnResetFiltersButton();
        Assert.assertTrue(testResult);
    }

    @And("the user sees the column (.*) is displayed with data (.*)")
    public void theUserSeesTheColumnIsDisplayedWithDataNonEmptyData(String ColName, String ColValue) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyTheColumnValuesUnderSpecifiedTab(ColName, ColValue);
        Assert.assertTrue(testResult);
    }

    @And("the user click on Clinical Data Quality section select the filters (.*) and click on Add Filters button and verify the table loaded")
    public void theUserClickOnClinicalDataQualitySectionSelectTheFiltersAndClickOnAddFiltersButtonAndVerifyTheTableLoaded(String value) {
        Assert.assertTrue(miClinicalDataQualityPage.navigateToClinicalDataQualityPage());
        Assert.assertTrue(miClinicalDataQualityPage.selectValueInGlhDropDown(value));
        Assert.assertTrue(miClinicalDataQualityPage.clickOnApplyFiltersButton());
        Assert.assertTrue(miClinicalDataQualityPage.verifyTheElementsPresentInApplyFiltersSection());
        Debugger.println("The Elements Present In Apply Filters Section are verified");
    }

    @And("the user selects (.*) tab")
    public void theUserSelectsTab(String expectedTabName) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnSpecifiedTab(expectedTabName);
        Assert.assertTrue(testResult);
    }

    @When("the user click on {string} tab")
    public void theUserClickOnTab(String expValue) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnDqTab(expValue);
        Assert.assertTrue(testResult);
    }
}