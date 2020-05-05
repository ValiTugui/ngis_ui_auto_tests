#@regression
#@referral
@03-TEST_ORDER
@SYSTEM_TEST
Feature: GlobalConsistency:Global Patent Flow 7 - Referral Header

  @NTS-4813 @Z-LOGOUT
    #@E2EUI-1005
  Scenario Outline:NTS-4813:E2EUI-1005:Referral Cancer - Show alert when page is mandatory and must be completed to submit
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage1>" stage
    And the "<stage1>" stage is marked as Completed
    When the user submits the referral
    And the user sees a dialog box with a title "<dialogTitle>"
    And the user sees a list of outstanding mandatory stages to be completed in the dialog box
      | MandatoryStagesToComplete |
      | Requesting organisation   |
      | Test package              |
      | Responsible clinician     |
      | Tumours                   |
      | Patient choice            |
    And the user clicks on the mandatory stage "<stage2>" in the dialog box
    And the "<stage2>" stage is selected
    Then the "<pageTitle>" page is displayed
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And  the user clicks the Save and Continue button
    And the "<stage3>" stage is selected
    When the user submits the referral
    And the user sees a dialog box with a title "<dialogTitle>"
    And the user sees a list of outstanding mandatory stages to be completed in the dialog box
      | MandatoryStagesToComplete |
      | Test package              |
      | Responsible clinician     |
      | Tumours                   |
      | Patient choice            |
    And the user clicks on the mandatory stage "<stage3>" in the dialog box
    And the "<stage3>" stage is selected

    Examples:
      | stage1          | dialogTitle                  | stage2                  | pageTitle                     | ordering_entity_name | stage3       |
      | Patient details | There is missing information | Requesting organisation | Add a requesting organisation | Maidstone            | Test package |


  @NTS-4813 @Z-LOGOUT
    #@E2EUI-1005
  Scenario Outline:NTS-4813:Referral RD - Show alert when page is mandatory and must be completed to submit
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Cerebellar anomalies | RD | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage1>" stage
    And the "<stage1>" stage is marked as Completed
    When the user submits the referral
    Then the user sees a dialog box with a title "<dialogTitle>"
    And the user sees a list of outstanding mandatory stages to be completed in the dialog box
      | MandatoryStagesToComplete |
      | Requesting organisation   |
      | Test package              |
      | Responsible clinician     |
      | Clinical questions        |
      | Patient choice            |
    When the user clicks on the mandatory stage "<stage2>" in the dialog box
    Then the "<stage2>" stage is selected
    And the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And  the user clicks the Save and Continue button
    And the "<stage3>" stage is selected
    When the user submits the referral
    And the user sees a dialog box with a title "<dialogTitle>"
    And the user sees a list of outstanding mandatory stages to be completed in the dialog box
      | MandatoryStagesToComplete |
      | Test package              |
      | Responsible clinician     |
      | Clinical questions        |
      | Patient choice            |
    And the user clicks on the mandatory stage "<stage3>" in the dialog box
    And the "<stage3>" stage is selected

    Examples:
      | stage1          | dialogTitle                  | stage2                  | ordering_entity_name | stage3       |
      | Patient details | There is missing information | Requesting organisation | Maidstone            | Test package |

  @NTS-4562 @Z-LOGOUT
#    @E2EUI-1088
  Scenario Outline: NTS-4562-The user is able to logout from Referral Header - Test Ordering system
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the user clicks the Log out button
    Then the user is successfully logged out

    Examples:
      | stage           |
      | Patient details |
