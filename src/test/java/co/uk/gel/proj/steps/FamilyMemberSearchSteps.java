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

    @When("the user navigates to the family member search Page")
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
}//end
