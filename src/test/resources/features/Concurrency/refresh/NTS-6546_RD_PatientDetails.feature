@Concurrency
@Refresh
@Refresh_RD
Feature: NTS-6546:RD_new_referral_PatientDetails: Navigate and verify the changes on Patient details stage done by another user
###FLOW
  #User1 Login to new Referral
  #User2 Login to the same referral
  #User1 Updated Patient details stage for the referral
  #User2 Navigate and verify the changes done by user1 in Patient details stage

   @NTS-6546 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral and update the patient details stage ,Login as user B and navigates to patient details satge and verify the changes done by User A.
   #Login as User 1, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NTS-6546_RD |
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Sandwell and West Birmingham Hospitals NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
#    ##Test Package - proband only
    When the user navigates to the "<testPackage>" stage
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    And the "<testPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
#    ##Notes
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
    Then the "<PatientChoiceStage>" stage is marked as Completed
    Then the user updates the file NTS-6546_RD with Mandatory Stages Completed by User1
    And the user waits max 10 minutes for the update Requesting Organisation details Updated by User2 in the file NTS-6546_RD
    #Patient details stage updated by user1
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6546_RD with Patient details Updated by User1
    And the user waits max 10 minutes for the update Patient details Validated by User2 in the file NTS-6546_RD
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated_1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6546_RD with Patient details Updated by User1
    And the user waits max 10 minutes for the update Patient details Validated by User2 in the file NTS-6546_RD
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated_2>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6546_RD with Patient details Updated by User1
    And the user waits max 10 minutes for the update Patient details Validated by User2 in the file NTS-6546_RD
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated_3>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6546_RD with Patient details Updated by User1

    Examples:
      | PatientDetails  | PatientDetailsUpdated | PatientDetailsUpdated_1 | PatientDetailsUpdated_2 | PatientDetailsUpdated_3 |  RequestingOrganisation  | testPackage  | OneParticipant | ResponsibleClinician  | ClinicalQuestion   | ClinicalQuestionDetails                                                     | ResponsibleClinicianDetails                              | PatientChoiceStage | ClinicianName      |
      | Patient details | Title=Ms              | Title=Mr                | Title=Mrs               | Title=Mrss              |  Requesting organisation | Test package | 1              | Responsible clinician | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Patient choice     | ClinicianName=John |

  @NTS-6546 @Z-LOGOUT
  Scenario Outline: Verify Referral Banner by navigating to different stages when User A update Patient details
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6546_RD
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6546_RD |
    ##Patient Details verified by User2
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user updates the file NTS-6546_RD with Requesting Organisation details Updated by User2
    And the user waits max 10 minutes for the update Patient details Updated by User1 in the file NTS-6546_RD
    And the user clicks the Save and Continue button
    When the user navigates to the "<TestPackage>" stage
    Then the user verifies the referral header with "<ReferralBannerUpdated>"
    Then the user updates the file NTS-6546_RD with Patient details Validated by User2
    And the user waits max 10 minutes for the update Patient details Updated by User1 in the file NTS-6546_RD
    And the user clicks the Save and Continue button
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user verifies the referral header with "<ReferralBannerUpdated_1>"
    Then the user updates the file NTS-6546_RD with Patient details Validated by User2
    And the user waits max 10 minutes for the update Patient details Updated by User1 in the file NTS-6546_RD
    And the user clicks the Save and Continue button
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user verifies the referral header with "<ReferralBannerUpdated_2>"
    Then the user updates the file NTS-6546_RD with Patient details Validated by User2
    And the user waits max 10 minutes for the update Patient details Updated by User1 in the file NTS-6546_RD
    And the user clicks the Save and Continue button
    When the user navigates to the "<Notes>" stage
    Then the user verifies the referral header with "<ReferralBannerUpdated_3>"
    Then the user updates the file NTS-6546_RD with Patient details Validated by User2
    Examples:
      | RequestingOrganisation  | ReferralBannerUpdated | ReferralBannerUpdated_1 | ReferralBannerUpdated_2 | ReferralBannerUpdated_3 | TestPackage  | ResponsibleClinician  | ClinicalQuestions  | Notes |
      | Requesting organisation | Title=Ms              | Title=Mr                | Title=Mrs               | Title=Mrss              | Test package | Responsible clinician | Clinical questions | Notes |

