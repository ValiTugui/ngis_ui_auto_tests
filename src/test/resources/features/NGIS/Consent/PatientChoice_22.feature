@SYSTEM_TEST
@NTS-6029
Feature: Create archived copy of files when user clicks on delete button in UI

  Scenario Outline: NTS-6029: As a user, I want to create a copy of the file before deleting the file uploaded.
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1992:Gender=Male |
#    Then the user is navigated to a page with title Add a requesting organisation
#    And the user clicks the Save and Continue button
#    ##Requesting Organisation
#    Then the user is navigated to a page with title Add a requesting organisation
#    And the user enters the keyword "South London and Maudsley NHS Foundation Trust" in the search field
#    And the user selects a random entity from the suggestions list
#    Then the details of the new organisation are displayed
#    And the user clicks the Save and Continue button
#    And the "<RequestingOrganisation>" stage is marked as Completed
#    ##Test Package - proband only
#    When the user navigates to the "<TestPackage>" stage
#    And the user selects the number of participants as "<OneParticipant>"
#    And the user clicks the Save and Continue button
#    And the "<TestPackage>" stage is marked as Completed
#    When the user navigates to the "<PatientChoice>" stage
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    Then the user is navigated to a page with title Add patient choice information
#    And the user is in the section Patient choice category
#    When the user selects the option Adult (With Capacity) in patient choice category
#    Then the Patient choice category option is marked as completed
#    And the option Adult (With Capacity) displayed with edit option in Patient choice category
#    When the user selects the option Rare & inherited diseases – WGS in section Test type
#    Then the Test type option is marked as completed
#    And the option Rare & inherited diseases – WGS displayed with edit option in Test type
#    And the user is in the section Recorded by
#    Then the user should able to see upload button is enable by default in Record by section
#    And the user should see form to follow button as enabled
#    When the user fills "<RecordedByWithFormUpload>" details in recorded by
#    Then the user sees a success message after form upload in recorded by as Successfully Uploaded
#    And the user selects Record of Discussion Form from dropdown option in recorded by
#    Then the Date of Signature fields are displayed as enabled
#    When the user fills current date as Date of Signature
#    Then the user clicks on Continue Button
#    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
#    When the user selects the option Yes for the question Has research participation been discussed?
#    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
#    And the user clicks on Continue Button
#    When the user is in the section Patient signature
#    When the user fills PatientSignature details in patient signature
#    And the user clicks on submit patient choice Button
#    Then the user should be able to see the patient choice form with success message
#    When the user selects the History tab in patient choice page
#    And the user should be able to see patient choice in history tab
#    And the user has to click on latest record of discussion
    And the user is able to connect to the S3 bucket and read the files in folder "<Patient records>"
    Examples:
      | Patient records |
      | patient-records |

