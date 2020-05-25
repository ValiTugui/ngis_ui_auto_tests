@E2E_TEST

Feature: Cancer:NTS-4967:Select a test type in TS instead of CI and check the downstream

  @NTS-4967 @E2EUI-2680 @Z-LOGOUT
  Scenario Outline: NTS-4967 - CSV-E2E- Select a testtype in TS instead of CI and check the downstream
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user selects the Tests tab
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Epilepsy - early onset or syndromic WGS |
    And the user clicks the CI Search Start Referral button
    And the user selects the test in the test page and clicks on Continue button
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    #Test Ordering
    And the user logs in to the Test Order system successfully
      | Find your patient |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    When the user clicks the Start a new Referral button
      ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails>" stage is marked as Completed
      ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
      ##Test Package - No of participants -1
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<OneParticipant>"
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
      ##Family Members - for Additional Participants, need to deselect the test
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Continue with this family member
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user deselects the test
    And  the user clicks the Save and Continue button
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the deselected member "<FamilyMemberDetails>" status display as "<Status>"
    And the user clicks the Save and Continue button
    Then the "<FamilyMemberStage>" stage is marked as Completed
      ##Patient Choice
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
           ##Panels
    Then the user is navigated to a page with title Manage panels
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
      ###Pedigree
    Then the user is navigated to a page with title Build a pedigree
      ### need to modify pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
      ###Print forms
    When the user navigates to the "<PrintFormsStage>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user is able to download print forms for "<OneParticipant>" family members with the below details
      | FamilyMemberDetails         |
      | NHSNumber=NA:DOB=18-07-2015 |
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

     ##NOTE: ONLY GUI PART IS DONE. CSV,DDF PART TO BE DONE
    Examples:
      | hyperlinkText               | reason_for_no_nhsNumber       | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | FamilyMemberDetails                                               | DiseaseStatusDetails     | Status           | ResponsibleClinician  | ResponsibleClinicianDetails                                    | ClinicalQuestion   | ClinicalQuestionDetails                                                                              | Notes | FamilyMemberStage | PatientChoiceStage | RecordedBy         | Panels | Pedigree | searchPanels | PrintFormsStage |
      | create a new patient record | Patient is a foreign national | Patient details | Requesting organisation | Test package | 1              | NHSNumber=NA:DOB=18-07-2015:Gender=Male:Relationship=Full Sibling | DiseaseStatus=Unaffected | Not being tested | Responsible clinician | FirstName=George:LastName=Williams:Department=Prague Street,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=00,11:HpoPhenoType=Prostatitis:PhenotypicSex=Male:KaryotypicSex=XY | Notes | Family members    | Patient choice     | ClinicianName=John | Panels | Pedigree | Catar,Crani  | Print forms     |

