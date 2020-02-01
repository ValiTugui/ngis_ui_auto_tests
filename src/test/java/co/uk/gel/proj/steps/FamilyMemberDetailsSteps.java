package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.pages.FamilyMemberDetailsPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.TestUtils;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FamilyMemberDetailsSteps extends Pages {

    Faker faker = new Faker();

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

    @When("the user selects the Relationship to proband as {string}")
    public void theUserFillsTheFamilyMemberDetailsPageWithThe(String relationToProband) {
        //To fill ethnicity also, as this field made mandatory.
        patientDetailsPage.editDropdownField(patientDetailsPage.ethnicityButton, "A - White - British");
        familyMemberDetailsPage.fillTheRelationshipToProband(relationToProband);
     }

    @And("the user selects the test to add to the family member {string}")
    public void theFamilyMemberDetailsWithTheSelectedTestAreAddedToTheReferral(String nhsDetails) {
        boolean testResult = false;
        NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(nhsDetails);
        if(familyMember == null){
            Debugger.println("Family Member:"+nhsDetails+" not found in the added list!");
            Assert.assertTrue(false);
        }
        testResult = familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember(familyMember);
        Assert.assertTrue(testResult);
    }

    @When("the user fills the DiseaseStatusDetails for family member with the with the {string}")
    public void theUserFillsTheDiseaseStatusDetailsForFamilyMember(String diseaseStatus) {
        familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(diseaseStatus);
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
    @Then("confirm family member details page populate with same details found in patient card for {string}")
    public void theFamilyDetailsPagePopulateWithSameDetailsAsInPatientCard(String memberDetails) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyPopulatedDetailsForFamilyMember(memberDetails);
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
    @And("verify the patient card displays the same NHS and DOB in {string}")
    public void thePatientCardDisplaysTheSameNHSAndDOBSUedForSearching(String familyDetails) {
        boolean testResult = false;
        testResult = patientSearchPage.verifyNHSAndDOBInPatientCard(familyDetails);
        Assert.assertTrue(testResult);
    }

    @And("the back button should not be present")
    public void theBackButtonShouldNotBePresent() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyThePresenceOfBackButton(false);
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
//
//    @Then("the message will be displayed as {string} in {string} in new patient page")
//    public void theMessageWillBeDisplayedAsInInNewPatientPage(String errorMessage, String fontColor) {
//        boolean testResult = false;
//        testResult = familyMemberNewPatientPage.checkTheErrorMessageForMandatoryFields(errorMessage, fontColor);
//        Assert.assertTrue(testResult);
//    }

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

    @When("the user clicks on back button")
    public void clicksOnBackButton() {
        familyMemberDetailsPage.clickOnBackButton();
    }

    @And("the color of referral name for {string} displays as {string}")
    public void theEditingReferralColorInRed(String nhsDetails,String color) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheEditingReferralColor(nhsDetails,color);
        Assert.assertTrue(testResult);
    }
    @When("the user edits to complete the highlighted family member")
    public void theUserShouldEditToCompleteTheHighlightedFamilyMember() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.editSpecificFamilyMember(0);
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



    @Then("the user should not see the removal message on the family member landing page")
    public void theUserDoesNotSeeTheRemovalMessageOnTheFamilyMemberLandingPage() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheDeleteMessageIsNotPresent();
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
    @Then("the user accept the alert with message (.*)")
    public void verifyFamlyMemberRemovalAlert(String alertMessage) {
        familyMemberDetailsPage.verifyAlertMessageOnRemoval(alertMessage);
    }

    @Then("the user should be able to see {string} removal message on the family member landing page")
    public void theUserShouldBeAbleToSeeRemovalMessageOnTheFamilyMemberLandingPage(String deleteMessage) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheDeleteMessage(deleteMessage);
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on participant amendment link to amend the number of participants")
    public void theUserClicksOnParticipantAmendmentLinkToAmendTestPackage() {
        familyMemberDetailsPage.clicksOnParticipantAmendmentLink();
    }

    @And("the user should see a warning message displayed as {string}")
    public void theUserShouldSeeAWarningMessageDisplayedAs(String expectedMessage) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.unmatchedParticipantErrorMessage(expectedMessage);
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
        patientChoicePage.clickOnContinue();
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

    @And("the user will see the patient choice status in family member page as {string}")
    public void theUserWillSeeThePatientChoiceStatus(String status) {
        familyMemberDetailsPage.patientChoiceStatus(status);
    }

    @And("the global patient information bar display with the editing members information {string}")
    public void theFamilyMemebrBannerShouldDisplayWithTheEditingMembersInformation(String nhsDetails) {
        boolean testResult = false;
        NGISPatientModel familyMember = familyMemberDetailsPage.getFamilyMember(nhsDetails);
        if(familyMember == null){
            Debugger.println("FamilyMemer with NHS "+nhsDetails+" Could not found.");
            Assert.assertTrue(testResult);
        }
        testResult = referralPage.verifyGlobalPatientInformationBar(familyMember);
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
    @And("the user reads the patient details in family member landing page")
    public void theUserShouldBeAbleToSeeThePatientDetailsInFamilyMemberLandingPage() {
        familyMemberDetailsPage.readPatientDetailsInFamilyMemberLandingPage();
    }

    @And("the user reads the patient details in patient choice page")
    public void theUserShouldBeAbleToSeeThePatientDetailsInPatientChoicePage() {
        familyMemberDetailsPage.readPatientDetailsInPatientChoicePage();
    }

    @And("the user should see same set of family member identifiers in family member landing page and patient choice page")
    public void compareTheFamilyIdentifiersInFamilyMemberLandingPageAndPatientChoicePage() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.compareFamilyIdentifiersOnFamilyMemberAndPatientChoice();
        Assert.assertTrue(testResult);
    }

    @And("the user reads the patient details in print forms page")
    public void theUserShouldBeAbleToSeeThePatientDetailsInPrintFormsPage() {
        familyMemberDetailsPage.readPrintFormsInPatientChoicePage();
    }

    @Then("the user should see same data in family member landing page and print forms page")
    public void theUserShouldSeeSameDataFromFamilyMemberLandingPageAndPrintFormsPage() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.compareDataFromFamilyMemberAndPrintForms();
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

    @When("the user adds {string} family members to the proband patient as new family member patient record with below details")
    public void theUserAddFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        try {
            int noOfParticipants = Integer.parseInt(noParticipant);
            List<List<String>> memberDetails = inputDetails.asLists();
            if(memberDetails.size() < noOfParticipants){
                Debugger.println("No of Participants mentioned and details provided are not matching.");
            }
            String nhsNumber = "";
            for (int i = 1; i < memberDetails.size(); i++) {
                referralPage.navigateToFamilyMemberSearchPage();
                HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(memberDetails.get(i).get(0));
                //Verify whether the search with or without NHS
                nhsNumber = paramNameValue.get("NHSNumber");
                if(nhsNumber.equalsIgnoreCase("NA")){
                    NGISPatientModel familyMember = new NGISPatientModel();
                    familyMember.setNHS_NUMBER(RandomDataCreator.generateRandomNHSNumber());
                    familyMember.setDATE_OF_BIRTH(paramNameValue.get("DOB"));
                    patientSearchPage.fillInNHSNumberAndDateOfBirth(familyMember);
                    patientSearchPage.clickSearchButtonByXpath(driver);
                    patientSearchPage.getPatientSearchNoResult();
                    patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
                    patientDetailsPage.newPatientPageIsDisplayed();
                    familyMember.setNO_NHS_REASON("Patient is a foreign national");
                    familyMember.setGENDER(paramNameValue.get("Gender"));
                    familyMember.setRELATIONSHIP_TO_PROBAND(paramNameValue.get("Relationship"));
                    if(paramNameValue.get("Ethnicity") != null){
                        familyMember.setETHNICITY(paramNameValue.get("Ethnicity"));
                    }else{
                        familyMember.setETHNICITY("A - White - British");
                    }
                    patientDetailsPage.createNewFamilyMember(familyMember);
                    patientDetailsPage.patientIsCreated();
                    Wait.seconds(5);
                    referralPage.updatePatientNGSID(familyMember);
                }else {
                    familyMemberSearchPage.searchFamilyMemberWithGivenParams(memberDetails.get(i).get(0));
                    if (!familyMemberDetailsPage.verifyPatientRecordDetailsDisplay(memberDetails.get(i).get(1))) {
                        Debugger.println("Patient already added...continuing with next.");
                        continue;
                    }
                    familyMemberDetailsPage.clickPatientCard();
                    familyMemberDetailsPage.fillTheRelationshipToProband(memberDetails.get(i).get(1));
                    referralPage.clickSaveAndContinueButton();
                }
                Wait.seconds(2);
                NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
                if(familyMember == null){
                    Debugger.println("Family Member:"+memberDetails.get(i).get(0)+" not found in the added list!");
                    Assert.assertTrue(false);
                }
                if(!familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember(familyMember)){
                    Assert.assertFalse("Family Member "+memberDetails.get(i).get(0)+" Not added.",true);
                }
                Wait.seconds(2);
                referralPage.clickSaveAndContinueButton();
                Wait.seconds(2);
                clinicalQuestionsPage.fillDiseaseStatusAgeOfOnsetAndHPOTerm(memberDetails.get(i).get(2));
                //Adding Phenotypic and Karyotypic sex also as it is needed in Pedigree validation
                if(familyMember.getPHENOTYPIC_SEX() == null){
                    familyMember.setPHENOTYPIC_SEX(familyMember.getGENDER());//By default same as Gender
                }
                clinicalQuestionsPage.selectSpecificPhenotypicSexDropdownValue(familyMember.getPHENOTYPIC_SEX());
                clinicalQuestionsPage.selectSpecificKaryotypicSexDropdownValue("XY");
                Wait.seconds(2);
                referralPage.clickSaveAndContinueButton();
                Wait.seconds(2);
                if(!familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage(memberDetails.get(i).get(0))){
                    Debugger.println("Details of Added family member not displayed as expected in FamilyMember Landing Page.");
                    Assert.assertTrue(false);
                }
                Wait.seconds(2);
            }//end
        }catch(Exception exp){
            Debugger.println("FamilyMemberDetailsSteps: Exception in Filling the Family Member Details: "+exp);
            Assert.assertTrue("FamilyMemberDetailsSteps: Exception in Filling the Family Member Details: ",false);
        }
    }

    @And("the Relationship to Proband drop-down is allowed to have values up to {string}")
    public void theRelationshipToProbandDropDownIsAllowedToHaveValuesUpTo(String allowedValuesCount) {
        Assert.assertTrue(familyMemberDetailsPage.verifyMaxAllowedValuesInRelationshipToProbandField(Integer.parseInt(allowedValuesCount)));
    }

    @When("^the user navigates to \"([^\"]*)\" stage$")
    public void navigateTOSpecificStage(String stage) {
        Debugger.println("Stage: "+stage+" Starting.");
        referralPage.navigateToStage(stage);
    }
    @Then("the user should {string} participant error message as {string}")
    public void theUserShouldParticipantErrorMessageAs(String expStatus, String errorMessage) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.unmatchedParticipantErrorMessage(errorMessage);
        if (expStatus.equalsIgnoreCase("get")) {
            Assert.assertTrue(testResult);
        }else {
            Assert.assertFalse(testResult);
        }
    }
}//end
