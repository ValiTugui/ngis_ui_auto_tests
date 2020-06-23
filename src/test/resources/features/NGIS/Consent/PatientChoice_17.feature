#@patientChoice
@05-CONSENT
@SYSTEM_TEST
Feature: Patient Choice-17 - Adult with Capacity

  @NTS-4100 @Z-LOGOUT
    #@E2EUI-1540
  Scenario Outline: NTS-4100: Removing the patient choice step in the family member flow
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1998:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Test Package - Trio family - No of participants -2
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
      | NHSNumber=NA:DOB=14-05-1936:Gender=Male:Relationship=Father | Father                | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema |
    Then the "<FamilyMembers>" stage is marked as Completed
    ###Validation of added family member is covered in addition step
    Examples:
      | TestPackage  | NoOfParticipants | FamilyMembers  |
      | Test package | 2                | Family members |