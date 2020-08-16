#@pedigree_uiCustomizationNonNGIS
@07-PEDIGREE
@SYSTEM_TEST
Feature: Pedigree - UI Customizations - Non NGIS

  @NTS-4759 @Z-LOGOUT
#    @E2EUI-1626
  Scenario Outline: NTS-4759:E2EUI-1626: Warn a user that they will lose their changes when navigating away from pedigree
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-11-1986:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user adds new parent node to proband "<ProbandDetails>"
    Then the user sees two NON NGIS Patient ID nodes added to the patient "<ProbandDetails>"
    When the user scroll to the top of landing page
    And the user navigates to the "<Panels>" stage
    Then the user sees a prompt alert "<DiscardMessage>" after clicking "<Panels>" button and "<Dismiss>" it
    And the user is navigated to a page with title Build a pedigree
    ##Note: Proband details same as the DOB used for search in first step
    Examples:
      | PedigreeStage | ProbandDetails              | Panels | DiscardMessage                                              | Dismiss | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1986 | Panels | This section contains unsaved information. Discard changes? | Dismiss | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-4759 @Z-LOGOUT
#    @E2EUI-1391 @E2EUI-1629
  Scenario Outline: NTS-4759:E2EUI-1391,1629: AgeOfOnset should be editable for Non NGIS Patients in Pedigree tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-11-1987:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user adds new parent node to proband "<ProbandDetails>"
    Then the user sees two NON NGIS Patient ID nodes added to the patient "<ProbandDetails>"
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Clinical
    Then the user should be able to update the Age of Onset with "<AgeOfOnset>"
    And the user is able to close the popup by clicking on the close icon
    ##Note: Proband details same as the DOB used for search in first step
    Examples:
      | PedigreeStage | ProbandDetails              | AgeOfOnset       | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-1987 | 2 years,2 months | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-4759 @Z-LOGOUT
#    @E2EUI-1670
  Scenario Outline: NTS-4759:E2EUI-1670: Date of death and birth field validation with respect to a proband
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-11-2001:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user adds new parent node to proband "<ProbandDetails>"
    Then the user sees two NON NGIS Patient ID nodes added to the patient "<ProbandDetails>"
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Personal
    Then the user updates the parent date of year as "<ParentDOY>"
    And the user is able to close the popup by clicking on the close icon
    And the user click on Save menu button
    ###No validation of error message included as it not currently applicable
    ##Note: Proband details same as the DOB used for search in first step
    Examples:
      | PedigreeStage | ProbandDetails              | ParentDOY | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-2001 | 2005      | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-4759 @Z-LOGOUT
#    @E2EUI-1126
  Scenario Outline: NTS-4759:E2EUI-1126: Age at death input field validations under Personal tab
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-11-2002:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user adds new parent node to proband "<ProbandDetails>"
    Then the user sees two NON NGIS Patient ID nodes added to the patient "<ProbandDetails>"
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Personal
    And the user enters age at death as "<AgeAtDeath>"
    And the user is able to close the popup by clicking on the close icon
    When the user click on Save menu button
    Then the user should see error pop up message displayed as "<ErrorMessage>"
    ##Note: Proband details same as the DOB used for search in first step
    ##Strike Mark on the diagrom not verified - not able to automate
    Examples:
      | PedigreeStage | ProbandDetails              | AgeAtDeath | ErrorMessage                                                     | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-2002 | 20hmy      | Invalid value entered for 'Age of Death' for Participant with id | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-4759 @Z-LOGOUT
#    @E2EUI-1311 @E2EUI-1230
  Scenario Outline: NTS-4759:E2EUI-1311,230: Updating Ethnicity Enumerations to Reflect FHIR Definition
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-11-2003:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Pedigree Stage
    When the user navigates to the "<PedigreeStage>" stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user adds new parent node to proband "<ProbandDetails>"
    Then the user sees two NON NGIS Patient ID nodes added to the patient "<ProbandDetails>"
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Personal
    Then the user should see the below options for Ethnicity field Personal tab
      | Black and White                                     |
      | Chinese and White                                   |
      | Asian and Chinese                                   |
      | Other Mixed, Mixed Unspecified                      |
      | Indian or British Indian                            |
      | Pakistani or British Pakistani                      |
      | Bangladeshi or British Bangladeshi                  |
      | Any other Asian background                          |
      | Mixed Asian                                         |
      | Punjabi                                             |
      | Kashmiri                                            |
      | East African Asian                                  |
      | Sri Lanka                                           |
      | Tamil                                               |
      | Sinhalese                                           |
      | British Asian                                       |
      | Caribbean Asian                                     |
      | Other Asian, Asian unspecified                      |
      | Black or Black British - Caribbean                  |
      | Black or Black British - African                    |
      | Black or Black British - Any other Black background |
      | Black or Black British - Somali                     |
      | Black or Black British - Mixed Black                |
      | Black or Black British - Nigerian                   |
      | Black British                                       |
      | Other Black, Black unspecified                      |
      | Chinese                                             |
      | Any other ethnic group                              |
      | Ashkenazi                                           |
      | Sephardi                                            |
      | Vietnamese                                          |
      | Japanese                                            |
      | Filipino                                            |
      | Malaysian                                           |
      | Any Other Group                                     |
      | Not stated                                          |
    And the user is able to close the popup by clicking on the close icon

    Examples:
      | PedigreeStage | ProbandDetails              | WarningMessage                                                                                |
      | Pedigree      | NHSNumber=NA:DOB=25-11-2003 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-4759 @Z-LOGOUT
#    @E2EUI-1105 @E2EUI-1030 @E2EUI-1165
  Scenario Outline: NTS-4759:E2EUI-1105,1030,1165: Add additional non-tested family member
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-11-2004:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Add a requesting organisation
    ##Family Member
    When the user navigates to the "<FamilyMember>" stage
    Then the user is navigated to a page with title Add a family member to this referral
    When the user clicks on add non-tested-family member link on family landing page
    ##Pedigree Stage
    Then the user is navigated to a page with title Build a pedigree
    And the user should be able to see a "<WarningMessage>" on the pedigree page
    When the user adds new parent node to proband "<ProbandDetails>"
    Then the user sees two NON NGIS Patient ID nodes added to the patient "<ProbandDetails>"
    ##E2EUI-1030
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Clinical
    Then the user should see below fields on Clinical Tab with the given status
      | FieldLabel                                       | FieldStatus |
      | Please select coding system for disorder search: | Editable    |
    And the user should see diagnosis code options as below
      | Options  |
      | OMIM     |
      | Orphanet |
    ##E2EUI-1165
    When the user select the pedigree tab Phenotype
    And the user should see HPO Present options as below
      | Options |
      | Present |
      | Absent  |
      | Unknown |
    And the user is able to close the popup by clicking on the close icon

    Examples:
      | FamilyMember   | ProbandDetails              | WarningMessage                                                                                |
      | Family members | NHSNumber=NA:DOB=25-11-2004 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |