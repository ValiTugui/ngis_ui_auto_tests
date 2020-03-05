@regression
@TO_RD_And_Tumour
@GlobalPatientInformation
Feature: Global Patient Information Bar on Family Members Navigation Stage Navigation

  @NTS-4320 @E2EUI-1065 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4320: Verify continue button
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R61 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-2010:Gender=Male |
    When the user is navigated to a page with title Check your patient's details
    And the user navigates to the "<FamilyMembers>" stage
    And the user is navigated to a page with title Add a family member to this referral
    Then the user should be able to see continue button on landing page
    When the user clicks on Continue Button
    And the user is navigated to a page with title Patient choice
    Then the user should be able to see continue button on landing page
    Examples:
      | FamilyMembers  |
      | Family members |

  @NTS-3494 @LOGOUT @E2EUI-2017
  Scenario Outline: NTS-3494:Validating referral banner fixed at the top of every page.
    Given a referral is created for a new patient without nhs number and associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R143 | NGIS | Rare-Disease | Patient is a foreign national | GEL_NORMAL_USER |

    When the user is navigated to a page with title Check your patient
    Then the user should verify the referral banner present at the top
    When the user navigates to the "<RequestingOrganisation>" stage
    Then the user is navigated to a page with title Add a requesting organisation
    And the user should verify the referral banner present at the top
    And the user enters the keyword "ROCHDALE INFIRMARY" in the search field
    And the user selects a random entity from the suggestions list
    Then the details of the new organisation are displayed
    And the user clicks the Save and Continue button
    Then the user is navigated to a page with title Confirm the test package
    And the user selects the number of participants as "<NoOfParticipants>"
    And the user should verify the referral banner present at the top
    And the user clicks the Save and Continue button
    And the user is navigated to a page with title Add clinician information
    And the user fills the responsible clinician page with "<ResponsibleClinicianDetails>"
    And the user should verify the referral banner present at the top
    And the user clicks the Save and Continue button
    And the user navigates to the "<ClinicalQuestion>" stage
    And the user is navigated to a page with title Answer clinical questions
    And the user should verify the referral banner present at the top
    And the user navigates to the "<Notes>" stage
    And the user is navigated to a page with title Add notes to this referral
    And the user should verify the referral banner present at the top
    And the user navigates to the "<FamilyMembers>" stage
    And the user is navigated to a page with title Add a family member to this referral
    And the user should verify the referral banner present at the top
    And the user navigates to the "<PatientChoice>" stage
    And the user is navigated to a page with title Patient choice
    And the user should verify the referral banner present at the top
    And the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Panels
    And the user should verify the referral banner present at the top
    And the user navigates to the "<Pedigree>" stage
    And the user is navigated to a page with title Build a pedigree
    And the user should verify the referral banner present at the top
    And the user navigates to the "<PrintForms>" stage
    And the user is navigated to a page with title Print sample forms
    Then the user should verify the referral banner present at the top

    Examples:
      | RequestingOrganisation  | ordering_entity_name | TestPackage  | NoOfParticipants | ResponsibleClinicianDetails                               | ClinicalQuestion   | Notes | FamilyMembers  | PatientChoice  | Panels | Pedigree | PrintForms  |
      | Requesting organisation | ROCHDALE INFIRMARY   | Test package | 1                | FirstName=Karen:LastName=Smith:Department=Victoria Street | Clinical questions | Notes | Family members | Patient choice | Panels | Pedigree | Print forms |
