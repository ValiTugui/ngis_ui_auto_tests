@regression
@regression_set1
@newPatientPage

Feature: New Patient page

  @newPatientPage_01 @NTS-3072 @E2EUI-981 @P1 @v_1 @BVT_P0
  Scenario Outline: NTS-3072: Verify the interface links and buttons for a New Patient Patient page - Invalid NhsNo and DOB
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the new patient page displays expected input-fields and a "<label_on_submit_button>" submit button

    Examples:
      | hyperlinkText               | label_on_submit_button       |
      | create a new patient record | Save patient details to NGIS |

  @newPatientPage_02 @NTS-3072 @E2EUI-981 @P1 @v_1 @BVT_P0
  Scenario Outline: NTS-3072: Verify the interface links and buttons for a New Patient Patient page - Invalid Details in N) fields
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the new patient page displays expected input-fields and a "<label_on_submit_button>" submit button

    Examples:
      | hyperlinkText               | label_on_submit_button       |
      | create a new patient record | Save patient details to NGIS |

  @COMP2_TO_NewPatient
  @newPatientPage_03 @NTS-3150 @E2EUI-2122 @P0 @v_1
  Scenario Outline: NTS-3150:Add Enums for no nhsnumber reasons - Patient record creation
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"

    Examples:
      | hyperlinkText               | reason_for_no_nhsNumber       |
      | create a new patient record | Other - provide explanation   |
      | create a new patient record | Patient is a foreign national |


  @COMP2_TO_NewPatient @LOGOUT
    @newPatientPage_04 @E2EUI-1134 @v_1
  Scenario Outline: Normal User - To validate input fields for the Non-NHS patient creation - with a Normal-User
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    When the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    Then the "<pageTitle>" page is displayed
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader                    |
      | First name ✱                   |
      | Last name ✱                    |
      | Date of birth ✱                |
      | Gender ✱                       |
      | Life status ✱                  |
      | Reason NHS Number is missing ✱ |
      | Hospital number ✱              |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader   |
      | Title         |
      | Date of death |
      | Ethnicity     |
      | Address       |
      | Postcode      |

    Examples:
      | message          | hyperlinkText               | pageTitle                         |
      | No patient found | create a new patient record | Add a new patient to the database |


  @COMP2_TO_NewPatient @LOGOUT
    @newPatientPage_05 @E2EUI-1134 @v_1
  Scenario Outline: Super User - To validate input fields for the Non-NHS patient creation - with a Super User
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    And the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    When the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    Then the "<pageTitle>" page is displayed
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader                    |
      | First name ✱                   |
      | Last name ✱                    |
      | Date of birth ✱                |
      | Gender ✱                       |
      | Life status ✱                  |
      | Reason NHS Number is missing ✱ |
      | Hospital number ✱              |
    And the No button is selected by default for the question - Do you have the NHS Number?
    And the user select a reason for "<reason_for_no_nhsNumber>"
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader                          |
      | Explanation for missing NHS Number ✱ |
    And the user click YES button for the question - Do you have the NHS no?
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader  |
      | NHS Number ✱ |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader   |
      | Title         |
      | Date of death |
      | Ethnicity     |
      | Address       |
      | Postcode      |

    Examples:
      | message          | hyperlinkText               | pageTitle                         | reason_for_no_nhsNumber     |
      | No patient found | create a new patient record | Add a new patient to the database | Other - provide explanation |


  @COMP2_TO_NewPatient @LOGOUT
    @newPatientPage_06 @NTS-3465 @E2EUI-892 @E2EUI-1475 @v_1
  Scenario Outline: NTS-3465: Normal User - Create a new patient record with no NHS Number
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle>" page is displayed
    Then the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    Then the patient is successfully updated with a "<notification>"
    And the Start Referral button is disabled


    Examples:
      | message          | hyperlinkText               | pageTitle                         | reason_for_no_nhsNumber     | notification  |
      | No patient found | create a new patient record | Add a new patient to the database | Other - provide explanation | Details saved |


  @COMP2_TO_NewPatient @LOGOUT
    @newPatientPage_07 @NTS-3465 @E2EUI-892 @E2EUI-1475 @v_1
  Scenario Outline: NTS-3465: Super User - Create a new patient record with no NHS Number
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    When the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    Then the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    Then the patient is successfully updated with a "<notification>"
    And the Start Referral button is disabled

    Examples:
      | message          | hyperlinkText               | pageTitle                         | reason_for_no_nhsNumber     | notification  |
      | No patient found | create a new patient record | Add a new patient to the database | Other - provide explanation | Details saved |


  @COMP2_TO_NewPatient @LOGOUT
    @newPatientPage_08 @NTS-3466 @E2EUI-1052 @v_1
  Scenario Outline: NTS-3466: Validate the Ethnicity drop down values to check for the order of the drop down is in logical - Alphabetical order
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the "<pageTitle>" page is displayed
    And the Ethnicity drop-down values are in Alphabetical order
      | EthnicityListHeader                                     |
      | A - White - British                                     |
      | B - White - Irish                                       |
      | C - White - Any other White background                  |
      | D - Mixed - White and Black Caribbean                   |
      | E - Mixed - White and Black African                     |
      | F - Mixed - White and Asian                             |
      | G - Mixed - Any other mixed background                  |
      | H - Asian or British Asian - Indian                     |
      | J - Asian or British Asian - Pakistani                  |
      | K - Asian or British Asian - Bangladeshi                |
      | L - Asian or British Asian - Any other Asian background |
      | M - Black or Black British - Caribbean                  |
      | N - Black or Black British - African                    |
      | P - Black or Black British - Any other Black background |
      | R - Chinese                                             |
      | S - Any other ethnic group                              |
      | Z - Not stated                                          |
      | Not known                                               |

    Examples:
      | hyperlinkText               | pageTitle                         |
      | create a new patient record | Add a new patient to the database |


  @COMP2_TO_NewPatient @LOGOUT
    @newPatientPage_09 @E2EUI-822
  Scenario Outline: Verify the input field validations on create new patient page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle>" page is displayed
    When the user clears the date of birth field
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                    | errorMessageHeader                  | messageColourHeader |
      | First name ✱                   | First name is required.             | #dd2509             |
      | Last name ✱                    | Last name is required.              | #dd2509             |
      | Date of birth ✱                | Date of birth is required.          | #dd2509             |
      | Gender ✱                       | Gender is required.                 | #dd2509             |
      | Life status ✱                  | Life status is required.            | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number | #dd2509             |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |

    Examples:
      | message          | hyperlinkText               | pageTitle                         |
      | No patient found | create a new patient record | Add a new patient to the database |

