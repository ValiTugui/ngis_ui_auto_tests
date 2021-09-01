@Concurrency
@Concurrency_RD
Feature: NTS-7213:RD_new_referral_same_stage

  ###FLOW
  #User1 Login and create New Referral
  #User2 Login to the same referral as user1
  #User1 Update any field in each stage for the referral
  #User2 Update same stage as user1 and verify the alert message while saving the stage

  @NTS-7213 @Z-LOGOUT
  Scenario Outline: Login as User A,Create a New Referral, Complete all stages and do not submit referral,and validate the data updated, when B is updating every stage upon referral submission by A.

#   Login as User A, Complete all stages and do not submit referral
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NTS-7213 |
#   Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Wye Valley NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
#   Test Package - proband only - No of participants -2
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<TwoParticipant>"
    And the user clicks the Save and Continue button
#   Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
#   Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
#   Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
#   Family Members -
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=11-01-1971:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Unaffected:AgeOfOnset=02,02:HpoPhenoType=Dystonia |
    And the user clicks the Save and Continue button
    ##Patient Choice - Proband
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Patient conversation happened; form to follow for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
#   Patient Choice - Family Details Provided below should be same as above
    When the user edits patient choice for "<TwoParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-01-1971 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
#   Panels
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
#   Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
#   Print forms
    Then the user is navigated to a page with title Print sample forms
    Then the user updates the file NTS-7213 with Mandatory Stages Completed by User1
#   Patient Details - Update
    And the user waits max 20 minutes for the update User2 navigated to PatientDetails in the file NTS-7213
    When the user navigates to the "<PatientDetails>" stage
    Then the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7213 with PatientDetails Updated by User1
#   Requesting Organization - Update
    And the user waits max 10 minutes for the update Patient details validated by User2 in the file NTS-7213
    And the user refresh the browser
    Then the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7213 with Requesting Organisation updated by User1
#   Test Package - Update
    And the user waits max 10 minutes for the update Requesting Organisation validated by User2 in the file NTS-7213
    And the user refresh the browser
    And the user updates the stage "<TestPackage>" with "<TestPackageUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7213 with Test Package details updated by User1
#   Responsible Clinician - Update
    And the user waits max 8 minutes for the update Test Package details validated by User2 in the file NTS-7213
    And the user refresh the browser
    Then the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7213 with Responsible Clinician details updated by User1
#   ClinicalQuestions - Update
    And the user waits max 8 minutes for the update Responsible Clinician details validated by User2 in the file NTS-7213
    And the user refresh the browser
    Then the user updates the stage "<ClinicalQuestions>" with "<ClinicalQuestionDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-7213 with Clinical Questions updated by User1
#   Notes - Update
    And the user waits max 8 minutes for the update Clinical Questions details validated by User2 in the file NTS-7213
    And the user refresh the browser
    Then the user updates the stage "<Notes>" with "<NotesUpdatedByUser1>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-7213 with Notes details updated by User1
#   FamilyMembers - Update
    And the user waits max 8 minutes for the update Notes details validated by User2 in the file NTS-7213
    And the user refresh the browser
    Then the user updates the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Select tests for
    Then the user updates the file NTS-7213 with Family Member patient details updated by User1
#   FamilyMembers Test package- Update
    And the user waits max 8 minutes for the update Family Member patient details validated by User2 in the file NTS-7213
    And the user refresh the browser
    And the user deselects the test
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-7213 with Family Member Test Package updated by User1
#   Add family member details page - Update
    And the user waits max 8 minutes for the update Family Member Test Package validated by User2 in the file NTS-7213
    And the user refresh the browser
    Then the user updates the family member clinical details with "DiseaseStatus=Uncertain"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Patient choice
    Then the user updates the file NTS-7213 with Family Member clinical details updated by User1
#   PatientChoice- Update
    And the user waits max 15 minutes for the update Family Member clinical details validated by User2 in the file NTS-7213
    And the user refresh the browser
    Then the user updates the stage "<PatientChoice>" with "<PatientChoiceDetailsUpdatedByUser1>"
    Then the user updates the file NTS-7213 with Patient Choice details updated by User1

    Examples:
      | PatientDetails  | PatientDetailsUpdatedByUser1 | RequestingOrganisation  | RequestingOrganisationUpdatedByUser1           | TestPackage  | TestPackageUpdatedByUser1 | ResponsibleClinician  | ResponsibleClinicianDetailsUpdatedByUser1 | ClinicalQuestions  | ClinicalQuestionDetailsUpdatedByUser1 | Notes | NotesUpdatedByUser1 | FamilyMembers  | FamilyMemberDetailsUpdatedByUser1               | PatientChoice  | PatientChoiceDetailsUpdatedByUser1 | TwoParticipant | ResponsibleClinicianDetails                              | ClinicalQuestionDetails                                                     | RecordedBy         |
      | Patient details | FirstName=Jhon12             | Requesting organisation | South London and Maudsley NHS Foundation Trust | Test package | Priority=Urgent           | Responsible clinician | FirstName=Edward                          | Clinical questions | AgeOfOnset=1,1                        | Notes | NotesUpdatedByUser1 | Family members | Ethnicity=D - Mixed - White and Black Caribbean | Patient choice | Proband=Authorised by clinician    | 2              | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | ClinicianName=John |

