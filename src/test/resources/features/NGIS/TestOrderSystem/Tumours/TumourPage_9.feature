@03-TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Tumours Page - 9

 # E2EUI-1440 E2EUI-1219
  @NTS-3154 @Z-LOGOUT
#    @E2EUI-894 @E2EUI-1549 @E2EUI-949
  Scenario Outline: NTS-3154:E2EUI-894,1549,949: Add a new tumour for a new patient
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the user see a tick mark next to the added tumour
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples:
      | stage   | tumour_type              | presentationType   | searchTerm | notificationText |
      | Tumours | Solid tumour: metastatic | First presentation | test       | Tumour added     |

  @NTS-6026  @Z-LOGOUT
  Scenario Outline:NTS-6026_Sceario1: As a user, I should see the tumour details which I created earlier for a referral for a new patient in another referral for the same patient (populated)
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    ##Requesting Organisation
    When the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "University College London Hospitals NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ##Tumour
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    And the user clicks the Save and Continue button
    ##Samples
    And the user clicks the Save and Continue button
    ##Notes
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ##Patient Choice
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user clicks on Continue Button
    ##print Forms
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Then the user clicks on start a new referral button
    When the user types in the CI term  in the search field and selects the first result from the results list
      | M89 |
    And the user sees the button "Yes, start Referral" on Bottom right
    Then the user clicks the Start Test Order Referral button
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    And the user logs in to the Test Order system successfully
      | Find your patient |
    And the user clicks the NO button
    Then the user fills in valid patient details in the search fields when No is selected
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the user clicks the patient result card
    And the user clicks the Start Referral button to display the referral page
    And the "<PatientDetails>" stage is marked as Completed
    And the user navigates to the "<Tumours>" stage
    Then the user is navigated to a page with title Select or edit a tumour
    And the "<Tumours>" stage is NOT marked as Mandatory To Do
    Then on the select or edit a tumour page, the new tumour details are displayed in the tumour table list

    Examples:
      | tumour_type              | presentationType | RecordedBy                            | PatientDetails  | Tumours | patient-type |
      | Solid tumour: metastatic | Recurrence       | ClinicianName=John:HospitalNumber=123 | Patient details | Tumours | NGIS         |

  @NTS-6026  @Z-LOGOUT
  Scenario Outline:NTS-6026_Scenario2: As a user, I should see the tumour details which I created earlier for a referral for a spine patient in another referral for the same patient (populated)
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ##Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ## Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ##Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M45 | GEL_NORMAL_USER | NHSNumber=<NhsNumber>:DOB=<DOB>:Ethnicity=A - White - British |
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "University College London Hospitals NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ##Tumour
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    And the user clicks the Save and Continue button
    ##Samples
    And the user clicks the Save and Continue button
    ##Notes
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    #Patient Choice
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user clicks on Continue Button
    ##print Forms
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Then the user clicks on start a new referral button
    When the user types in the CI term  in the search field and selects the first result from the results list
      | M89 |
    And the user sees the button "Yes, start Referral" on Bottom right
    Then the user clicks the Start Test Order Referral button
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    And the user logs in to the Test Order system successfully
      | Find your patient |
    When the user types in valid details of a "<patient-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-type>" result is successfully returned
    And the user clicks the patient result card
    And the user clicks the Start Referral button to display the referral page
    And the "<PatientDetails>" stage is marked as Completed
    And the user navigates to the "<Tumours>" stage
    Then the user is navigated to a page with title Select or edit a tumour
    And the "<Tumours>" stage is NOT marked as Mandatory To Do
    Then on the select or edit a tumour page, the new tumour details are displayed in the tumour table list

    Examples:
      | NhsNumber  | DOB        | tumour_type              | presentationType | RecordedBy                            | PatientDetails  | Tumours  | patient-type |
      | 9449303673 | 16-07-2010 | Solid tumour: metastatic | Recurrence       | ClinicianName=John:HospitalNumber=123 | Patient details | Tumours | NGIS         |