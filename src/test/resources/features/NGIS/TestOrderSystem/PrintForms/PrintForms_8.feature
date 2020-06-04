@03-TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Print Forms 8 - User flows

 @NTS-4746 @Z-LOGOUT
#    @E2EUI-2094 @scenario_01
  Scenario Outline: NTS-4746:E2EUI-2094: scenario_01 Update warning box content on print sample forms
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient is a foreign national |
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ###Tumour
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the user clicks the Save and Continue button
    ###Samples
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
    ###Notes
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ###Patient Choice
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    When the user selects the option Child in patient choice category
    And the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    When the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
#    When the user clicks on Continue Button
    And the user clicks the Save and Continue button
    ###Print Forms
    And the user is navigated to a page with title Print sample forms
    Then the user should be able to see a "<WarningMessage>" on the print forms page

    Examples:
      | tumour_type           | presentationType   | sampleType          | sampleState         | ClinicianName                         | WarningMessage                                                                                            |
      | Solid tumour: primary | First presentation | Solid tumour sample | Fresh frozen tumour | ClinicianName=John:HospitalNumber=123 | Follow local trust information governance guidelines for data protection if saving sample forms anywhere. |

  @NTS-4746 @Z-LOGOUT
#    @E2EUI-2094  @scenario_02
  Scenario Outline: NTS-4746 : scenario_02 Update warning box content on print sample forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=15-09-2009:Gender=Male |
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    When the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    ###Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    And the user clicks the Save and Continue button
    ###Clinical Questions
    Then the user is navigated to a page with title Answer clinical questions
    When  the user selects "<diseaseStatueValue>"
    And the user provided the values "<year>" "<month>" for Age of onset fields
    And the user clicks the Save and Continue button
    ###Notes
    Then the user is navigated to a page with title Add clinical notes
    When the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    ###Family Members
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks the Save and Continue button
    ###Patient Choice
    Then the user is navigated to a page with title Patient choice
    And the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Child in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<ClinicianName>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Parent(s) / guardian changed their mind about the clinical test for the question Have the parent(s) / guardian had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user selects the option Not applicable for the question Does the child agree to participate in research?
    And the user clicks on Continue Button
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
#    And the user clicks on Continue Button
    And the user clicks the Save and Continue button
    ###Panels
    Then the user is navigated to a page with title Manage panels
    When the user clicks the Save and Continue button
    Then the user is navigated to a page with title Build a pedigree
    ###Print Forms
    When the user navigates to the "<printForms>" stage
    Then the user is navigated to a page with title Print sample forms
    Then the user should be able to see a "<WarningMessage>" on the print forms page

    Examples:
      | printForms  | OneParticipant | diseaseStatueValue | year | month | ClinicianName                         | WarningMessage                                                                                            |
      | Print forms | 1              | Unaffected         | 1    | 2     | ClinicianName=John:HospitalNumber=123 | Follow local trust information governance guidelines for data protection if saving sample forms anywhere. |