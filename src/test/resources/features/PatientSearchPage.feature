@patientSearch
Feature: Patient search page

  Background:
    Given a web browser is at the patient search page

  @patientSearch_01 @E2EUI-2128 @E2EUI-1109
  Scenario Outline: patient search "<patient-search-type>" With NHS Number and Date of Birth
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card


    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |
      | NGIS                | 9449306680 | 14-06-2011 |


  @patientSearch_02 @E2EUI-2129  @E2EUI-2136
  Scenario Outline: patient search "<patient-search-type>" - Alternate Search without NHS Number
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    Examples:
      | patient-search-type | SearchDetails                                                                             |
      | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female                  |
      | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female:Postcode=Kt7 0BE |
      | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male                               |
      | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male:Postcode=KT21 2BE             |


  @patientSearch_02 @E2EUI-1472
  Scenario Outline: patient search - "<patient-search-type>" Patient Search Results Page validation
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "We found a result"
    And a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |
      | NGIS                | 9449306680 | 14-06-2011 |

  @E2EUI-1481
  Scenario: Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - Yes
    When the user clicks the Search button
    Then the mandatory fields such as NHS Number and DOB should be highlighted with a red mark


  @E2EUI-1188
  Scenario: Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user clicks the Search button
    Then the mandatory fields such as DOB , First Name, Last Name and Gender should be highlighted with a red mark

  @E2EUI-1304
  Scenario Outline: Patient Search Results Page invalid data
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then The message will be displayed as You’ve searched for "<NhsNumber>" "<DOB> " "<error_message>"
    And There is a "<hyperlinkText>"link available
    Examples:
      | patient-search-type | NhsNumber  | DOB        | error_message    | hyperlinkText               |
      | NHS Spine           | 9449305099 | 05-11-2005 | No patient found | create a new patient record |

  @E2EUI-1211
  Scenario: Patient Search Form label color validation
    Then form labels should be consistent to font colour "#212b32"
    And  form labels should be consistent to font size "16px"
    And  form labels should be consistent to font face "bold"


  @E2EUI-1114
  Scenario Outline: Patient Search- NHS number Validations - number of digits limited to 10
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then the message will be displayed as "<error_message>" in "#212b32" color
    Examples:
      | patient-search-type | NhsNumber | DOB        | error_message                                     |
      | NHS Spine           | 94493     | 23-03-2011 | Please enter your full NHS Number (10 characters) |
      | NGIS                | 944956    | 14-06-2011 | Please enter your full NHS Number (10 characters) |


  @E2EUI-1114
  Scenario Outline: Patient Search- NHS number Validations - special character & alphabets
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then the message will be displayed as "<error_message>" in "#212b32" color
    Examples:
      | patient-search-type | NhsNumber  | DOB        | error_message                                     |
      | NHS Spine           | 912*&      | 23-03-2011 | Please enter your full NHS Number (10 characters) |
      | NGIS                | 944956778a | 14-06-2011 | Please enter your full NHS Number (10 characters) |


