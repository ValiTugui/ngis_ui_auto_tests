#@regression
#@printForms
@TEST_ORDER
@SYSTEM_TEST
Feature: Print Forms 5 - User flows

  @NTS-4746 @LOGOUT
#    @E2EUI-1223 @E2EUI-1848 @E2EUI-1445
    ##Note: For E2EUI-1445, all the fields mentioned in the E2EUI ticket is not validated as some of them not possible to validate via automation
    ## like box, barcode, flags etc
  Scenario Outline: NTS-4746: User is completing a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R193 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-05-2000:Gender=Male |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "WITHINGTON COMMUNITY HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Trio family - No of participants 3
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Adding two members - Father and Mother
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<ThreeParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=12-03-1978:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis  |
      | NHSNumber=NA:DOB=12-02-1979:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##patient choice for the proband-YES
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ##Patient Choice - Family Details Provided below same as the Family Members
    Then the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails         | PatientChoiceCategory | RecordedBy                                                                                                           |
      | NHSNumber=NA:DOB=12-03-1978 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=12-02-1979 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Print forms - Yes
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print form for the proband
    And the user is able to download print forms for "<ThreeParticipant>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=12-03-1978 |
      | NHSNumber=NA:DOB=12-02-1979 |
      ##Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
   ### Going back to the referral - any stage
    When the user navigates to the "<Notes>" stage
    Then the user is navigated to a page with title Add notes to this referral

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ThreeParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                        | Notes | FamilyMembers  | PatientChoice  | PrintForms  | Notes |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis | Notes | Family members | Patient choice | Print forms | Notes |

  @NTS-4746 @LOGOUT
#    @E2EUI-1697 @E2EUI-1628 @E2EUI-1757
  Scenario Outline:NTS-4746: Validating submit referral and able to start a new referral after submission.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=5-10-2000:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "BOLTON ROYAL HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ###Clinical Questions
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ###Patient Choice
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
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
    And the "<PatientChoiceStage>" stage is marked as Completed
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    And the user should see the referral submit button as enabled
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the user is able to see the following guidelines below the confirmation message
      | Contact the laboratory if a submitted referral needs to be updated           |
      | Updates made online might not be seen before tests begin.                    |
      | Make sure you have:                                                          |
      | - printed your sample forms from this page                                   |
      | - sent your forms to the relevant laboratory                                 |
      | Search for a Clinical indication in the Test Directory to request more tests |
      | Start a new referral                                                         |
   ###Starting a new referral, for scenario's E2EUI-1628, E2EUI-1757
    And the user should be able to see start a new Referral button
    When the user clicks on start a new referral button
    Then the user is navigated to a page with title Search for genomic tests

    Examples:
      | OneParticipant | PatientChoiceStage | ClinicalQuestionDetails                   | ClinicianName                             | PrintForms  |
      | 1              | Patient choice     | DiseaseStatus=Unaffected:AgeOfOnset=01,02 | ClinicianName=John Doe:HospitalNumber=123 | Print forms |

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

  @NTS-4802 @LOGOUT
#    @E2EUI-1789 @E2EUI-1262 @E2EUI-826
  Scenario Outline: NTS-4802:  As a user viewing the print forms section, I should be able to see all family member identifiers so that I can correctly identify they are the correct family members in the referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R143 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=15-10-2001:Gender=Female |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "ALTRINCHAM GENERAL" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipants>"
    And the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    Then the user clicks the Save and Continue button
    ###Clinical Questions
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ###Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ###Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<ThreeParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1976:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=02,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=10-11-1977:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the user is navigated to a page with title Add a family member to this referral
    And the user reads the patient details in family member landing page
    And the user clicks the Save and Continue button
    ###Patient Choice
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
    When the user edits patient choice for "<ThreeParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=14-05-1976 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
      | NHSNumber=NA:DOB=10-11-1977 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
   ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    And the user reads the patient details in print forms page
    Then the user should see same data in family member landing page and print forms page
    And the user is able to download print form for the proband
    ###Covering E2EUI-826
    And the user should be able to click "Show address" link to verify the address of the lab in the downloaded file
    Then the user is able to download print forms for "<ThreeParticipants>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=14-05-1976 |
      | NHSNumber=NA:DOB=10-11-1977 |
    And the user should be able to click "Show address" link to verify the address of the lab in the downloaded file
    Examples:
      | ThreeParticipants | ClinicalQuestionDetails                   | ResponsibleClinicianDetails                | ClinicianName                           | PrintForms  |
      | 3                 | DiseaseStatus=Unaffected:AgeOfOnset=01,02 | LastName=Barick:Department=Victoria Street | ClinicianName=Deepak:HospitalNumber=123 | Print forms |