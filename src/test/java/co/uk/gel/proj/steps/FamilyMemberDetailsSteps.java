package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.StylesUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FamilyMemberDetailsSteps extends Pages {

    public FamilyMemberDetailsSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("^the user types in valid details of a patient in the NHS number \"([^\"]*)\" and Date of Birth \"([^\"]*)\" fields$")
    public void theUserTypesInValidDetailsOfAPatientInTheNHSNumberAndDateOfBirthFields(String nhsNo, String dob) throws Throwable {
        familyMemberDetailsPage.searchPatientDetailsUsingNHSNumberAndDOB(nhsNo, dob);
    }

    @Then("the message will be displayed as {string} in {string} for the invalid field in family member details page")
    public void theMessageWillBeDisplayedAsInForTheInvalidFieldInFamilyDetailsPage(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = referralPage.verifyTheErrorMessageDisplay(errorMessage, messageColor);
        Assert.assertTrue(testResult);
        Actions.scrollToTop(driver);
    }


    @And("^the user search a patient with valid NHS number \"([^\"]*)\" and Date of Birth \"([^\"]*)\" fields$")
    public void theUserSearchAPatientInTheNHSNumberAndDateOfBirthFields(String nhsNo, String dob) throws Throwable {
        familyMemberDetailsPage.searchPatientDetailsUsingNHSNumberAndDOB(nhsNo, dob);
    }

    @When("the user clicks on the patient card")
    public void theUserSelectsThePatientSearchResultTab() {
        familyMemberDetailsPage.clickPatientCard();
    }

    @Then("the message displays as {string} in color {string}")
    public void theMessageDisplaysAsGivenInSpecifiedColor(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = referralPage.verifyTheErrorMessageDisplay(errorMessage, messageColor);
        if(!testResult){
            //IT is observed that click on the button was not happening properly.. trying again.
            referralPage.clickSaveAndContinueButton();
            testResult = referralPage.verifyTheErrorMessageDisplay(errorMessage, messageColor);
        }
        Assert.assertTrue(testResult);
    }

    @When("the user fills the FamilyMemberDetailsPage with the {string}")
    public void theUserFillsTheFamilyMemberDetailsPageWithThe(String relationToProband) {
        familyMemberDetailsPage.fillTheRelationshipToProband(relationToProband);
    }

    @And("reads the details of selected family member {string}")
    public void readsTheDetailsOfSelectedMember(String relationToProband) {
        familyMemberDetailsPage.readFamilyMemberDetailsFor(relationToProband);
    }

    @Then("the family member details with the selected test are added to the referral")
    public void theFamilyMemberDetailsWithTheSelectedTestAreAddedToReferral() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember();
        Assert.assertTrue(testResult);
    }

    @And("the user can select the test to add to the family member")
    public void theFamilyMemberDetailsWithTheSelectedTestAreAddedToTheReferral() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember();
        Assert.assertTrue(testResult);
    }

    @When("the user fills the DiseaseStatusDetails for family member with the with the {string}")
    public void theUserFillsTheDiseaseStatusDetailsForFamilyMember(String searchDetails) {
        familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(searchDetails);
    }

    @Then("the user returns to family member landing page with the added family member details")
    public void theUserReturnsToFamilyMemberLandingPageWithTheAddedFamilyMemberDetails() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage();
        Assert.assertTrue(testResult);
    }

    @And("the display title of the family member details page is {string}")
    public void theDisplayTitleOfTheFamilyMemberDetailsPageIs(String familyMemeberDeatailsPageTitle) {
        familyMemberDetailsPage.verifyTheTitleOfTheFamilyMemberDetailsPage(familyMemeberDeatailsPageTitle);
    }

    @Then("the default family member details page is correctly displayed with the proper number of fields")
    public void theDefaultFamilyMemberDetailsPageIsCorrectlyDisplayedWithTheProperNumberOfFields() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheElementsOnFamilyMemberDetailsPage();
        Assert.assertTrue(testResult);
    }

    @And("the user removes the data from all fields {string} in the family member details page")
    public void theUserRemovesTheDataFromAllFieldsInTheFamilyMemberDetailsPage(String clearDropdown) {
        familyMemberDetailsPage.removeFetchedDataInFamilyMemberDetailsPage(clearDropdown);
    }

    @Then("the default family member details page is correctly displayed")
    public void theDefaultFamilyMemberDetailsPageIsCorrectlyDisplayed() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheElementsOnFamilyMemberDetailsPage();
        Assert.assertTrue(testResult);
    }

    @Then("the patient card displays with Born,Gender and NHS No details")
    public void thePatientCardDisplaysWithBornGenderAndNHSNoDetails() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyPatientRecordDetailsDisplay();
        Assert.assertTrue(testResult);
    }



    @And("the user should be able to see test package for family member is selected by default")
    public void theUserShouldBeAbleToSeeTestPackageForFamilyMemberIsSelectedByDefault() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestCheckboxIsSelected();
        Assert.assertTrue(testResult);
    }

    @And("the new patient page is correctly displayed with expected fields")
    public void theNewPatientPageIsCorrectlyDisplayedWithExpectedFields() {
        boolean testResult = false;
        testResult = familyMemberNewPatientPage.verifyTheElementsOnFamilyMemberNewPatientPage();
        Assert.assertTrue(testResult);
    }

    @When("the user removes the data from all fields {string} in the family member new patient page")
    public void theUserRemovesTheDataFromAllFieldsInTheFamilyMemberNewPatientPage(String clearDropdown) {
        familyMemberNewPatientPage.clearFieldsInFamilyMemberNewPatientPage(clearDropdown);
    }

    @And("the user clicks the Add new patient to referral button")
    public void theUserClicksTheAddNewPatientToReferralButton() {
        familyMemberNewPatientPage.clickOnAddNewPatientToReferral();
    }

    @Then("the message will be displayed as {string} in {string} in new patient page")
    public void theMessageWillBeDisplayedAsInInNewPatientPage(String errorMessage, String fontColor) {
        boolean testResult = false;
        testResult = familyMemberNewPatientPage.checkTheErrorMessageForMandatoryFields(errorMessage, fontColor);
        Assert.assertTrue(testResult);

    }

    @When("the user deselects the test")
    public void theUserDeselectTheSelectedTest() {
        familyMemberDetailsPage.deSelectTheTest();
    }

    @Then("the user sees test remains as deselected")
    public void theUserSeesTheTestRemainsUnSelected() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTestPackageCheckBoxDeSelected();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks the checkbox and Save and Continue button in family member test package page")
    public void theUserClicksTheCheckboxAndSaveAndContinueButtonInFamilyMemberTestPackagePage() {
        familyMemberDetailsPage.clickOnCheckBoxAndSaveAndContinueButton();
    }

    @And("the user navigates back to family member test package page to verify the test remains deselect")
    public void theUserNavigatesBackToFamilyMemberTestPackagePage() {
        familyMemberDetailsPage.clickOnBackButton();
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestCheckboxIsSelected();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on back button on family member details page")
    public void clicksOnBackButtonOnFamilyMemberDetailsPage() {
        familyMemberDetailsPage.clickOnBackButton();
    }

    @Then("the user should be able to see family member's details card")
    public void theUserShouldBeAbleToSeeFamilyMemberSDetailsCard() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyThePatientCardField();
        Assert.assertTrue(testResult);

    }

    @And("the editing referral color in Red")
    public void theEditingReferralColorInRed() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheEditingReferralColor();
        Assert.assertTrue(testResult);
    }

    @Then("the family member landing page is correctly displayed")
    public void theFamilyMemberLandingPageIsCorrectlyDisplayed() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheElementsOnFamilyMemberLandingPage();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on remove button to remove family member")
    public void theUserClicksOnRemoveButtonToRemoveFamilyMember() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.removeFamilyFromLandingPage();
        Assert.assertTrue(testResult);
    }

    @Then("the user sees {string} removal message on the family member landing page")
    public void theUserSeesASuccessRemovalMessageOnTheFamilyMemberLandingPage(String deleteMessage) {
        familyMemberDetailsPage.verifyTheDeleteMessage(deleteMessage);

    }

    @When("the user clicks on dustbin icon of the family member")
    public void theUserClicksOnDustbinIconOfTheFamilyMember() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.removeAFamilyMember();
        Assert.assertTrue(testResult);
    }

    @Then("the user should see mismatch message in selected and actual participant as {string}")
    public void theUserShouldSeeMismatchParticipantMessage(String errorMessage) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.unmatchedParticipantErrorMessage(errorMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the user should not see the removal message on the family member landing page")
    public void theUserDoesNotSeeTheRemovalMessageOnTheFamilyMemberLandingPage() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheDeleteMessageIsPresent();
        Assert.assertTrue(testResult);
    }

    @Then("the family member test package page is correctly displayed")
    public void theFamilyMemberTestPackPageIsCorrectlyDisplayed() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheElementsOnFamilyMemberTestPackagePage();
        Assert.assertTrue(testResult);
    }

    @Then("the family member details on family Member landing page is correctly displayed")
    public void theFamilyMemberDetailsOnFamilyMemberLandingPageIsCorrectlyDisplayed() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheDetailsOfFamilyMemberOnFamilyMemberPage();
        Assert.assertTrue(testResult);
    }

    @When("the user removes the family member")
    public void theUserRemoveTheFamilyMember() {
        familyMemberDetailsPage.removeAFamilyMember();
    }

    @Then("the user should be able to see {string} removal message on the family member landing page")
    public void theUserShouldBeAbleToSeeRemovalMessageOnTheFamilyMemberLandingPage(String deleteMessage) {
        familyMemberDetailsPage.verifyTheDeleteMessage(deleteMessage);
    }

    @When("the user clicks on participant amendment link to amend the number of participants")
    public void theUserClicksOnParticipantAmendmentLinkToAmendTestPackage() {
        familyMemberDetailsPage.clicksOnParticipantAmendmentLink();
    }

    @And("the user should see a warning message displayed as {string}")
    public void theUserShouldSeeAWarningMessageDisplayedAs(String expectedMessage) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.participantsNotMatchingMsg(expectedMessage);
        Assert.assertTrue(testResult);
    }

    @And("the deselected member status display as {string}")
    public void theDeselectedMemberStatusDisplay(String status) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyDeselectedPatientTestStatus(status);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see the patient identifiers on family member landing page")
    public void theUserShouldBeAbleToSeeThePatientIdentifiersOnFamilyMemberLandingPage() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyPatientIdentifiersInFamilyMemberLandingPage();
        Assert.assertTrue(testResult);
    }
    @When("the user clicks on the link to amend the number of participants for test")
    public void theUserClicksOnTheLinkToAmendTheNumberOfParticipantsForTest() {
        familyMemberDetailsPage.clickOnParticipantAmendmentLink();
    }

    @And("the user clicks on Continue Button")
    public void theUserClicksOnContinueButton() {
        familyMemberDetailsPage.clickOnContinueButton();
    }

    @And("the user should see an error message displayed as {string} in {string} color")
    public void theUserShouldSeeAMessageDisplayedAsInColor(String expectedMsg, String expectedColor) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.participantsErrorMessageCheck(expectedMsg, expectedColor);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see a warning message displayed as {string} in {string} color")
    public void theUserShouldSeeAWarningMessageDisplayedAsInColor(String expectedMsg, String expectedColor) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.participantsWarningMessageCheck(expectedMsg, expectedColor);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the patient choice status in family member page as {string}")
    public void theUserSeesThePatientChoiceStatus(String status) {
        familyMemberDetailsPage.patientChoiceStatus(status);
    }

    @And("the family member banner should display with the editing members information")
    public void theFamilyMemebrBannerShouldDisplayWithTheEditingMembersInformation() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyFamilyMemberBanner();
        Assert.assertTrue(testResult);
    }
    @When("the user clicks on edit icon to update patient choice status for family member")
    public void theUserClicksOnEditIconToUpdatePatientChoiceStatusForFamilyMember() {
        familyMemberDetailsPage.editPatientChoiceOfFamilyMember();
    }

    @When("the user moves back to previous page")
    public void theUserMovesBackToPreviousPage() {
        familyMemberDetailsPage.clickOnBackButton();
    }
}//end
