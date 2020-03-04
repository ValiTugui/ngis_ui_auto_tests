@regression
@printForms
Feature: Print Forms - Offline Orders

  @NTS-4703 @E2EUI-847 @E2EUI-938 @v_1 @P0
  Scenario Outline: NTS-4703: User visits offline order page for form download
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    And the user enters the keyword "Surrey and Sussex Healthcare NHS Trust" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    When the user clicks the Continue button
    Then the "Offline order" page is properly displayed for chosen clinical indication
    ##For E2EUI-938
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Additional family members" section and validate the text "<TextToValidate>" in the file "AdditionalParticipantForm.pdf"
    And the user is able to verify the section "receiving laboratory" in the downloaded form "AdditionalParticipantForm.pdf"
    ###The requesting organisation name should be same in the above step to add and the below example to search
    Examples:
      | TextToValidate                                                                                   |
      | RARE AND INHERITED DISEASES,Surrey and Sussex Healthcare NHS Trust,N o t f o r C i n i c a l U s |

  @NTS-4703 @E2EUI-957
  Scenario Outline: NTS-4703: Test Directory - Paper ordering process apply new styles on consent forms
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "<RequestingOrganisation>" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user should see the "Download" button next to each of the forms
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | RequestingOrganisation                                  |
      | Liverpool Heart and Chest Hospital NHS Foundation Trust |

