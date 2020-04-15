#@regression
#@GlobalFlow
#@GlobalFlow_Validations_RD
@03-TEST_ORDER
@SYSTEM_TEST
Feature: GlobalConsistency: Global Patient Flow 2- End to end RD

  @NTS-4731 @Z-LOGOUT
#    @E2EUI-1087 @E2EUI-873
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
    And the user selects the proband
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
    When the user edits patient choice for the newly added family member
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
