package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;

public class MiPortalPlaterSamplesSteps extends Pages {

    public MiPortalPlaterSamplesSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user enters a date {string} in the plater-samples date field")
    public void theUserEntersADateInThePlaterSamplesDateField(String date) {
        miPlaterSamplesPage.fillInThePlaterSamplesDate(date);
    }

}