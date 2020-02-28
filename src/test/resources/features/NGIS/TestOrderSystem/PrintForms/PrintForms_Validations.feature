@regression
@printForms
Feature: Print Forms - Validations

  @NTS-4702 @E2EUI-1306 @LOGOUT
  Scenario Outline: NTS-4702: As a user I want to see some advisory notice on the PDFs that says Not for Clinical use
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R61 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=28-04-2007:Gender=Female |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the print forms stage is locked
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<OrderingEntityName>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - No of participants 1
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    Then the print forms stage is unlocked
    ###Print forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print form for the proband
    Then the user is able to validate the text "<Watermark>" in the downloaded form "SampleForm.pdf"

    Examples:
      | PrintForms  | Watermark                         | PatientDetails  | RequestingOrganisation  | OrderingEntityName    | TestPackage  | NoOfParticipants |
      | Print forms | N o t f o r C l i n i c a l U s e | Patient details | Requesting organisation | BOLTON ROYAL HOSPITAL | Test package | 1                |

  @NTS-4702 @E2EUI-1794 @E2EUI-1786 @LOGOUT @v_1 @BVT_P0
  Scenario Outline: NTS-4702: Cancel a referral as revoked or marked in error
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient is a foreign national:DOB=9-09-1999:Gender=Male |
    ###Patient Details- cancelling referral
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Cancel referral link
    Then the cancel referral dialog box is displayed with the following fields
      | FieldText                                    |
      | Please enter the reason for the cancellation |
      | Why do you want to cancel this referral?     |
      | Cancelling a referral can’t be undone        |
      | Return to referral                           |
      | Confirm cancellation                         |
    When the user selects the cancellation reason "<CancellationReason>" from the modal
    And the user submits the cancellation
    Then the user should be able to see referral status as cancelled with selected "<Reason>" reason
    And the message should display as "<CancellationSuccessMessage>"

    Examples:
      | Reason          | CancellationReason                                           | CancellationSuccessMessage                                                                      |
      | Revoked         | The referral has been paused or stopped (“Revoke”)           | This referral has been cancelled so further changes might not take effect. Start a new referral |
      | Marked in error | An uneditable mistake was made in creation (“Mark in error”) | This referral has been cancelled so further changes might not take effect. Start a new referral |
