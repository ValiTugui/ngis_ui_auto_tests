#@userJourneysRD
#@userJourneysRD_NgisProband
@SYSTEM_INTEGRATION_TEST

Feature: UserJourney_RD_NGIS_Proband_5: - UC05 - E2EUI-1296

  @NTS-4558 @Z-LOGOUT
#    @E2EUI-1296 @UseCase05
  Scenario Outline: NTS-4558: UC#05:E2EUI-1296: User Journey by creating new NGIS Referral for proband with edit data and Patient choice no
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R27 | GEL_SUPER_USER | NHSNumber=NGIS:DOB=14-05-1983:Gender=Male |
    ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms
    And the "<PatientDetails>" stage is marked as Completed
    #Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the "<PatientDetails>" stage is marked as Completed
    And the user enters the keyword "Great Western Hospitals NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only - No of participants -1
    And the user selects the number of participants as "<OneParticipant>"
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
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members -
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    ##Added below step as panels page Save and Click have some issue
    And the user clicks the Save and Continue button
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
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
      | <Pedigree>               |
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | PatientChoiceStage | RecordedBy                            | Panels | searchPanels | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Hyper        | Pedigree |