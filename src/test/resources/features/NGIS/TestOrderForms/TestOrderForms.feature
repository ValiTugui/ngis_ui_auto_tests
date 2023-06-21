@SYSTEM_TEST
@TestOrderforms
Feature: Test Order Forms


  Scenario Outline: Test Order Forms- Users can successfully upload up to 5 forms at a time
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    When the user uploads the following files
      | <file1> | <file2> | <file3> | <file4> | <file5> |
    Then the list of "Uploaded" files contains the following
      | <file1> | <file2> | <file3> | <file4> | <file5> |
    When the user uploads the following files
      | <file6> | <file7> |
    Then the list of "Uploaded" files contains the following
      | <file1> | <file2> | <file3> | <file4> | <file5> | <file6> | <file7> |
    When the user clicks on Continue Button
    Then the user is navigated to a page with title Add a requesting organisation

    Examples:
      | file1        | file2        | file3             | file4              | file5             | file6            | file7                      | testOrderForms   |
      | testfile.pdf | png_file.png | testfile_11MB.jpg | word_document.docx | consulteeform.pdf | pdf_document.PDF | png_file_with_capitals.PNG | Test order forms |


  Scenario Outline: Test Order Forms- Uploaded list header should contain the number of uploaded files
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    When the user uploads the following files
      | <file1> | <file2> |
    Then the list of "Uploaded" files in Test Order Forms contains 2 files
    When the user uploads the following files
      | <file3> | <file4> | <file5> |
    Then the list of "Uploaded" files in Test Order Forms contains 5 files
    When the user clicks on Continue Button
    Then the user is navigated to a page with title Add a requesting organisation

    Examples:
      | file1        | file2                      | file3             | file4                            | file5             | testOrderForms   |
      | testfile.pdf | png_file_with_capitals.PNG | testfile_11MB.jpg | word_document_with_capitals.DOCX | consulteeform.pdf | Test order forms |

  Scenario Outline: Test Order Forms- Users should see an error message being displayed when trying to upload more than 5 form at a time
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    When the user uploads the following files
      | <file1> | <file2> | <file3> | <file4> | <file5> | <file6> |
    Then "Upload has failed" error banner should be displayed on Test Order Forms page
    And the following error messages are displayed under the Upload has failed error banner in Test Order Forms page
      | <errorMessage> |
    And the list of "Uploaded" files contains the following
      | <file1> | <file2> | <file3> | <file4> | <file5> |
    When the user clicks on Continue Button
    Then the user is navigated to a page with title Add a requesting organisation

    Examples:
      | file1        | file2         | file3                            | file4          | file5             | file6            | errorMessage                                    | testOrderForms   |
      | testfile.pdf | testfile2.pdf | word_document_with_capitals.DOCX | assentform.pdf | consulteeform.pdf | deceasedform.pdf | A maximum of 5 files can be uploaded at a time. | Test order forms |

  Scenario Outline: Test Order Forms- Users should see an error message being displayed when trying to upload forms of unsupported file types
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    When the user uploads the following files
      | <file1> | <file2> | <file3> |
    Then "Upload has failed" error banner should be displayed on Test Order Forms page
    And the following error messages are displayed under the Upload has failed error banner in Test Order Forms page
      | <errorMessage> |
    And the list of "Uploading" files contains the following
      | <file1> |

    Examples:
      | file1                                           | file2                      | file3         | errorMessage                                      | testOrderForms   |
      | CNV1_112008234_10002_referral_DDF_modified.json | png_file_with_capitals.PNG | testfile2.pdf | The selected file must be PDF, JPEG, PNG or DOCX. | Test order forms |

  Scenario Outline: Test Order Forms- Users should see an error message being displayed when trying the same form twice
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    When the user uploads the following files
      | <file1> | <file2> | <file3> |
    When the user uploads the following files
      | <file1> |
    Then "Upload has failed" error banner should be displayed on Test Order Forms page
    And the following error messages are displayed under the Upload has failed error banner in Test Order Forms page
      | <errorMessage> |
    And the list of "Uploading" files contains the following
      | <file1> |

    Examples:
      | file1            | file2         | file3             | errorMessage                                                                | testOrderForms   |
      | pdf_document.PDF | testfile2.pdf | testfile_11MB.jpg | A file named “pdf_document.PDF” has already been uploaded to this referral. | Test order forms |


  Scenario Outline: Test Order Forms- Users should be able to delete unwanted forms and restore them back if needed
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    And the user uploads the following files
      | <file1> | <file2> | <file3> |
    When the user deletes the following files
      | <file1> | <file2> |
    Then the list of "Deleted" files contains the following
      | <file1> | <file2> |
    And the list of "Uploaded" files contains the following
      | <file3> |
    When the user restores the following files
      | <file1> |
    And the list of "Uploaded" files contains the following
      | <file1> | <file3> |

    Examples:
      | file1        | file2         | file3             | testOrderForms   |
      | testfile.pdf | testfile2.pdf | testfile_11MB.jpg | Test order forms |

  ##The below scenario has to be reviewed if the current design flow changes
  Scenario Outline: Test Order Forms- Users should be able to upload again deleted forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    When the user uploads the following files
      | <file1> | <file2> | <file3> |
    When the user deletes the following files
      | <file1> | <file2> |
    Then the list of "Deleted" files contains the following
      | <file1> | <file2> |
    And the list of "Uploaded" files contains the following
      | <file3> |
    When the user uploads the following files
      | <file1> |
    Then the list of "Uploaded" files contains the following
      | <file1> | <file3> |
    And the list of "Deleted" files contains the following
      | <file2> |

    Examples:
      | file1                      | file2         | file3             | testOrderForms   |
      | png_file_with_capitals.PNG | testfile2.pdf | testfile_11MB.jpg | Test order forms |


  Scenario Outline: Test Order Forms- Users should be able to download forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    When the user uploads the following files
      | <file1> | <file2> | <file3> |
    Then the user should be able to download "<file1>" form

    Examples:
      | file1        | file2                      | file3         | testOrderForms   |
      | testfile.pdf | png_file_with_capitals.PNG | testfile2.pdf | Test order forms |


  Scenario Outline: Test Order Forms- Users should be able to view uploaded forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M101 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    When the user navigates to the "<testOrderForms>" stage
    When the user uploads the following files
      | <file1> | <file2> | <file3> |
    Then the user should be able to view the following Test Order Forms
      | <file1> | <file3> |

    Examples:
      | file1                      | file2         | file3             | testOrderForms   |
      | png_file_with_capitals.PNG | testfile2.pdf | testfile_11MB.jpg | Test order forms |

