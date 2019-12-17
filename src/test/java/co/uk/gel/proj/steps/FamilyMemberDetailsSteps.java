package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

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
            //It is observed that sometimes click on the button was not happening properly.. trying again.
            referralPage.clickSaveAndContinueButton();
            testResult = referralPage.verifyTheErrorMessageDisplay(errorMessage, messageColor);
        }
        Assert.assertTrue(testResult);
    }

    @When("the user fills the FamilyMemberDetailsPage with the {string}")
    public void theUserFillsTheFamilyMemberDetailsPageWithThe(String relationToProband) {
        familyMemberDetailsPage.fillTheRelationshipToProband(relationToProband,"");
    }

    @And("the user can select the test to add to the family member {string}")
    public void theFamilyMemberDetailsWithTheSelectedTestAreAddedToTheReferral(String nhsDetails) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember(nhsDetails);
        Assert.assertTrue(testResult);
    }

    @When("the user fills the DiseaseStatusDetails for family member with the with the {string}")
    public void theUserFillsTheDiseaseStatusDetailsForFamilyMember(String searchDetails) {
        familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(searchDetails);
    }

    @Then("the user returns to family member landing page with the added family member details {string}")
    public void theUserReturnsToFamilyMemberLandingPageWithTheAddedFamilyMemberDetails(String nhsDetails) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage(nhsDetails);
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
        testResult = familyMemberDetailsPage.verifyPatientRecordDetailsDisplay("");
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

    @Then("the user clicks on a test that is selected and the test is no longer selected")
    public void theUserClicksOnATestThatIsSelectedAndTheTestIsNoLongerSelected() {
        familyMemberDetailsPage.deselectCheckBoxOnFamilyPage();
    }
    @And("there is a message displayed on top of landing page stating {string}")
    public void thereIsAMessageDisplayedOnTopOfLandingPageStating(String subTitlemsg) {
        familyMemberDetailsPage.subTitleMessage(subTitlemsg);
    }
    @Then("The user should be able to see details like name,relationship with proband,Date of birth,Gender,NHS No & Patient NGIS ID for all the family members added.")
    public void theUserShouldBeAbleToSeeDetailsLikeNameRelationshipWithProbandDateOfBirthGenderNHSNoPatientNGISIDForAllTheFamilyMembersAdded() {
        familyMemberDetailsPage.verifyTheElementsOnFamilyMemberPage();

    }
    @Then("the user should be able to see the patient details in family member landing page")
    public void theUserShouldBeAbleToSeeThePatientDetailsInFamilyMemberLandingPage() {
        familyMemberDetailsPage.patientDetailsInFamilyMemberLandingPage();
    }

    @And("the user should be able to see the patient details in patient choice page")
    public void theUserShouldBeAbleToSeeThePatientDetailsInPatientChoicePage() {
        familyMemberDetailsPage.patientDetailsInPatientChoicePage();
    }

    @Then("the user should verify the data from family member landing page and patient choice page")
    public void theUserShouldVerifyTheDataFromFamilyMemberLandingPageAndPatientChoicePage() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyDataFromFamilyMemberAndPatientChoice();
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see the patient details in print forms page")
    public void theUserShouldBeAbleToSeeThePatientDetailsInPrintFormsPage() {
        familyMemberDetailsPage.printFormsInPatientChoicePage();
    }

    @Then("the user should verify the data from family member landing page and print forms page")
    public void theUserShouldVerifyTheDataFromFamilyMemberLandingPageAndPrintFormsPage() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyDataFromFamilyMemberAndPrintForms();
        Assert.assertTrue(testResult);
    }

    @And("the family member status {string} Marked in {string}")
    public void theUserShouldBeAbleToSeeIfTheFamilyMemberIsMarkedIn(String testfield, String color) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.testedFieldColor(testfield, color);
        Assert.assertTrue(testResult);
    }
    @And("the user should be able to view patient choice status for all the family members added.")
    public void theUserShouldBeAbleToViewPatientChoiceStatusForAll() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.patientChoiceStatusDetail();
        Assert.assertTrue(testResult);
    }
    @And("The user also should see the Add Family Member button and continue button displayed")
    public void theUserAlsoShouldSeeTheAddFamilyMemberButtonToAddOne() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.addFamilyMemberAndContinueButtonIsDisplayed();
        Assert.assertTrue(testResult);
    }
    @And("The user should also see the separate edit or delete icon under every family member details provided.")
    public void theUserShouldAlsoSeeTheSeparateEditOrDeleteIconUnderEveryFamilyMemberDetailsProvided() {
        familyMemberDetailsPage.editAndDeleteButtonDisplay();
    }
    @And("The user should be able to view patient choice status for all the family members added.")
    public void theUserShouldBeAbleToViewPatientChoiceStatusForAllTheFamilyMembersAdded() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.patientChoiceStatusDetail();
        Assert.assertTrue(testResult);
    }

    @And("There is a message displayed on top of landing page stating {string}")
    public void thereIsAMessageDisplayedOnTopOfLanding(String subTitlemsg) {
        familyMemberDetailsPage.subTitleMessage(subTitlemsg);

    }

    @When("the user adds {string} family members with the below details")
    public void theUserAddFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        try {
            int noOfParticipants = Integer.parseInt(noParticipant);
            List<List<String>> memberDetails = inputDetails.asLists();
            if(memberDetails.size() != noOfParticipants){
                Debugger.println("No of Participants mentioned and details provided are not matching.");
                return;
            }
            for (int i = 1; i < memberDetails.size(); i++) {
                Debugger.println("Searching and Adding Family member: "+memberDetails.get(i).get(0));
                referralPage.navigateToFamilyMemberSearchPage();
                familyMemberSearchPage.searchFamilyMemberWithGivenParams(memberDetails.get(i).get(0));
                Debugger.println("Verifying Patient details.");
                if(!familyMemberDetailsPage.verifyPatientRecordDetailsDisplay(memberDetails.get(i).get(1))){
                    Debugger.println("Patient already added...");
                    continue;
                }
                Debugger.println("Clicking on Patient Card.");
                familyMemberDetailsPage.clickPatientCard();
                Debugger.println("Filling RelationShip to Proband");
                familyMemberDetailsPage.fillTheRelationshipToProband(memberDetails.get(i).get(1),memberDetails.get(i).get(0));

                Debugger.println("Filling RelationShip to Proband, Done");
                //referralPage.clickSaveAndContinueButton();
                familyMemberDetailsPage.clickOnSaveAndContinueButton();
                Debugger.println("continuing...");
                if(!familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember(memberDetails.get(i).get(0))){
                    Assert.assertFalse("Family Member "+memberDetails.get(i).get(0)+" Not added.",true);
                }
                Debugger.println("Verified details..");
                familyMemberDetailsPage.clickOnSaveAndContinueButton();
                Debugger.println("Continuing to Disease status filling..........");
                familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(memberDetails.get(i).get(2));
                Debugger.println("Filled Disease Status Details........");
                referralPage.clickSaveAndContinueButton();
                Debugger.println("Continuing.to verify family details in landing page..........");
                familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage(memberDetails.get(i).get(0));
                Debugger.println("DONE...........");
            }//end
        }catch(Exception exp){
            Debugger.println("FamilyMemberDetailsSteps: Exception in Filling the Family Member Details: "+exp);
        }
    }

    @And("the Relationship to Proband drop-down is allowed to have values up to {string}")
    public void theRelationshipToProbandDropDownIsAllowedToHaveValuesUpTo(String allowedValuesCount) {
        Assert.assertTrue(familyMemberDetailsPage.verifyMaxAllowedValuesInRelationshipToProbandField(Integer.parseInt(allowedValuesCount)));
    }

}//end
