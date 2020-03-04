@regression
@TO_RD
@GlobalFlow
@GlobalFlow_Validations_For_RD

Feature: Global Patient Flow for Rare-Disease

  @NTS-4711 @E2EUI-964 @E2EUI-1587 @LOGOUT @RD
  Scenario Outline:NTS-4711:Verify Page titles for RD on every stage
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R86 | GEL_NORMAL_USER |
    ##Patient Search Page Title
    And the user is navigated to a page with title Find your patient
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    When the user verify the text present in the page as "<Referral>"
    And the message "No patient found" is displayed below the search button
    Then the user clicks on the create new patient record
    ##Create a New Patient Page Title
    And the user is navigated to a page with title Add a new patient to the database
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "Patient is a foreign national"
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Start a new Referral button
    ##Patient Details Page Title
    When the user is navigated to a page with title Check your patient's details
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page Title
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "HALTON HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package Page Title
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "1"
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page Title
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Question Page Title
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ##Notes Page Title
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Family Members Page Title
    Then the user is navigated to a page with title Add a family member to this referral
    When the user verify the text present in the page as "<Referral>"
    And the user clicks on Add family member button
    ##Family Members Search Page Title
    Then the user is navigated to a page with title Find a family member
    When the user verify the text present in the page as "<Referral>"
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    ## Relationship what we pass need to update
    Then the user is navigated to family member test package page with title Select tests for full sibling
    And the user clicks on the Back link
    ##Family Members Details Page Title
    Then the user is navigated to a page with title Confirm family member details
    And  the user clicks the Save and Continue button
    ##Family Members Test Package Page Title
    Then the user is navigated to family member test package page with title Select tests for full sibling
    And  the user clicks the Save and Continue button
    ##Family Members Clinical Questions Page Title
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    And  the user clicks the Save and Continue button
    ####Family Members Landing Page Title
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice Page Title
    Then the user is navigated to a page with title Patient choice
    When the user edits the patient choice status
    ##Patient Choice Form Page Title
    Then the user is navigated to a page with title Add patient choice information
    When the user verify the text present in the page as "<Referral>"
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
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Patient Choice Landing Page Title
    Then the user is navigated to a page with title Patient choice
    When the user edits the index number 1 of family member in patient choice
    ##Patient Choice Form For Family Member Page Title
    When the user is navigated to a page with title Add family member patient choice information
    And the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    And the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    ##Patient Choice Landing Page Title
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    ##Panels Page Title
    Then the user is navigated to a page with title Panels
    And the user clicks the Save and Continue button
    ##Pedigree Page Title
    When the user is navigated to a page with title Build a pedigree
    Then the user should be able to see SaveAndContinue button on Pedigree Page
    And the user verify the text present in the page as "<Referral>"
    Then the user clicks on Save and Continue on Pedigree Page
    ##print Forms Page Title
    When the user is navigated to a page with title Print sample forms
    And the user submits the referral
    ##Referral Submitted
    And the submission confirmation message "Your referral has been submitted" is displayed
    When the referral status is set to "Submitted"
    Then the user verify the text present in the page as "<Referral>"

    Examples:
      | ResponsibleClinicianDetails                               | ClinicalQuestionDetails                                            | FamilyMemberDetails                                               | DiseaseStatusDetails                                                                                            | RecordedBy           | Referral          |
      | FirstName=Karen:LastName=Smith:Department=Victoria Street | DiseaseStatus=Affected:AgeOfOnset=03,02:HpoPhenoType=Lower lip pit | NHSNumber=NA:DOB=11-04-2000:Gender=Male:Relationship=Full Sibling | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality:PhenotypicSex=Male:KaryotypicSex=XY | ClinicianName=Govind | referral,Referral |

  @NTS-4711 @E2EUI-964 @E2EUI-1587 @LOGOUT @Cancer/Tumour
  Scenario Outline:NTS-4711:Verify Page titles for Cancer/Tumour on every stage
    Given the user search and select clinical indication test for the patient through to Test Order System online service patient search
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M89 | GEL_NORMAL_USER |
    ##Patient Search Page Title
    When the user is navigated to a page with title Find your patient
    And the user types in invalid details of a patient in the NHS number and DOB fields
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Search button
    And the message "No patient found" is displayed below the search button
    And the user clicks on the create new patient record
    ##Create a New Patient Page Title
    And the user is navigated to a page with title Add a new patient to the database
    When the user create a new patient record without NHS number and enter a reason for noNhsNumber "Patient is a foreign national"
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Start a new Referral button
    ##Patient Details Page Title
    When the user is navigated to a page with title Check your patient's details
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page Title
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "BOLTON ROYAL HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Test Package Page Title
    Then the user is navigated to a page with title Confirm the test package
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page Title
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Tumour Page Title
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Tumour Dynamic Questions Page Title
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Added a Tumour Page Title
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Manage Samples Page Title
    Then the user is navigated to a page with title Manage samples
    When the user verify the text present in the page as "<Referral>"
    And the user clicks the Add sample button
    ##Add Samples Page Title
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    And the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Samples Dynamic Questions Page Title
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    And the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Manage Samples Page Title
    Then the user is navigated to a page with title Manage samples
    And the user verify the text present in the page as "<Referral>"
    When the user clicks the Save and Continue button
    ##Notes Page Title
    Then the user is navigated to a page with title Add notes to this referral
    When the user fills in the Add Notes field
    And the user verify the text present in the page as "<Referral>"
    And the user clicks the Save and Continue button
    ##Patient Choice Page Title
    Then the user is navigated to a page with title Patient choice
    And the user verify the text present in the page as "<Referral>"
    And the user edits the patient choice status
    ##Patient Choice Form Page Title
    Then the user is navigated to a page with title Add patient choice information
    And the user verify the text present in the page as "<Referral>"
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    Then the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    ##Patient Choice Landing Page Title
    Then the user is navigated to a page with title Patient choice
    When the user clicks on Continue Button
    ##print Forms Page Title
    When the user is navigated to a page with title Print sample forms
    And the user submits the referral
    ##Referral Submitted
    And the submission confirmation message "Your referral has been submitted" is displayed
    When the referral status is set to "Submitted"
    Then the user verify the text present in the page as "<Referral>"

    Examples:
      | tumour_type           | presentationType | sampleType          | sampleState         | RecordedBy                                | Referral |
      | Solid tumour: primary | Recurrence       | Solid tumour sample | Fresh frozen tumour | ClinicianName=Herman:HospitalNumber=11203 | referral |

  @NTS-4731 @E2EUI-1087 @E2EUI-873 @LOGOUT @Tumour
  Scenario Outline: NTS-4731: Verify warning pop up when navigating without saving changes
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M211 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=11-11-2011:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient's details
    And the user fill in the first name field
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "UNIVERSITY HOSPITAL AINTREE" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    When the user deselects the test
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user selects the test by clicking the deselected test
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Tumour Page
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select or edit a tumour
    And the success notification is displayed "Tumour added"
    And the user clicks the Save and Continue button
    ##Samples Page
    Then the user is navigated to a page with title Manage samples
    And the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "<sampleType>", sample state "<sampleState>" and filling SampleID
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add sample details
    When the user answers the Samples dynamic questions on Add a Sample Details page by selecting sample search"test"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Save and Continue button
    ##Notes Page
    Then the user is navigated to a page with title Add notes to this referral
    When the user fills in the Add Notes field
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Patient Choice Page
    Then the user is navigated to a page with title Patient choice
    And the user edits the patient choice status
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user selects the option Yes for the question Has research participation been discussed?
    And the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    And the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user clicks the Save and Continue button
    ##print Forms Page
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | warningMessage                    | warningMessage1                                             | newStage | acknowledgeMessage | tumour_type           | presentationType | sampleType          | sampleState         | RecordedBy                                  |
      | Changes you made may not be saved | This section contains unsaved information. Discard changes? | Notes    | Dismiss            | Solid tumour: primary | Recurrence       | Solid tumour sample | Fresh frozen tumour | ClinicianName=NARAYAN:HospitalNumber=102030 |

  @NTS-4731 @E2EUI-1087 @E2EUI-873 @LOGOUT @RD
  Scenario Outline: NTS-4731: Verify warning pop up when navigating without saving changes
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R81 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=02-01-2010:Gender=Female |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient's details
    And the user fill in the first name field
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "UNIVERSITY HOSPITAL AINTREE" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "2"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user selects the number of participants as "1"
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    Then the user is navigated to a page with title Add clinician information
    When the user fills in all the clinician form fields
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Clinical Question Page
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Notes Page
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Family Members Page
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    Then the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the user is navigated to a page with title Select tests for
    And the user clicks on the Back link
    Then the user is navigated to a page with title Confirm family member details
    And the user fill in the last name field
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Family Members Test Package Page
    Then the user is navigated to a page with title Select tests for
    And the user selects the test by clicking the deselected test
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Family Members Clinical Questions Page
    Then the user is navigated to a page with title Add family member details
    When the user fills the DiseaseStatusDetails for family member with the with the "<DiseaseStatusDetails>"
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice Page
    Then the user is navigated to a page with title Patient choice
    And the user edits the patient choice status
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Cancer (paired tumour normal) – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Patient conversation happened; form to follow for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    When the user edits the index number 1 of family member in patient choice
    Then the user is navigated to a page with title Add family member patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks on Continue Button
    Then the user is in the section Patient choices
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    ##Panels Page
    Then the user is navigated to a page with title Panels
    And the user search and add the "<SearchPanels>" panels
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Pedigree Page
    Then the user is navigated to a page with title Build a pedigree
    When the user clicks on the specified node on the pedigree diagram for "<MemberDetails>"
    And the user select the pedigree tab Clinical
    Then the user selects the document evaluation option
    ##Navigating By Logout
    When the user clicks the Log out button
    Then the user sees a prompt alert "<warningMessage>" after clicking "logout" button and "<acknowledgeMessage>" it
    ##Navigating By Other Stage
    When the user navigates to the "<newStage>" stage
    Then the user sees a prompt alert "<warningMessage1>" after clicking "<newStage>" button and "<acknowledgeMessage>" it
    ##Navigating By Refreshing the page
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<warningMessage>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks on Save and Continue on Pedigree Page
    ##Print forms
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | warningMessage                    | warningMessage1                                             | newStage | acknowledgeMessage | ClinicalQuestionDetails                   | FamilyMemberDetails                                               | DiseaseStatusDetails                    | RecordedBy                            | SearchPanels | MemberDetails               |
      | Changes you made may not be saved | This section contains unsaved information. Discard changes? | Notes    | Dismiss            | DiseaseStatus=Unaffected:AgeOfOnset=00,02 | NHSNumber=NA:DOB=19-04-2001:Gender=Male:Relationship=Full Sibling | DiseaseStatus=Affected:AgeOfOnset=01,02 | ClinicianName=Bond:HospitalNumber=007 | cardiac arr  | NHSNumber=NA:DOB=02-01-2010 |
