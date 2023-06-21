@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_3
@Sample
Feature: Samples Page -5

  @NTS-3312 @Z-LOGOUT
#    @NTS-3312 @NTS-3332 @NTS-3333 @NTS-3308
#    @E2EUI-868 @@E2EUI-1261 @E2EUI-887 @E2EUI-943 @E2EUI-2338 @E2EUI-1232 @E2EUI-1446 @E2EUI-1272 @E2EUI-1252
  Scenario Outline: NTS-3312:E2EUI-868,1261,887: Add a sample page - Validate the mandatory input fields in add a Sample page without filling in the fields
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
     ##Test Order Forms
#    Then the user is navigated to a page with title Test Order Forms
    And the "Patient details" stage is marked as Completed
##Requesting Organisation
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
#    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
#NTS-3332
    And the Add a Sample page displays the appropriate field elements - sample type, sample state and sampleID
#NTS-3333
    #noinspection NonAsciiCharacters
    And the labels and help hint texts are displayed on Add a Sample page
      | labelHeader    | HintTextHeader                                                                         |
      | Sample type ✱  | None                                                                                   |
      | Sample state ✱ | None                                                                                   |
      | Sample ID ✱    | This could be the block ID, sample ID or nucleic acid ID given at the local laboratory |
#NTS-3312
    And the user clicks the Save and Continue button
    #noinspection NonAsciiCharacters
    Then the error messages for the sample mandatory fields on Add a Sample page are displayed
      | labelHeader    | errorMessageHeader        |
      | Sample type ✱  | Sample type is required.  |
      | Sample state ✱ | Sample state is required. |
      | Sample ID ✱    | Sample ID is required.    |
 #NTS-3308
    And the following drop-down values are displayed for Sample types on Add a sample page
      | sampleTypesHeader         |
      | Solid tumour sample       |
      | Liquid tumour sample      |
      | Normal or germline sample |
    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


