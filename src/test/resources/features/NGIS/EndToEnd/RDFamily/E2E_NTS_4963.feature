@E2E_TEST

Feature: RDFamily:NTS-4963:Submit an RD referral for 7 participants with various relationships to proband (2 siblings,2 grand parents,father,mother)

  @NTS-4963 @Z-LOGOUT
    #@E2EUI-2690
  Scenario Outline:NTS-4963: Create a referral with 2 siblings,2 grand parents,father,mother for a proband and process the CSVs and check in DDF payload
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R193 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=19-10-1999:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails>" stage is marked as Completed
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ###Test Package - No of participants 7
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<SevenParticipant>"
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
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ###Family Members - Adding six members - Father and Mother, Grandparents and Siblings
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<SevenParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                         | RelationshipToProband | DiseaseStatusDetails                                                                                      |
      | NHSNumber=NA:DOB=26-01-1969:Gender=Female:Relationship=Mother               | Mother                | DiseaseStatus=Affected:AgeOfOnset=05,11:HpoPhenoType=Gastroschisis:Phenotypic sex=Male:Karyotypic sex=XX  |
      | NHSNumber=NA:DOB=26-01-1968:Gender=Male:Relationship=Father                 | Father                | DiseaseStatus=Affected:AgeOfOnset=05,11:HpoPhenoType=Gastroschisis :Phenotypic sex=Male:Karyotypic sex=XX |
      | NHSNumber=NA:DOB=26-01-1930:Gender=Male:Relationship=Paternal Grandparent   | Paternal Grandparent  | DiseaseStatus=Affected:AgeOfOnset=05,11:HpoPhenoType=Gastroschisis :Phenotypic sex=Male:Karyotypic sex=XX |
      | NHSNumber=NA:DOB=26-01-1932:Gender=Female:Relationship=Maternal Grandparent | Maternal Grandparent  | DiseaseStatus=Affected:AgeOfOnset=05,11:HpoPhenoType=Gastroschisis :Phenotypic sex=Male:Karyotypic sex=XX |
      | NHSNumber=NA:DOB=26-01-2003:Gender=Female:Relationship=Full Sibling         | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=05,11:HpoPhenoType=Gastroschisis :Phenotypic sex=Male:Karyotypic sex=XX |
      | NHSNumber=NA:DOB=26-01-2004:Gender=Male:Relationship=Full Sibling           | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=05,11:HpoPhenoType=Gastroschisis :Phenotypic sex=Male:Karyotypic sex=XX |

    Then the "<FamilyMembers>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###Patient choice for the proband-YES
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    Then the "<PatientChoice>" page is displayed
    And the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ###Patient Choice - Family Details Provided below same as the Family Members
    Then the user is navigated to a page with title Patient choice
    ###Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails         | PatientChoiceCategory | RecordedBy                                                                                                           |
      | NHSNumber=NA:DOB=26-01-1969 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=26-01-1968 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=26-01-1930 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=26-01-1932 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=26-01-2003 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=26-01-2004 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ###Panels
    Then the user is navigated to a page with title Manage panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ###Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ###Print forms - No
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    ##NOTE: ONLY GUI PART IS DONE. CSV,DDF PART TO BE DONE

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | SevenParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                        | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | searchPanels        |
      | Patient details | Requesting organisation | Test package | 7                | Responsible clinician | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Epistaxis | Notes | Family members | Patient choice | Panels | Pedigree | Cardiac arrhythmias |

