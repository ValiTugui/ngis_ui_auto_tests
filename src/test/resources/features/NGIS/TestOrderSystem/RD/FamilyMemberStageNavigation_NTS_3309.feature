@regression
@TO_RD
@FamilyMemberStageNavigation

Feature: Family Members Navigation Stage Validation

  @NTS-3309 @E2EUI-2105 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3309: Verify warning message if number of family members is less than number of participants
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<stage>" stage
    Then the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1931:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."

    Examples:
      | stage          | TestPackage  | NoOfParticipants |
      | Family members | Test package | 3                |

  @NTS-3309 @E2EUI-2104 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3309: Validate the user is displayed with the warning message on Family members landing page by adding extra Family member more than the expected number of participants
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1931:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=10-11-1949:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."

    Examples:
      | stage          | TestPackage  | NoOfParticipants |
      | Family members | Test package | 2                |
