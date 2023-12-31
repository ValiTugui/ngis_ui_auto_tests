@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@PrintForms
Feature: Print Forms 1 - Offline Orders

  @NTS-4713 @E2EUI-957  @NTS-6619
    #@E2EUI-1795
    #NTS-5965
  Scenario Outline: NTS-4713: scenario_1: PDFs: Implement Generic PDF forms-Rare Disease
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
    And the user is able to download form of the "Additional family members" section and validate the text "<Text1>" in the file "AdditionalParticipantForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "AdditionalParticipantForm.pdf"
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | Text                                                                                                                                                                                                                                                                                                                                                                                          | Text1                                                                                                                                                                                                                                                                                                                                                                                           | RequestingOrganisation                                  |
      | RARE AND INHERITED,QR06_G v1.21,Reason NHS Number not available,Patient not eligible for NHS Number (e.g. foreign national),Other (provide reason):,Liverpool Heart and Chest Hospital NHS Foundation Trust,Samples (being sent to GLH DNA extraction lab),Blood (EDTA),Amniotic fluid,Fetal blood (EDTA),Chorionic Villus,Fresh Tissue (not tumour),Stored DNA,Sample ID,N ot for C inical U | RARE AND INHERITED,QR06_F_G v1.21,Reason NHS Number not available,Patient not eligible for NHS Number (e.g. foreign national),Other (provide reason):,Liverpool Heart and Chest Hospital NHS Foundation Trust,Samples (being sent to GLH DNA extraction lab),Blood (EDTA),Amniotic fluid,Fetal blood (EDTA),Chorionic Villus,Fresh Tissue (not tumour),Stored DNA,Sample ID,N ot for C inical U | Liverpool Heart and Chest Hospital NHS Foundation Trust |


  @NTS-4713  @NTS-6619
    #@E2EUI-1795
    #NTS-5965
  Scenario Outline: NTS-4713: scenario_2: PDFs: Implement Generic PDF forms-Cancer
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | M88 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "<RequestingOrganisation>" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
#    Then the "Review test selection" page is properly opened and by default a test is selected
    Then the "<ReviewTestSelection>" page is properly opened and by default has a number of <NoOfTests> tests selected
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
      | ReviewTestSelection   | NoOfTests | Text                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              | RequestingOrganisation                                  |
      | Review test selection | 2         | CANCER,QT04_G v1.21,Liverpool Heart and Chest Hospital NHS Foundation Trust,Complete for tumour samples (being sent to GLH DNA extraction lab),Complete for germline samples (being sent to GLH DNA extraction lab),Reason NHS Number not available,Patient not eligible for NHS Number (e.g. foreign national),Other (provide reason):,Sample ID,Primary,Metastatic,Unknown,Lymphoma,Haemato-oncology liquid tumour,AML,ALL,Other (please specify),Date of this diagnosis,Blood (EDTA),Saliva,Fibroblasts,Skin biopsy,Other,N ot for C linical U | Liverpool Heart and Chest Hospital NHS Foundation Trust |

  @NTS-5982
  Scenario Outline: NTS-5982: scenario_1: Design: Interactive PDF forms-Rare Disease
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
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "ReferralForm.pdf"
    And the user is able to download form of the "Additional family members" section and validate the text "<Text1>" in the file "AdditionalParticipantForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "AdditionalParticipantForm.pdf"
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | Text                                                                                                                                                    | Text1                                                                                                                                                                                                                                                                      | RequestingOrganisation                                  |
      | RARE AND INHERITED DISEASES PROBAND,Patient conversation taken place; Record of Discussion form to follow,Additional clinical information (if relevant) | RARE AND INHERITED DISEASES FAMILY MEMBER,Patient conversation taken place; Record of Discussion form to follow,Additional clinical information (if relevant), Specific rare or inherited diseases that are suspected or have been confirmed (OMIM/Orphanet) (if relevant) | Liverpool Heart and Chest Hospital NHS Foundation Trust |

  @NTS-5982
  Scenario Outline: NTS-5982: scenario_2: Design: Interactive PDF forms-Cancer
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | M88 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "<RequestingOrganisation>" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
#    Then the "Review test selection" page is properly opened and by default a test is selected
    Then the "<ReviewTestSelection>" page is properly opened and by default has a number of <NoOfTests> tests selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the warning message contains the text "If there is more than one tumour (distinct tissue mass) to be tested, please complete separate referral forms for each tumour and provide a different identifying description to distinguish them (e.g. 3 o`clock and 12 o`clock)" in the Referral section
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "ReferralForm.pdf"
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | ReviewTestSelection   | NoOfTests | Text                                                                                                                                                                                          | RequestingOrganisation                                  |
      | Review test selection | 2         | CANCER,Patient conversation taken place; Record of Discussion form to follow,Additional clinical information (if required),Additional tumour information (if relevant),Responsible consultant | Liverpool Heart and Chest Hospital NHS Foundation Trust |
