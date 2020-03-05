@regression
@TO_RD_And_Tumour
@GlobalFlow
@GlobalStagValidation

Feature: Global Patient Information Bar on Family Members Navigation Stage Navigation

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

  @NTS-3494 @LOGOUT @E2EUI-2017
  Scenario Outline: NTS-3494:Validating referral banner fixed at the top of every page.
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R143 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient
    Then the user should verify the referral banner present at the top
    ##Requesting Organisation Page
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    Then the user should verify the referral banner present at the top
    And the user enters the keyword "ROCHDALE INFIRMARY" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    Then the user should verify the referral banner present at the top
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    And the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    Then the user should verify the referral banner present at the top
    And the user clicks the Save and Continue button
    ##Clinical Question Page
    And the user navigates to the "<ClinicalQuestion>" stage
    And the user is navigated to a page with title Answer clinical questions
    Then the user should verify the referral banner present at the top
    ##Notes Page
    And the user navigates to the "<Notes>" stage
    And the user is navigated to a page with title Add notes to this referral
    Then the user should verify the referral banner present at the top
    ##Family Member Page
    And the user navigates to the "<FamilyMembers>" stage
    And the user is navigated to a page with title Add a family member to this referral
    Then the user should verify the referral banner present at the top
    ##Patient Choice Page
    And the user navigates to the "<PatientChoice>" stage
    And the user is navigated to a page with title Patient choice
    Then the user should verify the referral banner present at the top
    ##Panels
    And the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Panels
    Then the user should verify the referral banner present at the top
    ##Pedigree
    And the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should verify the referral banner present at the top
    ##Print Forms Page
    And the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    Then the user should verify the referral banner present at the top

    Examples:
      | RequestingOrganisation  | NoOfParticipants | ResponsibleClinicianDetails                               | ClinicalQuestion   | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  |
      | Requesting organisation | 1                | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | Notes | Family members | Patient choice | Panels | Pedigree | Print forms |

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
    When the user verify the text present in the page as "<NHSNumber>"
    Then the user verifies the NHS format as "<NHSNoFormat>"
    And the user clicks the patient result card
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient
    Then the user verify the text present in the page as "<NHSNumber>"
    When the user clicks the Update NGIS record button
    Then the patient is successfully updated with a message "Details saved"
    And the user clicks the Start Referral button
    ##Referral Details Page
    When the user is navigated to a page with title Check your patient's details
    Then the user verify the text present in the page as "<NHSNumber>"
    Then the user verifies the NHS format as "<NHSNoFormat>"
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
    When the user verify the text present in the page as "<NHSNumber>"
    Then the user verifies the NHS format as "<NHSNoFormat>"
    And the user clicks on Add family member button
    ##Family Members Search Page
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the message will be displayed as "<ResultMessage>" result found
    When the user verify the text present in the page as "<NHSNumber>"
    Then the user verifies the NHS format as "<NHSNoFormat>"
    ###Print forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    When the user verify the text present in the page as "<NHSNumber>"
    Then the user verifies the NHS format as "<NHSNoFormat>"

    Examples:
      | NHSNumber          | NHSNoFormat | Type | NhsNumber  | DOB        | RequestingOrganisation  | FamilyMembers  | FamilyMemberDetails                 | ResultMessage          | PrintForms  |
      | NHS Number,NHS No. | 3,3,4       | NHS  | 9449306621 | 09-05-2011 | Requesting organisation | Family members | NHSNumber=9449310440:DOB=12-07-2003 | 1 patient record found | Print forms |
