@userJourneys
@userJourneysRD
@userJourneysRD_probandOnlyWithoutPC
Feature: Create Referral for Proband Only + Edit Data + Patient Choice Not Given

  @NTS-4555 @E2EUI-1298 @LOGOUT
  Scenario Outline: NTS-4555: Use Case #7: Create Referral for Proband Only + Edit Data + Patient Choice Not Given - Search NGIS Patient

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449306680:DOB=14-06-2011 |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    When the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Greater Manchester Mental Health" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    When the user clicks the Save and Continue button
    Then the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Proband only
    When the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
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
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
     ##Panels
    Then the user is navigated to a page with title Panels
    And the user should see the default status of penetrance button as Incomplete
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
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
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                                                             | Notes | PatientChoiceStage | RecordedBy                            | Panels | searchPanels                  | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Karan:LastName=Singh:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema:PhenotypicSex=Male:KaryotypicSex=XY | Notes | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Arrhythmogenic cardiomyopathy | Pedigree |

  @NTS-4561 @E2EUI-977 @LOGOUT
  Scenario Outline: NTS-4561: Use Case #7: Create Referral for Proband Only + Edit Data + Patient Choice Not Given - Search Spine Patient

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R59 | GEL_NORMAL_USER | NHSNumber=9449303673:DOB=16-07-2010 |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    When the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "The Whittington Hospital NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    When the user clicks the Save and Continue button
    Then the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Proband only
    When the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
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
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
     ##Panels
    Then the user is navigated to a page with title Panels
    And the user should see the default status of penetrance button as Complete
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
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
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                                                             | Notes | PatientChoiceStage | RecordedBy                            | Panels | searchPanels                  | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Karan:LastName=Singh:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema:PhenotypicSex=Male:KaryotypicSex=XY | Notes | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Arrhythmogenic cardiomyopathy | Pedigree |

  @NTS-4556 @E2EUI-1274 @LOGOUT
  Scenario Outline: NTS-4556: Use Case #7: Create Referral for Proband Only + Default Data + Patient Choice Not Given - Search Non Spine/NGIS Patient
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R14 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=27-07-1987:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
    When the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "UNIVERSITY DENTAL HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    When the user clicks the Save and Continue button
    Then the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - Proband only
    When the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
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
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
     ##Panels
    Then the user is navigated to a page with title Panels
    And the user should see the default status of penetrance button as Complete
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
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
      | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                                                             | Notes | PatientChoiceStage | RecordedBy                            | Panels | searchPanels                  | Pedigree |
      | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Karan:LastName=Singh:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema:PhenotypicSex=Male:KaryotypicSex=XY | Notes | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Arrhythmogenic cardiomyopathy | Pedigree |