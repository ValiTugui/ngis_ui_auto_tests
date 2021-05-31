package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.models.ReferralFromJson;
import co.uk.gel.proj.TestDataProvider.NewPatient;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.FamilyMemberDetailsPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.pages.PatientDetailsPage;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.gel.models.participant.avro.*;
import org.gel.models.participant.avro.Date;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @When("the {string} referral is created with details from Json provided")
    public void theReferralIsCreatedWithDetailsFromJsonProvided(String referralType,List<String> attributeOfURL) {
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
            Assert.fail("Could not select the first entity from CI Search Result.");
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
        String yearOfBirth ;//= referralObject.getCancerParticipant().getYearOfBirth().toString();
//        if (yearOfBirth == null) {
//            Debugger.println("The Json is for RD patient... reading YOB...  ");
            List<Integer> probandMemberNum = memberDetails(referralObject, "Proband");
            if (probandMemberNum == null) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NoProband.jpg");
                Assert.fail("Could not get member details from JSON.");
            }
            Debugger.println("The position of proband member participant is " + probandMemberNum.toString());
            PedigreeMember probandMember = referralObject.getPedigree().getMembers().get(probandMemberNum.get(0));
            yearOfBirth = String.valueOf(probandMember.getYearOfBirth());
            Debugger.println("The value for YOB is " + yearOfBirth);
