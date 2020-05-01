#@regression
#@printForms
@03-TEST_ORDER1
@SYSTEM_TEST
Feature: TestOrder - Print Forms 2 - User flows

  @NTS-4746 @Z-LOGOUT
#    @E2EUI-1729
  Scenario Outline: NTS-4746: Update copy on print form
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=24-08-1998:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "TAMESIDE GENERAL HOSPITAL" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ###Print Forms
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms
    And the user should be able to see a "<WarningMessage>" on the print forms page

    Examples:
      | OneParticipant | PrintForms  | WarningMessage                                                                                            |
      | 1              | Print forms | Follow local trust information governance guidelines for data protection if saving sample forms anywhere. |

  @NTS-4746 @Z-LOGOUT
#    @E2EUI-1332
  Scenario Outline: NTS-4746: Download sample form stage becomes available
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R104 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=11-11-2011:Gender=Male |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the print forms stage is locked
    And the user clicks the Save and Continue button
    ###Requesting Organisation
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "Queen" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ###Test Package
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<OneParticipant>"
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Add clinician information
    ###Print Forms
    And the print forms stage is unlocked
    When the user navigates to the "<PrintForms>" stage
    Then the user is navigated to a page with title Print sample forms

    Examples:
      | OneParticipant | PrintForms  |
      | 1              | Print forms |

  @NTS-4746 @Z-LOGOUT
#    @E2EUI-1132
  Scenario Outline: NTS-4746: User has submitted a referral and able to start a new referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2001:Gender=Female |
    ###Patient Details
    When the user is navigated to a page with title Check your patient's details
    And the user clicks the Save and Continue button
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