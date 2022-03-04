@E2E_TEST

Feature: NTS-5766:CSV-E2E-Submit a RD Referral for a proband and 4 family members and verify the payload.

  @NTS-5766 @Z-LOGOUT
#@E2EUI-2577
  Scenario Outline:NTS-5766:E2EUI-2577:Submit a RD Referral for a proband and 4 family members and verify the payload.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=16-11-1978:Gender=Female |
    ##Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    Then the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    And the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Birmingham Womens & Childrens NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    And the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<FiveParticipant>"
    And the user clicks the Save and Continue button
    Then the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    When the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    Then the "<ResponsibleClinician>" stage is marked as Completed
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
    ##Family Members
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<FiveParticipant>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                                                                             | RelationshipToProband | DiseaseStatusDetails                                               |
      | NHSNumber=NA:DOB=21-05-1950:Gender=Female:Life status=Aborted:Ethnicity=F - Mixed - White and Asian:Relationship=Mother         | Mother                | DiseaseStatus=Affected:AgeOfOnset=06,10:HpoPhenoType=Gastroschisis |
      | NHSNumber=NA:DOB=05-08-1948:Gender=Male:Life status=Deceased:Ethnicity=H - Asian or British Asian - Indian:Relationship=Father  | Father                | DiseaseStatus=Affected:AgeOfOnset=08,09:HpoPhenoType=Megalocornea  |
      | NHSNumber=NA:DOB=18-05-2002:Gender=Male:Life status=Miscarriage:Ethnicity=N - Black or Black British - African:Relationship=Son | Son                   | DiseaseStatus=Affected:AgeOfOnset=02,04:HpoPhenoType=Azoospermia   |
      | NHSNumber=NA:DOB=12-12-2005:Gender=Female:Life status=Stillborn:Ethnicity=S - Any other ethnic group:Relationship=Daughter      | Daughter              | DiseaseStatus=Affected:AgeOfOnset=03,05:HpoPhenoType=Hydroureter   |
    And the user clicks the Save and Continue button
    Then the "<FamilyMembers>" stage is marked as Completed
    ##Patient Choice
    When the user is navigated to a page with title Patient choice
    And the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    And the user is in the section Patient choices
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Patient choice
    And the Patient Choice landing page is updated to "Agreed to testing" for the proband
    ##Patient Choice - Family Details Provided below same as the Family Members
    ##Note: FileName mentioned in RecordedBy argument, should be present in the testdata folder. Child Assent and ParentSignature not required, if uploading file.
    When the user completes the patient choice for below family members as agreeing to test
      | FamilyMemberDetails         | PatientChoiceCategory    | RecordedBy                                                                                                           |
      | NHSNumber=NA:DOB=21-05-1950 | Adult (With Capacity)    | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=05-08-1948 | Adult (Without Capacity) | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=18-05-2002 | Adult (With Capacity)    | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
      | NHSNumber=NA:DOB=12-12-2005 | Child                    | ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf |
    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Manage panels
    And the user sees suggested panels under the section Default Panel based on the clinical information
    And the user search and add the "<SearchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    When the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    ##Submitting Referral
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | FiveParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | FamilyMembers  | RecordedBy                                | Notes | PatientChoiceStage | Pedigree | Panels | SearchPanels                |
      | Patient details | Requesting organisation | Test package | 5               | Responsible clinician | FirstName=Samuel:LastName=John:Department=GreenGarden,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=08,02:HpoPhenoType=Phenotypic abnormality | Family members | ClinicianName=Janardan:HospitalNumber=879 | Notes | Patient choice     | Pedigree | Panels | Inborn errors of metabolism |
