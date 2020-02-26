@regression
@pedigree
@pedigree_uiCustomization
Feature: Pedigree - UI Customizations

  @NTS-3384 @E2EUI-1226 @E2EUI-1948 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3384: UI Customizations: NGIS Patient -  Disease status
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1970:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    Then the user should see the below messages displayed in the pedigree page
      | The pedigree automatically shows test participants including:                                                            |
      | To make changes related to these participants, update the patient details and family members sections, not the pedigree. |
      | Update the pedigree to add additional family members who are not being tested as part of this referral.                  |
      | Do not include any personal identifiable information in the pedigree.                                                    |
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user clicks on the specified node on the pedigree diagram for "<FamilyMemberDetails>"
    And the user select the pedigree tab Clinical
    Then the user should see below fields on Clinical Tab with the given status
      | FieldName        | FieldStatus  |
      | Age at Onset (Y) | Non-Editable |
      | Age at Onset (m) | Non-Editable |
      | Disease status   | Non-Editable |
    Examples:
      | PedigreeStage | FamilyMemberDetails         | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1970 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-3464 @E2EUI-1754 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3464 : Clinical Indication is displayed properly on Pedigree tool.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2008:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Clinical Information
    When the user navigates to the "<ClinicalStage>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add notes to this referral
     ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    When the user clicks on the specified node on the pedigree diagram for "<FamilyMemberDetails>"
    And the user select the pedigree tab Clinical
    Then the below field values should be displayed properly on Clinical Tab
      | FieldName                |
      | Clinical Indication Name |
    Examples:
      | ClinicalStage      | FamilyMemberDetails         | ClinicalQuestionDetails                 | Pedigree |
      | Clinical questions | NHSNumber=NA:DOB=25-10-2008 | DiseaseStatus=Affected:AgeOfOnset=03,02 | Pedigree |

  @NTS-3464 @E2EUI-1630 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-TODO:User is making a referral and has arrived in the Pedigree section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R55 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2000:Gender=Female |
    When the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should be able to see Save button on Pedigree Page
    ##Requesting Organisation
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Manchester" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
     ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should be able to see SaveAndContinue button on Pedigree Page
    And the user clicks the Save and Continue button
    And the "<Pedigree>" stage is marked as Completed
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | Requesting organisation | Pedigree | NoOfParticipants |
      | Requesting organisation | Pedigree | 1                |
