@04-GENOMIC_RECORD
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: GenomicRecord: Patient Record

  @NTS-3379 @Z-LOGOUT
#    @E2EUI-2135
  Scenario Outline: NTS-3379:(E2EUI-2135) NHS Number field validation
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Then the user is navigated to a page with title Find your patient
    And the display question for NHS Number is Do you have the patient's NHS Number?
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader     |
      | NHS Number ✱    |
      | Date of birth ✱ |
    When the user search the family member with the specified details "<InvalidNHS>"
    And the user clicks the Search button
    Then the user will see error messages highlighted in red colour
      | ErrorMessage                            | MessageColor |
      | Invalid NHS Number. Check and try again | #dd2509      |
    Examples:
      | InvalidNHS                          |
      | NHSNumber=5922721713:DOB=20-09-2008 |

  @NTS-3513 @Z-LOGOUT
#    @E2EUI-849
  Scenario Outline:NTS-3513:(E2EUI-849) New Patient Page - User journey when Clinical Indication has not been selected
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
#    And the clinical indication ID missing banner is displayed
#    And the message displayed on the notification banner is "You must add a Clinical Indication from the Test Directory to start a new referral"
#    When the user clicks the "Test Directory" link on the notification banner
#    Then the Test Directory homepage is displayed
#    User is navigated back to test-directory to search and select  Ci for the patient and start a referral
    And the Start New Referral button is enabled
    And the user clicks the Start Referral button
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | GEL_NORMAL_USER |
    Then the "<pageTitle2>" page is displayed
    And the user clicks the NO button
    And the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    When the user clicks the Start Referral button
    And the referral page is displayed

    Examples:
      | pageTitle                        | pageTitle2        | reason_for_no_nhsNumber       | patient-search-type |
      | Create a record for this patient | Find your patient | Other (please provide reason) | NGIS                |

  @NTS-3516 @Z-LOGOUT
#    @E2EUI-1056
  Scenario Outline: NTS-3516:(E2EUI-1056):Normal-User - Create a new non-NHS patient record to Verify the mandatory input field validations with a Life status and all mandatory fields left blank
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
      | pageTitle                        | lifeStatus |
      | Create a record for this patient | Deceased   |

  @NTS-3517 @Z-LOGOUT
#    @E2EUI-891
  Scenario Outline: NTS-3517:(E2EUI-891): Normal-User: Verify the mandatory input field validations for non-NHS patient creation when invalid DOB is entered and all mandatory fields left blank
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
      | Date of birth ✱                | Enter a month between 1 and 12          | #dd2509             |
      | Gender ✱                       | Gender is required.                 | #dd2509             |
      | Life status ✱                  | Life status is required.            | #dd2509             |
      | Ethnicity ✱                    | Ethnicity is required.              | #dd2509             |
      | Reason NHS Number is missing ✱ | Select the reason for no NHS Number | #dd2509             |
      | Hospital number ✱              | Hospital number is required.        | #dd2509             |

    Examples:
      | pageTitle                        | InvalidDateOfBirth |
      | Create a record for this patient | 10-13-2020          |

## Ethnicity is now Mandatory
#  @NTS-4500 @Z-LOGOUT
##   @E2EUI-2499
#  Scenario Outline: NTS-4500:(E2EUI-2499):Ethnicity - Create New Patient Page - Lookup an existing NGIS patient – NHSNo = Yes
#    Given a web browser is at create new patient page
#      | TO_PATIENT_NEW_URL | new-patient | GEL_SUPER_USER |
#    Then the "<pageTitle>" page is displayed
#    And the No button is selected by default for the question - Do you have the NHS Number?
#    When the user click YES button for the question - Do you have the NHS no?
#    And the user fills in all the fields with NHS number on the New Patient page
#    When the user deletes the content of the Ethnicity field
#
#    Examples:
#      | pageTitle                        |
#      | Create a record for this patient |

  @NTS-4541 @Z-LOGOUT
