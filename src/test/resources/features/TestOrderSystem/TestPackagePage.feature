@testPackage
Feature: Test Package page

  @E2EUI-911 @NTS-3073 @LOGOUT_BEFORE_TEST @LOGOUT @V1
  Scenario Outline: @NTS-3073 - Test package Page Layout - Cancer
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | NGIS | Cancer |
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

  @E2EUI-911 @NTS-3080 @LOGOUT @V1
  Scenario Outline: @NTS-3080 - Test package Page Layout - Rare Disease
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
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

