@regression
@TO_Common
@newPatientPage

Feature: New Patient page

  @NTS-3072 @E2EUI-981 @P1 @v_1 @BVT_P0
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

  @NTS-3072 @E2EUI-981 @P1 @v_1 @BVT_P0
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

  @NTS-3150 @E2EUI-2122 @P0 @v_1
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

  @NTS-3456 @E2EUI-1134 @E2EUI-1067 @LOGOUT @v_1
  Scenario Outline: NTS-3456: Normal User - To validate input fields for the Non-NHS patient creation - with a Normal-User
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
      |Ethnicity ✱                     |
      | Reason NHS Number is missing ✱ |
      | Hospital number ✱              |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader   |
      | Title         |
      | Date of death |
      | Address       |
      | Postcode      |

    Examples:
      | message          | hyperlinkText               | pageTitle                         |
      | No patient found | create a new patient record | Add a new patient to the database |


  @NTS-3456 @E2EUI-1134 @LOGOUT @v_1
  Scenario Outline: NTS-3456: Super User - To validate input fields for the Non-NHS patient creation - with a Super User
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
      |Ethnicity ✱                     |
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
      | Address       |
      | Postcode      |

    Examples:
      | message          | hyperlinkText               | pageTitle                         | reason_for_no_nhsNumber     |
      | No patient found | create a new patient record | Add a new patient to the database | Other - provide explanation |

  @NTS-3465 @E2EUI-892 @E2EUI-1475 @E2EUI-1308 @E2EUI-1416  @LOGOUT @v_1
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
    Then the patient is successfully updated with a message "<notification>"
#    And the Start Referral button is disabled
    And the Start New Referral button is disabled


    Examples:
      | message          | hyperlinkText               | pageTitle                         | reason_for_no_nhsNumber     | notification  |
      | No patient found | create a new patient record | Add a new patient to the database | Other - provide explanation | Details saved |

  @NTS-3465 @E2EUI-892 @E2EUI-1475 @E2EUI-1416 @LOGOUT @v_1
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
    Then the patient is successfully updated with a message "<notification>"
