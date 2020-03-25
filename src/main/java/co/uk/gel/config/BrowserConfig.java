package co.uk.gel.config;

import co.uk.gel.proj.util.Debugger;
import groovy.grape.GrapeIvy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserConfig {

    //static Properties config;
    public static String browser;
    public static String browserVersion;
    public static String serverType;
    public static String osName;
    public static String osVersion;

    /*public static void loadConfig() {

        String configFileName = "%s-BrowserConfig.properties";
        String EnvironmentName = System.getProperty("TestEnvironment");
        System.out.println("TestEnvironment: " + EnvironmentName);

        configFileName = String.format(configFileName, EnvironmentName);
        Properties properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try (InputStream resourceStream = loader.getResourceAsStream(configFileName)) {
                properties.load(resourceStream);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        config = properties;
        browser = System.getProperty("browser");
        serverType = System.getProperty("serverType");
        browserVersion = System.getProperty("browserVersion");
        osName = System.getProperty("osName");
        osVersion = System.getProperty("osVersion");
    }*/

    public static String getBrowser() {
        browser = System.getProperty("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "Chrome";
        }
        return browser;
    }

    public static String getServerType() {
        serverType = System.getProperty("serverType");
        if (serverType == null || serverType.isEmpty()) {
            serverType = "Local";
        }
        return serverType;
    }

    public static String getBrowserVersion() {
        browserVersion = System.getProperty("browserVersion");
        if (browserVersion == null || browserVersion.isEmpty()) {
            browserVersion = "68";
        }
        return browserVersion;
    }

    public static String getOsName() {
        osName = System.getProperty("osName");
        if (osName == null || osName.isEmpty()) {
            osName = "Windows";
        }
        return osName;
    }

    public static String getOsVersion() {
        osVersion = System.getProperty("osVersion");
        if (osVersion == null || osVersion.isEmpty()) {
            osVersion = "10";
        }
        return osVersion;
    }

}//end
