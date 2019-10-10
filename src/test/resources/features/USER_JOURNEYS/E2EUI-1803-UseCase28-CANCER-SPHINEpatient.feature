@UserJourneys
Feature: Create Referral for Proband Only + Edit Data + Patient Choice No + Tumour + Sample - Search Spine Patient


  @UserJourneys-CANCER @UserJourneys-E2EUI-1803
  Scenario Outline: E2EUI-1803 - UseCase#28: SPHINE Patient -> Create Referral for Proband only -> Patient Choice No -> Tumour -> Sample
    Given a web browser is at the Test Directory homepage
    And the search field is loaded
    When the user types the CI term "<clinical indication>" in the search field
    And the user clicks on the search icon
    And the user selects the first result from the search results list
    And the user clicks the Yes, Start referral button
    And the user clicks the "Sign in to the online service" hyperlink
    And the user logs in to the Test Order system
    And the patient search page is loaded
    And the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    #patient details


    Examples:
      | clinical indication     | patient-search-type | NhsNumber  | DOB        |
      | WGS Germline and Tumour | NHS Spine           | 9449310602 | 23-03-2011 |

