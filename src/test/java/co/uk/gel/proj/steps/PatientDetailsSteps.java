package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.ClinicalQuestionsPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static co.uk.gel.proj.pages.PatientDetailsPage.newPatient;


public class PatientDetailsSteps extends Pages {

    public PatientDetailsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("^the Patient Details page is displayed$")
    public void thePatientDetailsPageIsDisplayed() {
        boolean testResult;
        testResult = patientDetailsPage.patientDetailsPageIsDisplayed();
        Assert.assertTrue(testResult);
    }

    @When("the user create a new patient record by clicking the {string} link to fill all fields without NHS number and reason {string}")
    public void theUserCreateANewPatientRecordByClickingTheLinkToFillAllFieldsWithoutNHSNumberAndReason(String createANewPatientLink, String reason) {
        if(!patientSearchPage.checkCreateNewPatientLinkDisplayed(createANewPatientLink)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NewPatientLink.jpg");
            Assert.fail("patientSearchPage.checkCreateNewPatientLinkDisplayed");
        }
        if(!patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NewPatientLink.jpg");
            Assert.fail("patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage");
        }
        if(!patientDetailsPage.newPatientPageIsDisplayed()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NewPatientPage.jpg");
            Assert.fail("patientDetailsPage.newPatientPageIsDisplayed");
        }
        if(!patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reason)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NewPatientFill.jpg");
            Assert.fail("patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber");
        }
        if(!patientDetailsPage.clickOnCreateRecord()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CreateRcord.jpg");
            Assert.fail("patientDetailsPage.clickOnCreateRecord");
        }
       if(!patientDetailsPage.patientIsCreated()) {
           SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientCreated.jpg");
           Assert.fail("patientDetailsPage.patientIsCreated");
       }
    }

    @And("the user clicks the Start a new Referral button")
    public void theUserClicksTheStartANewReferralButton() {
        boolean testResult;
        testResult = patientDetailsPage.clickStartNewReferralButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_StartNewReferral.jpg");
            Assert.fail("Could not click on New Referral Button");
        }

    }

    @When("the user clicks the Start Referral button")
    public void theUserClicksTheStartReferralButton() {

        boolean testResult;
        testResult = patientDetailsPage.clickStartReferralButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_StartReferral.jpg");
            Assert.fail("Could not click on Start Referral Button");
        }
    }

    @When("the user clicks the CI Search Start Referral button")
    public void theUserClicksTheCISerachStartReferralButton() {
        boolean testResult;
        testResult = patientDetailsPage.clickCISearchStartReferralButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_StartReferral.jpg");
            Assert.fail("Could not click on New Referral Button");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_StartReferral.jpg");
        }
    }

    @And("the user clicks the Start Referral button to display the referral page")
    public void theUserClicksTheStartReferralButtonToDisplayTheReferralPage() {
        boolean testResult = patientDetailsPage.clickStartNewReferralButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_StartReferral.jpg");
            Assert.fail("Could not click on New Referral Button");
        }
    }

    @Then("the clinical indication ID missing banner is displayed")
    public void theClinicalIndicationIDMissingBannerIsDisplayed() {
        boolean testResult;
        testResult = patientDetailsPage.clinicalIndicationIDMissingBannerIsDisplayed();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CIndication.jpg");
            Assert.fail("Clinical indication id missing banner not displayed");
        }
    }

    @And("the Start New Referral button is disabled")
    public void theStartNewReferralButtonIsDisabled() {
        boolean isDisabled = patientDetailsPage.startNewReferralButtonIsDisabled();
        if(!isDisabled){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_StartReferralBut.jpg");
            Assert.fail("Could not see Start Referral button as disabled");
        }
    }

    @When("the user clicks the - {string} - link")
    public void theUserClicksTheLink(String goBackToPatientSearch) {
        boolean testResult;
        testResult = patientDetailsPage.clickTheGoBackLink(goBackToPatientSearch);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_BackLink.jpg");
            Assert.fail("Could not click on back link");
        }
    }

    @Given("a web browser is at the Patient Details page of a {string} patient with NHS number {string} and Date of Birth {string} without clinical indication test selected")
    public void aWebBrowserIsAtThePatientDetailsPageOfAPatientWithNHSNumberAndDateOfBirthWithoutClinicalIndicationTestSelected(String patientType, String nhsNo, String dob) {

        String[] value = dob.split("-");  // Split DOB in the format 01-01-1900
        boolean testResult = patientSearchPage.fillInValidPatientDetailsUsingNHSNumberAndDOB(nhsNo, value[0], value[1], value[2]);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FillValues.jpg");
            Assert.fail("Could not fill patient search values");
        }
        patientSearchPage.clickSearchButtonByXpath();
        if(patientSearchPage.checkThatPatientCardIsDisplayed() == null){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCCard.jpg");
            Assert.fail("Could not see PC card displayed");
        }
        testResult = patientSearchPage.clickPatientCard();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CreatePC.jpg");
            Assert.fail("Could not Click on Create Patient");
        }

        testResult = patientDetailsPage.patientDetailsPageIsDisplayed();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCCreated.jpg");
            Assert.fail("Patient details page is displayed");
        }
    }

    @When("the user clicks the {string} link on the notification banner")
    public void theUserClicksTheLinkOnTheNotificationBanner(String linkOnNotification) {
        boolean testResult = false;
        testResult = patientDetailsPage.clickTheLinkOnNotificationBanner();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Notification.jpg");
            Assert.fail("Could not click on notification banner");
        }
    }

    @Given("a web browser is logged in as a {string} user at the Patient Details page of a {string} with valid details of NHS number and DOB")
    public void aWebBrowserIsLoggedInAsAUserAtThePatientDetailsPageOfAWithValidDetailsOfNHSNumberAndDOB(String userType, String patientType) throws IOException {
        if(!patientSearchPage.fillInNHSNumberAndDateOfBirth(patientType)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FillDetails.jpg");
            Assert.fail("patientSearchPage.fillInNHSNumberAndDateOfBirth");
        }
        if(!patientSearchPage.clickSearchButtonByXpath()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClickSearch.jpg");
            Assert.fail("patientSearchPage.clickSearchButtonByXpath");
        }
        if(!patientSearchPage.clickPatientCard()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClickPatientCard.jpg");
            Assert.fail("patientSearchPage.clickPatientCard");
        }
    }

    @Then("^the NHS number field is disabled$")
    public void nhsNumberFieldIsDisabled() {
        boolean testResult = false;
        testResult = patientDetailsPage.nhsNumberFieldIsDisabled();
        Assert.assertTrue(testResult);
    }

    @Given("web browser is logged in as a {string} user at the Patient Details page of a {string} with valid details of NHS number and DOB")
    public void webBrowserIsLoggedInAsAUserAtThePatientDetailsPageOfAWithValidDetailsOfNHSNumberAndDOB(List<String> attributeOfUrl, String userType, String patientType) throws IOException {
        String baseURL = attributeOfUrl.get(0);
        String confirmationPage = attributeOfUrl.get(1);
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        if(!patientSearchPage.fillInNHSNumberAndDateOfBirth(patientType)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FillDetails.jpg");
            Assert.fail("patientSearchPage.fillInNHSNumberAndDateOfBirth");
        }
        if(!patientSearchPage.clickSearchButtonByXpath()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClickSearch.jpg");
            Assert.fail("patientSearchPage.clickSearchButtonByXpath");
        }
        if(!patientType.equalsIgnoreCase(patientSearchPage.checkThatPatientCardIsDisplayed())){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCNotDisplayed.jpg");
            Assert.fail("patientSearchPage.Patient card not displayed");
        }
        if(!patientSearchPage.clickPatientCard()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClickPatientCard.jpg");
            Assert.fail("patientSearchPage.clickPatientCard");
        }
    }

    @Then("the NHS number field is enabled")
    public void theNHSNumberFieldIsEnabled() {
        boolean testResult = false;
        testResult = patientDetailsPage.nhsNumberFieldIsEnabled();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NHSNumber.jpg");
            Assert.fail("NHS Number not displayed");
        }
    }

    @Then("the new patient page is opened")
    public void theNewPatientPageIsOpened() {
        if(!patientDetailsPage.newPatientPageIsDisplayed()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientPage.jpg");
            Assert.fail("New patient page not displayed");
        }
    }

    @And("the NHS number and DOB fields are pre-populated in the new patient page from the search page")
    public void theNHSNumberAndDOBFieldsArePrePopulatedInTheNewPatientPageFromTheSearchPage() {
        patientDetailsPage.nhsNumberAndDOBFieldsArePrePopulatedInNewPatientPage();
    }

    @And("the new patient page displays expected input-fields and a {string} submit button")
    public void theNewPatientPageDisplaysExpectedInputFieldsAndASubmitButton(String labelOnSubmitButton) {
        if(labelOnSubmitButton.equalsIgnoreCase("Create record")){
            Assert.assertEquals(labelOnSubmitButton, patientDetailsPage.createRecord.getText());
        }else {
            Assert.assertEquals(labelOnSubmitButton, patientDetailsPage.savePatientDetailsToNGISButton.getText());
        }
    }

    @And("the user click on the referral card on patient details page to navigate to referral page")
    public void theUserClickOnTheReferralCardOnPatientDetailsPageToNavigateToReferralPage() {
        patientDetailsPage.verifyAndClickOnTheReferralCardOnPatientDetailsPage();
        if(!referralPage.checkThatReferralWasSuccessfullyCreated()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ReferralNotCreated.jpg");
            Assert.fail("Referral Created Message not displayed");
        }
        referralPage.saveAndContinueButtonIsDisplayed();
    }


    @And("the Ethnicity drop-down is allowed to have values up to {string}")
    public void theEthnicityDropDownIsAllowedToHaveValuesUpTo(String allowedValuesCount) {
        Assert.assertTrue(patientDetailsPage.verifyMaxAllowedValuesInEthnicityField(Integer.parseInt(allowedValuesCount)));
    }

    @Then("the patient's referrals are displayed at the bottom of the page")
    public void thePatientSReferralsAreDisplayedAtTheBottomOfThePage() {
        if(!patientDetailsPage.patientReferralsAreDisplayed()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientRferral.jpg");
            Assert.fail("Patient Referrals not displayed");
        }
    }

    @And("the referral status from the card is {string}")
    public void theReferralStatusFromTheCardIs(String expectedStatus) {
        boolean testResult = false;
        testResult = patientDetailsPage.verifyReferralStatus(expectedStatus);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ReferralStatus.jpg");
            Assert.fail("Referral Status not displayed correctly");
        }
    }

    @And("the referral cancel reason from the card is {string}")
    public void theReferralCancelReasonFromTheCardIs(String expectedReason) {
        if(!patientDetailsPage.verifyReferralReason(expectedReason)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CancelReason.jpg");
            Assert.fail("Referral Cancel Reason not displayed correctly");
        }
    }

    @And("the user edit the patients Gender {string}, Life Status {string} and Ethnicity {string} fields")
    public void theUserEditThePatientsGenderLifeStatusAndEthnicityFields(String gender, String lifeStatus, String ethnicity) {
        if(!patientDetailsPage.editPatientGenderLifeStatusAndEthnicity(gender, lifeStatus, ethnicity)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ReferralDetailsUpdate.jpg");
            Assert.fail("Referral Details not updated");
        }
    }

    @And("the user clicks the Update NGIS record button")
    public void theUserClicksTheUpdateNGISRecordButton() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickUpdateNGISRecordButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_UpdateNGISRecord.jpg");
            Assert.fail("Update NGIS Record button not displayed correctly");
        }
    }
    @And("the user clicks the Save and Continue button on Patient details page")
    public void theUserClicksSaveAndContinue() {
        boolean testResult;
        testResult = patientDetailsPage.clickOnSaveAndContinueButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SaveAndContinue.jpg");
            Assert.fail("Could not click on Save and Continue");
        }
    }

    @Then("the patient is successfully updated with a message {string}")
    public void thePatientIsSuccessfullyUpdatedWithAMessage(String expectedNotification) {
        String actualNotification = patientDetailsPage.getNotificationMessageForPatientCreatedOrUpdated();
        if(actualNotification == null){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NotificationNull.jpg");
            Assert.fail("Expected Notification not present:"+expectedNotification);
        }
        if(!expectedNotification.equalsIgnoreCase(actualNotification)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NotificationMismatch.jpg");
            Assert.fail("Expected Notification not matching with actual:Exp:"+expectedNotification+",Actual:"+actualNotification);
        }
    }

    @And("the newly edited patient's Gender {string}, Life Status {string} and Ethnicity {string} are displayed in Patient Details page")
    public void theNewlyEditedPatientSGenderLifeStatusAndEthnicityAreDisplayedInPatientDetailsPage(String expectedGender, String expectedLifeStatus, String expectedEthnicity) {

        String actualGender = Action.getText(patientDetailsPage.genderFieldStatus);
        String actualLifeStatus = Action.getText(patientDetailsPage.lifeStatusFieldStatus);
        String actualEthnicity = Action.getText(patientDetailsPage.ethnicityFieldStatus);
        Assert.assertEquals(expectedGender, actualGender);
        Assert.assertEquals(expectedLifeStatus, actualLifeStatus);
        Assert.assertEquals(expectedEthnicity, actualEthnicity);
    }

    @And("the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol")
    public void theMandatoryInputFieldsAndDropsDownsLabelsAreShownWithMandatoryAsteriskStarSymbol(DataTable dataTable) {
        List<Map<String, String>> expectedLabelList = dataTable.asMaps(String.class, String.class);
        boolean fieldLabelsFlag;
        fieldLabelsFlag = referralPage.verifyTheExpectedFieldLabelsWithActualFieldLabels(expectedLabelList);
        if(!fieldLabelsFlag){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_MandateField.jpg");
            Assert.fail("Mandatory fields not displayed correctly");
        }
    }

    @And("the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol")
    public void theNonMandatoryInputFieldsAndDropsDownsLabelsAreShownWithoutAsteriskStarSymbol(DataTable dataTable) {
        List<Map<String, String>> expectedLabelList = dataTable.asMaps(String.class, String.class);
        boolean fieldLabelsFlag;
        fieldLabelsFlag = referralPage.verifyTheExpectedFieldLabelsWithActualFieldLabels(expectedLabelList);
        if(!fieldLabelsFlag){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_MandateField.jpg");
            Assert.fail("Mandatory fields not displayed correctly");
        }
    }


    @And("the No button is selected by default for the question - Do you have the NHS Number?")
    public void theNoButtonIsSelectedByDefaultForTheQuestionDoYouHaveTheNHSNumber() {
        String selectedStatus = patientSearchPage.getNoBtnSelectedAttribute();
        Assert.assertEquals(selectedStatus, "true");
    }

    @And("the user select a reason for {string}")
    public void theUserSelectAReasonFor(String reasonForNoNHSNumber) {
        if(!patientDetailsPage.selectMissingNhsNumberReason(reasonForNoNHSNumber)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NHSReason.jpg");
            Assert.fail("NHS Missing Reason could not select");
        }
    }

    @And("the user click YES button for the question - Do you have the NHS no?")
    public void theUserClickYESButtonForTheQuestionDoYouHaveTheNHSNo() {
        Action.scrollToTop(driver);
        Wait.forElementToBeDisplayed(driver, patientDetailsPage.yesButton);
        patientDetailsPage.yesButton.click();
    }

    @Then("the user create a new patient record without NHS number and enter a reason for noNhsNumber {string}")
    public void theUserCreateANewPatientRecordWithoutNHSNumberAndEnterAReasonForNoNhsNumber(String reasonForNoNHSNo) {
        if(!patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNo)){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FillDetails.jpg");
            Assert.fail("Patient details could not fill properly");
        }
        if(!patientDetailsPage.clickOnCreateRecord()){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CreateRecord.jpg");
            Assert.fail("Could not click on Create Record");
        }
        if(!patientDetailsPage.patientIsCreated()) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientCreated.jpg");
            Assert.fail("Patient not created successfully");
        }
    }

    @And("the Ethnicity drop-down values are in Alphabetical order")
    public void theEthnicityDropDownValuesAreInAlphabeticalOrder(DataTable dataTable) {
        List<Map<String, String>> expectedEthnicityList = dataTable.asMaps(String.class, String.class);
        List<String> actualEthnicityList = patientDetailsPage.getTheEthnicityDropDownValues();

        for (int i = 0; i < expectedEthnicityList.size(); i++) {
            //Debugger.println("Expected ethnicity: " + expectedEthnicityList.get(i).get("EthnicityListHeader") + ":" + i + ":" + "Actual ethnicity " + actualEthnicityList.get(i) + "\n");
            Assert.assertEquals(expectedEthnicityList.get(i).get("EthnicityListHeader"), actualEthnicityList.get(i));
        }
    }

    @And("the user clicks the Save patient details to NGIS button")
    public void theUserClicksTheSavePatientDetailsToNGISButton() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickSavePatientDetailsToNGISButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SaveNGIS.jpg");
            Assert.fail("Cold not click on Save Patient details to NGIS");
        }
    }

    @Then("the patient is successfully created with a message {string}")
    public void thePatientIsSuccessfullyCreatedWithAMessage(String expectedNotification) {
        thePatientIsSuccessfullyUpdatedWithAMessage(expectedNotification);
    }

    @When("the user clears the date of birth field")
    public void theUserClearsTheDateOfBirthField() {
       String stepResult = patientDetailsPage.clearDateOfBirth();
       Assert.assertEquals("Success",stepResult);
    }

    @Then("the error messages for the mandatory fields on the {string} page are displayed as follows")
    public void theErrorMessagesForTheMandatoryFieldsOnThePageAreDisplayedAsFollows(String titlePage, DataTable dataTable) {
        Assert.assertEquals(titlePage, referralPage.getTheCurrentPageTitle());
        List<List<String>> expectedLabelsAndErrorMessagesList = dataTable.asLists(String.class);
        List actualFieldsLabels = referralPage.getTheFieldsLabelsOnCurrentPage();
        List actualFieldErrorMessages = referralPage.getTheListOfFieldsErrorMessagesOnCurrentPage();
        //Checking Labels
        for (int i = 1; i < expectedLabelsAndErrorMessagesList.size(); i++) { //i starts from 1 because i=0 represents the header
            if(!actualFieldsLabels.contains(expectedLabelsAndErrorMessagesList.get(i).get(0))) {
                Assert.fail("Expected Mandatory Label:"+expectedLabelsAndErrorMessagesList.get(i).get(0)+" not present in the page.");
            }
        }
        for (int i = 1; i < expectedLabelsAndErrorMessagesList.size(); i++) { //i starts from 1 because i=0 represents the header
            if(!actualFieldErrorMessages.contains(expectedLabelsAndErrorMessagesList.get(i).get(1))){
                Assert.fail("Expected Error message for Label not displayed:"+expectedLabelsAndErrorMessagesList.get(i).get(1) );
            }

        }
    }

    @And("the user fill in the last name field")
    public void theUserFillInTheLastNameField() {
        boolean testResult = false;
        testResult = patientDetailsPage.fillInLastName();
        Assert.assertTrue(testResult);
    }

    @And("the sub-heading title is displayed {string}")
    public void theSubHeadingTitleIsDisplayed(String expectedSubHeading) {
        String actualSubHeading = patientDetailsPage.getPageSubTitle();
        if(actualSubHeading == null){
            Assert.assertTrue("Subheading not displayed.",false);
        }
        if(!actualSubHeading.trim().contains(expectedSubHeading)){
            //Debugger.println("ActualSubHeading\n"+actualSubHeading+"\nExpected\n"+expectedSubHeading+"\n");
           // Debugger.println("LEN:"+actualSubHeading.length()+":Expected:"+expectedSubHeading.length()+":");
            Assert.assertTrue(false);
        }
    }

    @Then("the user fills in all fields without NHS number, enters a reason for noNhsNumber {string} and leaves HospitalNo field blank")
    public void theUserFillsInAllFieldsWithoutNHSNumberEntersAReasonForNoNhsNumberAndLeavesHospitalNoFieldBlank(String reasonForNoNHSNo) {
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNo);
        Action.clearInputField(patientDetailsPage.hospitalNumber);
    }

    @And("the NHS number field is displayed")
    public void theNHSNumberFieldIsDisplayed() {
        boolean nhsFieldDisplayed;
        nhsFieldDisplayed = Wait.isElementDisplayed(driver, patientDetailsPage.nhsNumber, 5);
        Assert.assertTrue(nhsFieldDisplayed);
        //Debugger.println("NHS Number field is displayed");
    }


    @Then("the user fills in all fields with the NHS number and leaves HospitalNo blank")
    public void theUserFillsInAllFieldsWithTheNHSNumberAndLeavesHospitalNoBlank() {
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithNHSNumber("N/A");
        Action.clearInputField(patientDetailsPage.hospitalNumber);
    }


    @Then("the user fills in all fields and leaves NHS Number and HospitalNo fields blank")
    public void theUserFillsInAllFieldsAndLeavesNHSNumberAndHospitalNoFieldsBlank() {
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithNHSNumber("N/A");
        Action.clearInputField(patientDetailsPage.hospitalNumber);
        Action.clearInputField(patientDetailsPage.nhsNumber);
    }

    @When("the user fills in the NHS Number field")
    public void theUserFillsInTheNHSNumberField() {
        patientDetailsPage.fillInNHSNumber();
    }

    @When("the user fills in all the fields with NHS number on the New Patient page")
    public void theUserFillsInAllTheFieldsWithNHSNumberOnTheNewPatientPage() {
        boolean testResult = false;
        testResult = patientDetailsPage.fillInAllNewPatientDetails();
        Assert.assertTrue(testResult);
        testResult = patientDetailsPage.fillInNHSNumber();
        Assert.assertTrue(testResult);
    }

    @And("the message displayed on the notification banner is {string}")
    public void theMessageDisplayedOnTheNotificationBannerIs(String expectedTextOnBanner) {
        String actualSubHeading = patientDetailsPage.getNotificationBannerText();
        if(!actualSubHeading.equalsIgnoreCase(expectedTextOnBanner)){
            //Debugger.println("ActualSubHeading:"+actualSubHeading+":Expected:"+expectedTextOnBanner+":");
            Assert.assertTrue(false);
        }
    }

    @Given("a web browser is at create new patient page")
    public void aWebBrowserIsAtCreateNewPatientPage(List<String> attributeOfUrl) {
        String baseURL = attributeOfUrl.get(0);
        String confirmationPage = attributeOfUrl.get(1);
        String userType = attributeOfUrl.get(2);
        if (userType != null) {
            NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage, userType);
        }
    }

    @When("the user select the life status {string}")
    public void theUserSelectTheLifeStatus(String lifeStatus) {
        boolean testResult = false;
        testResult = patientDetailsPage.editDropdownField(patientDetailsPage.lifeStatusButton, lifeStatus);
        Assert.assertTrue(testResult);
    }

    @And("the user view the day field")
    public void theUserViewDayField() {
        boolean testResult = false;
        testResult = patientDetailsPage.dayField();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_DayField.jpg");
            Assert.fail("Day field not success");
        }
    }

    @Then("the user clicks in the birthday field")
    public void theuserclicksinthebirthday() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickDayinputfield();
        Assert.assertTrue(testResult);
    }
    @Then("the user clicks in the birthday field in patient details page")
    public void theuserclicksinthebirthdayfieldinpatientdetailspage() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickDayinputfield_1();
        Assert.assertTrue(testResult);
    }
    @And("the user view the month field")
    public void theUserViewMonthField() {
        boolean testResult = false;
        testResult = patientDetailsPage.monthfield();
        Assert.assertTrue(testResult);
    }

    @Then("the user clicks in the birthmonth in patient details page")
    public void theuserclicksinthebirthmonthinpatientdetailspage() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickMonthinputfield_1();
        Assert.assertTrue(testResult);
    }
    @Then("the user clicks in the birthmonth")
    public void theuserclicksinthebirthmonth() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickMonthinputfield();
        Assert.assertTrue(testResult);
    }
    @And("the user view the year field")
    public void theUserViewYearField() {
        boolean testResult = false;
        testResult = patientDetailsPage.yearfield();
        Assert.assertTrue(testResult);
    }

    @Then("the user clicks in the birthyear in patient details page")
    public void theuserclicksinthebirthyearinpatientdetailspage() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickYearinputfield_1();
        Assert.assertTrue(testResult);
    }
    @Then("the user clicks in the birthyear")
    public void theuserclicksinthebirthyear() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickYearinputfield();
        Assert.assertTrue(testResult);
    }

    @Then ("the user clicks in the dateOfDiagnosis day field")
    public void theUserClicksInTheDateOfDiagnosisDayField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickDayInputFieldDateOfDiagnosis();
        Assert.assertTrue(testResult);
    }

    @Then ("the user clicks in the dateOfDiagnosis month field")
    public void theUserClicksInTheDateOfDiagnosisMonthField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickMonthInputFieldDateOfDiagnosis();
        Assert.assertTrue(testResult);
    }

    @Then ("the user clicks in the dateOfDiagnosis year field")
    public void theUserClicksInTheDateOfDiagnosisYearField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickYearInputFieldDateOfDiagnosis();
        Assert.assertTrue(testResult);
    }

    @Then ("the user clicks in the Sample Details day field")
    public void theUserClicksInTheSampleDetailsDayField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickDayInputFieldSampleDetails();
        Assert.assertTrue(testResult);
    }

    @Then ("the user clicks in the Sample Details month field")
    public void theUserClicksInTheSampleDetailsMonthField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickMonthInputFieldSampleDetails();
        Assert.assertTrue(testResult);
    }

    @Then ("the user clicks in the Sample Details year field")
    public void theUserClicksInTheSampleDetailsYearField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickYearInputFieldSampleDetails();
        Assert.assertTrue(testResult);
    }

    @And("the user clicks in the Date of Signature day field")
    public void theUserClicksInTheDateOfSignatureDayField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickDayInputFieldDateOfSignature();
        Assert.assertTrue(testResult);
    }

    @And("the user clicks in the Date of Signature month field")
    public void theUserClicksInTheDateOfSignatureMonthField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickMonthInputFieldDateOfSignature();
        Assert.assertTrue(testResult);
    }

    @And("the user clicks in the Date of Signature year field")
    public void theUserClicksInTheDateOfSignatureYearField() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickYearInputFieldDateOfSignature();
        Assert.assertTrue(testResult);
    }


 @And("the user fills in the date of birth {string}")
    public void theUserFillsInTheDateOfBirth(String dateOfBirth) {
        String stepResult = patientDetailsPage.fillDateOfBirth(dateOfBirth);
        Assert.assertEquals("Success",stepResult);
    }

       @And("the user fills in the birthday {string}")
    public void theUserFillsInTheDateOfBirthString(String  Birthday) {
        String stepResult = patientDetailsPage.fillBirthday(Birthday);
        Assert.assertEquals("Success",stepResult);
    }

    @And("the user fills in the birthyear {string}")
    public void theUserFillsInTheYearOfBirthString(String  BirthYear) {
        String stepResult = patientDetailsPage.fillBirthYear(BirthYear);
        Assert.assertEquals("Success", stepResult);
    }



    @And("the date of death input field is displayed")
    public void theDateOfDeathInputFieldIsDisplayed() {
        boolean inputFieldStatus;
        inputFieldStatus = Wait.isElementDisplayed(driver, patientDetailsPage.dateOfBirthDay, 10);
        Assert.assertTrue(inputFieldStatus);
    }

    @When("the user deletes the content of the Ethnicity field")
    public void theUserDeletesTheContentOfTheEthnicityField() {
        Wait.forElementToBeDisplayed(driver, patientDetailsPage.ethnicityButton);
        if (Wait.isElementDisplayed(driver, patientDetailsPage.clearEthnicityDropDownValue, 10)) {
            Wait.seconds(1);
            Action.retryClickAndIgnoreElementInterception(driver,patientDetailsPage.clearEthnicityDropDownValue);
            Debugger.println("Content of Ethnicity field is now deleted: " + Action.getText(patientDetailsPage.ethnicityButton));
            Action.retryClickAndIgnoreElementInterception(driver,patientDetailsPage.hospitalNumber);// click om an element field to trigger error on ethnicity button
            Wait.seconds(1); // Wait for the error to be triggered after deleting drop-down value
        }

    }

    @When("the selects the ethnicity as {string}")
    public void theSelectsTheEthnicityAs(String ethnicity) {
        boolean testResult = false;
        testResult = patientDetailsPage.editDropdownField(patientDetailsPage.ethnicityButton, ethnicity);
        Assert.assertTrue(testResult);
    }

    @And("the Relationship to Proband from the patient referral card is {string}")
    public void theRelationshipToProbandFromThePatientReferralCardIs(String expectedRelationShipToProband) {
        String actualRelationShipToProband = Action.getText(patientDetailsPage.referralProbandRelationShipStatus);
        Debugger.println("actual relationShip : " + actualRelationShipToProband);
        Debugger.println("Expected relationShip : " + expectedRelationShipToProband);
        Assert.assertEquals(expectedRelationShipToProband,actualRelationShipToProband);

    }

    @When("the user fills in the Ethnicity field {string}")
    public void theUserFillsInTheEthnicityField(String ethnicity) {
        boolean testResult = false;
        testResult = patientDetailsPage.addPatientEthnicity(ethnicity);
        Assert.assertTrue(testResult);
    }

    @When("the user attempts to fill in the NHS Number {string} with data that exceed the maximum data allowed {int}")
    public void theUserAttemptsToFillInTheNHSNumberWithDataThatExceedTheMaximumDataAllowed(String nHSNumber, int maximumCharactersAllowed) {
        if (nHSNumber.length() > maximumCharactersAllowed) {
            patientDetailsPage.nhsNumber.sendKeys(nHSNumber);
            Assert.assertTrue(true);
        }
    }

    @When("the user attempts to fill in the Hospital Number {string} with data that exceed the maximum data allowed {int}")
    public void theUserAttemptsToFillInTheHospitalNumberWithDataThatExceedTheMaximumDataAllowed(String hospitalNumber, int maximumCharactersAllowed) {

        if (hospitalNumber.length() > maximumCharactersAllowed) {
            patientDetailsPage.hospitalNumber.sendKeys(hospitalNumber);
            Assert.assertTrue(true);
        }

    }

    @Then("the user is prevented from entering data that exceed that allowable maximum data {int}")
    public void theUserIsPreventedFromEnteringDataThatExceedThatAllowableMaximumData(int maximumCharactersAllowed) {

        String actualNhsNumber = patientDetailsPage.nhsNumber.getAttribute("value").trim();
        Debugger.println("Actual NhsNumber :" + actualNhsNumber + " : " + actualNhsNumber.length());
        Assert.assertEquals(actualNhsNumber.length(), maximumCharactersAllowed);
    }


    @Then("the user is prevented from entering data that exceed that allowable maximum data {int} in the {string} field")
    public void theUserIsPreventedFromEnteringDataThatExceedThatAllowableMaximumDataInTheField(int maximumCharactersAllowed, String inputField) {

        switch (inputField) {
            case "NHSNumber": {
                String actualNhsNumber = patientDetailsPage.nhsNumber.getAttribute("value").trim();
                Debugger.println("Actual NhsNumber :" + actualNhsNumber + " : " + actualNhsNumber.length());
                Assert.assertEquals(actualNhsNumber.length(), maximumCharactersAllowed);
                break;
            }
            case "HospitalNumber": {
                String actualHospitalNumber = patientDetailsPage.hospitalNumber.getAttribute("value").trim();
                Debugger.println("Actual Hospital :" + actualHospitalNumber + " : " + actualHospitalNumber.length());
                Assert.assertEquals(actualHospitalNumber.length(), maximumCharactersAllowed);
                break;
            }
            case "ClinicianPhoneNumber": {
                String actualPhoneNumber = responsibleClinicianPage.clinicianPhoneNumberField.getAttribute("value").trim();
                Debugger.println("Actual PhoneNumber :" + actualPhoneNumber + " : " + actualPhoneNumber.length());
                Assert.assertEquals(actualPhoneNumber.length(), maximumCharactersAllowed);
                break;
            }
            case "TumourDescription": {
                String actualTumourDescription = Action.getValue(tumoursPage.descriptiveName).trim();
                Debugger.println("Actual actualTumourDescription :" + actualTumourDescription + " : " + actualTumourDescription.length());
                Assert.assertEquals(actualTumourDescription.length(), maximumCharactersAllowed);
                break;
            }
              default:
                throw new IllegalArgumentException("Invalid query search parameters");
        }

    }

    @And("the user deletes data in the NHS Number field")
    public void theUserDeletesDataInTheNHSNumberField() {
        Action.clearInputField(patientDetailsPage.nhsNumber);
        Wait.seconds(1);
    }

    @And("the user deletes the data in the Hospital Number field")
    public void theUserDeletesTheDataInTheHospitalNumberField() {
        Wait.seconds(1);
        Action.clearInputField(patientDetailsPage.hospitalNumber);
    }

    @And("the correct patient address is displayed on patient details page")
    public void theCorrectPatientAddressIsDisplayedOnPatientDetailsPage() {
        List<String> actualPatientAddress = patientDetailsPage.getActualPatientAddressOnPatientDetailPage();
        List<String> expectedPatientAddress = newPatient.getPatientAddress();
        Debugger.println("actual Patient Address :" + actualPatientAddress);
        Debugger.println("expected Patient Address :" + expectedPatientAddress);
        Assert.assertEquals(expectedPatientAddress,actualPatientAddress);
    }

    @When("the user fills in all the mandatory fields without NHS number and enter a reason for noNhsNumber {string}")
    public void theUserFillsInAllTheMandatoryFieldsWithoutNHSNumberAndEnterAReasonForNoNhsNumber(String reasons) {
        boolean testResult  = false;
        testResult = patientDetailsPage.fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason(reasons);
        Assert.assertTrue(testResult);
    }

    @And("the user fills in the HospitalNo field")
    public void theUserFillsInTheHospitalNoField() {
        patientDetailsPage.fillInHospitalNo();
    }

    @And("the Hospital number field displays the hint text {string}")
    public void theHospitalNumberFieldDisplaysTheHintText(String expectedHintText) {
        String actualHintText = Action.getPlaceHolderAttribute(patientDetailsPage.hospitalNumber);
        if(!actualHintText.contains(expectedHintText)){
            Assert.fail("Actual Hint:"+actualHintText+",Expected:"+expectedHintText);
        }
    }

    @And("the user deletes data in the fields - First Name, Last Name, Date of Birth, Gender, Life Status and Ethnicity")
    public void theUserDeletesDataInTheFieldsFirstNameLastNameDateOfBirthGenderLifeStatusAndEthnicity() {

        Action.clearInputField(patientDetailsPage.firstName);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.firstName);
        Action.clearInputField(patientDetailsPage.familyName);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.familyName);
        //patientDetailsPage.dateOfBirth.click();
        Action.clearInputField(patientDetailsPage.dateOfBirthDay);
        Action.clearInputField(patientDetailsPage.dateOfBirthMonth);
        Action.clearInputField(patientDetailsPage.dateOfBirthYear);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.administrativeGenderButton);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.clearGenderDropDownValue);
        Wait.seconds(1);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.lifeStatusButton);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.clearLifeStatusDropDown);
        Wait.seconds(1);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.ethnicityButton);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.clearLifeStatusDropDownValue);

    }

    @And("the user fills in all the fields without NHS number and enter a reason for noNhsNumber {string}")
    public void theUserFillsInAllTheFieldsWithoutNHSNumberAndEnterAReasonForNoNhsNumber(String reasonForNoNHSNo) {
        boolean testResult = false;
        testResult = patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNo);
        Assert.assertTrue(testResult);
    }

    @And("the user fill in the first name field")
    public void theUserFillInTheFirstNameField() {

        patientDetailsPage.fillInFirstName();
    }

    @And("the user deletes the pre-populated fields - First Name, Last Name, Date of Birth, Gender, and PostCode")
    public void theUserDeletesThePrePopulatedFieldsFirstNameLastNameDateOfBirthGenderAndPostCode() {

        Action.clearInputField(patientDetailsPage.firstName);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.firstName);
        Action.clearInputField(patientDetailsPage.familyName);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.familyName);
        //patientDetailsPage.dateOfBirth.click();
        Action.clearInputField(patientDetailsPage.dateOfBirthDay);
        Action.clearInputField(patientDetailsPage.dateOfBirthMonth);
        Action.clearInputField(patientDetailsPage.dateOfBirthYear);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.administrativeGenderButton);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.clearGenderDropDownValue);
        Wait.seconds(1);
        Action.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.postcode);
        Action.clearInputField(patientDetailsPage.postcode);
    }

    @When("the user select the gender {string}")
    public void theUserSelectTheGender(String gender) {
        Action.scrollToTop(driver);
        boolean testResult = false;
        testResult = patientDetailsPage.selectGender(patientDetailsPage.administrativeGenderButton, gender);
        Assert.assertTrue(testResult);
    }

    @And("the Add To Patient Details {string} button is displayed")
    public void theAddToPatientDetailsButtonIsDisplayed(String expectedPatientButton) {
        String actualPatientSubmitButton = Action.getText(patientDetailsPage.addDetailsToNGISButton);
        Debugger.println("Actual Patient Submit: " + actualPatientSubmitButton);
        Debugger.println("Expected Patient Submit: " + expectedPatientButton);
        Assert.assertEquals(expectedPatientButton,actualPatientSubmitButton);
    }

    @And("the user clicks the Add patient details to NGIS button")
    public void theUserClicksTheAddPatientDetailsToNGISButton() {
        boolean testResult = false;
        testResult = patientDetailsPage.clickAddDetailsToNGISButton();
        Assert.assertTrue(testResult);
    }

    @And("the user retrieve the patient HumanReadable-ID from the patient detail url")
    public void theUserRetrieveThePatientHumanReadableIDFromThePatientDetailUrl() {
        NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
        String currentURl = driver.getCurrentUrl();
        String patientIdRegex = "^[p0-9]+$";
        String expectedPatientID = TestUtils.getTheExpectedCurrentHumanReadableID(currentURl,patientIdRegex);
        Debugger.println("Expected patient ID " + expectedPatientID);
        Assert.assertNotNull(expectedPatientID);
        newPatient.setPatientID(expectedPatientID);
    }

    @And("the user clicks on RelationshipToProband drop down and sees the values of the drop down{string} with recently used suggestion values")
    public void theUserClicksOnRelationshipToProbandDropDownAndSeesTheValuesOfTheDropDownWithRecentlyUsedSuggestionValues(String expValue) {
        boolean testResult=false;
        testResult=patientDetailsPage.verifyRelationshipToProbandDropDownShowsRecentlyUsedSuggestion(expValue);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ProbandRelation.jpg");
            Assert.fail("Relationship to proband could not click");
        }
    }

    @And("the user sees the Create Record button highlighted with color as {string}")
    public void theUserSeesTheButtonAndColorAs(String expButtonColor) {
        boolean testResult = false;
        testResult = patientDetailsPage.verifyColorOfCreateRecordButton(expButtonColor);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CreateRecord.jpg");
            Assert.fail("Create Record button not highlighted");
        }
    }

    @And("the user fills in the Postcode field box with {string}")
    public void theUserFillsInThePostcodeWith(String postcode) {
        boolean testResult = false;
        testResult=patientDetailsPage.fillPostcodeValue(postcode);
        Assert.assertTrue(testResult);
    }

    @And("the user is able to see the entered postcode value in the address field in correct {string} format")
    public void theUserIsAbleToSeeTheEnteredPostcodeValueInTheAddressFieldInCorrectFormat(String formattedPostcode) {
        boolean testResult=false;
        testResult=patientDetailsPage.verifyPostcodeFormatInAddress(formattedPostcode);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PostCode.jpg");
            Assert.fail("Post code value not verified");
        }
    }

    @And("the user is able to see the entered postcode value is in correct {string} format")
    public void theUserIsAbleToSeeTheEnteredPostcodeValueIsInCorrectFormat(String formattedPostcode) {
        boolean testResult=false;
        testResult=patientDetailsPage.verifyPostcodeFormatInPD(formattedPostcode);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PostCodeFormat.jpg");
            Assert.fail("Post code format not verified successfully");
        }
    }

    @And("the user updates the patient details stage with {string}")
    public void theUserUpdatesPatientDetailsStageWith(String patientDetails) {
        boolean testResult = false;
        testResult= patientDetailsPage.updatePatientDetails(patientDetails);
        Assert.assertTrue(testResult);
    }

   @When("the user modifies the gender value")
    public void theUserModifiesTheGender() {
        boolean testResult = false;
        String gender;
        String genderValue= patientDetailsPage.readGenderValue();
        Debugger.println("The Existing gender value "+genderValue);
        if(genderValue==null||genderValue.isEmpty()){
            Assert.fail("No value read from gender field");
        }
        if(genderValue.equalsIgnoreCase("Male")){
            gender="Female";
        }
        else{
            gender="Male";;
        }
        ClinicalQuestionsPage.genderValue=gender;
        Debugger.println("The new gender value to be selected "+gender);
        testResult = patientDetailsPage.selectGender(patientDetailsPage.administrativeGenderButton, gender);
        Assert.assertTrue(testResult);
    }

    @And("the Start New Referral button is enabled")
    public void theStartNewReferralButtonIsEnabled() {
        boolean testResult = false;
        testResult = patientDetailsPage.startNewReferralButtonIsEnabled();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_StartReferral.jpg");
            Assert.fail("Start New Referral button is not enabled");
        }
    }

    @And("the user verifies the - {string} - link")
    public void theUserVerifiesTheLink(String goBackToPatientSearch) {
        boolean testResult;
        testResult = patientDetailsPage.verifyTheGoBackLink(goBackToPatientSearch);
        Assert.assertTrue(testResult);
    }

    @And("the correct details are displayed in patient record page")
    public void theCorrectDetailsAreDisplayedInPatientRecordPage() {
        boolean testResult ;
        testResult=patientDetailsPage.verifyThePatientRecordDetails();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientRecordDetails.jpg");
            Assert.fail("Patient Record details not displayed properly");
        }
    }

    @And("the user verifies the patient NGIS ID")
    public void theuserverifiesthepatientNGISID() {
        boolean testResult;
        testResult=patientDetailsPage.verifyPatientNgisId();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see {string}")
    public void theUserShouldBeAbleToSee(String expMessage) {
        boolean testResult;
        testResult = patientDetailsPage.verifyPostCodeErrorMessage(expMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the user should not see the merge warning notification banner")
    public void theUserShouldNotSeeTheMergeWarningNotificationBanner() {
        boolean testResult = false;
        testResult = patientDetailsPage.verifyNoMergeWarningNotificationBannerDisplayed();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NoWarningMsg");
            Assert.fail("Warning message displayed");
        }
    }

    @Then("the user should not see the merge status on the patient result card")
    public void theUserShouldNotSeeTheMergeStatusOnThePatientResultCard() {
        boolean testResult = false;
        testResult = patientSearchPage.checkMergeStatusIsNotDisplayed();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_userSeeTheMergeStatusOnCard.jpg");
            Assert.fail("MergeStatus displayed :");
        }
    }

    @Then("the user fills in the Gender field in the Patient details")
    public void theUserFillsInTheGenderFieldInThePatientDetails(DataTable dataTable) {
        List<List<String >> list=dataTable.asLists();
        System.out.println("SIZE="+list.get(0).size());

        for(int index=0; index<list.get(0).size();index++) {
            System.out.println("Value:" + list.get(0).get(index));
            String expectedGenderValue = list.get(0).get(index);

            boolean testresult = patientDetailsPage.checkGenderDropdownSuggestionInPatientDetails(expectedGenderValue);
            if (!testresult) {
                SeleniumLib.takeAScreenShot("CheckingGenderInPatientDetails.jpg");
                Assert.fail("Checking gender dropdown failed" + testresult);
            }
        }
    }

    @And("the {string} message {string} displayed")
    public void messageDisplayed(String auditMessage, String visibility) {
        if(visibility.trim().equalsIgnoreCase("is")){
            if(!Wait.isElementDisplayed(driver,homePage.relationshipWIthPatientMessage,15)){
                Action.scrollToBottom(driver);
                SeleniumLib.takeAScreenShot("AuditMessageNotDisplayed.jpg");
            }
            Assert.assertEquals("\"Your access will be audited to validate there is a legitimate relationship with the patient\" is not displayed", auditMessage, homePage.relationshipWIthPatientMessage.getText());
        }else if(visibility.trim().equalsIgnoreCase("is not")){
            boolean flag = false;
            if(driver.getPageSource().contains(auditMessage)){
                Action.scrollToBottom(driver);
                SeleniumLib.takeAScreenShot("AuditMessageIsDisplayed.jpg");
                flag=true;
                Assert.assertFalse("\"Your access will be audited to validate there is a legitimate relationship with the patient\" is displayed", flag);
            }
        }
        else{
            Debugger.println("Incorrect value for visibility");
        }
    }
}//end