package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import sun.security.ssl.Debug;

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
        boolean testResult = false;
        try {
            testResult = testPackagePage.selectNumberOfParticipants(Integer.parseInt(numberOfParticipants));
            Assert.assertTrue(testResult);
        }catch(Exception exp){
            Debugger.println("Could not select test package: "+numberOfParticipants+", Trying with default 2...");
            testResult = testPackagePage.selectNumberOfParticipants(2);
            Assert.assertTrue(testResult);
        }
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
            case "close":
                Actions.closeBrowser(driver);
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

    @Then("the user selects the {string}")
    public void theUserSelectsThe(String testReferralUrgencyInfo) {
        if (testReferralUrgencyInfo.contains("Urgent")) {
            testPackagePage.clickUrgentPriority();
        } else {
            testPackagePage.clickRoutinePriority();
        }
        //Observed failures in Jenkins run, looks like it is too fast, so provided a wait.
        Wait.seconds(5);
    }

    @And("the user selects the number of participants: {string}")
    public void theUserSelectsTheNumberOfParticipants(String numberOfParticipants) {
        testPackagePage.setTotalNumberOfParticipantsField(Integer.parseInt(numberOfParticipants));
    }

    @And("the correct {string} tests are saved to the referral in  {string}")
    public void theCorrectTestsAreSavedToTheReferralIn(String numberOfTests, String stage) {
        referralPage.navigateToStage(stage);
        Assert.assertTrue(testPackagePage.verifyTheTestsList(numberOfTests));
    }

    @And("the Test Package page priority header has {string}")
    public void theTestPackagePagePriorityHeaderHas(String expectedPriorityLabel) {
        Assert.assertTrue(testPackagePage.verifyPrioritySectionHeaderText(expectedPriorityLabel));
    }

    @And("the Test Package page {string} is de-selected")
    public void theTestPackagePageIsDeSelected(String previousPriority) {
       Assert.assertFalse(testPackagePage.verifyGivenPriorityIsSelected(previousPriority));
    }

    @And("the Test Package page has the help text as {string} on the page")
    public void theTestPackagePageHasTheHelpTextAsOnThePage(String helpText) {
        Assert.assertTrue(testPackagePage.verifyTheHelpText(helpText));
    }

    @And("the correct {string} tests are saved to the referral in  {string} with the chosen {string}")
    public void theCorrectTestsAreSavedToTheReferralInWithTheChosen(String expectedNumberOfTests, String stage, String expectedPriority) {
        referralPage.navigateToStage(stage);
        Assert.assertTrue(testPackagePage.verifyTheTestsList(expectedNumberOfTests));
        Assert.assertTrue(testPackagePage.verifyGivenPriorityIsSelected(expectedPriority));
    }

    @And("the drop down box is displayed as empty by default")
    public void theDropDownBoxIsDisplayedAsEmptyByDefault() {
        Assert.assertTrue(testPackagePage.verifyNumberOfParticipantsFieldDefaultValueIsEmpty());
    }

    @And("the user does not select one of the values")
    public void theUserDoesNotSelectOneOfTheValues() {
        testPackagePage.setTotalNumberOfParticipantsField(2);
        testPackagePage.clearNumberOfParticipants();
    }

    @And("the user sees an error message {string}")
    public void theUserSeesAnErrorMessage(String errorMessage) {
        Assert.assertTrue(testPackagePage.verifyErrorMessageInTotalNumberOfParticipants(errorMessage));
    }

    @When("the user clicks on the drop down box to see the values between {string} - {string} displayed")
    public void theUserClicksOnTheDropDownBoxToSeeTheValuesBetweenDisplayed(String minValueParticipantsDropBox, String maxValueParticipantsDropBox) {
        int intMinValueParticipantsDropBox = Integer.parseInt(minValueParticipantsDropBox);
        int intMaxValueParticipantsDropBox = Integer.parseInt(maxValueParticipantsDropBox);
        Assert.assertTrue(testPackagePage.verifyTheValuesShownInNumberOfParticipantsField(intMinValueParticipantsDropBox, intMaxValueParticipantsDropBox));


    }

    @And("The user sees a drop down box for the Total number of participants")
    public void theUserSeesADropDownBoxForTheTotalNumberOfParticipants() {
        Assert.assertTrue(testPackagePage.verifyNumberOfParticipantsFieldExists());
    }

    @And("the correct {string} of participants are saved to the referral in {string}")
    public void theCorrectOfParticipantsAreSavedToTheReferralIn(String participantsCount, String stage) {
        referralPage.navigateToStage(stage);
        Assert.assertTrue(testPackagePage.verifyTheNumberOfParticipants(participantsCount));
    }

    @Then("the user sees the test has become deselected")
    public void theUserSeesTheTestHasBecomeDeselected() {
        Assert.assertTrue(testPackagePage.testIsDeselected());
    }

    @And("the Total number of participants field is disappeared for the deselected test")
    public void theTotalNumberOfParticipantsFieldIsDisappearedForTheDeselectedTest() {
        Assert.assertTrue(testPackagePage.numberOfParticipantsFieldIsNotDisplayed());
    }

    @And("the user sees the test has been selected by default")
    public void theUserSeesTheTestHasBeenSelectedByDefault() {
        Assert.assertTrue(testPackagePage.testIsSelected());
    }

    @Then("the user sees the test has been selected")
    public void theUserSeesTheTestHasBeenSelected() {
        theUserSeesTheTestHasBeenSelectedByDefault();
    }

    @And("the user navigates to {string} stage section without clicking on the {string} button from the {string}")
    public void theUserNavigatesToStageSectionWithoutClickingOnTheButtonFromThe(String targetStage, String buttonName, String currentStage) {
        referralPage.navigateToStage(targetStage);
    }

    @And("the user see a tick mark next to the chosen {string}")
    public void theUserSeeATickMarkNextToTheChosen(String priority) {
        if(priority.equalsIgnoreCase("Urgent")){
            Assert.assertTrue(testPackagePage.ensureTickMarkIsDisplayedNextToUrgent());
        } else {
            Assert.assertTrue(testPackagePage.ensureTickMarkIsDisplayedNextToRoutine());
        }
    }

    @And("the user selects the test by clicking the deselected test")
    public void theUserSelectsTheTestByClickingTheDeselectedTest() {
        boolean testResult = false;
        testResult =testPackagePage.selectTheDeselectedTestPackage();
        Assert.assertTrue(testResult);

    }

    @Then("the user should be able to sees trio family icon in review test selection")
    public void theUserShouldBeAbleToSeesTrioFamilyIconInReviewTestSelection() {
        boolean testResult = false;
        testResult = testPackagePage.validateTrioFamilyIconInOfflineOrder();
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see trio family icon in test package")
    public void theUserShouldBeAbleToSeeTrioFamilyIconInTestPackage() {
        boolean testResult = false;
        testResult = testPackagePage.verifyTrioFamilyIconPresenceInTestPackage();
        Assert.assertTrue(testResult);
    }
}
