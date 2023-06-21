@NEAT
Feature: NEAT application login and Patient search

  @NTS-6648 @Z-LOGOUT
#  @E2EUI-2413
  Scenario:NEAT-4:NTS-6648:E2EUI-2413:Update active patient record to become Inactive
    ###New Referral to search in the NEAT admin tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
#    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the status button "Change status to inactive"
 #   And the confirmation dialog box appears with the heading "Are you sure?"
 #   And the user clicks on the status button "Continue"
    Then the user is navigated to a page with title Make this patient record inactive
    And the user clicks on the reason button "Created in error"
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "This record is now inactive"


  @NTS-6642 @Z-LOGOUT
#  @E2EUI-2560
  Scenario:NTS-6642:E2EUI-2560:NEAT-49:Update Inactive patient record to be active
    ###New Referral to search in the NEAT admin tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
#    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    ### Make it inactive first
    And the user clicks on the status button "Change status to inactive"
  #  And the confirmation dialog box appears with the heading "Are you sure?"
  #  And the user clicks on the status button "Continue"
    Then the user is navigated to a page with title Make this patient record inactive
    And the user clicks on the reason button "Created in error"
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "This record is now inactive"
    ### Changing to Active
    And the user clicks on the status button "Change status to active"
    Then the user is navigated to a page with title Make this patient record active
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "This record is now active"


  @NTS-6656 @Z-LOGOUT
#  @E2EUI-2643
  Scenario:NTS-6656:E2EUI-2643:NEAT-38:Allow users to edit the Reason and Justification for status change
    ###New Referral to search in the NEAT admin tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the status button "Change status to inactive"
#    And the confirmation dialog box appears with the heading "Are you sure?"
#    And the user clicks on the status button "Continue"
    Then the user is navigated to a page with title Make this patient record inactive
    And the user clicks on the reason button "Duplicate"
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "This record is now inactive"

    And the user clicks on the status button "Change status to active"
    Then the user is navigated to a page with title Make this patient record active
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "This record is now active"
    And the user clicks on the status button "Change status to inactive"
#    And the confirmation dialog box appears with the heading "Are you sure?"
#    And the user clicks on the status button "Continue"
    Then the user is navigated to a page with title Make this patient record inactive
    And the user clicks on the reason button "Created in error"
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "This record is now inactive"
   # And the user clicks on the link "Back to patient search"
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the link "Back to patient search"
#    And the user clicks on the status button "Change merge status"

  @NTS-6643 @Z-LOGOUT
#  @E2EUI-2414
  Scenario:NEAT-40:Scenario1:E2EUI-2414:Show a warning to users who try to deactivate a patient with an active referral
    ##New Referral to search in the NEAT admin tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
      ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the status button "Change status to inactive"
#    And the confirmation dialog box appears with the heading "Are you sure?"
#    And the user clicks on the status button "Continue"
    Then the user is navigated to a page with title Make this patient record inactive
    Then the user sees the notification "There are active referrals linked to this patient record"
    And the user clicks on the link "Back to patient record"
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the status button "Change status to inactive"

  @NTS-6643 @Z-LOGOUT
#  @E2EUI-2414
  Scenario:NEAT-40:Scenario2:E2EUI-2414:Show a warning to users who try to deactivate a patient with an active referral
    ###New Referral to search in the NEAT admin tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    And the user clicks the Cancel referral link
    And the user selects the cancellation reason "The referral has been paused or stopped (“Revoke”)" from the modal
    And the user submits the cancellation
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the status button "Change status to inactive"
    Then the user is navigated to a page with title Make this patient record inactive
    And the user clicks on the link "Back to patient record"
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the status button "Change status to inactive"


  @NTS-6649  @Z-LOGOUT
#  @E2EUI-2412
  Scenario Outline:NTS-6649:NEAT-46:E2EUI-2412:Show patient record history
    ##New Referral to search in the NEAT admin tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
     ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "Active"
    And the user clicks on the link "Edit patient record history"
    Then the user is navigated to a page with title Patient record history
    Then the user verifies the patient record history
    And the user will able to see the table with below details
      | Date | Status | Reason | Justification |
    And the user clicks on the link "Back to patient record"
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the status button "Change status to inactive"
#    And the confirmation dialog box appears with the heading "Are you sure?"
#    And the user clicks on the status button "Continue"
    Then the user is navigated to a page with title Make this patient record inactive
    And the user sees the "<Current status>" and "<Updated status>"
    And the user clicks on the reason button "Created in error"
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "Inactive"

    Examples:
      | Current status | Updated status |
      | Active         | Inactive       |
