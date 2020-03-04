package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.util.List;

public class GlobalBehaviourSteps extends Pages {

    public GlobalBehaviourSteps(SeleniumDriver driver) {
        super(driver);
    }

    @Then("the user can see the NGIS version number on the right side bottom of the page next to the privacy policy link")
    public void theUserCanSeeTheNGISVersionNumberOnTheRightSideBottomOfThePageNextToThePrivacyPolicyLink() {
        Debugger.println("Current page is: " + driver.getCurrentUrl());
        Assert.assertTrue("The NGIS version number is NOT present next to the privacy policy link", globalBehaviourPage.isNGISVersionPresent());
    }

    @Then("the user can see the {string} link at bottom of the page")
    public void theUserCanSeeTheLinkAtBottomOfThePage(String expectedText) {
        Assert.assertTrue(globalBehaviourPage.isPrivacyPolicyLinkPresent(expectedText));
        Assert.assertTrue(globalBehaviourPage.footerLinks.get(1).getAttribute("href").contains("privacy-policy"));
    }

    @When("the user clicks the privacy policy link")
    public void theUserClicksThePrivacyPolicyLink() {
        globalBehaviourPage.clickPrivacyPolicy();
    }

    @Then("the {string} page should be opened in the next tab")
    public void thePageShouldBeOpenedInTheNextTab(String pageTitle) {
        Assert.assertTrue(driver.getCurrentUrl().contains("privacy-policy"));
        Assert.assertTrue(globalBehaviourPage.checkPrivacyPolicyLinkPage(pageTitle));
        Actions.switchTab(driver);
    }

    @And("the user should verify the referral banner present at the top")
    public void theUserShouldVerifyTheReferralBannerPresentAtTheTop() {
        boolean testResult = false;
        testResult = globalBehaviourPage.verifyTheElementsOnReferralBanner();
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see continue button on landing page")
    public void theUserShouldBeAbleToSeeContinueButtonOnLandingPage() {
        boolean testResult = false;
        testResult = globalBehaviourPage.verifyTheContinueButtonOnLandingPage();
        Assert.assertTrue(testResult);
    }

    @And("the user verifies the color and size of the labels in (.*) page")
    public void theUserVerifiesTheColorAndSizeOfTheLabelsInCheckYourPatientSDetailsPage(String pageTitle, DataTable fontproperty) {
        Assert.assertEquals(pageTitle, referralPage.getTheCurrentPageTitle());
        List<List<String>> expectedLlabelProperties = fontproperty.asLists(String.class);
        List actualFieldsLabels = referralPage.getTheFieldsLabelsOnCurrentPage();
        List actualFieldLabelsSize = referralPage.getTheFontSizeOfFieldLabelsOnCurrentPage();
        List actualColourFieldLabel = referralPage.getColourOfTheFieldsLabelsOnCurrentPage();
        String expectedFontColorInRGB = "";
        for (int i = 1; i < expectedLlabelProperties.size(); i++) { //i starts from 1 because i=0 represents the header
            Debugger.println("Expected labelHeader " + expectedLlabelProperties.get(i).get(0) + " count: " + i);
            Debugger.println("Actual labelHeader " + actualFieldsLabels.get(i - 1) + "\n");
            Assert.assertTrue(actualFieldsLabels.contains(expectedLlabelProperties.get(i).get(0)));
            Debugger.println("Expected size of labels " + expectedLlabelProperties.get(i).get(2) + " count: " + i);
            Debugger.println("Actual size of labels " + actualFieldLabelsSize.get(i - 1) + "\n");
            Assert.assertEquals(expectedLlabelProperties.get(i).get(2), actualFieldLabelsSize.get(i - 1));
            Debugger.println("Expected label Colour " + expectedLlabelProperties.get(i).get(1) + " count: " + i);
            expectedFontColorInRGB = StylesUtils.convertFontColourStringToCSSProperty(expectedLlabelProperties.get(i).get(1));
            Debugger.println("Expected label Colour RGB " + expectedFontColorInRGB);
            Debugger.println("Actual label Colour RGB " + actualColourFieldLabel.get(i - 1) + "\n");
            Assert.assertEquals(expectedFontColorInRGB, actualColourFieldLabel.get(i - 1));
        }
    }
}//end
