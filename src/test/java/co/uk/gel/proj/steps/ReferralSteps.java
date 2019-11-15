package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.lib.Actions;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.TestDataProvider.NgisPatientOne;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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

        // Check condition for different scenarios when referral submit button is displayed
        if (patientDetailsPage.addDetailsToNGISButtonList.size() > 0) {
            Debugger.println("Add Patient Details button shown");
            patientDetailsPage.addDetailsToNGISButton.click();
            Wait.forElementToBeDisplayed(driver, patientDetailsPage.successNotification);
            patientDetailsPage.clickStartReferralButton();
        } else if (patientDetailsPage.updateNGISRecordButtonList.size() > 0) {
            Debugger.println("Update Patient Details button shown");
            patientDetailsPage.updateNGISRecordButton.click();
            Wait.forElementToBeDisplayed(driver, patientDetailsPage.successNotification);
            patientDetailsPage.clickStartReferralButton();
        } else if (patientDetailsPage.savePatientDetailsToNGISButtonList.size() > 0) {
            Debugger.println("Save Patient Details button shown");
            patientDetailsPage.clickSavePatientDetailsToNGISButton();
            patientDetailsPage.patientIsCreated();
            patientDetailsPage.clickStartNewReferralButton();
        }
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

    @And("the {string} stage is marked as Completed")
    public void theStageIsMarkedAsCompleted(String stage) {
        Assert.assertTrue(referralPage.stageIsCompleted(stage));
    }

    @Given("a referral is created with the below details for a newly created patient and associated tests in Test Order System online service")
    public void aReferralIsCreatedWithTheBelowDetailsForANewlyCreatedPatientAndAssociatedTestsInTestOrderSystemOnlineService(List<String> attributeOfURL) throws IOException {
        boolean eachElementIsLoaded;
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String diseaseType = attributeOfURL.get(3);
        String createPatientHyperTextLink = attributeOfURL.get(4);
        String reasonForNoNHSNumber = attributeOfURL.get(5);
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
        //patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
        switchToURL(driver.getCurrentUrl());
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        Assert.assertTrue(eachElementIsLoaded);
        patientSearchPage.fillInNonExistingPatientDetailsUsingNHSNumberAndDOB();
        patientSearchPage.clickSearchButtonByXpath(driver);
        Assert.assertEquals("No patient found", patientSearchPage.noPatientFoundLabel.getText());
        patientSearchPage.checkCreateNewPatientLinkDisplayed(createPatientHyperTextLink);
        //driver.navigate().to("https://test-ordering.e2e.ngis.io/test-order/new-patient");  //Temp
        patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
        patientDetailsPage.newPatientPageIsDisplayed();
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNumber); //check DOB is pre-filled
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
        patientDetailsPage.patientIsCreated();
        patientDetailsPage.clickStartNewReferralButton();
        referralPage.checkThatReferralWasSuccessfullyCreated();
        referralPage.saveAndContinueButtonIsDisplayed();
    }

    @Then("the user sees a prompt alert {string} after clicking {string} button and {string} it")
    public void theUserSeesAPromptAlertAfterClickingButtonAndIt(String partOfMessage, String browserInteraction, String acknowledgeAlertPopup) {

        String actualAlertMessage;
        if (browserInteraction.equals("Samples") || (browserInteraction.equals("back"))) {
            actualAlertMessage = referralPage.acknowledgeThePromptAlertPopups(acknowledgeAlertPopup);
            Debugger.println("Clicking " + browserInteraction + " Actual alert in step:" + actualAlertMessage + " Expected part of Message: " + partOfMessage);
            Assert.assertTrue(actualAlertMessage.contains(partOfMessage));
        } else {
            actualAlertMessage = referralPage.acknowledgeThePromptAlertPopups(acknowledgeAlertPopup);
            Debugger.println("Clicking " + browserInteraction + " generate Browser Alert and not JS Web Application Alert:" + actualAlertMessage);
        }

    }

    @When("the user clicks the Log out button")
    public void theUserClicksTheLogOutButton() {
        referralPage.clickLogoutButton();
    }

    @Then("the user sees a warning message {string} on the page")
    public void theUserSeesAWarningMessageOnThePage(String expectedWarningText) {
        Wait.forAlertToBePresent(driver);
        Alert alertBox = driver.switchTo().alert();
        Wait.seconds(10);
        String actualWarningText = alertBox.getText();
        Assert.assertTrue(expectedWarningText.contains(actualWarningText));
        Actions.acceptAlert(driver);
        Wait.seconds(10);
        Debugger.println("URL info after accepting alert :: " + driver.getCurrentUrl());

    }

    @And("the {string} stage is marked as Mandatory To Do")
    public void theStageIsMarkedAsMandatoryToDo(String stage) {
        Assert.assertTrue(referralPage.stageIsMandatoryToDo(stage));
    }

    @And("Save and Continue button is displayed")
    public void saveAndContinueButtonIsDisplayed() {
        Assert.assertTrue("Save and Continue is meant to be displayed", referralPage.saveAndContinueButtonIsDisplayed());
    }

    //Added by STAG to create referral with the given patient details than taking from Test Data Provider
    @Given("a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service")
    public void aReferralIsCreatedWithTheBelowDetailsForTheGivenExistingPatientRecordTypeAndAssociatedTestsInTestOrderSystemOnlineService(List<String> attributeOfURL) throws IOException {
        boolean eachElementIsLoaded;
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String patientType = attributeOfURL.get(3);
        String diseaseType = attributeOfURL.get(4);
        String referralDetails = attributeOfURL.get(5);
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
        if (patientType.equalsIgnoreCase("NGIS")) {
            //Create NGIS Patient with the given Details and the use for referral Creation
            NGISPatientModel ngisPatient = new NGISPatientModel();
            HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(referralDetails);
            Set<String> paramsKey = paramNameValue.keySet();
            for (String key : paramsKey) {
                switch (key) {
                    case "NHSNumber": {
                        ngisPatient.setNHS_NUMBER(paramNameValue.get(key).trim());
                        break;
                    }
                    case "DOB": {
                        ngisPatient.setDATE_OF_BIRTH(paramNameValue.get(key).trim());
                        break;
                    }
                    case "FirstName": {
                        ngisPatient.setFIRST_NAME(paramNameValue.get(key));
                        break;
                    }
                    case "LastName": {
                        ngisPatient.setLAST_NAME(paramNameValue.get(key));
                        break;
                    }
                    case "Gender": {
                        ngisPatient.setGENDER(paramNameValue.get(key));
                        break;
                    }
                    case "Postcode": {
                        ngisPatient.setPOST_CODE(paramNameValue.get(key));
                        break;
                    }
                    case "Title": {
                        ngisPatient.setTITLE(paramNameValue.get(key));
                        break;
                    }
                }//switch
            }//for
            patientSearchPage.fillInNHSNumberAndDateOfBirth(ngisPatient);
        }else if (patientType.equalsIgnoreCase("SPINE")) {
            patientSearchPage.fillInNHSNumberAndDateOfBirthByProvidingRandomSpinePatientRecord();
        }
        patientSearchPage.clickSearchButtonByXpath(driver);
        patientSearchPage.clickPatientCard();
        // Check condition for different scenarios when referral submit button is displayed
        if (patientDetailsPage.addDetailsToNGISButtonList.size() > 0) {
            Debugger.println("Add Patient Details button shown");
            patientDetailsPage.addDetailsToNGISButton.click();
            Wait.forElementToBeDisplayed(driver, patientDetailsPage.successNotification);
            patientDetailsPage.clickStartReferralButton();
        } else if (patientDetailsPage.updateNGISRecordButtonList.size() > 0) {
            patientDetailsPage.clickStartReferralButton();
        } else if (patientDetailsPage.savePatientDetailsToNGISButtonList.size() > 0) {
            Debugger.println("Save Patient Details button shown");
            patientDetailsPage.clickSavePatientDetailsToNGISButton();
            patientDetailsPage.patientIsCreated();
            patientDetailsPage.clickStartNewReferralButton();
        }
        referralPage.checkThatReferralWasSuccessfullyCreated();
    }
}