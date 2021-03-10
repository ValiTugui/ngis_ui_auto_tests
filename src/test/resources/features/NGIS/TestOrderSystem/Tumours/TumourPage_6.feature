@03-TEST_ORDER
@Tumours
@SYSTEM_TEST
Feature: Tumours Page - 6

  @NTS-3250 @NTS-4503 @Z-LOGOUT
#    @E2EUI-1247 @E2EUI-1130
  Scenario Outline: NTS-3250:E2EUI-1247: Verify the presence of pathology Sample Id and check long characters more than 20 can be entered.
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    #NTS-4503
    And the user answers the tumour system specific question fields - Description, Date of Diagnosis, amd Select a tumour type "<tumour_type>"
    When the user attempts to fill in the Tumour Description "<TumourDescription>" with data that exceed the maximum data allowed 45
    Then the user is prevented from entering data that exceed that allowable maximum data 45 in the "TumourDescription" field

    And the user enters "<pathologySampleId>" in the Pathology Sample ID field
    And the user answers the tumour system specific question fields - Description, Date of Diagnosis, amd Select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    And the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user answers the tumour dynamic questions for Tumour Diagnosis by selecting a SnomedCT from the searched "<searchTerm>" result drop list
    And the user clicks the Save and Continue button
    Then the new tumour is displayed in the landing page
    And the new tumour is not highlighted
    And the "<stage>" stage is marked as Completed
    And the success notification is displayed "<notificationText>"

    Examples: of filling out the year and leaving the month and day blank
      | stage   | pathologySampleId                 | tumour_type              | presentationType | searchTerm | notificationText | TumourDescription                                  |
      | Tumours | A12345678912345667890-ABCDEFGHIJK | Solid tumour: metastatic | Recurrence       | test       | Tumour added     | 12345678901234567890123456789012345678901234567890 |

