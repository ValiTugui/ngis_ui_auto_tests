@regression
@PatientChoice
@PatientChoice_set
Feature: Patient Choice Page - Identifiers

  @NTS-3450 @E2EUI-1773 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3450: As a user, I should be able to see family member identifiers so that I know who the family member is.
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    And the user navigates to the "<ClinicalQuestions>" stage
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestions>" stage is marked as Completed
    And the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                               | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-2002:Gender=Male:Relationship=Full Sibling | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    And the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user should see the details of family members displayed in patient choice landing page
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=14-05-2002 |

    Examples:
      | FamilyMembers  | TestPackage  | ClinicalQuestions  | NoOfParticipants | ClinicalQuestionDetails                                         | Patient choice stage |
      | Family members | Test package | Clinical questions | 2                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Patient choice       |

  @NTS-4100 @E2EUI-1540 @LOGOUT @BVT_P0 @v_1
  Scenario Outline: NTS-4100: Removing the patient choice step in the family member flow
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1998:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Test Package - Trio family - No of participants -2
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Family Members - Family member details to be added - creating new referrals
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1936:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ###Validation of added family member is covered in addition step
    Examples:
      | TestPackage  | NoOfParticipants | FamilyMembers  |
      | Test package | 2                | Family members |

  @NTS-3435 @E2EUI-1877 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3435: AS a user I should be able to see the patient choice stage completion when any one of the members declined the test package
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2002:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Test Package - Trio family - No of participants -2
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Family Members - Family member details to be added - creating new referrals
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                               | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-2003:Gender=Male:Relationship=Full Sibling | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    #patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    #Patient Choice - Family Details Provided below should be same as above
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=14-05-2003 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    Then the "<PatientChoice>" stage is marked as Completed
    Examples:
      | PatientDetails  | TestPackage  | NoOfParticipants | FamilyMembers  | PatientChoice  |
      | Patient details | Test package | 2                | Family members | Patient choice |

  @NTS-3435 @E2EUI-2180 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3435: Verify the upload revised patient choice documentation to form library
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the Form library tab in patient choice page
    Then the user should see the supporting information links under the section Patient choice forms
      | FormName                             |
      | Agreement to Participate in Research |

    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-4603 @E2EUI-1892 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4603: Moving the warn message to research section
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2012:Gender=Male |
    Then the user is navigated to a page with title Check your patient's details
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Child in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user is in the section Recorded by
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    When the user selects the option No for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    ###Again editing the Proband
    And the user selects the proband
    And the user is navigated to a page with title Add patient choice information
    Then the user will see a warning message "<WarningMessage>"
    And the user clicks on the amend patient choice button
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button

    Examples:
      | Patient choice stage | RecordedBy                              | WarningMessage                                                                                                                                                                              |
      | Patient choice       | ClinicianName=Johny:HospitalNumber=1234 | A patient choice record currently exists for this test referral ID. You may create another to override the current record by clicking the 'Amend patient choice for referral' button below. |

  @NTS-4603 @E2EUI-1856 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4603: Verify that the old file uploaded names remain after all files have been deleted.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2012:Gender=Male |
    When the user navigates to the "<Stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedByWithoutFormSelection1>" details in recorded by
    Then the user should be able to see the uploaded file name "<FileName1>"
    And the user selects the History tab in patient choice page
    And the user selects the New patient choice tab in patient choice page
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedByWithoutFormSelection2>" details in recorded by
    Then the user should be able to see the uploaded file name "<FileName2>"

    Examples:
      | Stage          | RecordedByWithoutFormSelection1                                             | FileName1    | RecordedByWithoutFormSelection2                                              | FileName2     |
      | Patient choice | RecordingClinicianName=John Doe:Action=UploadDocument:FileName=testfile.pdf | testfile.pdf | RecordingClinicianName=John Doe:Action=UploadDocument:FileName=testfile2.pdf | testfile2.pdf |

  @NTS-4603 @E2EUI-1891 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4603: Verify verify referral id displayed in history tab is same as on the banner
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user is navigated to a page with title Patient choice
    And the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    And the user selects the History tab in patient choice page
    And the user is navigated to a page of section with title Patient choice history
    Then the user verifies the referral id on history tab is same as on referral id on referral bar

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |