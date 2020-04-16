#@regression
#@GlobalFlow
#@GlobalFlow_Validations_Common
@03-TEST_ORDER
@SYSTEM_TEST
Feature: GlobalConsistency: Global Patient Flow 1- Stage Validation

  @NTS-4320 @Z-LOGOUT
#    @E2EUI-1065
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

  @NTS-3497 @Z-LOGOUT
#    @E2EUI-1995
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
    When the user is navigated to a page with title Check your patient
    And the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    When the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"
    And the user clicks the Start Referral button
    ##Referral Details Page
    When the user is navigated to a page with title Check your patient's details
    Then the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |

#    ##Patient Details Page
#    When the user is navigated to a page with title Patient record
#    Then the user should see previous labels replaced as current labels
#      | PreviousLabel      | CurrentLabel      |
#      | NHS number,NHS no. | NHS Number,NHS No |
#    When the user clicks the Start a new Referral button
#    ##Referral Details Page
#    Then the user is navigated to a page with title Check your patient's details
#    And the user should see previous labels replaced as current labels
#      | PreviousLabel      | CurrentLabel      |
#      | NHS number,NHS no. | NHS Number,NHS No |
#
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
    ###Print forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    And the user should see previous labels replaced as current labels
      | PreviousLabel      | CurrentLabel      |
      | NHS number,NHS no. | NHS Number,NHS No |
    And the NHS display format as "<NHSNoFormat>"

    Examples:
      | NHSNoFormat | Type | NhsNumber  | DOB        | RequestingOrganisation  | FamilyMembers  | FamilyMemberDetails                 | ResultMessage          | PrintForms  |
      | 3,3,4       | NHS  | 9449306621 | 09-05-2011 | Requesting organisation | Family members | NHSNumber=9449310440:DOB=12-07-2003 | 1 patient record found | Print forms |

  @NTS-3498 @Z-LOGOUT
#    @E2EUI-1701
  Scenario Outline: NTS-3498: Verify Global patient information bar component
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Other rare neuromuscular disorders | Rare-Disease | create a new patient record | Patient is a foreign national |
    ###Patient Details
    And the user is navigated to a page with title Check your patient's details
    Then the user should be able to see the patient referral banner at the top
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
    And the user is navigated to a page with title Add notes to this referral
    Then the user should be able to see the patient banner at same location
    ###Family Members
    When the user navigates to the "<Family members>" stage
    And the user is navigated to a page with title Add a family member to this referral
    Then the user should be able to see the patient banner at same location
    ###Patient Choice
    When the user navigates to the "<Patient choice>" stage
    And the user is navigated to a page with title Patient choice
    Then the user should be able to see the patient banner at same location
    ###Panels
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Panels
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
      | Requesting organisation | ordering_entity_name | NoOfParticipants | Family members | Clinical questions | Notes | Patient choice | Panels | Pedigree | Print forms |
      | Requesting organisation | Maidstone            | 1                | Family members | Clinical questions | Notes | Patient choice | Panels | Pedigree | Print forms |

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
