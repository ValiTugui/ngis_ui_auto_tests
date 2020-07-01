@04-GENOMIC_RECORD
@SYSTEM_TEST
Feature: GenomicRecord: New Patient page 1

  @NTS-5919
  Scenario Outline: NTS-5919:Change enumerations of reason for no NHS number
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |
    When the user is navigated to a page with title Find your patient
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    Then the user create a new patient record by clicking the "<hyperlinkText>" link to fill all fields without NHS number and reason "<reason_for_no_nhsNumber>"

    Examples:
      | hyperlinkText               | reason_for_no_nhsNumber                                     |
      | create a new patient record | Other (please provide reason)                               |
      | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) |
