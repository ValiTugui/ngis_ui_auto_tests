#@regression
#@clinicalIndicationTestSelect
@01-TEST_DIRECTORY
@SYSTEM_TEST
Feature: Test Directory: ClinicalIndicationSelect_4

  @NTS-4710 @Z-LOGOUT
#    @E2EUI-1573
  Scenario Outline: NTS-4710:E2EUI-1573: test environment content consistency: update save and continue
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | Rare Diseases | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER | child |
    ##Requesting Organisation Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Clinical Question Page
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Notes Page
#    Then the user is navigated to a page with title Add notes to this referral
    And the user is navigated to a page with title Add clinical notes
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Family Members - Family member details to be added - creating new referrals
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Continue with this family member
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Patient Choice Landing Page
    When the user navigates to the "<PatientChoice>" stage
    And the user is navigated to a page with title Patient choice
#    Then the "<PatientChoice>" stage is selected
    #### Not working due to defect:- https://jira.extge.co.uk/browse/NTOS-5039
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes
    And the user submits the patient choice with signature
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Patient choice
#    And the user clicks on Continue Button
    And the user clicks the Save and Continue button
    ##Panels Page
#    Then the user is navigated to a page with title Panels
    Then the user is navigated to a page with title Manage panels
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Pedigress Page
    Then the user is navigated to a page with title Build a pedigree
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Print Forms Page
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | ordering_entity_name                | NoOfParticipants | ResponsibleClinicianDetails                          | ClinicalQuestionDetails                                                     | FamilyMemberDetails                 | PatientChoice  | RelationshipToProband |
      | Northern Devon Healthcare NHS Trust | 1                | FirstName=Winnie:LastName=Ocean:Department=Down Town | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | NHSNumber=9449303924:DOB=14-05-2004 | Patient choice | Maternal Uncle        |
