@ReferralFromJson
@RDReferralFromJson
Feature: Create RD Referrals by reading details from Json file

#  Background:User Gets app versions from NGIS Status page
#    Given the user opens the NGIS status page
#    And the user reads the version numbers present on the page
#    Then the user writes the versions of RequiredComponents in the txt file "VersionFile.txt"
#      | TEST_DIRECTORY_PRIVATE_UI_TAG,TEST_DIRECTORY_PUBLIC_UI_TAG,TEST_ORDER_UI_TAG,BIOBANK_ILLUMINA_TAG,BIOBANK_ILLUMINA_WATCHER_TAG,MIPORTAL_UI_TAG,PANEL_MS_TAG |

#  @RDReferralFromJson  @Z-LOGOUT
#  Scenario Outline: Read the details from Json file and create RD referrals
#    ##Note that the json file is expected to be present in testdata folder
#    Given the json file <JSONFileName> with referral information is available in the specified location
#    And the referral object is created successfully from the json file <JSONFileName>
#    When the "Rare Disease" referral is created with details from Json provided
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
#
#    Examples:
#      | JSONFileName                                       |
##      | RD1_r20210100001_10002_referral_DDF_modified.json |
##      | RD2_r20210100002_10001_referral_DDF_modified.json |
##      | RD3_r20210100006_10001_referral_DDF_modified.json |
##      | RD4_r20210100007_10001_referral_DDF_modified.json |
##      | RD5_r20210100011_10001_referral_DDF_modified.json  |
##      | RD6_r20210100014_10001_referral_DDF_modified.json  |
#      | RD7_r20210100020_10001_referral_DDF_modified.json  |
#      | RD8_r20210100021_10000_referral_DDF_modified.json  |
#      | RD9_r20210100022_10000_referral_DDF_modified.json  |
#      | RD10_r20210100023_10000_referral_DDF_modified.json |

  @RDCase1  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3001191-DNA_B10,LP3001158-DNA_D01,LP3001237-DNA_H12,LP3001224-DNA_B12,LP3001220-DNA_D10
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD1_r20210100001_10002_referral_DDF_modified.json |

  @RDCase2  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3000180-DNA_A09,LP3000173-DNA_A10,LP3000209-DNA_A05
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD2_r20210100002_10001_referral_DDF_modified.json |

  @RDCase3  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3000301-DNA_E12,LP3000311-DNA_H09,LP3000286-DNA_H04
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD3_r20210100006_10001_referral_DDF_modified.json |

  @RDCase4  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3000565-DNA_H11
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD4_r20210100107_10000_referral_DDF_modified.json |

  @RDCase5  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3001148-DNA_B06,LP3001076-DNA_F01
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD5_r20210100125_10000_referral_DDF_modified.json |

  @RDCase6  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Sample- LP3000560-DNA_B12
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD6_r20210100014_10001_referral_DDF_modified.json |

  @RDCase7  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3000167-DNA_B11,LP3000374-DNA_D06,LP3000199-DNA_D01
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD7_r20210100020_10001_referral_DDF_modified.json |

  @RDCase8  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3001230-DNA_F01,LP3001231-DNA_E01
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD8_r20210100015_10001_referral_DDF_modified.json |

  @RDCase9  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3000358-DNA_E11,LP3000339-DNA_F07
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                      |
      | RD9_r20210100022_10000_referral_DDF_modified.json |

  @RDCase10  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referral for Samples- LP3000327-DNA_C07,LP3000332-DNA_E10,LP3000337-DNA_A08,LP3000326-DNA_F04
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                       |
      | RD10_r20210100023_10000_referral_DDF_modified.json |

#  @RDCase11  @Z-LOGOUT
#  Scenario Outline: Read the details from Json file and create RD referrals
#    Given the json file <JSONFileName> with referral information is available in the specified location
#    And the referral object is created successfully from the json file <JSONFileName>
#    When the "Rare Disease" referral is created with details from Json provided
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
#    Examples:
#      | JSONFileName           |
#      | RD46_E2E_TestCase.json |
#
#  @RDCase12  @Z-LOGOUT
#  Scenario Outline: Read the details from Json file and create RD referrals
#    Given the json file <JSONFileName> with referral information is available in the specified location
#    And the referral object is created successfully from the json file <JSONFileName>
#    When the "Rare Disease" referral is created with details from Json provided
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
#    Examples:
#      | JSONFileName           |
#      | RD10_E2E_TestCase.json |

  @SmallCNV_1  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV1_112008234_10002_referral_DDF_modified.json |

  @SmallCNV_2  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV2_115002358_10003_referral_DDF_modified.json |

  @SmallCNV_3  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV3_115011288_10008_referral_DDF_modified.json |

  @SmallCNV_4  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV4_118002059_10035_referral_DDF_modified.json |

  @SmallCNV_5  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV5_123000278_10016_referral_DDF_modified.json |

  @SmallCNV_6  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV6_128050012_10000_referral_DDF_modified.json |

  @SmallCNV_7  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV7_128050098_10000_referral_DDF_modified.json |

  @SmallCNV_8  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV8_128050099_10000_referral_DDF_modified.json |

  @SmallCNV_9  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                    |
      | CNV9_210015879_10009_referral_DDF_modified.json |

  @SmallCNV_10  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                     |
      | CNV10_210019611_10001_referral_DDF_modified.json |

  @RD_WWM_1  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                        |
      | RD_WWM_1_111003721_10029_referral_DDF_modified.json |

  @RD_WWM_2  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                        |
      | RD_WWM_2_121000375_10014_referral_DDF_modified.json |

  @RD_LNS_1  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_LNS_1_r20210100011_10001_referral_DDF_modified.json |

  @RD_LNS_2  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_LNS_2_r20210100040_10000_referral_DDF_modified.json |

  @RD_LNS_3  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_LNS_3_r20210100041_10000_referral_DDF_modified.json |

  @RD_LNS_4 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_LNS_4_r20210100053_10000_referral_DDF_modified.json |

  @RD_LNS_5 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_LNS_5_r20210100104_10000_referral_DDF_modified.json |

  @RD_SOW_1 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_SOW_1_r20210100001_10002_referral_DDF_modified.json |

  @RD_LNS_6 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_LNS_6_r20210100107_10000_referral_DDF_modified.json |

  @RD_LNS_7 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_LNS_7_r20210100131_10000_referral_DDF_modified.json |

  @RD_LNS_8 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | RD_LNS_8_r20210100125_10000_referral_DDF_modified.json |

  @RD_New_CI_Panel @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                                  |
      | RD_New_CI_Panel_r20210100126_10000_referral_DDF_modified.json |

  @RD_Old_CI_Panel @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                                  |
      | RD_Old_CI_Panel_r20210100118_10000_referral_DDF_modified.json |




