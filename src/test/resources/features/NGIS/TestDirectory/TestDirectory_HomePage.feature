#@homePage
@01-TEST_DIRECTORY
@SYSTEM_TEST
Feature: TestDirectory : Home Page

  @NTS-3160
#  @E2EUI-1366
  Scenario: NTS-3160:E2EUI-1366: Home Page - Show number of results in a tab
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field
      | 270 |
    Then the search results have been displayed
    And the number of results shown in each filters & total results should match

  @NTS-3168
#  @E2EUI-1793
  Scenario: NTS-3168:E2EUI-1793 Home Page - The tests from Tests tab are loaded properly
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user selects the Tests tab
    Then various test details are displayed

  @NTS-4726 @Z-LOGOUT
#    @E2EUI-1155
  Scenario Outline: NTS-4726:E2EUI-1155: Displaying the current state for each stage
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449309221:DOB=26-02-2004 |
      ##Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user should be able to see the active stage "<PatientDetails>" in to-do list
    And the user clicks the Save and Continue button
    Then the "<PatientDetails>" stage is marked as Completed
    And the below stages marked as incompleted
      | Requesting organisation |
      | Test package            |
      | Responsible clinician   |
      | Clinical questions      |
      | Patient choice          |
    And the print forms stage is locked
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Add notes to this referral
    And the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    And the "<Notes>" stage is marked as Completed
    Then the below stages marked as completed
      | Patient details         |
      | Requesting organisation |
      | Test package            |
      | Clinical questions      |
      | Notes                   |
    And the user should be able to see the active stage "<FamilyMembers>" in to-do list
    And the below stages marked as incompleted
      | Family members |
      | Patient choice |
      | Panels         |
      | Print forms    |
    Examples:
      | PatientDetails  | RequestingOrganisation  | NoOfParticipants | ResponsibleClinicianDetails                               | ResponsibleClinician  | ClinicalQuestionDetails                                         | Notes | FamilyMembers  |
      | Patient details | Requesting organisation | 2                | FirstName=Karen:LastName=Smith:Department=Victoria Street | Responsible clinician | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema | Notes | Family members |
