@E2E_TEST
@NTS-5705 @Z-LOGOUT
Feature: NTS-5705 Patient demographics are unclear - missing NHS number and typo on name

  Scenario Outline: NTS-5705 Patient demographics are unclear - missing NHS number and typo on name
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    And the user clicks the CI Search Start Referral button
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    And the user logs in to the Test Order system successfully
      | Find your patient |
    When the user is navigated to a page with title Find your patient
    And the user click YES button for the question - Do you have the NHS no?
    And the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the message "<message>" is displayed below the search button
    When the user clicks the "<hyperlinkText>" link from the No Search Results page
    And the "Create a record for this patient" page is displayed
    When the user updates the patient details stage with "<PatientDetailsUpdated>"
    And the user fills in the HospitalNo field
    And the user select a reason for "<reason_for_no_nhsNumber>"
    And the user clicks the Save patient details to NGIS button
    Then the patient is successfully created with a message "NGIS patient record created"
    Then the user clicks the Start Referral button to display the referral page
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    When the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Greater Manchester Mental Health" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    When the user clicks the Save and Continue button
    Then the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only
    Then the user is navigated to a page with title Confirm the test package
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
    ##Family Members -
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice
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
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | message          | hyperlinkText               | PatientDetailsUpdated                                                                    | reason_for_no_nhsNumber                                     | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | ClinicianName      | Panels | Pedigree |
      | No patient found | create a new patient record | FirstName=$#212%:LastName=#@(6#:Gender=Male:LifeStatus=Alive:Ethnicity=B - White - Irish | Patient not eligible for NHS number (e.g. foreign national) | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree |
