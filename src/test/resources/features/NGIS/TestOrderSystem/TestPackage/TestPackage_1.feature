@03-TEST_ORDER
@SYSTEM_TEST
@TestPackage

Feature: Test Package 1  - Cancer

  @NTS-3080 @NTS-3156 @NTS-325 @NTS-4700 @Z-LOGOUT
#    @E2EUI-911 @E2EUI-828 @E2EUI-1585 @E2EUI-886
  Scenario Outline: NTS-3080:E2EUI-911: Page Layout - Rare Disease
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    Then the Test Package page header is shown as "<title>"
    And the Test Package page has two "<priorityButtons>" buttons
    And the Test Package page has "<helpText>" under the priority buttons
    And  the Test package page has "<section>" header
    And the Test package page has Targeted genes section with the "<TargetedGenes>"
    And the Test package page has test selected for the proband with "<testInfo>"
    And the test package page has Total number of participants drop down box
    Then the test package page has Selected family members with the "<membersInfo>"
    ##Below Steps for NTS-3156
    When the user selects the "<priority>"
    And the user selects the number of participants: "<count>"
    Then the user clicks the Save and Continue button
    And the "<stage>" stage is marked as Completed
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    Then the user see a tick mark next to the chosen "<priority>"
    ##Below 2 steps for NTS-3253
    When the user clicks a test to de-select it
    And the user sees the test has become deselected
    Then the Total number of participants field is disappeared for the deselected test

    Examples:
      | stage        | title                    | priorityButtons | helpText                                                                          | section        | TargetedGenes                | testInfo                  | membersInfo | priority | count | new_stage             | number_of |
      | Test package | Confirm the test package | Urgent, Routine | Choose Urgent if you want the laboratory to prioritise some or all of your tests. | Selected tests | Cerebral malformations (491) | Routine,Trio or singleton | Proband     | Urgent   | 2     | Responsible clinician | 1         |


  @NTS-3073 @NTS-3156 @Z-LOGOUT
# @E2EUI-911 @E2EUI-828
  Scenario Outline: NTS-3073 - Test package - Page Layout - Cancer
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    Then the Test Package page header is shown as "<title>"
    And the Test Package page has two "<priorityButtons>" buttons
    And the Test Package page has "<helpText>" under the priority buttons
    And  the Test package page has "<section>" header
    And the Test package page has Targeted genes section with the "<text>"
    Then the Test package page has test selected for the proband with "<testInfo>"
    ##Below Steps for NTS-3156
    When the user selects the "<priority>"
    Then the user clicks the Save and Continue button
    And the "<stage>" stage is marked as Completed
    And the "<new_stage>" stage is selected
    Then the correct "<number_of>" tests are saved to the referral in  "<stage>"
    Then the user see a tick mark next to the chosen "<priority>"

    Examples:
      | stage        | title                    | priorityButtons | helpText                                                                          | section        | text                                                                | testInfo           | new_stage             | number_of | priority |
      | Test package | Confirm the test package | Urgent, Routine | Choose Urgent if you want the laboratory to prioritise some or all of your tests. | Selected tests | All including burden / signature, This test is for one person only. | Routine, Singleton | Responsible clinician | 1         | Routine  |


  @NTS-3109 @NTS-3258 @Z-LOGOUT
#    @E2EUI-2139 @E2EUI-1900
  Scenario Outline: NTS-3457: Warn a user that they will lose their changes when navigating away from patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2014:Gender=Male |
    And the "Patient details" stage is marked as Completed
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user sees the test has been selected by default
    And the user clicks a test to de-select it
    Then the user sees the test has become deselected
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<DiscardMessage>" after clicking "<new_stage>" button and "<Dismiss>" it
    Then the user is navigated to a page with title Confirm the test package
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<UnsavedMessage>" after clicking "refresh" button and "<Dismiss>" it
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Log out button
    And the user sees a prompt alert "<UnsavedMessage>" after clicking "logout" button and "<Dismiss>" it
    Then the user is navigated to a page with title Confirm the test package
    And the user is able to clicks on deselected test
    And the user selects the number of participants as "3"
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<DiscardMessage>" after clicking "<new_stage>" button and "<Dismiss>" it

    Examples:
      | stage        | new_stage             | DiscardMessage                                              | Dismiss | UnsavedMessage                    |
      | Test package | Responsible clinician | This section contains unsaved information. Discard changes? | Dismiss | Changes you made may not be saved |
