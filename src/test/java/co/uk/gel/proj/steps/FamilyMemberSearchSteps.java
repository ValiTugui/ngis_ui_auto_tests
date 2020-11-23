package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.RandomDataCreator;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.HashMap;
import java.util.List;

public class FamilyMemberSearchSteps extends Pages {

    public FamilyMemberSearchSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user clicks on Add family member button")
    public void theUserNavigatesToTheFamilyMemberSearchPage() {
        referralPage.navigateToFamilyMemberSearchPage();
    }

    @Then("the default family member search page is correctly displayed with the NHS number and Date of Birth fields")
    public void theFamilyMemberSearchPageIsDisplayedCorrectly() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyTheElementsOnFamilyMemberSearchPageWhenYesIsSelected();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FMSearch.jpg");
            Assert.fail("FM search page is not correctly displayed");
        }
    }

    @And("^the YES button is selected by default on family member search$")
    public void theYESButtonIsSelectedByDefaultOnPatientSearch() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyYesBtnSelectedStatus("true");
        Assert.assertTrue(testResult);
    }

    @And("^the background colour of the YES button in family member is strong blue \"([^\"]*)\"$")
    public void theBackgroundColourOfTheYESButtonIsStrongBlue(String buttonColour) throws Throwable {
        boolean testResult = false;
        String expBgColor = StylesUtils.convertFontColourStringToCSSProperty(buttonColour);
        testResult = familyMemberSearchPage.verifyYesButtonBackgroundColour(expBgColor);
        Assert.assertTrue(testResult);
    }

    @When("^the user clicks the NO button in family member search page$")
    public void theUserClicksTheNOButtonInFamilyMemberSearchPage() throws Throwable {
        familyMemberSearchPage.clickNoButton();
    }

    @When("^the user clicks the Yes button in family member search page$")
    public void theUserClicksTheYesButtonInFamilyMemberSearchPage() throws Throwable {
        familyMemberSearchPage.clickYesButton();
    }

    @Then("^the family member search page displays input fields such as DOB, First Name, Last Name, Gender, postcode and search buttons$")
    public void theFamilyMemberSearchPageDisplaysInputFieldsSuchAsDOBFirstNameLastNameGenderPostcodeAndSearchButtons() {
        boolean testResult;
        testResult = familyMemberSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenNoIsSelected();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FMSearch.jpg");
            Assert.fail("FM search page is not correctly displayed");
        }
    }

    @When("^the user clicks the Search button in family member search page$")
    public void theUserClicksTheSearchButtonInFamilyMemberSearchPage() throws Throwable {
        familyMemberSearchPage.clickSearchButton();
    }

    @Then("^the user will see error messages highlighted in red colour when search with the given details$")
    public void theUserWillSeeErrorMessagesInRedColorWhenSearchWithGivenDetails(DataTable messages) {
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 1; i < messageDetails.size(); i++) {
            familyMemberSearchPage.searchFamilyMemberWithGivenParams(messageDetails.get(i).get(0));
            if(!referralPage.verifyTheErrorMessageDisplay(messageDetails.get(i).get(1),messageDetails.get(i).get(2))){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ErrorMsgl.jpg");
                Assert.fail("No error message displayed");
            }
        }
    }

    @Then("^the message should display as \"([^\"]*)\" and \"([^\"]*)\" along with search string")
    public void theFamilyMemberSearchMessage(String message1, String message2) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyMessageOfExistingPatient(message1, message2);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_EMessage.jpg");
            Assert.fail("Message not displayed as expected.");
        }
    }

    @And("the user search the family member with the specified details {string}")
    public void theUserSearchTheFamilyMemberWithTheSpecifiedDetails(String searchDetails) {
        HashMap<String, String> paramNameValue = TestUtils.splitAndGetParams(searchDetails);
        //Verify whether the search with or without NHS
        String nhsNumber = paramNameValue.get("NHSNumber");
        if(nhsNumber != null && nhsNumber.equalsIgnoreCase("NA")){
            NGISPatientModel familyMember = new NGISPatientModel();
            familyMember.setDATE_OF_BIRTH(paramNameValue.get("DOB"));
            familyMember.setNHS_NUMBER(RandomDataCreator.generateRandomNHSNumber());
            if(!patientSearchPage.fillInNHSNumberAndDateOfBirth(familyMember)){
                //Debugger.println("Could not fill the NHS number and DOB for search....");
                Assert.assertTrue(false);
            }
            patientSearchPage.clickSearchButtonByXpath();
            if(patientSearchPage.getPatientSearchNoResult() == null){//Got error saying invalid NHS number, proceeding with No search in that case
                //Debugger.println("NHS Not Found...going with No option.");
                familyMember.setGENDER(paramNameValue.get("Gender"));
                 if(patientSearchPage.fillInPatientSearchWithNoFields(familyMember)){
                     patientSearchPage.clickSearchButtonByXpath();
                }
            }
            if(!patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage()){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CreateNewPCLink.jpg");
                Assert.assertTrue(false);
            }
            if(!patientDetailsPage.newFamilyMemberPageIsDisplayed()){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NewFM.jpg");
                Assert.assertTrue(false);
            }

            familyMember.setNO_NHS_REASON("Patient not eligible for NHS number (e.g. foreign national)");
            familyMember.setGENDER(paramNameValue.get("Gender"));
            familyMember.setRELATIONSHIP_TO_PROBAND(paramNameValue.get("Relationship"));
            if(paramNameValue.get("Ethnicity") != null){
                familyMember.setETHNICITY(paramNameValue.get("Ethnicity"));
            }else{
                familyMember.setETHNICITY("A - White - British");
            }
            if(!patientDetailsPage.createNewFamilyMember(familyMember)){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CreateFM.jpg");
                Assert.assertTrue(false);
            }
            referralPage.updatePatientNGSID(familyMember);
            Wait.seconds(10);
        }else {
            familyMemberSearchPage.searchFamilyMemberWithGivenParams(searchDetails);
        }
        Wait.seconds(2);
    }

    @Then("the user should see an error message {string} in {string} for the family member")
    public void theUserWillBeAbleToSeeAnErrorMessageAsInForTheFamilyMember(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheErrorMessageForIncompleteDetailsForFamilyMember(errorMessage, messageColor);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoErrorMsg.jpg");
            Assert.fail("Expected error message:"+errorMessage+" not displayed.");
        }

    }

    @Then("the message will be displayed as {string} result found")
    public void theMessageWillBeDisplayedAsResultFound(String resultMessage) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheResultMessageForFamilyMember(resultMessage);
        Assert.assertTrue(testResult);
    }

    @And("^the family member search page display description title contains the phrase \"([^\"]*)\"$")
    public void theDisplayDescriptionTitleContainsThePhrase(String descriptionOfPage) throws Throwable {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyTheDescriptionOfThePage(descriptionOfPage);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FMDescription.jpg");
            Assert.fail("FM description is not displayed");
        }
    }

    @And("^the display question for NHS Number of the family member search page is (.*)$")
    public void theDisplayQuestionContainsThePhrase(String descriptionOfPage) throws Throwable {
        Assert.assertTrue(familyMemberSearchPage.verifyTheQuestionOfThePage(descriptionOfPage));
    }

    @Then("the search results have been displayed with Patient Name, dob, gender, NHS number and address")
    public void theSearchResultsHaveBeenDisplayedWithPatientNameDobGenderNHSNumberAndAddress() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyTheFamilyMemberSearchResultDisplay();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FMSearchDisplay.jpg");
            Assert.fail("FM  search result not displayed");
        }
    }

     @Then("^the user can see a message \"([^\"]*)\" \"([^\"]*)\" in \"([^\"]*)\" font$")
    public void theMessageWillBeDisplayedAsYouVeSearchedForInFont(String expSearchString, String errorMessage, String fontFace) throws Throwable {
        familyMemberSearchPage.verifyNoPatientFoundDetails(expSearchString, errorMessage, fontFace);
    }

    @When("the user clicks on the create new patient record")
    public void theUserClicksOnThe() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.clickOnNewPatientLink();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NewPatientRecord.jpg");
            Assert.fail("Could not click on new patient record");
        }
    }

    @Then("the family member landing page displayed without incomplete error message")
    public void theFamilyMemberLandingPageDisplayedWithoutIncompleteErrorMessage() {
        boolean testResult = true;
        testResult = familyMemberSearchPage.checkTheErrorMessageForIncompleteFamilyMember();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FMIncomplete.jpg");
            Assert.fail("FM incomplete message is not displayed");
        }
    }
    @Then("the message will be displayed as {string} in {string} for the invalid field")
    public void theMessageWillBeDisplayedAsInForTheInvalidField(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheErrorMessageForInvalidField(errorMessage, messageColor);
        Assert.assertTrue(testResult);

    }
    @And("^the NHS number entry fields should be of length 10$")
    public void theNHSNumberEntryFiledDisplayLengthAs10() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyNHSFieldPlaceHolder();
        Assert.assertTrue(testResult);
    }
    @And("^the DOB entry fields should have the format dd-mm-yyyy displayed$")
    public void theDOBEntryFiledDisplayFormat() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyDOBFieldPlaceHolder();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_DOBPlaceHolder.jpg");
            Assert.fail("FM DOB Place holder not displayed");
        }
    }
    @And("^the Search button should be displayed with search symbol and click-able$")
    public void theSearchButtonShouldBeClickable() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifySearchButtonClickable();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_FMSearchButton.jpg");
            Assert.fail("FM Search Button is not displayed");
        }
    }
    @And("the user verifies the svg icon for tick mark")
    public void theUserVerifiesTheIconForTickMark() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifySVGForTickMark();
        Assert.assertTrue(testResult);
    }

    @When("the user clicks on add non-tested-family member link on family landing page")
    public void theUserClicksOnAddNonTestedFamilyMemberLinkOnFamilyLandingPage() {
        familyMemberSearchPage.clickOnAddNonTestedFamilyMemberLink();
    }

}//end
