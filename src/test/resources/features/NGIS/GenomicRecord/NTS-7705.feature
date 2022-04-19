@04-GENOMIC_RECORD
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: Update gender to filter by starting with (instead of including)

  @NTS-7705
  Scenario Outline:NTS-7705: As a user I want to see the drop down suggestion for the field 'Gender' to return the value based on the starting letter that has typed
#checking gender dropdown while searching a patient details
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Then the user is navigated to a page with title Find your patient
    And the user clicks the NO button
    And the user fills in the Gender field
      |Fe|Ot|Un|Ma|
    And the user clicks the Search button
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R84 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the user navigates to the "<PatientDetails>" stage
    Then the user fills in the Gender field in the Patient details
      |Fe|Ot|Un|Ma|
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMember>" stage
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    When the user clicks the NO button in family member search page
    Then the user fills in the Gender field in the Family member search page
      |Fe|Ot|Un|Ma|

    Examples:
    |PatientDetails|FamilyMember|
    |Patient details|Family members|
