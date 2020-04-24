@03-TEST_ORDER
@SYSTEM_TEST
Feature: TestOrder - Global behaviors

  @NTS-5069 @Z-LOGOUT
    #@E2EUI-875
  Scenario Outline: NTS-5069: Microsoft Login / Authentication
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | M143 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=11-07-2000:Gender=Male |
    When the user is navigated to a page with title Check your patient's details
    Then the "Patient details" stage is marked as Completed
    When the user clicks the Log out button
    Then the user should be navigated to Microsoft login "<MicrosoftLoginUrl>" page
    When the user login to Test Order with invalid credential
    Then the user should be able to see an error message "<errorMessage>"
    When the user login to Test Order with valid credential
    Then the user is navigated to a page with title Welcome to the National Genomic Informatics System

    Examples:
      | MicrosoftLoginUrl           | errorMessage                                                                |
      | /login.microsoftonline.com/ | Your account or password is incorrect. If you don't remember your password, |

  @NTS-5068 @Z-LOGOUT
    #@E2EUI-1841
  Scenario Outline:NTS-5068:Verify Referral Id same as url
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R81 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=02-01-2010:Gender=Female |
    ##Patient Details Page
    When the user is navigated to a page with title Check your patient
    Then the user should be able to see same referral id in the global banner and the url
    ##Requesting Organisation Page
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user enters the keyword "manchester" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the user should be able to see same referral id in the global banner and the url
    ##Test Package Page
    When the user navigates to the "<TestPackage>" stage
    Then the user is navigated to a page with title Confirm the test package
    Then the user should be able to see same referral id in the global banner and the url
    And the user selects the number of participants as "1"
    And the user clicks the Save and Continue button
    ##Responsible Clinician Page
    And the user is navigated to a page with title Add clinician information
    Then the user should be able to see same referral id in the global banner and the url
    ##Clinical Question Page
    And the user navigates to the "<ClinicalQuestion>" stage
    And the user is navigated to a page with title Answer clinical questions
    Then the user should be able to see same referral id in the global banner and the url
    ##Notes Page
    And the user navigates to the "<Notes>" stage
    And the user is navigated to a page with title Add notes to this referral
    Then the user should be able to see same referral id in the global banner and the url
    ##Family Member Page
    And the user navigates to the "<FamilyMembers>" stage
    And the user is navigated to a page with title Add a family member to this referral
    Then the user should be able to see same referral id in the global banner and the url
    ##Patient Choice Page
    And the user navigates to the "<PatientChoice>" stage
    And the user is navigated to a page with title Patient choice
    Then the user should be able to see same referral id in the global banner and the url
    ##Panels
    And the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Panels
    Then the user should be able to see same referral id in the global banner and the url
    ##Pedigree
    And the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    Then the user should be able to see same referral id in the global banner and the url
    And the user clicks the Save and Continue button
    ##Print Forms Page
    And the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    Then the user should be able to see same referral id in the global banner and the url
    When the user provides an invalid referral id in the url "<invalidReferralURL>"
    Then the user should see page is not loading

    Examples:
      | RequestingOrganisation  | TestPackage  | invalidReferralURL                                                                | ClinicalQuestion   | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  |
      | Requesting organisation | Test package | https://test-ordering.int.ngis.io/test-order/referral/r5E$&%5E943/patient-details | Clinical questions | Notes | Family members | Patient choice | Panels | Pedigree | Print forms |


