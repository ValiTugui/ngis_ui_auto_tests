package co.uk.gel.proj.pages;

import co.uk.gel.lib.SeleniumLib;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.util.Debugger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class PanelsPage {

    WebDriver driver;
    SeleniumLib seleniumLib;

    @FindBy(xpath = "//h3[contains(text(),'Add another panel')]")
    public WebElement addAnotherPanel;

    @FindBy(xpath = "//input[@placeholder='e.g. Adult solid tumours for rare disease']")
    public WebElement panelsSearchFieldPlaceHolder;

    @FindBy(xpath = "//div[@class='styles_search-input__icon__3511W search-input__icon--search']")
    public WebElement panelsSearchIcon;


    //    public boolean panelSearchFieldAndSearchIcon() {
//        Debugger.println("panelsSearchFieldAndSearchIcon");
//        try {
//            if (!seleniumLib.isElementPresent(addAnotherPanel)) {
//                Debugger.println("Panels Page:Add another panel:label Not found");
//                return false;
//            }
//            if (!seleniumLib.isElementPresent(panelsSearchFieldPlaceHolder)) {
//                Debugger.println("Panels Page:Add another panel:field Not found");
//                return false;
//            }
//            if (!seleniumLib.isElementPresent(panelsSearchIcon)) {
//                Debugger.println("Panels Page:Add another panel:Icon Not found");
//                return false;
//            }
//            return true;
//        } catch (Exception exp) {
//            Debugger.println("Panels Page:Add another panel: Not found" + exp);
//            return false;
//        }
//    }
    public boolean panelSearchFieldAndSearchIcon() {
        List<String> expElements = new ArrayList<String>();
        expElements.add(addAnotherPanel.getText());
        expElements.add(panelsSearchFieldPlaceHolder.getText());
        expElements.add(panelsSearchIcon.getText());
        Debugger.println(expElements.get(0));
        /*for (int i = 0; i < expElements.size(); i++) {
            if (!seleniumLib.isElementPresent(expElements.get(i))) {
                return false;
            }
        }*/
        return true;
    }
}//end