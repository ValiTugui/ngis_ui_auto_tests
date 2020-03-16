package co.uk.gel.config;

import co.uk.gel.proj.util.Debugger;
import cucumber.api.java.sl.In;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.net.URL;

import static co.uk.gel.config.BrowserConfig.*;

public class BrowserFactory {
    WebDriver driver;
    public static final String USERNAME = "rexsureshthankas1";
    public static final String AUTOMATE_KEY = "1uFqNqxkLFhYGRcVbVbQ";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    Date date = Calendar.getInstance().getTime();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    String strDate = "NGIS-UI-" + getBrowser() + "-" + dateFormat.format(date);

    public WebDriver getDriver() throws MalformedURLException {
        return getDriver(BrowserConfig.getServerType(), BrowserConfig.getBrowser(), true);
    }

    public WebDriver getDriver(String serverType, String browser,
                               boolean javascriptEnabled) throws MalformedURLException {
        BrowserEnum browserEnum = BrowserEnum.valueOf(browser.toUpperCase());
        ServerTypeEnum serverTypeEnum = ServerTypeEnum.valueOf(serverType.toUpperCase());
        if (serverTypeEnum == ServerTypeEnum.LOCAL) {
            switch (browserEnum) {
                case CHROME:
                    WebDriverManager.chromedriver().clearPreferences();
                    WebDriverManager.chromedriver().setup(); // 30-09-2019 - Added WebDriver Manager to get the Chrome Driver version and download
                    driver = getChromeDriver(null, javascriptEnabled);
                    break;
                case FIREFOX:
                    WebDriverManager.firefoxdriver().clearPreferences();
                    WebDriverManager.firefoxdriver().setup();
                    driver = getFirefoxDriver(null, javascriptEnabled);
                    break;
                case SAFARI:
                    driver = getSafariDriver(null, javascriptEnabled);
                    break;
                case IE:
                    WebDriverManager.iedriver().clearPreferences();
                    WebDriverManager.iedriver().setup();
                    driver = getInternetExplorer(null, javascriptEnabled);
                    break;
                case OPERA:
                    WebDriverManager.operadriver().clearPreferences();
                    WebDriverManager.operadriver().setup();
                    driver = getOpera(null, javascriptEnabled);
                    break;
                case EDGE:
                    WebDriverManager.edgedriver().clearPreferences();
                    WebDriverManager.edgedriver().forceDownload().setup();
                    driver = getEdge(null, javascriptEnabled);
                    break;
                default:
                    Debugger.println("Invalid Browser information");
                    Assert.fail("Browser : " + browser + " is not present in the BrowserEnum");
                    break;
            }
        }
        if (serverTypeEnum == ServerTypeEnum.BROWSERSTACK)
        {
            switch (browserEnum) {
                case CHROME:
                    driver = new RemoteWebDriver(new URL(URL), getChromeOptions(null, javascriptEnabled));
                    break;
                case FIREFOX:
                    driver = new RemoteWebDriver(new URL(URL), getFirefoxOptions(null, javascriptEnabled));
                    break;
                case SAFARI:
                    driver = new RemoteWebDriver(new URL(URL), getsafariOptions(null, javascriptEnabled));
                    break;
                case IE:
                    driver = new RemoteWebDriver(new URL(URL), getInternetExplorerOptions(null, javascriptEnabled));
                    break;
                case OPERA:
                    driver = new RemoteWebDriver(new URL(URL), getOperaOptions(null, javascriptEnabled));
                    break;
                case EDGE:
                    driver = new RemoteWebDriver(new URL(URL), getEdgeOptions(null, javascriptEnabled));
                    break;

                default:
                    Debugger.println("Invalid Browser information");
                    Assert.assertFalse("Browser : "+browser+" is not present in the BrowserEnum",true);
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver getSafariDriver(String userAgent,
                                      boolean javascriptEnabled) {
        return new SafariDriver(getSafariLocalOptions(userAgent, javascriptEnabled));
    }

    private SafariOptions getSafariLocalOptions(String userAgent, boolean javascriptEnabled) {
        SafariOptions safariLocalOptions = new SafariOptions();
        safariLocalOptions.setCapability("safari.options.dataDir", downloadFilepath());
        return safariLocalOptions;
    }


    private SafariOptions getsafariOptions(String userAgent, boolean javascriptEnabled) {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability("project", "NGIS UI Automation");
        safariOptions.setCapability("build", "NGIS UI Browser Stack Safari");
        safariOptions.setCapability("browser", "Safari");
        safariOptions.setCapability("browser_version", getBrowserVersion());
        safariOptions.setCapability("os", getOsName());
        safariOptions.setCapability("os_version", getOsVersion());
        safariOptions.setCapability("resolution", "1920x1080");
        safariOptions.setCapability("name", strDate);
        safariOptions.setCapability("safari.options.dataDir", downloadFilepath());
        return safariOptions;
    }

    private WebDriver getFirefoxDriver(String userAgent,
                                       boolean javascriptEnabled) {
        return new FirefoxDriver(getFirefoxLocalOptions(userAgent, javascriptEnabled));

    }

    private FirefoxOptions getFirefoxLocalOptions(String userAgent,
                                             boolean javascriptEnabled) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir",downloadFilepath());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/msword, application/json, application/ris, participant_id/csv, image/png, application/pdf, participant_id/html, participant_id/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
        if (null != userAgent) {
            profile.setPreference("general.useragent.override", userAgent);
        }
        FirefoxOptions fireFoxLocalOptions = new FirefoxOptions();
        fireFoxLocalOptions.setProfile(profile);
        return fireFoxLocalOptions;
    }

    private FirefoxOptions getFirefoxOptions(String userAgent,
                                             boolean javascriptEnabled) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadFilepath());
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/msword, application/json, application/ris, participant_id/csv, image/png, application/pdf, participant_id/html, participant_id/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
        if (null != userAgent) {
            profile.setPreference("general.useragent.override", userAgent);
        }
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setCapability("project", "NGIS UI Automation");
        firefoxOptions.setCapability("build", "NGIS UI Browser Stack Firefox");
        firefoxOptions.setCapability("browser", "Firefox");
        firefoxOptions.setCapability("browser_version", getBrowserVersion());
        firefoxOptions.setCapability("os", getOsName());
        firefoxOptions.setCapability("os_version", getOsVersion());
        firefoxOptions.setCapability("resolution", "1920x1080");
        firefoxOptions.setCapability("name", strDate);
        firefoxOptions.setProfile(profile);
        return firefoxOptions;
    }

