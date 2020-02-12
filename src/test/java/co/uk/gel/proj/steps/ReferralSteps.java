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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
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
        Debugger.println("Stage: "+stage+" Starting.");
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
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
        homePage.closeCookiesBannerFromFooter();
        clinicalIndicationsTestSelect.clickStartTestOrderReferralButton();
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
        if (patientDetailsPage.addDetailsToNGISButtonList.size() > 0) {  // AddDetailsToNGISButton is shown when adding SPINE data
            Debugger.println("Add Patient Details button shown");
            Wait.seconds(1);

            //https://jira.extge.co.uk/browse/E2EUI-2499 - Ethnicity is now a mandatory field, hence Ethnicity field - for SPINE data need to be updated with a value in Patient Details
            if (Wait.isElementDisplayed(driver, patientDetailsPage.ethnicityButton, 15)) {
                String ethnicityFieldCurrentValue = Actions.getText(patientDetailsPage.ethnicityButton);
                if (ethnicityFieldCurrentValue.equalsIgnoreCase("Select..."))
                {
                    patientDetailsPage.addPatientEthnicity("A - White - British");
                }
            }
            Debugger.println("New Ethnicity " + Actions.getText(patientDetailsPage.ethnicityButton));
            patientDetailsPage.clickAddDetailsToNGISButton();
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
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
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

            NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
            String expectedFullName = newPatient.getLastName().toUpperCase() + ", " + newPatient.getFirstName() + " (" + newPatient.getTitle() +  ")";

            Debugger.println("Expected full name = " + expectedFullName + ", Actual full name " + actualFullName);
            Assert.assertEquals(expectedFullName, actualFullName);

            String expectedDateOfBirth = newPatient.getDay() + "-" + TestUtils.convertMonthNumberToMonthForm(newPatient.getMonth()) + "-" + newPatient.getYear();
            Debugger.println("Expected DOB = " + expectedDateOfBirth + ", Actual DOB: " + actualFullDOB);
            Assert.assertTrue(actualFullDOB.contains(expectedDateOfBirth));

            Debugger.println("Expected Gender= " + newPatient.getGender() + ", Actual Gender: " + actualGender);
            Assert.assertEquals(newPatient.getGender(), actualGender);

            Debugger.println("Expected nhs no = " + newPatient.getNhsNumber() + ", Actual nhs no: " +  actualNHSNumber);
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
        Assert.assertTrue(referralPage.stageIsSelected(newStage));
    }

    @And("the {string} stage is marked as Completed")
    public void theStageIsMarkedAsCompleted(String stage) {
        // deliberate 2 seconds wait is added to handle the slowness of UI on Jenkins run
        // Exception in Checking Stage Completion Status: org.openqa.selenium.StaleElementReferenceException: stale element reference: element is not attached to the page
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
            Assert.assertTrue(testResult);
        }catch(Exception exp){
            Debugger.println("Exception in verifying the stage completed status for :"+stage+":"+exp);
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
        if(attributeOfURL.size() > 6) {
            userType = attributeOfURL.get(6);//User Type
        }
        String patientType = "";
        if(attributeOfURL.size() > 7) {
            patientType = attributeOfURL.get(7);//Child or adult
        }
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.typeInSearchField(searchTerm);
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
        homePage.closeCookiesBannerFromFooter();
        clinicalIndicationsTestSelect.clickStartTestOrderReferralButton();
        paperFormPage.clickSignInToTheOnlineServiceButton();
        //patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
        if(userType != null && !userType.isEmpty()) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        //switchToURL(driver.getCurrentUrl());
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        Assert.assertTrue(eachElementIsLoaded);
        if(patientType == null || patientType.isEmpty()) {
            Debugger.println("SEARCH USING NHS AND DOB...........");
            patientSearchPage.fillInNonExistingPatientDetailsUsingNHSNumberAndDOB();
        }else{
            if(patientType.equalsIgnoreCase("Child")) {
                Debugger.println("SEARCH FORA CHILD...........");
                patientSearchPage.fillInNonExistingPatientDetailsForChildReferral();
            }
        }
        patientSearchPage.clickSearchButtonByXpath(driver);
        patientSearchPage.getPatientSearchNoResult();
        String actualNoPatientFoundLabel = patientSearchPage.getPatientSearchNoResult();
        Assert.assertEquals("No patient found", actualNoPatientFoundLabel);
        patientSearchPage.checkCreateNewPatientLinkDisplayed(createPatientHyperTextLink);
        //driver.navigate().to("https://test-ordering.e2e.ngis.io/test-order/new-patient");  //Temp
        patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
        patientDetailsPage.newPatientPageIsDisplayed();
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNumber); //check DOB is pre-filled
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
        patientDetailsPage.patientIsCreated();
        patientDetailsPage.clickStartNewReferralButton();
        referralPage.checkThatReferralWasSuccessfullyCreated();
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
        referralPage.saveAndContinueButtonIsDisplayed();
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

    @When("the user clicks on the Back link")
    public void theUserClicksOnTheBackLink() {
       referralPage.clickOnTheBackLink();
    }
    @Given("a referral is created for a new patient without nhs number and associated tests in Test Order System online service")
    public void aReferralIsCreatedWithTheBelowDetailsForANewlyCreatedPatientRecord(List<String> attributeOfURL) throws IOException {
        boolean toDoListDisplayed;
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String patientType = attributeOfURL.get(3);
        String diseaseType = attributeOfURL.get(4);
        String reasonForNoNHSNumber = attributeOfURL.get(5);
        String userType = null;
        if(attributeOfURL.size() > 6){
            userType = attributeOfURL.get(6);
        }
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.typeInSearchField(searchTerm);
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
        homePage.closeCookiesBannerFromFooter();
        clinicalIndicationsTestSelect.clickStartTestOrderReferralButton();
        paperFormPage.clickSignInToTheOnlineServiceButton();
        Debugger.println(" User Type : " + userType);
        if(userType != null) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        boolean searchPageLoaded = referralPage.verifyThePageTitlePresence("Find your patient");
        if(!searchPageLoaded){
            Debugger.println("Search Page Could not load Properly:");
            Assert.assertFalse("Search Page not loaded successfully.",true);
        }
        patientSearchPage.fillInNonExistingPatientDetailsUsingNHSNumberAndDOB();
        patientSearchPage.clickSearchButtonByXpath(driver);
        String actualSearchResult = patientSearchPage.getPatientSearchNoResult();
        Assert.assertEquals("No patient found", actualSearchResult);
        patientSearchPage.checkCreateNewPatientLinkDisplayed("create a new patient record");
        patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
        patientDetailsPage.newPatientPageIsDisplayed();
        patientDetailsPage.fillInAllFieldsNewPatientDetailsWithOutNhsNumber(reasonForNoNHSNumber); //check DOB is pre-filled
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
        patientDetailsPage.patientIsCreated();
        patientDetailsPage.clickStartNewReferralButton();
        referralPage.checkThatReferralWasSuccessfullyCreated();
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
        referralPage.saveAndContinueButtonIsDisplayed();
    }
    @And("the success notification is displayed {string}")
    public void theSuccessNotificationIsDisplayed(String notificationText) {
        String actualNotificationText = referralPage.successNotificationIsDisplayed();
        Assert.assertEquals(notificationText,actualNotificationText);
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
    }
    @When("the user clicks the Cancel referral link")
    public void theUserClicksTheCancelReferralLink() {
        referralPage.clicksOnCancelReferralLink();
    }
    
    @Then("the message should display as {string}")
    public void theMessageShouldDisplayAs(String revokeMessage) {
        boolean testResult = false;
        testResult = referralPage.verifyCancellationMessage(revokeMessage);
        Assert.assertTrue(testResult);
    }
    @And("the user submits the cancellation")
    public void theUserSubmitsTheCancellation() {
        referralPage.submitCancellation();
    }

    @And("the user selects the cancellation reason {string} from the modal")
    public void theUserSelectsTheCancellationReasonFromTheModal(String cancellationReasonText) {
        referralPage.selectCancellationReason(cancellationReasonText);
    }

    @Then("the referral is successfully {string} with reason {string}")
    public void theReferralIsSuccessfullyWithReason(String referralStatus, String reason) {
        Assert.assertTrue(referralPage.cancelReferralConfirmationIsDisplayed());
        Assert.assertTrue(referralPage.cancelReasonMatches(reason));
        Assert.assertTrue(referralPage.verifyTheReferralStatus(referralStatus));
    }

    @Given("a referral is created by the logged in user with the below details for a newly created patient and associated tests in Test Order System online service")
    public void aReferralIsCreatedByTheLoggedInUserWithTheBelowDetailsForANewlyCreatedPatientAndAssociatedTestsInTestOrderSystemOnlineService(List<String> attributeOfURL) {
        boolean eachElementIsLoaded;
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String patientNameWithSpecialCharacters = attributeOfURL.get(3);
        String createPatientHyperTextLink = attributeOfURL.get(4);
        String reasonForNoNHSNumber = attributeOfURL.get(5);
        String userType = null;
        if(attributeOfURL.size() == 7){
            userType = attributeOfURL.get(6);
        }
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.typeInSearchField(searchTerm);
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
        homePage.closeCookiesBannerFromFooter();
        clinicalIndicationsTestSelect.clickStartTestOrderReferralButton();
        paperFormPage.clickSignInToTheOnlineServiceButton();
        //patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
        Debugger.println(" User Type : " + userType);
        if(userType != null) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        Assert.assertTrue(eachElementIsLoaded);
        patientSearchPage.fillInNonExistingPatientDetailsForAdultReferral();
        patientSearchPage.clickSearchButtonByXpath(driver);
        patientSearchPage.getPatientSearchNoResult();
        String actualNoPatientFoundLabel = patientSearchPage.getPatientSearchNoResult();
        Assert.assertEquals("No patient found", actualNoPatientFoundLabel);
        patientSearchPage.checkCreateNewPatientLinkDisplayed(createPatientHyperTextLink);
        //driver.navigate().to("https://test-ordering.e2e.ngis.io/test-order/new-patient");  //Temp
        patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
        patientDetailsPage.newPatientPageIsDisplayed();
        boolean flag = false;
        // assert userType != null;  // if user type is declared, use declared user name, else use default normal user
        if (userType != null) {
            if (userType.equalsIgnoreCase("GEL_NORMAL_USER")) {
                patientDetailsPage.fillInAllFieldsNewPatientDetailsExceptNHSNumber(reasonForNoNHSNumber); //check DOB is pre-filled
                //Ensure all the fields are correctly populated without any error shown on patient details page
                flag = patientDetailsPage.verifyTheElementsOnAddNewPatientPageNormalUserFlow();

            }else if (userType.equalsIgnoreCase("GEL_SUPER_USER")  && patientNameWithSpecialCharacters != null) {
                patientDetailsPage.fillInAllFieldsNewPatientDetailsWithNHSNumber(patientNameWithSpecialCharacters);
                //Ensure all the fields are correctly populated without any error shown on patient details page
                flag = patientDetailsPage.verifyTheElementsOnAddNewPatientPageSuperUserFlow();
            }
        } else {
            patientDetailsPage.fillInAllFieldsNewPatientDetailsExceptNHSNumber(reasonForNoNHSNumber);
            flag = patientDetailsPage.verifyTheElementsOnAddNewPatientPageNormalUserFlow();
        }
        if(!flag){
            // Navigate to top of page
            Actions.scrollToTop(driver);
            SeleniumLib.takeAScreenShot("PatientDetailsPage.jpg");
            Assert.assertTrue(false);
        }
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
        patientDetailsPage.patientIsCreated();
        patientDetailsPage.clickStartNewReferralButton();
        referralPage.checkThatReferralWasSuccessfullyCreated();
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
        referralPage.saveAndContinueButtonIsDisplayed();
        // Store the Clinical Indication info into the NewPatient test context
        Debugger.println("PATIENT CI " + referralPage.getPatientClinicalIndication());
        Debugger.println("PATIENT Referral Id " + referralPage.getPatientReferralId());
        Debugger.println("PATIENT NGIS Id " + referralPage.getPatientNGISId());

        PatientDetailsPage.newPatient.setClinicalIndication(referralPage.getPatientClinicalIndication());
        PatientDetailsPage.newPatient.setReferralHumanReadableID(referralPage.getPatientReferralId());
        patientDetailsPage.newPatient.setPatientHumanReadableID(referralPage.getPatientNGISId());
    }

    @When("the user clicks the Save and Continue button on the {string}")
    public void theUserClicksTheSaveAndContinueButtonOnThe(String stage) {
        referralPage.clickSaveAndContinueButtonOnThePatientChoiceComponent();

    }

    @And("the referral status is set to {string}")
    public void theReferralStatusIsSetTo(String expectedReferralStatus) {
        boolean testResult  = false;
        testResult = referralPage.verifyReferralButtonStatus(expectedReferralStatus);
        Assert.assertTrue(testResult);

    }

    @Then("the submission confirmation message {string} is displayed")
    public void theSubmissionConfirmationMessageIsDisplayed(String expectedMessage) {
        String actualMessage = referralPage.getSubmissionConfirmationMessageIsDisplayed();
        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @And("the new patient gender {string} is displayed on the referral banner")
    public void theNewPatientGenderIsDisplayedOnTheReferralBanner(String expectedGender) {
        String actualGender = referralPage.referralHeaderGender.getText();
        Debugger.println("actual gender " + actualGender);
        Debugger.println("expected gender " + expectedGender);
        Assert.assertEquals(expectedGender,actualGender);
    }

    @And("the user navigates back to patient existing referral page")
    public void theUserNavigatesBackToPatientExistingReferralPage(List<String> attributeOfURL) {

        String existingReferralID = patientDetailsPage.newPatient.getReferralHumanReadableID();
        Debugger.println("existingReferralID " + existingReferralID);
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String referralFullUrl = TestUtils.getReferralURL(baseURL,existingReferralID,confirmationPage);
        Debugger.println("referralFullUrl :" + referralFullUrl);
        NavigateTo(referralFullUrl, confirmationPage);
        referralPage.saveAndContinueButtonIsDisplayed();

    }

    @Given("the user search and select clinical indication test for the patient through to Test Order System online service patient search")
    public void theUserSearchAndSelectClinicalIndicationTestForThePatientThroughToTestOrderSystemOnlineServicePatientSearch(List<String> attributeOfURL) {

        boolean eachElementIsLoaded;
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String diseaseType = attributeOfURL.get(3);
        String userType = null;
        if(attributeOfURL.size() == 5){
            userType = attributeOfURL.get(4);
        }
        NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.typeInSearchField(searchTerm);
        homePage.clickSearchIconFromSearchField();
        homePage.waitUntilHomePageResultsContainerIsLoaded();
        homePage.closeCookiesBannerFromFooter();
        homePage.selectFirstEntityFromResultList();
        homePage.closeCookiesBannerFromFooter();
        clinicalIndicationsTestSelect.clickStartTestOrderReferralButton();
        paperFormPage.clickSignInToTheOnlineServiceButton();
        Debugger.println(" User Type : " + userType);
        if(userType != null) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        Assert.assertTrue(eachElementIsLoaded);
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

        clinicalIndicationsTestSelect.clickStartTestOrderReferralButton();
        paperFormPage.clickSignInToTheOnlineServiceButton();
        Debugger.println("User Type : " + userType);
        if(userType == null || userType.isEmpty()) {
            userType = "GEL_NORMAL_USER";//Default Login as NORMAL_USER
        }
        switchToURL(driver.getCurrentUrl(), userType);
        boolean searchPageLoaded = referralPage.verifyThePageTitlePresence("Find your patient");
        if(!searchPageLoaded){
            Debugger.println("Search Page Could not load Properly:");
            Assert.assertFalse("Search Page not loaded successfully.",true);
        }
        //Create NGIS Patient with the given Details and the use for referral Creation
        NGISPatientModel searchPatient = new NGISPatientModel();
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(referralDetails);
        Set<String> paramsKey = paramNameValue.keySet();
        String paramValue = "";
        for (String key : paramsKey) {
            paramValue = paramNameValue.get(key).trim();
            switch (key) {
                case "NHSNumber": {
                    if(paramValue.startsWith("NA")) {
                        searchPatient.setNHS_NUMBER(RandomDataCreator.generateRandomNHSNumber());
                        //Debugger.println("NHS Number IS: "+searchPatient.getNHS_NUMBER());
                        searchPatient.setNO_NHS_REASON(paramValue.replaceAll("NA-",""));
                    }else{
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
        if(searchResult.equalsIgnoreCase("No patient found")){
            //Create New Patient and Add as Referral
            patientSearchPage.checkCreateNewPatientLinkDisplayed("create a new patient record");
            patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
            Assert.assertTrue(patientDetailsPage.createNewPatientReferral(searchPatient));
            referralPage.checkThatReferralWasSuccessfullyCreated();
            referralPage.saveAndContinueButtonIsDisplayed();
        }else if(searchResult.equalsIgnoreCase("1 patient record found")){
            //Existing Patient
            patientSearchPage.clickPatientCard();
            Assert.assertTrue(patientDetailsPage.startReferral());
            boolean toDoListDisplayed = referralPage.checkThatToDoListSuccessfullyLoaded();
            if(!toDoListDisplayed){
                SeleniumLib.takeAScreenShot("ToDoList.jpg");
                Assert.assertFalse("ToDoList in Referral Page is not loaded even after the waiting time..",true);
            }
        }else{
            Debugger.println("Search Result: "+searchResult);
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
            testResult = referralPage.verifyMandatoryFieldDisplaySymbol(messageDetails.get(i).get(0),messageDetails.get(i).get(1),messageDetails.get(i).get(2),messageDetails.get(i).get(3));
            if(!testResult){
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
            testResult = referralPage.verifyTheErrorMessageDisplay(messageDetails.get(i).get(0),messageDetails.get(i).get(1));
            if(!testResult){
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
        expectedCalculatedAge = Objects.requireNonNull(expectedCalculatedAge).substring(0, expectedCalculatedAge.indexOf('y')+1)+")";
        Debugger.println("Expected Age in Years ONLY calculated from DOB = " + expectedCalculatedAge);

        String expectedDateOfBirthFormat = newPatient.getDay() + "-" + TestUtils.convertMonthNumberToMonthForm(newPatient.getMonth()) + "-" + newPatient.getYear();
        Debugger.println("Expected DOB Format = " + expectedDateOfBirthFormat);

        String expectedBornFormat = expectedDateOfBirthFormat + " " + expectedCalculatedAge;
        Debugger.println("expectedDOBAndAgeBornFormat " + expectedBornFormat);

        String actualBornInReferralHeader = Actions.getText(referralPage.referralHeaderBorn);
        Debugger.println("actualDOBAndAgeBornFormat " + actualBornInReferralHeader);
        Assert.assertEquals(expectedBornFormat,actualBornInReferralHeader);
    }

    @Then("the user is successfully logged out")
    public void theUserIsSuccessfullyLoggedOut() {
        String actualSourcePageSourceTitle = referralPage.logoutSuccessMessageIsDisplayed();
        String expectedPageSourceTitle = "Sign out";
        Debugger.println("Expected :" + expectedPageSourceTitle + " : " + "Actual "  + actualSourcePageSourceTitle);
        Assert.assertEquals(expectedPageSourceTitle, actualSourcePageSourceTitle);
    }

    @And("the page url address contains the directory-path web-page {string}")
    public void thePageUrlAddressContainsTheDirectoryPathWebPage(String directoryPath) {
        boolean flag = false;
        flag = referralPage.verifyTheCurrentURLContainsTheDirectoryPathPage(directoryPath);
        Assert.assertTrue(flag);
    }
}