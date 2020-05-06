#@regression
#@pedigree_uiCustomizationNonNGIS
@07-PEDIGREE
@SYSTEM_TEST
Feature: Pedigree - UI Customizations - Non NGIS-2

  @NTS-4796 @Z-LOGOUT
#    @E2EUI-1138 @E2EUI-933
  Scenario Outline: NTS-4796:(E2EUI-1138,933): editing panel – Personal tab
    ##Note: E2EUI-933 talks about pedigree diagram embedded as part of NGIS app - Can be part of any diagram click ticket
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1990:Gender=Male |
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
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Personal
    Then the user should see below fields on Personal Tab with the given status
      | FieldLabel                  |
      | Gender                      |
      | Working Karyotypic sex      |
      | NHS Number                  |
      | NGIS Patient ID             |
      | Non NGIS Patient ID         |
      | CHI Number                  |
      | Organ Donor ID              |
      | Local Identifier            |
      | Last name                   |
      | First name                  |
      | Ethnicity                   |
      | Age at death                |
      | Date of birth               |
      | Date of death               |
      | Gestation age               |
      | Estimated Date Of Delivery  |
      | Individual is               |
      | Heredity options            |
      | Adopted status              |
      | Participating in test       |
      | Comments                    |
      | Not in contact with proband |
    And the user should see the below options for Gender field Personal tab
      | Female        |
      | Indeterminate |
      | Male          |
      | Unknown       |
    And the user should see the below options for KaryoTypicSex field Personal tab
      | XY    |
      | XX    |
      | XO    |
      | XXY   |
      | XYY   |
      | XXX   |
      | XXYY  |
      | XXXY  |
      | XXXX  |
      | Other |
    And the user should see the below options for GestationAge field Personal tab
      | 0 weeks  |
      | 1 week   |
      | 2 weeks  |
      | 10 weeks |
      | 11 weeks |
      | 20 weeks |
      | 25 weeks |
      | 30 weeks |
      | 35 weeks |
      | 40 weeks |
      | 45 weeks |
      | 49 weeks |
      | 50 weeks |
    And the user should see the below options for Heredity field Personal tab
      | None      |
      | Childless |
      | Infertile |

    Examples:
      | FamilyMember   | ProbandDetails              | WarningMessage                                                                                |
      | Family members | NHSNumber=NA:DOB=25-11-1990 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-47961 @Z-LOGOUT
#    @E2EUI-1345 @E2EUI-1160
  Scenario Outline: NTS-4796:(E2EUI-1345,1160): Editable Fields for Non NGIS Participants
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1991:Gender=Male |
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
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Tumours
    Then the user should see below fields on Tumours Tab with the given status
      | FieldName                            | FieldStatus |
      | Number Of Colorectal Polyps Total    | Editable    |
      | Number of Colorectal Polyps Adenomas | Editable    |
    ##E2EUI-1160
    And the user enters tumour field values as "<TumourFieldValues>"
    And the user is able to close the popup by clicking on the close icon
    When the user click on Save menu button
    Then the user should see error pop up message displayed as "<ErrorMessage>"

    Examples:
      | FamilyMember   | ProbandDetails              | TumourFieldValues | ErrorMessage                                                                                                                                                                   | WarningMessage                                                                                |
      | Family members | NHSNumber=NA:DOB=25-11-1991 | 123abc,234jut     | Invalid value entered for 'Number Of Colorectal Polyps Total' for Participant with id,Invalid value entered for 'Number of Colorectal Polyps Adenomas' for Participant with id | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-4796 @Z-LOGOUT
#    @E2EUI-1348 @E2EUI-1230
    ##One part of 1230 covered here and another part in NTS-4795
  Scenario Outline: NTS-4796:(E2EUI-1348,1230): Non NGIS Patient Stable UID
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1992:Gender=Male |
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
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Personal
    Then the user should see the Non NGSID displayed in personal tab for the selected Non NGIS member for "<ProbandDetails>"
    Examples:
      | FamilyMember   | ProbandDetails              | WarningMessage                                                                                |
      | Family members | NHSNumber=NA:DOB=25-11-1992 | Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |

  @NTS-4796 @Z-LOGOUT
#    @E2EUI-1074
  Scenario Outline: NTS-4796:E2EUI-1074: Connecting Lookup Services to Pedigree Tool
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R105 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-11-1993:Gender=Male |
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
    When the user selects pedigree node for one of the Non NGIS family member for "<ProbandDetails>"
    And the user select the pedigree tab Clinical
    Then the user should be able to search disease "<Disease>" and codes in the pedigree and add to the selected nodes

    Examples:
      | FamilyMember   | ProbandDetails              | Disease|WarningMessage                                                                                |
      | Family members | NHSNumber=NA:DOB=25-11-1993 | carcinoid|Save this pedigree before leaving this section. Changes will be lost if details aren’t saved. |
