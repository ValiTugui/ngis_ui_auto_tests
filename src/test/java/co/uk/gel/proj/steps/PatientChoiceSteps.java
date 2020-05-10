package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.pages.FamilyMemberDetailsPage;
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
//        boolean testResult = false;
//        testResult = patientChoicePage.editPatientChoice();
//        Assert.assertTrue(testResult);
        patientChoicePage.selectMember(0);
    }

    @When("the user edits patient choice for {string} family members with the below details")
    public void theUserEditsPatientChoiceForFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        try {
            int noOfParticipants = Integer.parseInt(noParticipant);
            List<List<String>> memberDetails = inputDetails.asLists();
            boolean testResult = false;
            for (int i = 1; i < memberDetails.size(); i++) {
                Debugger.println("\nPatient Choice for Family Member:"+i);
                //No need to alert as there are cases which directly landing to specific patient page
                patientChoicePage.selectMember(i);
                Wait.seconds(2);
                //Debugger.println("Selected Family Member:");
                testResult = patientChoicePage.selectPatientChoiceCategory(memberDetails.get(i).get(1));
                if(!testResult){
                    Assert.assertTrue("Failed in selectPatientChoiceCategory",false);
                    break;
                }
                Wait.seconds(2);
                //Debugger.println("Category Done:");
                testResult = patientChoicePage.selectTestType(memberDetails.get(i).get(2));
                if(!testResult){
                    Assert.assertTrue("Failed in selectTestType",false);
                    break;
                }
                Wait.seconds(2);
                //Debugger.println("Test Type done:");
                testResult = patientChoicePage.fillRecordedByDetails(memberDetails.get(i).get(0), memberDetails.get(i).get(3));
                if(!testResult){
                    Assert.assertTrue("Failed in fillRecordedByDetails",false);
                    break;
                }
                Wait.seconds(2);
                //Debugger.println("Record details done:");
                testResult = patientChoicePage.clickOnContinue();
                if(!testResult){
                    Assert.assertTrue("Failed in clickOnContinue",false);
                    break;
                }
                Wait.seconds(2);
                //Debugger.println("Continuing:");
                String patientChoice=memberDetails.get(i).get(4);
                if(patientChoice.equalsIgnoreCase("Patient has agreed to the test")) {
                    patientChoicePage.selectOptionForQuestion(memberDetails.get(i).get(4), "Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?");
                    patientChoicePage.selectOptionForQuestion("Yes", "Has research participation been discussed?");
                    patientChoicePage.selectOptionForQuestion("Yes", "The patient agrees that their data and samples may be used for research, separate to NHS care.");
                }else{
                    patientChoicePage.selectOptionForQuestion(memberDetails.get(i).get(4), "Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?");
                }
                Wait.seconds(2);
                testResult = patientChoicePage.clickOnContinue();
                if(!testResult){
                    Assert.assertTrue("Failed in clickOnContinue",false);
                    break;
                }
                if(memberDetails.get(i).get(5) != null && !memberDetails.get(i).get(5).isEmpty()) {
                    testResult = patientChoicePage.selectChildAssent(memberDetails.get(i).get(5));
                    if(!testResult){
                        Assert.assertTrue("Failed in selectChildAssent",false);
                        break;
                    }
                    testResult = patientChoicePage.clickOnContinue();
                    if(!testResult){
                        Assert.assertTrue("Failed in clickOnContinue",false);
                        break;
                    }
                }
                if(memberDetails.get(i).get(6) != null && !memberDetails.get(i).get(6).isEmpty()) {
                    patientChoicePage.drawSignature();
                }
                Debugger.println("Submitting PC:");
                if (!patientChoicePage.submitPatientChoice()) {
                    referralPage.navigateToStage("Patient choice");
                    Wait.seconds(5);
                    continue;
                }
                patientChoicePage.clickOnSaveAndContinueButton();
                Wait.seconds(10);//Waiting for 10 seconds as there is a delay observed in patient choice page in e2elatest
                Debugger.println("\nPC done for FM:"+i);
            }//end
            Wait.seconds(5);
        } catch (Exception exp) {
            Debugger.println("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp+"\n"+driver.getCurrentUrl());
            Assert.assertTrue("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp,false);
        }
    }
    @When("the user completes the patient choice for below family members as agreeing to test")
    public void theUserCompletesThePatientChoiceForBelowFamilyMembers(DataTable inputDetails) {
        try {
            List<List<String>> memberDetails = inputDetails.asLists();
            for (int i = 1; i < memberDetails.size(); i++) {//First line is title
                Debugger.println("Patient Choice for Member: "+i);
                Assert.assertTrue(patientChoicePage.selectMember(i));
                Wait.seconds(2);
                Assert.assertTrue(patientChoicePage.completePatientChoiceWithAgreeingTestForFamilyMember(memberDetails.get(i).get(0),memberDetails.get(i).get(1),memberDetails.get(i).get(2)));
                Wait.seconds(5);//After submitting PC
                patientChoicePage.clickOnSaveAndContinueButton();
                Wait.seconds(10);//Waiting for 10 seconds as there is a delay observed in patient choice page in e2elatest
            }//end

        } catch (Exception exp) {
            Debugger.println("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp);
            Assert.assertTrue("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp,false);
        }
    }
    @When("the user edits patient choice for the newly added family member")
    public void theUserEditsPatientChoiceForTheNewlyAddedFamilyMembersWithTheBelowDetails() {
        patientChoicePage.selectMember(1);
    }

    @And("the user sees the patient choice status for proband as (.*)")
    public void theUserSeesThePatientChoiceStatus(String status) {
        boolean testResult = false;
        //For proband he index will be 0
        testResult = patientChoicePage.verifyPatientChoiceStatus(status, 0);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see the details of same patient {string} in Patient Choice Landing Page")
    public void theUserShouldBeAbleToSeeThePatientIdentifiersOnPatientChoicePage(String patientDetails) {
        boolean testResult = false;
        NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(patientDetails);
        testResult = patientChoicePage.verifyFamilyMemberDetailsPatientChoicePage(patient);
        Assert.assertTrue(testResult);
    }

    @When("the user selects the (.*) tab in patient choice page")
    public void theUserSelectsTheTabInPatientChoicePage(String tabName) {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnPatientChoiceInformationLink(tabName);
        Assert.assertTrue(testResult);
    }

    @And("the user selects the option (.*) in patient choice category")
    public void theUserFillsDetailsInPatientChoiceCategory(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory(inputData);
        Assert.assertTrue(testResult);
    }
    @When("the user selects the option (.*) in section (.*)")
    public void theUserSelectsThe(String option,String section) {
        boolean testResult = false;
        testResult = patientChoicePage.selectOptionInSection(option,section);
        Assert.assertTrue(testResult);
        Wait.seconds(3);
    }
    @Then("the user sees the new patient choice tab selected by default with subtitle (.*)")
    public void theUserSeesNewPatientChoiceTabSelectedByDefault(String subtitle) {
        boolean testResult = false;
        testResult = patientChoicePage.verifySelectedTabInPatientChoice(subtitle);
        Assert.assertTrue(testResult);
    }

    @And("the user fills {string} details in recorded by")
    public void theUserFillsDetailsInRecordedBy(String recordedBy) {
        boolean testResult = false;
        testResult = patientChoicePage.fillRecordedByDetails("", recordedBy);
        Assert.assertTrue(testResult);
    }

    @When("the user selects the option (.*) for the question (.*)")
    public void theUserFillsDetailsInPatientChoices(String option,String question) {
        boolean testResult = false;
        testResult = patientChoicePage.selectOptionForQuestion(option,question);
        Assert.assertTrue(testResult);
        Wait.seconds(3);
    }

    @When("the user fills PatientSignature details in patient signature")
    public void theUserFillsDetailsInPatientSignature() {
        boolean testResult = false;
        testResult = patientChoicePage.drawSignature();
        Assert.assertTrue(testResult);
        Wait.seconds(3);
    }

    @Then("the option (.*) displayed with edit option in (.*)")
    public void theUserWillSeeTheChosenWithEditButton(String option, String category) {
        boolean testResult = false;
        testResult = patientChoicePage.verifySelectedOption(option);
        Assert.assertTrue(testResult);
        testResult = patientChoicePage.verifyEditButton(category);
        Assert.assertTrue(testResult);
    }

    @Then("the (.*) option is marked as completed")
    public void theOptionIsMarkedAsCompleted(String category) {
        boolean testResult = false;
        testResult = patientChoicePage.optionIsCompleted(category);
        Assert.assertTrue(testResult);
    }

    @And("the user is in the section (.*)")
    public void theUserIsInTheSection(String sectionName) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyTheSectionTitle(sectionName);
        Assert.assertTrue(testResult);
    }

    @And("the user should see selected details displayed under the section (.*)")
    public void theUserShouldBeAbleToSeeSelectedChildAssentDetails(String sectionName,DataTable inputDetails) {
        List<List<String>> choiceOptions = inputDetails.asLists();
        for (int i = 0; i < choiceOptions.size(); i++) {
            Assert.assertTrue(patientChoicePage.verifySelectedChoicesInSection(sectionName,choiceOptions.get(i).get(0)));
            Wait.seconds(2);
        }
    }

    @And("the user will see a warning message {string}")
    public void theUserWillSeeAWarningMessage(String warningMessage) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyWarningMessage(warningMessage);
       Assert.assertTrue(testResult);
    }

    @And("the user will see a notification warning message {string}")
    public void theUserWillSeeANotificationWarningMessage(String warningMessage) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyNotificationWarning(warningMessage);
        Assert.assertTrue(testResult);
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

   @Then("the user should see a error message box with border color (.*) and message as (.*)")
    public void theUserShouldSeeAErrorMessageBox(String boxColor,String message) {
        boolean testResult = false;
        testResult = patientChoicePage.errorMessageInPatientChoicePage(boxColor,message);
        Assert.assertTrue(testResult);
    }

    @When("the user selects the proband")
    public void theUserSelectsTheProband() {
        boolean testResult = false;
        testResult = patientChoicePage.selectMember(0);
        Assert.assertTrue(testResult);
    }

    @And("the user answers the patient choice questions with agreeing to testing")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTesting() {
        boolean testResult = patientChoicePage.selectPatientChoiceCategory();
        Assert.assertTrue(testResult);
        Wait.seconds(2);
        testResult = patientChoicePage.selectTestType("Cancer (paired tumour normal) – WGS");
        Assert.assertTrue(testResult);
        Wait.seconds(2);
        testResult = patientChoicePage.enterRecordedByDetails();
        Assert.assertTrue(testResult);
        Wait.seconds(2);
        testResult = patientChoicePage.selectChoicesWithPatientChoiceNotRequired();
        Assert.assertTrue(testResult);
        Wait.seconds(2);
        testResult = patientChoicePage.submitPatientChoiceWithoutSignature();
        Assert.assertTrue(testResult);
        Wait.seconds(2);
    }

    @And("the user submits the patient choice with signature")
    public void theUserSubmitsThePatientChoiceWithSignature() {
        boolean testResult = false;
        testResult = patientChoicePage.submitPatientChoiceWithSignature();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the patient choice form with success message")
    public void theUserShouldBeAbleToSeeThePatientChoiceFormWithSuccessMessage() {
        boolean testResult = false;
        testResult = patientChoicePage.patientChoiceFormCompleted();
        Assert.assertTrue(testResult);
    }

    @And("the user should see patient choice submit button as (.*)")
    public void theUserShouldBeAbleToSeeTheHighlightedSubmitPatientChoiceButton(String status) {
        boolean testResult = false;
        testResult = patientChoicePage.verifySubmitPatientChoiceButtonStatus("#f0f0f0");
        if(status.equalsIgnoreCase("enabled")){
            Assert.assertTrue(testResult);
        }else {
            Assert.assertFalse(testResult);
        }
    }
    @And("the user should see form to follow button as (.*)")
    public void theUserShouldSeeHighlightedFormToFollowButton(String status) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyFormToFollowButtonStatus("#f0f0f0");
        if(status.equalsIgnoreCase("enabled")){
            Assert.assertFalse(testResult);
        }else {
            Assert.assertTrue(testResult);
        }
    }

    @And("the user should see Continue button as (.*)")
    public void theUserShouldSeeHighlightedContinueButton(String status) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyContinueButtonStatus("#f0f0f0");
        if(status.equalsIgnoreCase("enabled")){
            Assert.assertFalse(testResult);
        }else {
            Assert.assertTrue(testResult);
        }
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

    @Then("the user should be able to see hint text in search box on requesting organisation page")
    public void theUserShouldBeAbleToSeeHintTextInSearchBoxOnRequestingOrganisationPage() {
        Assert.assertTrue(patientChoicePage.verifyHintText());
    }

    @And("the user should be able to see an intro message {string} on requesting organisation page")
    public void theUserShouldBeAbleToSeeAnIntroMessageOnRequestingOrganisationPage(String introMessage) {
        Assert.assertTrue(patientChoicePage.verifyTheIntroMessage(introMessage));
    }

    @Then("the Patient Choice landing page is updated to {string} for the proband")
    public void thePatientChoiceLandingPageIsUpdatedToForTheProband(String expectedStatusInfo) {
        boolean testResult = false;
        testResult = patientChoicePage.statusUpdatedCorrectly(expectedStatusInfo, 0);
        Assert.assertTrue(testResult);
    }

    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYes() {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory();
        if (!testResult) {
            Assert.assertTrue("Cancer : Failure from patientChoicePage.selectPatientChoiceCategory.", false);
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectTestType("Cancer (paired tumour normal) – WGS");
        if (!testResult) {
            Assert.assertTrue("Cancer: Failure from patientChoicePage.selectTestType.", false);
        }
        Wait.seconds(2);
        testResult = patientChoicePage.enterRecordedByDetails();
        if (!testResult) {
            Assert.assertTrue("Cancer: Failure from patientChoicePage.enterRecordedByDetails.", false);
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectChoicesWithAgreeingTesting();
        if (!testResult) {
            Assert.assertTrue("Cancer: Failure from patientChoicePage.selectChoicesWithAgreeingTesting.", false);
        }
        Wait.seconds(2);
        testResult = patientChoicePage.drawSignature();
        if (!testResult) {
            Assert.assertTrue("Cancer:Failure from patientChoicePage.drawSignature.", false);
        }
        Wait.seconds(2);

    }
    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYesForRD() {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory();
        if (!testResult) {
            Assert.assertTrue("RD Proband : Failure from patientChoicePage.selectPatientChoiceCategory.", false);
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectTestType("Rare & inherited diseases – WGS");
        if (!testResult) {
            Assert.assertTrue("RD Proband : Failure from patientChoicePage.selectTestType.", false);
        }
        Wait.seconds(2);
        testResult = patientChoicePage.enterRecordedByDetails();
        if (!testResult) {
            Assert.assertTrue("RD Proband : Failure from patientChoicePage.enterRecordedByDetails.", false);
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectChoicesWithAgreeingTesting();
        if (!testResult) {
            Assert.assertTrue("RD Proband : Failure from patientChoicePage.selectPatientChoiceCategory.", false);
        }
        Wait.seconds(2);
        testResult = patientChoicePage.drawSignature();
        if (!testResult) {
            Assert.assertTrue("RD Proband : Failure from patientChoicePage.drawSignature.", false);
        }
        Wait.seconds(2);
    }

    @Then("the help text is displayed")
    public void theHelpTextIsDisplayed() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyHelpTextLabelIsVisible();
        Assert.assertTrue(testResult);
    }

    @Then("the user should see the question displayed as (.*)")
    public void theUserShouldShouldSeeTheSectionTitleAs(String sectionTitle) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyQuestionTitle(sectionTitle);
        Assert.assertTrue(testResult);
    }
    @And("the options displayed as below for the question (.*)")
    public void thePatientChoiceOptionsAsBelow(String question,DataTable inputDetails) {
        List<List<String>> choiceOptions = inputDetails.asLists();
        for (int i = 1; i < choiceOptions.size(); i++) {
            Assert.assertTrue(patientChoicePage.verifyTheChoiceOptionsForTheQuestion(question,choiceOptions.get(i).get(0)));
        }
    }

    @And("Save and continue button is displayed as (.*)")
    public void saveAndContinueButtonIsDisplayedAs(String expectedStatus) {
        boolean testResult = false;
        testResult = patientChoicePage.verifySaveAndContinueButtonStatus();
        if (expectedStatus.equals("enabled")) {
            Assert.assertTrue(testResult);
        } else {
             Assert.assertFalse(testResult);
        }
    }

    @When("the user clicks on the amend patient choice button")
    public void theUserClicksOnTheAmendPatientChoiceButton() {
        patientChoicePage.clickOnAmendPatientChoice();
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

    @And("the user should be able to clear the signature")
    public void theUserShouldBeAbleToClearTheSignature() {
        boolean testResult = false;
        testResult = patientChoicePage.clearTheSignature();
        Assert.assertTrue(testResult);
    }

    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes and uploading recording form")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYesAndUploadingForm() {
        patientChoicePage.selectPatientChoiceCategory();
        Wait.seconds(2);
        patientChoicePage.selectTestType();
        Wait.seconds(2);
        patientChoicePage.fillRecordedByDetails("","ClinicianName=John:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf");
        Wait.seconds(5);
       patientChoicePage.selectDefaultPatientChoices();
        Wait.seconds(2);
        patientChoicePage.clickOnContinue();
        Wait.seconds(2);
        if(!patientChoicePage.submitPatientChoice()){//Trying again as sometimes the click not happened.
            Debugger.println("Continuing from Patient Choice again as looks like the first click not happened.");
            patientChoicePage.clickOnContinue();
            Wait.seconds(2);
            patientChoicePage.submitPatientChoice();
            Wait.seconds(2);
        }

    }
    @And("the user save patient choice form and continue")
    public void theUserSavePatientChoiceFormAndContinue() {
       patientChoicePage.clickOnSaveAndContinueButton();
    }

    @Then("the user should see the supporting information links under the section (.*)")
    public void theUserShouldSeeTheSupportingInformationLinksUnderTheSection(String formSection, DataTable inputLinks) {
        try {
            boolean testResult = false;
            List<List<String>> linkDetails = inputLinks.asLists();
            testResult = patientChoicePage.verifyTheFormLibrarySection(formSection);
            if(!testResult){
                Debugger.println("Section "+formSection+" not present in the FormLibrary Tab.");
                Assert.assertTrue(testResult);
            }

            for (int i = 1; i < linkDetails.size(); i++) {
               testResult = patientChoicePage.verifyTheSupportingInformationLink(formSection,linkDetails.get(i).get(0));
               if(!testResult){
                   Debugger.println("Form "+linkDetails.get(i).get(0)+" could not verify in section:"+formSection);
                   Assert.assertTrue(testResult);
               }
               Wait.seconds(2);
            }
            Assert.assertTrue(testResult);
        } catch (Exception exp) {
            Debugger.println("Exception from supporting information links " + exp);
            Assert.assertFalse("PatientChoiceSteps: Exception from supporting information links: " + exp, true);
        }
    }
    @And("^the mandatory fields in patient choice shown with the symbol in red color$")
    public void theMandatoryFieldsShownWithSymbolInRedColor(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 1; i < messageDetails.size(); i++) {
            testResult = patientChoicePage.verifyMandatoryFieldDisplaySymbolInPatientChoice(messageDetails.get(i).get(0),messageDetails.get(i).get(1),messageDetails.get(i).get(2),messageDetails.get(i).get(3));
            if(!testResult){
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }
    @And("^the following optional fields should be displayed in patient choice section$")
    public void theOptionalFieldInPatientChoice(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 1; i < messageDetails.size(); i++) {
            testResult = patientChoicePage.verifyOptionalFieldPresence(messageDetails.get(i).get(0));
            if(!testResult){
                Assert.assertTrue(testResult);
            }
        }
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
            Wait.seconds(2);//Just waiting for 2 seconds between each upload
        }
    }
    @And("the file type dropdown options loaded with below details")
    public void theFileTypeDropdownOptions(DataTable inputDetails) {
        boolean testResult = false;
        List<String> fileTypes = inputDetails.asList();
        testResult = patientChoicePage.verifyFileTypeDropdownValues(fileTypes);
        Assert.assertTrue(testResult);
    }
    @And("the Date of Signature fields are displayed as (.*)")
    public void theDateOfSignatureFieldsAreDisplayedAs(String expStatus) {
        boolean testResult = false;
        testResult = patientChoicePage.dateOfSignatureStatusInRecordedBYSection();
        if (expStatus.equals("enabled")) {
            Assert.assertTrue(testResult);
        } else {
            Assert.assertFalse(testResult);
        }
    }
    @And("the user selects (.*) from dropdown option in recorded by")
    public void theUserSelectsFromDropdownOptionInRecordedBy(String dropdownValue) {
        boolean testResult = false;
        testResult = patientChoicePage.selectUploadFormType(dropdownValue);
        Assert.assertTrue(testResult);
    }
    @And("the user fills current date as Date of Signature")
    public void theUserFillsTheValidDateIn() {
        boolean testResult = false;
        testResult = patientChoicePage.fillTheDateOfSignatureInRecordedBy();
        Assert.assertTrue(testResult);
    }
    @Then("the user sees a success message after form upload in recorded by as (.*)")
    public void theUserSeesASuccessMessageAfterFormUploadInRecordedByAs(String expMessage) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyFormUploadSuccessMessage(expMessage);
        Assert.assertTrue(testResult);
    }
    @And("the user will see a {string} message on upload section")
    public void theUserWillSeeAMessageOnUploadSection(String message) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyUploadMessage(message);
        Assert.assertTrue(testResult);
    }
    @When("the user clicks the Save and Continue button on the patient choice")
    public void theUserClicksTheSaveAndContinueButtonOnThe() {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnSaveAndContinueButton();
        Assert.assertTrue(testResult);

    }
    @And("the user sees the patient choice status for family member (.*) as (.*)")
    public void theFamilyMemberPatientChoice(String familyNumber, String status) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyPatientChoiceStatus(status, Integer.parseInt(familyNumber));
        Assert.assertTrue(testResult);
    }

    @And("the user sees the test badge status for family member as (.*)")
    public void theFamilyMemberTestBadge(String status) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyFamilyMemberTestBadge(status);
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on patient choice status link for family member (.*)")
    public void clickOnFamilyMemberPatientChoice(String familyNumber) {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnPatientChoiceStatusLink(Integer.parseInt(familyNumber));
        Assert.assertTrue(testResult);
    }

    @And("the user should able to see TO DO list even after clicking the Save and Continue button")
    public void theUserShouldAbleToSeeTODOListEvenAfterClickingTheSaveAndContinueButton() {
        boolean testResult = false;
        testResult = referralPage.checkThatToDoListSuccessfullyLoaded();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("ToDoList.jpg");
            Assert.assertFalse("ToDoList in Referral Page is not loaded after PC submitting Patient Choice..", true);
        }
        Assert.assertTrue(testResult);
    }

    @And("the user should see the details of family members displayed in patient choice landing page")
    public void detailsOfPatientsInPatientChoicePage(DataTable inputDetails) {
        List<List<String>> memberDetails = inputDetails.asLists();
        NGISPatientModel familyMember = null;
        boolean testResult = false;
        for (int i = 1; i < memberDetails.size(); i++) {
            familyMember = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
            testResult = patientChoicePage.verifyFamilyMemberDetailsPatientChoicePage(familyMember);
            Assert.assertTrue(testResult);
            Wait.seconds(2);
        }
    }
    @And("the user should wait for session expiry time (.*) seconds")
    public void theUserShouldWaitUntilTokenExpires(String timeToWait) {
        patientChoicePage.waitUntilTokenExpire(Integer.parseInt(timeToWait));
    }

    @And("the user should be able to see the uploaded file name {string}")
    public void theUserShouldBeAbleToSeeTheUploadedFileName(String fileName) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyUploadedFileName(fileName);
        Assert.assertTrue(testResult);

    }

    @Then("the user is navigated to a page of section with title (.*)")
    public void theUserIsNavigatedToAPageOfSectionPatientChoiceHistory(String title) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyThePageSectionTitleInPatientChoicePage(title);
        Assert.assertTrue(testResult);
    }
    @Then("the user verifies the referral id on history tab is same as on referral id on referral bar")
    public void theUserVerifiesTheReferralIdOnHistoryTabIsSameAsOnReferralIdOnReferralBar() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyReferralIdOnHistoryTabIsSameAsOnReferralIdOnReferralBar();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on Form To Follow Button")
    public void theUserClicksOnFormToFollowButton() {
        patientChoicePage.clickOnFormToFollow();
    }

    @When("the user Cancel the uploaded forms")
    public void theUserCancelUploadedForms() {
        patientChoicePage.clickOnCancelUpload();
    }

    @Then("the user should not see any uploaded files")
    public void theUserShouldNotSeeAnyUploadedFiles() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyUploadedFileName("");
        Assert.assertTrue(testResult);
    }

}//end
