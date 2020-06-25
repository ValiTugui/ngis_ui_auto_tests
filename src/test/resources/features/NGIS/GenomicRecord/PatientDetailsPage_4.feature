@patientDetails
@04-GENOMIC_RECORD
@SYSTEM_TEST
Feature: GenomicRecord: Patient details page 4

  @NTS-3173 @Z-LOGOUT
#    @E2EUI-1364
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

  @NTS-4760 @Z-LOGOUT
#   @E2EUI-1097
  Scenario Outline: NTS-4760:E2EUI-1097: The user is stopped from navigating away when mandatory fields have not been completed in new patient page
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks the - "Back to patient search" - link
    And the page url address contains the directory-path web-page "<directoryPathPage>"
    Then the "<pageTitle2>" page is displayed
    And the YES button is selected by default on patient search
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
      #  User click on refresh button
    And the user clicks on edit patient details
    And the user clears the date of birth field
    When the user attempts to navigate away by clicking "<browser_exit1>"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit1>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
     #  User click on logout button
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit2>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
#    fill in date of birth
    And the user fills in the date of birth "01/03/2010"
    And the user clicks the Save and Continue button on Patient details page
    Then the patient is successfully created with a message "Patient details updated"

    Examples:
      | pageTitle                        | pageTitle2        | patient-search-type | reason_for_no_nhsNumber                                     | directoryPathPage         | browser_exit1 | partOfMessage1    | acknowledgeMessage | partialCurrentUrl1 | browser_exit2 |
      | Create a record for this patient | Find your patient | NGIS                | Patient not eligible for NHS number (e.g. foreign national) | test-order/patient-search | refresh       | may not be saved. | Dismiss            | patient            | logout        |

  @NTS-4760 @Z-LOGOUT
#   @E2EUI-1097
  Scenario Outline:NTS-4760:E2EUI-1097:Referral-Patient Detail Page - The user is stopped from navigating away when mandatory fields have not been completed in new patient page
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    When the user navigates to the "<stage>" stage
    Then the "<stage>" stage is selected
    And the "<stage>" stage is marked as Completed
    #  User click on refresh button
    And the user clears the date of birth field
    When the user attempts to navigate away by clicking "<browser_exit1>"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit1>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
     #  User click on logout button
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit2>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    #  fill in date of birth
    And the user fills in the date of birth "<dateOfBirth>"
    And the user clicks the Save and Continue button
    Then the patient is successfully updated with a message "Patient details updated"

    Examples:
      | stage           | dateOfBirth | browser_exit1 | partOfMessage1    | acknowledgeMessage | partialCurrentUrl1 | browser_exit2 |
      | Patient details | 20/10/2010  | refresh       | may not be saved. | Dismiss            | patient            | logout        |

  @NTS-4055 @Z-LOGOUT
#    @E2EUI-1904
  Scenario Outline: NTS-4055:E2EUI-1904: The patient record should be saved without entering "DATE OF DEATH", As a user I want to save a new patient record without date of death.
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the user is navigated to a page with title Find your patient
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the message "No patient found" is displayed below the search button
    When the user clicks on the hyper link
    Then the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol in the current page
      | labelHeader   |
      | Date of death |
    And the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    Examples:
      | reason_for_no_nhsNumber                                     |
      | Patient not eligible for NHS number (e.g. foreign national) |

  @NTS-3557 @Z-LOGOUT
#  @E2EUI-1809 @scenario_1
  Scenario: NTS-3557:E2EUI-1809-scenario_1: Validating cancel button present when login as super user
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R61 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_SUPER_USER |
    When the user is navigated to a page with title Add a requesting organisation
    Then the user should be able to see a cancel referral link "present"

  @NTS-3557 @Z-LOGOUT
#  @E2EUI-1809 @scenario_2
  Scenario: : NTS-3557:E2EUI-1809-scenario_2 Validating cancel button present when login as normal user
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R84 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    When the user is navigated to a page with title Add a requesting organisation
    Then the user should be able to see a cancel referral link "not present"