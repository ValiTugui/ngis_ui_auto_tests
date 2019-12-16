@regression
@COMP09
@PatientChoice
Feature: Patient Choice Page

  @COMP8_TO_PatientSearch
    @patientChoice_Page01 @LOGOUT @NTS-3341 @E2EUI-1659 @BVT-P0 @v_1 @P0
  Scenario Outline: E2EUI-1659: Verify the patient choice status in family member page
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<Family members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status in family member page as "<Status1>"
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills new patient choice form with below details
      | <PatientChoiceCategory> |
      | <TestType>              |
      | <RecordedBy>            |
      | <PatientChoice>         |
      | <ChildAssent>           |
      | <ParentSignature>       |
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user sees the patient choice status as "<Status2>"
    When the user navigates to the "<Family members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status as "<Status2>"

    Examples:
      | Family members | Status1     | Patient choice stage | Status2           | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                        | ChildAssent | ParentSignature                       |
      | Family members | Not entered | Patient choice       | Agreed to testing | Child                 | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123 | Parent(s) / carer / guardian have agreed to the test | Yes         | FirstName=firstname:LastName=lastname |

  @COMP9_TO_PatientChoice
    @patientChoice_Page02 @LOGOUT @E2EUI-2110 @v_1 @P0
  Scenario Outline: E2EUI-2110: Verify the upload revised patient choice documentation to form library
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<Family members>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status in family member page as "<Status1>"
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the "<LinkText>" link in patient choice page
    Then the patient choice form library page displays correctly

    Examples:
      | Family members | Status1     | Patient choice stage | LinkText     |
      | Family members | Not entered | Patient choice       | Form library |

  @COMP9_TO_PatientChoice
    @patientChoice_Page03 @E2EUI-2039 @v_1 @P0
  Scenario Outline: E2EUI-2039: Verify the relevant Patient choice for an Adult with capacity
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449303843:DOB=18-08-2005 |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user will see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user fills "<TestType>" details in test type
    And the user will see the chosen "<TestType>" with edit button in "Test type"
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the user will see the chosen "Recorded by:" with edit button in "Recorded by"
    Then the Recorded by option is marked as completed

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            |
      | Patient choice       | Adult (With Capacity) | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123 |

  @COMP9_TO_PatientChoice
    @patientChoice_Page04 @E2EUI-2039 @v_1 @P0 @scenario_01
  Scenario Outline: E2EUI-2039: scenario_01 - Verify the relevant Patient choice for an Adult with capacity
    When the user is navigated to a patient choice form option with title Patient choices
    And the user should be able to see the details of patient choices option
    When the user fills "<PatientChoice>" details in patient choices
    Then the user will see a "<WarningMessage1>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
##  Review and submit button depends on environment(e2e=Review and Submit, e2e-latest=Review and submit)
    When the user is navigated to a patient choice form option with title Review and Submit
##  Below two lines are only for e2e-latest
#    And the user will see a "<WarningMessage2>" warning message on the patient choice information option
#    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as disabled

    Examples:
      | PatientChoice                                      | WarningMessage1                                                                                                                                               | WarningMessage2                                                                                                                                                                          |
      | Patient changed their mind about the clinical test | Did you mean to select ‘Patient changed their mind about the clinical test’? If so, please consider whether continuing with this test request is appropriate. | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_Page05 @E2EUI-2039 @v_1 @P0 @scenario_02
  Scenario Outline: E2EUI-2039: scenario_02 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    And the user is navigated to a patient choice form option with title Patient choices
    Then the user should be able to see the details of patient choices option
    And the user should see continue button is not highlighted
    When the user fills "<PatientChoice>" details in patient choices
    Then the user should be able to see all the details of patient choices reasons
    When the user fills "<Reasons>" details in patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see selected patient choice details
