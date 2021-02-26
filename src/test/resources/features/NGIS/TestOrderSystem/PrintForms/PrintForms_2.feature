@03-TEST_ORDER
@SYSTEM_TEST
@PrintForms
Feature: TestOrder - Print Forms 2 - User flows

  @NTS-4746 @Z-LOGOUT
#    @E2EUI-1132
  Scenario Outline: NTS-4746: User has submitted a referral and able to start a new referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2001:Gender=Female |
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "LONDON" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user will not see back button on print forms page

    Examples:
      | PrintForms  | OneParticipant |
      | Print forms | 1              |