package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
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

    @When("clicks the Save and Continue button in family member details page")
    public void theUserClicksTheSaveAndContinueButtonInFamilyMemberDetailsPage() {
        familyMemberDetailsPage.clickOnSaveAndContinueButton();
    }

    @Then("the message will be displayed as {string} in {string} for the invalid field in family member details page")
    public void theMessageWillBeDisplayedAsInForTheInvalidFieldInFamilyDetailsPage(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheErrorMessageDisplay(errorMessage, messageColor);
        Assert.assertTrue(testResult);
        Actions.scrollToTop(driver);
    }


    @Then("the message will be displayed as {string} result found")
    public void theMessageWillBeDisplayedAsResultFound(String resultMessage) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheResultMessageForFamilyMember(resultMessage);
        Assert.assertTrue(testResult);
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
        testResult = familyMemberDetailsPage.verifyTheErrorMessageDisplay(errorMessage,messageColor);
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
    public void theFamilyMemberDetailsWithTheSelectedTestAreAddedToTheReferral() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember();
        Assert.assertTrue(testResult);
    }
    @And("the user can select the test to add to the family member")
    public void theFamilyMemberDetailsTheSelectedTestAreAddedToTheReferral() {
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

    @Then("the user is navigated to a page with title (.*)")
    public void theUserIsNavigatedToAPageWithTitleConfirmFamilyMemberDetails(String title) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyThePageTitlePresence(title);
        Assert.assertTrue(testResult);
    }
    @Then("the user should be able to see test package for family member is selected by default")
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
        testResult = familyMemberNewPatientPage.checkTheErrorMessageForMandatoryFields(errorMessage,fontColor);
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
    @Then("the user should be able to see family member's details card")
    public void theUserShouldBeAbleToSeeFamilyMemberSDetailsCard() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyThePatientCardField();
        Assert.assertTrue(testResult);

    }

    @Then("the user should be able to see which patient in my referral currently editing")
    public void theUserShouldBeAbleToSeeWhichPatientInMyReferralCurrentlyEditing() {
        familyMemberDetailsPage.verifyThePatientByEdited();
    }

    @Then("the family member test package page is correctly displayed")
    public void theFamilyMemberTestPackagePageIsCorrectlyDisplayed() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheElementsOnFamilyMemberTestPackagePage();
        Assert.assertTrue(testResult);
    }
}//end
