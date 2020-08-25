Feature: RD Duo Family : Genetic sex does not match sex as reported

  @NTS-5723 @Z-LOGOUT
   #@E2EUI-2773
  Scenario Outline: NTS-5723: Create Referral for Additional Participants  - Genetic sex does not match sex as reported
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R14 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=16-10-1985:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    When the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    #Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    When the user clicks the Save and Continue button
    Then the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    Then the user navigates to the "<TestPackage>" stage
    When the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<TwoParticipant>"
    When the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
    ##Responsible Clinician
    When the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    When the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user answers the phenotypic and karyotypic sex questions
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
    ##Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members
    Then the user navigates to the "Family members" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    And the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient details
    Then the default family member details page is correctly displayed
    Then the user modifies the gender value
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user selects the test to add to the family member "<FamilyMemberDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    Then the user answers the phenotypic sex based on gender of the family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Patient conversation happened; form to follow for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
     ##Patient Choice - Family Members
    When the user edits patient choice for "<TwoParticipant>" family members with the below details
      | FamilyMemberDetails                 | PatientChoiceCategory | TestType                        | RecordedBy                            | PatientChoice                                      | ChildAssent | ParentSignature |
      | NHSNumber=9449305307:DOB=14-02-2011 | Adult (With Capacity) | Rare & inherited diseases – WGS | ClinicianName=John:HospitalNumber=123 | Patient changed their mind about the clinical test |             |                 |
    Then the "<PatientChoiceStage>" stage is marked as Completed
    And the user clicks the Save and Continue button
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user should see the default status of penetrance button as Complete
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    ##Submit Referral
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | TwoParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | FamilyMemberDetails                 | DiseaseStatusDetails                                               | RelationshipToProband | PatientChoiceStage | RecordedBy                            | Panels | searchPanels | Pedigree |
      | Patient details | Requesting organisation | Test package | 2              | Responsible clinician | FirstName=Karan:LastName=Singh:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema | Notes | NHSNumber=9449306621:DOB=09-05-2011 | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Early balding | Maternal Aunt         | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Cataracts    | Pedigree |