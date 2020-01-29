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
        | Add a new patient to the database | Find your patient | NGIS            |


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
