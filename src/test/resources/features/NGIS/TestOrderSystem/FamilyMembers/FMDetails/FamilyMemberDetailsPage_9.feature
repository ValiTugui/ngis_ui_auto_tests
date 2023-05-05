#@FamilyMembersDetailsPage
@03-TEST_ORDER
@SYSTEM_TEST
@SYSTEM_TEST_1
@FamilyMember
Feature: Family Members Details Page 9 - Roll out: Multi input date

  @NTS-6155
  Scenario Outline: NTS-6155 - Rollout: Multi input date - Pre-referral patient detailsage
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1997:Gender=Male |
    Then the user is navigated to a page with title Test Order Forms
    When the user navigates to the "Requesting organisation" stage
    Then the user is navigated to a page with title Add a requesting organisation
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    And the user clicks on Add family member button
    And the user search the family member with the specified details "<FamilyMemberDetails>"
    When the user clicks on the patient card
    Then the user is navigated to a page with title Add missing family member details
    When the user clicks on edit patient details
    Then the user is navigated to a page with title Edit patient
    And the user fills in the date of birth "<dateOfBirth2>"
    Then the message will be displayed as "<error_message2>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth3>"
    Then the message will be displayed as "<error_message3>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth4>"
    Then the message will be displayed as "<error_message4>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth5>"
    Then the message will be displayed as "<error_message5>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth6>"
    Then the message will be displayed as "<error_message6>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth7>"
    Then the message will be displayed as "<error_message7>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth8>"
    Then the message will be displayed as "<error_message8>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth9>"
    Then the message will be displayed as "<error_message9>" in "#dd2509" color for the DOB field
    And the user fills in the date of birth "<dateOfBirth10>"
    Then the message will be displayed as "<error_message10>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    Then the message will be displayed as "<error_message11>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    And the user fills in the birthday "<Birthday>"
    Then the Day field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message12>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    And the user fills in the birthday "<Birthday_1>"
    Then the Day field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message13>" in "#dd2509" color for the DOB field
    And the user clears the date of birth field
    And the user fills in the birthyear "<Birthyear_3>"
    Then the year field remains empty as invalid characters are not accepted
    Then the message will be displayed as "<error_message15>" in "#dd2509" color for the DOB field

    Examples:
      | FamilyMember   | FamilyMemberDetails                 | dateOfBirth2 | error_message2 | dateOfBirth3 | error_message3 | dateOfBirth4 | error_message4 | dateOfBirth5 | error_message5             | dateOfBirth6 | error_message6               | dateOfBirth7 | error_message7                 | dateOfBirth8 | error_message8                    | dateOfBirth9 | error_message9          | dateOfBirth10 | error_message10                  | error_message11            | Birthday | error_message12            | Birthday_1 | error_message13                | Birthyear_3 | error_message15 |
      | Family members | NHSNumber=2000004296:DOB=24-09-2011 | a-01-2011    | Enter a day    | 01-b-2011    | Enter a month  | 01-01-c      | Enter a year   | a-b-c        | Date of birth is required. | 33-09-1990   | Enter a day between 1 and 31 | 12-15-2000   | Enter a month between 1 and 12 | 29-02-2002   | Check the day and month are valid | 01-01-1899   | Enter a year after 1900 | 10-10-2030    | Please enter a date before today | Date of birth is required. | AASFGWE  | Date of birth is required. | ABC12d34ef | Enter a month between 1 and 12 | 200074      | Enter a day     |