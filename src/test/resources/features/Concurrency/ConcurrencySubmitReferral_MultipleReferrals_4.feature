@Concurrency
@Concurrency_newReferral_RD
Feature: Submit New Referral for RD flow

  @Newreferral_RD @Z-LOGOUT
    @NTS-6470_4
  Scenario Outline: User A is unable to submit an RD family referral where User B has updated the same family member participant in another referral until it has been checked.


#Login as User A
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NRF1 |
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
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                           |
      | NHSNumber=NA:DOB=11-03-1978:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
    And the user stores the first name, last name,date of birth & Gender values
      And the user clicks the Save and Continue button
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient Choice
    Then the user navigates to the "<PatientChoice>" stage
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    #Patient Choice - Family Details Provided below should be same as above
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<TwoParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                                                                                                           | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-03-1978 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient conversation happened; form to follow |             |                 |
    Then the user is navigated to a page with title Patient choice
    Then the "<PatientChoice>" stage is marked as Completed
    Then the user updates the file NRF1 with Mandatory Stages Completed by User1
   # Referral Submission by User1 after proband details updated by user2 in FamilyMember details in another referral
    And the user waits max 10 minutes for the update PatientDetails Updated by User2 in the file NRF1
    And the user submits the referral
    Then the user sees a prompt alert "<partOfMessage>" after clicking "submit" button and click on "ReloadReferral" to validate the data
    Then the user reads & validate the familymember gender & HPO Phenotypes updated
    Then the user updates the file NRF1 with Patient details validated by User1
    # Finally User1 submit Referral Successfully
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | RequestingOrganisation  | testPackage  | OneParticipant | ResponsibleClinician  | ClinicalQuestion   | ClinicalQuestionDetails                                                     | ResponsibleClinicianDetails                              | PatientChoiceStage | ClinicianName      | partOfMessage                        | Patientdobupdated |
      | Requesting organisation | Test package | 1              | Responsible clinician | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Patient choice     | ClinicianName=John | This Referral has not been Submitted | 20-10-2010        |


  @newreferral_RD @Z-LOGOUT
  Scenario Outline: Update the patient details  in another referral

    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER2_NAME | New Referral | NRF1 |
    And the "<PatientDetails>" stage is marked as Completed
       # Requesting Organisation updated by User2
    When the user waits max 20 minutes for the update Mandatory Stages Completed by User1 in the file NRF1
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
     # When the user waits max 10 minutes for the update Patient details validated by User1 in the file NRF1
    And the user navigates to the "<RequestingOrganisation>" stage
    And the user enters the keyword "East London NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
        #Test Package - Two participants updated by User2
    And the user waits max 5 minutes for the update Requesting organisation details validated by User1 in the file NRF1
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<TwoParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
        ##Family Members - Adding two members - Father & proband  updated by User2
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with first name,last name,gender & DOB values stored in User1 family members stage
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And And the user fills in the gender
    When the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the HPO Phenotype
    And the user clicks the Save and Continue button
    And the user updates the file NRF1 with FamilyMember details Updated by User2

    Examples:
      | TwoParticipants | dateofBirthUpdated | RequestingOrganisation  | testPackage  | FamilyMembers|
      | 2               | 05-03-2020         | Requesting organisation | Test package | Family members|
