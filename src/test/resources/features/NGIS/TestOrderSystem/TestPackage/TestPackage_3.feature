#@regression
#@testPackageRD
@03-TEST_ORDER
@SYSTEM_TEST

Feature: TestOrder - Test Package 3 - RD

  @NTS-3258 @Z-LOGOUT
#    @E2EUI-1900
  Scenario Outline: NTS-3258:E2EUI-1900:Selection/deselection of test should be saved based on the submission
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage1>" stage
    And the Test Package page header is shown as "<title>"
    And the user sees the test has been selected by default
    When the user clicks a test to de-select it
    Then the user sees the test has become deselected
    When the user navigates to "<stage2>" stage section without clicking on the "save and continue" button from the "<stage1>"
    Then the user sees a warning message "<message>" on the page

    Examples:
      | stage1       | title                    | stage2         | message                                                     |
      | Test package | Confirm the test package | Family members | This section contains unsaved information. Discard changes? |

  @NTS-4540 @Z-LOGOUT
#    @E2EUI-1569
  Scenario Outline: NTS-4540:E2EUI-1569:Showing participants selected for the test - Family Member
    ##Test Package - Trio family - No of participants - 2
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants: "<NoOfParticipants>"
   # And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Family Members - Family member details to be added - creating new referrals
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1930:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the family member details on family Member landing page is correctly displayed
    And proband participant is displayed first in the list followed by "<RelationshipToProband>"
    When the user deselects the test for the "<RelationshipToProband>"
    Then the family member details on family Member landing page is shown as "<NotBeingTested>" for the participant "<RelationshipToProband>"
    And the user selects the test for the "<RelationshipToProband>"
    And the family member details on family Member landing page is shown as "<BeingTested>" for the participant "<RelationshipToProband>"
    Examples:
      | TestPackage  | NoOfParticipants | FamilyMembers  | RelationshipToProband | NotBeingTested   | BeingTested  |
      | Test package | 2                | Family members | Father                | Not Being Tested | Being Tested |

  @NTS-4700 @Z-LOGOUT
#    @E2EUI-886
  Scenario Outline: NTS-4700:E2EUI-886: New fields on Test package page
    ##Test Package
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the user is navigated to a page with title Add a requesting organisation
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user sees the test has been selected by default
     ## need to check with sir
    And the Test package page has Targeted genes section with the "<TargetedGenes>"
    And the test package page has Total number of participants drop down box
    ## need to check with sir
    And the test package page has Selected family members with the "<membersInfo>"
    And the User should be able to see the clinical indication code and name in the test package card

    Examples:
      | TestPackage  | TargetedGenes            | membersInfo |
      | Test package | Skeletal dysplasia (309) | Proband     |