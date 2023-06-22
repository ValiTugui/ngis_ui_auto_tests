#@userJourneysRD
#@userJourneysRD_NewAP
@SYSTEM_INTEGRATION_TEST
Feature: UserJourney_RD_NEW_AP_2 - UC16 - E2EUI-1428

  @NTS-4587 @Z-LOGOUT
#    @E2EUI-1428 @UseCase16
  Scenario Outline: NTS-4587: Use Case#16: Create Referral for Additional Participants (not part of Referral) + Default Data + Patient Choice No - Search Non Spine/NGIS Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R15 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=27-07-1987:Gender=Male |
   ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms
#    When the user clicks on Continue Button
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the "<PatientDetails>" stage is marked as Completed
    And the user enters the keyword "Great Western Hospitals NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
#    And the "<RequestingOrganisation>" stage is marked as Completed
     ##Test Package -
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
#    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
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
   ##Family Members - for Additional Participants, need to deselect the test
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
    ##Patient Choice
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
    #Panels
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
#    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    ##pedigree diagram has to updated
    And the user clicks on Save and Continue on Pedigree Page
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
      | <PatientChoiceStage>     |
      | <Panels>                 |
      | <Pedigree>               |
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMember   | FamilyMemberDetails                                             | DiseaseStatusDetails     | Status           | PatientChoiceStage | RecordedBy                            | Panels | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Family members | NHSNumber=NA:DOB=14-04-2014:Gender=Female:Relationship=Daughter | DiseaseStatus=Unaffected | Not being tested | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Pedigree |