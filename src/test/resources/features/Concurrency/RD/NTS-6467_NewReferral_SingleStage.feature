@Concurrency
@Concurrency_RD
Feature: NTS-6467:Create New Referral for RD flow and verify the stage update messages
  ###FLOW
  #User1 creates new referral and entered all mandatory stages
  #User2 Login to the same referral
  #User2 Updated the Patient details stage (Single Stage)
  #User1 Submits the referral and observe for patient details change made by user2

  @NTS-6467 @NTS-6467_RD @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating a stage upon referral submission by A.

    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NTS-6467_RD |
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "<RequestingOrganisation>" stage
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Sandwell and West Birmingham Hospitals NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package - proband only
    When the user navigates to the "<testPackage>" stage
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
    Then the user updates the file NTS-6467_RD with Mandatory Stages Completed by User1

    #### verify patient details after changes done by B
    And the user waits max 10 minutes for the update PatientDetails Updated by User2 in the file NTS-6467_RD
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user updates the file NTS-6467_RD with Patient details validated by User1
     # Finally User1 submit Referral Successfully
    And the user submits the referral for Concurrency
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | RequestingOrganisation  | testPackage  | OneParticipant | ResponsibleClinician  | ClinicalQuestion   | ClinicalQuestionDetails                                                     | ResponsibleClinicianDetails                              | PatientChoiceStage | ClinicianName      | PatientDetails  | PatientDetailsUpdated                                                                                       | RequestingOrganisation  | ResponsibleClinician  |
      | Requesting organisation | Test package | 1              | Responsible clinician | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Patient choice     | ClinicianName=John | Patient details | FirstName=Jhon12:LastName=Peter:DOB=20-10-2010:Gender=Other:LifeStatus=Deceased:Ethnicity=B - White - Irish | Requesting organisation | Responsible clinician |


   #User2
  @NTS-6467 @NTS-6467_RD @Z-LOGOUT
  Scenario Outline: Update every stage of new referral created by another user
    And the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6467_RD
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6467_RD |
    #Below step is for new referrals
    #Patient Details - Update By user2
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6467_RD with PatientDetails Updated by User2


    Examples:
      | PatientDetails  | PatientDetailsUpdated                                                                                       |
      | Patient details | FirstName=Jhon12:LastName=Peter:DOB=20-10-2010:Gender=Other:LifeStatus=Deceased:Ethnicity=B - White - Irish |
