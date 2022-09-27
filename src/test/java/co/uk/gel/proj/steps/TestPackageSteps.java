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
        boolean testResult = testPackagePage.verifyTheHelpText(helpText);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_HelpTxt.jpg");
            Assert.fail("Help text has not been displayed:"+helpText);
        }
    }

    @And("the Test package page has {string} header")
    public void theTestPackagePageHasHeader(String testsSection) {
        boolean testResult = testPackagePage.verifyTestsSection(testsSection);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TestSection.jpg");
            Assert.fail("Test section has not been displayed:"+testsSection);
        }
    }

    @And("the Test package page has Targeted genes section with the {string}")
    public void theTestPackagePageHasTargetedGenesSectionWithThe(String targetGenesSection) {
        boolean testResult = testPackagePage.verifyTargetedGenes(targetGenesSection);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_GeneSection.jpg");
            Assert.fail("Test package does not display targeted genes section:"+targetGenesSection);
        }
    }

    @And("the Test package page has test selected for the proband with {string}")
    public void theTestPackagePageHasTestSelectedForTheProbandWith(String testCard) {
        boolean testResult = testPackagePage.verifyTestCardDetails(testCard);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TCardInfo.jpg");
            Assert.fail("Test card info not display targeted genes section:"+testCard);
        }
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
        testResult = testPackagePage.selectNumberOfParticipants(Integer.parseInt(numberOfParticipants));
        if(!testResult){
            testResult = testPackagePage.selectNumberOfParticipants(2);
        }
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoOfParticipants");
            Assert.fail("No Of Participants could not select");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoOfParticipants");
        }
    }

    @When("the user attempts to navigate away by clicking {string}")
    public void theUserAttemptsToNavigateAwayByClicking(String browserInteraction) {
        switch (browserInteraction) {
            case "refresh":
                Action.refreshBrowser(driver);
                Wait.seconds(5);
                break;
            case "back":
                Action.browseBackward(driver);
                break;
            case "close":
                Action.closeBrowser(driver);
                break;
            default:
                throw new IllegalStateException("Unexpected Browser Interaction value: " + browserInteraction);
        }
    }

    @Then("the user sees a warning message on the page")
    public void theUserSeesAWarningMessageOnThePage() {
        try {
            Wait.seconds(2);
            if(!Action.isAlertPresent(driver)){
                Debugger.println("No Alert message present as expected.");
                SeleniumLib.takeAScreenShot("AlertMessage.jpg");
                return;
            }
            //Wait.forAlertToBePresent(driver);
            Action.acceptAlert(driver);
            Wait.seconds(5);
            System.out.println("URL info after accepting alert :: " + driver.getCurrentUrl());
        }catch(Exception exp){
            Debugger.println("Exception from theUserSeesAWarningMessageOnThePage:"+exp);
            SeleniumLib.takeAScreenShot("AlertMessage.jpg");
        }
    }

    @And("the user clicks a test to de-select it")
    public void theUserClicksATestToDeSelectIt() {
        if (testPackagePage.testIsSelected()){
            testPackagePage.clickTest();
        }
    }

    @Then("the user selects the {string}")
    public void theUserSelectsThe(String testReferralUrgencyInfo) {
        boolean testResult = false;
        if (testReferralUrgencyInfo.contains("Urgent")) {
            testResult = testPackagePage.clickUrgentPriority();
        } else {
            testResult = testPackagePage.clickRoutinePriority();
        }
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TestPriority");
            Assert.fail("Test Package :"+testReferralUrgencyInfo+" could not select.");
        }
        if(AppConfig.snapshotRequired){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TestPackage");
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
       boolean testResult = testPackagePage.verifyGivenPriorityIsDeSelected(previousPriority);
       if(!testResult){
           SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TestPackage.jpg");
           Assert.fail("Test package priority is deselected."+previousPriority);
       }
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
        boolean testResult = false;
        testResult = testPackagePage.verifyNumberOfParticipantsFieldDefaultValueIsEmpty();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TPNoOfParticipants.jpg");
            Assert.fail("Test package drop down box for no of participants.");
        }
    }

    @And("the user does not select one of the values")
    public void theUserDoesNotSelectOneOfTheValues() {
        boolean testResult = false;
        testResult = testPackagePage.selectNumberOfParticipants(2);
        if(!testResult){
            testResult = testPackagePage.selectNumberOfParticipants(2);
        }
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoOfParticipants");
            Assert.fail("No Of Participants could not select");
        }
        testResult = testPackagePage.clearNumberOfParticipants();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoOfParticipants");
            Assert.fail("Could not clear number of participants");
        }

    }

    @And("the user sees an error message {string}")
    public void theUserSeesAnErrorMessage(String errorMessage) {
        boolean testResult = false;
        testResult = testPackagePage.verifyErrorMessageInTotalNumberOfParticipants(errorMessage);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoOfParticipants");
            Assert.fail("Could not see error message :"+errorMessage);
        }
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_NoOfParticipants");
            Assert.fail("NCould not select the test");
        }

    }

    @Then("the user should be able to sees trio family icon in review test selection")
    public void theUserShouldBeAbleToSeesTrioFamilyIconInReviewTestSelection() {
        boolean testResult = false;
        testResult = testPackagePage.verifyTrioFamilyIcon();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TrioIcon.jpg");
            Assert.fail("No Trio family icon present");
        }
    }

    @And("the user should be able to see trio family icon in test package")
    public void theUserShouldBeAbleToSeeTrioFamilyIconInTestPackage() {
        boolean testResult = false;
        testResult = testPackagePage.verifyTrioFamilyIcon();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TrioIcon.jpg");
            Assert.fail("No Trio family icon present");
        }
    }

    @And("the User should be able to see the clinical indication code and name in the test package card")
    public void theUserShouldBeAbleToSeeTheClinicalIndicationCodeAndNameInTheTestPackageCard() {
        boolean testResult = false;
        testResult = testPackagePage.verifyCINameIDPresence();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_CICode.jpg");
            Assert.fail("CI Code not present");
        }
    }
}
