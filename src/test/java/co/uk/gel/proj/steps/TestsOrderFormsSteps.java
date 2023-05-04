package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

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

    @And("the list of uploaded files contains the following")
    public void theListOfUploadedFilesShouldContainTheFollowing(List<String> expectedUploadedFiles) {
        boolean testResult = false;
        testResult = testOrderFormsPage.verifyUploadedFiles(expectedUploadedFiles);

        if (!testResult) {
            List<String> actualUploadedList = Action.getValuesFromDropdown(testOrderFormsPage.uploadedFilesList);
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TestOrderForms");
            Debugger.println("Expected list of files: " + expectedUploadedFiles + " != " + "Actual list of files: "+ actualUploadedList);

            Assert.fail("Uploaded forms list does not contain the expected files.");
        }

    }
}
