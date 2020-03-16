@regression
@testDirectory
@clinicalIndicationTestSelect

Feature: Test Directory Test Order

  @NTS-4565 @E2EUI-1842 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4565: Verify the confirmation message doesn't push down the content after cancelling a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    ##Patient Details Page
    Then the user is navigated to a page with title Check your patient
    And the web browser is still at the same "<PartCurrentURL>" page
    Examples:
      | PartCurrentURL |
      | test-order     |

  @NTS-3262 @E2EUI-1796 @v_1 @P0
  Scenario Outline: NTS-3262: Test package displayed as a table on clinical indication pages
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    And the user selects the "<TestPackageTab>" tab
    And the user clicks on view more icon
    Then the user should be able to see a new modal window

    Examples:
      | TestPackageTab |
      | Test Package   |

  @NTS-3493 @E2EUI-2015 @LOGOUT @v_1 @P0 @scenario_01
  Scenario Outline: NTS-3493: Restricted access to navigate to cancelled referrals
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R14 | GEL_SUPER_USER | NHSNumber=9449305897:DOB=10-10-1987 |
    ##Patient Details Page
    Then the user is navigated to a page with title Check your patient
    When the user clicks the Cancel referral link
    And the user selects the cancellation reason "<Reason>" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"

    Examples:
      | Reason                                             | RevokeMessage                                                             |
      | The referral has been paused or stopped (“Revoke”) | This referral has been cancelled so further changes might not take effect |

  @NTS-3161 @E2EUI-2091 @LOGOUT @scenario1
  Scenario Outline: NTS-3161: Verify Spinning Helix
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page
    When the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ##Note spinning helix is covered in Save and Continue button
    Examples:
      | ordering_entity_name |
      | Maidstone            |

  @NTS-3251 @E2EUI-1695 @v_1 @P0
  Scenario Outline: NTS-3251: Generic modal component for a button
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    ##Test package in test directory
    Then the user sees the "Test Package" tab is selected by default
    And the user clicks on view more icon
    Then the user should be able to see a new modal window
    And the user verifies the page will be covered by an overlay
    And the user click on Go to test page button
    Then the list of clinical indications are loaded
    ##Test package in Clinical Indications
    And the user sees the "Clinical Indications" tab is selected by default
    Then the user clicks on first Clinical indications results displayed
    And the user verifies the page will be covered by an overlay
    And the user sees Clinical indications modal with two sections and "Go to Clinical Indication" is present
      | <sectionName1> | <sectionName2> |
    When the user clicks the close icon of clinical indication pop up
    And the user should be able to see Clinical indications list is displayed containing clickable cards for each clinical indication
    Then the user should be able to see a link "<linkName>" at left side top of the page
    And the user clicks on Back to Search button

    Examples:
      | linkName       | sectionName1 | sectionName2             |
      | Back to search | Who to test  | Test package includes... |

  @NTS-4710 @E2EUI-1573 @LOGOUT
  Scenario Outline: NTS-4710: test environment content consistency: update save and continue
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | Rare Diseases | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER | child |
    ##Patient Details Page
    Then the user is navigated to a page with title Check your patient
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Test Package Page
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Clinical Question Page
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Notes Page
    Then the user is navigated to a page with title Add notes to this referral
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Family Members - Family member details to be added - creating new referrals
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Continue Button
    ##Patient Choice Landing Page
    And the user is navigated to a page with title Patient choice
    Then the "<PatientChoice>" stage is selected
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    And the user answers the patient choice questions with agreeing to testing - patient choice Yes
    And the user submits the patient choice with signature
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button on the "<PatientChoice>"
    And the user is navigated to a page with title Patient choice
    And the user clicks on Continue Button
    ##Panels Page
    Then the user is navigated to a page with title Panels
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Pedigress Page
    Then the user is navigated to a page with title Build a pedigree
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    ##Print Forms Page
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | ordering_entity_name                     | NoOfParticipants | ResponsibleClinicianDetails                          | ClinicalQuestionDetails                                                     | FamilyMemberDetails                | PatientChoice  | Panels | Pedigree | RelationshipToProband |
      | UNIVERSITY DENTAL HOSPITAL OF MANCHESTER | 1                | FirstName=Winnie:LastName=Ocean:Department=Down Town | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality | NHSNumber=9449304831:DOB=27-4-2007 | Patient choice | Panels | Pedigree | Maternal Uncle        |

  @NTS-4810 @E2EUI-1545 @LOGOUT
  Scenario Outline: NTS-4810: Deep linking - Add patient_id to URLs
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient
    Then the user should be able to see same referral id in the global banner and the url
    And the user clicks the Save and Continue button
    And the "<PatientDetails>" stage is marked as Completed
    ##Test Package Page
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    When the user selects the number of participants as "2"
    Then the user should be able to see same referral id in the global banner and the url
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    Then the user is navigated to a page with title Add clinician information
    ##Clinical Questions Page
    When the user navigates to the "<ClinicalQuestions>" stage
    Then the user is navigated to a page with title Answer clinical questions
    When the user selects "Unaffected"
    Then the user should be able to see same referral id in the global banner and the url
    And the user clicks the Save and Continue button
    ##Notes Page
    And the user is navigated to a page with title Add notes to this referral
    ##Family Member Page
    When the user navigates to the "<FamilyMember>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user is navigated to a page with title Find a family member
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    Then the patient card displays with Born,Gender and NHS No details
    When the user clicks on the patient card
    Then the user is navigated to a page with title Confirm family member details
    Then the user should be able to see same referral id in the global banner and the url
    When the user selects the Relationship to proband as "<RelationshipToProband>"
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    Then the user should be able to see same referral id in the global banner and the url
    And  the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And  the user selects "Unaffected"
    Then the user should be able to see same referral id in the global banner and the url
    And the user clicks the Save and Continue button
    ##Family Member Page
    Then the user is navigated to a page with title Add a family member to this referral

    Examples:
      | PatientDetails  | FamilyMember   | TestPackage  | ClinicalQuestions  | RelationshipToProband | FamilyMemberDetails                 |
      | Patient details | Family members | Test package | Clinical questions | Father                | NHSNumber=9449305307:DOB=14-02-2011 |

  @NTS-4701 @E2EUI-1645 @LOGOUT
  Scenario Outline: NTS-4701 : Stop a user losing their changes when they try to navigate to away using browser behaivor
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | Rare-Disease | create a new patient record | Patient is a foreign national | GEL_NORMAL_USER |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient's details
    And the user fill in the first name field
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    ##Test Package Page
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    When the user deselects the test
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "refresh" button and "<acknowledgeMessage>" it
    When the user selects the test by clicking the deselected test
    And the user clicks the Save and Continue button
    ##Requesting Organisation Page
    When the user navigates to the "<new_stage>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "UNIVERSITY HOSPITAL AINTREE" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    #Panels - no need to check for its completion
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Panels
    When the user search and add the "Cerebral malformations" panels
    When the user navigates to the "<new_stage>" stage
    Then the user sees a prompt alert "<partOfMessage>" after clicking "<new_stage>" button and "<acknowledgeMessage>" it
    When the user attempts to navigate away by clicking "refresh"
    Then the user sees a prompt alert "<partOfMessage1>" after clicking "refresh" button and "<acknowledgeMessage>" it
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed

    Examples:
      | new_stage               | TestPackage  | acknowledgeMessage | partOfMessage1                     | acknowledgeMessage | partOfMessage                                               | Panels |
      | Requesting organisation | Test package | Dismiss            | Changes you made may not be saved. | Dismiss            | This section contains unsaved information. Discard changes? | Panels |

  @NTS-4725 @E2EUI-879 @LOGOUT
  Scenario Outline: NTS-4725: Highlight the current/active stage
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R14 | GEL_NORMAL_USER | NHSNumber=9449306680:DOB=14-06-2011 |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient's details
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
    Then the user is navigated to a page with title Add notes to this referral
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
    And the user should be able to see the active stage "<PatientChoice>" in to-do list
    And the below stages marked as incompleted
      | Family members |
      | Patient choice |
      | Panels         |
      | Print forms    |


    Examples:
      | PatientDetails  | RequestingOrganisation  | TestPackage  | NoOfParticipants | ResponsibleClinician  | ResponsibleClinicianDetails                               | ClinicalQuestion   | ClinicalQuestionDetails                                         | Notes | PatientChoice  | CompletedStages |
      | Patient details | Requesting organisation | Test package | 1                | Responsible clinician | FirstName=Karan:LastName=Singh:Department=Victoria Street | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema | Notes | Patient choice |                 |

  @NTS-4726 @E2EUI-1155 @LOGOUT
  Scenario Outline: NTS-4726: Displaying the current state for each stage
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=9449309221:DOB=26-02-2004 |
      ##Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user should be able to see the active stage "<PatientDetails>" in to-do list
    And the user clicks the Save and Continue button
    Then the "<PatientDetails>" stage is marked as Completed
    And the below stages marked as incompleted
      | Requesting organisation |
      | Test package            |
      | Responsible clinician   |
      | Clinical questions      |
      | Patient choice          |
    And the print forms stage is locked
    ##Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Maidstone" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    And the "<RequestingOrganisation>" stage is marked as Completed
    ##Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Responsible Clinician
    Then the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user clicks the Save and Continue button
    And the "<ResponsibleClinician>" stage is marked as Completed
    ##Clinical Question
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add notes to this referral
    And the user fills in the Add Notes field
    And the user clicks the Save and Continue button
    And the "<Notes>" stage is marked as Completed
    Then the below stages marked as completed
      | Patient details         |
      | Requesting organisation |
      | Test package            |
      | Clinical questions      |
      | Notes                   |
    And the user should be able to see the active stage "<FamilyMembers>" in to-do list
    And the below stages marked as incompleted
      | Family members |
      | Patient choice |
      | Panels         |
      | Print forms    |
    Examples:
      | PatientDetails  | RequestingOrganisation  | NoOfParticipants | ResponsibleClinicianDetails                               | ResponsibleClinician  | ClinicalQuestionDetails                                         | Notes | FamilyMembers  |
      | Patient details | Requesting organisation | 2                | FirstName=Karen:LastName=Smith:Department=Victoria Street | Responsible clinician | DiseaseStatus=Affected:AgeOfOnset=01,02:HpoPhenoType=Lymphedema | Notes | Family members |
