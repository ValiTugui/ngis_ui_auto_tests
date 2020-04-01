#@regression
#@patientSearchNGIS
@GENOMIC_RECORD
@SYSTEM_TEST
Feature: Genomic Record - Patient search page_NGIS

  @NTS-3379 @LOGOUT
#    @E2EUI-2135
  Scenario Outline: NTS-3379: NHS Number field validation
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    Then the user is navigated to a page with title Find your patient
    And the display question for NHS Number is Do you have the patient's NHS Number?
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader     |
      | NHS Number ✱    |
      | Date of birth ✱ |
    When the user search the family member with the specified details "<InvalidNHS>"
    And the user clicks the Search button
    Then the user will see error messages highlighted in red colour
      | ErrorMessage                            | MessageColor |
      | Invalid NHS Number. Check and try again | #dd2509      |
    Examples:
      | InvalidNHS                          |
      | NHSNumber=5922721713:DOB=20-09-2008 |

