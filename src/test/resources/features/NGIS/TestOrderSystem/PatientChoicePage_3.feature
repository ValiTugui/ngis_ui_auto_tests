@regression
@regression_set10
@patientChoice
Feature: Patient Choice Page

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_17 @LOGOUT @NTS-3409 @E2EUI-1822 @v_1 @P0
  Scenario Outline: NTS-3409: Navigate around the patient choice pages
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |

    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    And the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                            | RecordedBy                            | PatientChoice                  | YesOption |
      | Patient choice       | Adult (With Capacity) | Cancer (paired tumour normal) – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       |


  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_18 @LOGOUT @E2EUI-1960 @NTS-3411 @v_1 @P0
  Scenario Outline: NTS-3411: Verify the info message when user declined for a test
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |

    When the user navigates to "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    Then the user will see a "<InfoMessage>" warning message on the patient choice information option

    Examples:
      | Patient choice stage | PatientChoiceCategory    | TestType                            | RecordedBy                            | PatientChoice                                                           | InfoMessage                                                                                                                                                   |
      | Patient choice       | Adult (With Capacity)    | Rare & inherited diseases – WGS     | ClinicianName=John:HospitalNumber=123 | Patient changed their mind about the clinical test                      | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |
      | Patient choice       | Adult (Without Capacity) | Rare & inherited diseases – WGS     | ClinicianName=John:HospitalNumber=123 | Consultee changed their mind about the clinical test                    | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |
      | Patient choice       | Child                    | Cancer (paired tumour normal) – WGS | ClinicianName=John:HospitalNumber=123 | Parent(s) / carer / guardian changed their mind about the clinical test | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. |

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_19 @LOGOUT @E2EUI-1127 @E2EUI-1934 @NTS-3410 @v_1 @P0
  Scenario Outline: NTS-3410: Verify the patient Choice stage marked as completed
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |

    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    And the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice
    Then the "<Patient choice stage>" stage is marked as Completed

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | YesOption |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       |


  @COMP9_TO_PatientChoice
    @PatientChoice_page_20 @LOGOUT @NTS-3414 @E2EUI-1889 @v_1 @P0
  Scenario Outline: NTS-3414: Verify the Supporting information form section in form library
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user clicks on the "<forms>" link in patient choice page
    And the user should be able to see a sub title "<forms>" on add patient choice information page
    Then the should be able to see an additional section "<additionalForms>" under the Form Library

    Examples:
      | Patient choice stage | forms        | additionalForms        |
      | Patient choice       | Form library | Supporting information |


  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_21 @LOGOUT @E2EUI-1627 @NTS-3415 @v_1 @P0
  Scenario Outline: NTS-3415: Verify the patient Choice 'Save & Continue' button is disabled until the patient choice has been submitted
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |

    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see highlighted continue button
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    And the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then Save and continue button is displayed as disabled
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    Then Save and continue button is displayed as enabled
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user should be able to see highlighted continue button
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on "New patient choice" link
    And the user clicks on the amend patient choice button
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then Save and continue button is displayed as disabled
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    Then Save and continue button is displayed as enabled
    When the user clicks the Save and Continue button
    And the user should be able to see highlighted continue button
    Then the "<Patient choice stage>" stage is marked as Completed

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | YesOption |
      | Patient choice       | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test | Yes       |