#    And the Start Referral button is disabled
    And the Start New Referral button is disabled

    Examples:
      | message          | hyperlinkText               | pageTitle                         | reason_for_no_nhsNumber     | notification  |
      | No patient found | create a new patient record | Add a new patient to the database | Other - provide explanation | Details saved |

  @NTS-3466 @E2EUI-1052 @LOGOUT @v_1
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

  @NTS-3468 @E2EUI-1189 @LOGOUT
  Scenario Outline: NTS-3468:Verify the input field validations on create new patient page
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
      | Ethnicity ✱                    | Ethnicity is required.              | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number | #dd2509             |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |

    Examples:
      | message          | hyperlinkText               | pageTitle                         |
      | No patient found | create a new patient record | Add a new patient to the database |

  @NTS-3468 @E2EUI-1196 @LOGOUT
  Scenario Outline: NTS-3468: new patient page with no NHsNumber- when last name is filled and all mandatory fields are left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle>" page is displayed
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader                    |
      | First name ✱                   |
      | Last name ✱                    |
      | Date of birth ✱                |
      | Gender ✱                       |
      | Life status ✱                  |
      | Ethnicity ✱                    |
      | Reason NHS Number is missing ✱ |
      | Hospital number ✱              |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader   |
      | Title         |
      | Date of death |
      | Address       |
      | Postcode      |
    When the user clears the date of birth field
    And the user fill in the last name field
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                    | errorMessageHeader                  | messageColourHeader |
      | First name ✱                   | First name is required.             | #dd2509             |
      | Date of birth ✱                | Date of birth is required.          | #dd2509             |
      | Gender ✱                       | Gender is required.                 | #dd2509             |
      | Life status ✱                  | Life status is required.            | #dd2509             |
      | Ethnicity ✱                    | Ethnicity is required.              | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number | #dd2509             |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |

    Examples:
      | message          | hyperlinkText               | pageTitle                         |
      | No patient found | create a new patient record | Add a new patient to the database |


  @NTS-3507 @E2EUI-1649 @E2EUI-1584 @LOGOUT
  Scenario Outline:NTS-3507:Super-user - Hospital number is conditionally non-nullable if NHS number is null
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the No button is selected by default for the question - Do you have the NHS Number?
    Then the user fills in all fields without NHS number, enters a reason for noNhsNumber "<reason_for_no_nhsNumber>" and leaves HospitalNo field blank
    When the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                    | errorMessageHeader                  | messageColourHeader |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |
    And the user fills in the HospitalNo field
    When the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"

    Examples:
      | hyperlinkText               | pageTitle                         | reason_for_no_nhsNumber     |
      | create a new patient record | Add a new patient to the database | Other - provide explanation |


  @NTS-3507 @E2EUI-1649 @E2EUI-1584 @LOGOUT
  Scenario Outline:NTS-3507: Super-user - Hospital number is conditionally non-nullable if NHS number is null
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    And the NHS number field is displayed
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader                    |
      | Hospital number ✱              |
    Then the user fills in all fields and leaves NHS Number and HospitalNo fields blank
    When the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader       | errorMessageHeader           | messageColourHeader |
      | NHS Number ✱      | NHS Number is required.      | #dd2509             |
      | Hospital number ✱ | Hospital number is required. | #dd2509             |
    When the user fills in the NHS Number field
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader     |
      | Hospital number |
    When the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"

    Examples:
      | hyperlinkText               | pageTitle                         |
      | create a new patient record | Add a new patient to the database |


   @NTS-3508 @E2EUI-1660 @LOGOUT @v_1
  Scenario Outline: NTS-3508: Super User - Create a new patient record with an NHS Number
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    When the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    Then the NHS number field is displayed
    When the user fills in all the fields with NHS number on the New Patient page
    When the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"

    Examples:
      | message          | hyperlinkText               | pageTitle                         |
      | No patient found | create a new patient record | Add a new patient to the database |


  @NTS-3512 @E2EUI-1508 @LOGOUT
  Scenario Outline: NTS-3512: Patient record-New Page | Verify address and patient record fields
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle>" page is displayed
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader    |
      | Address        |
      | Address line 2 |
      | Address line 3 |
      | Address line 5 |
      | Postcode       |
    Then the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    Then the patient is successfully created with a message "Details saved"
    When the user clicks the - "Go back to patient search" - link
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned

    Examples:
      | hyperlinkText               | pageTitle                         | reason_for_no_nhsNumber     | patient-type|
      | create a new patient record | Add a new patient to the database | Other - provide explanation | NGIS        |


  @NTS-3513 @E2EUI-849 @LOGOUT @P0 @v_1
  Scenario Outline:NTS-3513: New Patient Page - User journey when Clinical Indication has not been selected
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the clinical indication ID missing banner is displayed
    And the message displayed on the notification banner is "You need to add a Clinical Indication from the Test Directory before you can start a new referral."
    And the Start New Referral button is disabled
    When the user clicks the "Test Directory" link on the notification banner
    Then the Test Directory homepage is displayed
    #    User is navigated back to test-directory to search and select  Ci for the patient and start a referral
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | GEL_NORMAL_USER |
    Then the "<pageTitle2>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    When the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"
    And the user clicks the Start Referral button
    And the referral page is displayed

    Examples:
      | pageTitle                         | pageTitle2        | reason_for_no_nhsNumber     | patient-search-type |
      | Add a new patient to the database | Find your patient | Other - provide explanation | NGIS                |

  @NTS-3516 @E2EUI-1056 @LOGOUT @v_1
  Scenario Outline: NTS-3516:Normal-User - Create a new non-NHS patient record to Verify the mandatory input field validations with a Life status and all mandatory fields left blank
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader       |
      | First name ✱      |
      | Last name ✱       |
      | Date of birth ✱   |
      | Gender ✱          |
      | Life status ✱     |
      | Ethnicity ✱       |
      | Hospital number ✱ |

    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader   |
      | Title         |
      | Date of death |
      | Address       |
      | Postcode      |
    When the user select the life status "<lifeStatus>"
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                    | errorMessageHeader                  | messageColourHeader |
      | First name ✱                   | First name is required.             | #dd2509             |
      | Last name ✱                    | Last name is required.              | #dd2509             |
      | Date of birth ✱                | Date of birth is required.          | #dd2509             |
      | Gender ✱                       | Gender is required.                 | #dd2509             |
      | Ethnicity ✱                    | Ethnicity is required.              | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number | #dd2509             |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |

    Examples:
      | pageTitle                         | lifeStatus |
      | Add a new patient to the database | Deceased   |


  @NTS-3517 @E2EUI-891 @LOGOUT @v_1
  Scenario Outline: NTS-3517: Normal-User: Verify the mandatory input field validations for non-NHS patient creation when invalid DOB is entered and all mandatory fields left blank
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader                    |
      | First name ✱                   |
      | Last name ✱                    |
      | Date of birth ✱                |
      | Gender ✱                       |
      | Life status ✱                  |
      | Ethnicity ✱                    |
      | Reason NHS Number is missing ✱ |
      | Hospital number ✱              |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader   |
      | Title         |
      | Date of death |
      | Address       |
      | Postcode      |
    And the user fills in the date of birth "<InvalidDateOfBirth>"
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader                    | errorMessageHeader                  | messageColourHeader |
      | First name ✱                   | First name is required.             | #dd2509             |
      | Last name ✱                    | Last name is required.              | #dd2509             |
      | Date of birth ✱                | Date of birth is required.          | #dd2509             |
      | Gender ✱                       | Gender is required.                 | #dd2509             |
      | Life status ✱                  | Life status is required.            | #dd2509             |
      | Ethnicity ✱                    | Ethnicity is required.              | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number | #dd2509             |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |

    Examples:
      | pageTitle                         | InvalidDateOfBirth |
      | Add a new patient to the database | Jan012020          |

