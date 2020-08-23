package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.ConcurrencyTest;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ConcurrencyStepDefs extends Pages {

    public ConcurrencyStepDefs(SeleniumDriver driver) {
        super(driver);
    }

    @When("^the user waits max (\\d+) minutes for the update (.*) in the file (.*)$")
    public void waitForTheUpdateInConcurrencyControllerFile(int waitTime, String expectedUpdate, String filePrefix) {
        Debugger.println(TestUtils.currentUser + ": Reading from File: " + expectedUpdate);
        try {
            boolean isUpdatePresent = ConcurrencyTest.verifyTextPresence(expectedUpdate, filePrefix);
            int actWaitTime = 0;
            Debugger.println(TestUtils.currentUser + ",Waiting max for (mins): " + waitTime + " for the update: " + expectedUpdate);
            while (!isUpdatePresent) {//Check every 15 seconds, the presence of expected update
                SeleniumLib.sleepInSeconds(30);
                isUpdatePresent = ConcurrencyTest.verifyTextPresence(expectedUpdate, filePrefix);
                actWaitTime = actWaitTime + 30;
                if (actWaitTime > (waitTime * 60)) {
                    break;
                }
            }
            if (!isUpdatePresent) {
                Debugger.println(TestUtils.currentUser + ",Could not read : " + expectedUpdate + " even after " + waitTime + " minutes.");
                Assert.fail("Expected update:" + expectedUpdate + " not updated by any users even after " + waitTime + " minutes, failing.");
            }
        } catch (Exception exp) {
            Debugger.println("Exception in waitForTheUpdateInConcurrencyControllerFile:" + exp);
            Assert.fail("Exception in waitForTheUpdateInConcurrencyControllerFile:" + exp);
        }
    }

    @And("the user updates the stage {string} with {string}")
    public void theUserUpdatesPatientDetailsStageWith(String stageName, String updateDetails) {
        Debugger.println(TestUtils.currentUser + " : Updating " + stageName + " with " + updateDetails);
        boolean testResult = false;
        if (stageName.equalsIgnoreCase("Patient details")) {
            testResult = patientDetailsPage.updatePatientDetails(updateDetails);
        } else if (stageName.equalsIgnoreCase("Requesting organisation")) {
            testResult = paperFormPage.fillInSpecificValueInSearchField(updateDetails);
            if (!testResult) {
                Assert.fail("Could not search for Order entity.");
            }
            testResult = paperFormPage.checkThatEntitySuggestionsAreDisplayed();
        } else if (stageName.equalsIgnoreCase("Test package")) {
            testResult = testPackagePage.selectNumberOfParticipants(Integer.parseInt(updateDetails));
        } else if (stageName.equalsIgnoreCase("Responsible clinician")) {
            testResult = responsibleClinicianPage.fillResponsibleClinicianFields(updateDetails);
            if (!testResult) {
                Assert.fail("Responsible Clinician Details could not enter.");
            }
        } else if (stageName.equalsIgnoreCase("Clinical questions")) {
            testResult = clinicalQuestionsPage.fillDiseaseStatusAgeOfOnset(updateDetails);
            if (!testResult) {
                Assert.fail("Clinical Questions Details could not be enter.");
            }
        } else if (stageName.equalsIgnoreCase("Notes")) {
            testResult = notesPage.fillInAddNotes(updateDetails);
            if (!testResult) {
                Assert.fail("Notes Details could not be enter.");
            }
        } else if (stageName.equalsIgnoreCase("Family members")) {
            familyMemberDetailsPage.editFamilyMember();
            familyMemberDetailsPage.editPatientDetails();
            testResult = patientDetailsPage.updateFamilyMemberDetails(updateDetails);
            if (!testResult) {
                Assert.fail("Family member details could not be enter.");
            }
        }  else if (stageName.equalsIgnoreCase("Patient choice")) {
            testResult = patientChoicePage.theUserAnswersThePatientChoiceQuestionswithPatientChoiceNotrequiredForRD(updateDetails);
            referralPage.clickSaveAndContinueButton();
            if (!testResult) {
                Assert.fail("Patient Choice details could not be enter.");
            }
        } else if (stageName.equalsIgnoreCase("Panels")) {
            testResult = panelsPage.updatePanelDetails(updateDetails);
        }

    }

    @And("the user updates the page {string} with {string}")
    public void theUserUpdatesFamilyMemberClinicalDetails(String pageName, String updateDetails){
        Debugger.println(TestUtils.currentUser + " : Updating " + pageName + " with " + updateDetails);
        boolean testResult = false;
        referralPage.clickSaveAndContinueButton();
        referralPage.clickSaveAndContinueButton();
        if (pageName.equalsIgnoreCase("Add family member details")) {
            testResult = clinicalQuestionsPage.updateFamilyMemberClinicalDetails(updateDetails);
        }
        Assert.assertTrue(testResult);
    }
    @And("the user verifies the page {string} with {string}")
    public void theUserVerifiesFamilyMemberClinicalDetails(String pageName, String verifyDetails){
        Debugger.println(TestUtils.currentUser + " : Updating " + pageName + " with " + verifyDetails);
        boolean testResult = false;
        referralPage.clickSaveAndContinueButton();
        referralPage.clickSaveAndContinueButton();
        referralPage.clickSaveAndContinueButton();
        if (pageName.equalsIgnoreCase("Add family member details")) {
            testResult = clinicalQuestionsPage.verifyFamilyMemberClinicalQuestions(verifyDetails);
        }
        Assert.assertTrue(testResult);
    }

    @When("the user updates the file (.*) with (.*)")
    public void theUserUpdateConcurrencyControllerFileWith(String filePrefix, String stringToUpdate) {
        Debugger.println(TestUtils.currentUser + ": Writing to File: " + stringToUpdate);
        boolean testResult = ConcurrencyTest.writeToControllerFile(filePrefix, stringToUpdate);
        if (!testResult) {
            Assert.fail("Could not write the update:" + stringToUpdate + " to the file.");
        }
    }

    @And("the user verifies the stage {string} with {string}")
    public void theUserVerifiesPatientDetailsStageWith(String stageName, String verifyDetails) {
        Debugger.println(TestUtils.currentUser + " : Verifying " + stageName);
        boolean testResult = false;
        if (stageName.equalsIgnoreCase("Patient details")) {
            testResult = patientDetailsPage.verifyPatientDetails(verifyDetails);
        } else if (stageName.equalsIgnoreCase("Requesting organisation")) {
            testResult = requestingOrganisationPage.verifyOrganisationDetails(verifyDetails);
        } else if (stageName.equalsIgnoreCase("Test package")) {
            testResult = testPackagePage.VerifyNumberOfParticipants(verifyDetails);
        } else if (stageName.equalsIgnoreCase("Responsible clinician")) {
            testResult = responsibleClinicianPage.verifyResponsibleClinicianDetails(verifyDetails);
        } else if (stageName.equalsIgnoreCase("Clinical questions")) {
            testResult = clinicalQuestionsPage.verifyClinicalQuestionsDetails(verifyDetails);
        } else if (stageName.equalsIgnoreCase("Notes")) {
            testResult = notesPage.verifyNotesDetails(verifyDetails);
        }  else if (stageName.equalsIgnoreCase("Family members")) {
            familyMemberDetailsPage.editFamilyMember();
            familyMemberDetailsPage.editPatientDetails();
            testResult = patientDetailsPage.verifyFamilyMemberDetails(verifyDetails);
            if (!testResult) {
                Assert.fail("Family member details could not be enter.");
            }
        } else if (stageName.equalsIgnoreCase("Patient choice")) {
            testResult = patientChoicePage.statusUpdatedCorrectlyforproband(verifyDetails);
        } else if (stageName.equalsIgnoreCase("Panels")) {
            testResult = panelsPage.verifyPanelDetails(verifyDetails);
        }

        Assert.assertTrue(testResult);
    }

}
;
