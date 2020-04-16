package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class PedigreePage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//p[contains(@class,'pedigree-tool__intro')]")
    public List<WebElement> pedigreeIntroMessages;

    String pedigreeNGISDNode = "//*[name()='tspan'][contains(text(),'NGIS Patient ID : dummyNGSID')]/..";
    String pedigreeNonNGISDNode = "//*[name()='tspan'][contains(text(),'Non NGIS Patient ID : dummyNGSID')]/..";

    @FindBy(xpath = "//*[name()='tspan'][contains(text(),'Non NGIS Patient ID : ')]/..")
    List<WebElement> nonNGISPatientNodes;

    @FindBy(xpath = "//ul[@class='message-list']")
    WebElement warningMessageBox;

    @FindBy(xpath = "//li[@class='message-line']")
    List<WebElement> warningMessage;

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')] ")
    public WebElement warningMessageOnPedigreePage;

    @FindBy(xpath = "//p[contains(text(),'The pedigree')]")
    public WebElement heading;

    @FindBy(xpath = "//div[@id='work-area']")
    public WebElement pedigreeWorkArea;

    @FindBy(xpath = "//div[@id='pedigree-tool']")
    public WebElement pedigreeTool;

    @FindBy(xpath = "//div[@class='msdialog-box pedigree-okcancel']")
    public WebElement confirmationDialog;
    @FindBy(xpath = "//input[@type='button'][not(contains(@style,'display: none'))][@name='ok']")
    public WebElement confirmationYes;
    @FindBy(xpath = "//div[@class='ok-cancel-body']")
    public WebElement popupMessageBody;

    //Personal Tab
    @FindBy(xpath = "//div[@id='tab_Personal']//label[contains(@class,'field-name')]")
    public List<WebElement> personalTabFields;
    @FindBy(xpath = "//select[@name='year']")
    public WebElement personalTab_YearOfBirth;
    @FindBy(xpath = "//select[@name='ethnicity']")
    public WebElement personalTab_Ethnicity;
    @FindBy(xpath = "//select[@name='gender']")
    public WebElement personalTab_Gender;
    @FindBy(xpath = "//select[@name='karyotypic_sex']")
    public WebElement personalTab_KaryotypicSex;
    @FindBy(xpath = "//select[@name='gestationAgeWeeks']")
    public WebElement personalTab_gestationAge;
    @FindBy(xpath = "//select[@name='childlessSelect']")
    public WebElement personalTab_Heredity;
    @FindBy(xpath = "//input[@name='age_of_death']")
    public WebElement personalTab_AgeAtDeath;
    @FindBy(xpath = "//input[@name='nonNgisPatientStableUid']")
    public WebElement personalTab_nonNgisPatientStableUid;
    @FindBy(xpath = "//input[@type='checkbox' and @name='participatingInTest']")
    public WebElement personalTab_participatingInTest;

    //Tumours Tab
    @FindBy(xpath = "//div[@id='tab_Tumours']//label[contains(@class,'field-name')]")
    public List<WebElement> tumoursTabFields;
    @FindBy(xpath = "//input[@name='numberOfColorectalPolypsTotal']")
    public WebElement tumoursTab_PolypsTotal;
    @FindBy(xpath = "//input[@name='numberOfColorectalPolypsAdenomas']")
    public WebElement tumoursTab_PolypsAdenomas;

    //Phenotype Tab
    @FindBy(xpath = "//div[@id='tab_Phenotype']//label[contains(@class,'field-name')]")
    public List<WebElement> phenotypeTabFields;
    @FindBy(xpath = "//select[@name='hpoPresent']")
    public WebElement phenotypeTab_HPOPresent;
    @FindBy(xpath = "//input[@name='hpo_positive' and contains(@class,'suggest multi suggest')]")
    public WebElement phenotypeTab_Phenotype;
    @FindBy(xpath = "//ul[contains(@class,'hpo_suggestions')]/li")
    public List<WebElement> hpoSuggestedLists;

    //Clinical Tab
    @FindBy(xpath = "//div[@id='tab_Clinical']//label[contains(@class,'field-name')]")
    public List<WebElement> clinicalTabFields;
    @FindBy(xpath = "//input[@name='carrierStatus' and @value='Unaffected']")
    public WebElement diseaseStatusUnaffected;
    @FindBy(xpath = "//input[@name='carrierStatus' and @value='Affected']")
    public WebElement diseaseStatusAffected;
    @FindBy(xpath = "//input[@name='carrierStatus' and @value='Uncertain']")
    public WebElement diseaseStatusUncertain;
    @FindBy(xpath = "//input[@name='carrierStatus' and @value='Unknown']")
    public WebElement diseaseStatusUnknown;
    @FindBy(xpath = "//select[@name='clinicalIndicationAgeOfOnsetYears']")
    public WebElement clinicalTab_AgeOfOnsetYears;
    @FindBy(xpath = "//select[@name='clinicalIndicationAgeOfOnsetMonths']")
    public WebElement clinicalTab_AgeOfOnsetMonths;
    @FindBy(xpath = "//select[@name='diagnosisCertainty']")
    public WebElement clinicalTab_diagnosisCertainty;
    @FindBy(xpath = "//select[@name='disorderType']")
    public WebElement clinicalTab_disorderType;
    @FindBy(xpath = "//div[@class='field-box field-disorders']//input[@name='disorders']")
    public WebElement clinicalTab_disOrders;
    @FindBy(xpath = "//ul[contains(@class,'disorder_suggestions')]")
    public WebElement clinicalTab_disOrdersSuggestions;
    @FindBy(xpath = "//input[@type='checkbox' and @name='evaluated']")
    public WebElement clinicalTab__documentEvaluation;
    @FindBy(xpath = "//input[@name='clinicalIndicationName']")
    public WebElement clinicalTab_ClinicalIndicatorName;

    String selectedPedigreeTab = "//dl[@class='tabs']//a[contains(text(),'dummyOption')]";

    @FindBy(xpath = "//button[text()='Save']")
    WebElement saveButton;
    @FindBy(xpath = "//button[text()='Save and continue']")
    WebElement saveAndContinueButton;
    @FindBy(xpath = "//button[text()='Try again']")
    WebElement tryAgainButton;

    @FindBy(xpath = "//div[@id='clinical-indication-name']")
    WebElement clinicalIndicationName;
    //Pedigree Diagram Menus
    @FindBy(xpath = "//div[@class='editor-menu']//span[@id='action-undo']")
    WebElement undoButton;
    @FindBy(xpath = "//div[@class='editor-menu']//span[@id='action-redo']")
    WebElement redoButton;
    @FindBy(xpath = "//div[@class='editor-menu']//span[@id='action-reset']")
    WebElement resetButton;
    @FindBy(xpath = "//div[@class='editor-menu']//span[@id='action-print']")
    WebElement printButton;
    @FindBy(xpath = "//div[@class='editor-menu']//span[@id='action-saveAndExit']")
    WebElement saveAndExitButton;
    @FindBy(xpath = "//div[@class='editor-menu']//span[@id='action-export']")
    WebElement exportButton;
    @FindBy(xpath = "//div[@class='editor-menu']//span[@id='text-familyId']")
    WebElement familyIdInfo;
    @FindBy(xpath = "//div[@class='editor-menu']//span[@id='text-version']")
    WebElement versionInfo;
    //Diagram view controls
    @FindBy(xpath = "//div[@class='view-controls']//span[@title='Pan up']")
    WebElement moveUp;
    @FindBy(xpath = "//div[@class='view-controls']//span[@title='Pan down']")
    WebElement moveDown;
    @FindBy(xpath = "//div[@class='view-controls']//span[@title='Pan right']")
    WebElement moveRight;
    @FindBy(xpath = "//div[@class='view-controls']//span[@title='Pan left']")
    WebElement moveLeft;
    @FindBy(xpath = "//div[@class='view-controls']//span[@title='Pan home']")
    WebElement moveHome;
    @FindBy(xpath = "//div[@class='view-controls']//div[@title='Zoom out']")
    public WebElement zoomOutButton;
    @FindBy(xpath = "//div[@class='view-controls']//div[@title='Zoom in']")
    public WebElement zoomInButton;

    @FindBy(xpath = "//div[@class='menu-box']//span[@class='close-button']")
    public WebElement closePopup;


    public PedigreePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public void closePopup() {
        try {
            Wait.forElementToBeClickable(driver,closePopup);
            Actions.retryClickAndIgnoreElementInterception(driver, closePopup);
        } catch (ElementClickInterceptedException exp) {
            SeleniumLib.scrollToElement(closePopup);
            Actions.retryClickAndIgnoreElementInterception(driver, closePopup);
        } catch (Exception exp) {
            Debugger.println("Could not close the Popup." + exp);
            SeleniumLib.takeAScreenShot("PedigreePopupClose.jpg");
        }

    }

    public boolean clickSpecificPedigreeTab(String tabName) {
        try {
            String selectedTab = selectedPedigreeTab.replaceAll("dummyOption", tabName);
            WebElement selectTabOption = driver.findElement(By.xpath(selectedTab));
            if (!Wait.isElementDisplayed(driver, selectTabOption, 30)) {
                Debugger.println("Expected Tab: " + tabName + " Not loaded in Pedigree popup.");
                SeleniumLib.takeAScreenShot("NoTabLoadedInPedigree.jpg");
                return false;
            }
            Actions.retryClickAndIgnoreElementInterception(driver, selectTabOption);
            Wait.seconds(2);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from clicking on Specific Tab" + tabName + "  in  Pedigree:" + exp);
            SeleniumLib.takeAScreenShot("NoTabLoadedInPedigree.jpg");
            return false;
        }
    }

    public boolean verifyFieldsPresenceOnTumoursTab(String fieldName) {
        try {
            //Ensure the field is present
            boolean isPresent = false;
            for (int i = 0; i < tumoursTabFields.size(); i++) {
                if (tumoursTabFields.get(i).getText().equalsIgnoreCase(fieldName)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Field " + fieldName + " not present in Tumours Tab.");
                SeleniumLib.takeAScreenShot("NoFieldInTumoursTab.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field presence In Tumours Tab :" + exp);
            SeleniumLib.takeAScreenShot("NoFieldInTumoursTab.jpg");
            return false;
        }
    }

    public boolean getDisableStatusOfTumoursTabField(String fieldName) {
        try {
            boolean fieldDisableStatus = false;
            switch (fieldName) {
                case "Number Of Colorectal Polyps Total": {
                    if (tumoursTab_PolypsTotal.isEnabled()) {
                        fieldDisableStatus = false;
                    } else {
                        fieldDisableStatus = true;
                    }
                    break;
                }
                case "Number of Colorectal Polyps Adenomas": {
                    if (tumoursTab_PolypsAdenomas.isEnabled()) {
                        fieldDisableStatus = false;
                    } else {
                        fieldDisableStatus = true;
                    }
                    break;
                }
            }
            return fieldDisableStatus;
        } catch (Exception exp) {
            Debugger.println("Exception in getting field status In Tumours Tab :" + exp);
            SeleniumLib.takeAScreenShot("NoFieldInTumoursTab.jpg");
            return false;
        }
    }

    public boolean getDisableStatusOfPhenotypeTabField(String fieldName) {
        try {
            boolean fieldDisableStatus = false;
            switch (fieldName) {
                case "HPO Present": {
                    fieldDisableStatus = phenotypeTab_HPOPresent.getAttribute("class").contains("disabled");
                    break;
                }
                case "Phenotype": {
                    if (phenotypeTab_Phenotype.isEnabled()) {
                        fieldDisableStatus = false;
                    } else {
                        fieldDisableStatus = true;
                    }
                    break;
                }
            }
            return fieldDisableStatus;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Phenotype Tab :" + exp);
            SeleniumLib.takeAScreenShot("NoFieldInPhenotypeTab.jpg");
            return false;
        }
    }

    public boolean verifyFieldsPresenceOnPhenotypeTab(String fieldName) {
        try {
            //Ensure the field is present
            boolean isPresent = false;
            for (int i = 0; i < phenotypeTabFields.size(); i++) {
                if (phenotypeTabFields.get(i).getText().equalsIgnoreCase(fieldName)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Field " + fieldName + " not present in Phenotype Tab.");
                SeleniumLib.takeAScreenShot("NoFieldInPhenotyeTab.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field presence In Phenotype Tab :" + exp);
            SeleniumLib.takeAScreenShot("NoFieldInPhenotypeTab.jpg");
            return false;
        }
    }

    public boolean verifyFieldsPresenceOnClinicalTab(String fieldName) {
        try {
            boolean isPresent = false;
            for (int i = 0; i < clinicalTabFields.size(); i++) {
                if (clinicalTabFields.get(i).getText().equalsIgnoreCase(fieldName)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Field " + fieldName + " not present in Clinical Tab.");
                SeleniumLib.takeAScreenShot("NoFieldInClinicalTab.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Clinical Tab :" + exp);
            SeleniumLib.takeAScreenShot("NoFieldInClinicalTab.jpg");
            return false;
        }
    }

    public boolean getDisableStatusOfClinicalTabField(String fieldName) {
        try {
            //Verify the Disable Status of each Field
            boolean fieldDisableStatus = false;
            switch (fieldName) {
                case "Clinical Indication Name": {
                    if (clinicalTab_ClinicalIndicatorName.isEnabled()) {
                        fieldDisableStatus = false;
                    } else {
                        fieldDisableStatus = true;
                    }
                    break;
                }
                case "Age at Onset (Y)": {
                    fieldDisableStatus = clinicalTab_AgeOfOnsetYears.getAttribute("class").contains("disabled");
                    break;
                }
                case "Age at Onset (m)": {
                    fieldDisableStatus = clinicalTab_AgeOfOnsetMonths.getAttribute("class").contains("disabled");
                    break;
                }
                case "Disease status": {
                    if (diseaseStatusUnaffected.isEnabled() ||
                            diseaseStatusAffected.isEnabled() ||
                            diseaseStatusUncertain.isEnabled() ||
                            diseaseStatusUnknown.isEnabled()) {
                        fieldDisableStatus = false;
                    } else {
                        fieldDisableStatus = true;
                    }
                    break;
                }
                case "Diagnosis Certainty": {
                    fieldDisableStatus = clinicalTab_diagnosisCertainty.getAttribute("class").contains("disabled");
                    break;
                }
                case "Please select coding system for disorder search:": {
                    fieldDisableStatus = clinicalTab_disorderType.getAttribute("class").contains("disabled");
                    break;
                }
                case "Disorder": {
                    if (clinicalTab_disOrders.isEnabled()) {
                        fieldDisableStatus = false;
                    } else {
                        fieldDisableStatus = true;
                    }
                    break;
                }
                case "Documented evaluation": {
                    if (clinicalTab__documentEvaluation.isEnabled()) {
                        fieldDisableStatus = false;
                    } else {
                        fieldDisableStatus = true;
                    }
                }
            }
            return fieldDisableStatus;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Clinical Tab :" + exp);
            SeleniumLib.takeAScreenShot("NoFieldInClinicalTab.jpg");
            return false;
        }
    }

    public boolean verifyFieldsValueOnClinicalTab(String fieldName) {
        try {
            //Ensure the field is present
            boolean isPresent = false;
            for (int i = 0; i < clinicalTabFields.size(); i++) {
                if (clinicalTabFields.get(i).getText().equalsIgnoreCase(fieldName)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Field " + fieldName + " not present in Clinical Tab.");
                SeleniumLib.takeAScreenShot("NoFieldPresentInClinicalTab.jpg");
                return false;
            }
            //Verify the Value of each Field
            switch (fieldName) {
                case "Clinical Indication Name": {
                    Wait.forElementToBeDisplayed(driver, clinicalTab_ClinicalIndicatorName);
                    Debugger.println(fieldName + ":" + clinicalTab_ClinicalIndicatorName.getText());
                    break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Clinical Tab :" + exp);
            SeleniumLib.takeAScreenShot("NoFieldPresentInClinicalTab.jpg");
            return false;
        }
    }

    public boolean clickSpecificNodeOnPedigreeDiagram(NGISPatientModel patient, String patientType) {
        if (!waitForThePedigreeDiagramToBeLoaded()) {
            return false;
        }
        String gender = patient.getGENDER();
        if (gender == null || patient.getNGIS_ID() == null) {
            Debugger.println("Gender: " + gender + " and/or NGSID:" + patient.getNGIS_ID() + " is NULL.");
            return false;
        }

        boolean zoomOutFlag = false;
        String nodePath = null;
        try {
            if (patientType == null || patientType.isEmpty() || patientType.equalsIgnoreCase("NGIS")) {
                SeleniumLib.scrollToElement(pedigreeWorkArea);
                Debugger.println("Clicking on Pedigree Node for NGIS: " + patient.getNGIS_ID());
                nodePath = pedigreeNGISDNode.replaceAll("dummyNGSID", patient.getNGIS_ID());
            } else {
                Debugger.println("Clicking on Pedigree Node for Non NGIS: " + patient.getNON_NGIS_ID1());
                nodePath = pedigreeNonNGISDNode.replaceAll("dummyNGSID", patient.getNON_NGIS_ID1());
            }
            WebElement patientPedigreeNode = driver.findElement(By.xpath(nodePath));
            if (!Wait.isElementDisplayed(driver, patientPedigreeNode, 30)) {
                if (!zoomOutFlag) {
                    Wait.forElementToBeDisplayed(driver, zoomOutButton, 10);
                    Actions.clickElement(driver, zoomOutButton);
                    zoomOutFlag = true;
                    clickSpecificNodeOnPedigreeDiagram(patient, patientType);
                } else {
                    Debugger.println("Could not locate the Pedigree node for " + patientType + ", NGS:" + patient.getNGIS_ID() + ",NonNGIS:" + patient.getNON_NGIS_ID1());
                    return false;
                }
            }
            if (patientType.equalsIgnoreCase("NGIS")) {
                if (gender.equalsIgnoreCase("Male")) {
                    Debugger.println("Clicking on NGIS:-Male Node");
                    return clickOnMaleNode(patientPedigreeNode.getAttribute("x"), patientType);
                } else if (gender.equalsIgnoreCase("Female")) {
                    Debugger.println("Clicking on NGIS:-Female Node");
                    return clickOnFemaleNode(patientPedigreeNode.getAttribute("x"), patientType);
                }
            } else {//Non-NGIS
                int xLocation = Integer.parseInt(patientPedigreeNode.getAttribute("x"));
                if (xLocation < 0) {//Left of the proband node - Male
                    Debugger.println("Clicking on Non-NGIS:-Male Node");
                    return clickOnMaleNode(patientPedigreeNode.getAttribute("x"), patientType);
                } else {
                    Debugger.println("Clicking on Non-NGIS:-Female Node");
                    return clickOnFemaleNode(patientPedigreeNode.getAttribute("x"), patientType);
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Pedigree Node for NGSID:" + patient.getNGIS_ID() + " could not locate.");
            SeleniumLib.takeAScreenShot("PedigreeDiagram.jpg");
            return false;
        }
    }

    private boolean clickOnMaleNode(String ngsIdX, String patientType) {
        boolean diagramClicked = false;
        boolean zoomOutFlag = false;
        try {
            String nodeX = "";
            try {
                nodeX = Integer.toString(Integer.parseInt(ngsIdX) - 40);
            } catch (Exception exp) {
                nodeX = Float.toString(Float.parseFloat(ngsIdX) - 40);
            }
            By male_node = null;
            //Trying with close locations
            int idx_array[] = {0, -1, 1, 2, -2, 3, -3, 4, -4};
            String xCoordinate = null;
            String yCoordinate = null;
            Debugger.println(patientType + ",MaleBase Node X Location:" + nodeX);
            for (int i = 0; i < idx_array.length; i++) {//X coordinates may vary depends on the browser
                xCoordinate = Integer.toString((int) Double.parseDouble(nodeX) + idx_array[i]);
                for (int j = 0; j < idx_array.length; j++) {//Y coordinates may vary depends on the browser
                    male_node = By.xpath("//*[name()='rect'][@class='pedigree-node-shadow'][@x='" + xCoordinate + "']");
                    try {
                        seleniumLib.moveMouseAndClickOnElement(male_node);
                        diagramClicked = true;
                        Debugger.println("Male: " + patientType + ",Clicked On:" + xCoordinate);
                        break;
                    } catch (NoSuchElementException NSEE) {
                        Debugger.println("No Such element....." + idx_array[i] + "...." + male_node);
                    } catch (MoveTargetOutOfBoundsException mtobe) {
                        By ZoomOut = By.xpath("//div[@title='Zoom out']");
                        seleniumLib.clickOnElement(ZoomOut);
                        if (!zoomOutFlag) {
                            zoomOutFlag = true;
                            clickOnMaleNode(ngsIdX, patientType);
                        }
                    } catch (Exception exp) {
                        try {
                            String tmpstr = "//*[name()='rect'][@class='pedigree-node-shadow'][@x='" + xCoordinate + "'][@transform='matrix(1,0,0,1,0,0)']";
                            By xpathtemp = By.xpath(tmpstr);
                            seleniumLib.moveMouseAndClickOnElement(xpathtemp);
                            diagramClicked = true;
                        } catch (Exception exp1) {
                            i = idx_array.length + 1;
                            break;
                        }
                    }
                }//for y-coordinate
                if (diagramClicked) {
                    break;
                }
            }//for x-coordinate
            return diagramClicked;
        } catch (Exception exp) {
            Debugger.println("Unable to click on Pedigree Node.\n" + exp);
            return false;
        }
    }

    private boolean clickOnFemaleNode(String ngsIdX, String patientType) {
        boolean diagramClicked = false;
        boolean zoomOutFlag = false;
        try {
            String xCoordinate = null;
            int idx_array[] = {0, -1, 1, 2, -2, 3, -3, 4, -4};
            Debugger.println("FemaleNode: Base Calculated X:" + ngsIdX);
            By female_node = null;
            for (int i = 0; i < idx_array.length; i++) {//X coordinates may vary depends on the browser
                xCoordinate = Integer.toString((int) Double.parseDouble(ngsIdX) + idx_array[i]);
                if (!diagramClicked) {
                    for (int j = 0; j < idx_array.length; j++) {//Y coordinates may vary depends on the browser
                        female_node = By.xpath("//*[name()='circle'][@class='pedigree-node-shadow'][@cx='" + xCoordinate + "']");
                        try {
                            seleniumLib.moveMouseAndClickOnElement(female_node);
                            diagramClicked = true;
                            Debugger.println("Female: " + patientType + ",Clicked On:" + xCoordinate);
                            break;
                        } catch (NoSuchElementException E) {
                            Debugger.println("FemaleNode...X:" + xCoordinate);
                        }
                    }
                } else {
                    break;
                }
            }//For
            if (diagramClicked) {
                return diagramClicked;
            }

        } catch (MoveTargetOutOfBoundsException exp) {
            Wait.forElementToBeDisplayed(driver, zoomOutButton, 10);
            Actions.clickElement(driver, zoomOutButton);
            if (!zoomOutFlag) {
                zoomOutFlag = true;
                clickOnFemaleNode(ngsIdX, patientType);
            }

        } catch (WebDriverException exp) {
            //Exception might be thrown from FireFox. Constructing the xpath from the exception.
            try {
                String[] tem_element_props = exp.getMessage().split("<");
                String[] element_props = tem_element_props[1].split("\"");
                String[] temp = element_props[0].split(" ");

                String Xpath = "//*[name()=" + "'" + temp[0] + "']";
                for (int i = 2; i < element_props.length - 1; i++) {
                    Xpath = Xpath + "[@" + element_props[i].trim() + "'" + element_props[++i].trim() + "']";
                }
                seleniumLib.moveMouseAndClickOnElement(By.xpath(Xpath));
            } catch (MoveTargetOutOfBoundsException mtobe) {
                Wait.forElementToBeDisplayed(driver, zoomOutButton, 10);
                Actions.clickElement(driver, zoomOutButton);
                if (!zoomOutFlag) {
                    zoomOutFlag = true;
                    clickOnFemaleNode(ngsIdX, patientType);
                }
            } catch (Exception scp) {
                Debugger.println("catch block for try with in WebDriverException catch block\n" + scp);
            }

        } catch (Exception exp) {
            Debugger.println("Unable to click on Pedigree Node.\n" + exp);
            return false;
        }
        return true;
    }

    public boolean pedigreeWarningMessage(String message) {
        try {
            Wait.forElementToBeDisplayed(driver, warningMessageBox);
            for (int i = 0; i < warningMessage.size(); i++) {
                if (message.equalsIgnoreCase(warningMessage.get(i).getText())) {
                    return true;
                }
            }
            Debugger.println("Expected message:" + message + ", Not displayed in Patient Choice.");
            SeleniumLib.takeAScreenShot("PedigreeWarning.jpg");
            return false;
        } catch (Exception exp) {
            Debugger.println("PatientChoicePage, patientChoiceInformationWarningMessage - warning message box not found. " + exp);
            SeleniumLib.takeAScreenShot("PedigreeWarning.jpg");
            return false;
        }
    }

    public boolean infoMessagesInPedigreePage(String warningMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, warningMessageOnPedigreePage);
            String actualMessage = warningMessageOnPedigreePage.getText();
            if (!warningMessage.equalsIgnoreCase(warningMessageOnPedigreePage.getText())) {
                Debugger.println("Expected Message: " + warningMessage + ", but Actual Message is: " + actualMessage);
                SeleniumLib.takeAScreenShot("PedigreeInfoMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Pedigree Page:Info Message :not found " + exp);
            SeleniumLib.takeAScreenShot("PedigreeInfoMessage.jpg");
            return false;
        }
    }

    public boolean verifyPedigreeIntroMessages(String expMessage) {
        try {
            for (int i = 0; i < pedigreeIntroMessages.size(); i++) {
                if (pedigreeIntroMessages.get(i).getText().contains(expMessage)) {
                    return true;
                }
            }
            Debugger.println("Pedigree section does not contains the expected message: " + expMessage);
            SeleniumLib.takeAScreenShot("pedigreeMessage.jpg");
            return false;
        } catch (Exception exp) {
            return false;
        }
    }

    public boolean verifyPresenceOfButton(String buttonName) {
        try {
            if (!waitForThePedigreeDiagramToBeLoaded()) {
                return false;
            }
            boolean isPresent = false;
            if (buttonName.equalsIgnoreCase("Save")) {
                isPresent = Wait.isElementDisplayed(driver, saveButton, 60);
            } else if (buttonName.equalsIgnoreCase("SaveAndContinue")) {
                isPresent = Wait.isElementDisplayed(driver, saveAndContinueButton, 60);
            }
            if (!isPresent) {
                Debugger.println("Expected " + buttonName + " not present in Pedigree Stage.");
                Actions.scrollToBottom(driver);
                SeleniumLib.takeAScreenShot("No" + buttonName + "InPedigree.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying " + buttonName + " in Pedigree Page." + exp);
            Actions.scrollToBottom(driver);
            SeleniumLib.takeAScreenShot("No" + buttonName + "InPedigree.jpg");
            return false;
        }
    }

    public boolean saveAndContinueOnPedigree() {
        try {
            if (!waitForThePedigreeDiagramToBeLoaded()) {
                return false;
            }
            if (!Wait.isElementDisplayed(driver, saveAndContinueButton, 60)) {
                Debugger.println("SaveAndContinueButton on Pedigree diagram not loaded after waiting a minute.");
                SeleniumLib.takeAScreenShot("NoSaveAndContinueInPedigree.jpg");
                return false;
            }
            Actions.retryClickAndIgnoreElementInterception(driver, saveAndContinueButton);
            Wait.seconds(3);//Observed some alert many times.
            Actions.acceptAlert(driver);
            Wait.seconds(3);
            if (Wait.isElementDisplayed(driver, tryAgainButton, 5)) {
                Actions.clickElement(driver, tryAgainButton);
            }
            return true;

        } catch (Exception exp) {
            Debugger.println("Exception in clicking Save and Continue in Pedigree Page." + exp);
            Actions.scrollToBottom(driver);
            SeleniumLib.takeAScreenShot("PedigreeSaveAndContinue.jpg");
            return false;
        }
    }

    public boolean waitForThePedigreeDiagramToBeLoaded() {
        try {
            Wait.forElementToBeDisplayed(driver, pedigreeWorkArea, 120);
            //Background colour of WorkArea expected to be white, once the diagram is loaded.
            String backGroundColor = pedigreeWorkArea.getCssValue("background-color");
            int retryCount = 0;
            while (backGroundColor.equalsIgnoreCase("rgba(0, 0, 0, 0)")) {
                Wait.seconds(10);
                backGroundColor = pedigreeWorkArea.getCssValue("background-color");
                retryCount++;
                if (retryCount > 10) {//Max 100 seconds
                    break;
                }
            }
            if (!backGroundColor.equalsIgnoreCase("rgba(255, 255, 255, 1)")) {
                Debugger.println("Pedigree Diagram not loaded fully. WorkArea background color:" + backGroundColor);
                SeleniumLib.takeAScreenShot("PedigreeDiagramNotLoaded.jpg");
                return false;
            }

            return true;
        } catch (Exception exp) {
            Debugger.println("Pedigree Diagram not loaded fully." + exp);
            SeleniumLib.takeAScreenShot("PedigreeDiagramNotLoaded.jpg");
            return false;
        }
    }

    public boolean verifyClinicalIndicationName(String expectedCiName) {
        try {
            if (!waitForThePedigreeDiagramToBeLoaded()) {
                return false;
            }
            if (!Wait.isElementDisplayed(driver, clinicalIndicationName, 30)) {
                Debugger.println("Clinical Indication Name not loaded in Pedigree Page.");
                SeleniumLib.takeAScreenShot("PedigreeCIName.jpg");
                return false;
            }
            String actualCiName = clinicalIndicationName.getText();
            if (!expectedCiName.equalsIgnoreCase(actualCiName)) {
                //Wait for 30 more seconds and check - added this based on jenkin run failure
                Wait.seconds(30);
                actualCiName = clinicalIndicationName.getText();
                if (!expectedCiName.equalsIgnoreCase(actualCiName)) {
                    Debugger.println("Clinical Indication Name mismatch in Pedigree Page.Expected:" + expectedCiName + ",Actual:" + actualCiName);
                    Actions.scrollToBottom(driver);
                    SeleniumLib.takeAScreenShot("PedigreeCIName.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from Verifying Clinical Indication Name in  Pedigree:" + exp);
            SeleniumLib.takeAScreenShot("PedigreeCIName.jpg");
            return false;
        }
    }

    public boolean verifyPedigreeDiagramMenu(String menuItem) {
        try {
            if (!waitForThePedigreeDiagramToBeLoaded()) {
                return false;
            }
            boolean isPresent = false;
            switch (menuItem) {
                case "Undo": {
                    isPresent = Wait.isElementDisplayed(driver, undoButton, 30);
                    break;
                }
                case "Redo": {
                    isPresent = Wait.isElementDisplayed(driver, redoButton, 30);
                    break;
                }
                case "Reset": {
                    isPresent = Wait.isElementDisplayed(driver, resetButton, 30);
                    break;
                }
                case "Print": {
                    isPresent = Wait.isElementDisplayed(driver, printButton, 30);
                    break;
                }
                case "Save": {
                    isPresent = Wait.isElementDisplayed(driver, saveAndExitButton, 30);
                    break;
                }
                case "Export": {
                    isPresent = Wait.isElementDisplayed(driver, exportButton, 30);
                    break;
                }
                case "RequestID": {
                    isPresent = Wait.isElementDisplayed(driver, familyIdInfo, 30);
                    break;
                }
                case "NGISVersion": {
                    isPresent = Wait.isElementDisplayed(driver, versionInfo, 30);
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Expected Menu Item:" + menuItem + ", not present in Pedigree Diagram Menu.");
                SeleniumLib.takeAScreenShot("PedigreeMenu.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from Verifying Pedigree Diagram Menus:" + exp);
            SeleniumLib.takeAScreenShot("PedigreeMenu.jpg");
            return false;
        }
    }

    public boolean verifyPedigreeDiagramViewControls(String viewControl) {
        try {
            if (!waitForThePedigreeDiagramToBeLoaded()) {
                return false;
            }
            boolean isPresent = false;
            switch (viewControl) {
                case "MoveRight": {
                    isPresent = Wait.isElementDisplayed(driver, moveRight, 30);
                    break;
                }
                case "MoveDown": {
                    isPresent = Wait.isElementDisplayed(driver, moveDown, 30);
                    break;
                }
                case "MoveLeft": {
                    isPresent = Wait.isElementDisplayed(driver, moveLeft, 30);
                    break;
                }
                case "MoveUp": {
                    isPresent = Wait.isElementDisplayed(driver, moveUp, 30);
                    break;
                }
                case "MoveHome": {
                    isPresent = Wait.isElementDisplayed(driver, moveHome, 30);
                    break;
                }
                case "ZoomIn": {
                    isPresent = Wait.isElementDisplayed(driver, zoomInButton, 30);
                    break;
                }
                case "ZoomOut": {
                    isPresent = Wait.isElementDisplayed(driver, zoomOutButton, 30);
                    break;
                }

            }
            if (!isPresent) {
                Debugger.println("Expected View Control:" + viewControl + ", not present in Pedigree Diagram.");
                SeleniumLib.takeAScreenShot("PedigreeViewControl.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception from Verifying Pedigree Diagram View Controls:" + exp);
            SeleniumLib.takeAScreenShot("PedigreeViewControl.jpg");
            return false;
        }
    }

    public boolean verifyButtonStatus(String buttonName, String expStatus) {
        try {
            waitForThePedigreeDiagramToBeLoaded();
            String currentStatus = "";
            if (buttonName.equalsIgnoreCase("Undo")) {
                currentStatus = undoButton.getCssValue("color");
            } else if (buttonName.equalsIgnoreCase("Redo")) {
                currentStatus = redoButton.getCssValue("color");
            }
            String disableColor = StylesUtils.convertFontColourStringToCSSProperty("#777777");
            //Debugger.println("BN:"+buttonName+",curStatus:"+currentStatus+",dis:"+disableColor+",exp:"+expStatus);
            //Disable color is - rgba(119, 119, 119, 1) - Since display as span, based on color, judging for disable/enable
            if (currentStatus.equalsIgnoreCase(disableColor)) {
                if (expStatus.equalsIgnoreCase("Disabled")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (expStatus.equalsIgnoreCase("Enabled")) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (Exception exp) {
            Debugger.println("Exception in verifyButtonStatus:" + exp);
            SeleniumLib.takeAScreenShot("ButtonStatus.jpg");
            return false;
        }
    }

    public boolean clickOnMenuButton(String buttonName) {
        try {

            if (buttonName.equalsIgnoreCase("Undo")) {
                Actions.retryClickAndIgnoreElementInterception(driver, undoButton);
                Wait.seconds(2);
            } else if (buttonName.equalsIgnoreCase("Redo")) {
                Actions.retryClickAndIgnoreElementInterception(driver, redoButton);
                Wait.seconds(2);
            } else if (buttonName.equalsIgnoreCase("Reset")) {
                Actions.retryClickAndIgnoreElementInterception(driver, resetButton);
                //Popup will display for Reset button click
                Wait.seconds(2);
                SeleniumLib.scrollToElement(confirmationDialog);
                Wait.seconds(2);
                Actions.clickElement(driver, confirmationYes);
                Wait.seconds(2);
                Actions.scrollToTop(driver);
                Wait.seconds(2);
                waitForThePedigreeDiagramToBeLoaded();
                Wait.seconds(2);
            } else if (buttonName.equalsIgnoreCase("Save")) {
                Actions.scrollToTop(driver);
                Actions.retryClickAndIgnoreElementInterception(driver, saveAndExitButton);
                Wait.seconds(3);//Wait to save the diagram
            } else if (buttonName.equalsIgnoreCase("Export")) {
                Actions.retryClickAndIgnoreElementInterception(driver, exportButton);
                Wait.seconds(2);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in clicking on Button:" + buttonName + "," + exp);
            SeleniumLib.takeAScreenShot("ButtonClick.jpg");
            return false;
        }
    }

    //Add Parent Node to Specified Proband
    public boolean addParentNodeToProband(NGISPatientModel patient) {
        if (!waitForThePedigreeDiagramToBeLoaded()) {
            return false;
        }
        //Scroll to the WorkArea to locate the diagram nodes without interruption
        SeleniumLib.scrollToElement(pedigreeWorkArea);
        String gender = patient.getGENDER();
        if (gender == null || patient.getNGIS_ID() == null) {
            Debugger.println("Gender: " + gender + " and/or NGSID:" + patient.getNGIS_ID() + " is NULL.");
            return false;
        }
        boolean zoomOutFlag = false;
        try {
            String nodePath = pedigreeNGISDNode.replaceAll("dummyNGSID", patient.getNGIS_ID());
            WebElement patientPedigreeNode = driver.findElement(By.xpath(nodePath));
            if (!Wait.isElementDisplayed(driver, patientPedigreeNode, 30)) {
                if (!zoomOutFlag) {
                    Wait.forElementToBeDisplayed(driver, zoomOutButton, 10);
                    Actions.clickElement(driver, zoomOutButton);
                    zoomOutFlag = true;
                    addParentNodeToProband(patient);
                } else {
                    Debugger.println("Could find the Pedigree node for NGIS:" + patient.getNGIS_ID());
                    SeleniumLib.takeAScreenShot("NoPedigreeNode.jpg");
                    return false;
                }
            }
            if (gender.equalsIgnoreCase("Male")) {
                return addParentNodeToMaleNode(patientPedigreeNode.getAttribute("x"), patientPedigreeNode.getAttribute("y"));
            } else if (gender.equalsIgnoreCase("Female")) {
                return true;
                //Add Parent Node to Female node will be done, if such test case come across
                //return clickOnFemaleNode(patientPedigreeNode.getAttribute("x"), patientPedigreeNode.getAttribute("y"));
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Pedigree Node for NGSID:" + patient.getNGIS_ID() + " could not locate.");
            SeleniumLib.takeAScreenShot("Pedigree"+patient.getNGIS_ID()+".jpg");
            return false;
        }
    }

    private boolean addParentNodeToMaleNode(String ngsIdX, String ngsIdY) {
        boolean diagramClicked = false;
        boolean zoomOutFlag = false;
        try {
            String nodeX = "";
            String nodeY = "";
            try {
                nodeX = Integer.toString(Integer.parseInt(ngsIdX) - 40);
                nodeY = Integer.toString(Integer.parseInt(ngsIdY) - 146);
            } catch (Exception exp) {
                nodeX = Float.toString(Float.parseFloat(ngsIdX) - 40);
                nodeY = Float.toString(Float.parseFloat(ngsIdY) - 146);
            }
            By male_node = null;
            //Trying with close locations
            int idx_array[] = {0, -1, 1, 2, -2, 3, -3, 4, -4};
            int idy_array[] = {-1, 0, 1, 2, -2, 3, -3, 4, -4};
            String xCoordinate = null;
            String yCoordinate = null;
            Debugger.println("Add Parent Nodes: Calculated Base: x,y..." + nodeX + "," + nodeY);
            for (int i = 0; i < idx_array.length; i++) {//X coordinates may vary depends on the browser
                xCoordinate = Integer.toString((int) Double.parseDouble(nodeX) + idx_array[i]);
                for (int j = 0; j < idx_array.length; j++) {//Y coordinates may vary depends on the browser
                    yCoordinate = Integer.toString((int) Double.parseDouble(nodeY) + idy_array[j]);
                    //Debugger.println("X,Y..."+xCoordinate+","+yCoordinate);
                    male_node = By.xpath("//*[name()='rect'][@class='pedigree-node-shadow'][@x='" + xCoordinate + "'][contains(@y,'" + yCoordinate + "')]");
                    try {
                        seleniumLib.moveMouseAndClickOnElement(male_node);
                        diagramClicked = true;
                        Debugger.println("NGIS:Proband:ClickedON:" + xCoordinate + "," + yCoordinate);
                        break;
                    } catch (NoSuchElementException NSEE) {
                        Debugger.println("No Such element....." + idx_array[i] + "...." + male_node);
                    } catch (MoveTargetOutOfBoundsException mtobe) {
                        By ZoomOut = By.xpath("//div[@title='Zoom out']");
                        seleniumLib.clickOnElement(ZoomOut);
                        if (!zoomOutFlag) {
                            zoomOutFlag = true;
                            addParentNodeToMaleNode(ngsIdX, ngsIdY);
                        }
                    } catch (Exception exp) {
                        try {
                            String tmpstr = "//*[name()='rect'][@class='pedigree-node-shadow'][@x='" + xCoordinate + "'][contains(@y,'" + yCoordinate + "')][@transform='matrix(1,0,0,1,0,0)']";
                            By xpathtemp = By.xpath(tmpstr);
                            seleniumLib.moveMouseAndClickOnElement(xpathtemp);
                            diagramClicked = true;
                        } catch (Exception exp1) {
                            i = idx_array.length + 1;
                            break;
                        }
                    }
                }//for y-coordinate
                if (diagramClicked) {
                    break;
                }
            }//for x-coordinate
            //Click On Parent Node, after clicking the Proband Node
            if (!diagramClicked) {
                Debugger.println("Could find the Pedigree node for Pdoband");
                SeleniumLib.takeAScreenShot("PedigreeProbandNode.jpg");
                return diagramClicked;
            }
            try {
                String parentXCoordinate = Integer.toString(Integer.parseInt(xCoordinate) + 40);
                String parentYCoordinate = Integer.toString(Integer.parseInt(yCoordinate) + (-35));
                //Debugger.println("Parent X:" + parentXCoordinate + ",Y:" + parentYCoordinate);
                By parentNode = By.xpath("//*[name()='ellipse'][@cx='" + parentXCoordinate + "'][@cy='" + parentYCoordinate + "']");
                seleniumLib.clickOnElement(parentNode);
            } catch (Exception exp) {
                Debugger.println("Could not click on Parent Node.");
                SeleniumLib.takeAScreenShot("PedigreeParentNode.jpg");
                diagramClicked = false;
            }
            return diagramClicked;
        } catch (Exception exp) {
            Debugger.println("Unable to click on Pedigree Node.\n" + exp);
            SeleniumLib.takeAScreenShot("PedigreeProbandNode.jpg");
            return false;
        }
    }

    public boolean verifyNonNGISNodesPresence(NGISPatientModel patient) {
        if (!waitForThePedigreeDiagramToBeLoaded()) {
            return false;
        }
        try {
            //Scroll to the WorkArea to locate the diagram nodes without interruption
            SeleniumLib.scrollToElement(pedigreeWorkArea);
            if (nonNGISPatientNodes.size() == 0) {
                Debugger.println("Non NGIS Patient Nodes not added to the NGIS Node:" + patient.getNGIS_ID());
                SeleniumLib.takeAScreenShot("NonNGISPedigree.jpg");
                return false;
            }
            String nonNgisId = "";
            for (int i = 0; i < nonNGISPatientNodes.size(); i++) {
                nonNgisId = nonNGISPatientNodes.get(i).getText();
                if (nonNgisId != null) {
                    if (i == 0) {
                        patient.setNON_NGIS_ID1(nonNgisId.replaceAll("Non NGIS Patient ID :", "").trim());
                    } else {
                        patient.setNON_NGIS_ID2(nonNgisId.replaceAll("Non NGIS Patient ID :", "").trim());
                    }
                }
            }
            //For Further use in pedigree actions
            FamilyMemberDetailsPage.updateNonNGISID(patient);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception verifying NonNGIS Patient Node Presence:" + exp);
            SeleniumLib.takeAScreenShot("NonNGIsPedigreeDiagram.jpg");
            return false;
        }
    }

    public boolean selectAgeOfOnset(String years, String months) {
        try {
            //For dropdown selection, tried using method from Actions class, some how it is not selecting
            //Looks the type of dropdown is different here. So used another method existing in SeleniumLib and seems working
            if (!seleniumLib.selectFromListByText(clinicalTab_AgeOfOnsetYears, years)) {
                Debugger.println("Could not select the AgeOfOnset Years");
                SeleniumLib.takeAScreenShot("AgeOfOnset.jpg");
                return false;
            }
            if (!seleniumLib.selectFromListByText(clinicalTab_AgeOfOnsetMonths, months)) {
                Debugger.println("Could not select the AgeOfOnset Months");
                SeleniumLib.takeAScreenShot("AgeOfOnset.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Could not select AgeOfOnset values :" + exp);
            SeleniumLib.takeAScreenShot("AgeOfOnsetNotSelected.jpg");
            return false;
        }
    }
    public boolean setYearOfBirth(String years) {
        try {
            if (!seleniumLib.selectFromListByText(personalTab_YearOfBirth, years)) {
                Debugger.println("Could not select the Year Of Birth");
                SeleniumLib.takeAScreenShot("YearOfBirth.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Could not select Year Of Birth values :" + exp);
            SeleniumLib.takeAScreenShot("YearOfBirth.jpg");
            return false;
        }
    }
    public boolean verifyHPOPhenotype(String phenotype) {
        boolean isPresent = false;
        try {
            for(int i=0; i<hpoSuggestedLists.size(); i++){
                Debugger.println("HPO: "+hpoSuggestedLists.get(i).getText());
                if(hpoSuggestedLists.get(i).getText().contains(phenotype)){
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Phenotype: "+phenotype+" not loaded in Pedigree diagram.");
                SeleniumLib.takeAScreenShot("HPOPhenotype.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Could not verifyHPOPhenotype :" + exp);
            SeleniumLib.takeAScreenShot("HPOPhenotype.jpg");
            return false;
        }
    }

    public boolean verifyDiagnosisDisorders(String disorder) {
        boolean isPresent = false;
        try {
            //Re-using an existing method in SeleniumLib for Select drop downs
            isPresent = seleniumLib.selectFromListByText(clinicalTab_disorderType,disorder);
            if(!isPresent){
                Debugger.println("Diagnosis disorder :"+disorder+" not present in Clinical Tab.");
                SeleniumLib.takeAScreenShot("DiagnosisDisorder.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying DiagnosisDisorders options.");
            SeleniumLib.takeAScreenShot("DiagnosisDisorder.jpg");
            return false;
        }
    }

    public boolean verifyFieldsPresenceOnPersonalTab(String fieldName) {
        try {
            boolean isPresent = false;
            for (int i = 0; i < personalTabFields.size(); i++) {
                if (personalTabFields.get(i).getText().equalsIgnoreCase(fieldName)) {
                    isPresent = true;
                    break;
                }
            }
            if (!isPresent) {
                Debugger.println("Field " + fieldName + " not present in Personal Tab.");
                SeleniumLib.takeAScreenShot("PersonalTab.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Personal Tab :" + exp);
            SeleniumLib.takeAScreenShot("PersonalTab.jpg");
            return false;
        }
    }
    public boolean verifyPersonalTabDropDownOptions(String fieldName,String option) {
        boolean isPresent = false;
        try {
            //Re-using an existing method in SeleniumLib for Select drop downs
            switch(fieldName) {
                case "Ethnicity":
                    isPresent = seleniumLib.selectFromListByText(personalTab_Ethnicity, option);
                    break;
                case "Gender":
                    isPresent = seleniumLib.selectFromListByText(personalTab_Gender, option);
                    break;
                case "KaryoTypicSex":
                    isPresent = seleniumLib.selectFromListByText(personalTab_KaryotypicSex, option);
                    break;
                case "GestationAge":
                    isPresent = seleniumLib.selectFromListByText(personalTab_gestationAge, option);
                    break;
                case "Heredity":
                    isPresent = seleniumLib.selectFromListByText(personalTab_Heredity, option);
                    break;

            }
            if(!isPresent){
                Debugger.println(fieldName+" Option :"+option+" not present.");
                SeleniumLib.takeAScreenShot("PersonalTab.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying Personal Tab Filed Enumeration.");
            SeleniumLib.takeAScreenShot("PersonalTab.jpg");
            return false;
        }
    }
    public boolean verifyErrorMessageOnPopup(String errorMessage){
        boolean isPresent = false;
        try {
            Wait.seconds(2);
            SeleniumLib.scrollToElement(confirmationDialog);
            Wait.seconds(2);
            String actualMessage = popupMessageBody.getText();
            if(errorMessage.indexOf(",") != -1){
                //In case of multiple error messages, checking one by one
                String[] messages = errorMessage.split(",");
                for(int i=0; i<messages.length; i++){
                    if(actualMessage.contains(messages[i])){
                        isPresent = true;
                    }else{
                        break;
                    }
                }
            }else {
                if (actualMessage.contains(errorMessage)) {
                    isPresent = true;
                }
            }
            if(!isPresent){
                Debugger.println("Popup error message mismatch. Actual:"+actualMessage+",Expected:"+errorMessage);
                SeleniumLib.takeAScreenShot("ErrorPopup.jpg");
            }
            Actions.clickElement(driver, confirmationYes);
            Wait.seconds(2);
            return isPresent;

        }catch(Exception exp){
            Debugger.println("Exception in validating Error popup:"+exp);
            SeleniumLib.takeAScreenShot("ErrorPopup.jpg");
            return false;
        }
    }
    public boolean setAgeAtDeath(String age){
        try{
            Wait.forElementToBeDisplayed(driver,personalTab_AgeAtDeath);
            personalTab_AgeAtDeath.sendKeys(age);
            Wait.seconds(2);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in setting AgeAtDeath:"+exp);
            SeleniumLib.takeAScreenShot("AgeAtDeath.jpg");
            return false;
        }
    }
    public boolean verifyThePedigreeDiagramLoadedAsJavaScript(){
        //Previously it was loading as svg diagram inside iframe... now loading as
        //Manual team also checking the same thing via developer tool options.
        if(!Wait.isElementDisplayed(driver,pedigreeTool,10)){
            Debugger.println("Pedigree diagram expected to load as java script..with tag div...Not present the expected tag.");
            return false;
        }
        return true;
    }

    public boolean verifyNonNGISPatientIDInPersonalTab(String nonNgisUID){

        if(!Wait.isElementDisplayed(driver,personalTab_nonNgisPatientStableUid,30)){
            Debugger.println("nonNgisPatientStableUid field not loaded under Personal Tab:");
            SeleniumLib.takeAScreenShot("PersonalTab.jpg");
            return false;
        }
        if(personalTab_nonNgisPatientStableUid.isEnabled()) {//Read and compare when the field is enabled
            String actualUid = personalTab_nonNgisPatientStableUid.getText();
            if (!actualUid.equalsIgnoreCase(nonNgisUID)) {
                Debugger.println("Expected nonNgisPatientStableUid:" + nonNgisUID + ", but actual:" + actualUid);
                SeleniumLib.takeAScreenShot("PersonalTab.jpg");
                return false;
            }
        }
        return true;
    }

    public boolean setTumourValues(String tumourValues){
        try{
            String[] values = tumourValues.split(",");
            if(values == null || values.length != 2){
                Debugger.println("Wrong input "+tumourValues+":Expected TWO tumour values.");
                return false;
            }
            Wait.forElementToBeDisplayed(driver,tumoursTab_PolypsTotal);
            tumoursTab_PolypsTotal.sendKeys(values[0]);
            Wait.forElementToBeDisplayed(driver,tumoursTab_PolypsAdenomas);
            tumoursTab_PolypsAdenomas.sendKeys(values[1]);
            Wait.seconds(2);
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in setting AgeAtDeath:"+exp);
            SeleniumLib.takeAScreenShot("AgeAtDeath.jpg");
            return false;
        }
    }
    public boolean verifyHPOPresentOptions(String hpo) {
        boolean isPresent = false;
        try {
            //Re-using an existing method in SeleniumLib for Select drop downs
            isPresent = seleniumLib.selectFromListByText(phenotypeTab_HPOPresent,hpo);
            if(!isPresent){
                Debugger.println("HPO Present :"+hpo+" not present in PhenoType Tab.");
                SeleniumLib.takeAScreenShot("PhenoType.jpg");
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying HPO Present options.");
            SeleniumLib.takeAScreenShot("PhenoType.jpg");
            return false;
        }
    }

    public boolean searchAndAddDiseaseOrder(String diseases) {
        try {
            if(!Wait.isElementDisplayed(driver,clinicalTab_disOrders,10)){
                Debugger.println("Disease disorder field not populated in Clinical Tab.");
                SeleniumLib.takeAScreenShot("DiseaseOrder.jpg");
                return false;
            }
            String[] diseaseList = null;
            if(diseases.indexOf(",") == -1) {
                diseaseList = new String[]{diseases};
            }else{
                diseaseList = diseases.split(",");
            }
            for(int i=0; i<diseaseList.length; i++){
                clinicalTab_disOrders.sendKeys(diseaseList[i]);
                Wait.seconds(3);//To load the matching disOrders, if exists
                if(!Wait.isElementDisplayed(driver,clinicalTab_disOrdersSuggestions,5)){
                    Debugger.println("No suggestions displayed for the disease :"+diseaseList[i]);
                    SeleniumLib.takeAScreenShot("DiseaseOrderSuggestion.jpg");
                    return false;
                }
                Actions.retryClickAndIgnoreElementInterception(driver,clinicalTab_disOrdersSuggestions);
                Wait.seconds(2);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying HPO Present options.");
            SeleniumLib.takeAScreenShot("PhenoType.jpg");
            return false;
        }
    }
    public boolean verifyParticipateInSelectStatus(String expStatus) {
        boolean isSelected = false;
        try {
            if(!Wait.isElementDisplayed(driver,personalTab_participatingInTest,10)){
                Debugger.println("PArticipatingInTest feild not displayed in Personal Tab.");
                SeleniumLib.takeAScreenShot("ParticipateInTest.jpg");
                return false;
            }
            boolean actStatus = personalTab_participatingInTest.isSelected();
            if(expStatus.equalsIgnoreCase("Selected")){
                isSelected = actStatus;
            }else{
                if(!actStatus){
                    isSelected = true;
                }
            }
            if(!isSelected){
                Debugger.println("Expected Status of IsParticipatingTest:"+expStatus+", but actual:"+isSelected);
                SeleniumLib.takeAScreenShot("ParticipateInTest.jpg");
            }
            return isSelected;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying DiagnosisDisorders options.");
            SeleniumLib.takeAScreenShot("ParticipateInTest.jpg");
            return false;
        }
    }
    public boolean selectDocumentEvaluationOption() {
        try {
            if (!Wait.isElementDisplayed(driver, clinicalTab__documentEvaluation, 30)) {
                Debugger.println("Document Evalution Option not present.");
                SeleniumLib.takeAScreenShot("EvaluateDocOption.jpg");
                return false;
            }
            Actions.clickElement(driver, clinicalTab__documentEvaluation);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from selectDocumentEvaluationOption " + exp);
            SeleniumLib.takeAScreenShot("EvaluateDocOption.jpg");
            return false;
        }
    }
}//end