#  User2
  @NTS-7213 @Z-LOGOUT
  Scenario Outline: Update entity in every stage of new referral created by another user
    And the user waits max 30 minutes for the update Mandatory Stages Completed by User1 in the file NTS-7213
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-7213 |
#    Patient Details - Update and verify
    When the user navigates to the "<PatientDetails>" stage
    And the user updates the file NTS-7213 with User2 navigated to PatientDetails
    Then the user waits max 20 minutes for the update PatientDetails Updated by User1 in the file NTS-7213
    And the user updates the stage "<PatientDetails>" with "<PatientDetailsUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Check your patient's details
    And the user verifies the stage "<PatientDetails>" with "<PatientDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7213 with Patient details validated by User2
#   Requesting Organisation - Update and verify
    And the user waits max 10 minutes for the update Requesting Organisation updated by User1 in the file NTS-7213
    And the user updates the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Add a requesting organisation
    And the user verifies the stage "<RequestingOrganisation>" with "<RequestingOrganisationUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7213 with Requesting Organisation validated by User2
#   Test Package - Update and verify
    And the user waits max 10 minutes for the update Test Package details updated by User1 in the file NTS-7213
    And the user deselects the test
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Confirm the test package
    And the user verifies the stage "<TestPackage>" with "<TestPackageUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7213 with Test Package details validated by User2
#   Responsible Clinician- Update and verify
    And the user waits max 10 minutes for the update Responsible Clinician details updated by User1 in the file NTS-7213
    And the user updates the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Add clinician information
    And the user verifies the stage "<ResponsibleClinician>" with "<ResponsibleClinicianDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user updates the file NTS-7213 with Responsible Clinician details validated by User2
#   ClinicalQuestions - Update and verify
    And the user waits max 8 minutes for the update Clinical Questions updated by User1 in the file NTS-7213
    Then the user updates the stage "<ClinicalQuestions>" with "<ClinicalQuestionDetailsUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Answer clinical questions
    And the user verifies the stage "<ClinicalQuestions>" with "<ClinicalQuestionDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-7213 with Clinical Questions details validated by User2
#   Notes - Update and verify
    And the user waits max 8 minutes for the update Notes details updated by User1 in the file NTS-7213
    Then the user updates the stage "<Notes>" with "<NotesUpdatedByUser2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    Then the user is navigated to a page with title Add clinical notes
    And the user verifies the stage "<Notes>" with "<NotesUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user clicks on edit button of Family member
    And the user clicks on edit patient details
    Then the user updates the file NTS-7213 with Notes details validated by User2
#   FamilyMembers - Update and verify
    And the user waits max 15 minutes for the update Family Member patient details updated by User1 in the file NTS-7213
    And the user fills in the Postcode field box with "AB1 2CD"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    And the user clicks on edit button of Family member
    Then the user is navigated to a page with title Continue with this family member
    Then the user is navigated to a page with title Edit patient details
    And the user verifies the stage "<FamilyMembers>" with "<FamilyMemberDetailsUpdatedByUser1>"
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-7213 with Family Member patient details validated by User2
#   FamilyMembers Test Package - Update and verify
    And the user waits max 15 minutes for the update Family Member Test Package updated by User1 in the file NTS-7213
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    And the user clicks on edit button of Family member
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    Then the user updates the file NTS-7213 with Family Member Test Package validated by User2
#   Add family member details page - Update and verify
    And the user waits max 15 minutes for the update Family Member clinical details updated by User1 in the file NTS-7213
    Then the user updates the family member clinical details with "DiseaseStatus=Unknown"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    And the user is navigated to a page with title Add family member details
    And the user clicks the Save and Continue button
    And the user clicks the Save and Continue button
    And the user edits the patient choice status
    And the user clicks on the amend patient choice button
    Then the user updates the file NTS-7213 with Family Member clinical details validated by User2
