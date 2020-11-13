@E2E_TEST
Feature:Submit a RD Referral for Spine Patient and add a Family Member who is an NGIS patient and having RD referral and verify the payload.
  @NTS-5767 @Z-LOGOUT
#E2EUI-2730
  Scenario Outline:NTS-5767:Submit a RD Referral for Spine Patient and add a Family Member who is an NGIS patient and having RD referral and verify the payload.
     ##Create a RD referral for a patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=2000003753:DOB=12-09-2011|
    ##Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
    And the user clicks the Log out button
   ##Convert a NGIS patient into a Spine using the NEAT tool
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber1>" and DOB "<DOB1>" fields
    And the user clicks the Search button
    ### Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ###Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R28 | GEL_NORMAL_USER | NHSNumber=2000000584:DOB=06-06-1937:Ethnicity=A - White - British |
    ###Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails1>" stage is marked as Completed
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "South London and Maudsley NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation1>" stage is marked as Completed
    ###Test Package - duo family - No of participants 2
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage1>" stage is marked as Completed
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ###Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ###Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ###Family Members - Adding a family member - Mother
    When the user navigates to the "<FamilyMembers>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    And the user is navigated to a page with title Add missing family member details
    And the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###patient choice for the proband-YES
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    Then the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails                 | PatientChoiceCategory | RecordedBy                                                                                                           |
      | NHSNumber=2000003753:DOB=12-09-2011 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###Panels
    Then the user is navigated to a page with title Manage panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ###Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ###Print forms - No
    When the user navigates to the "Print forms" stage
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    ##NOTE: ONLY GUI PART IS DONE. CSV,DDF PART TO BE DONE IN END TO END FRAMEWORK

    Examples:
      | PatientDetails  | NhsNumber1 | DOB1       | PatientDetails1 | RequestingOrganisation1 | TestPackage1 | TwoParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                            | ClinicalQuestion   | ClinicalQuestionDetails                                                                                       | Notes | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                                                                        | PatientChoice  | Panels | Pedigree | searchPanels |
      | Patient details | 2000000584 | 06-06-1937 | Patient details | Requesting organisation | Test package | 2              | Responsible clinician | FirstName=George:LastName=Williams:Department=Cleaning | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Female:KaryotypicSex=XX | Notes | Family members | NHSNumber=2000003753:DOB=12-09-2011 | Son                   | DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Male:KaryotypicSex=XY | Patient choice | Panels | Pedigree | Amyloidosis  |
