package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.TestDataProvider.*;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.FamilyMemberDetailsPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.List;

import java.awt.*;
import java.util.Map;

public class PatientSearchSteps extends Pages {

    public PatientSearchSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the patient search page")
    public void aWebBrowserIsAtThePatientSearchPage(List<String> attributeOfUrl) {
        String baseURL = attributeOfUrl.get(0);
        String confirmationPage = attributeOfUrl.get(1);
        String userType = attributeOfUrl.get(2);
        if (userType != null) {
            NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage, userType);
        }

    }

    @Then("the Patient Search page is displayed")
    public void thePatientSearchPageIsDisplayed() {
        patientSearchPage.pageIsDisplayed();
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
        Assert.assertEquals(selectedStatus, "true");
    }

    @And("^the background colour of the YES button is strong blue \"([^\"]*)\"$")
    public void theBackgroundColourOfTheYESButtonIsStrongBlue(String buttonColour) throws Throwable {
        String expectedYesButtonColour = StylesUtils.convertFontColourStringToCSSProperty(buttonColour);
        String actualYesButtonColour = patientSearchPage.getYesButtonColour();
        //  Assert.assertEquals(expectedButtonColour,"rgba(0, 94, 184, 1)");
        Assert.assertEquals(expectedYesButtonColour, actualYesButtonColour);
    }

    @Then("^the patient search page displays input fields such as DOB, First Name, Last Name, Gender, postcode and search buttons$")
    public void thePatientSearchPageDisplaysInputFieldsSuchAsDOBFirstNameLastNameGenderPostcodeAndSearchButtons() {

        boolean eachElementIsLoaded;
        eachElementIsLoaded = patientSearchPage.verifyTheElementsOnPatientSearchAreDisplayedWhenNoIsSelected();
        Assert.assertTrue(eachElementIsLoaded);

    }

    @When("the user types in valid details of a {string} patient in the NHS number and Date of Birth fields")
    public void theUserTypesInValidDetailsOfAPatientInTheNHSNumberAndDateOfBirthFields(String patientType) throws Throwable {
        patientSearchPage.fillInNHSNumberAndDateOfBirth(patientType);

    }

    @When("^the user types in valid details of a \"([^\"]*)\" patient in the NHS number \"([^\"]*)\" and Date of Birth \"([^\"]*)\" fields$")
    public void theUserTypesInValidDetailsOfAPatientInTheNHSNumberAndDateOfBirthFields(String arg0, String nhsNo, String dob) throws Throwable {
        String[] value = dob.split("-");  // Split DOB in the format 01-01-1900
        patientSearchPage.fillInValidPatientDetailsUsingNHSNumberAndDOB(nhsNo, value[0], value[1], value[2]);
    }


    @And("^the user clicks the Search button$")
    public void theUserClicksTheSearchButton() throws Throwable {
        boolean testResult = false;
        testResult = patientSearchPage.clickSearchButtonByXpath();
        Assert.assertTrue(testResult);
    }


    @Then("^a \"([^\"]*)\" result is successfully returned$")
    public void aResultIsSuccessfullyReturned(String badgeText) throws Throwable {

        String actualBadge = patientSearchPage.checkThatPatientCardIsDisplayed();
        if(actualBadge == null) {
            Assert.fail("Could not read the Patient Card ID");
        }
        if(!actualBadge.equalsIgnoreCase(badgeText)) {
            Assert.fail("Actual Badge:"+actualBadge+",Expected:"+badgeText);
        }
    }


    @And("^the correct details of the \"([^\"]*)\" patient are displayed in the card$")
    public void theCorrectDetailsOfThePatientAreDisplayedInTheCard(String patientSearchType) throws Throwable {

        //patientSearchPage.patientDetailsAreDisplayedInTheCard(patientSearchType);

        switch (patientSearchType) {
            case "NHS Spine": {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.patientFullName);
                String actualFullName = patientSearchPage.patientFullName.getText().trim();
                //String expectedDateOfBirth =expectedDayOfBirth+"-" + TestUtils.convertMonthNumberToMonthForm(expectedMonthOfBirth)+ "-"+expectedYearOfBirth;
                Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + SpinePatientOne.DATE_OF_BIRTH);
                String actualFullDOB = patientSearchPage.patientDateOfBirth.getText().trim();
                String actualGender = patientSearchPage.patientGender.getText().trim();
                String actualNHSNumber = patientSearchPage.patientNSNo.getText().trim();
                String actualAddress = patientSearchPage.patientAddress.getText().trim();

                Debugger.println("Expected full name = " + SpinePatientOne.FULL_NAME + ", Actual full name " + actualFullName);
                Assert.assertEquals(SpinePatientOne.FULL_NAME, actualFullName);

                Debugger.println("Expected DOB = " + SpinePatientOne.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + SpinePatientOne.DATE_OF_BIRTH));

                Debugger.println("Expected Gender= " + SpinePatientOne.GENDER + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + SpinePatientOne.GENDER, actualGender);

                Debugger.println("Expected nhs no = " + SpinePatientOne.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + SpinePatientOne.NHS_NUMBER, actualNHSNumber);

                Debugger.println("Expected address = " + SpinePatientOne.FULL_ADDRESS + ", Actual address " + actualAddress);
                Assert.assertEquals(SpinePatientOne.FULL_ADDRESS, actualAddress);

                break;
            }
            case "NHS Spine2": {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.patientFullName);
                String actualFullName = patientSearchPage.patientFullName.getText().trim();
                //String expectedDateOfBirth =expectedDayOfBirth+"-" + TestUtils.convertMonthNumberToMonthForm(expectedMonthOfBirth)+ "-"+expectedYearOfBirth;
                Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + SpinePatientTwo.DATE_OF_BIRTH);
                String actualFullDOB = patientSearchPage.patientDateOfBirth.getText().trim();
                String actualGender = patientSearchPage.patientGender.getText().trim();
                String actualNHSNumber = patientSearchPage.patientNSNo.getText().trim();
                String actualAddress = patientSearchPage.patientAddress.getText().trim();

                Debugger.println("Expected full name = " + SpinePatientTwo.FULL_NAME + ", Actual full name " + actualFullName);
                Assert.assertEquals(SpinePatientTwo.FULL_NAME, actualFullName);

                Debugger.println("Expected DOB = " + SpinePatientTwo.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + SpinePatientTwo.DATE_OF_BIRTH));

                Debugger.println("Expected Gender= " + SpinePatientTwo.GENDER + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + SpinePatientTwo.GENDER, actualGender);

                Debugger.println("Expected nhs no = " + SpinePatientTwo.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + SpinePatientTwo.NHS_NUMBER, actualNHSNumber);

                Debugger.println("Expected address = " + SpinePatientTwo.FULL_ADDRESS + ", Actual address " + actualAddress);
                Assert.assertEquals(SpinePatientTwo.FULL_ADDRESS, actualAddress);

                break;
            }
            case "NGIS-redundant": {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.patientFullName);
                String actualFullName = patientSearchPage.patientFullName.getText().trim();
                Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + NgisPatientOne.DATE_OF_BIRTH);
                String actualFullDOB = patientSearchPage.patientDateOfBirth.getText().trim();
                String actualGender = patientSearchPage.patientGender.getText().trim();
                String actualNHSNumber = patientSearchPage.patientNSNo.getText().trim();
                String actualAddress = patientSearchPage.patientAddress.getText().trim();

                Debugger.println("Expected full name = " + NgisPatientOne.FULL_NAME + ", Actual full name " + actualFullName);
                Assert.assertEquals(NgisPatientOne.FULL_NAME, actualFullName);

                Debugger.println("Expected DOB = " + NgisPatientOne.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + NgisPatientOne.DATE_OF_BIRTH));

                Debugger.println("Expected Gender= " + NgisPatientOne.GENDER + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + NgisPatientOne.GENDER, actualGender);

                Debugger.println("Expected nhs no = " + NgisPatientOne.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + NgisPatientOne.NHS_NUMBER, actualNHSNumber);

                Debugger.println("Expected address = " + NgisPatientOne.FULL_ADDRESS + ", Actual address " + actualAddress);
                Assert.assertEquals(NgisPatientOne.FULL_ADDRESS, actualAddress);

                break;
            }
            case "NGIS2": {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.patientFullName);
                String actualFullName = patientSearchPage.patientFullName.getText().trim();
                Debugger.println("Expected date of birth re-formatted from dd-mm-yyyy to dd-mmm-yyyy: " + NgisPatientTwo.DATE_OF_BIRTH);
                String actualFullDOB = patientSearchPage.patientDateOfBirth.getText().trim();
                String actualGender = patientSearchPage.patientGender.getText().trim();
                String actualNHSNumber = patientSearchPage.patientNSNo.getText().trim();
                String actualAddress = patientSearchPage.patientAddress.getText().trim();

                Debugger.println("Expected full name = " + NgisPatientTwo.FULL_NAME + ", Actual full name " + actualFullName);
                Assert.assertEquals(NgisPatientTwo.FULL_NAME, actualFullName);

                Debugger.println("Expected DOB = " + NgisPatientTwo.DATE_OF_BIRTH + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + NgisPatientTwo.DATE_OF_BIRTH));

                Debugger.println("Expected Gender= " + NgisPatientTwo.GENDER + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + NgisPatientTwo.GENDER, actualGender);

                Debugger.println("Expected nhs no = " + NgisPatientTwo.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + NgisPatientTwo.NHS_NUMBER, actualNHSNumber);

                Debugger.println("Expected address = " + NgisPatientTwo.FULL_ADDRESS + ", Actual address " + actualAddress);
                Assert.assertEquals(NgisPatientTwo.FULL_ADDRESS, actualAddress);
                break;
            }
            case "NGIS": {
                Wait.forElementToBeDisplayed(driver, patientSearchPage.patientFullName);
                String actualFullName = patientSearchPage.patientFullName.getText().trim();
                String actualFullDOB = patientSearchPage.patientDateOfBirth.getText().trim();
                String actualGender = patientSearchPage.patientGender.getText().trim();
                String actualNHSNumber = patientSearchPage.patientNSNo.getText().trim();
                String actualAddress = patientSearchPage.patientAddress.getText().trim();

                NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
                String expectedFullName = newPatient.getLastName().toUpperCase() + ", " + newPatient.getFirstName() + " (" + newPatient.getTitle() +  ")";
                Debugger.println("Expected full name = " + expectedFullName+ ", Actual full name " + actualFullName);
                Assert.assertEquals(expectedFullName, actualFullName);

                String expectedDateOfBirth = newPatient.getDay() + "-" + TestUtils.convertMonthNumberToMonthForm(newPatient.getMonth()) + "-" + newPatient.getYear();
                Debugger.println("Expected DOB = " + expectedDateOfBirth  + ", Actual DOB: " + actualFullDOB);
                Assert.assertTrue(actualFullDOB.contains("Born " + expectedDateOfBirth));

                Debugger.println("Expected Gender= " + newPatient.getGender() + ", Actual Gender: " + actualGender);
                Assert.assertEquals("Gender " + newPatient.getGender(), actualGender);

                Debugger.println("Expected nhs no = " + newPatient.getNhsNumber() + ", Actual nhs no: " + actualNHSNumber);
                Assert.assertEquals("NHS No. " + newPatient.getNhsNumber(), actualNHSNumber);

                Debugger.println("Expected post-code " + newPatient.getPostCode() + " Actual address " + actualAddress);
                Assert.assertTrue(actualAddress.contains(newPatient.getPostCode()));
                break;
            }
            default:

                throw new IllegalArgumentException("Invalid query search parameters");

        }

    }

    @And("^the user clicks the NO button$")
    public void theUserClicksTheNOButton() throws Throwable {
        boolean testResult = false;
        testResult = patientSearchPage.clickNoButton();
        Assert.assertTrue(testResult);
    }


    @When("^the user types in valid details \"([^\"]*)\" of a \"([^\"]*)\" patient in the No of Fields$")
    public void theUserTypesInValidDetailsOfAPatientInTheNoOfFields(String searchDetails, String patientSearchType) throws Throwable {
        boolean testResult = false;
        testResult = patientSearchPage.fillInValidPatientDetailsUsingNOFields(searchDetails);
        Assert.assertTrue(testResult);
    }


    @When("the user fills in invalid patient details {string} in the search fields when No is selected")
    public void theUserFillsInInvalidPatientDetailsInTheSearchFieldsWhenNoIsSelected(String invalidPatientDetails) {
        patientSearchPage.fillInValidPatientDetailsUsingNOFields(invalidPatientDetails);
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
        patientSearchPage.checkNHSNumberAndDOBareDisplayed(nhsNumber, dOB, error_message);
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
        boolean testResult = false;
        testResult = patientSearchPage.clickPatientCard();
        Assert.assertTrue(testResult);
    }


    @When("^the user types in different valid details in the NHS number \"([^\"]*)\" and DOB \"([^\"]*)\" fields$")
    public void theUserTypesInDifferentValidDetailsInTheNHSNumberAndDOBFields(String nhsNo, String dob) throws Throwable {
        String[] value = dob.split("-");  // Split DOB in the format 01-01-1900
        patientSearchPage.fillInDifferentValidPatientDetailsUsingNHSNumberAndDOB(nhsNo, value[0], value[1], value[2]);

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

    @And("the patient search page displays the sub-titles text {string}")
    public void thePatientSearchPageDisplaysTheSubTitlesText(String descriptionOfPage) {
        patientSearchPage.verifyTheDescriptionOfThePage(descriptionOfPage);
    }

    @Then("^User clicks on a field \"([^\"]*)\" and auto-complete is disabled$")
    public void userClicksOnAFieldAndAutoCompleteIsDisabled(String allTextFields) throws Throwable {
        String[] textFieldElements = null;
        if(allTextFields.indexOf(":") != -1) {
            textFieldElements = allTextFields.split(":");  // Split all textFieldElement
        }else{//If only one field is present
            textFieldElements = new String[]{allTextFields};
        }
        boolean testResult = patientSearchPage.clickOnFieldsAndVerifyAutoCompleteIsDisabled(textFieldElements);
        Assert.assertTrue(testResult);
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


    @And("the user types in valid details of a patient in the NHS number and DOB fields")
    public void theUserTypesInValidDetailsOfAPatientInTheNHSNumberAndDOBFields(List<String> patientTypesList) throws IOException {
        String patientType = patientTypesList.get(0);
        if (patientType.equalsIgnoreCase("NGIS"))
            patientSearchPage.fillInNHSNumberAndDateOfBirthByProvidingNGISPatientOne();
    }

    @And("the user searches for a patient by providing valid details of NHS number and DOB fields in the patient search page")
    public void theUserSearchesForAPatientByProvidingValidDetailsOfNHSNumberAndDOBFieldsInThePatientSearchPage(List<String> patientTypesList) throws IOException {
        theUserTypesInValidDetailsOfAPatientInTheNHSNumberAndDOBFields(patientTypesList);
        patientSearchPage.clickSearchButtonByXpath();
    }

    @When("the user types in invalid details of a patient in the NHS number and DOB fields")
    public void theUserTypesInInvalidDetailsOfAPatientInTheNHSNumberAndDOBFields() {
        boolean testResult = false;
        testResult = patientSearchPage.fillInNonExistingPatientDetailsUsingNHSNumberAndDOB();
        Assert.assertTrue(testResult);
    }


    @And("the user clicks the {string} link from the No Search Results page")
    public void theUserClicksTheLinkFromTheNoSearchResultsPage(String hyperLinkText) {
        patientSearchPage.checkCreateNewPatientLinkDisplayed(hyperLinkText);
        patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
    }

    @And("the user types in invalid details of a patient in the NO fields")
    public void theUserTypesInInvalidDetailsOfAPatientInTheNOFields() {
        boolean testResult = false;
        testResult = patientSearchPage.fillInInvalidPatientDetailsInTheNOFields();
        Assert.assertTrue(testResult);
    }

    @And("the fields from NO section are pre-populated in the new patient page from the search page")
    public void theFieldsFromNOSectionArePrePopulatedInTheNewPatientPageFromTheSearchPage() {
        patientSearchPage.noFieldsArePrePopulatedInNewPatientPage();
    }

    @And("the correct details of the {string} are displayed in patient details")
    public void theCorrectDetailsOfTheAreDisplayedInPatientDetails(String patientType) throws Throwable {

        switch (patientType) {
            case "NHS Spine": {
                String actualPrefix = patientDetailsPage.title.getAttribute("value");
                String actualFirstName = patientDetailsPage.firstName.getAttribute("value");
                String actualLastName = patientDetailsPage.familyName.getAttribute("value");
                String actualFullDOB = patientDetailsPage.dateOfBirth.getAttribute("value");
                String actualGender = patientDetailsPage.administrativeGenderButton.getText().trim();
                String actualLifeStatus = patientDetailsPage.lifeStatusButton.getText().trim();
                String actualNHSNumber = patientDetailsPage.nhsNumber.getAttribute("value");
                String expectedDOB = SpinePatientOne.DAY_OF_BIRTH + "/" + SpinePatientOne.MONTH_OF_BIRTH + "/" + SpinePatientOne.YEAR_OF_BIRTH;

                Debugger.println("Expected Prefix = " + SpinePatientOne.TITLE + ", Actual Prefix : " + actualPrefix);
                Debugger.println("Expected FirstName = " + SpinePatientOne.FIRST_NAME + ", Actual FirstName: " + actualFirstName);
                Debugger.println("Expected LastName = " + SpinePatientOne.LAST_NAME + ", Actual LastName: " + actualLastName);
                Debugger.println("Expected DOB = " + expectedDOB + ", Actual DOB: " + actualFullDOB);
                Debugger.println("Expected Gender= " + SpinePatientOne.GENDER + ", Actual Gender: " + actualGender);
                Debugger.println("Expected Life Status = " + SpinePatientOne.LIFE_STATUS + ", Actual Life Status: " + actualLifeStatus);
                Debugger.println("Expected nhs no = " + SpinePatientOne.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);

                Assert.assertEquals(SpinePatientOne.TITLE, actualPrefix);
                Assert.assertEquals(SpinePatientOne.FIRST_NAME, actualFirstName);
                Assert.assertEquals(SpinePatientOne.LAST_NAME, actualLastName);
                Assert.assertEquals(expectedDOB, actualFullDOB);
                Assert.assertEquals(SpinePatientOne.GENDER, actualGender);
                Assert.assertEquals(SpinePatientOne.LIFE_STATUS, actualLifeStatus);
                Assert.assertEquals(SpinePatientOne.NHS_NUMBER, actualNHSNumber);

                break;
            }
            case "NGIS-Redundant-Static": { // used for static data
                String actualPrefix = patientDetailsPage.title.getAttribute("value");
                String actualFirstName = patientDetailsPage.firstName.getAttribute("value");
                String actualLastName = patientDetailsPage.familyName.getAttribute("value");
                String actualFullDOB = patientDetailsPage.dateOfBirth.getAttribute("value");
                String actualGender = patientDetailsPage.administrativeGenderButton.getText().trim();
                String actualLifeStatus = patientDetailsPage.lifeStatusButton.getText().trim();

                // Wait.getFieldValue(dateOfBirth);
                String actualNHSNumber = patientDetailsPage.nhsNumber.getAttribute("value");
                String expectedDOB = NgisPatientOne.DAY_OF_BIRTH + "/" + NgisPatientOne.MONTH_OF_BIRTH + "/" + NgisPatientOne.YEAR_OF_BIRTH;

                Debugger.println("Expected Prefix = " + NgisPatientOne.TITLE + ", Actual Prefix : " + actualPrefix);
                Debugger.println("Expected FirstName = " + NgisPatientOne.FIRST_NAME + ", Actual FirstName: " + actualFirstName);
                Debugger.println("Expected LastName = " + NgisPatientOne.LAST_NAME + ", Actual LastName: " + actualLastName);
                Debugger.println("Expected DOB = " + expectedDOB + ", Actual DOB: " + actualFullDOB);
                Debugger.println("Expected Gender= " + NgisPatientOne.GENDER + ", Actual Gender: " + actualGender);
                Debugger.println("Expected Gender= " + NgisPatientOne.LIFE_STATUS + ", Actual Gender: " + actualLifeStatus);
                Debugger.println("Expected nhs no = " + NgisPatientOne.NHS_NUMBER + ", Actual nhs no: " + actualNHSNumber);

                Assert.assertEquals(NgisPatientOne.TITLE, actualPrefix);
                Assert.assertEquals(NgisPatientOne.FIRST_NAME, actualFirstName);
                Assert.assertEquals(NgisPatientOne.LAST_NAME, actualLastName);
                Assert.assertEquals(expectedDOB, actualFullDOB);
                Assert.assertEquals(NgisPatientOne.GENDER, actualGender);
                Assert.assertEquals(NgisPatientOne.LIFE_STATUS, actualLifeStatus);
                Assert.assertEquals(NgisPatientOne.NHS_NUMBER, actualNHSNumber);

                break;
            }
            case "NGIS": {
                String actualPrefix = patientDetailsPage.title.getAttribute("value");
                String actualFirstName = patientDetailsPage.firstName.getAttribute("value");
                String actualLastName = patientDetailsPage.familyName.getAttribute("value");
                String actualFullDOB = patientDetailsPage.dateOfBirth.getAttribute("value");
                String actualGender = patientDetailsPage.administrativeGenderButton.getText().trim();
                String actualLifeStatus = patientDetailsPage.lifeStatusButton.getText().trim();
                //String actualNHSNumber = patientDetailsPage.nhsNumber.getAttribute("value");

                NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
                String expectedDOB = newPatient.getDay() + "/" + newPatient.getMonth() + "/" + newPatient.getYear();
                Debugger.println("Expected Prefix = " + newPatient.getTitle() + ", Actual Prefix : " + actualPrefix);
                Debugger.println("Expected FirstName = " + newPatient.getFirstName() + ", Actual FirstName: " + actualFirstName);
                Debugger.println("Expected LastName = " + newPatient.getLastName() + ", Actual LastName: " + actualLastName);
                Debugger.println("Expected DOB = " + expectedDOB + ", Actual DOB: " + actualFullDOB);
                Debugger.println("Expected Gender= " + newPatient.getGender() + ", Actual Gender: " + actualGender);
                //Debugger.println("Expected nhs no = " + newPatient.getNhsNumber() + ", Actual nhs no: " + actualNHSNumber);

                Assert.assertEquals(newPatient.getTitle(), actualPrefix);
                Assert.assertEquals(newPatient.getFirstName(), actualFirstName);
                Assert.assertEquals(newPatient.getLastName(), actualLastName);
                Assert.assertEquals(expectedDOB, actualFullDOB);
                Assert.assertEquals(newPatient.getGender(), actualGender);
                break;
            }

            default:

                throw new IllegalArgumentException("Invalid query search parameters");

        }

    }

    @And("^User place the cursor over the tab in which the Dashboard - Home page is opened$")
    public void theUserPlaceTheCursorOverTheTab() throws AWTException {
        Click.mouseMoveByLocation(driver, 5, 200);
    }

    @Then("The user should see the tab title as {string}")
    public void theUserShouldSeeTheTabTitleAs(String titleText) {
        Assert.assertTrue("The Correct Text is NOT Displayed", patientSearchPage.windowTitleValidation(titleText));
    }

    @And("the user navigates back to patient search page")
    public void theUserNavigatesBackToPatientSearchPage(List<String> attributeOfUrl) {
        aWebBrowserIsAtThePatientSearchPage(attributeOfUrl);
    }

    @Then("the NHS number field is not enabled with auto-fill feature")
    public void theNHSNumberFieldIsNotEnabledWithAutoFillFeature() {
        Assert.assertTrue(patientSearchPage.confirmAutoCompleteOffOnNHSNumberField());
    }

    @When("the user types in the same {string} patient record in the patient search page")
    public void theUserTypesInTheSamePatientRecordInThePatientSearchPage(String patientRecordType) {
        NewPatient newPatient = patientDetailsPage.getNewlyCreatedPatientData();
        String dayOfBirth = newPatient.getDay();
        String monthOfBirth = newPatient.getMonth();
        String yearOfBirth = newPatient.getYear();
        String firstName = newPatient.getFirstName();
        String lastName = newPatient.getLastName();
        String gender = newPatient.getGender();
        String postCode = newPatient.getPostCode();
        String searchParams = "DOB="+dayOfBirth +"-" +monthOfBirth +"-" + yearOfBirth+":FirstName="+firstName+":LastName="+lastName+":Gender="+gender+":Postcode="+postCode;
        Debugger.println("Search Params "+ searchParams);
        patientSearchPage.useTheSameTestDataUsedForCreatingReferralInUseCase29Tests(searchParams);
    }

    @And("the user search for the new patient using date of birth, first name, last name and gender")
    public void theUserSearchForTheNewPatientUsingDateOfBirthFirstNameLastNameAndGender() {
        boolean testResult = false;
        testResult = patientSearchPage.fillInNewPatientDetailsInTheNoFields();
        Assert.assertTrue(testResult);
    }


    @And("the user search for the new patient using date of birth, first name, last name and edited gender {string}")
    public void theUserSearchForTheNewPatientUsingDateOfBirthFirstNameLastNameAndEditedGender(String editedGender) {
        Wait.seconds(7); // Wait for the patient to be updated in the database and for it to be retrieved back
        patientSearchPage.fillInNewPatientDetailsInTheNoFieldsWithEditedGender(editedGender);
    }

    @And("the user types in the details of the NGIS patient in the NHS number and DOB fields")
    public void theUserTypesInTheDetailsOfTheNGISPatientInTheNHSNumberAndDOBFields() {
        boolean testResult = false;
        testResult = patientSearchPage.fillInNewPatientDetailsInTheYesFields();
        Assert.assertTrue(testResult);
    }

    @And("the user search for the new patient using date of birth, first name, last name, gender and post-code")
    public void theUserSearchForTheNewPatientUsingDateOfBirthFirstNameLastNameGenderAndPostCode() {
        patientSearchPage.fillInNewPatientDetailsWithPostCodeInTheNoFields();
    }

    @Then("no validation error red mark highlighted on the DOB field")
    public void noValidationErrorRedMarkHighlightedOnTheDOBField() {
        //click on NHSLabel label to move cursor away from DOB field
        Actions.retryClickAndIgnoreElementInterception(driver,patientSearchPage.nhsNumberLabel);
        Wait.forElementToBeDisplayed(driver, patientSearchPage.dateOfBirthLabel);
        Assert.assertEquals(StylesUtils.convertFontColourStringToCSSProperty("#212b32"), patientSearchPage.dateOfBirthLabel.getCssValue("color"));
    }

    @Then("the message {string} is displayed below the search button")
    public void theMessageIsDisplayedBelowTheSearchButton(String expectedMessage) {
        String actualNoPatientFoundLabel = patientSearchPage.getPatientSearchNoResult();
        if(actualNoPatientFoundLabel == null){
            SeleniumLib.takeAScreenShot("NoSearchResult.jpg");
            Assert.assertTrue(false);
        }
        if(!expectedMessage.equalsIgnoreCase(actualNoPatientFoundLabel)){
            SeleniumLib.takeAScreenShot("NoSearchResult.jpg");
            Assert.assertTrue(false);
        }
    }

    @Then("the NHS number field remains empty as invalid characters are not accepted")
    public void theNHSNumberFieldRemainsEmptyAsInvalidCharactersAreNotAccepted() {
        Assert.assertTrue(Actions.getText(patientSearchPage.nhsNumber).isEmpty()); //NHS number field is empty
    }

    @And("the user sees placeholder texts displayed in the fields - Date of birth {string}, First name {string}, Last name {string} and Postcode {string}")
    public void theUserSeesPlaceholderTextsDisplayedInTheFieldsDateOfBirthFirstNameLastNameAndPostcode(String expectedDOBPlaceHolder, String expectedFirstNamePlaceHolder, String expectedLastNamePlaceHolder, String expectedPostCodePlaceHolder) {

        String actualDOBPlaceHolder = Actions.getPlaceHolderAttribute(patientSearchPage.dateDay) + "-" + Actions.getPlaceHolderAttribute(patientSearchPage.dateMonth) + "-" + Actions.getPlaceHolderAttribute(patientSearchPage.dateYear);
        String actualFirstNamePlaceHolder = Actions.getPlaceHolderAttribute(patientSearchPage.firstName);
        String actualLastNamePlaceHolder = Actions.getPlaceHolderAttribute(patientSearchPage.lastName);
        String actualPostCodePlaceHolder = Actions.getPlaceHolderAttribute(patientSearchPage.postcode);

        Debugger.println("Actual placeholder-text: " + "DOB:" + actualDOBPlaceHolder + " FName:" + actualFirstNamePlaceHolder + " LName:" + actualLastNamePlaceHolder + " PostCode:" + actualPostCodePlaceHolder);
        Debugger.println("Expected placeholder-text: " + "DOB:" + expectedDOBPlaceHolder + " FName: " + expectedFirstNamePlaceHolder + " LName:" + expectedLastNamePlaceHolder + " PostCode:" + expectedPostCodePlaceHolder);
        Assert.assertEquals(expectedDOBPlaceHolder,actualDOBPlaceHolder);
        Assert.assertEquals(expectedFirstNamePlaceHolder, actualFirstNamePlaceHolder);
        Assert.assertEquals(expectedLastNamePlaceHolder,actualLastNamePlaceHolder);
        Assert.assertEquals(expectedPostCodePlaceHolder,actualPostCodePlaceHolder);
    }


    @And("the following drop-down values are displayed for gender")
    public void theFollowingDropDownValuesAreDisplayedForGender(DataTable dataTable) {
        List<Map<String, String>> expectedGenderList = dataTable.asMaps(String.class, String.class);
        List<String> actualGenderList = patientSearchPage.getTheGenderDropDownValues();
        for (int i = 0; i < expectedGenderList.size(); i++) {
            Debugger.println("Expected gender: " + expectedGenderList.get(i).get("genderListHeader") + ":" + i + ":" + "Actual gender list " + actualGenderList.get(i) + "\n");
            Assert.assertEquals(expectedGenderList.get(i).get("genderListHeader"), actualGenderList.get(i));
        }
    }

    @And("the user see a tick mark next to the YES button")
    public void theUserSeeATickMarkNextToTheYESButton() {
        Assert.assertTrue(patientSearchPage.ensureTickMarkIsDisplayedNextToYesButton());
    }

    @And("the user see a tick mark next to the NO button")
    public void theUserSeeATickMarkNextToTheNOButton() {
        Assert.assertTrue(patientSearchPage.ensureTickMarkIsDisplayedNextToNoButton());
    }

    @And("the user clicks on the hyper link")
    public void theUserClicksOnTheHyperLink() {
        boolean testResult = false;
        testResult = patientSearchPage.clickCreateNewPatientLinkFromNoSearchResultsPage();
        Assert.assertTrue(testResult);
    }

    @And("the display question for NHS Number is (.*)$")
    public void theDisplayQuestionForNHSNumber(String nhsQuestion) {
        boolean testResult = false;
        testResult = patientSearchPage.verifyTheNHSQuestionOfThePage(nhsQuestion);
        Assert.assertTrue(testResult);
    }
}
