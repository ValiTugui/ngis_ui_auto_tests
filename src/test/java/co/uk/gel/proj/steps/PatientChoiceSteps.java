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

public class PatientChoiceSteps extends Pages {

    public PatientChoiceSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user edits the patient choice status")
    public void theUserEditsThePatientChoiceStatus() {
        boolean testResult = false;
        testResult = patientChoicePage.editPatientChoice();
        Assert.assertTrue(testResult);
    }

    @When("the user fills new patient choice form with below details")
    public void theUserFillsNewPatientChoiceForm(DataTable inputData) {
        boolean testResult = false;
        List<List<String>> patientChoice = inputData.asLists();
        testResult = patientChoicePage.selectPatientChoiceCategory(patientChoice.get(0).get(0));
        Assert.assertTrue(testResult);
        testResult = patientChoicePage.selectTestType(patientChoice.get(1).get(0));
        Assert.assertTrue(testResult);
        testResult = patientChoicePage.fillRecordedByDetails(patientChoice.get(0).get(0),patientChoice.get(2).get(0));
        Assert.assertTrue(testResult);
        patientChoicePage.clickOnContinue();
        testResult = patientChoicePage.selectPatientChoice(patientChoice.get(3).get(0));
        Assert.assertTrue(testResult);
        patientChoicePage.clickingOnYesNoOptions("Yes,Yes");
        Assert.assertTrue(patientChoicePage.notHighlightedContinueButton());
        patientChoicePage.clickOnContinue();
        testResult = patientChoicePage.selectChildAssent(patientChoice.get(4).get(0));
        Assert.assertTrue(testResult);
        patientChoicePage.clickOnContinue();
        testResult = patientChoicePage.fillParentSignatureDetails(patientChoice.get(5).get(0));
        Assert.assertTrue(testResult);
        patientChoicePage.submitPatientChoice();
    }
    @When("the user edits patient choice for {string} family members with the below details")
    public void theUserEditsPatientChoiceForFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        try {
            int noOfParticipants = Integer.parseInt(noParticipant);
            List<List<String>> memberDetails = inputDetails.asLists();
            if(memberDetails.size() < noOfParticipants){
                Debugger.println("No of Participants mentioned and details provided are not matching.");
            }
            for (int i = 1; i < memberDetails.size(); i++) {
                Debugger.println("Doing Patient Choice for "+memberDetails.get(i).get(0));
                patientChoicePage.editSpecificPatientChoice(memberDetails.get(i).get(0));
                Debugger.println("PatientChoiceCategory..Start");
                patientChoicePage.selectPatientChoiceCategory(memberDetails.get(i).get(1));
                Debugger.println("PatientChoiceCategory..Done. TestType start");
                patientChoicePage.selectTestType(memberDetails.get(i).get(2));
                Debugger.println("TestType..Done.Record Type start");
                patientChoicePage.fillRecordedByDetails(memberDetails.get(i).get(0),memberDetails.get(i).get(3));
                Debugger.println("Record Type..Done..Continuing...");
                patientChoicePage.clickOnContinue();
                Debugger.println("patientChoice......start");
                patientChoicePage.selectPatientChoice(memberDetails.get(i).get(4));
                patientChoicePage.clickingOnYesNoOptions("Yes,Yes");
                Debugger.println("patientChoice..Done..Continuing");
                patientChoicePage.clickOnContinue();
                Debugger.println("Child Assent start");
                if(!patientChoicePage.selectChildAssent(memberDetails.get(i).get(5))){
                    Debugger.println("Could not complete Child Assent...");
                    Assert.assertFalse("Could not complete Child Assent...",true);
                    continue;
                }
                Debugger.println("Child Assent Done. Continuing....");
                patientChoicePage.clickOnContinue();
                Debugger.println("Parent Signature....start.");
                if(!patientChoicePage.fillParentSignatureDetails(memberDetails.get(i).get(6))){
                    Debugger.println("Could not complete ParentSignature...");
                    Assert.assertFalse("Could not complete ParentSignature...",true);
                    continue;
                }
                Debugger.println("Parent Signature Done...Submitting form");
                if(!patientChoicePage.submitPatientChoice()){
                    Debugger.println("Could not Submit Form...");
                    Assert.assertFalse("Could not Submit form...",true);
                    continue;
                }
                Debugger.println("Submitted.....Continuing");
                referralPage.clickSaveAndContinueButton();

                Debugger.println("DONE.");
            }//end
        }catch(Exception exp){
            Debugger.println("PatientChoiceSteps: Exception in Filling PatientChoice Details: "+exp);
        }
    }

    @And("the user sees the patient choice status as {string}")
    public void theUserSeesThePatientChoiceStatus(String status) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyPatientChoiceStatus(status);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see the patient identifiers on patient choice page similar as in family member landing page")
    public void theUserShouldBeAbleToSeeThePatientIdentifiersOnPatientChoicePage() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyPatientIdentifiersInPatientChoicePage();
        Assert.assertTrue(testResult);
    }
    //This step is for Notes, page. included here as there is only one method need for that page .So not created a separate stepdef
    @And("the user fills the NotesPage with the {string}")
    public void theUserFillsTheNotesPageWithThe(String notes) {
        notesPage.fillNotes(notes);
    }

    // 2110
    @And("the user clicks on the {string} link in patient choice page")
    public void theUserClicksOnTheLinkInPatientChoicePage(String linkText) {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnPatientChoiceInformationLink(linkText);
        Assert.assertTrue(testResult);
    }

    @Then("the details in patient choice form library page displays correctly")
    public void theUserShouldVerifyTheDetailsInPatientChoiceFormLibraryPage() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyThePatientChoiceFormLibraryDetails();
        Assert.assertTrue(testResult);
    }

    /*@When("the user wants to edit {string} in patient choice status")
    public void theUserWantsToEditInPatientChoiceStatus(String index) {
        int editIndex = Integer.parseInt(index);
        boolean testResult = false;
        testResult = patientChoicePage.editPatientChoice2(editIndex);
        Assert.assertTrue(testResult);
    }*/

    // 2039
    @When("the user fills {string} details in patient choice category")
    public void theUserFillsDetailsInPatientChoiceCategory(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory(inputData);
        Assert.assertTrue(testResult);
    }

    @When("the user fills {string} details in test type")
    public void theUserFillsDetailsInTestType(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectTestType(inputData);
        Assert.assertTrue(testResult);
    }

