package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import java.util.List;

public class TestsOrderFormsSteps extends Pages {

    public TestsOrderFormsSteps(SeleniumDriver driver) {
        super(driver);
    }


    @When("the user clicks on Choose files")
    public void theUserClicksOnChooseFiles() {
        Assert.assertTrue(testOrderFormsPage.clickOnChooseFiles());
    }

    @And("the user uploads the following files")
    public void theUserUploadsTheFollowingFiles(List<String> files) {
        boolean testResult = false;
            testResult = testOrderFormsPage.uploadTestOrderForms(files);
            Wait.seconds(3);
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TestOrderForms");
                Assert.fail("Could not upload forms.");
            }
    }

    @Then("{string} error banner should be displayed on Test Order Forms page")
    public void theUploadHasFailedErrorShouldBeDisplayedOnTestOrderFormsPage(String expectedError) {
        boolean testResult = false;
        testResult = Wait.isElementDisplayed(driver, testOrderFormsPage.uploadHasFailedBanner, 10);
        String actualError = testOrderFormsPage.uploadHasFailedBanner.getText();
        if (!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_TestOrderForms");
            Assert.fail("Upload has failed error is not displayed");
        }
        Assert.assertEquals(expectedError, actualError);
    }

    @And("the following error messages are displayed under the Upload has failed error banner in Test Order Forms page")
    public void theFollowingErrorMessagesAreDisplayedUnderTheUploadHasFailedErrorBannerInTestOrderFormsPage(List<String> expectedErrorMessages) {
        boolean testResult = false;
        testResult = testOrderFormsPage.verifyUploadOrderFormsErrors(expectedErrorMessages);

        if (!testResult) {
            List<String> actualErrorMessages = Action.getValuesFromDropdown(testOrderFormsPage.uploadFailedErrorMessages);
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TestOrderForms");
            Debugger.println("Expected list of errors: " + expectedErrorMessages + " != " + "Actual list of errors: "+ actualErrorMessages);

            Assert.fail("One or more Upload Order Forms errors are not displayed");
        }
    }

    @And("the list of {string} files in Test Order Forms contains {int} files")
    public void theListOfFilesContainsFiles(String listName, int expectedNumberOfFiles) {
        int actualNumberOfFiles = testOrderFormsPage.getTheNumberOfFilesInTheList(listName);
        if(actualNumberOfFiles>0){
            Assert.assertEquals(expectedNumberOfFiles, actualNumberOfFiles);
        }else{
            Assert.fail("Please enter one of the following for List names: [Uploading, Uploaded]");
        }

    }

    @Then("the list of {string} files contains the following")
    public void theListOfUploadingFilesContainsTheFollowing(String listName, List<String> expectedUploadingFiles) {
        boolean testResult = false;
        testResult = testOrderFormsPage.verifyUploadedForms(listName, expectedUploadingFiles);

        if (!testResult) {
            List<String> actualUploadingFiles = Action.getValuesFromDropdown(testOrderFormsPage.uploadingFilesList);
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_UploadingFilesList_TestOrderForms");
            Debugger.println("Expected list of files: " + expectedUploadingFiles + " != " + "Actual list of files: "+ actualUploadingFiles);

            Assert.fail("Uploaded forms list does not contain the expected files.");
        }
    }

    @When("the user deletes {string} form")
    public void theUserDeletesForm(String fileToDelete) {
        String xpathForDeleteButton = "//div[.='"+fileToDelete+"']/../div[@class='styles_item__stgKp styles_item__icon__2M6YW'][2]/a";
        Wait.forElementToBeDisplayed(driver, SeleniumLib.getWebElement(By.xpath(xpathForDeleteButton)), 10);
        try {
            SeleniumLib.getWebElement(By.xpath(xpathForDeleteButton)).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("the user restores {string} form")
    public void theUserRestoresForm(String fileToRestore) {
        String xpathForRestoreButton = "//div[.='"+ fileToRestore +"']/..//a[.='Restore']";
        Wait.forElementToBeDisplayed(driver, SeleniumLib.getWebElement(By.xpath(xpathForRestoreButton)), 10);
        try {
            SeleniumLib.getWebElement(By.xpath(xpathForRestoreButton)).click();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
