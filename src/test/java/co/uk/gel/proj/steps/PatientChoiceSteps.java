package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class PatientChoiceSteps extends Pages {
    public PatientChoiceSteps(SeleniumDriver driver) {
        super(driver);
    }
    @When("the user selects the proband")
    public void theUserSelectsTheProband() {
        patientChoicePage.selectMember(0);
    }

    @And("the user answers the patient choice questions with agreeing to testing")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTesting() {
        patientChoicePage.selectPatientChoiceCategory();
        patientChoicePage.selectTestType();
        patientChoicePage.enterRecordedByDetails();
        patientChoicePage.selectChoicesWithPatientChoiceNotRequired();
        patientChoicePage.submitPatientChoiceWithoutSignature();
    }

    @And("the user submits the patient choice with signature")
    public void theUserSubmitsThePatientChoiceWithSignature() {
        patientChoicePage.submitPatientChoiceWithSignature();
    }

    @Then("the Patient Choice landing page is updated to {string} for the proband")
    public void thePatientChoiceLandingPageIsUpdatedToForTheProband(String expectedStatusInfo) {
        Assert.assertTrue(patientChoicePage.statusUpdatedCorrectly(expectedStatusInfo, 0));
    }

    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYes() {
        patientChoicePage.selectPatientChoiceCategory();
        patientChoicePage.selectTestType();
        patientChoicePage.enterRecordedByDetails();
        patientChoicePage.selectChoicesWithAgreeingTesting();
        patientChoicePage.drawSignature();
    }
}
