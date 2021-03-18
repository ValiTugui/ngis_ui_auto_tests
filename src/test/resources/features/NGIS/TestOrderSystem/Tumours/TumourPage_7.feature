#@tumoursPage
@03-TEST_ORDER
@Tumours
@SYSTEM_TEST
@SYSTEM_TEST_3
Feature: Tumours Page - 7

  @NTS-3225 @NTS-3204 @NTS-3154 @NTS-3431 @Z-LOGOUT
#    @E2EUI-2279 @E2EUI-1434 @E2EUI-890 @E2EUI-1026  @E2EUI-894 @E2EUI-1549 @E2EUI-949 @E2EUI-997
  Scenario Outline: :NTS-3225:E2EUI-2279,1434: Edit a tumour page - The saved changes are displayed in the Edit a Tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    #NTS-3431
    And the "<stage>" stage is marked as Mandatory To Do
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    #NTS-3154
    And the user see a tick mark next to the added tumour
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"
    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    #NTS-3204
    Then the user is navigated to a page with title Edit a tumour
    And an information "<information>" is displayed that a test cannot start without a tumour

    And the user edits the tumour system questions fields and select a new tumour type "<updated_tumour_type>"
    And the user clicks the Save and Continue button
    And the user navigates to the "<stage>" stage
    And the tumour stage is on select or edit a tumour page showing
      | pageTitleHeader         | notificationTextHeader | textInformationHeader                           | linkToAddANewTumourHeader | NumberOfTumoursAdded |
      | Select or edit a tumour | Tumour updated         | Only one tumour can be tested in each referral. | add a new tumour          | 1                    |
    And the user selects the existing tumour from the landing page by clicking on the chevron right arrow icon
    And the "<pageTitle>" page is displayed
    And the new tumour details are displayed in the Edit a Tumour page

    Examples:
      | stage   | tumour_type              | presentationType | searchTerm | updated_tumour_type   | pageTitle     | notificationText | information                                                                                              |
      | Tumours | Solid tumour: metastatic | Recurrence       | test       | Solid tumour: primary | Edit a tumour | Tumour added     | A laboratory cannot start a test without a tumour (neoplasm).-Each referral can only include one tumour. |