    // Added the functions for getChromeDriver for WebDriver Manager  30/09/2019..
    private WebDriver getChromeDriver(String userAgent, boolean javascriptEnabled) {
        return new ChromeDriver(getChromeLocalOptions(userAgent, javascriptEnabled));
    }

    private ChromeOptions getChromeLocalOptions(String userAgent,
                                           boolean javascriptEnabled) {
        ChromeOptions chromeLocalOptions = new ChromeOptions();
        if (null != userAgent) {
            chromeLocalOptions.addArguments("user-agent=" + userAgent);
        }
        chromeLocalOptions.setExperimentalOption("prefs", downloadPathsetup());
        if (!javascriptEnabled) {
            chromeLocalOptions.addArguments("disable-javascript");
        }
        return chromeLocalOptions;
    }

    private ChromeOptions getChromeOptions(String userAgent,
                                                               boolean javascriptEnabled) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", downloadPathsetup());
        chromeOptions.setCapability("project", "NGIS UI Automation");
        chromeOptions.setCapability("build", "NGIS UI Browser Stack Chrome");
        chromeOptions.setCapability("browser", "Chrome");
        chromeOptions.setCapability("browser_version", getBrowserVersion());
        chromeOptions.setCapability("os", getOsName());
        chromeOptions.setCapability("os_version", getOsVersion());
        chromeOptions.setCapability("resolution", "1920x1080");
        chromeOptions.setCapability("name", strDate);
        return chromeOptions;
    }

    private WebDriver getInternetExplorer(String userAgent, boolean javascriptEnabled) {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        internetExplorerOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        internetExplorerOptions.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        internetExplorerOptions.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "");
        internetExplorerOptions.setCapability(InternetExplorerDriver.EXTRACT_PATH, downloadFilepath());
        return driver = new InternetExplorerDriver(internetExplorerOptions);

    }

    private InternetExplorerOptions getInternetExplorerOptions(String userAgent, boolean javascriptEnabled) {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.setCapability("project", "NGIS UI Automation");
        internetExplorerOptions.setCapability("build", "NGIS UI Browser Stack IE");
        internetExplorerOptions.setCapability("browser", "IE");
        internetExplorerOptions.setCapability("browser_version", getBrowserVersion());
        internetExplorerOptions.setCapability("os", getOsName());
        internetExplorerOptions.setCapability("os_version", getOsVersion());
        internetExplorerOptions.setCapability("resolution", "1920x1080");
        internetExplorerOptions.setCapability("name", strDate);
        return internetExplorerOptions;
    }

    private WebDriver getEdge(String userAgent, boolean javascriptEnabled) {
        //System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + File.separator + "driver/msedgedriver.exe");
        EdgeOptions edgeOptions = new EdgeOptions();
        return driver = new EdgeDriver(edgeOptions);

    }

    private EdgeOptions getEdgeLocalOptions(String userAgent, boolean javascriptEnabled) {
        EdgeOptions edgeLocalOptions = new EdgeOptions();
        edgeLocalOptions.setCapability("prefs", downloadPathsetup());
        return edgeLocalOptions;
    }


    private EdgeOptions getEdgeOptions(String userAgent, boolean javascriptEnabled) {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability("project", "NGIS UI Automation");
        edgeOptions.setCapability("build", "NGIS UI Browser Stack Edge");
        edgeOptions.setCapability("browser", "Edge");
        edgeOptions.setCapability("browser_version", getBrowserVersion());
        edgeOptions.setCapability("os", getOsName());
        edgeOptions.setCapability("os_version", getOsVersion());
        edgeOptions.setCapability("resolution", "1920x1080");
        edgeOptions.setCapability("name", strDate);
        return edgeOptions;
    }

    private WebDriver getOpera(String userAgent, boolean javascriptEnabled) {
        return new OperaDriver(getOperaLocalOptions(userAgent, javascriptEnabled));

    }

    private OperaOptions getOperaLocalOptions(String userAgent, boolean javascriptEnabled) {
        OperaOptions operaLocalOptions = new OperaOptions();
        operaLocalOptions.setCapability("prefs", downloadPathsetup());
        return operaLocalOptions;
    }


    private OperaOptions getOperaOptions(String userAgent, boolean javascriptEnabled) {
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.setCapability("project", "NGIS UI Automation");
        operaOptions.setCapability("build", "NGIS UI Browser Stack Opera");
        operaOptions.setCapability("browser", "Opera");
        operaOptions.setCapability("browser_version", getBrowserVersion());
        operaOptions.setCapability("os", getOsName());
        operaOptions.setCapability("os_version", getOsVersion());
        operaOptions.setCapability("resolution", "1920x1080");
        operaOptions.setCapability("name", strDate);
        return operaOptions;
    }

    private HashMap downloadPathsetup()
    {
        String downloadFilePath = System.getProperty("user.dir") + File.separator +"downloads"+File.separator;
        File location = new File(downloadFilePath);
        if(!location.exists()){
            location.mkdirs();
        }
        HashMap<String, Object> pathPrefs = new HashMap<String, Object>();
        pathPrefs.put("profile.default_content_settings.popups", 0);
        pathPrefs.put("download.default_directory", downloadFilePath);
        return pathPrefs;
    }

    private String downloadFilepath()
    {
        //Setting default download path
        String downloadFilepath = System.getProperty("user.dir") + File.separator +"downloads"+File.separator;
        try{
            File download_loc = new File(downloadFilepath);
            if(!download_loc.exists()){
                download_loc.mkdirs();
            }
        }catch(Exception exp){
            System.out.println("Exception in creating download directory..."+exp);
        }
        return downloadFilepath;
    }
}//end


