@Concurrency
@Concurrency_RD
Feature: NTS-6466: Proband patient details updated by user3, and user1 and user2 submit and verify the changes

  ###FLOW
  #User1 Login and create new Referral
  #User2 Login to the same Referral as User1
  #User3 Login to the same referral as User1 and User2
  #User3 Updates patient details of Proband
  #User1 and User2 Submit and verify the changes done by user3

  @NTS-6466 @NTS-6466_Scenario1 @Z-LOGOUT
  Scenario Outline: User A and User B are unable to submit referral, where User C  has updated the same referral, until it has been checked.
    #Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NTS-6466_Scenario1 |
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Sandwell and West Birmingham Hospitals NHS Trust" in the search field
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
    Then the user updates the file NTS-6466_Scenario1 with Mandatory Stages Completed by User1
    # Referral Submission by User1 after Patient Details updated by user 3
    And the user waits max 10 minutes for the update Patient Details Updated by User3 in the file NTS-6466_Scenario1
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    Then the user updates the file NTS-6466_Scenario1 with Patient details validated by User1
    And the user waits max 3 minutes for the update Patient details validated by User2 in the file NTS-6466_Scenario1
    # Finally User1 submit Referral Successfully
    #And the user submits the referral for Concurrency
    #Then the submission confirmation message "Your referral has been submitted" is displayed
    #And the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | PatientDetailsUpdated | TestPackage  | OneParticipant | ResponsibleClinicianDetails                              | ClinicalQuestionDetails                                                     | ClinicianName      |
      | Patient details | DOB=20-10-2010        | Test package | 1              | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | ClinicianName=John |


  #Login as User B,
  @NTS-6466 @NTS-6466_Scenario1 @Z-LOGOUT
  Scenario Outline: Update the stage of new referral created by another user
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6466_Scenario1
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-6466_Scenario1 |
    ## Referral Submission by User2 after Patient Details updated by user 3
    And the user waits max 10 minutes for the update Patient Details Updated by User3 in the file NTS-6466_Scenario1
    And the user waits max 10 minutes for the update Patient details validated by User1 in the file NTS-6466_Scenario1
    And the user submits the referral for Concurrency
    Then the user click on Reload referral button to validate the data
    When the user navigates to the "<PatientDetails>" stage
    Then the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    Then the user updates the file NTS-6466_Scenario1 with Patient details validated by User2

    Examples:
      | PatientDetails  | PatientDetailsUpdated |
      | Patient details | DOB=20-10-2010        |

   #Login as User C,

  @NTS-6466 @NTS-6466_Scenario1 @Z-LOGOUT
  Scenario Outline: Update the stage of new referral created by another user
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NTS-6466_Scenario1
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER3_NAME | New Referral | NTS-6466_Scenario1 |
    And the user navigates to the "<PatientDetails>" stage
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdated>"
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    Then the user updates the file NTS-6466_Scenario1 with Patient Details Updated by User3
    And the user waits max 10 minutes for the update Patient details validated by User1 in the file NTS-6466_Scenario1
    And the user waits max 10 minutes for the update Patient details validated by User2 in the file NTS-6466_Scenario1
    Examples:
      | PatientDetails  | PatientDetailsUpdated |
      | Patient details | DOB=20-10-2010        |

