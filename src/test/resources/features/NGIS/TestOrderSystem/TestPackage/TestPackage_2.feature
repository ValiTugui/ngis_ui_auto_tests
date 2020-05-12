#@regression
#@testPackageRD
@03-TEST_ORDER
@SYSTEM_TEST

Feature: TestOrder - Test Package 2 - RD

  @NTS-3080 @Z-LOGOUT
#    @E2EUI-911
  Scenario Outline: NTS-3080:E2EUI-911: Page Layout - Rare Disease
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    Then the Test Package page header is shown as "<title>"
    And the Test Package page has two "<priority>" buttons
    And the Test Package page has "<helpText>" under the priority buttons
    And  the Test package page has "<section>" header
    And the Test package page has Targeted genes section with the "<text>"
    And the Test package page has test selected for the proband with "<testInfo>"
    And the test package page has Total number of participants drop down box
    And the test package page has Selected family members with the "<membersInfo>"
    Examples:
      | stage        | title                    | priority        | helpText                                                                          | section        | text                         | testInfo           | membersInfo |
      | Test package | Confirm the test package | Urgent, Routine | Choose Urgent if you want the laboratory to prioritise some or all of your tests. | Selected tests | Cerebral malformations (491) | Routine, Singleton | Proband     |

  @NTS-3156 @Z-LOGOUT
#    @E2EUI-828 @E2EUI-1585
  Scenario Outline: NTS-3156:E2EUI-828,1585: Selecting Urgent for the question priority of the test - Rare-Disease
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    When the user selects the "<priority>"
    And the user selects the number of participants: "<count>"
    Then the user clicks the Save and Continue button
    And the "<stage>" stage is marked as Completed
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    Examples:
      | stage        | priority | count | new_stage             | number_of |
      | Test package | Urgent   | 2     | Responsible clinician | 1         |

  @NTS-3177 @Z-LOGOUT
#    @E2EUI-1547 @E2EUI-1585
  Scenario Outline: NTS-3177:E2EUI-1547,1585: To Do list should be Mandatory To Do - verify permissible Number of Participants
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the Test Package page header is shown as "<title>"
    And The user sees a drop down box for the Total number of participants
    And the drop down box is displayed as empty by default
    When the user clicks on the drop down box to see the values between "<minParticipants>" - "<maxParticipants>" displayed
    And the "<stage>" stage is marked as Mandatory To Do
    When the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Mandatory To Do
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    Examples:
      | stage        | title                    | minParticipants | maxParticipants | new_stage             | number_of |
      | Test package | Confirm the test package | 1               | 8               | Responsible clinician | 1         |

  @NTS-3177 @Z-LOGOUT
#    @E2EUI-1547 @E2EUI-1585
  Scenario Outline: NTS-3177:E2EUI-1547,1585: To Do list should be Mandatory To Do
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the Test Package page header is shown as "<title>"
    And The user sees a drop down box for the Total number of participants
    And the drop down box is displayed as empty by default
    When the user does not select one of the values
    And the user sees an error message "<errorMessage>"
    And the "<stage>" stage is marked as Mandatory To Do
    And the user selects the number of participants: "<count>"
    When the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Completed
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    And the correct "<count>" of participants are saved to the referral in "<stage>"
    Examples:
      | stage        | title                    | errorMessage                                                     | count | new_stage             | number_of |
      | Test package | Confirm the test package | Select the total number of participants you expect for this test | 2     | Responsible clinician | 1         |

  @NTS-3253 @Z-LOGOUT
#    @E2EUI-1585
  Scenario Outline: NTS-3253:E2EUI-1585:If the test is de-selected then Total number of participants field should disappear
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the Test Package page header is shown as "<title>"
    When the user clicks a test to de-select it
    Then the user sees the test has become deselected
    And the Total number of participants field is disappeared for the deselected test

    Examples:
      | stage        | title                    |
      | Test package | Confirm the test package |