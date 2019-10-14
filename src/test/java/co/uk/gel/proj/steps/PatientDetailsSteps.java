package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class PatientDetailsSteps extends Pages {


    public PatientDetailsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("^the Patient Details page is displayed$")
    public void thePatientDetailsPageIsDisplayed() {

        patientDetailsPage.patientDetailsPageIsDisplayed();
    }

    @When("the user create a new patient record by clicking the {string} link to fill all fields without NHS number and reason {string}")
    public void theUserCreateANewPatientRecordByClickingTheLinkToFillAllFieldsWithoutNHSNumberAndReason(String createANewPatientLink, String reason) {
        patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
        patientDetailsPage.newPatientPageIsDisplayed();
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reason);
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
        patientDetailsPage.patientIsCreated();
    }


    @When("the user clicks the Start Referral button")
    public void theUserClicksTheStartReferralButton() {
        patientDetailsPage.clickStartNewReferralButton();
    }

    @And("the user clicks the Start Referral button to display the referral page")
    public void theUserClicksTheStartReferralButtonToDisplayTheReferralPage() {
        patientDetailsPage.clickStartNewReferralButton();
        referralPage.checkThatReferalWasSuccessfullyCreated();
    }

    @When("the user does not modify the existing information on the {string} form")
    public void theUserDoesNotModifyTheExistingInformationOnTheForm(String arg0) {
        patientDetailsPage.TestTest();

    }
}
