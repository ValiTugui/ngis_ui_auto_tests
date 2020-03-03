@regression
@printForms
Feature: Print Forms - User flows

  @NTS-4746 @E2EUI-1729  @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4746: Update copy on print form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=24-08-1998:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "TAMESIDE GENERAL HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user should be able to see a "<WarningMessage>" on the print forms page

    Examples:
      | OneParticipant | PrintForms  | WarningMessage                                                                                            |
      | 1              | Print forms | Follow local trust information governance guidelines for data protection if saving sample forms anywhere. |

  @NTS-4746 @E2EUI-1332 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4746: Download sample form stage becomes available
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=11-11-2011:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the print forms stage is locked
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Queen" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ###Print Forms
    And the print forms stage is unlocked
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | OneParticipant | PrintForms  |
      | 1              | Print forms |

  @NTS-4746 @E2EUI-1132 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4746: User has submitted a referral and able to start a new referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2001:Gender=Female |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "LONDON" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user will not see back button on print forms page

    Examples:
      | PrintForms  | OneParticipant |
      | Print forms | 1              |

  @NTS-4746 @E2EUI-2094 @LOGOUT @v_1 @P0 @scenario_01
  Scenario Outline: NTS-4746: scenario_01 Update warning box content on print sample forms
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NORTH MANCHESTER GENERAL HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ###Tumour
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the user clicks the Save and Continue button
    ###Samples
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
    ###Notes
    Then the user is navigated to a page with title Add notes to this referral
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ###Patient Choice
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Child in patient choice category
    And the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    When the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user clicks on Continue Button
    ###Print Forms
    And the user is navigated to a page with title Print sample forms
    Then the user should be able to see a "<WarningMessage>" on the print forms page

    Examples:
      | tumour_type           | presentationType   | sampleType          | sampleState         | ClinicianName                         | WarningMessage                                                                                            |
      | Solid tumour: primary | First presentation | Solid tumour sample | Fresh frozen tumour | ClinicianName=John:HospitalNumber=123 | Follow local trust information governance guidelines for data protection if saving sample forms anywhere. |

  @NTS-4746 @E2EUI-2094 @LOGOUT @v_1 @P0 @scenario_02
  Scenario Outline: NTS-4746 : scenario_02 Update warning box content on print sample forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=15-09-2009:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "UNIVERSITY DENTAL HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ###Clinical Questions
    Then the user is navigated to a page with title Answer clinical questions
    When  the user selects "<diseaseStatueValue>"
    And the user provided the values "<year>" "<month>" for Age of onset fields
    And the user clicks the Save and Continue button
    ###Notes
    Then the user is navigated to a page with title Add notes to this referral
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ###Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks the Save and Continue button
    ###Patient Choice
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Child in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks on Continue Button
    ###Panels
    Then the user is navigated to a page with title Panels
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Build a pedigree
    ###Print Forms
    When the user navigates to the "<printForms>" stage
    Then the user is navigated to a page with title Print sample forms
    Then the user should be able to see a "<WarningMessage>" on the print forms page

    Examples:
      | printForms  | OneParticipant | diseaseStatueValue | year | month | ClinicianName                         | WarningMessage                                                                                            |
      | Print forms | 1              | Unaffected         | 1    | 2     | ClinicianName=John:HospitalNumber=123 | Follow local trust information governance guidelines for data protection if saving sample forms anywhere. |

  @NTS-4746 @E2EUI-1223 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4746: User is completing a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R193 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-05-2000:Gender=Male |
    ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "WITHINGTON COMMUNITY HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Trio family - No of participants 3
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipant>"
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
    When the user adds "<ThreeParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=12-03-1978:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis  |
      | NHSNumber=NA:DOB=12-02-1979:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##patient choice for the proband-YES
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ##Patient Choice - Family Details Provided below same as the Family Members
    Then the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails         | PatientChoiceCategory | RecordedBy                                                                                                           |
      | NHSNumber=NA:DOB=12-03-1978 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=12-02-1979 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Print forms - Yes
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print form for the proband
    And the user is able to download print forms for "<ThreeParticipant>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=12-03-1978 |
      | NHSNumber=NA:DOB=12-02-1979 |
      ##Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
   ### Going back to the referral - any stage
    When the user navigates to the "<Notes>" stage
    Then the user is navigated to a page with title Add notes to this referral

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ThreeParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                        | Notes | FamilyMembers  | PatientChoice  | PrintForms  | Notes |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis | Notes | Family members | Patient choice | Print forms | Notes |

    @NTS-3476 @E2EUI-1697 @E2EUI-1628 @E2EUI-1757 @LOGOUT @v_1 @P0
  Scenario Outline:NTS-3476: Validating submit referral and able to start a new referral after submission.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=5-10-2000:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "BOLTON ROYAL HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ###Clinical Questions
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ###Patient Choice
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice
    And the "<PatientChoiceStage>" stage is marked as Completed
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    And the user should see referral submit button as "enabled"
    And the user submits the referral
    Then the submission confirmation message "Your referral has been submitted" is displayed
    And the user is able to see the following guidelines below the confirmation message
      | Contact the laboratory if a submitted referral needs to be updated           |
      | Updates made online might not be seen before tests begin.                    |
      | Make sure you have:                                                          |
      | - printed your sample forms from this page                                   |
      | - sent your forms to the relevant laboratory                                 |
      | Search for a Clinical indication in the Test Directory to request more tests |
      | Start a new referral                                                         |
   ###Start a new referral for E2EUI-1628, E2EUI-1757
    And the user should be able to see start a new Referral button
    When the user clicks on start a new referral button
    Then the user is navigated to a page with title Search for genomic tests

    Examples:
      | OneParticipant | PatientChoiceStage | ClinicalQuestionDetails                   | ClinicianName                             | PrintForms  |
      | 1              | Patient choice     | DiseaseStatus=Unaffected:AgeOfOnset=01,02 | ClinicianName=John Doe:HospitalNumber=123 | Print forms |

  @NTS-4343 @E2EUI-889 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4343: Submit a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R59 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the referral status is set to "Created"
    When the user submits the referral
    And the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | MandatoryStagesToComplete |
      | Requesting organisation   |
      | Test package              |
      | Responsible clinician     |
      | Clinical questions        |
      | Patient choice            |
    ###Requesting Organisation
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "WYTHENSHAWE HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ###Responsible Clinician
    When the user navigates to the "<ResponsibleClinician>" stage
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ###Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ###Patient Choice
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
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
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice
   ###Submit referral-twice
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"
    And the user is able to see the following guidelines below the confirmation message
      | Contact the laboratory if a submitted referral needs to be updated           |
      | Updates made online might not be seen before tests begin.                    |
      | Make sure you have:                                                          |
      | - printed your sample forms from this page                                   |
      | - sent your forms to the relevant laboratory                                 |
      | Search for a Clinical indication in the Test Directory to request more tests |
      | Start a new referral                                                         |
    When the user submits the referral
    Then after submitting the referral once, the user is unable to submit it again

    Examples:
      | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                                                    | PatientChoice  | ClinicianName                                |
      | Requesting organisation | Test package | 1                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Functional abnormality of the bladder | Patient choice | ClinicianName=Billy:HospitalNumber=178827893 |

