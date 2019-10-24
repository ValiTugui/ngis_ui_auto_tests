@patientSearch
Feature: Patient search page

  Background:
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL  |  patient-search | GEL-normal-user|


  @patientSearch_01 @NTS-2818 @E2EUI-914 @v_1
  Scenario: NTS-2818: The default patient search page is properly displayed
    Then the default patient search page is correctly displayed with the NHS number and Date of Birth fields
    And the YES button is selected by default on patient search
    And the background colour of the YES button is strong blue "#005eb8"

  @patientSearch_02 @NTS-2817 @E2EUI-831 @v_1
  Scenario:NTS-2817: Patient search page is correctly rendered when NO button is selected
    When the user clicks the NO button
    Then the patient search page displays input fields such as DOB, First Name, Last Name, Gender, postcode and search buttons

  @patientSearch_03 @NTS-2780 @E2EUI-2128 @E2EUI-1109 @v_1
  Scenario Outline: NTS-2780:patient search "<patient-search-type>" With NHS Number and Date of Birth
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |
      | NGIS                | 9449306680 | 14-06-2011 |

  @COMP2_TO_PatientSearch
  @patientSearch_04 @NTS-2795 @E2EUI-2129  @E2EUI-2136 @E2EUI-1762 @E2EUI-1788 @v_1
  Scenario Outline: NTS-2795:patient search - "<ui-ticket-no>" - "<patient-search-type>" Alternate Search without NHS Number
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
      | E2EUI-1762   | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male                               |
      | E2EUI-2136   | NGIS                | DOB=14-06-2011:FirstName=GORE:LastName=PHONANAN:Gender=Male:Postcode=KT21 2BE             |


  @patientSearch_05 @NTS-2822 @E2EUI-2140 @E2EUI-2132 @E2EUI-2131 @v_1
  Scenario Outline: NTS-2822:patient search - "<ui-ticket-no>" - "<patient-search-type>" Defuzzing, accented and special characters
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    Examples: De-fuzzing
      | ui-ticket-no | patient-search-type | SearchDetails                                                                 |
      | E2EUI-2140   | NHS Spine           | DOB=23-03-2011:FirstName=n:LastName=Stambukdelifsch:Gender=Female             |
      | E2EUI-2140   | NHS Spine           | DOB=23-03-2011:FirstName=nell:LastName=Stambuk:Gender=Female:Postcode=Kt7 0BE |

    Examples: Accented and Special Characters Characters
      | ui-ticket-no | patient-search-type | SearchDetails                                                                    |
      | E2EUI-2132   | NHS Spine2          | DOB=07-03-1997:FirstName=Gillian:LastName=O'hern:Gender=Female                   |
      | E2EUI-2132   | NHS Spine2          | DOB=07-03-1997:FirstName=Gillian:LastName=O'HERN:Gender=Female:Postcode=KT10 0JF |
      | E2EUI-2131   | NGIS2               | DOB=12-12-2012:FirstName=Bén:LastName=O'Müller:Gender=Male:Postcode=EC2A 2EX     |
      | E2EUI-2131   | NGIS2               | DOB=12-12-2012:FirstName=Bén:LastName=O'Muller:Gender=Male                       |
      | E2EUI-2131   | NGIS2               | DOB=12-12-2012:FirstName=Ben:LastName=O'Müller:Gender=Male                       |
      | E2EUI-2131   | NGIS2               | DOB=12-12-2012:FirstName=Ben:LastName=OMüller:Gender=Male                        |
      | E2EUI-2132   | NGIS2               | DOB=12-12-2012:FirstName=Ben:LastName=OMuller:Gender=Male                        |
