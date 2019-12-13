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
}//end