# Ethnicity is now Mandatory
  @NTS-4500 @LOGOUT @v_1 @E2EUI-2499
  Scenario Outline: NTS-4500-Ethnicity - Create New Patient Page - Lookup an existing NGIS patient – NHSNo = Yes
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    When the user deletes the content of the Ethnicity field
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader | errorMessageHeader     | messageColourHeader |
      | Ethnicity ✱ | Ethnicity is required. | #dd2509             |
    When the user fills in the Ethnicity field "B - White - Irish"
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"

    Examples:
      | pageTitle                         |
      | Add a new patient to the database |


 @NTS-4541 @LOGOUT @v_1 @E2EUI-1753
  Scenario Outline: NTS-4541 -New patient be created without filling in the non-mandatory Title field
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user fills in all the mandatory fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"

    Examples:
      | pageTitle                         | reason_for_no_nhsNumber     |
      | Add a new patient to the database | Other - provide explanation |


  @NTS-4745 @E2EUI-821 @LOGOUT
  Scenario Outline: NTS-4745: Normal User :Create a new non-NHS patient record to Verify the mandatory input field validations with  valid Forename and all mandatory fields left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    When the user fills in invalid patient details "<searchDetails>" in the search fields when No is selected
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle2>" page is displayed
    And the user deletes the pre-populated fields - First Name, Last Name, Date of Birth, Gender, and PostCode
    And the user fill in the first name field
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle2>" page are displayed as follows
      | labelHeader                    | errorMessageHeader                  | messageColourHeader |
      | Last name ✱                    | Last name is required.              | #dd2509             |
      | Date of birth ✱                | Date of birth is required.          | #dd2509             |
      | Gender ✱                       | Gender is required.                 | #dd2509             |
      | Life status ✱                  | Life status is required.            | #dd2509             |
      | Ethnicity ✱                    | Ethnicity is required.              | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number | #dd2509             |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |

    Examples:
      | pageTitle         | searchDetails                                                            | message          | hyperlinkText               | pageTitle2                        |
      | Find your patient | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found | create a new patient record | Add a new patient to the database |



  @NTS-4745 @E2EUI-821 @LOGOUT
  Scenario Outline: NTS-4745:Super User :Create a new non-NHS patient record to Verify the mandatory input field validations with  valid Forename and all mandatory fields left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    When the user fills in invalid patient details "<searchDetails>" in the search fields when No is selected
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle2>" page is displayed
    And the user deletes the pre-populated fields - First Name, Last Name, Date of Birth, Gender, and PostCode
    And the user fill in the first name field
    And the user click YES button for the question - Do you have the NHS no?
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle2>" page are displayed as follows
      | labelHeader       | errorMessageHeader           | messageColourHeader |
      | Last name ✱       | Last name is required.       | #dd2509             |
      | Date of birth ✱   | Date of birth is required.   | #dd2509             |
      | Gender ✱          | Gender is required.          | #dd2509             |
      | Life status ✱     | Life status is required.     | #dd2509             |
      | Ethnicity ✱       | Ethnicity is required.       | #dd2509             |
      | NHS Number ✱      | NHS Number is required.      | #dd2509             |
      | Hospital number ✱ | Hospital number is required. | #dd2509             |

    Examples:
      | pageTitle         | searchDetails                                                            | message          | hyperlinkText               | pageTitle2                        |
      | Find your patient | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found | create a new patient record | Add a new patient to the database |


   @NTS-4754 @E2EUI-1380 @LOGOUT
  Scenario Outline: NTS-4754: Normal User :Create a new non-NHS patient record to Verify the mandatory input field validations with valid Gender and all mandatory fields left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    When the user fills in invalid patient details "<searchDetails>" in the search fields when No is selected
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle2>" page is displayed
    And the user deletes the pre-populated fields - First Name, Last Name, Date of Birth, Gender, and PostCode
    When the user select the gender "<gender>"
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle2>" page are displayed as follows
      | labelHeader                    | errorMessageHeader                  | messageColourHeader |
      | First name ✱                   | First name is required.             | #dd2509             |
      | Last name ✱                    | Last name is required.              | #dd2509             |
      | Date of birth ✱                | Date of birth is required.          | #dd2509             |
      | Life status ✱                  | Life status is required.            | #dd2509             |
      | Ethnicity ✱                    | Ethnicity is required.              | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number | #dd2509             |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |

    Examples:
      | pageTitle         | searchDetails                                                            | message          | hyperlinkText               | pageTitle2                        | gender  |
      | Find your patient | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found | create a new patient record | Add a new patient to the database | Unknown |


   @NTS-4754 @E2EUI-1380 @LOGOUT
  Scenario Outline: NTS-4754:Super User :Create a new non-NHS patient record to Verify the mandatory input field validations with valid Gender and all mandatory fields left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER|
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    When the user fills in invalid patient details "<searchDetails>" in the search fields when No is selected
    And the user clicks the Search button
    Then the message  "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "<pageTitle2>" page is displayed
    And the user deletes the pre-populated fields - First Name, Last Name, Date of Birth, Gender, and PostCode
    When the user select the gender "<gender>"
    And the user click YES button for the question - Do you have the NHS no?
    And the user clicks the Save patient details to NGIS button
    Then the error messages for the mandatory fields on the "<pageTitle2>" page are displayed as follows
      | labelHeader       | errorMessageHeader           | messageColourHeader |
      | First name ✱      | First name is required.      | #dd2509             |
      | Last name ✱       | Last name is required.       | #dd2509             |
      | Date of birth ✱   | Date of birth is required.   | #dd2509             |
      | Life status ✱     | Life status is required.     | #dd2509             |
      | Ethnicity ✱       | Ethnicity is required.       | #dd2509             |
      | NHS Number ✱      | NHS Number is required.      | #dd2509             |
      | Hospital number ✱ | Hospital number is required. | #dd2509             |


    Examples:
      | pageTitle         | searchDetails                                                            | message          | hyperlinkText               | pageTitle2                        | gender  |
      | Find your patient | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found | create a new patient record | Add a new patient to the database | Unknown |



