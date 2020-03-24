@securitytest
Feature: NTS-3407 - RD flow - Create New NGIS Patient Referral for Trio Family - Create Referral for Trio Family + Default Data + Add Family Members to Test + Patient Choice Not Given - Search Non Spine/NGIS Patient

    @NTS-3362 @LOGOUT @securityscan_cancer
    # E2EUI-905
    Scenario Outline: NTS-3362 - Create Referral for Proband Only - Standard user - patient choice Yes
      Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
        | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |GEL_NORMAL_USER |
      And the "<patientDetails>" stage is marked as Completed
      And the referral status from the card is "Created"
      And the user navigates to the "<requestingOrganisation>" stage
      And the user enters the keyword "Maidstone" in the search field
      And the user selects a random entity from the suggestions list
      And the details of the new organisation are displayed
      And the user clicks the Save and Continue button
      And the "<requestingOrganisation>" stage is marked as Completed
      And the user navigates to the "<testPackage>" stage
      And the user selects the "Routine"
      And the user clicks the Save and Continue button
      And the "<testPackage>" stage is marked as Completed
      And the "<responsibleClinician>" stage is selected
      And the correct "1" tests are saved to the referral in  "<testPackage>"
      And the user navigates to the "<responsibleClinician>" stage
      And the "Add clinician information" page is displayed
      And the user fills in all the clinician form fields
      And the user clicks the Save and Continue button
      And the "<responsibleClinician>" stage is marked as Completed
      And the "<tumours>" stage is selected
      And the user answers the tumour system questions fields and select a tumour type "Solid tumour: metastatic"
      And the user clicks the Save and Continue button
      And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "Recurrence"
      And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "test" result drop list
      And the user clicks the Save and Continue button
      Then the new tumour is displayed in the landing page for the existing patient with tumour list
      And the new tumour is not highlighted
      And the "<tumours>" stage is marked as Completed
      And the success notification is displayed "Tumour added"
      And the user navigates to the "<samples>" stage
      And the "<samples>" stage is selected
      Then the "Manage samples" page is displayed
      When the user clicks the Add sample button
      Then the "Add a sample" page is displayed
      When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state and filling SampleID
      And the tumour details are displayed in the Add a sample page on selecting a tumour sample type
      And the user clicks the Save and Continue button
      Then the "Add sample details" page is displayed
      When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
      And the user clicks the Save and Continue button
      And the success notification is displayed "Sample added"
      Then the "Manage samples" page is displayed
      Then the new sample is displayed in the landing page
      And on the Manage samples page, the sample table list shows the column header names
        | SampleTypeHeader | SampleStateHeader | SampleLocalLabIDHeader | SampleParentIDHeader | TumourDescriptionHeader |
        | Sample type      | State             | Local sample tube ID   | Parent ID            | Tumour description      |
      And the "<samples>" stage is marked as Completed
      And the user navigates to the "<notes>" stage
      Then the "<notes>" stage is selected
      When the user fills in the Add Notes field
      And the user clicks the Save and Continue button
      And the user navigates to the "<patientChoice>" stage
      Then the "<patientChoice>" stage is selected
      And the "<notes>" stage is marked as Completed
      When the user selects the proband
      And the user answers the patient choice questions with agreeing to testing - patient choice Yes
      And the user submits the patient choice with signature
      And the user clicks the Save and Continue button on the "<patientChoice>"

      Then the "<patientChoice>" page is displayed
      Then the help text is displayed

      Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
      When the user clicks the Save and Continue button
      Then the "<PrintForms>" stage is selected
      When the user navigates to the "<PrintForms>" stage
      Then the user is navigated to a page with title Print sample forms
#      And the user is able to download Sample form which has the correct user name, DOB , patient Id, ReferralId, Laboratory address, clinician info, Tumour info details
      And the user submits the referral
      And the submission confirmation message "Your referral has been submitted" is displayed
      And the referral status is set to "Submitted"
      Examples:
        | patientDetails  | requestingOrganisation  | testPackage  | responsibleClinician  | tumours | samples | notes | patientChoice  | PrintForms  |
        | Patient details | Requesting organisation | Test package | Responsible clinician | Tumours | Samples | Notes | Patient choice | Print forms |

  @NTS-3407 @E2EUI-895 @LOGOUT @securityscan_rd
  Scenario Outline: NTS-3407: User Journey by creating new NGIS Referral for Trio Family - By Signature
    ##Create referral with new patient without providing NHS number
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1995:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Trio family - No of participants -3
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    When the user navigates to the "<Notes>" stage
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field

    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Family member details to be added - creating new referrals
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1935:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    #patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the "<PatientChoice>"
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    #Patient Choice - Family Details Provided below should be same as above
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=14-05-1935 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    #Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    When the user clicks on VisitPanelApp link
    Then the user navigates to panelApp page
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigress
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    ##Since Pedigree is always ticked, navigating to printforms
   ##And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
#    And the user is able to download print forms for "<NoOfParticipants>" family members with the below details
#      | FamilyMemberDetails         |
#      | NHSNumber=NA:DOB=14-05-1935 |
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    When user run security scan
    Examples:
      | PatientDetails  | RequestingOrganisation  | ordering_entity_name | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | NotesDetails                                              | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  | RevokeMessage                                                             |
      | Patient details | Requesting organisation | Maidstone            | Test package | 2                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Urgent request because of deteriorating patient condition | Family members | Patient choice | Panels | Pedigree | Print forms | This referral has been cancelled so further changes might not take effect |
