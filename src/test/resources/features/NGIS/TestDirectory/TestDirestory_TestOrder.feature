@01-TEST_DIRECTORY
@SYSTEM_TEST
Feature: Test Directory: TestOrder

  @NTS-4565 @Z-LOGOUT
#    @E2EUI-1842
  Scenario Outline: NTS-4565: Verify the confirmation message doesn't push down the content after cancelling a referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=10-02-1985:Gender=Male |
    ##Patient Details Page
    Then the user is navigated to a page with title Add a requesting organisation
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

  @NTS-3493 @Z-LOGOUT
# @E2EUI-2015 @scenario_01
  Scenario Outline: NTS-3493: Restricted access to navigate to cancelled referrals
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R14 | GEL_SUPER_USER | NHSNumber=2000001327:DOB=05-12-1987 |
    ##Patient Details Page
    Then the user is navigated to a page with title Add a requesting organisation
    When the user clicks the Cancel referral link
    And the user selects the cancellation reason "<Reason>" from the modal
    And the user submits the cancellation
    Then the message should display as "<RevokeMessage>"

    Examples:
      | Reason                                             | RevokeMessage                                                             |
      | The referral has been paused or stopped (“Revoke”) | This referral has been cancelled so further changes might not take effect |

  @NTS-3161 @Z-LOGOUT
# @E2EUI-2091 @scenario1
  Scenario Outline: NTS-3161: Verify Spinning Helix
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | NGIS | Rare-Disease | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
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