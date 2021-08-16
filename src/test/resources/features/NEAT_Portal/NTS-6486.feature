@NEAT
@NTS_6486

Feature: Patient merge status

  @NTS-6486_Scenario1
  Scenario Outline:For an active patient, as a user I can see a list of merge status options that can be selected in NEAT,Validate the error message and select a status

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "Active"
    Then the user sees the Merge status as "<current status>"
    And a button to change the merge status is displayed.
    And the user clicks on the status button "Change merge status"
    Then the user is navigated to a page with title Change merge status
    And the patient detail summary card is displayed
    Then the user sees the Current Merge status as "<current merge status>"
    And the user should see the field label "Updated merge status"
    And the user click on the Confirm button
    Then the user should see the error message "Select a status"
    And the user sees the below values in the Updated merge status dropdown
      | None | Merged | Demerged | Merged and Demerged |
    And the user clicks on the Updated merge status drop down
    And the user has to select a merge status "<Merge status>"
    And the user click on the Confirm button
    And the user has to see the success notification "<success notification>" on the Edit this patient record page
    Then the user sees the Merge status as "<latest merge status>"

    Examples:
      | current status | current merge status | Merge status | success notification                        | latest merge status |
      | None           | None                 | Merged       | This record's merge status has been updated | Merged              |


  @NTS-6486_Scenario2
  Scenario Outline:As a user I want to see an error message where the new merge status has not been updated successfully.

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=14-05-2004:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    And the user stores the generated Patient NGIS-ID
    ### Navigating to NEAT Tool
    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the NGIS-ID in the search box
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "Active"
    And the user clicks on the status button "Change status to inactive"
    And the confirmation dialog box appears with the heading "Are you sure?"
    And the user clicks on the status button "Continue"
    Then the user is navigated to a page with title Make this patient record inactive
    And the user clicks on the reason button "Created in error"
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "This record is now inactive"
    Then the user sees the Patient record status as "Inactive"
    Then the user sees the Merge status as "<current status>"
    And a button to change the merge status is displayed.
    And the user clicks on the status button "Change merge status"
    Then the user is navigated to a page with title Change merge status
    And the patient detail summary card is displayed
    Then the user sees the Current Merge status as "<current merge status>"
    And the user should see the field label "Updated merge status"
    And the user clicks on the Updated merge status drop down
    And the user has to select a merge status "<Merge status>"
    And the user click on the Confirm button
    And the user has to see the error notification "<error notification>" on the Edit this patient record page
    Then the user sees the Merge status as "<unchanged merge status>"

    Examples:
      | current status | current merge status | Merge status | error notification                                             | unchanged merge status |
      | None           | None                 | Demerged     | This record's merge status update has failed. Please try again | None                   |

