@regression
@patientChoice
@patientChoice_page4
Feature: Patient Choice Page - Family Member Addition

  @NTS-4099 @E2EUI-1583 @E2EUI-1760 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-TODO : The Patient Choice page is not loading when there are more than 1 participants
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1997:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Family Members
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1928:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=10-11-1929:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    And the user should see the details of family members displayed in patient choice landing page
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=14-05-1928 |
      | NHSNumber=NA:DOB=10-11-1929 |

    Examples:
      | PatientDetails  | TestPackage  | NoOfParticipants | FamilyMembers  | PatientChoice  |
      | Patient details | Test package | 3                | Family members | Patient choice |

  @NTS-3449 @E2EUI-989 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3449: User is making a referral – Adding patient consent
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | Rare-Disease | create a new patient record | Patient is a foreign national |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user deselects the test
    And the user clicks the Save and Continue button
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the test badge status for family member as Not being tested
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    And the user sees the test badge status for family member as Not being tested
    ##Family member details validation covered in E2EUI-1583
    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | RelationshipToProband | PatientChoice  |
      | Family members | NHSNumber=9449310122:DOB=30-06-1974 | Maternal Aunt         | Patient choice |

  @NTS-3505 @E2EUI-1644 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3505:When additional family members are added and their patient choice hasn't been provided, the patient choice stage status should be updated as incomplete
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1997:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipants>"
    And the user clicks the Save and Continue button
    ##Family Members
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=12-05-1932:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
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
    When the user edits patient choice for "<TwoParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=12-05-1932 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    Then the "<PatientChoice>" stage is marked as Completed
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Adding Additional Family Member
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=10-12-1950:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the "<PatientChoice>" stage is marked as Mandatory To Do

    Examples:
      | TestPackage  | TwoParticipants | FamilyMembers  | PatientChoice  | ThreeParticipants |
      | Test package | 2               | Family members | Patient choice | 3                 |

  @E2EUI-2109 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-todo: Validate the Patient choice section is incomplete by not submitting the choice for selected Family member
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1997:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Family Members - Family member details to be added
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=12-05-1932:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=10-12-1950:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
     #Patient Choice - Doing PC for only one Family Member
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=12-05-1932 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    Then the "<PatientChoice>" stage is marked as Mandatory To Do
    Examples:
      | TestPackage  | NoOfParticipants | FamilyMembers  | PatientChoice  |
      | Test package | 3                | Family members | Patient choice |

  @NTS-3481 @E2EUI-2151 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3481: Verify the updated warning message content in patient choice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
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
    When the user selects the proband
    And the user selects the History tab in patient choice page
    And the user selects the New patient choice tab in patient choice page
    And the user clicks on the amend patient choice button
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    Examples:
      | PatientChoice  |
      | Patient choice |

