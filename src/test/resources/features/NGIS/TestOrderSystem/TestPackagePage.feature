@testPackage
Feature: Test Package page

  @E2EUI-911 @NTS-3073 @LOGOUT @V1
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

  @E2EUI-2139 @NTS-3109 @V1 @P0
  Scenario Outline: NTS-3109 - Test package Page - verify Warning Messages of unsaved changes - Moving to another To do list - Cancer
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Cancer |
    And the user navigates to the "<stage>" stage
    And the user clicks a test to de-select it
    When the user navigates to the "<new_stage>" stage
    Then the user sees a warning message on the page

    Examples:
      | stage        | new_stage             |
      | Test package | Responsible clinician |

  @E2EUI-2139 @NTS-3109 @LOGOUT_BEFORE_TEST @V1 @P0
  Scenario Outline: NTS-3109 - Test package Page - verify Warning Messages of unsaved changes - Moving to another To do list - Rare-Disease
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    And the user selects the number of participants as "<number>"
    When the user navigates to the "<new_stage>" stage
    Then the user sees a warning message on the page

    Examples:
      | stage        | number | new_stage             |
      | Test package | 2      | Responsible clinician |


  @E2EUI-2139 @NTS-3109 @LOGOUT @V1 @P0
  Scenario Outline: @NTS-3109 - Test package Page - verify Warning Messages of unsaved changes - Browser interactions - Rare-Disease
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    And the user selects the number of participants as "<number>"
    When the user attempts to navigate away by clicking "<browser_exit_option>"
    Then the user sees a warning message on the page

    Examples:
      | stage        | number | browser_exit_option |
      | Test package | 2      | refresh             |
      #| Test package | 3      | forward             |
      #| Test package | 4      | back                |



  @E2EUI-2139 @NTS-3109  @V1 @P0
  Scenario Outline: NTS-3109 - Test package Page - verify Warning Messages of unsaved changes - Browser interactions - Cancer
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebral malformation | NGIS | Cancer |
    And the user navigates to the "<stage>" stage
    And the user clicks a test to de-select it
    When the user attempts to navigate away by clicking "<browser_exit_option>"
    Then the user sees a warning message on the page

    Examples:
      | stage        | browser_exit_option |
      | Test package | refresh             |
      #| Test package | forward             |
     # | Test package | back                |

