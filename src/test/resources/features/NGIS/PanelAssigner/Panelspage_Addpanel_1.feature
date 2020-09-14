#Josephine release
@06-PANEL_ASSIGNER
@SYSTEM_TEST

Feature: Submitting the referral after saving the Panels stage

  @NTS-5947 @Z-LOGOUT @Scenario1
  Scenario Outline: NTS-5947:Automatically save suggested panels, if no other panels have been saved upon submission of the referral.

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
        ##Requesting Organisation
    When the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "The Royal Orthopaedic Hospital NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
         ##Test Package -1 participants
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
     ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
     #Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
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
    When the user is in the section Patient choices
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And Penetrance section with options Complete and Incomplete
    And the user clicks on Incomplete button and button will show tick marked
    And the user clicks on Complete button and button will show tick marked
    And the user should see the section with title Suggestions based on the clinical information
    And the user sees suggested panels under the section Suggestions based on the clinical information
    And the user sees link with title View On PanelApp attached to all the suggested panels
    And the user clicks the Save and Continue button
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | PatientDetails  | OneParticipant | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Panels | RecordedBy                            |
      | Patient details | 1              | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Panels | ClinicianName=John:HospitalNumber=123 |

  @NTS-5947 @Z-LOGOUT @Scenario2
  Scenario Outline:NTS-5947:Saving selected panel suggestions(Disease status=affected),if the panels are not previously saved.

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
        ##Requesting Organisation
    When the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "The Royal Orthopaedic Hospital NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
         ##Test Package -1 participants
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
     ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
     #Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
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
    When the user is in the section Patient choices
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    When the user submits the referral
    And the submission confirmation message "Your referral has been submitted" is displayed
    Then the referral status is set to "Submitted"

    Examples:
      | OneParticipant | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | RecordedBy                            |
      | 1              | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | ClinicianName=John:HospitalNumber=123 |

  @NTS-5947 @Z-LOGOUT @Scenario3
  Scenario Outline:NTS-5947:Saving unselected panel suggestions(Disease status= unaffected),if the panels are not previously saved.

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
        ##Requesting Organisation
    When the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "The Royal Orthopaedic Hospital NHS Foundation Trust" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
         ##Test Package -1 participants
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
     ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    ##Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
     #Notes
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
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
    When the user is in the section Patient choices
    When the user selects the option Patient changed their mind about the clinical test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Patient choice
    And the user clicks the Save and Continue button
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And Penetrance section with options Complete and Incomplete
#    And the user should see the section with title Suggestions based on the clinical information
#    And the user sees suggested panels are coming as unchecked by default under the section Suggestions based on the clinical information
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Title>"
    Then the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | MandatoryStagesToComplete |
      | Panels                    |
    And the user should be able to close the pop up dialog box

    Examples:
      | OneParticipant | RecordedBy                            | ClinicalQuestion   | ClinicalQuestionDetails                                           | Panels | Title                        | ResponsibleClinicianDetails                               |
      | 1              | ClinicianName=John:HospitalNumber=123 | Clinical questions | DiseaseStatus=Unaffected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Panels | There is missing information | FirstName=Karen:LastName=Smith:Department=Victoria Street |

#  @NTS-5947 @Z-LOGOUT @Scenario4
  #as per the discussion with manual team it is not feasible for automation since required dev tools interaction
  Scenario Outline:NTS-5947:Message has to be displayed to alert the user if auto-save fails,when panels are not previously saved.

    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Add a requesting organisation
    And the user clicks the Save and Continue button
    ##Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the panels landing page displays the introduction message as shown below
      | The test package requires:         |
      | confirmation of disease penetrance |
      | addition of at least one panel     |
    And the user should be able to see a sub title Confirm disease penetrance on panels page
    And Penetrance section with options Complete and Incomplete
    And the user should be able to see an additional line "<penetranceIntro>" underneath the penetrance title
    And the user should see the section with title Suggestions based on the clinical information
    And the user sees suggested panels under the section Suggestions based on the clinical information
#    And the user sees suggested panels are coming as selected by default under the section Suggestions based on the clinical information
    When the user submits the referral
#    And the saving of the panels fails for any reason
#    Then the referral is not submitted
#    And a message is shown to alert the user
    Examples:
      | ClinicalQuestion   | ClinicalQuestionDetails                                         | Panels | penetranceIntro                                                                                                                                                           |
      | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Panels | Change suggested penetrance if:there is a referral form that confirms a different penetrance local decision-making processes indicate a different penetrance is preferred |








