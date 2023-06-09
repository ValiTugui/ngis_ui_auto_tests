#@userJourneysCancer
@SYSTEM_INTEGRATION_TEST

Feature: UserJourney_CAN_NGIS_Proband_2: UC-29 - E2EUI-1804,905

  @NTS-3348 @Z-LOGOUT
  ##@E2EUI-1804 @E2EUI-905 --Super user sbumit and cancel referral
  Scenario Outline: NTS-3348 - UC#29:E2EUI-1804-905: Create Referral for Proband Only - Patient Choice Not given
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Small cell lung cancer | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_SUPER_USER |
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    And the "<PatientDetails>" stage is marked as Completed
    #Requesting Organisation
    And the user navigates to the "<stage2>" stage
#    And the "<PatientDetails>" stage is marked as Completed
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
    And the details of the new organisation are displayed
    And the user clicks the Save and Continue button
#    And the "<stage2>" stage is marked as Completed
    ##Test Package
    And the user navigates to the "<stage3>" stage
    And the user selects the "Routine"
    And the user clicks the Save and Continue button
#    And the "<stage3>" stage is marked as Completed
    And the "<stage4>" stage is selected
    And the correct "1" tests are saved to the referral in  "<stage3>"
    ##Responsible Clinician
    And the user navigates to the "<stage4>" stage
    And the "Add clinician information" page is displayed
    And the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
#    And the "<stage4>" stage is marked as Completed
    ##Tumour
    And the "<stage5>" stage is selected
    And the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "Recurrence"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "test" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page for the existing patient with tumour list
    And the new tumour is not highlighted
#    And the "<stage5>" stage is marked as Completed
    And the success notification is displayed "Tumour added"
    ##Samples
    And the user navigates to the "<stage6>" stage
    Then the user is navigated to a page with title Manage samples
#    And the "<stage6>" stage is selected
    When the user clicks the Add sample button
    Then the "Add a sample" page is displayed
    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state and filling SampleID
    And the tumour details are displayed in the Add a sample page on selecting a tumour sample type
    And the user clicks the Save and Continue button
    Then the "Add sample details" page is displayed
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    And the success notification is displayed "Sample added"
    Then the "Manage samples" page is displayed
    Then the new sample is displayed in the landing page
    And on the Manage samples page, the sample table list shows the column header names
      | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
      | Sample type      | State             | Sample ID              | Parent ID            | Tumour description      |
#    And the "<stage6>" stage is marked as Completed
    ##Notes
    And the user navigates to the "<stage7>" stage
    Then the "<stage7>" stage is selected
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
#    And the "<stage7>" stage is marked as Completed
    ##Patient Choice
    And the user navigates to the "<stage8>" stage
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing
    And the user clicks the Save and Continue button on the patient choice
    Then the user is navigated to a page with title Patient choice
    Then the Patient Choice landing page is updated to "Authorised by clinician" for the proband
    When the user clicks the Save and Continue button
    Then the "<stage9>" stage is selected
    And the below stages marked as completed
      | <PatientDetails> |
      | <stage2>         |
      | <stage3>         |
      | <stage4>         |
      | <stage5>         |
      | <stage6>         |
      | <stage7>         |
    #checking forms in stage9
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    When the user clicks the Cancel referral link
    And the user selects the cancellation reason "An uneditable mistake was made in creation (“Mark in error”)" from the modal
    And the user submits the cancellation
    Then the referral is successfully "<referralStatus>" with reason "<referralCancelledReason>"
    And the message should display as "<CancellationSuccessMessage>"


    Examples:
      | PatientDetails  | stage2                  | stage3       | stage4                | stage5  | stage6  | stage7 | stage8         | stage9      | referralStatus | referralCancelledReason |CancellationSuccessMessage                                                                      |
      | Patient details | Requesting organisation | Test package | Responsible clinician | Tumours | Samples | Notes  | Patient choice | Print forms | Cancelled      | Marked in error         |This referral has been cancelled so further changes might not take effect. Go back the patient page or search for a new patient |