package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.pages.FamilyMemberDetailsPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class PedigreeSteps extends Pages {
    public PedigreeSteps(SeleniumDriver driver) {
        super(driver);
    }

    @When("the user clicks on the specified node on the pedigree diagram for {string}")
    public void theUserSearchTheFamilyMemberWithTheSpecifiedDetails(String searchDetails) {
        NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(searchDetails);
        if (patient == null) {
            Debugger.println("Specified Family member could not get from the list.");
            return;
        }
        if (patient.getNGIS_ID() == null) {
            patient.setNGIS_ID(referralPage.getPatientNGISId());
        }

        boolean testResult = pedigreePage.clickSpecificNodeOnPedigreeDiagram(patient, "NGIS");
        Assert.assertTrue(testResult);
    }

    @And("the user should be able see the pedigree diagram loaded for the given members")
    public void theUserShouldSeeThePedigreeDiagramLoadedForMembers(DataTable members) {
        boolean testResult = false;
        List<List<String>> memberDetails = members.asLists();
        for (int i = 1; i < memberDetails.size(); i++) {
            NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
            if (patient == null) {
                Debugger.println("Specified Member" + memberDetails.get(i).get(0) + " could not get from the list.");
            }
            if (patient.getNGIS_ID() == null) {
                patient.setNGIS_ID(referralPage.getPatientNGISId());
            }
            testResult = pedigreePage.clickSpecificNodeOnPedigreeDiagram(patient, "NGIS");
            Assert.assertTrue(testResult);
            Wait.seconds(10);//Waiting to ensure the diagram loaded and dissappeard
        }
    }

    @And("the user will see a warning message {string} on the pedigree page")
    public void theUserWillSeeAWarningMessageOnThePatientChoiceInformationOption(String warningMessage) {
        boolean testResult = false;
        testResult = pedigreePage.pedigreeWarningMessage(warningMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see a {string} on the pedigree page")
    public void theUserShouldBeAbleToSeeAOnThePedigreePage(String warningMessage) {
        boolean testResult = false;
        testResult = pedigreePage.infoMessagesInPedigreePage(warningMessage);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see the below messages displayed in the pedigree page")
    public void theUserShouldBelowMessageDisplayedOnPedigreePage(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 0; i < messageDetails.size(); i++) {
            testResult = pedigreePage.verifyPedigreeIntroMessages(messageDetails.get(i).get(0));
            Assert.assertTrue(testResult);
        }
    }


    @And("the user select the pedigree tab (.*)")
    public void theUserClicksTheSpecifiedOnTheNode(String value) {
        boolean testResult = false;
        testResult = pedigreePage.clickSpecificPedigreeTab(value);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see below fields on Clinical Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnClinicalTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsPresenceOnClinicalTab(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
            testResult = pedigreePage.getDisableStatusOfClinicalTabField(fields.get(i).get(0));
            if (fields.get(i).get(1).equalsIgnoreCase("Non-Editable")) {
                if (!testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Non-Editable,But actual Editable.");
                    SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                }
                Assert.assertTrue(testResult);
            } else {
                if (testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Editable,But actual Non-Editable.");
                    SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                }
                Assert.assertFalse(testResult);
            }
        }//for
    }

    @Then("the user should see below fields on Phenotype Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnPhenotypeTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsPresenceOnPhenotypeTab(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
            testResult = pedigreePage.getDisableStatusOfPhenotypeTabField(fields.get(i).get(0));
            if (fields.get(i).get(1).equalsIgnoreCase("Non-Editable")) {
                if (!testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Non-Editable,But actual Editable.");
                    SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                }
                Assert.assertTrue(testResult);
            } else {
                if (testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Editable,But actual Non-Editable.");
                    SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                }
                Assert.assertFalse(testResult);
            }
        }//for
        Assert.assertTrue(testResult);
    }

    @Then("the user should see below fields on Tumours Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnTumoursTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsPresenceOnTumoursTab(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
            testResult = pedigreePage.getDisableStatusOfTumoursTabField(fields.get(i).get(0));
            if (fields.get(i).get(1).equalsIgnoreCase("Non-Editable")) {
                if (!testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Non-Editable,But actual Editable.");
                    SeleniumLib.takeAScreenShot("TumoursTab.jpg");
                }
                Assert.assertTrue(testResult);
            } else {
                if (testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Editable,But actual Non-Editable.");
                    SeleniumLib.takeAScreenShot("TumoursTab.jpg");
                }
                Assert.assertFalse(testResult);
            }
        }
    }

    @Then("the below field values should be displayed properly on Clinical Tab")
    public void theUserShouldBeAbleToReadTheGivenFiledValues(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsValueOnClinicalTab(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to see (.*) button on Pedigree Page")
    public void theUserShouldBeAbleToSeeOnlySaveButton(String buttonName) {
        boolean testResult = false;
        testResult = pedigreePage.verifyPresenceOfButton(buttonName);
        Assert.assertTrue(testResult);
    }

    @And("the user clicks on Save and Continue on Pedigree Page")
    public void theUserClicksOnSaveAndContinueOnPedigree() {
        boolean testResult = false;
        testResult = pedigreePage.saveAndContinueOnPedigree();
        Assert.assertTrue(testResult);
    }

    //This step introduced as navigating back from pedigree stage sometimes not working due to some overlay on other stage elements
    @When("the user scroll to the top of landing page")
    public void theUserScrollToTopOfLandingPage() {
        Actions.scrollToTop(driver);
        Wait.seconds(3);
    }

    @And("the user should see clinical indication same as what is selected at the start of test order")
    public void theUserShouldSeeClinicalIndicationSameAsWhatIsSelectedAtTheStartOfTestOrder() {
        boolean testResult = false;
        String clinicalIndication = referralPage.getPatientClinicalIndication();
        if (clinicalIndication == null || clinicalIndication.isEmpty()) {
            Debugger.println("Clinical Indication Name could not read for the referral.");
            Assert.assertTrue(testResult);
        }
        testResult = pedigreePage.verifyClinicalIndicationName(clinicalIndication);
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see following menu items in the diagram menus")
    public void theUserShouldSeeThePedigreeDiagramMenu(DataTable menuItems) {
        boolean testResult = false;
        List<List<String>> fields = menuItems.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyPedigreeDiagramMenu(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see following controls to view the diagram")
    public void theUserShouldSeeThePedigreeDiagramControls(DataTable viewControls) {
        boolean testResult = false;
        List<List<String>> fields = viewControls.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyPedigreeDiagramViewControls(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the user should be able to see the below tabs in the popup window")
    public void theUserShouldSeeThePedigreeTabsInPopup(DataTable tabNames) {
        boolean testResult = false;
        List<List<String>> fields = tabNames.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.clickSpecificPedigreeTab(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @And("the user is able to close the popup by clicking on the close icon")
    public void theUserIsAbleToCloseThePopupByClickingOnTheCloseIcon() {
        pedigreePage.closePopup();
    }

    @And("the user should see the (.*) button status as (.*)")
    public void theUserShouldSeeTheButtonStatusAs(String buttonName, String status) {
        boolean testResult = false;
        testResult = pedigreePage.verifyButtonStatus(buttonName, status);
        Assert.assertTrue(testResult);
    }

    @When("the user click on (.*) menu button")
    public void theUserClickOnButton(String buttonName) {
        boolean testResult = false;
        testResult = pedigreePage.clickOnMenuButton(buttonName);
        Assert.assertTrue(testResult);
    }

    @When("the user adds new parent node to proband {string}")
    public void theUserAddNewParentNodeToProband(String searchDetails) {
        NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(searchDetails);
        if (patient == null) {
            Debugger.println("Specified Proband Details could not get from the list.");
            return;
        }
        if (patient.getNGIS_ID() == null) {
            patient.setNGIS_ID(referralPage.getPatientNGISId());
        }

        boolean testResult = pedigreePage.addParentNodeToProband(patient);
        Assert.assertTrue(testResult);
    }

    @Then("the user sees two NON NGIS Patient ID nodes added to the patient {string}")
    public void theUserSeesTwoNONNGISPatientIDNodesAddedToThePedigreeDiagram(String searchDetails) {
        NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(searchDetails);
        if (patient == null) {
            Debugger.println("Specified Proband Details could not get from the list.");
            return;
        }
        if (patient.getNGIS_ID() == null) {
            patient.setNGIS_ID(referralPage.getPatientNGISId());
        }
        boolean testResult = pedigreePage.verifyNonNGISNodesPresence(patient);
        Assert.assertTrue(testResult);
    }

    @Then("the user selects pedigree node for one of the Non NGIS family member for {string}")
    public void theUserPedigreeNodeForNonNGISPatient(String searchDetails) {
        NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(searchDetails);
        if (patient == null) {
            Debugger.println("Specified Proband Details could not get from the list.");
            return;
        }
        if (patient.getNGIS_ID() == null) {
            patient.setNGIS_ID(referralPage.getPatientNGISId());
        }
        boolean testResult = pedigreePage.clickSpecificNodeOnPedigreeDiagram(patient, "NonNGIS");
        Assert.assertTrue(testResult);
    }

    @Then("the user should be able to update the Age of Onset with {string}")
    public void theUserShouldBeAbleToUpdateTheAgeOfOnsetWithTheGivenDetails(String ageOfOnset) {
        boolean testResult = false;
        String[] year_months = ageOfOnset.split(",");
        testResult = pedigreePage.selectAgeOfOnset(year_months[0], year_months[1]);
        Assert.assertTrue(testResult);
    }

    @Then("the user updates the parent date of year as {string}")
    public void theUserUpdatesTheParentDateOfYearAs(String yearOfBirth) {
        boolean testResult = false;
        testResult = pedigreePage.setYearOfBirth(yearOfBirth);
        Assert.assertTrue(testResult);
    }

    @Then("the user should see the hpo phenotypes {string} displayed")
    public void theUserShouldSeeTheHpoPhenotypesDisplayed(String phenotypes) {
        boolean testResult = false;
        if(phenotypes.indexOf(",") == -1) {
            testResult = pedigreePage.verifyHPOPhenotype(phenotypes);
        }else{
            String[] phenos = phenotypes.split(",");
            for(int i=0; i<phenos.length; i++){
                testResult = pedigreePage.verifyHPOPhenotype(phenos[i]);
                if(!testResult){
                    Assert.assertTrue(testResult);
                }
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user should see the below options for (.*) field Personal tab")
    public void theUserShouldSeeTheBelowListedEthnicityEnumerationsLoadedInPedigree(String fieldName,DataTable enumerations) {
        boolean testResult = false;
        List<List<String>> enumerationsList = enumerations.asLists();
        for (int i = 0; i < enumerationsList.size(); i++) {
            testResult = pedigreePage.verifyPersonalTabDropDownOptions(fieldName,enumerationsList.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user enters age at death as {string}")

    public void theUserShouldNotSavePedigreeWithInvalidAgeAtDeath(String ageAtDeath) {
        boolean testResult = false;
        testResult = pedigreePage.setAgeAtDeath(ageAtDeath);
        Assert.assertTrue(testResult);
    }

    @And("the user should see diagnosis code options as below")
    public void theUserShouldSeeDiagnosisCodeOptionsAs(DataTable diagnosis) {
        boolean testResult = false;
        List<List<String>> disOrders = diagnosis.asLists();
        for (int i = 1; i < disOrders.size(); i++) {
            testResult = pedigreePage.verifyDiagnosisDisorders(disOrders.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }
        Assert.assertTrue(testResult);
    }

    @Then("the user should see below fields on Personal Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnPersonalTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsPresenceOnPersonalTab(fields.get(i).get(0));
            if (!testResult) {
                Assert.assertTrue(testResult);
            }
        }//for
    }

    @Then("the user should see error pop up message displayed as {string}")
    public void theUserShouldSeeBelowFieldsOnPersonalTabWithTheGivenStatus(String errorMessage) {
        boolean testResult = false;
        testResult = pedigreePage.verifyErrorMessageOnPopup(errorMessage);
        Assert.assertTrue(testResult);
    }

    @And("the verify the Pedigree diagram is loaded with java script instead of iframe")
    public void theVerifyThePedigreeDiagramIsLoadedWithJavaScriptInsteadOfIframe() {
        boolean testResult = false;
        testResult = pedigreePage.verifyThePedigreeDiagramLoadedAsJavaScript();
        Assert.assertTrue(testResult);

    }

    @Then("the user should see the Non NGSID displayed in personal tab for the selected Non NGIS member for {string}")
    public void theUserShouldSeeTheNonNGSIDDisplayedInPersonalTabForTheSelectedMember(String searchDetails) {
        NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(searchDetails);
        if (patient == null) {
            Debugger.println("Specified Proband Details could not get from the list.");
            return;
        }
        boolean testResult = pedigreePage.verifyNonNGISPatientIDInPersonalTab(patient.getNON_NGIS_ID1());
        Assert.assertTrue(testResult);
    }

    @And("the user enters tumour field values as {string}")
    public void theUserEntersTumourFieldValuesAs(String tumourValues) {
        boolean testResult = false;
        testResult = pedigreePage.setTumourValues(tumourValues);
        Assert.assertTrue(testResult);
    }


}//end
