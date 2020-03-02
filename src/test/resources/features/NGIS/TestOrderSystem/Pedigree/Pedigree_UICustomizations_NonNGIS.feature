@regression
@pedigree
@pedigree_uiCustomizationNonNGIS
Feature: Pedigree - UI Customizations - Non NGIS

  @NTS-4759 @E2EUI-1626 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4759: Warn a user that they will lose their changes when navigating away from pedigree
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1986:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
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

  @NTS-4759 @E2EUI-1391 @LOGOUT @v_1 @P0
  Scenario Outline: NTS-4759: AgeOfOnset should be editable for Non NGIS Patients in Pedigree tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1986:Gender=Male |
    ##Patient Details
    Then the user is navigated to a page with title Check your patient's details
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
      | Pedigree      | NHSNumber=NA:DOB=25-11-1986 | 2 years,2 months | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |
