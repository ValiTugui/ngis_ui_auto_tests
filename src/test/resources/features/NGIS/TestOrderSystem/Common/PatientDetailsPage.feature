@regression
@TO_Common
@patientDetails

Feature: Patient details page

    @NTS-3068 @E2EUI-1182 @E2EUI-1463 @LOGOUT @v_1 @BVT_P0
    Scenario Outline: NTS-3068: New "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with with NHS-Number
      Given a web browser is at create new patient page
        | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
      Then the "<pageTitle>" page is displayed
      And the No button is selected by default for the question - Do you have the NHS Number?
      When the user click YES button for the question - Do you have the NHS no?
      When the user fills in all the fields with NHS number on the New Patient page
      And the user clicks the Save patient details to NGIS button
      Then the patient is successfully created with a message "Details saved"
      And the user clicks the - "Go back to patient search" - link
      Then the "<pageTitle2>" page is displayed
      And the YES button is selected by default on patient search
      And the user types in the details of the NGIS patient in the NHS number and DOB fields
      And the user clicks the Search button
      Then a "<patient-search-type>" result is successfully returned
      And the user clicks the patient result card
      Then the Patient Details page is displayed
      And the correct details of the "<patient-search-type>" are displayed in patient details

      Examples:
        | pageTitle                         | pageTitle2        | patient-search-type |
        | Add a new patient to the database | Find your patient | NGIS                |


    @NTS-3068 @E2EUI-1182 @LOGOUT @v_1 @BVT_P0
    Scenario Outline: NTS-3068:Existing "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with without NHS-Number
      Given a web browser is at create new patient page
        | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
      Then the "<pageTitle>" page is displayed
      When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
      And the user clicks the - "Go back to patient search" - link
      Then the "<pageTitle2>" page is displayed
      And the YES button is selected by default on patient search
      And the user clicks the NO button
      And the user search for the new patient using date of birth, first name, last name and gender
      And the user clicks the Search button
      Then a "<patient-search-type>" result is successfully returned
      And the user clicks the patient result card
      Then the Patient Details page is displayed
      And the correct details of the "<patient-search-type>" are displayed in patient details

      Examples:
        | pageTitle                         | pageTitle2        | reason_for_no_nhsNumber     | patient-search-type |
        | Add a new patient to the database | Find your patient | Other - provide explanation | NGIS                |



  @NTS-3068 @E2EUI-1182 @LOGOUT @v_1
  Scenario Outline: NTS-3068: The user can return to the patient search page by clicking the Back link
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user clicks the - "Go back to patient search" - link
    Then the Patient Search page is displayed

    Examples:
      | pageTitle                         |
      | Add a new patient to the database |


  @NTS-3067 @E2EUI-1128 @LOGOUT @v_1 @BVT_P0
  Scenario Outline:NTS-3067:The user can not create a referral for a newly created patient without a clinical indication test selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    And the clinical indication ID missing banner is displayed
    And the Start New Referral button is disabled

    Examples:
      | hyperlinkText               | reason_for_no_nhsNumber       |
      | create a new patient record | Patient is a foreign national |


  @NTS-3067 @E2EUI-1128 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3067: The user can navigate to Test Directory from the notification banner on patient details page when a clinical indication is not selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    And the clinical indication ID missing banner is displayed
    When the user clicks the "Test Directory" link on the notification banner
    Then the Test Directory homepage is displayed

    Examples:
      | hyperlinkText               | reason_for_no_nhsNumber       |
      | create a new patient record | Patient is a foreign national |



  @NTS-3101 @E2EUI-2147 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: NTS-3101:A normal user cannot edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Given a web browser is logged in as a "GEL_NORMAL_USER" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the Patient Details page is displayed
    Then the NHS number field is disabled

    Examples:
      | patient-search-type |
      | NGIS                |
      | NHS Spine           |

  @NTS-3101 @E2EUI-2146 @LOGOUT @BVT_P0
  Scenario Outline: NTS-3101:A super-user can edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    Given a web browser is logged in as a "GEL-ops" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the Patient Details page is displayed
    Then the NHS number field is enabled

    Examples:
      | patient-search-type |
      | NGIS                |

  @NTS-3151 @E2EUI-1047 @LOGOUT @PO @v_1
  Scenario Outline: NTS-3151:'Completed and ongoing referrals' should display the details only with respect to the concerned patient
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
    And the user click on the referral card on patient details page to navigate to referral page
    And the "<patient-search-type>" patient details searched for are the same in the referral header bar

    Examples:
      | patient-search-type | stage1          |
      | NGIS                | Patient details |

  @NTS-3173 @E2EUI-1364 @LOGOUT @PO @v_1
  Scenario Outline: NTS-3173 - Patient Details page - navigation to the Responsible clinician page from the Test Package page
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
    And the user click on the referral card on patient details page to navigate to referral page
    And the "<patient-search-type>" patient details searched for are the same in the referral header bar
    And the referral page is displayed
    And the "<stage1>" stage is marked as Completed
    And the user navigates to the "<stage2>" stage
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    And the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<stage2>" stage is marked as Completed
    And the user navigates to the "<stage3>" stage
    And the user selects the "<priority>"
    And the user clicks the Save and Continue button
    And the "<stage3>" stage is marked as Completed
    And the "<stage4>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage3>"

    Examples:
      | patient-search-type | stage1          | stage2                  | ordering_entity_name | stage3       | priority | stage4                | number_of |
      | NGIS                | Patient details | Requesting organisation | Maidstone            | Test package | Routine  | Responsible clinician | 1         |

  @NTS-3346 @E2EUI-995 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-3346 - Patient Details - Page Layout - Verify enum values in Ethnicity dropdown
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the Ethnicity drop-down is allowed to have values up to "<maximumAllowedValues>"

    Examples:
      | pageTitle                         | maximumAllowedValues |
      | Add a new patient to the database | 50                   |

  @NTS-3438 @E2EUI-1511 @E2EUI-1128 @LOGOUT
  Scenario Outline: NTS-3438 - Patient Details page - Update patient details - Life Status, Gender and Ethnicity and verify in patient records
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "<notification>"
#   Navigate back to patient search, to search for the patient details and verify edited details
    When the user clicks the - "Go back to patient search" - link
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and edited gender "<gender>"
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    When the user clicks the patient result card
    Then the Patient Details page is displayed
    And the newly edited patient's Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" are displayed in Patient Details page
#    Navigate back to referral page
    And the user navigates back to patient existing referral page
      | APP_URL | patient-details|
    And the referral page is displayed
    And the new patient gender "<gender>" is displayed on the referral banner

    Examples:
      | stage           | patient-search-type | gender | lifeStatus | ethnicity         | notification  |
      | Patient details | NGIS                | Female | Deceased   | B - White - Irish | Details saved |

  @NTS-3454 @E2EUI-893 @LOGOUT
  Scenario Outline: NTS-3454: Verify the elements and mandatory fields on patient detail page
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the patient detail page displays expected input-fields and drop-down fields
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader     |
      | First name ✱    |
      | Last name ✱     |
      | Date of birth ✱ |
      | Gender ✱        |
      | Life status ✱   |

    Examples:
      | pageTitle                         | pageTitle2        | patient-search-type | reason_for_no_nhsNumber       |
      | Add a new patient to the database | Find your patient | NGIS                | Patient is a foreign national |


  @NTS-3470 @E2EUI-1538 @LOGOUT
  Scenario Outline: NTS-3470:Test Order - Patient details page - Patient details update message
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"

    Examples:
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber     | patient-search-type | gender | lifeStatus | ethnicity                              |
      | create a new patient record | Find your patient | Other - provide explanation | NGIS                | Other  | Deceased   | G - Mixed - Any other mixed background |

  @NTS-3470 @E2EUI-1538 @LOGOUT
  Scenario Outline: NTS-3470: Referral Component - Patient details Page - Patient details update message
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Save and Continue button
    Then the patient is successfully updated with a message "Patient details updated"

    Examples:
      | stage           | gender  | lifeStatus | ethnicity   |
      | Patient details | Unknown | Deceased   | R - Chinese |



  @NTS-3848 @E2EUI-1609 @LOGOUT
  Scenario Outline: NTS-3848: Verifying the sub-heading on patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the sub-heading title is displayed "Patient details entered here will be added to NGIS only. Contact the patient's GP to have their details updated to NHS Spine."


    Examples:
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber     | patient-search-type |
      | create a new patient record | Find your patient | Other - provide explanation | NGIS                |


  @NTS-3513 @E2EUI-849 @LOGOUT @P0 @v_1
  Scenario Outline:NTS-3513: Patient-detail page - User journey when Clinical Indication has not been selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the clinical indication ID missing banner is displayed
    And the message displayed on the notification banner is "You need to add a Clinical Indication from the Test Directory before you can start a new referral."
    And the Start Referral button is disabled
    When the user clicks the "Test Directory" link on the notification banner
    Then the Test Directory homepage is displayed
