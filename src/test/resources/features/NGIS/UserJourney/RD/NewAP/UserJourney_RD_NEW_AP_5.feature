#@userJourneysRD
#@userJourneysRD_NewAP
@SYSTEM_INTEGRATION_TEST
Feature: UserJourney_RD_NEW_AP_5 - UC17 - E2EUI-1207
  @SYSTEM_INTEGRATION_Temp
  @NTS-4604 @Z-LOGOUT
#    @E2EUI-1207 @UseCase17
  Scenario Outline: NTS:4604: Use Case#17: Create Referral for Additional Participants (not part of Referral) + Edit Data + Patient Choice No - Search Non Spine/NGIS Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R84 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=09-06-1985:Gender=Male |
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
#    And the "<PatientDetails>" stage is marked as Completed
    And the user enters the keyword "Great Western Hospitals NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
#    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - Trio family - No of participants 1
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
#    And the "<TestPackage>" stage is marked as Completed
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
#    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
#    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
#    Then the "<Notes>" stage is marked as Completed
    ##Family Members -
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Continue with this family member
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user deselects the test
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the deselected member "<FamilyMemberDetails>" status display as "<Status>"
    And the user clicks the Save and Continue button
#    Then the "<FamilyMember>" stage is marked as Completed
    ##Patient Choice - Proband
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
#    Then the "<PatientChoiceStage>" stage is marked as Completed
     ##Panels adding 1 new panels
    Then the user is navigated to a page with title Manage panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
#    Then the "<Panels>" stage is marked as Completed
     ###Pedigree - Pedigree line to be added
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
#    Then the "<Pedigree>" stage is marked as Completed
     ##Print forms
    Then the user is navigated to a page with title Print sample forms
    And the below stages marked as completed
      | <PatientDetails>         |
      | <RequestingOrganisation> |
      | <TestPackage>            |
      | <ResponsibleClinician>   |
      | <ClinicalQuestion>       |
      | <Notes>                  |
      | <FamilyMember>           |
      | <PatientChoiceStage>     |
      | <Panels>                 |
      | <Pedigree>               |
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMember   | FamilyMemberDetails                                             | DiseaseStatusDetails     | Status           | PatientChoiceStage | RecordedBy                            | Panels | searchPanels | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Family members | NHSNumber=NA:DOB=14-04-2014:Gender=Female:Relationship=Daughter | DiseaseStatus=Unaffected | Not being tested | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Cataracts    | Pedigree |