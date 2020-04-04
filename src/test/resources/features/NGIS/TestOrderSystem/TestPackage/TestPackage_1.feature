#@regression
#@testPackageTumour
@TEST_ORDER5
@SYSTEM_TEST
Feature: Test Package 1  - Cancer

  Background:
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed

  @NTS-3073 @LOGOUT
#    @E2EUI-911
  Scenario Outline: NTS-3073 - Test package - Page Layout - Cancer
    When the user navigates to the "<stage>" stage
    Then the Test Package page header is shown as "<title>"
    And the Test Package page has two "<priority>" buttons
    And the Test Package page has "<helpText>" under the priority buttons
    And  the Test package page has "<section>" header
    And the Test package page has Targeted genes section with the "<text>"
    And the Test package page has test selected for the proband with "<testInfo>"
    Examples:
      | stage        | title                    | priority        | helpText                                                                          | section        | text                                                                | testInfo           |
      | Test package | Confirm the test package | Urgent, Routine | Choose Urgent if you want the laboratory to prioritise some or all of your tests. | Selected tests | All including burden / signature, This test is for one person only. | Routine, Singleton |

  @NTS-3109 @LOGOUT
#    @E2EUI-2139
  Scenario Outline: NTS-3109 - Test package - verify Warning Messages of unsaved changes - Moving to another To do list - Cancer
    And the user navigates to the "<stage>" stage
    And the user clicks a test to de-select it
    When the user navigates to the "<new_stage>" stage
    Then the user sees a warning message on the page

    Examples:
      | stage        | new_stage             |
      | Test package | Responsible clinician |


  @NTS-3109 @LOGOUT
#    @E2EUI-2139
  Scenario Outline: NTS-3109 - Test package - verify Warning Messages of unsaved changes - Browser "<browser_exit_option>" - Cancer
    And the user navigates to the "<stage>" stage
    And the user clicks a test to de-select it
    When the user attempts to navigate away by clicking "<browser_exit_option>"
    Then the user sees a warning message on the page

    Examples:
      | stage        | browser_exit_option |
      | Test package | refresh             |


  @NTS-3156 @LOGOUT
#    @E2EUI-828
  Scenario Outline: NTS-3156 - Test package - selecting Routine for the question priority of the test - Cancer
    And the user navigates to the "<stage>" stage
    When the user selects the "<priority>"
    Then the user clicks the Save and Continue button
    And the "<stage>" stage is marked as Completed
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    Examples:
      | stage        | priority | new_stage             | number_of |
      | Test package | Routine  | Responsible clinician | 1         |

  @NTS-3070 @NTS-3823 @LOGOUT
#    @E2EUI-1316 @E2EUI-1123
  Scenario Outline: NTS-3070 - Test package - Urgency selection
    And the user navigates to the "<stage>" stage
    And the Test Package page priority header has "<priority_label>"
    When the user selects the "<priority>"
    And the Test Package page "<previous_priority>" is de-selected
    And the Test Package page has the help text as "<help_text>" on the page
    Then the user clicks the Save and Continue button
    And the "<new_stage>" stage is selected
    And the "<stage>" stage is marked as Completed
    And the correct "<number_of>" tests are saved to the referral in  "<stage>" with the chosen "<priority>"
    And the user see a tick mark next to the chosen "<priority>"
    Examples:
      | stage        | priority | previous_priority | new_stage             | priority_label                         | help_text                                                                         | number_of |
      | Test package | Urgent   | Routine           | Responsible clinician | What is the priority of your referral? | Choose Urgent if you want the laboratory to prioritise some or all of your tests. | 1         |
