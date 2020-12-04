package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.ReferralFromJson;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.gel.models.participant.avro.Date;
import org.gel.models.participant.avro.GermlineSample;
import org.gel.models.participant.avro.Referral;
import org.gel.models.participant.avro.TumourSample;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
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
        fillStageResponsibleClinician(referralObject);
        //Tumours
        fillStageTumours(referralObject);
        //Samples
        fillStageSamples(referralObject);
        //Notes
        fillStageNotes(referralObject);
        //Patient Choice
        fillStagePatientChoice(referralObject);
        //Print Forms
        fillStagePrintForms(referralObject);
        //Submit Referral
        verifyAndSubmitReferral();
    }

    private void fillStageRequestingOrganisation(Referral referralObject) {
        String stageName = "Requesting organisation";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = paperFormPage.fillInSpecificKeywordInSearchField(referralObject.getRequester().getOrganisationName());
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not search for Order entity.");
        }
        testResult = paperFormPage.checkThatEntitySuggestionsAreDisplayed();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("No suggestions listed for the order entity.");
        }
        testResult = paperFormPage.selectRandomEntityFromSuggestionsList();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not select requesting organization.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_RequestingOrg.jpg");
            Assert.fail("Could not save requesting organization.");
        }
    }

    private void fillStageTestPackage(Referral referralObject) {
        String stageName = "Test package";
        String numberOfTests = String.valueOf(referralObject.getReferralTests().size());
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
//        Selecting "Routine" priority as default option
//        String testReferralUrgencyInfo= "Routine";
//        if (testReferralUrgencyInfo.contains("Urgent")) {
//            testResult = testPackagePage.clickUrgentPriority();
//        } else {
        testResult = testPackagePage.clickRoutinePriority();
//        }
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TestPriority.jpg");
            Assert.fail("Test Package: Routine priority could not select.");
        }
        //Observed failures in Jenkins run, looks like it is too fast, so provided a wait.
        Wait.seconds(5);
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_TestPackage.jpg");
            Assert.fail("Could not save Test package.");
        }
        testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_TestPackage.jpg");
            Assert.fail("Could not navigate to Test package after saving priority.");
        }
        testResult = testPackagePage.verifyTheTestsList(numberOfTests);
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_TestPackage.jpg");
            Assert.fail("Could not verify the selected number of Tests in Test package stage.");
        }
    }

    private void fillStageResponsibleClinician(Referral referralObject) {
        String stageName = "Responsible clinician";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = referralPage.verifyThePageTitlePresence("Add clinician information");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Add clinician information not present.");
        }
        testResult = responsibleClinicianPage.fillInClinicianFormFields();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_ResponsibleClinician.jpg");
            Assert.fail("Could not fill Clinical Form Fields.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_ResponsibleClinician.jpg");
            Assert.fail("Could not save Responsible Clinician information.");
        }
    }

    private void fillStageTumours(Referral referralObject) {
        String stageName = "Tumours";

        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = tumoursPage.navigateToAddTumourPageIfOnEditTumourPage();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourNavigate.jpg");
            Assert.fail("Could not navigate to Tumour Page.");
        }
        int numOfTumours = referralObject.getCancerParticipant().getTumours().size();

        for (int i = 0; i < numOfTumours; i++) {
            if (tumoursPage.fillInTumourDescription() == null) {
                Assert.assertTrue(false);
            }
            Date tumourDiagnosisDate = referralObject.getCancerParticipant().getTumours().get(i).getTumourDiagnosisDate();
            testResult = tumoursPage.fillInDateOfDiagnosis(String.valueOf(tumourDiagnosisDate.getDay()),String.valueOf(tumourDiagnosisDate.getMonth()),String.valueOf(tumourDiagnosisDate.getYear()));
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDOD.jpg");
                Assert.fail("Could not fill Tumour Page Date Of Diagnosis.");
            }

            String tumourType = String.valueOf(referralObject.getCancerParticipant().getTumours().get(i).getTumourType());
            tumourType=tumourType.replace("_"," ");
            char firstChar=tumourType.charAt(0);
            int length=tumourType.length();
            String tumourTypeWithoutFirstChar=tumourType.substring(1,length);
            String newTumourType=firstChar+tumourTypeWithoutFirstChar.toLowerCase();
            Debugger.println("The lower case tumour type is: "+newTumourType);
            String tumour = tumoursPage.selectTumourType(newTumourType);
            if (tumour == null) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourType.jpg");
                Assert.fail("Could not fill tumour type.");
            }
            String specimenLabId=referralObject.getCancerParticipant().getTumours().get(i).getTumourLocalId();
            if (tumoursPage.fillInSpecimenID(specimenLabId) == null) {
                Assert.fail("Could not fill Histopathology or SIHMDS Lab ID.");
            }
            Wait.seconds(5);//Observed timeout in next step, so introducing a wait of 5 seconds.
            testResult = referralPage.clickSaveAndContinueButton();
            if (!testResult) {
                SeleniumLib.takeAScreenShot("Ref_Tumours.jpg");
                Assert.fail("Could not save Tumours information.");
            }
            testResult = tumoursPage.selectTumourFirstPresentationOrOccurrenceValue("Recurrence");
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourFirstPresentation.jpg");
                Assert.fail("Could not select tumour first presentation");
            }
