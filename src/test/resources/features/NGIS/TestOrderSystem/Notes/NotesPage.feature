#@regression
#@notes
@TEST_ORDER
@SYSTEM_TEST
Feature: Notes page

 @NTS-4503 @LOGOUT
#   @E2EUI-1130 @E2EUI-1172
  Scenario Outline: Responsible Clinician Page - Phone number field - maximum length validation
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    Then the "<stage>" stage is selected
    When the user attempts to fill in Referral Notes field with data that exceed the maximum data allowed 3000
    Then the user is prevented from entering data that exceed that allowable maximum data 3000 in the "referralNotes" field

    Examples:
      | stage |
      | Notes |

  @NTS-4375 @LOGOUT
#    @E2EUI-1213 @E2EUI-834 @E2EUI-1488
  Scenario Outline: NTS-4375 : Validation of the character count in Add notes text box in Notes section
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | Rare Diseases | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER | child |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient
    And the user navigates to the "<Notes>" stage
    ##Notes Page
    And the user is navigated to a page with title Add notes to this referral
    Then the user should be able to see a "<infoMessage>" on the Notes page
    When the user attempts to fill in Referral Notes field with data that exceed the maximum data allowed 3000
    Then the user should see an error message "<infoMessage>" in "<MessageColor>" for maximum chracters

    Examples:
      | Notes | infoMessage                | MessageColor |
      | Notes | Max. character count: 3000 | #dd2509      |