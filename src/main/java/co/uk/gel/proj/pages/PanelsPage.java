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

    @FindBy(xpath = "//input[@placeholder='e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust']")
    public WebElement hintTextInSearchBoxOnRequestingOrganisation;

    @FindBy(xpath = "//p[@class='styles_text__1aikh styles_text--5__203Ot styles_ordering-entity__sub-title-copy__3QZ9_']")
    public WebElement introMessageOnRequestingOrganisation;

    @FindBy(xpath = "//h3[contains(text(),'Suggestions')]")
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

    public boolean searchPanelsInSearchBox(String panelResult) {
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
      }catch (Exception exc){
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
            expElements.add(addedPanels);
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

    //for E2EUI-1231
    public boolean completeIncompleteButtonsPresent() {
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
        try{
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
            ArrayList<WebElement> expElements = new ArrayList<WebElement>();
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
        }catch(Exception exp){
            Debugger.println("PanelsPage: deselectTheSelectedPanels, Deselected panels not found." + exp);
            return false;
        }
    }
}//end