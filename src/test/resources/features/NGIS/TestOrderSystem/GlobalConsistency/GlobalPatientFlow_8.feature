#@regression
#@GlobalFlow
#@GlobalFlow_Validations_Tumour
@03-TEST_ORDER
@SYSTEM_TEST
@GlobalConsistency
Feature: GlobalConsistency:Global Patient Flow 8 - End to end Tumour

  @NTS-4731 @Z-LOGOUT
#    @E2EUI-1087 @E2EUI-873
  Scenario Outline: NTS-4731:E2EUI-1087,873: Verify warning pop up when navigating without saving changes
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M211 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=11-11-2011:Gender=Male |
    ##Patient Details Page
    When the user navigates to the "<PatientDetails>" stage
    When the user is navigated to a page with title Check your patient's details
    And the user fill in the first name field
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Greater" in the search field
    And the user selects the first entity from the suggestions list
    Then the details of the new organisation are displayed
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    When the user deselects the test
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user selects the test by clicking the deselected test
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Tumour Page
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    And the user clicks the Save and Continue button
    ##Samples Page
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
    ##Notes Page
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Patient Choice Page
    Then the user is navigated to a page with title Patient choice
    And the user edits the patient choice status
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user clicks the Save and Continue button
    ##print Forms Page
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | PatientDetails  | warningMessage                    | warningMessage1                                             | newStage | acknowledgeMessage | tumour_type           | presentationType | sampleType          | sampleState         | RecordedBy                                  |
      | Patient details | Changes you made may not be saved | This section contains unsaved information. Discard changes? | Notes    | Dismiss            | Solid tumour: primary | Recurrence       | Solid tumour sample | Fresh frozen tumour | ClinicianName=NARAYAN:HospitalNumber=102030 |