package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.miportal_pages.MiClinicalDataQualityPage;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
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
        miClinicalDataQualityPage.clickOnClinicalDqFilterGlhDropdown();
        boolean testResult = false;
        List<List<String>> expectedDropDownValues = dataTable.asLists();
        for (int i=0; i < expectedDropDownValues.size(); i++){
            testResult = miClinicalDataQualityPage.selectClinicalDqFilterGlh(expectedDropDownValues.get(i).get(0));
            Assert.assertTrue(testResult);
        }
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
        miClinicalDataQualityPage.ClickOnOrderingEntityDd();
    }

    @And("the user click on Deselect All button by default all the ordering entities should select")
    public void theUserClickOnDeselectAllButtonByDefaultAllTheOrderingEntitiesShouldSelect() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.ClickOnDeselectAllButton();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Select All button")
    public void theUserClickOnSelectAllButton() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.ClickOnSelectAllButton();
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
       testResult = miClinicalDataQualityPage.VerifyTheElementsPresentInApplyFiltersSection();
       Assert.assertTrue(testResult);
    }

    @Then("the user able to see all the ordering entities should deselect")
    public void theUserAbleToSeeAllTheOrderingEntitiesShouldDeselect() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.OrderingEntitiesDeselect();
        Assert.assertTrue(testResult);
    }

    @Then("the user able to see all the ordering entities should select")
    public void theUserAbleToSeeAllTheOrderingEntitiesShouldSelect() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.OrderingEntitiesSelect();
        Assert.assertTrue(testResult);
    }

    @And("the user sees the Clinical DQ Report table column (.*) is displayed with data (.*)")
    public void theUserSeesTheClinicalDQReportTableIsDisplayedWithDataNonEmptyData(String ColName, String ColValue) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyTheColumnValuesInClinicalDqReportTable(ColName, ColValue);
        Assert.assertTrue(testResult);
    }

    @And("the user click on Full Output tab")
    public void theUserClickOnFullOutputTab() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnFullOptputTab();
        Assert.assertTrue(testResult);
    }

    @And("the user click on Streamline Output tab")
    public void theUserClickOnStreamlineOutputTab() {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.clickOnStreamlineOutputTab();
        Assert.assertTrue(testResult);
    }

    @And("the user sees Clinical DQ Report table column (.*) is displayed with data (.*)")
    public void theUserSeesClinicalDQReportTableIsDisplayedWithDataNonEmptyData(String ColName, String ColValue) {
        boolean testResult = false;
        testResult = miClinicalDataQualityPage.verifyTheColumnValuesUnderFullOutputTab(ColName, ColValue);
        Assert.assertTrue(testResult);
    }
}
