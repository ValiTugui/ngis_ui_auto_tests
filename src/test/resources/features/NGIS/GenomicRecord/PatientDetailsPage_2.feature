#@regression
#@patientDetails
@04-GENOMIC_RECORD
@SYSTEM_TEST
Feature: GenomicRecord: Patient details page 2

 @NTS-3101 @Z-LOGOUT
#    @E2EUI-2147
  Scenario Outline: NTS-3101:E2EUI-2147:A normal user cannot edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Given a web browser is logged in as a "GEL_NORMAL_USER" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    Then the NHS number field is disabled

    Examples:
      | patient-search-type |
      | NGIS                |
      | NHS Spine           |

  @NTS-3101 @Z-LOGOUT
#    @E2EUI-2146
  Scenario Outline: NTS-3101:E2EUI-2146:A super-user can edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    Given a web browser is logged in as a "GEL-ops" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    Then the NHS number field is enabled

    Examples:
      | patient-search-type |
      | NGIS                |

  @NTS-3151 @Z-LOGOUT
#    @E2EUI-1047
  Scenario Outline: NTS-3151:E2EUI-1047:'Completed and ongoing referrals' should display the details only with respect to the concerned patient
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage1>" stage
    And the "<stage1>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    ##Below two steps not valid for Gonzalo
#    And the user click on the referral card on patient details page to navigate to referral page
#    And the "<patient-search-type>" patient details searched for are the same in the referral header bar

    Examples:
      | patient-search-type | stage1          |
      | NGIS                | Patient details |

  @NTS-4762 @Z-LOGOUT
#    @E2EUI-1192
  Scenario Outline: NTS-4762:E2EUI-1192: Referral-Patient Detail - Update the 'success' message design
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    Then the "<stage>" stage is selected
    And the "<stage>" stage is marked as Completed
    And the user clicks the Save and Continue button
    Then the patient is successfully updated with a message "Patient details updated"

    Examples:
      | stage           |
      | Patient details |

  @NTS-4052 @Z-LOGOUT
    #@E2EUI-1837
#    @scenario_1
  Scenario Outline: NTS-4052:E2EUI-1837: Multidate picker - Real dates validation in Find your Patient Page.
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user is navigated to a page with title Find your patient
    And the YES button is selected by default on patient search
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the DOB field
    And the user clicks the NO button
    When the user click YES button for the question - Do you have the NHS no?
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB2>" fields
    Then the user does not see an error message on the page
    Examples:
      | patient-search-type | NhsNumber  | DOB        | error_message                     | DOB2       |
      | NGIS                | 9449305935 | 30-02-2000 | Check the day and month are valid | 19-03-2000 |

  @NTS-5810 @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field- create a new patient by passing Postcode.
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user is navigated to a page with title Find your patient
    And the display question for NHS Number is Do you have the patient's NHS Number?
    And the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    Then the message "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the "Create a record for this patient" page is displayed
    And the user fills in all the fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user fills in the Postcode field box with "<Postcode>"
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "NGIS patient record created"
    And the user is able to see the entered postcode value in the address field in correct "<PostcodeFormat>" format

    Examples:
      | message          | hyperlinkText               | reason_for_no_nhsNumber       | Postcode | PostcodeFormat |
      | No patient found | create a new patient record | Patient is a foreign national | AB1 2CD  | AB1 2CD        |
      | No patient found | create a new patient record | Patient is a foreign national | AB1  2CD | AB1 2CD        |
      | No patient found | create a new patient record | Patient is a foreign national | AB12CD   | AB1 2CD        |
      | No patient found | create a new patient record | Patient is a foreign national | ab1 2cd  | ab1 2cd        |
      | No patient found | create a new patient record | Patient is a foreign national | ab1  2cd | ab1 2cd        |
      | No patient found | create a new patient record | Patient is a foreign national | ab12cd   | ab1 2cd        |

  @NTS-5810  @Z-LOGOUT
#    @E2EUI-3018
  Scenario Outline:NTS-5810:E2EUI-3018: Verify Postcode update - handling whitespace in the postcode field- update a patient record by passing postcode.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-2005:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode1>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode2>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode3>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode4>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode5>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the Postcode field box with "<Postcode6>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user is able to see the entered postcode value is in correct "<PostcodeFormat>" format

    Examples:
      | PatientDetails  | PostcodeFormat | Postcode1 | Postcode2 | Postcode3 | Postcode4 | Postcode5 | Postcode6 |
      | Patient details | AB1 2CD        | AB1 2CD   | AB1  2CD  | AB12CD    | ab12cd    | ab1 2cd   | ab1  2cd  |
