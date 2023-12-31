@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@PrintForms
Feature: Print Forms 9 - Offline Orders

  @NTS-5928 @NTS-6015
#    @NDGS-53
  Scenario Outline: NTS-5928: User visits offline order page for form download for Rare Disease
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
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<TextToValidate>" in the file "ReferralForm.pdf"
    And the user is able to download form of the "Additional family members" section and validate the text "<TextToValidate>" in the file "AdditionalParticipantForm.pdf"

    Examples:
      | TextToValidate                                                                                                                                                                                                                                               |
      | RARE AND INHERITED,Reason NHS Number not available,Patient not eligible for NHS Number (e.g. foreign national), Other (provide reason):,Viapath (GSTT),Viapath Genetics,Guy’s Hospital,Great Maze Pond,London,SE1 9RT,Surrey and Sussex Healthcare NHS Trust |

  @NTS-5928 @NTS-6015
#    @NDGS-53
  Scenario Outline: NTS-5928: scenario-2:User visits offline order page for form download for Cancer
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | M89 |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    And the user enters the keyword "Dorset Healthcare University NHS Foundation Trust" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
#    Then the "Review test selection" page is properly opened and by default a test is selected
    Then the "<ReviewTestSelection>" page is properly opened and by default has a number of <NoOfTests> tests selected
    When the user clicks the Continue button
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user should see the "Download" button next to each of the forms
    And the user is able to download form of the "Referral" section and validate the text "<TextToValidate>" in the file "ReferralForm.pdf"

    Examples:
      | ReviewTestSelection   | NoOfTests | TextToValidate                                                                                                                                                                                                                                                                                               |
      | Review test selection | 2         | Reason NHS Number not available,Patient not eligible for NHS Number (e.g. foreign national), Other (provide reason):,West Midlands Regional Genetics Laboratory,BIRMINGHAM WOMEN'S HOSPITAL, MINDELSOHN WAY, EDGBASTON, BIRMINGHAM, WEST MIDLANDS, B15 2TG,Dorset Healthcare University NHS Foundation Trust |