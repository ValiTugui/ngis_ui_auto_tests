@regression
@TO_RD
@FamilyMemberStageNavigation

Feature: Global Patient Information Bar on Family Members Navigation Stage Navigation

  @NTS-3329 @E2EUI-1665 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3329: Verify Global patient information bar component
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2010:Gender=Male|
    When the user navigates to the "<Requesting organisation>" stage
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And  the Save and Continue button should be clickable
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    When the user navigates to the "<Family members>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    And the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    And the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the global patient information bar display with the editing members information "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user reads the patient details in family member landing page
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user reads the patient details in patient choice page
    And the user should see same set of family member identifiers in family member landing page and patient choice page
    When the user navigates to the "<Print forms>" stage
    And the user reads the patient details in print forms page
    Then the user should see same data in family member landing page and print forms page

    Examples:
      | Requesting organisation | ordering_entity_name | Family members | TestPackage  | NoOfParticipants | FamilyMemberDetails                 | RelationshipToProband | DiseaseStatusDetails     | Print forms |
      | Requesting organisation | Maidstone            | Family members | Test package | 2                | NHSNumber=9449305307:DOB=14-02-2011:Relationship=Full Sibling | Full Sibling          | DiseaseStatus=Unaffected | Print forms |