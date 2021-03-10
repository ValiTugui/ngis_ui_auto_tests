@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: TestOrder - Family Members Navigation Stage 6 - Varying Members addition and removal

  @NTS-3309 @Z-LOGOUT
#    @E2EUI-2104 @E2EUI-1149
  Scenario Outline: NTS-3309: Validate the user is displayed with the warning message on Family members landing page by adding extra Family member more than the expected number of participants
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1953:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1932:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=10-11-1949:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the user is navigated to a page with title Add a family member to this referral
    Then the user should "get" participant error message as "<ErrorMessage>"
    Examples:
      | stage          | TestPackage  | NoOfParticipants | ErrorMessage                                                                                                                                  |
      | Family members | Test package | 2                | The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test |