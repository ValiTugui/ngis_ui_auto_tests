package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
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
        // Wait has been added in each step to give time for form loading
        Wait.seconds(3);
        testResult = patientChoicePage.selectTestType(patientChoice.get(1).get(0));
        Assert.assertTrue(testResult);
        Wait.seconds(3);
        testResult = patientChoicePage.fillRecordedByDetails("", patientChoice.get(2).get(0));
        Assert.assertTrue(testResult);
        Wait.seconds(3);
        patientChoicePage.clickOnContinue();
        testResult = patientChoicePage.selectPatientChoice(patientChoice.get(3).get(0));
        Assert.assertTrue(testResult);
        Wait.seconds(3);
        patientChoicePage.clickingOnYesNoOptions("Yes,Yes");
        Wait.seconds(3);
        patientChoicePage.clickOnContinue();
        testResult = patientChoicePage.selectChildAssent(patientChoice.get(4).get(0));
        Assert.assertTrue(testResult);
        Wait.seconds(3);
        patientChoicePage.clickOnContinue();
        testResult = patientChoicePage.fillParentSignatureDetails(patientChoice.get(5).get(0));
        Assert.assertTrue(testResult);
        Wait.seconds(3);
        patientChoicePage.submitPatientChoice();
        Wait.seconds(3);
        //  added 2 new lines below to wait and check for the loading of the completed form of patient choice after clicking the submit patient choice button
        Assert.assertTrue(patientChoicePage.patientChoiceFormCompleted());
        Wait.forElementToBeClickable(driver, patientChoicePage.saveAndContinueButton);
    }

    @When("the user edits patient choice for {string} family members with the below details")
    public void theUserEditsPatientChoiceForFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        try {
            int noOfParticipants = Integer.parseInt(noParticipant);
            List<List<String>> memberDetails = inputDetails.asLists();
            if (memberDetails.size() < noOfParticipants) {
                Debugger.println("No of Participants mentioned and details provided are not matching.");
            }
            for (int i = 1; i < memberDetails.size(); i++) {
                Debugger.println("Doing Patient Choice for " + memberDetails.get(i).get(0));
                patientChoicePage.selectMember(i);
                Debugger.println("PatientChoiceCategory..Start");
                Assert.assertTrue(patientChoicePage.selectPatientChoiceCategory(memberDetails.get(i).get(1)));
                Debugger.println("PatientChoiceCategory..Done. TestType start");
                Assert.assertTrue(patientChoicePage.selectTestType(memberDetails.get(i).get(2)));
                Debugger.println("TestType..Done.Record Type start");
                Assert.assertTrue(patientChoicePage.fillRecordedByDetails(memberDetails.get(i).get(0), memberDetails.get(i).get(3)));
                Debugger.println("Record Type..Done..Continuing...");
                patientChoicePage.clickOnContinue();
                Debugger.println("patientChoice......start");
                Assert.assertTrue(patientChoicePage.selectPatientChoice(memberDetails.get(i).get(4)));
                patientChoicePage.clickingOnYesNoOptions("Yes,Yes");
                Debugger.println("patientChoice..Done..Continuing");
                patientChoicePage.clickOnContinue();

                if(memberDetails.get(i).get(5) != null && !memberDetails.get(i).get(5).isEmpty()) {
                    Debugger.println("Child Assent start");
                    Assert.assertTrue(patientChoicePage.selectChildAssent(memberDetails.get(i).get(5)));
                    Debugger.println("Child Assent Done. Continuing....");
                    patientChoicePage.clickOnContinue();
                }
                Debugger.println("Parent/Patient Signature....start.");
                patientChoicePage.drawSignature();
                Debugger.println("Parent/Patient Signature Done...Submitting form");
                if (!patientChoicePage.submitPatientChoice()) {
                    Debugger.println("Submitted form, but save and continue not displayed..Proceeding to next Patient..");
                    referralPage.navigateToStage("Patient choice");
                    Wait.seconds(5);
                    continue;
                } else {
                    Debugger.println("Submitted.....Continuing");
                    referralPage.clickOnSaveAndContinueButton();
                }
                Debugger.println("DONE.");

            }//end
            Wait.seconds(10);//Waiting for 10 seconds as there is a delay observed in patient choice page in e2elatest
        } catch (Exception exp) {
            Debugger.println("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp);
            Assert.assertTrue("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp,false);
        }
    }
    @When("the user edits patient choice for the newly added family member")
    public void theUserEditsPatientChoiceForTheNewlyAddedFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        patientChoicePage.selectMember(1);
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

    @And("the user clicks on the {string} link in patient choice page")
    public void theUserClicksOnTheLinkInPatientChoicePage(String linkText) {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnPatientChoiceInformationLink(linkText);
        Assert.assertTrue(testResult);
    }

    @Then("the patient choice form library page displays correctly")
    public void thePatientChoiceFormLibraryPageDisplaysCorrectly() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyThePatientChoiceFormLibraryDetails();
        Assert.assertTrue(testResult);
    }


    @And("the user selects the option (.*) in patient choice category")
    public void theUserFillsDetailsInPatientChoiceCategory(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory(inputData);
        Assert.assertTrue(testResult);
        Wait.seconds(3);
    }

    @And("the user fills {string} details in test type")
    public void theUserFillsDetailsInTestType(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectTestType(inputData);
        Assert.assertTrue(testResult);
    }

    @And("the user fills {string} details in recorded by")
    public void theUserFillsDetailsInRecordedBy(String recordedBy) {
        boolean testResult = false;
        testResult = patientChoicePage.fillRecordedByDetails("", recordedBy);
        Assert.assertTrue(testResult);
    }

    @When("the user selects the option {string} as patient choices")
    public void theUserFillsDetailsInPatientChoices(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoice(inputData);
        Assert.assertTrue(testResult);
        Wait.seconds(3);
    }

    @When("the user fills {string} details in child assent")
    public void theUserFillsDetailsInChildAssent(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectChildAssent(inputData);
        Assert.assertTrue(testResult);

    }

    @When("the user fills {string} details in parent or guardian signature")
    public void theUserFillsDetailsInParentOrGuardianSignature(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.fillParentSignatureDetails(inputData);
        Assert.assertTrue(testResult);
        patientChoicePage.submitPatientChoice();
    }


    @When("the user fills signature details in (.*)")
    public void theUserFillsDetailsInPatientSignature(String arg) {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientSignature();
        Assert.assertTrue(testResult);
        Wait.seconds(3);
    }

    @When("the user fills PatientSignature details in patient signature")
    public void theUserFillsDetailsInPatientSignature() {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientSignature();
        Assert.assertTrue(testResult);
        Wait.seconds(3);
    }

    @And("the user should see the chosen {string} with edit button in {string}")
    public void theUserWillSeeTheChosenWithEditButton(String expectedResult, String category) {
        Assert.assertTrue(patientChoicePage.verifySelectedOption(expectedResult));
        Assert.assertTrue(patientChoicePage.verifyEditButton(category));
    }

    @Then("the (.*) option is marked as completed")
    public void theOptionIsMarkedAsCompleted(String category) {
        Assert.assertTrue(patientChoicePage.optionIsCompleted(category));
    }

    @Then("the user is navigated to a patient choice form option with title (.*)")
    public void theUserIsNavigatedToAPatientChoiceFormOptionWithTitle(String title) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyTheOptionTitlePresence(title);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see selected patient choice details")
    public void theUserShouldBeAbleToSeeSelectedPatientChoiceDetails(DataTable inputDetails) {
        List<List<String>> choiceOptions = inputDetails.asLists();
        for (int i = 0; i < choiceOptions.size(); i++) {
            Assert.assertTrue(patientChoicePage.selectedPatientChoiceDetails(choiceOptions.get(i).get(0)));
        }
    }

    @And("the user sees continue button is highlighted and clicks on Continue Button")
    public void theUserSeesContinueButtonIsHighlightedAndClicksOnContinueButton() {
        Assert.assertTrue(patientChoicePage.highlightedContinueButton());
        patientChoicePage.clickOnContinue();
    }

    @And("the user will see a {string} warning message on the patient choice information option")
    public void theUserWillSeeAWarningMessageOnThePatientChoiceInformationOption(String warningMessage) {
        patientChoicePage.patientChoiceInformationWarningMessage(warningMessage);
    }


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

    @And("the question will be displayed as {string}")
    public void theQuestionWillBeDisplayedAs(String question) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyTheQuestionInPatientChoice(question);
        Assert.assertTrue(testResult);
    }

    @And("the user selects {string} research participation in patient choices option")
    public void theUserSelectsResearchParticipationInPatientChoicesOption(String option) {
        patientChoicePage.clickingOnResearchParticipationYesNoOptions(option);
    }

    @And("the user selects {string} NHS care in patient choices option")
    public void theUserSelectsNHSCareInPatientChoicesOption(String option) {
        patientChoicePage.clickingOnNHSCareYesNoOptions(option);
    }

   @Then("the user should see a error message box with border color (.*) and message as (.*)")
    public void theUserShouldSeeAErrorMessageBox(String boxColor,String message) {
        boolean testResult = false;
        testResult = patientChoicePage.errorMessageInPatientChoicePage(boxColor,message);
        Assert.assertTrue(testResult);
    }

    @When("the user selects the proband")
    public void theUserSelectsTheProband() {
        patientChoicePage.selectMember(0);
    }

    @And("the user answers the patient choice questions with agreeing to testing")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTesting() {
        patientChoicePage.selectPatientChoiceCategory();
        Wait.seconds(2);
        patientChoicePage.selectTestType();
        Wait.seconds(2);
        patientChoicePage.enterRecordedByDetails();
        Wait.seconds(2);
        patientChoicePage.selectChoicesWithPatientChoiceNotRequired();
        Wait.seconds(2);
        patientChoicePage.submitPatientChoiceWithoutSignature();
        Wait.seconds(2);
    }

    @And("the user submits the patient choice with signature")
    public void theUserSubmitsThePatientChoiceWithSignature() {
        patientChoicePage.submitPatientChoiceWithSignature();

    }

    @Then("the user should be able to see the patient choice form with success message")
    public void theUserShouldBeAbleToSeeThePatientChoiceFormWithSuccessMessage() {
        Assert.assertTrue(patientChoicePage.patientChoiceFormCompleted());
    }

    @Then("the user should be able to see the highlighted Submit patient choice button")
    public void theUserShouldBeAbleToSeeTheHighlightedSubmitPatientChoiceButton() {
        Assert.assertTrue(patientChoicePage.highlightedSubmitPatientChoiceButton());
    }

    @And("the patient choice reason options as below")
    public void thePatientChoiceReasonOptionsAsBelow(DataTable inputDetails) {
        List<List<String>> choiceOptions = inputDetails.asLists();
        for (int i = 1; i < choiceOptions.size(); i++) {
            Assert.assertTrue(patientChoicePage.verifyThePatientChoiceOption(choiceOptions.get(i).get(0)));
        }
    }

    @And("the user should be able to see the previous sections disappeared")
    public void theUserShouldBeAbleToSeeThePreviousSectionsDisappeared() {
        Assert.assertTrue(patientChoicePage.previousSectionReclosed());
    }

    @And("the user should be able to see Yes and No answer options")
    public void theUserShouldBeAbleToSeeYesAndNoAnswerOptions() {
        Assert.assertTrue(patientChoicePage.verifyYesNoButtonForResearchParticipation());
    }

    @And("the user selects {string} research participation option in patient choices")
    public void theUserSelectsResearchParticipationOptionInPatientChoices(String option) {
        Assert.assertTrue(patientChoicePage.clickingOnResearchParticipationYesNoOptions(option));
    }

    @And("the user should be able to see Yes and No answer options for the question")
    public void theUserShouldBeAbleToSeeYesAndNoAnswerOptionsForTheQuestion() {
        Assert.assertTrue(patientChoicePage.verifyYesNoButtonForSeparateToNnsCare());
    }

    @And("the user selects {string} data and sample option in patient choices")
    public void theUserSelectsDataAndSampleOptionInPatientChoices(String option) {
        patientChoicePage.clickingOnDataAndSampleForResearchYesNoOptions(option);
    }

    @Then("the user should be able to see patient hospital number")
    public void theUserShouldBeAbleToSeePatientHospitalNumber() {
        boolean testResult = false;
        testResult = patientChoicePage.patientHospitalNumberInRecordedByOption();
        Assert.assertTrue(testResult);
    }

    @And("the user sees a back button on Add patient choice information page")
    public void theUserSeesABackButtonOnAddPatientChoiceInformationPage() {
        boolean testResult = false;
        testResult = patientChoicePage.backButtonOnPatientChoiceInformationPage();
        Assert.assertTrue(testResult);
    }

    @Then("the user will be able to see an error message as {string}")
    public void theUserWillBeAbleToSeeAnErrorMessageAs(String errorMessage) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyErrorMessageOnPatientChoiceFormPage(errorMessage);
        Assert.assertTrue(testResult);
    }


    @Then("the user should be able to see enabled continue button")
    public void theUserShouldBeAbleToSeeEnabledContinueButton() {
        boolean testResult = false;
        testResult = patientChoicePage.enabledContinueButtonOnPatientChoiceFormPage();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on submit patient choice Button")
    public void theUserClicksOnSubmitPatientChoiceButton() {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnSubmitPatientChoiceButton();
        Assert.assertTrue(testResult);
        Wait.seconds(10); // used here to wait for the form loading delay
    }

    @Then("the user should be able to see Patient Choice form")
    public void theUserShouldBeAbleToSeePatientChoiceForm() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyPatientChoiceForm();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see hint text in search box on requesting organisation page")
    public void theUserShouldBeAbleToSeeHintTextInSearchBoxOnRequestingOrganisationPage() {
        Assert.assertTrue(patientChoicePage.verifyHintText());
    }

    @And("the user should be able to see an intro message {string} on requesting organisation page")
    public void theUserShouldBeAbleToSeeAnIntroMessageOnRequestingOrganisationPage(String introMessage) {
        Assert.assertTrue(patientChoicePage.verifyTheIntroMessage(introMessage));
    }

    @And("the user fills Review and submit details in patient signature")
    public void theUserFillsReviewAndSubmitDetailsInPatientSignature() {
        Assert.assertTrue(patientChoicePage.selectPatientSignature());
    }

    @Then("the Patient Choice landing page is updated to {string} for the proband")
    public void thePatientChoiceLandingPageIsUpdatedToForTheProband(String expectedStatusInfo) {
         //Assert.assertTrue(patientChoicePage.statusUpdatedCorrectly(expectedStatusInfo, 0));
    }

    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYes() {

        patientChoicePage.selectPatientChoiceCategory();
        Wait.seconds(2);
        patientChoicePage.selectTestType();
        Wait.seconds(2);
        patientChoicePage.enterRecordedByDetails();
        Wait.seconds(2);
        patientChoicePage.selectChoicesWithAgreeingTesting();
        Wait.seconds(2);
        patientChoicePage.drawSignature();
        Wait.seconds(2);

    }

    @Then("the help text is displayed")
    public void theHelpTextIsDisplayed() {
        Assert.assertTrue(patientChoicePage.verifyHelpTextLabelIsVisible());
    }

    @And("the user should be able to see submit patient choice button disabled")
    public void theUserShouldBeAbleToSeeSubmitPatientChoiceButtonDisabled() {
        Assert.assertTrue(patientChoicePage.submitPatientChoiceButtonStatus());
    }

    @Then("the user should see the section title as (.*)")
    public void theUserShouldShouldSeeTheSectionTitleAs(String sectionTitle) {
        Assert.assertTrue(patientChoicePage.verifyThePatientChoiceSectionTitle(sectionTitle));
    }
    @And("the patient choice options as below")
    public void thePatientChoiceOptionsAsBelow(DataTable inputDetails) {
        List<List<String>> choiceOptions = inputDetails.asLists();
        for (int i = 1; i < choiceOptions.size(); i++) {
            Assert.assertTrue(patientChoicePage.verifyThePatientChoiceOption(choiceOptions.get(i).get(0)));
        }
    }
    @And("the child assent options as below")
    public void theChildAssentAsBelow(DataTable inputDetails) {
        List<List<String>> choiceOptions = inputDetails.asLists();
        for (int i = 1; i < choiceOptions.size(); i++) {
            Assert.assertTrue(patientChoicePage.verifyTheChildAssentOption(choiceOptions.get(i).get(0)));
        }
    }

    @And("Save and continue button is displayed as {string}")
    public void saveAndContinueButtonIsDisplayedAs(String expectedStatus) {
        boolean testResult = false;
        testResult = patientChoicePage.saveAndContinueButtonStatus();
        if (expectedStatus.equals("enabled")) {
            Assert.assertTrue(testResult);
        } else {
             Assert.assertFalse(testResult);
        }
    }

    @And("the user should be able to see a sub title {string} on add patient choice information page")
    public void theUserShouldBeAbleToSeeASubTitleOnAddPatientChoiceInformationPage(String formsTitle) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyFormsTitleUnderFormsLibrary(formsTitle);
        Assert.assertTrue(testResult);
    }

    @Then("the should be able to see an additional section {string} under the Form Library")
    public void theShouldBeAbleToSeeAnAdditionalSectionUnderTheFormLibrary(String formsSection) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyAdditionalFormsSection(formsSection);
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on {string} link")
    public void theUserClicksOnLink(String link) {
        patientChoicePage.clickOnLink(link);
    }
    @Then("the user should be able to see highlighted continue button")
    public void theUserShouldBeAbleToSeeHighlightedContinueButton() {
        boolean testResult = false;
        testResult = patientChoicePage.highlightedContinueButton();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on the amend patient choice button")
    public void theUserClicksOnTheAmendPatientChoiceButton() {
        patientChoicePage.clickOnAmendPatientChoice();
    }

     @And("the user selects {string} lacks capacity of consultee for the person in Consultee Attestation")
    public void theUserSelectsLacksCapacityOfConsulteeForThePerson(String option) {
        patientChoicePage.clickingOnLacksCapacityOfConsulteeForThePerson(option);
    }

    @And("the user selects {string} National Genomic Research Library for the person in Consultee Attestation")
    public void theUserSelectsNationalGenomicResearchLibraryForThePerson(String option) {
        patientChoicePage.clickingOnNationalGenomicResearchLibraryForThePerson(option);
    }

    @And("the user selects {string} willing to accept the role of consultee for the person in Consultee Attestation")
    public void theUserSelectsWillingToAcceptTheRoleOfConsulteeForThePerson(String option) {
        patientChoicePage.clickingOnWillingToAcceptTheRoleOfConsulteeForThePerson(option);
    }

    @And("the user should be able to see patient choice in history tab")
    public void theUserShouldBeAbleToSeePatientChoiceInHistoryTab() {
        Assert.assertTrue(patientChoicePage.patientChoiceUnderHistoryTab());
    }

    @Then("the user should be able to see replaced patient choice in history tab")
    public void theUserShouldBeAbleToSeeReplacedPatientChoiceInHistoryTab() {
        Assert.assertTrue(patientChoicePage.replacedPatientChoiceUnderHistoryTab());
    }

    @When("the user fills {string} details for signature")
    public void theUserFillsDetailsForSignature(String signatureDetails) {
        boolean testResult = false;
        testResult = patientChoicePage.fillTheSignatureDetails(signatureDetails);
        Assert.assertTrue(testResult);
    }

    @And("the user selects {string} agree to participate in research for Child Assent")
    public void theUserSelectsAgreeToParticipateInResearchForChildAssent(String option) {
        patientChoicePage.clickingOnChildAgreeToParticipate(option);
    }

    @And("the user should be able to see enabled continue button and upload document button")
    public void theUserShouldBeAbleToSeeEnabledContinueButtonAndUploadDocumentButton() {
        boolean testResult = false ;
        testResult = patientChoicePage.enabledContinueButtonOnPatientChoiceFormPage();
        Assert.assertTrue(testResult);
        testResult = patientChoicePage.enabledUploadDocumentButtonOnPatientChoiceFormPage();
        Assert.assertTrue(testResult);
    }

    @And("the user will see a {string} message on upload section")
    public void theUserWillSeeAMessageOnUploadSection(String message) {
        boolean testResult=false;
        testResult=patientChoicePage.verifyUploadMessage(message);
        Assert.assertTrue(testResult);
    }

    @When("the user edits the patient choice status for family member with {string}")
    public void theUserEditsThePatientChoiceStatusForFamilyMemberWith(String familyDetails) {
        Debugger.println("Doing Patient Choice for " + familyDetails);
        patientChoicePage.editSpecificPatientChoice(familyDetails);
    }

    @Then("the user should wait for the form to be uploaded by moving to the next section {string}")
    public void theUserShouldWaitForTheFormToBeUploadedByMovingToTheNextSection(String expTitle) {
        boolean testResult=false;
        testResult=patientChoicePage.waitForFormUpload(expTitle);
        Assert.assertTrue(testResult);
    }

    @And("the patient choice test package is correctly displayed")
    public void thePatientChoiceTestPackageIsCorrectlyDisplayed() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyTheElementsOnPatientChoiceTestPackagePage();
        Assert.assertTrue(testResult);
    }

    @Then("the user sees a success message after form upload in recorded by as {string}")
    public void theUserSeesASuccessMessageAfterFormUploadInRecordedByAs(String expMessage) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyFormUploadSuccessMessage(expMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the user sees the specified error messages for unsupported file uploads")
    public void theUserSelectsFileToBeUploadedFrom(DataTable inputDetails) {
        boolean testResult = false;
        List<List<String>> uploadFiles = inputDetails.asLists();
        String fileName = "";
        String errorMessage = "";
        for (int i = 1; i < uploadFiles.size(); i++) {
            fileName = uploadFiles.get(i).get(0);
            errorMessage = uploadFiles.get(i).get(1);
            testResult = patientChoicePage.verifyInvalidFileUploadMessages(fileName, errorMessage);
            Assert.assertTrue(testResult);
        }
    }
    @When("the user clicks the Upload document button")
    public void theUserClicksTheUploadDocumentButton() {
        patientChoicePage.clickTheUploadDocumentButton();
    }

    @Then("the user should verify the dropdown and dob field after the document successfully uploaded")
    public void theUserShouldVerifyTheDropdownAndDobFieldAfterTheDocumentSuccessfullyUploaded() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyTheDropDownAndDOBFieldOfPatientChoice();
        Assert.assertTrue(testResult);
    }

    @And("the Date of Signature fields are displayed as {string}")
    public void theDateOfSignatureFieldsAreDisplayedAs(String expStatus) {
        boolean testResult = false;
        if (expStatus.equals("enabled")) {
            testResult = patientChoicePage.dateOfSignatureStatusInRecordedBY();
            Assert.assertTrue(testResult);
        } else {
            testResult = true;
            testResult = patientChoicePage.dateOfSignatureStatusInRecordedBY();
            Assert.assertFalse(testResult);
        }
    }

    @And("the user selects {string} from dropdown option in recorded by")
    public void theUserSelectsFromDropdownOptionInRecordedBy(String dropdownValue) {
        boolean testResult = false;
        testResult = patientChoicePage.selectUploadFormType(dropdownValue);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks the upload button to upload the file {string}")
    public void theUserClicksTheUploadButtonToUploadTheFile(String fileName) {
        boolean testResult = false;
        testResult = patientChoicePage.uploadDocumentInRecordedBy(fileName);
        Assert.assertTrue(testResult);
    }

    @And("the user fills the valid Date in {string}")
    public void theUserFillsTheValidDateIn(String expDate) {
        boolean testResult = false;
        testResult = patientChoicePage.fillTheDateOfSignatureInRecordedBy(expDate);
        Assert.assertTrue(testResult);
    }
}//end
