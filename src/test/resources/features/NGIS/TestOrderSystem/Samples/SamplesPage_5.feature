@03-TEST_ORDER
@SYSTEM_TEST
@Sample
Feature: Samples Page -5

  @NTS-3308 @Z-LOGOUT
#    @E2EUI-943 @E2EUI-2338 @E2EUI-1232
  Scenario Outline: NTS-3308:E2EUI-943,2338,1232: Add a sample page - verify the sample type drop down list
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the following drop-down values are displayed for Sample types on Add a sample page
      | sampleTypesHeader         |
      | Solid tumour sample       |
      | Liquid tumour sample      |
      | Normal or germline sample |
#      | Abnormal tissue sample - Descoped 24/02/2020 |
#      | Omics sample  - Descoped 24/02/2020            |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |

  @NTS-3312 @Z-LOGOUT
#    @E2EUI-868 @@E2EUI-1261 @E2EUI-887
  Scenario Outline: NTS-3312:E2EUI-868,1261,887: Add a sample page - Validate the mandatory input fields in add a Sample page without filling in the fields
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the user clicks the Save and Continue button
    Then the error messages for the sample mandatory fields on Add a Sample page are displayed
      | labelHeader    | errorMessageHeader        |
      | Sample type ✱  | Sample type is required.  |
      | Sample state ✱ | Sample state is required. |
      | Sample ID ✱    | Sample ID is required.    |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |


  @NTS-3332 @Z-LOGOUT
#    @E2EUI-1446 @E2EUI-1272
  Scenario Outline: NTS-3332:E2EUI-1446,1272: Add a Sample page - Verify sample type, sample state and sampleID are display
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the Add a Sample page displays the appropriate field elements - sample type, sample state and sampleID

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |

  @NTS-3333 @Z-LOGOUT
#    @E2EUI-1252
  Scenario Outline: NTS-3333:E2EUI-1252: Add a Sample page - verify the help hint-text on Local sample tube ID
    Given a referral is created with the below details for a newly created patient and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Angiomatoid Fibrous Histiocytoma | Cancer | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
    And the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<stage>" stage
    Then the "<pageTitle>" page is displayed
    When the user clicks the Add sample button
    Then the "<pageTitle2>" page is displayed
    And the labels and help hint texts are displayed on Add a Sample page
      | labelHeader    | HintTextHeader                                                                         |
      | Sample type ✱  | None                                                                                   |
      | Sample state ✱ | None                                                                                   |
      | Sample ID ✱    | This could be the block ID, sample ID or nucleic acid ID given at the local laboratory |

    Examples:
      | stage   | pageTitle      | pageTitle2   |
      | Samples | Manage samples | Add a sample |