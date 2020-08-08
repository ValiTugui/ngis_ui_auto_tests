@Concurrency
@Concurrency_Stage
Feature: Concurrency Stage Update Validation

  @Z-LOGOUT
  Scenario Outline: User One Login and Updating Stages
    ##Create referral with new patient without providing NHS number
    Given a new patient referral is created with associated tests in Test Order System online service
      | TEST_DIRECTORY_PRIVATE_URL | test-selection/clinical-tests | R100 | GEL_NORMAL_USER | NHSNumber=NA-Patient is a foreign national:DOB=25-10-1998:Gender=Male |
    ##Patient Details
    And the "<PatientDetails>" stage is marked as Completed
    ##Requesting Organisation
    Examples:
      | PatientDetails  |
      | Patient details |