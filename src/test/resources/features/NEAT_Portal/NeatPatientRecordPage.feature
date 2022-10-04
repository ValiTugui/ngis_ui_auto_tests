@NEAT
Feature:E2EUI-2558: [E2E|UI] - Display Reason for inactive on the Edit patient record page


  @NTS-6650  @Z-LOGOUT
#    @E2EUI-2558
  Scenario Outline:NTS-6650:E2EUI-2558:[E2E|UI] - Display Reason for inactive on the Edit patient record page
    ###New Referral to search in the NEAT admin tool
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
    Then the user verifies the patient record history
    And the user sees the "<Current status>" and "<Updated status>"
    And The user sees the Reason section with toggle options Duplicate and Created in error
    Then the user sees blank mandatory field labels highlighted in red color
      | field_name    | color   |
      | Reason        | #212b32 |
      | Justification | #212b32 |
    And the user clicks on the reason button "Created in error"
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "This record is now inactive"
    Then the user sees the Patient record status as "inactive"
    Then The user should be able to see the "<reason>" reason displayed at Patient record status
    Examples:
      | Current status | Updated status | reason                           |
      | Active         | Inactive       | This record was created in error |


  @NTS-6654  @Z-LOGOUT
#    @E2EUI-2557

  Scenario Outline:NTS-6654:E2EUI-2557:[E2E|UI] - Prevent users from reactivating a patient whose NHS number exists when deep linking to the status page (form)
# Pre-requisite: Verify the NGIS ID provided in the examples is matching with respect to the provided NHS no and DOB,
  # if not update the Patient ID which can be verified via Patient search
      #making the patient inactive

    Given the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
    Then the user is navigated to a page with title Find a patient record
    And the user searches the "<NGIS-ID>"
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

# creates new referral and stores the information

    Then the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | RD | GEL_SUPER_USER |
    When the user types in valid details of a "<patient-search-type2>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    And the user clicks the patient result card
    Then the Patient Details page is displayed
    Then the user clicks on edit patient details
    And the user fills in the Ethnicity field "A - White - British"
    And the user clicks the Save and Continue button on Patient details page
    Then the user accept the alert with message "OK"
    Then the user clicks the Start Referral button
    And the user stores the generated Patient NGIS-ID
    Then the user is navigated to a page with title Add a requesting organisation

      #Goes back to neat and check the status Newly created is in active state

    When the user logs into the NEAT admin tool with the following credentials
      | NEAT_URL | find-patient-record | GEL_SUPER_USER |
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


      #Goes back to search initial patient to change the status to active

    And the user clicks on the link "Back to patient search"
    And the user searches the "<NGIS-ID>"
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "inactive"
    And the user clicks on the status button "Change status to active"
    And the user enters the justification reason in the text box as "from automation script"
    And the user clicks on the status button "Confirm"
    Then the user is navigated to a page with title Edit this patient record
    And the user clicks on the link "Back to patient search"

      #Goes to newly created inactive patient to check if it can be activated

    And the user searches the NGIS-ID in the search box
    And the user is navigated to a page with title Edit this patient record
    Then the user sees the notification "You cannot reactivate this patient record. An active record is using the same NHS Number"
    And the user has to click on "An active record" link present in warning message
    Then the user is navigated to a page with title Edit this patient record
    Then the user sees the Patient record status as "inactive"
    Then User should be able to see the same patient details with old NGIS id
    Examples:
      | patient-search-type1 | NhsNumber  | DOB        | patient-search-type2 | NGIS-ID      |
      | NGIS                 | 9449303223 | 26-01-2010 | NHS Spine            | p73168204461 |

