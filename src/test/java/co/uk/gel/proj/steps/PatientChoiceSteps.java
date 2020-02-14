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
        boolean testResult = false;
        testResult = patientChoicePage.editPatientChoice();
        Assert.assertTrue(testResult);
    }

    @When("the user edits patient choice for {string} family members with the below details")
    public void theUserEditsPatientChoiceForFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        try {
            int noOfParticipants = Integer.parseInt(noParticipant);
            List<List<String>> memberDetails = inputDetails.asLists();
            for (int i = 1; i < memberDetails.size(); i++) {
                patientChoicePage.selectMember(i);
                Wait.seconds(2);
                Assert.assertTrue(patientChoicePage.selectPatientChoiceCategory(memberDetails.get(i).get(1)));
                Wait.seconds(2);
                Assert.assertTrue(patientChoicePage.selectTestType(memberDetails.get(i).get(2)));
                Wait.seconds(2);
                 Assert.assertTrue(patientChoicePage.fillRecordedByDetails(memberDetails.get(i).get(0), memberDetails.get(i).get(3)));
                Wait.seconds(2);
                patientChoicePage.clickOnContinue();
                Wait.seconds(2);
                patientChoicePage.selectOptionForQuestion("Patient has agreed to the test", "Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?");
                patientChoicePage.selectOptionForQuestion("Yes", "Has research participation been discussed?");
                patientChoicePage.selectOptionForQuestion("Yes", "The patient agrees that their data and samples may be used for research, separate to NHS care.");
                Wait.seconds(2);
                patientChoicePage.clickOnContinue();
                if(memberDetails.get(i).get(5) != null && !memberDetails.get(i).get(5).isEmpty()) {
                    Assert.assertTrue(patientChoicePage.selectChildAssent(memberDetails.get(i).get(5)));
                    patientChoicePage.clickOnContinue();
                }
                if(memberDetails.get(i).get(6) != null && !memberDetails.get(i).get(6).isEmpty()) {
                    //patientChoicePage.fillTheSignatureDetails(memberDetails.get(i).get(6));
                    patientChoicePage.drawSignature();
                }
                if (!patientChoicePage.submitPatientChoice()) {
                    referralPage.navigateToStage("Patient choice");
                    Wait.seconds(5);
                    continue;
                }
                patientChoicePage.clickOnSaveAndContinueButton();
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

    @And("the user sees the patient choice status for proband as (.*)")
    public void theUserSeesThePatientChoiceStatus(String status) {
        boolean testResult = false;
        //For proband he index will be 0
        testResult = patientChoicePage.verifyPatientChoiceStatus(status, 0);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see the patient identifiers on patient choice page similar as in family member landing page")
    public void theUserShouldBeAbleToSeeThePatientIdentifiersOnPatientChoicePage() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyPatientIdentifiersInPatientChoicePage();
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

    @Then("the option (.*) displayed with edit option in (.*)")
    public void theUserWillSeeTheChosenWithEditButton(String option, String category) {
        Assert.assertTrue(patientChoicePage.verifySelectedOption(option));
        Assert.assertTrue(patientChoicePage.verifyEditButton(category));
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

    @And("the user should see continue button is highlighted in color (.*)")
    public void theUserShouldSeeContinueButtonIsNotHighlighted(String backgroundColor) {
        Assert.assertTrue(patientChoicePage.notHighlightedContinueButton(backgroundColor));
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
        patientChoicePage.selectTestType("Cancer (paired tumour normal) – WGS");
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

    @And("the user should see patient choice submit button as (.*)")
    public void theUserShouldBeAbleToSeeTheHighlightedSubmitPatientChoiceButton(String status) {
        boolean testResult = false;
        testResult = patientChoicePage.verifySubmitPatientChoiceButtonStatus("#f0f0f0");
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
         Assert.assertTrue(patientChoicePage.statusUpdatedCorrectly(expectedStatusInfo, 0));
    }

    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYes() {

        patientChoicePage.selectPatientChoiceCategory();
        Wait.seconds(2);
        patientChoicePage.selectTestType("Cancer (paired tumour normal) – WGS");
        Wait.seconds(2);
        patientChoicePage.enterRecordedByDetails();
        Wait.seconds(2);
        patientChoicePage.selectChoicesWithAgreeingTesting();
        Wait.seconds(2);
        patientChoicePage.drawSignature();
        Wait.seconds(2);

    }
    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYesForRD() {
        patientChoicePage.selectPatientChoiceCategory();
        Wait.seconds(2);
        patientChoicePage.selectTestType("Rare & inherited diseases – WGS");
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

    @When("the user clicks on {string} link")
    public void theUserClicksOnLink(String link) {
        patientChoicePage.clickOnLink(link);
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
    @And("the Date of Signature fields are displayed as {string}")
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
    @Then("the user sees a success message after form upload in recorded by as {string}")
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
        referralPage.clickSaveAndContinueButtonOnThePatientChoiceComponent();

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

    @And("the user should be able to see the uploaded file name")
    public void theUserShouldBeAbleToSeeTheUploadedFileName() {
        boolean testResult = false;
        testResult = patientChoicePage.uploadedfileNameIsDisplayedOnThePage();
        Assert.assertTrue(testResult);

    }
    @Then("the user verifies that the newly uploaded file name doesn't match with the old uploaded file name")
    public void theNewUploadedFileNameDoesnTMatchWithTheOldUploadedFileName() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyNewUploadedFileNameNotMatchingWithOldFileName();
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

}//end