#    User is navigated back to test-directory to search and select  Ci for the patient and start a referral
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
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
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber     | patient-search-type |
      | create a new patient record | Find your patient | Other - provide explanation | NGIS                |

    # Ethnicity is now Mandatory
  @NTS-4500 @LOGOUT @v_1 @E2EUI-2499
  Scenario Outline: NTS-4500-Ethnicity - Patient Detail Page - Lookup an existing NGIS patient – NHSNo = Yes
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    When the user deletes the content of the Ethnicity field
#    And the user clicks the Update NGIS record button
    Then the error messages for the mandatory fields on the "<pageTitle3>" page are displayed as follows
      | labelHeader | errorMessageHeader     | messageColourHeader |
      | Ethnicity ✱ | Ethnicity is required. | #dd2509             |
    When the user fills in the Ethnicity field "B - White - Irish"
    And the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"

    Examples:
      | pageTitle                         | pageTitle2        | pageTitle3                   | patient-search-type |
      | Add a new patient to the database | Find your patient | Check your patient's details | NGIS                |


  # Ethnicity is now Mandatory
  @NTS-4500 @LOGOUT @v_1 @E2EUI-2499
  Scenario Outline: NTS-4500-Ethnicity - Referral Component Patient Detail Page - Lookup an existing NGIS patient – NHSNo = Yes
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user deletes the content of the Ethnicity field
    Then the error messages for the mandatory fields on the "<pageTitle>" page are displayed as follows
      | labelHeader | errorMessageHeader     | messageColourHeader |
      | Ethnicity ✱ | Ethnicity is required. | #dd2509             |
    When the user fills in the Ethnicity field "B - White - Irish"
    And the user clicks the Save and Continue button
    Then the "<pageTitle2>" page is displayed

    Examples:
      | stage           | pageTitle                    | pageTitle2                    |
      | Patient details | Check your patient's details | Add a requesting organisation |


  @NTS-4503 @E2EUI-1130 @v_1 @LOGOUT
  Scenario Outline: NTS-4503 - Patient detail - NHSNumber and Hospital Number field - maximum length validation
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user deletes data in the NHS Number field
    When the user attempts to fill in the NHS Number "<NHSNumber>" with data that exceed the maximum data allowed 10
    Then the user is prevented from entering data that exceed that allowable maximum data 10 in the "NHSNumber" field
    And the user deletes the data in the Hospital Number field
    When the user attempts to fill in the Hospital Number "<HospitalNumber>" with data that exceed the maximum data allowed 15
    Then the user is prevented from entering data that exceed that allowable maximum data 15 in the "HospitalNumber" field

    Examples:
      | pageTitle                         | pageTitle2        | patient-search-type | NHSNumber        | HospitalNumber      |
      | Add a new patient to the database | Find your patient | NGIS                | 9449310602111111 | 1234567890123456789 |


  @NTS-4538 @E2EUI-1054 @E2EUI-1507 @LOGOUT @P0 @v_1
  Scenario Outline: NTS-4538 - Add patient contact(address) details to a patient
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name, gender and post-code
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the correct patient address is displayed on patient details page

    Examples:
      | pageTitle                         | pageTitle2        | patient-type | patient-search-type |
      | Add a new patient to the database | Find your patient | NGIS         | NGIS                |


   @NTS-4565 @LOGOUT @v_1 @E2EUI-1582
  Scenario Outline: NTS-4565- New Patient Page - The Patient Details page is loaded when clicking browser's Back button after starting a referral
     Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
       | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
     And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    When the user attempts to navigate away by clicking "back"
     And the page url address contains the directory-path web-page "<directoryPathPage>"
    Then the "<pageTitle>" page is displayed

    Examples:
      | stage           | pageTitle                         | directoryPathPage      |
      | Patient details | Add a new patient to the database | test-order/new-patient |


  @NTS-4565 @LOGOUT @v_1 @E2EUI-1582
  Scenario Outline: NTS-4565- Patient Detailed Page - The Patient Details page is loaded when clicking browser's Back button after starting a referral
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    When the user clicks the - "Go back to patient search" - link
    And the page url address contains the directory-path web-page "<directoryPathPage>"
    Then the "<pageTitle2>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the clinical indication ID missing banner is displayed
    And the message displayed on the notification banner is "You need to add a Clinical Indication from the Test Directory before you can start a new referral."
    And the Start Referral button is disabled
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
    And the "<stage>" stage is marked as Completed
    When the user attempts to navigate away by clicking "back"
#    Click the back button the second time due to user already click save and continue in the test "And the referral page is displayed"
    When the user attempts to navigate away by clicking "back"
    Then the "<pageTitle3>" page is displayed

    Examples:
      | stage           | pageTitle                         | pageTitle2        | pageTitle3                   | reason_for_no_nhsNumber     | patient-search-type | directoryPathPage         |
      | Patient details | Add a new patient to the database | Find your patient | Check your patient's details | Other - provide explanation | NGIS                | test-order/patient-search |



  @NTS-4627 @E2EUI-1664 @v_1 @LOGOUT
  Scenario Outline:  Patient detail - Hospital Number field - Display an editable hospital number
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "Details saved"
    And the user clicks the - "Go back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user deletes the data in the Hospital Number field
    And the Hospital number field displays the hint text "<hintText>"
    When the user attempts to fill in the Hospital Number "<HospitalNumber>" with data that exceed the maximum data allowed 15
    Then the user is prevented from entering data that exceed that allowable maximum data 15 in the "HospitalNumber" field

    Examples:
      | pageTitle                         | pageTitle2        | patient-search-type | HospitalNumber      | hintText |
      | Add a new patient to the database | Find your patient | NGIS                | 1234567890123456789 | B123456  |
