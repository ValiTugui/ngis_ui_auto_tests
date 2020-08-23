@Concurrency
@Concurrency_RDReferral_ThreeUsers

Feature: Submit Referral for RD

  @Newreferral_RD @Z-LOGOUT
    @NTS-6466_1
  Scenario Outline:User A and User B are unable to submit referral, where User C  has updated the same referral, until it has been checked.

#Login as User A, Complete all stages and do not submit referral

    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NRF1 |
#    ##Patient Details
#    Then the user is navigated to a page with title Check your patient's details
#    And the user clicks the Save and Continue button
#    And the "<PatientDetails>" stage is marked as Completed
#    ##Requesting Organisation
#    Then the user is navigated to a page with title Add a requesting organisation
#    And the user enters the keyword "Sandwell and West Birmingham Hospitals NHS Trust" in the search field
#    And the user selects a random entity from the suggestions list
#    Then the details of the new organisation are displayed
#    And the user clicks the Save and Continue button
#    And the "<RequestingOrganisation>" stage is marked as Completed
#    ##Test Package - proband only
#    When the user navigates to the "<TestPackage>" stage
#    And the user selects the number of participants as "<OneParticipant>"
#    And the user clicks the Save and Continue button
#    And the "<TestPackage>" stage is marked as Completed
#    ##Responsible Clinician
#    Then the user is navigated to a page with title Add clinician information
#    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
#    And the user clicks the Save and Continue button
#    And the "<ResponsibleClinician>" stage is marked as Completed
#    ##Clinical Question
#    Then the user is navigated to a page with title Answer clinical questions
#    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
#    And the user clicks the Save and Continue button
#    Then the "<ClinicalQuestion>" stage is marked as Completed
#    ##Notes
#    Then the user is navigated to a page with title Add clinical notes
#    And the user fills in the Add Notes field
#    And the user clicks the Save and Continue button
#    Then the "<Notes>" stage is marked as Completed
#    ##Family Members
#    Then the user is navigated to a page with title Add a family member to this referral
#    And the user clicks the Save and Continue button
#    ##Patient Choice
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    Then the user is navigated to a page with title Add patient choice information
#    When the user selects the option Adult (With Capacity) in patient choice category
#    When the user selects the option Rare & inherited diseases – WGS in section Test type
#    When the user fills "<ClinicianName>" details in recorded by
#    And the user clicks on Continue Button
#    When the user is in the section Patient choices
#    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
#    When the user selects the option Yes for the question Has research participation been discussed?
#    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
#    And the user clicks on Continue Button
#    When the user is in the section Patient signature
#    And the user fills PatientSignature details in patient signature
#    And the user clicks on submit patient choice Button
#    Then the user should be able to see the patient choice form with success message
#    And the user clicks the Save and Continue button
#    Then the "<PatientChoiceStage>" stage is marked as Completed
#    ##Panels
#    When the user navigates to the "<Panels>" stage
#    Then the user is navigated to a page with title Manage panels
#    And the user clicks the Save and Continue button
#    Then the "<Panels>" stage is marked as Completed
#    ##Pedigree
#    Then the user is navigated to a page with title Build a pedigree
#    And the user clicks the Save and Continue button
#    Then the "<Pedigree>" stage is marked as Completed
#    ##Print forms
#    And  the user is navigated to a page with title Print sample forms
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
    # Referral Submission by User1 after Patient Details updated by user 3
    And the user waits max 3 minutes for the update Patient Details Updated by User3 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
#    Then the user reads & validate the patient date of birth entered by user3
    Then the user updates the file NRF1 with Patient details validated by User1
    And the user waits max 3 minutes for the update Patient details validated by User2 in the file NRF1
    # Finally User1 submit Referral Successfully
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | RequestingOrganisation  | testPackage  | OneParticipant | ResponsibleClinician  | ClinicalQuestion   | ClinicalQuestionDetails                                                     | ResponsibleClinicianDetails                              | Notes | PatientChoiceStage | ClinicianName      | Panels | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree |


#Login as User B,

  @newreferral_RD @Z-LOGOUT

  Scenario Outline: Update the stage of new referral created by another user
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NRF1 |
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Notes Section
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
## Referral Submission by User2 after Patient Details updated by user 3
    And the user waits max 3 minutes for the update Patient Details Updated by User3 in the file NRF1
    And the user waits max 3 minutes for the update Patient details validated by User1 in the file NRF1
    And the user submits the referral
    Then the user click on Reload referral button to validate the data
#    Then the user reads & validate the patient date of birth entered by user3
    Then the user updates the file NRF1 with Patient details validated by User2

    Examples:
      | PatientDetails  | notes | testPackage  |
      | Patient details | Notes | Test package |

#Login as User C,

  @newreferral_RD @Z-LOGOUT

  Scenario Outline: Update the stage of new referral created by another user

    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER3_NAME | New Referral | NRF1 |
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    When the user waits max 25 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    And the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user fills in the date of birth "01-03-2002"
    And the user clicks the Save and Continue button on Patient details page
    And the "<PatientDetails>" stage is marked as Completed
    Then the user updates the file NRF1 with Patient Details Updated by User3
    And the user waits max 3 minutes for the update Patient details validated by User1 in the file NRF1
    And the user waits max 3 minutes for the update Patient details validated by User2 in the file NRF1
    Examples:
      | PatientDetails  |
      | Patient details |




