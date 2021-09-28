package co.uk.gel.lib;

import co.uk.gel.config.BrowserConfig;
import co.uk.gel.models.ReferralDataModel;
import co.uk.gel.models.ReferralID;
import co.uk.gel.models.Referrals;
import co.uk.gel.models.ReferralsList;
import co.uk.gel.proj.util.Debugger;
import co.uk.gel.proj.util.TestUtils;
import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Point;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

public class SeleniumLib {

    private static WebDriver driver;
    private static boolean HIGHLIGHT = true;
    private static WebElement webElement = null;
    private static List<WebElement> webElementList = null;

    private String strtext;
    public static String ParentWindowID = null;
    static String defaultSnapshotLocation = System.getProperty("user.dir") + File.separator + "target" + File.separator + "NGIS_UI_Snapshots" + File.separator;
    static String referralFileName = "Referrals.json";
    static String referralTextFileName = "ReferralID.txt";

    public SeleniumLib(WebDriver driver) {
        SeleniumLib.driver = driver;
    }

    private static Logger LOGGER = LoggerFactory.getLogger(SeleniumLib.class);

    private static String timeoutErrorMessage(By element) {
        return "Unable to find visibility of element located by " + element + " within " + "10" + " seconds \n "
                + "\nThere several reasons why this may have occurred including: \n" +
                "\n - The page did not finish loading some ui features before the expected timeout (usually related to Ajaxian elements)" +
                "\n - The ui element is legitimately missing from the page due to a bug or feature change " +
                "\n - There was a problem with communication within the Selenium stack" +
                "\nSee image below (if available) for clues.\n If all else fails, RERUN THE TEST MANUALLY! - CPK\n\n";
    }


