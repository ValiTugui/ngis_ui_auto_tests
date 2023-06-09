@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@PrintForms
Feature: TestOrder - Print Forms 3 - Validations

  @NTS-4702 @Z-LOGOUT
#    @E2EUI-1306
  Scenario Outline: NTS-4702: As a user I want to see some advisory notice on the PDFs that says Not for Clinical use
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R61 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=28-04-2007:Gender=Female |
    ###Patient Details
    Then the user is navigated to a page with title Test Order Forms

    And the print forms stage is locked
#    And the "<PatientDetails>" stage is marked as Completed
    When the user navigates to the "Requesting organisation" stage
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - No of participants 1
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
  ## Covered @NTS-4746 (@E2EUI-1306)
    Then the print forms stage is unlocked
    ###Print forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print form for the proband
    ## covered @NTS-4703
    Then the user is able to validate the text "<Watermark>" in the downloaded form "SampleForm.pdf"

    Examples:
      | PrintForms  | Watermark               | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants |
      | Print forms | N ot for C linical U se | Patient details | Requesting organisation | Test package | 1                |

  @NTS-4702 @Z-LOGOUT
#    @E2EUI-1794 @E2EUI-1786
  Scenario Outline: NTS-4702: Cancel a referral as revoked or marked in error
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_SUPER_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=9-09-1999:Gender=Male |
    ###Patient Details- cancelling referral
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    And the user clicks the Cancel referral link
    ##Title,Question,Warning,button1,button2
    Then the cancel referral dialog box is displayed with the following fields
      | Dialog Contents                              |
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
      | Revoked         | The referral has been paused or stopped (“Revoke”)           | This referral has been cancelled so further changes might not take effect. Go back the patient page or search for a new patient |
      | Marked in error | An uneditable mistake was made in creation (“Mark in error”) | This referral has been cancelled so further changes might not take effect. Go back the patient page or search for a new patient |

  @NTS-4702-2 @Z-LOGOUT
#    @E2EUI-1794 @E2EUI-1786
  Scenario Outline: NTS-4702: Cancel a referral as revoked or marked in error
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=9-09-1999:Gender=Male |
    ###Patient Details- cancelling referral
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    And the user clicks the Cancel referral link
    ##Title,Question,Warning,button1,button2
    Then the cancel referral dialog box is displayed with the following fields
      | Dialog Contents                              |
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
      | Revoked         | The referral has been paused or stopped (“Revoke”)           | This referral has been cancelled so further changes might not take effect. Go back the patient page or search for a new patient|
      | Marked in error | An uneditable mistake was made in creation (“Mark in error”) | This referral has been cancelled so further changes might not take effect. Go back the patient page or search for a new patient |

  @NTS-4702 @Z-LOGOUT
#    @E2EUI-1787
  Scenario Outline: NTS-4702: scenario_1: View cancelled referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54 | GEL_SUPER_USER | NHSNumber=2000001130:DOB=08-11-1959 |
     ###Patient Details
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
#    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Cancel referral link
    And the user selects the cancellation reason "<CancellationReason>" from the modal
    When the user submits the cancellation
    And the user should be able to see referral status as cancelled with selected "<Reason>" reason
    Then the message should display as "<CancellationSuccessMessage>"

    Examples:
      | Reason  | CancellationReason                                 | CancellationSuccessMessage                                                                      |
      | Revoked | The referral has been paused or stopped (“Revoke”) | This referral has been cancelled so further changes might not take effect. Go back the patient page or search for a new patient |

  @NTS-4702 @Z-LOGOUT
#    @E2EUI-1787
  Scenario Outline:NTS-4702: scenario_2: View cancelled referral
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54 | Rare Disease | GEL_SUPER_USER |
    ##Patient Search Page
    When the user is navigated to a page with title Find your patient
    When the user types in valid details of a "NGIS" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    And the user clicks the patient result card
    ##Referral Details Page
    Then the user should be able to see referral card status as cancelled with selected "<Reason>" reason

    Examples:
      | NhsNumber            | DOB            | Reason  |
      | NHSNumber=2000001130 | DOB=08-11-1959 | Revoked |

  @NTS-3413 @Z-LOGOUT
#    @E2EUI-1661
  Scenario Outline: NTS-3413 :scenario_1: Any updates done in the referral will not be reflected in the Print Forms stage- for Family Member
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R193 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
    ###Patient Details
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ###Family Members
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    When the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    And the user verifies that the relationship to proband "<RelationshipToProband>" is updated in Print forms section
    ###Family Members for modification
    When the user navigates to the "<FamilyMembers>" stage
    And the user edits the highlighted family member with "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Continue with this family member
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<ChangedRelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user verifies that the relationship to proband "<ChangedRelationshipToProband>" is updated in Print forms section

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | FamilyMembers  | PrintForms  | FamilyMemberDetails                 | RelationshipToProband | ChangedRelationshipToProband |
      | Patient details | Requesting organisation | Test package | 2                | Family members | Print forms | NHSNumber=2000003869:DOB=18-09-2011 | Full Sibling          | Paternal Half Sibling        |