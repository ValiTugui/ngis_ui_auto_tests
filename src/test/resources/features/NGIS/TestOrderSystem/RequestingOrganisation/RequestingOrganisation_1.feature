#@regression
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@ReqOrg
Feature: TestOrder - Requesting Organisation page 1

  @NTS-3069
    #@E2EUI-916 @E2EUI-936 @E2EUI-1421 @E2EUI-1343 @E2EUI-980 @E2EUI-1290
    #@E2EUI-924 @E2EUI-1206
  Scenario Outline: NTS-3069 - Feature: Find/Select Ordering Entity
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
#    When the user navigates to the "Requesting organisation" stage
#    And the user is navigated to a page with title Add a requesting organisation

    When the user navigates to the "<stage>" stage
    And the user is navigated to a page with title Add a requesting organisation
    And the user enters ordering entity name and verify the message
      | searchDetails | messageText                                           |
      | lllLondon     | Your search has returned 0 results. Please try again. |
      | xyxyx         | Your search has returned 0 results. Please try again. |
      | $@%','*%#$    | Your search has returned 0 results. Please try again. |
      | 11111         | Your search has returned 0 results. Please try again. |

    ##Steps for NTS-3069
    And the user enters the invalid keyword "<ordering_entity_name>" in the search field
    Then  the Save and Continue button should be disabled
    And the user enters the invalid keyword "<ordering_entity_name1>" in the search field
    Then  the Save and Continue button should be disabled
    When the user navigates to the "Patient details" stage

    Examples:
      | stage                   | ordering_entity_name | ordering_entity_name1 |
      | Requesting organisation | k                    | Xy                    |

  @NTS-3047 @NTS-3155 @NTS-3383 @Z-LOGOUT
  # @E2EUI-1413 @E2EUI-1360 @E2EUI-1361 @E2EUI-1542 @E2EUI-1415
  Scenario Outline: NTS-3047 - Find my ordering entity via name to order a test from the correct Lab/GLH
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
    When the user navigates to the "<stage>" stage
    And the user is navigated to a page with title Add a requesting organisation
    And the user sees the search label with "<introMessage>"
    Then the user should be able to see hint text in search box on requesting organisation page
    And the user sees the search field with search icon
    And the user see the search field has placeholder text as "<placeholderText>"
    And the Save and Continue button should be disabled
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    And the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Completed

    Examples:
      | stage                   | introMessage                                                   | placeholderText                                                                         | ordering_entity_name |
      | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust | Bedford              |

  @HTO-782
  Scenario Outline: HTO-782 TO - Find/Select Ordering Entity - With Valid Search Term - <ordering_entity_name>
    Given a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | <ci_term> | <ci_type> | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
     ##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    And the user is navigated to a page with title Add a requesting organisation
#    And the "Patient details" stage is marked as Completed
    And the user sees the search label with "<introMessage>"
    Then the user should be able to see hint text in search box on requesting organisation page
    And the user sees the search field with search icon
    And the user see the search field has placeholder text as "<placeholderText>"
    And the Save and Continue button should be disabled
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    And the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the "<stage>" stage is marked as Completed

    Examples:
      | ci_term                    | ci_type | stage                   | introMessage                                                   | placeholderText                                                                         | ordering_entity_name         |
      | Cancer of Unknown Primary  | cancer  | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust | BANBURY CROSS HEALTH CENTRE  |
      | Skeletal dysplasia         | RD      | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust | BANBURY CROSS HEALTH CENTRE  |
      | Paediatric Tumours         | cancer  | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust | MIDLANDS MEDICAL PARTNERSHIP |
      | Sudden cardiac death PILOT | RD      | Requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust | MIDLANDS MEDICAL PARTNERSHIP |
