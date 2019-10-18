@framework
Feature: This feature is to build and debug framework

  Background:
    Given a web browser is at the patient search page

  @navigation_support
  Scenario Outline: Build Generic navigation
    Given a web browser is at the "<urlToNavigate>" page with confirmation "<confirmPageAfterNavigate>"
    Examples:
      | urlToNavigate         | confirmPageAfterNavigate |
      | TO_PATIENT_SEARCH_URL | patient-search           |
      | TO_PATIENT_NEW_URL    | new-patient              |

