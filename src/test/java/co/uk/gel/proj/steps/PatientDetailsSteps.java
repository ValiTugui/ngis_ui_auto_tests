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
import org.junit.Assert;


public class PatientDetailsSteps extends Pages {

    PatientDetailsPage patientDetails;
    PatientSearchSteps patientSearchSteps;
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

    @When("the user clicks the Back link")
    public void theUserClicksTheBackLink() {
        patientDetailsPage.clickGoBackToPatientSearchLink();
    }

    @Given("a web browser is at the Patient Details page of a {string} patient with NHS number {string} and Date of Birth {string} without clinical indication test selected")
    public void aWebBrowserIsAtThePatientDetailsPageOfAPatientWithNHSNumberAndDateOfBirthWithoutClinicalIndicationTestSelected(String patientType, String nhsNo, String dob) {

        NavigateTo(AppConfig.getPropertyValueFromPropertyFile("TO_PATIENT_SEARCH_URL"), "patient-search");
        String[] value=  dob.split("-");  // Split DOB in the format 01-01-1900
        patientSearchPage.fillInValidPatientDetailsUsingNHSNumberAndDOB(nhsNo,value[0],value[1],value[2]);
        patientSearchPage.clickSearchButtonByXpath(driver);
        Assert.assertEquals(patientType, patientSearchPage.checkThatPatientCardIsDisplayed(driver));
        patientSearchPage.clickPatientCard();
        Assert.assertTrue("Patient details page is displayed", patientDetailsPage.patientDetailsPageIsDisplayed());
    }

    @When("the user clicks the Test Directory link from the notification banner")
    public void theUserClicksTheTestDirectoryLinkFromTheNotificationBanner() {
        patientDetailsPage.clickTestDirectoryLinkFromNotificationBanner();
    }
}
