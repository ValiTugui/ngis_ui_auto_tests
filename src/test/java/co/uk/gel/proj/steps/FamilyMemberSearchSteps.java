package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.StylesUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

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
        Assert.assertTrue(testResult);
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
        boolean eachElementIsLoaded;
        eachElementIsLoaded = familyMemberSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenNoIsSelected();
        Assert.assertTrue(eachElementIsLoaded);
    }

    @When("^the user clicks the Search button in family member search page$")
    public void theUserClicksTheSearchButtonInFamilyMemberSearchPage() throws Throwable {
        familyMemberSearchPage.clickSearchButton();
    }

    @Then("^the mandatory fields should be highlighted with a red mark in family member search page with Yes option selected$")
    public void theMandatoryFieldsShouldBeHighlightedWithARedMarkForYes() {
        familyMemberSearchPage.validateErrorsAreDisplayedForSkippedMandatoryValuesForYes();
    }

    @Then("^the mandatory fields should be highlighted with a red mark in family member search page with No option$")
    public void theMandatoryFieldsShouldBeHighlightedWithARedMarkForNo() {
        familyMemberSearchPage.validateErrorsAreDisplayedForSkippingMandatoryValuesNo();
    }

    @When("the user provides NHS and DOB of an already added patient and search")
    public void theUserProvidesDetailsOfExistingPatientAndSearch() {
        familyMemberSearchPage.searchWithAlreadyAddedPatientDetailsUsingNHSNumberAndDOB();
    }

    @Then("^the message should display as \"([^\"]*)\" and \"([^\"]*)\" along with search string")
    public void theFamilyMemberSearchMessage(String message1, String message2) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyMessageOfExistingPatient(message1, message2);
        Assert.assertTrue(testResult);
    }

    @And("the user search the family member with the specified details {string}")
    public void theUserSearchTheFamilyMemberWithTheSpecifiedDetails(String searchDetails) {
        familyMemberSearchPage.searchFamilyMemberWithGivenParams(searchDetails);
    }

    @And("the user will be able to see an error message as {string} in {string} for the family member")
    public void theUserWillBeAbleToSeeAnErrorMessageAsInForTheFamilyMember(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheErrorMessageForIncompleteDetailsForFamilyMember(errorMessage, messageColor);
        Assert.assertTrue(testResult);
    }

    @Then("the message will be displayed as {string} result found")
    public void theMessageWillBeDisplayedAsResultFound(String resultMessage) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheResultMessageForFamilyMember(resultMessage);
        Assert.assertTrue(testResult);
    }

    @And("^the display title of the family member search page is \"([^\"]*)\"$")
    public void theDisplayTitleOfThePageIs(String titlePage) throws Throwable {
        familyMemberSearchPage.verifyTheTitleOfThePage(titlePage);
    }

    @And("^the family member search page display description title contains the phrase \"([^\"]*)\"$")
    public void theDisplayDescriptionTitleContainsThePhrase(String descriptionOfPage) throws Throwable {
        familyMemberSearchPage.verifyTheDescriptionOfThePage(descriptionOfPage);
    }

    @And("^the display question for NHS Number of the family member search page is \"([^\"]*)\"$")
    public void theDisplayQuestionContainsThePhrase(String descriptionOfPage) throws Throwable {
        familyMemberSearchPage.verifyTheQuestionOfThePage(descriptionOfPage);
    }

    @Then("the search results have been displayed with Patient Name, dob, gender, NHS number and address")
    public void theSearchResultsHaveBeenDisplayedWithPatientNameDobGenderNHSNumberAndAddress() {
        boolean testResult = false;
        testResult = familyMemberSearchPage.verifyTheFamilyMemberSearchPatientCardDetailsAreDisplayed();
        Assert.assertTrue(testResult);
    }

    @And("There is a {string} link available to create a new patient")
    public void thereIsALinkAvailableToCreateANewPatient(String hyperLinkText) {
        familyMemberSearchPage.checkCreateNewPatientLinkDisplayed(hyperLinkText);
    }

    @And("the user clicks the {string} to create a new patient")
    public void theUserClicksTheToCreateANewPatient(String hyperLinkText) {
        familyMemberSearchPage.createNewPatientLinkDisplayed(hyperLinkText);
    }
    @Then("^the user can see a message \"([^\"]*)\" \"([^\"]*)\" in \"([^\"]*)\" font$")
    public void theMessageWillBeDisplayedAsYouVeSearchedForInFont(String expSearchString, String errorMessage, String fontFace) throws Throwable {
        familyMemberSearchPage.verifyNoPatientFoundDetails(expSearchString, errorMessage, fontFace);
    }

    @When("the user clicks on the create new patient record")
    public void theUserClicksOnThe() {
        familyMemberSearchPage.clickOnNewPatientLink();
    }

    @And("the user should be able to see incomplete family member in {string}")
    public void theUserShouldBeAbleToSeeIncompleteFamilyMemberIn(String messageColor) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.getTextFromErrorPatientCardFields(messageColor);
        Assert.assertTrue(testResult);
    }
    @When("the user edits to complete the highlighted family member")
    public void theUserShouldEditToCompleteTheHighlightedFamilyMember() {
        familyMemberSearchPage.completingQuestionnaireFamilyMember();
    }

    @Then("the family member landing page displayed without incomplete error message")
    public void theFamilyMemberLandingPageDisplayedWithoutIncompleteErrorMessage() {
        boolean testResult = true;
        testResult = familyMemberSearchPage.checkTheErrorMessageForIncompleteFamilyMember();
        Assert.assertFalse(testResult);
    }
    @Then("the message will be displayed as {string} in {string} for the invalid field")
    public void theMessageWillBeDisplayedAsInForTheInvalidField(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheErrorMessageForInvalidField(errorMessage, messageColor);
        Assert.assertTrue(testResult);

    }

}//end