#  @NTS-3473 @E2EUI-2037 @LOGOUT @v_1 @P1
#  Scenario Outline: NTS-3473: Patient Choice critical error Should not be occurred when refresh token expires
#    Given a new patient referral is created with associated tests in Test Order System online service
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
#     #patient choice for the proband
#    When the user navigates to the "<PatientChoice>" stage
#    Then the user is navigated to a page with title Patient choice
#    When the user selects the proband
#    And the user should wait for session expiry time 740 seconds
#    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
#    And the user submits the patient choice with signature
#    And the user clicks the Save and Continue button on the patient choice
#    Then the "<PatientChoice>" page is displayed
#    Then the help text is displayed
#    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
#    Examples:
#      | PatientChoice  |
#      | Patient choice |

  @NTS-3445 @E2EUI-1931 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3445: Validate the incomplete status of Patient choice and Family members stage with a red asterisk and without a green tick
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Test Package No of participants -1
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
      | NHSNumber=NA:DOB=14-05-1935:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    #patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    And the Patient Choice landing page is updated to "Agreed to testing" for the proband
    And the "<FamilyMembers>" stage is marked as Mandatory To Do
    And the "<PatientChoice>" stage is marked as Mandatory To Do
    Examples:
      | TestPackage  | NoOfParticipants | FamilyMembers  | PatientChoice  |
      | Test package | 1                | Family members | patient choice |

  @NTS-3446 @E2EUI-2035 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3446: As a user, I should be able to edit test type for a family member in the patient choice form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    And the user is in the section Recorded by
    When the user clicks on edit button in Test type
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    Then the option Cancer (paired tumour normal) – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    And the user is in the section Recorded by
    Examples:
      | Patient choice stage |
      | Patient choice       |

  @NTS-3472 @E2EUI-2149 @LOGOUT @v_1 @P1
  Scenario Outline: NTS-3472: Remove Child Assent section where paper form is not present
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Child in section Patient choice category
    Then the option Child displayed with edit option in Patient choice category
    And the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    And the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the options displayed as below for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
      | Parent(s) / guardian have agreed to the test                       |
      | Parent(s) / guardian conversation happened; form to follow         |
      | Parent(s) / guardian changed their mind about the clinical test    |
      | Clinician has agreed to the test (in the Patient's best interests) |
    When the user selects the option Parent(s) / guardian conversation happened; form to follow for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    And the user should see selected details displayed under the section Patient choices
      | Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Parent(s) / guardian conversation happened; form to follow |
    When the user is in the section Review and submit

    Examples:
      | Patient choice stage | RecordedBy                            |
      | Patient choice       | ClinicianName=John:HospitalNumber=123 |

  @NTS-3471 @E2EUI-2155 @LOGOUT @v_1 @P1
  Scenario Outline: NTS-3471: Verify the child assent question content
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user sees the new patient choice tab selected by default with subtitle New patient choice form
    When the user selects the option Child in section Patient choice category
    Then the option Child displayed with edit option in Patient choice category
    And the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    And the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Parent(s) / guardian have agreed to the test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    When the user selects the option Parent(s) / guardian would like to revisit at a later date for the question Why has research participation not been discussed?
    And the user clicks on Continue Button
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    And the options displayed as below for the question Does the child agree to participate in research?
      | Yes            |
      | No             |
      | Not applicable |

    Examples:
      | Patient choice stage | RecordedBy                                                                                                           |
      | Patient choice       | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |

  @NTS-3415 @E2EUI-1627 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-3415: Verify the patient Choice 'Save & Continue' button is disabled until the patient choice has been submitted
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1991:Gender=Male |
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    And the user selects the Relationship to proband as "Full Sibling"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    And the user clicks on Continue Button
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    Then the option Adult (With Capacity) displayed with edit option in Patient choice category
    Then the Patient choice category option is marked as completed
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    Then the option Rare & inherited diseases – WGS displayed with edit option in Test type
    Then the Test type option is marked as completed
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the option Recorded by: displayed with edit option in Recorded by
    Then the Recorded by option is marked as completed
    When the user is in the section Patient choices
    Then the user should see the question displayed as Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    And the options displayed as below for the question Has research participation been discussed?
      | Yes |
      | No  |
    And the user should see continue button is highlighted in color #f0f0f0
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    And the options displayed as below for the question Why has research participation not been discussed?
      | Inappropriate to have discussion                  |
      | Patient would like to revisit at a later date     |
      | Patient lacks capacity and no consultee available |
      | Other                                             |
    And the user will see a warning message "<WarningMessage>"
    And the user should see continue button is highlighted in color #f0f0f0
    When the user selects the option Patient would like to revisit at a later date for the question Why has research participation not been discussed?
    And the user clicks on Continue Button
    Then the user should see selected details displayed under the section Patient choices
      | Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?::Patient has agreed to the test |
      | Has research participation been discussed?::No                                                                                                            |
      | Why has research participation not been discussed?::Patient would like to revisit at a later date                                                         |
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user should see patient choice submit button as enabled
    And Save and continue button is displayed as disabled

    Examples:
      | FamilyMembers  | FamilyMemberDetails                 | ClinicalQuestionDetails                 | WarningMessage                                                                                                         | RecordedBy                            |
      | Family members | NHSNumber=9449305919:DOB=24-07-2011 | DiseaseStatus=Affected:AgeOfOnset=02,02 | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | ClinicianName=John:HospitalNumber=123 |

  @E2EUI-1678 @LOGOUT @v_1 @BVT_P0
  Scenario Outline:Remove word consent from UI
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    #patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user is navigated to a page with title Add patient choice information
    When the user clicks on "Preferences" link
    And the user will see a warning message "<WarningMessage1>"
    And the user clicks on "New patient choice" link
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    ### this will fail since  Patient consent category: is present in rendered form
    Then the user should be able to see the patient choice form with success message
    When the user clicks on "Preferences" link
    And the user will see a warning message "<WarningMessage2>"
    And the user clicks on "New patient choice" link

    Examples:
      | PatientChoice  | WarningMessage2                                                                                                         | WarningMessage1                                                                                    |
      | Patient choice | Note: Patient preferences are applied across all completed patient choice forms and will autopopulate on all new forms. | No patient choice data found. Please complete patient choice before modifying patient preferences. |