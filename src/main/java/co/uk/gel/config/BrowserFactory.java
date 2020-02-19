package co.uk.gel.config;

import co.uk.gel.proj.util.Debugger;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.continuumsecurity.proxy.ScanningProxy;
import net.continuumsecurity.proxy.Spider;
import net.continuumsecurity.proxy.ZAProxyScanner;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BrowserFactory {
    WebDriver driver;

    private final static String ZAP_PROXYHOST = "localhost";
    private final static int ZAP_PROXYPORT = 9191;
    private final static String ZAP_APIKEY = null;
    private final static String CHROME_DRIVER_PATH = "/Users/krishanshukla/Library/Application Support/ZAP/webdriver/macos/64/chromedriver";
    private final static String CHROME_DRIVER_UBUNTU = "/usr/lib/chromium-browser/chromedriver";
    private final static String CHROME_DRIVER_PATH_On_TEST_MACHINE = "C:\\Users\\Testing Team\\OWASP ZAP\\webdriver\\windows\\32\\chromedriver.exe";
    private final static String MEDIUM = "MEDIUM";
    private final static String HIGH = "HIGH";
    private ScanningProxy zapScanner;
    private Spider zapSpider;
    private final static String CHROME = "chrome";
    private final static String FIREFOX = "firefox";
    private String OS = null;


    public WebDriver getDriver() {
        return getDriver(BrowserConfig.getBrowser(), true);
    }

    public WebDriver getDriver(String browser,
                               boolean javascriptEnabled) {
        BrowserEnum browserEnum = BrowserEnum.valueOf(browser.toUpperCase());
        switch (browserEnum) {
            case CHROME:
                WebDriverManager.chromedriver().clearPreferences();
                WebDriverManager.chromedriver().setup(); // 30-09-2019 - Added WebDriver Manager to get the Chrome Driver version and download
                driver = getChromeDriver(null, javascriptEnabled);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                driver = getFirefoxDriver(null, javascriptEnabled);
                break;
            case SAFARI:
                driver = getSafariDriver(null, javascriptEnabled);
                break;
            case IE:
                driver = getInternetExplorer(null, javascriptEnabled);
                break;
            case SECUREBROWSER:

                zapScanner = new ZAProxyScanner(ZAP_PROXYHOST, ZAP_PROXYPORT, ZAP_APIKEY);
                zapScanner.clear(); //Start a new session
                zapSpider = (Spider) zapScanner;
                Debugger.println("Created client to ZAP API");
                OS = System.getProperty("os.name").toLowerCase();
                if (OS.indexOf("win") >= 0)
                    driver = createProxyDriver("chrome", createZapProxyConfigurationForWebDriver(), CHROME_DRIVER_PATH_On_TEST_MACHINE);
                else {
                    if (OS.indexOf("linux") >= 0) {
                        driver = createProxyDriver("chrome", createZapProxyConfigurationForWebDriver(), CHROME_DRIVER_UBUNTU );


                    } else
                        driver = createProxyDriver("chrome", createZapProxyConfigurationForWebDriver(), CHROME_DRIVER_PATH);
                }


                //  driver = DriverFactory.createProxyDriver("firefox", createZapProxyConfigurationForWebDriver(), "");
                break;
            default:
                Debugger.println("Invalid Browser information");
                Assert.assertFalse("Browser : " + browser + " is not present in the BrowserEnum", true);
                break;
        }

        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver createProxyDriver(String type, Proxy proxy, String path) {
        return createProxyDriver(type ,proxy , null );
    }

    public static WebDriver createProxyDriver(String type, Proxy proxy, String path , String typeOfOS) {

        if(typeOfOS!=null){
            if (type.equalsIgnoreCase(CHROME)) return createChromeDriver(createProxyCapabilities(proxy), path , "linux");
            else if (type.equalsIgnoreCase(FIREFOX)) return createFirefoxDriver(createProxyCapabilities(proxy) );
            throw new RuntimeException("Unknown WebDriver browser in linux OS selction " + type);
        }
        else {
            if (type.equalsIgnoreCase(CHROME)) return createChromeDriver(createProxyCapabilities(proxy), path);
            else if (type.equalsIgnoreCase(FIREFOX)) return createFirefoxDriver(createProxyCapabilities(proxy));
            throw new RuntimeException("Unknown WebDriver browser: " + type);
        }
    }

    public static WebDriver createChromeDriver(DesiredCapabilities capabilities, String path, String OS) {

        System.out.printf("I am in create createChromeDriver" + "path=" + path + " OS.toString()=");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--ignore-certificate-errors");
        System.setProperty("webdriver.chrome.driver", path);

        if (OS.equalsIgnoreCase("linux")) {
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
            chromeOptions.addArguments("disable-infobars"); // disabling infobars
            chromeOptions.addArguments("--disable-extensions"); // disabling extensions
            chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
            chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
            chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        }


        //  chromeOptions.addArguments("--headless");

        if (capabilities != null) {
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
            return new ChromeDriver(capabilities);
        } else return new ChromeDriver();

    }

    public static WebDriver createChromeDriver(DesiredCapabilities capabilities, String path) {

       return createChromeDriver(capabilities, path, "non-linux");

    }

    public static WebDriver createFirefoxDriver(DesiredCapabilities capabilities) {
        if (capabilities != null) {
            return new FirefoxDriver(capabilities);
        }

        ProfilesIni allProfiles = new ProfilesIni();
        FirefoxProfile myProfile = allProfiles.getProfile("WebDriver");
        if (myProfile == null) {
            File ffDir = new File(System.getProperty("user.dir") + File.separator + "ffProfile");
            if (!ffDir.exists()) {
                ffDir.mkdir();
            }
            myProfile = new FirefoxProfile(ffDir);
        }
        myProfile.setAcceptUntrustedCertificates(true);
        myProfile.setAssumeUntrustedCertificateIssuer(true);
        myProfile.setPreference("webdriver.load.strategy", "unstable");
        if (capabilities == null) {
            capabilities = new DesiredCapabilities();
        }
        capabilities.setCapability(FirefoxDriver.PROFILE, myProfile);
        return new FirefoxDriver(capabilities);
    }

    public static DesiredCapabilities createProxyCapabilities(Proxy proxy) {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("proxy", proxy);
        return capabilities;
    }

    private static Proxy createZapProxyConfigurationForWebDriver() {
        Proxy proxy = new Proxy();
        proxy.setHttpProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
        proxy.setSslProxy(ZAP_PROXYHOST + ":" + ZAP_PROXYPORT);
        return proxy;
    }


    private WebDriver getSafariDriver(Object object,
                                      boolean javascriptEnabled) {
        DesiredCapabilities safariCaps = DesiredCapabilities.safari();
        safariCaps.setCapability("safari.cleanSession", true);
        return new SafariDriver(safariCaps);
    }

    private WebDriver getFirefoxDriver(String userAgent,
                                       boolean javascriptEnabled) {
        return new FirefoxDriver(getFirefoxOptions(userAgent, javascriptEnabled));

    }

    private FirefoxOptions getFirefoxOptions(String userAgent,
                                             boolean javascriptEnabled) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setAcceptUntrustedCertificates(true);
//			profile.setEnableNativeEvents(true);
        profile.shouldLoadNoFocusLib();
        profile.setAssumeUntrustedCertificateIssuer(true);
        profile.setPreference("javascript.enabled", javascriptEnabled);
        String downloadFilepath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
        try {
            File download_loc = new File(downloadFilepath);
            if (!download_loc.exists()) {
                download_loc.mkdirs();
            }
        } catch (Exception exp) {
            System.out.println("Exception in creating download directory..." + exp);
        }
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", downloadFilepath);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/msword, application/json, application/ris, participant_id/csv, image/png, application/pdf, participant_id/html, participant_id/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
        if (null != userAgent) {
            profile.setPreference("general.useragent.override", userAgent);
        }
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        firefoxOptions.setCapability("marionette", true);
        return firefoxOptions;
    }

    //Deprecated Selenium Driver Path 30/09/2019
    /*private WebDriver getChromeDriver(String userAgent, boolean javascriptEnabled) {
        System.setProperty("webdriver.chrome.driver",
                //System.getProperty("user.dir") + File.separator + "drivers/chromedriver.exe");
                System.getProperty("user.dir") + File.separator + "drivers/chromedriver_mac_76v");
        return new ChromeDriver(getChromeOptions(userAgent, javascriptEnabled));
    } */

    // Added the functions for getChromeDriver for WebDriver Manager  30/09/2019..
    private WebDriver getChromeDriver(String userAgent, boolean javascriptEnabled) {
        return new ChromeDriver(getChromeOptions(userAgent, javascriptEnabled));
    }


    private ChromeOptions getChromeOptions(String userAgent,
                                           boolean javascriptEnabled) {
        //Setting default download path for chrome browser
        String downloadFilePath = System.getProperty("user.dir") + File.separator + "downloads" + File.separator;
        File location = new File(downloadFilePath);
        if (!location.exists()) {
            location.mkdirs();
        }
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadFilePath);
        ChromeOptions opts = new ChromeOptions();
        if (null != userAgent) {
            opts.addArguments("user-agent=" + userAgent);
        }
        opts.setExperimentalOption("prefs", chromePrefs);
        if (!javascriptEnabled) {
            opts.addArguments("disable-javascript");
        }
        return opts;
    }

    private WebDriver getInternetExplorer(String UserAgent,
                                          boolean javascriptEnabled) {
        System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator + "drivers/IEDriverServer.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        return driver = new InternetExplorerDriver(capabilities);

    }
}//end


