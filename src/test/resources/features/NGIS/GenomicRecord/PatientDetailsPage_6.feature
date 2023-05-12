@04-GENOMIC_RECORD
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: GenomicRecord: Patient details page 6

  @NTS-6155 @Z-LOGOUT
  Scenario Outline: NTS-6155 - Rollout: Multi input date - Pre-referral patient detailsage
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
    And the user fills in the date of birth "<dateOfBirth2>"
    Then the message will be displayed as "<error_message2>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth3>"
    Then the message will be displayed as "<error_message3>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth4>"
    Then the message will be displayed as "<error_message4>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth5>"
    Then the message will be displayed as "<error_message5>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth6>"
    Then the message will be displayed as "<error_message6>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth7>"
    Then the message will be displayed as "<error_message7>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth8>"
    Then the message will be displayed as "<error_message8>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth9>"
    Then the message will be displayed as "<error_message9>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth10>"
    Then the message will be displayed as "<error_message10>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    Then the message will be displayed as "<error_message11>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    And the user fills in the birthday "<Birthday>"
    Then the Day field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message12>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    And the user fills in the birthday "<Birthday_1>"
    Then the Day field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message13>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    And the user fills in the birthyear "<Birthyear_3>"
    Then the year field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message15>" in "#dd2509" color for the DOB field

    Examples:

      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber       | patient-search-type | dateOfBirth2 | error_message2 | dateOfBirth3 | error_message3 | dateOfBirth4 | error_message4 | dateOfBirth5 | error_message5             | dateOfBirth6 | error_message6               | dateOfBirth7 | error_message7                 | dateOfBirth8 | error_message8                    | dateOfBirth9 | error_message9          | dateOfBirth10 | error_message10                  | error_message11            | Birthday | error_message12            | Birthday_1 | error_message13                | Birthyear_3 | error_message15 |
      | create a new patient record | Find your patient | Other (please provide reason) | NGIS                | a-01-2011    | Enter a day    | 01-b-2011    | Enter a month  | 01-01-c      | Enter a year   | a-b-c        | Date of birth is required. | 33-09-1990   | Enter a day between 1 and 31 | 12-15-2000   | Enter a month between 1 and 12 | 29-02-2002   | Check the day and month are valid | 01-01-1899   | Enter a year after 1900 | 10-10-2030    | Please enter a date before today | Date of birth is required. | AASFGWE  | Date of birth is required. | ABC12d34ef | Enter a month between 1 and 12 | 200074      | Enter a day     |

  @NTS-6343 @Z-LOGOUT
  Scenario Outline: NTS-6343 - Rollout: Multi input date - Pre-referral patient detailsage
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | None | GEL_SUPER_USER |
    And the user navigates to the "<stage1>" stage
    And the "<stage1>" stage is marked as Completed
    And the user view the day field
    Then the user clicks in the birthday field in patient details page
    And the user view the month field
    Then the user clicks in the birthmonth in patient details page
    And the user view the year field
    Then the user clicks in the birthyear in patient details page
    When the user navigates back to patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    And the YES button is selected by default on patient search
    And the user view the day field
    Then the user clicks in the birthday field
    And the user view the month field
    Then the user clicks in the birthmonth
    And the user view the year field
    Then the user clicks in the birthyear
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    And the user clicks on edit patient details
    And the user view the day field
    Then the user clicks in the birthday field in patient details page
    And the user view the month field
    Then the user clicks in the birthmonth in patient details page
    And the user view the year field
    Then the user clicks in the birthyear in patient details page
    When the user clicks the Save and Continue button on Patient details page
    Then the patient is successfully updated with a message "Patient details updated"

    Examples:
      | stage1          | patient-search-type | NhsNumber  | DOB        |
      | Patient details | NGIS                | 2000004083 | 06-10-2011 |

  @NTS-6343 @Z-LOGOUT
  Scenario Outline:  NTS-6343 - Scenario3
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1971:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user view the day field
    Then the user clicks in the birthday field
    And the user view the month field
    Then the user clicks in the birthmonth
    And the user view the year field
    Then the user clicks in the birthyear
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user view the day field
    Then the user clicks in the birthday field in patient details page
    And the user view the month field
    Then the user clicks in the birthmonth in patient details page
    And the user view the year field
    Then the user clicks in the birthyear in patient details page

    Examples:
      | stage          | FamilyMemberDetails                 |
      | Family members | NHSNumber=2000004326:DOB=08-10-2011 |

  @NTS-6343 @Z-LOGOUT
  Scenario Outline:  NTS-6343 - Scenario4
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1971:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user can see a message "<SearchDetails>" "<PatientSearchMessage>" in "bold" font
    And the user clicks on the hyper link
    Then the user is navigated to a page with title Create a record for this family member
    And the user view the day field
    Then the user clicks in the birthday field in patient details page
    And the user view the month field
    Then the user clicks in the birthmonth in patient details page
    And the user view the year field
    Then the user clicks in the birthyear in patient details page


    Examples:
      | stage          | SearchDetails                                               | PatientSearchMessage |
      | Family members | DOB=23-03-2011:FirstName=john:LastName=Michel:Gender=Female | No patient found     |

  @NTS-6343 @Z-LOGOUT
  Scenario Outline: NTS-3165:E2EUI-953:Tumours page layout
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the tumours stage displays Add a tumour page with appropriate fields - description, Date of diagnosis etc
    And the user view the day field
    Then the user clicks in the dateOfDiagnosis day field
    And the user view the month field
    Then the user clicks in the dateOfDiagnosis month field
    And the user view the year field
    Then the user clicks in the dateOfDiagnosis year field
    When the user navigates to the "<Samples>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType-non-tumour>", sample state and filling SampleID
    And the user clicks the Save and Continue button
    Then the "<pageTitle3>" page is displayed
    And the user view the day field
    Then the user clicks in the Sample Details day field
    And the user view the month field
    Then the user clicks in the Sample Details month field
    And the user view the year field
    Then the user clicks in the Sample Details year field
    Examples:
      | stage   | Samples | pageTitle      | pageTitle2   | pageTitle3         | sampleType-non-tumour     |
      | Tumours | Samples | Manage samples | Add a sample | Add sample details | Normal or germline sample |

  @NTS-6343 @Z-LOGOUT
  Scenario Outline:  NTS-6343 - Scenario5
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1992:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option <option1> in patient choice category
    Then the <section1> option is marked as completed
    When the user selects the option <option2> in section <section2>
    Then the <section2> option is marked as completed
    When the user is in the section <section3>
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks in the Date of Signature day field
    And the user clicks in the Date of Signature month field
    And the user clicks in the Date of Signature year field
    And the user sees a success message after form upload in recorded by as <message>

    Examples:
      | PatientChoice  | option1               | section1                | option2                         | section2  | section3    | RecordedBy                                                                                                           | message               |
      | Patient choice | Adult (With Capacity) | Patient choice category | Rare & inherited diseases – WGS | Test type | Recorded by | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Successfully Uploaded |