@regression
@printForms
Feature: Print Forms - Offline Orders

  @NTS-4703 @E2EUI-847 @E2EUI-938 @LOGOUT @v_1 @P0
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

  @NTS-4703 @E2EUI-957 @LOGOUT
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

  @NTS-4703 @E2EUI-1993 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4703: Scenario_1: PDF forms - change watermark 'VOID' to 'Not for Clinical Use'
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | R100 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    And the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Continue button
    Then the "Review test selection" page is properly opened and by default a test is selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Watermark>" in the file "ReferralForm.pdf"
    And the user is able to download form of the "Additional family members" section and validate the text "<Watermark>" in the file "AdditionalParticipantForm.pdf"
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | Watermark                     |
      | N o t f o r C i n i c a l U s |

  @NTS-Todo @E2EUI-1993 @LOGOUT @v_1 @P
  Scenario Outline: NTS- : Scenario_2: PDF forms - change watermark 'VOID' to 'Not for Clinical Use'
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R109 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=04-03-2000:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ###Requesting organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    When the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    And the user is able to download print form for the proband
    Then the user is able to validate the text "<Watermark>" in the downloaded form "SampleForm.pdf"

    Examples:
      | NoOfParticipants | PrintForms  | Watermark                         |
      | 1                | Print forms | N o t f o r C l i n i c a l U s e |

  @NTS-4713 @E2EUI-1795
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
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "ReferralForm.pdf"
    And the user is able to verify the section "receiving laboratory" in the downloaded form "ReferralForm.pdf"
    And the user is able to download form of the "Additional family members" section and validate the text "<Text>" in the file "AdditionalParticipantForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "AdditionalParticipantForm.pdf"
    And the user is able to verify the section "receiving laboratory" in the downloaded form "AdditionalParticipantForm.pdf"
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | Text                                                                                                                                       | RequestingOrganisation                                  |
      | RARE AND INHERITED,Liverpool Heart and Chest Hospital NHS Foundation Trust,North West Genomic Laboratory Hub,N o t f o r C i n i c a l U s | Liverpool Heart and Chest Hospital NHS Foundation Trust |

  @NTS-4713 @E2EUI-1795
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
    And the warning message contains the text "If there is more than one tumour (distinct tissue mass) to be tested, please complete separate referral forms for each tumour and provide a different identifying description to distinguish them (e.g. 3 o`clock and 12 o`clock)" in the Referral section
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<Text>" in the file "ReferralForm.pdf"
    And the user is able to verify the section "ordered test type" in the downloaded form "ReferralForm.pdf"
    And the user is able to verify the section "receiving laboratory" in the downloaded form "ReferralForm.pdf"
    Then the user is able to download form of the "Patient choice" section having file name "Consent.zip"

    Examples:
      | Text                                                                                                                      | RequestingOrganisation                                  |
      | CANCER,Liverpool Heart and Chest Hospital NHS Foundation Trust,North West Genomic Laboratory Hub,N oC l i n i c a l U s e | Liverpool Heart and Chest Hospital NHS Foundation Trust |
