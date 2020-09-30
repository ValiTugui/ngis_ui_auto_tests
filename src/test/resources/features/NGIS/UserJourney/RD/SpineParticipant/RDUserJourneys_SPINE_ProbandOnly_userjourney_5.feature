#@userJourneys
#@userJourneysRD
#@userJourneysRD_SPINE
@SYSTEM_INTEGRATION_TEST_SPINE
Feature: Create Referrals for SPINE Patient
@spine
  @NTS-4570 @Z-LOGOUT
#    @E2EUI-1430 @UseCase02
  Scenario Outline: NTS-4570: Use Case#02: Create Referral for Proband Only + Default Data + Patient Choice Yes - Search Spine Patient
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ### Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ###Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R59 | GEL_NORMAL_USER | NHSNumber=<NhsNumber>:DOB=<DOB>:Ethnicity=A - White - British |

    #Requesting Organisation
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
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
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
      | NhsNumber  | DOB        | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | ClinicianName      | Panels | Pedigree |
      |9449310084  | 20-10-1973 | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Samuel:LastName=John:Department=Greenvalley,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree |

  @NTS-4608 @Z-LOGOUT
#     @E2EUI-1267 @UseCase03
  Scenario Outline: NTS-4608: Use Case#03: Create Referral for Proband Only + Edit Data + Patient Choice Yes - Search Spine Patient
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ### Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ###Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=<NhsNumber>:DOB=<DOB>:Ethnicity=A - White - British |

    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Croydon Health Services NHS Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package -
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
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
    Then the user is navigated to a page with title Panels
    And the user should see the default status of penetrance button as Complete
    When the user search and add the "<SearchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree -  Modify pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    ##Print forms
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | NhsNumber  | DOB      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                                   | ClinicalQuestion   | ClinicalQuestionDetails                                       | Notes | PatientChoiceStage | RecordedBy         | Panels | Pedigree | SearchPanels |
      |9449310084  | 20-10-1973| Patient details | Requesting organisation | Test package | 1                | Responsible clinician | FirstName=William:LastName=John:Department=West Minister road | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=04,05:HpoPhenoType=Cachexia | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree | Cataracts    |

  @NTS-4575 @Z-LOGOUT
#    @E2EUI-1059 @UseCase04
  Scenario Outline: NTS-4575: Use Case#04: Create Referral for Proband Only + Default Data + Patient Choice No - Search Spine Patient
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ### Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ###Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=<NhsNumber>:DOB=<DOB>:Ethnicity=A - White - British |

    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Rotherham Doncaster and South Humber NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package -
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
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
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the "Patient choice" page is displayed
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
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
      | NhsNumber  | DOB | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | RecordedBy         | Panels | Pedigree |
      |9449310084  | 20-10-1973| Patient details | Requesting organisation | Test package | 1                | Responsible clinician | FirstName=Glen:LastName=Martin:Department=Victoria st,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree |

  @NTS-4554 @Z-LOGOUT
#    @E2EUI-1383 @UseCase05
  Scenario Outline: NTS-4554:: Use Case#05: User Journey to Create NGIS Referral for Proband Only + Edit Data + Patient Choice Yes - Search NGIS Patient
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ### Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ###Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=<NhsNumber>:DOB=<DOB>:Ethnicity=A - White - British |

    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Rotherham Doncaster and" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only - No of participants -1
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
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
    ##Notes automatically filling notes with some random data
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice
    When the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
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
    When the user search and add the "<searchPanels>" panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    And the user clicks the Save and Continue button
    Then the "<Pedigree>" stage is marked as Completed
    #Print forms
    Then the user is navigated to a page with title Print sample forms
    And the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"
    Examples:
      | NhsNumber  | DOB | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                             | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | RecordedBy                            | Panels | searchPanels | Pedigree |
      |9449308969  | 11-03-2009| Patient details | Requesting organisation | Test package | 1                | Responsible clinician | FirstName=Karan:LastName=Sam:Department=Riverside st,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Cataracts    | Pedigree |

  @NTS-4571 @Z-LOGOUT
#    @E2EUI-907 @UseCase06
  Scenario Outline: NTS-4571: Use Case#06 Create Referral for Proband Only + Default Data + Patient Choice Not Given - Search Spine Patient
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ### Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ###Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=<NhsNumber>:DOB=<DOB>:Ethnicity=A - White - British |

   ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Rotherham Doncaster and South Humber NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only - No of participants -1
    When the user navigates to the "<TestPackage>" stage
    And the user selects the number of participants as "<NoOfParticipants>"
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
    When the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient conversation happened; form to follow for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    Then the "<PatientChoiceStage>" stage is marked as Completed
    ##Panels
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
    | NhsNumber  | DOB  | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                              | ClinicalQuestion   | ClinicalQuestionDetails                                                     | Notes | PatientChoiceStage | RecordedBy         | Panels | Pedigree |
    |9449303673  | 16-07-2010 | Patient details | Requesting organisation | Test package | 1                | Responsible clinician | FirstName=Glen:LastName=Martin:Department=Victoria st,uk | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | Notes | Patient choice     | ClinicianName=John | Panels | Pedigree |

  @NTS-4561 @Z-LOGOUT
#    @E2EUI-977 @UseCase07
  Scenario Outline: NTS-4561: Use Case#07: Create Referral for Proband Only + Edit Data + Patient Choice Not Given - Search Spine Patient
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    ###Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    When the user types in different valid details in the NHS number "<NhsNumber>" and DOB "<DOB>" fields
    And the user clicks the Search button
    ### Check if NGIS and convert to SPINE from NEAT
    Then the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool
    ###Start the referral
    When a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=<NhsNumber>:DOB=<DOB>:Ethnicity=A - White - British |
            ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
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
      | NhsNumber  | DOB  | PatientDetails  | RequestingOrganisation  | TestPackage  | OneParticipant | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                                                             | Notes | PatientChoiceStage | RecordedBy                            | Panels | searchPanels | Pedigree |
  |9449310084  | 20-10-1973 | Patient details | Requesting organisation | Test package | 1              | Responsible clinician | FirstName=Karan:LastName=Singh:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema:PhenotypicSex=Male:KaryotypicSex=XY | Notes | Patient choice     | ClinicianName=John:HospitalNumber=123 | Panels | Cataracts    | Pedigree |