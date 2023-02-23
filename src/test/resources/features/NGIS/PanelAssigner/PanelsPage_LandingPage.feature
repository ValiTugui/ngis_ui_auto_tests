#@regression
@panelsPage_LandingPage
@06-PANEL_ASSIGNER
@SYSTEM_TEST
@SYSTEM_TEST_2
  ##This feature files have to be re-implemented based on new/modified tickets for Panel App.
  ##This changes are from Hanna Release
Feature: PanelAssigner: Panels Page Landing Page

  @NTS-3380 @NTS-3381 @NTS-3424 @NTS-5818 @NTS-5947-Scenario-4 @NTS-5818 @Z-LOGOUT
#    @E2EUI-1231 @E2EUI-1906 @E2EUI-1278 @E2EUI-976 @E2EUI-1045 @E2EUI-1484 @E2EUI-1158
#    @E2EUI-3015 @E2EUI-2978 @E2EUI-2979 @E2EUI-1469 @E2EUI-1322 @E2EUI-1278 @E2EUI-1258
  Scenario Outline: NTS-3380: Search and add panels to referral
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1967:Gender=Male |
    ##Patient Details Page
    When the user is navigated to a page with title Add a requesting organisation
    ##Clinical Questions
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user is navigated to a page with title Answer clinical questions
    And the user fills the ClinicalQuestionsPage with the "<ClinicalQuestionDetails>"
    And the user clicks the Save and Continue button
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    ##NTS-5947 @Scenario4
    And the panels landing page displays the introduction message as shown below
      | The test package requires:         |
      | confirmation of disease penetrance |
      | addition of at least one panel     |
    ##NTS-3381
    And the user should be able to see panels page is correctly displayed
    And the user should see the section with title Default Panel based on the clinical information
    And the user sees suggested panels under the section Default Panel based on the clinical information
    ##@E2EUI-1906
    And the user should be able to see a sub title Confirm disease penetrance on panels page
    Then the user should be able to see an additional line "<textLine>" underneath the penetrance title
    ##@E2EUI-1278
    And the user should be able to see Add another panel section with search field and search icon
    #Below steps for NTS-5818
    And the user should see the Add Panels section with the Only ticked panels will be sent for analysis
    ##@E2EUI-1231
    And Penetrance section with options Complete and Incomplete
    And the user clicks on Incomplete button and button will show tick marked
    And the user clicks on Complete button and button will show tick marked
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    ##Pedigree
    Then the user is navigated to a page with title Build a pedigree
    Then the user attempts to navigate away by clicking "back"
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user sees the selected "<searchPanels>" panels under added panels
    Then the user should see the referral submit button as disabled
    ##NTS-3381
    Then the user should be able to deselect the selected panels
    And the user should be able to change the penetrance status
    When the user clicks on VisitPanelApp link
    Then the user navigates to panelApp page
    ##NTS-5818
    When the user navigates to the "<ClinicalQuestion>" stage
    Then the user sees a prompt alert "This section contains unsaved information. Discard changes?" after clicking "<ClinicalQuestion>" button and "OK" it


    Examples:
      | Panels | ClinicalQuestion   | ClinicalQuestionDetails                                         | searchPanels                                  | textLine                                                                                                                                                                   |
      | Panels | Clinical questions | DiseaseStatus=Affected:AgeOfOnset=10,02:HpoPhenoType=Lymphedema | Cardiac arrhythmias,Pigmentary skin disorders | Change suggested penetrance if: there is a referral form that confirms a different penetrance local decision-making processes indicate a different penetrance is preferred |

  @HTO-420 @HTO-699 @HTO-420-1
  Scenario Outline: HTO-420-1 Verifies the default panel name is <panelName>
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | <CIId> | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Manage panels
    And Penetrance section with options Complete and Incomplete