//        }
        if (!patientSearchPage.searchParticipantFromJson(RandomDataCreator.generateRandomNHSNumber(),"01","01",yearOfBirth)) {
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
                if (!patientDetailsPage.fillInPatientDetailsFromJson(referralType,reasonForNoNHSNumber,referralObject)) {
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

    @Then("the {string} referral should be created via TOMS using json provided information and submitted successfully")
    public void theReferralsShouldBeCreatedAndSubmittedSuccessfullyViaTOMS(String caseType) {
        Referral referralObject = referralFromJson.getReferralObject();
        if (referralObject == null) {
            Assert.fail("Referral Object is not initialized.");
        }
        //Requesting Organisation
        fillStageRequestingOrganisation(referralObject);
        //Test Package
        fillStageTestPackage(caseType,referralObject);
        //Responsible Clinician
        fillStageResponsibleClinician(referralObject);
        // stage differs for Cancer & RD
        if (caseType.equalsIgnoreCase("Cancer")) {
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
        } else {
            //Clinical Questions
            fillStageClinicalQuestions(referralObject);
            //Notes
            fillStageNotes(referralObject);
            //Family Members
            fillStageFamilyMembers(referralObject);
            //Patient Choice
            fillStagePatientChoice(referralObject);
            fillStagePatientChoiceForFamilyMembers(referralObject);
            //Panels
            fillStagePanels(referralObject);
            //Pedigree
            fillStagePedigree(referralObject);
            //Print Forms
            fillStagePrintFormsForRD(referralObject);
            //Submit Referral
            verifyAndSubmitReferral();
        }
        String referralID = referralPage.returnReferralID();
        saveJsonCreatedReferralID(caseType,referralID);
    }

    private void fillStageRequestingOrganisation(Referral referralObject) {
        String stageName = "Requesting organisation";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = paperFormPage.fillInSpecificKeywordInSearchField(referralObject.getRequester().getOrganisationCode());
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
//        testResult = paperFormPage.selectFirstEntityFromSuggestionsList();
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

    private void fillStageTestPackage(String caseType, Referral referralObject) {
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
        //Add reading priority from json and then selecting
        testResult = testPackagePage.clickRoutinePriority();
//        }
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TestPriority.jpg");
            Assert.fail("Test Package: Routine priority could not select.");
        }
        //Observed failures in Jenkins run, looks like it is too fast, so provided a wait.
        Wait.seconds(5);
        if(!caseType.equalsIgnoreCase("Cancer")) {
//            int numberOfTestParticipants = getNumberOfParticipantsFromJson(referralObject);
            List<Integer> positionOfTestParticipants = getPositionOfParticipantsFromJson(referralObject);
            Debugger.println("Non-Proband test participants- "+positionOfTestParticipants);
            if(positionOfTestParticipants == null){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NoOfParticipants");
                Assert.fail("Could not get Participants from the Json file.");
            }
            // adding 1 for proband
            int numberOfTestParticipants = positionOfTestParticipants.size()+1;
            Debugger.println("The number of participants to be selected "+numberOfTestParticipants);
            if(numberOfTestParticipants==0){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NoOfParticipants");
                Assert.fail("No Of Participants should be more than zero, but found to be: "+numberOfTestParticipants);
            }
            //select number of participants
            testResult = testPackagePage.selectNumberOfParticipants(numberOfTestParticipants);
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NoOfParticipants");
                Assert.fail("No Of Participants could not select");
            }
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot("Ref_TestPackage.jpg");
            Assert.fail("Could not save Test package.");
        }
        if (caseType.equalsIgnoreCase("Cancer")) {

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
    }

    private List<Integer> getPositionOfParticipantsFromJson(Referral referralObject) {
        try {
            List<Integer> nonProbandMembers = TestUtils.getMemberPositionDetailsFromJson(referralObject, "Non Proband");
            Debugger.println("The list of position of all non-proband participants from Json= " + nonProbandMembers);
            List<Integer> positionOfTestParticipants = new ArrayList<>();
            for (int i = 0; i < nonProbandMembers.size(); i++) {
                int memberPosititon = nonProbandMembers.get(i);
//                Debugger.println("The member position "+memberPosititon);
                PedigreeMember member = referralObject.getPedigree().getMembers().get(memberPosititon);
//                Debugger.println("The member JSON: "+member.toString());
                // Convert to Json type to check for a node's presence
                JSONParser parser=new JSONParser();
                org.json.simple.JSONObject simpleJsonObj= (org.json.simple.JSONObject) parser.parse(String.valueOf(member));
                JSONObject memberJson = new JSONObject( simpleJsonObj);
//                Debugger.println("The JSON: "+memberJson.toString());
                if (!memberJson.isNull("yearOfBirth") ) {
                    if (memberJson.getString("yearOfBirth").startsWith("19") || memberJson.getString("yearOfBirth").startsWith("20")) {
                        Debugger.println("Adding non-proband participant "+i+" for test from position: "+memberPosititon);
                        positionOfTestParticipants.add(memberPosititon);
                    }
                }
            }
            // add 1 for Proband
            return positionOfTestParticipants;
        } catch (Exception exp) {
            Debugger.println("Exception from getting number of test participants from Json: " + exp);
            return null;
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
//            tumourType=tumourType.replace("_"," ");
//            char firstChar=tumourType.charAt(0);
//            int length=tumourType.length();
//            String tumourTypeWithoutFirstChar=tumourType.substring(1,length);
//            String newTumourType=firstChar+tumourTypeWithoutFirstChar.toLowerCase();
            String newTumourType = tumourTypeToDropDownOptionConversion(tumourType);
            Debugger.println("The converted tumour type as per dropdown is: "+newTumourType);
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
            String tumourPresentation= String.valueOf(referralObject.getCancerParticipant().getTumours().get(i).getTumourPresentation());
            tumourPresentation = tumourPresentation.replace("_"," ");
            tumourPresentation = TestUtils.convertUpperCaseJSONDataToProperFormat(tumourPresentation);
            testResult = tumoursPage.selectTumourFirstPresentationOrOccurrenceValue(tumourPresentation);
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TumourFirstPresentation.jpg");
                Assert.fail("Could not select tumour first presentation");
            }
//            testResult = tumoursPage.answerTumourDiagnosisQuestions("test");
//            tumoursPage.answerTumourDiagnosisQuestionsBasedOnTumourType(newTumourType,"test");
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

    private String tumourTypeToDropDownOptionConversion(String tumourType) {
        switch (tumourType) {
            case "BRAIN_TUMOUR": {
                return "Brain tumour";
            }
            case "HAEMATOLOGICAL_MALIGNANCY_LIQUID_SAMPLE": {
                return "Haematological malignancy: liquid sample";
            }
            case "HAEMATOLOGICAL_MALIGNANCY_SOLID_SAMPLE": {
                return "Haematological malignancy: solid sample";
            }
            case "SOLID_TUMOUR_PRIMARY": {
                return "Solid tumour: primary";
            }
            case "SOLID_TUMOUR_METASTATIC": {
                return "Solid tumour: metastatic";
            }
            case "SOLID_TUMOUR_UNKNOWN": {
                return "Solid tumour: unknown";
            }
        }
        return null;
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
//        samplesPage.selectSampleCollectionDate();

        Calendar calendarDate = Calendar.getInstance();
        calendarDate.add(Calendar.DATE, -1);
        calendarDate.getTime();
        String receivedSampleCollectionDate = new SimpleDateFormat("dd/MM/yyyy").format(calendarDate.getTime());
        String[] dateArr = receivedSampleCollectionDate.split("/");
        samplesPage.selectSampleCollectionDateAsDate(dateArr[0],dateArr[1],dateArr[2]);
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

    private void fillStagePrintFormsForRD(Referral referralObject) {
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

    private void fillStageClinicalQuestions(Referral referralObject) {
//        driver.get("https://test-ordering.e2e-latest.ngis.io/test-order/referral/r21256076464/requesting-organisation?newReferral=true");
        String stageName = "Clinical questions";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = referralPage.verifyThePageTitlePresence("Answer clinical questions");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Answer clinical questions not present.");
        }
        List<Integer> memberList = memberDetails(referralObject, "Non Proband");
        Debugger.println("The Non Proband participants are " + memberList.toString());
        List<Integer> probandMemberNum = memberDetails(referralObject, "Proband");
        if(probandMemberNum==null){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_ClinicalQuestions.jpg");
            Assert.fail("Could not get member details from JSON.");
        }
        Debugger.println("The position of proband participant is- " + probandMemberNum.toString());

        PedigreeMember probandMember = referralObject.getPedigree().getMembers().get(probandMemberNum.get(0));
        String diseaseStatus = String.valueOf(probandMember.getAffectionStatus());
        diseaseStatus=convertJsonDataUpperCaseToLowerCase(diseaseStatus);
        String karyotypicSex = String.valueOf(probandMember.getPersonKaryotypicSex());
        if(karyotypicSex.equalsIgnoreCase("other") || karyotypicSex.equalsIgnoreCase("UNKNOWN")){
            karyotypicSex = karyotypicSex.toLowerCase();
        }

        String phenotypicSex = String.valueOf(probandMember.getSex());
        phenotypicSex=convertJsonDataUpperCaseToLowerCase(phenotypicSex);
        String lifeStatus = String.valueOf(probandMember.getLifeStatus());

        List<HpoTerm> hpoList = probandMember.getHpoTermList();
        List<String> hpoTermList=new ArrayList<>();

        for(HpoTerm hpo:hpoList){
            String hpoData=hpo.getTerm()+"-"+ hpo.getTermPresence();
//            Debugger.println("The HPO data - "+hpoData);
            hpoTermList.add(hpoData);
        }
//        Debugger.println("The hpoTerm list: "+hpoTermList.toString());

        Debugger.println("disease status " + diseaseStatus);
        Debugger.println("karyotypic sex " + karyotypicSex);
        Debugger.println("sex " + phenotypicSex);
        Debugger.println("life status " + lifeStatus);

        String clinicalQuesAnswers="DiseaseStatus="+diseaseStatus+";AgeOfOnset=01,02;HpoPhenoType="+ hpoTermList.toString() +";PhenotypicSex="+phenotypicSex+";KaryotypicSex="+karyotypicSex;
        Debugger.println("The answers are "+clinicalQuesAnswers);
        testResult = clinicalQuestionsPage.fillDiseaseStatusAgeOfOnsetAndHPOTermFromJson(clinicalQuesAnswers);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_ClinicalQuestions.jpg");
            Assert.fail("Clinical Questions could not enter.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCSaveAndContinue.jpg");
            Assert.fail("Could not click on Save and Continue.");
        }
    }

    private List<Integer> memberDetails(Referral referralObject, String memberType) {
        try {
            int numOfParticipants = referralObject.getPedigree().getMembers().size();
            List<Integer> memberPositions = new ArrayList<Integer>();
            for (int i = 0; i < numOfParticipants; i++) {
                PedigreeMember member = referralObject.getPedigree().getMembers().get(i);
                boolean probandStatus = member.getIsProband();

                if (memberType.equalsIgnoreCase("Proband")) {
                    if (probandStatus) {
                        Debugger.println("Adding proband position "+i);
                        memberPositions.add(i);
                        return memberPositions;
                    }
                } else {
                    if (!probandStatus) {
                        Debugger.println("Adding member position "+i);
                        memberPositions.add(i);
                    }
                }
            }
            Debugger.println("The member positions added "+memberPositions.toString());
            return memberPositions;
        } catch (Exception exp) {
            Debugger.println("Exception from getting member position details " + exp);
            return null;
        }
    }

    private static  List<List<String>> familyDetails = new ArrayList<>();

    private void fillStageFamilyMembers(Referral referralObject) {
        String stageName = "Family members";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = referralPage.verifyThePageTitlePresence("Add a family member to this referral");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Manage panels not present.");
        }
        List<Integer> positionOfTestParticipants = getPositionOfParticipantsFromJson(referralObject);
        if (positionOfTestParticipants == null) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_NoOfParticipants");
            Assert.fail("Error in reading family Participants from JSON file.");
        }
        Debugger.println("The participants to be selected as family are at position: " + positionOfTestParticipants.toString());
        Debugger.println("The number of family participants to be selected: " + positionOfTestParticipants.size());
        int numberOfTestParticipants = positionOfTestParticipants.size();

        List<List<String>> familyParticipants = new ArrayList<>();

        if(numberOfTestParticipants > 0) {
            for (int i = 0; i < numberOfTestParticipants; i++) {
                List<String> familyMemberDetails = new ArrayList<>();
                int familyMemberPositionInJson = positionOfTestParticipants.get(i);
                PedigreeMember familyMember = referralObject.getPedigree().getMembers().get(familyMemberPositionInJson);

                String yearOfBirth = String.valueOf(familyMember.getYearOfBirth());
                Debugger.println("The value for YOB is " + yearOfBirth);
                String dob = TestUtils.getRandomDobFromYear(yearOfBirth);
                Debugger.println("The dob- " + dob);

                String phenotypicSex = String.valueOf(familyMember.getSex());
                String gender = convertJsonDataUpperCaseToLowerCase(phenotypicSex);

                String diseaseStatus = String.valueOf(familyMember.getAffectionStatus());
                diseaseStatus = convertJsonDataUpperCaseToLowerCase(diseaseStatus);

                String karyotypicSex = String.valueOf(familyMember.getPersonKaryotypicSex()).toLowerCase();
                String lifeStatus = String.valueOf(familyMember.getLifeStatus());
                lifeStatus = TestUtils.convertUpperCaseJSONDataToProperFormat(lifeStatus);

//            Debugger.println("disease status " + diseaseStatus);
//            Debugger.println("karyotypic sex " + karyotypicSex);
//            Debugger.println("sex " + gender);
//            Debugger.println("life status " + lifeStatus);
                String familyRelation = null;
                if (gender.equalsIgnoreCase("Male")) {
                    familyRelation = "Father";
                } else if (gender.equalsIgnoreCase("Female")) {
                    familyRelation = "Mother";
                } else {
                    familyRelation = "Other";
                }
                String familyMemberData = "NHSNumber=NA:DOB=" + dob + ":Gender=" + gender + ":Relationship=" + familyRelation + ":LifeStatus=" + lifeStatus;
                String clinicalQuesAnswers = "DiseaseStatus=" + diseaseStatus + ":AgeOfOnset=01,02:HpoPhenoType=Phenotypic abnormality:PhenotypicSex=" + gender + ":KaryotypicSex=" + karyotypicSex;
                familyMemberDetails.add(familyMemberData);
                familyMemberDetails.add(familyRelation);
                familyMemberDetails.add(clinicalQuesAnswers);
                Debugger.println("The details are " + familyMemberDetails.toString());
                familyParticipants.add(familyMemberDetails);
            }
        }
        if (!(familyParticipants == null && familyParticipants.isEmpty())) {
            Debugger.println("The family details are " + familyDetails.toString());
            enterFamilyMembersForRDReferral(familyParticipants);
            familyDetails = familyParticipants;
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FamilySaveAndContinue");
            Assert.fail("Could not click on Save and Continue.");
        }
    }

    private void enterFamilyMembersForRDReferral(List<List<String>> memberDetails){
        try {
            String nhsNumber = "";
            for (int i = 0; i < memberDetails.size(); i++) {
                Debugger.println("\nAdding Family Member: " + i);
                if (!referralPage.navigateToFamilyMemberSearchPage()) {
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                    Assert.fail("Could not click on Add Family Member Button.");
                }
                HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(memberDetails.get(i).get(0));
                //Verify whether the search with or without NHS
                nhsNumber = paramNameValue.get("NHSNumber");
                if (nhsNumber != null && nhsNumber.equalsIgnoreCase("NA")) {
                    NGISPatientModel familyMember = new NGISPatientModel();
                    familyMember.setNHS_NUMBER(RandomDataCreator.generateRandomNHSNumber());
                    familyMember.setDATE_OF_BIRTH(paramNameValue.get("DOB"));
                    familyMember.setGENDER(paramNameValue.get("Gender"));
                    familyMember.setRELATIONSHIP_TO_PROBAND(paramNameValue.get("Relationship"));
                    familyMember.setNO_NHS_REASON("Patient not eligible for NHS number (e.g. foreign national)");
                    if (paramNameValue.get("Ethnicity") != null) {
                        familyMember.setETHNICITY(paramNameValue.get("Ethnicity"));
                    } else {
                        familyMember.setETHNICITY("A - White - British");
                    }
                    if (paramNameValue.get("LifeStatus") != null) {
                        familyMember.setLIFE_STATUS(paramNameValue.get("LifeStatus"));
                    } else {
                        familyMember.setLIFE_STATUS("Alive");
                    }
                    if (!patientSearchPage.fillInNHSNumberAndDateOfBirth(familyMember)) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + ": fillInNHSNumberAndDateOfBirth Failed");
                    }
                    if (!patientSearchPage.clickSearchButtonByXpath()) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + ": fillInNHSNumberAndDateOfBirth Failed");
                    }
                    if (patientSearchPage.getPatientSearchNoResult() == null) {//Got error saying invalid NHS number, proceeding with No search in that case
                        if (patientSearchPage.fillInPatientSearchWithNoFields(familyMember)) {
                            patientSearchPage.clickSearchButtonByXpath();
                        }
                    }
                    if (!patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage()) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + "clickCreateNewPatientLinkFromNoSearchResultsPage Failed");
                    }
                    if (!familyMemberNewPatientPage.newFamilyMemberPageIsDisplayed()) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + ": new Family Member URL not displayed");
                    }
                    if (!patientDetailsPage.createNewFamilyMember(familyMember)) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + "createNewFamilyMember Failed");
                    }
                    if (!referralPage.verifyThePageTitlePresence("Continue with this family member")) {
                        if (!referralPage.verifyThePageTitlePresence("Create a record for this family member")) {
                            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                            Assert.fail("FM:" + memberDetails.get(i).get(0) + "verifyThePageTitlePresence Failed");
                        }
                    }
                    referralPage.updatePatientNGSID(familyMember);
                    if (!referralPage.clickSaveAndContinueButton()) {
                        if (!referralPage.clickSaveAndContinueButton()) {//Again Clicking
                            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                            Assert.fail("FM:" + memberDetails.get(i).get(0) + "clickSaveAndContinueButton Failed");
                        }
                    }
                } else {
                    if (!familyMemberSearchPage.searchFamilyMemberWithGivenParams(memberDetails.get(i).get(0))) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + "searchFamilyMemberWithGivenParams Failed");
                    }
                    if (!familyMemberDetailsPage.verifyPatientRecordDetailsDisplay(memberDetails.get(i).get(1))) {
                        Debugger.println("Patient already added...continuing with next.");
                        continue;
                    }
                    if (!familyMemberDetailsPage.clickPatientCard()) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + ": clickPatientCard Failed");
                    }
                    if (!familyMemberDetailsPage.fillTheRelationshipToProband(memberDetails.get(i).get(1))) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + "fillTheRelationshipToProband Failed");
                    }
                    if (!referralPage.clickSaveAndContinueButton()) {
                        SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                        Assert.fail("FM:" + memberDetails.get(i).get(0) + "clickSaveAndContinueButton Failed");
                    }
                }
                Wait.seconds(5);
                NGISPatientModel familyMember = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
                if (familyMember == null) {
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                    Assert.fail("Family Member:" + memberDetails.get(i).get(0) + " not found in the added list!");
                }
                Wait.seconds(5);//Continuos time out failures observed at this point in jenkins runs.
                if (!familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember(familyMember)) {
                    Assert.fail("Select Test title for Family Member " + memberDetails.get(i).get(0) + " Not displayed. Pls check SelectTitle.jpg");
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                }
                Wait.seconds(5);
                if (memberDetails.get(i).size() < 3) {
                    continue;//Some times the Disease status not passing
                }
                if (!referralPage.clickSaveAndContinueButton()) {
                    Assert.assertTrue(false);
                }
                Wait.seconds(5);
                if (!familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(memberDetails.get(i).get(2))) {
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                    Assert.fail("fillFamilyMemberDiseaseStatusWithGivenParams not completed.");
                }
                Wait.seconds(5);
                if (!referralPage.clickSaveAndContinueButton()) {
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                    Assert.assertTrue(false);
                }
                Wait.seconds(5);
                if (!referralPage.verifyThePageTitlePresence("Add a family member to this referral")) {
                    Wait.seconds(10);
                    if (!referralPage.verifyThePageTitlePresence("Add a family member to this referral")) {
                        Wait.seconds(20);
                    }
                }

                if (!familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage(memberDetails.get(i).get(0))) {
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
                    Assert.fail("Details of Added family member not displayed as expected in FamilyMember Landing Page.");
                }
                Debugger.println("Family Member:" + memberDetails.get(i).get(0) + " Added Successfully.\n");
                Wait.seconds(5);
            }//end
            if (AppConfig.snapshotRequired) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FamilyMembers");
            }
        } catch (Exception exp) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_FM.jpg");
            Assert.fail("FamilyMemberDetailsSteps: Exception in Filling the Family Member Details: ");
        }
    }

    private void fillStagePanels(Referral referralObject) {
        String stageName = "Panels";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = referralPage.verifyThePageTitlePresence("Manage panels");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Manage panels not present.");
        }
        int numOfPanels = referralObject.getReferralTests().get(0).getAnalysisPanels().size();
        List<AnalysisPanel> panelsList=referralObject.getReferralTests().get(0).getAnalysisPanels();
        Debugger.println("The panels to be added are-"+panelsList.toString());
        for(int i=0;i<numOfPanels;i++){
            String panelName = panelsList.get(i).getPanelName();
            testResult = panelsPage.searchAndAddPanel(panelName);
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PanelsAdding");
                Assert.fail("Could not add panels."+panelName);
            }
        }
        String penetrance = referralObject.getReferralTests().get(0).getDiseasePenetrances().get(0).getPenetrance().toString();
        Debugger.println("Penetrance-"+penetrance);
        testResult = panelsPage.verifyButtonAsCompletedByClickingInPanelsPage(penetrance);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PanelsSaveAndContinue");
            Assert.fail("Could not click on Penetrance.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PanelsSaveAndContinue");
            Assert.fail("Could not click on Save and Continue.");
        }
    }

    private void fillStagePedigree(Referral referralObject) {
        String stageName = "Pedigree";
        boolean testResult = referralPage.navigateToStage(stageName);
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + TestUtils.removeAWord(stageName, " ") + ".jpg");
            Assert.fail("Could not navigate to stage:" + stageName);
        }
        testResult = referralPage.verifyThePageTitlePresence("Build a pedigree");
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
            Assert.fail("Page title- Build a pedigree not present.");
        }
        testResult = referralPage.clickSaveAndContinueButton();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCSaveAndContinue");
            Assert.fail("Could not click on Save and Continue.");
        }
    }

    private void fillStagePatientChoiceForFamilyMembers(Referral referralObject) {
        try {
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

            List<List<String>> familyMemberDetails = familyDetails;
            List<List<String>> familyMemberPC = new ArrayList<>();
            for (int i = 0; i < familyMemberDetails.size(); i++) {
                HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(familyMemberDetails.get(i).get(0));
                String dob = paramNameValue.get("DOB");
                String familyMember = "NHSNumber=NA:DOB=" + dob;
                String category = "Adult (With Capacity)";
                String recordedBy = "ClinicianName=John:HospitalNumber=123:Action=UploadDocument:FileType=Record of Discussion Form:FileName=testfile.pdf";

                List<String> patientChoiceDetails = new ArrayList<>();
                patientChoiceDetails.add(familyMember);
                patientChoiceDetails.add(category);
                patientChoiceDetails.add(recordedBy);
                familyMemberPC.add(patientChoiceDetails);
            }
            Debugger.println("The PC choices are " + familyMemberPC.toString());
            for (int j = 0; j < familyMemberPC.size(); j++) {
                    // doing for 1st family member so add 1
                Debugger.println("Patient Choice for Member: " + j+1);
                if (!patientChoicePage.selectMember(j+1)) {
                    Assert.fail("Could not select the member to complete PC");
                }
                Wait.seconds(2);
                if (!patientChoicePage.completePatientChoiceWithAgreeingTestForFamilyMember(familyMemberPC.get(j).get(0), familyMemberPC.get(j).get(1), familyMemberPC.get(j).get(2))) {
                    Assert.fail("Could not complete PC for the member " + j);
                }
                Wait.seconds(5);//After submitting PC
                if (!patientChoicePage.clickOnSaveAndContinueButton()) {
                    Assert.fail("Could not proceed from PC after submitting Patient Choice..");
                }
                Wait.seconds(10);//Waiting for 10 seconds as there is a delay observed in patient choice page in e2elatest
            }

//            testResult = patientChoicePage.clickOnSaveAndContinueButton();
//            if (!testResult) {
//                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCSaveAndContinue");
//                Assert.fail("Could not click on Save and Continue.");
//            }
            testResult = referralPage.verifyThePageTitlePresence("Patient choice");
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TitleNotDisplayed.jpg");
                Assert.fail("Page title- Patient choice not present after selecting PC for family members.");
            }

            testResult = referralPage.clickSaveAndContinueButton();
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PCSaveAndContinue");
                Assert.fail("Could not click on Save and Continue.");
            }
        } catch (Exception exp) {
//            Debugger.println("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp);
            Assert.assertTrue("PatientChoiceSteps: Exception in Filling PatientChoice Details: " + exp, false);
        }
    }

    public List<String> getSampleWellIdFromJson(String caseType) {
        try {
            Referral referralObject = referralFromJson.getReferralObject();
            if (referralObject == null) {
                Assert.fail("Referral Object is not initialized.");
            }
            List<String> sampleWellIdList = new ArrayList<>();
            if (caseType.equalsIgnoreCase("Cancer")) {
                sampleWellIdList.add(referralObject.getReferralTests().get(0).getGermlineSamples().get(0).getSampleId());
                sampleWellIdList.add(referralObject.getReferralTests().get(0).getTumourSamples().get(0).getSampleId());
            } else {
                for (GermlineSample germlineSample : referralObject.getReferralTests().get(0).getGermlineSamples()) {
                    sampleWellIdList.add(germlineSample.getSampleId());
                }
            }
            return sampleWellIdList;
        } catch (Exception exp) {
            Debugger.println("Exception from getting Sample Well ID from Json: " + exp);
            return null;
        }
    }

    public void saveJsonCreatedReferralID(String caseType, String referralIdData) {
        try {
            List<String> sampleWellIdList = getSampleWellIdFromJson(caseType);
            Debugger.println("Samples list- " + sampleWellIdList);
            String referralID = caseType + " --> " + referralIdData + "\n";
            SeleniumLib.writeToJsonFileOfName("JsonReferrals.json", caseType, referralIdData, sampleWellIdList);
            Debugger.println(referralID);
//        SeleniumLib.writeToTextFileOfName("JsonReferralID.txt",referralID);
            Assert.assertTrue("JSON file with referral Id, case type, sample well details created successfully.",true);
        } catch (Exception exp) {
            Debugger.println("Exception from Saving the referral Id: " + exp);
            Assert.fail("FAILURE in creating JSON file with referral Id, case type, sample well details.");
        }
    }

}//end
