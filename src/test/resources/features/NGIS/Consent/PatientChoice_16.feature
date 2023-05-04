#@patientChoice
@05-CONSENTNotInST
Feature: Patient Choice-16 - History - Withdrawal - Form

  @NTS-6427
  Scenario Outline: NTS-6427: Verify the patient type in withdrawal form after submitting the patient withdrawal
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-2005:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "<Patient choice stage>" stage
    Then the user is navigated to a page with title <title1>
    When the user edits the patient choice status
    Then the user is navigated to a page with title <title2>
    When the user selects the option <Option1> in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    And the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    When the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    When the user selects the History tab in patient choice page
    And the user should be able to see patient choice in history tab
    And the user has to click on latest record of discussion
    And the user should be able to view Withdraw from Research button on the top.
    And the user should click on that Withdraw button and should start providing details.
    And the user should selects the option <Option1> in patient category
    And the user should provide answer as yes for the question Has the patient had the opportunity to read and understood the withdrawal information, and have had the opportunity to get more information and ask questions?
    And the user should provide answer as Full Withdrawal for the question Which withdrawal would you like to make?
    And the user click on Continue Button
    And the user should see the Withdrawal Received section
    And the user should provide <Option2> in Withdrawal Received details as
    And the user provide <Admin/ClinicianName> in Admin/Clinician Name section
    When the user fills PatientSignature details in patient signature
    And the user click on Submit Withdrawal button
    When the user selects the History tab in patient choice page
    And the user has to click on Withdrawal form.
    Then the user should be able to see the Patient Type as <Option1> ,according to Patient Category provided in Withdrawal Form

    Examples:
      | Patient choice stage | title1         | title2                         | Option1               | Option2   | RecordedBy                            | Admin/ClinicianName |
      | Patient choice       | Patient choice | Add patient choice information | Adult (With Capacity) | In-person | ClinicianName=John:HospitalNumber=123 | John                |