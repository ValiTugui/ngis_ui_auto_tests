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

    @And("the user navigates to the family member search Page")
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

    @When("the user provides DOB,FirstName,LastName and Gender of an already added patient and search")
    public void theUserProvidesDOBFirstNameLastNameAndGenderOfAnAlreadyAddedPatientAndSearch() {
        familyMemberSearchPage.fillInDOBFirstNameLastNameGender();
    }

    @And("the user search the family member with the specified details {string}")
    public void theUserSearchTheFamilyMemberWithTheSpecifiedDetails(String searchDetails) {
        familyMemberSearchPage.searchFamilyMemberWithGivenParams(searchDetails);
    }

    @Then("the message will be displayed as {string} in {string} for the invalid field")
    public void theMessageWillBeDisplayedAsInForTheInvalidField(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheErrorMessageForInvalidField(errorMessage,messageColor);
        Assert.assertTrue(testResult);
    }

    @And("^the user types in valid details of a patient in the NHS number \"([^\"]*)\" and Date of Birth \"([^\"]*)\" fields$")
    public void theUserTypesInValidDetailsOfAPatientInTheNHSNumberAndDateOfBirthFields(String nhsNo, String dob) throws Throwable {
       familyMemberDetailsPage.searchPatientDetailsUsingNHSNumberAndDOB(nhsNo, dob);
    }

    @And("the user selects the patient search result tab")
    public void theUserSelectsThePatientSearchResultTab() {
        familyMemberDetailsPage.clickPatientCard();
    }

    @When("the user clicks the Save and Continue button in family member details page")
    public void theUserClicksTheSaveAndContinueButtonInFamilyMemberDetailsPage() {
        familyMemberDetailsPage.clickOnSaveAndContinueButton();
    }
    @Then("the message will be displayed as {string} in {string} for the invalid field in family member details page")
    public void theMessageWillBeDisplayedAsInForTheInvalidFieldInFamilyDetailsPage(String errorMessage, String messageColor) {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.checkTheErrorMessageForInvalidField(errorMessage,messageColor);
        Assert.assertTrue(testResult);
    }

    @And("the user fills the FamilyMemberDetailsPage with the {string}")
    public void theUserFillsTheFamilyMemberDetailsPageWithThe(String relationToProband) {
            familyMemberDetailsPage.fillTheRelationshipToProband(relationToProband);
    }
    @Then("the message will be displayed as {string} result found")
    public void theMessageWillBeDisplayedAsResultFound(String resultMessage) {
        boolean testResult = false;
        testResult = familyMemberSearchPage.checkTheResultMessageForFamilyMember(resultMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the family member details with the selected test are added to the referral")
    public void theFamilyMemberDetailsWithTheSelectedTestAreAddedToTheReferral() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyTheTestAndDetailsOfAddedFamilyMember();
        Assert.assertTrue(testResult);
    }

    @And("the user fills the DiseaseStatusDetails for family member with the with the {string}")
    public void theUserFillsTheDiseaseStatusDetailsForFamilyMember(String searchDetails) {
        familyMemberDetailsPage.fillFamilyMemberDiseaseStatusWithGivenParams(searchDetails);
    }

    @Then("the user returns to family member landing page with the added family member details")
    public void theUserReturnsToFamilyMemberLandingPageWithTheAddedFamilyMemberDetails() {
        boolean testResult = false;
        testResult = familyMemberDetailsPage.verifyAddedFamilyMemberDetailsInLandingPage();
        Assert.assertTrue(testResult);
    }
}//end
