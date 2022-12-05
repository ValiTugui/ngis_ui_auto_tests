#@regression
#@requestingOrganisationTestDirectory
@01-TEST_DIRECTORY
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: TestDirectory: Requesting Organisation page
#  @NTS-3161 @E2EUI-2091
  @NTS-3315 @Z-LOGOUT
#      @E2EUI-916 @E2EUI-1456 @E2EUI-978 @E2EUI-951   @E2EUI-916 @E2EUI-1019 @E2EUI-1442
  Scenario Outline: NTS-3315:E2EUI-916,1456,978,951 - Find/Select Ordering Entity - With Valid Search Term
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    Then the requesting organisation page in Test Directory is displayed with Title, title copy text, search icon and search placeholder text
      | Add a requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust | And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the search results are displayed
    And  the Continue button should be clickable
    And the user enters the invalid keyword "<ordering_entity_name1>" in the search field
    Then there isn't any search results returned
    And  the user sees the tool tip with text as "<headerText>"
    When the user clicks on the tool tip
    Then a slide out panel is displayed with following header as "<headerText>",  body details as "<bodyText>" and a X for the user to select to close the panel
    And the user is able to close the panel

    Examples:
      | ordering_entity_name | ordering_entity_name1 | headerText                                 | bodyText                                                                                                                                              |
      | Maidstone            | mmmMaidstone          | I can't find my requesting organisation... | NGIS does not yet support non-NHS (e.g. private, international) patients. If your patient is non-NHS, please follow your existing ordering processes. |
      | Man                  | lllLondon             | I can't find my requesting organisation... | NGIS does not yet support non-NHS (e.g. private, international) patients. If your patient is non-NHS, please follow your existing ordering processes. |

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

  @HTO-782
  Scenario Outline: HTO-782 TD - Find/Select Ordering Entity - With Valid Search Term - <ordering_entity_name>
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the "<ci_term>"  in the search field and selects the first result from the results list
    When the user clicks the Start Test Order Referral button
    And the user clicks the PDF order form button
    Then the requesting organisation page in Test Directory is displayed with Title, title copy text, search icon and search placeholder text
      | Add a requesting organisation | Enter the hospital trust for the clinic you are ordering from. | e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust |
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the search results are displayed
    And Requesting Organisation ID "<ordering_entity_id>" and Managing Entity "<managing_entity_name>" are correct for "<ordering_entity_name>"
    And  the Continue button should be clickable
    Examples:
      | ci_term                                   | ordering_entity_name         | ordering_entity_id | managing_entity_name                     |
      | Cancer of Unknown Primary                 | BANBURY CROSS HEALTH CENTRE  | K84028             | Central and South Genomic Laboratory Hub |
      | Paediatric Tumours                        | BANBURY CROSS HEALTH CENTRE  | K84028             | Central and South Genomic Laboratory Hub |
      | Ewing Sarcoma of Bone                     | BANBURY CROSS HEALTH CENTRE  | K84028             | Central and South Genomic Laboratory Hub |
      | Skeletal dysplasia                        | BANBURY CROSS HEALTH CENTRE  | K84028             | Central and South Genomic Laboratory Hub |
      | Cystic renal disease                      | BANBURY CROSS HEALTH CENTRE  | K84028             | Central and South Genomic Laboratory Hub |
      | Skeletal dysplasia                        | MIDLANDS MEDICAL PARTNERSHIP | M85063             | Central and South Genomic Laboratory Hub |
      | Hereditary ataxia with onset in adulthood | MIDLANDS MEDICAL PARTNERSHIP | M85063             | Central and South Genomic Laboratory Hub |
      | Endometrial Stromal Sarcoma               | MIDLANDS MEDICAL PARTNERSHIP | M85063             | Central and South Genomic Laboratory Hub |
      | Ewing Sarcoma of Bone                     | MIDLANDS MEDICAL PARTNERSHIP | M85063             | Central and South Genomic Laboratory Hub |
      | Retinal disorders                         | MIDLANDS MEDICAL PARTNERSHIP | M85063             | Central and South Genomic Laboratory Hub |

