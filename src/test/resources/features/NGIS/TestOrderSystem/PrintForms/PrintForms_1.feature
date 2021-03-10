@03-TEST_ORDER
@SYSTEM_TEST
@PrintForms
Feature: Print Forms 1 - Offline Orders

#  @NTS-4713 @NTS-5191 @NTS-3414
    @NTS-4703 @E2EUI-957
#    @E2EUI-1795
  Scenario Outline: scenario_1: PDFs: Implement Generic PDF forms-Rare Disease
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | R89 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "<RequestingOrganisation>" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    ## Covered @NTS-4703 @NTS-3414
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "ReferralForm.pdf"
   ## Covered @NTS-5191
    And the user is able to download form of the "Additional family members" section and validate the text "<Text>" in the file "AdditionalParticipantForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "AdditionalParticipantForm.pdf"
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | Text                                                                                           | RequestingOrganisation                                  |
      | RARE AND INHERITED,Liverpool Heart and Chest Hospital NHS Foundation Trust,N ot for C inical U | Liverpool Heart and Chest Hospital NHS Foundation Trust |

#  @NTS-5191 @NTS-4703

  @NTS-4713
#    @E2EUI-1795
  Scenario Outline: scenario_2: PDFs: Implement Generic PDF forms-Cancer
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | M88 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "<RequestingOrganisation>" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
  ## Covered @NTS-5191 @NTS-4703
    And the warning message contains the text "If there is more than one tumour (distinct tissue mass) to be tested, please complete separate referral forms for each tumour and provide a different identifying description to distinguish them (e.g. 3 o`clock and 12 o`clock)" in the Referral section
    And the user should see the "Download" button next to each of the forms
    ## Covered @NTS-5191 @NTS-4703
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "ReferralForm.pdf"
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | Text                                                                                | RequestingOrganisation                                  |
      | CANCER,Liverpool Heart and Chest Hospital NHS Foundation Trust,N ot for C linical U | Liverpool Heart and Chest Hospital NHS Foundation Trust |