    public static boolean isClickable(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            final WebElement el = driver.findElement(element);
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;
        } catch (Exception e) {
            Debugger.println("Element is not clickable: " + e);
            return false;
        }
    }

    public static WebElement waitForElementVisible(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 60);
            final WebElement el = driver.findElement(element);

            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {

                    return el.isDisplayed();
                }
            });
            return el;
        }catch(Exception exp){
            return null;
        }
    }

    /**
     * @param element
     * @return
     */
    public WebElement getElement(By element) {
        try {
            webElement = waitForElementVisible(element);
            return webElement;
        } catch (NoSuchElementException e) {
            Debugger.println("[Error]" + element.toString() + " Not Found ");
            return null;
            //throw e;
        }
    }


    /**
     * @param element
     */
    public static void elementHighlight(WebElement element) {
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
                "color: pink; border: 3px solid red;");
        javascript.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");

    }

    /**
     * @param element
     */
    public void clickOnElement(By element) {
        WebElement webele = null;
        try {
            webele = getElement(element);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", webele);

        } catch (Exception exp) {
            try {
                webele.click();
            } catch (Exception exp1) {
                Actions actions = new Actions(driver);
                actions.moveToElement(driver.findElement(element)).click().build().perform();
                //throw exp1;
            }
        }
    }

    public void clickOnWebElement(WebElement webEle) {
       try {
           WebDriverWait wait = new WebDriverWait(driver, 30);//Default waiting
           wait.until(ExpectedConditions.visibilityOf(webEle));
           if(!webEle.isDisplayed()){
               //Waiting for another 30 seconds
               sleepInSeconds(30);
           }
           JavascriptExecutor executor = (JavascriptExecutor) driver;
           executor.executeScript("arguments[0].click();", webEle);

        } catch (Exception exp) {
            try {
                //Debugger.println("Clicking Via JavaScript....");
                elementHighlight(webEle);
                webEle.click();
            } catch (Exception exp1) {
               // Debugger.println("Clicking Via Action....");
                Actions actions = new Actions(driver);
                actions.moveToElement(webEle).click();
            }
        }
    }

    public List<WebElement> getElements(By ele) {
        try {
            waitForElementVisible(driver.findElement(ele));
            highLightElement(ele);
            return driver.findElements(ele);
        } catch (NoSuchElementException exp) {
            return null;
        }
    }

    public void clearValue(By element) {
        try {
            webElement = getWebElement(element);
            webElement.clear();
        } catch (NoSuchElementException e) {
            LOGGER.error("[Error]" + element.toString() + " Not found");
        }
    }

    public void sendValue(By element, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        try {
            webElement = getWebElement(element);
            webElement.clear();
            webElement.sendKeys(value);
        } catch (NoSuchElementException J) {
            throw new NoSuchElementException(timeoutErrorMessage(element) + J);
        } catch (Exception exp) {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            webElement = wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.sendKeys(value);
        }
    }
    public void sendValue(WebElement element, String value) {
        if (value == null || value.isEmpty()) {
            return;
        }
        try {
            element.clear();
            element.sendKeys(value);
        } catch (Exception exp) {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            element = wait.until(ExpectedConditions.elementToBeClickable(element));
            element.sendKeys(value);
        }
    }

    public void focusElement(By element) {
        webElement = getElement(element);
        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        javascript.executeScript("arguments[0].focus();", webElement);
    }

    public void sendKey(By element, Keys key) {
        try {
            webElement = getWebElement(element);
            webElement.sendKeys(key);
        } catch (NoSuchElementException J) {
            throw new NoSuchElementException(timeoutErrorMessage(element) + J);
        }
    }

    public boolean selectFromListByText(WebElement element, String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            new Select(element).selectByVisibleText(text);
            return true;
        } catch (NoSuchElementException e) {
            try {
                Select select = new Select(element);
                if (select == null) {
                    return false;
                }
                List<WebElement> options = select.getOptions();
                for (WebElement option : options) {
                    String originalText = text.trim().replace(" ", "").replace(" ", "").toLowerCase();
                    String expectedString = option.getText().trim().replace(" ", "").toLowerCase();
                    //Debugger.println("Original....."+originalText+",Exp.."+expectedString);
                    if (originalText.equalsIgnoreCase(expectedString)) {
                        select.selectByVisibleText(option.getText());
                        //Debugger.println("Yes..got it..........");
                        return true;
                    }
                }
            } catch (Exception exp) {
                return false;
            }
            return false;
        }
    }

    public boolean optionFromListByText(By element, String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        try {
            webElementList = driver.findElements(element);
            if (webElementList == null) {
                Debugger.println("element list is null " + webElementList);
                return false;
            }
            for (WebElement actWebelement : webElementList) {
                String actualText = text.trim().replace(" ", "").replace(" ", "").toLowerCase();
                String expectedText = actWebelement.getText().trim().replace(" ", "").toLowerCase();
                if (actualText.equalsIgnoreCase(expectedText)) {
                    actWebelement.click();
                    return true;
                }
            }
            return false;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public static void selectFromListByValue(By element, String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        try {
            webElement = getWebElement(element);
            Select select = new Select(webElement);
            select.selectByValue(text);
        } catch (NoSuchElementException e) {
            Debugger.println("element not found  " + element);
        }
    }

    public static boolean IsDisplayed(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement == null) {
                return false;
            }
            elementHighlight(webElement);
            return webElement.isDisplayed();
        } catch (Exception exp) {
            //Debugger.println("Element not Displayed......" + exp + "\nElement..." + element);
            return false;
        }
    }

    public boolean highLightWebElement(WebElement element) {
        try {
            if (element != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                elementHighlight(element);
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            //Debugger.println("[Error]" + element.toString() + "  Not displayed");
            return false;
        }
    }

    public boolean highLightElement(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
                elementHighlight(webElement);
                return true;
            }
            return false;
        } catch (NoSuchElementException e) {
            //Debugger.println("[Error]" + element.toString() + "  Not displayed");
            return false;
        }
    }

    public boolean isElementPresent(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement == null) {
                return false;
            }
            elementHighlight(webElement);
            return webElement.isDisplayed();

        } catch (Exception e) {
            Debugger.println("[Error]" + element.toString() + "  Not displayed");
            return false;
        }
    }
    public boolean isElementClickable(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement == null) {
                return false;
            }
            elementHighlight(webElement);
            return webElement.isEnabled();

        } catch (Exception e) {
            Debugger.println("[Error]" + element.toString() + "  Not Enabled");
            return false;
        }
    }

    public boolean isElementPresent(WebElement element) {
        try {
            sleepInSeconds(5);
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {

        }
        return false;
    }
    public boolean isErrorMessageElementDisplayed(WebElement element,int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            final WebElement el = element;
            wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver driver) {
                    return el.isDisplayed();
                }
            });
            return false;
        }catch(Exception exp){
            return false;
        }
    }


    public void waitForElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        final WebElement el = element;
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return el.isDisplayed();
            }
        });
    }

    /**
     * @param element
     * @return
     */
    public static WebElement wait(By element) {

        FluentWait<WebDriver> wait = new WebDriverWait(driver, 50);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (NoSuchElementException J) {
            throw new NoSuchElementException(timeoutErrorMessage(element) + J);
        } catch (TimeoutException e) {
            throw new TimeoutException(timeoutErrorMessage(element) + e);
        }
        return driver.findElement(element);
    }

    /**
     * @param element
     */
    public boolean JavaScriptClick(By element) {
        try {
            webElement = getWebElement(element);
            if (webElement == null) {
                return false;
            }
            elementHighlight(webElement);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", webElement);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception: SeleniumLib: Javascript Click.." + exp);
            return false;
        }
    }
    public boolean JavaScriptClick(WebElement element) {
        try {
            elementHighlight(element);
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception: SeleniumLib: Javascript Click.." + exp);
            return false;
        }
    }

    public static WebElement getWebElement(By by) {
        try {
            WebElement element = new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(by));
            return element;
        } catch (Exception exp) {
            return null;
        }
    }

    public static void refreshPage() {
        driver.navigate().refresh();
    }
    /**
     * @param element
     * @return
     */
    public String getText(By element) {
        try {
            webElement = waitForElementVisible(element);
            if (webElement == null) {
                return "";
            }
            elementHighlight(webElement);
            strtext = webElement.getText();
            return "" + strtext;

        } catch (Exception ex) {

            webElement = driver.findElement(element);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
            try {
                elementHighlight(webElement);
                strtext = webElement.getText();
            } catch (Exception exp1) {
                return "";
            }
            return strtext;
        }
    }
    public String getText(WebElement element) {
        try {

            elementHighlight(element);
            strtext = element.getText();
            return "" + strtext;

        } catch (Exception ex) {

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            try {
                elementHighlight(element);
                strtext = element.getText();
            } catch (Exception exp1) {
                return "";
            }
            return strtext;
        }
    }

    /**
     * @param i
     */
    public static void sleep(int i) {
        try {
            Thread.sleep(i * 2000);
        } catch (InterruptedException exp) {

        }
    }

    public static void sleepInSeconds(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException exp) {

        }
    }

    public void waitForAjax(int timeoutInSeconds) {
        //Checking active ajax calls by calling jquery.active
        try {
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

                for (int i = 0; i < timeoutInSeconds; i++) {
                    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
                    // return should be a number
                    if (numberOfAjaxConnections instanceof Long) {
                        Long n = (Long) numberOfAjaxConnections;

                        if (n.longValue() == 0L)
                            break;
                    }
                    Thread.sleep(1000);
                }
            } else {
                LOGGER.error("Web driver: " + driver + " cannot execute javascript");
            }
        } catch (InterruptedException e) {
            LOGGER.error("Ajax wait Exception  " + e);
        }
    }

    /**
     *
     */
    public static void ChangeWindow() {
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            driver.switchTo().window(window);
        }
    }
    //File upload logic changed from using Robot script to Selenium option
    public static boolean upload(WebElement element, String path) {
        try {
//            File file = new File(path);
//            if (!file.exists()) {
//                Debugger.println("Specified File does not exist for upload:"+path);
//                return false;
//            }
            Debugger.println("Uploading the file: "+path);
            element.sendKeys(path);
            sleepInSeconds(5);
            Debugger.println("Upload Finished.");
            return true;
        } catch (Exception exp) {
            Debugger.println("Exception from uploading the file: " + exp);
            return false;
        }
    }

    public static boolean isTextPresent(String text) {
        try {
            if (text == null) {
                return false;
            }
            return getVisibleText().contains(text);
        } catch (Exception exp) {
            LOGGER.error("element not found  " + exp);
            return false;
        }
    }

    /**
     * @return
     */
    public static String getVisibleText() {
        try {
            return driver.findElement(By.tagName("body")).getText();
        } catch (Exception exp) {
            return "";
        }
    }
    public static void dismissAllert() {
        if (isAlertPresent()) {
            driver.switchTo().alert().dismiss();
        }
    }

    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public static void authenticateWithAlert(String userName, String password) {
        try {
            ChangeWindow();
            Debugger.println("User name is " + userName);
            Debugger.println("Password is " + password);
            Robot robot = null;
            robot = new Robot();
            driver.findElement(By.id("username")).sendKeys(userName);
            robot.keyPress(KeyEvent.VK_TAB);
            driver.findElement(By.id("password")).sendKeys(password);
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception ex) {
            Debugger.println("Exception in switching to new Tab: " + ex);
        }
    }

    public static void scrollToElement(WebElement element) {
        try {
            if(element == null){
                return;
            }
            Point location = element.getLocation();
            String script = "scroll(" + location.x + "," + (location.y - 120) + ")";
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript(script);
        } catch (Exception e) {

        }
    }
    public static void takeAScreenShot(String filename){
        try{
            if(filename == null || filename.isEmpty()){
                filename = "screenshot";
            }
            if(filename.indexOf(".") == -1){
                filename = filename+".jpg";
            }
            if(!filename.contains("NTS")) {
                String[] today = TestUtils.getCurrentDay();
                if (today != null && today.length == 3) {
                    filename = "T" + today[0] + today[1] + filename;
                }
            }
            File snapLocation = new File(defaultSnapshotLocation);
            if(!snapLocation.exists()){
                snapLocation.mkdirs();
            }
            //Debugger.println("ScreenShotFile:"+filename);
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            FileUtils.copyFile(screenshot, new File(defaultSnapshotLocation+filename));

        }catch(Exception exp){

        }
    }
    public static boolean switchToNewTab(){
        try {
            ((JavascriptExecutor) driver).executeScript("window.open()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size()-1));
            return true;
        }catch(Exception exp){
            Debugger.println("Exception in switching to new Tab: "+exp);
            return false;
        }
    }
    public static boolean closeCurrentWindow(){
        try {
            //Close current tab and move to the previous tab
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.close()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(tabs.size()-1));

            return true;
        }catch(Exception exp){
            Debugger.println("Could not close current window: "+exp);
            return false;
        }
    }
    public static boolean closeCurrentAndMoveToFirstTab(){
        try {
            //Close current tab and move to the previous tab
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.close()");
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            return true;
        }catch(Exception exp){
            Debugger.println("Could not close First Tab: "+exp);
            return false;
        }
    }
    public static boolean switchToFirstTab(){
        try {
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
            return true;
        }catch(Exception exp){
            Debugger.println("Could not close current window: "+exp);
            return false;
        }
    }
    public static boolean drawSignature(WebElement drawArea) {
        try {
            Wait.forElementToBeDisplayed(driver, drawArea);
            Click.element(driver, drawArea);
            Actions builder = new Actions(driver);
            Action drawAction = builder.moveToElement(drawArea, 135, 15) //start points x axis and y axis.
                    .clickAndHold()
                    .moveByOffset(80, 80)
                    .moveByOffset(50, 20)
                    .release()
                    .build();
            drawAction.perform();
            Wait.seconds(1);
            return true;
        }catch(Exception exp){
           // Debugger.println("SeleniumLib: Could not draw Signature: "+exp);
            takeAScreenShot("drawSignature.jpg");
            return false;
        }
    }
    //Created new method, where tooltip on mouseMove need to be validated
    public boolean moveAndClickOn(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
            return true;
        }catch(Exception exp){
            //Debugger.println("Exception in clicking on Element by moving mouse:"+element.toString()+"\n"+exp);
            return false;
        }
    }
    public void moveMouseAndClickOnElement(By element) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(element);
        if(we == null){
            return;
        }
        action.click(we).build().perform();
    }


    public static boolean skipIfBrowserStack(String serverType) {
        return BrowserConfig.getServerType().toUpperCase().equals(serverType);
    }

    public static void writeToJsonFile (String dataToWrite) {
        String nameRead;
        try {
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(new FileReader(referralFileName));
            JsonObject jsonObject = (JsonObject) obj;
            JsonArray msg = (JsonArray)jsonObject.get("referrals");
            Iterator<JsonElement> iterator = msg.iterator();
            while(iterator.hasNext()) {
                nameRead = iterator.next().toString();
            }
            ReferralID referralID = new ReferralID();

            referralID.setReferralID(dataToWrite);
            Gson gson = new Gson();
            String json = gson.toJson(referralID);

            FileWriter file = new FileWriter(referralFileName, false);
            JsonWriter jw = new JsonWriter(file);
            iterator = msg.iterator();

            Referrals referrals = new Referrals();

            while(iterator.hasNext()) {
                referrals.addReferrals(gson.fromJson(iterator.next().toString(), ReferralID.class));
            }
            referrals.addReferrals(referralID);
            gson.toJson(referrals, Referrals.class, jw);
            file.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToTextFile (String dataToWrite) {
        try {
            FileWriter file = new FileWriter(referralTextFileName, true);
            file.write(dataToWrite);
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getNoOfRows(By element) {
        try {
            waitForElementVisible(driver.findElement(element));
            return driver.findElements(element).size();
        } catch (NoSuchElementException exp) {
            return 0;
        }
    }
    public int getNoOfRows(String rowPath){
        By element = By.xpath(rowPath);
        return getNoOfRows(element);
    }
    public int getColumnIndex(By TableHeading, String column_name) {
        List<WebElement> Headings =  getHeadingElements(TableHeading);
        if(Headings == null || Headings.size() == 0){
            return -1;
        }
        int colindex = -1;
        String heading_name = "";
        try{
            highLightWebElement(Headings.get(Headings.size()-1));
            sleepInSeconds(2);
        }catch(Exception exp){

        }
        for (int index = 0; index < Headings.size(); index++) {
            heading_name = Headings.get(index).getText();
            if(column_name.equalsIgnoreCase(heading_name)) {
                colindex = index + 1;
                break;
            }
        }
        return colindex;
    }
    public int getColumnIndex(List<WebElement> Headings, String column_name) {
        if(Headings == null || Headings.size() == 0){
            Debugger.println("Headings NULL...");
        return -1;
    }
        int colindex = -1;
        String heading_name = "";
        try{
            highLightWebElement(Headings.get(Headings.size()-1));
            sleepInSeconds(2);
        }catch(Exception exp){

        }
        for (int index = 0; index < Headings.size(); index++) {
            heading_name = Headings.get(index).getText();
            if(heading_name == null || heading_name.isEmpty()){
                try{
                    highLightWebElement(Headings.get(0));
                    sleepInSeconds(2);
                    heading_name = Headings.get(index).getText();
                }catch(Exception exp){

                }
            }
            if(heading_name == null || heading_name.isEmpty()){
                try{
                    highLightWebElement(Headings.get(Headings.size()-1));
                    sleepInSeconds(2);
                    heading_name = Headings.get(index).getText();
                }catch(Exception exp){

                }
            }
            //Debugger.println("ColumnHead: "+heading_name);
            if(column_name.equalsIgnoreCase(heading_name)) {
                colindex = index + 1;
                break;
            }
        }
        return colindex;
    }
    public List<WebElement> getHeadingElements(By element) {
        try {
            waitForElementVisible(driver.findElement(element));
            return driver.findElements(element);
        } catch (NoSuchElementException exp) {
            exp.printStackTrace();
            // DDFREDebugger.println("SeleniumLib: [Error]" + element.toString() + " Not Found ");
            return null;
        }
    }

    public void doubleClickOperation(WebElement element) {
        try {
            Actions action = new Actions(driver);
            action.doubleClick(element).perform();
        } catch (NoSuchElementException exp) {
            exp.printStackTrace();
        }
    }

    public List<String> scrollTableAndGetHeaders(By tableHeaderElement) {
        try {
            //Requires the /th element of table header to read each column header of the table
            List<WebElement> allHeaders = driver.findElements(tableHeaderElement);
            //Retrieve the column headers
            List<String> headersList = new ArrayList<>();
            int headerSize = allHeaders.size();
            for (int i = 0; i < headerSize; i++) {
                Wait.seconds(2);
                String headerText = allHeaders.get(i).getAttribute("aria-label");
                String[] headerTextArray = headerText.split(":");
                String headerStartText = headerTextArray[0];
                if (headerStartText != null && !headerStartText.isEmpty()) {
                    headersList.add(headerStartText);
                } else {
                    WebElement lastHeader = allHeaders.get(headerSize - 1);
                    if (!lastHeader.isDisplayed()) {
                        moveAndClickOn(lastHeader);
                        Wait.seconds(2);
                        headerText = allHeaders.get(i).getAttribute("aria-label");
                        headerTextArray = headerText.split(":");
                        headerStartText = headerTextArray[0];
                    }
                    headersList.add(headerStartText);
                }
            }
            return headersList;
        } catch (Exception exp) {
            Debugger.println("Exception from scrolling table:" + exp);
            return null;
        }
    }

    public static void writeToTextFileOfName (String fileName,String dataToWrite) {
        try {
            FileWriter file = new FileWriter(fileName, true);
            file.write(dataToWrite);
            file.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToJsonFileOfName(String fileName, String caseType, String referralIdData, List<String> sampleWellIdList,String probandID) {
        try {
            File jsonFile = new File(fileName);
            JsonParser parser = new JsonParser();
            Iterator<JsonElement> iterator;
            ReferralsList referralsList = new ReferralsList();
            FileWriter fileWriter; //= new FileWriter(fileName, false);
            JsonWriter jWriter;//= new JsonWriter(fileWriter);
            Gson gson = new Gson();

            if (!jsonFile.exists()) {
                jsonFile.createNewFile();
                System.out.println(" Creating file..........");
            } else {
                System.out.println(" File exists..........");
                Object obj = parser.parse(new FileReader(fileName));
//                System.out.println("The current file contents as object-" + obj.toString());
                JsonObject jObject = (JsonObject) obj;
                Debugger.println("The current file contents as Json Object-" + jObject);
                JsonArray existingData = (JsonArray) jObject.get("referralsList");
                iterator = existingData.iterator();
                while (iterator.hasNext()) {
                    referralsList.addReferralsInList(gson.fromJson(iterator.next().toString(), ReferralDataModel.class));
                }
            }

            fileWriter = new FileWriter(fileName, false);
            jWriter = new JsonWriter(fileWriter);
        /// Sort the data in proper Format
            ReferralDataModel newReferralData = new ReferralDataModel();
            newReferralData.setCaseType(caseType);
            newReferralData.setReferralId(referralIdData);
            newReferralData.setProbandId(probandID);
            newReferralData.setSampleWellIdList(sampleWellIdList);

            referralsList.addReferralsInList(newReferralData);
            //Writing in the file
            gson.toJson(referralsList, ReferralsList.class, jWriter);
            fileWriter.close();
        } catch (Exception exp) {
            Debugger.println("Exception from Writing to JSON file: "+exp);
        }
    }

    public static String readTextFileInLines (String fileName) {
        try
        {
            File file = new File(fileName);
            System.out.println(file.getAbsolutePath());
            FileReader fileRdr = new FileReader(file);
            BufferedReader br = new BufferedReader(fileRdr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            br.close();
            fileRdr.close();
            return sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}//end

