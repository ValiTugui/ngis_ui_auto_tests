#@regression
#@responsibleClinicianOrg
@TEST_ORDER
@SYSTEM_TEST
Feature: Responsible Clinician - Modal Dialog

  @NTS-3895 @LOGOUT
#    @E2EUI-1730  @Scenario_01
  Scenario Outline: NTS-3895:  Modal showing when there are incomplete stages
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R27 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=02-07-1981:Gender=Male |
    And the "Patient details" stage is marked as Completed
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Title>"

    Examples:
      | Title                        |
      | There is missing information |

  @NTS-3895 @LOGOUT
#    @E2EUI-1730  @Scenario_02
  Scenario Outline: NTS-3895:  Modal showing when there are incomplete stages
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R29 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=07-07-1960:Gender=Male |
    And the "Patient details" stage is marked as Completed
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Title>"
    And the user should be able to close the pop up dialog box
    Then the user is navigated to a page with title Check your patient's details

    Examples:
      | Title                        |
      | There is missing information |

  @NTS-3895 @LOGOUT
#    @E2EUI-1730  @Scenario_03
  Scenario Outline: NTS-3895 :  Modal showing when there are incomplete stages
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R84 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=27-08-1988:Gender=Male |
    And the "Patient details" stage is marked as Completed
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Title>"
    When the user clicks outside of the modal
    Then the user is navigated to a page with title Check your patient's details

    Examples:
      | Title                        |
      | There is missing information |


  @NTS-3895 @LOGOUT
#    @E2EUI-1730  @Scenario_04
  Scenario Outline: NTS-3895 :  Modal showing when there are incomplete stages
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R61 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=17-01-1967:Gender=Male |
   ##Patient details
    And the "Patient details" stage is marked as Completed
    When the user clicks the Save and Continue button
    ##Requesting Organisation
    And the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks the Save and Continue button
    ##Test Package
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    When the user selects the number of participants as "<NoOfParticipants>"
    And the user clicks the Save and Continue button
    ##Patient Choice
    When the user navigates to the "<PatientChoiceStage>" stage
    Then the user is navigated to a page with title Patient choice
    When the user selects the proband
    Then the user is navigated to a page with title Add patient choice information
    When the user selects the option Adult (With Capacity) in patient choice category
    When the user selects the option Rare & inherited diseases – WGS in section Test type
    When the user fills "<RecordedBy>" details in recorded by
    And the user clicks on Continue Button
    When the user is in the section Patient choices
    When the user selects the option Patient has agreed to the test for the question Has the patient had the opportunity to read and discuss information about genomic testing and agreed to the genomic test?
    When the user selects the option Yes for the question Has research participation been discussed?
    When the user selects the option Yes for the question The patient agrees that their data and samples may be used for research, separate to NHS care.
    And the user clicks on Continue Button
    When the user is in the section Patient signature
    And the user fills PatientSignature details in patient signature
    And the user clicks on submit patient choice Button
    Then the user should be able to see the patient choice form with success message
    And the user clicks the Save and Continue button
    When the user submits the referral
    Then the user should see a new popup dialog with title "<Title>"
    And the user sees a dialog box with following mandatory stages to be completed for successful submission of a referral
      | Mandatory Stage       |
      | Responsible clinician |
      | Clinical questions    |
    ##URL validation has to include - can be as part of another ticket
    Examples:
      | PatientChoiceStage | TestPackage  | ordering_entity_name | NoOfParticipants | RecordedBy                            | Title                        |
      | Patient choice     | Test package | Queen                | 1                | ClinicianName=John:HospitalNumber=123 | There is missing information |