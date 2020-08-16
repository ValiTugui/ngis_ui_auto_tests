package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class PanelsPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    public PanelsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        seleniumLib = new SeleniumLib(driver);
    }

    @FindBy(xpath = "//h3[contains(text(),'Add another panel')]")
    public WebElement addAnotherPanel;

    @FindBy(xpath = "//input[@placeholder='e.g. Adult solid tumours for rare disease']")
    public WebElement panelsSearchFieldPlaceHolder;

    @FindBy(xpath = "//div[@class='styles_search-input__icon__3511W search-input__icon--search']")
    public WebElement panelsSearchIcon;

    @FindBy(xpath = "//ul[contains(@class,'styles_panel-list')]/li//span")
    public List<WebElement> suggestedPanelsList;

    @FindBy(xpath = "//ul[contains(@class,'styles_panel-list')]/li//a[@title='View on PanelApp']")
    public List<WebElement> suggestedPanelsLinkToPanelApp;

    @FindBy(xpath = "//input[contains(@placeholder,'Adult solid tumours')]/following::span[contains(@class,'select-panel__name')]")
    public List<WebElement> panelsSearchResultsList;

    @FindBy(xpath = "//h3[contains(text(),'Penetrance')]")
    public WebElement penetranceTitle;

    @FindBy(xpath = "//button[contains(text(),'Complete')]")
    public WebElement completeButton;

    @FindBy(xpath = "//button[contains(text(),'Incomplete')]")
    public WebElement incompleteButton;

    @FindBy(xpath = "//a[contains(@class,'styles_panel-assigner__panelapp-link__2u0t7')]")
    public WebElement visitPanelApp;

    @FindBy(xpath = "//h1[contains(text(),'panels')]")
    public WebElement panelAppTitle;

    @FindBy(xpath = "//div[@class='styles_select-panel__3qIYD']")
    public List<WebElement> deselectedPanelsList;

    @FindBy(xpath = "//div[contains(@class,'panel-assigner__penetrance')]//p")
    public WebElement penetranceIntroMessage;

    String titleStringPath = "//h3[contains(text(),'dummyTitle')]";

    @FindBy(xpath = "//h3[text()='Added panels']/following::input[@checked]/ancestor::div[contains(@class,'checked')]")
    List<WebElement> addedPanelsList;

    @FindBy(xpath = "//div[contains(@class,'panel-assigner__intro')]//p|//div[contains(@class,'styles_panel-assigner__intro_')]//div//ul")
    public List<WebElement> panelsPageIntroMessage;

    @FindBy(xpath = "//div[contains(@class,'styles_panel-assigner__intro_')]//div//ul")
    public  WebElement panelsPageIntroMessage2;

    @FindBy(xpath = "//h3[contains(@class,'subheader')]")
    public List<WebElement> panelSubtitles;

    @FindBy(xpath = "//h3[text()='No suggested panels found']")
    public WebElement noSuggestedPanels;

    @FindBy(xpath = "//h2[contains(text(),'Add panels')]")
    public WebElement addPanelsHeader;

    @FindBy(xpath = "//h2[contains(text(),'Add panels')]/parent::div/following-sibling::p")
    public WebElement addPanelsMessage;


    public boolean verifyPanelSearchFieldAndSearchIcon(String expTitle) {
        try {
            Wait.forElementToBeDisplayed(driver, addAnotherPanel);
            if(!expTitle.isEmpty()) {
                By titleElement = By.xpath(titleStringPath.replaceAll("dummyTitle",expTitle));
                if (!seleniumLib.isElementPresent(titleElement)) {
                    Debugger.println("Panels Page: Title "+expTitle+" not present as expected.");
                    SeleniumLib.takeAScreenShot("PanelsPage.jpg");
                    return false;
                }
            }
            if (!Wait.isElementDisplayed(driver,panelsSearchFieldPlaceHolder,10)) {
                Debugger.println("Panels Page: Search field not present.");
                SeleniumLib.takeAScreenShot("PanelsPage.jpg");
                return false;
            }
            if (!Wait.isElementDisplayed(driver,panelsSearchIcon,10)) {
                Debugger.println("Panels Page: Search icon not present.");
                SeleniumLib.takeAScreenShot("PanelsPage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyPanelSearchFieldAndSearchIcon:" + exp);
            SeleniumLib.takeAScreenShot("PanelsPage.jpg");
            return false;
        }
    }

    public boolean searchAndAddPanel(String panels) {
        try {
            Wait.forElementToBeDisplayed(driver, addAnotherPanel);
            String [] panelList = null;
            if(panels.indexOf(",") == -1){
                panelList = new String[]{panels};
            }else{
                panelList = panels.split(",");
            }
            for(int i=0; i<panelList.length; i++) {
                panelsSearchFieldPlaceHolder.clear();
                panelsSearchFieldPlaceHolder.sendKeys(panelList[i]);
                Wait.seconds(5);//Wait to load the related panel based on the search word
                if(panelsSearchResultsList.size() == 0){
                    Debugger.println("No matching Panels for the word: "+panelList[i]);
                    SeleniumLib.takeAScreenShot("NoPanelsListed.jpg");
                    return false;
                }
                try {
                    Click.element(driver, panelsSearchResultsList.get(0));
                }catch(Exception exp1){
                    seleniumLib.clickOnWebElement(panelsSearchResultsList.get(0));
                }
                Wait.seconds(2);//Waiting for 3 seconds after each panel adding
                panelsSearchResultsList.clear();
                Wait.seconds(2);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Search and selection of panel" + exp);
            SeleniumLib.takeAScreenShot("PanelsPageSearchResult.jpg");
            return false;
        }
    }

    public boolean verifyPanelsPageFields() {
        try {
            Wait.forElementToBeDisplayed(driver, penetranceTitle);
            List<WebElement> expElements = new ArrayList<WebElement>();
            expElements.add(penetranceTitle);
            expElements.add(addAnotherPanel);
            expElements.add(completeButton);
            expElements.add(incompleteButton);
            expElements.add(visitPanelApp);
            for (int i = 0; i < expElements.size(); i++) {
                if (!seleniumLib.isElementPresent(expElements.get(i))) {
                    Debugger.println("Panels Page:verifyPanelsPageFields: Element not present " + expElements.get(i));
                    SeleniumLib.takeAScreenShot("PanelsPageFieldsVerification.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels Page:verifyPanelsPageFields, Element not found. " + exp);
            SeleniumLib.takeAScreenShot("PanelsPageFieldsVerification.jpg");
            return false;
        }
    }

    public boolean clicksOnVisitPanelsAppLink() {
        if (!Wait.isElementDisplayed(driver, visitPanelApp, 100)) {
            Debugger.println("Visit Panel App Link not displayed..."+driver.getCurrentUrl());
            SeleniumLib.takeAScreenShot("VisitPanelAppLink.jpg");
            return false;
        }
        try {
        Actions.clickElement(driver,visitPanelApp);
        }catch(Exception exp){
            try {
                seleniumLib.clickOnWebElement(visitPanelApp);
            }catch(Exception exp1){
                Debugger.println("Exception in Visiting Panelapp Link.."+exp1);
                SeleniumLib.takeAScreenShot("VisitPanelAppLink.jpg");
                return false;
            }
        }
        seleniumLib.ChangeWindow();
        return true;
    }

    public boolean verifyPanelAppNavigation() {
        //Verify the navigated URL is correct
        Wait.seconds(10);// Waiting to load the new external Link
        String url = driver.getCurrentUrl();
        Debugger.println("Current URL: " + url);
        if (!url.contains(AppConfig.getPanel_app_url())) {
            Debugger.println("URL navigated is Wrong: " + url);
            SeleniumLib.takeAScreenShot("VisitPanelApp.jpg");
            SeleniumLib.closeCurrentWindow();
            return false;
        }
        if (!Wait.isElementDisplayed(driver, panelAppTitle, 20)) {
            Debugger.println("Panels not displayed on the Navigated Page.");
            SeleniumLib.takeAScreenShot("VisitPanelApp.jpg");
            SeleniumLib.closeCurrentWindow();
            return false;
        }
        SeleniumLib.closeCurrentWindow();
        return true;
    }

    public boolean changeTheStatusOfPenetrance() {
        try {
            seleniumLib.scrollToElement(penetranceTitle);
            if ("true".equalsIgnoreCase(incompleteButton.getAttribute("aria-pressed"))) {
                Actions.clickElement(driver,completeButton);
            } else if ("true".equalsIgnoreCase(completeButton.getAttribute("aria-pressed"))) {
                Actions.clickElement(driver,incompleteButton);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PanelsPage: Exception from changeTheStatusOfPenetrance " + exp);
            SeleniumLib.takeAScreenShot("PanelsPagePenetranceStatus.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfPenetranceOptions() {
        try {
            if(!Wait.isElementDisplayed(driver,completeButton,10)){
               Debugger.println("Complete button not found.");
               SeleniumLib.takeAScreenShot("PanelsPagePentrance.jpg");
               return false;
            }
            if (!Wait.isElementDisplayed(driver,incompleteButton,10)) {
                Debugger.println("Incomplete button not found.");
                SeleniumLib.takeAScreenShot("PanelsPagePentrance.jpg");
                return false;
            }
            return true;

        } catch (Exception exp) {
            Debugger.println("PanelsPage: Complete and Incomplete buttons not found" + exp);
            SeleniumLib.takeAScreenShot("PanelsPagePentrance.jpg");
            return false;
        }
    }

    public boolean verifyButtonAsCompletedByClickingInPanelsPage(String expectedButton) {
        try {
            if (expectedButton.equalsIgnoreCase("complete")) {
                seleniumLib.clickOnWebElement(completeButton);
                if (!"true".equalsIgnoreCase(completeButton.getAttribute("aria-pressed"))) {
                    Debugger.println("Tick marked not found for " + expectedButton + completeButton.getAttribute("aria-pressed"));
                    SeleniumLib.takeAScreenShot("PanelsPage.jpg");
                    return false;
                }
            }
            if (expectedButton.equalsIgnoreCase("Incomplete")) {
                seleniumLib.clickOnWebElement(incompleteButton);
                if (!"true".equalsIgnoreCase(incompleteButton.getAttribute("aria-pressed"))) {
                    Debugger.println("Tick marked not found for " + expectedButton + incompleteButton.getAttribute("aria-pressed"));
                    SeleniumLib.takeAScreenShot("PanelsPage.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels page: verifyButtonAsCompletedByClickingInPanelsPage " + exp);
            SeleniumLib.takeAScreenShot("PanelsPagePenetranceButtons.jpg");
            return false;
        }
    }

    public void deselectTheSelectedPanels() {
        try {
            for (int i = suggestedPanelsList.size() - 1; i >= 0; i--) {
                seleniumLib.clickOnWebElement(suggestedPanelsList.get(i));
            }
        } catch (Exception exp) {
            Debugger.println("PanelsPage: deselectTheSelectedPanels, Selected panels not found." + exp);
            SeleniumLib.takeAScreenShot("PanelsPageSelectedPanelsDeselection.jpg");
        }
    }

    public boolean verifyTheDeselectedPanels() {
        try {
            for (int i = 0; i < deselectedPanelsList.size(); i++) {
                if (!seleniumLib.isElementPresent(deselectedPanelsList.get(i))) {
                    Debugger.println("Deselected element not present for " + deselectedPanelsList.get(i));
                    return false;
                }
            }
            Debugger.println("Deselect and verify successfully.");
            return true;
        } catch (Exception exp) {
            Debugger.println("PanelsPage: deselectTheSelectedPanels, Deselected panels not found." + exp);
            SeleniumLib.takeAScreenShot("PanelsPageDeselectedPanels.jpg");
            return false;
        }
    }

    public boolean verifyPenetranceTitle(String expSubtitle) {
        try {
            if(!Wait.isElementDisplayed(driver,penetranceTitle,10)){
                Debugger.println("Section "+expSubtitle+" not present in Panels Landing Page\n"+driver.getCurrentUrl());
                SeleniumLib.takeAScreenShot("PanelsPageSection.jpg");
                return false;
            }
            String actualSubTitle = penetranceTitle.getText();
            if(!actualSubTitle.equalsIgnoreCase(expSubtitle)){
                Debugger.println("Section title mismatch Panels Landing Page:Actual:"+actualSubTitle+",Expected:"+expSubtitle);
                SeleniumLib.takeAScreenShot("PanelsPageSection.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception in verifying verifyPenetranceTitle:" + exp);
            SeleniumLib.takeAScreenShot("PanelsPageSection.jpg");
            return false;
        }
    }

    public boolean verifyPenetranceIntroMessage(String expMessage) {
        try {

            if(!Wait.isElementDisplayed(driver,penetranceIntroMessage,10)){
                Debugger.println("PanelAssignerIntoMessage Not displayed.");
                SeleniumLib.takeAScreenShot("PenetranceIntroMessage.jpg");
                return false;
            }
            String actualMessage = penetranceIntroMessage.getText();
            if(!actualMessage.contains(expMessage)){
                Debugger.println("PenetranceIntoMessage mismatch. Expected:"+expMessage+"\nActual:"+actualMessage);
                SeleniumLib.takeAScreenShot("PenetranceIntroMessage.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PanelsPage: Penetrance IntroMessage" + exp);
            SeleniumLib.takeAScreenShot("PenetranceIntroMessage.jpg");
            return false;
        }
    }

    public boolean verifySuggestedPanels() {
        try {
            if (suggestedPanelsList.size() == 0) {
                Debugger.println("No panels have been suggested based on the CI search ");
                SeleniumLib.takeAScreenShot("SuggestedPanels.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying verifySuggestedPanels:" + exp);
            SeleniumLib.takeAScreenShot("SuggestedPanels.jpg");
            return false;
        }
    }
    public boolean verifySuggestedPanelsLinkToPanelApp() {
        try {
            if (suggestedPanelsLinkToPanelApp.size() == 0) {
                Debugger.println("No panels have been suggested with link to PanelApp ");
                SeleniumLib.takeAScreenShot("PanelsLinkToPanelApp.jpg");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying verifySuggestedPanelsLinkToPanelApp:" + exp);
            SeleniumLib.takeAScreenShot("PanelsLinkToPanelApp.jpg");
            return false;
        }
    }
    public boolean verifyInAddedPanelsList(String addedPanel) {
        try {
            boolean isPresent = false;
            String actPanel = "";
            if(addedPanelsList.size() < 1){
                Wait.seconds(5);
            }
            for (int i = 0; i < addedPanelsList.size(); i++) {
                actPanel = addedPanelsList.get(i).getText();
                if (actPanel.contains(addedPanel)) {
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("Added Panel :"+addedPanel+" not present under Added Panels section."+driver.getCurrentUrl());
            }
            return isPresent;
        } catch (Exception exp) {
            Debugger.println("Panels page: addedPanelsList, Element not found." + exp);
            SeleniumLib.takeAScreenShot("PanelsPageAddedPanels.jpg");
            return false;
        }
    }

    public boolean verifyDefaultStatusOfPenetranceButton(String expectedButton) {
        try {
            if (expectedButton.equalsIgnoreCase("complete")) {
                if (!"true".equalsIgnoreCase(completeButton.getAttribute("aria-pressed"))) {
                    Debugger.println("Complete Button expected as Selected, but not.");
                    SeleniumLib.takeAScreenShot("PenatranceStatus.jpg");
                    return false;
                }
            }else if (expectedButton.equalsIgnoreCase("Incomplete")) {
                if (!"true".equalsIgnoreCase(incompleteButton.getAttribute("aria-pressed"))) {
                    Debugger.println("InComplete Button expected as Selected, but not.");
                    SeleniumLib.takeAScreenShot("PenatranceStatus.jpg");
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels page: verifyDefaultStatusOfPenetranceButton " + exp);
            SeleniumLib.takeAScreenShot("PenatranceStatus.jpg");
            return false;
        }
    }

    public boolean verifyThePanelAssignerIntoMessage(String expMessage) {
        try {
            Wait.forElementToBeDisplayed(driver, panelsPageIntroMessage2);
            if(!Wait.isElementDisplayed(driver,panelsPageIntroMessage2,10)){
                Debugger.println("PanelAssignerIntoMessage Not displayed.");
                SeleniumLib.takeAScreenShot("PanelsIntroMessage.jpg");
                return false;
            }
            for (int i = 0; i < panelsPageIntroMessage.size(); i++) {
                if (panelsPageIntroMessage.get(i).getText().contains(expMessage)) {
                    return true;
                }
            }
            return false;

        } catch (Exception exp) {
            Debugger.println("Exception from verifyThePanelAssignerIntoMessage:" + exp);
            SeleniumLib.takeAScreenShot("PanelsIntroMessage.jpg");
            return false;
        }
    }

    public boolean verifyThePresenceOfSuggestedPanelsSection(String sectionTitle) {
        try {
            if(panelSubtitles.size() == 0){
                Debugger.println("PanelAssigner Suggestion Not displayed.");
                SeleniumLib.takeAScreenShot("PanelsSuggestion.jpg");
                return false;
            }
            String actualMessage = "";
            boolean isPresent = false;
            for(int i=0; i<panelSubtitles.size(); i++){
                actualMessage = panelSubtitles.get(i).getText();
                if(actualMessage.contains(sectionTitle)){
                    isPresent = true;
                    break;
                }
            }
            if(!isPresent){
                Debugger.println("PanelAssigner Suggestion Not displayed.");
                SeleniumLib.takeAScreenShot("PanelsSuggestion.jpg");
                return false;
            }
            return isPresent;

        } catch (Exception exp) {
            Debugger.println("Exception in verifying verifyThePresenceOfSection:" + exp);
            SeleniumLib.takeAScreenShot("PanelsSuggestion.jpg");
            return false;
        }
    }

    public boolean verifyNoSuggestedPanels(String message) {
        try {
            if (!(suggestedPanelsList.size() == 0)) {
                Debugger.println("Suggested Panels are displayed.");
                SeleniumLib.takeAScreenShot("NoSuggestedPanels.jpg");
                return false;
            }
            Wait.forElementToBeDisplayed(driver,noSuggestedPanels);
            if(!noSuggestedPanels.getText().equalsIgnoreCase(message)){
                Debugger.println("Panels have been suggested on Page.");

            }
            return  true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifying verifyNoSuggestedPanels:" + exp);
            SeleniumLib.takeAScreenShot("NoSuggestedPanels.jpg");
            return false;
        }
    }


    public boolean verifyMessageInAddPanels(String message) {
        try {
            if (!seleniumLib.isElementPresent(addPanelsHeader)) {
                Debugger.println("Add Panels header is not displayed.");
                SeleniumLib.takeAScreenShot("AddPanelHeaderNotPresent.jpg");
                return false;
            }
            if(!addPanelsMessage.getText().equalsIgnoreCase(message)){
                Debugger.println("Add Panels actual message "+addPanelsMessage.getText()+" but expected "+message);
                SeleniumLib.takeAScreenShot("PanelMessageNotPresent.jpg");
                return false;
            }
            return  true;
        } catch (Exception exp) {
            Debugger.println("Exception from verifyAddPanelsSection:" + exp);
            SeleniumLib.takeAScreenShot("PanelMessageNotPresent.jpg");
            return false;
        }
    }

}//end