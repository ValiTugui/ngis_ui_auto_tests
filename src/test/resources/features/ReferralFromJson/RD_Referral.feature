@ReferralFromJson
Feature: Create Referrals by reading details from Json file

  @RDReferralFromJson  @Z-LOGOUT
  Scenario Outline: Read the details from Json file and create RD referrals
    ##Note that the json file is expected to be present in testdata folder
    Given the json file <JSONFileName> with referral information is available in the specified location
    And the referral object is created successfully from the json file <JSONFileName>
    When the "Rare Disease" referral is created with details from Json provided
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | create a new patient record | Patient not eligible for NHS number (e.g. foreign national) | GEL_NORMAL_USER |
    Then the "Rare Disease" referral should be created via TOMS using json provided information and submitted successfully

    Examples:
      | JSONFileName                                       |
#      | RD1_r20210100001_10002_referral_DDF_modified.json |
#      | RD2_r20210100002_10001_referral_DDF_modified.json |
#      | RD3_r20210100006_10001_referral_DDF_modified.json |
#      | RD4_r20210100007_10001_referral_DDF_modified.json |
      | RD5_r20210100011_10001_referral_DDF_modified.json  |
      | RD6_r20210100014_10001_referral_DDF_modified.json  |
#      | RD7_r20210100020_10001_referral_DDF_modified.json  |
#      | RD8_r20210100021_10000_referral_DDF_modified.json  |
#      | RD9_r20210100022_10000_referral_DDF_modified.json  |
#      | RD10_r20210100023_10000_referral_DDF_modified.json |
