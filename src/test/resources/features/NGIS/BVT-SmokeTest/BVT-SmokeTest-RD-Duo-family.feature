@BVT_UI_SMOKE_TEST_PACK
@SMOKE_TEST_TD
#@userJourneysRD_BVT
#@BVT_UI_SMOKE_TEST_RD
 ##Chaning BVT back to DUO Family from Trio Family
Feature: NTS-3407-TD: Create RD Duo Family by completing - Patient Details - Requesting Organisation - Ordering Entity Name - Test Package - No Of Participants - Responsible Clinician - Responsible Clinician Details - Clinical Question - Clinical Question Details - Notes - Notes Details - Family Members - Patient Choice - Panels - Pedigree - Print Forms - Submit

  @NTS-3407-TD @Z-LOGOUT
 #@E2EUI-895
  Scenario Outline: NTS-3407: User Journey by creating new NGIS Referral for Duo Family - By Signature
    ##NGIS Version
    Given the user gets the NGIS version
    Given a web browser is at the dashboard page
    Then the user should see the banner elements based on the current environment
    And a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user has scrolled down the page to the bottom (Footer)
    Then the user can see the "Privacy Policy" link at bottom of the page
    And the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link
    ##Create referral with new patient without providing NHS number
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1998:Gender=Male:Life status=Deceased |
    ## Please remove Life status=Deceased once R441 testing is done from the above step
    And the "<PatientDetails>" stage is marked as Completed
     ##Test Order Forms
    When the user navigates to the "<testOrderForms>" stage
    Then the user is navigated to a page with title Test Order Forms
    When the user uploads the following files
      | testfile.pdf |
    When the user deletes the following files
      | testfile.pdf |
    When the user restores the following files
      | testfile.pdf |
    And the list of "Uploaded" files contains the following
      | testfile.pdf |
    #Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - No of participants -2
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
    #Notes
    When the user navigates to the "<Notes>" stage
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - creating new referrals
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1931:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    #Patient Choice - proband
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the user is navigated to a page with title Patient choice
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    #Patient Choice - Family Member
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=14-05-1931 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    Then the "<PatientChoice>" stage is marked as Completed
    #Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    ##The below step has to be updated based on the expected penetrance for that specific CI
    And the user should see the default status of penetrance button as Incomplete
    When the user clicks on VisitPanelApp link
    Then the user navigates to panelApp page
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    Then the user is navigated to a page with title Build a pedigree
    ##Since Pedigree is always ticked, navigating to print forms without any changes
    ##And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print forms for all patients
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=14-05-1931 |
    ##Submit Referral
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | testOrderForms   | RequestingOrganisation  | ordering_entity_name        | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  |
      | Patient details | Test order forms | Requesting organisation | BANBURY CROSS HEALTH CENTRE | Test package | 2                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Family members | Patient choice | Panels | Pedigree | Print forms |