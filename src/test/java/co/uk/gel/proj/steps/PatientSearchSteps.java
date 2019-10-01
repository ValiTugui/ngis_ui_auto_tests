package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class PatientSearchSteps extends Pages {

    public PatientSearchSteps(SeleniumDriver driver) {
        super(driver);
    }


    @Given("^a web browser is at the patient search page$")
    public void navigateToPatientSearchPage() {
        driver.get(AppConfig.getApp_url());
        patientSearchPage.loginToTestOrderingSystemAsServiceDeskUser(driver);

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
    public void theUserTypesInValidDetailsOfAPatientInTheNoOfFields(String searchDetails, String arg1) throws Throwable {

        patientSearchPage.fillInValidPatientDetailsUsingNOFields(searchDetails);
    }


    @Then("^The patient record is displayed with a heading of \"([^\"]*)\"$")
    public void thePatientRecordIsDisplayedWithAHeadingOf(String expectedResultHeader) throws Throwable {

         patientSearchPage.checkSearchResultHeaderIsDisplayed(driver, expectedResultHeader);

    }
}
