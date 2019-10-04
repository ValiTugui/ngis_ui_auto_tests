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


  @patientSearch_02 @E2EUI-2129  @E2EUI-2136 @E2EUI-1762 @E2EUI-1788 @E2EUI-2140
  Scenario Outline: patient search - "<ui-ticket-no>" - "<patient-search-type>" Alternate Search without NHS Number
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    Examples:
      | ui-ticket-no | patient-search-type | SearchDetails                                                                             |
      | E2EUI-1788   | NHS Spine           | DOB=23-03-2011:FirstName=Nelly:LastName=Stambukdelifschitz:Gender=Female                  |
      | E2EUI-2129   | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=STAMBUKDELIFSCHITZ:Gender=Female:Postcode=Kt7 0BE |
      | E2EUI-2129   | NHS Spine           | DOB=23-03-2011:FirstName=nElLy:LastName=StAmBuKdElIfScHiTz:Gender=Female:Postcode=Kt7 0BE |
      | E2EUI-2140   | NHS Spine           | DOB=23-03-2011:FirstName=n:LastName=Stambukdelifsch:Gender=Female:Postcode=Kt7 0BE        |
      | E2EUI-1762   | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male                               |
      | E2EUI-2136   | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male:Postcode=KT21 2BE             |


  @patientSearch_03 @E2EUI-1472
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


  @patientSearch_04 @E2EUI-1481
  Scenario: Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - Yes
    When the user clicks the Search button
    Then the mandatory fields such as NHS Number and DOB should be highlighted with a red mark


  @patientSearch_05 @E2EUI-1188
  Scenario: Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user clicks the Search button
    Then the mandatory fields such as DOB , First Name, Last Name and Gender should be highlighted with a red mark

  @patientSearch_06 @E2EUI-1304
  Scenario Outline: Patient Search Results Page invalid data
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then The message will be displayed as You’ve searched for "<NhsNumber>" "<DOB> " "<error_message>"
    And There is a "<hyperlinkText>"link available
    Examples:
      | patient-search-type | NhsNumber  | DOB        | error_message    | hyperlinkText               |
      | NHS Spine           | 9449305099 | 05-11-2005 | No patient found | create a new patient record |

  @patientSearch_07 @E2EUI-1211
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




  @patientSearch_08 @E2EUI-1182
  Scenario Outline: Verifying the Patient Details page after successful search for "<patient-search-type>" patient
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed

    Examples:
      |patient-search-type  | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |
      | NGIS                | 9449306680 | 14-06-2011 |


  @patientSearch_09 @E2EUI-2162
  Scenario Outline: "<patient-search-type>" - User can search for a different patient after successful using NHS No with Date of Birth
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    When the user types in different valid details in the NHS number "<NhsNumber2>" and DOB "<DOB2>" fields
    And the user clicks the Search button
    Then the correct details of the second "<patient-search-type>" patient are displayed in the result card

    Examples:
      | patient-search-type | NhsNumber  | DOB        | NhsNumber2 | DOB2       |
      | NHS Spine           | 9449310602 | 23-03-2011 | 9449304580 | 11-04-1909 |


  @patientSearch_10 @E2EUI-2161
  Scenario Outline: "<patient-search-type>" - User can search for a different patient after successful alternative search
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    When the user types in different valid details "<SearchDetails2>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then the correct details of the second "<patient-search-type>" patient using alternative searches are displayed in the result card

    Examples:
      | patient-search-type | SearchDetails                                                                             | SearchDetails2                                                                        |
      | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female                  | DOB=11-04-1909:FirstName=ALEXANDRINA:LastName=MCBRYDE:Gender=Female                   |
      | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female:Postcode=Kt7 0BE | DOB=11-04-1909:FirstName=ALEXANDRINA:LastName=MCBRYDE:Gender=Female:Postcode=KT17 2EG |


  @patientSearch_11 @E2EUI-1390
  Scenario: To verify the text information present on the 'Find a Patient' page
    Then the display title of the page is "Find your patient"
    And the display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"
