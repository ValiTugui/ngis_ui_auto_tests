@LoginCheck

  Feature: Login with NHS
    Scenario: Login check with NHS mail id
#      Given a new patient referral is created with associated tests in Test Order System online service
#        | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=19-10-2001:Gender=Male |

      Given a new patient referral is created with associated tests in Test Order System online service
        | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | NHS_Test_User | NHSNumber=NA-Patient is a foreign national:DOB=19-10-2001:Gender=Male |

      Then the user is navigated to a page with title Check your patient's details