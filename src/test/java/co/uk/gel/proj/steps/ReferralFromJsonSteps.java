package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.models.ReferralFromJson;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.gel.models.participant.avro.Referral;
import org.junit.Assert;

import java.util.List;

public class ReferralFromJsonSteps extends Pages {
    ReferralFromJson referralFromJson = new ReferralFromJson();

    public ReferralFromJsonSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Given("^the json file (.*) with referral information is available in the specified location$")
    public void applicationIsUpAndRunning(String fileName) throws Throwable {
        String stepResult = referralFromJson.verifyFilePresence(fileName);
        if(!stepResult.equalsIgnoreCase("Success")){
            Assert.fail(stepResult);
        }
    }

    @When("^the referral object is created successfully from the json file (.*)$")
    public void theDetailsProvidedInTheJsonFileIsCorrect(String jsonFile) {
        String stepResult = referralFromJson.readReferralDetailsFromJson(jsonFile);
        if(!stepResult.equalsIgnoreCase("Success")){
            Assert.fail(stepResult);
        }
    }
    //Referral From JSON
    @When("the referral is created with details from Json provided")
    public void theReferralIsCreatedWithDetailsFromJsonProvided(List<String> attributeOfURL) {
        Referral referralObject = referralFromJson.getReferralObject();
        if(referralObject == null){
            Assert.fail("Referral Object is not initialized.");
        }

        String baseURL = attributeOfURL.get(0);
        String confirmationPage = attributeOfURL.get(1);
        String createPatientHyperTextLink = attributeOfURL.get(2);
        String reasonForNoNHSNumber = attributeOfURL.get(3);
        String userType = attributeOfURL.get(4);
        String searchTerm = referralObject.getClinicalIndication().getClinicalIndicationCode();
        if(userType == null) {
            NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage);
        }else{
            NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage,userType);
        }
        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_HomePageContainer.jpg");
            Assert.fail("Home page result containers not loaded properly.");
        }
        if (!homePage.typeInSearchField(searchTerm)) {
            Assert.fail("Could not search for CI Term.");
        }
        if (!homePage.clickSearchIconFromSearchField()) {
            Assert.fail("Could not search the CI Term.");
        }
        if (!homePage.waitUntilHomePageResultsContainerIsLoaded()) {
            Assert.fail("CI Term search results are not displayed.");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CISearch");
        }
        homePage.closeCookiesBannerFromFooter();
        if (!homePage.selectFirstEntityFromResultList()) {
            Assert.fail("Could not select the first entitry from CI Search Result.");
        }
        homePage.closeCookiesBannerFromFooter();
        if (!clinicalIndicationsTestSelect.clickStartTestOrderReferralButton()) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_startReferral.jpg");
            Assert.fail("Could not click on StartTestOrderReferral Button");
        }
        if (!paperFormPage.clickSignInToTheOnlineServiceButton()) {
            Assert.fail("Could not click on SignInToTheOnlineServiceButton");
        }
        //patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
        Debugger.println(" User Type : " + userType);
        if (userType != null) {
            switchToURL(driver.getCurrentUrl(), userType);
        } else {
            switchToURL(driver.getCurrentUrl());
        }
        if (!patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected()) {
            Assert.fail("Patient Search Page not displayed properly.");
        }
        if (!patientSearchPage.searchParticipantFromJson(RandomDataCreator.generateRandomNHSNumber(),"01","01",referralObject.getCancerParticipant().getYearOfBirth().toString())) {
            Assert.fail("Could not fill the patient search information.");
        }
        if (!patientSearchPage.clickSearchButtonByXpath()) {
            Assert.fail("Could not click search button in Patient Search Page");
        }
        String actualNoPatientFoundLabel = patientSearchPage.getPatientSearchNoResult();
        if (actualNoPatientFoundLabel == null) {
            Assert.fail("Patient Search Result:"+actualNoPatientFoundLabel);
        }
        Assert.assertEquals("No patient found", actualNoPatientFoundLabel);
        if (!patientSearchPage.checkCreateNewPatientLinkDisplayed(createPatientHyperTextLink)) {
            Assert.fail(createPatientHyperTextLink+" not displayed.");
        }
        if (!patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage()) {
            Assert.fail("Could not Click on create new patient link.");
        }
        if (!patientDetailsPage.newPatientPageIsDisplayed()) {
            Assert.fail("New Patient creation page not displayed properly.");
        }
        // assert userType != null;  // if user type is declared, use declared user name, else use default normal user
        Debugger.println("USER TYPE: "+userType);
        if (userType != null) {
            if (userType.equalsIgnoreCase("GEL_NORMAL_USER")) {
                if (!patientDetailsPage.fillInPatientDetailsFromJson(reasonForNoNHSNumber,referralObject)) {
                    Assert.assertTrue(false);
                }
            } else if (userType.equalsIgnoreCase("GEL_SUPER_USER")) {
                if (!patientDetailsPage.fillInAllFieldsNewPatientDetailsWithNHSNumber("")) {
                    Assert.assertTrue(false);
                }
            }
        } else {
            if (!patientDetailsPage.fillInAllFieldsNewPatientDetailsExceptNHSNumber(reasonForNoNHSNumber)) {
                Assert.assertTrue(false);
            }
        }
        if (!patientDetailsPage.clickOnCreateRecord()) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PCCreate.jpg");
            Assert.fail("Could not click on Create Record.");
        }
        if (!patientDetailsPage.patientIsCreated()) {
            Assert.fail("Could not verify Patient Created Message.");
        }
        if (!patientDetailsPage.clickStartReferralButton()) {
            Assert.fail("Could not start referral button.");
        }
        if (!referralPage.checkThatReferralWasSuccessfullyCreated()) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_RefCreateMsg.jpg");
            Assert.fail("Could not verify the successful creation of referral.");
        }
        //To log the ReferralI in the Log.
        referralPage.logTheReferralId();
        if (!referralPage.saveAndContinueButtonIsDisplayed()) {
            Assert.fail("Save and Continue button not displayed after Referral Creation.");
        }
        // Store the Clinical Indication info into the NewPatient test context
        PatientDetailsPage.newPatient.setClinicalIndication(referralPage.getPatientClinicalIndication());
        PatientDetailsPage.newPatient.setReferralHumanReadableID(referralPage.getPatientReferralId());
        PatientDetailsPage.newPatient.setPatientHumanReadableID(referralPage.getPatientNGISId());
    }

    @Then("the referral should be created via TOMS using json provided information and submitted successfully")
    public void theReferralsShouldBeCreatedAndSubmittedSuccessfullyViaTOMS() {
        Referral referralObject = referralFromJson.getReferralObject();
        if(referralObject == null){
            Assert.fail("Referral Object is not initialized.");
        }
        //Requesting Organisation
        fillStageRequestingOrganisation(referralObject);
        //Test Package
        fillStageTestPackage(referralObject);
        //Responsible Clinician
        //Tumours
        //Samples
        //Notes
        //Patient Choice
        //Print Forms
        //Submit Referral
    }
    private void fillStageTestPackage(Referral referralObject){
        String stageName = "Test package";
        boolean testResult = referralPage.navigateToStage(stageName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord(stageName," ")+".jpg");
            Assert.fail("Could not navigate to stage:"+stageName);
        }
    }
    private void fillStageRequestingOrganisation(Referral referralObject){
        String stageName = "Requesting organisation";
        boolean testResult = referralPage.navigateToStage(stageName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+TestUtils.removeAWord(stageName," ")+".jpg");
            Assert.fail("Could not navigate to stage:"+stageName);
        }
        testResult = paperFormPage.fillInSpecificKeywordInSearchField(referralObject.getRequester().getOrganisationName());
        if(!testResult){
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not search for Order entity.");
        }
        testResult = paperFormPage.checkThatEntitySuggestionsAreDisplayed();
        if(!testResult){
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("No suggestions listed for the order entity.");
        }
        testResult = paperFormPage.selectRandomEntityFromSuggestionsList();
        if(!testResult){
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not select requesting organization.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if(!testResult){
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not save requesting organization.");
        }
    }
}//end
