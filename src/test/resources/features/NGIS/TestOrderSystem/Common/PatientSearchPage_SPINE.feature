@regression
@TO_Common
@patientSearchSPINE
Feature: Patient search page_SPINE

  Background:
    Given a web browser is at the patient search page
      | TO_PATIENT_SEARCH_URL | patient-search | GEL_NORMAL_USER |

  @NTS-2818 @E2EUI-914 @v_1
  Scenario: NTS-2818: The default patient search page is properly displayed
    Then the default patient search page is correctly displayed with the NHS number and Date of Birth fields
    And the YES button is selected by default on patient search
    And the background colour of the YES button is strong blue "#005eb8"
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader       |
      | NHS Number ✱      |
      | Date of birth ✱   |


  @NTS-3336 @E2EUI-1663 @v_1 @P0
  Scenario: NTS-3336: Auto-fill feature not enabled for NHS Number field
    When the default patient search page is correctly displayed with the NHS number and Date of Birth fields
    Then the NHS number field is not enabled with auto-fill feature

  @NTS-2817 @E2EUI-831 @E2EUI-1294 @v_1
  Scenario:NTS-2817: Patient search page is correctly rendered when NO button is selected
    When the user clicks the NO button
    Then the patient search page displays input fields such as DOB, First Name, Last Name, Gender, postcode and search buttons
    And the mandatory input-fields and drops-downs labels are shown with mandatory asterisk star symbol
      | labelHeader     |
      | Date of birth ✱ |
      | First name ✱    |
      | Last name ✱     |
      | Date of birth ✱ |
      | Gender ✱        |
    And the non mandatory input-fields and drops-downs labels are shown without asterisk star symbol
      | labelHeader |
      | Postcode    |

  @NTS-2780 @E2EUI-2128 @E2EUI-1109 @v_1 @BVT_P0
  Scenario Outline: NTS-2780:patient search "<patient-search-type>" With NHS Number and Date of Birth
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then a "<patient-search-type>" result is successfully returned
    And the correct details of the "<patient-search-type>" patient are displayed in the card
    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |

  @NTS-2795 @E2EUI-2129 @E2EUI-2136 @E2EUI-1762 @E2EUI-1788 @v_1 @BVT_P0
  Scenario Outline: NTS-2795:patient search - "<ui-ticket-no>" - "<patient-search-type>" Alternate Search without NHS Number
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    Examples:
      | ui-ticket-no | patient-search-type | SearchDetails                                                                             |
      | E2EUI-1788   | NHS Spine           | DOB=23-03-2011:FirstName=Nelly:LastName=Stambukdelifschitz:Gender=Female                  |
      | E2EUI-2129   | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=STAMBUKDELIFSCHITZ:Gender=Female:Postcode=Kt7 0BE |
      | E2EUI-2129   | NHS Spine           | DOB=23-03-2011:FirstName=nElLy:LastName=StAmBuKdElIfScHiTz:Gender=Female:Postcode=Kt7 0BE |


  @NTS-2822 @E2EUI-2140 @E2EUI-2132 @E2EUI-2131 @v_1
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

  @NTS-2796 @E2EUI-1472 @v_1 @BVT_P0
  Scenario Outline:NTS-2796:patient search - "<patient-search-type>" Patient Search Results Page validation
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number and Date of Birth fields
    And the user clicks the Search button
    Then The patient record is displayed with a heading of "<result_message>"
    And the correct details of the "<patient-search-type>" patient are displayed in the card

    Examples:
      | patient-search-type | result_message         |
      | NHS Spine           | 1 patient record found |

  @NTS-2781 @E2EUI-1481 @v_1
  Scenario: NTS-2781:Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - Yes
    When the user clicks the Search button
    Then the mandatory fields such as NHS Number and DOB should be highlighted with a red mark

  @NTS-2781 @E2EUI-1188 @E2EUI-1481 @v_1
  Scenario:NTS-2781:Validation errors are displayed when clicking the Search button without typing mandatory fields - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user clicks the Search button
    Then the mandatory fields such as DOB , First Name, Last Name and Gender should be highlighted with a red mark

  @NTS-2789 @E2EUI-1304 @E2EUI-904  @E2EUI-827 @E2EUI-968 @v_1
  Scenario Outline: NTS-2789:Invalid data - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    When the user clicks the Search button
    Then The message will be displayed as You’ve searched for "<SearchDetails>" "<error_message>" in "bold" font

    Examples:
      | patient-search-type | SearchDetails                                                            | error_message    |
      | NHS Spine           | DOB=12-03-2019:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | No patient found |

  @NTS-2802 @E2EUI-827 @E2EUI-1294 @v_1
  Scenario: NTS-2802:Validation errors are not displayed when clicking the Search button without typing Gender - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user clicks the Search button
    Then the non mandatory field "Postcode" shouldn't be highlighted with a red mark

  @NTS-2789 @E2EUI-1304 @E2EUI-968 @v_1
  Scenario Outline: NTS-2789:Patient Search Results Page invalid data
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    Then The message will be displayed as You’ve searched for "<NhsNumber>" "<DOB> " "<error_message>"
    And There is a "<hyperlinkText>"link available
    Examples:
      | patient-search-type | NhsNumber  | DOB        | error_message    | hyperlinkText               |
      | NHS Spine           | 9449305099 | 05-11-2005 | No patient found | create a new patient record |

  @NTS-2800 @E2EUI-1211 @v_1
  Scenario: NTS-2800:Patient Search Form label color validation
    Then form labels should be consistent to font colour "#212b32"
    And  form labels should be consistent to font size "16px"
    And  form labels should be consistent to font face "bold"

  @NTS-3068 @E2EUI-1182 @v_1
  Scenario Outline: NTS-3068:Verifying the Patient Details page after successful search for "<patient-search-type>" patient
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number and Date of Birth fields
    And the user clicks the Search button
    And the user clicks the patient result card
    Then the Patient Details page is displayed

    Examples:
      | patient-search-type |
      | NHS Spine           |

  @NTS-2798 @E2EUI-2162 @v_1
  Scenario Outline: NTS-2798: "<patient-search-type>" - User can search for a different patient after successful using NHS No with Date of Birth
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    When the user types in different valid details in the NHS number "<NhsNumber2>" and DOB "<DOB2>" fields
    And the user clicks the Search button
    Then the correct details of the second "<patient-search-type>" patient are displayed in the result card

    Examples:
      | patient-search-type | NhsNumber  | DOB        | NhsNumber2 | DOB2       |
      | NHS Spine           | 9449310602 | 23-03-2011 | 9449304580 | 11-04-1909 |

  @NTS-2797 @E2EUI-2161 @v_1
  Scenario Outline: NTS-2797: "<patient-search-type>" - User can search for a different patient after successful alternative search
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    When the user types in different valid details "<SearchDetails2>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then the correct details of the second "<patient-search-type>" patient using alternative searches are displayed in the result card

    Examples:
      | patient-search-type | SearchDetails                                                                             | SearchDetails2                                                                        |
      | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female                  | DOB=11-04-1909:FirstName=ALEXANDRINA:LastName=MCBRYDE:Gender=Female                   |
      | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female:Postcode=Kt7 0BE | DOB=11-04-1909:FirstName=ALEXANDRINA:LastName=MCBRYDE:Gender=Female:Postcode=KT17 2EG |


  @NTS-2799 @E2EUI-1390 @v_1
  Scenario: NTS-2799:To verify the text information present on the 'Find a Patient' page
    Then the display title of the page is "Find your patient"
    And the display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"

  @NTS-2801 @E2EUI-1114 @E2EUI-929 @v_1
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

  @NTS-2801 @E2EUI-1114 @E2EUI-1840 @E2EUI-2163 @E2EUI-915 @E2EUI-1399 @E2EUI-929 @v_1
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
#       Date in US format MM-DD-YYYY
      | NGIS                | 9449305099 | 01-16-2011 | Enter a month between 1 and 12 |

    Examples: of invalid year
      | patient-search-type | NhsNumber  | DOB        | error_message                       |
      | NGIS                | 9449305099 | 14-11-1    | Enter a year in 4 figures e.g. 1983 |
      | NGIS                | 9449305099 | 14-11-19   | Enter a year in 4 figures e.g. 1983 |
      | NGIS                | 9449305099 | 14-11-193  | Enter a year in 4 figures e.g. 1983 |
      | NGIS                | 9449305099 | 14-11-1800 | Enter a year beyond 1900            |
      | NGIS                | 9449305099 | 14-11- -   | Enter a year                        |
