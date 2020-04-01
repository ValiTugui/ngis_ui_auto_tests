#@regression
#@printForms
@TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Print Forms 4 - Field name consistency in Print Forms

  @NTS-4746 @LOGOUT
#    @E2EUI-889 @LOGOUT
  Scenario Outline: NTS-4746: Submit a referral and resubmit again
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R59 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the referral status is set to "Created"
    When the user submits the referral
    Then the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | MandatoryStagesToComplete |
      | Requesting organisation   |
      | Test package              |
      | Responsible clinician     |
      | Clinical questions        |
      | Patient choice            |
    And the user should be able to close the pop up dialog box
    ###Requesting Organisation
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "WYTHENSHAWE HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ###Responsible Clinician
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ###Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ###Patient Choice
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice
   ###Submit referral-twice
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    And the user is able to see the following guidelines below the confirmation message
      | Contact the laboratory if a submitted referral needs to be updated           |
      | Updates made online might not be seen before tests begin.                    |
      | Make sure you have:                                                          |
      | - printed your sample forms from this page                                   |
      | - sent your forms to the relevant laboratory                                 |
      | Search for a Clinical indication in the Test Directory to request more tests |
      | Start a new referral                                                         |
    When the user submits the referral
    Then the user should see the referral submit button as "disabled"
    Examples:
      | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                                                    | PatientChoice  | ClinicianName                                |
      | Requesting organisation | Test package | 1                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Functional abnormality of the bladder | Patient choice | ClinicianName=Billy:HospitalNumber=178827893 |

  @NTS-3414
#    @E2EUI-2780
  Scenario Outline: NTS-3414: User visits offline order page for form download for Rare Disease
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    And the user enters the keyword "Surrey and Sussex Healthcare NHS Trust" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    When the user clicks the Continue button
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<TextToValidate>" in the file "ReferralForm.pdf"
    And the user is able to download form of the "Additional family members" section and validate the text "<TextToValidate>" in the file "AdditionalParticipantForm.pdf"

    Examples:
      | TextToValidate                                                                                                                                                                                          |
      | RARE AND INHERITED,v1.17,Samples (being sent to GLH DNA extraction lab),Blood (EDTA),Amniotic fluid, Fetal blood (EDTA), Chorionic Villus, Fresh Tissue (not tumour),Stored DNA,Sample ID, Age of onset |

  @NTS-3414
#    @E2EUI-2780
  Scenario Outline: NTS-3414: scenario-2:User visits offline order page for form download for Cancer
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | M89 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    And the user enters the keyword "Surrey and Sussex Healthcare NHS Trust" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    When the user clicks the Continue button
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<TextToValidate>" in the file "ReferralForm.pdf"

    Examples:
      | TextToValidate                                                                                                                                                                                                                                                                                                                           |
      | CANCER,v1.17,Complete for tumour samples(being sent to GLH DNA extraction lab),Complete for germline samples (being sent to GLH DNA extraction lab),Sample ID,Primary,Metastatic,Unknown,Lymphoma,Haemato-oncology liquid tumour,AML,ALL,Other (please specify),Date of this diagnosis,Blood (EDTA),Saliva,Fibroblasts,Skin biopsy,Other |
