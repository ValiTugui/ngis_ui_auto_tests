#@userJourneysRD
#@userJourneysRD_NgisProband
@SYSTEM_INTEGRATION_TEST
Feature: UserJourney_RD_NGIS_Proband_6 - UC07 - E2EUI-1298

  @NTS-4555 @Z-LOGOUT
#    @E2EUI-1298 @UseCase07
  Scenario Outline: NTS-4555: Use Case#07: Create Referral for Proband Only + Edit Data + Patient Choice Not Given - Search NGIS Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NGIS:DOB=14-05-1985:Gender=Male |
   ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms
    And the "<PatientDetails>" stage is marked as Completed
    #Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the "<PatientDetails>" stage is marked as Completed
    And the user enters the keyword "Greater Manchester Mental Health" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    When the user clicks the Save and Continue button
#    Then the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Proband only
    When the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    When the user clicks the Save and Continue button
#    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    When the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    When the user clicks the Save and Continue button
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
    ##Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Patient conversation happened; form to follow for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
#    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user should see the default status of penetrance button as Incomplete
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
#    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
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
      | <PatientChoiceStage>     |
      | <Panels>                 |
      | <Pedigree>               |
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | PatientChoiceStage | RecordedBy                            | Panels | searchPanels | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Karan:LastName=Singh:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema | Notes | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Cataracts    | Pedigree |