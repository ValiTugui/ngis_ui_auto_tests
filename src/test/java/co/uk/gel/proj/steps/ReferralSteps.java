package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.lib.Actions;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class ReferralSteps extends Pages {


    public ReferralSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Then("^the referral page is displayed$")
    public void referralPageIsDisplayed() {
        boolean testResult = false;
        testResult = referralPage.checkThatReferralWasSuccessfullyCreated();
        Assert.assertTrue(testResult);
        testResult = referralPage.saveAndContinueButtonIsDisplayed();
        Assert.assertTrue(testResult);
        testResult = referralPage.clickSaveAndContinueButton();
        Assert.assertTrue(testResult);
    }

    @When("^the user navigates to the \"([^\"]*)\" stage$")
    public void navigateTOSpecificStage(String stage) {
        Debugger.println("Stage: " + stage + " Starting.");
        referralPage.navigateToStage(stage);
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord(stage," ")+"_Start");
        }
    }

    @And("the user clicks the Save and Continue button")
    public void theUserClicksTheSaveAndContinueButton() {
        boolean testResult = false;
        testResult = referralPage.clickSaveAndContinueButton();
        Assert.assertTrue(testResult);
    }

    @Given("a referral is created with the below details for an existing patient record type and associated tests in Test Order System online service")
    public void aReferralIsCreatedWithTheBelowDetailsForAnExistingPatientRecordTypeAndAssociatedTestsInTestOrderSystemOnlineService(List<String> attributeOfURL) throws IOException {
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String patientType = attributeOfURL.get(3);
        String diseaseType = attributeOfURL.get(4);
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);

        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            Assert.assertTrue(false);
        }
        if (!homePage.typeInSearchField(searchTerm)) {
            Assert.assertTrue(false);
        }
        if (!homePage.clickSearchIconFromSearchField()) {
            Assert.assertTrue(false);
        }
        homePage.closeCookiesBannerFromFooter();
        if (!homePage.selectFirstEntityFromResultList()) {
            Assert.assertTrue(false);
        }
        homePage.closeCookiesBannerFromFooter();
        if (!clinicalIndicationsTestSelect.clickStartTestOrderReferralButton()) {
            Assert.assertTrue(false);
        }
        if (!paperFormPage.clickSignInToTheOnlineServiceButton()) {
            Assert.assertTrue(false);
        }
        switchToURL(driver.getCurrentUrl());
        if (!patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected()) {
            Assert.assertTrue(false);
        }
        // utilising static NGIS test data for now. In future test framework will support api calls to get a random NGIS record
        if (diseaseType.equalsIgnoreCase("cancer") && patientType.equalsIgnoreCase("NGIS")) {
            if (!patientSearchPage.fillInNHSNumberAndDateOfBirthByProvidingNGISPatientOne()) {
                Assert.assertTrue(false);
            }
        } else if (diseaseType.equalsIgnoreCase("rare-disease") && patientType.equalsIgnoreCase("NGIS")) {
            if (!patientSearchPage.fillInNHSNumberAndDateOfBirthByProvidingNGISPatientTwo()) {
                Assert.assertTrue(false);
            }
        } else if (patientType.equalsIgnoreCase("SPINE")) {
            if (!patientSearchPage.fillInNHSNumberAndDateOfBirthByProvidingRandomSpinePatientRecord()) {
                Assert.assertTrue(false);
            }
        }
        if (!patientSearchPage.clickSearchButtonByXpath()) {
            Assert.assertTrue(false);
        }
        if (!patientSearchPage.clickPatientCard()) {
            Assert.assertTrue(false);
        }
        //New flow observed for an existing patient - Spine
        if (patientSearchPage.isNotificationErrorPresent()) {
            Debugger.println("Here yes...111111");
            if (!patientSearchPage.editPatientDetails()) {
                Assert.assertTrue(false);
            }
            if (!patientDetailsPage.editDropdownField(patientDetailsPage.ethnicityButton, "A - White - British")) {
                Assert.assertTrue(false);
            }
            if (!patientDetailsPage.clickOnSaveAndContinueButton()) {
                Assert.assertTrue(false);
            }
            if (!patientDetailsPage.clickStartNewReferralButton()) {
                Assert.assertTrue(false);
            }
        } else {
            Debugger.println("Here yes...");
            if (!patientDetailsPage.clickStartReferralButton()) {
                Assert.assertTrue(false);
            }
        }
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();

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

            NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
            String expectedFullName = newPatient.getLastName().toUpperCase() + ", " + newPatient.getFirstName() + " (" + newPatient.getTitle() + ")";

            Debugger.println("Expected full name = " + expectedFullName + ", Actual full name " + actualFullName);
            Assert.assertEquals(expectedFullName, actualFullName);

            String expectedDateOfBirth = newPatient.getDay() + "-" + TestUtils.convertMonthNumberToMonthForm(newPatient.getMonth()) + "-" + newPatient.getYear();
            Debugger.println("Expected DOB = " + expectedDateOfBirth + ", Actual DOB: " + actualFullDOB);
            Assert.assertTrue(actualFullDOB.contains(expectedDateOfBirth));

            Debugger.println("Expected Gender= " + newPatient.getGender() + ", Actual Gender: " + actualGender);
            Assert.assertEquals(newPatient.getGender(), actualGender);

            Debugger.println("Expected nhs no = " + newPatient.getNhsNumber() + ", Actual nhs no: " + actualNHSNumber);
            Assert.assertEquals(newPatient.getNhsNumber(), actualNHSNumber);

            Debugger.println("Expected patient ID = " + newPatient.getPatientID() + ", Actual Patient-Id: " + actualPatientId);
            Assert.assertNotNull(actualPatientId);

            Debugger.println("Expected Cid = " + newPatient.getClinicalIndication() + ", Actual Cid: " + actualCid);
            Assert.assertNotNull(actualCid);

            Debugger.println("Expected referralId = " + newPatient.getReferralID() + ", Actual referralId: " + actualReferralId);
            Assert.assertNotNull(actualReferralId);
        }
    }

    @Then("the {string} stage is selected")
    public void theStageIsSelected(String newStage) {
        boolean testResult = false;
        testResult = referralPage.stageIsSelected(newStage);
        Assert.assertTrue(testResult);
    }

    @And("the {string} stage is marked as Completed")
    public void theStageIsMarkedAsCompleted(String stage) {
        // deliberate 2 seconds wait is added to handle the slowness of UI on Jenkins run
        // Exception in Checking Stage Completion Status: org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page
        Debugger.println("Verifying completion of Package:" + stage);
        Wait.seconds(2);
        try {
            boolean testResult = referralPage.stageIsCompleted(stage);
            if (!testResult) {
                testResult = referralPage.stageIsCompleted(stage);
                if (!testResult) {
                    Debugger.println("Stage: " + stage + " NOT Completed.");
                } else {
                    Debugger.println("Stage: " + stage + " Completed.");
                }
            }
            if(AppConfig.snapshotRequired){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord(stage," ")+"_End");
            }
            Assert.assertTrue(testResult);
        } catch (Exception exp) {
            Debugger.println("Exception in verifying the stage completed status for :" + stage + ":" + exp);
        }
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
        String userType = "";
        if (attributeOfURL.size() > 6) {
            userType = attributeOfURL.get(6);//User Type
        }
        String patientType = "";
        if (attributeOfURL.size() > 7) {
            patientType = attributeOfURL.get(7);//Child or adult
        }
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        boolean testResult = false;
        testResult = homePage.waitUntilHomePageResultsContainerIsLoaded();
        if (!testResult) {
            Assert.assertTrue("Failed in waitUntilHomePageResultsContainerIsLoaded", false);
        }
        testResult = homePage.typeInSearchField(searchTerm);
        if (!testResult) {
            Assert.assertTrue("Failed in typeInSearchField", false);
        }

        testResult = homePage.clickSearchIconFromSearchField();
        if (!testResult) {
            Assert.assertTrue("Failed in clickSearchIconFromSearchField", false);
        }

        testResult = homePage.waitUntilHomePageResultsContainerIsLoaded();
        if (!testResult) {
            Assert.assertTrue("Failed in waitUntilHomePageResultsContainerIsLoaded", false);
        }

        homePage.closeCookiesBannerFromFooter();
        testResult = homePage.selectFirstEntityFromResultList();
        if (!testResult) {
            Assert.assertTrue("Failed in selectFirstEntityFromResultList", false);
        }

        homePage.closeCookiesBannerFromFooter();
        testResult = clinicalIndicationsTestSelect.clickStartTestOrderReferralButton();
        if (!testResult) {
            Assert.assertTrue("Failed in clickStartTestOrderReferralButton", false);
        }

        testResult = paperFormPage.clickSignInToTheOnlineServiceButton();
        if (!testResult) {
            Assert.assertTrue("Failed in clickSignInToTheOnlineServiceButton", false);
        }

        if (userType != null && !userType.isEmpty()) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        testResult = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        if (!testResult) {
            Assert.assertTrue("Failed in verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected", false);
        }

        if (patientType == null || patientType.isEmpty()) {
            Debugger.println("SEARCH USING NHS AND DOB...........");
            testResult = patientSearchPage.fillInNonExistingPatientDetailsUsingNHSNumberAndDOB();
            if (!testResult) {
                Assert.assertTrue("Failed in fillInNonExistingPatientDetailsUsingNHSNumberAndDOB", false);
            }
        } else {
            if (patientType.equalsIgnoreCase("Child")) {
                Debugger.println("SEARCH FORA CHILD...........");
                testResult = patientSearchPage.fillInNonExistingPatientDetailsForChildReferral();
                if (!testResult) {
                    Assert.assertTrue("Failed in fillInNonExistingPatientDetailsForChildReferral", false);
                }
            }
        }
        testResult = patientSearchPage.clickSearchButtonByXpath();
        if (!testResult) {
            Assert.assertTrue("Failed in clickSearchButtonByXpath", false);
        }

        String actualNoPatientFoundLabel = patientSearchPage.getPatientSearchNoResult();
        if (actualNoPatientFoundLabel == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals("No patient found", actualNoPatientFoundLabel);
        testResult = patientSearchPage.checkCreateNewPatientLinkDisplayed(createPatientHyperTextLink);
        if (!testResult) {
            Assert.assertTrue("Failed in checkCreateNewPatientLinkDisplayed", false);
        }

        testResult = patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
        if (!testResult) {
            Assert.assertTrue("Failed in clickCreateNewPatientLinkFromNoSearchResultsPage", false);
        }

        testResult = patientDetailsPage.newPatientPageIsDisplayed();
        if (!testResult) {
            Assert.assertTrue("Failed in newPatientPageIsDisplayed", false);
        }

        testResult = patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNumber);
        if (!testResult) {
            Assert.assertTrue("Failed in fillInAllFieldsNewPatientDetailsWithOutNhsNumber", false);
        }

        testResult = patientDetailsPage.clickOnCreateRecord();
        if (!testResult) {
            Assert.assertTrue("Failed in clickOnCreateRecord", false);
        }

        testResult = patientDetailsPage.patientIsCreated();
        if (!testResult) {
            Assert.assertTrue("Failed in patientIsCreated", false);
        }

        testResult = patientDetailsPage.clickStartReferralButton();
        if (!testResult) {
            Assert.assertTrue("Failed in clickStartReferralButton", false);
        }

        testResult = referralPage.checkThatReferralWasSuccessfullyCreated();
        if (!testResult) {
            Assert.assertTrue("Failed in checkThatReferralWasSuccessfullyCreated", false);
        }

        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
        testResult = referralPage.saveAndContinueButtonIsDisplayed();
        if (!testResult) {
            Assert.assertTrue("Failed in saveAndContinueButtonIsDisplayed", false);
        }

    }

    @Then("the user sees a prompt alert {string} after clicking {string} button and {string} it")
    public void theUserSeesAPromptAlertAfterClickingButtonAndIt(String partOfMessage, String browserInteraction, String acknowledgeAlertPopup) {
        String actualAlertMessage;
        if (browserInteraction.equals("Samples") || (browserInteraction.equals("back") || (browserInteraction.equals("add a Tumour") || (browserInteraction.equals("Not the right tumour"))))) {
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
        boolean testResult = false;
        testResult = referralPage.clickLogoutButton();
        Assert.assertTrue(testResult);
    }

    @Then("the user sees a warning message {string} on the page")
    public void theUserSeesAWarningMessageOnThePage(String expectedWarningText) {
        try {
            Wait.forAlertToBePresent(driver);
            Alert alertBox = driver.switchTo().alert();
            Wait.seconds(10);
            String actualWarningText = alertBox.getText();
            Assert.assertTrue(expectedWarningText.contains(actualWarningText));
            Actions.acceptAlert(driver);
            Wait.seconds(10);
            Debugger.println("URL info after accepting alert :: " + driver.getCurrentUrl());
        } catch (Exception exp) {
            Debugger.println("Exception in validating warning message: " + exp);
            SeleniumLib.takeAScreenShot("WarningMessage.jpg");
        }

    }

    @And("the {string} stage is marked as Mandatory To Do")
    public void theStageIsMarkedAsMandatoryToDo(String stage) {
        Assert.assertTrue(referralPage.stageIsMandatoryToDo(stage));
    }

    @And("Save and Continue button is displayed")
    public void saveAndContinueButtonIsDisplayed() {
        Assert.assertTrue("Save and Continue is meant to be displayed", referralPage.saveAndContinueButtonIsDisplayed());
    }

    @When("the user clicks on the Back link")
    public void theUserClicksOnTheBackLink() {
        boolean testResult = false;
        testResult = referralPage.clickOnTheBackLink();
        Assert.assertTrue(testResult);
    }

    @Given("a referral is created for a new patient without nhs number and associated tests in Test Order System online service")
    public void aReferralIsCreatedWithTheBelowDetailsForANewlyCreatedPatientRecord(List<String> attributeOfURL) throws IOException {
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String patientType = attributeOfURL.get(3);
        String diseaseType = attributeOfURL.get(4);
        String reasonForNoNHSNumber = attributeOfURL.get(5);
        String userType = null;
        if (attributeOfURL.size() > 6) {
            userType = attributeOfURL.get(6);
        }
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            Assert.assertTrue("Failure in waitUntilHomePageResultsContainerIsLoaded.", false);
        }
        if (!homePage.typeInSearchField(searchTerm)) {
            Assert.assertTrue("Failure in typeInSearchField", false);
        }
        if (!homePage.clickSearchIconFromSearchField()) {
            Assert.assertTrue("Failure in clickSearchIconFromSearchField", false);
        }
        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            Assert.assertTrue("Failure in waitUntilHomePageResultsContainerIsLoaded", false);
        }
        homePage.closeCookiesBannerFromFooter();
        if (!homePage.selectFirstEntityFromResultList()) {
            Assert.assertTrue("Failure in selectFirstEntityFromResultList", false);
        }
        homePage.closeCookiesBannerFromFooter();
        if (!clinicalIndicationsTestSelect.clickStartTestOrderReferralButton()) {
            Assert.assertTrue("Failure in clickStartTestOrderReferralButton", false);
        }
        if (!paperFormPage.clickSignInToTheOnlineServiceButton()) {
            Assert.assertTrue("Failure in clickSignInToTheOnlineServiceButton", false);
        }
        Debugger.println(" User Type : " + userType);
        if (userType != null) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        if (!referralPage.verifyThePageTitlePresence("Find your patient")) {
            Assert.assertTrue("Failure in verifyThePageTitlePresence:Find your patient", false);
        }
        if (!patientSearchPage.fillInNonExistingPatientDetailsUsingNHSNumberAndDOB()) {
            Assert.assertTrue("Failure in fillInNonExistingPatientDetailsUsingNHSNumberAndDOB", false);
        }
        if (!patientSearchPage.clickSearchButtonByXpath()) {
            Assert.assertTrue("Failure in clickSearchButtonByXpath", false);
        }
        String actualSearchResult = patientSearchPage.getPatientSearchNoResult();
        if (actualSearchResult == null) {
            Assert.assertTrue("Failure in getPatientSearchNoResult", false);
        }
        Assert.assertEquals("No patient found", actualSearchResult);
        if (!patientSearchPage.checkCreateNewPatientLinkDisplayed("create a new patient record")) {
            Assert.assertTrue("Failure in checkCreateNewPatientLinkDisplayed", false);
        }
        if (!patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage()) {
            Assert.assertTrue("Failure in clickCreateNewPatientLinkFromNoSearchResultsPage", false);
        }
        if (!patientDetailsPage.newPatientPageIsDisplayed()) {
            Assert.assertTrue("Failure in newPatientPageIsDisplayed", false);
        }
        if (!patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNumber)) {
            Assert.assertTrue("Failure in fillInAllFieldsNewPatientDetailsWithOutNhsNumber", false);
        }
        //patientDetailsPage.clickSavePatientDetailsToNGISButton();
        if (!patientDetailsPage.clickOnCreateRecord()) {
            Assert.assertTrue("Failure in clickOnCreateRecord", false);
        }
        if (!patientDetailsPage.patientIsCreated()) {
            Assert.assertTrue("Failure in patientIsCreated", false);
        }

        if (!patientDetailsPage.clickStartReferralButton()) {
            Assert.assertTrue("Failure in clickStartReferralButton", false);
        }
        if (!referralPage.checkThatReferralWasSuccessfullyCreated()) {
            Assert.assertTrue("Failure in checkThatReferralWasSuccessfullyCreated", false);
        }
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
        if (!referralPage.saveAndContinueButtonIsDisplayed()) {
            Assert.assertTrue("Failure in saveAndContinueButtonIsDisplayed", false);
        }
    }

    @And("the success notification is displayed {string}")
    public void theSuccessNotificationIsDisplayed(String notificationText) {
        String actualNotificationText = referralPage.successNotificationIsDisplayed();
        if (actualNotificationText == null) {
            Assert.assertTrue("Expected Notification not displayed", false);
        }
        Assert.assertEquals(notificationText, actualNotificationText);
    }

    @Then("the user is navigated to a page with title (.*)")
    public void theUserIsNavigatedToAPageWithTitleConfirmFamilyMemberDetails(String title) {
        boolean testResult = false;
        testResult = referralPage.verifyThePageTitlePresence(title);
        Assert.assertTrue(testResult);
    }

    //Added for user journey E2EUI-1800
    @When("the user submits the referral")
    public void theUserSubmitsTheReferral() {
        referralPage.submitReferral();
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("Referral"," ")+"_Submitting");
        }
    }

    @When("the user clicks the Cancel referral link")
    public void theUserClicksTheCancelReferralLink() {
        boolean testResult = false;
        testResult = referralPage.clicksOnCancelReferralLink();
        Assert.assertTrue(testResult);
    }

    @Then("the message should display as {string}")
    public void theMessageShouldDisplayAs(String revokeMessage) {
        boolean testResult = false;
        testResult = referralPage.verifyCancellationMessage(revokeMessage);
        Assert.assertTrue(testResult);
    }

    @And("the user submits the cancellation")
    public void theUserSubmitsTheCancellation() {
        boolean testResult = false;
        testResult = referralPage.submitCancellation();
        Assert.assertTrue(testResult);
    }

    @And("the user selects the cancellation reason {string} from the modal")
    public void theUserSelectsTheCancellationReasonFromTheModal(String cancellationReasonText) {
        boolean testResult = false;
        testResult = referralPage.selectCancellationReason(cancellationReasonText);
        Assert.assertTrue(testResult);
    }

    @Then("the referral is successfully {string} with reason {string}")
    public void theReferralIsSuccessfullyWithReason(String referralStatus, String reason) {
        Assert.assertTrue(referralPage.cancelReferralConfirmationIsDisplayed());
        Assert.assertTrue(referralPage.cancelReasonMatches(reason));
        Assert.assertTrue(referralPage.verifyTheReferralStatus(referralStatus));
    }

    @Given("a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service")
    public void aReferralIsCreatedByTheLoggedInUserWithTheBelowDetailsForANewlyCreatedPatientAndAssociatedTestsInTestOrderSystemOnlineService(List<String> attributeOfURL) {
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String patientNameWithSpecialCharacters = attributeOfURL.get(3);
        String createPatientHyperTextLink = attributeOfURL.get(4);
        String reasonForNoNHSNumber = attributeOfURL.get(5);
        String userType = null;
        if (attributeOfURL.size() == 7) {
            userType = attributeOfURL.get(6);
        }
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            Assert.assertTrue(false);
        }
        if (!homePage.typeInSearchField(searchTerm)) {
            Assert.assertTrue(false);
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("CI"," ")+"_Searching");
        }
        if (!homePage.clickSearchIconFromSearchField()) {
            Assert.assertTrue(false);
        }
        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            Assert.assertTrue(false);
        }
        homePage.closeCookiesBannerFromFooter();
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("CI"," ")+"_SearchResult");
        }
        if (!homePage.selectFirstEntityFromResultList()) {
            Assert.assertTrue(false);
        }
        homePage.closeCookiesBannerFromFooter();
        if (!clinicalIndicationsTestSelect.clickStartTestOrderReferralButton()) {
            Assert.assertTrue(false);
        }
        if (!paperFormPage.clickSignInToTheOnlineServiceButton()) {
            Assert.assertTrue(false);
        }
        //patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
        Debugger.println(" User Type : " + userType);
        if (userType != null) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        if (!patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected()) {
            Assert.assertTrue(false);
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("Patient"," ")+"_Searching");
        }
        if (!patientSearchPage.fillInNonExistingPatientDetailsForAdultReferral()) {
            Assert.assertTrue(false);
        }
        if (!patientSearchPage.clickSearchButtonByXpath()) {
            Assert.assertTrue(false);
        }
        String actualNoPatientFoundLabel = patientSearchPage.getPatientSearchNoResult();
        if (actualNoPatientFoundLabel == null) {
            Assert.assertTrue(false);
        }
        Assert.assertEquals("No patient found", actualNoPatientFoundLabel);
        if (!patientSearchPage.checkCreateNewPatientLinkDisplayed(createPatientHyperTextLink)) {
            Assert.assertTrue(false);
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("Patient"," ")+"_Creating");
        }
        if (!patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage()) {
            Assert.assertTrue(false);
        }
        if (!patientDetailsPage.newPatientPageIsDisplayed()) {
            Assert.assertTrue(false);
        }
        // assert userType != null;  // if user type is declared, use declared user name, else use default normal user
        if (userType != null) {
            if (userType.equalsIgnoreCase("GEL_NORMAL_USER")) {
                if (!patientDetailsPage.fillInAllFieldsNewPatientDetailsExceptNHSNumber(reasonForNoNHSNumber)) {
                    Assert.assertTrue(false);
                }
            } else if (userType.equalsIgnoreCase("GEL_SUPER_USER") && patientNameWithSpecialCharacters != null) {
                if (!patientDetailsPage.fillInAllFieldsNewPatientDetailsWithNHSNumber(patientNameWithSpecialCharacters)) {
                    Assert.assertTrue(false);
                }
            }
        } else {
            if (!patientDetailsPage.fillInAllFieldsNewPatientDetailsExceptNHSNumber(reasonForNoNHSNumber)) {
                Assert.assertTrue(false);
            }
        }
        if (!patientDetailsPage.clickOnCreateRecord()) {
            Assert.assertTrue(false);
        }
        if (!patientDetailsPage.patientIsCreated()) {
            Assert.assertTrue(false);
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("Starting"," ")+"_Referral");
        }
        if (!patientDetailsPage.clickStartReferralButton()) {
            Assert.assertTrue(false);
        }
        if (!referralPage.checkThatReferralWasSuccessfullyCreated()) {
            Assert.assertTrue(false);
        }
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
        if (!referralPage.saveAndContinueButtonIsDisplayed()) {
            Assert.assertTrue(false);
        }
        // Store the Clinical Indication info into the NewPatient test context
        Debugger.println("PATIENT CI " + referralPage.getPatientClinicalIndication());
        Debugger.println("PATIENT Referral Id " + referralPage.getPatientReferralId());
        Debugger.println("PATIENT NGIS Id " + referralPage.getPatientNGISId());

        PatientDetailsPage.newPatient.setClinicalIndication(referralPage.getPatientClinicalIndication());
        PatientDetailsPage.newPatient.setReferralHumanReadableID(referralPage.getPatientReferralId());
        patientDetailsPage.newPatient.setPatientHumanReadableID(referralPage.getPatientNGISId());
        //Debugger.println("SNAP REQD: "+AppConfig.snapshotRequired);
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"ReferralCreated.jpg");
        }
    }

    @And("the referral status is set to {string}")
    public void theReferralStatusIsSetTo(String expectedReferralStatus) {
        boolean testResult = false;
        testResult = referralPage.verifyReferralButtonStatus(expectedReferralStatus);
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("Referral"," ")+"_Submitted");
        }
        Assert.assertTrue(testResult);
        referralPage.saveReferralID();
    }

    @Then("the submission confirmation message {string} is displayed")
    public void theSubmissionConfirmationMessageIsDisplayed(String expectedMessage) {
        String actualMessage = referralPage.getSubmissionConfirmationMessageIsDisplayed();
        //Debugger.println("SUBMISSION REFERRAL: "+driver.getCurrentUrl());
        if (actualMessage == null) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @And("the new patient gender {string} is displayed on the referral banner")
    public void theNewPatientGenderIsDisplayedOnTheReferralBanner(String expectedGender) {
        String actualGender = referralPage.referralHeaderGender.getText();
        Debugger.println("actual gender " + actualGender);
        Debugger.println("expected gender " + expectedGender);
        Assert.assertEquals(expectedGender, actualGender);
    }

    @And("the user navigates back to patient existing referral page")
    public void theUserNavigatesBackToPatientExistingReferralPage(List<String> attributeOfURL) {

        String existingReferralID = patientDetailsPage.newPatient.getReferralHumanReadableID();
        Debugger.println("existingReferralID:" + existingReferralID);
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String referralFullUrl = TestUtils.getReferralURL(baseURL, existingReferralID, confirmationPage);
        Debugger.println("referralFullUrl:" + referralFullUrl);
        NavigateTo(referralFullUrl, confirmationPage);
        Debugger.println("Navigated to:" + driver.getCurrentUrl());
        Assert.assertTrue(referralPage.saveAndContinueButtonIsDisplayed());

    }

    @Given("the user search and select clinical indication test for the patient through to Test Order System online service patient search")
    public void theUserSearchAndSelectClinicalIndicationTestForThePatientThroughToTestOrderSystemOnlineServicePatientSearch(List<String> attributeOfURL) {

        boolean eachElementIsLoaded;
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String diseaseType = attributeOfURL.get(3);
        String userType = null;
        if (attributeOfURL.size() == 5) {
            userType = attributeOfURL.get(4);
        }
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            Assert.assertTrue(false);
        }
        if (!homePage.typeInSearchField(searchTerm)) {
            Assert.assertTrue(false);
        }
        if (!homePage.clickSearchIconFromSearchField()) {
            Assert.assertTrue(false);
        }
        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            Assert.assertTrue(false);
        }
        homePage.closeCookiesBannerFromFooter();
        if (!homePage.selectFirstEntityFromResultList()) {
            Assert.assertTrue(false);
        }
        homePage.closeCookiesBannerFromFooter();
        if (!clinicalIndicationsTestSelect.clickStartTestOrderReferralButton()) {
            Assert.assertTrue(false);
        }
        if (!paperFormPage.clickSignInToTheOnlineServiceButton()) {
            Assert.assertTrue(false);
        }
        Debugger.println(" User Type : " + userType);
        if (userType != null) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        if (!patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected()) {
            Assert.assertTrue(false);
        }
    }

    //Added on January 14-2020 - All the referral creation steps in RD package changed to this step.
    //This method will create referral with existing patient, if exists OR create new patient referral, if not exists
    //New referral creation with specific details - as various test cases needs the referral to created with specific condition
    @Given("a new patient referral is created with associated tests in Test Order System online service")
    public void aNewPatientReferralIsCreatedWithAssociatedTestsInTOSyatem(List<String> attributeOfURL) throws IOException {

        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String userType = attributeOfURL.get(3);
        String referralDetails = attributeOfURL.get(4);

        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        Assert.assertTrue(homePage.searchForTheTest(searchTerm));
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("CI"," ")+"_Search");
        }
        boolean stepResult = false;
        stepResult = clinicalIndicationsTestSelect.clickStartTestOrderReferralButton();
        Assert.assertTrue(stepResult);
        if (!paperFormPage.clickSignInToTheOnlineServiceButton()) {
            Assert.assertTrue(false);
        }
        Debugger.println("User Type : " + userType);
        if (userType == null || userType.isEmpty()) {
            userType = "GEL_NORMAL_USER";//Default Login as NORMAL_USER
        }
        switchToURL(driver.getCurrentUrl(), userType);
        stepResult = referralPage.verifyThePageTitlePresence("Find your patient");
        Assert.assertTrue(stepResult);
        //Create NGIS Patient with the given Details and the use for referral Creation
        NGISPatientModel searchPatient = new NGISPatientModel();
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(referralDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        String paramValue = "";
        for (String key : paramsKey) {
            paramValue = paramNameValue.get(key).trim();
            switch (key) {
                case "NHSNumber": {
                    if (paramValue.startsWith("NA")) {
                        searchPatient.setNHS_NUMBER(RandomDataCreator.generateRandomNHSNumber());
                        //Debugger.println("NHS Number IS: "+searchPatient.getNHS_NUMBER());
                        searchPatient.setNO_NHS_REASON(paramValue.replaceAll("NA-", ""));
                    } else if (paramValue.equalsIgnoreCase("NGIS")) {
                        searchPatient.setNHS_NUMBER(RandomDataCreator.generateRandomNHSNumber());
                        searchPatient.setNO_NHS_REASON("NGIS");
                    } else {
                        searchPatient.setNHS_NUMBER(paramValue);
                    }
                    break;
                }
                case "DOB": {
                    searchPatient.setDATE_OF_BIRTH(paramValue);
                    break;
                }
                case "FirstName": {
                    searchPatient.setFIRST_NAME(paramValue);
                    break;
                }
                case "LastName": {
                    searchPatient.setLAST_NAME(paramValue);
                    break;
                }
                case "Gender": {
                    searchPatient.setGENDER(paramValue);
                    break;
                }
                case "Postcode": {
                    searchPatient.setPOST_CODE(paramValue);
                    break;
                }
                case "Title": {
                    searchPatient.setTITLE(paramValue);
                    break;
                }
                case "Ethnicity": {
                    searchPatient.setETHNICITY(paramValue);
                    break;
                }
            }//switch
        }//for
        String searchResult = patientSearchPage.searchPatientReferral(searchPatient);
        if (searchResult.equalsIgnoreCase("No patient found")) {
            //Create New Patient and Add as Referral
            if (!patientSearchPage.checkCreateNewPatientLinkDisplayed("create a new patient record")) {
                Assert.assertTrue(false);
            }
            if (!patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage()) {
                Assert.assertTrue(false);
            }
            if(AppConfig.snapshotRequired){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("RDReferral"," ")+"_Creating");
            }
            if (!patientDetailsPage.createNewPatientReferral(searchPatient)) {
                Assert.assertTrue(false);
            }
            if (!referralPage.checkThatReferralWasSuccessfullyCreated()) {
                Assert.assertTrue(false);
            }
            if(AppConfig.snapshotRequired){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord("RDReferral"," ")+"_Created");
            }
            if (!referralPage.saveAndContinueButtonIsDisplayed()) {
                Assert.assertTrue(false);
            }
        } else if (searchResult.equalsIgnoreCase("1 patient record found")) {
            //Existing Patient
            if (!patientSearchPage.clickPatientCard()) {
                Assert.assertTrue(false);
            }
            if (!patientDetailsPage.clickStartNewReferralButton()) {
                Assert.assertTrue(false);
            }

            if (!referralPage.checkThatReferralWasSuccessfullyCreated()) {
                Assert.assertTrue(false);
            }
            boolean toDoListDisplayed = referralPage.checkThatToDoListSuccessfullyLoaded();
            if (!toDoListDisplayed) {
                SeleniumLib.takeAScreenShot("ToDoList.jpg");
                //Observed undefined attached in the URL sometime....This is to verify the URL the moment
                Debugger.println("ToDoListNotLeaded:URL:" + driver.getCurrentUrl());
                Assert.assertFalse("ToDoList in Referral Page is not loaded even after the waiting time..", true);
            }
        } else {
            Debugger.println("Search Result: " + searchResult);
            Assert.assertTrue(false);
        }
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
    }

    @And("^the mandatory fields shown with the symbol in red color$")
    public void theMandatoryFieldsShownWithSymbolInRedColor(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 1; i < messageDetails.size(); i++) {
            testResult = referralPage.verifyMandatoryFieldDisplaySymbol(messageDetails.get(i).get(0), messageDetails.get(i).get(1), messageDetails.get(i).get(2), messageDetails.get(i).get(3));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the blank mandatory field labels highlighted in red color")
    public void theBlankMandatoryFieldsHighlightedInRedColor(DataTable fields) {
        boolean testResult = false;
        List<List<String>> fieldDetails = fields.asLists();
        for (int i = 1; i < fieldDetails.size(); i++) {
            testResult = referralPage.verifyBlankMandatoryFieldLabelColor(fieldDetails.get(i).get(0), fieldDetails.get(i).get(1));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("^the user will see error messages highlighted in red colour$")
    public void theUserWillSeeErrorMessagesInRedColor(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 1; i < messageDetails.size(); i++) {
            testResult = referralPage.verifyTheErrorMessageDisplay(messageDetails.get(i).get(0), messageDetails.get(i).get(1));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the DOB and age in the referral header bar are displayed in the expected format")
    public void theDOBAndAgeInTheReferralHeaderBarAreDisplayedInTheExpectedFormat() {

        NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
        String expectedDateOfBirth = newPatient.getDay() + "-" + newPatient.getMonth() + "-" + newPatient.getYear();
        Debugger.println("Expected DOB = " + expectedDateOfBirth);

        String expectedCalculatedAge = TestUtils.getAgeInYearsAndMonth(expectedDateOfBirth);
        Debugger.println("Expected Age in Years and Months calculated from DOB = " + expectedCalculatedAge);

        // To remove the month from the calculated age (NNy Nm) => (NNy)
        expectedCalculatedAge = Objects.requireNonNull(expectedCalculatedAge).substring(0, expectedCalculatedAge.indexOf('y') + 1) + ")";
        Debugger.println("Expected Age in Years ONLY calculated from DOB = " + expectedCalculatedAge);

        String expectedDateOfBirthFormat = newPatient.getDay() + "-" + TestUtils.convertMonthNumberToMonthForm(newPatient.getMonth()) + "-" + newPatient.getYear();
        Debugger.println("Expected DOB Format = " + expectedDateOfBirthFormat);

        String expectedBornFormat = expectedDateOfBirthFormat + " " + expectedCalculatedAge;
        Debugger.println("expectedDOBAndAgeBornFormat " + expectedBornFormat);

        String actualBornInReferralHeader = Actions.getText(referralPage.referralHeaderBorn);
        Debugger.println("actualDOBAndAgeBornFormat " + actualBornInReferralHeader);
        Assert.assertEquals(expectedBornFormat, actualBornInReferralHeader);
    }

    @Then("the user is successfully logged out")
    public void theUserIsSuccessfullyLoggedOut() {
        String actualSourcePageSourceTitle = referralPage.logoutSuccessMessageIsDisplayed();
        String expectedPageSourceTitle = "Sign out";
        Debugger.println("Expected :" + expectedPageSourceTitle + " : " + "Actual " + actualSourcePageSourceTitle);
        if (actualSourcePageSourceTitle != null) {
            if (!expectedPageSourceTitle.equalsIgnoreCase(actualSourcePageSourceTitle)) {
                SeleniumLib.takeAScreenShot("LogoutMessage.jpg");
                Assert.assertFalse("Logout Message not displayed as expected:" + expectedPageSourceTitle, true);
            }
        } else {
            SeleniumLib.takeAScreenShot("LogoutMessage.jpg");
            Assert.assertFalse("Logout Message not displayed.", true);
        }
    }

    @And("the page url address contains the directory-path web-page {string}")
    public void thePageUrlAddressContainsTheDirectoryPathWebPage(String directoryPath) {
        boolean flag = false;
        flag = referralPage.verifyTheCurrentURLContainsTheDirectoryPathPage(directoryPath);
        Assert.assertTrue(flag);
    }


    @And("the Genomic Medicine Service logo {string} is displayed in the header of Test Ordering")
    public void theGenomicMedicineServiceLogoIsDisplayedInTheHeaderOfTestOrdering(String expectedGenomicsEngLogo) {
        String actualGenomicsEngLogo = referralPage.getGenomicMedicineServiceLogoInHeader();
        Debugger.println("actual Genomics Logo " + actualGenomicsEngLogo);
        Debugger.println("expected Genomics Logo " + expectedGenomicsEngLogo);
        Assert.assertEquals(expectedGenomicsEngLogo, actualGenomicsEngLogo);
    }

    @And("the username {string} is displayed in the header of Test Ordering")
    public void theUsernameIsDisplayedInTheHeaderOfTestOrdering(String userType) {
        String expectedLoginUserName = referralPage.getExpectedUserNameFromLoginEmailAddress(userType);
        String actualLoginUserName = referralPage.getActualLoginUserName();
        Debugger.println("Expected user-login full name: " + expectedLoginUserName);
        Debugger.println("Actual user-login full name: " + actualLoginUserName);
        Assert.assertEquals(expectedLoginUserName, actualLoginUserName);
    }

    @And("the logout {string} text is displayed in the header of Test Ordering")
    public void theLogoutTextIsDisplayedInTheHeaderOfTestOrdering(String expectedLogoutText) {
        String actualLogoutText = referralPage.getActualLogoutText();
        Debugger.println("Actual logout text: " + actualLogoutText);
        Debugger.println("Expected logout text: " + expectedLogoutText);
        Assert.assertEquals(expectedLogoutText, actualLogoutText);
    }

    @Then("the NHS logo is displayed in the header of Test Ordering")
    public void theNHSLogoIsDisplayedInTheHeaderOfTestOrdering() {
        boolean flag = false;
        flag = referralPage.nhsEnglandLogoIsDisplayedInHeader();
        Assert.assertTrue(flag);
    }

    @And("the NHS logo is displayed in the footer of Test Ordering")
    public void theNHSLogoIsDisplayedInTheFooterOfTestOrdering() {
        boolean flag = false;
        flag = referralPage.nhsEnglandLogoIsDisplayedInFooter();
        Assert.assertTrue(flag);
    }

    @And("the Genomics England logo is displayed in the footer of Test Ordering")
    public void theGenomicsEnglandLogoIsDisplayedInTheFooterOfTestOrdering() {
        boolean flag = false;
        flag = referralPage.genomicsEnglandLogIsDisplayedInFooter();
        Assert.assertTrue(flag);
    }

    @And("the Report an issue or provide feedback text link is displayed in the footer of Test Ordering")
    public void theReportAnIssueOrProvideFeedbackTextLinkIsDisplayedInTheFooterOfTestOrdering() {
        boolean flag = false;
        flag = referralPage.serviceDeskReportAndIssueIsDisplayedInTheFooter();
        Assert.assertTrue(flag);
    }

    @And("the Privacy Policy text link is displayed in the footer of Test Ordering")
    public void thePrivacyPolicyTextLinkIsDisplayedInTheFooterOfTestOrdering() {
        boolean flag = false;
        flag = referralPage.privacyPolicyIsDisplayedInTheFooter();
        Assert.assertTrue(flag);
    }

    @And("the copyright text is displayed in the footer of Test Ordering")
    public void theCopyrightTextIsDisplayedInTheFooterOfTestOrdering() {
        boolean flag = false;
        flag = referralPage.copyrightTextIsDisplayedInTheFooter();
        Assert.assertTrue(flag);
    }

    @And("the referral submit button is not enabled")
    public void theReferralSubmitButtonIsNotEnabled() {
        Assert.assertTrue(referralPage.checkSubmitReferralIsDisabled());
    }

    @Then("the user should see a new popup dialog with title {string}")
    public void theUserShouldSeeANewPageWith(String titleMessage) {
        boolean testResult = false;
        testResult = referralPage.verifyTheSubmitDialogTitle(titleMessage);
        Assert.assertTrue(testResult);
    }

    @And("the user should see the referral submit button as (.*)")
    public void theUserShouldBeAbleToSeeReferralSubmitButton(String expectedStatus) {
        boolean testResult = false;
        testResult = referralPage.referralSubmitButtonStatus("#eaebee");
        if (expectedStatus.equals("enabled")) {
            Assert.assertFalse(testResult);
        } else {
            Assert.assertTrue(testResult);
        }
    }

    @Then("the user verifies that the revoke message doesn't overlap any other element")
    public void theUserVerifiesThatTheRevokeMessageDoesnTOverlapAnyOtherElement() {
        boolean testResult = false;
        testResult = referralPage.verifyTheCancellationSuccessMsgDoesNotOverlapWithOtherElements();
        Assert.assertTrue(testResult);
    }

    @And("the NHS display format as {string}")
    public void theUserVerifiesTheNHSFormatAs(String nhsFormat) {
        boolean testResult = false;
        testResult = referralPage.verifyNHSDisplayFormat(nhsFormat);
        Assert.assertTrue(testResult);
    }

    @And("the NHS display format as {string} in patient card")
    public void theUserVerifiesTheNHSFormatInPatientCard(String nhsFormat) {
        boolean testResult = false;
        testResult = referralPage.verifyNHSDisplayFormatInPatientCard(nhsFormat);
        Assert.assertTrue(testResult);
    }

    @And("the user retrieve the referral HumanReadable-ID from the referral page url")
    public void theUserRetrieveTheReferralHumanReadableIDFromTheReferralPageUrl() {
        // Set the retrieved ReferralHumanID from URl into setReferralID
        NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
        String currentURl = driver.getCurrentUrl();
        String referralIdRegex = "^[r0-9]+$";
        String expectedReferralID = TestUtils.getTheExpectedCurrentHumanReadableID(currentURl, referralIdRegex);
        newPatient.setReferralID(expectedReferralID);
        Debugger.println("Expected ReferralID " + expectedReferralID);
    }

    @And("the user sees the patient details on the referral header of each referral component page {string}")
    public void theUserSeesThePatientDetailsOnTheReferralHeaderOfEachReferralComponentPage(String titlePage, DataTable dataTable) {
        Assert.assertEquals(titlePage, referralPage.getTheCurrentPageTitle());
        List<String> expectedList = dataTable.asList(String.class);
        //Debugger.println("List : " + expectedList);

        String actualFullName = referralPage.referralHeaderPatientName.getText();
        String actualFullDOB = referralPage.referralHeaderBorn.getText();
        String actualGender = referralPage.referralHeaderGender.getText();
        String actualNHSNumber = referralPage.referralHeaderNhsNo.getText();
        String actualPatientId = referralPage.getPatientNGISId();
        String actualCid = referralPage.getPatientClinicalIndication();
        String actualReferralId = referralPage.getPatientReferralId();

        NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
        String expectedFullName = newPatient.getLastName().toUpperCase() + ", " + newPatient.getFirstName() + " (" + newPatient.getTitle() + ")";

        //Debugger.println("Expected full name = " + expectedFullName + ", Actual full name " + actualFullName);
        Assert.assertEquals(expectedFullName, actualFullName);

        String expectedDateOfBirth = newPatient.getDay() + "-" + TestUtils.convertMonthNumberToMonthForm(newPatient.getMonth()) + "-" + newPatient.getYear();
        //Debugger.println("Expected DOB = " + expectedDateOfBirth + ", Actual DOB: " + actualFullDOB);
        Assert.assertTrue(actualFullDOB.contains(expectedDateOfBirth));

        Debugger.println("Expected Gender= " + newPatient.getGender() + ", Actual Gender: " + actualGender);
        Assert.assertEquals(newPatient.getGender(), actualGender);

        //Debugger.println("Expected nhs no = " + newPatient.getNhsNumber() + ", Actual nhs no: " +  actualNHSNumber);
        Assert.assertEquals(newPatient.getNhsNumber(), actualNHSNumber);

        //Debugger.println("Expected patient ID = " + newPatient.getPatientID() + ", Actual Patient-Id: " + actualPatientId);
        Assert.assertEquals(newPatient.getPatientID(), actualPatientId);

        //Debugger.println("Expected Cid = " + newPatient.getClinicalIndication() + ", Actual Cid: " + actualCid);
        Assert.assertNotNull(actualCid);

        //Debugger.println("Expected referralId = " + newPatient.getReferralID() + ", Actual referralId: " + actualReferralId);
        Assert.assertEquals(newPatient.getReferralID(), actualReferralId);
    }

    @And("the user sees a dialog box with a title {string}")
    public void theUserSeesADialogBoxWithATitle(String dialogBoxTitlePage) {
        boolean testResult = false;
        testResult = referralPage.verifyThePageTitlePresence(dialogBoxTitlePage);
        Debugger.println("test-result flag for verifying page title is: " + testResult);
        Assert.assertTrue(testResult);

        boolean flag = false;
        flag = referralPage.mandatoryStageDialogBoxIsDisplayed();
        Assert.assertTrue(flag);
    }

    @And("the user sees a list of outstanding mandatory stages to be completed in the dialog box")
    public void theUserSeesAListOfOutstandingMandatoryStagesToBeCompletedInTheDialogBox(DataTable dataTable) {
        List<Map<String, String>> expectedList = dataTable.asMaps(String.class, String.class);
        List<String> actualMandatoryStages = referralPage.getTheListOfMandatoryStagesOnDialogBox();
        for (int i = 0; i < expectedList.size(); i++) {
            //Debugger.println("Expected mandatory stages: " + i + " : " + expectedList.get(i).get("MandatoryStagesToComplete"));
            //Debugger.println("Actual mandatory stages: " + i + " : " + actualMandatoryStages.get(i));
            Assert.assertEquals(expectedList.get(i).get("MandatoryStagesToComplete"), actualMandatoryStages.get(i));
        }
    }

    @And("the user clicks on the mandatory stage {string} in the dialog box")
    public void theUserClicksOnTheMandatoryStageInTheDialogBox(String mandatoryStageTextLink) {
        boolean testResult = false;
        testResult = referralPage.clickOnTheMandatoryStageTextLinkInDialogBox(mandatoryStageTextLink);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see same referral id in the global banner and the url")
    public void theUserShouldBeAbleToSeeSameReferralIdInTheGlobalBannerAndTheUrl() {
        boolean testResult = false;
        testResult = referralPage.verifyPatientReferralIdInUrl();
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see the active stage {string} in to-do list")
    public void theUserShouldAbleToSeeTheActiveStageInToDoList(String activeStage) {
        boolean testResult = false;
        testResult = referralPage.stageIsSelected(activeStage);
        Assert.assertTrue(testResult);
    }

    @Then("the below stages marked as incompleted")
    public void theUserSeesTheNotCompletedStages(DataTable incompletedStages) {
        boolean testResult;
        List<List<String>> stages = incompletedStages.asLists();
        for (int i = 0; i < stages.size(); i++) {
            testResult = referralPage.stageIsCompleted(stages.get(i).get(0));
            if (testResult) {
                Debugger.println("Stage: " + stages.get(i).get(0) + " expected to be incomplete, but complete.");
                SeleniumLib.takeAScreenShot("IncompletedStage.jpg");
                Assert.assertFalse(testResult);
            }
            Assert.assertFalse(testResult);
        }
    }

    @Then("the below stages marked as completed")
    public void theUserSeesAllTheCompletedStages(DataTable completedStages) {
        boolean testResult;
        List<List<String>> stages = completedStages.asLists();
        for (int i = 0; i < stages.size(); i++) {
            testResult = referralPage.stageIsCompleted(stages.get(i).get(0));
            if (!testResult) {
                Debugger.println("Stage: " + stages.get(i).get(0) + " expected to be complete, but not.");
                SeleniumLib.takeAScreenShot("completedStage.jpg");
                Assert.assertTrue(testResult);
            }
            Assert.assertTrue(testResult);
        }
    }

    @And("the user should be able to close the pop up dialog box")
    public void theUserShouldBeAbleToCloseThePopUpDialogBox() {
        boolean testResult = false;
        testResult = referralPage.closeMandatoryStagePopUp();
        Assert.assertTrue(testResult);
    }

    @And("the user clicks outside of the modal")
    public void theUserShouldBeAbleToClickAnywhereOutSideTheDialogBox() {
        boolean testResult = false;
        testResult = referralPage.clicksOutsideModalDialog();
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to click on incomplete {string} section")
    public void theUserShouldBeAbleToClickOnIncompleteSection(String expStage) {
        boolean testResult = false;
        testResult = referralPage.clickOnIncompleteStageInDialogBox(expStage);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see a cancel referral link {string}")
    public void theUserShouldBeAbleToSeeACancelReferralLink(String cancelReferral) {
        boolean testResult = false;
        testResult = referralPage.verifyPresenceOfCancelReferralLink();
        if (cancelReferral.equals("present")) {
            Assert.assertTrue(testResult);
        } else {
            Assert.assertFalse(testResult);
        }
    }

    @Then("the user verifies the Ngis Id and Referral Id from the referral banner")
    public void theUserVerifiesTheNgisIdAndReferralIdFromTheReferralBanner() {
        boolean testResult = false;
        testResult = referralPage.verifyNgisIdAndReferralId();
        Assert.assertTrue(testResult);
    }

    @Then("user copies text from NgisId and verifies it with actual content")
    public void userCopiesTextFromNgisIdAndVerifiesItWithActualContent() {
        boolean testResult = false;
        testResult = referralPage.verifyTextFromReferralHeaderPatientNgisId();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the patient referral banner at the top")
    public void theUserShouldBeAbleToSeeThePatientBanner() {
        boolean testResult = false;
        testResult = referralPage.readTheReferralBannerLocation();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see the patient banner at same location")
    public void theUserShouldBeAbleToSeeThePatientBannerAtSameLocation() {
        boolean testResult = false;
        testResult = referralPage.verifyTheBannerLocationAtSameLocation();
        Assert.assertTrue(testResult);
    }

    @Then("the user sees the color of feedback link as NHS Blue (.*)")
    public void theUserSeesTheColorOfFeedbackLinkAsNHSBlue(String colorValue) {
        boolean testResult = false;
        testResult = referralPage.verifyFeedbackLinkFontColor(colorValue);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the color of privacy policy link as NHS Blue (.*)")
    public void theUserSeesTheColorOfPrivacyPolicyLinkAsNHSBlue(String colorValue) {
        boolean testResult = false;
        testResult = referralPage.verifyPrivacyPolicyLinkFontColor(colorValue);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be navigated to Microsoft login {string} page")
    public void theUserShouldBeNavigatedToMicrosoftLoginPage(String loginPageUrl) {
        boolean testResult = false;
        testResult = referralPage.verifyMicrosoftLoginPage(loginPageUrl);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see page is not loading")
    public void theUserShouldSeePageIsNotLoading() {
        boolean testResult = false;
        testResult = referralPage.verifyPageLoadingWithInvalidReferralURL();
        Assert.assertTrue(testResult);
    }
}