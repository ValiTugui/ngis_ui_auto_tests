package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NeatToolSteps extends Pages {

    public NeatToolSteps(SeleniumDriver driver) {
        super(driver);
    }

    public static String searchNgisId = null;

    @Given("the user logs into the NEAT admin tool with the following credentials")
    public void theUserLogsIntoTheNEATAdminToolWithTheFollowingCredentials(List<String> loginDetailsInput) {
        boolean testResult = false;
        String baseURL = loginDetailsInput.get(0);
        String pageToNavigate = loginDetailsInput.get(1);
        String userType = loginDetailsInput.get(2);
        //Get the URL from Properties file
        String requiredPage = AppConfig.getPropertyValueFromPropertyFile(baseURL);
        Debugger.println("Opening the URL: " + requiredPage + ",\n and Page:" + pageToNavigate);
        driver.get(requiredPage);
        Wait.seconds(10);//Wait for 10 Seconds for the page to load
        String navigatedURL = driver.getCurrentUrl();
        Debugger.println("Current URL before LOGIN is :" + navigatedURL);
        if (navigatedURL.contains("login.microsoft")) {
            if (userType != null) {
                patientSearchPage.loginToTestOrderingSystem(driver, userType);
            } else {
                Assert.fail("There is No User details provided to login.");
            }
        } else if (navigatedURL.contains(pageToNavigate)) {
            Debugger.println("Already logged in, current URL after LOGIN :" + navigatedURL);
        }
        navigatedURL = driver.getCurrentUrl();
        Debugger.println("Current URL after LOGIN :" + navigatedURL);
        if (!navigatedURL.contains(pageToNavigate)) {
            Debugger.println("The open page after login is not correct..." + navigatedURL + "Reloading page..." + requiredPage);
            driver.get(requiredPage);
            Wait.seconds(2);//Wait for page load
        }
        testResult = neatHomePage.openAndVerifyNeatToolPage();
        Assert.assertTrue(testResult);
    }

    @And("the user stores the generated Patient NGIS-ID")
    public void theUserStoresTheGeneratedPatientNGISID() {
        boolean testResult = false;
        searchNgisId = referralPage.getPatientNGISId();
        if (searchNgisId == null || searchNgisId.isEmpty()) {
            Debugger.println("Did not catch the generated NGIS Id.");
        } else {
            Debugger.println("The generated NGIS Id:" + searchNgisId);
            testResult = true;
        }
        Assert.assertTrue(testResult);
    }

    @And("the user searches the NGIS-ID in the search box")
    public void theUserSearchesTheNGISIdInTheSearchBox() {
        boolean testResult = false;
        testResult = neatHomePage.enterAndSearchPatientNGISId(searchNgisId);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on the link {string}")
    public void theUserClicksOnTheLinkBackToPatientSearch(String linkText) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.clickOnLink(linkText);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on the status button {string}")
    public void theUserClicksOnTheStatusButton(String buttonText) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.clickOnButton(buttonText);
        Assert.assertTrue(testResult);
    }

    @And("the confirmation dialog box appears with the heading {string}")
    public void theConfirmationDialogBoxAppearsWithTheHeading(String dialogHeader) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.verifyDialogBox(dialogHeader);
        Assert.assertTrue(testResult);
    }

    @And("the user enters the justification reason in the text box as {string}")
    public void theUserEntersTheJustificationReasonInTheTextBoxAs(String reason) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.fillJustificationTextBox(reason);
        Assert.assertTrue(testResult);
    }

    @Then("the user sees the notification {string}")
    public void theUserSeesTheNotification(String notificationText) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.validateNotification(notificationText);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on the reason button {string}")
    public void theUserClicksOnTheReasonButton(String buttonText) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.clickReasonButton(buttonText);
        Assert.assertTrue(testResult);
    }

    @Then("the user sees the Patient record status as {string}")
    public void theUserSeesThePatientRecordStatusAs(String status) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.validatePatientRecordStatus(status);
        Assert.assertTrue(testResult);
    }

    @Then("the user verifies the patient record history")
    public void theUserVerifiesThePatientRecordHistory() {
        boolean testResult = false;
        testResult = neatPatientRecordPage.verifyPatientRecordHistoryDisplay();
        Assert.assertTrue(testResult);
    }

    @And("the user will able to see the table with below details")
    public void theUserWillAbleToSeeTheTableWithBelowDetails(DataTable fieldName) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.verifyPatientRecordTable(fieldName);
        Assert.assertTrue(testResult);
    }

    @And("the user sees the {string} and {string}")
    public void theUserSeesTheAnd(String currentStatus, String Updatedstatus) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.verifyPatientRecordStatusField(currentStatus, Updatedstatus);
        Assert.assertTrue(testResult);
    }

    @And("the user Sees the {string}")
    public void theUserSeesThe(String currentStatus) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.verifyRecordStatusDetails(currentStatus);
        Assert.assertTrue(testResult);
    }

    @And("The user sees the Reason section with toggle options Duplicate and Created in error")
    public void theUserSeesTheReasonSectionWithToggleOptionsDuplicateAndCreatedInError() {
        boolean testResult = false;
        testResult = neatPatientRecordPage.verifyThePatientRecordReasonOptions();
        Assert.assertTrue(testResult);
    }

    @Then("The user should be able to see the {string} reason displayed at Patient record status")
    public void theUserShouldBeAbleToSeeTheReasonDisplayedAtPatientRecordStatus(String reason) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.verifyTheReasonInPatientRecordStatus(reason);
        Assert.assertTrue(testResult);
    }

    @Then("the blank mandatory field labels highlighted in red color")
    public void theBlankMandatoryFieldsHighlightedInRedColor(DataTable fields) {
        boolean testResult = false;
        List<List<String>> messageDetails = fields.asLists();
        for (int i = 1; i < messageDetails.size(); i++) {
            testResult = neatPatientRecordPage.mandatoryfieldvalidation(messageDetails.get(i).get(0), messageDetails.get(i).get(1));
            Debugger.println("Test check");
            Assert.assertTrue(testResult);
        }
    }

    @And("the user searches the {string}")
    public void theUserSearchesThe(String ngisID) {
        boolean testResult = false;
        testResult = neatHomePage.enterAndSearchPatientNGISId(ngisID);
        Assert.assertTrue(testResult);
    }

    @And("the user has to click on {string} link present in warning message")
    public void theUserHasToClickOnLinkPresentInWarningMessage(String activeRecordLink) {
        boolean testResult = false;
        testResult = neatPatientRecordPage.clickOnActiveRecordLink(activeRecordLink);
        Assert.assertTrue(testResult);
    }

    @Then("User should be able to see the same patient details with old NGIS id")
    public void userShouldBeAbleToSeeTheSamePatientDetailsWithOldNGISId() {
        boolean testResult = false;
        testResult = neatHomePage.verifyNgisID(searchNgisId);
        Assert.assertTrue(testResult);
    }

    @Then("the user sees the result as NGIS patient and converts that into SPINE patient from the NEAT Tool")
    public void theUserSeesTheResultAsNGISPatientAndConvertsThatIntoSPINEPatientFromTheNEATTool() {
        boolean testResult=false;
        //Checking for NGIS
        String actualBadge = patientSearchPage.checkThatPatientCardIsDisplayed();
        if(actualBadge == null) {
            Assert.fail("Could not read the Patient Card ID");
        }
        if(actualBadge.equalsIgnoreCase("NGIS")) {
            //Steps integrated to goto NEAT Tool and convert to SPINE
            //Click on patient result card
            testResult = patientSearchPage.clickPatientCard();
            Assert.assertTrue(testResult);
            //Move onto Patient record details page
            testResult = patientDetailsPage.patientDetailsPageIsDisplayed();
            Assert.assertTrue(testResult);
            //Pick up the NGIS ID
            theUserStoresTheGeneratedPatientNGISID();
            //Log out from the test order
            Wait.seconds(2);
            testResult = referralPage.clickLogoutButton();
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Logout.jpg");
                Assert.fail("Could not logout");
            }
            //Log in to NEAT Tool
            List<String> neatLoginCredentials= new ArrayList<>(Arrays.asList("NEAT_URL", "find-patient-record", "GEL_SUPER_USER"));
            theUserLogsIntoTheNEATAdminToolWithTheFollowingCredentials(neatLoginCredentials);
            //Check the page Title
            testResult = referralPage.verifyThePageTitlePresence("Find a patient record");
            Assert.assertTrue(testResult);
            //Search the NGIS ID
            theUserSearchesTheNGISIdInTheSearchBox();
            //Check page title Edit this patient record
            testResult = referralPage.verifyThePageTitlePresence("Edit this patient record");
            Assert.assertTrue(testResult);
            //Change status to inactive
            theUserClicksOnTheStatusButton("Change status to inactive");
            //Verify confirmation dialog box
            theConfirmationDialogBoxAppearsWithTheHeading("Are you sure?");
            //Click on continue in the dialog box
            theUserClicksOnTheStatusButton("Continue");
            //Check the page title
            testResult = referralPage.verifyThePageTitlePresence("Make this patient record inactive");
            Assert.assertTrue(testResult);
            //Select reason for making inactive
            theUserClicksOnTheReasonButton("Duplicate");
            //Enter the reason
            theUserEntersTheJustificationReasonInTheTextBoxAs("from automation script");
            //Enter the confirmation
            theUserClicksOnTheStatusButton("Confirm");
            //Verify the page title
            testResult = referralPage.verifyThePageTitlePresence("Edit this patient record");
            Assert.assertTrue(testResult);
            //Verify the success notification message
            theUserSeesTheNotification("This record is now inactive");
            //Logout from NEAT Tool
            testResult = referralPage.clickLogoutButton();
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_Logout.jpg");
                Assert.fail("Could not logout");
            }
        }else if(actualBadge.equalsIgnoreCase("NHS Spine")){
            Debugger.println("This NHS patient is already SPINE, proceeding to next steps for referral creation...");
        }
    }

}//end class