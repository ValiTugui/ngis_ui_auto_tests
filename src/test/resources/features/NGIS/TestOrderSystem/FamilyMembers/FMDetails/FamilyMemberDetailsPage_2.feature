#@regression
#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
@FamilyMember
Feature: Family Members Details Page 2- Field Validation_2

  @NTS-3296 @NTS-4612 @Z-LOGOUT
#    @E2EUI-1038 @E2EUI-1772
  Scenario Outline: NTS-3296: Verify the mandatory input fields validations for non-NHS family member creation
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1970:Gender=Male |
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the user can see a message "<SearchDetails>" "<PatientSearchMessage>" in "bold" font
    And the user clicks on the hyper link
    Then the user is navigated to a page with title Create a record for this family member
    And the mandatory fields shown with the symbol in red color
      | mandatory_field              | field_type | symbol | symbol color |
      | First name                   | label      | ✱      | #dd2509      |
      | Last name                    | label      | ✱      | #dd2509      |
      | Date of birth                | legend     | ✱      | #dd2509      |
      | Gender                       | label      | ✱      | #dd2509      |
      | Life status                  | label      | ✱      | #dd2509      |
      | Reason NHS Number is missing | label      | ✱      | #dd2509      |
      | Hospital number              | label      | ✱      | #dd2509      |
      | Relationship to proband      | label      | ✱      | #dd2509      |
      | Ethnicity                    | label      | ✱      | #dd2509      |
    When the user removes the data from all fields "<ClearFields>" in the family member new patient page
    And the user clicks the Add new patient to referral button
    Then the blank mandatory field labels highlighted in red color
      | field_name                   | color   |
      | First name                   | #dd2509 |
      | Last name                    | #dd2509 |
      | Date of birth                | #dd2509 |
      | Life status                  | #dd2509 |
      | Reason NHS Number is missing | #dd2509 |
      | Hospital number              | #dd2509 |
      | Relationship to proband      | #dd2509 |
      | Ethnicity                    | #dd2509 |
    Examples:
      | FamilyMember   | SearchDetails                                               | PatientSearchMessage | ClearFields                                          |
      | Family members | DOB=23-03-2011:FirstName=john:LastName=Michel:Gender=Female | No patient found     | Gender,Life status,Ethnicity,Relationship to proband |

  @NTS-4413 @Z-LOGOUT
#    @E2EUI-833  @Scenario2
  Scenario Outline: NTS-4413 : Change 'Trio Pedigree' icon as it is upside down
    Given a web browser is at the Private Test Selection homepage
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests |
    When the user types in the CI term  in the search field and selects the first result from the results list
      | R105 |
    And the user clicks the Start Test Order Referral button
    And the user clicks the PDF order form button
    Then the user is navigated to a page with title Add a requesting organisation
    When the user enters the keyword "<ordering_entity_name>" in the search field
    And the user selects a random entity from the suggestions list
    And the user clicks on Continue Button
    Then the user should be able to sees trio family icon in review test selection
    Examples:
      | ordering_entity_name |
      | Maidstone            |