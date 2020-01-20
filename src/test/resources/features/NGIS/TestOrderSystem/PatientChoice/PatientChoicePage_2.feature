@regression
@PatientChoice

Feature: Patient Choice Page Verification

  @NTS-3384 @E2EUI-1677 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3384: Verify the hospital no field on patient choice form
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user fills "<TestType>" details in test type
    And the user fills "<RecordedBy>" details in recorded by
    Then the user should be able to see patient hospital number

    Examples:
      | Patient choice stage | TestType                        | RecordedBy         |
      | Patient choice       | Rare & inherited diseases – WGS | ClinicianName=John |

  @NTS-3385 @E2EUI-1474 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3385: Create referral navigation component - Patient choice
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user edits the patient choice status
    When the user is navigated to a page with title Add patient choice information
    And the user sees a back button on Add patient choice information page
    When the user clicks on the Back link
    Then the user is navigated to a page with title Patient choice

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3386 @E2EUI-1141 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3386: Recorded By field is mandatory field in Add patient choice form
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user should see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user fills "<TestType>" details in test type
    And the user should see the chosen "<TestType>" with edit button in "Test type"
    Then the Test type option is marked as completed
    And the user clicks on Continue Button
    Then the user will be able to see an error message as "<ErrorMessage>"
    When the user fills "<RecordedBy>" details in recorded by
    Then the user should be able to see enabled continue button
    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | ErrorMessage                                                                           | RecordedBy         |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | Please complete the required field Clinician Name (Admin support user ID is optional): | ClinicianName=John |

  @NTS-3387 @E2EUI-1464 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3387: patient signature is a mandatory field in Add patient choice form
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user should see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user fills "<TestType>" details in test type
    And the user should see the chosen "<TestType>" with edit button in "Test type"
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the user should see the chosen "Recorded by:" with edit button in "Recorded by"
    Then the Recorded by option is marked as completed
    And the user selects the option "<PatientChoice>" as patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient signature
    And the user should be able to see submit patient choice button disabled
    ##Include the step for clicking on Continue without providing signature and validate the warning message.
    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy         | PatientChoice                  | YesOption |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John | Patient has agreed to the test | Yes       |
      ##Include other combination of PatientChoiceCategory and other patient choice section combinations.
      ##OR include this validation of clicking without providing signature ijnbig scenario where we cover all the combinations.

  @NTS-3388 @E2EUI-1112 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3388: Verify add patient choice form is an embedded app
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    And the user is navigated to a page with title Add patient choice information
    Then the user should be able to see Patient Choice form

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3377 @E2EUI-2034 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3377: Editing Patient Choice in Patient Choice category
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user should see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user clicks on edit button in Patient choice category
    When the user selects the option Child in patient choice category
    And the user should see the chosen "<Option2>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed

    Examples:
      | Patient choice stage | PatientChoiceCategory | Option2 |
      | Patient choice       | Adult (With Capacity) | Child   |

  @NTS-3378 @E2EUI-1181 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3378: Navigate around the patient choice pages
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user selects the option "<PatientChoice>" as patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Review and submit
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the Back link
    Then the user is navigated to a page with title Patient choice

    Examples:
      | Patient choice stage | TestType                        | RecordedBy                            | PatientChoice                                      |
      | Patient choice       | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient changed their mind about the clinical test |
