package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
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
        testResult = patientChoicePage.fillRecordedByDetails(patientChoice.get(2).get(0));
        Assert.assertTrue(testResult);
        patientChoicePage.clickOnContinue();
        testResult = patientChoicePage.selectPatientChoice(patientChoice.get(3).get(0));
        Assert.assertTrue(testResult);
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
                return;
            }
            for (int i = 1; i < memberDetails.size(); i++) {
                patientChoicePage.editSpecificPatientChoice(memberDetails.get(i).get(0));
                patientChoicePage.selectPatientChoiceCategory(memberDetails.get(i).get(1));
                patientChoicePage.selectTestType(memberDetails.get(i).get(2));
                patientChoicePage.fillRecordedByDetails(memberDetails.get(i).get(3));
                patientChoicePage.clickOnContinue();
                patientChoicePage.selectPatientChoice(memberDetails.get(i).get(4));
                patientChoicePage.clickOnContinue();
                patientChoicePage.selectChildAssent(memberDetails.get(i).get(5));
                patientChoicePage.clickOnContinue();
                patientChoicePage.fillParentSignatureDetails(memberDetails.get(i).get(6));
                patientChoicePage.submitPatientChoice();
                referralPage.clickSaveAndContinueButton();
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
}//end