#    And the user clicks on Incomplete button and button will show tick marked
#    And the user should see the section with title Default Panel based on the clinical information
    And the user should see the default status of penetrance button as Incomplete
    And the default panel name is "<panelName>"
    And the user clicks the Save and Continue button

    Examples:
      | Panels | CIId | panelName                                                        |
      | Panels | R15  | Primary immunodeficiency or monogenic inflammatory bowel disease |
      | Panels | R27  | Paediatric disorders                                             |
      | Panels | R29  | Intellectual disability - microarray and sequencing                                         |
      | Panels | R143 | Neonatal diabetes                                        |
      | Panels | R98  | Likely inborn error of metabolism                                      |
      | Panels | R104 | Skeletal dysplasia                                               |
      | Panels | R100 | Craniosynostosis                                                 |
      | Panels | R54  | Hereditary ataxia - adult onset                                  |
      | Panels | R55  | Hereditary ataxia and cerebellar anomalies - childhood onset     |
      | Panels | R59  | Genetic epilepsy syndromes                                       |
      | Panels | R61  | Hereditary spastic paraplegia - childhood onset                  |
      | Panels | R83  | Arthrogryposis                                                   |
      | Panels | R381 | Neuromuscular disorders                                          |
      | Panels | R84  | Hereditary ataxia and cerebellar anomalies - childhood onset     |
      | Panels | R85  | Holoprosencephaly                                                |
      | Panels | R86  | Hydrocephalus                                                    |
      | Panels | R87  | Cerebral malformations                                           |
      | Panels | R88  | Severe microcephaly                                              |
      | Panels | R109 | White matter disorders - childhood onset                         |
      | Panels | R193 | Cystic renal disease                                             |
      | Panels | R89  |                                                                  |

  @HTO-420 @HTO-420-2
  Scenario Outline: HTO-420-2 Verifies that <searchPanels> is added under added panels
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | <CIId> | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Manage panels
    And Penetrance section with options Complete and Incomplete
    And the user clicks on Incomplete button and button will show tick marked
    And the user should see the section with title Default Panel based on the clinical information
    And the default panel name is "<panelName>"
    When the user search and add the "<searchPanels>" panels
    Then the user sees the selected "<searchPanels>" panels under added panels
    And the user clicks the Save and Continue button
    Then the "<Panels>" stage is marked as Completed
    ##panels
    When the user navigates to the "<Panels>" stage
    Then the user is navigated to a page with title Manage panels
    And the user sees the selected "<searchPanels>" panels under added panels
    Examples:
      | Panels | CIId | panelName        | searchPanels                                                     |
      | Panels | R100 | Craniosynostosis | Primary immunodeficiency or monogenic inflammatory bowel disease |

  @HTO-877
  Scenario: HTO-877 Search for panels using their full name and add them as additional panels
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R98 | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    ##Panels Page
    When the user navigates to the "Panels" stage
    And the user is navigated to a page with title Manage panels
    And Penetrance section with options Complete and Incomplete
    And the user clicks on Incomplete button and button will show tick marked
    And the user should see the section with title Default Panel based on the clinical information
#    And the default panel name is "Craniosynostosis"
    When the user searches and adds the following panels
    ## New panel names
      | Intellectual disability - microarray and sequencing                             |
      | Neonatal diabetes                                                               |
#      | Likely inborn error of metabolism - targeted testing not possible                                               |
      | Rare syndromic craniosynostosis or isolated multisuture synostosis              |
      | Early onset or syndromic epilepsy                                               |
      | Hereditary ataxia with onset in adulthood                                       |
      | Hereditary neuropathy or pain disorder - NOT PMP22 copy number                  |
      | Adult onset hereditary spastic paraplegia                                       |
      | Childhood onset hereditary spastic paraplegia                                   |
      | Holoprosencephaly - NOT chromosomal                                             |
      | Adult onset neurodegenerative disorder                                          |
      | Adult onset leukodystrophy                                                      |
      | Bilateral congenital or childhood onset cataracts                               |
      | Paediatric or syndromic cardiomyopathy                                          |
      | Adult onset dystonia, chorea or related movement disorder                       |
      | Childhood onset dystonia, chorea or related movement disorder                   |
      | Unexplained young onset end-stage renal disease                                 |
#     no | Childhood solid tumours                                                         |
      | Cerebral malformation                                                           |
      | Other rare neuromuscular disorders                                              |
      | Childhood onset leukodystrophy                                                  |
      | Arrhythmogenic right ventricular cardiomyopathy                                 |
# no     | Brugada syndrome and cardiac sodium channel disease                             |
      | Dilated and arrhythmogenic cardiomyopathy                                       |
      | Hypertrophic cardiomyopathy                                                     |
      | Thoracic aortic aneurysm or dissection                                          |
      | Polycystic liver disease                                                        |
      | Iron metabolism disorders - NOT common HFE mutations                            |
      | Thrombophilia with a likely monogenic cause                                     |
      | Familial chylomicronaemia syndrome (FCS)                                        |
      | Ehlers Danlos syndrome with a likely monogenic cause                            |
      | Sporadic aniridia                                                               |
      | Corneal dystrophy                                                               |
      | Hereditary systemic amyloidosis                                                 |
      | Membranoproliferative glomerulonephritis including C3 glomerulopathy            |
      | Familial hypercholesterolaemia (GMS)                                            |
#   no   | Hypogonadotropic hypogonadism (GMS)                                             |
      | Segmental overgrowth disorders - Deep sequencing                                |
