package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
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

    @And("the user selects the patient search result tab")
    public void theUserSelectsThePatientSearchResultTab() {
        familyMemberDetailsPage.clickPatientCard();
    }

    @When("the user clicks the Save and Continue button in family member details page")
    public void theUserClicksTheSaveAndContinueButtonInFamilyMemberDetailsPage() {
        familyMemberDetailsPage.clickOnSaveAndContinueButton();
    }
    @Then("the message will be displayed as {string} in {string} for the invalid field in family member details page")
    public void theMessageWillBeDisplayedAsInForTheInvalidFieldInFamilyDetailsPage(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.checkTheErrorMessageForInvalidField(errorMessage,messageColor);
        Assert.assertTrue(testResult);
    }

    @And("the user fills the FamilyMemberDetailsPage with the {string}")
    public void theUserFillsTheFamilyMemberDetailsPageWithThe(String relationToProband) {
        familyMemberDetailsPage.fillTheRelationshipToProband(relationToProband);
    }

    @Then("the family member details with the selected test are added to the referral")
    public void theFamilyMemberDetailsWithTheSelectedTestAreAddedToTheReferral() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember();
        Assert.assertTrue(testResult);
    }

    @And("the user fills the DiseaseStatusDetails for family member with the with the {string}")
    public void theUserFillsTheDiseaseStatusDetailsForFamilyMember(String searchDetails) {
        familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(searchDetails);
    }

    @Then("the user returns to family member landing page with the added family member details")
    public void theUserReturnsToFamilyMemberLandingPageWithTheAddedFamilyMemberDetails() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage();
        Assert.assertTrue(testResult);
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

}//end
