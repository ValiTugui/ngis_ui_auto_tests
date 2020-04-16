package co.uk.gel.config;

import co.uk.gel.proj.util.Debugger;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BrowserConfig {

    static Properties config;
    public static String browser;

    public static void loadConfig() {
        if (browser != null) {//Already Loaded, No need to Load again.
            return;
        }

        String configFileName = "%s-BrowserConfig.properties";

        String EnvironmentName = System.getProperty("TestEnvironment");
        System.out.println("\nTestEnvironment: " + EnvironmentName);

        configFileName = String.format(configFileName, EnvironmentName);
        Debugger.println("CONFIG FILE NAME: "+configFileName);
        Properties  properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try (InputStream resourceStream = loader.getResourceAsStream(configFileName)) {
                //  properties.load(new FileInputStream(new File(configFileName)));
                properties.load(resourceStream);
            }

        } catch (IOException e) {
            Debugger.println("Exception in loading Config File.:"+e);
            e.printStackTrace();
            Assert.assertTrue(false);

        }
        config          = properties;
        browser         = properties.getProperty("Browser");
    }
    public static String getBrowser() {
        if(browser == null || browser.isEmpty()){
            loadConfig();
        }
        return browser;
    }

}//end
