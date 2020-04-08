#@userJourneys
#@userJourneysRD
#@userJourneysRD_NEW_ProbandOnly
@SYSTEM_INTEGRATION_TEST
Feature: UserJourney_RD_NEW_Proband_1 - UC02-03 - E2EUI-1166,1032

  @NTS-4568 @LOGOUT
#    @E2EUI-1166 @UseCase02
  Scenario Outline: NTS-4568: Use Case#02: Create Referral for Proband Only + Default Data + Patient Choice Yes - Search Non Spine/NGIS Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R84 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=27-07-1987:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Rotherham Doncaster and South Humber NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only
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
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members
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
    Then the user is navigated to a page with title Panels
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
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | ClinicianName      | Panels | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree |

  @NTS-4606 @LOGOUT
#    @E2EUI-1032 @UseCase03
  Scenario Outline: NTS-4606: Use Case#03: Create Referral for Proband Only + Edit Data + Patient Choice Yes - Search Non Spine/NGIS Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R193 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-05-2007:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Croydon Health Services NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
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
    Then the user is navigated to a page with title Add notes to this referral
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
    When the user selects the option Child in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    Then the user should see the question displayed as Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Parent(s) / guardian have agreed to the test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    Then the user should see the question displayed as Has research participation been discussed?
    When the user selects the option Yes for the question Has research participation been discussed?
    Then the user should see the question displayed as The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    When the user selects the option Yes for the question The patient's parent(s) / guardian agrees that their child's data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Child assent
    Then the user should see the question displayed as Does the child agree to participate in research?
    When the user selects the option Yes for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    And the user fills signature details in Child signature
    And the user clicks on Continue Button
    When the user is in the section Parent/Guardian signature
    And the user fills "<Parent/Guardian signature>" details for signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    And the user should see the default status of penetrance button as Incomplete
    When the user search and add the "<SearchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree -  Modify pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    ###Submitting Referral
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    And the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                                   | ClinicalQuestion   | ClinicalQuestionDetails                                       | Notes | PatientChoiceStage | RecordedBy         | Panels | Pedigree | SearchPanels | Parent/Guardian signature          |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=William:LastName=John:Department=West Minister road | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=04,05:HpoPhenoType=Cachexia | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree | Cataracts    | FirstName=WILTON:LastName=BRITTAIN |