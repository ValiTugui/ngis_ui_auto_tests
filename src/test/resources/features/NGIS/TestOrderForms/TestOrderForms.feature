Feature: Upload forms

  @inprogress
  Scenario: Users can successfully upload test order forms
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user clicks on Choose files
    And the user uploads the following files
      | testfile.pdf | testfile2.pdf | testfile_11MB.jpg |
    And the list of uploaded files contains the following
      | testfile.pdf | testfile2.pdf | testfile_11MB.jpg |
    When the user clicks on Continue Button
    Then the user is navigated to a page with title Check your patient's details