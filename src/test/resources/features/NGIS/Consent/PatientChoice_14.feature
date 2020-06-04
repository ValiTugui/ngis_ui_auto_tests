#@regression
#@patientChoice
@05-CONSENT
@SYSTEM_TEST
Feature: Patient Choice-14 ConsentScenario - Child

  @NTS-3415 @Z-LOGOUT
    #@E2EUI-1627
  Scenario Outline: NTS-3415: Verify the patient Choice 'Save & Continue' button is disabled until the patient choice has been submitted
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1991:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMembers>" stage
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    And the user selects the Relationship to proband as "Full Sibling" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
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
    And the user should see Continue button as disabled
    When the user selects the option No for the question Has research participation been discussed?
    Then the user should see the question displayed as Why has research participation not been discussed?
    And the options displayed as below for the question Why has research participation not been discussed?
      | Inappropriate to have discussion                  |
      | Patient would like to revisit at a later date     |
      | Patient lacks capacity and no consultee available |
      | Other                                             |
    And the user will see a warning message "<WarningMessage>"
    And the user should see Continue button as disabled
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
      | FamilyMembers  | FamilyMemberDetails                 | ClinicalQuestionDetails                                         | WarningMessage                                                                                                         | RecordedBy                            |
      | Family members | NHSNumber=9449303959:DOB=14-09-2005 | DiseaseStatus=Affected:AgeOfOnset=02,02:HpoPhenoType=Lymphedema | All patients who receive genomic tests should be offered the opportunity to participate in research where appropriate. | ClinicianName=John:HospitalNumber=123 |