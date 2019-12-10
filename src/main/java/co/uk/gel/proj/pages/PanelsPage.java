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
    @FindBy(xpath = "//h3[contains(text(),'Add another panel')]")
    public WebElement addAnotherPanel;

    @FindBy(xpath = "//input[@placeholder='e.g. Adult solid tumours for rare disease']")
    public WebElement panelsSearchFieldPlaceHolder;

    @FindBy(xpath = "//div[@class='styles_search-input__icon__3511W search-input__icon--search']")
    public WebElement panelsSearchIcon;

    @FindBy(xpath = "//input[contains(@placeholder,'Adult solid tumours')]/following::span[contains(@class,'select-panel__name')]")
    public List<WebElement> panelsSuggestionList;


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

    @FindBy(xpath = "/span[contains(@class,'select-panel__name')]")
    public WebElement selectedPanels;

    @FindBy(xpath = "//h3[text()='Added panels']/following::span[contains(@class,'select-panel__name')]")
    public List<WebElement> selectedPanelsList;

    @FindBy(xpath = "//h3[text()='Suggestions based on the clinical information']")
    public WebElement suggestedPanelTitle;

    @FindBy(xpath = "//ul[starts-with(@class,'styles_panel-list__')]")
    public WebElement suggestedPanelList;


    public boolean selectedPanels() {
        try {
            if (seleniumLib.isElementPresent(selectedPanels)) {
                Debugger.println("No Panel Selected");
            }
            return true;
        } catch (Exception exp) {
            Debugger.println("Panels page: selectedPanels: " + exp + "panel not selected");
            return false;
        }
    }

    public boolean addedPanelsList() {

        Debugger.println("panelsSearchAndSelection");
        Wait.forElementToBeDisplayed(driver, addAnotherPanel);
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
      }catch (Exception exc){
          Debugger.println("Not found List of panels" + exc);
                return false;
            }
    }

    @FindBy(xpath = "//p[@class='styles_text__1aikh styles_text--5__203Ot styles_ordering-entity__sub-title-copy__3QZ9_']")
    public WebElement introMessageOnRequestingOrganisation;

    public boolean verifyTheIntroMessage(String introMessage) {
        Debugger.println("Intro Message on requesting organization");
        try {
            Wait.forElementToBeDisplayed(driver, introMessageOnRequestingOrganisation);
            Assert.assertEquals(introMessage, introMessageOnRequestingOrganisation.getText());
        return true;
        } catch (Exception exp) {
            Debugger.println("Requesting Organisation page: Intro message: " + exp + "Message Not Matched");
            return false;
        }

    }

    @FindBy(xpath = "//input[@placeholder='e.g. Dorset County Hospital NHS Foundation Trust, Imperial College Healthcare NHS Trust']")
    public WebElement hintTextInSearchBoxOnRequestingOrganisation;

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
    public boolean verifyPresenceOfSuggestedPanels(){
        try{
            if(!Wait.isElementDisplayed(driver,suggestedPanelTitle,50)){
                Debugger.println("Suggested Panel Title expected, but not present.");
                return false;
            }
            if(!Wait.isElementDisplayed(driver,suggestedPanelList,10)){
                Debugger.println("Suggested Panel List expected, but not present.");
                return false;
            }
            return true;
        }catch(Exception exp){
            Debugger.println("Exception from verifying presence of Suggested Panels:"+exp);
            SeleniumLib.takeAScreenShot("PanelsSuggested.jpg");
            return false;
        }
    }
}//end