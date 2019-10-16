package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

public class PatientSearchSteps extends Pages {

    public PatientSearchSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("^a web browser is at the patient search page$")
    public void navigateToPatientSearchPage() {

        driver.get(AppConfig.getTo_patient_search_url());

        if (driver.getCurrentUrl().contains("patient-search")) {
            Wait.forElementToBeDisplayed(driver, patientSearchPage.pageTitle);
            Assert.assertTrue(patientSearchPage.pageTitle.isDisplayed());

        } else {
            if (driver.getCurrentUrl().contains("login.microsoft")) {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.emailAddressField);
                Assert.assertTrue(patientSearchPage.emailAddressField.isDisplayed());
                patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
            } else {
                if(patientSearchPage.logout.isDisplayed()) {
                    patientSearchPage.logout.click();
                    patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);
                }else
                    Debugger.println(" User is at url "+driver.getCurrentUrl());
            }
        }

    }


    @Then("^the default patient search page is correctly displayed with the NHS number and Date of Birth fields$")
    public void theDefaultPatientSearchPageIsCorrectlyDisplayedWithTheNHSNumberAndDateOfBirthFields() {
        boolean eachElementIsLoaded;
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenYesIsSelected();
        Assert.assertTrue(eachElementIsLoaded);
    }

    @And("^the YES button is selected by default on patient search$")
    public void theYESButtonIsSelectedByDefaultOnPatientSearch() {

        String selectedStatus = patientSearchPage.getYesBtnSelectedAttribute();
        Assert.assertEquals(selectedStatus,"true");

    }

    @And("^the background colour of the YES button is strong blue \"([^\"]*)\"$")
    public void theBackgroundColourOfTheYESButtonIsStrongBlue(String buttonColour) throws Throwable {
        String expectedYesButtonColour = StylesUtils.convertFontColourStringToCSSProperty(buttonColour);
        String actualYesButtonColour = patientSearchPage.getYesButtonColour();
        //  Assert.assertEquals(expectedButtonColour,"rgba(0, 94, 184, 1)");
        Assert.assertEquals(expectedYesButtonColour, actualYesButtonColour );
    }

    @Then("^the patient search page displays input fields such as DOB, First Name, Last Name, Gender, postcode and search buttons$")
    public void thePatientSearchPageDisplaysInputFieldsSuchAsDOBFirstNameLastNameGenderPostcodeAndSearchButtons() {

        boolean eachElementIsLoaded;
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenNoIsSelected();
        Assert.assertTrue(eachElementIsLoaded);

    }


    @When("^the user types in valid details of a \"([^\"]*)\" patient in the NHS number \"([^\"]*)\" and Date of Birth \"([^\"]*)\" fields$")
    public void theUserTypesInValidDetailsOfAPatientInTheNHSNumberAndDateOfBirthFields(String arg0, String nhsNo, String dob) throws Throwable {
       String[] value=  dob.split("-");  // Split DOB in the format 01-01-1900
       patientSearchPage.fillInValidPatientDetailsUsingNHSNumberAndDOB(nhsNo,value[0],value[1],value[2]);
    }


    @And("^the user clicks the Search button$")
    public void theUserClicksTheSearchButton() throws Throwable{

        patientSearchPage.clickSearchButtonByXpath(driver);
    }


    @Then("^a \"([^\"]*)\" result is successfully returned$")
    public void aResultIsSuccessfullyReturned(String badgeText) throws Throwable {
       patientSearchPage.checkThatPatientCardIsDisplayed(driver,badgeText);
    }


    @And("^the correct details of the \"([^\"]*)\" patient are displayed in the card$")
    public void theCorrectDetailsOfThePatientAreDisplayedInTheCard(String patientSearchType) throws Throwable {
        patientSearchPage.patientDetailsAreDisplayedInTheCard(patientSearchType);
    }


    @And("^the user clicks the NO button$")
    public void theUserClicksTheNOButton() throws Throwable {
        patientSearchPage.clickNoButton();
    }


    @When("^the user types in valid details \"([^\"]*)\" of a \"([^\"]*)\" patient in the No of Fields$")
    public void theUserTypesInValidDetailsOfAPatientInTheNoOfFields(String searchDetails, String patientSearchType) throws Throwable {

        patientSearchPage.fillInValidPatientDetailsUsingNOFields(searchDetails);
    }


    @Then("^The patient record is displayed with a heading of \"([^\"]*)\"$")
    public void thePatientRecordIsDisplayedWithAHeadingOf(String expectedResultHeader) throws Throwable {

         patientSearchPage.checkSearchResultHeaderIsDisplayed(driver, expectedResultHeader);

    }

    @Then("^the mandatory fields such as DOB , First Name, Last Name and Gender should be highlighted with a red mark$")
    public void theMandatoryFieldsSuchAsDOBFirstNameLastNameAndGenderShouldBeHighlightedWithARedMark() {
        patientSearchPage.validationErrorsAreDisplayedForSkippingMandatoryValuesDoYouHavePatientNHSNumberNO();
    }

    @Then("^the mandatory fields such as NHS Number and DOB should be highlighted with a red mark$")
    public void theMandatoryFieldsSuchAsNHSNumberAndDOBShouldBeHighlightedWithARedMark() {
        patientSearchPage.validationErrorsAreDisplayedForSkippingMandatoryValues();
    }

    @Then("^The message will be displayed as You’ve searched for \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void theMessageWillBeDisplayedAsYouVeSearchedFor(String nhsNumber, String dOB, String error_message) throws Throwable {
        patientSearchPage.checkNHSNumberAndDOBareDisplayed(nhsNumber, dOB, error_message );
    }

    @And("^There is a \"([^\"]*)\"link available$")
    public void thereIsALinkAvailable(String hyperLinkText) throws Throwable {
        patientSearchPage.checkCreateNewPatientLinkDisplayed(hyperLinkText);
    }

    @Then("^form labels should be consistent to font colour \"([^\"]*)\"$")
    public void formLabelsShouldBeConsistentToFontColour(String fontColor) throws Throwable {
        patientSearchPage.validateFormLabelColour(fontColor);
    }

    @And("^form labels should be consistent to font size \"([^\"]*)\"$")
    public void formLabelsShouldBeConsistentToFontSize(String fontSize) throws Throwable {
       patientSearchPage.validateFormLabelSize(fontSize);
    }

    @And("^form labels should be consistent to font face \"([^\"]*)\"$")
    public void formLabelsShouldBeConsistentToFontFace(String fontFace) throws Throwable {
       patientSearchPage.validateFormLabelFontFace(fontFace);
    }

    @Then("^the message will be displayed as \"([^\"]*)\" in \"([^\"]*)\" color$")
    public void theMessageWillBeDisplayedAsInColor(String errorMessage, String fontColor) throws Throwable {
        patientSearchPage.checkTheErrorMessage(errorMessage, fontColor);
    }

    @And("^the user clicks the patient result card$")
    public void theUserClicksThePatientResultCard() {
        patientSearchPage.clickPatientCard();
    }


    @When("^the user types in different valid details in the NHS number \"([^\"]*)\" and DOB \"([^\"]*)\" fields$")
    public void theUserTypesInDifferentValidDetailsInTheNHSNumberAndDOBFields(String nhsNo, String dob) throws Throwable {
        String[] value=  dob.split("-");  // Split DOB in the format 01-01-1900
        patientSearchPage.fillInDifferentValidPatientDetailsUsingNHSNumberAndDOB(nhsNo,value[0],value[1],value[2]);

    }

    @Then("^the correct details of the second \"([^\"]*)\" patient are displayed in the result card$")
    public void theCorrectDetailsOfTheSecondPatientAreDisplayedInTheResultCard(String patientSearchType) throws Throwable {
        patientSearchPage.secondPatientDetailsAreDisplayedInTheCard();
    }


    @When("^the user types in different valid details \"([^\"]*)\" of a \"([^\"]*)\" patient in the No of Fields$")
    public void theUserTypesInDifferentValidDetailsOfAPatientInTheNoOfFields(String searchDetails, String patientSearchType) throws Throwable {
        patientSearchPage.fillInValidSecondPatientDetailsUsingNOFields(searchDetails);
    }

    @Then("^the correct details of the second \"([^\"]*)\" patient using alternative searches are displayed in the result card$")
    public void theCorrectDetailsOfTheSecondPatientUsingAlternativeSearchesAreDisplayedInTheResultCard(String patientSearchType) throws Throwable {
        patientSearchPage.secondPatientDetailsAreDisplayedInTheCard();
    }


    @Then("^the display title of the page is \"([^\"]*)\"$")
    public void theDisplayTitleOfThePageIs(String titlePage) throws Throwable {
        patientSearchPage.verifyTheTitleOfThePage(titlePage);
    }

    @And("^the display description title contains the phrase \"([^\"]*)\"$")
    public void theDisplayDescriptionTitleContainsThePhrase(String descriptionOfPage) throws Throwable {
        patientSearchPage.verifyTheDescriptionOfThePage(descriptionOfPage);
    }

    @Then("^User clicks on a field \"([^\"]*)\" and auto-complete is disabled$")
    public void userClicksOnAFieldAndAutoCompleteIsDisabled(String allTextFields) throws Throwable {
        String[] textFieldElements =  allTextFields.split(":");  // Split all textFieldElement
        for (String eachElement : textFieldElements) {
            Debugger.println("Show eachElement: " + eachElement);
        }
        patientSearchPage.clickOnFieldsAndVerifyAutoCompleteIsDisabled(textFieldElements);
    }


    @Then("^the message will be displayed as \"([^\"]*)\" in \"([^\"]*)\" color for the DOB field$")
    public void theMessageWillBeDisplayedAsInColorForTheDOBField(String errorMessage, String fontColor) throws Throwable {
        patientSearchPage.checkTheErrorMessagesInDOB(errorMessage, fontColor);
    }

    @Then("^the non mandatory field \"([^\"]*)\" shouldn't be highlighted with a red mark$")
    public void theNonMandatoryFieldShouldnTBeHighlightedWithARedMark(String postcodeLabel) throws Throwable {
        patientSearchPage.checkThatPostcode(postcodeLabel);
    }

    @Then("^The message will be displayed as You’ve searched for \"([^\"]*)\" \"([^\"]*)\" in \"([^\"]*)\" font$")
    public void theMessageWillBeDisplayedAsYouVeSearchedForInFont(String expSearchString, String errorMessage, String fontFace) throws Throwable {
        patientSearchPage.checkTheNoPatientFoundLabel(expSearchString, errorMessage, fontFace);
    }
}