//    @When("the user fills {string} details in recorded by")
//    public void theUserFillsDetailsInRecordedBy(String inputData) {
//        boolean testResult = false;
//        testResult = patientChoicePage.fillRecordedByDetails(inputData);
//        Assert.assertTrue(testResult);
////        patientChoicePage.clickOnContinue();
//    }

    @When("the user fills {string} details in patient choices")
    public void theUserFillsDetailsInPatientChoices(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoice(inputData);
        Assert.assertTrue(testResult);
    }

    @When("the user fills {string} details in child assent")
    public void theUserFillsDetailsInChildAssent(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectChildAssent(inputData);
        Assert.assertTrue(testResult);
//        patientChoicePage.clickOnContinue();
    }

    @When("the user fills {string} details in parent or guardian signature")
    public void theUserFillsDetailsInParentOrGuardianSignature(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.fillParentSignatureDetails(inputData);
        Assert.assertTrue(testResult);
        patientChoicePage.submitPatientChoice();
    }

    @When("the user fills PatientSignature details in patient signature")
    public void theUserFillsDetailsInPatientSignature() {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientSignature();
        Assert.assertTrue(testResult);
        patientChoicePage.submitPatientChoice();
    }

    @And("the user should see the chosen {string} with edit button in {string}")
    public void theUserShouldSeeTheChosenWithEditButton(String expectedResult, String category) {
        Assert.assertTrue(patientChoicePage.verifySelectedOption(expectedResult));
        Assert.assertTrue(patientChoicePage.verifyEditButton(category));
    }

    @Then("the option (.*) is marked as completed")
    public void theOptionIsMarkedAsCompletedForOption(String category) {
        Assert.assertTrue(patientChoicePage.optionIsCompleted(category));
    }

    @When("the user is navigated to a patient choice form option with title (.*)")
    public void theUserIsNavigatedToAPatientChoiceFormOptionWithTitle(String title) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyTheOptionTitlePresence(title);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see all the details of patient choices option")
    public void theUserShouldBeAbleToSeeAllTheDetailsOfPatientChoicesOption() {
        Assert.assertTrue(patientChoicePage.verifyThePatientChoiceOptions());
        Assert.assertTrue(patientChoicePage.notHighlightedContinueButton());
    }

    @And("the user sees the status of Submit patient choice button and Save and continue button")
    public void theUserSeesTheStatusOfSubmitPatientChoiceButtonAndSaveAndContinueButton() {
        Assert.assertTrue(patientChoicePage.highlightedSubmitPatientChoiceButton());
        Assert.assertTrue(patientChoicePage.saveAndContinueButtonStatus());
    }

    @And("the user should be able to see all selected patient choice details")
    public void theUserShouldBeAbleToSeeAllSelectedPatientChoiceDetails() {
        Assert.assertTrue(patientChoicePage.selectedPatientChoiceDetails());
    }

    @And("the user should see continue button is highlighted and clicks on Continue Button")
    public void theUserShouldSeeContinueButtonIsHighlightedAndClicksOnContinueButton() {
        Assert.assertTrue(patientChoicePage.highlightedContinueButton());
        patientChoicePage.clickOnContinue();
    }

    @And("the user sees a {string} warning message on the patient choice information option")
    public void theUserSeesAWarningMessageOnThePatientChoiceInformationOption(String warningMessage) {
        patientChoicePage.patientChoiceInformationWarningMessage(warningMessage);
    }

