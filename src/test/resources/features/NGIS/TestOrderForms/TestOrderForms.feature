@SYSTEM_TEST
@TestOrderforms
Feature: Test Order Forms


  Scenario: Test Order Forms- Users can successfully upload up to 5 forms at a time
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    And the user uploads the following files
      | testfile.pdf | png_file.png | testfile_11MB.jpg | word_document.docx | consulteeform.pdf |
    Then the list of "Uploaded" files contains the following
      | testfile.pdf | png_file.png | testfile_11MB.jpg | word_document.docx | consulteeform.pdf |
    And the user uploads the following files
      | pdf_document.PDF | png_file_with_capitals.PNG | 1.JPEG | word_document_with_capitals.DOCX |
    Then the list of "Uploaded" files contains the following
      | testfile.pdf | png_file.png | testfile_11MB.jpg | word_document.docx | consulteeform.pdf | pdf_document.PDF | png_file_with_capitals.PNG | 1.JPEG | word_document_with_capitals.DOCX |
    When the user clicks on Continue Button
    Then the user is navigated to a page with title Check your patient's details


  Scenario: Test Order Forms- Uploaded list header should contain the number of uploaded files
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    And the user uploads the following files
      | testfile.pdf | png_file_with_capitals.PNG |
    And the list of "Uploaded" files in Test Order Forms contains 2 files
    And the user uploads the following files
      | testfile_11MB.jpg | word_document_with_capitals.DOCX | consulteeform.pdf |
    And the list of "Uploaded" files in Test Order Forms contains 5 files
    When the user uploads the following files
      | pdf_document.PDF | testfile_doc.docx |
    And the list of "Uploaded" files in Test Order Forms contains 7 files
    When the user clicks on Continue Button
    Then the user is navigated to a page with title Check your patient's details


  Scenario: Test Order Forms- Users should see an error message being displayed when trying to upload more than 5 form at a time
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    And the user uploads the following files
      | testfile.pdf | testfile2.pdf | word_document_with_capitals.DOCX | assentform.pdf | consulteeform.pdf | deceasedform.pdf |
    Then "Upload has failed" error banner should be displayed on Test Order Forms page
    And the following error messages are displayed under the Upload has failed error banner in Test Order Forms page
      | A maximum of 5 files can be uploaded at a time. |
    And the list of "Uploaded" files contains the following
      | testfile.pdf | testfile2.pdf | word_document_with_capitals.DOCX | assentform.pdf | consulteeform.pdf |
    When the user clicks on Continue Button
    Then the user is navigated to a page with title Check your patient's details


  Scenario: Test Order Forms- Users should see an error message being displayed when trying to upload forms of unsupported file types
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    And the user uploads the following files
      | CNV1_112008234_10002_referral_DDF_modified.json | png_file_with_capitals.PNG | testfile2.pdf | testfile_11MB.jpg | assentform.pdf |
    Then "Upload has failed" error banner should be displayed on Test Order Forms page
    And the following error messages are displayed under the Upload has failed error banner in Test Order Forms page
      | The selected file must be PDF, JPEG, PNG or DOCX. |
    Then the list of "Uploading" files contains the following
      | CNV1_112008234_10002_referral_DDF_modified.json |


  Scenario: Test Order Forms- Users should see an error message being displayed when trying the same form twice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    And the user uploads the following files
      | pdf_document.PDF | testfile2.pdf | testfile_11MB.jpg | assentform.pdf | consulteeform.pdf |
    And the user uploads the following files
      | pdf_document.PDF |
    Then "Upload has failed" error banner should be displayed on Test Order Forms page
    And the following error messages are displayed under the Upload has failed error banner in Test Order Forms page
      | A file named “pdf_document.PDF” has already been uploaded to this referral. |
    And the list of "Uploading" files contains the following
      | pdf_document.PDF |


  Scenario: Test Order Forms- Users should be able to delete unwanted forms and restore them back if needed
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    And the user uploads the following files
      | testfile.pdf | testfile2.pdf | testfile_11MB.jpg | assentform.pdf | consulteeform.pdf |
    When the user deletes "testfile.pdf" form
    Then the list of "Deleted" files contains the following
      | testfile.pdf |
    And the list of "Uploaded" files contains the following
      | testfile2.pdf | testfile_11MB.jpg | assentform.pdf | consulteeform.pdf |
    When the user restores "testfile.pdf" form
    And the list of "Uploaded" files contains the following
      | testfile.pdf | testfile2.pdf | testfile_11MB.jpg | assentform.pdf | consulteeform.pdf |

  ##The below scenario has to be reviewed if the current design flow changes

  Scenario: Test Order Forms- Users should be able to upload again deleted forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    And the user uploads the following files
      | testfile.pdf | testfile2.pdf | testfile_11MB.jpg | assentform.pdf | consulteeform.pdf |
    When the user deletes "testfile.pdf" form
    When the user deletes "testfile2.pdf" form
    Then the list of "Deleted" files contains the following
      | testfile.pdf | testfile2.pdf |
    And the list of "Uploaded" files contains the following
      | testfile_11MB.jpg | assentform.pdf | consulteeform.pdf |
    When the user uploads the following files
      | testfile.pdf |
    Then the list of "Uploaded" files contains the following
      | testfile.pdf | testfile_11MB.jpg | assentform.pdf | consulteeform.pdf |
    Then the list of "Deleted" files contains the following
      | testfile2.pdf |


  Scenario: Test Order Forms- Users should be able to download forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    And the user uploads the following files
      | testfile.pdf | testfile2.pdf | testfile_11MB.jpg | assentform.pdf | consulteeform.pdf |
    Then the user should be able to download "testfile.pdf" form




