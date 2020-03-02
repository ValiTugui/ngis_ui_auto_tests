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

  @NTS-TODO @E2EUI-1787 @LOGOUT
  Scenario Outline: NTS-TODO: scenario_1: View cancelled referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54 | GEL_SUPER_USER | NHSNumber=9449310661:DOB=05-04-2000 |
     ###Patient Details
    When the user is navigated to a page with title Check your patient
    And the user clicks the Cancel referral link
    And the user selects the cancellation reason "<CancellationReason>" from the modal
    When the user submits the cancellation
    And the user should be able to see referral status as cancelled with selected "<Reason>" reason
    Then the message should display as "<CancellationSuccessMessage>"

    Examples:
      | Reason  | CancellationReason                                 | CancellationSuccessMessage                                                                      |
      | Revoked | The referral has been paused or stopped (“Revoke”) | This referral has been cancelled so further changes might not take effect. Start a new referral |

  @NTS-TODO @E2EUI-1787 @LOGOUT
  Scenario Outline:NTS-TODO: scenario_2: View cancelled referral
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R54  |Rare Disease|GEL_SUPER_USER |
    ##Patient Search Page
    When the user is navigated to a page with title Find your patient
    When the user types in valid details of a "NGIS" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    And the user clicks the patient result card
    ##Referral Details Page
    When the user is navigated to a page with title Check your patient's details
    And the user should be able to see referral card status as cancelled with selected "<Reason>" reason
    And the user clicks the cancelled patient referral card
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient's details
    Then the user should be able to see referral status as cancelled with selected "<Reason>" reason

    Examples:
      | NhsNumber            | DOB            | Reason  |
      | NHSNumber=9449310661 | DOB=05-04-2000 | Revoked |

  @NTS-4368 @E2EUI-1212 @LOGOUT
  Scenario Outline: NTS-4368: update the 'warning' message design - Print forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R109 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "ST MARY'S HOSPITAL" in the search field
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
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=10-11-1949:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    Then the user should be able to see a "<WarningMessage>" on the print forms page

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | FamilyMembers  | PrintForms  | WarningMessage                                                                                            |
      | Patient details | Requesting organisation | Test package | 2                | Family members | Print forms | Follow local trust information governance guidelines for data protection if saving sample forms anywhere. |
