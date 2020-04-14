#@regression
#@patientChoice
@CONSENT
@SYSTEM_TEST
Feature: Patient Choice -13 Page - FamilyMemberAddition

  @NTS-3411 @LOGOUT
    #@E2EUI-1583 @E2EUI-1760 @E2EUI-1516 @E2EUI-1570
  Scenario Outline: NTS-4099 : The Patient Choice page is not loading when there are more than 1 participants
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1997:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Test Package
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
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=14-05-1928:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=10-11-1929:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    And the user should see the details of family members displayed in patient choice landing page
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=14-05-1928 |
      | NHSNumber=NA:DOB=10-11-1929 |

    Examples:
      | PatientDetails  | TestPackage  | NoOfParticipants | FamilyMembers  | PatientChoice  |
      | Patient details | Test package | 3                | Family members | Patient choice |

  @NTS-3451 @LOGOUT
    #@E2EUI-2109
  Scenario Outline: NTS-3451: Validate the Patient choice section is incomplete by not submitting the choice for selected Family member
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1987:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ##Family Members - Family member details to be added
    When the user navigates to the "<FamilyMembers>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user adds "<NoOfParticipants>" family members to the proband patient as new family member patient record with below details
      | FamilyMemberDetails                                                 | RelationshipToProband | DiseaseStatusDetails                                            |
      | NHSNumber=NA:DOB=12-05-1933:Gender=Male:Relationship=Father         | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
      | NHSNumber=NA:DOB=10-12-1951:Gender=Male:Relationship=Maternal Uncle | Maternal Uncle        | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
     #Patient Choice - Doing PC for only one Family Member
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user edits patient choice for "<NoOfParticipants>" family members with the below details
      | FamilyMemberDetails         | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                  | ChildAssent | ParentSignature |
      | NHSNumber=NA:DOB=12-05-1933 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient has agreed to the test |             | Yes             |
    Then the "<PatientChoice>" stage is marked as Mandatory To Do
    Examples:
      | TestPackage  | NoOfParticipants | FamilyMembers  | PatientChoice  |
      | Test package | 3                | Family members | Patient choice |


  @NTS-3445 @LOGOUT
    #@E2EUI-1931
  Scenario Outline: NTS-3445: Validate the incomplete status of Patient choice and Family members stage with a red asterisk and without a green tick
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2005:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    ##Test Package No of participants -1
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
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
      | Test package | 1                | Family members | Patient choice |