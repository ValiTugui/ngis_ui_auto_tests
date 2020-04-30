#@regression
#@printForms
@03-TEST_ORDER5
@SYSTEM_TEST
Feature: Print Forms 5 - Field name validations in Print Forms

  @NTS-5191
  # @E2EUI-1603
  Scenario Outline:NTS-5191:E2EUI-1603: scenario_1: Finalise copy on PDF forms with respect to acceptance criteria- Rare Disease
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | R89 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "Liverpool Heart and Chest Hospital NHS Foundation Trust" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"
    And the user is able to download form of the "Additional family members" section and validate the text "<AdditionalFormText>" in the file "AdditionalParticipantForm.pdf"

    Examples:
      | Text                                                                                                                                                                                       | AdditionalFormText                                                                                                                                                                                                                                                                                                                         |
      | RARE AND INHERITED ,Patient first name,Patient last name,Patient conversation taken place; Record of Discussion form to follow,1 of 2,2 of 2,Additional clinical information (if relevant) | RARE AND INHERITED,First name,Last name,Patient conversation taken place; Record of Discussion form to follow,1 of 2,2 of 2,Additional clinical information (if relevant),HPO terms,Specific rare or inherited diseases that are suspected,have been confirmed (OMIM/Orphanet) (if relevant),Additional clinical information (if relevant) |

  @NTS-5191
  # @E2EUI-1603
  Scenario Outline:NTS-5191:E2EUI-1603:scenario_2: Finalise copy on PDF forms with respect to acceptance criteria-Cancer
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | M89 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "Liverpool Heart and Chest Hospital NHS Foundation Trust" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the warning message contains the text "If there is more than one tumour (distinct tissue mass) to be tested, please complete separate referral forms for each tumour and provide a different identifying description to distinguish them (e.g. 3 o`clock and 12 o`clock)" in the Referral section
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"

    Examples:
      | Text                                                                                                                                                                                                                                                     |
      | CANCER,Patient conversation taken place; Record of Discussion form to follow,Additional clinical information (if required),Additional tumour information (if relevant),Haemato-oncology liquid tumour requests only,Responsible consultant,1 of 2,2 of 2 |

  @NTS-5191
  # @E2EUI-1115
  Scenario Outline:NTS-5191:E2EUI-1115: Cancer generic forms updates
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | M89 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "Liverpool Heart and Chest Hospital NHS Foundation Trust" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the warning message contains the text "If there is more than one tumour (distinct tissue mass) to be tested, please complete separate referral forms for each tumour and provide a different identifying description to distinguish them (e.g. 3 o`clock and 12 o`clock)" in the Referral section
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"

    Examples:
      | Text                                                                                                                                                                                                                                                     |
      | CANCER,Solid tumour requests only,Haemato-oncology liquid tumour requests only,Complete for tumour samples,Complete for tumour samples,Complete for germline samples,Fresh frozen tumour,Sample ID  |
