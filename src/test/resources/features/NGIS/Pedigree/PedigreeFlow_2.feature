#@regression
#@pedigree_flow
@07-PEDIGREE
@SYSTEM_TEST
Feature: Pedigree - Pedigree Flow 2

  @NTS-3386 @Z-LOGOUT
#    @E2EUI-1630 @E2EUI-1051
  Scenario Outline: NTS-3464:E2EUI-1630,1051: User is making a referral and has arrived in the Pedigree section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-11-1970:Gender=Male |
    When the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should be able to see Save button on Pedigree Page
    ##Requesting Organisation
    When the user scroll to the top of landing page
    When the user navigates to the "<Requesting organisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Greater" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
     ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should be able to see SaveAndContinue button on Pedigree Page
    And the user clicks on Save and Continue on Pedigree Page
    And the "<Pedigree>" stage is marked as Completed
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | Requesting organisation | Pedigree | NoOfParticipants |
      | Requesting organisation | Pedigree | 1                |

  @NTS-3386 @Z-LOGOUT
#    @E2EUI-1194
  Scenario Outline: NTS-3386 :E2EUI-1194: Order the display of HPO Terms in Pedigree
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2006:Gender=Female |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
     ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<Two>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ##Family Member
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<Two>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=17-07-1979:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    And the user added additional phenotypes "<Phenotypes>" to the family member
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    When the user clicks on the specified node on the pedigree diagram for "<FamilyMemberDetails>"
    And the user select the pedigree tab Phenotype
    Then the user should see the hpo phenotypes "<Phenotypes>" displayed

    Examples:
      | TestPackage  | Two | FamilyMembers  | Pedigree | Phenotypes                          | FamilyMemberDetails         |
      | Test package | 2   | Family members | Pedigree | Congestive heart failure,Lymphedema | NHSNumber=NA:DOB=17-07-1979 |
