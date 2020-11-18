#@tumoursPage
@03-TEST_ORDER
@SYSTEM_TEST
Feature: Tumours Page - 4

  @NTS-4829 @Z-LOGOUT
#    @E2EUI-1758
  Scenario Outline:NTS-4829:E2EUI-1758:Update validation in Estimated Date of Diagnosis to account for birth date on Tumour page
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    And the user enters "<Date_of_Diagnosis>" in the date of diagnosis field
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the date of diagnosis field

    Examples:
      | stage   | Date_of_Diagnosis                                | error_message                                     |
      | Tumours | Month_is_more_than_9_months_before_date_of_birth | Cannot be more than 9 months before date of birth |
      | Tumours | Date_is_more_than_9_months_before_date_of_birth  | Cannot be more than 9 months before date of birth |
      | Tumours | Year_is_more_than_9_months_before_date_of_birth  | Cannot be more than 9 months before date of birth |

  @NTS-3487 @Z-LOGOUT
#    @E2EUI-2144 @E2EUI-2097
  Scenario Outline: NTS-3487:E2EUI-2144,2097: Change 'Tumour Content' display value
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M185 | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    When the user navigates to the "<stage>" stage
    Then the user is navigated to a page with title Add a tumour
    And the user answers the tumour system questions fields and select a tumour type "<tumour_type>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Answer questions about this tumour
    When the user answers the tumour dynamic questions for Tumour Core Data by selecting the tumour presentation "<presentationType>"
    And the user clicks the Save and Continue button
    And the "<stage>" stage is marked as Completed
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Manage samples
    When the user clicks the Add sample button
    Then the user is navigated to a page with title Add a sample
    When the user answers the questions on Add a Sample page by selecting the sample type "Solid tumour sample", sample state "Blood (EDTA)" and filling SampleID
    And the user clicks the Save and Continue button
    And the user sees no error message on tumour page by selecting the sample type "Solid tumour sample", sample state "Blood (EDTA)" and filling SampleID
    Then the user is navigated to a page with title Add sample details
    And asterisk "<TumourContentPercentageOfMalignant>" star symbol is shown as mandatory next to the Tumour content - percentage of malignant field label for only Solid tumour sample

    Examples:
      | stage   | tumour_type              | presentationType   | TumourContentPercentageOfMalignant                         |
      | Tumours | Solid tumour: metastatic | First presentation | Tumour content (percentage of malignant nuclei / blasts) ✱ |