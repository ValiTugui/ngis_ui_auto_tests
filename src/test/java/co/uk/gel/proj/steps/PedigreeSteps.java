package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;

import co.uk.gel.lib.Action;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.pages.FamilyMemberDetailsPage;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
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
        if(!testResult) {
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName) + "_PedigreeNode.jpg");
            Assert.fail("Could not click on specific node:"+patient);
        }
    }
    @When("the user clicks on the proband node on the pedigree diagram for {string} and {string}")
    public void theUserClicksOnTheProbandNodeOnThePedigreeDiagram(String patientType,String gender) {
        String ngisID=referralPage.getPatientNGISId();
        pedigreePage.clickProbandNodeOnPedigreeDiagram(ngisID,patientType,gender);
    }

    @And("the user should be able see the pedigree diagram loaded for the given members")
    public void theUserShouldSeeThePedigreeDiagramLoadedForMembers(DataTable members) {
        boolean testResult = false;
        List<List<String>> memberDetails = members.asLists();
        for (int i = 1; i < memberDetails.size(); i++) {
            NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(memberDetails.get(i).get(0));
            if (patient == null) {
                Debugger.println("Specified Member" + memberDetails.get(i).get(0) + " could not get from the list.");
                continue;
            }
            if (patient.getNGIS_ID() == null) {
                patient.setNGIS_ID(referralPage.getPatientNGISId());
            }
            testResult = pedigreePage.clickSpecificNodeOnPedigreeDiagram(patient, "NGIS");
            Assert.assertTrue("Error from click specific node for member with NGIS:"+patient.getNON_NGIS_ID1(),testResult);
            pedigreePage.closePopup();
            Wait.seconds(10);//Waiting to ensure the diagram loaded and dissappeard
        }
    }

    @And("the user will see a warning message {string} on the pedigree page")
    public void theUserWillSeeAWarningMessageOnThePatientChoiceInformationOption(String warningMessage) {
        boolean testResult = false;
        testResult = pedigreePage.pedigreeWarningMessage(warningMessage);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeWarning.jpg");
            Assert.fail("Pedigree warning message not displayed");
        }
    }

    @Then("the user should be able to see a {string} on the pedigree page")
    public void theUserShouldBeAbleToSeeAOnThePedigreePage(String warningMessage) {
        boolean testResult = false;
        testResult = pedigreePage.infoMessagesInPedigreePage(warningMessage);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeInfo.jpg");
            Assert.fail("Pedigree Info message not displayed");
        }
    }

    @Then("the user should see the below messages displayed in the pedigree page")
    public void theUserShouldBelowMessageDisplayedOnPedigreePage(DataTable messages) {
        boolean testResult = false;
        List<List<String>> messageDetails = messages.asLists();
        for (int i = 0; i < messageDetails.size(); i++) {
            testResult = pedigreePage.verifyPedigreeIntroMessages(messageDetails.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeIntro.jpg");
                Assert.fail("Pedigree Intro message not displayed");
            }
        }
    }


    @And("the user select the pedigree tab (.*)")
    public void theUserClicksTheSpecifiedOnTheNode(String value) {
        boolean testResult = false;
        testResult = pedigreePage.clickSpecificPedigreeTab(value);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeTab.jpg");
            Assert.fail("Click on pedigree tab failed:"+value);
        }
    }

    @Then("the user should see below fields on Clinical Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnClinicalTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsPresenceOnClinicalTab(fields.get(i).get(0));
            if (!testResult) {
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClinicalTab.jpg");
                Assert.fail("Clinical tab fields are not present");
            }
            testResult = pedigreePage.getDisableStatusOfClinicalTabField(fields.get(i).get(0));
            if (fields.get(i).get(1).equalsIgnoreCase("Non-Editable")) {
                if (!testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Non-Editable,But actual Editable.");
                    SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                }
            } else {
                if (testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Editable,But actual Non-Editable.");
                    SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                }
            }
        }//for
    }

    @Then("the user should see below fields on Phenotype Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnPhenotypeTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsPresenceOnPhenotypeTab(fields.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PhenotypeTab.jpg");
                Assert.fail("Phenotype tab not present:");
            }
            testResult = pedigreePage.getDisableStatusOfPhenotypeTabField(fields.get(i).get(0));
            if (fields.get(i).get(1).equalsIgnoreCase("Non-Editable")) {
                if (!testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Non-Editable,But actual Editable.");
                    SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                }
            } else {
                if (testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Editable,But actual Non-Editable.");
                    SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                }
            }
        }//for
    }

    @Then("the user should see below fields on Tumours Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnTumoursTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsPresenceOnTumoursTab(fields.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TumourTab.jpg");
                Assert.fail("Could not verify presence of Tumour tab:");
            }

            testResult = pedigreePage.getDisableStatusOfTumoursTabField(fields.get(i).get(0));
            if (fields.get(i).get(1).equalsIgnoreCase("Non-Editable")) {
                if (!testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Non-Editable,But actual Editable.");
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TumourTab.jpg");
                }
            } else {
                if (testResult) {
                    Debugger.println("Filed " + fields.get(i).get(0) + ": Expected status,Editable,But actual Non-Editable.");
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_TumourTab.jpg");
                }
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
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ClinicalTab.jpg");
                Assert.fail("Filed on clinical tab is not present.");
            }
        }
    }

    @Then("the user should be able to see (.*) button on Pedigree Page")
    public void theUserShouldBeAbleToSeeOnlySaveButton(String buttonName) {
        boolean testResult = false;
        testResult = pedigreePage.verifyPresenceOfButton(buttonName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeButton.jpg");
            Assert.fail("Pedigree buttons not displayed");
        }
    }

    @And("the user clicks on Save and Continue on Pedigree Page")
    public void theUserClicksOnSaveAndContinueOnPedigree() {
        boolean testResult = false;
        testResult = pedigreePage.saveAndContinueOnPedigree();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeSaveContinue.jpg");
            Assert.fail("Pedigree Save and Continue not happened");
        }
    }

    //This step introduced as navigating back from pedigree stage sometimes not working due to some overlay on other stage elements
    @When("the user scroll to the top of landing page")
    public void theUserScrollToTopOfLandingPage() {
        Action.scrollToTop(driver);
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeClinical.jpg");
            Assert.fail("Pedigree Clinical Indication name not displayed");
        }
    }

    @And("the user should be able to see following menu items in the diagram menus")
    public void theUserShouldSeeThePedigreeDiagramMenu(DataTable menuItems) {
        boolean testResult = false;
        List<List<String>> fields = menuItems.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyPedigreeDiagramMenu(fields.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeMenu.jpg");
                Assert.fail("Pedigree diagram not displayed.."+fields.get(i).get(0));
            }
        }
    }

    @And("the user should be able to see following controls to view the diagram")
    public void theUserShouldSeeThePedigreeDiagramControls(DataTable viewControls) {
        boolean testResult = false;
        List<List<String>> fields = viewControls.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyPedigreeDiagramViewControls(fields.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeViewControl.jpg");
                Assert.fail("Pedigree diagram view control not displayed.."+fields.get(i).get(0));
            }
        }
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeButton.jpg");
            Assert.fail("Pedigree button status as not expected..");
        }
    }

    @When("the user click on (.*) menu button")
    public void theUserClickOnButton(String buttonName) {
        boolean testResult = false;
        testResult = pedigreePage.clickOnMenuButton(buttonName);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeButton.jpg");
            Assert.fail("Pedigree diagram could not click on button."+buttonName);
        }
    }

    @When("the user adds new parent node to proband {string}")
    public void theUserAddNewParentNodeToProband(String searchDetails) {
        boolean testResult = false;
        NGISPatientModel patient = FamilyMemberDetailsPage.getFamilyMember(searchDetails);
        if (patient == null) {
            Debugger.println("Specified Proband Details could not get from the list.");
            Assert.assertTrue(testResult);
        }
        if (patient.getNGIS_ID() == null) {
            patient.setNGIS_ID(referralPage.getPatientNGISId());
        }

        testResult  = pedigreePage.addParentNodeToProband(patient);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_ParentNode.jpg");
            Assert.fail("Pedigree diagram could not add parent node"+patient);
        }
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeNGISNode.jpg");
            Assert.fail("Pedigree diagram NGIS Node not present.."+patient);
        }
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeAgeOfOnset.jpg");
            Assert.fail("Could not select Age of Onset..");
        }
    }
    @Then("the user updates the parent date of year as {string}")
    public void theUserUpdatesTheParentDateOfYearAs(String yearOfBirth) {
        boolean testResult = false;
        testResult = pedigreePage.setYearOfBirth(yearOfBirth);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeYOB.jpg");
            Assert.fail("Could not select Year of Birth..");
        }
    }

    @Then("the user should see the hpo phenotypes {string} displayed")
    public void theUserShouldSeeTheHpoPhenotypesDisplayed(String phenotypes) {
        boolean testResult = false;
        if(phenotypes.indexOf(",") == -1) {
            testResult = pedigreePage.verifyHPOPhenotype(phenotypes);
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreePhenotype.jpg");
                Assert.fail("Could not select Phenotype."+phenotypes);
            }
        }else{
            String[] phenos = phenotypes.split(",");
            for(int i=0; i<phenos.length; i++){
                testResult = pedigreePage.verifyHPOPhenotype(phenos[i]);
                if(!testResult){
                    SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreePhenotype.jpg");
                    Assert.fail("Could not select Phenotype."+phenos[i]);
                }
            }
        }
    }
    @Then("the user should see the below options for (.*) field Personal tab")
    public void theUserShouldSeeTheBelowListedEthnicityEnumerationsLoadedInPedigree(String fieldName,DataTable enumerations) {
        boolean testResult = false;
        List<List<String>> enumerationsList = enumerations.asLists();
        for (int i = 0; i < enumerationsList.size(); i++) {
            testResult = pedigreePage.verifyPersonalTabDropDownOptions(fieldName,enumerationsList.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreePersonal.jpg");
                Assert.fail("Could not see personal tab options.");
            }
        }
    }
    @Then("the user enters age at death as {string}")
    public void theUserShouldNotSavePedigreeWithInvalidAgeAtDeath(String ageAtDeath) {
        boolean testResult = false;
        testResult = pedigreePage.setAgeAtDeath(ageAtDeath);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeAgeAtDeath.jpg");
            Assert.fail("Could not set Age At Death ");
        }
    }

    @And("the user should see diagnosis code options as below")
    public void theUserShouldSeeDiagnosisCodeOptionsAs(DataTable diagnosis) {
        boolean testResult = false;
        List<List<String>> disOrders = diagnosis.asLists();
        for (int i = 1; i < disOrders.size(); i++) {
            testResult = pedigreePage.verifyDiagnosisDisorders(disOrders.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeDiagnosis.jpg");
                Assert.fail("Could not select Diagnosis disorder."+disOrders.get(i).get(0));
            }
        }
    }
    @Then("the user should see below fields on Personal Tab with the given status")
    public void theUserShouldSeeBelowFieldsOnPersonalTabWithTheGivenStatus(DataTable fieldsDetails) {
        boolean testResult = false;
        List<List<String>> fields = fieldsDetails.asLists();
        for (int i = 1; i < fields.size(); i++) {
            testResult = pedigreePage.verifyFieldsPresenceOnPersonalTab(fields.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreePersonalTab.jpg");
                Assert.fail("Could not see presence on personal tab."+fields.get(i).get(0));
            }
        }//for
    }
    @Then("the user should see error pop up message displayed as {string}")
    public void theUserShouldSeeBelowFieldsOnPersonalTabWithTheGivenStatus(String errorMessage) {
        boolean testResult = false;
        testResult = pedigreePage.verifyErrorMessageOnPopup(errorMessage);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeErrorMsg.jpg");
            Assert.fail("Could not verify error message "+errorMessage);
        }
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
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeNGSID.jpg");
            Assert.fail("NonNGIS ID ni personal tab could not see ");
        }
    }

    @And("the user enters tumour field values as {string}")
    public void theUserEntersTumourFieldValuesAs(String tumourValues) {
        boolean testResult = false;
        testResult = pedigreePage.setTumourValues(tumourValues);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeTumour.jpg");
            Assert.fail("Could not set Tumour values "+tumourValues);
        }
    }

    @And("the user should see HPO Present options as below")
    public void theUserShouldSeeHPOPresentOptionsAs(DataTable hpoOptions) {
        boolean testResult = false;
        List<List<String>> hpos = hpoOptions.asLists();
        for (int i = 1; i < hpos.size(); i++) {
            testResult = pedigreePage.verifyHPOPresentOptions(hpos.get(i).get(0));
            if(!testResult){
                SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeHPO.jpg");
                Assert.fail("HPO not present "+(hpos.get(i).get(0)));
            }
        }
    }

    @Then("the user should be able to search disease {string} and codes in the pedigree and add to the selected nodes")
    public void theUserShouldBeAbleToSearchDiseaseAndCodesInThePedigreeAndAddToTheSelectedNodes(String disease) {
        boolean testResult = false;
        testResult = pedigreePage.searchAndAddDiseaseOrder(disease);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeDisease.jpg");
            Assert.fail("Disease could not add. "+disease);
        }
    }

    @Then("user should see the Participating in Test check box is (.*)")
    public void userShouldSeeTheParticipatingInTestCheckBoxIsNotSelected(String selectStatus) {
        boolean testResult = false;
        testResult = pedigreePage.verifyParticipateInSelectStatus(selectStatus);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreePID.jpg");
            Assert.fail("Participant could not verify"+selectStatus);
        }
    }

    @Then("the user selects the document evaluation option")
    public void theUserSelectsTheDocumentEvaluationOption() {
        boolean testResult =false;
        testResult=pedigreePage.selectDocumentEvaluationOption();
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeDocEvaluate.jpg");
            Assert.fail("Could not select Document Evaluation option.");
        }
    }

    @When("the user clicks on the proband node on the pedigree for {string} and {string}")
    public void theUserClicksOnTheProbandNodeOnThePedigree(String patientType, String gender) {
        boolean testResult = false;
        String ngisID=referralPage.getPatientNGISId();
        testResult = pedigreePage.clickProbandNodeOnPedigree(patientType,gender,ngisID);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeNode.jpg");
            Assert.fail("Could not click on Pedigree Node ");
        }
    }

    @When("the user clicks on the family member node on the pedigree diagram for {string} and {string}")
    public void theUserClicksOnTheFamilyMemberNodeOnThePedigreeDiagram(String patientType, String gender) {
        boolean testResult = false;
        String ngisID=referralPage.getPatientNGISId();
        testResult = pedigreePage.clickFamilyMemberNodeOnPedigreeDiagram(patientType, gender,ngisID);
        Assert.assertTrue("Error from click specific node for member with NGIS:", testResult);
    }

    @Then("user should see the monozygotic twin check box is (.*)")
    public void userShouldSeeTheMonozygoticTwinCheckboxIs(String selectStatus) {
        boolean testResult = false;
        testResult = pedigreePage.verifyMonozygoticTwinInSelectStatus(selectStatus);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeStatus.jpg");
            Assert.fail("Could not verifyMonozygoticTwinInSelectStatus "+selectStatus);
        }
    }

    @Then("The user should see the monozygotic twin check box status as (.*)")
    public void theUserShouldSeeTheMonoTwinCheckboxIsNotPresent(String status) {
        boolean testResult = false;
        testResult = pedigreePage.verifyMonozygoticTwinInSelectStatusAsNotSelected(status);
        if(!testResult){
            SeleniumLib.takeAScreenShot(TestUtils.getNtsTag(TestHooks.currentTagName)+"_PedigreeStatus.jpg");
            Assert.fail("Could not verifyMonozygoticTwinInSelectStatus "+status);
        }
    }
}//end
