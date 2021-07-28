package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.FamilyMemberDetailsPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.AWS3Connect;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
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
                Wait.seconds(8);
                //Debugger.println("Selected Family Member:");
                testResult = patientChoicePage.selectPatientChoiceCategory(memberDetails.get(i).get(1));
                if(!testResult){
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCEdit.jpg");
                    Assert.fail("FM "+i+": Could not select Patient Choice Category:"+memberDetails.get(i).get(1));
                    break;
                }
                Wait.seconds(2);
                //Debugger.println("Category Done:");
                testResult = patientChoicePage.selectTestType(memberDetails.get(i).get(2));
                if(!testResult){
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCTestType.jpg");
                    Assert.fail("FM "+i+": Could not select Patient Choice Test Type:"+memberDetails.get(i).get(2));
                    break;
                }
                Wait.seconds(2);
                //Debugger.println("Test Type done:");
                testResult = patientChoicePage.fillRecordedByDetails(memberDetails.get(i).get(0), memberDetails.get(i).get(3));
                if(!testResult){
                    Assert.fail("FM "+i+": Could not fill Patient Choice RecordedBy Details:"+memberDetails.get(i).get(3));
                    break;
                }
                Wait.seconds(2);
                //Debugger.println("Record details done:");
                testResult = patientChoicePage.clickOnContinue();
                if(!testResult){
                    Assert.fail("FM "+i+": Could not Click Patient Choice Continue");
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
                //Debugger.println("Submitting PC:");
                if (!patientChoicePage.submitPatientChoice()) {
                    referralPage.navigateToStage("Patient choice");
                    Wait.seconds(5);
                    continue;
                }
                patientChoicePage.clickOnSaveAndContinueButton();
                Wait.seconds(10);//Waiting for 10 seconds as there is a delay observed in patient choice page in e2elatest
                Debugger.println("\nPC done for FM:"+i);
            }//end
            Wait.seconds(10);
            if(AppConfig.snapshotRequired){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientChoiceFM");
            }
        } catch (Exception exp) {
            Debugger.println("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp+"\n"+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientChoiceFM");
            Assert.fail("PatientChoiceSteps: Exception in Filling FM PatientChoice Details: " + exp);
        }
    }
    @When("the user completes the patient choice for below family members as agreeing to test")
    public void theUserCompletesThePatientChoiceForBelowFamilyMembers(DataTable inputDetails) {
        try {
            List<List<String>> memberDetails = inputDetails.asLists();
            for (int i = 1; i < memberDetails.size(); i++) {//First line is title
                Debugger.println("Patient Choice for Member: "+i);
                if(!patientChoicePage.selectMember(i)){
                    Assert.fail("Could not select the member to complete PC");
                }
                Wait.seconds(2);
                if(!patientChoicePage.completePatientChoiceWithAgreeingTestForFamilyMember(memberDetails.get(i).get(0),memberDetails.get(i).get(1),memberDetails.get(i).get(2))){
                    Assert.fail("Could not complete PC for the member "+i);
                }
                Wait.seconds(5);//After submitting PC
                if(!patientChoicePage.clickOnSaveAndContinueButton()){
                    Assert.fail("Could not proceed from PC after submitting Patient Choice..");
                }
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
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Subtitle");
            Assert.fail("Could not see PC subtitle."+subtitle);
        }
    }

    @And("the user fills {string} details in recorded by")
    public void theUserFillsDetailsInRecordedBy(String recordedBy) {
        boolean testResult = false;
        testResult = patientChoicePage.fillRecordedByDetails("", recordedBy);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_RecordBy");
            Assert.fail("Could not fill recorder by details.");
        }
    }

    @When("the user selects the option (.*) for the question (.*)")
    public void theUserFillsDetailsInPatientChoices(String option,String question) {
        boolean testResult = false;
        testResult = patientChoicePage.selectOptionForQuestion(option,question);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Option");
            Assert.fail("Could not select Question option");
        }
        Wait.seconds(3);
    }

    @When("the user fills PatientSignature details in patient signature")
    public void theUserFillsDetailsInPatientSignature() {
        boolean testResult = false;
        testResult = patientChoicePage.drawSignature();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Signature");
            Assert.fail("Could not draw signature.");
        }
        Wait.seconds(3);
    }

    @Then("the option (.*) displayed with edit option in (.*)")
    public void theUserWillSeeTheChosenWithEditButton(String option, String category) {
        boolean testResult = false;
        testResult = patientChoicePage.verifySelectedOption(option);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCOtion");
            Assert.fail("Could not see selected option "+option);
        }
        testResult = patientChoicePage.verifyEditButton(category);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Edit");
            Assert.fail("Could not Edit the option "+category);
        }
    }

    @Then("the (.*) option is marked as completed")
    public void theOptionIsMarkedAsCompleted(String category) {
        boolean testResult = false;
        testResult = patientChoicePage.optionIsCompleted(category);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Option");
            Assert.fail("Could not see option marked as completed "+category);
        }
    }

    @And("the user is in the section (.*)")
    public void theUserIsInTheSection(String sectionName) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyTheSectionTitle(sectionName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Section");
            Assert.fail("Could not see the section "+sectionName);
        }
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
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_WarningMsg");
            Assert.fail("Could not see warning message "+warningMessage);
        }
    }

    @And("the user will see a notification warning message {string}")
    public void theUserWillSeeANotificationWarningMessage(String warningMessage) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyNotificationWarning(warningMessage);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_WarningMsg");
            Assert.fail("Could not see warning message "+warningMessage);
        }
    }

    @When("the user clicks on edit button in (.*)")
    public void theUserClicksOnEditButton(String category) {
        boolean testResult = false;
        testResult= patientChoicePage.clickOnEditButton(category);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Edit");
            Assert.fail("Could not click on Edit button ");
        }
    }

    @Then("the user should be able to see previous section re-opened")
    public void theUserShouldBeAbleToSeePreviousSectionReOpened() {
        boolean testResult = false;
        testResult = patientChoicePage.previousSectionsReopened();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Reopen");
            Assert.fail("Could not see as previous section re-opened ");
        }
    }

   @Then("the user should see a error message box with border color (.*) and message as (.*)")
    public void theUserShouldSeeAErrorMessageBox(String boxColor,String message) {
        boolean testResult = false;
        testResult = patientChoicePage.errorMessageInPatientChoicePage(boxColor,message);
       if (!testResult) {
           SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ErrorMsg");
           Assert.fail("Could not see message "+message);
       }
    }

    @When("the user selects the proband")
    public void theUserSelectsTheProband() {
        boolean testResult = false;
        testResult = patientChoicePage.selectMember(0);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Edit");
            Assert.fail("Could not Edit member ");
        }
    }

    @And("the user answers the patient choice questions with agreeing to testing")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTesting() {
        boolean testResult = patientChoicePage.selectPatientChoiceCategory();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_tPCCategorySelect.jpg");
            Assert.fail("Could not select Patient Choice Category");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectTestType("Cancer (paired tumour normal) – WGS");
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCTestType.jpg");
            Assert.fail("Could not select test type Patient Choice");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.enterRecordedByDetails();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCrecordDetails.jpg");
            Assert.fail("Could enter record details Patient Choice");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectChoicesWithPatientChoiceNotRequired();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SelectPCChoice.jpg");
            Assert.fail("Could not Select Patient Choice");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.submitPatientChoiceWithoutSignature();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_SubmitPC.jpg");
            Assert.fail("Could not submit Patient Choice");
        }
        Wait.seconds(2);
    }

    @And("the user submits the patient choice with signature")
    public void theUserSubmitsThePatientChoiceWithSignature() {
        boolean testResult = false;
        testResult = patientChoicePage.submitPatientChoiceWithSignature();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCSubmit.jpg");
            Assert.fail("Patient Choice Could not submit with Signature.");
        }
    }

    @Then("the user should be able to see the patient choice form with success message")
    public void theUserShouldBeAbleToSeeThePatientChoiceFormWithSuccessMessage() {
        boolean testResult = false;
        testResult = patientChoicePage.patientChoiceFormCompleted();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCForm.jpg");
            Assert.fail("Patient Choice form could not complete.");
        }
    }

    @And("the user should see patient choice submit button as (.*)")
    public void theUserShouldBeAbleToSeeTheHighlightedSubmitPatientChoiceButton(String status) {
        boolean testResult = false;
        testResult = patientChoicePage.verifySubmitPatientChoiceButtonStatus(status,"#f0f0f0");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCSubmit.jpg");
            Assert.fail("Patient Choice Submit button status is not as expected:"+status);
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
            if(testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ContinueButton.jpg");
                Assert.fail("Continue button status is not as expected:"+status);
            }
        }else {
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ContinueButton.jpg");
                Assert.fail("Continue button status is not as expected:"+status);
            }
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
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCBackButton.jpg");
            Assert.fail("Patient Choice back button could not click.");
        }
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
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PC.jpg");
            Assert.fail("Patient Choice continue button not enabled");
        }
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCStatus.jpg");
            Assert.fail("Proband Patient Choice could not complete.");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientChoiceProband");
        }
    }

    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYes() {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientChoiceProband");
            Assert.fail("Cancer : Failure from patientChoicePage.selectPatientChoiceCategory.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectTestType("Cancer (paired tumour normal) – WGS");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCTestType");
            Assert.fail("Cancer: Failure from patientChoicePage.selectTestType.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.enterRecordedByDetails();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCRecordDetail");
            Assert.fail("Cancer: Failure from patientChoicePage.enterRecordedByDetails.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectChoicesWithAgreeingTesting();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCChoice");
            Assert.fail("Cancer: Failure from patientChoicePage.selectChoicesWithAgreeingTesting.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.drawSignature();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCSignature");
            Assert.fail("Cancer:Failure from patientChoicePage.drawSignature.");
        }
        Wait.seconds(2);

    }
    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYesForRD() {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientChoiceProband");
            Assert.fail("RD Proband : Failure from patientChoicePage.selectPatientChoiceCategory.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectTestType("Rare & inherited diseases – WGS");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCTestType");
            Assert.fail("RD Proband : Failure from patientChoicePage.selectTestType.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.enterRecordedByDetails();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCRecordDetail");
            Assert.fail("RD Proband : Failure from patientChoicePage.enterRecordedByDetails.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectChoicesWithAgreeingTesting();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCChoice");
            Assert.fail("RD Proband : Failure from patientChoicePage.selectPatientChoiceCategory.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.drawSignature();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCSignature");
            Assert.fail("RD Proband : Failure from patientChoicePage.drawSignature.");
        }
        Wait.seconds(2);
    }

    @Then("the help text is displayed")
    public void theHelpTextIsDisplayed() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyHelpTextLabelIsVisible();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCHelpText");
            Assert.fail("PC Help text not displayed.");
        }
    }

    @Then("the user should see the question displayed as (.*)")
    public void theUserShouldShouldSeeTheSectionTitleAs(String sectionTitle) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyQuestionTitle(sectionTitle);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCQnTitle");
            Assert.fail("Question title not displayed.");
        }
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
        boolean testResult = patientChoicePage.clickOnAmendPatientChoice();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCAmend");
            Assert.fail("Could not amend PC.");
        }
    }

    @And("the user should be able to see patient choice in history tab")
    public void theUserShouldBeAbleToSeePatientChoiceInHistoryTab() {
        boolean testResult = patientChoicePage.patientChoiceUnderHistoryTab();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCHistory");
            Assert.fail("PC History tab not present.");
        }
    }

    @Then("the user should be able to see replaced patient choice in history tab")
    public void theUserShouldBeAbleToSeeReplacedPatientChoiceInHistoryTab() {
        boolean testResult = patientChoicePage.replacedPatientChoiceUnderHistoryTab();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCHistory");
            Assert.fail("Replaced history tab not present");
        }
    }

    @When("the user fills {string} details for signature")
    public void theUserFillsDetailsForSignature(String signatureDetails) {
        boolean testResult = false;
        testResult = patientChoicePage.fillTheSignatureDetails(signatureDetails);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCSignature");
            Assert.fail("Could not fill Signature details");
        }
    }

    @And("the user should be able to clear the signature")
    public void theUserShouldBeAbleToClearTheSignature() {
        boolean testResult = false;
        testResult = patientChoicePage.clearTheSignature();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCSignature");
            Assert.fail("Could not clear the signature.");
        }
    }

    @And("the user answers the patient choice questions with agreeing to testing - patient choice Yes and uploading recording form")
    public void theUserAnswersThePatientChoiceQuestionsWithAgreeingToTestingPatientChoiceYesAndUploadingForm() {
        boolean testResult = patientChoicePage.selectPatientChoiceCategory();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PatientChoiceProband");
            Assert.fail("Failure from patientChoicePage.selectPatientChoiceCategory.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectTestType();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCTestType");
            Assert.fail("Failure from patientChoicePage.selectTestType.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.fillRecordedByDetails("","ClinicianName=John:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCRecordDetail");
            Assert.fail("Failure from patientChoicePage.enterRecordedByDetails.");
        }
        Wait.seconds(5);
        testResult = patientChoicePage.selectDefaultPatientChoices();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCChoice");
            Assert.fail("Failure from patientChoicePage.selectPatientChoiceCategory.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.clickOnContinue();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCContinue");
            Assert.fail("Failure from Clicking on Continue Button.");
        }
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

        boolean testResult = patientChoicePage.clickOnSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCClickAndContinue");
            Assert.fail("Could not click and continue on PC.");
        }
    }

    @Then("the user should see the supporting information links under the section (.*)")
    public void theUserShouldSeeTheSupportingInformationLinksUnderTheSection(String formSection, DataTable inputLinks) {
        try {
            boolean testResult = false;
            List<List<String>> linkDetails = inputLinks.asLists();
            testResult = patientChoicePage.verifyTheFormLibrarySection(formSection);
            if(!testResult){
                Debugger.println("Section "+formSection+" not present in the FormLibrary Tab.");
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFormLibrary");
                Assert.fail("Section "+formSection+" not present in the FormLibrary Tab.");
            }

            for (int i = 1; i < linkDetails.size(); i++) {
               testResult = patientChoicePage.verifyTheSupportingInformationLink(formSection,linkDetails.get(i).get(0));
               if(!testResult){
                   Debugger.println("Form "+linkDetails.get(i).get(0)+" could not verify in section:"+formSection);
                   SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFormLink");
                   Assert.fail("Form "+linkDetails.get(i).get(0)+" could not verify in section:"+formSection);
               }
               Wait.seconds(2);
            }
        } catch (Exception exp) {
            Debugger.println("Exception from supporting information links " + exp);
            Assert.fail("PatientChoiceSteps: Exception from supporting information links: " + exp);
        }
    }
    @And("^the mandatory fields in patient choice shown with the symbol in red color$")
    public void theMandatoryFieldsShownWithSymbolInRedColor(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 1; i < messageDetails.size(); i++) {
            testResult = patientChoicePage.verifyMandatoryFieldDisplaySymbolInPatientChoice(messageDetails.get(i).get(0),messageDetails.get(i).get(1),messageDetails.get(i).get(2),messageDetails.get(i).get(3));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCMandatoryField");
                Assert.fail("Field: not displayed as mandatory field.");
            }
        }
    }
    @And("^the following optional fields should be displayed in patient choice section$")
    public void theOptionalFieldInPatientChoice(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 1; i < messageDetails.size(); i++) {
            testResult = patientChoicePage.verifyOptionalFieldPresence(messageDetails.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCOptionalField");
                Assert.fail("Optional field not present.");
            }
        }
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
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUpload");
                Assert.fail("No file upload error message present.");
            }
            Wait.seconds(2);//Just waiting for 2 seconds between each upload
        }
    }
    @And("the file type dropdown options loaded with below details")
    public void theFileTypeDropdownOptions(DataTable inputDetails) {
        boolean testResult = false;
        List<String> fileTypes = inputDetails.asList();
        testResult = patientChoicePage.verifyFileTypeDropdownValues(fileTypes,0);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
            Assert.fail("No file upload drop down values not present.");
        }
    }

    @And("the file type dropdown options loaded with below details for {string} form type")
    public void theFileTypeDropdownOptionsLoadedWithBelowDetailsForFormType(String formNum,DataTable inputDetails) {
        boolean testResult = false;
        List<String> fileTypes = inputDetails.asList();
        if (formNum.equalsIgnoreCase("2nd")) { //to verify dropdown for 2nd form category
            testResult = patientChoicePage.verifyFileTypeDropdownValues(fileTypes,1);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
                Assert.fail("No file upload drop down values not present.");
            }
        } else if(formNum.equalsIgnoreCase("3rd")) { //to verify dropdown for 3rd form category
            testResult = patientChoicePage.verifyFileTypeDropdownValues(fileTypes,2);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
                Assert.fail("No file upload drop down values not present.");
            }
        }
    }

    @And("the Date of Signature fields are displayed as (.*)")
    public void theDateOfSignatureFieldsAreDisplayedAs(String expStatus) {
        boolean testResult = false;
        testResult = patientChoicePage.dateOfSignatureStatusInRecordedBYSection(0);
        if (expStatus.equals("enabled")) {
            Assert.assertTrue(testResult);
        } else {
            Assert.assertFalse(testResult);
        }
    }

    @And("the Date of Signature fields for the {string} form type are displayed as {string}")
    public void theDateOfSignatureFieldsAreDisplayedAsForTheFormType(String formNum,String expStatus) {
        boolean testResult = false;
        if (formNum.equalsIgnoreCase("2nd")) { //to fill date of signature for 2nd form category
            testResult = patientChoicePage.dateOfSignatureStatusInRecordedBYSection(1);
        } else if(formNum.equalsIgnoreCase("3rd")) { //to fill date of signature for 3rd form category
            testResult = patientChoicePage.dateOfSignatureStatusInRecordedBYSection(2);
        }
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
            Assert.fail("Upload form type not present.");
        }
    }

    @And("the user selects {string} from the {string} form type dropdown option in recorded by")
    public void theUserSelectsFromTheFormTypeDropdownOptionInRecordedBy(String dropdownValue,String formNum) {
        boolean testResult = false;
        if (formNum.equalsIgnoreCase("2nd")) { //to select dropdown for 2nd form category
            testResult = patientChoicePage.selectUploadFormTypeForFormNumber(dropdownValue,1);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
                Assert.fail("Signature field values not present.");
            }
        } else if(formNum.equalsIgnoreCase("3rd")) { //to select dropdown for 3rd form category
            testResult = patientChoicePage.selectUploadFormTypeForFormNumber(dropdownValue,2);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
                Assert.fail("Signature field values not present.");
            }
        }
    }
    @And("the user fills current date as Date of Signature")
    public void theUserFillsTheValidDateIn() {
        boolean testResult = false;
        testResult = patientChoicePage.fillTheDateOfSignatureInRecordedBy();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCDateOfSignature");
            Assert.fail("Date of Signature could not fill");
        }
    }

    @And("the user fills current date as Date of Signature in the {string} form type")
    public void theUserFillsTheValidDateInTheFormType(String formNum) {
        boolean testResult = false;
        if (formNum.equalsIgnoreCase("2nd")) { //to fill date for 2nd form category
            testResult = patientChoicePage.fillTheDateOfSignatureInRecordedByForFormNum(1);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
                Assert.fail("Signature field values not present.");
            }
        } else if(formNum.equalsIgnoreCase("3rd")) { //to fill date for 3rd form category
            testResult = patientChoicePage.fillTheDateOfSignatureInRecordedByForFormNum(2);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
                Assert.fail("Signature field values not present.");
            }
        }
    }

    @Then("the user sees a success message after form upload in recorded by as (.*)")
    public void theUserSeesASuccessMessageAfterFormUploadInRecordedByAs(String expMessage) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyFormUploadSuccessMessage(expMessage);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadMsg");
            Assert.fail("Upload success message not displayed.");
        }
    }

    @Then("the user sees a success message for {string} form type upload in recorded by as {string}")
    public void theUserSeesASuccessMessageForFormTypeUploadInRecordedByAsSuccessfullyUploaded(String formNumber, String expMessage) {
        boolean testResult = false;
        if (formNumber.equalsIgnoreCase("2nd")) { //to verify message for 2nd form category
            testResult = patientChoicePage.verifyFormUploadSuccessMessageForFormNum(expMessage, 1);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
                Assert.fail("Signature field values not present.");
            }
        } else if(formNumber.equalsIgnoreCase("3rd")) { //to verify message for 3rd form category
            testResult = patientChoicePage.verifyFormUploadSuccessMessageForFormNum(expMessage, 2);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadDD");
                Assert.fail("Signature field values not present.");
            }
        }
    }

    @And("the user will see a {string} message on upload section")
    public void theUserWillSeeAMessageOnUploadSection(String message) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyUploadMessage(message);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCFileUploadMsg");
            Assert.fail("Upload success message not displayed.");
        }
    }
    @When("the user clicks the Save and Continue button on the patient choice")
    public void theUserClicksTheSaveAndContinueButtonOnThe() {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnSaveAndContinueButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCSaveAndContinue");
            Assert.fail("Could not click on Save and Continue.");
        }

    }
    @And("the user sees the patient choice status for family member (.*) as (.*)")
    public void theFamilyMemberPatientChoice(String familyNumber, String status) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyPatientChoiceStatus(status, Integer.parseInt(familyNumber));
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCStatus");
            Assert.fail("Patient Choice status not displayed.");
        }
    }

    @And("the user sees the test badge status for family member as (.*)")
    public void theFamilyMemberTestBadge(String status) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyFamilyMemberTestBadge(status);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCBadge");
            Assert.fail("FM Badge status not present.");
        }
    }

    @When("the user clicks on patient choice status link for family member (.*)")
    public void clickOnFamilyMemberPatientChoice(String familyNumber) {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnPatientChoiceStatusLink(Integer.parseInt(familyNumber));
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCStatus");
            Assert.fail("PC Status link not displayed.");
        }
    }

    @And("the user should able to see TO DO list even after clicking the Save and Continue button")
    public void theUserShouldAbleToSeeTODOListEvenAfterClickingTheSaveAndContinueButton() {
        boolean testResult = false;
        testResult = referralPage.checkThatToDoListSuccessfullyLoaded();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ToDoList");
            Assert.fail("ToDoList in Referral Page is not loaded after PC submitting Patient Choice..");
        }
    }

    @And("the user should see the details of family members displayed in patient choice landing page")
    public void detailsOfPatientsInPatientChoicePage(DataTable inputDetails) {
        List<List<String>> memberDetails = inputDetails.asLists();
        NGISPatientModel familyMember = null;
        boolean testResult = false;
        for (int i = 1; i < memberDetails.size(); i++) {
            familyMember = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
            testResult = patientChoicePage.verifyFamilyMemberDetailsPatientChoicePage(familyMember);
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FMDetails");
                Assert.fail("FM details in PC Landing page not displayed..");
            }
            Wait.seconds(2);
        }
    }

    @And("the user should be able to see the uploaded file name {string}")
    public void theUserShouldBeAbleToSeeTheUploadedFileName(String fileName) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyUploadedFileName(fileName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_UploadFile");
            Assert.fail("Uploaded file name not present.");
        }
    }

    @Then("the user is navigated to a page of section with title (.*)")
    public void theUserIsNavigatedToAPageOfSectionPatientChoiceHistory(String title) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyThePageSectionTitleInPatientChoicePage(title);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PageTitle");
            Assert.fail("Page title "+title+" not present.");
        }
    }
    @Then("the user verifies the referral id on history tab is same as on referral id on referral bar")
    public void theUserVerifiesTheReferralIdOnHistoryTabIsSameAsOnReferralIdOnReferralBar() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyReferralIdOnHistoryTabIsSameAsOnReferralIdOnReferralBar();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_HistoryTab");
            Assert.fail("Referral id on history tab not present.");
        }
    }

    @When("the user clicks on Form To Follow Button")
    public void theUserClicksOnFormToFollowButton() {

        boolean testResult = patientChoicePage.clickOnFormToFollow();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FormToFollow");
            Assert.fail("Could not click on Form to follow.");
        }
    }

    @When("the user Cancel the uploaded forms")
    public void theUserCancelUploadedForms() {

        boolean testResult = patientChoicePage.clickOnCancelUpload();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CancelUpload");
            Assert.fail("Could not click on Cancel Upload.");
        }
    }

    @Then("the user should not see any uploaded files")
    public void theUserShouldNotSeeAnyUploadedFiles() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyUploadedFileName("");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCUpload");
            Assert.fail("Upload file could still see.");
        }
    }

    @And("the user clicks on the current completed referral")
    public void theUserClicksOnTheCurrentCompletedReferral() {
        boolean testResult = false;
        testResult = patientChoicePage.selectCompletedReferral();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CompletedReferral");
            Assert.fail("Could not click on Completed Referral.");
        }
    }

    @And("the user clicks on the {string} button")
    public void theUserClicksOnTheRemoveDocumentButton(String buttonText) {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnRemoveDocument(buttonText);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_RemoveDoc");
            Assert.fail("Could not click on button "+buttonText);
        }
    }

    @And("the user has to click on latest record of discussion")
    public void theUserHasToClickOnLatestRecordOfDiscussion() {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnRecordOfDiscussionForm();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_LastROD");
            Assert.fail("Could not click on Last record of discussion.");
        }
    }
    @And("the user should be able to view Withdraw from Research button on the top.")
    public void theUserShouldBeAbleToViewWithdrawFromResearchButtonOnTheTop() {
        boolean testResult = false;
        testResult = patientChoicePage.viewWithdrawButton();
        Assert.assertTrue(testResult);
    }
    @And("the user should click on that Withdraw button and should start providing details.")
    public void theUserShouldClickOnThatWithdrawButtonAndShouldStartProvidingDetails() {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnWithdrawButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Withdraw");
            Assert.fail("Could not click on Withdraw button.");
        }
    }
    @And("the user should selects the option (.*) in patient category")
    public void theUserShouldSelectsTheOptionOptionInPatientCategory(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory(inputData);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCCategory");
            Assert.fail("Could not select catagory."+inputData);
        }
    }
    @And("the user should provide answer as (.*) for the question (.*).")
    public void theUserShouldProvideAnswerAsYesForTheQuestionHasThePatientHadTheOpportunityToReadAndUnderstoodTheWithdrawalInformationAndHaveHadTheOpportunityToGetMoreInformationAndAskQuestions(String option, String question) {
        boolean testResult = false;
        testResult = patientChoicePage.selectOptionForQuestion(option,question);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCOption");
            Assert.fail("Could not Select the PC Option.");
        }
    }
    @And("the user should see the Withdrawal Received section")
    public void theUserShouldSeeTheWithdrawalReceivedSection() {
        boolean testResult = false;
        testResult = patientChoicePage.viewWithdrawalReceivedSection();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Withdraw");
            Assert.fail("Could not see withdrawal received section.");
        }
    }
    @And("the user should provide (.*) in Withdrawal Received details as")
    public void theUserShouldProvideInPersonInWithdrawalReceivedDetailsAs(String expButton) {
        boolean testResult = false;
        testResult = patientChoicePage.selectWithdrawalDetails(expButton);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Withdraw");
            Assert.fail("Could not provide withdrawal recieved details");
        }
    }
    @And("the user provide (.*) in Admin/Clinician Name section")
    public void theUserProvideAdminClinicianName(String adminName) {
        boolean testResult = false;
        testResult = patientChoicePage.fillAdminOrClinicianName(adminName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Admin");
            Assert.fail("Could not provide Admin/Clinician Name");
        }
    }
    @And("the user click on Submit Withdrawal button")
    public void theUserClickOnSubmitWithdrawalButton() {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnSubmitWithdrawalButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CancelUpload");
            Assert.fail("Could not click on Submit withdrawal.");
        }
    }
    @And("the user has to click on Withdrawal form.")
    public void theUserHasToClickOnWithdrawalForm() {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnWithdrawalForm();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Withdrawal");
            Assert.fail("Could not click on Withdrawal form.");
        }
    }
    @Then("the user should be able to see the Patient Type as (.*) ,according to Patient Category provided in Withdrawal Form")
    public void theUserShouldBeAbleToSeeThePatientTypeAsOptionAccordingToPatientCategoryProvidedInWithdrawalForm(String inputData) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyThePatientCategory(inputData);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCCategory");
            Assert.fail("Could not patient category details.");
        }
    }
    @And("the user click on Continue Button")
    public void theUserClickOnContinueButton() {
        boolean testResult = false;
        testResult = patientChoicePage.clickOnContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Continue");
            Assert.fail("Could not click on Continue button.");
        }
    }


    @Then("the user should able to see upload button is enable by default in Record by section")
    public void theUserShouldAbleToSeeUploadButtonIsEnableByDefaultInRecordBySection() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyUploadButtonStatus();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Upload");
            Assert.fail("Could not see upload button as enabled in Recorded by section.");
        }
    }

    @Then("the user should not be able to see the remove document button")
    public void theUserShouldNotBeAbleToSeeTheRemoveDocumentButton() {
        boolean testResult = false;
        testResult = patientChoicePage.verifyTheRemoveDocumentButtonIsNotPresent();
        Assert.assertTrue(testResult);
    }

    @And("the user see that proper message {string} is displayed after document is deleted")
    public void theUserSeeThatTheDocumentIsDeleted(String expectedDeletedMessage) {
        boolean testResult = false;
        testResult = patientChoicePage.verifyDeletedDocument(expectedDeletedMessage);
        Assert.assertTrue(testResult);
    }

    @And("the user is able to connect to the S3 bucket and read the files in folder {string}")
    public void theUserIsAbleToConnectToTheSBucketAndReadTheFilesInFolder(String s3FolderName) {
        boolean testResult = false;
        testResult = AWS3Connect.connectToS3Bucket();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_S3Connect");
            Assert.fail("Could not connect to the S3 bucket and access the folder.");
        }
    }
}//end