// scenario_3

    @When("the user clicks on edit button in (.*)")
    public void theUserClicksOnEditButton(String category) {
        patientChoicePage.clickOnEditButton(category);
    }

    @Then("the user should be able to see previous section re-opened")
    public void theUserShouldBeAbleToSeePreviousSectionReOpened() {
        boolean testResult = false;
        testResult = patientChoicePage.previousSectionsReopened();
        Assert.assertTrue(testResult);
    }

    @And("the user should see continue button is not highlighted")
    public void theUserShouldSeeContinueButtonIsNotHighlighted() {
        Assert.assertTrue(patientChoicePage.notHighlightedContinueButton());
    }

    @And("the user selects {string} options in patient choices option")
    public void theUserSelectsOptionsInPatientChoicesOption(String options) {
        patientChoicePage.clickingOnYesNoOptions(options);
    }

    @Then("the question will be displayed as {string}")
    public void theQuestionWillBeDisplayedAs(String question) {
        patientChoicePage.verifyTheQuestionInPatientChoice(question);

    }

    @And("the user selects {string} research participation in patient choices option")
    public void theUserSelectsResearchParticipationInPatientChoicesOption(String option) {
        patientChoicePage.clickingOnResearchParticipationYesNoOptions(option);

    }

    @And("the user selects {string} NHS care in patient choices option")
    public void theUserSelectsNHSCareInPatientChoicesOption(String option) {
        patientChoicePage.clickingOnNHSCareYesNoOptions(option);
    }

    @And("the user should be able to see all the details of patient choices research participation")
    public void theUserShouldBeAbleToSeeAllTheDetailsOfPatientChoicesResearchParticipation() {
        Assert.assertTrue(patientChoicePage.verifyResearchParticipationOfPatientChoice());
    }

    @Then("the user should see a error message box")
    public void theUserShouldSeeAErrorMessageBox() {
        boolean testResult = false;
        testResult = patientChoicePage.errorMessageInPatientChoicePage();
        Assert.assertTrue(testResult);
    }
    @When("the user selects the proband")
    public void theUserSelectsTheProband() {
        patientChoicePage.selectMember(0);
    }

    @And("the user answers the patient choice questions with agreeing to testing")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTesting() {
        patientChoicePage.selectPatientChoiceCategory();
        patientChoicePage.selectTestType();
        patientChoicePage.enterRecordedByDetails();
        patientChoicePage.selectChoicesWithPatientChoiceNotRequired();
        patientChoicePage.submitPatientChoiceWithoutSignature();
    }

    @And("the user submits the patient choice with signature")
    public void theUserSubmitsThePatientChoiceWithSignature() {
        patientChoicePage.submitPatientChoiceWithSignature();
    }

    @Then("the Patient Choice landing page is updated to {string} for the proband")
    public void thePatientChoiceLandingPageIsUpdatedToForTheProband(String expectedStatusInfo) {
        Assert.assertTrue(patientChoicePage.statusUpdatedCorrectly(expectedStatusInfo, 0));
    }

    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYes() {
        patientChoicePage.selectPatientChoiceCategory();
        patientChoicePage.selectTestType();
        patientChoicePage.enterRecordedByDetails();
        patientChoicePage.selectChoicesWithAgreeingTesting();
        patientChoicePage.drawSignature();
    }
}//end
