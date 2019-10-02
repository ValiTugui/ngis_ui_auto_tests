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
     |patient-search-type  | NhsNumber  | DOB        |
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


  @patientSearch_05 @E2EUI-1481
  Scenario: Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user clicks the Search button
    Then the mandatory fields such as DOB , First Name, Last Name and Gender should be highlighted with a red mark


  @patientSearch_06 @E2EUI-1182
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


  @patientSearch_07 @E2EUI-2162
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



  @patientSearch_08 @E2EUI-2161
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