package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class TestPackageSteps extends Pages {

    public TestPackageSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("the Test Package page header is shown as {string}")
    public void theTestPackagePageHeaderIsShownAs(String pageTitle) {
        Assert.assertTrue(testPackagePage.verifyTestPackagePageTitle(pageTitle));
    }

    @And("the Test Package page has two {string} buttons")
    public void theTestPackagePageHasTwoButtons(String priorityButtonLabels) {
        Assert.assertTrue(testPackagePage.verifyPriorityButtonLabels(priorityButtonLabels));
    }

    @And("the Test Package page has {string} under the priority buttons")
    public void theTestPackagePageHasUnderThePriorityButtons(String helpText) {
        Assert.assertTrue(testPackagePage.verifyTheHelpText(helpText));
    }

    @And("the Test package page has {string} header")
    public void theTestPackagePageHasHeader(String testsSection) {
        Assert.assertTrue(testPackagePage.verifyTestsSection(testsSection));
    }

    @And("the Test package page has Targeted genes section with the {string}")
    public void theTestPackagePageHasTargetedGenesSectionWithThe(String targetGenesSection) {
        Assert.assertTrue(testPackagePage.verifyTargetedGenes(targetGenesSection));
    }

    @And("the Test package page has test selected for the proband with {string}")
    public void theTestPackagePageHasTestSelectedForTheProbandWith(String testCard) {
        Assert.assertTrue(testPackagePage.verifyTestCardDetails(testCard));
    }

    @And("the test package page has Total number of participants drop down box")
    public void theTestPackagePageHasTotalNumberOfParticipantsDropDownBox() {
        Assert.assertTrue(testPackagePage.verifyTotalNumberOfParticipantsDropDownBox());
    }

    @And("the test package page has Selected family members with the {string}")
    public void theTestPackagePageHasSelectedFamilyMembersWithThe(String relationshipInfo) {
        Assert.assertTrue(testPackagePage.verifyDefaultSelectedFamilyMembersInfo(relationshipInfo));
    }

    @And("the user selects the number of participants as {string}")
    public void theUserSelectsTheNumberOfParticipantsAs(String numberOfParticipants) {
        testPackagePage.selectNumberOfParticipants(Integer.parseInt(numberOfParticipants));
    }

    @When("the user attempts to navigate away by clicking {string}")
    public void theUserAttemptsToNavigateAwayByClicking(String browserInteraction) {
        switch (browserInteraction) {
            case "refresh":
                Actions.refreshBrowser(driver);
                break;
            case "back":
                Actions.browseBackward(driver);
                break;
            default:
                throw new IllegalStateException("Unexpected Browser Interaction value: " + browserInteraction);
        }
    }

    @Then("the user sees a warning message on the page")
    public void theUserSeesAWarningMessageOnThePage() {
        Wait.forAlertToBePresent(driver);
        Actions.acceptAlert(driver);
        Wait.seconds(10);
        System.out.println("URL info after accepting alert :: " + driver.getCurrentUrl());
    }

    @And("the user clicks a test to de-select it")
    public void theUserClicksATestToDeSelectIt() {
        if (testPackagePage.testIsSelected()) testPackagePage.clickTest();
    }
}
