@E2E_TEST
Feature:Proband -spine with a family member (NGIS), the FM is a proband in another referral whose having a family member who was a part of cancer referral@NTS-5767 @Z-LOGOUT
  @NTS-5724 @Z-LOGOUT
#E2EUI-2751
  Scenario Outline:NTS-5724:Proband -spine with a family member (NGIS), the FM is a proband in another referral whose having a family member who was a part of cancer referral   ##Create a RD referral for a patient
 #Create a cancer referral for a patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M143 | GEL_NORMAL_USER | NHSNumber=4553534533:DOB=01-02-1990:Ethnicity=A - White - British |
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
    ##Create a RD referral for 2 members in which the additional family member should be the Proband of the above cancer referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R28 | GEL_NORMAL_USER | NHSNumber=5647546374:DOB=04-03-2014:Ethnicity=A - White - British |
    ###Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails1>" stage is marked as Completed
    ###Requesting Organisation
    And the user enters the keyword "South London and Maudsley NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the "<RequestingOrganisation1>" stage is marked as Completed
    ###Test Package - duo family - No of participants 2
    When the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant1>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage1>" stage is marked as Completed
    ###Family Members - Adding a family member
    When the user navigates to the "<FamilyMembers1>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails1>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    And the user is navigated to a page with title Add missing family member details
    And the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband1>" for family member "<FamilyMemberDetails1>"
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails1>" is selected by default
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails1>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Then the "<FamilyMembers1>" stage is marked as Completed
    And the user clicks the Save and Continue button
    Then the user clicks the Log out button
     ###Check if NGIS and convert to SPINE from NEAT
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    And the user types in different valid details in the NHS number "9449305978" and DOB "09-04-2010" fields
    And the user clicks the Search button
    ###Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
     ##Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R28 | GEL_NORMAL_USER | NHSNumber=9449305978:DOB=09-04-2010:Ethnicity=A - White - British |
    ###Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails2>" stage is marked as Completed
    ###Requesting Organisation
    And the user enters the keyword "South London and Maudsley NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the "<RequestingOrganisation2>" stage is marked as Completed
    ##Test Package - duo family - No of participants 2
    When the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant2>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage2>" stage is marked as Completed
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
    When the user navigates to the "<FamilyMembers2>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails2>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    And the user is navigated to a page with title Add missing family member details
    And the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband2>" for family member "<FamilyMemberDetails2>"
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails2>" is selected by default
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails2>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    Then the "<FamilyMembers2>" stage is marked as Completed
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
      | NHSNumber=5647546374:DOB=04-03-2014 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
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
      | PatientDetails | TestPackage | PatientDetails1 | RequestingOrganisation1 | TwoParticipant1 | TestPackage1 | FamilyMembers1 | FamilyMemberDetails1              | RelationshipToProband1 | DiseaseStatusDetails1                                                                                     | PatientDetails2 | RequestingOrganisation2 | TwoParticipant2 | TestPackage2 | ResponsibleClinicianDetails                          | ResponsibleClinician | ClinicalQuestionDetails                                                                                   | ClinicalQuestion | Notes | FamilyMembers2 | FamilyMemberDetails2              | RelationshipToProband2 | DiseaseStatusDetails2                                                                                     | PatientChoice | searchPanels | Panels | Pedigree | PrintForms |
      | Patient details| Test package|Patient details  |Requesting organisation  |2                |Test package  |Family members  |NHSNumber=4553534533:DOB=01-02-1990|Father                  |DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Male:KaryotypicSex=XY|Patient details  |Requesting organisation  |2                |Test package  |FirstName=George:LastName=Williams:Department=Cleaning|Responsible clinician |DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Male:KaryotypicSex=XX|Clinical questions|Notes  |Family members  |NHSNumber=5647546374:DOB=04-03-2014|Full Sibling            |DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Male:KaryotypicSex=XY|Patient choice |Amyloidosis   |Panels  |Pedigree  |Print forms |