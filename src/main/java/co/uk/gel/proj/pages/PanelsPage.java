package co.uk.gel.proj.pages;

import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Click;
import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.models.NGISPatientModel;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.StylesUtils;
import co.uk.gel.proj.util.TestUtils;
import io.cucumber.java.hu.De;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.security.ssl.Debug;


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

//     E2EUI-1278

    @FindBy(xpath = "//h3[contains(text(),'Add an')]")
    public WebElement addAnotherPanel;

    @FindBy(xpath = "//h3[contains(text(),'Added')]")
    public WebElement addedPanels;

    @FindBy(xpath = "//input[@placeholder='e.g. Adult solid tumours for rare disease']")
    public WebElement panelsSearchFieldPlaceHolder;

    @FindBy(xpath = "//div[@class='styles_search-input__icon__3511W search-input__icon--search']")
    public WebElement panelsSearchIcon;

    @FindBy(xpath = "//input[contains(@placeholder,'Adult solid tumours')]/following::span[contains(@class,'select-panel__name')]")
    public List<WebElement> panelsSuggestionList;


    @FindBy(xpath = "//span[contains(@class,'select-panel__name')]")
    public WebElement selectedPanels;

    @FindBy(xpath = "//h3[text()='Added panels']/following::span[contains(@class,'select-panel__name')]")
    public List<WebElement> selectedPanelsList;

    @FindBy(xpath = "//input[@placeholder='e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust']")
    public WebElement hintTextInSearchBoxOnRequestingOrganisation;

    @FindBy(xpath = "//p[@class='styles_text__1aikh styles_text--5__203Ot styles_ordering-entity__sub-title-copy__3QZ9_']")
    public WebElement introMessageOnRequestingOrganisation;

    @FindBy(xpath = "//h3[contains(text(),'Sugg')]")
    public WebElement suggestedPanels;

    @FindBy(xpath = "//p[contains(text(),'No sugg')]")
    public WebElement NosuggestedPanels;

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

    @FindBy(xpath = "//button[text()='Save and continue']")
    public WebElement saveAndContinueButton;

    public boolean panelSearchFieldAndSearchIcon() {
        Debugger.println("panelsSearchFieldAndSearchIcon");
        try {
            if (!seleniumLib.isElementPresent(addAnotherPanel)) {
                Debugger.println("Panels Page:Add another panel:label Not found");
                return false;
            }
            if (!seleniumLib.isElementPresent(panelsSearchFieldPlaceHolder)) {
                Debugger.println("Panels Page:Add another panel:field Not found");
                return false;
            }
            if (!seleniumLib.isElementPresent(panelsSearchIcon)) {
                Debugger.println("Panels Page:Add another panel:Icon Not found");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels Page:Add another panel: Not found" + exp);
            return false;
        }
    }

    public boolean searchPanelsInSearchBox(String panelResult) {
        Debugger.println("panelsSearchFieldAndSearchIcon");
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
        Debugger.println("panelsSearchAndSelection");
        try {
            if (!seleniumLib.isElementPresent(selectedPanels)) {
                Debugger.println("No Panel Selected");
                return false;
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels page: selectedPanels: " + exp + "panel not selected");
            return false;
        }
    }

    public boolean addedPanelsList() {

        Debugger.println("panelsSearchAndSelection");
        Wait.forElementToBeDisplayed(driver, penetranceTitle);
        try {
            List<WebElement> expElements = new ArrayList<WebElement>();
            for (int i = 0; i < expElements.size(); i++) {
                expElements.add(selectedPanelsList.get(i));
                if (!seleniumLib.isElementPresent(expElements.get(i))) {
                    Debugger.println("selected panels list not found");
                    return false;
                }

            }
            return true;
        } catch (Exception exc) {
            Debugger.println("Not found List of panels" + exc);
            return false;
        }
    }

    public boolean verifyTheIntroMessage(String introMessage) {
        Debugger.println("Intro Message on requesting organsation");
        try {
            Wait.forElementToBeDisplayed(driver, introMessageOnRequestingOrganisation);
            Assert.assertEquals(introMessage, introMessageOnRequestingOrganisation.getText());
            return true;
        } catch (Exception exp) {
            Debugger.println("Requesting Organisation page: Intro message: " + exp + "Message Not Matched");
            return false;
        }
    }

    public boolean verifyHintText() {
        Debugger.println("hint text in search box on requesting organsation");
        try {
            Wait.forElementToBeDisplayed(driver, hintTextInSearchBoxOnRequestingOrganisation);
            seleniumLib.isElementPresent(hintTextInSearchBoxOnRequestingOrganisation);
            return true;
        } catch (Exception exp) {
            Debugger.println("Requesting Organisation page: Help text in search box: " + exp);
            return false;
        }
    }

    //    E2EUI-1045
    public boolean verifyPanelsPageFields() {
        try {
            Debugger.println("Verify Fields on Panels page");
            Wait.forElementToBeDisplayed(driver, penetranceTitle);
            List<WebElement> expElements = new ArrayList<WebElement>();
            expElements.add(penetranceTitle);
            expElements.add(suggestedPanels);
            expElements.add(addAnotherPanel);
            expElements.add(addedPanels);
            expElements.add(completeButton);
            expElements.add(incompleteButton);
            expElements.add(visitPanelApp);

            for (int i = 0; i < expElements.size(); i++) {
                if (!seleniumLib.isElementPresent(expElements.get(i))) {
                    Debugger.println("Panels Page:verifyPanelsPageFields: Not found " + expElements.get(i));
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels Page:verifyPanelsPageFields: " + exp);
            return false;
        }
    }

    public boolean clicksOnVisitPanelsAppLink() {
        Debugger.println("clicks On Panel App Link");
        Wait.forElementToBeDisplayed(driver, visitPanelApp);
        seleniumLib.clickOnWebElement(visitPanelApp);

        String mainWindow = driver.getWindowHandle();
        // To handle all new opened window.
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String childWindow = i1.next();

            if (!mainWindow.equalsIgnoreCase(childWindow)) {
                // Switching to Child window
                driver.switchTo().window(childWindow);
                String url = driver.getCurrentUrl();
                Debugger.println("url of panelApp" + url);
                if (seleniumLib.isElementPresent(panelAppTitle)) {
                    if (panelAppTitle.getText().contains("panels")) {
                        Debugger.println("Panels Page: Title found.");
                    } else {
                        Debugger.println("Panels Page: penetrance Title: Not found");
                        return false;
                    }
                }
            }
        }
//  Closing the Child Window.
//        driver.close();
//         Switching to Parent window i.e Main Window.
        driver.switchTo().window(mainWindow);
        return true;
    }

    public boolean changeTheStatusOfPenetrance() {
        try {
            Actions.scrollToTop(driver);
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

    //for E2EUI-1231
    public boolean completeIncompleteButtonsPresent() {
        try {
            seleniumLib.waitForElementVisible(completeButton);
            seleniumLib.isElementPresent(completeButton);
            seleniumLib.isElementPresent(incompleteButton);
            return true;
        } catch (Exception exp) {
            Debugger.println("PanelsPage: Complete Incomplete buttons not present" + exp);
            return false;
        }
    }

    public boolean clickOnButtonsInPanelsPage(String expectedButton) {
        try {
            if (expectedButton.equalsIgnoreCase("complete")) {
                seleniumLib.clickOnWebElement(completeButton);
            }
            if (expectedButton.equalsIgnoreCase("Incomplete")) {
                seleniumLib.clickOnWebElement(incompleteButton);
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels page: click on Buttons: " + exp);
            return false;
        }
    }

    public boolean verifySelectedButton(String expectedButton) {
        try {
            if (expectedButton.equalsIgnoreCase("complete")) {
                if (!"true".equalsIgnoreCase(completeButton.getAttribute("aria-pressed"))) {
                    return false;
                }
            }
            if (expectedButton.equalsIgnoreCase("Incomplete")) {
                if (!"true".equalsIgnoreCase(incompleteButton.getAttribute("aria-pressed"))) {
                    return false;
                }
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("patient choice page: verify selected button " + exp);
            return false;
        }
    }

    public void clickOnSaveAndContinueButtonInPanelsPage() {
        try {
            Wait.forElementToBeDisplayed(driver, saveAndContinueButton);
            Wait.forElementToBeClickable(driver, saveAndContinueButton);
            seleniumLib.clickOnWebElement(saveAndContinueButton);
//            saveAndContinueButton.click();
            while(seleniumLib.isElementPresent(saveAndContinueButton)){
                seleniumLib.clickOnWebElement(saveAndContinueButton);
            }
        } catch (Exception exp) {
            Debugger.println("Exception from ReferralPage:clickSaveAndContinueButton: " + exp);
            SeleniumLib.takeAScreenShot("PanelsPageSaveAndContinue.jpg");
        }
    }

}//end
