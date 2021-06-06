@ReferralFromJson
@CancerReferralFromJson
Feature: Create Cancer Referrals by reading details from Json file

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
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer1_r20210200001_10000_referral_DDF_modified.json |

  @CancerCase2 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | cer2_r20210200002_10000_referral_DDF_modified.json |

  @CancerCase3 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer3_r20210200003_10000_referral_DDF_modified.json |

  @CancerCase4 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer4_r20210200005_10000_referral_DDF_modified.json |

  @CancerCase5 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer5_r20210200007_10000_referral_DDF_modified.json |

  @CancerCase6 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer6_r20210200009_10000_referral_DDF_modified.json |

  @CancerCase7 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer7_r20210200010_10000_referral_DDF_modified.json |

  @CancerCase8 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer8_r20210200015_10000_referral_DDF_modified.json |

  @CancerCase9 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                          |
      | Cancer9_r20210200017_10000_referral_DDF_modified.json |

  @CancerCase10 @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create Cancer referrals
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Cancer" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Cancer" referral should be created via TOMS using json provided information and submitted successfully
    Examples:
      | JSONFileName                                           |
      | Cancer10_r20210200020_10000_referral_DDF_modified.json |