//            testResult = tumoursPage.answerTumourDiagnosisQuestions("test");
            tumoursPage.answerTumourDiagnosisQuestionsBasedOnTumourType(newTumourType,"test");
//            if (!testResult) {
//                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourDiagnosis.jpg");
//                Assert.fail("Could not select tumour diagnosis SnomedCT");
//            }
            testResult = referralPage.clickSaveAndContinueButton();
            if (!testResult) {
                SeleniumLib.takeAScreenShot("Ref_Tumours.jpg");
                Assert.fail("Could not save Tumours information.");
            }
        }

        int numberOfTumours = tumoursPage.getTheNumbersOfTumoursDisplayedInLandingPage();
        if (numberOfTumours < 1) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NumTumours.jpg");
            Assert.fail("Numbers of Tumours displayed should be 1 or greater than 1");
        }
        testResult = tumoursPage.tumourIsNotHighlighted();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourHighlight.jpg");
            Assert.fail("Tumors are not highlighted");
        }

        testResult = tumoursPage.warningMessageIsNotDisplayed();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourWarning.jpg");
            Assert.fail("Warning messages displayed, but not expected");
        }
        //need to check if required to verify as per step - the new tumour is not highlighted
        String actualNotificationText = referralPage.successNotificationIsDisplayed();
        if (actualNotificationText == null) {
            Assert.assertTrue("Expected Notification not displayed", false);
        }
        Assert.assertEquals("Tumour added", actualNotificationText);
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_Tumours.jpg");
            Assert.fail("Could not save Tumours information.");
        }
    }

    private void fillStageSamples(Referral referralObject) {
        String stageName = "Samples";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = referralPage.verifyThePageTitlePresence("Manage samples");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Manage Samples not present.");
        }

        String sampleType="";
        String sampleState="";
        String labSampleID="";
        int germlineSampleSize=referralObject.getReferralTests().get(0).getGermlineSamples().size();
        int tumourSampleSize=referralObject.getReferralTests().get(0).getTumourSamples().size();
        for(int i=0;i<germlineSampleSize;i++){
            Debugger.println("Selecting Germline sample....");
            GermlineSample germlineSample = referralObject.getReferralTests().get(0).getGermlineSamples().get(i);
            sampleType = germlineSample.getSampleType();
            String updatedSampleType = convertJsonDataUpperCaseToLowerCase(sampleType);
            sampleState = germlineSample.getSampleState();
            String updatedSampleState= sampleStateNameConversion(sampleState);
            if(updatedSampleState==null){
                SeleniumLib.takeAScreenShot("Ref_SamplesStage.jpg");
                Assert.fail("Could not get sample state information for: "+sampleState);
            }
            labSampleID = String.valueOf(germlineSample.getLabSampleId());
            //method to complete all the steps of adding a sample
            addSample(updatedSampleType, updatedSampleState, labSampleID);
        }
        for(int j=0;j<tumourSampleSize;j++){
            Debugger.println("Selecting Tumour sample....");
            TumourSample tumourSample = referralObject.getReferralTests().get(0).getTumourSamples().get(j);
            sampleType = tumourSample.getSampleType();
            sampleState = tumourSample.getSampleState();
            labSampleID = String.valueOf(tumourSample.getLabSampleId());
            String updatedSampleType = convertJsonDataUpperCaseToLowerCase(sampleType);
            String updatedSampleState= sampleStateNameConversion(sampleState);
            if(updatedSampleState==null){
                SeleniumLib.takeAScreenShot("Ref_SamplesStage.jpg");
                Assert.fail("Could not get sample state information for: "+sampleState);
            }
            //method to complete all the steps of adding a sample
            addSample(updatedSampleType, updatedSampleState, labSampleID);
        }
        testResult = referralPage.verifyThePageTitlePresence("Manage samples");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
        }
        Assert.assertTrue(testResult);

        int numberOfSamples = samplesPage.numberOfNewSamplesDisplayedInLandingPage();
        if (AppConfig.snapshotRequired) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord("Organization", " ") + "_Added");
        }
        Assert.assertTrue("Numbers of samples displayed should be 1 or greater than 1", numberOfSamples > 0);

        List<String> expectedHeaders = new ArrayList<>(Arrays.asList("Sample type", "State", "Sample ID", "Parent ID", "Tumour description"));
        List actualHeaders = referralPage.getTableColumnHeaders();
        for (int i = 0; i < expectedHeaders.size(); i++) {
            Assert.assertEquals(expectedHeaders.get(i), actualHeaders.get(i));
        }
        Assert.assertEquals(expectedHeaders, actualHeaders);
    }

        //Convert sample state from JSON data to TOMS UI compatible names.
    private String sampleStateNameConversion(String sampleState) {
        switch (sampleState) {
            case "amnioticFluid_freshFrozen": {
                return "Amniotic fluid";
            }
            case "blood_unsorted_edta": {
                return "Blood (EDTA)";
            }
            case "boneMarrow_unsorted_edta": {
                return "Bone marrow";
            }
            case "chorionicVillusSample_freshFrozen": {
                return "Chorionic villus sample";
            }
            case "fetalBlood_edta": {
                return "Fetal blood (EDTA)";
            }
            case "fibroblast_freshFrozen": {
                return "Fibroblasts";
            }
            case "saliva_oragene": {
                return "Saliva";
            }
            case "tissue_freshFrozen": {
                return "Fresh tissue (not tumour)";
            }
            case "tissueInCultureMedium_freshFrozen": {
                return "Skin biopsy";
            }
            case "tumour_freshFrozen": {
                return "Fresh frozen tumour";
            }
            case "tumour_unsorted_freshFluid": {
                return "Tumour fresh fluid";
            }
        }
        return null;
    }
        // method to convert sample type names from Upper case to Lower case UI compatible names
    private String convertJsonDataUpperCaseToLowerCase(String inputValue) {
        inputValue = inputValue.replace("_", " ");
        char firstChar = inputValue.charAt(0);
        int length = inputValue.length();
        String inputValueWithoutFirstChar = inputValue.substring(1, length);
        String outputValue = firstChar + inputValueWithoutFirstChar.toLowerCase();
        Debugger.println("The lower case corrected value is: " + outputValue);
        return outputValue;
    }
        // all steps of adding a sample performed here
    private void addSample(String sampleType, String sampleState, String labSampleID) {
        boolean testResult = false;
        testResult = samplesPage.clickAddSampleButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_AddSample.jpg");
            Assert.fail("Could not click on Add sample button");
        }
        testResult = referralPage.verifyThePageTitlePresence("Add a sample");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Add a sample not present.");
        }
        testResult = samplesPage.selectSampleType(sampleType);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_SampleType.jpg");
            Assert.fail("Could not Select Sample Type:" + sampleType);
        }
        testResult = samplesPage.selectSpecificSampleState(sampleState);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_SampleType.jpg");
            Assert.fail("Could not Select Sample state:" + sampleState);
        }
        testResult = samplesPage.fillInSampleID(labSampleID);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_SampleID.jpg");
            Assert.fail("Could not fill In SampleID" + labSampleID);
        }

        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_Samples.jpg");
            Assert.fail("Could not save Samples information.");
        }

        testResult = referralPage.verifyThePageTitlePresence("Add sample details");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Manage Samples not present.");
        }
        // Non Mandatory steps
