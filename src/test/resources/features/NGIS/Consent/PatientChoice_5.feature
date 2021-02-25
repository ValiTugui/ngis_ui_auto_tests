#@regression
#@patientChoice
@05-CONSENT
@SYSTEM_TEST
Feature: Patient Choice -5 - Page - FamilyMemberAddition

  @NTS-3435 @NTS-3341 @NTS-3450 @NTS-3409 @NTS-3410 @Z-LOGOUT
    #@E2EUI-1877 @E2EUI-1822 @E2EUI-1127 @E2EUI-1934
  Scenario Outline: NTS-3435: AS a user I should be able to see the patient choice stage completion when any one of the members declined the test package
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2002:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Test Package - No of participants -2
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    And the "<TestPackage>" stage is marked as Completed
    ##Family Members
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                               | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-2003:Gender=Male:Relationship=Full Sibling | Full Sibling          | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    And the user sees the patient choice status for proband as Not entered
    And the user sees the patient choice status for family member 1 as Not entered
    #patient choice for the proband
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    And the user should see the details of family members displayed in patient choice landing page
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=14-05-2003 |
    When the user selects the proband
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes for RD
    ##This line added for NTS-3409
    And the user submits the patient choice with signature
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button on the patient choice
    And the user should able to see TO DO list even after clicking the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    Then the "<PatientChoice>" page is displayed
    Then the help text is displayed
    Then the Patient Choice landing page is updated to "Agreed to testing" for the proband
    #Patient Choice - Family Details Provided below should be same as above
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=14-05-2003 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    Then the user is navigated to a page with title Patient choice
    And the user sees the patient choice status for proband as Agreed to testing
    And the user sees the patient choice status for family member 1 as Agreed to testing
    Then the "<PatientChoice>" stage is marked as Completed
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user sees the patient choice status for proband as Agreed to testing
    And the user sees the patient choice status for family member 1 as Agreed to testing
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user clicks on the Back link
    Then the user is navigated to a page with title Patient choice
    Examples:
      | PatientDetails  | TestPackage  | NoOfParticipants | FamilyMembers  | PatientChoice  |
      | Patient details | Test package | 2                | Family members | Patient choice |