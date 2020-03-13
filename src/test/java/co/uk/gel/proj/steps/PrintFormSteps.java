package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.pages.FamilyMemberDetailsPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class PrintFormSteps extends Pages {

    public PrintFormSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user is able to download print forms for {string} family members with the below details")
    public void theUserDownloadsPrintFormsForFamilyMembersWithTheBelowDetails(String noParticipant, DataTable inputDetails) {
        try {
            boolean testResult = false;
            int noOfParticipants = Integer.parseInt(noParticipant);
            List<List<String>> memberDetails = inputDetails.asLists();
            if (memberDetails.size() < noOfParticipants) {
                Debugger.println("No of Participants mentioned and details provided are not matching.");
                //Continue with the availabe participants - modified for user journeys
            }

            for (int i = 1; i < memberDetails.size(); i++) {
                Debugger.println("Downloading and Verifying content for :" + memberDetails.get(i).get(0));
                if (!printFormsPage.downloadSpecificPrintForm(i, "RD")) {
                    Debugger.println("Could not download form for " + memberDetails.get(i).get(0));
                    testResult = false;
                    continue;
                }
                //Debugger.println("Downloaded...Verifying content....");
                NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
                if (familyMember == null) {
                    continue;//For Proband
                }
                String referralID = referralPage.getPatientReferralId();
                if (referralID != null) {
                    familyMember.setREFERAL_ID(referralID);
                }
                if (!printFormsPage.openAndVerifyPDFContent(familyMember, "RD")) {
                    Debugger.println("Could not verify PDF content for " + memberDetails.get(i).get(0));
                    testResult = false;
                    continue;
                }
                testResult = true;
            }//for
            Assert.assertTrue(testResult);
        } catch (Exception exp) {
            Debugger.println("PrintFormSteps: Exception in downloading PrintForms: " + exp);
            Assert.assertFalse("PrintFormSteps: Exception in downloading PrintForms: " + exp, true);
        }
    }

    @And("the user is able to download Sample form which has the correct user name, DOB , patient Id, ReferralId, Laboratory address, clinician info, Tumour info details")
    public void theUserIsAbleToDownloadSampleFormWhichHasTheCorrectUserNameDOBPatientIdReferralIdLaboratoryAddressClinicianInfoTumourInfoDetails() {
        boolean testResult = false;
        PatientDetailsPage.newPatient.setOrderingEntity(printFormsPage.getLaboratoryAddress());
        PatientDetailsPage.newPatient.setSampleType(printFormsPage.getSampleInfo());
        PatientDetailsPage.newPatient.setTumourType(printFormsPage.getTumourInfo());

        Debugger.println("Downloading and Verifying content the proband ");
        if (!printFormsPage.downloadProbandPrintForm()) {
            Debugger.println("Could not download form for proband");
            testResult = false;
        }
        Debugger.println("Downloaded the sample form...Verifying content....");

        NewPatient newlyCreatedPatientRecord = patientDetailsPage.getNewlyCreatedPatientData();
        String patientName = newlyCreatedPatientRecord.getPatientFullName();
        String dateOfBirth = newlyCreatedPatientRecord.getPatientDOBInMonthFormat();
        String gender = newlyCreatedPatientRecord.getGender();
        String patientNGISId = newlyCreatedPatientRecord.getReferralHumanReadableID();
        // Formatted patient NGIS Id
        patientNGISId = TestUtils.insertWhiteSpaceAfterEveryNthCharacter(patientNGISId, "4");
        String patientReferralId = newlyCreatedPatientRecord.getPatientHumanReadableID();
        // Formatted patient Referral Id
        patientReferralId = TestUtils.insertWhiteSpaceAfterEveryNthCharacter(patientReferralId, "4");

        String clinicalIndication = newlyCreatedPatientRecord.getClinicalIndication();
        String hospitalNumber = newlyCreatedPatientRecord.getHospitalNumber();

        String requestingOrg = newlyCreatedPatientRecord.getOrderingEntity();

        String responsibleClinicianName = newlyCreatedPatientRecord.getResponsibleClinicianName();
        String responsibleClinicianEmail = newlyCreatedPatientRecord.getResponsibleClinicianEmail();
        String responsibleClinicianContact = newlyCreatedPatientRecord.getResponsibleClinicianContactNumber();

        String tumourInfo = newlyCreatedPatientRecord.getTumourType();
        String sampleInfo = newlyCreatedPatientRecord.getSampleType();

        Debugger.println("Patient Name to verify in the downloaded PDF file                 : " + patientName);
        Debugger.println("Patient DOB to verify in the downloaded PDF file                  : " + dateOfBirth);
        Debugger.println("Patient Gender to verify in the downloaded PDF file               : " + gender);
        Debugger.println("Patient NGIS Is to verify in the downloaded PDF file               : " + patientNGISId);
        Debugger.println("Patient Referral Id to verify in the downloaded PDF file               : " + patientReferralId);

        Debugger.println("Patient CI     to verify in the downloaded PDF file               : " + clinicalIndication);
        Debugger.println("Ordering entity to verify in the downloaded PDF file              : " + requestingOrg);
        Debugger.println("Responsible Clinician Name to verify in the downloaded PDF file            : " + responsibleClinicianName);
        Debugger.println("Responsible Clinician Email to verify in the downloaded PDF file           : " + responsibleClinicianEmail);
        Debugger.println("Responsible Clinician contact number to verify in the downloaded PDF file  : " + responsibleClinicianContact);
        String[] tumours = tumourInfo.split(" •");
        Debugger.println("Tumour Type to verify in the downloaded PDF file  : " + tumours[0]);
        String[] samples = sampleInfo.split("sample required");
        Debugger.println("Sample info to verify in the downloaded PDF file  : " + samples[0]);

        List<String> expectedValuesToBeVerifiedInPDF = new ArrayList<>();
        expectedValuesToBeVerifiedInPDF.add(patientName);
        expectedValuesToBeVerifiedInPDF.add(dateOfBirth);
        expectedValuesToBeVerifiedInPDF.add(gender);

        expectedValuesToBeVerifiedInPDF.add(patientNGISId);
        expectedValuesToBeVerifiedInPDF.add(patientReferralId);

        expectedValuesToBeVerifiedInPDF.add(clinicalIndication);
        expectedValuesToBeVerifiedInPDF.add(requestingOrg);

        expectedValuesToBeVerifiedInPDF.add(responsibleClinicianName);
        expectedValuesToBeVerifiedInPDF.add(responsibleClinicianEmail);
        expectedValuesToBeVerifiedInPDF.add(responsibleClinicianContact);

        expectedValuesToBeVerifiedInPDF.add(tumours[0]);
        expectedValuesToBeVerifiedInPDF.add(samples[0]);
        testResult = printFormsPage.openAndVerifyPDFContent(expectedValuesToBeVerifiedInPDF);
        Assert.assertTrue(testResult);
    }

    @Then("the cancel referral dialog box is displayed with the following fields")
    public void theCancelReferralDialogBoxIsDisplayedWithTheFollowingFields(DataTable fieldDetails) {
        boolean testResult = false;
        List<List<String>> contents = fieldDetails.asLists();
        //Title,Question,Warning,button1,button2
        testResult = referralPage.validateCancelReferralDialog(contents.get(1).get(0),contents.get(2).get(0),contents.get(3).get(0),contents.get(4).get(0),contents.get(5).get(0));
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see referral status as cancelled with selected {string} reason")
    public void theUserShouldBeAbleToSeeReferralStatusAsCancelledWithSelectedReason(String reason) {
        boolean testResult = false;
        testResult = referralPage.referralCancelledStatusWithReason(reason);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see a {string} on the print forms page")
    public void theUserShouldBeAbleToSeeAOnThePrintFormsPage(String warningMessage) {
        boolean testResult = false;
        testResult = printFormsPage.verifyWarningMessageOnPrintFormsPage(warningMessage);
        Assert.assertTrue(testResult);
    }

    @And("the print forms stage is (.*)")
    public void thePrintFormsStageIsLocked(String lockStatus) {
        boolean testResult = false;
        if (lockStatus.equalsIgnoreCase("locked")) {
            testResult = printFormsPage.validateLockIconInPrintFormsStage(lockStatus);
            Assert.assertTrue(testResult);
        } else {
            testResult = printFormsPage.validateLockIconInPrintFormsStage(lockStatus);
            Assert.assertTrue(testResult);
        }
    }

    @Then("the user will not see back button on print forms page")
    public void theUserWillNotSeeBackButtonOnPrintFormsPage() {
        boolean testResullt = false;
        testResullt = printFormsPage.backButtonNotPresentOnPrintFormsPage();
        Assert.assertTrue(testResullt);
    }

    @And("the user is able to download print form for the proband")
    public void theUserIsAbleToDownloadPrintFormForTheProband() {
        boolean testResult = false;
        testResult = printFormsPage.downloadProbandPrintForm();
        Assert.assertTrue(testResult);
    }

    @Then("the user is able to validate the text {string} in the downloaded form {string}")
    public void theUserIsAbleToValidateTheTextInTheDownloadedForm(String expText, String formName) {
        boolean testResult = false;
        testResult = printFormsPage.validatePDFContent(expText, formName);
        Assert.assertTrue(testResult);
    }

    @Then("the user is able to download form of the {string} section and validate the text {string} in the file {string}")
    public void theUserIsAbleToDownloadFormOfTheSectionAndValidateTheTextInTheFile(String expectedSection, String expectedText, String fileName) {
        boolean testResult = false;
        if (expectedSection.equalsIgnoreCase("Referral")) {
            printFormsPage.downloadForm(fileName, expectedSection);
            testResult = printFormsPage.validatePDFContent(expectedText, fileName);
        }
        if (expectedSection.equalsIgnoreCase("Additional family members")) {
            printFormsPage.downloadForm(fileName, expectedSection);
            testResult = printFormsPage.validatePDFContent(expectedText, fileName);
        }
        if (expectedSection.equalsIgnoreCase("Patient choice")) {
            testResult=printFormsPage.downloadForm(fileName, expectedSection);
            if (!expectedText.isEmpty()) {
                testResult = printFormsPage.validatePDFContent(expectedText, fileName);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the user is able to verify the section {string} in the downloaded form {string}")
    public void theUserIsAbleToVerifyTheSectionInTheDownloadedForm(String fieldType, String fileName) {
        boolean testResult = false;
        if (fieldType.contains("test type")) {
            String testType = printFormsPage.readSelectedTestDetails();
            if (testType == null) {
                Debugger.println("No value present for " + fieldType + " on the Offline test order page ");
                Assert.assertTrue(testResult);
            }
            testResult = printFormsPage.validatePDFContent(testType, fileName);
            Assert.assertTrue(testResult);
        }
        else if (fieldType.contains("laboratory")) {
            String labDetails = printFormsPage.readSelectedLabDetails();
            if (labDetails == null) {
                Debugger.println("No value present for " + fieldType + " on the Offline test order page ");
                Assert.assertTrue(testResult);
            }
            testResult = printFormsPage.validatePDFContent(labDetails, fileName);
            Assert.assertTrue(testResult);
        }
        else {
            Debugger.println("The section name in the step is Not correct.: " + fieldType);
            Assert.assertTrue(testResult);
        }
    }

    @And("the user should be able to see referral card status as cancelled with selected {string} reason")
    public void theUserShouldBeAbleToSeeReferralCardStatusAsCancelledWithSelectedReason(String reason) {
        boolean testResult = false;
        testResult = referralPage.verifyReferralCancelledStatusOnPatientCard(reason);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks the cancelled patient referral card")
    public void theUserClicksTheCancelledPatientReferralCard() {
        boolean testResult = false;
        testResult = referralPage.clickOnCancelledReferralCard();
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see start a new Referral button")
    public void theUserShouldBeAbleToSeeStartANewReferralButton() {
        boolean testResult = false;
        testResult = printFormsPage.startANewReferralButton();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on start a new referral button")
    public void theUserClicksOnStartANewReferralButton() {
        boolean testResult = false;
        testResult = printFormsPage.clickOnStartANewReferralButton();
        Assert.assertTrue(testResult);
    }

    @And("the user is able to see the following guidelines below the confirmation message")
    public void theUserIsAbleToSeeTheFollowingGuidelinesBelowTheConfirmationMessage(DataTable guideLines) {
        boolean testResult = false;
        List<String> stages = guideLines.asList();
        for (int i = 0; i < stages.size(); i++) {
            testResult = printFormsPage.validateGuidelinesContent(stages.get(i));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user is able to download form of the {string} section having file name {string}")
    public void theUserIsAbleToDownloadFormOfTheSectionHavingFileName(String expectedSection, String fileName) {
        boolean testResult = false;
        if (expectedSection.equalsIgnoreCase("Patient choice")) {
            printFormsPage.downloadForm(fileName, expectedSection);
            testResult = printFormsPage.extractAndValidateZipFile(fileName);
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral")
    public void theUserSeesADialogBoxWithFollowingMandatoryStagesToBeCompletedForSuccessfulSubmissionOfAReferral(DataTable stageNames) {
        boolean testResult = false;
        List<String> stages = stageNames.asList();
        for (int i = 1; i < stages.size(); i++) {
            testResult = referralPage.validateMandatoryStages(stages.get(i));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to click {string} link to verify the address of the lab in the downloaded file")
    public void theUserShouldBeAbleToClickShowAddressLinkToVerifyTheAddressOfTheLabInTheDownloadedFile(String showAddress) {
        boolean testResult = false;
        String labAddress=printFormsPage.readLabAddress(showAddress);
        if(labAddress==null){
            Assert.assertTrue(testResult);
        }
        testResult = printFormsPage.validatePDFContent(labAddress,"SampleForm.pdf");
        Assert.assertTrue(testResult);
    }

    @And("the user verifies that the the relationship to proband {string} is updated in Print forms section")
    public void theUserVerifiesThatTheTheRelationshipToProbandIsUpdatedInPrintFormsSection(String realationToProband) {
        boolean testResult = false;
        testResult = printFormsPage.verifyRelationshipToProband(realationToProband);
        Assert.assertTrue(testResult);
    }

    @And("the user verifies that the relationship to proband {string} is updated in Print forms section")
    public void verifyRelationshipInPrintFormsSection(String relationToProband) {
        boolean testResult = false;
        testResult = printFormsPage.verifyRelationshipToProband(relationToProband);
        Assert.assertTrue(testResult);
    }

}//end
