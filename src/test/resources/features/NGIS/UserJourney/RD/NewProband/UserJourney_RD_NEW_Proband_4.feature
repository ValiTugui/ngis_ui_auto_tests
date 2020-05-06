#@userJourneys
#@userJourneysRD
#@userJourneysRD_NEW_ProbandOnly
@SYSTEM_INTEGRATION_TEST
Feature: UserJourney_RD_NEW_Proband_4 - UC26 - E2EUI-1801

  @NTS-5185 @Z-LOGOUT
#    @E2EUI-1801
  Scenario Outline: NTS-5185: Use Case #26: Create Referral for Proband Only + Edit Data + Patient Choice Not Given - Create new Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R143 | GEL_SUPER_USER | NHSNumber=NA-Patient is a foreign national:DOB=19-06-2011:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - Proband only - No of participants 1
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ###Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    And the "<ClinicalQuestion>" stage is marked as Completed
    ###Notes
    And the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ###Family Members -No members required and this stage will not be marked as completed
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ###Patient choice for the proband Not to be given for this scenario so this stage will be marked with asterisk
    Then the user is navigated to a page with title Patient choice
    And the user clicks on Continue Button
    Then the "<PatientChoice>" stage is marked as Mandatory To Do
    ###Panels - no need to check for its completion
    Then the user is navigated to a page with title Panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ###Pedigree - Pedigree by default marked as completed, no need to click on save and continue
    And the user is navigated to a page with title Build a pedigree
    Then the "<Pedigree>" stage is marked as Completed
    ###Print forms - only Proband
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    ###Cancel Referral (without submitting)
    And the user clicks the Cancel referral link
    And the user selects the cancellation reason "The referral has been paused or stopped (“Revoke”)" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"

    Examples:
      | PatientDetails  | RequestingOrganisation  | ordering_entity_name | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  | RevokeMessage                                                             |
      | Patient details | Requesting organisation | Manchester           | Test package | 1                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Family members | Patient choice | Panels | Pedigree | Print forms | This referral has been cancelled so further changes might not take effect |