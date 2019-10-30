package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.lib.Actions;
import co.uk.gel.proj.TestDataProvider.NgisPatientOne;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.List;

public class ReferralSteps extends Pages {


    public ReferralSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Then("^the referral page is displayed$")
    public void referralPageIsDisplayed() {
        referralPage.checkThatReferralWasSuccessfullyCreated();
        referralPage.saveAndContinueButtonIsDisplayed();
        referralPage.clickSaveAndContinueButton();
    }


    @When("^the user navigates to the \"([^\"]*)\" stage$")
    public void navigateTOSpecificStage(String stage) {
        referralPage.navigateToStage(stage);
    }

    @And("the user clicks the Save and Continue button")
    public void theUserClicksTheSaveAndContinueButton() {
        referralPage.clickSaveAndContinueButton();
    }

    @Given("a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service")
    public void aReferralIsCreatedWithTheBelowDetailsForAnExistingPatientRecordTypeAndAssociatedTestsInTestOrderSystemOnlineService(List<String> attributeOfURL) throws IOException {
        boolean eachElementIsLoaded;
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String patientType = attributeOfURL.get(3);
        String diseaseType = attributeOfURL.get(4);
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.typeInSearchField(searchTerm);
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
        homePage.closeCookiesBannerFromFooter();
        clinicalIndicationsTestSelect.clickStartReferralButton();
        paperFormPage.clickSignInToTheOnlineServiceButton();
        switchToURL(driver.getCurrentUrl());
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        Assert.assertTrue(eachElementIsLoaded);
        // utilising static NGIS test data for now. In future test framework will support api calls to get a random NGIS record
        if (diseaseType.equalsIgnoreCase("cancer") && patientType.equalsIgnoreCase("NGIS")) {
            patientSearchPage.fillInNHSNumberAndDateOfBirthByProvidingNGISPatientOne();
        } else if (diseaseType.equalsIgnoreCase("rare-disease") && patientType.equalsIgnoreCase("NGIS")) {
            patientSearchPage.fillInNHSNumberAndDateOfBirthByProvidingNGISPatientTwo();
        } else if (patientType.equalsIgnoreCase("SPINE")) {
            patientSearchPage.fillInNHSNumberAndDateOfBirthByProvidingRandomSpinePatientRecord();
        }
        patientSearchPage.clickSearchButtonByXpath(driver);
        patientSearchPage.clickPatientCard();
        patientDetailsPage.clickStartReferralButton();
        referralPage.checkThatReferralWasSuccessfullyCreated();
        referralPage.saveAndContinueButtonIsDisplayed();
        referralPage.clickSaveAndContinueButton();
    }


    @And("the {string} patient details searched for are the same in the referral header bar")
    public void thePatientDetailsSearchedForAreTheSameInTheReferralHeaderBar(String patientType) {

        if (patientType.equals("NGIS")) {
            //Wait.forNumberOfElementsToBeEqualTo(driver, By.cssSelector(valuesInReferralHeaderBar), 7);

            //String actualFullName = getText(referralHeaderDetails.get(0));
            String actualFullName = referralPage.referralHeaderPatientName.getText();
            String actualFullDOB = referralPage.referralHeaderBorn.getText();
            String actualGender = referralPage.referralHeaderGender.getText();
            String actualNHSNumber = referralPage.referralHeaderNhsNo.getText();
            String actualPatientId = referralPage.referralHeaderPatientNgisId.getText();
            String actualCid = referralPage.referralHeaderClinicalId.getText();
            String actualReferralId = referralPage.referralHeaderReferralId.getText();

            Debugger.println("Expected full name = " + NgisPatientOne.FULL_NAME + ", Actual full name " + actualFullName);
            Assert.assertEquals(NgisPatientOne.FULL_NAME, actualFullName);

            Debugger.println("Expected DOB = " + NgisPatientOne.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
            Assert.assertTrue(actualFullDOB.contains(NgisPatientOne.DATE_OF_BIRTH));

            Debugger.println("Expected Gender= " + NgisPatientOne.GENDER + ", Actual Gender: " + actualGender);
            Assert.assertEquals(NgisPatientOne.GENDER, actualGender);

            Debugger.println("Expected nhs no = " + NgisPatientOne.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
            Assert.assertEquals(NgisPatientOne.NHS_NUMBER, actualNHSNumber);
        }
    }

    @Then("the {string} stage is selected")
    public void theStageIsSelected(String newStage) {
        Assert.assertTrue(referralPage.stageIsSelected(newStage));
    }
}