#   @E2EUI-1753
  Scenario Outline: NTS-4541:(E2EUI-1753): New patient be created without filling in the non-mandatory Title field
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user fills in all the mandatory fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "NGIS patient record created"

    Examples:
      | pageTitle                        | reason_for_no_nhsNumber       |
      | Create a record for this patient | Other (please provide reason) |

  @NTS-4745 @Z-LOGOUT
#    @E2EUI-821
  Scenario Outline: NTS-4745:(E2EUI-821): Normal User :Create a new non-NHS patient record to Verify the mandatory input field validations with  valid Forename and all mandatory fields left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    When the user fills in invalid patient details "<searchDetails>" in the search fields when No is selected
    And the user clicks the Search button
    Then the message "<message>" is displayed below the search button
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
      | pageTitle         | searchDetails                                                            | message          | hyperlinkText               | pageTitle2                       |
      | Find your patient | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found | create a new patient record | Create a record for this patient |

  @NTS-4745 @Z-LOGOUT
#    @E2EUI-821
  Scenario Outline: NTS-4745:(E2EUI-821):Super User :Create a new non-NHS patient record to Verify the mandatory input field validations with  valid Forename and all mandatory fields left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    When the user fills in invalid patient details "<searchDetails>" in the search fields when No is selected
    And the user clicks the Search button
    Then the message "<message>" is displayed below the search button
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
      | pageTitle         | searchDetails                                                            | message          | hyperlinkText               | pageTitle2                       |
      | Find your patient | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found | create a new patient record | Create a record for this patient |

  @NTS-4754 @Z-LOGOUT
#     @E2EUI-1380
  Scenario Outline: NTS-4754:(E2EUI-1380): Normal User :Create a new non-NHS patient record to Verify the mandatory input field validations with valid Gender and all mandatory fields left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    When the user fills in invalid patient details "<searchDetails>" in the search fields when No is selected
    And the user clicks the Search button
    Then the message "<message>" is displayed below the search button
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
      | pageTitle         | searchDetails                                                            | message          | hyperlinkText               | pageTitle2                       | gender  |
      | Find your patient | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found | create a new patient record | Create a record for this patient | Unknown |

  @NTS-4754 @Z-LOGOUT
#     @E2EUI-1380
  Scenario Outline: NTS-4754:(E2EUI-1380):Super User :Create a new non-NHS patient record to Verify the mandatory input field validations with valid Gender and all mandatory fields left blank
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_SUPER_USER |
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    When the user fills in invalid patient details "<searchDetails>" in the search fields when No is selected
    And the user clicks the Search button
    Then the message "<message>" is displayed below the search button
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
      | pageTitle         | searchDetails                                                            | message          | hyperlinkText               | pageTitle2                       | gender  |
      | Find your patient | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found | create a new patient record | Create a record for this patient | Unknown |

  @NTS-4760 @Z-LOGOUT
#   @E2EUI-1097
  Scenario Outline:NTS-4760:(E2EUI-1097):New patient page - The user is stopped from navigating away when mandatory fields have not been completed in new patient page
    Given a web browser is at create new patient page
      | TO_PATIENT_NEW_URL | new-patient | GEL_NORMAL_USER |
    Then the "<pageTitle>" page is displayed
    When the user fills in all the mandatory fields without NHS number and enter a reason for noNhsNumber "<reason_for_no_nhsNumber>"
     #  User click on refresh button
    When the user attempts to navigate away by clicking "<browser_exit1>"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit1>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
     #  User click on logout button
    When the user clicks the Log out button
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "<browser_exit2>" button and "<acknowledgeMessage>" it
    And the web browser is still at the same "<partialCurrentUrl1>" page
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "NGIS patient record created"

    Examples:
      | pageTitle                        | reason_for_no_nhsNumber       | browser_exit1 | partOfMessage1    | acknowledgeMessage | partialCurrentUrl1 | browser_exit2 |
      | Create a record for this patient | Other (please provide reason) | refresh       | may not be saved. | Dismiss            | new-patient        | logout        |