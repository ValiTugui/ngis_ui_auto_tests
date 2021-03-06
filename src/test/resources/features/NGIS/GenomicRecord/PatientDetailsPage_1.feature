@04-GENOMIC_RECORD
@SYSTEM_TEST
Feature: GenomicRecord: Patient details page 1

  @NTS-3068 @Z-LOGOUT
#    @E2EUI-1182 @E2EUI-1463
  Scenario Outline: NTS-3068: New "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with with NHS-Number
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the No button is selected by default for the question - Do you have the NHS Number?
    When the user click YES button for the question - Do you have the NHS no?
    When the user fills in all the fields with NHS number on the New Patient page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "NGIS patient record created"
    And the user clicks the - "Back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user types in the details of the NGIS patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    And the correct details of the "<patient-search-type>" are displayed in patient details

    Examples:
      | pageTitle                        | pageTitle2        | patient-search-type |
      | Create a record for this patient | Find your patient | NGIS                |

  @NTS-3068 @Z-LOGOUT
#    @E2EUI-1182
  Scenario Outline: NTS-3068:Existing "<patient-search-type>" patients - Verifying the Patient Details page after performing a search with without NHS-Number
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
#    @E2EUI-1182
    And the user clicks the - "Back to patient search" - link
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    And the correct details of the "<patient-search-type>" are displayed in patient details

    Examples:
      | pageTitle                        | pageTitle2        | reason_for_no_nhsNumber       | patient-search-type |
      | Create a record for this patient | Find your patient | Other (please provide reason) | NGIS                |

  @NTS-3438 @Z-LOGOUT
#    @E2EUI-1511 @E2EUI-1128
#    @NTS-3848 @E2EUI-1609
  Scenario Outline: NTS-3438:(E2EUI-1511,1128): Patient Details page - Update patient details - Life Status, Gender and Ethnicity and verify in patient records
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
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
    And the user clicks on edit patient details
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Save and Continue button on Patient details page
    Then the patient is successfully updated with a message "<notification>"
    When the user clicks the - "Back to patient search" - link
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and edited gender "<gender>"
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    When the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
##    @NTS-3848 @E2EUI-1609
    And the sub-heading title is displayed "Details entered here will be added to NGIS only, not NHS Spine."
    And the newly edited patient's Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" are displayed in Patient Details page
    ###Navigate back to referral page
    ##Below steps no more valid as per Hanna Release, need to replace
#    And the user navigates back to patient existing referral page
#      | APP_URL | patient-details |
#    And the referral page is displayed
#    And the new patient gender "<gender>" is displayed on the referral banner
    Examples:
      | stage           | patient-search-type | gender | lifeStatus | ethnicity         | notification            |
      | Patient details | NGIS                | Female | Deceased   | B - White - Irish | Patient details updated |

  @NTS-3470 @Z-LOGOUT
#    @E2EUI-1538
  Scenario Outline: NTS-3470:E2EUI-1538:Test Order - Patient details page - Patient details update message
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the - "Back to patient search" - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Save and Continue button on Patient details page
    Then the patient is successfully updated with a message "Patient details updated"

    Examples:
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber       | patient-search-type | gender | lifeStatus | ethnicity                              |
      | create a new patient record | Find your patient | Other (please provide reason) | NGIS                | Other  | Deceased   | G - Mixed - Any other mixed background |

  @NTS-3470 @Z-LOGOUT
#    @E2EUI-1538
  Scenario Outline: NTS-3470:E2EUI-1538: Referral Component - Patient details Page - Patient details update message
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the user navigates to the "<stage>" stage
    And the "<stage>" stage is marked as Completed
    And the user edit the patients Gender "<gender>", Life Status "<lifeStatus>" and Ethnicity "<ethnicity>" fields
    And the user clicks the Save and Continue button
    Then the patient is successfully updated with a message "Patient details updated"

    Examples:
      | stage           | gender  | lifeStatus | ethnicity   |
      | Patient details | Unknown | Deceased   | R - Chinese |