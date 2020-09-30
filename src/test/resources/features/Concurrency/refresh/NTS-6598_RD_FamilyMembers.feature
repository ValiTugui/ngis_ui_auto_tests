@Concurrency
@Refresh
@Refresh_RD
Feature: NTS-6598:RD_new_referral_FamilyMembers: Navigate and verify the changes on Family Members stage done by another user
###FLOW
  #User1 Login to new Referral
  #User2 Login to the same referral
  #User1 Updated Family Members stage for the referral
  #User2 Navigated and verify the changes done by user1 in Family Members stage

    @NTS-6598 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and updated Family members stage, when B accessed same referral then verified data updated by A.

    Given The user is login to the Test Order Service and create a new referral
      | Holoprosencephaly - NOT chromosomal | CONCURRENT_USER1_NAME | New Referral | NTS-6598_RD |
    # Referral created and completed all stages but not submitted by user1
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Rotherham Doncaster and South Humber NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package - proband only
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ##Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ##Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    Then the user updates the file NTS-6598_RD with Mandatory Stages Completed by User1
    #Family Members - Updated by User1
    And the user waits max 10 minutes for the update Notes details Updated by User2 in the file NTS-6598_RD
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                               | RelationshipToProband | DiseaseStatusDetails   |
      | NHSNumber=NA:DOB=10-03-1995:Gender=Male:Relationship=Full Sibling | Full Sibling          | DiseaseStatus=Affected |
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6598_RD with Family member details Updated by User1

    Examples:
      | FamilyMembers  | TestPackage  | OneParticipant | NoOfParticipants | ClinicalQuestionDetails                                                     | ClinicianName      | Panels | ResponsibleClinicianDetails                              |
      | Family members | Test package | 1              | 2                | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | ClinicianName=John | Panels | FirstName=Samuel:LastName=John:Department=Greenvalley,uk |

  #User2
  #Login as User B, Verified Family members and related stages and do not submit referral
  @NTS-6598 @Z-LOGOUT
  Scenario Outline: Verified Family members and related stages of new referral updated by another user
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6598_RD
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6598_RD |
    #Family members - Verified by User2
    And the user navigates to the "<Notes>" stage
    And the user updates the file NTS-6598_RD with Notes details Updated by User2
    And the user waits max 15 minutes for the update Family member details Updated by User1 in the file NTS-6598_RD
    When the user navigates to the "<FamilyMembers>" stage
    And the user should "get" participant error message as "<ErrorMessage>"
    And the user should see an error message "<ErrorMessage1>" in "<Color>" for the family member
    And the "<FamilyMembers>" stage is marked as Mandatory To Do
    And the "<PatientChoice>" stage is marked as Mandatory To Do
    And the user navigates to the "<Pedigree>" stage
    And the user should be able see the pedigree diagram loaded for the given members
      | MemberDetails               |
      | NHSNumber=NA:DOB=10-03-1995 |
    And the user updates the file NTS-6598_RD with Family member details validated by User2


    Examples:
      | Notes | FamilyMembers  | PatientChoice  | ErrorMessage                                                                                                | ErrorMessage1                                                   | Color   | Pedigree |
      | Notes | Family members | Patient choice | The number of participants you’ve selected for one or more tests does not match the number that was entered | There is essential clinical information missing from this entry | #da291c | Pedigree |