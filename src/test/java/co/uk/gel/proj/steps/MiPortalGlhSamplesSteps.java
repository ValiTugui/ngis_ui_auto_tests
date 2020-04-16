package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class MiPortalGlhSamplesSteps extends Pages {

    public MiPortalGlhSamplesSteps(SeleniumDriver driver) {
        super(driver);
    }

    // for supriya------ticket
    @And("the user enters a sample consignment Number {string} in the search box field")
    public void theUserEntersASampleConsignmentNumberInTheSearchBoxField(String number) {
        boolean testResult = false;
        testResult = miGlhSamplesPage.fillInTheSampleConsignmentNumber(number);
        Assert.assertTrue(testResult);

    }

    @When("the user clicks on the Download CSV button to download the CSV file as {string}.csv in GLH samples page")
    public void theUserClicksOnTheDownloadCSVButtonToDownloadTheCSVFileAsCsvInGLHSamplesPage(String fileName) {
        String dateToday = miGlhSamplesPage.getGlhFileSubmissionDate.getAttribute("data-shinyjs-resettable-value");
        fileName = fileName + "-" + dateToday + ".csv";
        Debugger.println("Actual-filename : " + fileName);
        miPortalHomePage.downloadMiCSVFile(fileName);
    }
}
