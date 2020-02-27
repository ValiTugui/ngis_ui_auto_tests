package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PedigreePage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//span[@id='action-saveAndExit']")
    public WebElement saveAndExitButton;

    @FindBy(xpath = "//p[contains(@class,'pedigree-tool__intro')]")
    public List<WebElement> pedigreeIntroMessages;

    private String pedigreeNGISDNode = "//*[name()='tspan'][contains(text(),'NGIS Patient ID : dummyNGSID')]/..";

    @FindBy(xpath = "//ul[@class='message-list']")
    WebElement warningMessageBox;

    @FindBy(xpath = "//li[@class='message-line']")
    List<WebElement> warningMessage;

    @FindBy(xpath = "//div[contains(@data-testid,'notification-warning')] ")
    public WebElement warningMessageOnPedigreePage;

    @FindBy(xpath = "//p[contains(text(),'The pedigree')]")
    public WebElement heading;

    //Tumours Tab
    @FindBy(xpath = "//div[@id='tab_Tumours']//label[contains(@class,'field-name')]")
    public List <WebElement> tumoursTabFields;
    @FindBy(xpath="//input[@name='numberOfColorectalPolypsTotal']")
    public WebElement tumoursTab_PolypsTotal;
    @FindBy(xpath="//input[@name='numberOfColorectalPolypsAdenomas']")
    public WebElement tumoursTab_PolypsAdenomas;

    //Phenotype Tab
    @FindBy(xpath = "//div[@id='tab_Phenotype']//label[contains(@class,'field-name')]")
    public List <WebElement> phenotypeTabFields;
    @FindBy(xpath="//select[@name='hpoPresent']")
    public WebElement phenotypeTab_HPOPresent;
    @FindBy(xpath="//input[@name='hpo_positive' and contains(@class,'suggest multi suggest')]")
    public WebElement phenotypeTab_Phenotype;

    //Clinical Tab
    @FindBy(xpath = "//div[@id='tab_Clinical']//label[contains(@class,'field-name')]")
    public List <WebElement> clinicalTabFields;
    @FindBy(xpath = "//input[@name='carrierStatus']")
    public List <WebElement> diseaseStatusOptions;

    @FindBy(xpath="//select[@name='clinicalIndicationAgeOfOnsetYears']")
    public WebElement clinicalTab_AgeOfOnsetYears;
    @FindBy(xpath="//select[@name='clinicalIndicationAgeOfOnsetMonths']")
    public WebElement clinicalTab_AgeOfOnsetMonths;
    @FindBy(xpath="//select[@name='diagnosisCertainty']")
    public WebElement clinicalTab_diagnosisCertainty;
    @FindBy(xpath="//input[@name='clinicalIndicationName']")
    public WebElement clinicalTab_ClinicalIndicatorName;

    @FindBy(xpath = "//label[contains(@class,'Status_Unaffected')]")
    public WebElement Unaffected;
    @FindBy(xpath = "//label[contains(@class,'Status_Affected')]")
    public WebElement Affected;
    @FindBy(xpath = "//label[contains(@class,'Status_Unknown')]")
    public WebElement Unknown;

    String selectedPedigreeTab = "//dl[@class='tabs']//a[contains(text(),'dummyOption')]";

    @FindBy(xpath = "//div[@title='Zoom out']")
    public WebElement zoomOutButton;

    @FindBy(xpath = "//button[text()='Save']")
    WebElement saveButton;
    @FindBy(xpath = "//button[text()='Save and continue']")
    WebElement saveAndContinueButton;
    @FindBy(xpath = "//button[text()='Try again']")
    WebElement tryAgainButton;

    public PedigreePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    public void saveAndExitPedigree(){
        saveAndExitButton.click();
    }

    public boolean clickSpecificPedigreeTab(String tabName) {
        try {
            String selectedTab = selectedPedigreeTab.replaceAll("dummyOption", tabName);
            WebElement selectTabOption = driver.findElement(By.xpath(selectedTab));
            if(!Wait.isElementDisplayed(driver, selectTabOption,30)){
                Debugger.println("Expected Tab: "+tabName+" Not loaded in Pedigree popup.");
                SeleniumLib.takeAScreenShot("NoTabLoadedInPedigree.jpg");
                return false;
            }
            selectTabOption.click();
            Wait.seconds(2);
            SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from clicking on Specific Tab  in  Pedigree:"+exp);
            SeleniumLib.takeAScreenShot("NoTabLoadedInPedigree.jpg");
            return false;
        }
    }
    public boolean verifyFieldsStatusOnTumoursTab(String fieldName,String fieldStatus) {
        try {
            //Ensure the field is present
            boolean isPresent = false;
            for(int i=0; i<tumoursTabFields.size(); i++){
                if(tumoursTabFields.get(i).getText().equalsIgnoreCase(fieldName)){
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Field "+fieldName+" not present in Tumours Tab.");
                SeleniumLib.takeAScreenShot("NoFieldInTumoursTab.jpg");
                return false;
            }
            //Verify the Status of each Field
            switch (fieldName) {
                case "Number Of Colorectal Polyps Total": {
                    if(fieldStatus.equalsIgnoreCase("Non-Editable")) {
                        if(!tumoursTab_PolypsTotal.isEnabled()){
                            Debugger.println("Field :"+fieldName+" expected as "+fieldStatus+" under Tumours Tab.");
                            SeleniumLib.takeAScreenShot("TumourTab.jpg");
                            return false;
                        }
                    }
                    break;
                }
                case "Number of Colorectal Polyps Adenomas": {
                    if(fieldStatus.equalsIgnoreCase("AutoComplete")) {
                        if(tumoursTab_PolypsAdenomas.isEnabled()){
                            Debugger.println("Field :"+fieldName+" expected as "+fieldStatus+" under Tumours Tab.");
                            SeleniumLib.takeAScreenShot("TumourTab.jpg");
                            return false;
                        }
                    }
                    break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Tumours Tab :"+exp);
            SeleniumLib.takeAScreenShot("NoFieldInTumoursTab.jpg");
            return false;
        }
    }
    public boolean verifyFieldsStatusOnPhenotypeTab(String fieldName,String fieldStatus) {
        try {
            //Ensure the field is present
            boolean isPresent = false;
            for(int i=0; i<phenotypeTabFields.size(); i++){
                if(phenotypeTabFields.get(i).getText().equalsIgnoreCase(fieldName)){
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Field "+fieldName+" not present in Phenotype Tab.");
                SeleniumLib.takeAScreenShot("NoFieldInPhenotyeTab.jpg");
                return false;
            }
            //Verify the Status of each Field
            switch (fieldName) {
                case "HPO Present": {
                    if(fieldStatus.equalsIgnoreCase("Non-Editable")) {
                        if(!phenotypeTab_HPOPresent.getAttribute("class").contains("disabled")){
                            Debugger.println("Field :"+fieldName+" expected as "+fieldStatus+" under Phenotype Tab.");
                            SeleniumLib.takeAScreenShot("PhenotypeTab.jpg");
                            return false;
                        }
                    }
                    break;
                }
                case "Phenotype": {
                    if(fieldStatus.equalsIgnoreCase("AutoComplete")) {
                        if(!phenotypeTab_Phenotype.getAttribute("class").contains("suggest multi suggest")){
                            Debugger.println("Field :"+fieldName+" expected as "+fieldStatus+" under Phenotype Tab.");
                            SeleniumLib.takeAScreenShot("PhenotypeTab.jpg");
                            return false;
                        }
                    }
                    break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Phenotype Tab :"+exp);
            SeleniumLib.takeAScreenShot("NoFieldInPhenotypeTab.jpg");
            return false;
        }
    }
    public boolean verifyFieldsStatusOnClinicalTab(String fieldName,String fieldStatus) {
        try {
            //Ensure the field is present
            boolean isPresent = false;
            for(int i=0; i<clinicalTabFields.size(); i++){
                if(clinicalTabFields.get(i).getText().equalsIgnoreCase(fieldName)){
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Field "+fieldName+" not present in Clinical Tab.");
                SeleniumLib.takeAScreenShot("NoFieldInClinicalTab.jpg");
                return false;
            }
            //Verify the Status of each Field
            switch (fieldName) {
                case "Age at Onset (Y)": {
                    if(fieldStatus.equalsIgnoreCase("Non-Editable")) {
                        if(!clinicalTab_AgeOfOnsetYears.getAttribute("class").contains("disabled")){
                            Debugger.println("Field :"+fieldName+" expected as "+fieldStatus+" under Clinical Tab.");
                            SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                            return false;
                        }
                    }
                    break;
                }
                case "Age at Onset (m)": {
                    if(fieldStatus.equalsIgnoreCase("Non-Editable")) {
                        if(!clinicalTab_AgeOfOnsetMonths.getAttribute("class").contains("disabled")){
                            Debugger.println("Field :"+fieldName+" expected as "+fieldStatus+" under Clinical Tab.");
                            SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                            return false;
                        }
                    }
                    break;
                }
                case "Disease status": {
                    for(int i=0; i<diseaseStatusOptions.size(); i++){
                        if(fieldStatus.equalsIgnoreCase("Non-Editable")) {
                            if (diseaseStatusOptions.get(i).isEnabled()) {
                                Debugger.println("Field :"+fieldName+" expected as "+fieldStatus+" under Clinical Tab.");
                                SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                                return false;
                            }
                        }
                    }
                    break;
                }
                case "Diagnosis Certainty": {
                    if(fieldStatus.equalsIgnoreCase("Non-Editable")) {
                        if(!clinicalTab_diagnosisCertainty.getAttribute("class").contains("disabled")){
                            Debugger.println("Field :"+fieldName+" expected as "+fieldStatus+" under Clinical Tab.");
                            SeleniumLib.takeAScreenShot("ClinicalTab.jpg");
                            return false;
                        }
                    }
                    break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Clinical Tab :"+exp);
            SeleniumLib.takeAScreenShot("NoFieldInClinicalTab.jpg");
            return false;
        }
    }
    public boolean verifyFieldsValueOnClinicalTab(String fieldName) {
        try {
            //Ensure the field is present
            boolean isPresent = false;
            for(int i=0; i<clinicalTabFields.size(); i++){
                if(clinicalTabFields.get(i).getText().equalsIgnoreCase(fieldName)){
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Field "+fieldName+" not present in Clinical Tab.");
                SeleniumLib.takeAScreenShot("NoFieldPresentInClinicalTab.jpg");
                return false;
            }
            //Verify the Value of each Field
            switch (fieldName) {
                case "Clinical Indication Name": {
                    Wait.forElementToBeDisplayed(driver,clinicalTab_ClinicalIndicatorName);
                    Debugger.println(fieldName+":"+clinicalTab_ClinicalIndicatorName.getText());
                    break;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in validating field status In Clinical Tab :"+exp);
            SeleniumLib.takeAScreenShot("NoFieldPresentInClinicalTab.jpg");
            return false;
        }
    }
    public boolean clickSpecificNodeOnPedigreeDiagram(NGISPatientModel patient) {
        Actions.scrollToBottom(driver);
        if(!Wait.isElementDisplayed(driver,saveAndExitButton,60)){
            Debugger.println("Pedigree diagram not loaded after waiting a minute.");
            SeleniumLib.takeAScreenShot("NoPedigreeDiagramLoaded.jpg");
            return false;
        }
        //Waiting for 5 seconds to ensure the diagram is loaded
        Wait.seconds(5);
        String gender = patient.getGENDER();
        if(gender == null || patient.getNGIS_ID() == null){
            Debugger.println("Gender: "+gender+" and/or NGSID:"+patient.getNGIS_ID()+" is NULL.");
            return false;
        }
        Debugger.println("Clicking on Pedigree Node for NGIS: "+patient.getNGIS_ID()+", Gender:"+patient.getGENDER()+",DOB:"+patient.getDATE_OF_BIRTH());
        boolean diagramClicked = false;
        boolean zoomOutFlag = false;
        try {
            String nodePath = pedigreeNGISDNode.replaceAll("dummyNGSID",patient.getNGIS_ID());
            WebElement patientPedigreeNode = driver.findElement(By.xpath(nodePath));
            if(!Wait.isElementDisplayed(driver,patientPedigreeNode,30)){
                Debugger.println("Pedigree Node for NGSID:"+patient.getNGIS_ID()+" could not locate. ZoomOut and Try");
                if(!zoomOutFlag){
                    Wait.forElementToBeDisplayed(driver,zoomOutButton,10);
                    Actions.clickElement(driver,zoomOutButton);
                    zoomOutFlag = true;
                    clickSpecificNodeOnPedigreeDiagram(patient);
                }else{
                    return false;
                }
            }
            String xCoordinate = null;
            String yCoordinate = null;
            String tempXCoordinate = patientPedigreeNode.getAttribute("x");
            String tempYCoordinate = patientPedigreeNode.getAttribute("y");
            //Debugger.println("Default x............"+tempXCoordinate+"..."+tempYCoordinate);
            String expectedXCoordinate = "";
            String expectedYCoordinate = "";
            int idx_array[] = {0, -1, 1, 2, -2, 3, -3, 4, -4};
            int idy_array[] = {-1, 0, 1, 2, -2, 3, -3, 4, -4};
            if (gender.equalsIgnoreCase("male")) {
                try {
                    expectedXCoordinate = Integer.toString(Integer.parseInt(tempXCoordinate) - 40);
                    expectedYCoordinate = Integer.toString(Integer.parseInt(tempYCoordinate) - 146);
                } catch (Exception exp) {
                    expectedXCoordinate = Float.toString(Float.parseFloat(tempXCoordinate) - 40);
                    expectedYCoordinate = Float.toString(Float.parseFloat(tempYCoordinate) - 146);
                }
                //Debugger.println("Calculated x,y..."+expectedXCoordinate+","+expectedYCoordinate);
                for (int i = 0; i < idx_array.length; i++) {//X coordinates may vary depends on the browser
                    xCoordinate = Integer.toString((int) Double.parseDouble(expectedXCoordinate) + idx_array[i]);
                    if (!diagramClicked) {
                        for (int j = 0; j < idx_array.length; j++) {//Y coordinates may vary depends on the browser
                            yCoordinate = Integer.toString((int) Double.parseDouble(expectedYCoordinate) + idy_array[j]);
                            //Debugger.println("X,Y..."+xCoordinate+","+yCoordinate);
                            By male_node = By.xpath("//*[name()='rect'][@class='pedigree-node-shadow'][@x='" + xCoordinate + "'][contains(@y,'" + yCoordinate + "')]");
                            try {
                                seleniumLib.moveMouseAndClickOnElement(male_node);
                                diagramClicked = true;
                                break;
                            } catch (NoSuchElementException nseexp) {
                                Debugger.println("No Such element....." + idx_array[i] + "...." + male_node);
                            }catch(MoveTargetOutOfBoundsException mtobe ){
                                //Debugger.println("MoveTargetOutOfBounds.....");
                                By ZoomOut = By.xpath("//div[@title='Zoom out']");
                                seleniumLib.clickOnElement(ZoomOut);
                                if(!zoomOutFlag) {
                                    zoomOutFlag = true;
                                    clickSpecificNodeOnPedigreeDiagram(patient);
                                }
                            }catch (Exception exp){
                                //Debugger.println("General Exp....."+exp);
                                try{
                                    String tmpstr = "//*[name()='rect'][@class='pedigree-node-shadow'][@x='" + xCoordinate + "'][contains(@y,'" + yCoordinate + "')][@transform='matrix(1,0,0,1,0,0)']";
                                    By xpathtemp = By.xpath(tmpstr);
                                    //Debugger.println("Constructed...."+tmpstr);
                                    seleniumLib.moveMouseAndClickOnElement(xpathtemp);
                                    diagramClicked = true;
                                }catch(Exception exp1){
                                    //Debugger.println("General Internal Exp....."+exp1);
                                    i = idx_array.length+1;
                                    break;
                                }
                            }
                        }
                    } else {
                        break;
                    }
                }//For
                SeleniumLib.sleep(3);
            } else if (gender.equalsIgnoreCase("Female")) {
                try {
                     expectedYCoordinate = Integer.toString(Integer.parseInt(tempYCoordinate) - 106);
                } catch (Exception exp) {
                    expectedYCoordinate = Float.toString(Float.parseFloat(tempYCoordinate) - 106);
                }
                //Debugger.println("Calculated X,Y.."+expectedYCoordinate);
                By female_node = null;
                for (int i = 0; i < idx_array.length; i++) {//X coordinates may vary depends on the browser
                    xCoordinate = Integer.toString((int) Double.parseDouble(tempXCoordinate) + idx_array[i]);
                    if (!diagramClicked) {
                        for (int j = 0; j < idx_array.length; j++) {//Y coordinates may vary depends on the browser
                            yCoordinate = Integer.toString((int) Double.parseDouble(expectedYCoordinate) + idy_array[j]);
                            female_node = By.xpath("//*[name()='circle'][@class='pedigree-node-shadow'][@cx='" + xCoordinate + "'][contains(@cy,'" + yCoordinate + "')]");
                            try {
                               seleniumLib.moveMouseAndClickOnElement(female_node);
                               diagramClicked = true;
                               break;
                            } catch (NoSuchElementException E) {
                                Debugger.println("FemaleNode...X:" + xCoordinate + "..Y:" + yCoordinate);
                            }
                        }
                    } else {
                        break;
                    }
                }//For
                if(diagramClicked){
                    return diagramClicked;
                }
            }
        } catch (MoveTargetOutOfBoundsException exp) {
           Wait.forElementToBeDisplayed(driver,zoomOutButton,10);
           Actions.clickElement(driver,zoomOutButton);
            if(!zoomOutFlag){
                zoomOutFlag = true;
                clickSpecificNodeOnPedigreeDiagram(patient);
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
                Wait.forElementToBeDisplayed(driver,zoomOutButton,10);
                Actions.clickElement(driver,zoomOutButton);
                if(!zoomOutFlag) {
                    zoomOutFlag = true;
                    clickSpecificNodeOnPedigreeDiagram(patient);
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
            Debugger.println("Expected message:"+message+", Not displayed in Patient Choice.");
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

    public boolean verifyPedigreeIntroMessages(String expMessage){
        try {
            for(int i=0; i<pedigreeIntroMessages.size(); i++){
                if(pedigreeIntroMessages.get(i).getText().contains(expMessage)){
                    return true;
                }
            }
            Debugger.println("Pedigree section does not contains the expected message: "+expMessage);
            SeleniumLib.takeAScreenShot("pedigreeMessage.jpg");
            return false;
        }catch(Exception exp){
            return false;
        }
    }
    public boolean verifyPresenceOfButton(String buttonName) {
        try {
            if(!Wait.isElementDisplayed(driver,saveAndExitButton,60)){
                if(!Wait.isElementDisplayed(driver,zoomOutButton,30)) {
                    Debugger.println("Pedigree diagram not loaded after waiting a minute.");
                    SeleniumLib.takeAScreenShot("NoPedigreeDiagromLoaded.jpg");
                    return false;
                }
            }
           boolean isPresent = false;
           if(buttonName.equalsIgnoreCase("Save")) {
               isPresent = Wait.isElementDisplayed(driver, saveButton, 60);
           }else if(buttonName.equalsIgnoreCase("SaveAndContinue")) {
               isPresent = Wait.isElementDisplayed(driver, saveAndContinueButton, 60);
           }
           if(!isPresent){
                Debugger.println("Expected "+buttonName+" not present in Pedigree Stage.");
                Actions.scrollToBottom(driver);
                SeleniumLib.takeAScreenShot("No"+buttonName+"InPedigree.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying "+buttonName+" in Pedigree Page." + exp);
            Actions.scrollToBottom(driver);
            SeleniumLib.takeAScreenShot("No"+buttonName+"InPedigree.jpg");
            return false;
        }
    }
    public boolean saveAndContinueOnPedigree(){
        try {
            Wait.seconds(10);//Explicit wait for 10 seconds to ensure the diagram is loaded
            if(!Wait.isElementDisplayed(driver,saveAndExitButton,60)){
                if(!Wait.isElementDisplayed(driver,zoomOutButton,30)) {
                    Debugger.println("Pedigree diagram not loaded after waiting a minute.");
                    SeleniumLib.takeAScreenShot("NoPedigreeDiagromLoaded.jpg");
                    return false;
                }
            }
            if(!Wait.isElementDisplayed(driver, saveAndContinueButton, 60)){
                Debugger.println("SaveAndContinueButton on Pedigree diagram not loaded after waiting a minute.");
                SeleniumLib.takeAScreenShot("NoSaveAndContinueInPedigree.jpg");
                return false;
            }
            Actions.retryClickAndIgnoreElementInterception(driver,saveAndContinueButton);
            Wait.seconds(3);//Observed some alert many times.
            Actions.acceptAlert(driver);
            Wait.seconds(3);
            if(Wait.isElementDisplayed(driver,tryAgainButton,5)){
                Actions.clickElement(driver,tryAgainButton);
            }
            return true;

        } catch (Exception exp) {
            Debugger.println("Exception in clicking Save and Continue in Pedigree Page." + exp);
            Actions.scrollToBottom(driver);
            SeleniumLib.takeAScreenShot("PedigreeSaveAndContinue.jpg");
            return false;
        }
    }

}//end
