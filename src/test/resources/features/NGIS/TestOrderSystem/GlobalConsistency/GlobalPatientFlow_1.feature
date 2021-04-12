@GlobalFlow
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@GlobalConsistency
Feature: GlobalConsistency: Global Patient Flow 1- Stage Validation


  @NTS-3497 @Z-LOGOUT
#    @E2EUI-1995
  Scenario Outline: NTS-3497: Verify the confirmation message doesn't push down the content after cancelling a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R27 | GEL_SUPER_USER | NHSNumber=NGIS:DOB=14-05-1980:Gender=Male |
    When the user is navigated to a page with title Add a requesting organisation
#    @NTS-4813 @E2EUI-1005
    When the user submits the referral
    Then the user sees a dialog box with a title "<dialogTitle>"
    And the user sees a list of outstanding mandatory stages to be completed in the dialog box
      | MandatoryStagesToComplete |
      | Requesting organisation   |
      | Test package              |
      | Responsible clinician     |
      | Clinical questions        |
      | Patient choice            |
    When the user clicks on the mandatory stage "<RequestingOrganisation>" in the dialog box
    And the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "South Warwickshire NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And  the user clicks the Save and Continue button
    And the "<stage3>" stage is selected
    When the user submits the referral
    And the user sees a dialog box with a title "<dialogTitle>"
    And the user sees a list of outstanding mandatory stages to be completed in the dialog box
      | MandatoryStagesToComplete |
      | Test package              |
      | Responsible clinician     |
      | Clinical questions        |
      | Patient choice            |
    And the user clicks on the mandatory stage "<stage3>" in the dialog box
    And the "<stage3>" stage is selected
#    And the user clicks the Save and Continue button
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
#    @NTS-4562 @E2EUI-1088
    And the user clicks the Log out button
#    Then the user is successfully logged out

    Examples:
      | NoOfParticipants | RevokeMessage                                                             | PrintForms  | RequestingOrganisation  |dialogTitle                  | stage2                  | ordering_entity_name | stage3|
      | 1                | This referral has been cancelled so further changes might not take effect | Print forms | Requesting organisation |There is missing information | Requesting organisation | Maidstone            | Test package |

    @NTS-4711 @Z-LOGOUT
#    @E2EUI-1624
  Scenario Outline:NTS-4711:Verify Page titles for RD on every stage
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R86 | GEL_NORMAL_USER |
    ##Patient Search Page
    And the user is navigated to a page with title Find your patient
    When the user types in valid details of a "<Type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then the message will be displayed as "<ResultMessage>" result found
    And the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the user clicks the patient result card
    ##Patient Details Page
    ## Updated a/c to Gonzalo release
    Then the user is navigated to a page with title Patient record
    And the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the user clicks the Start a new Referral button
    ##Referral Details Page
    When the user is navigated to a page with title Add a requesting organisation
    Then the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
#    ##Patient Details Page

     ##Requesting Organisation Page
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "UNIVERSITY HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
###GPF_5 @NTS-3329
    And the referral submit button is not enabled

    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "1"
    And the user clicks the Save and Continue button
###GPF_5 @NTS-3329
    And the referral submit button is not enabled

    ##Responsible Clinician Page
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
###GPF_5 @NTS-3329
    And the referral submit button is not enabled
    ##Family Members Page
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the user clicks on Add family member button
    ##Family Members Search Page
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    And the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the NHS display format as "<NHSNoFormat>" in patient card
###GPF_5 @NTS-3329
    And the referral submit button is not enabled
    ###Print forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
#    Then the user should be able to see the patient banner at same location
    And the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the NHS display format as "<NHSNoFormat>"
###GPF_5 @NTS-3329
    And the referral submit button is not enabled
#   Added @NTS-5068
    When the user provides an invalid referral id in the url "<invalidReferralURL>"
    Then the user should see page is not loading



    Examples:
      | NHSNoFormat | Type | NhsNumber  | DOB        | RequestingOrganisation  | FamilyMembers  | FamilyMemberDetails                 | ResultMessage          | PrintForms  | invalidReferralURL                                                                |
      | 3,3,4       | NHS  | 2000004296 | 24-09-2011 | Requesting organisation | Family members | NHSNumber=2000006035:DOB=20-11-2016 | 1 patient record found | Print forms | https://test-ordering.int.ngis.io/test-order/referral/r5E$&%5E943/patient-details |

  @NTS-3498 @Z-LOGOUT
#    @E2EUI-1701
  Scenario Outline: NTS-3498: Verify Global patient information bar component
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Other rare neuromuscular disorders | Rare-Disease | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    ###Patient Details
    And the user is navigated to a page with title Add a requesting organisation
    Then the user should be able to see the patient referral banner at the top
    Then the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    ###Requesting Organisation
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user should be able to see the patient banner at same location
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ###Test Package
    And the user is navigated to a page with title Confirm the test package
    Then the user should be able to see the patient banner at same location
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ###Responsible Clinician
    And the user is navigated to a page with title Add clinician information
    Then the user should be able to see the patient banner at same location
    When the user navigates to the "<Clinical questions>" stage
    And the user is navigated to a page with title Answer clinical questions
    Then the user should be able to see the patient banner at same location
    ###Notes
    When the user navigates to the "<Notes>" stage
    And the user is navigated to a page with title Add clinical notes
    Then the user should be able to see the patient banner at same location
    ###Family Members
    When the user navigates to the "<Family members>" stage
    And the user is navigated to a page with title Add a family member to this referral
 ###   @NTS-4320 @E2EUI-1065
    Then the user should be able to see continue button on landing page
    Then the user should be able to see the patient banner at same location
    ###Patient Choice
    When the user navigates to the "<Patient choice>" stage
    And the user is navigated to a page with title Patient choice
    Then the user should be able to see the patient banner at same location
    ###Panels
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Manage panels
    Then the user should be able to see the patient banner at same location
    ###Pedigree
    When the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should be able to see the patient banner at same location
    ###Print Forms
    When the user navigates to the "<Print forms>" stage
    And the user is navigated to a page with title Print sample forms
    Then the user should be able to see the patient banner at same location

    Examples:
      | Requesting organisation | ordering_entity_name | NoOfParticipants | Family members | Clinical questions | Notes | Patient choice | Panels | Pedigree | Print forms | RevokeMessage                                                             |
      | Requesting organisation | Maidstone            | 1                | Family members | Clinical questions | Notes | Patient choice | Panels | Pedigree | Print forms | This referral has been cancelled so further changes might not take effect |

  @NTS-3498 @Z-LOGOUT
#  @E2EUI-1850
  Scenario: NTS-3498: The links from the footer should be the same color
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    When the user clicks the Start Test Order Referral button
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    And the user logs in to the Test Order system successfully
      | Find your patient |
    Then the user sees the color of feedback link as NHS Blue #005eb8
    And the user sees the color of privacy policy link as NHS Blue #005eb8