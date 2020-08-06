@PANEL_E2E_RT
@Panel_UJ_4
Feature: PanelAssigner: Selection operations in Panels in E2E user journey-4

  @NTS-5797 @Z-LOGOUT
  Scenario Outline: NTS-5797: Create and submit a referral with no suggested panels, and verify the Payload.
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R89 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=06-08-1998:Gender=Male |
     ###Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the "<PatientDetails>" stage is marked as Completed
     ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Rotherham Doncaster and South Humber NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
     ###Test Package - proband only
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    And the "<TestPackage>" stage is marked as Completed
     ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
     ###Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the "<ClinicalQuestion>" stage is marked as Completed
     ###Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
     ###Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
     ###Patient Choice
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<ClinicianName>" details in recorded by
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
    Then the "<PatientChoiceStage>" stage is marked as Completed
    And the user clicks the Save and Continue button
     ###Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
     ###No Submission without any panels
    When the user submits the referral
    Then the user should see a new popup dialog with title "There is missing information"
    Then the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | MandatoryStagesToComplete |
      | Panels                    |
     ### Adding a new panel to complete submission
    And the user should be able to click on incomplete "<Panels>" section
    Then the user is navigated to a page with title Manage panels
    And the user search and add the "<SearchPanels>" panels
    Then the user sees the selected "<SearchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
     ###Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
     ###Print forms
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | ClinicianName      | Panels | SearchPanels | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | ClinicianName=John | Panels | Cataracts    | Pedigree |

