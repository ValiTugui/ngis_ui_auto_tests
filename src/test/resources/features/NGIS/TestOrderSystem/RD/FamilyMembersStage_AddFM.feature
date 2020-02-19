@regression
@TO_RD
@FamilyMemberStageNavigation
@FamilyMemberStageNavigation_addFM
Feature: Family Members Navigation Stage - Member additions

  @NTS-3295 @E2EUI-1279 @E2EUI-1362 @E2EUI-1581 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-3295: Verify the family members page layout
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1999:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<Two>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And subtitle of the page displayed as Tested family members you add here will be visible in the pedigree.
    And subtitle links as add non-tested family members
    ##Added patient details on landing page covered in below step
    And the user adds "<Two>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=12-03-1942:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |

    Examples:
      | FamilyMembers  | TestPackage  | Two |
      | Family members | Test package | 2   |

  @NTS-3301 @E2EUI-1291 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3301: Verify the current additional family member information
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-09-2011:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Select tests for
    And the user should be able to see test package for family member "<FamilyMemberDetails>" is selected by default
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    And the color of referral name for "<FamilyMemberDetails>" displays as "<ReferralColor>"

    Examples:
      | FamilyMembers  | FamilyMemberDetails                                               | ReferralColor |
      | Family members | NHSNumber=NA:DOB=14-04-2011:Gender=Male:Relationship=Full Sibling | #da291c       |

  @NTS-3337 @E2EUI-1326 @E2EUI-1770 @LOGOUT @v_1 @P0
  Scenario Outline:NTS-3337: Verify the family members test package are selected by default
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1981:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    When the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Select tests for
    And the user deselects the test
    And  the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the deselected member "<FamilyMemberDetails>" status display as "<Status>"

    Examples:
      | FamilyMembers  | FamilyMemberDetails                                               | DiseaseStatusDetails     | Status           |
      | Family members | NHSNumber=NA:DOB=25-12-2000:Gender=Male:Relationship=Full Sibling | DiseaseStatus=Unaffected | Not being tested |

  @NTS-3322 @E2EUI-1509 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3322: Verify family members has completed in to-do list
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1955:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    When the user selects the number of participants as "<Two>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    And the "<TestPackage>" stage is marked as Completed
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<Two>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-02-2011:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the user is navigated to a page with title Add a family member to this referral

    Examples:
      | FamilyMember   | TestPackage  | Two |
      | Family members | Test package | 2   |