@E2E_TEST

Feature: RD Duo Family : NTS-6092:Twins Monozygous, Twins Unknown and Twins Dizygous relationship with Proband verification

  @NTS-6092 @NTS-6092_Scenario1 @Z-LOGOUT @inprogress2
  Scenario Outline:NTS-6092_Scenario-1:Twins Monozygous relationship with Proband verification
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-s?election/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=19-10-2001:Gender=Male |
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone And Tunbridge Wells NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Duo family
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
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
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Adding one member
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  the user clicks the Save and Continue button
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    #Patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    And the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    When the user edits patient choice for "<TwoParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=19-10-2001 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    Then the user clicks on the proband node on the pedigree for "NGIS" and "Male"
    And the user select the pedigree tab Personal
    Then user should see the monozygotic twin check box is Selected
    And the user is able to close the popup by clicking on the close icon
    Then the user clicks on the family member node on the pedigree diagram for "NGIS" and "Male"
    And the user select the pedigree tab Personal
    Then user should see the monozygotic twin check box is Selected
    And the user is able to close the popup by clicking on the close icon
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - FamilyDetails -same as provided above Family details
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                             | RecordedBy                            | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | FamilyMemberDetails                                                   | DiseaseStatusDetails                                                                                |
      | Patient details | Requesting organisation | Test package | 2              | Responsible clinician | FirstName=James:LastName=Smith:Department=Minister Road | ClinicianName=John:HospitalNumber=123 | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=2,11:HpoPhenoType=Square face | Notes | Family members | Patient choice | Panels | Pedigree | NHSNumber=NA:DOB=19-10-2001:Gender=Male:Relationship=Twins Monozygous | DiseaseStatus=Affected:AgeOfOnset=00,01:HpoPhenoType=Lymphedema:PhenotypicSex=Male:KaryotypicSex=XY |

  @NTS-6092 @NTS-6092_Scenario2 @Z-LOGOUT
  Scenario Outline: NTS-6092_Scenario-2: Twins Unknown relationship with Proband verification
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=19-10-2001:Gender=Male |
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone And Tunbridge Wells NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Duo family
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
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
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Adding one member -
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And  the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  the user clicks the Save and Continue button
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    #Patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    And the Patient Choice landing page is updated to "Agreed to testing" for the proband
    And the user is navigated to a page with title Patient choice
    ###Patient Choice - Family Details Provided below same as the Family Members
    When the user edits patient choice for "<TwoParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=19-10-2002 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    #Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    Then the user clicks on the proband node on the pedigree for "NGIS" and "Male"
    And the user select the pedigree tab Personal
    Then The user should see the monozygotic twin check box status as NotSelected
    And the user is able to close the popup by clicking on the close icon
    Then the user clicks on the family member node on the pedigree diagram for "NGIS" and "Male"
    Then The user should see the monozygotic twin check box status as NotSelected
    And the user is able to close the popup by clicking on the close icon
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - FamilyDetails -same as provided above Family details
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                             | RecordedBy                            | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | FamilyMemberDetails                                                | DiseaseStatusDetails                                                                                |
      | Patient details | Requesting organisation | Test package | 2              | Responsible clinician | FirstName=James:LastName=Smith:Department=Minister Road | ClinicianName=John:HospitalNumber=123 | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=2,11:HpoPhenoType=Square face | Notes | Family members | Patient choice | Panels | Pedigree | NHSNumber=NA:DOB=19-10-2002:Gender=Male:Relationship=Twins Unknown | DiseaseStatus=Affected:AgeOfOnset=00,01:HpoPhenoType=Lymphedema:PhenotypicSex=Male:KaryotypicSex=XY |

  @NTS-6092 @NTS-6092_Scenario3 @Z-LOGOUT
  Scenario Outline: NTS-6092_Scenario-3: Twins Dizygous relationship with Proband verification
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=19-10-2001:Gender=Male |
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone And Tunbridge Wells NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Duo family
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
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
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Adding one member -
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And  the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And the user clicks the Save and Continue button
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    #Patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    And the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    When the user edits patient choice for "<TwoParticipant>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                 | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=19-10-2001 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |             |                 |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    Then the user clicks on the proband node on the pedigree for "NGIS" and "Male"
    And the user select the pedigree tab Personal
    Then The user should see the monozygotic twin check box status as NotSelected
    And the user is able to close the popup by clicking on the close icon
    Then the user clicks on the family member node on the pedigree diagram for "NGIS" and "Male"
    Then The user should see the monozygotic twin check box status as NotSelected
    And the user is able to close the popup by clicking on the close icon
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - FamilyDetails -same as provided above Family details
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                             | RecordedBy                            | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | FamilyMemberDetails                                                 | DiseaseStatusDetails                                                                                |
      | Patient details | Requesting organisation | Test package | 2              | Responsible clinician | FirstName=James:LastName=Smith:Department=Minister Road | ClinicianName=John:HospitalNumber=123 | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=2,11:HpoPhenoType=Square face | Notes | Family members | Patient choice | Panels | Pedigree | NHSNumber=NA:DOB=19-10-2001:Gender=Male:Relationship=Twins Dizygous | DiseaseStatus=Affected:AgeOfOnset=00,01:HpoPhenoType=Lymphedema:PhenotypicSex=Male:KaryotypicSex=XY |
