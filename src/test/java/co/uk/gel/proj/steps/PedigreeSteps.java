package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;

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

public class PedigreeSteps extends Pages {
    public PedigreeSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user clicks on the specified node on the pedigree diagram for {string}")
    public void theUserSearchTheFamilyMemberWithTheSpecifiedDetails(String searchDetails) {
        NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(searchDetails);
        if(patient == null){
            Debugger.println("Specified Family member could not get from the list.");
            return;
        }
        if(patient.getNGIS_ID() == null) {
            patient.setNGIS_ID(referralPage.getPatientNGISId());
        }

        boolean testResult = pedigreePage.clickSpecificNodeOnPedigreeDiagram(patient);
        Assert.assertTrue(testResult);
    }
    @And("the user should be able see the pedigree diagram loaded for the given members")
    public void theUserShouldSeeThePedigreeDiagramLoadedForMembers(DataTable members) {
        boolean testResult = false;
        List<List<String>> memberDetails = members.asLists();
        for (int i = 1; i < memberDetails.size(); i++) {
            NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
            if (patient == null) {
                Debugger.println("Specified Member" + memberDetails.get(i).get(0) + " could not get from the list.");
            }
            if (patient.getNGIS_ID() == null) {
                patient.setNGIS_ID(referralPage.getPatientNGISId());
            }
            testResult = pedigreePage.clickSpecificNodeOnPedigreeDiagram(patient);
            Assert.assertTrue(testResult);
            Wait.seconds(10);//Waiting to ensure the diagram loaded and dissappeard
        }
    }
    @And("the user will see a warning message {string} on the pedigree page")
    public void theUserWillSeeAWarningMessageOnThePatientChoiceInformationOption(String warningMessage) {
        boolean testResult = false;
        testResult = pedigreePage.pedigreeWarningMessage(warningMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see a {string} on the pedigree page")
    public void theUserShouldBeAbleToSeeAOnThePedigreePage(String warningMessage) {
        boolean testResult = false;
        testResult = pedigreePage.warningMessageInPedigree(warningMessage);
        Assert.assertTrue(testResult);
    }
    @Then("the user should be able to see following content on pedigree page")
    public void theUserShouldBeAbleToSeeFollowingContentOnPedigreePage(DataTable fieldDetails) {
        boolean testResult = false;
        testResult = pedigreePage.contentOnPedigree(fieldDetails);
        Assert.assertTrue(testResult);
    }
    @Then("the user should see the below messages displayed in the pedigree page")
    public void theUserShouldBelowMessageDisplayedOnPedigreePage(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 0; i < messageDetails.size(); i++) {
            testResult = pedigreePage.verifyPedigreeIntroMessages(messageDetails.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }

    @And("the user verifies the pop up is present and close it")
    public void theUserVerifiesThePopUpIsPresentAndCloseIt() {
        boolean testResult = false;
        testResult = pedigreePage.popupMessageInPedigree();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on the unassigned participants drop down Link")
    public void theUserClicksOnTheUnassignedParticipantsDropDownLink() {
        pedigreePage.unassignedParticipantsDropDown();
    }
    @And("the user select the pedigree tab (.*)")
    public void theUserClicksTheSpecifiedOnTheNode(String value) {
        boolean testResult=false;
        testResult= pedigreePage.clickSpecificPedigreeTab(value);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see below fields on Clinical Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnClinicalTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsStatusOnClinicalTab(fields.get(i).get(0),fields.get(i).get(1));
            if(!testResult){
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user should see below fields on Phenotype Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnPhenotypeTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsStatusOnPhenotypeTab(fields.get(i).get(0),fields.get(i).get(1));
            if(!testResult){
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user should see below fields on Tumours Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnTumoursTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsStatusOnTumoursTab(fields.get(i).get(0),fields.get(i).get(1));
            if(!testResult){
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the below field values should be displayed properly on Clinical Tab")
    public void theUserShouldBeAbleToReadTheGivenFiledValues(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsValueOnClinicalTab(fields.get(i).get(0));
            if(!testResult){
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }
    @Then("the user should be able to see (.*) button on Pedigree Page")
    public void theUserShouldBeAbleToSeeOnlySaveButton(String buttonName) {
        boolean testResult = false;
        testResult = pedigreePage.verifyPresenceOfButton(buttonName);
        Assert.assertTrue(testResult);
    }
    @And("the user clicks on Save and Continue on Pedigree Page")
    public void theUserClicksOnSaveAndContinueOnPedigree() {
        boolean testResult = false;
        testResult = pedigreePage.saveAndContinueOnPedigree();
        Assert.assertTrue(testResult);
    }

}//end
