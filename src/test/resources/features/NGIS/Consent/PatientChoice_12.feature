#@patientChoice
@05-CONSENT
@SYSTEM_TEST
@SYSTEM_TEST_1
Feature: Patient Choice-12 - Adult with Capacity

  @NTS-3436 @Z-LOGOUT
    #@E2EUI-1173 @E2EUI-1112
  Scenario Outline:the user should be navigate to patient choice page by not entered link
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
    ##Test Order Forms
    Then the user is navigated to a page with title Test Order Forms
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
#    And the "<TestPackage>" stage is marked as Completed
    ##Family Members - Family member details to be added - creating new referrals
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1943:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status for family member 1 as Not entered
    And the user clicks on patient choice status link for family member 1
    Then the user is navigated to a page with title Add family member patient choice information
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=14-05-1943 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    And the user is navigated to a page with title Patient choice
    Then the user sees the patient choice status for family member 1 as Agreed to testing
    And the below stages marked as completed
      | <TestPackage> |
    Examples:
      | FamilyMembers  | TestPackage  | NoOfParticipants |
      | Family members | Test package | 2                |

  @NTS-3505 @NTS-3444 @NTS-3451 @NTS-3445 @Z-LOGOUT
   #@E2EUI-1644 @E2EUI-1727 @E2EUI-2109 @E2EUI-1931
  Scenario Outline: NTS-3505:When additional family members are added and their patient choice hasn't been provided, the patient choice stage status should be updated as incomplete
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1997:Gender=Male |
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Sussex Community NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
#    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
#    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Question
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
#    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    When the user navigates to the "<Notes>" stage
    #Then the user is navigated to a page with title Add notes to this referral
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
#    Then the "<Notes>" stage is marked as Completed
    ##Family Members
    When the user navigates to the "<FamilyMembers>" stage
    And the "<FamilyMembers>" stage is marked as Mandatory To Do
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                         | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=12-05-1932:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
#    Then the "<FamilyMembers>" stage is marked as Completed
    #patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    And the user submits the patient choice with signature
    And the user clicks the Save and Continue button on the patient choice
    #Below steps for NTS-3445
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    And the "<PatientChoice>" stage is marked as Mandatory To Do
    #Patient Choice - Family Details Provided below should be same as above
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<TwoParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=12-05-1932 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
#    Then the "<PatientChoice>" stage is marked as Completed
    And the below stages marked as completed
      | <PatientDetails>         |
      | <RequestingOrganisation> |
      | <TestPackage>            |
      | <ClinicalQuestion>       |
      | <Notes>                  |
      | <FamilyMembers>          |
      | <PatientChoice>          |
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<ThreeParticipants>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Adding Additional Family Member
    When the user navigates to the "<FamilyMembers>" stage
    And the "<FamilyMembers>" stage is marked as Mandatory To Do
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<TwoParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=10-12-1950:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    ##Steps for NTS-3444
    And the "<PatientChoice>" stage is marked as Mandatory To Do
    When the user submits the referral
    And the user should see a new popup dialog with title "There is missing information"
    Then the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | Mandatory Stage |
      | Patient choice  |

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipants | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMembers  | PatientChoice  | ThreeParticipants |
      | Patient details | Requesting organisation | Test package | 2               | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Notes | Family members | Patient choice | 3                 |