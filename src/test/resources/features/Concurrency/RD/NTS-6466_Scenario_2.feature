@Concurrency
@Concurrency_RD_loc
Feature: NTS-6466: Different stages updated by three users and verified by another two users.
  ###FLOW
  #User1 Login with creating a New Referral
  #User2 Login to the same Referral as User1
  #User3 Login to the same referral as User1 and User2
  #User1 Updates the Requesting organisation stage
  #User2 Updates the Notes stage
  #User3 Updates Patient details stage
  #User1 and User2 verifies the changes done by User3 in Patient details stage
  #User1 and user3 verifies the changes done by User2 in Notes stage
  #User2 and User3 verifies the changes done by User1 in Requesting organisation
  @NTS-6466 @NTS-6466_Scenario2 @Z-LOGOUT
  Scenario Outline: Different stages updated by three users and verified by another two users upon referral submissions.
      #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NTS-6466_Scenario2 |
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
    Then the user updates the file NTS-6466_Scenario2 with Mandatory Stages Completed by User1
    ## Referral Submission by User1 after Patient Details updated by user 3
    And the user waits max 10 minutes for the update Patient Details Updated by User3 in the file NTS-6466_Scenario2
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    Then the user updates the file NTS-6466_Scenario2 with Patient details validated by User1
    ## Referral Submission by User1 after Notes updated by user2
    And the user waits max 10 minutes for the update Notes Updated by User2 in the file NTS-6466_Scenario2
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Notes>" stage
    Then the user verifies the stage "<Notes>" with "<NotesUpdated>"
    When the user updates the file NTS-6466_Scenario2 with Notes validated by User1
    ##########################################################
    When the user navigates to the "<RequestingOrganisation>" stage
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6466_Scenario2 with RequestingOrganisation Updated by User1
    And the user waits max 10 minutes for the update RequestingOrganisation validated by User2 in the file NTS-6466_Scenario2
    And the user waits max 10 minutes for the update Notes validated by User3 in the file NTS-6466_Scenario2
    And the user waits max 10 minutes for the update RequestingOrganisation validated by User3 in the file NTS-6466_Scenario2
    # Finally User1 submit Referral Successfully
    And the user submits the referral for Concurrency
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | PatientDetailsUpdated | NotesUpdated        | Notes | RequestingOrganisation  | RequestingOrganisationUpdated                  |RequestingOrganisation  | testPackage  | OneParticipant | ResponsibleClinician  | ClinicalQuestion   | ClinicalQuestionDetails                                                     | ResponsibleClinicianDetails                              | PatientChoiceStage | ClinicianName      |
      | Patient details | Postcode=AB1 2CD      | NotesUpdatedByUser2 | Notes | Requesting organisation | South London and Maudsley NHS Foundation Trust |Requesting organisation | Test package | 1              | Responsible clinician | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Patient choice     | ClinicianName=John |
     #Login as User B
  @NTS-6466 @NTS-6466_Scenario2 @Z-LOGOUT
  Scenario Outline: Update the stage of new referral created by another user
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6466_Scenario2
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6466_Scenario2 |
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Notes Section
    And the user waits max 10 minutes for the update Patient Details Updated by User3 in the file NTS-6466_Scenario2
    And the user waits max 10 minutes for the update Patient details validated by User1 in the file NTS-6466_Scenario2
    When the user navigates to the "<Notes>" stage
    And the user updates the stage "<Notes>" with "<NotesUpdated>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-6466_Scenario2 with Notes Updated by User2
    And the user waits max 10 minutes for the update Notes validated by User1 in the file NTS-6466_Scenario2
    ## Referral Submission by User2 after Patient Details updated by user 3
    Then the user navigates to the "<ResponsibleClinician>" stage
    Then the user navigates to the "<ClinicalQuestion>" stage
    Then the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    When the user updates the file NTS-6466_Scenario2 with Patient details validated by User2
  ###############################################################
    And the user waits max 10 minutes for the update RequestingOrganisation Updated by User1 in the file NTS-6466_Scenario2
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    When the user updates the file NTS-6466_Scenario2 with RequestingOrganisation validated by User2
    And the user waits max 10 minutes for the update Notes validated by User3 in the file NTS-6466_Scenario2
    And the user waits max 10 minutes for the update RequestingOrganisation validated by User3 in the file NTS-6466_Scenario2
    Examples:
      | PatientDetails  | Notes | NotesUpdated        | PatientDetailsUpdated | Notes | RequestingOrganisation  | RequestingOrganisationUpdated                  |ResponsibleClinician  | ClinicalQuestion   |PatientChoiceStage|
      | Patient details | Notes | NotesUpdatedByUser2 | Postcode=AB1 2CD      | Notes | Requesting organisation | South London and Maudsley NHS Foundation Trust |Responsible clinician | Clinical questions | Patient choice   |
  @NTS-6466 @NTS-6466_Scenario2 @Z-LOGOUT
  Scenario Outline: Update the stage of new referral created by another user
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6466_Scenario2
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER3_NAME | New Referral | NTS-6466_Scenario2 |
    And the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-6466_Scenario2 with Patient Details Updated by User3
    And the user waits max 10 minutes for the update Patient details validated by User1 in the file NTS-6466_Scenario2
    ## Referral Submission by User1 after Notes updated by user2
    And the user waits max 10 minutes for the update Notes Updated by User2 in the file NTS-6466_Scenario2
    And the user waits max 10 minutes for the update Patient details validated by User2 in the file NTS-6466_Scenario2
    And the user waits max 10 minutes for the update RequestingOrganisation Updated by User1 in the file NTS-6466_Scenario2
    And the user waits max 10 minutes for the update RequestingOrganisation validated by User2 in the file NTS-6466_Scenario2
    Then the user navigates to the "<ResponsibleClinician>" stage
    Then the user navigates to the "<ClinicalQuestion>" stage
    Then the user navigates to the "<PatientChoiceStage>" stage
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<Notes>" stage
    Then the user verifies the stage "<Notes>" with "<NotesUpdated>"
    When the user updates the file NTS-6466_Scenario2 with Notes validated by User3
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdated>"
    When the user updates the file NTS-6466_Scenario2 with RequestingOrganisation validated by User3
    Examples:
      | PatientDetails  | Notes | NotesUpdated        | PatientDetailsUpdated | Notes | RequestingOrganisation  | RequestingOrganisationUpdated                  |ResponsibleClinician  | ClinicalQuestion   |PatientChoiceStage|ResponsibleClinician  | ClinicalQuestion   |PatientChoiceStage|
      | Patient details | Notes | NotesUpdatedByUser2 | Postcode=AB1 2CD      | Notes | Requesting organisation | South London and Maudsley NHS Foundation Trust |Responsible clinician | Clinical questions | Patient choice   |Responsible clinician | Clinical questions | Patient choice   |



