package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
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

    @When("the user clicks on the patient card")
    public void theUserSelectsThePatientSearchResultTab() {
        familyMemberDetailsPage.clickPatientCard();
    }

    @When("the user selects the Relationship to proband as {string}")
    public void theUserFillsTheFamilyMemberDetailsPageWithThe(String relationToProband) {
        //To fill ethnicity also, as this field made mandatory.
        patientDetailsPage.editDropdownField(patientDetailsPage.ethnicityButton, "A - White - British");
        familyMemberDetailsPage.fillTheRelationshipToProband(relationToProband);
     }
    @When("the user selects the Relationship to proband as {string} for family member {string}")
    public void theUserSelectRelationshipForFamilyMember(String relationToProband,String memberDetails) {
        //To fill ethnicity also, as this field made mandatory.
        patientDetailsPage.editDropdownField(patientDetailsPage.ethnicityButton, "A - White - British");
        familyMemberDetailsPage.fillTheRelationshipToProband(relationToProband);
        NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(memberDetails);
        if(familyMember != null){
            familyMember.setRELATIONSHIP_TO_PROBAND(relationToProband);
            FamilyMemberDetailsPage.updateRelationship(familyMember);
        }
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
        boolean testResult = false;
        testResult = familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(diseaseStatus);
        Assert.assertTrue(testResult);
    }

    @Then("the user returns to family member landing page with the added family member details {string}")
    public void theUserReturnsToFamilyMemberLandingPageWithTheAddedFamilyMemberDetails(String nhsDetails) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage(nhsDetails);
        Assert.assertTrue(testResult);
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

    @And("the user should be able to see test package for family member (.*) is selected by default")
    public void theUserShouldBeAbleToSeeTestPackageForFamilyMemberIsSelectedByDefault(String nhsDetails) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestCheckboxIsSelected(nhsDetails);
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

    @Then("the user should not see the removal message on the family member landing page")
    public void theUserDoesNotSeeTheRemovalMessageOnTheFamilyMemberLandingPage() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheDeleteMessageIsNotPresent();
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
    public void verifyFamilyMemberRemovalAlert(String alertMessage) {
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

    @And("the deselected member {string} status display as {string}")
    public void theDeselectedMemberStatusDisplay(String nhsDetails,String status) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyDeselectedPatientTestStatus(nhsDetails,status);
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

    @And("the global patient information bar display with the editing members information {string}")
    public void theFamilyMemberBannerShouldDisplayWithTheEditingMembersInformation(String nhsDetails) {
        boolean testResult = false;
        NGISPatientModel familyMember = familyMemberDetailsPage.getFamilyMember(nhsDetails);
        if(familyMember == null){
            Debugger.println("FamilyMember with NHS "+nhsDetails+" Could not found.");
            Assert.assertTrue(testResult);
        }
        testResult = referralPage.verifyGlobalPatientInformationBar(familyMember);
        Assert.assertTrue(testResult);
    }
    @When("the user clicks on edit icon to update patient choice status for family member")
    public void theUserClicksOnEditIconToUpdatePatientChoiceStatusForFamilyMember() {
        familyMemberDetailsPage.editPatientChoiceOfFamilyMember();
    }

    @Then("the user clicks on a test that is selected and the test is no longer selected")
    public void theUserClicksOnATestThatIsSelectedAndTheTestIsNoLongerSelected() {
        familyMemberDetailsPage.deselectCheckBoxOnFamilyPage();
    }

    @And("subtitle of the page displayed as (.*)")
    public void subtitleOfPageDisplayedAs(String subTitlemsg) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifySubTitleMessage(subTitlemsg);
        Assert.assertTrue(testResult);
    }
    @And("subtitle links as (.*)")
    public void subtitleLinkAs(String linkMessage) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifySubTitleLink(linkMessage);
        Assert.assertTrue(testResult);
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
        testResult = familyMemberDetailsPage.verifyTestBadgeBackgroundColor(testfield, color);
        Assert.assertTrue(testResult);
    }

    @And("The user also should see the Add Family Member button and continue button displayed")
    public void theUserAlsoShouldSeeTheAddFamilyMemberButtonToAddOne() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.addFamilyMemberAndContinueButtonIsDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("The user should be able to view patient choice status for all the family members added.")
    public void theUserShouldBeAbleToViewPatientChoiceStatusForAllTheFamilyMembersAdded() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.patientChoiceStatusDetail();
        Assert.assertTrue(testResult);
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
                if(nhsNumber != null && nhsNumber.equalsIgnoreCase("NA")){
                    NGISPatientModel familyMember = new NGISPatientModel();
                    familyMember.setNHS_NUMBER(RandomDataCreator.generateRandomNHSNumber());
                    familyMember.setDATE_OF_BIRTH(paramNameValue.get("DOB"));
                    familyMember.setGENDER(paramNameValue.get("Gender"));
                    familyMember.setRELATIONSHIP_TO_PROBAND(paramNameValue.get("Relationship"));
                    familyMember.setNO_NHS_REASON("Patient is a foreign national");
                    if(paramNameValue.get("Ethnicity") != null){
                        familyMember.setETHNICITY(paramNameValue.get("Ethnicity"));
                    }else{
                        familyMember.setETHNICITY("A - White - British");
                    }
                    patientSearchPage.fillInNHSNumberAndDateOfBirth(familyMember);
                    patientSearchPage.clickSearchButtonByXpath(driver);
                    if(patientSearchPage.getPatientSearchNoResult() == null){//Got error saying invalid NHS number, proceeding with No search in that case
                        if(patientSearchPage.fillInPatientSearchWithNoFields(familyMember)){
                            patientSearchPage.clickSearchButtonByXpath(driver);
                        }
                    }
                    patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
                    patientDetailsPage.newPatientPageIsDisplayed();

                    if(!patientDetailsPage.createNewFamilyMember(familyMember)){
                        break;
                    }
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
//                Wait.seconds(15);//Continuos time out failures observed at this point in jenkins runs.
//                if (!referralPage.verifyThePageTitlePresence("Select tests for")) {
//                    Wait.seconds(20);//Continuos time out failures observed at this point in jenkins runs.
//                }
                if(!familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember(familyMember)){
                    Assert.assertFalse("Select Test title for Family Member " + memberDetails.get(i).get(0) + " Not displayed. Pls check SelectTitle.jpg", true);
                    SeleniumLib.takeAScreenShot("SelectTitle.jpg");
                }
                Wait.seconds(2);
                referralPage.clickSaveAndContinueButton();
                Wait.seconds(2);
                if(!familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(memberDetails.get(i).get(2))){
                    Debugger.println("fillFamilyMemberDiseaseStatusWithGivenParams not completed.");
                    Assert.assertTrue(false);
                }
//                //Adding Phenotypic and Karyotypic sex also as it is needed in Pedigree validation
//                if(familyMember.getPHENOTYPIC_SEX() == null){
//                    familyMember.setPHENOTYPIC_SEX(familyMember.getGENDER());//By default same as Gender
//                }
//                clinicalQuestionsPage.selectSpecificPhenotypicSexDropdownValue(familyMember.getPHENOTYPIC_SEX());
//                clinicalQuestionsPage.selectSpecificKaryotypicSexDropdownValue("XY");
                Wait.seconds(2);
                referralPage.clickSaveAndContinueButton();
                Wait.seconds(2);
                referralPage.verifyThePageTitlePresence("Add a family member to this referral");
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
