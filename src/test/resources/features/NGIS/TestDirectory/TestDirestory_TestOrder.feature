@01-TEST_DIRECTORY
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: Test Directory: TestOrder

  @NTS-3493 @Z-LOGOUT
# @E2EUI-2015 @scenario_01
  Scenario Outline: NTS-3493: Restricted access to navigate to cancelled referrals
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R14 | GEL_SUPER_USER | NHSNumber=2000001327:DOB=05-12-1987 |
    ##Patient Details Page
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
#    Then the user is navigated to a page with title Add a requesting organisation
    When the user clicks the Cancel referral link
    And the user selects the cancellation reason "<Reason>" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"

    Examples:
      | Reason                                             | RevokeMessage                                                             |
      | The referral has been paused or stopped (“Revoke”) | This referral has been cancelled so further changes might not take effect |

  @HTO-483
  Scenario Outline:HTO-483 User searches for Clinical indication after the patient details are loaded
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    Then the Patient Details page is displayed
    And the Start New Referral button is enabled
    And the user clicks the Start Referral button
    Then the "<newpageTitle>" page is displayed
    And the user types in the "<ciTerm>" in the search field
    And the user clicks on first Clinical indications result displayed in Test Oder
    Then the user is navigated to a page with title Test Order Forms
#    And the "Patient details" stage is marked as Completed
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Add a requesting organisation

    Examples:
      | hyperlinkText               | reason_for_no_nhsNumber       | ciTerm                           | newpageTitle        |
      | create a new patient record | Other (please provide reason) | Angiomatoid Fibrous Histiocytoma | Clinical Indication |


  @HTO-483
  Scenario Outline:HTO-483 User can view the test details from the CI search page
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    And the Patient Details page is displayed
    When the Start New Referral button is enabled
    And the user clicks the Start Referral button
    Then the "<newpageTitle>" page is displayed
    When the user types in the "<ciTerm>" in the search field
    And the user clicks on About this test button
    Then the user should be able to see details about this test in a new modal window
      | Technology               |
      | Scope                    |
      | Targeted genes           |
      | Sample                   |
      | Optimum family structure |
      | Eligibility criteria     |
    When the user click on Continue test ordering button
    And the user clicks on first Clinical indications result displayed in Test Oder
    Then the user is navigated to a page with title Test Order Forms
#    And the "Patient details" stage is marked as Completed
#    And the user clicks the Save and Continue button
#    Then the user is navigated to a page with title Add a requesting organisation

    Examples:
      | hyperlinkText               | ciTerm                           | reason_for_no_nhsNumber       | newpageTitle        |
      | create a new patient record | Angiomatoid Fibrous Histiocytoma | Other (please provide reason) | Clinical Indication |

  @HTO-494
  Scenario Outline:HTO-494 User sees <auditMessage> when starting a new referral for an existing patient
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"
    Given the user clicks the - "Back to patient search" - link
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    Given the user search for the new patient using date of birth, first name, last name and gender
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the "<auditMessage>" message "<visibility>" displayed

    Examples:
      | hyperlinkText               | pageTitle         | reason_for_no_nhsNumber       | patient-search-type | auditMessage                                                                                | visibility |
      | create a new patient record | Find your patient | Other (please provide reason) | NGIS                | Your access will be audited to validate there is a legitimate relationship with the patient | is         |


  @HTO-996 @newCIchecks
  Scenario: HTO-996 CI card should have CI name, type and code being displayed
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field
      | R441 |
    Then the first clinical indication result card displayed in Test Directory contains "Unexplained death in infancy and sudden unexplained death in childhood" CI Name
    And the CI Type and CI Code displayed on the card in Test Directory are "Rare and inherited Disease - R441"
    When the user types in the CI term  in the search field
      | M130 |
    Then the first clinical indication result card displayed in Test Directory contains "Cribriform Neuroepithelial Tumour - Paediatric" CI Name
    And the CI Type and CI Code displayed on the card in Test Directory are "Tumour - M130"
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "create a new patient record" link to fill all fields without NHS number and reason "Other (please provide reason)"
    And the Patient Details page is displayed
    When the Start New Referral button is enabled
    And the user clicks the Start Referral button
    Then the "Clinical Indication" page is displayed
    When the user types in the "R441" in the search field
    Then the first clinical indication result card displayed in Test Ordering contains "Unexplained death in infancy and sudden unexplained death in childhood" CI Name
    And The CI Type and CI Code displayed on the card in Test Ordering are "Rare and inherited Disease - R441"
    When the user types in the "M130" in the search field
    Then the first clinical indication result card displayed in Test Ordering contains "Cribriform Neuroepithelial Tumour - Paediatric" CI Name
    And The CI Type and CI Code displayed on the card in Test Ordering are "Tumour - M130"


