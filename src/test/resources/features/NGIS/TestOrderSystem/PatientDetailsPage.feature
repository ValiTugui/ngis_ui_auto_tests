@regression
@regression_set1
@patientDetails
Feature: Patient details page

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_01 @NTS-3068 @E2EUI-1182 @E2EUI-1463 @P0 @v_1 @BVT_P0
  Scenario Outline: NTS-3068:Existing "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with with NHS-Number
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the correct details of the "<patient-search-type>" are displayed in patient details

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_02 @NTS-3068 @E2EUI-1182 @P0 @v_1 @BVT_P0
  Scenario Outline: NTS-3068:Existing "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with without NHS-Number
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the correct details of the "<patient-search-type>" are displayed in patient details

    Examples:
      | patient-search-type | SearchDetails                                                            |
      | NHS Spine           | DOB=23-03-2011:FirstName=Nelly:LastName=Stambukdelifschitz:Gender=Female |

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_03 @v_1
  Scenario Outline: The user can return to the patient search page by clicking the Back link
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    When the user clicks the - Go back to patient search - link
    Then the Patient Search page is displayed

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_04 @NTS-3067 @E2EUI-1128 @P0 @v_1 @BVT_P0
  Scenario Outline:NTS-3067:The user can not create a referral for a newly created patient without a clinical indication test selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    And the clinical indication ID missing banner is displayed
    And the Start Referral button is disabled

    Examples:
      | hyperlinkText               | reason_for_no_nhsNumber       |
      | create a new patient record | Patient is a foreign national |

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_05 @NTS-3067 @E2EUI-1128 @P0 @v_1
  Scenario Outline:NTS-3067:The user can not create a referral for an existing patient without a clinical indication test selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And a web browser is at the Patient Details page of a "<patient-search-type>" patient with NHS number "<NhsNumber>" and Date of Birth "<DOB>" without clinical indication test selected
    And the clinical indication ID missing banner is displayed
    And the Start Referral button is disabled

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_06 @v_1
  Scenario Outline: The user can navigate to Test Directory from the notification banner on patient details page when a clinical indication is not selected
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And a web browser is at the Patient Details page of a "<patient-search-type>" patient with NHS number "<NhsNumber>" and Date of Birth "<DOB>" without clinical indication test selected
    And the clinical indication ID missing banner is displayed
    When the user clicks the Test Directory link from the notification banner
    Then the Test Directory homepage is displayed

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |


  @COMP2_TO_PatientDetails @LOGOUT
    @patientDetails_07 @NTS-3101 @E2EUI-2147 @P0 @v_1 @BVT_P0
  Scenario Outline: NTS-3101:A normal user cannot edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Given a web browser is logged in as a "GEL_NORMAL_USER" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the NHS number field is disabled

    Examples:
      | patient-search-type |
      | NGIS                |
      | NHS Spine           |

  @COMP2_TO_PatientDetails @LOGOUT
    @patientDetails_08 @NTS-3101 @E2EUI-2146 @P0 @BVT_P0
  Scenario Outline: NTS-3101:A super-user can edit or add into the NHS number field from the patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    Given a web browser is logged in as a "GEL-ops" user at the Patient Details page of a "<patient-search-type>" with valid details of NHS number and DOB
    Then the NHS number field is enabled

    Examples:
      | patient-search-type |
      | NGIS                |

  @COMP2_TO_PatientDetails @LOGOUT
    @patientDetails_09 @NTS-3151 @E2EUI-1047 @PO @v_1
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

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_10 @E2EUI-1364 @NTS-3173 @PO @v_1 @COMP2_TO_PatientDetails
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

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_11 @NTS-3346 @E2EUI-995 @P0 @v_1
  Scenario Outline: NTS-3346 - Patient Details - Page Layout - Verify enum values in Ethnicity dropdown
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL-normal-user |
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the Ethnicity drop-down is allowed to have values up to "<maximumAllowedValues>"

    Examples:
      | patient-search-type | NhsNumber  | DOB        | maximumAllowedValues |
      | NHS Spine           | 9449310602 | 23-03-2011 | 50                   |

  @COMP2_TO_PatientDetails @LOGOUT
  @patientDetails_12 @NTS-3438 @E2EUI-1511 @E2EUI-1128
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
    Then the patient is successfully updated with a "<notification>"
#   Navigate back to patient search, to search for the patient details and verify edited details
    When the user clicks the - Go back to patient search - link
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


  @COMP2_TO_PatientDetails @LOGOUT
    @patientDetails_13 @E2EUI-893 @NTS-3454
  Scenario Outline: NTS-3454: Verify the elements and mandatory fields on patient detail page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    Then the "<pageTitle>" page is displayed
    And the patient detail page displays expected input-fields and drop-down fields
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader     |
      | First name ✱    |
      | Last name ✱     |
      | Date of birth ✱ |
      | Gender ✱        |
      | Life status ✱   |

    Examples:
      | patient-search-type | NhsNumber  | DOB        | pageTitle                    |
      | NHS Spine           | 9449310602 | 23-03-2011 | Check your patient's details |


  @COMP2_TO_PatientDetails @LOGOUT
    @patientDetails_14 @NTS-3470 @E2EUI-1538
  Scenario Outline: NTS-3470:Test Order - Patient details page - Patient details update message
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - Go back to patient search - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Update NGIS record button
    Then the patient is successfully updated with a "Details saved"

    Examples:
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber     | patient-search-type | gender | lifeStatus | ethnicity                              |
      | create a new patient record | Find your patient | Other - provide explanation | NGIS                | Other  | Deceased   | G - Mixed - Any other mixed background |


  @COMP2_TO_PatientDetails @LOGOUT
    @patientDetails_15 @NTS-3470 @E2EUI-1538
  Scenario Outline: NTS-3470: Referral Component - Patient details Page - Patient details update message
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Save and Continue button
    Then the patient is successfully updated with a "Patient details updated"

    Examples:
      | stage           | gender  | lifeStatus | ethnicity   |
      | Patient details | Unknown | Deceased   | R - Chinese |


  @COMP2_TO_PatientDetails @LOGOUT
    @patientDetails_16 @NTS-3848 @E2EUI-1609
  Scenario Outline: NTS-3848: Verifying the sub-heading on patient details page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - Go back to patient search - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the sub-heading title is displayed "Patient details entered here will be added to NGIS only. Contact the patient's GP to have their details updated to NHS Spine."


    Examples:
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber     | patient-search-type | gender | lifeStatus | ethnicity                              |
      | create a new patient record | Find your patient | Other - provide explanation | NGIS                | Other  | Deceased   | G - Mixed - Any other mixed background |