#       | E2EUI-2132   | NHS Spine2           | DOB=07-03-1977:FirstName=Gillian:LastName=Ohern:Gender=Female  | Defects NTOS-4169


  @patientSearch_06 @NTS-2796 @E2EUI-1472 @v_1
  Scenario Outline:NTS-2796:patient search - "<patient-search-type>" Patient Search Results Page validation
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number and Date of Birth fields
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "<result_message>"
    And a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    Examples:
      | patient-search-type | result_message         |
      | NHS Spine           | 1 patient record found |
      | NGIS                | 1 patient record found |


  @patientSearch_07 @NTS-2781 @E2EUI-1481 @v_1
  Scenario: NTS-2781:Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - Yes
    When the user clicks the Search button
    Then the mandatory fields such as NHS Number and DOB should be highlighted with a red mark


  @patientSearch_08 @NTS-2781 @E2EUI-1188 @E2EUI-1481 @v_1
  Scenario:NTS-2781:Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user clicks the Search button
    Then the mandatory fields such as DOB , First Name, Last Name and Gender should be highlighted with a red mark


  @patientSearch_09 @NTS-2789 @E2EUI-1304 @E2EUI-904  @E2EUI-827 @v_1
  Scenario Outline: NTS-2789:Invalid data - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    When the user clicks the Search button
    Then The message will be displayed as You’ve searched for "<SearchDetails>" "<error_message>" in "bold" font

    Examples:
      | patient-search-type | SearchDetails                                                            | error_message    |
      | NHS Spine           | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found |

  @patientSearch_10 @NTS-2802 @E2EUI-827 @E2EUI-1294 @v_1
  Scenario: NTS-2802:Validation errors are not displayed when clicking the Search button without typing Gender - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user clicks the Search button
    Then the non mandatory field "Postcode" shouldn't be highlighted with a red mark

  @patientSearch_11 @NTS-2789 @E2EUI-1304  @v_1
  Scenario Outline: NTS-2789:Patient Search Results Page invalid data
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then The message will be displayed as You’ve searched for "<NhsNumber>" "<DOB> " "<error_message>"
    And There is a "<hyperlinkText>"link available
    Examples:
      | patient-search-type | NhsNumber  | DOB        | error_message    | hyperlinkText               |
      | NHS Spine           | 9449305099 | 05-11-2005 | No patient found | create a new patient record |

  @patientSearch_12 @NTS-2800 @E2EUI-1211 @v_1
  Scenario: NTS-2800:Patient Search Form label color validation
    Then form labels should be consistent to font colour "#212b32"
    And  form labels should be consistent to font size "16px"
    And  form labels should be consistent to font face "bold"

  @patientSearch_13 @NTS-3068 @E2EUI-1182 @v_1
  Scenario Outline: NTS-3068:Verifying the Patient Details page after successful search for "<patient-search-type>" patient
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number and Date of Birth fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed

    Examples:
      | patient-search-type |
      | NHS Spine           |
      | NGIS                |


  @patientSearch_14 @NTS-2798 @E2EUI-2162 @v_1
  Scenario Outline: NTS-2798: "<patient-search-type>" - User can search for a different patient after successful using NHS No with Date of Birth
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    When the user types in different valid details in the NHS number "<NhsNumber2>" and DOB "<DOB2>" fields
    And the user clicks the Search button
    Then the correct details of the second "<patient-search-type>" patient are displayed in the result card

    Examples:
      | patient-search-type | NhsNumber  | DOB        | NhsNumber2 | DOB2       |
      | NHS Spine           | 9449310602 | 23-03-2011 | 9449304580 | 11-04-1909 |


  @patientSearch_15 @NTS-2797 @E2EUI-2161 @v_1
  Scenario Outline: NTS-2797: "<patient-search-type>" - User can search for a different patient after successful alternative search
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


  @patientSearch_16 @NTS-2799 @E2EUI-1390 @v_1
  Scenario: NTS-2799:To verify the text information present on the 'Find a Patient' page
    Then the display title of the page is "Find your patient"
    And the display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"


  @patientSearch_17 @NTS-2801 @E2EUI-1114 @v_1
  Scenario Outline: NTS-2801:Patient Search - NHS number Validations - number of digits limited to 10
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then the message will be displayed as "<error_message>" in "#212b32" color
    Examples: of number of digits limited to 10 validation
      | patient-search-type | NhsNumber   | DOB        | error_message                                     |
      | NHS Spine           | 94493       | 23-03-2011 | Please enter your full NHS Number (10 characters) |
      | NGIS                | 94495644442 | 14-06-2011 | Invalid NHS Number. Check and try again           |

    Examples: of special character & alphabets
      | patient-search-type | NhsNumber  | DOB        | error_message                                     |
      | NHS Spine           | 912*&      | 23-03-2011 | Please enter your full NHS Number (10 characters) |
      | NGIS                | 944956778a | 14-06-2011 | Please enter your full NHS Number (10 characters) |


  @patientSearch_18 @NTS-2801 @E2EUI-1114  @E2EUI-1840  @E2EUI-2163 @v_1
  Scenario Outline: NTS-2801:Patient Search - DOB field Validations - invalid day , month , year values
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the DOB field
    Examples: of invalid day
      | patient-search-type | NhsNumber  | DOB        | error_message                |
      | NHS Spine           | 9449305099 | 32-03-2011 | Enter a day between 1 and 31 |
      | NHS Spine           | 9449305099 | 0-04-2011  | Enter a day between 1 and 31 |
      | NHS Spine           | 9449305099 | -05-2011   | Enter a day                  |

    Examples: of invalid month
      | patient-search-type | NhsNumber  | DOB        | error_message                  |
      | NGIS                | 9449305099 | 10-28-2011 | Enter a month between 1 and 12 |
      | NGIS                | 9449305099 | 10-0-2011  | Enter a month between 1 and 12 |
      | NGIS                | 9449305099 | 10- -2011  | Enter a month                  |
      #Date in US format MM-DD-YYYY
      | NGIS                | 9449305099 | 01-16-2011 | Enter a month between 1 and 12 |

    Examples: of invalid year
      | patient-search-type | NhsNumber  | DOB        | error_message                       |
      | NGIS                | 9449305099 | 14-11-1    | Enter a year in 4 figures e.g. 1983 |
      | NGIS                | 9449305099 | 14-11-19   | Enter a year in 4 figures e.g. 1983 |
      | NGIS                | 9449305099 | 14-11-193  | Enter a year in 4 figures e.g. 1983 |
      | NGIS                | 9449305099 | 14-11-1800 | Enter a year beyond 1900            |
      | NGIS                | 9449305099 | 14-11- -   | Enter a year                        |

    Examples: of future date scenario
      | patient-search-type | NhsNumber  | DOB        | error_message                    |
      | NHS Spine           | 9449305099 | 12-03-2150 | Please enter a date before today |

    Examples: of invalid leap year
      | patient-search-type | NhsNumber  | DOB        | error_message                     |
      | NHS Spine           | 9449305099 | 29-02-2001 | Check the day and month are valid |


  @patientSearch_19 @NTS-2801 @E2EUI-1840 @v_1
  Scenario Outline: NTS-2801:Patient Search - DOB field Validations - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the DOB field
    Examples: of future date scenario
      | patient-search-type | SearchDetails                                                            | error_message                    |
      | NHS Spine           | DOB=12-03-2150:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | Please enter a date before today |

  @patientSearch_20 @NTS-2819 @E2EUI-1182 @E2EUI-926 @v_1
  Scenario Outline: NTS-2819:Verifying the Patient Details page after successful search for "<patient-search-type>" patient
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number and Date of Birth fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the user clicks the patient result card
    Then the Patient Details page is displayed

    Examples:
      | patient-search-type |
      | NHS Spine           |
      | NGIS                |


  @patientSearch_21 @NTS-2798 @E2EUI-2162 @v_1
  Scenario Outline: NTS-2798:"<patient-search-type>" - User can search for a different patient after successful using NHS No with Date of Birth
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    When the user types in different valid details in the NHS number "<NhsNumber2>" and DOB "<DOB2>" fields
    And the user clicks the Search button
    Then the correct details of the second "<patient-search-type>" patient are displayed in the result card

    Examples:
      | patient-search-type | NhsNumber  | DOB        | NhsNumber2 | DOB2       |
      | NHS Spine           | 9449310602 | 23-03-2011 | 9449304580 | 11-04-1909 |


  @patientSearch_22 @NTS-2797 @E2EUI-2161 @v_1
  Scenario Outline:NTS-2797:"<patient-search-type>" - User can search for a different patient after successful alternative search
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


  @patientSearch_23 @NTS-2799 @E2EUI-1390 @v_1
  Scenario: NTS-2799:To verify the text information present on the 'Find a Patient' page
    Then the display title of the page is "Find your patient"
    And the display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"


  @patientSearch_24 @NTS-2816 @E2EUI-1990 @v_1
  Scenario: NTS-2816:To verify auto-complete and auto-fill are disabled on the patient search page with NHS Number field
    Then User clicks on a field "nhsNumber:dateDay:dateMonth:dateYear" and auto-complete is disabled


  @patientSearch_25 @NTS-2816 @E2EUI-1990 @v_1
  Scenario: NTS-2816:To verify auto-complete and auto-fill are disabled on the patient search page without NHS Number field
    And the user clicks the NO button
    Then User clicks on a field "dateDay:dateMonth:dateYear:firstName:lastName:postcode" and auto-complete is disabled

    @patientSearch_26 @NTS-3050 @E2EUI-968 @E2EUI-1308 @v_1
    Scenario Outline: NTS-3050: The new patient page is opened when clicking the 'create a new patient record' link from the No Search Results page when searching using NHS number
      When the user types in invalid details of a patient in the NHS number and DOB fields
      And the user clicks the Search button
      And the user clicks the "<hyperlinkText>" link from the No Search Results page
      Then the new patient page is opened
      And the NHS number and DOB fields are pre-populated in the new patient page from the search page

      Examples:
        | hyperlinkText               |
        | create a new patient record |

  @patientSearch_27 @NTS-3050 @E2EUI-968 @E2EUI-1308 @v_1
  Scenario Outline: NTS-3050: The new patient page is opened when clicking the 'create a new patient record' link from the No Search Results page when searching without NHS number
    When the user clicks the NO button
    And the user types in invalid details of a patient in the NO fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the fields from NO section are pre-populated in the new patient page from the search page

    Examples:
      | hyperlinkText               |
      | create a new patient record |