#   no   | Limb girdle muscular dystrophies, myofibrillar myopathies and distal myopathies |
      ## NEW WGS PANELS
      | Arrhythmogenic right ventricular cardiomyopathy*                                |
#     no | Congenital muscular dystrophy and congenital myopathy                           |
    And the user clicks the Save and Continue button
    Then the "Panels" stage is marked as Completed

  @HTO-877-2
  Scenario Outline: HTO-877-2 Verifies the default panel name  of  CI
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | <CI> | GEL_NORMAL_USER | NHSNumber=NA-Patient not eligible for NHS number (e.g. foreign national):DOB=25-10-1987:Gender=Male |
    ##Panels Page
    When the user navigates to the "<Panels>" stage
    And the user is navigated to a page with title Manage panels
    And Penetrance section with options Complete and Incomplete
#    And the user clicks on Incomplete button and button will show tick marked
#    And the user should see the section with title Default Panel based on the clinical information
    And the user should see the default status of penetrance button as Incomplete
    And the default panel name is "<panelName>"
    And the user clicks the Save and Continue button

    Examples:
      | Panels | CI | panelName                                                        |
      | Panels | Primary immunodeficiency or monogenic inflammatory bowel disease  | Primary immunodeficiency or monogenic inflammatory bowel disease |
      | Panels | Congenital malformation and dysmorphism syndromes  | Paediatric disorders                                             |
      | Panels | Intellectual disability  | Intellectual disability - microarray and sequencing                                         |
      | Panels | Neonatal diabetes | Neonatal diabetes                                        |
      | Panels | Likely inborn error of metabolism  | Likely inborn error of metabolism - targeted testing not possible                                   |
      | Panels | Skeletal dysplasia | Skeletal dysplasia                                               |
      | Panels | Rare syndromic craniosynostosis or isolated multisuture synostosis | Rare syndromic craniosynostosis or isolated multisuture synostosis                                                 |
      | Panels | Hereditary ataxia with onset in adulthood  | Hereditary ataxia with onset in adulthood                                  |
      | Panels | Hereditary ataxia with onset in childhood  | Hereditary ataxia and cerebellar anomalies - childhood onset     |
      | Panels | Early onset or syndromic epilepsy  | Early onset or syndromic epilepsy                                       |
      | Panels | Childhood onset hereditary spastic paraplegia  | Childhood onset hereditary spastic paraplegia                  |
      | Panels | Arthrogryposis  | Arthrogryposis                                                   |
      | Panels | Other rare neuromuscular disorders | Other rare neuromuscular disorders                                          |
      | Panels | Cerebellar anomalies  | Hereditary ataxia and cerebellar anomalies - childhood onset     |
      | Panels | Holoprosencephaly - NOT chromosomal  | Holoprosencephaly - NOT chromosomal                                                |
      | Panels | Hydrocephalus  | Hydrocephalus                                                    |
      | Panels | Cerebral malformation  | Cerebral malformation                                          |
      | Panels | Severe microcephaly  | Severe microcephaly                                             |
      | Panels | Childhood onset leukodystrophy | Childhood onset leukodystrophy                        |
      | Panels | Cystic renal disease | Cystic renal disease                                            |
#      | r89Panels | Ultra-rare and atypical monogenic disorders  |                                                                  |
      | Panels | Bilateral congenital or childhood onset cataracts  |Bilateral congenital or childhood onset cataracts                                                                 |
      | Panels | Retinal disorders  |  Retinal disorders                                                                |
      | Panels | Structural eye disease  |   Structural eye disease                                                               |
      | Panels | Adult onset dystonia, chorea or related movement disorder  | Adult onset dystonia, chorea or related movement disorder                                                                |
      | Panels | Childhood onset dystonia, chorea or related movement disorder  | Childhood onset dystonia, chorea or related movement disorder                                                                 |
      | Panels | Adult onset neurodegenerative disorder  |  Adult onset neurodegenerative disorder                                                                |
      | Panels | Adult onset hereditary spastic paraplegia  | Adult onset hereditary spastic paraplegia                                                                 |
      | Panels | Adult onset leukodystrophy  |   Adult onset leukodystrophy                                                               |
      | Panels | Hypotonic infant  | Hypotonic infant                                                                 |
      | Panels | Hereditary neuropathy or pain disorder - NOT PMP22 copy number  |Hereditary neuropathy or pain disorder - NOT PMP22 copy number                                                                  |
      | Panels | Paediatric or syndromic cardiomyopathy  |Paediatric or syndromic cardiomyopathy                                                                |
      | Panels | Unexplained paediatric onset end-stage renal disease  |    Unexplained young onset end-stage renal disease                                                              |
      | Panels | Sudden cardiac death PILOT  | Sudden unexplained death or survivors of a cardiac event                                                                 |





