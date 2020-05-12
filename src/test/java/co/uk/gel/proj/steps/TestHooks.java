package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.lib.Actions;
import co.uk.gel.lib.Wait;
import co.uk.gel.proj.config.AppConfig;
import co.uk.gel.proj.pages.Pages;
import co.uk.gel.proj.util.Debugger;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.ValidatableResponse;
import com.jayway.restassured.specification.RequestSpecification;
import io.cucumber.core.api.Scenario;
import io.cucumber.core.event.Status;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.When;
import net.continuumsecurity.proxy.ScanningProxy;
import net.continuumsecurity.proxy.Spider;
import net.continuumsecurity.proxy.ZAProxyScanner;
import org.apache.commons.exec.util.DebugUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zaproxy.clientapi.core.Alert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class TestHooks extends Pages {

    public static String currentTagName = "";
    public static String currentTags = "";
    public static String currentFeature = "";
    public static String temptagname = "";
    public static boolean new_scenario_feature = false;
    private RequestSpecification request;

    private final static String ZAP_PROXYHOST = "localhost";
    private final static int ZAP_PROXYPORT = 9191;
    private final static String ZAP_APIKEY = null;
    private final static String MEDIUM = "MEDIUM";
    private final static String HIGH = "HIGH";
    private final static String LOW = "LOW";
    private ScanningProxy zapScanner;
    private Spider zapSpider;

    private static String BASE_URL_TS;
    private static String BASE_URL_TO;
    private static String BASE_URL_PA;
    private static String BASE_URL_PP;
    private static String BASE_URL_DS;
    private static String BASE_URL_PEDG;
    private static String BASE_URL_API;
    private static String BASE_URL_PEDT;
    private static String EXCLUDE_URL_FROM_SECURITYSCAN;

    Scenario scenario = null;
    //private MyAppNavigation myApp;
    private final static String[] policyNames = {"directory-browsing", "cross-site-scripting", "sql-injection", "path-traversal", "remote-file-inclusion", "server-side-include",
            "script-active-scan-rules", "server-side-code-injection", "external-redirect", "crlf-injection"};
    int currentScanID;


    public TestHooks(SeleniumDriver driver) {
        super(driver);
    }

    @Before
    public void begininingOfTest(Scenario scenario) {
        this.scenario = scenario;
        currentTagName = scenario.getSourceTagNames().toString();
        currentTags = scenario.getSourceTagNames().toString();
        currentFeature = scenario.getId().split(";")[0];
        if (temptagname.isEmpty() || !(temptagname.equalsIgnoreCase(currentTagName))) {
            Debugger.println("\n" + scenario.getSourceTagNames() + " STARTED");
            temptagname = currentTagName;
            new_scenario_feature = true;
            Debugger.println("FEATURE: " + currentFeature.replaceAll("-", " "));
            Debugger.println("SCENARIO: " + scenario.getName());
        } else {
            new_scenario_feature = false;
        }
        request = RestAssured.with();

        Debugger.println("\n******* Security RUN STARTS " + new java.util.Date() + " *******************************");
        zapScanner = new ZAProxyScanner(ZAP_PROXYHOST, ZAP_PROXYPORT, ZAP_APIKEY);
        zapScanner.clear(); //Start a new session
        zapSpider = (Spider) zapScanner;
        Debugger.println("Created client to ZAP API...Initializing URLS to SCAN");

        BASE_URL_TS = AppConfig.getPropertyValueFromPropertyFile("BASE_URL_TS");
        BASE_URL_TO = AppConfig.getPropertyValueFromPropertyFile("BASE_URL_TO");
        BASE_URL_PA = AppConfig.getPropertyValueFromPropertyFile("BASE_URL_PA");
        BASE_URL_PP = AppConfig.getPropertyValueFromPropertyFile("BASE_URL_PP");
        BASE_URL_DS = AppConfig.getPropertyValueFromPropertyFile("BASE_URL_DS");
        BASE_URL_PEDG = AppConfig.getPropertyValueFromPropertyFile("BASE_URL_PEDG");
        BASE_URL_API = AppConfig.getPropertyValueFromPropertyFile("BASE_URL_API");
        BASE_URL_PEDT = AppConfig.getPropertyValueFromPropertyFile("BASE_URL_PEDT");
        EXCLUDE_URL_FROM_SECURITYSCAN = AppConfig.getPropertyValueFromPropertyFile("EXCLUDE_URL_FROM_SECURITYSCAN");
    }

    @When("user run security scan")
    public void userRunSecurityScan() {
        Debugger.println("Running Security Scan starts at: " + new Date());
        long startTime = System.currentTimeMillis();
        List<String> urlsToSpider = new LinkedList<>();
        urlsToSpider.add(BASE_URL_TS);
        urlsToSpider.add(BASE_URL_TO);
        urlsToSpider.add(BASE_URL_PA);
        urlsToSpider.add(BASE_URL_PP);
        urlsToSpider.add(BASE_URL_DS);
        urlsToSpider.add(BASE_URL_PEDG);
        urlsToSpider.add(BASE_URL_API);
        urlsToSpider.add(BASE_URL_PEDT);
        Debugger.println("Added URL: BASE_URL_TS:" + BASE_URL_TS);
        Debugger.println("Added URL: BASE_URL_TO:" + BASE_URL_TO);
        Debugger.println("Added URL: BASE_URL_PA:" + BASE_URL_PA);
        Debugger.println("Added URL: BASE_URL_PP:" + BASE_URL_PP);
        Debugger.println("Added URL: BASE_URL_DS:" + BASE_URL_DS);
        Debugger.println("Added URL: BASE_URL_PEDG:" + BASE_URL_PEDG);
        Debugger.println("Added URL: BASE_URL_API:" + BASE_URL_API);
        Debugger.println("Added URL: BASE_URL_PEDT:" + BASE_URL_PEDT);

        urlsToSpider.stream().forEach(inciWinciSpider -> spiderWithZap(inciWinciSpider));
        setAlertAndAttackStrength();
        zapScanner.setEnablePassiveScan(true);

        urlsToSpider.stream().forEach(scanBaseURL -> scanWithZap(scanBaseURL));

        List<Alert> alerts = filterAlerts(zapScanner.getAlerts());
        logAlerts(alerts);
        Debugger.println("Logged Alerts");
        try {
            String zapResultXML = "ZAP_ResultOn_" + new SimpleDateFormat("yyyyMMddHHmm'.xml'").format(new Date());
            String zapResultHTML = "ZAP_ResultOn_" + new SimpleDateFormat("yyyyMMddHHmm'.html'").format(new Date());
            writeXmlReport("logs/" + zapResultXML);
            writeHTMLReport("logs/" + zapResultHTML);
        } catch (IOException exp) {
            Debugger.println("Exception in userRunSecurityScan:" + exp);
            exp.printStackTrace();
        }
        Debugger.println("Running security scan completed at:" + new Date());
        long endTime = System.currentTimeMillis();
        Debugger.println("TOTAL TIME TAKEN: " + (endTime - startTime) / (1000 * 60) + " Minutes.");
    }

    public void writeHTMLReport(String path) throws IOException {
        try {
            Debugger.println("SCAN: generateHTMLReport............. ");
            byte[] htmlReport = zapScanner.getHtmlReport();
            Path pathToFile = Paths.get(path);
            Files.createDirectories(pathToFile.getParent());
            Files.write(pathToFile, htmlReport);
        } catch (Exception exp) {
            Debugger.println("Exception from Generating HTML Report." + exp);
        }
    }

    public void writeXmlReport(String path) {
        try {
            Debugger.println("SCAN: generateXmlReport............. ");
            byte[] xmlReport = zapScanner.getXmlReport();
            Path pathToFile = Paths.get(path);
            Files.createDirectories(pathToFile.getParent());
            Files.write(pathToFile, xmlReport);
        } catch (Exception exp) {
            Debugger.println("Exception from Generating XML Report." + exp);
        }
    }

    private void spiderWithZap(String baseURLToSprider) {
        try {
            Debugger.println("spiderWithZap............" + baseURLToSprider);
            if (baseURLToSprider == null) {
                return;
            }
            zapSpider.setThreadCount(5);
            zapSpider.setMaxDepth(5);
            zapSpider.setPostForms(false);
            Debugger.println("Spider with Zap Starts on BASE URL: " + baseURLToSprider);

            zapSpider.spider(baseURLToSprider);
            int spiderID = zapSpider.getLastSpiderScanId();
            int complete = 0;
            long startTime = System.currentTimeMillis();
            Debugger.println("SpiderZAP starting...." + new Date());
            while (complete < 100) {
                complete = zapSpider.getSpiderProgress(spiderID);
                if (complete % 5 == 0) {
                    Debugger.println("Spider with Zap " + complete + "% complete........");
                }
                try {
                    if(complete < 100) {
                        Thread.sleep(120000);//2 minutes
                    }
                } catch (InterruptedException exp) {
                    Debugger.println("Exception from spiderWithZap :" + exp);
                    exp.printStackTrace();
                }
            }
            for (String url : zapSpider.getSpiderResults(spiderID)) {
                Debugger.println("spiderWithZap:FoundURL:" + url);
            }
            long endTime = System.currentTimeMillis();
            Debugger.println("TIME TAKEN: " + (endTime - startTime) / (1000 * 60) + " Minutes.");
        } catch (Exception exp) {
            Debugger.println("Exception from spiderWithZap for URL:" + baseURLToSprider + "\nExp:" + exp);
        }
    }

    public void setAlertAndAttackStrength() {
        try {
            for (String policyName : policyNames) {
                String ids = enableZapPolicy(policyName);
                if (ids == null) {
                    continue;
                }
                for (String id : ids.split(",")) {
                    zapScanner.setScannerAlertThreshold(id, MEDIUM);
                    zapScanner.setScannerAlertThreshold(id, LOW);
                    zapScanner.setScannerAttackStrength(id, HIGH);
                }
                Debugger.println("enableZapPolicy :" + policyName + ":" + ids);
            }
        } catch (Exception exp) {
            Debugger.println("Exception from setAlertAndAttackStrength:" + exp);
        }
    }

    private String enableZapPolicy(String policyName) {
        try {
            String scannerIds = null;
            switch (policyName.toLowerCase()) {
                case "directory-browsing":
                    scannerIds = "0";
                    break;
                case "cross-site-scripting":
                    scannerIds = "40012,40014,40016,40017";
                    break;
                case "sql-injection":
                    scannerIds = "40018";
                    break;
                case "path-traversal":
                    scannerIds = "6";
                    break;
                case "remote-file-inclusion":
                    scannerIds = "7";
                    break;
                case "server-side-include":
                    scannerIds = "40009";
                    break;
                case "script-active-scan-rules":
                    scannerIds = "50000";
                    break;
                case "server-side-code-injection":
                    scannerIds = "90019";
                    break;
                case "remote-os-command-injection":
                    scannerIds = "90020";
                    break;
                case "external-redirect":
                    scannerIds = "20019";
                    break;
                case "crlf-injection":
                    scannerIds = "40003";
                    break;
                case "source-code-disclosure":
                    scannerIds = "42,10045,20017";
                    break;
                case "shell-shock":
                    scannerIds = "10048";
                    break;
                case "remote-code-execution":
                    scannerIds = "20018";
                    break;
                case "ldap-injection":
                    scannerIds = "40015";
                    break;
                case "xpath-injection":
                    scannerIds = "90021";
                    break;
                case "xml-external-entity":
                    scannerIds = "90023";
                    break;
                case "padding-oracle":
                    scannerIds = "90024";
                    break;
                case "el-injection":
                    scannerIds = "90025";
                    break;
                case "insecure-http-methods":
                    scannerIds = "90028";
                    break;
            /*case "parameter-pollution":
                scannerIds = "20014";
                break;*/
                default:
                    throw new RuntimeException("No policy found for: " + policyName);
            }
            if (scannerIds == null) {
                throw new RuntimeException("No matching policy found for: " + policyName);
            }
            zapScanner.setEnableScanners(scannerIds, true);
            return scannerIds;
        } catch (Exception exp) {
            Debugger.println("Exception from enableZapPlicy:" + policyName + "\nExp:" + exp);
            return null;
        }
    }

    private void scanWithZap(String scanBaseURL) {
        try {
            Debugger.println("scanWithZap.START FOR BASE URL:" + scanBaseURL);
            if (scanBaseURL == null) {
                return;
            }
            zapScanner.scan(scanBaseURL);
            zapScanner.excludeFromScanner(EXCLUDE_URL_FROM_SECURITYSCAN);
            currentScanID = zapScanner.getLastScannerScanId();
            int complete = 0;
            while (complete < 100) {
                complete = zapScanner.getScanProgress(currentScanID);
                if (complete % 5 == 0) {
                    Debugger.println("Scan with ZAP " + complete + "% complete......");
                }
                try {
                    if(complete < 100) {
                        Thread.sleep(120000);//wait for 2 minutes
                    }
                } catch (InterruptedException exp) {
                    Debugger.println("Exception from scanWithZap:" + exp);
                    exp.printStackTrace();
                }
            }
            Debugger.println("scanWithZap DONE FOR BASE URL:" + scanBaseURL);
        } catch (Exception exp) {
            Debugger.println("Exception in scanWithZap:" + scanBaseURL + "\nExp:" + exp);
        }
    }

    private void logAlerts(List<Alert> alerts) {
        for (Alert alert : alerts) {
            Debugger.println("Alert: " + alert.getAlert() + " at URL: " + alert.getUrl() + " Parameter: " + alert.getParam() + " CWE ID: " + alert.getCweId());
        }
    }

    /*
        Remove false positives, filter based on risk and reliability
     */
    private List<Alert> filterAlerts(List<Alert> alerts) {
        Debugger.println("filterAlerts...............");
        List<Alert> filtered = new ArrayList<Alert>();
        for (Alert alert : alerts) {
            if (alert.getRisk().equals(Alert.Risk.High) && alert.getConfidence() != Alert.Confidence.Low)
                filtered.add(alert);
        }
        return filtered;
    }

    @Before("@TD_VERSION_INFO")
    public void getNGISVersion() {
        globalBehaviourPage.getNGISVersion();
    }

    @Before("@LOGOUT_BEFORE_TEST")
    public void logoutCurrentSession() {
        homePage.logOutFromApplication();
    }


    @After(order = 0)
    public void tearDown(Scenario scenario) {
        Status scenarioStatus = scenario.getStatus();
        if (!scenarioStatus.toString().equalsIgnoreCase("PASSED")) {
            Debugger.println("TestHooks..Taking ScreenShot........");
            scenario.embed(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
        Debugger.println("STATUS: " + scenarioStatus.name().toUpperCase());
    }


    @After("@Z-LOGOUT")
    public void logOutAndTearDown(Scenario scenario) {
        homePage.logOutFromApplication();
    }

    public void securityScans() {
        if (scenario.isFailed()) {
            Debugger.println("START - Security scan is starting though selenium scenario FAILED.");
            userRunSecurityScan();
            Debugger.println("END - Security scan is starting though selenium scenario failed........");
        } else {
            Debugger.println("Selenium Script of Security scan PASSED for Scenario" + scenario.getName());
        }
    }

    @After("@secuirtyscans, @securityscan, @securitytest, @securityscan_cancer , @securityscan_rd , @securitydebugging,@securityscan_cancer_demo")
    public void securityScansCancer() {
        Debugger.println("TestHooks...START SECURITY SCANS......");
        securityScans();
    }

    @After("@CLEANUP")
    public void cleanUp() {
        //   driver = null;
    }

    public RequestSpecification getRequest() {
        return request;
    }

    public void setRequest(RequestSpecification request) {
        this.request = request;
    }

    public ValidatableResponse getResponse() {
        return response;
    }

    public void setResponse(ValidatableResponse response) {
        this.response = response;
    }


}//end class
