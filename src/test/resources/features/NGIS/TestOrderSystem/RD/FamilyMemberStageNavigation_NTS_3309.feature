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
    When the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."

    Examples:
      | stage          | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 3                | NHSNumber=9449305307:DOB=14-02-2011 | Full Sibling          | DiseaseStatus=Unaffected |

  @NTS-3309 @E2EUI-2104 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3309: Validate the user is displayed with the warning message on Family members landing page by adding extra Family member more than the expected number of participants
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<stage>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails1>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the family member test package page is correctly displayed
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails2>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the default family member details page is correctly displayed
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user should be able to see test package for family member is selected by default
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user should see a warning message displayed as "The number of participants you’ve selected for one or more tests does not match the number that was entered. Check participants for each test or amend the expected number of participants."

    Examples:
      | stage          | TestPackage  | NoOfParticipants | FamilyMemberDetails1                | FamilyMemberDetails2                | RelationshipToProband | DiseaseStatusDetails     |
      | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011 | NHSNumber=9449310343:DOB=02-03-2008 | Full Sibling          | DiseaseStatus=Unaffected |