#
    Examples: of future date scenario
      | patient-search-type | NhsNumber  | DOB        | error_message                    |
      | NHS Spine           | 9449305099 | 12-03-2150 | Please enter a date before today |

    Examples: of invalid leap year
      | patient-search-type | NhsNumber  | DOB        | error_message                     |
      | NHS Spine           | 9449305099 | 29-02-2001 | Check the day and month are valid |

    Examples: of alphaNumeric
      | patient-search-type | NhsNumber  | DOB        | error_message |
      | NHS Spine           | 9449305099 | ab-02-2011 | Enter a day   |
      | NHS Spine           | 9449305099 | 22-!!-2011 | Enter a month |
      | NHS Spine           | 9449305099 | 01-02-abcd | Enter a year  |

  @NTS-2801 @E2EUI-1840 @v_1
  Scenario Outline: NTS-2801:Patient Search - DOB field Validations - Do you have NHS patient Number - No
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then the message will be displayed as "<error_message>" in "#dd2509" color for the DOB field
    Examples: of future date scenario
      | patient-search-type | SearchDetails                                                            | error_message                    |
      | NHS Spine           | DOB=12-03-2150:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female | Please enter a date before today |

  @NTS-2819 @E2EUI-1182 @E2EUI-926 @v_1
  Scenario Outline: NTS-2819:Verifying the Patient Details page after successful search for "<patient-search-type>" patient
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    And the user clicks the patient result card
    Then the Patient Details page is displayed

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 23-03-2011 |


  @NTS-2819 @E2EUI-1991 @v_1
  Scenario Outline: NTS-2819: A validation Error should not be triggered while entering DOB as 01-01-1970
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then no validation error red mark highlighted on the DOB field

    Examples:
      | patient-search-type | NhsNumber  | DOB        |
      | NHS Spine           | 9449310602 | 01-01-1970 |

  @NTS-2798 @E2EUI-2162 @v_1
  Scenario Outline: NTS-2798:"<patient-search-type>" - User can search for a different patient after successful using NHS No with Date of Birth
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    And the user clicks the Search button
    When the user types in different valid details in the NHS number "<NhsNumber2>" and DOB "<DOB2>" fields
    And the user clicks the Search button
    Then the correct details of the second "<patient-search-type>" patient are displayed in the result card

    Examples:
      | patient-search-type | NhsNumber  | DOB        | NhsNumber2 | DOB2       |
      | NHS Spine           | 9449310602 | 23-03-2011 | 9449304580 | 11-04-1909 |


  @NTS-2797 @E2EUI-2161 @v_1
  Scenario Outline: NTS-2797:"<patient-search-type>" - User can search for a different patient after successful alternative search
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    When the user types in different valid details "<SearchDetails2>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then the correct details of the second "<patient-search-type>" patient using alternative searches are displayed in the result card

    Examples:
      | patient-search-type | SearchDetails                                                                             | SearchDetails2                                                                        |
      | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female                  | DOB=11-04-1909:FirstName=ALEXANDRINA:LastName=MCBRYDE:Gender=Female                   |
      | NHS Spine           | DOB=23-03-2011:FirstName=NELLY:LastName=StaMbukdelifschitZ:Gender=Female:Postcode=Kt7 0BE | DOB=11-04-1909:FirstName=ALEXANDRINA:LastName=MCBRYDE:Gender=Female:Postcode=KT17 2EG |


  @NTS-2799 @E2EUI-1390 @E2EUI-955  @v_1
  Scenario: NTS-2799:To verify the text information present on the 'Find a Patient' page
    Then the display title of the page is "Find your patient"
    And the display description title contains the phrase "Add any information you have to search the NHS Spine and the Genomics England database (NGIS)"

  @NTS-2816 @E2EUI-1990 @v_1
  Scenario: NTS-2816:To verify auto-complete and auto-fill are disabled on the patient search page with NHS Number field
    Then User clicks on a field "nhsNumber:dateDay:dateMonth:dateYear" and auto-complete is disabled

  @NTS-2816 @E2EUI-1990 @v_1
  Scenario: NTS-2816:To verify auto-complete and auto-fill are disabled on the patient search page without NHS Number field
    And the user clicks the NO button
    Then User clicks on a field "dateDay:dateMonth:dateYear:firstName:lastName:postcode" and auto-complete is disabled

  @NTS-3050 @E2EUI-968 @E2EUI-1308 @v_1
  Scenario Outline: NTS-3050: The new patient page is opened when clicking the 'create a new patient record' link from the No Search Results page when searching using NHS number
    When the user types in invalid details of a patient in the NHS number and DOB fields
    And the user clicks the Search button
    And the user clicks the "<hyperlinkText>" link from the No Search Results page
    Then the new patient page is opened
    And the NHS number and DOB fields are pre-populated in the new patient page from the search page

    Examples:
      | hyperlinkText               |
      | create a new patient record |

  @NTS-3050 @E2EUI-968 @E2EUI-1308 @E2EUI-1847 @v_1
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

  @NTS-3159 @E2EUI-1973 @v_1 @P0
  Scenario: NTS-3159 - Patient Search - To verify the Tab Title displayed correctly if cursor is mover over the Tab
    And User place the cursor over the tab in which the Dashboard - Home page is opened
    Then The user should see the tab title as "Genomic Medicine Service | Test Ordering Application - NGIS"


  @NTS-4722 @E2EUI-835 @v_1
  Scenario Outline: NTS-4722:Patient Search - Integer/decimal Type Validation in NHS Number Field
    When the user types in valid details of a "<patient-search-type>" patient in the NHS number "<NhsNumber>" and Date of Birth "<DOB>" fields
    Then the NHS number field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message>" in "#212b32" color

    Examples: Integer Decimal in NHSField
      | patient-search-type | NhsNumber | DOB        | error_message           |
      | NHS Spine           | abcdefghh | 01-01-2020 | NHS Number is required. |
      | NHS Spine           | !@#$%^&*  | 01-01-2020 | NHS Number is required. |


  @NTS-4727 @E2EUI-1133 @v_1 @BVT_P0
  Scenario Outline: NTS-4727:Patient Search - Gender field Validation when no gender is selected
    And the user clicks the NO button
    When the user types in valid details "<SearchDetails>" of a "<patient-search-type>" patient in the No of Fields
    And the user clicks the Search button
    Then the message will be displayed as "<error_message>" in "#212b32" color

    Examples:
      | patient-search-type | SearchDetails                                              | error_message       |
      | NHS Spine           | DOB=23-03-2011:FirstName=Nelly:LastName=Stambukdelifschitz | Gender is required. |


  @NTS-4742 @E2EUI-1071 @v_1 @BVT_P0
  Scenario Outline:NTS-4727:Patient Search Page Layout - NHS number is NOT Known - Verify placeholder values
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the user sees placeholder texts displayed in the fields - Date of birth "<DOB>", First name "<firstName>", Last name "<lastName>" and Postcode "<postCode>"

    Examples:
      | pageTitle         | DOB        | firstName  | lastName   | postCode   |
      | Find your patient | DD-MM-YYYY | e.g. Helen | e.g. Jones | e.g. N16 7TU |


  @NTS-4742 @E2EUI-1071 @v_1 @BVT_P0
  Scenario Outline: NTS-4742:Patient Search Page Layout - NHS number is NOT Known - verify Gender values
    Then the "<pageTitle>" page is displayed
    And the user clicks the NO button
    And the following drop-down values are displayed for gender
      | genderListHeader |
      | Female           |
      | Male             |
      | Other            |
      | Unknown          |

    Examples:
      | pageTitle         |
      | Find your patient |
