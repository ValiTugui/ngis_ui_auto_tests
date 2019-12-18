package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
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
            if(memberDetails.size() < noOfParticipants){
                Debugger.println("No of Participants mentioned and details provided are not matching.");
                return;
            }

            for (int i = 1; i < memberDetails.size(); i++) {
                Debugger.println("Downloading and Verifying content for :"+memberDetails.get(i).get(0));
                if(!printFormsPage.downloadSpecificPrintForm(memberDetails.get(i).get(0))){
                    Debugger.println("Could not download form for "+memberDetails.get(i).get(0));
                    testResult = false;
                    continue;
                }
                Debugger.println("Downloaded...Verifying content....");
                HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(memberDetails.get(i).get(0));
                Set<String> paramsKey = paramNameValue.keySet();
                String nhsNumber = "";
                for (String key : paramsKey) {
                    if(key.equalsIgnoreCase("NHSNumber")){
                        nhsNumber = paramNameValue.get(key);
                        break;
                    }
                }
                NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(nhsNumber);
                if(familyMember == null){
                    continue;//For Proband
                }
                String referralID = referralPage.getPatientReferralId();
                Debugger.println("ReferralID: "+referralID);
                if(referralID != null) {
                    familyMember.setREFERAL_ID(referralID);
                 }
                if(!printFormsPage.openAndVerifyPDFContent(familyMember)){
                    Debugger.println("Could not verify PDF content for "+memberDetails.get(i).get(0));
                    testResult = false;
                    continue;
                }
                testResult = true;
            }//for
            Assert.assertTrue(testResult);
        }catch(Exception exp){
            Debugger.println("PrintFormSteps: Exception in downloading PrintForms: "+exp);
            Assert.assertFalse("PrintFormSteps: Exception in downloading PrintForms: "+exp,true);
        }
    }

    @And("the user is able to download Sample form which has the correct user name, DOB , patient Id, ReferralId, Laboratory address, clinician info, Tumour info details")
    public void theUserIsAbleToDownloadSampleFormWhichHasTheCorrectUserNameDOBPatientIdReferralIdLaboratoryAddressClinicianInfoTumourInfoDetails() {
        boolean testResult = false;
        PatientDetailsPage.newPatient.setOrderingEntity(printFormsPage.getLaboratoryAddress());
        PatientDetailsPage.newPatient.setSampleType(printFormsPage.getSampleInfo());
        PatientDetailsPage.newPatient.setTumourType(printFormsPage.getTumourInfo());

        Debugger.println("Downloading and Verifying content the proband ");
        if(!printFormsPage.downloadProbandPrintForm()){
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

        String tumourInfo= newlyCreatedPatientRecord.getTumourType();
        String sampleInfo = newlyCreatedPatientRecord.getSampleType();




        Debugger.println("Patient Name to verify in the downloaded PDF file                 : " + patientName);
        Debugger.println("Patient DOB to verify in the downloaded PDF file                  : "  + dateOfBirth);
        Debugger.println("Patient Gender to verify in the downloaded PDF file               : "  + gender);
        Debugger.println("Patient NGIS Is to verify in the downloaded PDF file               : "  + patientNGISId);
        Debugger.println("Patient Referral Id to verify in the downloaded PDF file               : "  + patientReferralId);

        Debugger.println("Patient CI     to verify in the downloaded PDF file               : "  + clinicalIndication);
        Debugger.println("Ordering entity to verify in the downloaded PDF file              : "  + requestingOrg);
        Debugger.println("Responsible Clinician Name to verify in the downloaded PDF file            : "  + responsibleClinicianName);
        Debugger.println("Responsible Clinician Email to verify in the downloaded PDF file           : "  + responsibleClinicianEmail);
        Debugger.println("Responsible Clinician contact number to verify in the downloaded PDF file  : "  + responsibleClinicianContact);
        String[] tumours = tumourInfo.split(" •");
        Debugger.println("Tumour Type to verify in the downloaded PDF file  : "  + tumours[0]);
        String[] samples = sampleInfo.split("sample required");
        Debugger.println("Sample info to verify in the downloaded PDF file  : "  + samples[0]);

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
}//end
