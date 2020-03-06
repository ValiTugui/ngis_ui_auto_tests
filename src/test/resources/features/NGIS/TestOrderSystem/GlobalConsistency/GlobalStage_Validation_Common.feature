@regression
@TO_RD
@GlobalFlow
@GlobalFlow_Validations_Common

Feature: Feature: Global Patient Flow - Stage Validation

  @NTS-4320 @E2EUI-1065 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4320: Verify continue button
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R61 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2010:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient's details
    ##Family Member Landing Page
    And the user navigates to the "<FamilyMembers>" stage
    And the user is navigated to a page with title Add a family member to this referral
    Then the user should be able to see continue button on landing page
    When the user clicks on Continue Button
    ##Patient Choice Landing Page
    And the user is navigated to a page with title Patient choice
    Then the user should be able to see continue button on landing page
    Examples:
      | FamilyMembers  |
      | Family members |

  @LOGOUT @NTS-3497 @E2EUI-1995
  Scenario Outline: NTS-3497: Verify the confirmation message doesn't push down the content after cancelling a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R27 | GEL_SUPER_USER | NHSNumber=NGIS:DOB=14-05-1980:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient
    ##Requesting Organisation Page
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "ROCHDALE INFIRMARY" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Print Forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    ##Cancel Referral
    When the user clicks the Cancel referral link
    And the user selects the cancellation reason "The referral has been paused or stopped (“Revoke”)" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"
    Then the user verifies that the revoke message doesn't overlap any other element

    Examples:
      | NoOfParticipants | RevokeMessage                                                             | PrintForms  | RequestingOrganisation  |
      | 1                | This referral has been cancelled so further changes might not take effect | Print forms | Requesting organisation |

  @NTS-Todo @E2EUI-1624 @LOGOUT @RD
  Scenario Outline:NTS-4711:Verify Page titles for RD on every stage
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R86 | GEL_NORMAL_USER |
    ##Patient Search Page
    And the user is navigated to a page with title Find your patient
    When the user types in valid details of a "<Type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then the message will be displayed as "<ResultMessage>" result found
    And the the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the NHS display format as "<NHSNoFormat>"
    And the user clicks the patient result card
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient
    And the the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    When the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"
    And the user clicks the Start Referral button
    ##Referral Details Page
    When the user is navigated to a page with title Check your patient's details
    Then the the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the NHS display format as "<NHSNoFormat>"
    ##Requesting Organisation Page
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "UNIVERSITY HOSPITAL AINTREE" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "1"
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
    ##Family Members Page
    Then the user is navigated to a page with title Add a family member to this referral
    And the the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the NHS display format as "<NHSNoFormat>"
    And the user clicks on Add family member button
    ##Family Members Search Page
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    And the the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the NHS display format as "<NHSNoFormat>"
    ###Print forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    And the the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the NHS display format as "<NHSNoFormat>"

    Examples:
      | NHSNoFormat | Type | NhsNumber  | DOB        | RequestingOrganisation  | FamilyMembers  | FamilyMemberDetails                 | ResultMessage          | PrintForms  |
      | 3,3,4       | NHS  | 9449306621 | 09-05-2011 | Requesting organisation | Family members | NHSNumber=9449310440:DOB=12-07-2003 | 1 patient record found | Print forms |
