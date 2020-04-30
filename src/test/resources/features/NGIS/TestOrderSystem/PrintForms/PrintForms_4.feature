#@regression
#@printForms
@03-TEST_ORDER4
@SYSTEM_TEST
Feature: Print Forms 4 - Field name consistency in Print Forms

  @NTS-3414
#    @E2EUI-2780
  Scenario Outline: NTS-3414: User visits offline order page for form download for Rare Disease
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
      | TextToValidate                                                                                                                                                                                          |
      | RARE AND INHERITED,v1.17,Samples (being sent to GLH DNA extraction lab),Blood (EDTA),Amniotic fluid, Fetal blood (EDTA), Chorionic Villus, Fresh Tissue (not tumour),Stored DNA,Sample ID, Age of onset |

  @NTS-3414
#    @E2EUI-2780
  Scenario Outline: NTS-3414: scenario-2:User visits offline order page for form download for Cancer
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | M89 |
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

    Examples:
      | TextToValidate                                                                                                                                                                                                                                                                                                                           |
      | CANCER,v1.17,Complete for tumour samples(being sent to GLH DNA extraction lab),Complete for germline samples (being sent to GLH DNA extraction lab),Sample ID,Primary,Metastatic,Unknown,Lymphoma,Haemato-oncology liquid tumour,AML,ALL,Other (please specify),Date of this diagnosis,Blood (EDTA),Saliva,Fibroblasts,Skin biopsy,Other |