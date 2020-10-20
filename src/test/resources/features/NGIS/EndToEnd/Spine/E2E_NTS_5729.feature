@E2E_TEST
Feature:Submit a RD Referral for Spine Patient and add a NGIS patient as a Family Member which has Previously undergone cancer referral and verify the payload.
  @NTS-5729 @Z-LOGOUT
#E2EUI-2750
  Scenario Outline:NTS-5729:Submit a RD Referral for Spine Patient and add a NGIS patient as a Family Member which has Previously undergone cancer referral and verify the payload.
    ###Create a cancer referral for a patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M143 | GEL_NORMAL_USER | NHSNumber=9449303665:DOB=15-09-2000:Ethnicity=A - White - British |
    ##Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    And the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    When the user is navigated to a page with title Confirm the test package
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    Then the user clicks the Log out button
    ###Check if NGIS and convert to SPINE from NEAT
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    And the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ###Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ##Submit a RD referral for a spine patient and add a NGIS patient as a Family Member which has Previously undergone cancer referral
    ##Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R28 | GEL_NORMAL_USER | NHSNumber=9449308853:DOB=14-06-2011:Ethnicity=A - White - British |
    ###Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails>" stage is marked as Completed
    ###Requesting Organisation
    And the user enters the keyword "South London and Maudsley NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - duo family - No of participants 2
    When the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    ###Responsible Clinician
    When the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    Then the "<ResponsibleClinician>" stage is marked as Completed
    ###Clinical Question
    When the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ###Notes
    When the user is navigated to a page with title Add clinical notes
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
    And the "<PatientChoice>" page is displayed
    And the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    And the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails                 | PatientChoiceCategory | RecordedBy                                                                                                           |
      | NHSNumber=9449303665:DOB=15-09-2000 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###Panels
    When the user is navigated to a page with title Manage panels
    And the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ###Pedigree - Pedigree by default marked as completed
    When the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    ##NOTE: ONLY GUI PART IS DONE. CSV,DDF PART TO BE DONE IN END TO END FRAMEWORK

    Examples:
      | NhsNumber  | DOB        | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                            | ClinicalQuestion   | ClinicalQuestionDetails                                                                                     | Notes | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails                                                                                        | PatientChoice  | Panels | Pedigree | searchPanels | PrintForms  |
      | 9449308853 | 14-06-2011 | Patient details | Requesting organisation | Test package | 2              | Responsible clinician | FirstName=George:LastName=Williams:Department=Cleaning | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Male:KaryotypicSex=XY | Notes | Family members | NHSNumber=9449303665:DOB=15-09-2000 | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Male:KaryotypicSex=XY | Patient choice | Panels | Pedigree | Amyloidosis  | Print forms |



