@regression
@pedigree
@pedigree_uiCustomization
Feature: Pedigree - UI Customizations

  @E2EUI-1226 @E2EUI-1948 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-TODO: UI Customizations: NGIS Patient -  Disease status
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
      | PedigreeStage | FamilyMemberDetails         |WarningMessage|
      | Pedigree      | NHSNumber=NA:DOB=25-11-1970 |Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |
