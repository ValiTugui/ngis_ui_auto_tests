#@regression
#@requestingOrganisationTestDirectory
@01-TEST_DIRECTORY
@SYSTEM_TEST
Feature: TestDirectory: Requesting Organisation page

    @NTS-3315
#      @E2EUI-916 @E2EUI-1456 @E2EUI-978 @E2EUI-951
    Scenario Outline: NTS-3315:E2EUI-916,1456,978,951 - Find/Select Ordering Entity - With Valid Search Term
      Given a web browser is at the Private Test Selection homepage
        | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
      And the user types in the CI term  in the search field and selects the first result from the results list
        | Angiomatoid Fibrous Histiocytoma |
      And the user clicks the Start Test Order Referral button
      When the user clicks the PDF order form button
      Then the requesting organisation page in Test Directory is displayed with Title, title copy text, search icon and search placeholder text
        | Add a requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust |
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the search results are displayed
    And  the Continue button should be clickable

    Examples:
      | ordering_entity_name |
      | Maidstone            |
      | Man                  |

  @NTS-3315
#   @E2EUI-916 @E2EUI-1019 @E2EUI-1442
    Scenario Outline: NTS-3315:E2EUI-916,1019,1442 - Find/Select Ordering Entity - With Invalid Search Term
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    Then the requesting organisation page in Test Directory is displayed with Title, title copy text, search icon and search placeholder text
      | Add a requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust |
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then there isn't any search results returned
    And  the user sees the tool tip with text as "<headerText>"
    When the user clicks on the tool tip
    Then a slide out panel is displayed with following header as "<headerText>",  body details as "<bodyText>" and a X for the user to select to close the panel
    And the user is able to close the panel

    Examples:
      | ordering_entity_name | headerText                                 | bodyText                                                                                                                                              |
      | lllLondon            | I can't find my requesting organisation... | NGIS does not yet support non-NHS (e.g. private, international) patients. If your patient is non-NHS, please follow your existing ordering processes. |

  @NTS-3315
#  @E2EUI-978
  Scenario: NTS-3315:E2EUI-978: Find/Select Ordering Entity - With Valid Search Term
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    Then the requesting organisation page in Test Directory is displayed with Title, title copy text, search icon and search placeholder text
      | Add a requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust |
    And the user should be able to see a click able link "Cancel order" at top right side of the page
    When the user clicks the link Cancel Order
    Then the browser navigates to the previously selected Clinical Indication Details page while still saving the user's most recent search for further page navigation

  @NTS-4725 @Z-LOGOUT
#    @E2EUI-879
  Scenario Outline: NTS-4725:E2EUI-879: Highlight the current/active stage
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R14 | GEL_NORMAL_USER | NHSNumber=9449306680:DOB=14-06-2011 |
    ##Patient Details Page
    When the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientDetails>" stage
    Then the user is navigated to a page with title Check your patient's details
    And the user should be able to see the active stage "<PatientDetails>" in to-do list
    And the user clicks the Save and Continue button
    Then the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "CLATTERBRIDGE HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user should be able to see the active stage "<RequestingOrganisation>" in to-do list
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package - proband only - No of participants -1
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user should be able to see the active stage "<TestPackage>" in to-do list
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
#    Then the user is navigated to a page with title Add notes to this referral
    Then the user is navigated to a page with title Add clinical notes
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    Then the "<Notes>" stage is marked as Completed
    ##Family Members Page
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks the Save and Continue button
    ##Patient Choice Page
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    And the below stages marked as completed
      | Patient details         |
      | Requesting organisation |
      | Test package            |
      | Clinical questions      |
#    And the user should be able to see the active stage "<PatientChoice>" in to-do list
    ### Defect where Patient choice and Patient details are active at the same time -NTOS-5039
    And the below stages marked as incompleted
      | Family members |
      | Patient choice |
      | Panels         |
      | Print forms    |
    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | PatientChoice  | CompletedStages |
      | Patient details | Requesting organisation | Test package | 1                | Responsible clinician | FirstName=Karan:LastName=Singh:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema | Notes | Patient choice |                 |
