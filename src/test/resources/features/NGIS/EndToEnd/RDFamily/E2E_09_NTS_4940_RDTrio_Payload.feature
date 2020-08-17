@E2E_TEST

Feature: RDFamily:NTS-4940:E2E09:Sanity Test for RD Referral for Trio Family Patient Choice Yes

  @NTS-4940 @Z-LOGOUT
    #@E2EUI-2687
  Scenario Outline:NTS:4940: E2E#09: Verify payload for RD Referral for Trio Family with one parent with disease and another no disease with Patient Choice Yes
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-05-2000:Gender=Male |
    ##Patient Details
    When the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
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
      | NHSNumber=NA:DOB=12-03-1978:Gender=Male:Relationship=Father   | Father                | DiseaseStatus=Affected:AgeOfOnset=0,02:HpoPhenoType=Bladder diverticulum:PhenotypicSex=Male:Karyotypic sex=XX       |
      | NHSNumber=NA:DOB=12-02-1979:Gender=Female:Relationship=Mother | Mother                | DiseaseStatus=Unaffected:AgeOfOnset=0,01:HpoPhenoType=Phenotypic abnormality:PhenotypicSex=Female:Karyotypic sex=XY |
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
      | NHSNumber=NA:DOB=12-02-1979 |
      | NHSNumber=NA:DOB=12-03-1978 |
    ##Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | ThreeParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                            | ClinicalQuestion   | ClinicalQuestionDetails                                                                                     | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | searchPanels | TwoFamilyMember |
      | Patient details | Requesting organisation | Test package | 3                | Responsible clinician | FirstName=George:LastName=Williams:Department=Cleaning | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=0,11:HpoPhenoType=Renal insufficiency:PhenotypicSex=Male:KaryotypicSex=XY | Notes | Family members | Patient choice | Panels | Pedigree | Amyloidosis  | 2               |

    ##ONLY UI PART HAS BEEN COMPLETED, BELOW STEPS HAVE TO DO
#    Then The user take all the inputs from submitted referral like referral id,CI,referral UID,Patient NGIS ID,Patient NHS Number & Patient DOB for the three patients,Organisation Entity,Organisation ID .
    #ReferralDetails: referralid=r20002396950:CI=R100:referralUID=4c561cdd-6543-4db1-b637-9c2408fb5f2f:PatientNGISID=p14409964451:PatientNHSNumber=:Patient DOB=13-12-1979:
    ##GEL1001 file - filling the input fields & processing-in S3 Bucket & verifying in MI portal
#    When The user take the Sample types & Sample states from Sample Mapping table in latest specifications document.
#    And The user should refer the GEL1001 - Fields sheet in specification document & referral details from NGIS application while filling the input details.
#    And if the referral has family members,should provide those details in consecutive rows
#    When The user fill all the required input fields in GEL1001 ,should close the file and provide a unique file name by changing the last six digits in filename .
#    And The user should open the Cyber duck application ,connect to S3 bucket and open outgoing folder in glh_e2e directory and search for related organisation folder which is provided in GEL1001 file
#    Then The user should open drag the file from local directory to GLH Directory in S3 bucket.
#    And The user should login to MI Portal ,click on file submissions link from file menu
#    And The user should provide Created ,equals & file processed date values as three filters and click on Add button.
#    And The user should click on search button .
#    Then The user should be able to view the submitted GEL1001 file name
#    Then The user should also be able to view the output file GEL1004 above GEL1001 file in filename column with status as valid.
#    When The user should connect to S3 bucket again and navigate to Bio-repository directory incoming folder
#    Then The user should download the GEL1004 file from archive folder.
#    And The user should validate all the fields in the output file according to specifications document.
#    And The user should verify that Patient NGIS ID in GEL 1001 is matching with Participant ID and also referral ID in GEL1001 is matching with GroupID for all records
#    And The user should verify that Clinical Sample type field is displayed according to Sample Mapping table in Specifications .
#    And The user should verify dispatched_sample_lsid in GEL1001 is matching with Laboratory Sample ID in GEL1004 for all records.
#    And The user should verify that priority is found as per referal submitted
###GEL1005 file - filling the input fields & processing-in S3 Bucket & verifying in MI portal
#    When The user take Laboratory Sample ID and Participant ID values from GEL1001 file
#    Then The user fill all the required input fields in GEL1005 referring to GEL1005 - Fields sheet in specifications document
#    And The user should close the file and provide a unique file name by changing the last six digits in filename .
#    And The user should open the Cyber duck application ,connect to S3 bucket and open outgoing folder in Bio-repository directory
#    Then The user should open and drag the file from local directory to Bio-repository in S3 bucket.
#    And The user should login to MI Portal ,click on file submissions link from file menu
#    And The user should provide Created, equals & file processed date values as three filters and click on Add button.
#    And The user should click on search button .
#    Then The user should be able to view the submitted GEL1005 file in results with status as valid.
###GEL1006 file - filling the input fields & processing-in S3 Bucket & verifying in MI portal
#    When The user take Laboratory Sample ID and Participant ID values from GEL1001 file
#    Then The user fill all the required input fields in GEL1006 referring to GEL1006 - Fields sheet in specifications document
#    And The user should close the file and provide a unique file name by changing the last six digits in filename
#    And The user should open the Cyber duck application ,connect to S3 bucket and open outgoing folder in Bio-repository directory
#    Then The user should open and drag the file from local directory to Bio-repository in S3 bucket.
#    And The user should login to MI Portal ,click on file submissions link from file menu
#    And The user should provide Created, equals & file processed date values as three filters and click on Add button.
#    And The user should click on search button .
#    Then The user should be able to view the submitted GEL1006 file in results with status as valid.
###GEL1008 -RP file - filling the input fields & processing-in S3 Bucket & verifying in MI portal
#    When The user take Laboratory Sample ID and Participant ID values from GEL1001 file
#    And The user should provide Unique plate ID value and Type of Case as RP
#    And The user fill all the required input fields in GEL1008 referring to referring to GEL1008 - Fields sheet specifications document
#    And The user close the file and provide a unique file name by changing the last six digits in filename
#    And The user open the Cyber duck application ,connect to S3 bucket and open outgoing folder in Bio-repository directory
#    Then The user should open and drag the file from local directory to Bio-repository in S3 bucket.
#    And The user should login to MI Portal ,click on file submissions link from file menu
#    And The user should provide Created, equals & file processed date values as three filters and click on Add button.
#    And The user should click on search button .
#    Then The user should be able to view the submitted GEL1008 file in results with status as valid.
#    Then The user should also be able to view the output file GEL1009 sent to illumina above GEL1008 file, in filename column with status as valid.
###GEL1008 -RF file - filling the input fields & processing-in S3 Bucket & verifying in MI portal
#    When The user take Laboratory Sample ID and Participant ID values from GEL1001 file
#    And The user should provide Unique plate ID value and Type of Case as RF
#    And The user fill all the required input fields in GEL1008 referring to GEL1008 - Fields sheet in specifications document .
#    And The user close the file and provide a unique file name by changing the last six digits in filename
#    And The user open the Cyber duck application ,connect to S3 bucket and open outgoing folder in Bio-repository directory
#    Then The user should open and drag the file from local directory to Bio-repository in S3 bucket.
#    And The user should login to MI Portal ,click on file submissions link from file menu
#    And The user should provide Created, equals & file processed date values as three filters and click on Add button.
#    And The user should click on search button .
#    Then The user should be able to view the submitted GEL1008 file in results with status as valid.
#    Then The user should also be able to view the output file GEL1009 sent to illumina above GEL1008 file, in filename column with status as valid.
