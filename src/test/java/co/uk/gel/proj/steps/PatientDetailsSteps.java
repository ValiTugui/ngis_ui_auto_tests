package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.hu.De;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static co.uk.gel.proj.pages.PatientDetailsPage.newPatient;


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
        patientSearchPage.checkCreateNewPatientLinkDisplayed(createANewPatientLink);
        patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
        patientDetailsPage.newPatientPageIsDisplayed();
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reason);
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
        boolean flag = false;
        flag = patientDetailsPage.patientIsCreated();
        Assert.assertTrue(flag);
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

    @And("the Start New Referral button is disabled")
    public void theStartNewReferralButtonIsDisabled() {
        patientDetailsPage.startNewReferralButtonIsDisabled();
    }


    @When("the user clicks the - {string} - link")
    public void theUserClicksTheLink(String goBackToPatientSearch) {
        patientDetailsPage.clickTheGoBackLink(goBackToPatientSearch);
        Wait.seconds(1);
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

    @When("the user clicks the {string} link on the notification banner")
    public void theUserClicksTheLinkOnTheNotificationBanner(String linkOnNotification) {
        boolean testDirectoryLinkClickAble;
        testDirectoryLinkClickAble = patientDetailsPage.clickTheLinkOnNotificationBanner(linkOnNotification);
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
        Assert.assertTrue("All expected fields are not displayed on new patient page", patientDetailsPage.verifyTheElementsOnAddNewPatientPageNormalUserFlow());
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
        patientDetailsPage.editPatientGenderLifeStatusAndEthnicity(gender, lifeStatus, ethnicity);
    }

    @And("the user clicks the Update NGIS record button")
    public void theUserClicksTheUpdateNGISRecordButton() {
        patientDetailsPage.clickUpdateNGISRecordButton();
    }

    @Then("the patient is successfully updated with a message {string}")
    public void thePatientIsSuccessfullyUpdatedWithAMessage(String expectedNotification) {
        String actualNotification = patientDetailsPage.getNotificationMessageForPatientCreatedOrUpdated();
        Debugger.println("Expected notification : " + expectedNotification);
        Debugger.println(("Actual notification " + actualNotification));
        Assert.assertEquals(expectedNotification, actualNotification);
    }

    @And("the newly edited patient's Gender {string}, Life Status {string} and Ethnicity {string} are displayed in Patient Details page")
    public void theNewlyEditedPatientSGenderLifeStatusAndEthnicityAreDisplayedInPatientDetailsPage(String expectedGender, String expectedLifeStatus, String expectedEthnicity) {

        String actualGender = Actions.getText(patientDetailsPage.administrativeGenderButton);
        String actualLifeStatus = Actions.getText(patientDetailsPage.lifeStatusButton);
        String actualEthnicity = Actions.getText(patientDetailsPage.ethnicityButton);

        Debugger.println("Expected: gender: " + expectedGender + ":" + "lifestatus: " + expectedLifeStatus + ":" + "ethnicity: " + expectedEthnicity);
        Debugger.println("Actual: gender: " + actualGender + ":" + "lifestatus: " + actualLifeStatus + ":" + "ethnicity: " + actualEthnicity);

        Assert.assertEquals(expectedGender, actualGender);
        Assert.assertEquals(expectedLifeStatus, actualLifeStatus);
        Assert.assertEquals(expectedEthnicity, actualEthnicity);
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

    @Then("the user create a new patient record without NHS number and enter a reason for noNhsNumber {string}")
    public void theUserCreateANewPatientRecordWithoutNHSNumberAndEnterAReasonForNoNhsNumber(String reasonForNoNHSNo) {
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNo);
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
        boolean flag = false;
        flag = patientDetailsPage.patientIsCreated();
        Assert.assertTrue(flag);
    }

    @And("the Ethnicity drop-down values are in Alphabetical order")
    public void theEthnicityDropDownValuesAreInAlphabeticalOrder(DataTable dataTable) {
        List<Map<String, String>> expectedEthnicityList = dataTable.asMaps(String.class, String.class);
        List<String> actualEthnicityList = patientDetailsPage.getTheEthnicityDropDownValues();

        for (int i = 0; i < expectedEthnicityList.size(); i++) {
            Debugger.println("Expected ethnicity: " + expectedEthnicityList.get(i).get("EthnicityListHeader") + ":" + i + ":" + "Actual ethnicity " + actualEthnicityList.get(i) + "\n");
            Assert.assertEquals(expectedEthnicityList.get(i).get("EthnicityListHeader"), actualEthnicityList.get(i));
        }
    }

    @And("the user clicks the Save patient details to NGIS button")
    public void theUserClicksTheSavePatientDetailsToNGISButton() {
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
    }

    @Then("the patient is successfully created with a message {string}")
    public void thePatientIsSuccessfullyCreatedWithAMessage(String expectedNotification) {
        thePatientIsSuccessfullyUpdatedWithAMessage(expectedNotification);
    }

    @When("the user clears the date of birth field")
    public void theUserClearsTheDateOfBirthField() {
        patientDetailsPage.dateOfBirth.click();
        Actions.clearInputField(patientDetailsPage.dateOfBirth);
    }

    @Then("the error messages for the mandatory fields on the {string} page are displayed as follows")
    public void theErrorMessagesForTheMandatoryFieldsOnThePageAreDisplayedAsFollows(String titlePage, DataTable dataTable) {
        Assert.assertEquals(titlePage, referralPage.getTheCurrentPageTitle());
        List<List<String>> expectedLabelsAndErrorMessagesList = dataTable.asLists(String.class);
        List actualFieldsLabels = referralPage.getTheFieldsLabelsOnCurrentPage();
        List actualFieldErrorMessages = referralPage.getTheListOfFieldsErrorMessagesOnCurrentPage();
        List actualColourFieldErrorMessages = referralPage.getColourOfTheFieldsErrorMessagesOnCurrentPage();
        String expectedFontColorInRGB = "";

        for (int i = 1; i < expectedLabelsAndErrorMessagesList.size(); i++) { //i starts from 1 because i=0 represents the header
            Debugger.println("Expected labelHeader " + expectedLabelsAndErrorMessagesList.get(i).get(0) + " count: " + i);
            Debugger.println("Actual labelHeader " + actualFieldsLabels.get(i - 1) + "\n");
            Assert.assertTrue(actualFieldsLabels.contains(expectedLabelsAndErrorMessagesList.get(i).get(0)));

            Debugger.println("Expected ErrorMessage Header " + expectedLabelsAndErrorMessagesList.get(i).get(1) + " count: " + i);
            Debugger.println("Actual ErrorMessage Header " + actualFieldErrorMessages.get(i - 1) + "\n");
            Assert.assertEquals(expectedLabelsAndErrorMessagesList.get(i).get(1), actualFieldErrorMessages.get(i - 1));

            Debugger.println("Expected ErrorMessage Colour " + expectedLabelsAndErrorMessagesList.get(i).get(2) + " count: " + i);
            expectedFontColorInRGB = StylesUtils.convertFontColourStringToCSSProperty(expectedLabelsAndErrorMessagesList.get(i).get(2));
            Debugger.println("Expected ErrorMessage Colour RGB " + expectedFontColorInRGB);
            Debugger.println("Actual ErrorMessage Colour RGB " + actualColourFieldErrorMessages.get(i - 1) + "\n");
            Assert.assertEquals(expectedFontColorInRGB, actualColourFieldErrorMessages.get(i - 1));
        }
    }

    @And("the user fill in the last name field")
    public void theUserFillInTheLastNameField() {
        patientDetailsPage.fillInLastName();
    }

    @And("the sub-heading title is displayed {string}")
    public void theSubHeadingTitleIsDisplayed(String expectedSubHeading) {
        String actualSubHeading = Objects.requireNonNull(Actions.getText(patientDetailsPage.subPageTitle)).trim();
        expectedSubHeading = expectedSubHeading.trim();
        Debugger.println("Expected Patient Details sub-heading : " + expectedSubHeading);
        Debugger.println("Actual Patient Details sub-heading : " + actualSubHeading);
        Assert.assertEquals(expectedSubHeading, actualSubHeading);
    }

    @Then("the user fills in all fields without NHS number, enters a reason for noNhsNumber {string} and leaves HospitalNo field blank")
    public void theUserFillsInAllFieldsWithoutNHSNumberEntersAReasonForNoNhsNumberAndLeavesHospitalNoFieldBlank(String reasonForNoNHSNo) {
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNo);
        Actions.clearInputField(patientDetailsPage.hospitalNumber);
    }

    @And("the NHS number field is displayed")
    public void theNHSNumberFieldIsDisplayed() {
        boolean nhsFieldDisplayed;
        nhsFieldDisplayed = Wait.isElementDisplayed(driver, patientDetailsPage.nhsNumber, 5);
        Assert.assertTrue(nhsFieldDisplayed);
        Debugger.println("NHS Number field is displayed");
    }


    @Then("the user fills in all fields with the NHS number and leaves HospitalNo blank")
    public void theUserFillsInAllFieldsWithTheNHSNumberAndLeavesHospitalNoBlank() {
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithNHSNumber("N/A");
        Actions.clearInputField(patientDetailsPage.hospitalNumber);
    }


    @Then("the user fills in all fields and leaves NHS Number and HospitalNo fields blank")
    public void theUserFillsInAllFieldsAndLeavesNHSNumberAndHospitalNoFieldsBlank() {
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithNHSNumber("N/A");
        Actions.clearInputField(patientDetailsPage.hospitalNumber);
        Actions.clearInputField(patientDetailsPage.nhsNumber);
    }

    @When("the user fills in the NHS Number field")
    public void theUserFillsInTheNHSNumberField() {
        patientDetailsPage.fillInNHSNumber();
    }

    @When("the user fills in all the fields with NHS number on the New Patient page")
    public void theUserFillsInAllTheFieldsWithNHSNumberOnTheNewPatientPage() {
        patientDetailsPage.fillInAllNewPatientDetails();
        patientDetailsPage.fillInNHSNumber();
    }

    @And("the message displayed on the notification banner is {string}")
    public void theMessageDisplayedOnTheNotificationBannerIs(String expectedTextOnBanner) {
        String actualTextOnBanner = Actions.getText(patientDetailsPage.textOnPatientDetailsNotificationBanner);
        Debugger.println("Expected Text on Banner : " + expectedTextOnBanner);
        Debugger.println("Actual Text on Banner : " + actualTextOnBanner);
        Assert.assertEquals(expectedTextOnBanner, actualTextOnBanner);
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
        patientDetailsPage.editDropdownField(patientDetailsPage.lifeStatusButton, lifeStatus);
    }

    @And("the user fills in the date of birth {string}")
    public void theUserFillsInTheDateOfBirth(String dateOfBirth) {
        Actions.fillInValue(patientDetailsPage.dateOfBirth, dateOfBirth);
    }

    @And("the date of death input field is displayed")
    public void theDateOfDeathInputFieldIsDisplayed() {
        boolean inputFieldStatus;
        inputFieldStatus = Wait.isElementDisplayed(driver, patientDetailsPage.dateOfDeath, 10);
        Assert.assertTrue(inputFieldStatus);
    }

    @When("the user deletes the content of the Ethnicity field")
    public void theUserDeletesTheContentOfTheEthnicityField() {
        Wait.forElementToBeDisplayed(driver, patientDetailsPage.ethnicityButton);
        if (Wait.isElementDisplayed(driver, patientDetailsPage.clearEthnicityDropDownValue, 10)) {
            Wait.seconds(1);
            Actions.retryClickAndIgnoreElementInterception(driver,patientDetailsPage.clearEthnicityDropDownValue);
            Debugger.println("Content of Ethnicity field is now deleted: " + Actions.getText(patientDetailsPage.ethnicityButton));
            Actions.retryClickAndIgnoreElementInterception(driver,patientDetailsPage.hospitalNumber);// click om an element field to trigger error on ethnicity button
            Wait.seconds(1); // Wait for the error to be triggered after deleting drop-down value
        }

    }

    @When("the selects the ethnicity as {string}")
    public void theSelectsTheEthnicityAs(String ethnicity) {
        patientDetailsPage.editDropdownField(patientDetailsPage.ethnicityButton, ethnicity);
    }

    @And("the Relationship to Proband from the patient referral card is {string}")
    public void theRelationshipToProbandFromThePatientReferralCardIs(String expectedRelationShipToProband) {
        String actualRelationShipToProband = Actions.getText(patientDetailsPage.referralProbandRelationShipStatus);
        Debugger.println("actual relationShip : " + actualRelationShipToProband);
        Debugger.println("Expected relationShip : " + expectedRelationShipToProband);
        Assert.assertEquals(expectedRelationShipToProband,actualRelationShipToProband);

    }

    @When("the user fills in the Ethnicity field {string}")
    public void theUserFillsInTheEthnicityField(String ethnicity) {
        patientDetailsPage.addPatientEthnicity(ethnicity);
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
                String actualTumourDescription = Actions.getValue(tumoursPage.descriptiveName).trim();
                Debugger.println("Actual actualTumourDescription :" + actualTumourDescription + " : " + actualTumourDescription.length());
                Assert.assertEquals(actualTumourDescription.length(), maximumCharactersAllowed);
                break;
            }
            case "referralNotes": {
                String actualReferralNotes = Actions.getValue(notesPage.addNoteField).trim();
                Debugger.println("Actual actualReferralNotes :" + actualReferralNotes + " : " + actualReferralNotes.length());
                Assert.assertEquals(actualReferralNotes.length(), maximumCharactersAllowed);
                break;
            }
            default:
                throw new IllegalArgumentException("Invalid query search parameters");
        }

    }

    @And("the user deletes data in the NHS Number field")
    public void theUserDeletesDataInTheNHSNumberField() {
        Actions.clearInputField(patientDetailsPage.nhsNumber);
    }

    @And("the user deletes the data in the Hospital Number field")
    public void theUserDeletesTheDataInTheHospitalNumberField() {
        Actions.clearInputField(patientDetailsPage.hospitalNumber);
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
        patientDetailsPage.fillInAllMandatoryPatientDetailsWithoutMissingNhsNumberReason(reasons);


    }

    @And("the user fills in the HospitalNo field")
    public void theUserFillsInTheHospitalNoField() {
        patientDetailsPage.fillInHospitalNo();
    }

    @And("the Hospital number field displays the hint text {string}")
    public void theHospitalNumberFieldDisplaysTheHintText(String expectedHintText) {
        String actualHintText = Actions.getPlaceHolderAttribute(patientDetailsPage.hospitalNumber);
        Debugger.println("Actual Hint text  " + actualHintText);
        Debugger.println("Expected Hint text " + expectedHintText);
        Assert.assertEquals(expectedHintText, actualHintText);
    }

    @And("the user deletes data in the fields - First Name, Last Name, Date of Birth, Gender, Life Status and Ethnicity")
    public void theUserDeletesDataInTheFieldsFirstNameLastNameDateOfBirthGenderLifeStatusAndEthnicity() {

        Actions.clearInputField(patientDetailsPage.firstName);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.firstName);
        Actions.clearInputField(patientDetailsPage.familyName);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.familyName);
        patientDetailsPage.dateOfBirth.click();
        Actions.clearInputField(patientDetailsPage.dateOfBirth);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.administrativeGenderButton);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.clearGenderDropDownValue);
        Wait.seconds(1);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.lifeStatusButton);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.clearLifeStatusDropDown);
        Wait.seconds(1);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.ethnicityButton);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.clearLifeStatusDropDownValue);

    }

    @And("the user fills in all the fields without NHS number and enter a reason for noNhsNumber {string}")
    public void theUserFillsInAllTheFieldsWithoutNHSNumberAndEnterAReasonForNoNhsNumber(String reasonForNoNHSNo) {
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNo);
    }

    @And("the user fill in the first name field")
    public void theUserFillInTheFirstNameField() {
        patientDetailsPage.fillInFirstName();
    }

    @And("the user deletes the pre-populated fields - First Name, Last Name, Date of Birth, Gender, and PostCode")
    public void theUserDeletesThePrePopulatedFieldsFirstNameLastNameDateOfBirthGenderAndPostCode() {

        Actions.clearInputField(patientDetailsPage.firstName);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.firstName);
        Actions.clearInputField(patientDetailsPage.familyName);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.familyName);
        patientDetailsPage.dateOfBirth.click();
        Actions.clearInputField(patientDetailsPage.dateOfBirth);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.administrativeGenderButton);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.clearGenderDropDownValue);
        Wait.seconds(1);
        Actions.retryClickAndIgnoreElementInterception(driver, patientDetailsPage.postcode);
        Actions.clearInputField(patientDetailsPage.postcode);
    }
}