#@regression
#@testPackageRD
@TEST_ORDER
@SYSTEM_TEST

Feature: Test Package page

  Background:
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed

  @NTS-3080 @LOGOUT
#    @E2EUI-911
  Scenario Outline: NTS-3080 - Test package - Page Layout - Rare Disease
    When the user navigates to the "<stage>" stage
    Then the Test Package page header is shown as "<title>"
    And the Test Package page has two "<priority>" buttons
    And the Test Package page has "<helpText>" under the priority buttons
    And  the Test package page has "<section>" header
    And the Test package page has Targeted genes section with the "<text>"
    And the Test package page has test selected for the proband with "<testInfo>"
    And the test package page has Total number of participants drop down box
    And the test package page has Selected family members with the "<membersInfo>"
    Examples:
      | stage        | title                    | priority        | helpText                                                                          | section        | text                         | testInfo           | membersInfo |
      | Test package | Confirm the test package | Urgent, Routine | Choose Urgent if you want the laboratory to prioritise some or all of your tests. | Selected tests | Cerebral malformations (491) | Routine, Singleton | Proband     |


  @NTS-3109 @LOGOUT
#    @E2EUI-2139
  Scenario Outline: NTS-3109 - Test package - verify Warning Messages of unsaved changes - Moving to another To do list - Rare-Disease
    And the user navigates to the "<stage>" stage
    And the user selects the number of participants as "<number>"
    When the user navigates to the "<new_stage>" stage
    Then the user sees a warning message on the page

    Examples:
      | stage        | number | new_stage             |
      | Test package | 2      | Responsible clinician |


  @NTS-3109 @LOGOUT
#    @E2EUI-2139
  Scenario Outline: NTS-3109 - Test package - verify Warning Messages of unsaved changes - Browser "<browser_exit_option>" - Rare-Disease
    And the user navigates to the "<stage>" stage
    And the user selects the number of participants as "<number>"
    When the user attempts to navigate away by clicking "<browser_exit_option>"
    Then the user sees a warning message on the page

    Examples:
      | stage        | number | browser_exit_option |
      | Test package | 2      | refresh             |


  @NTS-3156 @LOGOUT
#    @E2EUI-828 @E2EUI-1585
  Scenario Outline: NTS-3156 - Test package - selecting Urgent for the question priority of the test - Rare-Disease
    And the user navigates to the "<stage>" stage
    When the user selects the "<priority>"
    And the user selects the number of participants: "<count>"
    Then the user clicks the Save and Continue button
    And the "<stage>" stage is marked as Completed
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    Examples:
      | stage        | priority | count | new_stage             | number_of |
      | Test package | Urgent   | 2     | Responsible clinician | 1         |

  @NTS-3177 @LOGOUT
#    @E2EUI-1547 @E2EUI-1585
  Scenario Outline: NTS-3177 - Test package - To Do list should be Mandatory To Do - verify permissible Number of Participants
    And the user navigates to the "<stage>" stage
    And the Test Package page header is shown as "<title>"
    And The user sees a drop down box for the Total number of participants
    And the drop down box is displayed as empty by default
    When the user clicks on the drop down box to see the values between "<minParticipants>" - "<maxParticipants>" displayed
    And the "<stage>" stage is marked as Mandatory To Do
    When the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Mandatory To Do
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    Examples:
      | stage        | title                    | minParticipants | maxParticipants | new_stage             | number_of |
      | Test package | Confirm the test package | 1               | 8               | Responsible clinician | 1         |


  @NTS-3177 @LOGOUT
#    @E2EUI-1547 @E2EUI-1585
  Scenario Outline: NTS-3177 - Test package - To Do list should be Mandatory To Do
    And the user navigates to the "<stage>" stage
    And the Test Package page header is shown as "<title>"
    And The user sees a drop down box for the Total number of participants
    And the drop down box is displayed as empty by default
    When the user does not select one of the values
    And the user sees an error message "<errorMessage>"
    And the "<stage>" stage is marked as Mandatory To Do
    And the user selects the number of participants: "<count>"
    When the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Completed
    And the "<new_stage>" stage is selected
    And the correct "<number_of>" tests are saved to the referral in  "<stage>"
    And the correct "<count>" of participants are saved to the referral in "<stage>"
    Examples:
      | stage        | title                    | errorMessage                                                     | count | new_stage             | number_of |
      | Test package | Confirm the test package | Select the total number of participants you expect for this test | 2     | Responsible clinician | 1         |

  @NTS-3253 @LOGOUT
#    @E2EUI-1585
  Scenario Outline: NTS-3253 - Test package - If the test is de-selected then Total number of participants field should disappear
    And the user navigates to the "<stage>" stage
    And the Test Package page header is shown as "<title>"
    When the user clicks a test to de-select it
    Then the user sees the test has become deselected
    And the Total number of participants field is disappeared for the deselected test

    Examples:
      | stage        | title                    |
      | Test package | Confirm the test package |

  @NTS-3258 @LOGOUT
#    @E2EUI-1900
  Scenario Outline: NTS-3258 - Test package - Selection/deselection of test should be saved based on the submission
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


  @NTS-4540 @LOGOUT
#    @E2EUI-1569
  Scenario Outline: NTS-4540 - Test package - Showing participants selected for the test - Family Member
    ##Test Package - Trio family - No of participants - 2
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

  @NTS-4700 @LOGOUT
#    @E2EUI-886
  Scenario Outline: NTS-4700 : New fields on Test package page
    ##Test Package
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