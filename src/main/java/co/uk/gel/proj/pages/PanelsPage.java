package co.uk.gel.proj.pages;

import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;
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

    @FindBy(xpath = "//h3[contains(text(),'Add an')]")
    public WebElement addAnotherPanel;

    @FindBy(xpath = "//input[@placeholder='e.g. Adult solid tumours for rare disease']")
    public WebElement panelsSearchFieldPlaceHolder;

    @FindBy(xpath = "//div[@class='styles_search-input__icon__3511W search-input__icon--search']")
    public WebElement panelsSearchIcon;

    @FindBy(xpath = "//input[contains(@placeholder,'Adult solid tumours')]/following::span[contains(@class,'select-panel__name')]")
    public List<WebElement> panelsSuggestionList;

    @FindBy(xpath = "//span[contains(@class,'select-panel__name')]")
    public WebElement selectedPanels;

    @FindBy(css = "button[class*='referral-navigation__continue']")
    public WebElement saveAndContinueButton;

    @FindBy(xpath = "//button[contains(text(),'Try again')]")
    public WebElement tryAgain;

    @FindBy(css = "*[class*='helix']")
    public List<WebElement> helix;
    String helixIcon = "*[class*='helix']";

    @FindBy(xpath = "//h3[contains(text(),'Suggestions')]")
    public WebElement suggestedPanels;

    @FindBy(xpath = "//h3[contains(text(),'Penetrance')]")
    public WebElement penetranceTitle;

    @FindBy(xpath = "//button[contains(text(),'Comp')]")
    public WebElement completeButton;

    @FindBy(xpath = "//button[contains(text(),'Incomp')]")
    public WebElement incompleteButton;

    @FindBy(xpath = "//a[@class='styles_panel-assigner__panelapp-link__2u0t7']")
    public WebElement visitPanelApp;

    @FindBy(xpath = "//h1[contains(text(),'panels')]")
    public WebElement panelAppTitle;

    @FindBy(xpath = "//input[@checked]/ancestor::div[contains(@class,'checked')]")
    public List<WebElement> selectedPanelsList;

    @FindBy(xpath = "//div[@class='styles_select-panel__3qIYD']")
    public List<WebElement> deselectedPanelsList;

    public boolean panelSearchFieldAndSearchIcon() {
        try {
            Wait.forElementToBeDisplayed(driver, penetranceTitle);
            if (!seleniumLib.isElementPresent(addAnotherPanel)) {
                Debugger.println("Panels Page:Add another panel, Label not found");
                return false;
            }
            if (!seleniumLib.isElementPresent(panelsSearchFieldPlaceHolder)) {
                Debugger.println("Panels Page:Add another panel, Field not found");
                return false;
            }
            if (!seleniumLib.isElementPresent(panelsSearchIcon)) {
                Debugger.println("Panels Page:Add another panel, Icon not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels Page:Add another panel, Element not found " + exp);
            return false;
        }
    }

    public boolean searchAndAddPanel(String panelResult) {
        try {
            Wait.forElementToBeDisplayed(driver, addAnotherPanel);
            panelsSearchFieldPlaceHolder.sendKeys(panelResult);
            Click.element(driver, panelsSuggestionList.get(new Random().nextInt(panelsSuggestionList.size())));
            return true;
        } catch (Exception exp) {
            Debugger.println("Search and selection of panel" + exp);
            return false;
        }
    }

    public boolean selectedPanels() {
        try {
            Wait.forElementToBeDisplayed(driver, penetranceTitle);
            if (!seleniumLib.isElementPresent(selectedPanels)) {
                Debugger.println("No Panel Selected");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels page: selectedPanels, Element not found. " + exp);
            return false;
        }
    }

    public boolean addedPanelsList() {
        try {
            Wait.forElementToBeDisplayed(driver, penetranceTitle);
            List<WebElement> expElements = new ArrayList<WebElement>();
            for (int i = 0; i < selectedPanelsList.size(); i++) {
                expElements.add(selectedPanelsList.get(i));
            }
            for (int i = 0; i < expElements.size(); i++) {
                if (!seleniumLib.isElementPresent(expElements.get(i))) {
                    Debugger.println("selected panels list not found. " + expElements.get(i));
                    return false;
                }

            }
            return true;
        } catch (Exception exc) {
            Debugger.println("Panels page: addedPanelsList, Element not found." + exc);
            return false;
        }
    }

    public boolean verifyPanelsPageFields() {
        try {
            Wait.forElementToBeDisplayed(driver, penetranceTitle);
            List<WebElement> expElements = new ArrayList<WebElement>();
            expElements.add(penetranceTitle);
            expElements.add(suggestedPanels);
            expElements.add(addAnotherPanel);
            //expElements.add(addedPanels);
            expElements.add(completeButton);
            expElements.add(incompleteButton);
            expElements.add(visitPanelApp);
            for (int i = 0; i < expElements.size(); i++) {
                if (!seleniumLib.isElementPresent(expElements.get(i))) {
                    Debugger.println("Panels Page:verifyPanelsPageFields: Element not present " + expElements.get(i));
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels Page:verifyPanelsPageFields, Element not found. " + exp);
            return false;
        }
    }

    public boolean clicksOnVisitPanelsAppLink() {
        if (!Wait.isElementDisplayed(driver, visitPanelApp, 100)) {
            Debugger.println("Visit Panel App Link not displayed...");
            return false;
        }
        seleniumLib.clickOnWebElement(visitPanelApp);
        seleniumLib.ChangeWindow();
        return true;
    }
    public boolean verifyPanelAppNavigation() {
        //Verify the navigated URL is correct
        Wait.seconds(10);// Waiting to load the new external Link
        String url = driver.getCurrentUrl();
        Debugger.println("Current URL: "+url);
        if (!url.contains("https://panelapp.genomicsengland.co.uk/panels/")) {
            Debugger.println("URL navigated is Wrong: " + url);
            SeleniumLib.closeCurrentWindow();
            return false;
        }
        if (!Wait.isElementDisplayed(driver, panelAppTitle, 20)) {
            Debugger.println("Panels not displayed on the Navigated Page.");
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
                seleniumLib.clickOnWebElement(completeButton);
                Debugger.println("Panels Page:penetrance status: changed from incomplete to complete ");
            } else if ("true".equalsIgnoreCase(completeButton.getAttribute("aria-pressed"))) {
                seleniumLib.clickOnWebElement(incompleteButton);
                Debugger.println("Panels Page:penetrance status: changed from complete to incomplete ");
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("PanelsPage: Exception from changeTheStatusOfPenetrance " + exp);
            return false;
        }
    }

    public boolean verifyThePresenceOfPenetranceOptions() {
        try {
            seleniumLib.waitForElementVisible(completeButton);
            if (!seleniumLib.isElementPresent(completeButton)) {
                Debugger.println("Complete button not found.");
                return false;
            }
            if (!seleniumLib.isElementPresent(incompleteButton)) {
                Debugger.println("Incomplete button not found.");
                return false;
            }
            return true;

        } catch (Exception exp) {
            Debugger.println("PanelsPage: Complete and Incomplete buttons not found" + exp);
            return false;
        }
    }

    public boolean verifyButtonAsCompletedByClickingInPanelsPage(String expectedButton) {
        try {
            if (expectedButton.equalsIgnoreCase("complete")) {
                seleniumLib.clickOnWebElement(completeButton);
                if (!"true".equalsIgnoreCase(completeButton.getAttribute("aria-pressed"))) {
                    Debugger.println("Tick marked not found for " + expectedButton + completeButton.getAttribute("aria-pressed"));
                    return false;
                }
            }
            if (expectedButton.equalsIgnoreCase("Incomplete")) {
                seleniumLib.clickOnWebElement(incompleteButton);
                if (!"true".equalsIgnoreCase(incompleteButton.getAttribute("aria-pressed"))) {
                    Debugger.println("Tick marked not found for " + expectedButton + incompleteButton.getAttribute("aria-pressed"));
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels page: verifyButtonAsCompletedByClickingInPanelsPage " + exp);
            return false;
        }
    }

    public void deselectTheSelectedPanels() {
        try {
             for (int i = selectedPanelsList.size() - 1; i >= 0; i--) {
                seleniumLib.clickOnWebElement(selectedPanelsList.get(i));
            }
        } catch (Exception exp) {
            Debugger.println("PanelsPage: deselectTheSelectedPanels, Selected panels not found." + exp);
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
            return false;
        }
    }
    //Created this method separately for PanelsPage as in PanelsPage,as using from Referral Page was giving errors continuously
    public boolean clicksOnSaveAndContinueButtonOnPanelsPage() {
        try {
            if (!Wait.isElementDisplayed(driver, saveAndContinueButton, 120)) {
                Debugger.println("Save and Continue Button not visible in Panels Page after 120 seconds. Failing.");
                return false;
            }
            seleniumLib.clickOnWebElement(saveAndContinueButton);
            Wait.seconds(2);
            //Again checking for the presence of button as some times , click not happening at first time.
            if (seleniumLib.isElementPresent(saveAndContinueButton)) {//If still present
                seleniumLib.clickOnWebElement(saveAndContinueButton);
            }
            if (seleniumLib.isElementPresent(tryAgain)) {
                seleniumLib.clickOnWebElement(tryAgain);
            }
            if (helix.size() > 0) {
                try {
                    Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
                } catch (TimeoutException texp) {
                    //Still the helix in action, waiting for another 30 seconds.
                    Debugger.println("PanelsPage:clickSaveAndContinueButton, Still helix in action, waiting for another 30 seconds:" + texp);
                    Wait.forElementToDisappear(driver, By.cssSelector(helixIcon));
                }
            }
            return true;
        } catch(StaleElementReferenceException staleExp){
            Debugger.println("SaveAndContinue Stale Exception in PanelsPage...");
            By continueBut = By.xpath("//button[contains(text(),'Save and continue')]");
            if(seleniumLib.isElementPresent(continueBut)){
                Debugger.println("Clicking with new Element....");
                seleniumLib.clickOnElement(continueBut);
                Wait.seconds(10);
                return true;
            }
            return false;
        }catch(Exception exp) {
            Debugger.println("Exception from PanelsPage:clickSaveAndContinueButton: " + exp);
            SeleniumLib.takeAScreenShot("PanelsPageSaveAndContinue.jpg");
            return false;
        }
    }

}//end