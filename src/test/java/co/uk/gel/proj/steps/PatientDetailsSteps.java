package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;


public class PatientDetailsSteps extends Pages {

    PatientDetailsPage patientDetails;
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

    @And("the user clicks the Start a new Referral button")
    public void theUserClicksTheStartANewReferralButton() {
        patientDetailsPage.clickStartNewReferralButton();
    }
    @When("the user clicks the Start Referral button")
    public void theUserClicksTheStartReferralButton() {
        patientDetailsPage.clickStartReferralButton();
    }

    @And("the user clicks the Start Referral button to display the referral page")
    public void theUserClicksTheStartReferralButtonToDisplayTheReferralPage() {
        patientDetailsPage.clickStartNewReferralButton();
        referralPage.checkThatReferalWasSuccessfullyCreated();
    }

    @When("the user does not modify the existing information on the {string} form")
    public void theUserDoesNotModifyTheExistingInformationOnTheForm(String arg0) {
        //patientDetailsPage.TestTest() - part of e2e user journey. To be continued once e2e commences

    }

    @Then("the clinical indication ID missing banner is displayed")
    public void theClinicalIndicationIDMissingBannerIsDisplayed() {
        patientDetailsPage.clinicalIndicationIDMissingBannerIsDisplayed();
    }

    @And("the Start Referral button is disabled")
    public void theStartReferralButtonIsDisabled() {
        patientDetailsPage.startReferralButtonIsDisabled();
    }
}
