#Josephine release
@SYSTEM_TEST
@NTS-5948 @Z-LOGOUT
#NTOS-5048

Feature: : New Patient page 1


  Scenario Outline: NTS-5948: Verifying 'Requesting organisation' page is getting displayed while navigating to 'Requesting organisation' after submitting the 'patient choice'.

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2008:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choice
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user sees the search label with "<expectedText>"
    And the user sees the search field with search icon
    And the user see the search field has placeholder text as "<placeholderText>"
    And  the Save and Continue button should be disabled
    Examples:
      | RequestingOrganisation  | expectedText                                                   | placeholderText                                                                         | Patient choice stage | RecordedBy                            |
      | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust | Patient choice       | ClinicianName=John:HospitalNumber=123 |
