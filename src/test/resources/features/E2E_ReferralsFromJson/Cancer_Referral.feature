@ReferralFromJson
@CancerReferralFromJson
Feature: Create Cancer Referrals by reading details from Json file

#  Background:User Gets app versions from NGIS Status page
#    Given the user opens the NGIS status page
#    And the user reads the version numbers present on the page
#    Then the user writes the versions of RequiredComponents in the txt file "VersionFile.txt"
#      | TEST_DIRECTORY_PRIVATE_UI_TAG,TEST_DIRECTORY_PUBLIC_UI_TAG,TEST_ORDER_UI_TAG,BIOBANK_ILLUMINA_TAG,BIOBANK_ILLUMINA_WATCHER_TAG,MIPORTAL_UI_TAG,PANEL_MS_TAG |

#  @CancerReferralFromJson @Z-LOGOUT
#  Scenario Outline: Read the details from Json file and create Cancer referrals
#    ##Note that the json file is expected to be present in testdata folder
#    Given the json file <JSONFileName> with referral information is available in the specified location
#    And the referral object is created successfully from the json file <JSONFileName>
#    When the "Cancer" referral is created with details from Json provided
#      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
#    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
#### Add tumour samples morphology & topography and tumours morphology and topography if present in json
#
#    Examples:
#      | JSONFileName                                           |
##      | Cancer1_r20210200001_10000_referral_DDF_modified.json |
##      | Cancer2_r20210200002_10000_referral_DDF_modified.json |
##      |Cancer3_r20210200003_10000_referral_DDF_modified.json  |
##      | Cancer4_r20210200005_10000_referral_DDF_modified.json |
##      | Cancer5_r20210200007_10000_referral_DDF_modified.json  |
##      | Cancer6_r20210200009_10000_referral_DDF_modified.json  |
#      | Cancer7_r20210200010_10000_referral_DDF_modified.json  |
#      | Cancer8_r20210200015_10000_referral_DDF_modified.json  |
#      | Cancer9_r20210200017_10000_referral_DDF_modified.json  |
#      | Cancer10_r20210200020_10000_referral_DDF_modified.json |

  @CancerCase1 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000205-DNA_F03,LP3000208-DNA_D05
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer1_r20210200001_10000_referral_DDF_modified.json |

  @CancerCase2 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000571-DNA_H07,LP3000575-DNA_A08
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer2_r20210200002_10000_referral_DDF_modified.json |

  @CancerCase3 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000226-DNA_H01,LP3000240-DNA_D12
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer3_r20210200003_10000_referral_DDF_modified.json |

  @CancerCase4 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3001150-DNA_H11,LP3001144-DNA_B10
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer4_r20210200005_10000_referral_DDF_modified.json |

  @CancerCase5 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals for Samples- LP3000430-DNA_B09,LP3000428-DNA_C12
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer5_r20210200007_10000_referral_DDF_modified.json |

  @CancerCase6 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000763-DNA_D02,LP3000762-DNA_D02
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer6_r20210200009_10000_referral_DDF_modified.json |

  @CancerCase7 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000542-DNA_G01,LP3000544-DNA_G12
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer7_r20210200010_10000_referral_DDF_modified.json |

  @CancerCase8 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000858-DNA_H02,LP3000859-DNA_H02
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer8_r20210200015_10000_referral_DDF_modified.json |

  @CancerCase9 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3001615-DNA_H01,LP3001616-DNA_D02
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer9_r20210200017_10000_referral_DDF_modified.json |

  @CancerCase10 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3001388-DNA_B05,LP3001379-DNA_B06
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | Cancer10_r20210200020_10000_referral_DDF_modified.json |

  @CancerLNS_1 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000226-DNA_H01,LP3000240-DNA_D12
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                               |
      | Cancer_LNS_1_r20210200003_10000_referral_DDF_modified.json |

  @CancerLNS_2 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000430-DNA_B09,LP3000428-DNA_C12
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                               |
      | Cancer_LNS_2_r20210200007_10000_referral_DDF_modified.json |

  @CancerLNS_3 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000763-DNA_D02,LP3000762-DNA_D02
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                               |
      | Cancer_LNS_3_r20210200009_10000_referral_DDF_modified.json |

  @CancerLNS_4 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3000542-DNA_G01,LP3000544-DNA_G12
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                               |
      | Cancer_LNS_4_r20210200010_10000_referral_DDF_modified.json |

  @CancerLNS_5 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referral for Samples- LP3001177-DNA_D01,LP3001120-DNA_A12
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                               |
      | Cancer_LNS_5_r20210200011_10001_referral_DDF_modified.json |
