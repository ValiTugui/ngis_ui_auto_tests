@05-CONSENT
@SYSTEM_TEST
Feature: Patient Choice-1 - Adult with Capacity

  @NTS-6341
  Scenario Outline: NTS-6341: As a user, I want to see that the RoD form is not displayed in History tab when patient choice is "Form to Follow"
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1992:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<PatientChoice>" stage
    Then the user is navigated to a page with title <title1>
    When the user edits the patient choice status
    Then the user is navigated to a page with title <title2>
    When the user selects the option <Option1> in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option <Option2> for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    And the user clicks on Continue Button
    Then the Patient choices option is marked as completed
    When the user is in the section Review and submit
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user selects the History tab in patient choice page
    And the user should be able to see patient choice in history tab
    And the user has to click on latest record of discussion
    Then the user should not be able to see the remove document button

    Examples:
      | PatientChoice  | title1         | title2                         | Option1               | RecordedBy                            | Option2                                       |
      | Patient choice | Patient choice | Add patient choice information | Adult (With Capacity) | ClinicianName=John:HospitalNumber=123 | Patient conversation happened; form to follow |