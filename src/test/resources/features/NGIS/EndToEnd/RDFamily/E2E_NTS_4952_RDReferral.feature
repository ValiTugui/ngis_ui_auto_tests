@E2E_TEST

Feature: RDFamily:NTS-4952: Create Referral for Trio Family + Default Data + Add Family Members to Test + Patient Choice Yes

  @NTS-4952 @Z-LOGOUT
    #@E2EUI-2666
  Scenario Outline: NTS-4952 : E2EUI-2666 E2E #8: Create Referral for Trio Family + Default Data + Add Family Members to Test + Patient Choice Yes
  Given a new patient referral is created with associated tests in Test Order System online service
  | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R19 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-05-2000:Gender=Male |
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "South London and Maudsley NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
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
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members - Adding two members - Father and Mother
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<ThreeParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                           | RelationshipToProband | DiseaseStatusDetails                                                                                                |
      | NHSNumber=NA:DOB=10-03-1978:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=0,02:HpoPhenoType=Bladder diverticulum:PhenotypicSex=Male:Karyotypic sex=XX       |
      | NHSNumber=NA:DOB=14-02-1979:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Unaffected:AgeOfOnset=0,01:HpoPhenoType=Phenotypic abnormality:PhenotypicSex=Female:Karyotypic sex=XY |
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
      | NHSNumber=NA:DOB=10-03-1978 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=14-02-1979 | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    Then the "<PatientChoice>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Panels
    Then the user is navigated to a page with title Manage panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree - Pedigree by default marked as completed
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms - No
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print form for the proband
    And the user is able to download print forms for "<TwoFamilyMember>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=14-02-1979 |
      | NHSNumber=NA:DOB=10-03-1978 |

    ##Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    ##NOTE: ONLY GUI PART IS DONE. CSV,DDF PART TO BE DONE

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ThreeParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                            | ClinicalQuestion   | ClinicalQuestionDetails                                                                                     | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | searchPanels | TwoFamilyMember |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=George:LastName=Williams:Department=Cleaning | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Male:KaryotypicSex=XY | Notes | Family members | Patient choice | Panels | Pedigree | Amyloidosis  | 2               |
