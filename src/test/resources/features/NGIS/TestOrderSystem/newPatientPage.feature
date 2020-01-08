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
    @newPatientPage_06 @E2EUI-892 @v_1
  Scenario Outline: Normal User - Create a new patient record with no NHS Number
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
    @newPatientPage_07 @E2EUI-892 @v_1
  Scenario Outline: Super User - Create a new patient record with no NHS Number
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