##  Review and submit button depends on environment(e2e=Review and Submit, e2e-latest=Review and submit)
    When the user is navigated to a patient choice form option with title Review and Submit
    And the user will see a "<WarningMessage>" warning message on the patient choice information option
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as disabled

    Examples:
      | PatientChoice                                     | Reasons | WarningMessage                                                                                                                                                                           |
      | Record of Discussion form not currently available | Other   | By hitting submit you are confirming that the patient has indicated their choice and that you have accurately recorded this choice as described or that a patient choice was not needed. |

  @COMP9_TO_PatientChoice
    @patientChoice_Page06 @E2EUI-2039 @v_1 @P0 @scenario_03
  Scenario Outline: E2EUI-2039: scenario_03 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    And the user should be able to see the details of patient choices option
    And the user clicks on Continue Button
    Then the user should see a error message box
    When the user fills "<PatientChoice1>" details in patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user clicks on Continue Button
    Then the user should see a error message box
    And the user selects "<NoOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should be able to see all the details of patient choices research participation
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the user should see a error message box
    When the user fills "<PatientChoice2>" details in patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see selected patient choice details
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as disabled

    Examples:
      | PatientChoice1                 | NoOption | Question1                                  | Question2                                          | WarningMessage                                                                                                         | PatientChoice2                                |
      | Patient has agreed to the test | No       | Has research participation been discussed? | Why has research participation not been discussed? | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | Patient would like to revisit at a later date |

  @COMP9_TO_PatientChoice
    @patientChoice_Page07 @E2EUI-2039 @v_1 @P0 @scenario_04
  Scenario Outline: E2EUI-2039: scenario_04 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    And the user should be able to see the details of patient choices option
    And the user clicks on Continue Button
    Then the user should see a error message box
    When the user fills "<PatientChoice>" details in patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should be able to see Yes and No answer options for the question
    And the user clicks on Continue Button
    Then the user should see a error message box
    And the user selects "<NoOption>" data and sample option in patient choices
    Then the user will see a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see selected patient choice details
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as disabled

    Examples:
      | PatientChoice                  | YesOption | NoOption | Question1                                  | WarningMessage                                                                                                                                                      | Question2                                                                                      |
      | Patient has agreed to the test | Yes       | No       | Has research participation been discussed? | You have selected \"No\" to participation in research. Please ensure the patient is aware they might be contacted in the future about other research opportunities. | The patient agrees that their data and samples may be used for research, separate to NHS care. |

  @COMP9_TO_PatientChoice
    @patientChoice_Page08 @LOGOUT @E2EUI-2039 @v_1 @P0 @scenario_05
  Scenario Outline: E2EUI-2039: scenario_05 - Verify the relevant Patient choice for an Adult with capacity
    When the user clicks on edit button in Patient choices
    And the user should be able to see previous section re-opened
    When the user is navigated to a patient choice form option with title Patient choices
    And the user should be able to see the details of patient choices option
    And the user clicks on Continue Button
    Then the user should see a error message box
    When the user fills "<PatientChoice>" details in patient choices
    Then the question will be displayed as "<Question1>"
    And the user should be able to see Yes and No answer options
    And the user selects "<YesOption>" research participation option in patient choices
    Then the question will be displayed as "<Question2>"
    And the user should be able to see Yes and No answer options for the question
    And the user clicks on Continue Button
    Then the user should see a error message box
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should be able to see selected patient choice details
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user should be able to see the highlighted Submit patient choice button
    Then Save and continue button is displayed as disabled

    Examples:
      | PatientChoice                  | YesOption | Question1                                  | Question2                                                                                      |
      | Patient has agreed to the test | Yes       | Has research participation been discussed? | The patient agrees that their data and samples may be used for research, separate to NHS care. |

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_09 @LOGOUT @E2EUI-1415 @v_1 @P0
  Scenario Outline: E2EUI-1415: Requesting Organisation landing page
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    And the user is navigated to a page with title Check your patient
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user should be able to see an intro message "<introMessage>" on requesting organisation page
    Then the user should be able to see hint text in search box on requesting organisation page
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed

    Examples:
      | Requesting organisation | introMessage                                                   | ordering_entity_name |
      | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | Maidstone            |

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_10 @LOGOUT @E2EUI-1677 @v_1 @P0
  Scenario Outline: E2EUI-1677: Verify the hospital no field on patient choice form
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user fills "<TestType>" details in test type
    And the user fills "<RecordedBy>" details in recorded by
    Then the user should be able to see patient hospital number

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy         |
      | Patient choice       | Adult (With Capacity) | Rare & heritable diseases – WGS | ClinicianName=John |

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_11 @LOGOUT @E2EUI-1474 @v_1 @P0
  Scenario Outline: E2EUI-1474: Verify patient choice form
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
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

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_12 @LOGOUT @E2EUI-1141 @v_1 @P0
  Scenario Outline: E2EUI-1141: Recorded By field is mandatory field in Add patient choice form
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user will see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user fills "<TestType>" details in test type
    And the user will see the chosen "<TestType>" with edit button in "Test type"
    Then the Test type option is marked as completed
    When the user fills "<blankRecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user will be able to see an error message as "<ErrorMessage>"
    When the user fills "<RecordedBy>" details in recorded by
    Then the user should be able to see enabled continue button
    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | blankRecordedBy | ErrorMessage                                                                           | RecordedBy         |
      | Patient choice       | Adult (With Capacity) | Rare & heritable diseases – WGS |                 | Please complete the required field Clinician Name (Admin support user ID is optional): | ClinicianName=John |

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_13 @LOGOUT @E2EUI-1464 @v_1 @P0
  Scenario Outline: E2EUI-1464: patient signature is a mandatory field in Add patient choice form
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user will see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user fills "<TestType>" details in test type
    And the user will see the chosen "<TestType>" with edit button in "Test type"
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the user will see the chosen "Recorded by:" with edit button in "Recorded by"
    Then the Recorded by option is marked as completed
    And the user fills "<PatientChoice>" details in patient choices
    And the user selects "<YesOption>" research participation option in patient choices
    And the user selects "<YesOption>" data and sample option in patient choices
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient signature
    And the user clicks on submit patient choice Button
    Then the user will be able to see an error message as "<ErrorMessage>"

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy         | PatientChoice                  | YesOption | ErrorMessage                                            |
      | Patient choice       | Adult (With Capacity) | Rare & heritable diseases – WGS | ClinicianName=John | Patient has agreed to the test | Yes       | Please provide a signature in order to complete consent |

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_14 @LOGOUT @E2EUI-1112  @v_1 @P0
  Scenario Outline: E2EUI-1112: Verify add patient choice form is an embedded app
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310475:DOB=09-12-2010 |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    And the user is navigated to a page with title Add patient choice information
    Then the user should be able to see Patient Choice form

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_15 @LOGOUT @E2EUI-2034 @v_1 @P0
  Scenario Outline: E2EUI-2034: Editing Patient Choice in Patient Choice category
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    And the user will see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed
    When the user clicks on edit button in Patient choice category
    When the user fills "<Option2>" details in patient choice category
    And the user will see the chosen "<Option2>" with edit button in "Patient choice category"
    Then the Patient choice category option is marked as completed

    Examples:
      | Patient choice stage | PatientChoiceCategory | Option2 |
      | Patient choice       | Adult (With Capacity) | Child   |

  @COMP9_TO_PatientChoiceAdd
    @PatientChoice_page_16 @LOGOUT @E2EUI-1181 @v_1 @P0
  Scenario Outline: E2EUI-1181: Navigate around the patient choice pages
    Given a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease | NHSNumber=9449310270:DOB=12-08-2007 |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user fills "<PatientChoiceCategory>" details in patient choice category
    When the user fills "<TestType>" details in test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is navigated to a patient choice form option with title Patient choices
    When the user fills "<PatientChoice>" details in patient choices
    And the user clicks on Continue Button
##  Review and submit button depends on environment(e2e=Review and Submit, e2e-latest=Review and submit)
    When the user is navigated to a patient choice form option with title Review and Submit
##   Below line is only valid for e2e environment, not in e2e-latest environment
    And the user fills Review and submit details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the Back link
    Then the user is navigated to a page with title Patient choice

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                      |
      | Patient choice       | Adult (With Capacity) | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient changed their mind about the clinical test |
