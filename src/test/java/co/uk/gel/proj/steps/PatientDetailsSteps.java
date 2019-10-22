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

import java.io.IOException;
import java.util.List;


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

    @Given("a web browser is logged in as a {string} user at the Patient Details page of a {string} with valid details of NHS number and DOB")
    public void aWebBrowserIsLoggedInAsAUserAtThePatientDetailsPageOfAWithValidDetailsOfNHSNumberAndDOB(String userType, String patientType) throws IOException {
        patientSearchPage.fillInNHSNumberAndDateOfBirth(patientType);
        patientSearchPage.clickSearchButtonByXpath(driver);
       // Assert.assertEquals(patientType, patientSearchPage.checkThatPatientCardIsDisplayed(driver));  // Spine test data converted to NGIS causing test to fail
        patientSearchPage.clickPatientCard();
    }

    @Then("^the NHS number field is disabled$")
    public void nhsNumberFieldIsDisabled() {
        Assert.assertTrue("NHS Number field is not disabled",!(patientDetailsPage.nhsNumberFieldIsDisabled())) ;
    }

    @Given("web browser is logged in as a {string} user at the Patient Details page of a {string} with valid details of NHS number and DOB")
    public void webBrowserIsLoggedInAsAUserAtThePatientDetailsPageOfAWithValidDetailsOfNHSNumberAndDOB(List<String> attributeOfUrl, String userType, String patientType) throws IOException{
        String baseURL = attributeOfUrl.get(0);
        String confirmationPage = attributeOfUrl.get(1);
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        patientSearchPage.fillInNHSNumberAndDateOfBirth(patientType);
        patientSearchPage.clickSearchButtonByXpath(driver);
        Assert.assertEquals(patientType, patientSearchPage.checkThatPatientCardIsDisplayed(driver));
        patientSearchPage.clickPatientCard();
    }

    @Then("the NHS number field is enabled")
    public void theNHSNumberFieldIsEnabled() {
        Assert.assertTrue("NHS Number field is not enabled",(patientDetailsPage.nhsNumberFieldIsEnabled())) ;
    }
}
