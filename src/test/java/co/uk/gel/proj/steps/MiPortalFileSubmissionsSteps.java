package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static co.uk.gel.proj.pages.PatientDetailsPage.newPatient;


public class MiPortalFileSubmissionsSteps extends Pages {

    public MiPortalFileSubmissionsSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("a web browser is at the mi-portal home page")
    public void aWebBrowserIsAtTheMiPortalHomePage(List<String> attributeOfUrl) {

        String baseURL = attributeOfUrl.get(0);
        String confirmationPage = attributeOfUrl.get(1);
        String userType = attributeOfUrl.get(2);
        if (userType != null) {
            NavigateTo(AppConfig.getPropertyValueFromPropertyFile(baseURL), confirmationPage, userType);
        }
        Wait.seconds(1);

    }


    @When("the user navigates to the mi-portal {string} stage")
    public void theUserNavigatesToTheMiPortalStage(String arg0) {
        Actions.clickElement(driver, miPortalFileSubmissionPage.fileSubmissionLnk);
    }


    @And("the user selects a value {string} from the file-submission search column drop-down")
    public void theUserSelectsAValueFromTheFileSubmissionSearchColumnDropDown(String value) {
        miPortalFileSubmissionPage.selectSearchValueDropDown(miPortalFileSubmissionPage.dropDownFileSubmissionsSearch, value);
    }


    @And("the user selects a search operator {string} from the file-submission search operator drop-down")
    public void theUserSelectsASearchOperatorFromTheFileSubmissionSearchOperatorDropDown(String searchOperator) {
        miPortalFileSubmissionPage.selectSearchValueDropDown(miPortalFileSubmissionPage.dropDownFileSubmissionSearchOperator, searchOperator);
    }

    @And("the user enters a date {string} in the file-submission date field")
    public void theUserEntersADateInTheFileSubmissionDateField(String date) {

//        Wait.seconds(2);
//        miPortalFileSubmissionPage.getFileSubmissionDate.click();
//        miPortalFileSubmissionPage.getFileSubmissionDate.clear();
//        Wait.seconds(3);
//        miPortalFileSubmissionPage.getFileSubmissionDate.sendKeys("12-02-2020");
    }

    @And("the user clicks on Add criteria button")
    public void theUserClicksOnAddCriteriaButton() {
        Actions.clickElement(driver,miPortalFileSubmissionPage.addButton);
    }

    @Then("file submission search criteria badge information is displayed below drop-down buttons")
    public void fileSubmissionSearchCriteriaBadgeInformationIsDisplayedBelowDropDownButtons() {
    }

    @When("the user click on the Search button")
    public void theUserClickOnTheSearchButton() {
        Actions.clickElement(driver,miPortalFileSubmissionPage.searchButton);
        Wait.seconds(2);
    }

    @Then("search results are displayed for the file-submission search")
    public void searchResultsAreDisplayedForTheFileSubmissionSearch() {
        //ToDo
    }

    @And("the user is able to see the field values - Filenames {string}, Status {string}, ErrorMessage {string} and WarningMessage {string}")
    public void theUserIsAbleToSeeTheFieldValuesFilenamesStatusErrorMessageAndWarningMessage(String expectedFileName, String expectedStatus, String expectedErrorMessage, String expectedWarningMessage) {
        Map<String, String> Test = new HashMap<>();
        Test = miPortalFileSubmissionPage.getValuesOfSearchedResult(expectedFileName);
        Debugger.println("Test " + Test);
        expectedErrorMessage = "{\"non_field_errors\":[\"Sample received datetime must be set if the sample has been received\"]}, {\"non_field_errors\":[\"Sample received datetime must be set if the sample has been received\"]}";
        Assert.assertEquals(expectedFileName, Test.get("Filename"));
        Assert.assertEquals(expectedStatus, Test.get("Status"));
        Assert.assertEquals(expectedWarningMessage, Test.get("Warning Msgs"));
    }
}