#@regression
#@paperForm
@01-TEST_DIRECTORY
@SYSTEM_TEST
@SYSTEM_TEST_2
Feature: TestDirectory: Paper Form


  @NTS-3288
    #@E2EUI-1022 @E2EUI-1257 @E2EUI-906
  Scenario Outline: NTS-3288 - Offline Order Page - Verify Offline Order Page is Displayed
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | <searchTerm> |
    And the user clicks the Start Test Order Referral button
    When the user clicks the PDF order form button
    When the user enters the keyword "<placeSearchTerm>" in the search field
    And the user selects the first entity from the suggestions list
    And the user clicks the Continue button
#    Then the "Review test selection" page is properly opened and by default a test is selected
    Then the "<ReviewTestSelection>" page is properly opened and by default has a number of <NoOfTests> tests selected
    And the user clicks the Continue button again
    Then the "Offline order" page is properly displayed for chosen clinical indication
    And the user should be able to see two sections as follows and a "Print Page" button
      | Complete the forms with patient information | Send samples and completed forms to |
    And the Complete the forms with patient information section the following should be displayed
      | <sectionName1> | <sectionName2> | <sectionName3> |
    And the user should see the "Download" button next to each of the forms
    And the user should be see lab details for "<placeSearchTerm>" under the heading Send samples and completed forms without any warning message

    Examples:
      | searchTerm | placeSearchTerm | ReviewTestSelection   | NoOfTests | sectionName1 | sectionName2              | sectionName3   |
      | R100       | Manchester      | Review test selection | 1         | Referral     | Additional family members | Patient choice |
      | M89        | Leeds           | Review test selection | 2         | Referral     | Patient choice            | null           |

  @NTS-3491
  #@E2EUI-2092
  Scenario:NTS-3491: Move the Online or Offline choice page
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start Test Order Referral button
    And the user should able to select online or offline order

  @NTS-3491
  #@E2EUI-2092  @E2EUI-1970 @E2EUI-2095
  Scenario Outline:NTS-3491: Move the Online or Offline choice page
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    And the user checks for different screen width of "<ScreenWidth>"
    And the user checks the presence of  horizontal scrollbar "<ScrollBarPresent>"
    And the user types in the CI term  in the search field and selects the first result from the results list
      | Angiomatoid Fibrous Histiocytoma |
    And the user clicks the Start Test Order Referral button
    And the user should able to select online or offline order
    And the user clicks the Sign in hyperlink
      | Sign in to the online service |
    And the user logs in to the Test Order system successfully
      | Find your patient |
    Then the user should be able to see NHS logo image

    Examples:
      | ScreenWidth | ScrollBarPresent |
      | 400         | Present          |
      | 1296        | Not Present      |
      | 1400        | Not Present      |


  @NTS-7771
  Scenario Outline:NTS-7771: Retired and Proposed <CI_code> code should not be displayed
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the "<CI_code>" term  in the search field
    Then the "<CI_code>" code and first result should not match
    #Then the "<CI_code>" code does not appear in any of the search results
    Examples:
      | CI_code |
      | M1      |
      | M2      |
      | M3      |
      | M4      |
      | M5      |
      | M6      |
      | M7      |
      | M8      |
      | M9      |
      | M10     |
      | M11     |
      | M12     |
      | M13     |
      | M15     |
      | M16     |
      | M17     |
      | M18     |
      | M187    |
      | M215    |
      | M217    |
      | M218    |
      | M219    |
      | M220    |
      | M221    |
      | M222    |
#      | M226    |
      | M227    |
      | M231    |
      | M14     |
      | R33     |
      | R34     |
      | R35     |


  @NTS-7771
  Scenario Outline:NTS-7771: Retired and Proposed <CI_full_name> term should not be displayed
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the "<CI_full_name>" term  in the search field
    Then the "<CI_full_name>" full name and first result should not match
    #Then the "<CI_full_name>" full name does not appear in any of the search results
    Examples:
      | CI_full_name                                                                            |
      | Colorectal Carcinoma                                                                    |
      | Ovarian Carcinoma                                                                       |
      | Breast Cancer                                                                           |
      | Non-Small Cell Lung Cancer                                                              |
      | Mesothelioma                                                                            |
      | Mucoepidermoid Carcinoma                                                                |
      | Melanoma - Adult                                                                        |
      | Gastrointestinal Stromal Tumour                                                         |
      | Thyroid Papillary Carcinoma - Adult                                                     |
      | Thyroid Follicular Carcinoma                                                            |
      | Poorly Differentiated Anaplastic Thyroid Carcinoma                                      |
      | Thyroid Medullary Carcinoma                                                             |
      | Phaeochromocytoma                                                                       |
      | Head and Neck Squamous Cell Carcinoma                                                   |
      | Adenoid Cystic Carcinoma                                                                |
      | Secretory Carcinoma (Salivary Gland)                                                    |
      | Renal Cell Carcinoma - Adult                                                            |
      | Uveal melanoma                                                                          |
      | Endometrial Cancer                                                                      |
      | Bladder Cancer                                                                          |
      | Prostate Cancer                                                                         |
      | Pancreatic Cancer                                                                       |
      | Cholangiocarcinoma                                                                      |
      | Spitzoid tumour                                                                         |
      | Hepatocellular carcinoma                                                                |
#      | Cancer of Unknown Primary                                                               |
      | Solid tumour other (i.e. specific histology not listed elsewhere in the test directory) |
      | Small cell lung cancer                                                                  |
      | Adrenal Cortical Carcinoma                                                              |
      | Possible X-linked retinitis pigmentosa                                                  |
      | Sorsby retinal dystrophy                                                                |
      | Doyne retinal dystrophy                                                                 |