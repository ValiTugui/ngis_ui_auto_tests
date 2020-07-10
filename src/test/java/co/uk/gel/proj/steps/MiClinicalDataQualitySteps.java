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
}
