package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Click;
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

import java.util.*;

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
                return;
            }

            for (int i = 1; i < memberDetails.size(); i++) {
                Debugger.println("Downloading and Verifying content for :" + memberDetails.get(i).get(0));
                if (!printFormsPage.downloadSpecificPrintForm(i, "RD")) {
                    Debugger.println("Could not download form for " + memberDetails.get(i).get(0));
                    testResult = false;
                    continue;
                }
                Debugger.println("Downloaded...Verifying content....");
                NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
                if (familyMember == null) {
                    continue;//For Proband
                }
                String referralID = referralPage.getPatientReferralId();
                Debugger.println("ReferralID: " + referralID);
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

    @When("the user clicks on the confirm cancellation button")
    public void theUserClicksOnTheConfirmCancellationButton() {
        referralPage.submitCancellation();
    }

    //1794
    @Then("the cancel referral dialog box is displayed with the following fields")
    public void theCancelReferralDialogBoxIsDisplayedWithTheFollowingFields(DataTable fieldDetails) {
        boolean testResult = false;
        testResult = referralPage.validateCancelReferralDialog(fieldDetails);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able see referral status as cancelled with selected {string} reason")
    public void theUserShouldBeAbleSeeReferralStatusAsCancelledWithSelectedReason(String reason) {
        boolean testResult = false;
        testResult = referralPage.referralCancelledStatusWithReason(reason);
        Assert.assertTrue(testResult);
    }

    //1729
    @Then("the user should be able to see a {string} on the print forms page")
    public void theUserShouldBeAbleToSeeAOnThePrintFormsPage(String warningMessage) {
        boolean testResult = false;
        testResult = printFormsPage.verifyWarningMessageOnPrintFormsPage(warningMessage);
        Assert.assertTrue(testResult);
    }

    @And("the user is able to download Sample form to check the text {string}")
    public void theUserIsAbleToDownloadSampleFormToCheckTheText(String expText) {
        boolean testResult1 = false, testResult2 = false;
        testResult1 = printFormsPage.downloadProbandPrintForm();
        testResult2 = printFormsPage.findPDFContent(expText, "SampleForm.pdf");
        if (testResult1 && testResult2) {
            Assert.assertTrue(testResult1);
        }
        Assert.fail();
    }

    @Then("the user is able to download form of the {string} section and validate the text {string} in the file {string}")
    public void theUserIsAbleToDownloadFormOfTheSectionAndValidateTheTextInTheFile(String expectedSection, String expectedText, String fileName) {
        boolean testResult = false;
        if (expectedSection.equalsIgnoreCase("Referral")) {
            printFormsPage.downloadForm(fileName, expectedSection);
            testResult = printFormsPage.findPDFContent(expectedText, fileName);
        }
        if (expectedSection.equalsIgnoreCase("Additional family members")) {
            printFormsPage.downloadForm(fileName, expectedSection);
            testResult = printFormsPage.findPDFContent(expectedText, fileName);
        }
        if (expectedSection.equalsIgnoreCase("Patient choice")) {
            testResult = printFormsPage.downloadForm(fileName, expectedSection);
            if (!expectedText.isEmpty()) {
                String unZippedFolderName = printFormsPage.extractZipFile(fileName);
                printFormsPage.findPDFContent(expectedText, unZippedFolderName);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the Print forms stage is (.*)")
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

    @And("the user should be able to see the {string} participant details taking part in the referral with the below details")
    public void theUserShouldBeAbleToSeeTheParticipantDetailsTakingPartInTheReferralWithTheBelowDetails(String numOfMembers, DataTable inputDetails) {
        try {
            boolean testResult = false;
            int noOfParticipants = Integer.parseInt(numOfMembers);
            List<String> memberDetails = inputDetails.asList();
            if (memberDetails.size() < noOfParticipants) {
                Debugger.println("Number of participants mentioned and details provided are not matching.");
                return;
            }
            for (int i = 1; i <= memberDetails.size(); i++) {
                Debugger.println("Checking participant details for " + memberDetails.get(i));

                testResult = familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage(memberDetails.get(i));
            }
            for (int i = 1; i < memberDetails.size(); i++) {
                printFormsPage.findPDFContent(memberDetails.get(i), "SampleForm.pdf");
            }
            Assert.assertTrue(testResult);
        } catch (Exception exp) {
            Assert.assertFalse("PrintFormSteps: Exception in verifying participant details presence: " + exp, true);
        }
    }

    @And("the user clicks {string} link to view the address of the lab where the test are supposed to be sent")
    public void theUserClicksLinkToViewTheAddressOfTheLabWhereTheTestAreSupposedToBeSent(String showAddress) {
        boolean testResult = false;
        testResult = printFormsPage.userSeesTheDetailsOfLabAddress(showAddress);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the  banner containing details of participant taking part in the referral creation")
    public void theUserSeesTheBannerContaningDeatailsOfParticipantTakingPartInTheReferral() {
        boolean testResult = false;
        testResult = printFormsPage.userVerifiesTheParticipantDetailsOnPrintFormsPage();
        boolean toDoListDisplayed = referralPage.checkThatToDoListSuccessfullyLoaded();
        if (testResult && toDoListDisplayed) {
            Assert.assertTrue(testResult);
        }
    }

    @Then("the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral")
    public void theUserSeesADialogBoxWithFollowingMandatoryStagesToBeCompletedForSuccessfulSubmissionOfAReferral(DataTable fieldDetails) {
        boolean testResult = false;
        testResult = referralPage.validateMandatoryStages(fieldDetails);
        Assert.assertTrue(testResult);
    }

    @Then("the user after submitting once the referral is unable to submit it again")
    public void theUserAfterSubmittingOnceTheReferralIsUnableToSubmitItAgain() {
        boolean testResult = false;
        testResult = referralPage.submiButtonStatusAfterSubmission();
        Assert.assertTrue(testResult);
    }

    @And("the user verifies that the the relationship to proband {string} status has been updated in Print forms section")
    public void theUserVerifiesThatTheTheRelationshipToProbandStatusHasBeenUpdatedInPrintFormsSection(String realation2) {
        boolean testResult = false;
        testResult = referralPage.verifyRelationshipToProbandStatus(realation2);
        Assert.assertTrue(testResult);
    }

    @And("the user is able to download print form for the proband")
    public void theUserIsAbleToDownloadPrintFormForTheProband() {
        boolean testResult = false;
        testResult = printFormsPage.downloadProbandPrintForm();
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on address link on print forms page")
    public void theUserClicksOnAddressLinkOnPrintFormsPage() {
        printFormsPage.clickPrintFormsAddressLink();
    }

    @And("the user should be able to see a routing address information for online form")
    public void theUserShouldBeAbleToSeeARoutingAddressInformationForOnlineForm() {
        printFormsPage.verifyLaboratoryAddressIsDisplayed();
    }

    @And("the user is able to verify the {string} in the downloaded form {string}")
    public void theUserIsAbleToVerifyTheInTheDownloadedForm(String fieldType, String fileName) {
        boolean testResult = false;
        String testType = printFormsPage.readSelectedTestType(fieldType);
        testResult = printFormsPage.findPDFContent(testType, fileName);
        Assert.assertTrue(testResult);
    }

    @Then("the user should not be able to see back button on print forms page")
    public void theUserShouldNotBeAbleToSeeBackButtonOnPrintFormsPage() {
        boolean testResullt = false;
        testResullt = printFormsPage.backButtonNotPresentOnPrintFormsPage();
        Assert.assertTrue(testResullt);
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
        testResult = printFormsPage.clicksOnStartANewReferralButton();
        Assert.assertTrue(testResult);
    }

}//end
