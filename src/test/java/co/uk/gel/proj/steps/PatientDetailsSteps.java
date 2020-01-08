package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;


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

        String[] value = dob.split("-");  // Split DOB in the format 01-01-1900
        patientSearchPage.fillInValidPatientDetailsUsingNHSNumberAndDOB(nhsNo, value[0], value[1], value[2]);
        patientSearchPage.clickSearchButtonByXpath(driver);
        Assert.assertEquals(patientType, patientSearchPage.checkThatPatientCardIsDisplayed(driver));
        patientSearchPage.clickPatientCard();
        Assert.assertTrue("Patient details page is displayed", patientDetailsPage.patientDetailsPageIsDisplayed());
    }

    @When("the user clicks the Test Directory link from the notification banner")
    public void theUserClicksTheTestDirectoryLinkFromTheNotificationBanner() {
        boolean testDirectoryLinkClickAble;
        testDirectoryLinkClickAble = patientDetailsPage.clickTestDirectoryLinkFromNotificationBanner();
        Assert.assertTrue(testDirectoryLinkClickAble);
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
        Assert.assertTrue("NHS Number field is not disabled", !(patientDetailsPage.nhsNumberFieldIsDisabled()));
    }

    @Given("web browser is logged in as a {string} user at the Patient Details page of a {string} with valid details of NHS number and DOB")
    public void webBrowserIsLoggedInAsAUserAtThePatientDetailsPageOfAWithValidDetailsOfNHSNumberAndDOB(List<String> attributeOfUrl, String userType, String patientType) throws IOException {
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
        Assert.assertTrue("NHS Number field is not enabled", (patientDetailsPage.nhsNumberFieldIsEnabled()));
    }

    @Then("the new patient page is opened")
    public void theNewPatientPageIsOpened() {
        patientDetailsPage.newPatientPageIsDisplayed();
    }

    @And("the NHS number and DOB fields are pre-populated in the new patient page from the search page")
    public void theNHSNumberAndDOBFieldsArePrePopulatedInTheNewPatientPageFromTheSearchPage() {
        patientDetailsPage.nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage();
    }

    @And("the new patient page displays expected input-fields and a {string} submit button")
    public void theNewPatientPageDisplaysExpectedInputFieldsAndASubmitButton(String labelOnSubmitButton) {
        Assert.assertTrue("All expected fields are not displayed on new patient page", patientDetailsPage.verifyTheElementsOnAddNewPatientPage());
        Debugger.println("Actual referral submit button: " + labelOnSubmitButton + " : " + "Expected referral submit button " + patientDetailsPage.savePatientDetailsToNGISButton.getText());
        Assert.assertEquals(labelOnSubmitButton, patientDetailsPage.savePatientDetailsToNGISButton.getText());
    }

    @And("the user click on the referral card on patient details page to navigate to referral page")
    public void theUserClickOnTheReferralCardOnPatientDetailsPageToNavigateToReferralPage() {
        patientDetailsPage.verifyAndClickOnTheReferralCardOnPatientDetailsPage();
        referralPage.checkThatReferralWasSuccessfullyCreated();
        referralPage.saveAndContinueButtonIsDisplayed();
    }


    @And("the Ethnicity drop-down is allowed to have values up to {string}")
    public void theEthnicityDropDownIsAllowedToHaveValuesUpTo(String allowedValuesCount) {
       Assert.assertTrue(patientDetailsPage.verifyMaxAllowedValuesInEthnicityField(Integer.parseInt(allowedValuesCount)));
    }
    @Then("the patient's referrals are displayed at the bottom of the page")
    public void thePatientSReferralsAreDisplayedAtTheBottomOfThePage() {
        Assert.assertTrue(patientDetailsPage.patientReferralsAreDisplayed());
    }

    @And("the referral status from the card is {string}")
    public void theReferralStatusFromTheCardIs(String expectedStatus) {
        Assert.assertTrue(patientDetailsPage.verifyReferralStatus(expectedStatus));
    }

    @And("the referral cancel reason from the card is {string}")
    public void theReferralCancelReasonFromTheCardIs(String expectedReason) {
        Assert.assertTrue(patientDetailsPage.verifyReferralReason(expectedReason));
    }

    @And("the user edit the patients Gender {string}, Life Status {string} and Ethnicity {string} fields")
    public void theUserEditThePatientsGenderLifeStatusAndEthnicityFields(String gender, String lifeStatus, String ethnicity) {
        patientDetailsPage.editPatientGenderLifeStatusAndEthnicity(gender,lifeStatus,ethnicity);
    }

    @And("the user clicks the Update NGIS record button")
    public void theUserClicksTheUpdateNGISRecordButton() {
        patientDetailsPage.clickUpdateNGISRecordButton();
    }

    @Then("the patient is successfully updated with a {string}")
    public void thePatientIsSuccessfullyUpdatedWithA(String expectedNotification) {
        String actualNotification =  patientDetailsPage.getNotificationMessageForPatientCreatedOrUpdated();
        Debugger.println("Expected notification : " + expectedNotification);
        Debugger.println(("Actual notification " + actualNotification));
        Assert.assertEquals(expectedNotification, actualNotification);
    }

    @And("the newly edited patient's Gender {string}, Life Status {string} and Ethnicity {string} are displayed in Patient Details page")
    public void theNewlyEditedPatientSGenderLifeStatusAndEthnicityAreDisplayedInPatientDetailsPage(String expectedGender, String expectedLifeStatus, String expectedEthnicity) {

        String actualGender = Actions.getText(patientDetailsPage.administrativeGenderButton);
        String actualLifeStatus = Actions.getText(patientDetailsPage.lifeStatusButton);
        String actualEthnicity = Actions.getText(patientDetailsPage.ethnicityButton);

        Debugger.println("Expected: gender: " + expectedGender + ":" + "lifestatus: " + expectedLifeStatus + ":" + "ethnicity: "  + expectedEthnicity);
        Debugger.println("Actual: gender: " + actualGender + ":" + "lifestatus: " + actualLifeStatus + ":" + "ethnicity: "  + actualEthnicity);

        Assert.assertEquals(expectedGender,actualGender);
        Assert.assertEquals(expectedLifeStatus,actualLifeStatus);
        Assert.assertEquals(expectedEthnicity,actualEthnicity);
    }

    @And("the patient detail page displays expected input-fields and drop-down fields")
    public void thePatientDetailPageDisplaysExpectedInputFieldsAndDropDownFields() {
        Assert.assertTrue("All expected fields are not displayed on patient detail page", patientDetailsPage.verifyTheElementsOfPatientDetailsPageWithNhsNumber());
    }

    @And("the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol")
    public void theMandatoryInputFieldsAndDropsDownsLabelsAreShownWithMandatoryAsteriskStarSymbol(DataTable dataTable) {
        List<Map<String, String>> expectedLabelList = dataTable.asMaps(String.class, String.class);
        boolean fieldLabelsFlag;
        fieldLabelsFlag = referralPage.verifyTheExpectedFieldLabelsWithActualFieldLabels(expectedLabelList);
        Assert.assertTrue(fieldLabelsFlag);
    }

    @And("the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol")
    public void theNonMandatoryInputFieldsAndDropsDownsLabelsAreShownWithoutAsteriskStarSymbol(DataTable dataTable) {
        List<Map<String, String>> expectedLabelList = dataTable.asMaps(String.class, String.class);
        boolean fieldLabelsFlag;
        fieldLabelsFlag = referralPage.verifyTheExpectedFieldLabelsWithActualFieldLabels(expectedLabelList);
        Assert.assertTrue(fieldLabelsFlag);
    }


    @And("the No button is selected by default for the question - Do you have the NHS Number?")
    public void theNoButtonIsSelectedByDefaultForTheQuestionDoYouHaveTheNHSNumber() {
        String selectedStatus = patientSearchPage.getNoBtnSelectedAttribute();
        Assert.assertEquals(selectedStatus, "true");
    }

    @And("the user select a reason for {string}")
    public void theUserSelectAReasonFor(String reasonForNoNHSNumber) {
        patientDetailsPage.selectMissingNhsNumberReason(reasonForNoNHSNumber);
    }

    @And("the user click YES button for the question - Do you have the NHS no?")
    public void theUserClickYESButtonForTheQuestionDoYouHaveTheNHSNo() {
        Wait.forElementToBeDisplayed(driver, patientDetailsPage.yesButton);
        patientDetailsPage.yesButton.click();
    }
}
