#@regression
#@clinicalIndicationTestSelect
@TEST_DIRECTORY
@SYSTEM_TEST
Feature: Test Directory: ClinicalIndicationSelect_2

  @NTS-4565 @LOGOUT
#    @E2EUI-1842
  Scenario Outline: NTS-4565: Verify the confirmation message doesn't push down the content after cancelling a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=10-02-1985:Gender=Male |
    ##Patient Details Page
    Then the user is navigated to a page with title Check your patient
    And the web browser is still at the same "<PartCurrentURL>" page
    Examples:
      | PartCurrentURL |
      | test-order     |

  @NTS-3262
#    @E2EUI-1796
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

  @NTS-3493 @LOGOUT
#    @E2EUI-2015 @scenario_01
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

  @NTS-3161 @LOGOUT
#    @E2EUI-2091 @scenario1
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

  @NTS-3251
#    @E2EUI-1695
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

  @NTS-4710 @LOGOUT
#    @E2EUI-1573
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
    When the user selects the Relationship to proband as "<RelationshipToProband>" for family member "<FamilyMemberDetails>"
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Select tests for
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add family member details
    And the user sees the Save and Continue button
    And the user clicks the Save and Continue button
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
