#@regression
#@pedigree_flow
@07-PEDIGREE
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: Pedigree - Pedigree Flow 3

  @NTS-3386 @Z-LOGOUT
#    @E2EUI-1854
  Scenario Outline: NTS-3386 :E2EUI-1854: Add a new RD family of female proband and 2 female daughters with no error messages in the Pedigree application.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1990:Gender=Female |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Requesting Organisation
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package
    When the user navigates to the "<Test Package>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Information
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
     ##Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    ##Family Member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                             | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=17-07-2010:Gender=Female:Relationship=Daughter | Daughter              | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=17-07-2011:Gender=Female:Relationship=Daughter | Daughter              | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able see the pedigree diagram loaded for the given members
      | MemberDetails               |
      | NHSNumber=NA:DOB=25-10-1990 |
      | NHSNumber=NA:DOB=17-07-2010 |
      | NHSNumber=NA:DOB=17-07-2011 |

    Examples:
      | Requesting organisation | ordering_entity_name                    | Test Package | NoOfParticipants | ResponsibleClinicianDetails               | ClinicalQuestionDetails                   | FamilyMembers  | Pedigree |
      | Requesting organisation | Maidstone And Tunbridge Wells NHS Trust | Test package | 3                | LastName=Smith:Department=Victoria Street | DiseaseStatus=Unaffected:AgeOfOnset=03,02 | Family members | Pedigree |
