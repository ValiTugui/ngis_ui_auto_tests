@Concurrency
@Refresh
@Refresh_RD
Feature: NTS-6616:RD_new_referral_PatientChoice: Navigate and verify the changes on Patient Choice stage done by another user
###FLOW
  #User1 Login to new Referral
  #User2 Login to the same referral
  #User1 Updated Patient Choice stage for the referral
  #User2 Navigated and verify the changes done by user1 in Patient Choice stage

  @NTS-6616 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and updated Patient choice, when B accessed same referral then verified data updated by A.

    Given The user is login to the Test Order Service and create a new referral
      | Holoprosencephaly - NOT chromosomal | CONCURRENT_USER1_NAME | New Referral | NTS-6616_RD |
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
    And the user selects the number of participants as "<TwoParticipant>"
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
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    Then the user updates the file NTS-6616_RD with Mandatory Stages Completed by User1
   #Patient Choice - Updated by User1
    And the user waits max 10 minutes for the update Family members details Updated by User2 in the file NTS-6616_RD
    And the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=17-07-1965:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    And the user waits max 10 minutes for the update Family members details Updated by User2 in the file NTS-6616_RD
    And the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<TwoParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=17-07-1965 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    And the user updates the file NTS-6616_RD with Patient choice details Updated by User1
    Examples:
      | FamilyMembers  | PatientChoice  | TestPackage  | TwoParticipant | ResponsibleClinicianDetails                              | ClinicalQuestionDetails                                                     | ClinicianName      |
      | Family members | Patient choice | Test package | 2              | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | ClinicianName=John |

  #User2
  #Login as User B, Verified Patient choice stage and do not submit referral
  @NTS-6616 @Z-LOGOUT
  Scenario Outline: Verified Patient Choice stage of new referral updated by another user
   And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6616_RD
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6616_RD |
   #Patient Choice - Verified by User2
    And the user navigates to the "<FamilyMembers>" stage
    And the user updates the file NTS-6616_RD with Family members details Updated by User2
    And the user waits max 20 minutes for the update Patient choice details Updated by User1 in the file NTS-6616_RD
    When the user navigates to the "<PatientChoice>" stage
    Then the user verifies the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdated>"
    And the user updates the file NTS-6616_RD with Patient choice details validated by User2

    Examples:
      | FamilyMembers  | PatientChoice  | PatientChoiceDetailsUpdated                       |
      | Family members | Patient choice | Proband=Declined testing:Father=Agreed to testing |