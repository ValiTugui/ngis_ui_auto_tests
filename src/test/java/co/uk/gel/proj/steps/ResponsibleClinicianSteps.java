package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import io.cucumber.java.en.And;

public class ResponsibleClinicianSteps extends Pages {
    public ResponsibleClinicianSteps(SeleniumDriver driver) {
        super(driver);
    }

    @And("the user fills in all the clinician form fields")
    public void theUserFillsInAllTheClinicianFormFields() {
        responsibleClinicianPage.fillInClinicianFormFields();
    }
}
