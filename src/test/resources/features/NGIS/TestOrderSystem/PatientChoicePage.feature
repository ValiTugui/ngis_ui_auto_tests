@regression
@COMP08_P0
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
#    Adding new line to wait for form loading
    And the user should be able to see that the patient choice form is displayed
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
    Then the details in patient choice form library page displays correctly

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
    And the user should see the chosen "<PatientChoiceCategory>" with edit button in "Patient choice category"
    Then the option Patient choice category is marked as completed
    When the user fills "<TestType>" details in test type
    And the user should see the chosen "<TestType>" with edit button in "Test type"
    Then the option Test type is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the user should see the chosen "Recorded by:" with edit button in "Recorded by"
    Then the option Recorded by is marked as completed

    Examples:
      | Patient choice stage | PatientChoiceCategory | TestType                        | RecordedBy                            |
      | Patient choice       | Adult (With Capacity) | Rare & heritable diseases – WGS | ClinicianName=John:HospitalNumber=123 |

  @COMP9_TO_PatientChoice
    @patientChoice_Page04 @E2EUI-2039 @v_1 @P0 @scenario_01
  Scenario Outline: E2EUI-2039: scenario_01 - Verify the relevant Patient choice for an Adult with capacity
    When the user is navigated to a patient choice form option with title Patient choices
    And the user should be able to see all the details of patient choices option
    When the user fills "<PatientChoice>" details in patient choices
    Then the user sees a "<WarningMessage1>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the option Patient choices is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see all selected patient choice details
##  Review and submit button depends on environment(e2e=Review and Submit, e2e-latest=Review and submit)
    When the user is navigated to a patient choice form option with title Review and Submit
##  Below two lines are only for e2e-latest
#    And the user sees a "<WarningMessage2>" warning message on the patient choice information option
#    Then the user sees the highlighted Submit patient choice button
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
    Then the user should be able to see all the details of patient choices option
    And the user should see continue button is not highlighted
    When the user fills "<PatientChoice>" details in patient choices
    Then the user should be able to see all the details of patient choices reasons
    When the user fills "<Reasons>" details in patient choices
    And the user clicks on Continue Button
    Then the option Patient choices is marked as completed
    And the user should be able to see the previous sections disappeared
    And the user should be able to see all selected patient choice details
##  Review and submit button depends on environment(e2e=Review and Submit, e2e-latest=Review and submit)
    When the user is navigated to a patient choice form option with title Review and Submit
    And the user sees a "<WarningMessage>" warning message on the patient choice information option
    Then the user sees the highlighted Submit patient choice button
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
    And the user should be able to see all the details of patient choices option
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
    Then the user sees a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the user should see a error message box
    When the user fills "<PatientChoice2>" details in patient choices
    And the user clicks on Continue Button
    Then the option Patient choices is marked as completed
    And the user should be able to see all selected patient choice details
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user sees the highlighted Submit patient choice button
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
    And the user should be able to see all the details of patient choices option
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
    Then the user sees a "<WarningMessage>" warning message on the patient choice information option
    And the user clicks on Continue Button
    Then the option Patient choices is marked as completed
    And the user should be able to see all selected patient choice details
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user sees the highlighted Submit patient choice button
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
    And the user should be able to see all the details of patient choices option
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
    Then the option Patient choices is marked as completed
    And the user should be able to see all selected patient choice details
    When the user is navigated to a patient choice form option with title Patient signature
    And the user fills PatientSignature details in patient signature
    Then the user sees the highlighted Submit patient choice button
    Then Save and continue button is displayed as disabled

    Examples:
      | PatientChoice                  | YesOption | Question1                                  | Question2                                                                                      |
      | Patient has agreed to the test | Yes       | Has research participation been discussed? | The patient agrees that their data and samples may be used for research, separate to NHS care. |
