#@userJourneys
#@userJourneysRD
#@userJourneysRD_SPINE_TrioFamily
@SYSTEM_INTEGRATION_TEST_SPINE
Feature: Create Referrals for SPINE Patient - Trio Family

  @NTS-4592 @Z-LOGOUT
#    @E2EUI-1195 @UseCase08
  Scenario Outline: NTS-4592: Use Case#08: Create Referral for Trio Family + Default Data + Add Family Members to Test + Patient Choice Yes- Search Spine Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R84 | GEL_NORMAL_USER | NHSNumber=9449310084:DOB=20-10-1973 |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "London North West Healthcare NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only - No of participants -3
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
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
    ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                           |
      | NHSNumber=NA:DOB=11-03-1977:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
      | NHSNumber=NA:DOB=12-02-1980:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Patient Choice
    When the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the user is navigated to a page with title Patient choice
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails         | PatientChoiceCategory | RecordedBy                                                                                                           |
      | NHSNumber=NA:DOB=11-03-1977 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=12-02-1980 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    And the user clicks the Save and Continue button
    Then the "<PatientChoice>" stage is marked as Completed
    ##Panels
    Then the user is navigated to a page with title Panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
     ###Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##PrintForms
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print forms for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=11-03-1977 |
      | NHSNumber=NA:DOB=12-02-1980 |
    ##Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                            | Notes | FamilyMembers  | PatientChoice  | Panels | searchPanels      | Pedigree |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=William:LastName=John:Department=Minister road | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=15,05:HpoPhenoType=Hydrocephalus | Notes | Family members | Patient choice | Panels | Holoprosencephaly | Pedigree |

  @NTS-4560 @Z-LOGOUT
#    @E2EUI-1244 @UseCase09
  Scenario Outline: NTS-4560: Use Case#09: Create Referral for Trio Family + Edit Data + Add Family Members to Test + Patient Choice Yes - Search Spine Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449310084:DOB=20-10-1973 |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Rotherham Doncaster and South Humber NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Information
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=17-07-1965:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=19-12-1970:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient Choice
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoiceStage>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails         | PatientChoiceCategory | RecordedBy                            |
      | NHSNumber=NA:DOB=17-07-1965 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123 |
      | NHSNumber=NA:DOB=19-12-1970 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123 |
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    ##Submit Referral
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | Panels | Pedigree | FamilyMembers  | searchPanels |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | Panels | Pedigree | Family members | Cataracts    |

  @NTS-4567 @Z-LOGOUT
#    @E2EUI-1263 @UseCase10
  Scenario Outline: NTS-4567: Use Case#10: Create Referral for Trio Family + Default Data + Add Family Members to Test + Patient Choice No - Search Spine Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R84 | GEL_NORMAL_USER | NHSNumber=9449310084:DOB=20-10-1973 |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone And Tunbridge Wells NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Trio family - No of participants 3
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
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
    ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Adding two members - Father and Mother
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                               | RelationshipToProband | DiseaseStatusDetails                                           |
      | NHSNumber=NA:DOB=11-03-2017:Gender=Male:Relationship=Full Sibling | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
      | NHSNumber=NA:DOB=12-02-1940:Gender=Female:Relationship=Mother     | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##patient choice for the proband
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    ##Patient Choice - Family Details Provided below same as the Family Members
    Then the user is navigated to a page with title Patient choice
    ##Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                                                                                                           | PatientChoice                                      | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-03-2017 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient changed their mind about the clinical test |             |                 |
      | NHSNumber=NA:DOB=12-02-1940 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient changed their mind about the clinical test |             |                 |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - FamilyDetails -same as provided above Family details
    Then the user is navigated to a page with title Print sample forms
    ##Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                        | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | RecordedBy                            |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis | Notes | Family members | Patient choice | Panels | Pedigree | ClinicianName=John:HospitalNumber=123 |

  @NTS-4607 @Z-LOGOUT
#    @E2EUI-922 @UseCase11
  Scenario Outline: NTS-4607: Use Case#11: Create Referral for Trio Family + Edit Data + Add Family Members to Test + Patient Choice No - Search Spine Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449310084:DOB=20-10-1973 |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - Trio family - No of participants 3
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ###Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ###Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ###Family Members - Adding two members - Father and Mother
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                               | RelationshipToProband | DiseaseStatusDetails                                           |
      | NHSNumber=NA:DOB=11-03-2017:Gender=Male:Relationship=Full Sibling | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
      | NHSNumber=NA:DOB=12-02-1940:Gender=Female:Relationship=Mother     | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###patient choice for the proband
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    Then the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                                                                                                           | PatientChoice                                      | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=11-03-2017 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient changed their mind about the clinical test |             |                 |
      | NHSNumber=NA:DOB=12-02-1940 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf | Patient changed their mind about the clinical test |             |                 |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###Panels
    Then the user is navigated to a page with title Panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ###Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ###Print forms
    When the user navigates to the "<PrintFormsStage>" stage
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                        | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | searchPanels | PrintFormsStage |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis | Notes | Family members | Patient choice | Panels | Pedigree | Cataracts    | Print forms     |

  @NTS-4577 @Z-LOGOUT
#     @E2EUI-1091 @UseCase12
  Scenario Outline: NTS-4577: Use Case#12: Create Referral for Trio Family + Default Data + Add Family Members to Test + Patient Choice Not Given - Search Spine Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449310084:DOB=20-10-1973 |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Rotherham Doncaster and South Humber NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Questions
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=17-07-1965:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=19-12-1970:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient Choice
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
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
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=17-07-1965 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
      | NHSNumber=NA:DOB=19-12-1970 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print Forms
    Then the user is navigated to a page with title Print sample forms
    ##Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | Panels | Pedigree | FamilyMembers  | RecordedBy                            |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | Panels | Pedigree | Family members | ClinicianName=John:HospitalNumber=123 |

  @NTS-4600 @Z-LOGOUT
#    @E2EUI-1177 @UseCase13
  Scenario Outline: NTS-4600: Use Case#13: Create Referral for Trio Family + Edit Data + Add Family Members to Test + Patient Choice Not Given - Search Spine Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449310084:DOB=20-10-1973 |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Wye Valley NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only - No of participants -3
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
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
    ##Notes
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members -
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=18-09-1975:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Unaffected:AgeOfOnset=02,02:HpoPhenoType=Dystonia |
      | NHSNumber=NA:DOB=28-11-1978:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Unknown:AgeOfOnset=03,02:HpoPhenoType=Trichiasis  |
    And the user clicks the Save and Continue button
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient Choice - Proband
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Child in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Parent(s) / guardian conversation happened; form to follow for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    #Patient Choice - Family Details Provided below should be same as above
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=18-09-1975 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
      | NHSNumber=NA:DOB=28-11-1978 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
    Then the user is navigated to a page with title Panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print forms for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=18-09-1975 |
      | NHSNumber=NA:DOB=28-11-1978 |
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"
    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | RecordedBy         | PatientChoiceStage | Panels | searchPanels | Pedigree | FamilyMembers  |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | ClinicianName=John | Patient choice     | Panels | Cataracts    | Pedigree | Family members |