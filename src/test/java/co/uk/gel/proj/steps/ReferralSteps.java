package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.lib.Actions;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

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
        clinicalIndicationsTestSelect.clickStartReferralButton();
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

    //Added by STAG to create referral with the given patient details than taking from Test Data Provider
    @Given("a referral is created with the below details for the given existing patient record type and associated tests in Test Order System online service")
    public void aReferralIsCreatedWithTheBelowDetailsForTheGivenExistingPatientRecordTypeAndAssociatedTestsInTestOrderSystemOnlineService(List<String> attributeOfURL) throws IOException {
        boolean toDoListDisplayed;
        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String searchTerm = attributeOfURL.get(2);
        String patientType = attributeOfURL.get(3);
        String diseaseType = attributeOfURL.get(4);
        String referralDetails = attributeOfURL.get(5);
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
        clinicalIndicationsTestSelect.clickStartReferralButton();
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
        //Wait.seconds();
        if (patientType.equalsIgnoreCase("NGIS") || patientType.equalsIgnoreCase("SPINE")) {
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
            searchPageLoaded = patientSearchPage.fillInNHSNumberAndDateOfBirth(ngisPatient);
            if(!searchPageLoaded){
                Assert.assertFalse("Could not Search Patient with NHS and DOB.",true);
            }
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
            patientDetailsPage.clickStartReferralButton();
        } else if (patientDetailsPage.savePatientDetailsToNGISButtonList.size() > 0) {
            Debugger.println("Save Patient Details button shown");
            patientDetailsPage.clickSavePatientDetailsToNGISButton();
            patientDetailsPage.patientIsCreated();
            patientDetailsPage.clickStartNewReferralButton();
        }
        toDoListDisplayed = referralPage.checkThatToDoListSuccessfullyLoaded();
        if(!toDoListDisplayed){
            SeleniumLib.takeAScreenShot("ToDoList.jpg");
            Assert.assertFalse("ToDoList in Referral Page is not loaded even after the waiting time..",true);
        }
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
        clinicalIndicationsTestSelect.clickStartReferralButton();
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
        clinicalIndicationsTestSelect.clickStartReferralButton();
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
        // assert userType != null;  // if user type is declared, use declared user name, else use default normal user
        if (userType != null) {
            if (userType.equalsIgnoreCase("GEL_NORMAL_USER")) {
                patientDetailsPage.fillInAllFieldsNewPatientDetailsExceptNHSNumber(reasonForNoNHSNumber); //check DOB is pre-filled
            }else if (userType.equalsIgnoreCase("GEL_SUPER_USER")  && patientNameWithSpecialCharacters != null) {
                patientDetailsPage.fillInAllFieldsNewPatientDetailsWithNHSNumber(patientNameWithSpecialCharacters);
            }
        } else {
            patientDetailsPage.fillInAllFieldsNewPatientDetailsExceptNHSNumber(reasonForNoNHSNumber);
        }
        patientDetailsPage.clickSavePatientDetailsToNGISButton();
        patientDetailsPage.patientIsCreated();
        patientDetailsPage.clickStartNewReferralButton();
        referralPage.checkThatReferralWasSuccessfullyCreated();
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
        String actualReferralStatus = referralPage.getReferralStatus();
        Assert.assertTrue(actualReferralStatus.contains(expectedReferralStatus));
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
        clinicalIndicationsTestSelect.clickStartReferralButton();
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
}