//        samplesPage.answerSampleTopography("test");
//        samplesPage.answerSampleMorphology("test");
        if (sampleType.equalsIgnoreCase("Solid tumour sample")) {
            samplesPage.fillInPercentageOfMalignantNuclei();
        }
//        samplesPage.fillInNumberOfSlides();
        samplesPage.selectSampleCollectionDate();
        samplesPage.fillInSampleComments();
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_Samples.jpg");
            Assert.fail("Could not save Samples information.");
        }
        String actualNotificationText = referralPage.successNotificationIsDisplayed();
        if (actualNotificationText == null) {
            Assert.assertTrue("Expected Notification not displayed", false);
        }
        Assert.assertEquals("Sample added", actualNotificationText);
    }

    private void fillStageNotes(Referral referralObject) {
        String stageName = "Notes";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = notesPage.fillInAddNotesField();
        if (!testResult) {
            Assert.fail("Could not fill Notes Details.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_Notes.jpg");
            Assert.fail("Could not save Notes information.");
        }
    }

    private void fillStagePatientChoice(Referral referralObject) {
        String stageName = "Patient choice";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = referralPage.verifyThePageTitlePresence("Patient choice");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Patient choice not present.");
        }
        testResult = patientChoicePage.selectMember(0);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_Edit");
            Assert.fail("Could not Edit member ");
        }
        answerPatientChoiceWithAgreeingToTestingAndPatientChoiceYes();
        testResult = patientChoicePage.submitPatientChoiceWithSignature();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCSubmit.jpg");
            Assert.fail("Patient Choice Could not submit with Signature.");
        }
        testResult = patientChoicePage.clickOnSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCSaveAndContinue");
            Assert.fail("Could not click on Save and Continue.");
        }
        testResult = referralPage.verifyThePageTitlePresence("Patient choice");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Patient choice not present.");
        }
        //agreed to test for Proband
        testResult = patientChoicePage.statusUpdatedCorrectly("Agreed to testing", 0);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCStatus.jpg");
            Assert.fail("Proband Patient Choice could not complete.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCSaveAndContinue");
            Assert.fail("Could not click on Save and Continue.");
        }
    }

    //method for selecting Patient Choice Yes
    private void answerPatientChoiceWithAgreeingToTestingAndPatientChoiceYes() {
        boolean testResult = false;
        testResult = patientChoicePage.selectPatientChoiceCategory();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PatientChoiceProband");
            Assert.fail("Cancer : Failure from patientChoicePage.selectPatientChoiceCategory.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectTestType("Cancer (paired tumour normal) – WGS");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCTestType");
            Assert.fail("Cancer: Failure from patientChoicePage.selectTestType.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.enterRecordedByDetails();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCRecordDetail");
            Assert.fail("Cancer: Failure from patientChoicePage.enterRecordedByDetails.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.selectChoicesWithAgreeingTesting();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCChoice");
            Assert.fail("Cancer: Failure from patientChoicePage.selectChoicesWithAgreeingTesting.");
        }
        Wait.seconds(2);
        testResult = patientChoicePage.drawSignature();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCSignature");
            Assert.fail("Cancer:Failure from patientChoicePage.drawSignature.");
        }
        Wait.seconds(2);
    }

    private void fillStagePrintForms(Referral referralObject) {
        String stageName = "Print forms";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = referralPage.verifyThePageTitlePresence("Print sample forms");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Print sample forms not present.");
        }
        //steps to download sample form and verify details.
        if (SeleniumLib.skipIfBrowserStack("LOCAL")) {
            Debugger.println("Downloading and verifying PrintForm PDF content...");

            try {
                PatientDetailsPage.newPatient.setOrderingEntity(printFormsPage.getLaboratoryAddress());
                PatientDetailsPage.newPatient.setSampleType(printFormsPage.getSampleInfo());
                PatientDetailsPage.newPatient.setTumourType(printFormsPage.getTumourInfo());
            } catch (Exception exp) {
                Debugger.println("Exception in setting printform details to Patient.." + exp + "\n" + driver.getCurrentUrl());
            }
            //Debugger.println("Downloading ........");
            if (!printFormsPage.downloadProbandPrintForm()) {
                Debugger.println("Could not download form for proband");
                Assert.assertTrue("Could not download print form for proband", false);
            }

            //Debugger.println("Verifying content....");
            NewPatient newlyCreatedPatientRecord = patientDetailsPage.getNewlyCreatedPatientData();
            String patientName = newlyCreatedPatientRecord.getPatientFullName();
            String dateOfBirth = newlyCreatedPatientRecord.getPatientDOBInMonthFormat();
            String gender = newlyCreatedPatientRecord.getGender();
            String patientNGISId = newlyCreatedPatientRecord.getReferralHumanReadableID();
            // Formatted patient NGIS Id
            patientNGISId = TestUtils.insertWhiteSpaceAfterEveryNthCharacter(patientNGISId, "4");
            String patientReferralId = newlyCreatedPatientRecord.getPatientHumanReadableID();
            // Formatted patient Referral Id
            patientReferralId = TestUtils.insertWhiteSpaceAfterEveryNthCharacter(patientReferralId, "4");

            String clinicalIndication = newlyCreatedPatientRecord.getClinicalIndication();
            String hospitalNumber = newlyCreatedPatientRecord.getHospitalNumber();

            String requestingOrg = newlyCreatedPatientRecord.getOrderingEntity();

            String responsibleClinicianName = newlyCreatedPatientRecord.getResponsibleClinicianName();
            String responsibleClinicianEmail = newlyCreatedPatientRecord.getResponsibleClinicianEmail();
            String responsibleClinicianContact = newlyCreatedPatientRecord.getResponsibleClinicianContactNumber();

            String tumourInfo = newlyCreatedPatientRecord.getTumourType();
            String sampleInfo = newlyCreatedPatientRecord.getSampleType();
            String[] tumours = tumourInfo.split(" •");
            if (tumours.length > 0) {
                Debugger.println("Tumour Type to verify in the downloaded PDF file  : " + tumours[0]);
            }
            String[] samples = sampleInfo.split("sample required");
            if (samples.length > 0) {
                Debugger.println("Sample info to verify in the downloaded PDF file  : " + samples[0]);
            }

            List<String> expectedValuesToBeVerifiedInPDF = new ArrayList<>();
            expectedValuesToBeVerifiedInPDF.add(patientName);
            expectedValuesToBeVerifiedInPDF.add(dateOfBirth);
            expectedValuesToBeVerifiedInPDF.add(gender);

            expectedValuesToBeVerifiedInPDF.add(patientNGISId);
            expectedValuesToBeVerifiedInPDF.add(patientReferralId);

            expectedValuesToBeVerifiedInPDF.add(clinicalIndication);
            expectedValuesToBeVerifiedInPDF.add(requestingOrg);

            expectedValuesToBeVerifiedInPDF.add(responsibleClinicianName);
            expectedValuesToBeVerifiedInPDF.add(responsibleClinicianEmail);
            expectedValuesToBeVerifiedInPDF.add(responsibleClinicianContact);
            if (tumours.length > 0) {
                expectedValuesToBeVerifiedInPDF.add(tumours[0]);
            }
            if (samples.length > 0) {
                expectedValuesToBeVerifiedInPDF.add(samples[0]);
            }
            testResult = printFormsPage.openAndVerifyPDFContent(expectedValuesToBeVerifiedInPDF);
            Assert.assertTrue(testResult);
        }
    }

    private void verifyAndSubmitReferral() {
        referralPage.submitReferral();
        String actualMessage = referralPage.getSubmissionConfirmationMessageIsDisplayed();
        if (actualMessage == null) {
            Assert.assertTrue(false);
        }
        Assert.assertTrue(actualMessage.contains("Your referral has been submitted"));
        boolean testResult = referralPage.verifyReferralButtonStatus("Submitted");
        if (!testResult) {
            Assert.fail("Referral could not submit successfully.");
        }
        referralPage.saveReferralID(TestUtils.getNtsTag(TestHooks.currentTagName));
    }


}//end
