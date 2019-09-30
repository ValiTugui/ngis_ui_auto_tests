@patientSearch
Feature: Patient search page

  Background:
    Given a web browser is at the patient search page

  @patientSearch_01 @E2EUI-2128
  Scenario Outline: patient search - <patient-search-type> Spine - With NHS Number and Date of Birth
    When the user types in valid details of a "NHS Spine" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "NHS Spine" result is successfully returned
    And the correct details of the "NHS Spine" patient are displayed in the card


    Examples:
      | NhsNumber  | DOB        |
      | 9449310602 | 23-03-2011 |


  @patientSearch_02 @E2EUI-2129
  Scenario Outline: patient search - <patient-search-type> Spine - Alternate Search without NHS Number <SearchDetails>
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "NHS Spine" patient in the No of Fields
    And the user clicks the Search button
    Then a "NHS Spine" result is successfully returned
    And the correct details of the "NHS Spine" patient are displayed in the card

    Examples:
      | SearchDetails                                                                             |
      | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female                  |
      | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female:postcode=Kt7 0BE |


  @patientSearch_02 @E2EUI-1472
  Scenario Outline: patient search - <patient-search-type> Spine1 - With NHS Number and Date of Birth
    When the user types in valid details of a "NHS Spine" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then The patient records is displayed with a heading of "We found a result"
    And a "NHS Spine" result is successfully returned
    And the correct details of the "NHS Spine" patient are displayed in the card

    Examples:
      | NhsNumber  | DOB        |
      | 9449310602 | 23-03-2011 |