#   PatientChoice- update and verify
    And the user waits max 15 minutes for the update Patient Choice details updated by User1 in the file NTS-7213
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    When the user fills PatientSignature details in patient signature
    And the user should be able to see concurrency alert message while submitting the patient choice
    And the user refresh the browser
    And the user navigates to the "<PatientChoice>" stage
    And the user verifies the stage "<PatientChoice>" with "<PatientChoiceUpdatedByUser1>"
    Then the user updates the file NTS-7213 with Patient Choice details validated by User2

    Examples:
      | PatientDetails  | PatientDetailsUpdatedByUser2 | PatientDetailsUpdatedByUser1 | RequestingOrganisation  | RequestingOrganisationUpdatedByUser2 | RequestingOrganisationUpdatedByUser1           | TestPackage  | TestPackageUpdatedByUser1 | ResponsibleClinician  | ResponsibleClinicianDetailsUpdatedByUser2 | ResponsibleClinicianDetailsUpdatedByUser1 | ClinicalQuestions  | ClinicalQuestionDetailsUpdatedByUser2 | ClinicalQuestionDetailsUpdatedByUser1 | FamilyMemberDetailsUpdatedByUser1               | Notes | NotesUpdatedByUser2 | NotesUpdatedByUser1 | PatientChoiceUpdatedByUser1     | RequestingOrganisation  | ResponsibleClinician  | PatientChoice  | FamilyMembers  | RecordedBy         |
      | Patient details | FirstName=Tim                | FirstName=Jhon12             | Requesting organisation | West London Mental Health NHS Trust  | South London and Maudsley NHS Foundation Trust | Test package | Priority=Urgent           | Responsible clinician | FirstName=Sam                             | FirstName=Edward                          | Clinical questions | AgeOfOnset=2,2                        | AgeOfOnset=1,1                        | Ethnicity=D - Mixed - White and Black Caribbean | Notes | NotesUpdatedByUser1 | NotesUpdatedByUser1 | Proband=Authorised by clinician | Requesting organisation | Responsible clinician | Patient choice | Family members | ClinicianName=John |

     ###FLOW
  #User1 Login and create New Referral
  #User2 Login to the same referral as user1
  #User1 Add a NGIS patient as a Family member to the referral
  #User1 Provide a relationship to proband (Add missing family member details page)
  #User2 Update same stage as user1 and verify the alert message while saving the stage

  @NTS-7261 @Z-LOGOUT
  Scenario Outline: Verify the concurrency error on Add missing family member details page
    Given The user is login to the Test Order Service and create a new referral
      | Rare syndromic craniosynostosis or isolated multisuture synostosis | CONCURRENT_USER1_NAME | New Referral | NTS-7261 |
#   Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Wye Valley NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
#   Test Package - proband only - No of participants -2
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<TwoParticipant>"
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the user clicks the Yes button in family member search page
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    Then a "<PatientType>" result is successfully returned
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    Then the user updates the file NTS-7261 with Reached missing family member details page by User1
    And the user waits max 20 minutes for the update Reached missing family member details page by User2 in the file NTS-7261
    Then the user should "get" participant error message as "This record is missing essential information"
    When the user selects the Relationship to proband as "<RelationshipToProband1>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    Then the user updates the file NTS-7261 with Relationship to proband updated by User1
    And the user waits max 10 minutes for the update Dismissed the concurrency error by User2 in the file NTS-7261

    Examples:
      | TestPackage  | TwoParticipant | FamilyMembers  | FamilyMemberDetails                 | PatientType | RelationshipToProband1 |
      | Test package | 2              | Family members | NHSNumber=9449306613:DOB=15-02-2009 | NGIS        | Father                 |


  @NTS-7261 @Z-LOGOUT
  Scenario Outline: Update the relationship to proband field in Add missing family member details page and verify the concurrency error
    And the user waits max 30 minutes for the update Reached missing family member details page by User1 in the file NTS-7261
    Given The user is login to the Test Order Service and access the given referral
      | CONCURRENT_USER2_NAME | New Referral | NTS-7261 |
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the display question for NHS Number of the family member search page is Do you have the family member’s NHS Number?
    And the user clicks the Yes button in family member search page
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    Then a "<PatientType>" result is successfully returned
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    Then the user updates the file NTS-7261 with Reached missing family member details page by User2
    And the user waits max 10 minutes for the update Relationship to proband updated by User1 in the file NTS-7261
    Then the user should "get" participant error message as "This record is missing essential information"
    When the user selects the Relationship to proband as "<RelationshipToProband2>"
    Then the user should be able to see concurrency alert message while saving the stage
    And the user refresh the browser
    And the user clicks on edit button of Family member
    Then the user is navigated to a page with title Continue with this family member
    And the user sees the relationship to proband selected is same as "<RelationshipToProband1>"
    Then the user updates the file NTS-7261 with Dismissed the concurrency error by User2

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | PatientType | RelationshipToProband2 | RelationshipToProband1 |
      | Family members | NHSNumber=9449306613:DOB=15-02-2009 | NGIS        | Mother                 | Father                 |
