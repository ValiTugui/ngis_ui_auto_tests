@FamilyMembers
Feature: FamilyMember search page

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_01 @NTS-3207 @E2EUI-1116 @v_1 @P0
  Scenario Outline: NTS-3207: Verify the family member search page with Yes option displayed properly
    Given a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | Holoprosencephaly - NOT chromosomal | NGIS | Rare-Disease |
    And the user navigates to the "<stage>" stage
    When the user navigates to the family member search Page
    Then the default family member search page is correctly displayed with the NHS number and Date of Birth fields
    And the YES button is selected by default on family member search
    And the background colour of the YES button in family member is strong blue "#005eb8"
    Examples:
      | stage          |
      | Family members |

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_02 @NTS-3207 @E2EUI-1116 @v_1 @P0
  Scenario: NTS-3207:Verify the mandatory field validation errors are displayed when clicking the Search button without typing mandatory fields
    When the user clicks the Search button in family member search page
    Then the mandatory fields should be highlighted with a red mark in family member search page with Yes option selected

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_03 @NTS-3207 @E2EUI-1116 @E2EUI-1083 @v_1 @P0
  Scenario:NTS-3207: Verify the family member search page with No option displayed properly
    When the user clicks the NO button in family member search page
    Then the family member search page displays input fields such as DOB, First Name, Last Name, Gender, postcode and search buttons

  @COMP8_TO_PatientSearch
  @familyMemberSearchPage_04 @NTS-3207 @E2EUI-1116 @E2EUI-1083 @v_1 @P0
  Scenario: NTS-3207:Verify the mandatory field validation errors are displayed when clicking the Search button without typing mandatory fields
    When the user clicks the Search button in family member search page
    Then the mandatory fields should be highlighted with a red mark in family member search page with No option

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_05 @NTS-<TODO> @E2EUI-1249 @v_1 @P0
  Scenario Outline:NTS-3207: Verify the family member search without providing first name displays correct error message
    When the user clicks the NO button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    ##Do not provide first name in the search details
    Examples:
      | SearchDetails                                            | ErrorMessage            | MessageColor |
      | DOB=23-03-2011:LastName=StaMbukdelifschitZ:Gender=Female | First name is required. | #dd2509      |

  @COMP8_TO_PatientSearch
    @familyMemberSearchPage_06 @NTS-<TODO> @E2EUI-1394 @v_1 @P0
  Scenario Outline:NTS-3207: Verify the family member search without providing first name displays correct error message
    When the user clicks the Yes button in family member search page
    And the user search the family member with the specified details "<SearchDetails>"
    Then the message will be displayed as "<ErrorMessage>" in "<MessageColor>" for the invalid field
    ##Do not provide first name in the search details
    Examples:
      | SearchDetails  | ErrorMessage                                      | MessageColor |
      | NHSNumber=1234 | Please enter your full NHS Number (10 characters) | #dd2509      |