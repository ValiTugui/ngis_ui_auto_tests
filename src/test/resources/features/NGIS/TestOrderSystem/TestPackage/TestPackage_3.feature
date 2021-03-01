@03-TEST_ORDER
@TestPackage
@SYSTEM_TEST
@TestPackage

Feature: TestOrder - Test Package 3 - RD

  @NTS-3070 @NTS-3823 @Z-LOGOUT
#    @E2EUI-1316 @E2EUI-1123
  Scenario Outline: NTS-3070 - Test package - Urgency selection
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    And the "Patient details" stage is marked as Completed
    And the user navigates to the "<stage>" stage
    And the Test Package page priority header has "<priority_label>"
    When the user selects the "<priority>"
    And the Test Package page "<previous_priority>" is de-selected
    And the Test Package page has the help text as "<help_text>" on the page
    Then the user clicks the Save and Continue button
    And the "<new_stage>" stage is selected
    And the "<stage>" stage is marked as Completed
    And the correct "<number_of>" tests are saved to the referral in  "<stage>" with the chosen "<priority>"
    Then the user see a tick mark next to the chosen "<priority>"

    Examples:
      | stage        | priority | previous_priority | new_stage             | priority_label                         | help_text                                                                         | number_of |
      | Test package | Urgent   | Routine           | Responsible clinician | What is the priority of your referral? | Choose Urgent if you want the laboratory to prioritise some or all of your tests. | 1         |

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
    Then the family member details on family Member landing page is shown as "<BeingTested>" for the participant "<RelationshipToProband>"

    Examples:
      | TestPackage  | NoOfParticipants | FamilyMembers  | RelationshipToProband | NotBeingTested   | BeingTested  |
      | Test package | 2                | Family members | Father                | Not Being Tested | Being Tested |

